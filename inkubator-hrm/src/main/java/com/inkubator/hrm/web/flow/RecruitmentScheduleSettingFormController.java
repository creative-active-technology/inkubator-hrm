package com.inkubator.hrm.web.flow;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.hamcrest.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.webflow.execution.RequestContext;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.inkubator.common.util.DateTimeUtil;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.ApprovalActivity;
import com.inkubator.hrm.entity.BusinessTravel;
import com.inkubator.hrm.entity.BusinessTravelComponent;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.RecruitHireApply;
import com.inkubator.hrm.entity.RecruitMppApplyDetail;
import com.inkubator.hrm.entity.RecruitmenSelectionSeries;
import com.inkubator.hrm.entity.RecruitmenSelectionSeriesDetail;
import com.inkubator.hrm.entity.TransactionCodefication;
import com.inkubator.hrm.entity.TravelComponentCostRate;
import com.inkubator.hrm.entity.TravelType;
import com.inkubator.hrm.entity.TravelZone;
import com.inkubator.hrm.json.util.JsonUtil;
import com.inkubator.hrm.service.ApprovalActivityService;
import com.inkubator.hrm.service.BusinessTravelComponentService;
import com.inkubator.hrm.service.BusinessTravelService;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.RecruitHireApplyService;
import com.inkubator.hrm.service.RecruitMppApplyDetailService;
import com.inkubator.hrm.service.RecruitMppApplyService;
import com.inkubator.hrm.service.RecruitmenSelectionSeriesDetailService;
import com.inkubator.hrm.service.RecruitmenSelectionSeriesService;
import com.inkubator.hrm.service.TransactionCodeficationService;
import com.inkubator.hrm.service.TravelComponentCostRateService;
import com.inkubator.hrm.service.TravelTypeService;
import com.inkubator.hrm.service.TravelZoneService;
import com.inkubator.hrm.util.HrmUserInfoUtil;
import com.inkubator.hrm.util.KodefikasiUtil;
import com.inkubator.hrm.web.model.BusinessTravelModel;
import com.inkubator.hrm.web.model.RecruitScheduleSettingModel;
import com.inkubator.securitycore.util.UserInfoUtil;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

import ch.lambdaj.Lambda;

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
			
		} catch (Exception ex) {
			LOGGER.error("Error", ex);
		}
		
		return model;
	}
	
	public void doCalculateQuantity(RequestContext context){
		BusinessTravelModel model = (BusinessTravelModel) context.getFlowScope().get("businessTravelModel");
		if(model.getBusinessTravelComponents().size() > 0){
			try {
				//get default quantity based on range start and end date
				int defaultQuantity = DateTimeUtil.getTotalDay(model.getStart(), model.getEnd());
				
				//re-set quantity based on default value
				List<BusinessTravelComponent> businessTravelComponents = model.getBusinessTravelComponents();
				Lambda.forEach(businessTravelComponents).setQuantity(defaultQuantity);
				model.setBusinessTravelComponents(businessTravelComponents);
				
				//re-calculate total amount
				double totalAmount = calculateTotalAmount(businessTravelComponents);
				model.setTotalAmount(totalAmount);
				
				context.getFlowScope().put("businessTravelModel", model);
			} catch (Exception ex) {
				LOGGER.error("Error", ex);
			}
		}
	}
	
	public String doSave(RequestContext context) {
		String message = "error";
		
		/*BusinessTravelModel model = (BusinessTravelModel) context.getFlowScope().get("businessTravelModel");
		BusinessTravel businessTravel = this.getEntityFromModel(model);
		
		try {
			if(businessTravel.getId() == null) {
				message = businessTravelService.saveWithApproval(businessTravel, model.getBusinessTravelComponents());
				
				if (StringUtils.equals(message, "success_need_approval")) {
					MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully_and_requires_approval", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
				}else if (StringUtils.equals(message, "success_without_approval")) {
					MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
					
					//set id to modelFlow
					model.setId(businessTravel.getId());
					context.getFlowScope().put("businessTravelModel", model);
				}
			} else {
				businessTravelService.update(businessTravel, model.getBusinessTravelComponents());
				message = "success_without_approval";
			}
		} catch (BussinessException ex) { 
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }*/
		
		
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
	
	public void doResetTravelComponentList(RequestContext context){
		BusinessTravelModel model = (BusinessTravelModel) context.getFlowScope().get("businessTravelModel");
		model.getBusinessTravelComponents().clear();
		context.getFlowScope().put("businessTravelModel", model);
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
	
	public void doResetBusinessTravelComponentForm(RequestContext context){
		BusinessTravelModel model = (BusinessTravelModel) context.getFlowScope().get("businessTravelModel");
		try {
			//set travel components list of quantity and earnedPerQuantity to default
			double totalAmount = 0;
			int defaultQuantity = DateTimeUtil.getTotalDay(model.getStart(), model.getEnd());
			List<BusinessTravelComponent> listBusinessTravelComponent = new ArrayList<BusinessTravelComponent>();
			for(BusinessTravelComponent btc : model.getBusinessTravelComponents()){
				btc.setQuantity(defaultQuantity);
				btc.setEarnedPerQuantity(btc.getDefaultRate());
				totalAmount = totalAmount + btc.getPayByAmount();
				listBusinessTravelComponent.add(btc);
			}
			model.setTotalAmount(totalAmount);
			model.setBusinessTravelComponents(listBusinessTravelComponent);
		} catch (Exception e) {
			LOGGER.error("Error", e);
		}
		
		context.getFlowScope().put("businessTravelModel", model);
	}
	
	public void doAdjustPayByAmount(RequestContext context){
		BusinessTravelModel model = (BusinessTravelModel) context.getFlowScope().get("businessTravelModel");
		double totalAmount = calculateTotalAmount(model.getBusinessTravelComponents());
		model.setTotalAmount(totalAmount);
		context.getFlowScope().put("businessTravelModel", model);
	}
	
	private double calculateTotalAmount(List<BusinessTravelComponent> businessTravelComponents){
		double totalAmount = 0;
		for(BusinessTravelComponent btc: businessTravelComponents){
			totalAmount = totalAmount + btc.getPayByAmount();
		}
		return totalAmount;
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
	
	
}
