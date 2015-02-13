package com.inkubator.hrm.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.hrm.dao.LogSalaryJournalDao;
import com.inkubator.hrm.entity.LogSalaryJournal;
import com.inkubator.hrm.service.LogSalaryJournalService;
import com.inkubator.hrm.web.model.ReportSalaryJounalModel;

/**
*
* @author rizkykojek
*/
@Service(value = "logSalaryJournalService")
@Lazy
public class LogSalaryJournalServiceImpl extends IServiceImpl implements LogSalaryJournalService {
	
	@Autowired
	private LogSalaryJournalDao logSalaryJournalDao;	

	@Override
	public LogSalaryJournal getEntiyByPK(String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LogSalaryJournal getEntiyByPK(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LogSalaryJournal getEntiyByPK(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(LogSalaryJournal entity) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(LogSalaryJournal entity) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveOrUpdate(LogSalaryJournal enntity) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public LogSalaryJournal saveData(LogSalaryJournal entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LogSalaryJournal updateData(LogSalaryJournal entity)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LogSalaryJournal saveOrUpdateData(LogSalaryJournal entity)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LogSalaryJournal getEntityByPkIsActive(String id, Integer isActive)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LogSalaryJournal getEntityByPkIsActive(String id, Byte isActive)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LogSalaryJournal getEntityByPkIsActive(String id, Boolean isActive)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LogSalaryJournal getEntityByPkIsActive(Integer id, Integer isActive)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LogSalaryJournal getEntityByPkIsActive(Integer id, Byte isActive)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LogSalaryJournal getEntityByPkIsActive(Integer id, Boolean isActive)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LogSalaryJournal getEntityByPkIsActive(Long id, Integer isActive)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LogSalaryJournal getEntityByPkIsActive(Long id, Byte isActive)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LogSalaryJournal getEntityByPkIsActive(Long id, Boolean isActive)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(LogSalaryJournal entity) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void softDelete(LogSalaryJournal entity) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public Long getTotalData() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getTotalDataIsActive(Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getTotalDataIsActive(Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getTotalDataIsActive(Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LogSalaryJournal> getAllData() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LogSalaryJournal> getAllData(Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LogSalaryJournal> getAllData(Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LogSalaryJournal> getAllData(Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LogSalaryJournal> getAllDataPageAble(int firstResult,
			int maxResults, Order order) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LogSalaryJournal> getAllDataPageAbleIsActive(int firstResult,
			int maxResults, Order order, Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LogSalaryJournal> getAllDataPageAbleIsActive(int firstResult,
			int maxResults, Order order, Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LogSalaryJournal> getAllDataPageAbleIsActive(int firstResult,
			int maxResults, Order order, Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteByPeriodId(Long periodeId) throws Exception {
		logSalaryJournalDao.deleteByPeriodId(periodeId);

	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<ReportSalaryJounalModel> getAllDataReportGroupingByPeriod(int firstResult, int maxResults, Order orderable) throws Exception {
		return logSalaryJournalDao.getAllDataReportGroupingByPeriod(firstResult, maxResults, orderable);
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<LogSalaryJournal> getAllDataReportByParam(Long periodId, int firstResult, int maxResults, Order orderable) throws Exception {
		
		return logSalaryJournalDao.getAllDataReportByParam(periodId, firstResult, maxResults, orderable);
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public Long getTotalReportGroupingByPeriod() throws Exception {
		
		return logSalaryJournalDao.getTotalReportGroupingByPeriod();
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public Long getTotalReportByParam(Long periodId) throws Exception {
		
		return logSalaryJournalDao.getTotalReportByParam(periodId);
	}

}
