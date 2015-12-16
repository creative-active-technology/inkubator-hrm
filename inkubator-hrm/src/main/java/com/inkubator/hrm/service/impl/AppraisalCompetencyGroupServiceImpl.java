package com.inkubator.hrm.service.impl;

import java.util.List;

import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.hrm.dao.AppraisalCompetencyGroupDao;
import com.inkubator.hrm.entity.AppraisalCompetencyGroup;
import com.inkubator.hrm.service.AppraisalCompetencyGroupService;
import com.inkubator.hrm.web.search.CompetencyGroupSearchParameter;

/**
 *
 * @author rizkykojek
 */
@Service(value = "appraisalCompetencyGroupService")
@Lazy
public class AppraisalCompetencyGroupServiceImpl extends IServiceImpl implements AppraisalCompetencyGroupService {

	@Autowired
	private AppraisalCompetencyGroupDao appraisalCompetencyGroupDao;
	
	@Override
	public AppraisalCompetencyGroup getEntiyByPK(String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalCompetencyGroup getEntiyByPK(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalCompetencyGroup getEntiyByPK(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(AppraisalCompetencyGroup entity) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(AppraisalCompetencyGroup entity) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveOrUpdate(AppraisalCompetencyGroup enntity) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public AppraisalCompetencyGroup saveData(AppraisalCompetencyGroup entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalCompetencyGroup updateData(AppraisalCompetencyGroup entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalCompetencyGroup saveOrUpdateData(AppraisalCompetencyGroup entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalCompetencyGroup getEntityByPkIsActive(String id, Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalCompetencyGroup getEntityByPkIsActive(String id, Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalCompetencyGroup getEntityByPkIsActive(String id, Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalCompetencyGroup getEntityByPkIsActive(Integer id, Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalCompetencyGroup getEntityByPkIsActive(Integer id, Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalCompetencyGroup getEntityByPkIsActive(Integer id, Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalCompetencyGroup getEntityByPkIsActive(Long id, Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalCompetencyGroup getEntityByPkIsActive(Long id, Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalCompetencyGroup getEntityByPkIsActive(Long id, Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(AppraisalCompetencyGroup entity) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void softDelete(AppraisalCompetencyGroup entity) throws Exception {
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
	public List<AppraisalCompetencyGroup> getAllData() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AppraisalCompetencyGroup> getAllData(Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AppraisalCompetencyGroup> getAllData(Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AppraisalCompetencyGroup> getAllData(Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AppraisalCompetencyGroup> getAllDataPageAble(int firstResult, int maxResults, Order order)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AppraisalCompetencyGroup> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order,
			Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AppraisalCompetencyGroup> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order,
			Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AppraisalCompetencyGroup> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order,
			Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<AppraisalCompetencyGroup> getAllDataByParam(CompetencyGroupSearchParameter searchParameter, int firstResult, int maxResult, Order order) throws Exception {
		// TODO Auto-generated method stub
		return appraisalCompetencyGroupDao.getAllDataByParam(searchParameter, firstResult, maxResult, order);
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public Long getTotalByParam(CompetencyGroupSearchParameter searchParameter) throws Exception {
		// TODO Auto-generated method stub
		return appraisalCompetencyGroupDao.getTotalByParam(searchParameter);
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public AppraisalCompetencyGroup getEntityByIdWithDetail(Long id) {
		return appraisalCompetencyGroupDao.getEntityByIdWithDetail(id);
	}

}
