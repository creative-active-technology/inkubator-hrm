/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web;

import com.inkubator.hrm.entity.ApprovalActivity;
import com.inkubator.hrm.service.ApprovalActivityService;
import com.inkubator.webcore.controller.BaseController;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

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

    @PostConstruct
    @Override
    public void initialization() {

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
    
    
    
}
