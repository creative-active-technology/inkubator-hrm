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
import com.inkubator.hrm.dao.AppraisalSystemScoringDao;
import com.inkubator.hrm.dao.AppraisalSystemScoringIndexDao;
import com.inkubator.hrm.entity.AppraisalSystemScoring;
import com.inkubator.hrm.entity.AppraisalSystemScoringIndex;
import com.inkubator.hrm.service.AppraisalSystemScoringService;
import com.inkubator.hrm.web.search.AppraisalSystemScoringSearchParameter;
import com.inkubator.securitycore.util.UserInfoUtil;

@Service(value = "appraisalSystemScoringService")
@Lazy
public class AppraisalSystemScoringServiceImpl extends IServiceImpl implements AppraisalSystemScoringService {

	@Autowired
	private AppraisalSystemScoringDao appraisalSystemScoringDao;
	@Autowired
	private AppraisalSystemScoringIndexDao appraisalSystemScoringIndexDao;
	
	@Override
	public AppraisalSystemScoring getEntiyByPK(String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalSystemScoring getEntiyByPK(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public AppraisalSystemScoring getEntiyByPK(Long id) throws Exception {
		return appraisalSystemScoringDao.getEntiyByPK(id);
	}

	@Override
	public void save(AppraisalSystemScoring entity) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(AppraisalSystemScoring entity) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveOrUpdate(AppraisalSystemScoring enntity) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public AppraisalSystemScoring saveData(AppraisalSystemScoring entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalSystemScoring updateData(AppraisalSystemScoring entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalSystemScoring saveOrUpdateData(AppraisalSystemScoring entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalSystemScoring getEntityByPkIsActive(String id, Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalSystemScoring getEntityByPkIsActive(String id, Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalSystemScoring getEntityByPkIsActive(String id, Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalSystemScoring getEntityByPkIsActive(Integer id, Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalSystemScoring getEntityByPkIsActive(Integer id, Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalSystemScoring getEntityByPkIsActive(Integer id, Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalSystemScoring getEntityByPkIsActive(Long id, Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalSystemScoring getEntityByPkIsActive(Long id, Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalSystemScoring getEntityByPkIsActive(Long id, Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void delete(AppraisalSystemScoring entity) throws Exception {
		this.appraisalSystemScoringDao.delete(entity);
	}

	@Override
	public void softDelete(AppraisalSystemScoring entity) throws Exception {
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
	public List<AppraisalSystemScoring> getAllData() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AppraisalSystemScoring> getAllData(Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AppraisalSystemScoring> getAllData(Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AppraisalSystemScoring> getAllData(Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AppraisalSystemScoring> getAllDataPageAble(int firstResult, int maxResults, Order order)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AppraisalSystemScoring> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order,
			Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AppraisalSystemScoring> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order,
			Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AppraisalSystemScoring> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order,
			Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<AppraisalSystemScoring> getByParam(AppraisalSystemScoringSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception {
		return appraisalSystemScoringDao.getByParam(searchParameter, firstResult, maxResults, order);
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalByParam(AppraisalSystemScoringSearchParameter searchParameter) throws Exception {
		return appraisalSystemScoringDao.getTotalByParam(searchParameter);
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void save(AppraisalSystemScoring entity, Integer totalOption, String[] label, Integer[] scaleValue, String[] description) throws Exception {
		entity.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
        entity.setCreatedBy(UserInfoUtil.getUserName());
        entity.setCreatedOn(new Date());
        entity.setName(entity.getName());        
        appraisalSystemScoringDao.save(entity);
        
        AppraisalSystemScoringIndex appraisalSystemScoringIndex;
        for(int i = 0; i < totalOption; i++){
        	appraisalSystemScoringIndex = new AppraisalSystemScoringIndex();
        	appraisalSystemScoringIndex.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
        	appraisalSystemScoringIndex.setAppraisalSystemScoring(entity);
        	appraisalSystemScoringIndex.setLabelMask(label[i]);
        	appraisalSystemScoringIndex.setValue(scaleValue[i]);
        	appraisalSystemScoringIndex.setDescription(description[i]);
        	appraisalSystemScoringIndex.setCreatedBy(UserInfoUtil.getUserName());
        	appraisalSystemScoringIndex.setCreatedOn(new Date());
        	appraisalSystemScoringIndexDao.save(appraisalSystemScoringIndex);
        }
        
	}

}
