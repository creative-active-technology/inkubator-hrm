package com.inkubator.hrm.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.ApprovalActivityDao;
import com.inkubator.hrm.dao.ApprovalDefinitionDao;
import com.inkubator.hrm.dao.EmpDataDao;
import com.inkubator.hrm.entity.ApprovalActivity;
import com.inkubator.hrm.entity.ApprovalDefinition;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.HrmUser;
import com.inkubator.hrm.entity.Jabatan;
import com.inkubator.hrm.service.ApprovalActivityService;
import com.inkubator.securitycore.util.UserInfoUtil;

/**
 *
 * @author rizkykojek
 */
@Service(value = "approvalActivityService")
@Lazy
public class ApprovalActivityServiceImpl extends IServiceImpl implements ApprovalActivityService {

	@Autowired
	private ApprovalActivityDao approvalActivityDao;
	@Autowired
	private ApprovalDefinitionDao approvalDefinitionDao;
	@Autowired
	private EmpDataDao empDataDao;
	
	@Override
	public ApprovalActivity getEntiyByPK(String id) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public ApprovalActivity getEntiyByPK(Integer id) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public ApprovalActivity getEntiyByPK(Long id) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void save(ApprovalActivity entity) throws Exception {
		entity.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
		approvalActivityDao.save(entity);
	}

	@Override
	public void update(ApprovalActivity entity) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public void saveOrUpdate(ApprovalActivity enntity) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public ApprovalActivity saveData(ApprovalActivity entity) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public ApprovalActivity updateData(ApprovalActivity entity)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public ApprovalActivity saveOrUpdateData(ApprovalActivity entity)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public ApprovalActivity getEntityByPkIsActive(String id, Integer isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public ApprovalActivity getEntityByPkIsActive(String id, Byte isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public ApprovalActivity getEntityByPkIsActive(String id, Boolean isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public ApprovalActivity getEntityByPkIsActive(Integer id, Integer isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public ApprovalActivity getEntityByPkIsActive(Integer id, Byte isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public ApprovalActivity getEntityByPkIsActive(Integer id, Boolean isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public ApprovalActivity getEntityByPkIsActive(Long id, Integer isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public ApprovalActivity getEntityByPkIsActive(Long id, Byte isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public ApprovalActivity getEntityByPkIsActive(Long id, Boolean isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public void delete(ApprovalActivity entity) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public void softDelete(ApprovalActivity entity) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public Long getTotalData() throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public Long getTotalDataIsActive(Boolean isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public Long getTotalDataIsActive(Integer isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public Long getTotalDataIsActive(Byte isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<ApprovalActivity> getAllData() throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<ApprovalActivity> getAllData(Boolean isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<ApprovalActivity> getAllData(Integer isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<ApprovalActivity> getAllData(Byte isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<ApprovalActivity> getAllDataPageAble(int firstResult,
			int maxResults, Order order) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<ApprovalActivity> getAllDataPageAbleIsActive(int firstResult,
			int maxResults, Order order, Boolean isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<ApprovalActivity> getAllDataPageAbleIsActive(int firstResult,
			int maxResults, Order order, Integer isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<ApprovalActivity> getAllDataPageAbleIsActive(int firstResult,
			int maxResults, Order order, Byte isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public ApprovalActivity checkApprovalProcess(String processName) throws Exception {
		
		ApprovalActivity appActivity = null;
		List<ApprovalDefinition> listAppDef = approvalDefinitionDao.getAllDataByNameAndProcessType(processName, HRMConstant.APPROVAL_PROCESS, Order.asc("sequence"));
		if(!listAppDef.isEmpty()){ //if not empty
			ApprovalDefinition appDef = listAppDef.get(0);
			String approverUserName = this.getApproverUserName(appDef);
			
			if(StringUtils.isNotEmpty(approverUserName)){				
				appActivity = new ApprovalActivity();				
				appActivity.setApprovalDefinition(appDef);			
				appActivity.setApprovedBy(approverUserName);
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
	
	private String getApproverUserName(ApprovalDefinition appDef){
		String userName = StringUtils.EMPTY;
		
		if(StringUtils.equals(appDef.getApproverType(), HRMConstant.APPROVAL_TYPE_INDIVIDUAL)){  //approver based on individual
			HrmUser approver = appDef.getHrmUserByApproverIndividual();
			userName = approver.getUserId();
			
		} else if(StringUtils.equals(appDef.getApproverType(), HRMConstant.APPROVAL_TYPE_POSITION)){ //approver based on position
			/** jika dalam satu jabatan yg sama terdapat beberapa employee, maka pilih employee berdasarkan joinDate/TMB yg terlama 
			 *  itulah kenapa di order desc "joinDate" */
			Jabatan jabatan = appDef.getJabatanByApproverPosition();			
			List<EmpData> employees = empDataDao.getAllDataByJabatanId(jabatan.getId(), Order.desc("joinDate"));
			
			if(!employees.isEmpty()) { //if not empty
				EmpData empData = employees.get(0);				
				if(!empData.getHrmUsers().isEmpty()){ //if not empty
					HrmUser approver = empData.getHrmUsers().iterator().next();
					userName = approver.getUserId();					
				}
			}
			
		} else if(StringUtils.equals(appDef.getApproverType(), HRMConstant.APPROVAL_TYPE_DEPARTMENT)){ ////approver based on department
			
			
		}
		
		return userName;
	}
	
	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ApprovalActivity approved(Long appActivityId, String comment) throws Exception {
		
		/* update APPROVED approval activity */
		ApprovalActivity approvalActivity = approvalActivityDao.getEntiyByPK(appActivityId);
		approvalActivity.setApprovalStatus(HRMConstant.APPROVAL_STATUS_APPROVED);
		approvalActivity.setApprovalCommment(comment);
		int approvedCount = approvalActivity.getApprovalCount() + 1; //increment +1
		approvalActivity.setApprovalCount(approvedCount);
		approvalActivity.setApprovalTime(new Date());
		approvalActivityDao.update(approvalActivity);
		
		
		/* create new approval activity (if any)*/
		ApprovalActivity newEntity = null;
		ApprovalDefinition previousAppDef = approvalActivity.getApprovalDefinition();
		
		/** cek apakah approver count sudah memenuhi dari minimal approver di approval definition 
		 *  1. jika belum memenuhi, maka lanjut ke checking atasannya untuk proses approval-nya
		 *  2. jika sudah memenuhi maka lanjut ke proses checking approval definition selanjutnya */
		if(approvalActivity.getApprovalCount() < previousAppDef.getMinApprover()){
			//proses no. 1
			
		} else {
			//proses no. 2
			List<ApprovalDefinition> listAppDef = approvalDefinitionDao.getAllDataByNameAndProcessTypeAndSequenceGreater(previousAppDef.getName(), previousAppDef.getProcessType(), previousAppDef.getSequence());
			if(listAppDef.size() > 0){
				ApprovalDefinition appDef = listAppDef.get(0);
				String approverUserName = this.getApproverUserName(appDef);
			
				newEntity = new ApprovalActivity();
				newEntity.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
				newEntity.setApprovalDefinition(appDef);			
				newEntity.setApprovedBy(approverUserName);
				newEntity.setApprovalStatus(HRMConstant.APPROVAL_STATUS_WAITING);
				newEntity.setNotificationSend(false);
				
				//copy value from previous approval activity
				newEntity.setSequence(approvalActivity.getSequence() + 1); //increment +1
				newEntity.setApprovalCount(approvalActivity.getApprovalCount());
				newEntity.setRejectCount(approvalActivity.getRejectCount());
				newEntity.setActivityNumber(approvalActivity.getActivityNumber());			
				newEntity.setRequestBy(approvalActivity.getRequestBy());
				newEntity.setPendingData(approvalActivity.getPendingData());
				approvalActivityDao.save(newEntity);
				
			}
		}
		
		
		if(newEntity != null){
			// jika newEntity sama dengan null, berarti sudah tidak ada lagi proses approval, lanjut ke saving objek dari "pendingData" json
			return newEntity;
		} else {
			// jika newEntity tidak sama dengan null, maka lanjut ke proses kirim email ke approver
			return approvalActivity;
		}
	}
	
	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ApprovalActivity rejected(Long appActivityId, String comment) throws Exception {
		
		/* update REJECTED approval activity */
		ApprovalActivity approvalActivity = approvalActivityDao.getEntiyByPK(appActivityId);
		approvalActivity.setApprovalStatus(HRMConstant.APPROVAL_STATUS_REJECTED);
		approvalActivity.setApprovalCommment(comment);
		int rejectedCount = approvalActivity.getRejectCount() + 1; //increment +1
		approvalActivity.setRejectCount(rejectedCount);
		approvalActivity.setApprovalTime(new Date());
		approvalActivityDao.save(approvalActivity);
		
		return null;
	}

}
