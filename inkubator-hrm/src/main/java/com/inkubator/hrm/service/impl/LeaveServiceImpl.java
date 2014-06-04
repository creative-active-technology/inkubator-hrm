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
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.dao.AttendanceStatusDao;
import com.inkubator.hrm.dao.LeaveDao;
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
public class LeaveServiceImpl extends IServiceImpl implements LeaveService {

	@Autowired
	private LeaveDao leaveDao;
	@Autowired
	private AttendanceStatusDao attendanceStatusDao;
	
	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(Leave leave) throws Exception {
		leaveDao.delete(leave);
	}

	@Override
	public List<Leave> getAllData() throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

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
	    leave.setLeaveAvailability(l.getLeaveAvailability());
	    leave.setIncreasedLeaveDate(l.getIncreasedLeaveDate());
	    leave.setIsTakingLeaveToNextYear(l.getIsTakingLeaveToNextYear());
	    leave.setMaxTakingLeaveToNextYear(l.getMaxTakingLeaveToNextYear());
	    leave.setBackwardPeriodLimit(l.getBackwardPeriodLimit());
	    leave.setIsAllowedMinus(l.getIsAllowedMinus());
	    leave.setMaxAllowedMinus(l.getMaxAllowedMinus());
	    leave.setEffectiveFrom(l.getEffectiveFrom());
	    leave.setSubmittedLimit(l.getSubmittedLimit());
	    leave.setApprovalLevel(l.getApprovalLevel());
	    leave.setLeaveQuotaReduction(l.getLeaveQuotaReduction());
	    leave.setEndOfPeriodLeave(l.getEndOfPeriodLeave());
	    leave.setEndOfPeriodLeaveMonth(l.getEndOfPeriodLeaveMonth());
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

}
