package com.inkubator.hrm.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;

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
	
	protected ApprovalActivity checkApprovalProcess(String processName, String requestByEmployee) throws Exception {
		
		ApprovalActivity appActivity = null;
		List<ApprovalDefinition> listAppDef = approvalDefinitionDao.getAllDataByNameAndProcessType(processName, HRMConstant.APPROVAL_PROCESS, Order.asc("sequence"));
		if(!listAppDef.isEmpty()){ //if not empty
			ApprovalDefinition appDef = listAppDef.get(0);
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
	
	protected GsonBuilder getGsonBuilder(){
		GsonBuilder gsonBuilder = new GsonBuilder();
    	gsonBuilder.serializeNulls();
		gsonBuilder.setDateFormat("dd MMMM yyyy hh:mm");
		gsonBuilder.registerTypeAdapterFactory(HibernateProxyIdOnlyTypeAdapter.FACTORY);
		gsonBuilder.setExclusionStrategies(new EntityExclusionStrategy());
		return gsonBuilder;
	}
	
	protected Map<String, String> approved(Long appActivityId, String comment) throws Exception {

        /* update APPROVED approval activity */
        ApprovalActivity approvalActivity = approvalActivityDao.getEntiyByPK(appActivityId);
        approvalActivity.setApprovalStatus(HRMConstant.APPROVAL_STATUS_APPROVED);
        approvalActivity.setApprovalCommment(comment);
        int approvedCount = approvalActivity.getApprovalCount() + 1; //increment +1
        approvalActivity.setApprovalCount(approvedCount);
        approvalActivity.setApprovalTime(new Date());
        approvalActivityDao.update(approvalActivity);
        

        /** checking process if there is any nextApproval for ApprovalActivity */
        ApprovalActivity nextApproval = this.checkingNextApproval(approvalActivity);
        HashMap<String, String> result = new HashMap<String, String>();        
        if (nextApproval == null) {
        	// jika nextApproval sama dengan null, berarti sudah tidak ada lagi proses approval, lanjut ke saving objek dari "pendingData" json  
        	result.put("status", String.valueOf(HRMConstant.APPROVAL_STATUS_APPROVED));
            result.put("pendingData", approvalActivity.getPendingData());
            result.put("approvalActivityNumber", approvalActivity.getActivityNumber());
        } else {
            // jika nextApproval tidak sama dengan null, maka lanjut ke proses kirim email ke approver
        	result.put("status", String.valueOf(HRMConstant.APPROVAL_STATUS_WAITING));
        	sendingEmailNotification(nextApproval);
        }
        
        return result;
    }
	
	protected Map<String, String> rejected(Long appActivityId, String comment) throws Exception {

        /* update REJECTED approval activity */
        ApprovalActivity approvalActivity = approvalActivityDao.getEntiyByPK(appActivityId);
        approvalActivity.setApprovalStatus(HRMConstant.APPROVAL_STATUS_REJECTED);
        approvalActivity.setApprovalCommment(comment);
        int rejectedCount = approvalActivity.getRejectCount() + 1; //increment +1
        approvalActivity.setRejectCount(rejectedCount);
        approvalActivity.setApprovalTime(new Date());
        approvalActivityDao.save(approvalActivity);
        
        
        /** checking process if there is any nextApproval for ApprovalActivity */
        ApprovalActivity nextApproval = this.checkingNextApproval(approvalActivity);
        HashMap<String, String> result = new HashMap<String, String>();        
        if (nextApproval == null) {
        	// jika nextApproval sama dengan null, berarti sudah tidak ada lagi proses approval, lanjut ke saving objek dari "pendingData" json  
        	result.put("status", String.valueOf(HRMConstant.APPROVAL_STATUS_REJECTED));
            result.put("pendingData", approvalActivity.getPendingData());
            result.put("approvalActivityNumber", approvalActivity.getActivityNumber());
        } else {
            // jika nextApproval tidak sama dengan null, maka lanjut ke proses kirim email ke approver
        	result.put("status", String.valueOf(HRMConstant.APPROVAL_STATUS_WAITING));
        	sendingEmailNotification(nextApproval);
        }
        
        return null;
        
    }
	
	protected void sendingEmailNotification(ApprovalActivity nextApproval){
		//this method will override in child object
	}
	
	private ApprovalActivity checkingNextApproval(ApprovalActivity previousAppActivity){
		/** create new approval activity (if any)*/
        ApprovalActivity nextApproval = null;
        ApprovalDefinition previousAppDef = previousAppActivity.getApprovalDefinition();

        /**
         * cek apakah approver count sudah memenuhi dari minimal approver di approval definition 
         * 1. jika belum memenuhi, maka lanjut ke checking atasannya untuk proses approval-nya 
         * 2. jika sudah memenuhi, maka lanjut ke proses checking approval definition selanjutnya */
        if (previousAppActivity.getApprovalCount() < previousAppDef.getMinApprover()) {

            //proses no. 1
            HrmUser user = hrmUserDao.getByUserId(previousAppActivity.getApprovedBy());
            Jabatan jabatan = user.getEmpData().getJabatanByJabatanId();
            Jabatan parentJabatan = jabatan.getJabatan();
            /**
             * jika approver mempunyai atasan maka lanjut approval ke atasannya,
             * jika tidak punya atasan langsung approve saja(dilewat proses checking)*/
            if (parentJabatan != null) {
                String approverUserId = this.getApproverByJabatanId(parentJabatan.getId());
                nextApproval = this.createNewApprovalActivity(approverUserId, previousAppDef, previousAppActivity);
                approvalActivityDao.save(nextApproval);
            }

        } else {

            //proses no. 2
            List<ApprovalDefinition> listAppDef = approvalDefinitionDao.getAllDataByNameAndProcessTypeAndSequenceGreater(previousAppDef.getName(), previousAppDef.getProcessType(), previousAppDef.getSequence());
            if (listAppDef.size() > 0) {
                ApprovalDefinition appDef = listAppDef.get(0);
                String approverUserId = this.getApproverByAppDefinition(appDef, previousAppActivity.getRequestBy());
                nextApproval = this.createNewApprovalActivity(approverUserId, appDef, previousAppActivity);
                approvalActivityDao.save(nextApproval);
            }
        }
        
        return nextApproval;
	}

    private ApprovalActivity createNewApprovalActivity(String approverUserId, ApprovalDefinition appDef, ApprovalActivity previousAppActv) {
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
        newEntity.setPendingData(previousAppActv.getPendingData());

        return newEntity;
    }

}
