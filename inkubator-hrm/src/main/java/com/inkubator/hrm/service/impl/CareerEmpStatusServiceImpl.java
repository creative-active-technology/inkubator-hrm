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
import com.inkubator.hrm.dao.CareerEmpStatusDao;
import com.inkubator.hrm.dao.EmployeeTypeDao;
import com.inkubator.hrm.dao.SystemLetterReferenceDao;
import com.inkubator.hrm.entity.CareerEmpStatus;
import com.inkubator.hrm.entity.EmployeeType;
import com.inkubator.hrm.entity.SystemLetterReference;
import com.inkubator.hrm.service.CareerEmpStatusService;
import com.inkubator.hrm.web.search.CareerEmpStatusSearchParameter;
import com.inkubator.securitycore.util.UserInfoUtil;

/**
 *
 * @author rizkykojek
 */
@Service(value = "careerEmpStatusService")
@Lazy
public class CareerEmpStatusServiceImpl extends IServiceImpl implements CareerEmpStatusService {

	@Autowired
	private CareerEmpStatusDao careerEmpStatusDao;
	@Autowired
	private EmployeeTypeDao employeeTypeDao;
	@Autowired
	private SystemLetterReferenceDao systemLetterReferenceDao;

	@Override
	public CareerEmpStatus getEntiyByPK(String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CareerEmpStatus getEntiyByPK(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CareerEmpStatus getEntiyByPK(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void save(CareerEmpStatus entity) throws Exception {
		// check duplicate name
		long totalDuplicates = careerEmpStatusDao.getTotalByName(entity.getName());
		if (totalDuplicates > 0) {
			throw new BussinessException("career_emp_status.error_duplicate_name");
		}
		// check duplicate code
		totalDuplicates = careerEmpStatusDao.getTotalByCode(entity.getCode());
		if (totalDuplicates > 0) {
			throw new BussinessException("career_emp_status.error_duplicate_code");
		}

		EmployeeType employeeType = employeeTypeDao.getEntiyByPK(entity.getEmpType().getId());
		SystemLetterReference systemLetterReference = systemLetterReferenceDao.getEntiyByPK(entity.getSystemLetterReference().getId());

		entity.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
		entity.setEmpType(employeeType);
		entity.setSystemLetterReference(systemLetterReference);
		entity.setCreatedBy(UserInfoUtil.getUserName());
		entity.setCreatedOn(new Date());
		careerEmpStatusDao.save(entity);

	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void update(CareerEmpStatus entity) throws Exception {
		// check duplicate name
		long totalDuplicates = careerEmpStatusDao.getTotalByNameAndNotId(entity.getName(), entity.getId());
		if (totalDuplicates > 0) {
			throw new BussinessException("career_emp_status.error_duplicate_name");
		}
		// check duplicate code
		totalDuplicates = careerEmpStatusDao.getTotalByCodeAndNotId(entity.getCode(), entity.getId());
		if (totalDuplicates > 0) {
			throw new BussinessException("career_emp_status.error_duplicate_name");
		}

		EmployeeType employeeType = employeeTypeDao.getEntiyByPK(entity.getEmpType().getId());
		SystemLetterReference systemLetterReference = systemLetterReferenceDao.getEntiyByPK(entity.getSystemLetterReference().getId());
		
		CareerEmpStatus careerEmpStatus =  careerEmpStatusDao.getEntiyByPK(entity.getId());
		careerEmpStatus.setIsAutoMove(entity.getIsAutoMove());
		careerEmpStatus.setCode(entity.getCode());
		careerEmpStatus.setEmpType(employeeType);
		careerEmpStatus.setLimitTime(entity.getLimitTime());
		careerEmpStatus.setName(entity.getName());
		careerEmpStatus.setSystemLetterReference(systemLetterReference);
		careerEmpStatus.setUpdatedBy(UserInfoUtil.getUserName());
		careerEmpStatus.setUpdatedOn(new Date());
		careerEmpStatusDao.update(careerEmpStatus);
	}

	@Override
	public void saveOrUpdate(CareerEmpStatus enntity) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public CareerEmpStatus saveData(CareerEmpStatus entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CareerEmpStatus updateData(CareerEmpStatus entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CareerEmpStatus saveOrUpdateData(CareerEmpStatus entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CareerEmpStatus getEntityByPkIsActive(String id, Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CareerEmpStatus getEntityByPkIsActive(String id, Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CareerEmpStatus getEntityByPkIsActive(String id, Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CareerEmpStatus getEntityByPkIsActive(Integer id, Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CareerEmpStatus getEntityByPkIsActive(Integer id, Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CareerEmpStatus getEntityByPkIsActive(Integer id, Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CareerEmpStatus getEntityByPkIsActive(Long id, Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CareerEmpStatus getEntityByPkIsActive(Long id, Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CareerEmpStatus getEntityByPkIsActive(Long id, Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(CareerEmpStatus entity) throws Exception {
		careerEmpStatusDao.delete(entity);
	}

	@Override
	public void softDelete(CareerEmpStatus entity) throws Exception {
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
	public List<CareerEmpStatus> getAllData() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CareerEmpStatus> getAllData(Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CareerEmpStatus> getAllData(Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CareerEmpStatus> getAllData(Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CareerEmpStatus> getAllDataPageAble(int firstResult, int maxResults, Order order) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CareerEmpStatus> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order,
			Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CareerEmpStatus> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order,
			Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CareerEmpStatus> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Byte isActive)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<CareerEmpStatus> getByParam(CareerEmpStatusSearchParameter parameter, int firstResult, int maxResults,
			Order orderable) {
		return careerEmpStatusDao.getByParam(parameter, firstResult, maxResults, orderable);
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public Long getTotalByParam(CareerEmpStatusSearchParameter parameter) {
		return careerEmpStatusDao.getTotalByParam(parameter);
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public CareerEmpStatus getEntityByPkWithDetail(Long id) {
		return careerEmpStatusDao.getEntityByPkWithDetail(id);
	}

}
