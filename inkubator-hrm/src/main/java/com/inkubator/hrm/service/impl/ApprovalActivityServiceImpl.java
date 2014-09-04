package com.inkubator.hrm.service.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.ApprovalActivityDao;
import com.inkubator.hrm.dao.ApprovalDefinitionDao;
import com.inkubator.hrm.dao.HrmUserDao;
import com.inkubator.hrm.entity.ApprovalActivity;
import com.inkubator.hrm.entity.ApprovalDefinition;
import com.inkubator.hrm.entity.HrmUser;
import com.inkubator.hrm.entity.Jabatan;
import com.inkubator.hrm.service.ApprovalActivityService;

/**
 *
 * @author rizkykojek
 */
@Service(value = "approvalActivityService")
@Lazy
public class ApprovalActivityServiceImpl extends BaseApprovalServiceImpl implements ApprovalActivityService {

	@Autowired
	private ApprovalActivityDao approvalActivityDao;
	@Autowired
	private ApprovalDefinitionDao approvalDefinitionDao;
	@Autowired
	private HrmUserDao hrmUserDao;
	
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
			HrmUser user = hrmUserDao.getByUserId(approvalActivity.getApprovedBy());
			Jabatan jabatan = user.getEmpData().getJabatanByJabatanId();
			Jabatan parentJabatan = jabatan.getJabatan();
			/** jika approver mempunyai atasan maka lanjut approval ke atasannya, 
			 *  jika tidak punya atasan langsung approve saja(dilewat proses checking)*/
			if(parentJabatan!=null){				
				String approverUserId = this.getApproverByJabatanId(parentJabatan.getId());
				newEntity = this.createNewApprovalActivity(approverUserId, previousAppDef, approvalActivity);
				approvalActivityDao.save(newEntity);
			}
			
		} else {
			
			//proses no. 2
			List<ApprovalDefinition> listAppDef = approvalDefinitionDao.getAllDataByNameAndProcessTypeAndSequenceGreater(previousAppDef.getName(), previousAppDef.getProcessType(), previousAppDef.getSequence());
			if(listAppDef.size() > 0){
				ApprovalDefinition appDef = listAppDef.get(0);
				String approverUserId = this.getApproverByAppDefinition(appDef);
				newEntity = this.createNewApprovalActivity(approverUserId, appDef, approvalActivity);
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
	
	private ApprovalActivity createNewApprovalActivity(String approverUserId, ApprovalDefinition appDef, ApprovalActivity previousAppActv){
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
