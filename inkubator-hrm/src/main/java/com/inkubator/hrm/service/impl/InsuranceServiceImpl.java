/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import com.inkubator.hrm.dao.CityDao;
import com.inkubator.hrm.dao.InsuranceDao;
import com.inkubator.hrm.entity.Bank;
import com.inkubator.hrm.entity.City;
import com.inkubator.hrm.entity.Insurance;
import com.inkubator.hrm.service.InsuranceService;
import com.inkubator.hrm.web.search.InsuranceSearchParameter;
import com.inkubator.securitycore.util.UserInfoUtil;

/**
 *
 * @author Deni
 */
@Service(value = "insuranceService")
@Lazy
public class InsuranceServiceImpl extends IServiceImpl implements InsuranceService{

	@Autowired
	private InsuranceDao insuranceDao;
	@Autowired
	private CityDao cityDao;
	
	@Override
	public Insurance getEntiyByPK(String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Insurance getEntiyByPK(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public Insurance getEntiyByPK(Long id) throws Exception {
		return insuranceDao.getEntiyByPK(id);
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void save(Insurance entity) throws Exception {
		long totalDuplicates = insuranceDao.getTotalByCode(entity.getCode());
        if (totalDuplicates > 0) {
            throw new BussinessException("bank.error_duplicate_bank_code");
        }
		City city = cityDao.getEntiyByPK(entity.getCity().getId());
		entity.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
		entity.setCreatedBy(UserInfoUtil.getUserName());
		entity.setCreatedOn(new Date());
		entity.setCity(city);
		insuranceDao.save(entity);
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void update(Insurance entity) throws Exception {
		long totalDuplicates = insuranceDao.getTotalByCodeAndNotId(entity.getCode(), entity.getId());
        if (totalDuplicates > 0) {
            throw new BussinessException("bank.error_duplicate_bank_code");
        }
        City city = cityDao.getEntiyByPK(entity.getCity().getId());
        Insurance insurance = insuranceDao.getEntiyByPK(entity.getId());
        insurance.setCode(entity.getCode());
        insurance.setName(entity.getName());
        insurance.setDescription(entity.getDescription());
        insurance.setAddress(entity.getAddress());
        insurance.setCity(city);
        insurance.setPostalCode(entity.getPostalCode());
        insurance.setPolisNo(entity.getPolisNo());
        insurance.setInsuranceProduct(entity.getInsuranceProduct());
        insurance.setUpdatedBy(UserInfoUtil.getUserName());
        insurance.setUpdatedOn(new Date());
        insuranceDao.update(insurance);
		
	}

	@Override
	public void saveOrUpdate(Insurance enntity) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Insurance saveData(Insurance entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Insurance updateData(Insurance entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Insurance saveOrUpdateData(Insurance entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Insurance getEntityByPkIsActive(String id, Integer isActive)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Insurance getEntityByPkIsActive(String id, Byte isActive)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Insurance getEntityByPkIsActive(String id, Boolean isActive)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Insurance getEntityByPkIsActive(Integer id, Integer isActive)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Insurance getEntityByPkIsActive(Integer id, Byte isActive)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Insurance getEntityByPkIsActive(Integer id, Boolean isActive)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Insurance getEntityByPkIsActive(Long id, Integer isActive)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Insurance getEntityByPkIsActive(Long id, Byte isActive)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Insurance getEntityByPkIsActive(Long id, Boolean isActive)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void delete(Insurance entity) throws Exception {
		this.insuranceDao.delete(entity);
	}

	@Override
	public void softDelete(Insurance entity) throws Exception {
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
	public List<Insurance> getAllData() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Insurance> getAllData(Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Insurance> getAllData(Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Insurance> getAllData(Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Insurance> getAllDataPageAble(int firstResult, int maxResults,
			Order order) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Insurance> getAllDataPageAbleIsActive(int firstResult,
			int maxResults, Order order, Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Insurance> getAllDataPageAbleIsActive(int firstResult,
			int maxResults, Order order, Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Insurance> getAllDataPageAbleIsActive(int firstResult,
			int maxResults, Order order, Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<Insurance> getByParam(InsuranceSearchParameter parameter,int firstResult, int maxResults, Order orderable) throws Exception {
		return insuranceDao.getByParam(parameter, firstResult, maxResults, orderable);
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalByParam(InsuranceSearchParameter parameter) throws Exception {
		return insuranceDao.getTotalByParam(parameter);
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public Insurance getEntityByPkWithDetail(Long id) throws Exception {
		return insuranceDao.getEntityByPkWithDetail(id);
	}
    
}
