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
import com.inkubator.hrm.dao.RecruitApplicantDao;
import com.inkubator.hrm.entity.RecruitApplicant;
import com.inkubator.hrm.service.RecruitApplicantService;
import com.inkubator.hrm.web.search.RecruitApplicantSearchParameter;

/**
 *
 * @author rizkykojek
 */
@Service(value = "recruitApplicantService")
@Lazy
public class RecruitApplicantServiceImpl extends IServiceImpl implements RecruitApplicantService {

	@Autowired
	private RecruitApplicantDao recruitApplicantDao;
	
	@Override
	public RecruitApplicant getEntiyByPK(String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitApplicant getEntiyByPK(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitApplicant getEntiyByPK(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(RecruitApplicant entity) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(RecruitApplicant entity) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveOrUpdate(RecruitApplicant enntity) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public RecruitApplicant saveData(RecruitApplicant entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitApplicant updateData(RecruitApplicant entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitApplicant saveOrUpdateData(RecruitApplicant entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitApplicant getEntityByPkIsActive(String id, Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitApplicant getEntityByPkIsActive(String id, Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitApplicant getEntityByPkIsActive(String id, Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitApplicant getEntityByPkIsActive(Integer id, Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitApplicant getEntityByPkIsActive(Integer id, Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitApplicant getEntityByPkIsActive(Integer id, Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitApplicant getEntityByPkIsActive(Long id, Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitApplicant getEntityByPkIsActive(Long id, Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitApplicant getEntityByPkIsActive(Long id, Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(RecruitApplicant entity) throws Exception {
		recruitApplicantDao.delete(entity);

	}

	@Override
	public void softDelete(RecruitApplicant entity) throws Exception {
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
	public List<RecruitApplicant> getAllData() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecruitApplicant> getAllData(Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecruitApplicant> getAllData(Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecruitApplicant> getAllData(Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecruitApplicant> getAllDataPageAble(int firstResult, int maxResults, Order order) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecruitApplicant> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Boolean isActive)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecruitApplicant> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Integer isActive)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecruitApplicant> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Byte isActive)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<RecruitApplicant> getByParam(RecruitApplicantSearchParameter parameter, int first, int pageSize, Order orderable) throws Exception {
		
		return recruitApplicantDao.getByParam(parameter, first, pageSize, orderable);
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public Long getTotalByParam(RecruitApplicantSearchParameter parameter) throws Exception {

		return recruitApplicantDao.getTotalByParam(parameter);
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public RecruitApplicant getEntityByPkWithDetail(Long id) throws Exception {
		
		return recruitApplicantDao.getEntityByPkWithDetail(id);
	}

}
