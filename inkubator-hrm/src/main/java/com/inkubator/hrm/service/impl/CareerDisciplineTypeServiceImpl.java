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
import com.inkubator.hrm.dao.CareerDisciplineTypeDao;
import com.inkubator.hrm.dao.SystemLetterReferenceDao;
import com.inkubator.hrm.entity.CareerDisciplineType;
import com.inkubator.hrm.entity.SystemLetterReference;
import com.inkubator.hrm.service.CareerDisciplineTypeService;
import com.inkubator.hrm.web.search.CareerDisciplineTypeSearchParameter;
import com.inkubator.securitycore.util.UserInfoUtil;

@Service(value = "careerDisciplineTypeService")
@Lazy
public class CareerDisciplineTypeServiceImpl extends IServiceImpl implements CareerDisciplineTypeService{
	
	@Autowired
	CareerDisciplineTypeDao careerdisciplinetypeDao;
	
	@Autowired
	SystemLetterReferenceDao systemLetterReferenceDao;

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(CareerDisciplineType entity) throws Exception {
		this.careerdisciplinetypeDao.delete(entity);
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, timeout = 50)
	public List<CareerDisciplineType> getAllData() throws Exception {
		return this.careerdisciplinetypeDao.getAllData();
	}

	@Override
	public List<CareerDisciplineType> getAllData(Boolean arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CareerDisciplineType> getAllData(Integer arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CareerDisciplineType> getAllData(Byte arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CareerDisciplineType> getAllDataPageAble(int arg0, int arg1, Order arg2) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CareerDisciplineType> getAllDataPageAbleIsActive(int arg0, int arg1, Order arg2, Boolean arg3)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CareerDisciplineType> getAllDataPageAbleIsActive(int arg0, int arg1, Order arg2, Integer arg3)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CareerDisciplineType> getAllDataPageAbleIsActive(int arg0, int arg1, Order arg2, Byte arg3)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CareerDisciplineType getEntityByPkIsActive(String arg0, Integer arg1) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CareerDisciplineType getEntityByPkIsActive(String arg0, Byte arg1) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CareerDisciplineType getEntityByPkIsActive(String arg0, Boolean arg1) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CareerDisciplineType getEntityByPkIsActive(Integer arg0, Integer arg1) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CareerDisciplineType getEntityByPkIsActive(Integer arg0, Byte arg1) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CareerDisciplineType getEntityByPkIsActive(Integer arg0, Boolean arg1) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CareerDisciplineType getEntityByPkIsActive(Long arg0, Integer arg1) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CareerDisciplineType getEntityByPkIsActive(Long arg0, Byte arg1) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CareerDisciplineType getEntityByPkIsActive(Long arg0, Boolean arg1) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CareerDisciplineType getEntiyByPK(String arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CareerDisciplineType getEntiyByPK(Integer arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public CareerDisciplineType getEntiyByPK(Long id) throws Exception {
		return this.careerdisciplinetypeDao.getEntiyByPK(id);
	}

	@Override
	public Long getTotalData() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getTotalDataIsActive(Boolean arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getTotalDataIsActive(Integer arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getTotalDataIsActive(Byte arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void save(CareerDisciplineType entity) throws Exception {
		long totalDuplicatesCode = careerdisciplinetypeDao.getTotalByCode(entity.getCode());
		if(totalDuplicatesCode > 0){
			throw new BussinessException("marital.error_duplicate_marital_code");
		}
		long totalDuplicatesName = careerdisciplinetypeDao.getTotalByName(entity.getName());
		if(totalDuplicatesName > 0){
			throw new BussinessException("appraisalDetail.error_duplicate_appraisalDetail_name");
		}
		entity.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
		if(entity.getSystemLetterReference() != null) {
			entity.setSystemLetterReference(systemLetterReferenceDao.getEntiyByPK(entity.getSystemLetterReference().getId()));
		}
		entity.setCreatedBy(UserInfoUtil.getUserName());
		entity.setCreatedOn(new Date());
		this.careerdisciplinetypeDao.save(entity);
	}

	@Override
	public CareerDisciplineType saveData(CareerDisciplineType arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveOrUpdate(CareerDisciplineType arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CareerDisciplineType saveOrUpdateData(CareerDisciplineType arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void softDelete(CareerDisciplineType arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void update(CareerDisciplineType entity) throws Exception {
		long totalDuplicatesCode = careerdisciplinetypeDao.getTotalByCodeAndNotId(entity.getCode(), entity.getId());
		if(totalDuplicatesCode > 0){
			throw new BussinessException("marital.error_duplicate_marital_code");
		}
		long totalDuplicatesName = careerdisciplinetypeDao.getTotalByNameAndNotId(entity.getCode(), entity.getId());
		if(totalDuplicatesName > 0){
			throw new BussinessException("appraisalDetail.error_duplicate_appraisalDetail_name");
		}
		// TODO Auto-generated method stub
		
		CareerDisciplineType update = careerdisciplinetypeDao.getEntiyByPK(entity.getId());
		update.setCode(entity.getCode());
		update.setName(entity.getName());
		update.setDescription(entity.getDescription());
		update.setValidity(entity.getValidity());
		update.setPoint(entity.getPoint());
		if(entity.getSystemLetterReference() != null) {
			SystemLetterReference systemLetterReference = this.systemLetterReferenceDao.getEntiyByPK(entity.getSystemLetterReference().getId());
			update.setSystemLetterReference(systemLetterReference);
		} else {
			update.setSystemLetterReference(null);
		}
		update.setUpdatedBy(UserInfoUtil.getUserName());
		update.setUpdatedOn(new Date());
		this.careerdisciplinetypeDao.update(update);
	}

	@Override
	public CareerDisciplineType updateData(CareerDisciplineType arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<CareerDisciplineType> getByParam(CareerDisciplineTypeSearchParameter searchParameter, int firstResult,
			int maxResults, Order order) {
		return this.careerdisciplinetypeDao.getByParam(searchParameter, firstResult, maxResults, order);
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public Long getTotalDataByParam(CareerDisciplineTypeSearchParameter searchParameter) {
		return this.careerdisciplinetypeDao.getTotalDataByParam(searchParameter);
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public CareerDisciplineType getEntityByIdWithDetail(Long id) throws Exception {
		return careerdisciplinetypeDao.getEntityByIdWithDetail(id);
	}

}
