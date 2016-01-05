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
import com.inkubator.hrm.dao.AppraisalProgramDao;
import com.inkubator.hrm.entity.AppraisalProgram;
import com.inkubator.hrm.service.AppraisalProgramService;
import com.inkubator.hrm.web.model.AppraisalProgramModel;
import com.inkubator.hrm.web.search.AppraisalProgramSearchParameter;

/**
 *
 * @author rizkykojek
 */

@Service(value = "appraisalProgramService")
@Lazy
public class AppraisalProgramServiceImpl extends IServiceImpl implements AppraisalProgramService {

	@Autowired
	private AppraisalProgramDao appraisalProgramDao;
	
	@Override
	public AppraisalProgram getEntiyByPK(String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalProgram getEntiyByPK(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public AppraisalProgram getEntiyByPK(Long id) throws Exception {
		
		return appraisalProgramDao.getEntiyByPK(id);
	}

	@Override
	public void save(AppraisalProgram entity) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(AppraisalProgram entity) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveOrUpdate(AppraisalProgram enntity) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public AppraisalProgram saveData(AppraisalProgram entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalProgram updateData(AppraisalProgram entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalProgram saveOrUpdateData(AppraisalProgram entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalProgram getEntityByPkIsActive(String id, Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalProgram getEntityByPkIsActive(String id, Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalProgram getEntityByPkIsActive(String id, Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalProgram getEntityByPkIsActive(Integer id, Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalProgram getEntityByPkIsActive(Integer id, Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalProgram getEntityByPkIsActive(Integer id, Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalProgram getEntityByPkIsActive(Long id, Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalProgram getEntityByPkIsActive(Long id, Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalProgram getEntityByPkIsActive(Long id, Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(AppraisalProgram entity) throws Exception {
		
		appraisalProgramDao.delete(entity);
	}

	@Override
	public void softDelete(AppraisalProgram entity) throws Exception {
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
	public List<AppraisalProgram> getAllData() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AppraisalProgram> getAllData(Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AppraisalProgram> getAllData(Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AppraisalProgram> getAllData(Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AppraisalProgram> getAllDataPageAble(int firstResult, int maxResults, Order order) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AppraisalProgram> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order,
			Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AppraisalProgram> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order,
			Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AppraisalProgram> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order,
			Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<AppraisalProgram> getAllDataByParam(AppraisalProgramSearchParameter searchParameter, int firstResult, int maxResult, Order order) throws Exception {
		
		return appraisalProgramDao.getAllDataByParam(searchParameter, firstResult, maxResult, order);
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public Long getTotalByParam(AppraisalProgramSearchParameter searchParameter) throws Exception {

		return appraisalProgramDao.getTotalByParam(searchParameter);
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public AppraisalProgram getEntityByIdWithDetail(Long id) throws Exception {
		
		return appraisalProgramDao.getEntityByIdWithDetail(id);
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void update(AppraisalProgramModel model) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void save(AppraisalProgramModel model) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
