/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.workingtime;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.inkubator.hrm.entity.ApprovalActivity;
import com.inkubator.hrm.entity.BusinessTravel;
import com.inkubator.hrm.entity.BusinessTravelComponent;
import com.inkubator.hrm.service.ApprovalActivityService;
import com.inkubator.hrm.service.BusinessTravelComponentService;
import com.inkubator.hrm.service.BusinessTravelService;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "businessTravelApprovalFormController")
@ViewScoped
public class BusinessTravelApprovalFormController extends BaseController {

	private Double totalAmount = 0.0;
    private BusinessTravel selectedBusinessTravel;
    private List<BusinessTravelComponent> businessTravelComponents;
    @ManagedProperty(value = "#{businessTravelService}")
    private BusinessTravelService businessTravelService;
    @ManagedProperty(value = "#{approvalActivityService}")
    private ApprovalActivityService approvalActivityService;

    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            String id = FacesUtil.getRequestParameter("execution");
            ApprovalActivity appActivity = approvalActivityService.getEntiyByPK(Long.parseLong(id.substring(1)));
            Gson gson = businessTravelService.getGsonBuilder().create();
			JsonObject jsonObject =  gson.fromJson(appActivity.getPendingData(), JsonObject.class);
			businessTravelComponents = gson.fromJson(jsonObject.get("businessTravelComponents"), new TypeToken<List<BusinessTravelComponent>>(){}.getType());
			selectedBusinessTravel = gson.fromJson(appActivity.getPendingData(), BusinessTravel.class);
            for(BusinessTravelComponent btc :businessTravelComponents){
            	totalAmount = totalAmount + btc.getPayByAmount();
            }
        } catch (Exception ex) {
            LOGGER.error("Error", ex);

        }
    }

    @PreDestroy
    public void cleanAndExit() {
    	businessTravelComponents =null;
        selectedBusinessTravel = null;
        businessTravelService = null;
        businessTravelComponents = null;
        totalAmount = null;
        approvalActivityService = null;
    }   

	public BusinessTravel getSelectedBusinessTravel() {
		return selectedBusinessTravel;
	}

	public void setSelectedBusinessTravel(BusinessTravel selectedBusinessTravel) {
		this.selectedBusinessTravel = selectedBusinessTravel;
	}

	public void setBusinessTravelService(BusinessTravelService businessTravelService) {
		this.businessTravelService = businessTravelService;
	}

	public List<BusinessTravelComponent> getBusinessTravelComponents() {
		return businessTravelComponents;
	}

	public void setBusinessTravelComponents(List<BusinessTravelComponent> businessTravelComponents) {
		this.businessTravelComponents = businessTravelComponents;
	}

    public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	public void setApprovalActivityService(
			ApprovalActivityService approvalActivityService) {
		this.approvalActivityService = approvalActivityService;
	}

	public String doBack() {
        return "/protected/home.htm";
    }

	public void doApproved() {
    	
    }
	
	public void doRejected() {
    	
    }

}
