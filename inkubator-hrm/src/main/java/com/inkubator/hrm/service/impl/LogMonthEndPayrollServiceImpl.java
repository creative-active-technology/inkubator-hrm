package com.inkubator.hrm.service.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
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
import com.inkubator.hrm.dao.LogMonthEndPayrollDao;
import com.inkubator.hrm.dao.PayTempKalkulasiDao;
import com.inkubator.hrm.dao.WtPeriodeDao;
import com.inkubator.hrm.entity.LogMonthEndPayroll;
import com.inkubator.hrm.entity.WtPeriode;
import com.inkubator.hrm.service.LogMonthEndPayrollService;
import com.inkubator.hrm.web.model.LogMonthEndPayrollViewModel;
import com.inkubator.hrm.web.search.LogMonthEndPayrollSearchParameter;

/**
 *
 * @author rizkykojek
 */
@Service(value = "logMonthEndPayrollService")
@Lazy
public class LogMonthEndPayrollServiceImpl extends IServiceImpl implements LogMonthEndPayrollService {
	
	@Autowired
	private LogMonthEndPayrollDao logMonthEndPayrollDao;
	@Autowired
	private PayTempKalkulasiDao payTempKalkulasiDao;
	@Autowired
	private WtPeriodeDao wtPeriodeDao;

	@Override
	public LogMonthEndPayroll getEntiyByPK(String id) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public LogMonthEndPayroll getEntiyByPK(Integer id) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public LogMonthEndPayroll getEntiyByPK(Long id) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public void save(LogMonthEndPayroll entity) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public void update(LogMonthEndPayroll entity) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public void saveOrUpdate(LogMonthEndPayroll enntity) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public LogMonthEndPayroll saveData(LogMonthEndPayroll entity)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public LogMonthEndPayroll updateData(LogMonthEndPayroll entity)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public LogMonthEndPayroll saveOrUpdateData(LogMonthEndPayroll entity)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public LogMonthEndPayroll getEntityByPkIsActive(String id, Integer isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public LogMonthEndPayroll getEntityByPkIsActive(String id, Byte isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public LogMonthEndPayroll getEntityByPkIsActive(String id, Boolean isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public LogMonthEndPayroll getEntityByPkIsActive(Integer id, Integer isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public LogMonthEndPayroll getEntityByPkIsActive(Integer id, Byte isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public LogMonthEndPayroll getEntityByPkIsActive(Integer id, Boolean isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public LogMonthEndPayroll getEntityByPkIsActive(Long id, Integer isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public LogMonthEndPayroll getEntityByPkIsActive(Long id, Byte isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public LogMonthEndPayroll getEntityByPkIsActive(Long id, Boolean isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public void delete(LogMonthEndPayroll entity) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public void softDelete(LogMonthEndPayroll entity) throws Exception {
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
	public List<LogMonthEndPayroll> getAllData() throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<LogMonthEndPayroll> getAllData(Boolean isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<LogMonthEndPayroll> getAllData(Integer isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<LogMonthEndPayroll> getAllData(Byte isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<LogMonthEndPayroll> getAllDataPageAble(int firstResult,
			int maxResults, Order order) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<LogMonthEndPayroll> getAllDataPageAbleIsActive(int firstResult,
			int maxResults, Order order, Boolean isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<LogMonthEndPayroll> getAllDataPageAbleIsActive(int firstResult,
			int maxResults, Order order, Integer isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<LogMonthEndPayroll> getAllDataPageAbleIsActive(int firstResult,
			int maxResults, Order order, Byte isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<LogMonthEndPayrollViewModel> getByParam(LogMonthEndPayrollSearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception {
		System.out.println("S");
		List<LogMonthEndPayrollViewModel> list = logMonthEndPayrollDao.getByParam(parameter, firstResult, maxResults, orderable);
		return list;

	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public Long getTotalByParam(LogMonthEndPayrollSearchParameter parameter) throws Exception {
		return logMonthEndPayrollDao.getTotalByParam(parameter);

	}
	
	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public BigDecimal getTotalTakeHomePayByPeriodeId(Long periodeId) throws Exception {
		return logMonthEndPayrollDao.getTotalTakeHomePayByPeriodeId(periodeId);

	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void afterMonthEndProcess() throws Exception {
		WtPeriode wtPeriode = wtPeriodeDao.getEntityByPayrollTypeActive();
		wtPeriode.setPayrollType(HRMConstant.PERIODE_PAYROLL_VOID);
		wtPeriodeDao.update(wtPeriode);		
		
		Date untilPeriode = wtPeriode.getUntilPeriode();
		Date fromPeriode = DateUtils.addDays(untilPeriode, 1);
		int lastDateOfMonth = DateUtils.toCalendar(untilPeriode).getActualMaximum(Calendar.DAY_OF_MONTH);
		if(lastDateOfMonth == DateUtils.toCalendar(untilPeriode).get(Calendar.DATE)){			
			untilPeriode = DateUtils.addMonths(untilPeriode, 1);
			lastDateOfMonth = DateUtils.toCalendar(untilPeriode).getActualMaximum(Calendar.DAY_OF_MONTH);
			untilPeriode = DateUtils.setDays(untilPeriode, lastDateOfMonth);
		} else {
			untilPeriode = DateUtils.addMonths(untilPeriode, 1);
		}
		
		WtPeriode wtp = wtPeriodeDao.getEntityByFromPeriodeAndUntilPeriode(fromPeriode, untilPeriode);
		if(wtp == null) {
			wtp = new WtPeriode();
			wtp.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
			wtp.setTahun(String.valueOf(DateUtils.toCalendar(fromPeriode).get(Calendar.YEAR)));
			wtp.setBulan(DateUtils.toCalendar(fromPeriode).get(Calendar.MONTH)+1);
			wtp.setFromPeriode(fromPeriode);
			wtp.setUntilPeriode(untilPeriode);
			wtp.setAbsen(HRMConstant.PERIODE_ABSEN_NOT_ACTIVE);
			wtp.setPayrollType(HRMConstant.PERIODE_PAYROLL_ACTIVE);
			wtp.setCreatedOn(new Date());
			wtp.setCreatedBy(HRMConstant.SYSTEM_ADMIN);
			wtPeriodeDao.save(wtp);
		} else {
			wtp.setPayrollType(HRMConstant.PERIODE_PAYROLL_ACTIVE);
			wtPeriodeDao.update(wtp);
		}		
		
		payTempKalkulasiDao.deleteAllData();
		
	}
}