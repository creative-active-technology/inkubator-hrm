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

import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.hrm.dao.EmpDataDao;
import com.inkubator.hrm.dao.UnregPayComponentsDao;
import com.inkubator.hrm.dao.UnregPayComponentsExceptionDao;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.UnregPayComponents;
import com.inkubator.hrm.entity.UnregPayComponentsException;
import com.inkubator.hrm.entity.UnregPayComponentsExceptionId;
import com.inkubator.hrm.service.UnregPayComponentsExceptionService;
import com.inkubator.hrm.web.search.UnregPayComponentExceptionSearchParameter;
import com.inkubator.securitycore.util.UserInfoUtil;

/**
 *
 * @author rizkykojek
 */
@Service(value = "unregPayComponentsExceptionService")
@Lazy
public class UnregPayComponentsExceptionServiceImpl extends IServiceImpl implements UnregPayComponentsExceptionService {
	
	@Autowired
	private UnregPayComponentsExceptionDao unregPayComponentsExceptionDao;
	@Autowired
	private UnregPayComponentsDao unregPayComponentsDao;
	@Autowired
	private EmpDataDao empDataDao;
	
	@Override
	public UnregPayComponentsException getEntiyByPK(String id) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public UnregPayComponentsException getEntiyByPK(Integer id)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public UnregPayComponentsException getEntiyByPK(Long id) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void save(UnregPayComponentsException entity) throws Exception {
		EmpData empData = empDataDao.getEntiyByPK(entity.getId().getEmpDataId());
		UnregPayComponents unregPayComponents = unregPayComponentsDao.getEntiyByPK(entity.getId().getUnregPayComponentsId());
		        
		entity.setEmpData(empData);
		entity.setUnregPayComponents(unregPayComponents);
		entity.setCreatedBy(UserInfoUtil.getUserName());
		entity.setCreatedOn(new Date());
		
		unregPayComponentsExceptionDao.save(entity);
	}

	@Override	
	public void update(UnregPayComponentsException entity) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}
	
	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void update(UnregPayComponentsException entity, Long oldEmpDataId) throws Exception {
		UnregPayComponentsException deleted = unregPayComponentsExceptionDao.getEntityByPK(new UnregPayComponentsExceptionId(entity.getId().getUnregPayComponentsId(), oldEmpDataId));						
		
		UnregPayComponentsException unregPayComponentsException = new UnregPayComponentsException();
		unregPayComponentsException.setId(new UnregPayComponentsExceptionId(entity.getId().getUnregPayComponentsId(), entity.getId().getEmpDataId()));
		EmpData empData = empDataDao.getEntiyByPK(entity.getId().getEmpDataId());
		UnregPayComponents unregPayComponents = unregPayComponentsDao.getEntiyByPK(entity.getId().getUnregPayComponentsId());		
		unregPayComponentsException.setEmpData(empData);
		unregPayComponentsException.setUnregPayComponents(unregPayComponents);
		unregPayComponentsException.setNominal(entity.getNominal());
		unregPayComponentsException.setCreatedBy(deleted.getCreatedBy());
		unregPayComponentsException.setCreatedOn(deleted.getCreatedOn());
		unregPayComponentsException.setUpdatedBy(UserInfoUtil.getUserName());
		unregPayComponentsException.setUpdatedOn(new Date());
		
		this.delete(deleted);
		unregPayComponentsExceptionDao.save(unregPayComponentsException);
	}

	@Override
	public void saveOrUpdate(UnregPayComponentsException enntity)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public UnregPayComponentsException saveData(
			UnregPayComponentsException entity) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public UnregPayComponentsException updateData(
			UnregPayComponentsException entity) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public UnregPayComponentsException saveOrUpdateData(
			UnregPayComponentsException entity) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public UnregPayComponentsException getEntityByPkIsActive(String id,
			Integer isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public UnregPayComponentsException getEntityByPkIsActive(String id,
			Byte isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public UnregPayComponentsException getEntityByPkIsActive(String id,
			Boolean isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public UnregPayComponentsException getEntityByPkIsActive(Integer id,
			Integer isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public UnregPayComponentsException getEntityByPkIsActive(Integer id,
			Byte isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public UnregPayComponentsException getEntityByPkIsActive(Integer id,
			Boolean isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public UnregPayComponentsException getEntityByPkIsActive(Long id,
			Integer isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public UnregPayComponentsException getEntityByPkIsActive(Long id,
			Byte isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public UnregPayComponentsException getEntityByPkIsActive(Long id,
			Boolean isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(UnregPayComponentsException entity) throws Exception {
		unregPayComponentsExceptionDao.delete(entity);

	}

	@Override
	public void softDelete(UnregPayComponentsException entity) throws Exception {
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
	public List<UnregPayComponentsException> getAllData() throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<UnregPayComponentsException> getAllData(Boolean isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<UnregPayComponentsException> getAllData(Integer isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<UnregPayComponentsException> getAllData(Byte isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<UnregPayComponentsException> getAllDataPageAble(
			int firstResult, int maxResults, Order order) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<UnregPayComponentsException> getAllDataPageAbleIsActive(
			int firstResult, int maxResults, Order order, Boolean isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<UnregPayComponentsException> getAllDataPageAbleIsActive(
			int firstResult, int maxResults, Order order, Integer isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<UnregPayComponentsException> getAllDataPageAbleIsActive(
			int firstResult, int maxResults, Order order, Byte isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<UnregPayComponentsException> getByParam(UnregPayComponentExceptionSearchParameter searchParameter, int firstResult, int maxResults, Order order) {		
		return unregPayComponentsExceptionDao.getByParam(searchParameter, firstResult, maxResults, order);
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public Long getTotalByParam(UnregPayComponentExceptionSearchParameter searchParameter) {
		return unregPayComponentsExceptionDao.getTotalByParam(searchParameter);
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public UnregPayComponentsException getEntityByPK(UnregPayComponentsExceptionId id) {		
		return unregPayComponentsExceptionDao.getEntityByPK(id);
	}

}
