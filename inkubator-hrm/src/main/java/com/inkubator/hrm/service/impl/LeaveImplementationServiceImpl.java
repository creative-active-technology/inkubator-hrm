package com.inkubator.hrm.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.dao.EmpDataDao;
import com.inkubator.hrm.dao.LeaveDao;
import com.inkubator.hrm.dao.LeaveImplementationDao;
import com.inkubator.hrm.entity.ApprovalActivity;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.Leave;
import com.inkubator.hrm.entity.LeaveImplementation;
import com.inkubator.hrm.service.LeaveImplementationService;
import com.inkubator.hrm.web.search.LeaveImplementationSearchParameter;
import com.inkubator.securitycore.util.UserInfoUtil;

/**
 *
 * @author rizkykojek
 */
@Service(value = "leaveImplementationService")
@Lazy
public class LeaveImplementationServiceImpl extends BaseApprovalServiceImpl implements LeaveImplementationService {

	@Autowired
	private LeaveImplementationDao leaveImplementationDao;
	@Autowired
	private LeaveDao leaveDao;
	@Autowired
	private EmpDataDao empDataDao;
	
	@Override
	public LeaveImplementation getEntiyByPK(String id) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public LeaveImplementation getEntiyByPK(Integer id) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public LeaveImplementation getEntiyByPK(Long id) throws Exception {
		return leaveImplementationDao.getEntiyByPK(id);

	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void save(LeaveImplementation entity) throws Exception {
		// check duplicate number filling
		long totalDuplicates = leaveImplementationDao.getTotalByNumberFilling(entity.getNumberFilling());
		if (totalDuplicates > 0) {
			throw new BussinessException("leaveimplementation.error_duplicate_filling_number");
		}		
		
		EmpData empData = empDataDao.getEntiyByPK(entity.getEmpData().getId());
		Leave leave = leaveDao.getEntiyByPK(entity.getLeave().getId());
		EmpData temporaryActing = entity.getTemporaryActing() != null ? empDataDao.getEntiyByPK(entity.getTemporaryActing().getId()) : null;
		
		entity.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
		entity.setEmpData(empData);
		entity.setLeave(leave);
		entity.setTemporaryActing(temporaryActing);
		
		String createdBy = StringUtils.isEmpty(entity.getCreatedBy()) ? UserInfoUtil.getUserName() : entity.getCreatedBy();
		Date createdOn = entity.getCreatedOn() == null ? new Date() : entity.getCreatedOn();
		entity.setCreatedBy(createdBy);
		entity.setCreatedOn(createdOn);
		
		leaveImplementationDao.save(entity);
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void update(LeaveImplementation entity) throws Exception {
		// check duplicate number filling
		long totalDuplicates = leaveImplementationDao.getTotalByNumberFillingAndNotId(entity.getNumberFilling(), entity.getId());
		if (totalDuplicates > 0) {
			throw new BussinessException("leaveimplementation.error_duplicate_filling_number");
		}		
		
		LeaveImplementation leaveImplementation = leaveImplementationDao.getEntiyByPK(entity.getId());		

		EmpData empData = empDataDao.getEntiyByPK(entity.getEmpData().getId());
		Leave leave = leaveDao.getEntiyByPK(entity.getLeave().getId());
		EmpData temporaryActing = entity.getTemporaryActing() != null ? empDataDao.getEntiyByPK(entity.getTemporaryActing().getId()) : null;

		leaveImplementation.setEmpData(empData);
		leaveImplementation.setLeave(leave);
		leaveImplementation.setTemporaryActing(temporaryActing);
		leaveImplementation.setNumberFilling(entity.getNumberFilling());
        leaveImplementation.setStartDate(entity.getStartDate());
        leaveImplementation.setEndDate(entity.getEndDate());
        leaveImplementation.setFillingDate(entity.getFillingDate());
        leaveImplementation.setAddress(entity.getAddress());
        leaveImplementation.setMobilePhone(entity.getMobilePhone());
        leaveImplementation.setMaterialJobsAbandoned(entity.getMaterialJobsAbandoned());
        leaveImplementation.setDescription(entity.getDescription());
		leaveImplementation.setUpdatedBy(UserInfoUtil.getUserName());
		leaveImplementation.setUpdatedOn(new Date());
		
		leaveImplementationDao.update(leaveImplementation);
	}

	@Override
	public void saveOrUpdate(LeaveImplementation enntity) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public LeaveImplementation saveData(LeaveImplementation entity)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public LeaveImplementation updateData(LeaveImplementation entity)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public LeaveImplementation saveOrUpdateData(LeaveImplementation entity)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public LeaveImplementation getEntityByPkIsActive(String id, Integer isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public LeaveImplementation getEntityByPkIsActive(String id, Byte isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public LeaveImplementation getEntityByPkIsActive(String id, Boolean isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public LeaveImplementation getEntityByPkIsActive(Integer id,
			Integer isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public LeaveImplementation getEntityByPkIsActive(Integer id, Byte isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public LeaveImplementation getEntityByPkIsActive(Integer id,
			Boolean isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public LeaveImplementation getEntityByPkIsActive(Long id, Integer isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public LeaveImplementation getEntityByPkIsActive(Long id, Byte isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public LeaveImplementation getEntityByPkIsActive(Long id, Boolean isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(LeaveImplementation entity) throws Exception {
		leaveImplementationDao.delete(entity);

	}

	@Override
	public void softDelete(LeaveImplementation entity) throws Exception {
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
	public List<LeaveImplementation> getAllData() throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<LeaveImplementation> getAllData(Boolean isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<LeaveImplementation> getAllData(Integer isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<LeaveImplementation> getAllData(Byte isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<LeaveImplementation> getAllDataPageAble(int firstResult,
			int maxResults, Order order) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<LeaveImplementation> getAllDataPageAbleIsActive(
			int firstResult, int maxResults, Order order, Boolean isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<LeaveImplementation> getAllDataPageAbleIsActive(
			int firstResult, int maxResults, Order order, Integer isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<LeaveImplementation> getAllDataPageAbleIsActive(
			int firstResult, int maxResults, Order order, Byte isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public void approved(long approvalActivityId, String pendingDataUpdate,
			String comment) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public void rejected(long approvalActivityId, String comment)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public void diverted(long approvalActivityId) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public void sendingEmailApprovalNotif(ApprovalActivity appActivity)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<LeaveImplementation> getByParam(LeaveImplementationSearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception {
		return leaveImplementationDao.getByParam(parameter, firstResult, maxResults, orderable);

	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public Long getTotalByParam(LeaveImplementationSearchParameter parameter) throws Exception {
		return leaveImplementationDao.getTotalByParam(parameter);

	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public LeaveImplementation getEntityByPkWithDetail(Long id) throws Exception {
		return leaveImplementationDao.getEntityByPkWithDetail(id);

	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public LeaveImplementation getLatestEntityByEmpDataId(Long empDataId) throws Exception {
		LeaveImplementation latest = null;
		//order by tanggal pengajuan descending
		List<LeaveImplementation> leaveImplementations = leaveImplementationDao.getAllDataByEmpDataId(empDataId, Order.desc("fillingDate"));
		if(! leaveImplementations.isEmpty()){
			latest = leaveImplementations.get(0);
		}				 
		return latest;
	}

}
