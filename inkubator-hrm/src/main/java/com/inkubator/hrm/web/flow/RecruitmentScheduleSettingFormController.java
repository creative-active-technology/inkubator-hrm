package com.inkubator.hrm.web.flow;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.webflow.execution.RequestContext;

import ch.lambdaj.Lambda;
import ch.lambdaj.group.Group;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.RecruitApplicant;
import com.inkubator.hrm.entity.RecruitHireApply;
import com.inkubator.hrm.entity.RecruitMppApplyDetail;
import com.inkubator.hrm.entity.RecruitSelectionApplicantInitial;
import com.inkubator.hrm.entity.RecruitSelectionApplicantSchedulle;
import com.inkubator.hrm.entity.RecruitSelectionApplicantSchedulleDetail;
import com.inkubator.hrm.entity.RecruitSelectionType;
import com.inkubator.hrm.entity.RecruitmenSelectionSeries;
import com.inkubator.hrm.entity.RecruitmenSelectionSeriesDetail;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.RecruitHireApplyService;
import com.inkubator.hrm.service.RecruitMppApplyDetailService;
import com.inkubator.hrm.service.RecruitMppApplyService;
import com.inkubator.hrm.service.RecruitSelectionApplicantInitialService;
import com.inkubator.hrm.service.RecruitSelectionApplicantSchedulleDetailRealizationService;
import com.inkubator.hrm.service.RecruitSelectionApplicantSchedulleDetailService;
import com.inkubator.hrm.service.RecruitSelectionApplicantSchedulleService;
import com.inkubator.hrm.service.RecruitmenSelectionSeriesDetailService;
import com.inkubator.hrm.service.RecruitmenSelectionSeriesService;
import com.inkubator.hrm.web.model.RecruitScheduleSettingModel;
import com.inkubator.hrm.web.model.RecruitSelectionApplicantScheduleDetailViewModel;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
@Component(value = "recruitmentScheduleSettingFormController")
@Lazy
public class RecruitmentScheduleSettingFormController implements Serializable{

	org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(getClass());
	
	@Autowired
	private EmpDataService empDataService;
	@Autowired
	private RecruitHireApplyService recruitHireApplyService;
	@Autowired
	private RecruitMppApplyService recruitMppApplyService;
	@Autowired
	private RecruitMppApplyDetailService recruitMppApplyDetailService;
	@Autowired
	private RecruitmenSelectionSeriesService recruitSelectionSeriesService;
	@Autowired
	private RecruitmenSelectionSeriesDetailService recruitmenSelectionSeriesDetailService;
	@Autowired
	private RecruitSelectionApplicantInitialService recruitSelectionApplicantInitialService;
	@Autowired
	private RecruitSelectionApplicantSchedulleService recruitSelectionApplicantSchedulleService;
	@Autowired
	private RecruitSelectionApplicantSchedulleDetailService recruitSelectionApplicantSchedulleDetailService;
	@Autowired
	private RecruitSelectionApplicantSchedulleDetailRealizationService recruitSelectionApplicantSchedulleDetailRealizationService;
	
	public void initRecruitmentScheduleSettingProcessFlow(RequestContext context){
		try {			
			
			//binding RecruitmenSelectionSeries
			List<RecruitmenSelectionSeries> listRecruitSelectionSeries = recruitSelectionSeriesService.getAllData();
			context.getFlowScope().put("listRecruitSelectionSeries", listRecruitSelectionSeries);
			
			//binding value to model
			RecruitScheduleSettingModel model = new RecruitScheduleSettingModel();
			Long id = context.getFlowScope().getLong("id");	
			if(id != null){	
				RecruitHireApply recruitHireApply = recruitHireApplyService.getEntityByPkWithDetail(id);
				model = getModelFromEntity(recruitHireApply);
			}  
            
			context.getFlowScope().put("recruitScheduleSettingModel", model);
			
		} catch (Exception ex) {
			LOGGER.error("Error", ex);
		}
	}
	
	public RecruitScheduleSettingModel setRecruitSelectionScheduleDetail(RequestContext context){
		
		RecruitScheduleSettingModel model = (RecruitScheduleSettingModel) context.getFlowScope().get("recruitScheduleSettingModel");
		
		try {
			
			RecruitmenSelectionSeries recruitmenSelectionSeries = recruitSelectionSeriesService.getEntiyByPK(model.getSelectionSeriesId());
			model.setSelectionSeriesName(recruitmenSelectionSeries.getName());
			Map<Long, List<RecruitSelectionApplicantScheduleDetailViewModel>> mapSelectionScheduleDetail = new HashMap<Long, List<RecruitSelectionApplicantScheduleDetailViewModel>>();
			
			//Jika jadwalnya sudah pernah di setup, maka generate view modelnya dari table  RecruitSelectionApplicantSchedulleDetail
			if(model.getIsAlreadyHaveSelectionScheduleSeries()){
				
				List<RecruitmenSelectionSeriesDetail> listRecruitmenSelectionSeriesDetails = recruitmenSelectionSeriesDetailService.getListBySelectionSeriesId(model.getSelectionSeriesId());
				model.setListSelectionSeriesDetails(listRecruitmenSelectionSeriesDetails);
				
				List<RecruitSelectionApplicantSchedulleDetail> listScheduleDetail = recruitSelectionApplicantSchedulleDetailService.getListByRecruitSelectionApplicantSchedulleId(model.getRecruitSelectionApplicantSchedulleId());
				Group<RecruitSelectionApplicantSchedulleDetail> groupRecruitSchedulleDetailPerSelectionType = Lambda.group(listScheduleDetail, Lambda.by(Lambda.on(RecruitSelectionApplicantSchedulleDetail.class).getSelectionType().getId()));
				
				for(String key : groupRecruitSchedulleDetailPerSelectionType.keySet()){
					
					List<RecruitSelectionApplicantScheduleDetailViewModel> listScheduleViewModelPerSelectionType = new ArrayList<RecruitSelectionApplicantScheduleDetailViewModel>();
					List<RecruitSelectionApplicantSchedulleDetail> listScheduleDetailPerSelectionType = groupRecruitSchedulleDetailPerSelectionType.find(key);
					
					for(RecruitSelectionApplicantSchedulleDetail schedulleDetail : listScheduleDetailPerSelectionType){
						RecruitSelectionApplicantScheduleDetailViewModel scheduleViewModel = convertRecruitSchedulleDetailToScheduleDetailViewModel(schedulleDetail);
						listScheduleViewModelPerSelectionType.add(scheduleViewModel);
					}
					
					mapSelectionScheduleDetail.put(Long.valueOf(key), listScheduleViewModelPerSelectionType);
				}
				
			}else{
				
				// Jika jadwal belum pernah di setup (masih kosong), generate dari RecruitSelectionApplicantInitial
				List<RecruitmenSelectionSeriesDetail> listRecruitmenSelectionSeriesDetails = recruitmenSelectionSeriesDetailService.getListBySelectionSeriesId(model.getSelectionSeriesId());
				model.setListSelectionSeriesDetails(listRecruitmenSelectionSeriesDetails);
				List<RecruitSelectionApplicantInitial> listRecruitSelectionApplicantInitials = recruitSelectionApplicantInitialService.getListByRecruitHireApplyId(model.getRecruitHireApplyId());
				
				for(RecruitmenSelectionSeriesDetail selectionDetail : listRecruitmenSelectionSeriesDetails){
					
					List<RecruitSelectionApplicantScheduleDetailViewModel> listScheduleDetailViewModelPerSelSeriesDetail = new ArrayList<RecruitSelectionApplicantScheduleDetailViewModel>();
					if(listRecruitSelectionApplicantInitials.isEmpty()){
						mapSelectionScheduleDetail.put(selectionDetail.getId().getSelectionTypeId(), listScheduleDetailViewModelPerSelSeriesDetail);
					}else{
						for(RecruitSelectionApplicantInitial applicantInitial : listRecruitSelectionApplicantInitials){
							RecruitSelectionApplicantScheduleDetailViewModel scheduleDetailViewModel = convertApplicantInitialToScheduleDetailViewModel(applicantInitial, selectionDetail.getRecruitSelectionType());
							listScheduleDetailViewModelPerSelSeriesDetail.add(scheduleDetailViewModel);
						}
						mapSelectionScheduleDetail.put(selectionDetail.getId().getSelectionTypeId(), listScheduleDetailViewModelPerSelSeriesDetail);
					}
				}
			}
			
			model.setMapSelectionApplicantSchedule(mapSelectionScheduleDetail);
			
		} catch (Exception ex) {
			LOGGER.error("Error", ex);
		}
		
		return model;
	}
	
	private RecruitSelectionApplicantScheduleDetailViewModel convertApplicantInitialToScheduleDetailViewModel(RecruitSelectionApplicantInitial applicantInitial, RecruitSelectionType selectionType){
		RecruitSelectionApplicantScheduleDetailViewModel scheduleDetailModel = new RecruitSelectionApplicantScheduleDetailViewModel();
		scheduleDetailModel.setIsHaveBeenRealized(Boolean.FALSE);
		scheduleDetailModel.setApplicantId(applicantInitial.getRecruitApplicant().getId());
		scheduleDetailModel.setApplicantName(applicantInitial.getRecruitApplicant().getBioData().getFullName());
		scheduleDetailModel.setSelectionTypeId(selectionType.getId());
		scheduleDetailModel.setSelectionTypeName(selectionType.getName());
		if(applicantInitial.getRecruitApplicant().getCareerCandidate() == HRMConstant.RECRUIT_APPLICANT_CAREER_CANDIDATE_EXTERNAL){
			scheduleDetailModel.setCandidateStatus("Eksternal");
		}else if(applicantInitial.getRecruitApplicant().getCareerCandidate() == HRMConstant.RECRUIT_APPLICANT_CAREER_CANDIDATE_INTERNAL){
			scheduleDetailModel.setCandidateStatus("Internal");
		}
		return scheduleDetailModel;
	}
	
	private RecruitSelectionApplicantScheduleDetailViewModel convertRecruitSchedulleDetailToScheduleDetailViewModel(RecruitSelectionApplicantSchedulleDetail schedulleDetail) throws Exception{
		
		RecruitSelectionApplicantScheduleDetailViewModel scheduleDetailModel = new RecruitSelectionApplicantScheduleDetailViewModel();
		Boolean isHaveBeenRealized = recruitSelectionApplicantSchedulleDetailRealizationService.isSchedulleDetailHaveBeenRealized(schedulleDetail.getId());
		
		scheduleDetailModel.setIsHaveBeenRealized(isHaveBeenRealized);
		scheduleDetailModel.setId(schedulleDetail.getId());
		scheduleDetailModel.setApplicantId(schedulleDetail.getApplicant().getId());
		scheduleDetailModel.setApplicantName(schedulleDetail.getApplicant().getBioData().getFullName());
		scheduleDetailModel.setSelectionTypeId(schedulleDetail.getSelectionType().getId());
		scheduleDetailModel.setSelectionTypeName(schedulleDetail.getSelectionType().getName());
		scheduleDetailModel.setScheduleDate(schedulleDetail.getSchdulleDate());
		scheduleDetailModel.setScheduleStartTime(schedulleDetail.getSchdulleTimeStart());
		scheduleDetailModel.setScheduleEndTime(schedulleDetail.getSchedulleTimeEnd());
		scheduleDetailModel.setRoom(schedulleDetail.getRoom());
		scheduleDetailModel.setNotes(schedulleDetail.getNotes());
		scheduleDetailModel.setEmpDataPic(schedulleDetail.getEmpData());
		
		
		if(schedulleDetail.getApplicant().getCareerCandidate() == HRMConstant.RECRUIT_APPLICANT_CAREER_CANDIDATE_EXTERNAL){
			scheduleDetailModel.setCandidateStatus("Eksternal");
		}else if(schedulleDetail.getApplicant().getCareerCandidate() == HRMConstant.RECRUIT_APPLICANT_CAREER_CANDIDATE_INTERNAL){
			scheduleDetailModel.setCandidateStatus("Internal");
		}
		
		return scheduleDetailModel;
	}
	
	public String doSave(RequestContext context) {
		String message = "error";
		
		RecruitScheduleSettingModel model = (RecruitScheduleSettingModel) context.getFlowScope().get("recruitScheduleSettingModel");
		RecruitSelectionApplicantSchedulle schedule = convertModelToRecruitSelectionApplicantSchedulle(model);
		List<RecruitSelectionApplicantSchedulleDetail> listSchedulleDetail = convertListScheduleViewModelToListScheduleDetail(model);
		
		try {
			
			if(model.getRecruitSelectionApplicantSchedulleId() == null) {
				
				message = recruitSelectionApplicantSchedulleService.saveData(schedule, listSchedulleDetail);
				MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
				context.getFlowScope().put("recruitScheduleSettingModel", model);
				
			} else {
				
				message = recruitSelectionApplicantSchedulleService.updateData(schedule, listSchedulleDetail);
				MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.update_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
				context.getFlowScope().put("recruitScheduleSettingModel", model);
			}
		} catch (BussinessException ex) { 
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
		
		return message;
	}
	
	
	public List<EmpData> doAutoCompleteEmployeeCoordinator(String param){
		List<EmpData> empDatas = new ArrayList<EmpData>();
		try {
			empDatas = empDataService.getAllDataByNameOrNik(StringUtils.stripToEmpty(param));
		} catch (Exception e) {
			LOGGER.error("Error", e);
		}
		return empDatas;
	}
	
	public void doResetRecruitmentScheduleForm(RequestContext context){
		
		RecruitScheduleSettingModel model = (RecruitScheduleSettingModel) context.getFlowScope().get("recruitScheduleSettingModel");
		try{
			
			if(model.getRecruitSelectionApplicantSchedulleId() == null){
				model.setEmpData(null);
				model.setSelectionSeriesId(null);
			}else{
				RecruitSelectionApplicantSchedulle recruitSchedulle = recruitSelectionApplicantSchedulleService.getEntityWithDetailByHireApplyId(model.getRecruitHireApplyId());
				model.setEmpData(recruitSchedulle.getEmpData());
				model.setRecruitSelectionApplicantSchedulleId(recruitSchedulle.getId());
			}
			
		}catch(Exception ex){
			LOGGER.error("Error", ex);
		}
		
		context.getFlowScope().put("recruitScheduleSettingModel", model);
	}
	
	private RecruitScheduleSettingModel getModelFromEntity(RecruitHireApply recruitHireApply) throws Exception{
		
		List<RecruitMppApplyDetail> listMppDetail = recruitMppApplyDetailService.getListByJabatanIdAndMppPeriodId(recruitHireApply.getJabatan().getId(), recruitHireApply.getRecruitMppPeriod().getId());
		
		//bind RecruitSelectionSchedule to model		
		RecruitScheduleSettingModel model = new RecruitScheduleSettingModel();
		
		model.setRecruitHireApplyId(recruitHireApply.getId());
		model.setRecruitHireApplyCode(recruitHireApply.getReqHireCode());
		
		if(!listMppDetail.isEmpty()){
			RecruitMppApplyDetail mppApplyDetail = listMppDetail.get(0);
			model.setRecruitMppApplyId(mppApplyDetail.getRecruitMppApply().getId());
			model.setRecruitMppApplyName(mppApplyDetail.getRecruitMppApply().getRecruitMppApplyName());
		}
		
		model.setTotalRecruitment(recruitHireApply.getCandidateCountRequest());
		model.setJabatanId(recruitHireApply.getJabatan().getId());
		model.setJabatanName(recruitHireApply.getJabatan().getName());
		model.setRecruitMppPeriodId(recruitHireApply.getRecruitMppPeriod().getId());
		model.setStartDateMppPeriod(recruitHireApply.getRecruitMppPeriod().getPeriodeStart());
		model.setEndDateMppPeriod(recruitHireApply.getRecruitMppPeriod().getPeriodeEnd());
				
		Boolean isAlreadyHaveSelectionSchedule = recruitSelectionApplicantSchedulleService.isHireApplyAlreadyHaveSelectionSchedulle(recruitHireApply.getId());
		model.setIsAlreadyHaveSelectionScheduleSeries(isAlreadyHaveSelectionSchedule);
		
		if(isAlreadyHaveSelectionSchedule){
			
			RecruitSelectionApplicantSchedulle recruitSchedulle = recruitSelectionApplicantSchedulleService.getEntityWithDetailByHireApplyId(recruitHireApply.getId());
			model.setSelectionSeriesId(recruitSchedulle.getSelectionSeries().getId());
			model.setSelectionSeriesName((recruitSchedulle.getSelectionSeries().getName()));
			
			model.setEmpData(recruitSchedulle.getEmpData());
			model.setRecruitSelectionApplicantSchedulleId(recruitSchedulle.getId());
			
		}
		
		return model;
	}
	
	private RecruitSelectionApplicantSchedulle convertModelToRecruitSelectionApplicantSchedulle(RecruitScheduleSettingModel model){
		RecruitSelectionApplicantSchedulle schedule = new RecruitSelectionApplicantSchedulle();
		if(model.getRecruitSelectionApplicantSchedulleId() != null){
			schedule.setId(model.getRecruitSelectionApplicantSchedulleId());
		}
		
		schedule.setHireApply(new RecruitHireApply(model.getRecruitHireApplyId()));
		schedule.setEmpData(model.getEmpData());
		schedule.setSelectionSeries(new RecruitmenSelectionSeries(model.getSelectionSeriesId()));
		schedule.setTotalRecrut(model.getTotalRecruitment());
		
		return schedule;
	}
	
	private List<RecruitSelectionApplicantSchedulleDetail> convertListScheduleViewModelToListScheduleDetail(RecruitScheduleSettingModel model){
		List<RecruitSelectionApplicantSchedulleDetail> listScheduleDetail = new ArrayList<RecruitSelectionApplicantSchedulleDetail>();
		List<RecruitmenSelectionSeriesDetail> listSelectionSeriesDetail = model.getListSelectionSeriesDetails();
		Map<Long, List<RecruitSelectionApplicantScheduleDetailViewModel>> mapScheduleDetailViewModel = model.getMapSelectionApplicantSchedule();
		
		for(RecruitmenSelectionSeriesDetail selectionSeriesDetail : listSelectionSeriesDetail){
			List<RecruitSelectionApplicantScheduleDetailViewModel> listScheduleDetailModel = mapScheduleDetailViewModel.get(selectionSeriesDetail.getRecruitSelectionType().getId());
			if(null != listScheduleDetailModel){
				for(RecruitSelectionApplicantScheduleDetailViewModel scheduleDetailModel : listScheduleDetailModel){
					RecruitSelectionApplicantSchedulleDetail scheduleDetail = convertScheduleDetailModelToScheduleDetail(scheduleDetailModel, selectionSeriesDetail);
					listScheduleDetail.add(scheduleDetail);
				}
			}
		}
		
		return listScheduleDetail;
	}
	
	private RecruitSelectionApplicantSchedulleDetail convertScheduleDetailModelToScheduleDetail(RecruitSelectionApplicantScheduleDetailViewModel scheduleDetailModel, RecruitmenSelectionSeriesDetail selectionSeriesDetail){
		
		RecruitSelectionApplicantSchedulleDetail scheduleDetail = new RecruitSelectionApplicantSchedulleDetail();
		if(scheduleDetailModel.getId() != null){
			scheduleDetail.setId(scheduleDetailModel.getId());
		}
		
		scheduleDetail.setApplicant(new RecruitApplicant(scheduleDetailModel.getApplicantId()));
		scheduleDetail.setEmpData(scheduleDetailModel.getEmpDataPic());
		scheduleDetail.setNotes(scheduleDetailModel.getNotes());
		scheduleDetail.setRoom(scheduleDetailModel.getRoom());
		scheduleDetail.setSchdulleDate(scheduleDetailModel.getScheduleDate());
		scheduleDetail.setSchdulleTimeStart(scheduleDetailModel.getScheduleStartTime());
		scheduleDetail.setSchedulleTimeEnd(scheduleDetailModel.getScheduleEndTime());
		scheduleDetail.setSelectionListOrder(selectionSeriesDetail.getListOrder());
		scheduleDetail.setSelectionType(new RecruitSelectionType(selectionSeriesDetail.getRecruitSelectionType().getId()));
		
		return scheduleDetail;
	}
}
