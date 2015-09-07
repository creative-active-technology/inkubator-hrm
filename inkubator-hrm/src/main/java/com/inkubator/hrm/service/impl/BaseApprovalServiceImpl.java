package com.inkubator.hrm.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ScheduledMessage;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Order;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ch.lambdaj.Lambda;

import com.inkubator.common.notification.model.SMSSend;
import com.inkubator.common.util.JsonConverter;
import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.exception.BussinessException;
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
import com.inkubator.hrm.web.model.ApprovalPushMessageModel;
import com.inkubator.securitycore.util.UserInfoUtil;
import com.inkubator.webcore.util.FacesUtil;

/**
 *
 * @author rizkykojek
 */
public abstract class BaseApprovalServiceImpl extends IServiceImpl {
	
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
	@Autowired
	private JmsTemplate jmsTemplateApprovalGrowl;
	@Autowired
    private JmsTemplate jmsTemplateSMS;
	@Autowired
    private JsonConverter jsonConverter;
	
	protected abstract void sendingEmailApprovalNotif(ApprovalActivity appActivity) throws Exception;
	
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
		
		List<ApprovalDefinition> listAppDef = approvalDefinitionDao.getAllDataByNameAndProcessType(processName, HRMConstant.APPROVAL_PROCESS, Order.asc("sequence"));		
		return this.checkApprovalProcess(listAppDef, requestByEmployee);
	}
	
	protected ApprovalActivity checkApprovalProcess(List<ApprovalDefinition> listAppDef, String requestByEmployee) throws Exception {		
		
		ApprovalActivity appActivity = null;
		/** Lakukan proses pengecekan approval process hanya jika user yg input bukan ADMINISTRATOR_ROLE dan memiliki list approval definition */
		if(!UserInfoUtil.hasRole(HRMConstant.ADMINISTRATOR_ROLE) && !listAppDef.isEmpty()){ 
			
			//sorting by sequence ASC
			listAppDef = Lambda.sort(listAppDef, Lambda.on(ApprovalDefinition.class).getSequence());
			
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
					appActivity.setLocale(FacesUtil.getFacesContext().getViewRoot().getLocale().toString());
					appActivity.setCreatedTime(new Date());
		        	
					//show growl notification for approver
			    	this.sendApprovalGrowlNotif(appActivity);
			    	
			    	//send sms notification to approver
					this.sendApprovalSmsnotif(appActivity);
					
					break; //keluar dari looping
				}
			}
		}
    	
		return appActivity;
	}
	
	private String getApproverByAppDefinition(ApprovalDefinition appDef, String requestByEmployee) throws Exception{
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
	
	private String getApproverByJabatanId(long jabatanId) throws Exception{
		String userId = StringUtils.EMPTY;
		
		/** jika dalam satu jabatan yg sama terdapat beberapa employee, maka pilih employee berdasarkan joinDate/TMB yg terlama 
		 *  itulah kenapa di order desc "joinDate" */
		List<EmpData> employees = empDataDao.getAllDataByJabatanId(jabatanId, Order.desc("joinDate"));		
		if(!employees.isEmpty()) { //if not empty
			EmpData empData = employees.get(0);	
			
			//if empty throw BussinessException : approver still not have account
			if (empData.getHrmUsers().isEmpty()) {
				throw new BussinessException("loan.approver_still_not_have_account");
			}
			
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
	protected Map<String, Object> approvedAndCheckNextApproval(Long appActivityId, String comment) throws Exception {
		return this.approvedAndCheckNextApproval(appActivityId, null, comment);
	}
	
	protected Map<String, Object> approvedAndCheckNextApproval(Long appActivityId, String pendingDataUpdate, String comment) throws Exception {
		ApprovalActivity approvalActivity = approvalActivityDao.getEntiyByPK(appActivityId);
		
		//check only approval status which is waiting that can be process
    	if(approvalActivity.getApprovalStatus() != HRMConstant.APPROVAL_STATUS_WAITING){
    		throw new BussinessException("approval.error_status_already_changed");
    	}
    	
        /* update APPROVED approval activity */        
        approvalActivity.setApprovalStatus(HRMConstant.APPROVAL_STATUS_APPROVED);
        approvalActivity.setApprovalCommment(comment);
        int approvedCount = approvalActivity.getApprovalCount() + 1; //increment +1
        approvalActivity.setApprovalCount(approvedCount);
        approvalActivity.setApprovalTime(new Date());
        /** kenapa di flush karena kalau pake Global Transaction (data yg di update, tapi di-query lagi, datanya jadi masih mengacu ke lama/before update) */
        approvalActivityDao.updateAndFlush(approvalActivity);
        

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
        	
        	//show growl notification for approverUserId
        	this.sendApprovalGrowlNotif(nextApproval);
        	
        	//send sms notification to approver
    		this.sendApprovalSmsnotif(nextApproval);
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

		ApprovalActivity approvalActivity = approvalActivityDao.getEntiyByPK(appActivityId);
		
		//check only approval status which is waiting that can be process
    	if(approvalActivity.getApprovalStatus() != HRMConstant.APPROVAL_STATUS_WAITING){
    		throw new BussinessException("approval.error_status_already_changed");
    	}
    	
        /* update REJECTED approval activity */
        approvalActivity.setApprovalStatus(HRMConstant.APPROVAL_STATUS_REJECTED);
        approvalActivity.setApprovalCommment(comment);
        int rejectedCount = approvalActivity.getRejectCount() + 1; //increment +1
        approvalActivity.setRejectCount(rejectedCount);
        approvalActivity.setApprovalTime(new Date());
        approvalActivityDao.save(approvalActivity);
        
        
        /** checking process if there is any nextApproval for ApprovalActivity */
        ApprovalActivity nextApproval = this.checkingNextApproval(approvalActivity);
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
        	
        	//show growl notification for approverUserId
        	this.sendApprovalGrowlNotif(nextApproval);
        	
        	//send sms notification to approver
    		this.sendApprovalSmsnotif(nextApproval);
        }
        
        return result;        
    }
	
	/**
     * <p>Method untuk meng-diverted suatu activity, yand dieksekusi dari scheduler. Method ini sekaligus sekaligus akan mengecek apakah terdapat nextApproval nya. 
     * Return-nya berupa Map<String, Object>, nanti di cek jika "isEndOfApprovalProcess" == "false", maka artinya terdapat nextApproval.
     * Tapi jika "isEndOfApprovalProcess" == "true", maka berarti sudah tidak terdapat nextApproval, 
     * sehingga bisa langsung melakukan proses parsing dari json ke entity dan melakukan saving objek entity tersebut
     * </p>
     *
     * <pre>
     * Map<String, Object> result = divertedAndCheckNextApproval(approvalActivityId);
     * if(StringUtils.equals((String) result.get("isEndOfApprovalProcess"), "true")){
     *    lakukan proses parsing json ke entity dan saving entity objek
     * }
     * </pre>
     *
     * @param appActivityId  Approval Activity id
     * @return Map<String, Object> Key value dari map tersebut "isEndOfApprovalProcess" berupa String dan "approvalActivity" berupa objek ApprovalActivity
     */
	protected Map<String, Object> divertedAndCheckNextApproval(Long appActivityId) throws Exception {
		
		ApprovalActivity approvalActivity = approvalActivityDao.getEntiyByPK(appActivityId);
		
		//check only approval status which is waiting that can be process
    	if(approvalActivity.getApprovalStatus() != HRMConstant.APPROVAL_STATUS_WAITING){
    		throw new BussinessException("approval.error_status_already_changed");
    	}
    	
        /* update DIVERTED approval activity */
        approvalActivity.setApprovalStatus(HRMConstant.APPROVAL_STATUS_DIVERTED);
        approvalActivity.setApprovalTime(new Date());
        approvalActivityDao.update(approvalActivity);
        

        /** checking process if there is any nextApproval for ApprovalActivity */
        ApprovalActivity nextApproval = this.checkingNextApproval(approvalActivity);
        HashMap<String, Object> result = new HashMap<String, Object>();        
        if (nextApproval == null) {        	
        	//added approval activity, set statusnya jadi approve
        	ApprovalActivity lastAppActivity = new ApprovalActivity();
        	BeanUtils.copyProperties(approvalActivity, lastAppActivity, new String[]{"id","approvalStatus","sequence"});
        	lastAppActivity.setApprovalStatus(HRMConstant.APPROVAL_STATUS_APPROVED); //set status approved
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
        	
        	//show growl notification for approverUserId
        	this.sendApprovalGrowlNotif(nextApproval);
        	
        	//send sms notification to approver
    		this.sendApprovalSmsnotif(nextApproval);
        }
        
        return result;
    }
	
	private ApprovalActivity checkingNextApproval(ApprovalActivity previousAppActivity) throws Exception{
		return this.checkingNextApproval(previousAppActivity, null);
	}
	
	private ApprovalActivity checkingNextApproval(ApprovalActivity previousAppActivity, String pendingDataUpdate) throws Exception{
		/** create new approval activity (if any)*/
        ApprovalActivity nextApproval = null;
        ApprovalDefinition previousAppDef = previousAppActivity.getApprovalDefinition();

        
        /** 
         * dapatkan nilai approvalOrRejectCount dan minApproverOrRejector, berdasarkan approvalStatus -nya (approved OR reject) dari previousActivity  */
        boolean isCheckingNextDefinition = true; // default true(check approval in next ApprovalDefinition)
        int approvalOrRejectCount = 0;
        int minApproverOrRejector = 0;
        if(Objects.equals(previousAppActivity.getApprovalStatus(), HRMConstant.APPROVAL_STATUS_APPROVED)){
        	approvalOrRejectCount = previousAppActivity.getApprovalCount();
        	minApproverOrRejector = previousAppDef.getMinApprover();
        } else if(Objects.equals(previousAppActivity.getApprovalStatus(), HRMConstant.APPROVAL_STATUS_REJECTED)) {
        	approvalOrRejectCount = previousAppActivity.getRejectCount();
        	minApproverOrRejector = previousAppDef.getMinRejector();
        	/** khusus jika approvalStatus = Reject, maka tidak perlu di check approval di next ApprovalDefinition -nya */
        	isCheckingNextDefinition = false;
        } else if(Objects.equals(previousAppActivity.getApprovalStatus(), HRMConstant.APPROVAL_STATUS_DIVERTED)){
        	approvalOrRejectCount = previousAppActivity.getApprovalCount();
        	minApproverOrRejector = previousAppDef.getMinApprover();
        } else if(Objects.equals(previousAppActivity.getApprovalStatus(), HRMConstant.APPROVAL_STATUS_WAITING)){
        	throw new Exception("Cannot process checkingNextApproval when approval status is still WAITING");
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
        	List<ApprovalDefinition> listAppDef = new ArrayList<ApprovalDefinition>();
            if(previousAppDef.getIsHaveManyToManyRelations()){
            	//ini untuk approval process yang  many to many, ex: leave, overtime
            	listAppDef = approvalDefinitionDao.getAllDataByNameAndProcessTypeAndSpecificNameAndSequenceGreater(previousAppDef.getName(), previousAppDef.getProcessType(), previousAppDef.getSpecificName(), previousAppDef.getSequence());
            } else {
            	//ini untuk approval process yang single, ex: businesstravel, loan, reimbursment
            	listAppDef = approvalDefinitionDao.getAllDataByNameAndProcessTypeAndSequenceGreater(previousAppDef.getName(), previousAppDef.getProcessType(), previousAppDef.getSequence());
            }
            
            /** Looping semua approvalDefinition berdasarkan urutan sequence (if any)
			 *  Looping akan berhenti jika sudah ditemukan approvalActivity yang harus di proses */
			for(ApprovalDefinition appDef: listAppDef){
                String approverUserId = this.getApproverByAppDefinition(appDef, previousAppActivity.getRequestBy());
                
                if(StringUtils.isNotEmpty(approverUserId)){
                	nextApproval = this.createNewApprovalActivity(approverUserId, pendingDataUpdate, appDef, previousAppActivity, 0, 0);
                	approvalActivityDao.save(nextApproval);
                	
                	break; //keluar dari looping
                }
            }
        }
        
        return nextApproval;
	}

	private ApprovalActivity createNewApprovalActivity(String approverUserId, String pendingDataUpdate, ApprovalDefinition appDef, ApprovalActivity previousAppActv){
		//copy value from previous approval activity
		Integer approvalCount = previousAppActv.getApprovalCount();
		Integer rejectCount = previousAppActv.getRejectCount();
		
		return this.createNewApprovalActivity(approverUserId, pendingDataUpdate, appDef, previousAppActv, approvalCount, rejectCount);
	}
			
    private ApprovalActivity createNewApprovalActivity(String approverUserId, String pendingDataUpdate, ApprovalDefinition appDef, ApprovalActivity previousAppActv, Integer approvalCount, Integer rejectCount) {
        ApprovalActivity newEntity = new ApprovalActivity();
        newEntity.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
        newEntity.setApprovalDefinition(appDef);
        newEntity.setApprovedBy(approverUserId);
        newEntity.setApprovalStatus(HRMConstant.APPROVAL_STATUS_WAITING);
        newEntity.setNotificationSend(false);
        newEntity.setApprovalCount(approvalCount);
        newEntity.setRejectCount(rejectCount);
        newEntity.setCreatedTime(new Date());

        //copy value from previous approval activity
        newEntity.setSequence(previousAppActv.getSequence() + 1); //increment +1        
        newEntity.setActivityNumber(previousAppActv.getActivityNumber());
        newEntity.setRequestBy(previousAppActv.getRequestBy());
        newEntity.setRequestTime(previousAppActv.getRequestTime());
        newEntity.setLocale(previousAppActv.getLocale());
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
    protected List<String> getCcEmailAddressesOnApproveOrReject(ApprovalActivity appActivity) throws Exception{
    	//initialization
    	List<String> emailAdresses = new ArrayList<String>();
    	List<ApprovalDefinition> appDefinitions = new ArrayList<ApprovalDefinition>();
    	
    	//get list appDefinitions by process type (only on_approve OR on_reject)
    	Integer approvalStatus = appActivity.getApprovalStatus();
    	if(StringUtils.isNotEmpty(appActivity.getApprovalDefinition().getSpecificName())){
        	//ini untuk approval process yang  many to many, ex: leave, overtime
    		if(approvalStatus == HRMConstant.APPROVAL_STATUS_APPROVED){
        		appDefinitions = approvalDefinitionDao.getAllDataByNameAndProcessTypeAndSpecificName(appActivity.getApprovalDefinition().getName(), HRMConstant.ON_APPROVE_INFO, appActivity.getApprovalDefinition().getSpecificName(), Order.asc("sequence"));    		
        	} else if(approvalStatus == HRMConstant.APPROVAL_STATUS_REJECTED){
        		appDefinitions = approvalDefinitionDao.getAllDataByNameAndProcessTypeAndSpecificName(appActivity.getApprovalDefinition().getName(), HRMConstant.ON_REJECT_INFO, appActivity.getApprovalDefinition().getSpecificName(), Order.asc("sequence"));    		
        	}
    	} else {
    		//ini untuk approval process yang single, ex: businesstravel, loan, reimbursment
    		if(approvalStatus == HRMConstant.APPROVAL_STATUS_APPROVED){
        		appDefinitions = approvalDefinitionDao.getAllDataByNameAndProcessType(appActivity.getApprovalDefinition().getName(), HRMConstant.ON_APPROVE_INFO, Order.asc("sequence"));    		
        	} else if(approvalStatus == HRMConstant.APPROVAL_STATUS_REJECTED){
        		appDefinitions = approvalDefinitionDao.getAllDataByNameAndProcessType(appActivity.getApprovalDefinition().getName(), HRMConstant.ON_REJECT_INFO, Order.asc("sequence"));    		
        	}
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
    
    /** jika approvalStatus masih waiting dan di approval definition membutuhkan sms approval notif, 
     *  maka kirim notif dalam bentuk sms ke approverUserId */
    private void sendApprovalSmsnotif(ApprovalActivity appActivity){
    	if(appActivity.getApprovalStatus() == HRMConstant.APPROVAL_STATUS_WAITING && appActivity.getApprovalDefinition().getSmsNotification()){
	    	
	    	/** di cek apakah approver memiliki phoneNumber yang valid */
    		HrmUser approver = hrmUserDao.getByUserId(appActivity.getApprovedBy());
	    	if(approver != null && StringUtils.isNotEmpty(approver.getPhoneNumber())) {
				final SMSSend mSSend = new SMSSend();
				mSSend.setFrom(HRMConstant.SYSTEM_ADMIN);
				mSSend.setDestination(approver.getPhoneNumber());
				mSSend.setContent("Dear " + approver.getRealName() + " please check your pending tasks. There is an approval that needs to be approved by you.");
				//Send notificatin SMS
				this.jmsTemplateSMS.send(new MessageCreator() {
					@Override
					public Message createMessage(Session session) throws JMSException {
						return session.createTextMessage(jsonConverter.getJson(mSSend));
					}
				});
	    	}
    	}
    }
    
    /** jika approvalStatus masih waiting, maka kirim notif dalam bentuk growl ke approverUserId 
	 *  Untuk pengaturan messagenya, silahkan di lihat di ApprovalRemoteCommand.java */
    private void sendApprovalGrowlNotif(ApprovalActivity appActivity){
    	if(appActivity.getApprovalStatus() == HRMConstant.APPROVAL_STATUS_WAITING){
    		
        	final ApprovalPushMessageModel model = new ApprovalPushMessageModel();
        	model.setApprovalName(appActivity.getApprovalDefinition().getName());
        	model.setApproverUserId(appActivity.getApprovedBy());
        	model.setRequestUserId(appActivity.getRequestBy());
        	model.setApproverFullName(hrmUserDao.getByUserId(appActivity.getApprovedBy()).getEmpData().getBioData().getFullName());
        	model.setRequestFullName(hrmUserDao.getByUserId(appActivity.getRequestBy()).getEmpData().getBioData().getFullName());
        	
        	
        	//send messaging, to trigger growl notif
            jmsTemplateApprovalGrowl.send(new MessageCreator() {
                @Override
                public Message createMessage(Session session) throws JMSException {
                	TextMessage message = session.createTextMessage(jsonConverter.getJson(model));
                	message.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY, 10000); //delay 10 second
                    return message;
                }
            });
    	}    	
    }
    
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void cancelled(long approvalActivityId, String comment) throws Exception {
    	ApprovalActivity appActivity = approvalActivityDao.getEntiyByPK(approvalActivityId);
    	//check only approval status which is waiting that can be process
    	if(appActivity.getApprovalStatus() != HRMConstant.APPROVAL_STATUS_WAITING){
    		throw new BussinessException("approval.error_status_already_changed");
    	}
    	
    	appActivity.setApprovalStatus(HRMConstant.APPROVAL_STATUS_CANCELLED);
    	appActivity.setApprovalCommment(comment);
    	approvalActivityDao.update(appActivity);
    	
    	//send email cancellation
    	this.sendingEmailApprovalNotif(appActivity);
    }

}
