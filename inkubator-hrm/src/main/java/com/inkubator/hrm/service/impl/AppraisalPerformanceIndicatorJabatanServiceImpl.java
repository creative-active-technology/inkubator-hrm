package com.inkubator.hrm.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.hrm.dao.AppraisalPerformanceIndicatorDao;
import com.inkubator.hrm.dao.AppraisalPerformanceIndicatorJabatanDao;
import com.inkubator.hrm.dao.JabatanDao;
import com.inkubator.hrm.dao.SystemScoringIndexDao;
import com.inkubator.hrm.entity.AppraisalPerformanceIndicator;
import com.inkubator.hrm.entity.AppraisalPerformanceIndicatorJabatan;
import com.inkubator.hrm.entity.Jabatan;
import com.inkubator.hrm.entity.SystemScoringIndex;
import com.inkubator.hrm.service.AppraisalPerformanceIndicatorJabatanService;
import com.inkubator.securitycore.util.UserInfoUtil;

/**
 *
 * @author rizkykojek
 */
@Service(value = "appraisalPerformanceIndicatorJabatanService")
@Lazy
public class AppraisalPerformanceIndicatorJabatanServiceImpl extends IServiceImpl implements AppraisalPerformanceIndicatorJabatanService {

	@Autowired
	private AppraisalPerformanceIndicatorJabatanDao appraisalPerformanceIndicatorJabatanDao;
	@Autowired
	private JabatanDao jabatanDao;
	@Autowired
	private SystemScoringIndexDao systemScoringIndexDao;
	@Autowired
	private AppraisalPerformanceIndicatorDao appraisalPerformanceIndicatorDao;
	
	@Override
	public AppraisalPerformanceIndicatorJabatan getEntiyByPK(String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalPerformanceIndicatorJabatan getEntiyByPK(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalPerformanceIndicatorJabatan getEntiyByPK(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(AppraisalPerformanceIndicatorJabatan entity) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(AppraisalPerformanceIndicatorJabatan entity) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveOrUpdate(AppraisalPerformanceIndicatorJabatan enntity) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public AppraisalPerformanceIndicatorJabatan saveData(AppraisalPerformanceIndicatorJabatan entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalPerformanceIndicatorJabatan updateData(AppraisalPerformanceIndicatorJabatan entity)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalPerformanceIndicatorJabatan saveOrUpdateData(AppraisalPerformanceIndicatorJabatan entity)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalPerformanceIndicatorJabatan getEntityByPkIsActive(String id, Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalPerformanceIndicatorJabatan getEntityByPkIsActive(String id, Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalPerformanceIndicatorJabatan getEntityByPkIsActive(String id, Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalPerformanceIndicatorJabatan getEntityByPkIsActive(Integer id, Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalPerformanceIndicatorJabatan getEntityByPkIsActive(Integer id, Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalPerformanceIndicatorJabatan getEntityByPkIsActive(Integer id, Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalPerformanceIndicatorJabatan getEntityByPkIsActive(Long id, Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalPerformanceIndicatorJabatan getEntityByPkIsActive(Long id, Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalPerformanceIndicatorJabatan getEntityByPkIsActive(Long id, Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(AppraisalPerformanceIndicatorJabatan entity) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void softDelete(AppraisalPerformanceIndicatorJabatan entity) throws Exception {
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
	public List<AppraisalPerformanceIndicatorJabatan> getAllData() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AppraisalPerformanceIndicatorJabatan> getAllData(Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AppraisalPerformanceIndicatorJabatan> getAllData(Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AppraisalPerformanceIndicatorJabatan> getAllData(Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AppraisalPerformanceIndicatorJabatan> getAllDataPageAble(int firstResult, int maxResults, Order order)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AppraisalPerformanceIndicatorJabatan> getAllDataPageAbleIsActive(int firstResult, int maxResults,
			Order order, Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AppraisalPerformanceIndicatorJabatan> getAllDataPageAbleIsActive(int firstResult, int maxResults,
			Order order, Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AppraisalPerformanceIndicatorJabatan> getAllDataPageAbleIsActive(int firstResult, int maxResults,
			Order order, Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<AppraisalPerformanceIndicatorJabatan> getAllDataByJabatanIdFetchScoringIndex(Long jabatanId) throws Exception {
		
		return appraisalPerformanceIndicatorJabatanDao.getAllDataByJabatanIdFetchScoringIndex(jabatanId);
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void saveOrUpdate(Long jabatanId, Map<Long, Long> mapIndicatorScoreIndex) {
		Jabatan jabatan = jabatanDao.getEntiyByPK(jabatanId);
		
		/** Behavior save/update : Delete all record, then insert each record */
		appraisalPerformanceIndicatorJabatanDao.deleteByJabatanId(jabatan.getId());
		
		for(Map.Entry<Long, Long> entry : mapIndicatorScoreIndex.entrySet()){
			if(entry.getValue() != null) {
				Long performanceIndicatorId = (Long) entry.getKey();
				Long scoringIndexId = Long.parseLong(String.valueOf(entry.getValue()));
				AppraisalPerformanceIndicator performanceIndicator = appraisalPerformanceIndicatorDao.getEntiyByPK(performanceIndicatorId);
				SystemScoringIndex scoringIndex =  systemScoringIndexDao.getEntiyByPK(scoringIndexId);
				
				AppraisalPerformanceIndicatorJabatan appraisalPerformanceIndicatorJabatan = new AppraisalPerformanceIndicatorJabatan();
				appraisalPerformanceIndicatorJabatan.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
				appraisalPerformanceIndicatorJabatan.setJabatan(jabatan);
				appraisalPerformanceIndicatorJabatan.setPerformanceIndicator(performanceIndicator);
				appraisalPerformanceIndicatorJabatan.setSystemScoringIndex(scoringIndex);
				appraisalPerformanceIndicatorJabatan.setCreatedBy(UserInfoUtil.getUserName());
				appraisalPerformanceIndicatorJabatan.setCreatedOn(new Date());
				
				appraisalPerformanceIndicatorJabatanDao.save(appraisalPerformanceIndicatorJabatan);
			}
		}
	}

}
