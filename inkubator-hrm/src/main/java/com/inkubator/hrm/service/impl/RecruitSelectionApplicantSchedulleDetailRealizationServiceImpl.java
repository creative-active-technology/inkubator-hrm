package com.inkubator.hrm.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.EmpDataDao;
import com.inkubator.hrm.dao.RecruitSelectionApplicantSchedulleDetailDao;
import com.inkubator.hrm.dao.RecruitSelectionApplicantSchedulleDetailRealizationDao;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.RecruitSelectionApplicantSchedulleDetail;
import com.inkubator.hrm.entity.RecruitSelectionApplicantSchedulleDetailRealization;
import com.inkubator.hrm.service.RecruitSelectionApplicantSchedulleDetailRealizationService;
import com.inkubator.hrm.web.model.SelectionApplicantSchedulleDetailRealizationModel;
import com.inkubator.securitycore.util.UserInfoUtil;

import ch.lambdaj.Lambda;

/**
 *
 * @author rizkykojek
 */
@Service(value = "recruitSelectionApplicantSchedulleDetailRealizationService")
@Lazy
public class RecruitSelectionApplicantSchedulleDetailRealizationServiceImpl extends IServiceImpl
		implements RecruitSelectionApplicantSchedulleDetailRealizationService {
	
	@Autowired
	private RecruitSelectionApplicantSchedulleDetailRealizationDao recruitSelectionApplicantSchedulleDetailRealizationDao;
	@Autowired
	private RecruitSelectionApplicantSchedulleDetailDao recruitSelectionApplicantSchedulleDetailDao;
	@Autowired
	private EmpDataDao empDataDao;

	@Override
	public RecruitSelectionApplicantSchedulleDetailRealization getEntiyByPK(String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitSelectionApplicantSchedulleDetailRealization getEntiyByPK(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitSelectionApplicantSchedulleDetailRealization getEntiyByPK(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(RecruitSelectionApplicantSchedulleDetailRealization entity) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(RecruitSelectionApplicantSchedulleDetailRealization entity) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveOrUpdate(RecruitSelectionApplicantSchedulleDetailRealization enntity) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public RecruitSelectionApplicantSchedulleDetailRealization saveData(
			RecruitSelectionApplicantSchedulleDetailRealization entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitSelectionApplicantSchedulleDetailRealization updateData(
			RecruitSelectionApplicantSchedulleDetailRealization entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitSelectionApplicantSchedulleDetailRealization saveOrUpdateData(
			RecruitSelectionApplicantSchedulleDetailRealization entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitSelectionApplicantSchedulleDetailRealization getEntityByPkIsActive(String id, Integer isActive)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitSelectionApplicantSchedulleDetailRealization getEntityByPkIsActive(String id, Byte isActive)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitSelectionApplicantSchedulleDetailRealization getEntityByPkIsActive(String id, Boolean isActive)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitSelectionApplicantSchedulleDetailRealization getEntityByPkIsActive(Integer id, Integer isActive)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitSelectionApplicantSchedulleDetailRealization getEntityByPkIsActive(Integer id, Byte isActive)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitSelectionApplicantSchedulleDetailRealization getEntityByPkIsActive(Integer id, Boolean isActive)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitSelectionApplicantSchedulleDetailRealization getEntityByPkIsActive(Long id, Integer isActive)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitSelectionApplicantSchedulleDetailRealization getEntityByPkIsActive(Long id, Byte isActive)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitSelectionApplicantSchedulleDetailRealization getEntityByPkIsActive(Long id, Boolean isActive)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(RecruitSelectionApplicantSchedulleDetailRealization entity) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void softDelete(RecruitSelectionApplicantSchedulleDetailRealization entity) throws Exception {
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
	public List<RecruitSelectionApplicantSchedulleDetailRealization> getAllData() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecruitSelectionApplicantSchedulleDetailRealization> getAllData(Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecruitSelectionApplicantSchedulleDetailRealization> getAllData(Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecruitSelectionApplicantSchedulleDetailRealization> getAllData(Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecruitSelectionApplicantSchedulleDetailRealization> getAllDataPageAble(int firstResult, int maxResults,
			Order order) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecruitSelectionApplicantSchedulleDetailRealization> getAllDataPageAbleIsActive(int firstResult,
			int maxResults, Order order, Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecruitSelectionApplicantSchedulleDetailRealization> getAllDataPageAbleIsActive(int firstResult,
			int maxResults, Order order, Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecruitSelectionApplicantSchedulleDetailRealization> getAllDataPageAbleIsActive(int firstResult,
			int maxResults, Order order, Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<RecruitSelectionApplicantSchedulleDetailRealization> getAllDataByApplicantIdAndSelectionApplicantSchedulleId(
			Long applicantId, Long selectionApplicantSchedulleId) {

		return recruitSelectionApplicantSchedulleDetailRealizationDao.getAllDataByApplicantIdAndSelectionApplicantSchedulleId(applicantId, selectionApplicantSchedulleId);
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<SelectionApplicantSchedulleDetailRealizationModel> getAllDataSelectionScheduleRealization(Long applicantId, Long selectionApplicantSchedulleId) {
		List<SelectionApplicantSchedulleDetailRealizationModel> listModel = new ArrayList<>();
		
		List<RecruitSelectionApplicantSchedulleDetail> listSchedule = recruitSelectionApplicantSchedulleDetailDao.
				getAllDataByApplicantIdAndSelectionApplicantSchedulleId(applicantId, selectionApplicantSchedulleId);
		
		/** sorting by urutan tahapan seleksi */
		listSchedule = Lambda.sort(listSchedule, Lambda.on(RecruitSelectionApplicantSchedulleDetail.class).getSelectionListOrder());    	
		
		for(RecruitSelectionApplicantSchedulleDetail schedule : listSchedule){		
			RecruitSelectionApplicantSchedulleDetailRealization realization = recruitSelectionApplicantSchedulleDetailRealizationDao.
					getEntityBySelectionApplicantSchedulleDetailId(schedule.getId());
			
			/** jika selection schedule realizationnya masih empty (artinya belum pernah di execute prosesnya, masih NEW), 
			 *  berarti dapatkan valuenya dari selection schedule detailnya (DEFAULT schedule) */
			SelectionApplicantSchedulleDetailRealizationModel model = new SelectionApplicantSchedulleDetailRealizationModel();
			if(realization != null){
				model = this.getModelFromRealization(realization);
			} else {
				model = this.getModelFromSchedule(schedule);
			}
			
			listModel.add(model);
    	}
    	
    	return listModel;
	}
	
	private SelectionApplicantSchedulleDetailRealizationModel getModelFromRealization(RecruitSelectionApplicantSchedulleDetailRealization realization){
		SelectionApplicantSchedulleDetailRealizationModel model = new SelectionApplicantSchedulleDetailRealizationModel();
		model.setId(realization.getId());
		model.setSelectionApplicantSchedulleDetailId(realization.getRecruitSelectionApplicantSchedulleDetail().getId());
		model.setSelectionTypeName(realization.getRecruitSelectionApplicantSchedulleDetail().getSelectionType().getName());
		model.setRealizationDate(realization.getRealizationDate());
		model.setRealizationTimeStart(realization.getRealizationTimeStart());
		model.setRealizationTimeEnd(realization.getRealizationTimeEnd());
		model.setRealizationRoom(realization.getRealizationRoom());
		model.setNotes(realization.getNotes());
		model.setScoringDate(realization.getScoringDate());
		model.setScoringPoint(realization.getScoringPoint());
		model.setScoringByEmpData(realization.getScoringByEmpData());
		model.setStatus(realization.getStatus());	
    	
    	return model;
    }
    
    private SelectionApplicantSchedulleDetailRealizationModel getModelFromSchedule(RecruitSelectionApplicantSchedulleDetail schedule){
    	SelectionApplicantSchedulleDetailRealizationModel model = new SelectionApplicantSchedulleDetailRealizationModel();
		model.setSelectionTypeName(schedule.getSelectionType().getName());
		model.setSelectionApplicantSchedulleDetailId(schedule.getId());
		model.setRealizationDate(schedule.getSchdulleDate());
		model.setRealizationTimeStart(schedule.getSchdulleTimeStart());
		model.setRealizationTimeEnd(schedule.getSchedulleTimeEnd());
		model.setRealizationRoom(schedule.getRoom());
		model.setScoringByEmpData(schedule.getEmpData());	
    	model.setStatus(HRMConstant.SELECTION_APPLICANT_STATUS_NEW);
    	return model;
    }

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void saveOrUpdate(List<SelectionApplicantSchedulleDetailRealizationModel> listModel) throws Exception {
		for(SelectionApplicantSchedulleDetailRealizationModel model :  listModel){
			if(!StringUtils.equals(model.getStatus(), HRMConstant.SELECTION_APPLICANT_STATUS_NEW)) {
				RecruitSelectionApplicantSchedulleDetailRealization realization;
				if(model.getId() != null && model.getId() != 0){
					realization = recruitSelectionApplicantSchedulleDetailRealizationDao.getEntiyByPK(model.getId());
					realization.setUpdatedBy(UserInfoUtil.getUserName());
					realization.setUpdatedOn(new Date());
				
				} else {
					realization = new RecruitSelectionApplicantSchedulleDetailRealization();
					realization.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
					realization.setRecruitSelectionApplicantSchedulleDetail(recruitSelectionApplicantSchedulleDetailDao.getEntiyByPK(model.getSelectionApplicantSchedulleDetailId()));
					realization.setCreatedBy(UserInfoUtil.getUserName());
					realization.setCreatedOn(new Date());
				}
				
				realization.setRealizationDate(model.getRealizationDate());
				realization.setRealizationTimeStart(model.getRealizationTimeStart());
				realization.setRealizationTimeEnd(model.getRealizationTimeEnd());
				realization.setRealizationRoom(model.getRealizationRoom());
				realization.setNotes(model.getNotes());
				realization.setScoringDate(model.getScoringDate());
				realization.setScoringPoint(model.getScoringPoint());
				realization.setScoringByEmpData(empDataDao.getEntiyByPK(model.getScoringByEmpData().getId()));
				realization.setStatus(model.getStatus());
				
				recruitSelectionApplicantSchedulleDetailRealizationDao.saveOrUpdate(realization);
			}
		}
	}

}
