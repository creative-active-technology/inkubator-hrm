/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.ApprovalActivity;
import com.inkubator.hrm.service.ApprovalActivityService;
import com.inkubator.securitycore.util.UserInfoUtil;
import com.inkubator.webcore.controller.BaseController;

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

    public String doDetailRequest() {
        return "/protected/approval/approval_request_detail.htm?faces-redirect=true&execution=e" + selectedApprovalActivity.getId();
    }

    public ApprovalActivity getSelectedApprovalActivity() {
        return selectedApprovalActivity;
    }

    public void setSelectedApprovalActivity(ApprovalActivity selectedApprovalActivity) {
        this.selectedApprovalActivity = selectedApprovalActivity;
    }

    public String doDetailRequestHistory() {
        return "/protected/approval/approval_request_detail.htm?faces-redirect=true&execution=e" + selectedApprovalActivity.getId();
    }

    public String doApprove() {
    	String redirect = "/protected/home.htm";
    	
    	try {
			selectedApprovalActivity = approvalActivityService.getEntityByPkWithDetail(selectedApprovalActivity.getId());
			switch (selectedApprovalActivity.getApprovalDefinition().getName()) {
				case HRMConstant.BUSINESS_TRAVEL:
					redirect = "/protected/personalia/business_travel_approval_form.htm?faces-redirect=true&execution=e" + selectedApprovalActivity.getId();
					break;
				default:
					break;
			}
		} catch (Exception e) {
			LOGGER.error("Error", e);
		}        
    	
    	return redirect;
    }

    public String doDetailRequestPending() {
        return "/protected/approval/biodata_pending_request_detail.htm?faces-redirect=true&execution=e" + selectedApprovalActivity.getId();
    }
}
