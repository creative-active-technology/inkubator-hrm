package com.inkubator.hrm.web.flow;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.webflow.execution.RequestContext;

import com.inkubator.common.util.DateTimeUtil;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.BusinessTravel;
import com.inkubator.hrm.entity.BusinessTravelComponent;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.TravelComponentCostRate;
import com.inkubator.hrm.entity.TravelType;
import com.inkubator.hrm.entity.TravelZone;
import com.inkubator.hrm.service.BusinessTravelComponentService;
import com.inkubator.hrm.service.BusinessTravelService;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.TravelComponentCostRateService;
import com.inkubator.hrm.service.TravelTypeService;
import com.inkubator.hrm.service.TravelZoneService;
import com.inkubator.hrm.web.model.BusinessTravelModel;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

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
	
	
	public void initBusinessTravelProcessFlow(RequestContext context){
		try {			
			//binding travel zone list
			List<TravelZone> travelZones = travelZoneService.getAllData();
			context.getFlowScope().put("travelZones", travelZones);
			
			//binding travel type list
			List<TravelType> travelTypes = travelTypeService.getAllData();
			context.getFlowScope().put("travelTypes", travelTypes);
			
			//binding value to model
			Long id = context.getFlowScope().getLong("id");	
			BusinessTravelModel model = new BusinessTravelModel();
			if(id != null){			
				BusinessTravel businessTravel = businessTravelService.getEntityByPkWithDetail(id);
				model = bindEntityToModel(businessTravel);
			}
			context.getFlowScope().put("businessTravelModel", model);
			
		} catch (Exception ex) {
			LOGGER.error("Error", ex);
		}
	}
	
	public BusinessTravelModel setBusinessTravelComponents(RequestContext context){
		BusinessTravelModel model = (BusinessTravelModel) context.getFlowScope().get("businessTravelModel");
		Long id = context.getFlowScope().getLong("id");
		
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
			}
		} catch (Exception ex) {
			LOGGER.error("Error", ex);
		}
		
		return model;
	}
	
	public String doSave(RequestContext context) {
		String savingResult = "false";
		
		BusinessTravelModel model = (BusinessTravelModel) context.getFlowScope().get("businessTravelModel");
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
		
		try {
			if(businessTravel.getId() == null) {
				businessTravelService.save(businessTravel, model.getBusinessTravelComponents(), false);
			} else {
				businessTravelService.update(businessTravel, model.getBusinessTravelComponents());
			}
			
			savingResult = "true";
		} catch (BussinessException ex) { //data already exist(duplicate)
            LOGGER.error("Error", ex);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
		
		return savingResult;
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
			model = new BusinessTravelModel();
		} else {
			try {
				BusinessTravel businessTravel = businessTravelService.getEntityByPkWithDetail(model.getId());
				model = bindEntityToModel(businessTravel);
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
	
	private BusinessTravelModel bindEntityToModel(BusinessTravel businessTravel) throws Exception{
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
		
		//get business travel components
		List<BusinessTravelComponent> listBusinessTravelComponent = businessTravelComponentService.getAllDataByBusinessTravelId(businessTravel.getId());
		model.setBusinessTravelComponents(listBusinessTravelComponent);
		
		//calculate total amount
		double totalAmount = calculateTotalAmount(listBusinessTravelComponent);
		model.setTotalAmount(totalAmount);
		
		return model;
	}
}
