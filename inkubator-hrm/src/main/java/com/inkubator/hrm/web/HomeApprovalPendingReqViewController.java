/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web;

import com.inkubator.hrm.entity.ApprovalActivity;
import com.inkubator.hrm.service.ApprovalActivityService;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
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
@ManagedBean(name = "homeApprovalPendingReqViewController")
@RequestScoped
public class HomeApprovalPendingReqViewController extends BaseController {

    @ManagedProperty(value = "#{approvalActivityService}")
    private ApprovalActivityService approvalActivityService;
    private List<ApprovalActivity> pendingRequest = new ArrayList<>();

    @PostConstruct
    @Override
    public void initialization() {
         super.initialization();
        try {
        	String activityNumber = FacesUtil.getRequestParameter("activityNumber");
        	pendingRequest = this.approvalActivityService.getAllDataByActivityNumberWithDetail(activityNumber);
        	
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
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
