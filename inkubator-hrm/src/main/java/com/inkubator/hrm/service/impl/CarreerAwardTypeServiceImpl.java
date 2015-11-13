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
import com.inkubator.hrm.dao.CarreerAwardTypeDao;
import com.inkubator.hrm.dao.SystemLetterReferenceDao;
import com.inkubator.hrm.entity.CarreerAwardType;
import com.inkubator.hrm.entity.SystemLetterReference;
import com.inkubator.hrm.service.CarreerAwardTypeService;
import com.inkubator.hrm.web.search.CarreerAwardTypeSearchParameter;
import com.inkubator.securitycore.util.UserInfoUtil;

@Service(value = "carreerAwardTypeService")
@Lazy
public class CarreerAwardTypeServiceImpl extends IServiceImpl implements CarreerAwardTypeService{
	
	@Autowired
	private CarreerAwardTypeDao carreerAwardTypeDao;
	
	@Autowired
	private SystemLetterReferenceDao systemLetterReferenceDao;

	@Override
	public CarreerAwardType getEntiyByPK(String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CarreerAwardType getEntiyByPK(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public CarreerAwardType getEntiyByPK(Long id) throws Exception {
		return this.carreerAwardTypeDao.getEntiyByPK(id);
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void save(CarreerAwardType entity) throws Exception {
		long totalDuplicates = carreerAwardTypeDao.getTotalByCode(entity.getCode());
		if (totalDuplicates > 0){
			throw new BussinessException("marital.error_duplicate_marital_code");
		}
		
		long totalDuplicateName = carreerAwardTypeDao.getTotalByName(entity.getName());
		if (totalDuplicateName > 0){
			throw new BussinessException("appraisalDetail.error_duplicate_appraisalDetail_name");
		}

		
		entity.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
		entity.setSystemLetterReferenceByLetterTemplateId(systemLetterReferenceDao.getEntiyByPK(entity.getSystemLetterReferenceByLetterTemplateId().getId()));
		entity.setSystemLetterReferenceByCertificateLetterTemplateId(systemLetterReferenceDao.getEntiyByPK(entity.getSystemLetterReferenceByCertificateLetterTemplateId().getId()));
		entity.setCreatedBy(UserInfoUtil.getUserName());
		entity.setCreatedOn(new Date());
		carreerAwardTypeDao.save(entity);
	}

	@Override
	@Transactional(readOnly =  false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void update(CarreerAwardType entity) throws Exception {
		long totalduplicates = carreerAwardTypeDao.getTotalByCodeAndNotId(entity.getCode(), entity.getId());
		if (totalduplicates > 0){
			throw new BussinessException("marital.error_duplicate_marital_code");
		}
		CarreerAwardType update = carreerAwardTypeDao.getEntiyByPK(entity.getId());
		update.setCode(entity.getCode());
		update.setName(entity.getName());
		update.setDescription(entity.getDescription());
		update.setValidity(entity.getValidity());
		update.setPoint(entity.getPoint());
		update.setAwardSource(entity.getAwardSource());
		SystemLetterReference letterTemplate = systemLetterReferenceDao.getEntiyByPK(entity.getSystemLetterReferenceByLetterTemplateId().getId());
		update.setSystemLetterReferenceByLetterTemplateId(letterTemplate);
		SystemLetterReference certificateLetterTemplate = systemLetterReferenceDao.getEntiyByPK(entity.getSystemLetterReferenceByCertificateLetterTemplateId().getId());
		update.setSystemLetterReferenceByCertificateLetterTemplateId(certificateLetterTemplate);
		update.setUpdatedBy(UserInfoUtil.getUserName());
		update.setUpdatedOn(new Date());
		this.carreerAwardTypeDao.update(update);
	}

	@Override
	public void saveOrUpdate(CarreerAwardType enntity) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CarreerAwardType saveData(CarreerAwardType entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CarreerAwardType updateData(CarreerAwardType entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CarreerAwardType saveOrUpdateData(CarreerAwardType entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CarreerAwardType getEntityByPkIsActive(String id, Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CarreerAwardType getEntityByPkIsActive(String id, Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CarreerAwardType getEntityByPkIsActive(String id, Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CarreerAwardType getEntityByPkIsActive(Integer id, Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CarreerAwardType getEntityByPkIsActive(Integer id, Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CarreerAwardType getEntityByPkIsActive(Integer id, Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CarreerAwardType getEntityByPkIsActive(Long id, Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CarreerAwardType getEntityByPkIsActive(Long id, Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CarreerAwardType getEntityByPkIsActive(Long id, Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(CarreerAwardType entity) throws Exception {
		this.carreerAwardTypeDao.delete(entity);
	}

	@Override
	public void softDelete(CarreerAwardType entity) throws Exception {
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
	public List<CarreerAwardType> getAllData() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CarreerAwardType> getAllData(Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CarreerAwardType> getAllData(Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CarreerAwardType> getAllData(Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CarreerAwardType> getAllDataPageAble(int firstResult, int maxResults, Order order) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CarreerAwardType> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order,
			Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CarreerAwardType> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order,
			Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CarreerAwardType> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order,
			Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<CarreerAwardType> getByParam(CarreerAwardTypeSearchParameter searchParameter, int firstResult,
			int maxResult, Order order) {
		return this.carreerAwardTypeDao.getByParam(searchParameter, firstResult, maxResult, order);
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public Long getTotalByParam(CarreerAwardTypeSearchParameter searchParameter) {
		return this.carreerAwardTypeDao.getTotalByParam(searchParameter);
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public CarreerAwardType getEntityByPkWithDetail(long id) {
		return this.carreerAwardTypeDao.getEntityByPkWithDetail(id);
	}

}
