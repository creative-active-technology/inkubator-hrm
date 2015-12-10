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
import com.inkubator.hrm.dao.AppraisalPerformanceGroupDao;
import com.inkubator.hrm.entity.AppraisalPerformanceGroup;
import com.inkubator.hrm.service.AppraisalPerformanceGroupService;
import com.inkubator.hrm.web.search.AppraisalPerformanceGroupSearchParameter;
import com.inkubator.securitycore.util.UserInfoUtil;

@Service(value = "appraisalPerformanceGroupService")
@Lazy
public class AppraisalPerformanceGroupServiceImpl extends IServiceImpl implements AppraisalPerformanceGroupService{
	
	@Autowired
	private AppraisalPerformanceGroupDao appraisalPerformanceGroupDao;

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(AppraisalPerformanceGroup entity) throws Exception {
		this.appraisalPerformanceGroupDao.delete(entity);
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<AppraisalPerformanceGroup> getAllData() throws Exception {
		return this.appraisalPerformanceGroupDao.getAllData();
	}

	@Override
	public List<AppraisalPerformanceGroup> getAllData(Boolean arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AppraisalPerformanceGroup> getAllData(Integer arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AppraisalPerformanceGroup> getAllData(Byte arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AppraisalPerformanceGroup> getAllDataPageAble(int arg0, int arg1, Order arg2) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AppraisalPerformanceGroup> getAllDataPageAbleIsActive(int arg0, int arg1, Order arg2, Boolean arg3)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AppraisalPerformanceGroup> getAllDataPageAbleIsActive(int arg0, int arg1, Order arg2, Integer arg3)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AppraisalPerformanceGroup> getAllDataPageAbleIsActive(int arg0, int arg1, Order arg2, Byte arg3)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalPerformanceGroup getEntityByPkIsActive(String arg0, Integer arg1) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalPerformanceGroup getEntityByPkIsActive(String arg0, Byte arg1) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalPerformanceGroup getEntityByPkIsActive(String arg0, Boolean arg1) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalPerformanceGroup getEntityByPkIsActive(Integer arg0, Integer arg1) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalPerformanceGroup getEntityByPkIsActive(Integer arg0, Byte arg1) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalPerformanceGroup getEntityByPkIsActive(Integer arg0, Boolean arg1) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalPerformanceGroup getEntityByPkIsActive(Long arg0, Integer arg1) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalPerformanceGroup getEntityByPkIsActive(Long arg0, Byte arg1) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalPerformanceGroup getEntityByPkIsActive(Long arg0, Boolean arg1) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalPerformanceGroup getEntiyByPK(String arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalPerformanceGroup getEntiyByPK(Integer arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public AppraisalPerformanceGroup getEntiyByPK(Long entity) throws Exception {
		return this.appraisalPerformanceGroupDao.getEntiyByPK(entity);
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
	public void save(AppraisalPerformanceGroup entity) throws Exception {
		long totalDuplicatesCode = appraisalPerformanceGroupDao.getTotalByCode(entity.getCode());
		if(totalDuplicatesCode > 0){
			throw new BussinessException("marital.error_duplicate_marital_code");
		}
		long totalDuplicatesName = appraisalPerformanceGroupDao.getTotalByName(entity.getName());
		if(totalDuplicatesName > 0){
			throw new BussinessException("appraisalDetail.error_duplicate_appraisalDetail_name");
		}
		
		entity.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
		entity.setCreatedBy(UserInfoUtil.getUserName());
		entity.setCreatedOn(new Date());
		System.out.println("Appraiser dari service : ========== " + entity.getAppraiser());
		this.appraisalPerformanceGroupDao.save(entity);
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public AppraisalPerformanceGroup saveData(AppraisalPerformanceGroup arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveOrUpdate(AppraisalPerformanceGroup arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public AppraisalPerformanceGroup saveOrUpdateData(AppraisalPerformanceGroup arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void softDelete(AppraisalPerformanceGroup arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void update(AppraisalPerformanceGroup entity) throws Exception {
		long totalDuplicates = appraisalPerformanceGroupDao.getTotalByCodeAndNotId(entity.getCode(), entity.getId());
		if(totalDuplicates > 0){
			throw new BussinessException("marital.error_duplicate_marital_code");
		}
		AppraisalPerformanceGroup update = appraisalPerformanceGroupDao.getEntiyByPK(entity.getId());
		update.setCode(entity.getCode());
		update.setName(entity.getName());
		update.setAppraiser(entity.getAppraiser());
		update.setOrientation(entity.getOrientation());
		update.setDescription(entity.getDescription());
		update.setUpdatedBy(UserInfoUtil.getUserName());
		update.setUpdatedOn(new Date());
		appraisalPerformanceGroupDao.update(update);
	}

	@Override
	public AppraisalPerformanceGroup updateData(AppraisalPerformanceGroup arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<AppraisalPerformanceGroup> getByParam(AppraisalPerformanceGroupSearchParameter searchParameter,
			int firstResult, int maxResults, Order order) throws Exception{
		return this.appraisalPerformanceGroupDao.getByParam(searchParameter, firstResult, maxResults, order);
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public Long getTotalByParam(AppraisalPerformanceGroupSearchParameter searchParameter) throws Exception{
		return this.appraisalPerformanceGroupDao.getTotalByParam(searchParameter);
	}

}
