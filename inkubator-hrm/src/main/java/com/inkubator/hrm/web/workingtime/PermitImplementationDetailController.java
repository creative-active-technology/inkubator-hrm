/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.workingtime;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang.StringUtils;

import ch.lambdaj.Lambda;

import com.inkubator.hrm.entity.ApprovalActivity;
import com.inkubator.hrm.entity.PermitImplementation;
//import com.inkubator.hrm.entity.PermitImplementationDate;
import com.inkubator.hrm.service.ApprovalActivityService;
import com.inkubator.hrm.service.PermitImplementationService;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;

/**
 *
 * @author Taufik
 */
@ManagedBean(name = "permitImplementationDetailController")
@ViewScoped
public class PermitImplementationDetailController extends BaseController {

    private PermitImplementation selectedPermitImplementation;
//    private List<PermitImplementationDate> listPermitImplDate;
    private ApprovalActivity selectedApprovalActivity;
    @ManagedProperty(value = "#{permitImplementationService}")
    private PermitImplementationService permitImplementationService;
    @ManagedProperty(value = "#{approvalActivityService}")
    private ApprovalActivityService approvalActivityService;

    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
//            listPermitImplDate = new ArrayList<PermitImplementationDate>();
            String execution = FacesUtil.getRequestParameter("execution");
            String param = execution.substring(0, 1);
            if(StringUtils.equals(param, "e")){
            	/* parameter (id) ini datangnya dari permit implementation View */
            	selectedPermitImplementation = permitImplementationService.getEntityByPkWithDetail(Long.parseLong(execution.substring(1)));
            } else {
            	/* parameter (activityNumber) ini datangnya dari home approval request history View */
            	selectedPermitImplementation = permitImplementationService.getEntityByApprovalActivityNumberWithDetail(execution.substring(1));            	
            	//jika null, maka ambil entity dari previous activity number-nya
            	if(selectedPermitImplementation == null){ 
            		ApprovalActivity prev = approvalActivityService.getEntityByActivityNumberLastSequence(execution.substring(1));
            		selectedPermitImplementation = permitImplementationService.getEntityByApprovalActivityNumberWithDetail(prev.getPreviousActivityNumber());
            	}
            }
            
//            //sort by actual date
//            listPermitImplDate.addAll(selectedPermitImplementation.getPermitImplementationDates());
//            listPermitImplDate = Lambda.sort(listPermitImplDate, Lambda.on(PermitImplementationDate.class).getActualDate());
//            selectedApprovalActivity = approvalActivityService.getEntityByActivityNumberLastSequence(selectedPermitImplementation.getApprovalActivityNumber());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);

        }
    }

    @PreDestroy
    public void cleanAndExit() {
    	selectedPermitImplementation = null;
        permitImplementationService = null;
        selectedApprovalActivity = null;
        approvalActivityService = null;
//        listPermitImplDate = null;
    }    
    
	public PermitImplementation getSelectedPermitImplementation() {
		return selectedPermitImplementation;
	}

	public void setSelectedPermitImplementation(PermitImplementation selectedPermitImplementation) {
		this.selectedPermitImplementation = selectedPermitImplementation;
	}

	public void setPermitImplementationService(PermitImplementationService permitImplementationService) {
		this.permitImplementationService = permitImplementationService;
	}

	public ApprovalActivity getSelectedApprovalActivity() {
		return selectedApprovalActivity;
	}

	public void setSelectedApprovalActivity(ApprovalActivity selectedApprovalActivity) {
		this.selectedApprovalActivity = selectedApprovalActivity;
	}

	public void setApprovalActivityService(ApprovalActivityService approvalActivityService) {
		this.approvalActivityService = approvalActivityService;
	}	

//	public List<PermitImplementationDate> getListPermitImplDate() {
//		return listPermitImplDate;
//	}
//
//	public void setListPermitImplDate(List<PermitImplementationDate> listPermitImplDate) {
//		this.listPermitImplDate = listPermitImplDate;
//	}

	public String doBack() {
        return "/protected/working_time/permit_impl_view.htm?faces-redirect=true";
    }

    public String doUpdate() {
        return "/protected/working_time/permit_impl_form.htm?faces-redirect=true&execution=e" + selectedPermitImplementation.getId();
    }
    
    public Boolean getIsHaveApprovalActivity(){
		return selectedApprovalActivity != null;
	}
}
