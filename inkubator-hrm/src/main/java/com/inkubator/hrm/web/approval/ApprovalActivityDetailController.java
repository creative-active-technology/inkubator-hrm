/*
 * To change this template, choose Tools | Templates
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
 * @author Deni
 */
@ManagedBean(name = "approvalActivityDetailController")
@ViewScoped
public class ApprovalActivityDetailController extends BaseController{
    private ApprovalActivity selected;
    @ManagedProperty(value = "#{approvalActivityService}")
    private ApprovalActivityService service;
    
    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            String approvalActivityId = FacesUtil.getRequestParameter("execution");
            selected = service.getEntityByPkWithAllRelation(Long.parseLong(approvalActivityId.substring(1)));
            
        } catch (Exception ex) {
            LOGGER.error("Error", ex);

        }
    }
    
    public String doBack() {
        return "/protected/approval/approval_activity_view.htm?faces-redirect=true";
    }
    
    @PreDestroy
    public void cleanAndExit() {
        selected = null;
        service = null;
    }

    public ApprovalActivity getSelected() {
        return selected;
    }

    public void setSelected(ApprovalActivity selected) {
        this.selected = selected;
    }

    public ApprovalActivityService getService() {
        return service;
    }

    public void setService(ApprovalActivityService service) {
        this.service = service;
    }
    
    
}
