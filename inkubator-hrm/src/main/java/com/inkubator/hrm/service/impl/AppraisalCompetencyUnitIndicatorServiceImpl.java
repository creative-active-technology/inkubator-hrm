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
import com.inkubator.hrm.dao.AppraisalCompetencyUnitDao;
import com.inkubator.hrm.dao.AppraisalCompetencyUnitIndicatorDao;
import com.inkubator.hrm.entity.AppraisalCompetencyUnitIndicator;
import com.inkubator.hrm.service.AppraisalCompetencyUnitIndicatorService;
import com.inkubator.securitycore.util.UserInfoUtil;

/**
 *
 * @author rizkykojek
 */
@Service(value = "appraisalCompetencyUnitIndicatorService")
@Lazy
public class AppraisalCompetencyUnitIndicatorServiceImpl extends IServiceImpl implements AppraisalCompetencyUnitIndicatorService {

	@Autowired
	private AppraisalCompetencyUnitIndicatorDao appraisalCompetencyUnitIndicatorDao;
	@Autowired
	private AppraisalCompetencyUnitDao appraisalCompetencyUnitDao;
	
	@Override
	public AppraisalCompetencyUnitIndicator getEntiyByPK(String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalCompetencyUnitIndicator getEntiyByPK(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalCompetencyUnitIndicator getEntiyByPK(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void save(AppraisalCompetencyUnitIndicator entity) throws Exception {
		// check duplicate indicator
		long totalDuplicates = appraisalCompetencyUnitIndicatorDao.getTotalByIndicatorAndCompetencyUnitId(entity.getIndicator(), entity.getAppraisalCompetencyUnit().getId());
		if (totalDuplicates > 0) {
			throw new BussinessException("competency_unit.error_indicator_duplicate");
		}
		
		// check duplicate level index
		totalDuplicates = appraisalCompetencyUnitIndicatorDao.getTotalByLevelIndexAndCompetencyUnitId(entity.getLevelIndex(), entity.getAppraisalCompetencyUnit().getId());
		if (totalDuplicates > 0) {
			throw new BussinessException("competency_unit.error_level_index_duplicate");
		}
		
		entity.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(12)));
		entity.setAppraisalCompetencyUnit(appraisalCompetencyUnitDao.getEntiyByPK(entity.getAppraisalCompetencyUnit().getId()));
		entity.setCreatedBy(UserInfoUtil.getUserName());
		entity.setCreatedOn(new Date());
		appraisalCompetencyUnitIndicatorDao.save(entity);
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void update(AppraisalCompetencyUnitIndicator u) throws Exception {
		// check duplicate indicator
		long totalDuplicates = appraisalCompetencyUnitIndicatorDao.getTotalByIndicatorAndCompetencyUnitIdAndNotId(u.getIndicator(), u.getAppraisalCompetencyUnit().getId(), u.getId());
		if (totalDuplicates > 0) {
			throw new BussinessException("competency_unit.error_indicator_duplicate");
		}
		
		// check duplicate level index
		totalDuplicates = appraisalCompetencyUnitIndicatorDao.getTotalByLevelIndexAndCompetencyUnitIdAndNotId(u.getLevelIndex(), u.getAppraisalCompetencyUnit().getId(), u.getId());
		if (totalDuplicates > 0) {
			throw new BussinessException("competency_unit.error_level_index_duplicate");
		}
		
		AppraisalCompetencyUnitIndicator entity = appraisalCompetencyUnitIndicatorDao.getEntiyByPK(u.getId());
		entity.setIndicator(u.getIndicator());
		entity.setLevelIndex(u.getLevelIndex());
		entity.setAppraisalCompetencyUnit(appraisalCompetencyUnitDao.getEntiyByPK(u.getAppraisalCompetencyUnit().getId()));
		entity.setUpdatedBy(UserInfoUtil.getUserName());
		entity.setUpdatedOn(new Date());
		appraisalCompetencyUnitIndicatorDao.update(entity);
	}

	@Override
	public void saveOrUpdate(AppraisalCompetencyUnitIndicator enntity) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public AppraisalCompetencyUnitIndicator saveData(AppraisalCompetencyUnitIndicator entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalCompetencyUnitIndicator updateData(AppraisalCompetencyUnitIndicator entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalCompetencyUnitIndicator saveOrUpdateData(AppraisalCompetencyUnitIndicator entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalCompetencyUnitIndicator getEntityByPkIsActive(String id, Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalCompetencyUnitIndicator getEntityByPkIsActive(String id, Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalCompetencyUnitIndicator getEntityByPkIsActive(String id, Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalCompetencyUnitIndicator getEntityByPkIsActive(Integer id, Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalCompetencyUnitIndicator getEntityByPkIsActive(Integer id, Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalCompetencyUnitIndicator getEntityByPkIsActive(Integer id, Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalCompetencyUnitIndicator getEntityByPkIsActive(Long id, Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalCompetencyUnitIndicator getEntityByPkIsActive(Long id, Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalCompetencyUnitIndicator getEntityByPkIsActive(Long id, Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(AppraisalCompetencyUnitIndicator entity) throws Exception {
		this.appraisalCompetencyUnitIndicatorDao.delete(entity);

	}

	@Override
	public void softDelete(AppraisalCompetencyUnitIndicator entity) throws Exception {
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
	public List<AppraisalCompetencyUnitIndicator> getAllData() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AppraisalCompetencyUnitIndicator> getAllData(Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AppraisalCompetencyUnitIndicator> getAllData(Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AppraisalCompetencyUnitIndicator> getAllData(Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AppraisalCompetencyUnitIndicator> getAllDataPageAble(int firstResult, int maxResults, Order order)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AppraisalCompetencyUnitIndicator> getAllDataPageAbleIsActive(int firstResult, int maxResults,
			Order order, Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AppraisalCompetencyUnitIndicator> getAllDataPageAbleIsActive(int firstResult, int maxResults,
			Order order, Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AppraisalCompetencyUnitIndicator> getAllDataPageAbleIsActive(int firstResult, int maxResults,
			Order order, Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<AppraisalCompetencyUnitIndicator> getAllDataByCompetencyUnitId(Long competencyUnitId) throws Exception  {
		
		return appraisalCompetencyUnitIndicatorDao.getAllDataByCompetencyUnitId(competencyUnitId);
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public AppraisalCompetencyUnitIndicator getEntityByIdWithDetail(Long id) {
		
		return appraisalCompetencyUnitIndicatorDao.getEntityByIdWithDetail(id);
	}

}
