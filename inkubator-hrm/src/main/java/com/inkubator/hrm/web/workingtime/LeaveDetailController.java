/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.workingtime;

import com.inkubator.hrm.entity.ApprovalDefinitionLeave;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.inkubator.hrm.entity.Leave;
import com.inkubator.hrm.service.ApprovalDefinitionLeaveService;
import com.inkubator.hrm.service.LeaveService;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import java.util.List;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "leaveDetailController")
@ViewScoped
public class LeaveDetailController extends BaseController {

    private Leave selectedLeave;
    private List<ApprovalDefinitionLeave> selectedApprovalDefinitionLeave;
    @ManagedProperty(value = "#{leaveService}")
    private LeaveService leaveService;
    @ManagedProperty(value = "#{approvalDefinitionLeaveService}")
    private ApprovalDefinitionLeaveService approvalDefinitionLeaveService;

    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            String id = FacesUtil.getRequestParameter("execution");
            selectedLeave = leaveService.getEntityByPkFetchAttendStatus(Long.parseLong(id.substring(1)));
            selectedApprovalDefinitionLeave = approvalDefinitionLeaveService.getByLeaveId(Long.parseLong(id.substring(1)));
        } catch (Exception ex) {
            LOGGER.error("Error", ex);

        }
    }

    @PreDestroy
    public void cleanAndExit() {
        selectedLeave = null;
        leaveService = null;
        selectedApprovalDefinitionLeave = null;
        approvalDefinitionLeaveService = null;
    }    

    public Leave getSelectedLeave() {
		return selectedLeave;
	}

	public void setSelectedLeave(Leave selectedLeave) {
		this.selectedLeave = selectedLeave;
	}

	public void setLeaveService(LeaveService leaveService) {
		this.leaveService = leaveService;
	}

	public String doBack() {
        return "/protected/working_time/leave_view.htm?faces-redirect=true";
    }

    public String doUpdate() {
        return "/protected/working_time/leave_form.htm?faces-redirect=true&execution=e" + selectedLeave.getId();
    }

    public List<ApprovalDefinitionLeave> getSelectedApprovalDefinitionLeave() {
        return selectedApprovalDefinitionLeave;
    }

    public void setSelectedApprovalDefinitionLeave(List<ApprovalDefinitionLeave> selectedApprovalDefinitionLeave) {
        this.selectedApprovalDefinitionLeave = selectedApprovalDefinitionLeave;
    }

    public ApprovalDefinitionLeaveService getApprovalDefinitionLeaveService() {
        return approvalDefinitionLeaveService;
    }

    public void setApprovalDefinitionLeaveService(ApprovalDefinitionLeaveService approvalDefinitionLeaveService) {
        this.approvalDefinitionLeaveService = approvalDefinitionLeaveService;
    }
    
    

}
