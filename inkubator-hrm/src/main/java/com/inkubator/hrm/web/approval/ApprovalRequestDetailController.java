/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.approval;

import com.inkubator.hrm.entity.ApprovalActivity;
import com.inkubator.hrm.service.ApprovalActivityService;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Deni Husni FR
 */
@ManagedBean(name = "approvalRequestDetailController")
@ViewScoped
public class ApprovalRequestDetailController extends BaseController {

    @ManagedProperty(value = "#{approvalActivityService}")
    private ApprovalActivityService approvalActivityService;
    private List<ApprovalActivity>dataResuest=new ArrayList<>();

  

    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            String approvalId = FacesUtil.getRequestParameter("execution");
            dataResuest = approvalActivityService.getReguestHistoryById(Long.parseLong(approvalId.substring(1)));
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }

    }

   
    @PreDestroy
    public void cleanAndExit() {
        approvalActivityService = null;
        dataResuest = null;
    }

    public void setApprovalActivityService(ApprovalActivityService approvalActivityService) {
        this.approvalActivityService = approvalActivityService;
    }

    public List<ApprovalActivity> getDataResuest() {
        return dataResuest;
    }

    public void setDataResuest(List<ApprovalActivity> dataResuest) {
        this.dataResuest = dataResuest;
    }

    
}
