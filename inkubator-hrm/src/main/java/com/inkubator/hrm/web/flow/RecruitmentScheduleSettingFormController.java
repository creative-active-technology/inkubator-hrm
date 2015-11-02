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
import com.inkubator.hrm.service.RecruitSelectionApplicantSchedulleDetailService;
import com.inkubator.hrm.service.RecruitSelectionApplicantSchedulleService;
import com.inkubator.hrm.service.RecruitmenSelectionSeriesDetailService;
import com.inkubator.hrm.service.RecruitmenSelectionSeriesService;
import com.inkubator.hrm.web.model.BusinessTravelModel;
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
	
	public void initRecruitmentScheduleSettingProcessFlow(RequestContext context){
		try {			
			
			//binding travel zone list
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
			
			EmpData empCoordinator = empDataService.getEmpDataWithBiodata(model.getEmpCoordinatorId());
			model.setEmpCoordinatorFullName(empCoordinator.getBioData().getFullName());
			model.setEmpCoordinatorNik(empCoordinator.getNik());
			
			RecruitmenSelectionSeries recruitmenSelectionSeries = recruitSelectionSeriesService.getEntiyByPK(model.getSelectionSeriesId());
			model.setSelectionSeriesName(recruitmenSelectionSeries.getName());
			
			List<RecruitmenSelectionSeriesDetail> listRecruitmenSelectionSeriesDetails = recruitmenSelectionSeriesDetailService.getListBySelectionSeriesId(model.getSelectionSeriesId());
			model.setListSelectionSeriesDetails(listRecruitmenSelectionSeriesDetails);
			List<RecruitSelectionApplicantInitial> listRecruitSelectionApplicantInitials = recruitSelectionApplicantInitialService.getListByRecruitHireApplyId(model.getRecruitHireApplyId());
			Map<Long, List<RecruitSelectionApplicantScheduleDetailViewModel>> mapSelectionScheduleDetail = new HashMap<Long, List<RecruitSelectionApplicantScheduleDetailViewModel>>();
			
			for(RecruitmenSelectionSeriesDetail selectionDetail : listRecruitmenSelectionSeriesDetails){
				
				List<RecruitSelectionApplicantScheduleDetailViewModel> listScheduleDetailPerSelSeriesDetail = new ArrayList<RecruitSelectionApplicantScheduleDetailViewModel>();
				if(listRecruitSelectionApplicantInitials.isEmpty()){
					
					mapSelectionScheduleDetail.put(selectionDetail.getId().getSelectionTypeId(), listScheduleDetailPerSelSeriesDetail);
					
				}else{
					
					for(RecruitSelectionApplicantInitial applicantInitial : listRecruitSelectionApplicantInitials){
						RecruitSelectionApplicantScheduleDetailViewModel scheduleDetailViewModel = convertApplicantInitialToScheduleDetailViewModel(applicantInitial, selectionDetail.getRecruitSelectionType());
						listScheduleDetailPerSelSeriesDetail.add(scheduleDetailViewModel);
					}
					
					mapSelectionScheduleDetail.put(selectionDetail.getId().getSelectionTypeId(), listScheduleDetailPerSelSeriesDetail);
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
		scheduleDetailModel.setApplicantId(applicantInitial.getRecruitApplicant().getId());
		scheduleDetailModel.setApplicantName(applicantInitial.getRecruitApplicant().getBioData().getFullName());
		scheduleDetailModel.setSelectionTypeId(selectionType.getId());
		scheduleDetailModel.setSelectionTypeName(selectionType.getName());
		scheduleDetailModel.setCandidateStatus("Eksternal");
		return scheduleDetailModel;
	}
	
	
	
	public String doSave(RequestContext context) {
		String message = "error";
		
		RecruitScheduleSettingModel model = (RecruitScheduleSettingModel) context.getFlowScope().get("recruitScheduleSettingModel");
		RecruitSelectionApplicantSchedulle schedule = convertModelToRecruitSelectionApplicantSchedulle(model);
		List<RecruitSelectionApplicantSchedulleDetail> listSchedulleDetail = convertListScheduleViewModelToListScheduleDetail(model);
		
		try {
			
			if(model.getId() == null) {
				
				message = recruitSelectionApplicantSchedulleService.saveData(schedule, listSchedulleDetail);
				MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
				context.getFlowScope().put("recruitScheduleSettingModel", model);
				
			} /*else {
				businessTravelService.update(businessTravel, model.getBusinessTravelComponents());
				message = "success_without_approval";
			}*/
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
	
	public void doResetRecruitmentSchduleForm(RequestContext context){
		BusinessTravelModel model = (BusinessTravelModel) context.getFlowScope().get("businessTravelModel");
		/*if(model.getId() == null){
			if(model.getIsAdministator()){
				model.setEmpData(null);
			}
			model.setDestination(null);
			model.setProposeDate(null);
			model.setTravelZoneId(null);
			model.setTravelTypeId(null);
			model.setStart(null);
			model.setEnd(null);
			model.setDescription(null);
			model.setGolonganJabatanName(null);
			model.setTotalAmount(null);
			model.getBusinessTravelComponents().clear();
		} else {
			try {
				BusinessTravel businessTravel = businessTravelService.getEntityByPkWithDetail(model.getId());
				List<BusinessTravelComponent> listBusinessTravelComponent = businessTravelComponentService.getAllDataByBusinessTravelId(businessTravel.getId());
				model = getModelFromEntity(businessTravel, listBusinessTravelComponent);
			} catch (Exception e) {
				LOGGER.error("Error", e);
			}
		}*/
		
		context.getFlowScope().put("businessTravelModel", model);
	}
	
	private RecruitScheduleSettingModel getModelFromEntity(RecruitHireApply recruitHireApply) throws Exception{
		
		List<RecruitMppApplyDetail> listMppDetail = recruitMppApplyDetailService.getListByJabatanIdAndMppPeriodId(recruitHireApply.getJabatan().getId(), recruitHireApply.getRecruitMppPeriod().getId());
		
		//bind business travel to model		
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
	
		return model;
	}
	
	private RecruitSelectionApplicantSchedulle convertModelToRecruitSelectionApplicantSchedulle(RecruitScheduleSettingModel model){
		RecruitSelectionApplicantSchedulle schedule = new RecruitSelectionApplicantSchedulle();
		if(model.getId() != null){
			schedule.setId(model.getId());
		}
		
		schedule.setHireApply(new RecruitHireApply(model.getRecruitHireApplyId()));
		schedule.setEmpData(new EmpData(model.getEmpCoordinatorId()));
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
		scheduleDetail.setEmpData(new EmpData(scheduleDetailModel.getEmpDataPicId()));
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
