/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.ApprovalActivity;
import com.inkubator.hrm.service.ApprovalActivityService;
import com.inkubator.securitycore.util.UserInfoUtil;
import com.inkubator.webcore.controller.BaseController;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Deni Husni FR
 */
@ManagedBean(name = "homeApproalActivityController")
@RequestScoped
public class HomeApproalActivityController extends BaseController {

    @ManagedProperty(value = "#{approvalActivityService}")
    private ApprovalActivityService approvalActivityService;
    private List<ApprovalActivity> requestHistory = new ArrayList<>();
    private List<ApprovalActivity> pendingTask = new ArrayList<>();
    private List<ApprovalActivity> pendingRequest = new ArrayList<>();
    private ApprovalActivity selectedApprovalActivity;

    @PostConstruct
    @Override
    public void initialization() {
        try {
            requestHistory = this.approvalActivityService.getRequestHistory(UserInfoUtil.getUserName());
            pendingRequest = this.approvalActivityService.getPendingRequest(UserInfoUtil.getUserName());
            pendingTask = this.approvalActivityService.getPendingTask(UserInfoUtil.getUserName());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public List<ApprovalActivity> getRequestHistory() {
        return requestHistory;
    }

    public void setRequestHistory(List<ApprovalActivity> requestHistory) {
        this.requestHistory = requestHistory;
    }

    public List<ApprovalActivity> getPendingTask() {
        return pendingTask;
    }

    public void setPendingTask(List<ApprovalActivity> pendingTask) {
        this.pendingTask = pendingTask;
    }

    public List<ApprovalActivity> getPendingRequest() {
        return pendingRequest;
    }

    public void setPendingRequest(List<ApprovalActivity> pendingRequest) {
        this.pendingRequest = pendingRequest;
    }

    public void setApprovalActivityService(ApprovalActivityService approvalActivityService) {
        this.approvalActivityService = approvalActivityService;
    }

    public ApprovalActivity getSelectedApprovalActivity() {
        return selectedApprovalActivity;
    }

    public void setSelectedApprovalActivity(ApprovalActivity selectedApprovalActivity) {
        this.selectedApprovalActivity = selectedApprovalActivity;
    }

    public String doDetailRequestHistory() {
        String redirect = "";
        
        try {
            selectedApprovalActivity = approvalActivityService.getEntityByPkWithDetail(selectedApprovalActivity.getId());
            switch (selectedApprovalActivity.getApprovalDefinition().getName()) {
                case HRMConstant.BUSINESS_TRAVEL:
                    redirect = "/protected/personalia/business_travel_detail.htm?faces-redirect=true&execution=a" + selectedApprovalActivity.getActivityNumber();
                    break;
                case HRMConstant.LOAN:
                    redirect = "/protected/personalia/loan_detail.htm?faces-redirect=true&execution=a" + selectedApprovalActivity.getActivityNumber();
                    break;
                case HRMConstant.REIMBURSEMENT:
                    redirect = "/protected/personalia/reimbursment_detail.htm?faces-redirect=true&execution=a" + selectedApprovalActivity.getActivityNumber();
                    break;
                case HRMConstant.SHIFT_SCHEDULE:
                    redirect = "/protected/personalia/schedule_approval_form.htm?faces-redirect=true&execution=e" + selectedApprovalActivity.getId();
                    break;
                case HRMConstant.LEAVE:
                    redirect = "/protected/working_time/leave_implementation_detail.htm?faces-redirect=true&execution=a" + selectedApprovalActivity.getActivityNumber();
                    break;
                case HRMConstant.LEAVE_CANCELLATION:
                    redirect = "/protected/working_time/leave_implementation_detail.htm?faces-redirect=true&execution=a" + selectedApprovalActivity.getActivityNumber();
                    break;
                case HRMConstant.OVERTIME:
                    redirect = "/protected/employee/overtime_implementation_detail.htm?faces-redirect=true&execution=a" + selectedApprovalActivity.getActivityNumber();
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
        
        return redirect;
    }

    public String doApprove() {
        String redirect = "home";

        try {
            selectedApprovalActivity = approvalActivityService.getEntityByPkWithDetail(selectedApprovalActivity.getId());
            switch (selectedApprovalActivity.getApprovalDefinition().getName()) {
                case HRMConstant.BUSINESS_TRAVEL:
                    redirect = "/protected/personalia/business_travel_approval_form.htm?faces-redirect=true&execution=e" + selectedApprovalActivity.getId();
                    break;
                case HRMConstant.REIMBURSEMENT:
                    redirect = "/protected/personalia/reimbursment_approval_form.htm?faces-redirect=true&execution=e" + selectedApprovalActivity.getId();
                    break;
                case HRMConstant.LOAN:
                    redirect = "/protected/personalia/loan_approval_form.htm?faces-redirect=true&execution=e" + selectedApprovalActivity.getId();
                    break;
                case HRMConstant.SHIFT_SCHEDULE:
                    redirect = "/protected/personalia/schedule_approval_form.htm?faces-redirect=true&execution=e" + selectedApprovalActivity.getId();
                    break;
                case HRMConstant.LEAVE:
                    redirect = "/protected/working_time/leave_implementation_approval_form.htm?faces-redirect=true&execution=e" + selectedApprovalActivity.getId();
                    break;
                case HRMConstant.LEAVE_CANCELLATION:
                    redirect = "/protected/working_time/leave_implementation_approval_form.htm?faces-redirect=true&execution=e" + selectedApprovalActivity.getId();
                    break;
                case HRMConstant.OVERTIME:
                    redirect = "/protected/employee/overtime_implementation_approval_form.htm?faces-redirect=true&execution=e" + selectedApprovalActivity.getId();
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }

        return redirect;
    }

    public void doDetailRequestPending() {

        List<String> values = new ArrayList<>();
        values.add(String.valueOf(selectedApprovalActivity.getActivityNumber()));

        Map<String, List<String>> dataToSend = new HashMap<>();
        dataToSend.put("activityNumber", values);

        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", true);
        options.put("contentWidth", 1000);
        options.put("contentHeight", 300);
        RequestContext.getCurrentInstance().openDialog("approval_activity_pending_request_view", options, dataToSend);
    }
}
