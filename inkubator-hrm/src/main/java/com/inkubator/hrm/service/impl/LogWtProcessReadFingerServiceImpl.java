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
import com.inkubator.hrm.dao.LogWtProcessReadFingerDao;
import com.inkubator.hrm.entity.LogWtProcessReadFinger;
import com.inkubator.hrm.service.LogWtProcessReadFingerService;

/**
 *
 * @author rizkykojek
 */
@Service (value = "logWtProcessReadFingerService")
@Lazy
public class LogWtProcessReadFingerServiceImpl extends IServiceImpl implements
		LogWtProcessReadFingerService {

	@Autowired
	private LogWtProcessReadFingerDao logWtProcessReadFingerDao;
	
	@Override
	public LogWtProcessReadFinger getEntiyByPK(String id) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public LogWtProcessReadFinger getEntiyByPK(Integer id) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public LogWtProcessReadFinger getEntiyByPK(Long id) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public void save(LogWtProcessReadFinger entity) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public void update(LogWtProcessReadFinger entity) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public void saveOrUpdate(LogWtProcessReadFinger enntity) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public LogWtProcessReadFinger saveData(LogWtProcessReadFinger entity)
			throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public LogWtProcessReadFinger updateData(LogWtProcessReadFinger entity)
			throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public LogWtProcessReadFinger saveOrUpdateData(LogWtProcessReadFinger entity)
			throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public LogWtProcessReadFinger getEntityByPkIsActive(String id,
			Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public LogWtProcessReadFinger getEntityByPkIsActive(String id, Byte isActive)
			throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public LogWtProcessReadFinger getEntityByPkIsActive(String id,
			Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public LogWtProcessReadFinger getEntityByPkIsActive(Integer id,
			Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public LogWtProcessReadFinger getEntityByPkIsActive(Integer id,
			Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public LogWtProcessReadFinger getEntityByPkIsActive(Integer id,
			Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public LogWtProcessReadFinger getEntityByPkIsActive(Long id,
			Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public LogWtProcessReadFinger getEntityByPkIsActive(Long id, Byte isActive)
			throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public LogWtProcessReadFinger getEntityByPkIsActive(Long id,
			Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public void delete(LogWtProcessReadFinger entity) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public void softDelete(LogWtProcessReadFinger entity) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public Long getTotalData() throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public Long getTotalDataIsActive(Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public Long getTotalDataIsActive(Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public Long getTotalDataIsActive(Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public List<LogWtProcessReadFinger> getAllData() throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public List<LogWtProcessReadFinger> getAllData(Boolean isActive)
			throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public List<LogWtProcessReadFinger> getAllData(Integer isActive)
			throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public List<LogWtProcessReadFinger> getAllData(Byte isActive)
			throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public List<LogWtProcessReadFinger> getAllDataPageAble(int firstResult,
			int maxResults, Order order) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public List<LogWtProcessReadFinger> getAllDataPageAbleIsActive(
			int firstResult, int maxResults, Order order, Boolean isActive)
			throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public List<LogWtProcessReadFinger> getAllDataPageAbleIsActive(
			int firstResult, int maxResults, Order order, Integer isActive)
			throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public List<LogWtProcessReadFinger> getAllDataPageAbleIsActive(
			int firstResult, int maxResults, Order order, Byte isActive)
			throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteByPeriodId(Long periodId) {
		
		logWtProcessReadFingerDao.deleteByPeriodId(periodId);
	}

}
