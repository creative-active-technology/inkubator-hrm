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
import com.inkubator.hrm.dao.RecruitSelectionApplicantSchedulleDetailDao;
import com.inkubator.hrm.entity.RecruitSelectionApplicantSchedulleDetail;
import com.inkubator.hrm.service.RecruitSelectionApplicantSchedulleDetailService;

/**
 *
 * @author rizkykojek
 */
@Service(value = "recruitSelectionApplicantSchedulleDetailRealizationService")
@Lazy
public class RecruitSelectionApplicantSchedulleDetailServiceImpl extends IServiceImpl
		implements RecruitSelectionApplicantSchedulleDetailService {

	@Autowired
	private RecruitSelectionApplicantSchedulleDetailDao recruitSelectionApplicantSchedulleDetailDao;
	
	@Override
	public RecruitSelectionApplicantSchedulleDetail getEntiyByPK(String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitSelectionApplicantSchedulleDetail getEntiyByPK(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitSelectionApplicantSchedulleDetail getEntiyByPK(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(RecruitSelectionApplicantSchedulleDetail entity) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(RecruitSelectionApplicantSchedulleDetail entity) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveOrUpdate(RecruitSelectionApplicantSchedulleDetail enntity) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public RecruitSelectionApplicantSchedulleDetail saveData(RecruitSelectionApplicantSchedulleDetail entity)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitSelectionApplicantSchedulleDetail updateData(RecruitSelectionApplicantSchedulleDetail entity)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitSelectionApplicantSchedulleDetail saveOrUpdateData(RecruitSelectionApplicantSchedulleDetail entity)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitSelectionApplicantSchedulleDetail getEntityByPkIsActive(String id, Integer isActive)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitSelectionApplicantSchedulleDetail getEntityByPkIsActive(String id, Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitSelectionApplicantSchedulleDetail getEntityByPkIsActive(String id, Boolean isActive)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitSelectionApplicantSchedulleDetail getEntityByPkIsActive(Integer id, Integer isActive)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitSelectionApplicantSchedulleDetail getEntityByPkIsActive(Integer id, Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitSelectionApplicantSchedulleDetail getEntityByPkIsActive(Integer id, Boolean isActive)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitSelectionApplicantSchedulleDetail getEntityByPkIsActive(Long id, Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitSelectionApplicantSchedulleDetail getEntityByPkIsActive(Long id, Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitSelectionApplicantSchedulleDetail getEntityByPkIsActive(Long id, Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(RecruitSelectionApplicantSchedulleDetail entity) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void softDelete(RecruitSelectionApplicantSchedulleDetail entity) throws Exception {
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
	public List<RecruitSelectionApplicantSchedulleDetail> getAllData() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecruitSelectionApplicantSchedulleDetail> getAllData(Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecruitSelectionApplicantSchedulleDetail> getAllData(Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecruitSelectionApplicantSchedulleDetail> getAllData(Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecruitSelectionApplicantSchedulleDetail> getAllDataPageAble(int firstResult, int maxResults,
			Order order) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecruitSelectionApplicantSchedulleDetail> getAllDataPageAbleIsActive(int firstResult, int maxResults,
			Order order, Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecruitSelectionApplicantSchedulleDetail> getAllDataPageAbleIsActive(int firstResult, int maxResults,
			Order order, Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecruitSelectionApplicantSchedulleDetail> getAllDataPageAbleIsActive(int firstResult, int maxResults,
			Order order, Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<RecruitSelectionApplicantSchedulleDetail> getAllDataByApplicantIdAndSelectionApplicantSchedulleId(
			Long applicantId, Long selectionApplicantSchedulleId) {

		return recruitSelectionApplicantSchedulleDetailDao.getAllDataByApplicantIdAndSelectionApplicantSchedulleId(applicantId, selectionApplicantSchedulleId);
	}

}
