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

import com.inkubator.hrm.entity.BusinessTravel;
import com.inkubator.hrm.entity.BusinessTravelComponent;
import com.inkubator.hrm.service.BusinessTravelComponentService;
import com.inkubator.hrm.service.BusinessTravelService;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "businessTravelDetailController")
@ViewScoped
public class BusinessTravelDetailController extends BaseController {

	private Double totalAmount = 0.0;
    private BusinessTravel selectedBusinessTravel;
    private List<BusinessTravelComponent> businessTravelComponents;
    @ManagedProperty(value = "#{businessTravelService}")
    private BusinessTravelService businessTravelService;
    @ManagedProperty(value = "#{businessTravelComponentService}")
    private BusinessTravelComponentService businessTravelComponentService;

    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            String id = FacesUtil.getRequestParameter("execution");
            selectedBusinessTravel = businessTravelService.getEntityByBusinessTravelNoWithDetail(id.substring(1));
            businessTravelComponents = businessTravelComponentService.getAllDataByBusinessTravelId(selectedBusinessTravel.getId());
            for(BusinessTravelComponent btc :businessTravelComponents){
            	totalAmount = totalAmount + btc.getEarnedPerQuantity();
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
        businessTravelComponentService = null;
        totalAmount = null;
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

	public void setBusinessTravelComponentService(
			BusinessTravelComponentService businessTravelComponentService) {
		this.businessTravelComponentService = businessTravelComponentService;
	}

	public List<BusinessTravelComponent> getBusinessTravelComponents() {
		return businessTravelComponents;
	}

	public void setBusinessTravelComponents(List<BusinessTravelComponent> businessTravelComponents) {
		this.businessTravelComponents = businessTravelComponents;
	}

	public String doBack() {
        return "/protected/personalia/business_travel_view.htm?faces-redirect=true";
    }

    public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public void doUpdate() {
    	try {
            ExternalContext red = FacesUtil.getExternalContext();
            red.redirect(red.getRequestContextPath() + "/flow-protected/business_travel?id=" + selectedBusinessTravel.getId());
        } catch (IOException ex) {
          LOGGER.error("Erorr", ex);
        }
    }

}
