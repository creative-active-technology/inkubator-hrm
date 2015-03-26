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
import com.inkubator.hrm.dao.CostCenterDao;
import com.inkubator.hrm.dao.RmbsTypeDao;
import com.inkubator.hrm.entity.RmbsType;
import com.inkubator.hrm.service.RmbsTypeService;
import com.inkubator.hrm.web.search.RmbsTypeSearchParameter;
import com.inkubator.securitycore.util.UserInfoUtil;

@Service(value = "rmbsTypeService")
@Lazy
public class RmbsTypeServiceImpl extends IServiceImpl implements RmbsTypeService {

	@Autowired
	private RmbsTypeDao rmbsTypeDao;
	@Autowired
	private CostCenterDao costCenterDao;
	
	@Override
	public RmbsType getEntiyByPK(String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RmbsType getEntiyByPK(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public RmbsType getEntiyByPK(Long id) throws Exception {
		return rmbsTypeDao.getEntiyByPK(id);
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void save(RmbsType entity) throws Exception {
		// check duplicate name
		long totalDuplicates = rmbsTypeDao.getTotalByName(entity.getName());
		if (totalDuplicates > 0) {
			throw new BussinessException("rmbs_type.error_duplicate_name");
		}
		// check duplicate code
		totalDuplicates = rmbsTypeDao.getTotalByCode(entity.getCode());
		if (totalDuplicates > 0) {
			throw new BussinessException("rmbs_type.error_duplicate_code");
		}		

		entity.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
		entity.setCostCenter(costCenterDao.getEntiyByPK(entity.getCostCenter().getId()));
		entity.setIsActive(Boolean.TRUE);
		entity.setCreatedBy(UserInfoUtil.getUserName());
		entity.setCreatedOn(new Date());
		rmbsTypeDao.save(entity);
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void update(RmbsType entity) throws Exception {
		// check duplicate name
		long totalDuplicates = rmbsTypeDao.getTotalByNameAndNotId(entity.getName(), entity.getId());
		if (totalDuplicates > 0) {
			throw new BussinessException("rmbs_type.error_duplicate_name");
		}
		// check duplicate code
		totalDuplicates = rmbsTypeDao.getTotalByCodeAndNotId(entity.getCode(), entity.getId());
		if (totalDuplicates > 0) {
			throw new BussinessException("rmbs_type.error_duplicate_code");
		}		
		
		RmbsType rmbsType = rmbsTypeDao.getEntiyByPK(entity.getId());
		rmbsType.setCode(entity.getCode());
		rmbsType.setCostCenter(costCenterDao.getEntiyByPK(entity.getCostCenter().getId()));
		rmbsType.setDaysOfAvailable(entity.getDaysOfAvailable());
		rmbsType.setDescription(entity.getDescription());
		rmbsType.setIsActive(entity.getIsActive());
		rmbsType.setName(entity.getName());
		rmbsType.setRoundDigit(entity.getRoundDigit());
		rmbsType.setUpdatedBy(UserInfoUtil.getUserName());
		rmbsType.setUpdatedOn(new Date());
		rmbsTypeDao.update(rmbsType);
	}
	

	@Override
	public void saveOrUpdate(RmbsType enntity) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public RmbsType saveData(RmbsType entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RmbsType updateData(RmbsType entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RmbsType saveOrUpdateData(RmbsType entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RmbsType getEntityByPkIsActive(String id, Integer isActive)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RmbsType getEntityByPkIsActive(String id, Byte isActive)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RmbsType getEntityByPkIsActive(String id, Boolean isActive)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RmbsType getEntityByPkIsActive(Integer id, Integer isActive)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RmbsType getEntityByPkIsActive(Integer id, Byte isActive)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RmbsType getEntityByPkIsActive(Integer id, Boolean isActive)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RmbsType getEntityByPkIsActive(Long id, Integer isActive)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RmbsType getEntityByPkIsActive(Long id, Byte isActive)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RmbsType getEntityByPkIsActive(Long id, Boolean isActive)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(RmbsType entity) throws Exception {
		rmbsTypeDao.delete(entity);

	}

	@Override
	public void softDelete(RmbsType entity) throws Exception {
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
	public List<RmbsType> getAllData() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RmbsType> getAllData(Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RmbsType> getAllData(Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RmbsType> getAllData(Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RmbsType> getAllDataPageAble(int firstResult, int maxResults,
			Order order) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RmbsType> getAllDataPageAbleIsActive(int firstResult,
			int maxResults, Order order, Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RmbsType> getAllDataPageAbleIsActive(int firstResult,
			int maxResults, Order order, Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RmbsType> getAllDataPageAbleIsActive(int firstResult,
			int maxResults, Order order, Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<RmbsType> getByParam(RmbsTypeSearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception {
		return rmbsTypeDao.getByParam(parameter, firstResult, maxResults, orderable);
		
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public Long getTotalByParam(RmbsTypeSearchParameter parameter) throws Exception {
		return rmbsTypeDao.getTotalByParam(parameter);
		
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<RmbsType> getAllDataByStatusActive() throws Exception {
		return rmbsTypeDao.getAllDataByStatusActive();
		
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public RmbsType getEntityByPkWithDetail(Long id) throws Exception {
		return rmbsTypeDao.getEntityByPkWithDetail(id);
		
	}

}
