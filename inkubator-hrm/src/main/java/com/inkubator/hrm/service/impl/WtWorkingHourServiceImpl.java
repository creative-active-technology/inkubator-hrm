package com.inkubator.hrm.service.impl;



import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.dao.WtWorkingHourDao;
import com.inkubator.hrm.entity.WtWorkingHour;
import com.inkubator.hrm.service.WtWorkingHourService;
import com.inkubator.hrm.web.search.WorkingHourSearchParameter;
import com.inkubator.securitycore.util.UserInfoUtil;
import java.util.Date;
import java.util.List;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author rizkykojek
 */
@Service(value = "wtWorkingHourService")
@Lazy
public class WtWorkingHourServiceImpl extends IServiceImpl implements WtWorkingHourService {

	@Autowired
	private WtWorkingHourDao wtWorkingHourDao;
	
	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(WtWorkingHour workingHour) throws Exception {
		wtWorkingHourDao.delete(workingHour);
	}

	@Override
	public List<WtWorkingHour> getAllData() throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<WtWorkingHour> getAllData(Boolean arg0) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<WtWorkingHour> getAllData(Integer arg0) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<WtWorkingHour> getAllData(Byte arg0) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<WtWorkingHour> getAllDataPageAble(int arg0, int arg1, Order arg2)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<WtWorkingHour> getAllDataPageAbleIsActive(int arg0, int arg1,
			Order arg2, Boolean arg3) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<WtWorkingHour> getAllDataPageAbleIsActive(int arg0, int arg1,
			Order arg2, Integer arg3) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<WtWorkingHour> getAllDataPageAbleIsActive(int arg0, int arg1,
			Order arg2, Byte arg3) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public WtWorkingHour getEntityByPkIsActive(String arg0, Integer arg1)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public WtWorkingHour getEntityByPkIsActive(String arg0, Byte arg1)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public WtWorkingHour getEntityByPkIsActive(String arg0, Boolean arg1)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public WtWorkingHour getEntityByPkIsActive(Integer arg0, Integer arg1)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public WtWorkingHour getEntityByPkIsActive(Integer arg0, Byte arg1)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public WtWorkingHour getEntityByPkIsActive(Integer arg0, Boolean arg1)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public WtWorkingHour getEntityByPkIsActive(Long arg0, Integer arg1)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public WtWorkingHour getEntityByPkIsActive(Long arg0, Byte arg1)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public WtWorkingHour getEntityByPkIsActive(Long arg0, Boolean arg1)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public WtWorkingHour getEntiyByPK(String arg0) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public WtWorkingHour getEntiyByPK(Integer arg0) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public WtWorkingHour getEntiyByPK(Long id) throws Exception {
		return wtWorkingHourDao.getEntiyByPK(id);
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
	public void save(WtWorkingHour workingHour) throws Exception {
		// check duplicate name
		long totalDuplicates = wtWorkingHourDao.getTotalByName(workingHour.getName());
		if (totalDuplicates > 0) {
			throw new BussinessException("workinghour.error_duplicate_name");
		}
		// check duplicate code
		totalDuplicates = wtWorkingHourDao.getTotalByCode(workingHour.getCode());
		if (totalDuplicates > 0) {
			throw new BussinessException("workinghour.error_duplicate_code");
		}
		
		workingHour.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
		workingHour.setCreatedBy(UserInfoUtil.getUserName());
		workingHour.setCreatedOn(new Date());
		wtWorkingHourDao.save(workingHour);
	}

	@Override
	public WtWorkingHour saveData(WtWorkingHour arg0) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public void saveOrUpdate(WtWorkingHour arg0) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public WtWorkingHour saveOrUpdateData(WtWorkingHour arg0) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public void softDelete(WtWorkingHour arg0) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void update(WtWorkingHour wt) throws Exception {
		// check duplicate name
		long totalDuplicates = wtWorkingHourDao.getTotalByNameAndNotId(wt.getName(),wt.getId());
		if (totalDuplicates > 0) {
			throw new BussinessException("workinghour.error_duplicate_name");
		}
		// check duplicate code
		totalDuplicates = wtWorkingHourDao.getTotalByCodeAndNotId(wt.getCode(),wt.getId());
		if (totalDuplicates > 0) {
			throw new BussinessException("workinghour.error_duplicate_code");
		}
		
		WtWorkingHour workingHour = wtWorkingHourDao.getEntiyByPK(wt.getId());
		workingHour.setCode(wt.getCode());
		workingHour.setName(wt.getName());
		workingHour.setDescription(wt.getDescription());
		workingHour.setWorkingHourBegin(wt.getWorkingHourBegin());
		workingHour.setWorkingHourEnd(wt.getWorkingHourEnd());
		workingHour.setMaxHour(wt.getMaxHour());
		workingHour.setArriveLimitBegin(wt.getArriveLimitBegin());
		workingHour.setArriveLimitEnd(wt.getArriveLimitEnd());
		workingHour.setGoHomeLimitBegin(wt.getGoHomeLimitBegin());
		workingHour.setGoHomeLimitEnd(wt.getGoHomeLimitEnd());
		workingHour.setIsPenaltyArriveLate(wt.getIsPenaltyArriveLate());
		workingHour.setIsPenaltyGoHomeEarly(wt.getIsPenaltyGoHomeEarly());
		workingHour.setIsManageBreakTime(wt.getIsManageBreakTime());
		workingHour.setBreakHourBegin(wt.getBreakHourBegin());
		workingHour.setBreakHourEnd(wt.getBreakHourEnd());
		workingHour.setBreakStartLimitBegin(wt.getBreakStartLimitBegin());
		workingHour.setBreakStartLimitEnd(wt.getBreakStartLimitEnd());
		workingHour.setBreakFinishLimitBegin(wt.getBreakFinishLimitBegin());
		workingHour.setBreakFinishLimitEnd(wt.getBreakFinishLimitEnd());
		workingHour.setIsPenaltyBreakStartEarly(wt.getIsPenaltyBreakStartEarly());
		workingHour.setIsPenaltyBreakFinishLate(wt.getIsPenaltyBreakFinishLate());		
		workingHour.setUpdatedBy(UserInfoUtil.getUserName());
		workingHour.setUpdatedOn(new Date());
		wtWorkingHourDao.update(workingHour);
	}

	@Override
	public WtWorkingHour updateData(WtWorkingHour arg0) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<WtWorkingHour> getByParam(WorkingHourSearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception {
		return wtWorkingHourDao.getByParam(parameter, firstResult, maxResults, orderable);
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public Long getTotalByParam(WorkingHourSearchParameter parameter) throws Exception {
		return wtWorkingHourDao.getTotalByParam(parameter);
	}

}
