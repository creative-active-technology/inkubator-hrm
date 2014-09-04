/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.approval;

import com.inkubator.hrm.entity.ApprovalDefinition;
import com.inkubator.hrm.service.ApprovalDefinitionService;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Deni Husni FR
 */
@ManagedBean(name = "approvalDefinitionDetilController")
@ViewScoped
public class ApprovalDefinitionDetilController extends BaseController {

    @ManagedProperty(value = "#{approvalDefinitionService}")
    private ApprovalDefinitionService approvalDefinitionService;
    private ApprovalDefinition selectedApprovalDefinition;

    public void setApprovalDefinitionService(ApprovalDefinitionService approvalDefinitionService) {
        this.approvalDefinitionService = approvalDefinitionService;
    }

    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            String approvalId = FacesUtil.getRequestParameter("execution");
            selectedApprovalDefinition = approvalDefinitionService.getEntiyByPK(Long.parseLong(approvalId.substring(1)));
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }

    }

    public ApprovalDefinition getSelectedApprovalDefinition() {
        return selectedApprovalDefinition;
    }

    public void setSelectedApprovalDefinition(ApprovalDefinition selectedApprovalDefinition) {
        this.selectedApprovalDefinition = selectedApprovalDefinition;
    }

    public String doEdit() {
        return "/protected/approval/approval_definition_form.htm?faces-redirect=true&execution=e" + selectedApprovalDefinition.getId();
    }

    public String doBack() {
        return "/protected/approval/approval_definition_view.htm?faces-redirect=true";
    }

    @PreDestroy
    public void cleanAndExit() {
        approvalDefinitionService = null;
        selectedApprovalDefinition = null;
    }

}
