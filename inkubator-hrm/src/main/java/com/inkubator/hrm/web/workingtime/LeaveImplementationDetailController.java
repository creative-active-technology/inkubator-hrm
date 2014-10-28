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
import com.inkubator.hrm.entity.LeaveImplementation;
import com.inkubator.hrm.entity.LeaveImplementationDate;
import com.inkubator.hrm.service.ApprovalActivityService;
import com.inkubator.hrm.service.LeaveImplementationService;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "leaveImplementationDetailController")
@ViewScoped
public class LeaveImplementationDetailController extends BaseController {

    private LeaveImplementation selectedLeaveImplementation;
    private List<LeaveImplementationDate> listLeaveImplDate;
    private ApprovalActivity selectedApprovalActivity;
    @ManagedProperty(value = "#{leaveImplementationService}")
    private LeaveImplementationService leaveImplementationService;
    @ManagedProperty(value = "#{approvalActivityService}")
    private ApprovalActivityService approvalActivityService;

    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            listLeaveImplDate = new ArrayList<LeaveImplementationDate>();
            String execution = FacesUtil.getRequestParameter("execution");
            String param = execution.substring(0, 1);
            if(StringUtils.equals(param, "e")){
            	/* parameter (id) ini datangnya dari leave implementation View */
            	selectedLeaveImplementation = leaveImplementationService.getEntityByPkWithDetail(Long.parseLong(execution.substring(1)));
            	            	
            } else {
            	/* parameter (activityNumber) ini datangnya dari home approval request history View */
            	selectedLeaveImplementation = leaveImplementationService.getEntityByApprovalActivityNumberWithDetail(execution.substring(1));            	
            	//jika null, maka ambil entity dari previous activity number-nya
            	if(selectedLeaveImplementation == null){ 
            		ApprovalActivity prev = approvalActivityService.getEntityByActivityNumberLastSequence(execution.substring(1));
            		selectedLeaveImplementation = leaveImplementationService.getEntityByApprovalActivityNumberWithDetail(prev.getPreviousActivityNumber());
            	}
            }
            
            //sort by actual date
            listLeaveImplDate.addAll(selectedLeaveImplementation.getLeaveImplementationDates());
            listLeaveImplDate = Lambda.sort(listLeaveImplDate, Lambda.on(LeaveImplementationDate.class).getActualDate());
            selectedApprovalActivity = approvalActivityService.getEntityByActivityNumberLastSequence(selectedLeaveImplementation.getApprovalActivityNumber());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);

        }
    }

    @PreDestroy
    public void cleanAndExit() {
    	selectedLeaveImplementation = null;
        leaveImplementationService = null;
        selectedApprovalActivity = null;
        approvalActivityService = null;
        listLeaveImplDate = null;
    }    
    
	public LeaveImplementation getSelectedLeaveImplementation() {
		return selectedLeaveImplementation;
	}

	public void setSelectedLeaveImplementation(LeaveImplementation selectedLeaveImplementation) {
		this.selectedLeaveImplementation = selectedLeaveImplementation;
	}

	public void setLeaveImplementationService(LeaveImplementationService leaveImplementationService) {
		this.leaveImplementationService = leaveImplementationService;
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

	public List<LeaveImplementationDate> getListLeaveImplDate() {
		return listLeaveImplDate;
	}

	public void setListLeaveImplDate(List<LeaveImplementationDate> listLeaveImplDate) {
		this.listLeaveImplDate = listLeaveImplDate;
	}

	public String doBack() {
        return "/protected/working_time/leave_implementation_view.htm?faces-redirect=true";
    }

    public String doUpdate() {
        return "/protected/working_time/leave_implementation_form.htm?faces-redirect=true&execution=e" + selectedLeaveImplementation.getId();
    }
    
    public Boolean getIsHaveApprovalActivity(){
		return selectedApprovalActivity != null;
	}
}
