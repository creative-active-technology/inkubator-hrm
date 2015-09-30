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
import com.inkubator.hrm.dao.ApplicantDao;
import com.inkubator.hrm.entity.Applicant;
import com.inkubator.hrm.service.ApplicantService;
import com.inkubator.hrm.web.search.ApplicantSearchParameter;

/**
 *
 * @author rizkykojek
 */
@Service(value = "applicantService")
@Lazy
public class ApplicantServiceImpl extends IServiceImpl implements ApplicantService {

	@Autowired
	private ApplicantDao applicantDao;
	
	@Override
	public Applicant getEntiyByPK(String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Applicant getEntiyByPK(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Applicant getEntiyByPK(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Applicant entity) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Applicant entity) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveOrUpdate(Applicant enntity) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public Applicant saveData(Applicant entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Applicant updateData(Applicant entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Applicant saveOrUpdateData(Applicant entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Applicant getEntityByPkIsActive(String id, Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Applicant getEntityByPkIsActive(String id, Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Applicant getEntityByPkIsActive(String id, Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Applicant getEntityByPkIsActive(Integer id, Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Applicant getEntityByPkIsActive(Integer id, Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Applicant getEntityByPkIsActive(Integer id, Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Applicant getEntityByPkIsActive(Long id, Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Applicant getEntityByPkIsActive(Long id, Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Applicant getEntityByPkIsActive(Long id, Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Applicant entity) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void softDelete(Applicant entity) throws Exception {
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
	public List<Applicant> getAllData() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Applicant> getAllData(Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Applicant> getAllData(Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Applicant> getAllData(Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Applicant> getAllDataPageAble(int firstResult, int maxResults, Order order) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Applicant> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Boolean isActive)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Applicant> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Integer isActive)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Applicant> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Byte isActive)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<Applicant> getByParam(ApplicantSearchParameter parameter, int first, int pageSize, Order orderable) throws Exception {
		
		return applicantDao.getByParam(parameter, first, pageSize, orderable);
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public Long getTotalByParam(ApplicantSearchParameter parameter) throws Exception {

		return applicantDao.getTotalByParam(parameter);
	}

}
