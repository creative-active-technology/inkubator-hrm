package com.inkubator.hrm.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Order;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.ApprovalActivityDao;
import com.inkubator.hrm.dao.ApprovalDefinitionDao;
import com.inkubator.hrm.dao.EmpDataDao;
import com.inkubator.hrm.dao.HrmUserDao;
import com.inkubator.hrm.entity.ApprovalActivity;
import com.inkubator.hrm.entity.ApprovalDefinition;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.HrmUser;
import com.inkubator.hrm.entity.Jabatan;
import com.inkubator.hrm.json.util.EntityExclusionStrategy;
import com.inkubator.hrm.json.util.HibernateProxyIdOnlyTypeAdapter;

/**
 *
 * @author rizkykojek
 */
public class BaseApprovalServiceImpl extends IServiceImpl {
	
	@Autowired
    private ApprovalActivityDao approvalActivityDao;
	@Autowired
	private ApprovalDefinitionDao approvalDefinitionDao;
	@Autowired
	private EmpDataDao empDataDao;
	@Autowired
	private HrmUserDao hrmUserDao;
	@Autowired
	protected JmsTemplate jmsTemplateApproval;
	
	/**
     * <p>Method untuk mengecek apakah di processName/module tersebut terdapat approval proses. 
     * Jika return-nya berupa null, maka module tersebut tidak memerlukan approval proses, tapi jika !=null maka memerlukan proses approval.
     * Selanjutnya nanti di objek yang memanggil method ini, harus menge-set approvalActivity.setPendingData(pendingData) dari entity module tersebut
     * 
     * </p>
     *
     * <pre>
     * ApprovalActivity appActivity = checkApprovalProcess(HRMConstant.BUSINESS_TRAVEL, requestUser.getUserId());
     * if(approvalActivity == null){
     *   businessTravelDao.save(entity);
     * } else {
     *   approvalActivity.setPendingData(gson.toJson(entity));
     *   approvalActivityDao.save(approvalActivity);
     * }
     * </pre>
     *
     * @param processName  ProcessName/module yg terdapat di approvalDefinition
     * @param requestByEmployee  Employee yang me-request/create
     * @return approvalActivity object
     */
	protected ApprovalActivity checkApprovalProcess(String processName, String requestByEmployee) throws Exception {
		
		ApprovalActivity appActivity = null;
		List<ApprovalDefinition> listAppDef = approvalDefinitionDao.getAllDataByNameAndProcessType(processName, HRMConstant.APPROVAL_PROCESS, Order.asc("sequence"));
		if(!listAppDef.isEmpty()){ //if not empty
			
			/** Looping semua approvalDefinition berdasarkan urutan sequence (if any)
			 *  Looping akan berhenti jika sudah ditemukan approvalActivity yang harus di proses */
			for(ApprovalDefinition appDef: listAppDef){
				String approverUserId = this.getApproverByAppDefinition(appDef, requestByEmployee);
				
				if(StringUtils.isNotEmpty(approverUserId)){				
					appActivity = new ApprovalActivity();
					appActivity.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
					appActivity.setApprovalDefinition(appDef);			
					appActivity.setApprovedBy(approverUserId);
					appActivity.setApprovalStatus(HRMConstant.APPROVAL_STATUS_WAITING);
					appActivity.setSequence(1);
					appActivity.setApprovalCount(0);
					appActivity.setRejectCount(0);
					appActivity.setActivityNumber(RandomNumberUtil.getRandomNumber(9));
					appActivity.setNotificationSend(false);
					appActivity.setRequestBy(requestByEmployee);
					appActivity.setRequestTime(new Date());
					
					break; //keluar dari looping
				}
			}
		}
		
		return appActivity;
	}
	
	private String getApproverByAppDefinition(ApprovalDefinition appDef, String requestByEmployee){
		String userId = StringUtils.EMPTY;
		
		if(StringUtils.equals(appDef.getApproverType(), HRMConstant.APPROVAL_TYPE_INDIVIDUAL)){  
			//approver based on individual
			HrmUser approver = appDef.getHrmUserByApproverIndividual();
			userId = approver.getUserId();
			
		} else if(StringUtils.equals(appDef.getApproverType(), HRMConstant.APPROVAL_TYPE_POSITION)){ 
			//approver based on position			
			Jabatan jabatan = appDef.getJabatanByApproverPosition();			
			userId = this.getApproverByJabatanId(jabatan.getId());
			
		} else if(StringUtils.equals(appDef.getApproverType(), HRMConstant.APPROVAL_TYPE_DEPARTMENT)){ 
			//approver based on department, it means approver is his parent/atasan
			HrmUser user = hrmUserDao.getByUserId(requestByEmployee);
			if(user.getEmpData().getJabatanByJabatanId().getJabatan() != null){
				Jabatan parentJabatan = user.getEmpData().getJabatanByJabatanId().getJabatan();
				userId = this.getApproverByJabatanId(parentJabatan.getId());
			}
			
		}
		
		return userId;
	}
	
	private String getApproverByJabatanId(long jabatanId){
		String userId = StringUtils.EMPTY;
		
		/** jika dalam satu jabatan yg sama terdapat beberapa employee, maka pilih employee berdasarkan joinDate/TMB yg terlama 
		 *  itulah kenapa di order desc "joinDate" */
		List<EmpData> employees = empDataDao.getAllDataByJabatanId(jabatanId, Order.desc("joinDate"));		
		if(!employees.isEmpty()) { //if not empty
			EmpData empData = employees.get(0);				
			if(!empData.getHrmUsers().isEmpty()){ //if not empty
				HrmUser approver = empData.getHrmUsers().iterator().next();
				userId = approver.getUserId();					
			}
		}
		
		return userId;
	}
	
	/**
     * <p>Method untuk meng-approved suatu activity sekaligus akan mengecek apakah terdapat nextApproval nya. 
     * Return-nya berupa Map<String, Object>, nanti di cek jika "isEndOfApprovalProcess" == "false", maka artinya terdapat nextApproval.
     * Tapi jika "isEndOfApprovalProcess" == "true", maka berarti sudah tidak terdapat nextApproval, 
     * sehingga bisa langsung melakukan proses parsing dari json ke entity dan melakukan saving objek entity tersebut
     * </p>
     *
     * <pre>
     * Map<String, Object> result = approvedAndCheckNextApproval(approvalActivityId, pendingDataUpdate, comment);
     * if(StringUtils.equals((String) result.get("isEndOfApprovalProcess"), "true")){
     *    lakukan proses parsing json ke entity dan saving entity objek
     * }
     * </pre>
     *
     * @param appActivityId  Approval Activity id
     * @param pendingDataUpdate  Json dari entity yg akan disave(jika terjadi perubahan), jika tidak ada perubahan cukup set "null", nanti method ini akan mengcopy dari value approvalActivity sebelumnya
     * @param comment String
     * @return Map<String, Object> Key value dari map tersebut "isEndOfApprovalProcess" berupa String dan "approvalActivity" berupa objek ApprovalActivity
     */
	protected Map<String, Object> approvedAndCheckNextApproval(Long appActivityId, String pendingDataUpdate, String comment) throws Exception {

        /* update APPROVED approval activity */
        ApprovalActivity approvalActivity = approvalActivityDao.getEntiyByPK(appActivityId);
        approvalActivity.setApprovalStatus(HRMConstant.APPROVAL_STATUS_APPROVED);
        approvalActivity.setApprovalCommment(comment);
        int approvedCount = approvalActivity.getApprovalCount() + 1; //increment +1
        approvalActivity.setApprovalCount(approvedCount);
        approvalActivity.setApprovalTime(new Date());
        approvalActivityDao.update(approvalActivity);
        

        /** checking process if there is any nextApproval for ApprovalActivity */
        ApprovalActivity nextApproval = this.checkingNextApproval(approvalActivity, pendingDataUpdate);
        HashMap<String, Object> result = new HashMap<String, Object>();        
        if (nextApproval == null) {        	
        	//added approval activity, hanya untuk nge-track perubahan terakhir di pendingData
        	//jika tidak ada update di json pending_data maka gunakan pending data yg lama/previous activity
        	ApprovalActivity lastAppActivity = new ApprovalActivity();
        	BeanUtils.copyProperties(approvalActivity, lastAppActivity, new String[]{"id","pendingData","sequence"});
        	String pendingData = StringUtils.isEmpty(pendingDataUpdate) ? approvalActivity.getPendingData() : pendingDataUpdate;
        	lastAppActivity.setPendingData(pendingData);
        	lastAppActivity.setSequence(approvalActivity.getSequence()+1); //increment +1
        	lastAppActivity.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));        	
        	approvalActivityDao.save(lastAppActivity);
        	
        	
        	// jika nextApproval sama dengan null, berarti sudah tidak ada lagi proses approval, lanjut ke saving objek dari "pendingData" json
        	// kirim approval activity yg current untuk diproses saving
        	result.put("isEndOfApprovalProcess", "true");
        	result.put("approvalActivity", lastAppActivity);
        } else {
            // jika nextApproval tidak sama dengan null, maka lanjut ke proses kirim email ke approver
        	// kirim approval activity yg new(next) untuk diproses kirim email
        	result.put("isEndOfApprovalProcess", "false");
        	result.put("approvalActivity", nextApproval); 
        }
        
        return result;
    }
	
	/**
     * <p>Method untuk meng-rejected suatu activity sekaligus akan mengecek apakah terdapat nextApproval nya. 
     * Return-nya berupa Map<String, Object>, nanti di cek jika "isEndOfApprovalProcess" == "false", maka artinya terdapat nextApproval.
     * Tapi jika "isEndOfApprovalProcess" == "true", maka berarti sudah tidak terdapat nextApproval, 
     * sehingga bisa langsung melakukan proses parsing dari json ke entity dan melakukan saving objek entity tersebut
     * </p>
     *
     * <pre>
     * Map<String, Object> result = rejectedAndCheckNextApproval(approvalActivityId, comment);
     * if(StringUtils.equals((String) result.get("isEndOfApprovalProcess"), "true")){
     *    lakukan proses parsing json ke entity dan saving entity objek
     * }
     * </pre>
     *
     * @param appActivityId  Approval Activity id
     * @param comment String
     * @return Map<String, Object> Key value dari map tersebut "isEndOfApprovalProcess" berupa String dan "approvalActivity" berupa objek ApprovalActivity
     */
	protected Map<String, Object> rejectedAndCheckNextApproval(Long appActivityId, String comment) throws Exception {

        /* update REJECTED approval activity */
        ApprovalActivity approvalActivity = approvalActivityDao.getEntiyByPK(appActivityId);
        approvalActivity.setApprovalStatus(HRMConstant.APPROVAL_STATUS_REJECTED);
        approvalActivity.setApprovalCommment(comment);
        int rejectedCount = approvalActivity.getRejectCount() + 1; //increment +1
        approvalActivity.setRejectCount(rejectedCount);
        approvalActivity.setApprovalTime(new Date());
        approvalActivityDao.save(approvalActivity);
        
        
        /** checking process if there is any nextApproval for ApprovalActivity */
        ApprovalActivity nextApproval = this.checkingNextApproval(approvalActivity, null);
        HashMap<String, Object> result = new HashMap<String, Object>();         
        if (nextApproval == null) {
        	// jika nextApproval sama dengan null, berarti sudah tidak ada lagi proses approval, lanjut ke saving objek dari "pendingData" json 
        	// kirim approval activity yg current untuk diproses saving
        	result.put("isEndOfApprovalProcess", "true");
        	result.put("approvalActivity", approvalActivity);
        } else {
            // jika nextApproval tidak sama dengan null, maka lanjut ke proses kirim email ke approver
        	// kirim approval activity yg new(next) untuk diproses kirim email
        	result.put("isEndOfApprovalProcess", "false");
        	result.put("approvalActivity", nextApproval); 
        }
        
        return result;        
    }
	
	private ApprovalActivity checkingNextApproval(ApprovalActivity previousAppActivity, String pendingDataUpdate){
		/** create new approval activity (if any)*/
        ApprovalActivity nextApproval = null;
        ApprovalDefinition previousAppDef = previousAppActivity.getApprovalDefinition();

        
        /** 
         * dapatkan nilai approvalOrRejectCount dan minApproverOrRejector, berdasarkan approvalStatus -nya (approved OR reject) dari previousActivity  */
        boolean isCheckingNextDefinition = true; // default true(check approval in next ApprovalDefinition)
        int approvalOrRejectCount = 0;
        int minApproverOrRejector = 0;
        if(previousAppActivity.getApprovalStatus() == HRMConstant.APPROVAL_STATUS_APPROVED){
        	approvalOrRejectCount = previousAppActivity.getApprovalCount();
        	minApproverOrRejector = previousAppDef.getMinApprover();
        } else if(previousAppActivity.getApprovalStatus() == HRMConstant.APPROVAL_STATUS_REJECTED) {
        	approvalOrRejectCount = previousAppActivity.getRejectCount();
        	minApproverOrRejector = previousAppDef.getMinRejector();
        	/** khusus jika approvalStatus = Reject, maka tidak perlu di check approval di next ApprovalDefinition -nya */
        	isCheckingNextDefinition = false;
        }
        
        /**
         * cek apakah approval/reject count sudah memenuhi dari minimal approver/rejecter di approval definition 
         * 1. jika belum memenuhi, maka lanjut ke checking atasannya untuk proses approval-nya 
         * 2. jika sudah memenuhi, maka lanjut ke proses checking approval definition selanjutnya,
         * khusus jika approvalStatus = Reject, maka jika sudah memenuhi dia tidak perlu di cek next approval definitionnya, langsung dibypass */        
        if (approvalOrRejectCount < minApproverOrRejector) {

            /** proses no. 1*/
            HrmUser user = hrmUserDao.getByUserId(previousAppActivity.getApprovedBy());
            Jabatan jabatan = user.getEmpData().getJabatanByJabatanId();
            Jabatan parentJabatan = jabatan.getJabatan();
            /**
             * jika approver mempunyai atasan maka lanjut approval ke atasannya,
             * jika tidak punya atasan langsung check ke next approval definition-nya (proses no.2) */
            if (parentJabatan != null) {
                String approverUserId = this.getApproverByJabatanId(parentJabatan.getId());
                nextApproval = this.createNewApprovalActivity(approverUserId, pendingDataUpdate, previousAppDef, previousAppActivity);
                approvalActivityDao.save(nextApproval);
                isCheckingNextDefinition = false; //set false, agar tidak perlu di check approval di next ApprovalDefinition nya
            }
        }        
        if (isCheckingNextDefinition) {

        	/** proses no. 2*/
            List<ApprovalDefinition> listAppDef = approvalDefinitionDao.getAllDataByNameAndProcessTypeAndSequenceGreater(previousAppDef.getName(), previousAppDef.getProcessType(), previousAppDef.getSequence());
            
            /** Looping semua approvalDefinition berdasarkan urutan sequence (if any)
			 *  Looping akan berhenti jika sudah ditemukan approvalActivity yang harus di proses */
			for(ApprovalDefinition appDef: listAppDef){
                String approverUserId = this.getApproverByAppDefinition(appDef, previousAppActivity.getRequestBy());
                
                if(StringUtils.isNotEmpty(approverUserId)){
                	nextApproval = this.createNewApprovalActivity(approverUserId, pendingDataUpdate, appDef, previousAppActivity);
                	approvalActivityDao.save(nextApproval);
                	
                	break; //keluar dari looping
                }
            }
        }
        
        return nextApproval;
	}

    private ApprovalActivity createNewApprovalActivity(String approverUserId, String pendingDataUpdate, ApprovalDefinition appDef, ApprovalActivity previousAppActv) {
        ApprovalActivity newEntity = new ApprovalActivity();
        newEntity.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
        newEntity.setApprovalDefinition(appDef);
        newEntity.setApprovedBy(approverUserId);
        newEntity.setApprovalStatus(HRMConstant.APPROVAL_STATUS_WAITING);
        newEntity.setNotificationSend(false);

        //copy value from previous approval activity
        newEntity.setSequence(previousAppActv.getSequence() + 1); //increment +1
        newEntity.setApprovalCount(previousAppActv.getApprovalCount());
        newEntity.setRejectCount(previousAppActv.getRejectCount());
        newEntity.setActivityNumber(previousAppActv.getActivityNumber());
        newEntity.setRequestBy(previousAppActv.getRequestBy());
        newEntity.setRequestTime(previousAppActv.getRequestTime());
        //jika tidak ada update di json pendingDataUpdate maka gunakan pending data yg lama/previous activity
        String pendingData = StringUtils.isEmpty(pendingDataUpdate) ? previousAppActv.getPendingData() : pendingDataUpdate;
        newEntity.setPendingData(pendingData);

        return newEntity;
    }
    
    /**
     * <p>Method untuk mendapatkan List dari emailAddresses yang akan di sendCC, hanya jika approval status adalah APPROVED or REJECTED 
     * </p>
     *
     * <pre>
     * List<String> ccEmailAddresses = getCcEmailAddressesOnApproveOrReject(appActivity);
     * </pre>
     *
     * @param appActivity  Approval Activity id
     * @return comment String
     */
    protected List<String> getCcEmailAddressesOnApproveOrReject(ApprovalActivity appActivity){
    	//initialization
    	List<String> emailAdresses = new ArrayList<String>();
    	List<ApprovalDefinition> appDefinitions = new ArrayList<ApprovalDefinition>();
    	
    	//get list appDefinitions by process type (only on_approve OR on_reject)
    	Integer approvalStatus = appActivity.getApprovalStatus();
    	if(approvalStatus == HRMConstant.APPROVAL_STATUS_APPROVED){
    		appDefinitions = approvalDefinitionDao.getAllDataByNameAndProcessType(appActivity.getApprovalDefinition().getName(), HRMConstant.ON_APPROVE_INFO, Order.asc("sequence"));    		
    	} else if(approvalStatus == HRMConstant.APPROVAL_STATUS_REJECTED){
    		appDefinitions = approvalDefinitionDao.getAllDataByNameAndProcessType(appActivity.getApprovalDefinition().getName(), HRMConstant.ON_REJECT_INFO, Order.asc("sequence"));    		
    	}
    	
    	//get all email address 
    	for(ApprovalDefinition appDefinition:appDefinitions){
    		String userId = this.getApproverByAppDefinition(appDefinition, appActivity.getRequestBy());
    		HrmUser user = hrmUserDao.getByUserId(userId);
    		if(user != null){
    			emailAdresses.add(user.getEmailAddress());
    		}
    	}
    	
    	return emailAdresses;
    }
    
    /**
     * <p>Method untuk mendapatkan GsonBuilder, yang akan digunakan untuk parsing dari entity ke json (ataupun sebaliknya) 
     * </p>
     *
     * <pre>
     * Gson gson = getGsonBuilder().create();
     * </pre>
     *
     * @return GsonBuilder GsonBuilder
     */
    protected GsonBuilder getGsonBuilder(){
		GsonBuilder gsonBuilder = new GsonBuilder();
    	gsonBuilder.serializeNulls();
		gsonBuilder.setDateFormat("dd MMMM yyyy hh:mm");
		gsonBuilder.registerTypeAdapterFactory(HibernateProxyIdOnlyTypeAdapter.FACTORY);
		gsonBuilder.setExclusionStrategies(new EntityExclusionStrategy());
		return gsonBuilder;
	}

}
