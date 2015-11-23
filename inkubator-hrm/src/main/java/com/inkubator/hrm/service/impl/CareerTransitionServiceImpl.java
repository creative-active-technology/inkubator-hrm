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
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.CareerEmpStatusDao;
import com.inkubator.hrm.dao.CareerTerminationTypeDao;
import com.inkubator.hrm.dao.CareerTransitionDao;
import com.inkubator.hrm.dao.SystemCareerConstDao;
import com.inkubator.hrm.dao.SystemLetterReferenceDao;
import com.inkubator.hrm.entity.CareerTransition;
import com.inkubator.hrm.service.CareerTransitionService;
import com.inkubator.hrm.web.search.CareerTransitionSearchParameter;
import com.inkubator.securitycore.util.UserInfoUtil;

@Service(value = "careerTransitionService")
@Lazy
public class CareerTransitionServiceImpl extends IServiceImpl implements CareerTransitionService{

	@Autowired
	private CareerTransitionDao careerTransitionDao;
	@Autowired
	private CareerEmpStatusDao careerEmpTypeDao;
	@Autowired
	private SystemLetterReferenceDao systemLetterReferenceDao;
	@Autowired
	private CareerTerminationTypeDao careerTerminationTypeDao;
	@Autowired
	private SystemCareerConstDao systemCareerConstDao;
	
	
	@Override
	public CareerTransition getEntiyByPK(String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CareerTransition getEntiyByPK(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 50)
    public CareerTransition getEntiyByPK(Long id) throws Exception {
		return careerTransitionDao.getEntiyByPK(id);
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void save(CareerTransition entity) throws Exception {
		entity.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
		if(entity.getTransitionRole() == HRMConstant.CAREER_EMPLOYEE_STATUS){
			entity.setCareerEmpStatus(careerEmpTypeDao.getEntiyByPK(entity.getCareerEmpStatus().getId()));
		}else if(entity.getTransitionRole() == HRMConstant.CAREER_TERMINATION_TYPE){
			entity.setCareerTerminationType(careerTerminationTypeDao.getEntiyByPK(entity.getCareerTerminationType().getId()));
    	}else if(entity.getTransitionRole() == HRMConstant.CAREER_TRANSITION){
    		entity.setSystemCareerConst(systemCareerConstDao.getEntiyByPK(entity.getSystemCareerConst().getId()));
    	}
		entity.setSystemLetterReference(systemLetterReferenceDao.getEntiyByPK(entity.getSystemLetterReference().getId()));
		entity.setCreatedBy(UserInfoUtil.getUserName());
		entity.setCreatedOn(new Date());
		careerTransitionDao.save(entity);
	}
	
	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void update(CareerTransition entity) throws Exception {
		CareerTransition careerTransition = careerTransitionDao.getEntityByPKWithDetail(entity.getId());
		if(entity.getTransitionRole() == HRMConstant.CAREER_EMPLOYEE_STATUS){
			careerTransition.setCareerEmpStatus(careerEmpTypeDao.getEntiyByPK(entity.getCareerEmpStatus().getId()));
		}else if(entity.getTransitionRole() == HRMConstant.CAREER_TERMINATION_TYPE){
			careerTransition.setCareerTerminationType(careerTerminationTypeDao.getEntiyByPK(entity.getCareerTerminationType().getId()));
    	}else if(entity.getTransitionRole() == HRMConstant.CAREER_TRANSITION){
    		careerTransition.setSystemCareerConst(systemCareerConstDao.getEntiyByPK(entity.getSystemCareerConst().getId()));
    	}
		careerTransition.setTransitionRole(entity.getTransitionRole());
		careerTransition.setSystemLetterReference(systemLetterReferenceDao.getEntiyByPK(entity.getSystemLetterReference().getId()));
		careerTransition.setTransitionCode(entity.getTransitionCode());
		careerTransition.setTransitionName(entity.getTransitionName());
		careerTransition.setDescription(entity.getDescription());
		careerTransition.setUpdatedBy(UserInfoUtil.getUserName());
		careerTransition.setUpdatedOn(new Date());
		careerTransitionDao.save(careerTransition);
		
	}

	@Override
	public void saveOrUpdate(CareerTransition enntity) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CareerTransition saveData(CareerTransition entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CareerTransition updateData(CareerTransition entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CareerTransition saveOrUpdateData(CareerTransition entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CareerTransition getEntityByPkIsActive(String id, Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CareerTransition getEntityByPkIsActive(String id, Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CareerTransition getEntityByPkIsActive(String id, Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CareerTransition getEntityByPkIsActive(Integer id, Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CareerTransition getEntityByPkIsActive(Integer id, Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CareerTransition getEntityByPkIsActive(Integer id, Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CareerTransition getEntityByPkIsActive(Long id, Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CareerTransition getEntityByPkIsActive(Long id, Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CareerTransition getEntityByPkIsActive(Long id, Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void delete(CareerTransition entity) throws Exception {
		this.careerTransitionDao.delete(entity);
	}

	@Override
	public void softDelete(CareerTransition entity) throws Exception {
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
	public List<CareerTransition> getAllData() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CareerTransition> getAllData(Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CareerTransition> getAllData(Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CareerTransition> getAllData(Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CareerTransition> getAllDataPageAble(int firstResult, int maxResults, Order order) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CareerTransition> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order,
			Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CareerTransition> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order,
			Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CareerTransition> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order,
			Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<CareerTransition> getByParam(CareerTransitionSearchParameter searchParameter, int firstResult, int maxResults, Order orderable) throws Exception {
		return careerTransitionDao.getByParam(searchParameter, firstResult, maxResults, orderable);
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalByParam(CareerTransitionSearchParameter parameter) throws Exception {
		return careerTransitionDao.getTotalByParam(parameter);
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public CareerTransition getEntityByPKWithDetail(Long id) throws Exception {
		return careerTransitionDao.getEntityByPKWithDetail(id);
	}

}
