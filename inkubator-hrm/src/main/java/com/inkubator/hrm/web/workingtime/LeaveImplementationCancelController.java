/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.workingtime;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.inkubator.hrm.entity.ApprovalActivity;
import com.inkubator.hrm.entity.LeaveImplementation;
import com.inkubator.hrm.service.ApprovalActivityService;
import com.inkubator.hrm.service.LeaveImplementationService;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "leaveImplementationCancelController")
@ViewScoped
public class LeaveImplementationCancelController extends BaseController {

    private LeaveImplementation selectedLeaveImplementation;
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
            String execution = FacesUtil.getRequestParameter("execution");
            selectedLeaveImplementation = leaveImplementationService.getEntityByPkWithDetail(Long.parseLong(execution.substring(1)));            
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

	public String doBack() {
        return "/protected/working_time/leave_implementation_view.htm?faces-redirect=true";
    }
    
    public Boolean getIsHaveApprovalActivity(){
		return selectedApprovalActivity != null;
	}
}
