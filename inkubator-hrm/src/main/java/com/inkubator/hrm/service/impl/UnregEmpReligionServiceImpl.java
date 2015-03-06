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
import com.inkubator.hrm.dao.UnregEmpReligionDao;
import com.inkubator.hrm.entity.UnregEmpReligion;
import com.inkubator.hrm.service.UnregEmpReligionService;

/**
 *
 * @author rizkykojek
 */
@Service(value = "unregEmpReligionService")
@Lazy
public class UnregEmpReligionServiceImpl extends IServiceImpl implements UnregEmpReligionService {

	@Autowired
	private UnregEmpReligionDao unregEmpReligionDao;
	
	@Override
	public UnregEmpReligion getEntiyByPK(String id) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public UnregEmpReligion getEntiyByPK(Integer id) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public UnregEmpReligion getEntiyByPK(Long id) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public void save(UnregEmpReligion entity) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public void update(UnregEmpReligion entity) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public void saveOrUpdate(UnregEmpReligion enntity) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public UnregEmpReligion saveData(UnregEmpReligion entity) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public UnregEmpReligion updateData(UnregEmpReligion entity)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public UnregEmpReligion saveOrUpdateData(UnregEmpReligion entity)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public UnregEmpReligion getEntityByPkIsActive(String id, Integer isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public UnregEmpReligion getEntityByPkIsActive(String id, Byte isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public UnregEmpReligion getEntityByPkIsActive(String id, Boolean isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public UnregEmpReligion getEntityByPkIsActive(Integer id, Integer isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public UnregEmpReligion getEntityByPkIsActive(Integer id, Byte isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public UnregEmpReligion getEntityByPkIsActive(Integer id, Boolean isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public UnregEmpReligion getEntityByPkIsActive(Long id, Integer isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public UnregEmpReligion getEntityByPkIsActive(Long id, Byte isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public UnregEmpReligion getEntityByPkIsActive(Long id, Boolean isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public void delete(UnregEmpReligion entity) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public void softDelete(UnregEmpReligion entity) throws Exception {
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
	public List<UnregEmpReligion> getAllData() throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<UnregEmpReligion> getAllData(Boolean isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<UnregEmpReligion> getAllData(Integer isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<UnregEmpReligion> getAllData(Byte isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<UnregEmpReligion> getAllDataPageAble(int firstResult,
			int maxResults, Order order) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<UnregEmpReligion> getAllDataPageAbleIsActive(int firstResult,
			int maxResults, Order order, Boolean isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<UnregEmpReligion> getAllDataPageAbleIsActive(int firstResult,
			int maxResults, Order order, Integer isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<UnregEmpReligion> getAllDataPageAbleIsActive(int firstResult,
			int maxResults, Order order, Byte isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<UnregEmpReligion> getAllDataByUnregSalaryId(Long unregSalaryId) throws Exception {
		return unregEmpReligionDao.getAllDataByUnregSalaryId(unregSalaryId);

	}

}
