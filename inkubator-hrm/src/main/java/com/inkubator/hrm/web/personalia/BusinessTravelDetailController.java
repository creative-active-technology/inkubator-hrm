/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.personalia;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;

import org.apache.commons.lang.StringUtils;

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
@ManagedBean(name = "businessTravelDetailController")
@ViewScoped
public class BusinessTravelDetailController extends BaseController {

	private Double totalAmount = 0.0;
    private BusinessTravel selectedBusinessTravel;
    private ApprovalActivity selectedApprovalActivity;
    private List<BusinessTravelComponent> businessTravelComponents;
    @ManagedProperty(value = "#{businessTravelService}")
    private BusinessTravelService businessTravelService;
    @ManagedProperty(value = "#{businessTravelComponentService}")
    private BusinessTravelComponentService businessTravelComponentService;
    @ManagedProperty(value = "#{approvalActivityService}")
    private ApprovalActivityService approvalActivityService;

    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            String execution = FacesUtil.getRequestParameter("execution");
            String param = execution.substring(0, 1);
            if(StringUtils.equals(param, "e")){
            	/* parameter (id) ini datangnya dari businesstravel Flow atau View */
            	selectedBusinessTravel = businessTravelService.getEntityByPkWithDetail(Long.parseLong(execution.substring(1)));
            } else {
            	/* parameter (activityNumber) ini datangnya dari home approval request history View */
            	selectedBusinessTravel = businessTravelService.getEntityByApprovalActivityNumberWithDetail(execution.substring(1));
            }
            
            selectedApprovalActivity = approvalActivityService.getEntityByActivityNumberLastSequence(selectedBusinessTravel.getApprovalActivityNumber());
            businessTravelComponents = businessTravelComponentService.getAllDataByBusinessTravelId(selectedBusinessTravel.getId());            
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
        businessTravelComponentService = null;
        totalAmount = null;
        selectedApprovalActivity = null;
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

	public ApprovalActivity getSelectedApprovalActivity() {
		return selectedApprovalActivity;
	}

	public void setSelectedApprovalActivity(
			ApprovalActivity selectedApprovalActivity) {
		this.selectedApprovalActivity = selectedApprovalActivity;
	}

	public void setApprovalActivityService(
			ApprovalActivityService approvalActivityService) {
		this.approvalActivityService = approvalActivityService;
	}
	
	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Boolean getIsHaveApprovalActivity(){
		return selectedApprovalActivity != null;
	}
	
	public Boolean getIsPaginator(){
		return businessTravelComponents.size() > 11;
	}
	
	public String doBack() {
        return "/protected/personalia/business_travel_view.htm?faces-redirect=true";
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
