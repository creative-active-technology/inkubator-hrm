package com.inkubator.hrm.web.personalia;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.webflow.execution.RequestContext;

import com.inkubator.hrm.entity.BusinessTravel;
import com.inkubator.hrm.entity.BusinessTravelComponent;
import com.inkubator.hrm.entity.TravelComponentCostRate;
import com.inkubator.hrm.service.BusinessTravelComponentService;
import com.inkubator.hrm.service.BusinessTravelService;
import com.inkubator.hrm.service.TravelComponentCostRateService;
import com.inkubator.hrm.web.model.BusinessTravelModel;

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
	
	public BusinessTravelModel initBusinessTravelProcessFlow(RequestContext context){
		BusinessTravelModel model = new BusinessTravelModel();
		Long id = context.getFlowScope().getLong("id");
		
		//binding value to model
		if(id != null){
			try {
				//bind business travel to model
				BusinessTravel businessTravel = businessTravelService.getEntiyByPK(id);
				model.setBusinessTravelNo(businessTravel.getBusinessTravelNo());
				model.setEmpDataId(businessTravel.getEmpData().getId());
				model.setEmpDataName(businessTravel.getEmpData().getNikWithFullName());
				model.setDestination(businessTravel.getDestination());
				model.setProposeDate(businessTravel.getProposeDate());
				model.setTravelZoneId(businessTravel.getTravelZone().getId());
				model.setTravelTypeId(businessTravel.getTravelType().getId());
				model.setStart(businessTravel.getStartDate());
				model.setEnd(businessTravel.getEndDate());
				model.setDescription(businessTravel.getDescription());
				
			} catch (Exception ex) {
				LOGGER.error("Error", ex);
			}
		}
		return model;
	}
	
	public BusinessTravelModel setBusinessTravelComponents(RequestContext context){
		BusinessTravelModel model = (BusinessTravelModel) context.getFlowScope().get("businessTravelModel");
		Long id = context.getFlowScope().getLong("id");
		
		try {
			/**
			 * bind list of business travel component to model.list 
			 * set hanya jika business travel compoenents size ==0, artinya jika > 0 maka sudah di set di flowSession model
			 * */
			List<BusinessTravelComponent> listBusinessTravelComponent = new ArrayList<BusinessTravelComponent>();
			if(model.getBusinessTravelComponents().size() == 0){
				if(id == null){				
					List<TravelComponentCostRate> travelComponentCostRates = travelComponentCostRateService.getAllDataByEmpDataIdAndTravelZoneId(model.getEmpDataId(), model.getTravelZoneId());
					for(TravelComponentCostRate travelComponentCostRate : travelComponentCostRates){
						BusinessTravelComponent businessTravelComponent = new BusinessTravelComponent();
						businessTravelComponent.setTravelComponent(travelComponentCostRate.getTravelComponent());
						businessTravelComponent.setCostCenter(travelComponentCostRate.getCostCenter());
						businessTravelComponent.setQuantity(0);
						businessTravelComponent.setEarnedPerQuantity(travelComponentCostRate.getDefaultRate());
						
						//add to list
						listBusinessTravelComponent.add(businessTravelComponent);
					}
					
					model.setBusinessTravelComponents(listBusinessTravelComponent);
					
				} else {			
					listBusinessTravelComponent = businessTravelComponentService.getAllDataByBusinessTravelId(id);
					model.setBusinessTravelComponents(listBusinessTravelComponent);
					
				}
			}
		} catch (Exception ex) {
			LOGGER.error("Error", ex);
		}
		
		return model;
	}
	
	public Boolean doSave(RequestContext context) {
		Boolean savingResult = Boolean.TRUE;
		
		return savingResult;
	}
}
