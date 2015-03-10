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
import com.inkubator.hrm.dao.LogUnregPayrollDao;
import com.inkubator.hrm.dao.TempUnregPayrollDao;
import com.inkubator.hrm.dao.TempUnregPayrollEmpPajakDao;
import com.inkubator.hrm.service.LogUnregPayrollService;

/**
 *
 * @author rizkykojek
 */
@Service(value = "logUnregPayrollService")
@Lazy
public class LogUnregPayrollServiceImpl extends IServiceImpl implements LogUnregPayrollService {

	@Autowired
	private LogUnregPayrollDao logUnregPayrollDao;
	@Autowired
	private TempUnregPayrollDao tempUnregPayrollDao;
	@Autowired
	private TempUnregPayrollEmpPajakDao tempUnregPayrollEmpPajakDao;
	
	@Override
	public com.inkubator.hrm.entity.LogUnregPayroll getEntiyByPK(String id)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public com.inkubator.hrm.entity.LogUnregPayroll getEntiyByPK(Integer id)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public com.inkubator.hrm.entity.LogUnregPayroll getEntiyByPK(Long id)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public void save(com.inkubator.hrm.entity.LogUnregPayroll entity)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public void update(com.inkubator.hrm.entity.LogUnregPayroll entity)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public void saveOrUpdate(com.inkubator.hrm.entity.LogUnregPayroll enntity)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public com.inkubator.hrm.entity.LogUnregPayroll saveData(
			com.inkubator.hrm.entity.LogUnregPayroll entity) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public com.inkubator.hrm.entity.LogUnregPayroll updateData(
			com.inkubator.hrm.entity.LogUnregPayroll entity) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public com.inkubator.hrm.entity.LogUnregPayroll saveOrUpdateData(
			com.inkubator.hrm.entity.LogUnregPayroll entity) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public com.inkubator.hrm.entity.LogUnregPayroll getEntityByPkIsActive(
			String id, Integer isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public com.inkubator.hrm.entity.LogUnregPayroll getEntityByPkIsActive(
			String id, Byte isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public com.inkubator.hrm.entity.LogUnregPayroll getEntityByPkIsActive(
			String id, Boolean isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public com.inkubator.hrm.entity.LogUnregPayroll getEntityByPkIsActive(
			Integer id, Integer isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public com.inkubator.hrm.entity.LogUnregPayroll getEntityByPkIsActive(
			Integer id, Byte isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public com.inkubator.hrm.entity.LogUnregPayroll getEntityByPkIsActive(
			Integer id, Boolean isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public com.inkubator.hrm.entity.LogUnregPayroll getEntityByPkIsActive(
			Long id, Integer isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public com.inkubator.hrm.entity.LogUnregPayroll getEntityByPkIsActive(
			Long id, Byte isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public com.inkubator.hrm.entity.LogUnregPayroll getEntityByPkIsActive(
			Long id, Boolean isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public void delete(com.inkubator.hrm.entity.LogUnregPayroll entity)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public void softDelete(com.inkubator.hrm.entity.LogUnregPayroll entity)
			throws Exception {
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
	public List<com.inkubator.hrm.entity.LogUnregPayroll> getAllData()
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<com.inkubator.hrm.entity.LogUnregPayroll> getAllData(
			Boolean isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<com.inkubator.hrm.entity.LogUnregPayroll> getAllData(
			Integer isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<com.inkubator.hrm.entity.LogUnregPayroll> getAllData(
			Byte isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<com.inkubator.hrm.entity.LogUnregPayroll> getAllDataPageAble(
			int firstResult, int maxResults, Order order) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<com.inkubator.hrm.entity.LogUnregPayroll> getAllDataPageAbleIsActive(
			int firstResult, int maxResults, Order order, Boolean isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<com.inkubator.hrm.entity.LogUnregPayroll> getAllDataPageAbleIsActive(
			int firstResult, int maxResults, Order order, Integer isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<com.inkubator.hrm.entity.LogUnregPayroll> getAllDataPageAbleIsActive(
			int firstResult, int maxResults, Order order, Byte isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteByUnregSalaryId(Long unregSalaryId) throws Exception {
		logUnregPayrollDao.deleteByUnregSalaryId(unregSalaryId);
		
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void afterPayrollProcess(Long unregSalaryId) throws Exception {
		
		/** delete all the record in the temporary table **/
		tempUnregPayrollDao.deleteByUnregSalaryId(unregSalaryId);
		tempUnregPayrollEmpPajakDao.deleteByUnregSalaryId(unregSalaryId);		
	}

}
