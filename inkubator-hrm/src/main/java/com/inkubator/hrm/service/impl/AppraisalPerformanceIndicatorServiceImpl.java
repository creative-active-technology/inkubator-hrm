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
import com.inkubator.hrm.dao.AppraisalPerformanceIndicatorDao;
import com.inkubator.hrm.entity.AppraisalPerformanceIndicator;
import com.inkubator.hrm.service.AppraisalPerformanceIndicatorService;

/**
 *
 * @author rizkykojek
 */
@Service(value = "appraisalPerformanceIndicatorService")
@Lazy
public class AppraisalPerformanceIndicatorServiceImpl extends IServiceImpl implements AppraisalPerformanceIndicatorService {
	
	@Autowired
    private AppraisalPerformanceIndicatorDao appraisalPerformanceIndicatorDao;

	@Override
	public AppraisalPerformanceIndicator getEntiyByPK(String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalPerformanceIndicator getEntiyByPK(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
	public AppraisalPerformanceIndicator getEntiyByPK(Long id) throws Exception {
		return appraisalPerformanceIndicatorDao.getEntiyByPK(id);
	}

	@Override
	public void save(AppraisalPerformanceIndicator entity) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(AppraisalPerformanceIndicator entity) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveOrUpdate(AppraisalPerformanceIndicator enntity) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public AppraisalPerformanceIndicator saveData(AppraisalPerformanceIndicator entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalPerformanceIndicator updateData(AppraisalPerformanceIndicator entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalPerformanceIndicator saveOrUpdateData(AppraisalPerformanceIndicator entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalPerformanceIndicator getEntityByPkIsActive(String id, Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalPerformanceIndicator getEntityByPkIsActive(String id, Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalPerformanceIndicator getEntityByPkIsActive(String id, Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalPerformanceIndicator getEntityByPkIsActive(Integer id, Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalPerformanceIndicator getEntityByPkIsActive(Integer id, Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalPerformanceIndicator getEntityByPkIsActive(Integer id, Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalPerformanceIndicator getEntityByPkIsActive(Long id, Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalPerformanceIndicator getEntityByPkIsActive(Long id, Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalPerformanceIndicator getEntityByPkIsActive(Long id, Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(AppraisalPerformanceIndicator entity) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void softDelete(AppraisalPerformanceIndicator entity) throws Exception {
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
	public List<AppraisalPerformanceIndicator> getAllData() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AppraisalPerformanceIndicator> getAllData(Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AppraisalPerformanceIndicator> getAllData(Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AppraisalPerformanceIndicator> getAllData(Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AppraisalPerformanceIndicator> getAllDataPageAble(int firstResult, int maxResults, Order order)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AppraisalPerformanceIndicator> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order,
			Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AppraisalPerformanceIndicator> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order,
			Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AppraisalPerformanceIndicator> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order,
			Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<AppraisalPerformanceIndicator> getListByIdAppraisalPerformanceGroup(Long idAppraisalPerformanceGroup) throws Exception {
		return appraisalPerformanceIndicatorDao.getListByIdAppraisalPerformanceGroup(idAppraisalPerformanceGroup);
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
	public AppraisalPerformanceIndicator getEntityWithDetailById(Long id) {
		return appraisalPerformanceIndicatorDao.getEntityWithDetailById(id);
	}

}
