package com.inkubator.hrm.service.impl;

import java.util.List;

import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.hrm.dao.LogMonthEndTaxesDao;
import com.inkubator.hrm.entity.LogMonthEndTaxes;
import com.inkubator.hrm.service.LogMonthEndTaxesService;

/**
*
* @author rizkykojek
*/
@Service(value = "logMonthEndTaxesService")
@Lazy
public class LogMonthEndTaxesServiceImpl extends IServiceImpl implements LogMonthEndTaxesService {

	@Autowired
	private LogMonthEndTaxesDao logMonthEndTaxesDao;
	
	@Override
	public LogMonthEndTaxes getEntiyByPK(String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LogMonthEndTaxes getEntiyByPK(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LogMonthEndTaxes getEntiyByPK(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(LogMonthEndTaxes entity) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(LogMonthEndTaxes entity) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveOrUpdate(LogMonthEndTaxes enntity) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public LogMonthEndTaxes saveData(LogMonthEndTaxes entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LogMonthEndTaxes updateData(LogMonthEndTaxes entity)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LogMonthEndTaxes saveOrUpdateData(LogMonthEndTaxes entity)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LogMonthEndTaxes getEntityByPkIsActive(String id, Integer isActive)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LogMonthEndTaxes getEntityByPkIsActive(String id, Byte isActive)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LogMonthEndTaxes getEntityByPkIsActive(String id, Boolean isActive)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LogMonthEndTaxes getEntityByPkIsActive(Integer id, Integer isActive)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LogMonthEndTaxes getEntityByPkIsActive(Integer id, Byte isActive)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LogMonthEndTaxes getEntityByPkIsActive(Integer id, Boolean isActive)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LogMonthEndTaxes getEntityByPkIsActive(Long id, Integer isActive)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LogMonthEndTaxes getEntityByPkIsActive(Long id, Byte isActive)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LogMonthEndTaxes getEntityByPkIsActive(Long id, Boolean isActive)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(LogMonthEndTaxes entity) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void softDelete(LogMonthEndTaxes entity) throws Exception {
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
	public List<LogMonthEndTaxes> getAllData() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LogMonthEndTaxes> getAllData(Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LogMonthEndTaxes> getAllData(Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LogMonthEndTaxes> getAllData(Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LogMonthEndTaxes> getAllDataPageAble(int firstResult,
			int maxResults, Order order) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LogMonthEndTaxes> getAllDataPageAbleIsActive(int firstResult,
			int maxResults, Order order, Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LogMonthEndTaxes> getAllDataPageAbleIsActive(int firstResult,
			int maxResults, Order order, Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LogMonthEndTaxes> getAllDataPageAbleIsActive(int firstResult,
			int maxResults, Order order, Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteByPeriodId(Long periodId) throws Exception {
		logMonthEndTaxesDao.deleteByPeriodId(periodId);

	}

}