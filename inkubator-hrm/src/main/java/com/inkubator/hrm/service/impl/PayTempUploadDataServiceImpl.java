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
import com.inkubator.hrm.dao.EmpDataDao;
import com.inkubator.hrm.dao.PaySalaryComponentDao;
import com.inkubator.hrm.dao.PayTempUploadDataDao;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.PaySalaryComponent;
import com.inkubator.hrm.entity.PayTempUploadData;
import com.inkubator.hrm.service.PayTempUploadDataService;
import com.inkubator.hrm.web.search.PayTempUploadDataSearchParameter;
import com.inkubator.securitycore.util.UserInfoUtil;

/**
 *
 * @author rizkykojek
 */
@Service(value = "payTempUploadDataService")
@Lazy
public class PayTempUploadDataServiceImpl extends IServiceImpl implements PayTempUploadDataService {

	@Autowired
	private PayTempUploadDataDao payTempUploadDataDao;
	@Autowired
	private EmpDataDao empDataDao;
	@Autowired
	private PaySalaryComponentDao paySalaryComponentDao;
	
	@Override
	public PayTempUploadData getEntiyByPK(String id) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public PayTempUploadData getEntiyByPK(Integer id) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public PayTempUploadData getEntiyByPK(Long id) throws Exception {
		return payTempUploadDataDao.getEntiyByPK(id);

	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor =Exception.class)
	public void save(PayTempUploadData entity) throws Exception {
		
		EmpData empData = empDataDao.getEntiyByPK(entity.getEmpData().getId());
		PaySalaryComponent paySalaryComponent = paySalaryComponentDao.getEntiyByPK(entity.getPaySalaryComponent().getId());
		
		entity.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
		entity.setPaySalaryComponent(paySalaryComponent);
		entity.setEmpData(empData);
        entity.setCreatedBy(UserInfoUtil.getUserName());
        entity.setCreatedOn(new Date());
        this.payTempUploadDataDao.save(entity);
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor =Exception.class)
	public void update(PayTempUploadData entity) throws Exception {
		
		EmpData empData = empDataDao.getEntiyByPK(entity.getEmpData().getId());
		PayTempUploadData payTempUploadData = payTempUploadDataDao.getEntiyByPK(entity.getId());
		payTempUploadData.setEmpData(empData);
		payTempUploadData.setNominalValue(entity.getNominalValue());
		payTempUploadData.setUpdatedBy(UserInfoUtil.getUserName());
		payTempUploadData.setUpdatedOn(new Date());
        this.payTempUploadDataDao.save(payTempUploadData);

	}

	@Override
	public void saveOrUpdate(PayTempUploadData enntity) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public PayTempUploadData saveData(PayTempUploadData entity)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public PayTempUploadData updateData(PayTempUploadData entity)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public PayTempUploadData saveOrUpdateData(PayTempUploadData entity)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public PayTempUploadData getEntityByPkIsActive(String id, Integer isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public PayTempUploadData getEntityByPkIsActive(String id, Byte isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public PayTempUploadData getEntityByPkIsActive(String id, Boolean isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public PayTempUploadData getEntityByPkIsActive(Integer id, Integer isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public PayTempUploadData getEntityByPkIsActive(Integer id, Byte isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public PayTempUploadData getEntityByPkIsActive(Integer id, Boolean isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public PayTempUploadData getEntityByPkIsActive(Long id, Integer isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public PayTempUploadData getEntityByPkIsActive(Long id, Byte isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public PayTempUploadData getEntityByPkIsActive(Long id, Boolean isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor =Exception.class)
	public void delete(PayTempUploadData entity) throws Exception {
		this.payTempUploadDataDao.delete(entity);

	}

	@Override
	public void softDelete(PayTempUploadData entity) throws Exception {
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
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<PayTempUploadData> getAllData() throws Exception {
		return this.payTempUploadDataDao.getAllData();

	}

	@Override
	public List<PayTempUploadData> getAllData(Boolean isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<PayTempUploadData> getAllData(Integer isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<PayTempUploadData> getAllData(Byte isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<PayTempUploadData> getAllDataPageAble(int firstResult,
			int maxResults, Order order) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<PayTempUploadData> getAllDataPageAbleIsActive(int firstResult,
			int maxResults, Order order, Boolean isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<PayTempUploadData> getAllDataPageAbleIsActive(int firstResult,
			int maxResults, Order order, Integer isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<PayTempUploadData> getAllDataPageAbleIsActive(int firstResult,
			int maxResults, Order order, Byte isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<PayTempUploadData> getAllDataByParam(PayTempUploadDataSearchParameter parameter, int firstResult, int maxResults, Order orderable) {
		return this.payTempUploadDataDao.getAllDataByParam(parameter, firstResult, maxResults, orderable);

	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public Long getTotalByParam(PayTempUploadDataSearchParameter parameter) {
		return this.payTempUploadDataDao.getTotalByParam(parameter);

	}
	
	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public Long getTotalByPaySalaryComponentId(Long paySalaryComponentId){
		return this.payTempUploadDataDao.getTotalByPaySalaryComponentId(paySalaryComponentId);
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public PayTempUploadData getEntityByPkWithDetail(Long id) {
		return this.payTempUploadDataDao.getEntityByPkWithDetail(id);

	}
	
	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public Double getTotalSalaryByPaySalaryComponentId(Long paySalaryComponentId){
		return this.payTempUploadDataDao.getTotalSalaryByPaySalaryComponentId(paySalaryComponentId);
	}

}
