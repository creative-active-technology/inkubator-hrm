package com.inkubator.hrm.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Order;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.RecruitApplicantDao;
import com.inkubator.hrm.dao.RecruitHireApplyDao;
import com.inkubator.hrm.dao.RecruitSelectionApplicantPassedDao;
import com.inkubator.hrm.dao.RecruitSelectionApplicantSchedulleDao;
import com.inkubator.hrm.dao.RecruitSelectionApplicantSchedulleDetailDao;
import com.inkubator.hrm.dao.RecruitSelectionApplicantSchedulleDetailRealizationDao;
import com.inkubator.hrm.dao.RecruitmenSelectionSeriesDetailDao;
import com.inkubator.hrm.entity.RecruitApplicant;
import com.inkubator.hrm.entity.RecruitHireApply;
import com.inkubator.hrm.entity.RecruitSelectionApplicantPassed;
import com.inkubator.hrm.entity.RecruitSelectionApplicantPassedId;
import com.inkubator.hrm.entity.RecruitSelectionApplicantSchedulle;
import com.inkubator.hrm.entity.RecruitSelectionApplicantSchedulleDetail;
import com.inkubator.hrm.entity.RecruitSelectionApplicantSchedulleDetailRealization;
import com.inkubator.hrm.entity.RecruitSelectionType;
import com.inkubator.hrm.entity.RecruitmenSelectionSeriesDetail;
import com.inkubator.hrm.entity.RecruitmenSelectionSeriesDetailId;
import com.inkubator.hrm.service.RecruitSelectionApplicantPassedService;
import com.inkubator.hrm.web.model.RecruitSelectionApplicantPassedViewModel;
import com.inkubator.hrm.web.search.RecruitSelectionApplicantPassedSearchParameter;
import com.inkubator.securitycore.util.UserInfoUtil;

import ch.lambdaj.Lambda;

/**
 *
 * @author rizkykojek
 */
@Service(value = "recruitSelectionApplicantPassedService")
@Lazy
public class RecruitSelectionApplicantPassedServiceImpl extends IServiceImpl implements RecruitSelectionApplicantPassedService {

	@Autowired
	private RecruitSelectionApplicantSchedulleDao recruitSelectionApplicantSchedulleDao;
	@Autowired
	private RecruitSelectionApplicantPassedDao recruitSelectionApplicantPassedDao;
	@Autowired
	private RecruitApplicantDao recruitApplicantDao;
	@Autowired
	private RecruitSelectionApplicantSchedulleDetailRealizationDao recruitSelectionApplicantSchedulleDetailRealizationDao;
	@Autowired
	private RecruitmenSelectionSeriesDetailDao recruitmenSelectionSeriesDetailDao;
	
	@Override
	public RecruitSelectionApplicantPassed getEntiyByPK(String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitSelectionApplicantPassed getEntiyByPK(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitSelectionApplicantPassed getEntiyByPK(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(RecruitSelectionApplicantPassed entity) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(RecruitSelectionApplicantPassed entity) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveOrUpdate(RecruitSelectionApplicantPassed enntity) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public RecruitSelectionApplicantPassed saveData(RecruitSelectionApplicantPassed entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitSelectionApplicantPassed updateData(RecruitSelectionApplicantPassed entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitSelectionApplicantPassed saveOrUpdateData(RecruitSelectionApplicantPassed entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitSelectionApplicantPassed getEntityByPkIsActive(String id, Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitSelectionApplicantPassed getEntityByPkIsActive(String id, Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitSelectionApplicantPassed getEntityByPkIsActive(String id, Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitSelectionApplicantPassed getEntityByPkIsActive(Integer id, Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitSelectionApplicantPassed getEntityByPkIsActive(Integer id, Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitSelectionApplicantPassed getEntityByPkIsActive(Integer id, Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitSelectionApplicantPassed getEntityByPkIsActive(Long id, Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitSelectionApplicantPassed getEntityByPkIsActive(Long id, Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitSelectionApplicantPassed getEntityByPkIsActive(Long id, Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(RecruitSelectionApplicantPassed entity) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void softDelete(RecruitSelectionApplicantPassed entity) throws Exception {
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
	public List<RecruitSelectionApplicantPassed> getAllData() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecruitSelectionApplicantPassed> getAllData(Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecruitSelectionApplicantPassed> getAllData(Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecruitSelectionApplicantPassed> getAllData(Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecruitSelectionApplicantPassed> getAllDataPageAble(int firstResult, int maxResults, Order order)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecruitSelectionApplicantPassed> getAllDataPageAbleIsActive(int firstResult, int maxResults,
			Order order, Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecruitSelectionApplicantPassed> getAllDataPageAbleIsActive(int firstResult, int maxResults,
			Order order, Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecruitSelectionApplicantPassed> getAllDataPageAbleIsActive(int firstResult, int maxResults,
			Order order, Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void save(Long selectionScheduleId, List<Long> listApplicantId) throws Exception {
		RecruitSelectionApplicantSchedulle selectionApplicantSchedulle = recruitSelectionApplicantSchedulleDao.getEntiyByPK(selectionScheduleId);
		RecruitHireApply hireApply = selectionApplicantSchedulle.getHireApply();
		for(Long applicantId : listApplicantId){
			RecruitApplicant applicant = recruitApplicantDao.getEntiyByPK(applicantId);
			
			/** dapatkan list dari realisasinya */
			List<RecruitSelectionApplicantSchedulleDetailRealization> listSelectionRealization = recruitSelectionApplicantSchedulleDetailRealizationDao.getAllDataByApplicantIdAndSelectionApplicantSchedulleId(applicant.getId(), selectionApplicantSchedulle.getId());

			/** dapatkan kapan terakhir test nya, dengan cara order desc dari realization */
			Date latestTestDate = ((RecruitSelectionApplicantSchedulleDetailRealization) Lambda.sort(listSelectionRealization, Lambda.on(RecruitSelectionApplicantSchedulleDetailRealization.class).getRealizationDate(), Collections.reverseOrder()).get(0)).getRealizationDate();
			
			/** dapatkan tanggal expiry nya, untuk digunakan di proses scheduller of offering letter 
			 *  jika result dari letterExpired==null artinya tidak ada proses pengiriman offering letter*/
			Date letterExpired = null;
			RecruitSelectionApplicantSchedulleDetailRealization latestStepSelection = (RecruitSelectionApplicantSchedulleDetailRealization) Lambda.sort(listSelectionRealization, Lambda.on(RecruitSelectionApplicantSchedulleDetailRealization.class).getRecruitSelectionApplicantSchedulleDetail().getSelectionListOrder(), Collections.reverseOrder()).get(0);
			Long selectionSeriesId = latestStepSelection.getRecruitSelectionApplicantSchedulleDetail().getRecruitSelectionApplicantSchedulle().getSelectionSeries().getId();
			Long selectionTypeId = latestStepSelection.getRecruitSelectionApplicantSchedulleDetail().getSelectionType().getId();
			RecruitmenSelectionSeriesDetail selectionSeriesDetail = recruitmenSelectionSeriesDetailDao.getEntityByPk(new RecruitmenSelectionSeriesDetailId(selectionSeriesId, selectionTypeId));
			if(selectionSeriesDetail.getRecruitLettersByAcceptLetterId() != null && selectionSeriesDetail.getRecruitLettersByAcceptLetterId().getExpiryDays() != null){
				Integer expiredInDays = selectionSeriesDetail.getRecruitLettersByAcceptLetterId().getExpiryDays();
				letterExpired = new DateTime().plusDays(expiredInDays).toDate();
			}
			
			/** mulai set value entity nya untuk proses saving */
			RecruitSelectionApplicantPassed selectionApplicantPassed = new RecruitSelectionApplicantPassed();
			selectionApplicantPassed.setId(new RecruitSelectionApplicantPassedId(hireApply.getId(), applicant.getId()));
			selectionApplicantPassed.setHireApply(hireApply);
			selectionApplicantPassed.setApplicant(applicant);
			selectionApplicantPassed.setLatestTestDate(latestTestDate);
			selectionApplicantPassed.setLetterExpired(letterExpired);
			selectionApplicantPassed.setPlacementStatus(HRMConstant.SELECTION_APPLICANT_PASSED_STATUS_PENDING);
			selectionApplicantPassed.setCreatedBy(UserInfoUtil.getUserName());
			selectionApplicantPassed.setCreatedOn(new Date());
			
			recruitSelectionApplicantPassedDao.save(selectionApplicantPassed);
		}
		
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public Long getTotalByHireApplyIdAndNotPlacementStatus(Long hireApplyId, String placementStatus) throws Exception {
		return recruitSelectionApplicantPassedDao.getTotalByHireApplyIdAndPlacementStatus(hireApplyId,placementStatus);
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<RecruitSelectionApplicantPassedViewModel> getListSelectionPassedViewModelByParam(RecruitSelectionApplicantPassedSearchParameter searchParameter, int firstResult, int maxResults, Order orderable) throws Exception {
		//return recruitSelectionApplicantPassedDao.getListSelectionPassedViewModelByParam(searchParameter, firstResult, maxResults, orderable);
		return new ArrayList<RecruitSelectionApplicantPassedViewModel>();
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public Long getTotalSelectionPassedViewModelByParam(RecruitSelectionApplicantPassedSearchParameter searchParameter) throws Exception {
		//return recruitSelectionApplicantPassedDao.getTotalSelectionPassedViewModelByParam(searchParameter);
		return 0l;
	}

}
