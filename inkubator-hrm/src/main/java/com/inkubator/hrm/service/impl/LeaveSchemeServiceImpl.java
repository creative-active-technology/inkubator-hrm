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
import com.inkubator.hrm.dao.LeaveDao;
import com.inkubator.hrm.dao.LeaveSchemeDao;
import com.inkubator.hrm.entity.Leave;
import com.inkubator.hrm.entity.LeaveScheme;
import com.inkubator.hrm.service.LeaveSchemeService;
import com.inkubator.hrm.web.search.LeaveSchemeSearchParameter;
import com.inkubator.securitycore.util.UserInfoUtil;

/**
 *
 * @author rizkykojek
 */
@Service(value = "leaveSchemeService")
@Lazy
public class LeaveSchemeServiceImpl extends IServiceImpl implements LeaveSchemeService {

	@Autowired
	private LeaveSchemeDao leaveSchemeDao;
	@Autowired
	private LeaveDao leaveDao;
	
	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(LeaveScheme leaveScheme) throws Exception {
		leaveSchemeDao.delete(leaveScheme);
	}

	@Override
        @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<LeaveScheme> getAllData() throws Exception {
		return this.leaveSchemeDao.getAllData();

	}

	@Override
	public List<LeaveScheme> getAllData(Boolean arg0) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<LeaveScheme> getAllData(Integer arg0) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<LeaveScheme> getAllData(Byte arg0) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<LeaveScheme> getAllDataPageAble(int arg0, int arg1, Order arg2)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<LeaveScheme> getAllDataPageAbleIsActive(int arg0, int arg1,
			Order arg2, Boolean arg3) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<LeaveScheme> getAllDataPageAbleIsActive(int arg0, int arg1,
			Order arg2, Integer arg3) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<LeaveScheme> getAllDataPageAbleIsActive(int arg0, int arg1,
			Order arg2, Byte arg3) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public LeaveScheme getEntityByPkIsActive(String arg0, Integer arg1)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public LeaveScheme getEntityByPkIsActive(String arg0, Byte arg1)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public LeaveScheme getEntityByPkIsActive(String arg0, Boolean arg1)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public LeaveScheme getEntityByPkIsActive(Integer arg0, Integer arg1)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public LeaveScheme getEntityByPkIsActive(Integer arg0, Byte arg1)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public LeaveScheme getEntityByPkIsActive(Integer arg0, Boolean arg1)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public LeaveScheme getEntityByPkIsActive(Long arg0, Integer arg1)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public LeaveScheme getEntityByPkIsActive(Long arg0, Byte arg1)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public LeaveScheme getEntityByPkIsActive(Long arg0, Boolean arg1)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public LeaveScheme getEntiyByPK(String arg0) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public LeaveScheme getEntiyByPK(Integer arg0) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public LeaveScheme getEntiyByPK(Long id) throws Exception {
		return leaveSchemeDao.getEntiyByPK(id);
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
	public void save(LeaveScheme leaveScheme) throws Exception {
		// check duplicate name
		long totalDuplicates = leaveSchemeDao.getTotalByName(leaveScheme.getName());
		if (totalDuplicates > 0) {
			throw new BussinessException("leavescheme.error_duplicate_name");
		}
		// check duplicate code
		totalDuplicates = leaveSchemeDao.getTotalByCode(leaveScheme.getCode());
		if (totalDuplicates > 0) {
			throw new BussinessException("leavescheme.error_duplicate_code");
		}
		
		leaveScheme.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
		Leave leave = leaveDao.getEntiyByPK(leaveScheme.getLeave().getId());
		leaveScheme.setLeave(leave);
		leaveScheme.setCreatedBy(UserInfoUtil.getUserName());
		leaveScheme.setCreatedOn(new Date());
		leaveSchemeDao.save(leaveScheme);
	}

	@Override
	public LeaveScheme saveData(LeaveScheme arg0) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public void saveOrUpdate(LeaveScheme arg0) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public LeaveScheme saveOrUpdateData(LeaveScheme arg0) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public void softDelete(LeaveScheme arg0) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void update(LeaveScheme ls) throws Exception {
		// check duplicate name
		long totalDuplicates = leaveSchemeDao.getTotalByNameAndNotId(ls.getName(),ls.getId());
		if (totalDuplicates > 0) {
			throw new BussinessException("leavescheme.error_duplicate_name");
		}
		// check duplicate code
		totalDuplicates = leaveSchemeDao.getTotalByCodeAndNotId(ls.getCode(),ls.getId());
		if (totalDuplicates > 0) {
			throw new BussinessException("leavescheme.error_duplicate_code");
		}
		
		LeaveScheme leaveScheme = leaveSchemeDao.getEntiyByPK(ls.getId());
		leaveScheme.setCode(ls.getCode());
		leaveScheme.setName(ls.getName());
		leaveScheme.setDescription(ls.getDescription());
		Leave leave = leaveDao.getEntiyByPK(ls.getLeave().getId()); 
		leaveScheme.setLeave(leave);
		leaveScheme.setTotalDays(ls.getTotalDays());
		leaveScheme.setUpdatedBy(UserInfoUtil.getUserName());
		leaveScheme.setUpdatedOn(new Date());
		leaveSchemeDao.update(leaveScheme);
	}

	@Override
	public LeaveScheme updateData(LeaveScheme arg0) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<LeaveScheme> getByParam(LeaveSchemeSearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception {
		return leaveSchemeDao.getByParam(parameter, firstResult, maxResults, orderable);
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public Long getTotalByParam(LeaveSchemeSearchParameter parameter) throws Exception {
		return leaveSchemeDao.getTotalByParam(parameter);
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public LeaveScheme getEntiyByPkFetchLeave(Long id) throws Exception{
		return leaveSchemeDao.getEntityByPkFetchLeave(id);
	}

}
