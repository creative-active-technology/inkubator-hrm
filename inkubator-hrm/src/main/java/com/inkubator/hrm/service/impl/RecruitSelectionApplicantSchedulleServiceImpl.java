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
import com.inkubator.hrm.dao.EmpDataDao;
import com.inkubator.hrm.dao.RecruitApplicantDao;
import com.inkubator.hrm.dao.RecruitHireApplyDao;
import com.inkubator.hrm.dao.RecruitSelectionApplicantSchedulleDao;
import com.inkubator.hrm.dao.RecruitSelectionApplicantSchedulleDetailDao;
import com.inkubator.hrm.dao.RecruitSelectionApplicantSchedulleDetailRealizationDao;
import com.inkubator.hrm.dao.RecruitSelectionTypeDao;
import com.inkubator.hrm.dao.RecruitmenSelectionSeriesDao;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.RecruitApplicant;
import com.inkubator.hrm.entity.RecruitHireApply;
import com.inkubator.hrm.entity.RecruitSelectionApplicantSchedulle;
import com.inkubator.hrm.entity.RecruitSelectionApplicantSchedulleDetail;
import com.inkubator.hrm.entity.RecruitSelectionApplicantSchedulleDetailRealization;
import com.inkubator.hrm.entity.RecruitSelectionType;
import com.inkubator.hrm.entity.RecruitmenSelectionSeries;
import com.inkubator.hrm.service.RecruitSelectionApplicantSchedulleService;
import com.inkubator.hrm.util.HrmUserInfoUtil;
import com.inkubator.hrm.web.model.SelectionApplicantPassedViewModel;
import com.inkubator.hrm.web.model.SelectionPositionPassedViewModel;

/**
 *
 * @author rizkykojek
 */
@Service(value = "recruitSelectionApplicantSchedulleService")
@Lazy
public class RecruitSelectionApplicantSchedulleServiceImpl extends IServiceImpl	implements RecruitSelectionApplicantSchedulleService {

	@Autowired
	private RecruitSelectionApplicantSchedulleDao recruitSelectionApplicantSchedulleDao;
	@Autowired
	private RecruitSelectionApplicantSchedulleDetailDao recruitSelectionApplicantSchedulleDetailDao;
	@Autowired
	private RecruitHireApplyDao recruitHireApplyDao;
	@Autowired
	private EmpDataDao empDataDao;
	@Autowired
	private RecruitmenSelectionSeriesDao recruitmenSelectionSeriesDao;
	@Autowired
	private RecruitApplicantDao recruitApplicantDao;
	@Autowired
	private RecruitSelectionTypeDao recruitSelectionTypeDao;
	@Autowired
	private RecruitSelectionApplicantSchedulleDetailRealizationDao recruitSelectionApplicantSchedulleDetailRealizationDao;
	
	@Override
	public RecruitSelectionApplicantSchedulle getEntiyByPK(String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitSelectionApplicantSchedulle getEntiyByPK(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public RecruitSelectionApplicantSchedulle getEntiyByPK(Long id) throws Exception {
		
		return recruitSelectionApplicantSchedulleDao.getEntiyByPK(id);
	}

	@Override
	public void save(RecruitSelectionApplicantSchedulle entity) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(RecruitSelectionApplicantSchedulle entity) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveOrUpdate(RecruitSelectionApplicantSchedulle enntity) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public RecruitSelectionApplicantSchedulle saveData(RecruitSelectionApplicantSchedulle entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitSelectionApplicantSchedulle updateData(RecruitSelectionApplicantSchedulle entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitSelectionApplicantSchedulle saveOrUpdateData(RecruitSelectionApplicantSchedulle entity)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitSelectionApplicantSchedulle getEntityByPkIsActive(String id, Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitSelectionApplicantSchedulle getEntityByPkIsActive(String id, Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitSelectionApplicantSchedulle getEntityByPkIsActive(String id, Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitSelectionApplicantSchedulle getEntityByPkIsActive(Integer id, Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitSelectionApplicantSchedulle getEntityByPkIsActive(Integer id, Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitSelectionApplicantSchedulle getEntityByPkIsActive(Integer id, Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitSelectionApplicantSchedulle getEntityByPkIsActive(Long id, Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitSelectionApplicantSchedulle getEntityByPkIsActive(Long id, Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitSelectionApplicantSchedulle getEntityByPkIsActive(Long id, Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(RecruitSelectionApplicantSchedulle entity) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void softDelete(RecruitSelectionApplicantSchedulle entity) throws Exception {
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
	public List<RecruitSelectionApplicantSchedulle> getAllData() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecruitSelectionApplicantSchedulle> getAllData(Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecruitSelectionApplicantSchedulle> getAllData(Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecruitSelectionApplicantSchedulle> getAllData(Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecruitSelectionApplicantSchedulle> getAllDataPageAble(int firstResult, int maxResults, Order order)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecruitSelectionApplicantSchedulle> getAllDataPageAbleIsActive(int firstResult, int maxResults,
			Order order, Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecruitSelectionApplicantSchedulle> getAllDataPageAbleIsActive(int firstResult, int maxResults,
			Order order, Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecruitSelectionApplicantSchedulle> getAllDataPageAbleIsActive(int firstResult, int maxResults,
			Order order, Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public RecruitSelectionApplicantSchedulle getEntityByPkWithDetail(Long id) {
		
		return recruitSelectionApplicantSchedulleDao.getEntityByPkWithDetail(id);
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<SelectionPositionPassedViewModel> getSelectionPositionPassedByParam(String parameter, int firstResults, int maxResults, Order orderable) throws Exception {

		return recruitSelectionApplicantSchedulleDao.getSelectionPositionPassedByParam(parameter, firstResults, maxResults, orderable);
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public Long getTotalSelectionPositionPassedByParam(String parameter) throws Exception {
		
		return recruitSelectionApplicantSchedulleDao.getTotalSelectionPositionPassedByParam(parameter);
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String saveData(RecruitSelectionApplicantSchedulle recruitSelectionchedulle, List<RecruitSelectionApplicantSchedulleDetail> listRecruitSelectionScheduleDetail)	throws Exception {
		
		String result = "error";
		String createBy = HrmUserInfoUtil.getUserName();
		Date createOn = new Date(); 
		
		recruitSelectionchedulle.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
		recruitSelectionchedulle.setCreatedBy(createBy);
		recruitSelectionchedulle.setCreatedOn(createOn);
		
		RecruitHireApply recruitHireApply = recruitHireApplyDao.getEntiyByPK(recruitSelectionchedulle.getHireApply().getId());
		EmpData empData = empDataDao.getEntiyByPK(recruitSelectionchedulle.getEmpData().getId());
		RecruitmenSelectionSeries recruitmenSelectionSeries = recruitmenSelectionSeriesDao.getEntiyByPK(recruitSelectionchedulle.getSelectionSeries().getId());
		
		recruitSelectionchedulle.setHireApply(recruitHireApply);
		recruitSelectionchedulle.setEmpData(empData);
		recruitSelectionchedulle.setSelectionSeries(recruitmenSelectionSeries);
		recruitSelectionApplicantSchedulleDao.save(recruitSelectionchedulle);
		
		for(RecruitSelectionApplicantSchedulleDetail scheduleDetail : listRecruitSelectionScheduleDetail){
			
			scheduleDetail.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
			scheduleDetail.setCreatedBy(createBy);
			scheduleDetail.setCreatedOn(createOn);
			
			RecruitApplicant recruitApplicant = recruitApplicantDao.getEntiyByPK(scheduleDetail.getApplicant().getId());
			EmpData empDataScheduleDetail = empDataDao.getEntiyByPK(scheduleDetail.getEmpData().getId());
			RecruitSelectionType recruitSelectionType = recruitSelectionTypeDao.getEntiyByPK(scheduleDetail.getSelectionType().getId());
			
			scheduleDetail.setApplicant(recruitApplicant);
			scheduleDetail.setEmpData(empDataScheduleDetail);
			scheduleDetail.setRecruitSelectionApplicantSchedulle(recruitSelectionchedulle);
			scheduleDetail.setSelectionType(recruitSelectionType);
			
			recruitSelectionApplicantSchedulleDetailDao.save(scheduleDetail);
			
		}
		
		result = "success";
		
		return result;
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<SelectionApplicantPassedViewModel> getSelectionApplicantPassedByParam(Long scheduleId, int firstResults, int maxResults, Order orderable) throws Exception {
		StringBuffer selectionTypeOfMaxScore = new StringBuffer();
		StringBuffer selectionTypeOfMinScore = new StringBuffer();
		
		List<SelectionApplicantPassedViewModel> listViewModel = recruitSelectionApplicantSchedulleDao.getSelectionApplicantPassedByParam(scheduleId, firstResults, maxResults, orderable);
		for(SelectionApplicantPassedViewModel model : listViewModel) {
			/** looping untuk mendapatkan nama proses rangkaian seleksi yang mempunyai nilai yang sama */
			List<RecruitSelectionApplicantSchedulleDetailRealization> listMaxScore = recruitSelectionApplicantSchedulleDetailRealizationDao.getAllDataByApplicantIdAndSelectionApplicantSchedulleIdAndScore(model.getApplicantId().longValue(), scheduleId, model.getMaxScore());
			List<RecruitSelectionApplicantSchedulleDetailRealization> listMinScore = recruitSelectionApplicantSchedulleDetailRealizationDao.getAllDataByApplicantIdAndSelectionApplicantSchedulleIdAndScore(model.getApplicantId().longValue(), scheduleId, model.getMinScore());
			
			selectionTypeOfMaxScore.setLength(0);
			selectionTypeOfMinScore.setLength(0);
			
			for(RecruitSelectionApplicantSchedulleDetailRealization realization : listMaxScore){
				if(selectionTypeOfMaxScore.length() > 0){
					selectionTypeOfMaxScore.append(", ");
				}
				selectionTypeOfMaxScore.append(realization.getRecruitSelectionApplicantSchedulleDetail().getSelectionType().getName());				
			}
			
			for(RecruitSelectionApplicantSchedulleDetailRealization realization : listMinScore){
				if(selectionTypeOfMinScore.length() > 0){
					selectionTypeOfMinScore.append(", ");
				}
				selectionTypeOfMinScore.append(realization.getRecruitSelectionApplicantSchedulleDetail().getSelectionType().getName());				
			}
			
			model.setSelectionTypeOfMaxScore(selectionTypeOfMaxScore.toString());
			model.setSelectionTypeOfMinScore(selectionTypeOfMinScore.toString());
		}
		
		return listViewModel;
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public Long getTotalSelectionApplicantPassedByParam(Long scheduleId) throws Exception {		
		return recruitSelectionApplicantSchedulleDao.getTotalSelectionApplicantPassedByParam(scheduleId);
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
	public Boolean isHireApplyAlreadyHaveSelectionSchedulle(Long hireApplyId) throws Exception {
		return recruitSelectionApplicantSchedulleDao.isHireApplyAlreadyHaveSelectionSchedulle(hireApplyId);
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
	public RecruitSelectionApplicantSchedulle getEntityWithDetailByHireApplyId(Long hireApplyId) throws Exception {
		return recruitSelectionApplicantSchedulleDao.getEntityWithDetailByHireApplyId(hireApplyId);
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String updateData(RecruitSelectionApplicantSchedulle recruitSelectionchedulle, List<RecruitSelectionApplicantSchedulleDetail> listRecruitSelectionScheduleDetail) throws Exception {
		
		String result = "error";
		String updateBy = HrmUserInfoUtil.getUserName();
		Date updateOn = new Date(); 
		
		RecruitSelectionApplicantSchedulle recruitSheduleToUpdate = recruitSelectionApplicantSchedulleDao.getEntiyByPK(recruitSelectionchedulle.getId());
		EmpData empData = empDataDao.getEntiyByPK(recruitSelectionchedulle.getEmpData().getId());
		
		recruitSheduleToUpdate.setEmpData(empData);
		recruitSheduleToUpdate.setUpdatedBy(updateBy);
		recruitSheduleToUpdate.setUpdatedOn(updateOn);
		recruitSelectionApplicantSchedulleDao.update(recruitSheduleToUpdate);
		
		for(RecruitSelectionApplicantSchedulleDetail scheduleDetail : listRecruitSelectionScheduleDetail){
			
			RecruitSelectionApplicantSchedulleDetail scheduleDetailToUpdate = recruitSelectionApplicantSchedulleDetailDao.getEntiyByPK(scheduleDetail.getId());
			EmpData empDataScheduleDetail = empDataDao.getEntiyByPK(scheduleDetail.getEmpData().getId());
			
			scheduleDetailToUpdate.setEmpData(empDataScheduleDetail);
			scheduleDetailToUpdate.setNotes(scheduleDetail.getNotes());
			scheduleDetailToUpdate.setRoom(scheduleDetail.getRoom());
			scheduleDetailToUpdate.setSchdulleDate(scheduleDetail.getSchdulleDate());
			scheduleDetailToUpdate.setSchdulleTimeStart(scheduleDetail.getSchdulleTimeStart());
			scheduleDetailToUpdate.setSchedulleTimeEnd(scheduleDetail.getSchedulleTimeEnd());
			scheduleDetailToUpdate.setSelectionListOrder(scheduleDetail.getSelectionListOrder());
			scheduleDetailToUpdate.setUpdatedBy(updateBy);
			scheduleDetailToUpdate.setUpdatedOn(updateOn);
			
			recruitSelectionApplicantSchedulleDetailDao.update(scheduleDetailToUpdate);
			
		}
		
		result = "success";
		return result;
		
	}

}
