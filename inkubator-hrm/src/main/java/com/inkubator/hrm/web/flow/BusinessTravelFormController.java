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
import com.inkubator.hrm.entity.TransactionCodefication;
import com.inkubator.hrm.entity.TravelComponentCostRate;
import com.inkubator.hrm.entity.TravelType;
import com.inkubator.hrm.entity.TravelZone;
import com.inkubator.hrm.json.util.JsonUtil;
import com.inkubator.hrm.service.ApprovalActivityService;
import com.inkubator.hrm.service.BusinessTravelComponentService;
import com.inkubator.hrm.service.BusinessTravelService;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.TransactionCodeficationService;
import com.inkubator.hrm.service.TravelComponentCostRateService;
import com.inkubator.hrm.service.TravelTypeService;
import com.inkubator.hrm.service.TravelZoneService;
import com.inkubator.hrm.util.HrmUserInfoUtil;
import com.inkubator.hrm.util.KodefikasiUtil;
import com.inkubator.hrm.web.model.BusinessTravelModel;
import com.inkubator.securitycore.util.UserInfoUtil;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

import ch.lambdaj.Lambda;

/**
 *
 * @author rizkykojek
 */
@Component(value = "businessTravelFormController")
@Lazy
public class BusinessTravelFormController implements Serializable{

	org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(getClass());
	@Autowired
	private BusinessTravelService businessTravelService;
	@Autowired
	private BusinessTravelComponentService businessTravelComponentService;
	@Autowired
	private TravelComponentCostRateService travelComponentCostRateService;
	@Autowired
	private EmpDataService empDataService;
	@Autowired 
	private TravelZoneService travelZoneService;
	@Autowired 
	private TravelTypeService travelTypeService;
	@Autowired
	private ApprovalActivityService approvalActivityService;
	@Autowired
	private TransactionCodeficationService transactionCodeficationService;
	
	
	public void initBusinessTravelProcessFlow(RequestContext context){
		try {			
			//binding travel zone list
			List<TravelZone> travelZones = travelZoneService.getAllData();
			context.getFlowScope().put("travelZones", travelZones);
			
			//binding travel type list
			List<TravelType> travelTypes = travelTypeService.getAllData();
			context.getFlowScope().put("travelTypes", travelTypes);
			
			//binding value to model
			Boolean isAdministator = Lambda.exists(UserInfoUtil.getRoles(), Matchers.containsString(HRMConstant.ADMINISTRATOR_ROLE));
			BusinessTravelModel model = new BusinessTravelModel();
			model.setIsAdministator(isAdministator);
			model.setIsRevised(Boolean.FALSE);
			
			Long id = context.getFlowScope().getLong("id");	
			Long appActivityId = context.getFlowScope().getLong("activity");
			if(id != null){	
				BusinessTravel businessTravel = businessTravelService.getEntityByPkWithDetail(id);
				List<BusinessTravelComponent> listBusinessTravelComponent = businessTravelComponentService.getAllDataByBusinessTravelId(businessTravel.getId());
				model = getModelFromEntity(businessTravel, listBusinessTravelComponent);
				
			} else if(appActivityId != null){
				//jika datangnya dari proses approval, artinya user akan melakukan revisi data yg masih dalam bentuk json 
                ApprovalActivity currentActivity = approvalActivityService.getEntityByPkWithDetail(appActivityId);
                model = this.getModelFromJson(currentActivity.getPendingData());
                ApprovalActivity askingRevisedActivity = approvalActivityService.getEntityByActivityNumberAndSequence(currentActivity.getActivityNumber(),
                        currentActivity.getSequence() - 1);
                
                model.setIsRevised(Boolean.TRUE);
                model.setCurrentActivity(currentActivity);
                model.setAskingRevisedActivity(askingRevisedActivity);
                
			} else {
				//set default random business travel number
				TransactionCodefication transactionCodefication = transactionCodeficationService.getEntityByModulCode(HRMConstant.BUSINESS_TRAVEL_CODE);
				if (!ObjectUtils.equals(transactionCodefication, null)) {
					model.setBusinessTravelNo(KodefikasiUtil.getKodefikasiOnlyPattern(transactionCodefication.getCode()));
				}
				
				if(!isAdministator) { 
					//jika bukan administrator, langsung di set empData berdasarkan yang login
					model.setEmpData(HrmUserInfoUtil.getEmpData());
				}
			}		
            
			context.getFlowScope().put("businessTravelModel", model);
			
		} catch (Exception ex) {
			LOGGER.error("Error", ex);
		}
	}
	
	public BusinessTravelModel setBusinessTravelComponents(RequestContext context){
		BusinessTravelModel model = (BusinessTravelModel) context.getFlowScope().get("businessTravelModel");
		
		try {
			/**
			 * bind list of business travel component to model.list 
			 * set hanya jika business travel components size == 0, artinya jika size > 0 maka value list nya sudah terdapat di flowSession tidak perlu getList lagi
			 * */
			List<BusinessTravelComponent> listBusinessTravelComponent = new ArrayList<BusinessTravelComponent>();
			if(model.getBusinessTravelComponents().size() == 0){
				//get default quantity based on range start and end date
				int defaultQuantity = DateTimeUtil.getTotalDay(model.getStart(), model.getEnd());
				
				//convert from TravelComponentCostRate to BusinessTravelComponent
				List<TravelComponentCostRate> travelComponentCostRates = travelComponentCostRateService.getAllDataByEmpDataIdAndTravelZoneId(model.getEmpData().getId(), model.getTravelZoneId());
				for(TravelComponentCostRate travelComponentCostRate : travelComponentCostRates){
					BusinessTravelComponent businessTravelComponent = new BusinessTravelComponent();
					businessTravelComponent.setTravelComponent(travelComponentCostRate.getTravelComponent());
					businessTravelComponent.setCostCenter(travelComponentCostRate.getCostCenter());
					businessTravelComponent.setQuantity(defaultQuantity);
					businessTravelComponent.setEarnedPerQuantity(travelComponentCostRate.getDefaultRate());
					businessTravelComponent.setDefaultRate(travelComponentCostRate.getDefaultRate());
						
					//add to list
					listBusinessTravelComponent.add(businessTravelComponent);
				}					
				model.setBusinessTravelComponents(listBusinessTravelComponent);
				
				//calculate total amount
				double totalAmount = calculateTotalAmount(listBusinessTravelComponent);
				model.setTotalAmount(totalAmount);
				
				//set employee data with detail
				EmpData empData = empDataService.getByEmpIdWithDetail(model.getEmpData().getId());
				model.setEmpData(empData);
			}
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
		
		BusinessTravelModel model = (BusinessTravelModel) context.getFlowScope().get("businessTravelModel");
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
        }
		
		
		return message;
	}
	
	public String doRevised(RequestContext context) {
		String message = "error";
		
		BusinessTravelModel model = (BusinessTravelModel) context.getFlowScope().get("businessTravelModel");
		BusinessTravel businessTravel = this.getEntityFromModel(model);
		
		try {
			if(businessTravel.getId() == null) {
				message = businessTravelService.saveWithRevised(businessTravel, model.getBusinessTravelComponents(), model.getCurrentActivity().getId());
				
				if (StringUtils.equals(message, "success_need_approval")) {
					MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully_and_requires_approval", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
				}else if (StringUtils.equals(message, "success_without_approval")) {
					MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
				}
			} else {
				businessTravelService.update(businessTravel, model.getBusinessTravelComponents());
				message = "success_without_approval";
			}
		} catch (BussinessException ex) { 
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
		
		return message;
    }
	
	public List<EmpData> doAutoCompletEmployee(String param){
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
	
	public void doResetBusinessTravelForm(RequestContext context){
		BusinessTravelModel model = (BusinessTravelModel) context.getFlowScope().get("businessTravelModel");
		if(model.getId() == null){
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
		}
		
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
	
	private BusinessTravelModel getModelFromEntity(BusinessTravel businessTravel, List<BusinessTravelComponent> listBusinessTravelComponent) throws Exception{
		//bind business travel to model		
		BusinessTravelModel model = new BusinessTravelModel();
		model.setId(businessTravel.getId());
		model.setBusinessTravelNo(businessTravel.getBusinessTravelNo());
		model.setEmpData(businessTravel.getEmpData());
		model.setDestination(businessTravel.getDestination());
		model.setProposeDate(businessTravel.getProposeDate());
		model.setTravelZoneId(businessTravel.getTravelZone().getId());
		model.setTravelTypeId(businessTravel.getTravelType().getId());
		model.setStart(businessTravel.getStartDate());
		model.setEnd(businessTravel.getEndDate());
		model.setDescription(businessTravel.getDescription());
		
		//set business travel components		
		model.setBusinessTravelComponents(listBusinessTravelComponent);
		
		//calculate total amount
		double totalAmount = calculateTotalAmount(listBusinessTravelComponent);
		model.setTotalAmount(totalAmount);
		
		return model;
	}
	
	private BusinessTravelModel getModelFromJson(String json) throws Exception{
		Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
		JsonObject jsonObject =  gson.fromJson(json, JsonObject.class);
		
		List<BusinessTravelComponent> businessTravelComponents = gson.fromJson(jsonObject.get("businessTravelComponents"), new TypeToken<List<BusinessTravelComponent>>(){}.getType());
		BusinessTravel businessTravel = gson.fromJson(json, BusinessTravel.class);
		EmpData empData = empDataService.getByEmpIdWithDetail(businessTravel.getEmpData().getId());
		businessTravel.setEmpData(empData);
		
		return this.getModelFromEntity(businessTravel, businessTravelComponents);
	}
	
	private BusinessTravel getEntityFromModel(BusinessTravelModel model){
		BusinessTravel businessTravel = new BusinessTravel();
		businessTravel.setId(model.getId());
		businessTravel.setBusinessTravelNo(model.getBusinessTravelNo());
		businessTravel.setDescription(model.getDescription());
		businessTravel.setEmpData(new EmpData(model.getEmpData().getId()));
		businessTravel.setProposeDate(model.getProposeDate());
		businessTravel.setStartDate(model.getStart());
		businessTravel.setEndDate(model.getEnd());
		businessTravel.setTravelType(new TravelType(model.getTravelTypeId()));
		businessTravel.setTravelZone(new TravelZone(model.getTravelZoneId()));
		businessTravel.setDestination(model.getDestination());
		
		return businessTravel;
	}
}
