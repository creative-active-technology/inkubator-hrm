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
import com.inkubator.hrm.dao.CareerAwardTypeDao;
import com.inkubator.hrm.dao.SystemLetterReferenceDao;
import com.inkubator.hrm.entity.CareerAwardType;
import com.inkubator.hrm.entity.SystemLetterReference;
import com.inkubator.hrm.service.CareerAwardTypeService;
import com.inkubator.hrm.web.search.CareerAwardTypeSearchParameter;
import com.inkubator.securitycore.util.UserInfoUtil;

@Service(value = "careerAwardTypeService")
@Lazy
public class CareerAwardTypeServiceImpl extends IServiceImpl implements CareerAwardTypeService{
	
	@Autowired
	private CareerAwardTypeDao careerAwardTypeDao;
	
	@Autowired
	private SystemLetterReferenceDao systemLetterReferenceDao;

	@Override
	public CareerAwardType getEntiyByPK(String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CareerAwardType getEntiyByPK(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public CareerAwardType getEntiyByPK(Long id) throws Exception {
		return this.careerAwardTypeDao.getEntiyByPK(id);
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void save(CareerAwardType entity) throws Exception {
		long totalDuplicates = careerAwardTypeDao.getTotalByCode(entity.getCode());
		if (totalDuplicates > 0){
			throw new BussinessException("marital.error_duplicate_marital_code");
		}
		
		long totalDuplicateName = careerAwardTypeDao.getTotalByName(entity.getName());
		if (totalDuplicateName > 0){
			throw new BussinessException("appraisalDetail.error_duplicate_appraisalDetail_name");
		}

		
		entity.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
		entity.setSystemLetterReferenceByLetterTemplateId(systemLetterReferenceDao.getEntiyByPK(entity.getSystemLetterReferenceByLetterTemplateId().getId()));
		entity.setSystemLetterReferenceByCertificateLetterTemplateId(systemLetterReferenceDao.getEntiyByPK(entity.getSystemLetterReferenceByCertificateLetterTemplateId().getId()));
		entity.setCreatedBy(UserInfoUtil.getUserName());
		entity.setCreatedOn(new Date());
		careerAwardTypeDao.save(entity);
	}

	@Override
	@Transactional(readOnly =  false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void update(CareerAwardType entity) throws Exception {
		long totalduplicates = careerAwardTypeDao.getTotalByCodeAndNotId(entity.getCode(), entity.getId());
		if (totalduplicates > 0){
			throw new BussinessException("marital.error_duplicate_marital_code");
		}
		CareerAwardType update = careerAwardTypeDao.getEntiyByPK(entity.getId());
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
		this.careerAwardTypeDao.update(update);
	}

	@Override
	public void saveOrUpdate(CareerAwardType enntity) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CareerAwardType saveData(CareerAwardType entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CareerAwardType updateData(CareerAwardType entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CareerAwardType saveOrUpdateData(CareerAwardType entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CareerAwardType getEntityByPkIsActive(String id, Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CareerAwardType getEntityByPkIsActive(String id, Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CareerAwardType getEntityByPkIsActive(String id, Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CareerAwardType getEntityByPkIsActive(Integer id, Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CareerAwardType getEntityByPkIsActive(Integer id, Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CareerAwardType getEntityByPkIsActive(Integer id, Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CareerAwardType getEntityByPkIsActive(Long id, Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CareerAwardType getEntityByPkIsActive(Long id, Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CareerAwardType getEntityByPkIsActive(Long id, Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(CareerAwardType entity) throws Exception {
		this.careerAwardTypeDao.delete(entity);
	}

	@Override
	public void softDelete(CareerAwardType entity) throws Exception {
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
	public List<CareerAwardType> getAllData() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CareerAwardType> getAllData(Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CareerAwardType> getAllData(Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CareerAwardType> getAllData(Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CareerAwardType> getAllDataPageAble(int firstResult, int maxResults, Order order) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CareerAwardType> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order,
			Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CareerAwardType> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order,
			Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CareerAwardType> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order,
			Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<CareerAwardType> getByParam(CareerAwardTypeSearchParameter searchParameter, int firstResult,
			int maxResult, Order order) {
		return this.careerAwardTypeDao.getByParam(searchParameter, firstResult, maxResult, order);
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public Long getTotalByParam(CareerAwardTypeSearchParameter searchParameter) {
		return this.careerAwardTypeDao.getTotalByParam(searchParameter);
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public CareerAwardType getEntityByPkWithDetail(long id) {
		return this.careerAwardTypeDao.getEntityByPkWithDetail(id);
	}

}
