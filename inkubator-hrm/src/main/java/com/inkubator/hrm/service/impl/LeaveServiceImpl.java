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
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.dao.ApprovalDefinitionDao;
import com.inkubator.hrm.dao.ApprovalDefinitionLeaveDao;
import com.inkubator.hrm.dao.AttendanceStatusDao;
import com.inkubator.hrm.dao.LeaveDao;
import com.inkubator.hrm.entity.ApprovalDefinition;
import com.inkubator.hrm.entity.ApprovalDefinitionLeave;
import com.inkubator.hrm.entity.ApprovalDefinitionLeaveId;
import com.inkubator.hrm.entity.AttendanceStatus;
import com.inkubator.hrm.entity.Leave;
import com.inkubator.hrm.service.LeaveService;
import com.inkubator.hrm.web.search.LeaveSearchParameter;
import com.inkubator.securitycore.util.UserInfoUtil;

/**
 *
 * @author rizkykojek
 */
@Service(value = "leaveService")
@Lazy
public class LeaveServiceImpl extends BaseApprovalConfigurationServiceImpl<Leave> implements LeaveService {

	@Autowired
	private LeaveDao leaveDao;
	@Autowired
	private AttendanceStatusDao attendanceStatusDao;
	@Autowired
	private ApprovalDefinitionDao approvalDefinitionDao;
	@Autowired
	private ApprovalDefinitionLeaveDao approvalDefinitionLeaveDao;
	
	
	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(Leave leave) throws Exception {
		leaveDao.delete(leave);
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<Leave> getAllData() throws Exception {
		return leaveDao.getAllData();
	}

	@Override
	public List<Leave> getAllData(Boolean arg0) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<Leave> getAllData(Integer arg0) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<Leave> getAllData(Byte arg0) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<Leave> getAllDataPageAble(int arg0, int arg1, Order arg2)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<Leave> getAllDataPageAbleIsActive(int arg0, int arg1,
			Order arg2, Boolean arg3) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<Leave> getAllDataPageAbleIsActive(int arg0, int arg1,
			Order arg2, Integer arg3) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<Leave> getAllDataPageAbleIsActive(int arg0, int arg1,
			Order arg2, Byte arg3) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public Leave getEntityByPkIsActive(String arg0, Integer arg1)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public Leave getEntityByPkIsActive(String arg0, Byte arg1)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public Leave getEntityByPkIsActive(String arg0, Boolean arg1)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public Leave getEntityByPkIsActive(Integer arg0, Integer arg1)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public Leave getEntityByPkIsActive(Integer arg0, Byte arg1)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public Leave getEntityByPkIsActive(Integer arg0, Boolean arg1)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public Leave getEntityByPkIsActive(Long arg0, Integer arg1)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public Leave getEntityByPkIsActive(Long arg0, Byte arg1)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public Leave getEntityByPkIsActive(Long arg0, Boolean arg1)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public Leave getEntiyByPK(String arg0) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public Leave getEntiyByPK(Integer arg0) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public Leave getEntiyByPK(Long id) throws Exception {
		return leaveDao.getEntiyByPK(id);
	}
        
	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public Leave getEntityByPkFetchAttendStatus(Long id) throws Exception {
		return leaveDao.getEntityByPkFetchAttendStatus(id);
	}

	@Override
	public Long getTotalData() throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public Long getTotalDataIsActive(Boolean arg0) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public Long getTotalDataIsActive(Integer arg0) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public Long getTotalDataIsActive(Byte arg0) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void save(Leave leave) throws Exception {
		// check duplicate name
		long totalDuplicates = leaveDao.getTotalByName(leave.getName());
		if (totalDuplicates > 0) {
			throw new BussinessException("leave.error_duplicate_name");
		}
		// check duplicate code
		totalDuplicates = leaveDao.getTotalByCode(leave.getCode());
		if (totalDuplicates > 0) {
			throw new BussinessException("leave.error_duplicate_code");
		}
		
		leave.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
		AttendanceStatus attendanceStatus = attendanceStatusDao.getEntiyByPK(leave.getAttendanceStatus().getId());
	    leave.setAttendanceStatus(attendanceStatus);
		leave.setCreatedBy(UserInfoUtil.getUserName());
		leave.setCreatedOn(new Date());
		leaveDao.save(leave);
	}

	@Override
	public Leave saveData(Leave arg0) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public void saveOrUpdate(Leave arg0) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public Leave saveOrUpdateData(Leave arg0) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public void softDelete(Leave arg0) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void update(Leave l) throws Exception {
		// check duplicate name
		long totalDuplicates = leaveDao.getTotalByNameAndNotId(l.getName(),l.getId());
		if (totalDuplicates > 0) {
			throw new BussinessException("leave.error_duplicate_name");
		}
		// check duplicate code
		totalDuplicates = leaveDao.getTotalByCodeAndNotId(l.getCode(),l.getId());
		if (totalDuplicates > 0) {
			throw new BussinessException("leave.error_duplicate_code");
		}
		
		Leave leave = leaveDao.getEntiyByPK(l.getId());
		leave.setCode(l.getCode());
		leave.setName(l.getName());
		leave.setDescription(l.getDescription());	
		leave.setDayType(l.getDayType());
	    leave.setCalculation(l.getCalculation());
	    AttendanceStatus attendanceStatus = attendanceStatusDao.getEntiyByPK(l.getAttendanceStatus().getId());
	    leave.setAttendanceStatus(attendanceStatus);
	    leave.setPeriodBase(l.getPeriodBase());
	    leave.setAvailability(l.getAvailability());
	    leave.setAvailabilityAtSpecificDate(l.getAvailabilityAtSpecificDate());
	    leave.setIsTakingLeaveToNextYear(l.getIsTakingLeaveToNextYear());
	    leave.setMaxTakingLeaveToNextYear(l.getMaxTakingLeaveToNextYear());
	    leave.setBackwardPeriodLimit(l.getBackwardPeriodLimit());
	    leave.setIsAllowedMinus(l.getIsAllowedMinus());
	    leave.setMaxAllowedMinus(l.getMaxAllowedMinus());
	    leave.setEffectiveFrom(l.getEffectiveFrom());
	    leave.setSubmittedLimit(l.getSubmittedLimit());
	    leave.setApprovalLevel(l.getApprovalLevel());
	    leave.setIsQuotaReduction(l.getIsQuotaReduction());
	    leave.setEndOfPeriod(l.getEndOfPeriod());
	    leave.setEndOfPeriodMonth(l.getEndOfPeriodMonth());
	    leave.setIsOnlyOncePerEmployee(l.getIsOnlyOncePerEmployee());
	    leave.setUpdatedBy(UserInfoUtil.getUserName());
	    leave.setUpdatedOn(new Date());
		leaveDao.update(leave);
	}

	@Override
	public Leave updateData(Leave arg0) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<Leave> getByParam(LeaveSearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception {
		return leaveDao.getByParam(parameter, firstResult, maxResults, orderable);
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public Long getTotalByParam(LeaveSearchParameter parameter) throws Exception {
		return leaveDao.getTotalByParam(parameter);
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void save(Leave entity, List<ApprovalDefinition> appDefs) throws Exception {
		// check duplicate name
		long totalDuplicates = leaveDao.getTotalByName(entity.getName());
		if (totalDuplicates > 0) {
			throw new BussinessException("leave.error_duplicate_name");
		}
		// check duplicate code
		totalDuplicates = leaveDao.getTotalByCode(entity.getCode());
		if (totalDuplicates > 0) {
			throw new BussinessException("leave.error_duplicate_code");
		}
		
		/** validasi approval definition conf */
		super.validateApprovalConf(appDefs);
		
		entity.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
		AttendanceStatus attendanceStatus = attendanceStatusDao.getEntiyByPK(entity.getAttendanceStatus().getId());
		entity.setAttendanceStatus(attendanceStatus);
		entity.setIsActive(true);
		entity.setCreatedBy(UserInfoUtil.getUserName());
		entity.setCreatedOn(new Date());
		leaveDao.save(entity);
		
		/** saving approval definition conf manyToMany */
		super.saveApprovalConf(appDefs, entity);
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void update(Leave entity, List<ApprovalDefinition> appDefs) throws Exception {
		// check duplicate name
		long totalDuplicates = leaveDao.getTotalByNameAndNotId(entity.getName(),entity.getId());
		if (totalDuplicates > 0) {
			throw new BussinessException("leave.error_duplicate_name");
		}
		// check duplicate code
		totalDuplicates = leaveDao.getTotalByCodeAndNotId(entity.getCode(),entity.getId());
		if (totalDuplicates > 0) {
			throw new BussinessException("leave.error_duplicate_code");
		}
		
		/** validasi approval definition conf */
		super.validateApprovalConf(appDefs);		
		
		Leave leave = leaveDao.getEntiyByPK(entity.getId());
		leave.setCode(entity.getCode());
		leave.setName(entity.getName());
		leave.setDescription(entity.getDescription());	
		leave.setDayType(entity.getDayType());
	    leave.setCalculation(entity.getCalculation());
	    AttendanceStatus attendanceStatus = attendanceStatusDao.getEntiyByPK(entity.getAttendanceStatus().getId());
	    leave.setAttendanceStatus(attendanceStatus);
	    leave.setPeriodBase(entity.getPeriodBase());
	    leave.setAvailability(entity.getAvailability());
	    leave.setAvailabilityAtSpecificDate(entity.getAvailabilityAtSpecificDate());
	    leave.setIsTakingLeaveToNextYear(entity.getIsTakingLeaveToNextYear());
	    leave.setMaxTakingLeaveToNextYear(entity.getMaxTakingLeaveToNextYear());
	    leave.setBackwardPeriodLimit(entity.getBackwardPeriodLimit());
	    leave.setIsAllowedMinus(entity.getIsAllowedMinus());
	    leave.setMaxAllowedMinus(entity.getMaxAllowedMinus());
	    leave.setEffectiveFrom(entity.getEffectiveFrom());
	    leave.setSubmittedLimit(entity.getSubmittedLimit());
	    leave.setApprovalLevel(entity.getApprovalLevel());
	    leave.setIsQuotaReduction(entity.getIsQuotaReduction());
	    leave.setEndOfPeriod(entity.getEndOfPeriod());
	    leave.setEndOfPeriodMonth(entity.getEndOfPeriodMonth());
	    leave.setIsOnlyOncePerEmployee(entity.getIsOnlyOncePerEmployee());
	    leave.setUpdatedBy(UserInfoUtil.getUserName());
	    leave.setUpdatedOn(new Date());
	    leaveDao.update(leave);
	    
	    /** updating approval definition conf manyToMany */
	    super.updateApprovalConf(appDefs, leave.getApprovalDefinitionLeaves().iterator(), leave);
	}
	
	@Override
	protected void saveManyToMany(ApprovalDefinition appDef, Leave entity) {
		ApprovalDefinitionLeave approvalDefinitionLeave =  new ApprovalDefinitionLeave();
		approvalDefinitionLeave.setId(new ApprovalDefinitionLeaveId(appDef.getId(), entity.getId()));
		approvalDefinitionLeave.setApprovalDefinition(appDef);
		approvalDefinitionLeave.setLeave(entity);
		approvalDefinitionLeaveDao.save(approvalDefinitionLeave);		
	}
	
	@Override
	protected void deleteManyToMany(Object entity) {
		approvalDefinitionLeaveDao.delete((ApprovalDefinitionLeave) entity);		
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public Leave getEntityByPkFetchApprovalDefinition(Long id) throws Exception {
		return leaveDao.getEntityByPkFetchApprovalDefinition(id);
	}
	
}
