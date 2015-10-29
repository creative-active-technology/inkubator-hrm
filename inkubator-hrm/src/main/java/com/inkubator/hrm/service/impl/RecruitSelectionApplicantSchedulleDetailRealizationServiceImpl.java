package com.inkubator.hrm.service.impl;

import java.util.ArrayList;
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
import com.inkubator.hrm.dao.RecruitSelectionApplicantSchedulleDetailRealizationDao;
import com.inkubator.hrm.entity.RecruitSelectionApplicantSchedulleDetail;
import com.inkubator.hrm.entity.RecruitSelectionApplicantSchedulleDetailRealization;
import com.inkubator.hrm.service.RecruitSelectionApplicantSchedulleDetailRealizationService;
import com.inkubator.hrm.web.model.SelectionApplicantSchedulleDetailRealizationModel;

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
		List<SelectionApplicantSchedulleDetailRealizationModel> listModel;
		
		List<RecruitSelectionApplicantSchedulleDetailRealization> listRealization = recruitSelectionApplicantSchedulleDetailRealizationDao.
    			getAllDataByApplicantIdAndSelectionApplicantSchedulleId(applicantId, selectionApplicantSchedulleId);
    	if(listRealization.isEmpty()){	     
    		List<RecruitSelectionApplicantSchedulleDetail> listSchedule = recruitSelectionApplicantSchedulleDetailDao.
    				getAllDataByApplicantIdAndSelectionApplicantSchedulleId(applicantId, selectionApplicantSchedulleId);
    		listModel = this.getModelFromListSchedule(listSchedule);
    		
    	} else {
    		listModel = this.getModelFromListRealization(listRealization);
    		
    	}
    	
    	return listModel;
	}
	
	private List<SelectionApplicantSchedulleDetailRealizationModel> getModelFromListRealization(List<RecruitSelectionApplicantSchedulleDetailRealization> listRealization){
    	List<SelectionApplicantSchedulleDetailRealizationModel> list =  new ArrayList<SelectionApplicantSchedulleDetailRealizationModel>();
    	for(RecruitSelectionApplicantSchedulleDetailRealization realization  : listRealization){
    		SelectionApplicantSchedulleDetailRealizationModel model = new SelectionApplicantSchedulleDetailRealizationModel();
    		model.setId(realization.getId());
    		model.setSelectionApplicantSchedulleDetailId(realization.getRecruitSelectionApplicantSchedulleDetail().getId());
    		model.setRealizationDate(realization.getRealizationDate());
    		model.setRealizationTimeStart(realization.getRealizationTimeStart());
    		model.setRealizationTimeEnd(realization.getRealizationTimeEnd());
    		model.setRealizationRoom(realization.getRealizationRoom());
    		model.setNotes(realization.getNotes());
    		model.setScoringDate(realization.getScoringDate());
    		model.setScoringPoint(realization.getScoringPoint());
    		model.setScoringByEmpDataId(realization.getScoringByEmpData().getId());
    		model.setStatus(realization.getStatus());
    		list.add(model);
    	}
    	
    	return list;
    }
    
    private List<SelectionApplicantSchedulleDetailRealizationModel> getModelFromListSchedule(List<RecruitSelectionApplicantSchedulleDetail> listSchedule){
    	List<SelectionApplicantSchedulleDetailRealizationModel> list =  new ArrayList<SelectionApplicantSchedulleDetailRealizationModel>();
    	for(RecruitSelectionApplicantSchedulleDetail schedule : listSchedule){
    		SelectionApplicantSchedulleDetailRealizationModel model = new SelectionApplicantSchedulleDetailRealizationModel();
    		model.setSelectionApplicantSchedulleDetailId(schedule.getId());
    		model.setRealizationDate(schedule.getSchdulleDate());
    		model.setRealizationTimeStart(schedule.getSchdulleTimeStart());
    		model.setRealizationTimeEnd(schedule.getSchedulleTimeEnd());
    		model.setRealizationRoom(schedule.getRoom());
    		model.setScoringByEmpDataId(schedule.getEmpData().getId());
    		list.add(model);
    	}    	
    	
    	return list;
    }

}
