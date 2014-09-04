package com.inkubator.hrm.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.GsonBuilder;
import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.ApprovalDefinitionDao;
import com.inkubator.hrm.dao.EmpDataDao;
import com.inkubator.hrm.entity.ApprovalActivity;
import com.inkubator.hrm.entity.ApprovalDefinition;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.HrmUser;
import com.inkubator.hrm.entity.Jabatan;
import com.inkubator.hrm.json.util.EntityExclusionStrategy;
import com.inkubator.hrm.json.util.HibernateProxyTypeAdapter;
import com.inkubator.securitycore.util.UserInfoUtil;

/**
 *
 * @author rizkykojek
 */
public class BaseApprovalServiceImpl extends IServiceImpl {
	
	@Autowired
	private ApprovalDefinitionDao approvalDefinitionDao;
	@Autowired
	private EmpDataDao empDataDao;
	
	protected ApprovalActivity checkApprovalProcess(String processName) throws Exception {
		
		ApprovalActivity appActivity = null;
		List<ApprovalDefinition> listAppDef = approvalDefinitionDao.getAllDataByNameAndProcessType(processName, HRMConstant.APPROVAL_PROCESS, Order.asc("sequence"));
		if(!listAppDef.isEmpty()){ //if not empty
			ApprovalDefinition appDef = listAppDef.get(0);
			String approverUserId = this.getApproverByAppDefinition(appDef);
			
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
				appActivity.setRequestBy(UserInfoUtil.getUserName());
				
			}
		}
		
		return appActivity;
	}
	
	protected String getApproverByAppDefinition(ApprovalDefinition appDef){
		String userId = StringUtils.EMPTY;
		
		if(StringUtils.equals(appDef.getApproverType(), HRMConstant.APPROVAL_TYPE_INDIVIDUAL)){  //approver based on individual
			HrmUser approver = appDef.getHrmUserByApproverIndividual();
			userId = approver.getUserId();
			
		} else if(StringUtils.equals(appDef.getApproverType(), HRMConstant.APPROVAL_TYPE_POSITION)){ //approver based on position			
			Jabatan jabatan = appDef.getJabatanByApproverPosition();			
			userId = this.getApproverByJabatanId(jabatan.getId());
			
		} else if(StringUtils.equals(appDef.getApproverType(), HRMConstant.APPROVAL_TYPE_DEPARTMENT)){ ////approver based on department
			
			
		}
		
		return userId;
	}
	
	protected String getApproverByJabatanId(long jabatanId){
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
		gsonBuilder.setDateFormat("dd MMMM yyyy");
		gsonBuilder.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
		gsonBuilder.setExclusionStrategies(new EntityExclusionStrategy());
		return gsonBuilder;
	}

}
