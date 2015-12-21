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
import com.inkubator.hrm.dao.AppraisalCompetencyGroupDao;
import com.inkubator.hrm.dao.AppraisalCompetencyUnitDao;
import com.inkubator.hrm.entity.AppraisalCompetencyUnit;
import com.inkubator.hrm.service.AppraisalCompetencyUnitService;
import com.inkubator.hrm.web.search.CompetencyUnitSearchParameter;
import com.inkubator.securitycore.util.UserInfoUtil;

/**
 *
 * @author rizkykojek
 */
@Service(value = "appraisalCompetencyUnitService")
@Lazy
public class AppraisalCompetencyUnitServiceImpl extends IServiceImpl implements AppraisalCompetencyUnitService {

	@Autowired
	private AppraisalCompetencyUnitDao appraisalCompetencyUnitDao;
	@Autowired
	private AppraisalCompetencyGroupDao appraisalCompetencyGroupDao;
	
	@Override
	public AppraisalCompetencyUnit getEntiyByPK(String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalCompetencyUnit getEntiyByPK(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public AppraisalCompetencyUnit getEntiyByPK(Long id) throws Exception {
		// TODO Auto-generated method stub
		return appraisalCompetencyUnitDao.getEntiyByPK(id);
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void save(AppraisalCompetencyUnit entity) throws Exception {
		// check duplicate name
		long totalDuplicates = appraisalCompetencyUnitDao.getTotalByName(entity.getName());
		if (totalDuplicates > 0) {
			throw new BussinessException("competency_unit.error_name_duplicate");
		}
		
		entity.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(12)));
		entity.setCompetencyGroup(appraisalCompetencyGroupDao.getEntiyByPK(entity.getCompetencyGroup().getId()));
		entity.setCreatedBy(UserInfoUtil.getUserName());
		entity.setCreatedOn(new Date());
		appraisalCompetencyUnitDao.save(entity);

	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void update(AppraisalCompetencyUnit u) throws Exception {
		// check duplicate name
		long totalDuplicates = appraisalCompetencyUnitDao.getTotalByNameAndNotId(u.getName(), u.getId());
		if (totalDuplicates > 0) {
			throw new BussinessException("competency_unit.error_name_duplicate");
		}
		
		AppraisalCompetencyUnit entity = appraisalCompetencyUnitDao.getEntiyByPK(u.getId());
		entity.setName(u.getName());
		entity.setDescription(u.getDescription());
		entity.setCompetencyGroup(appraisalCompetencyGroupDao.getEntiyByPK(u.getCompetencyGroup().getId()));
		entity.setUpdatedBy(UserInfoUtil.getUserName());
		entity.setUpdatedOn(new Date());
		appraisalCompetencyUnitDao.update(entity);
	}

	@Override
	public void saveOrUpdate(AppraisalCompetencyUnit enntity) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public AppraisalCompetencyUnit saveData(AppraisalCompetencyUnit entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalCompetencyUnit updateData(AppraisalCompetencyUnit entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalCompetencyUnit saveOrUpdateData(AppraisalCompetencyUnit entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalCompetencyUnit getEntityByPkIsActive(String id, Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalCompetencyUnit getEntityByPkIsActive(String id, Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalCompetencyUnit getEntityByPkIsActive(String id, Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalCompetencyUnit getEntityByPkIsActive(Integer id, Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalCompetencyUnit getEntityByPkIsActive(Integer id, Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalCompetencyUnit getEntityByPkIsActive(Integer id, Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalCompetencyUnit getEntityByPkIsActive(Long id, Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalCompetencyUnit getEntityByPkIsActive(Long id, Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalCompetencyUnit getEntityByPkIsActive(Long id, Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(AppraisalCompetencyUnit entity) throws Exception {
		this.appraisalCompetencyUnitDao.delete(entity);

	}

	@Override
	public void softDelete(AppraisalCompetencyUnit entity) throws Exception {
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
	public List<AppraisalCompetencyUnit> getAllData() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AppraisalCompetencyUnit> getAllData(Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AppraisalCompetencyUnit> getAllData(Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AppraisalCompetencyUnit> getAllData(Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AppraisalCompetencyUnit> getAllDataPageAble(int firstResult, int maxResults, Order order)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AppraisalCompetencyUnit> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order,
			Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AppraisalCompetencyUnit> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order,
			Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AppraisalCompetencyUnit> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order,
			Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<AppraisalCompetencyUnit> getAllDataByParam(CompetencyUnitSearchParameter searchParameter, int firstResult, int maxResult, Order order) throws Exception {
		// TODO Auto-generated method stub
		return appraisalCompetencyUnitDao.getAllDataByParam(searchParameter, firstResult, maxResult, order);
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public Long getTotalByParam(CompetencyUnitSearchParameter searchParameter) throws Exception {
		// TODO Auto-generated method stub
		return appraisalCompetencyUnitDao.getTotalByParam(searchParameter);
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public AppraisalCompetencyUnit getEntityByIdWithDetail(Long id) throws Exception {
		// TODO Auto-generated method stub
		return appraisalCompetencyUnitDao.getEntityByIdWithDetail(id);
	}

}
