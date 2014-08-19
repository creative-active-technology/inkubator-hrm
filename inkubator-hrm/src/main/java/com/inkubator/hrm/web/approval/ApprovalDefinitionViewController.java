/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.approval;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.ApprovalDefinition;
import com.inkubator.hrm.service.ApprovalDefinitionService;
import com.inkubator.hrm.web.lazymodel.ApprovalDefinitionLazyDataModel;
import com.inkubator.hrm.web.search.ApprovalDefinitionSearchParameter;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.hibernate.exception.ConstraintViolationException;
import org.primefaces.model.LazyDataModel;
import org.springframework.dao.DataIntegrityViolationException;

/**
 *
 * @author Deni Husni FR
 */
@ManagedBean(name = "approvalDefinitionController")
@ViewScoped
public class ApprovalDefinitionViewController extends BaseController {

    private ApprovalDefinitionSearchParameter approvalDefinitionSearchParameter;
    private LazyDataModel<ApprovalDefinition> lazyDataModelApprovalDefinition;
    @ManagedProperty(value = "#{approvalDefinitionService}")
    private ApprovalDefinitionService approvalDefinitionService;
    private ApprovalDefinition selectedApprovalDefinition;

    public void setApprovalDefinitionService(ApprovalDefinitionService approvalDefinitionService) {
        this.approvalDefinitionService = approvalDefinitionService;
    }

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        approvalDefinitionSearchParameter = new ApprovalDefinitionSearchParameter();

    }

    public LazyDataModel<ApprovalDefinition> getLazyDataModelApprovalDefinition() {
        if (lazyDataModelApprovalDefinition == null) {
            lazyDataModelApprovalDefinition = new ApprovalDefinitionLazyDataModel(approvalDefinitionSearchParameter, approvalDefinitionService);
        }
        return lazyDataModelApprovalDefinition;
    }

    public void setLazyDataModelApprovalDefinition(LazyDataModel<ApprovalDefinition> lazyDataModelApprovalDefinition) {
        this.lazyDataModelApprovalDefinition = lazyDataModelApprovalDefinition;
    }

    public ApprovalDefinitionSearchParameter getApprovalDefinitionSearchParameter() {
        return approvalDefinitionSearchParameter;
    }

    public void setApprovalDefinitionSearchParameter(ApprovalDefinitionSearchParameter approvalDefinitionSearchParameter) {
        this.approvalDefinitionSearchParameter = approvalDefinitionSearchParameter;
    }

    public void doSearch() {
        lazyDataModelApprovalDefinition = null;
    }

    public ApprovalDefinition getSelectedApprovalDefinition() {
        return selectedApprovalDefinition;
    }

    public void setSelectedApprovalDefinition(ApprovalDefinition selectedApprovalDefinition) {
        this.selectedApprovalDefinition = selectedApprovalDefinition;
    }

    public void onDelete() {
        try {
            selectedApprovalDefinition = this.approvalDefinitionService.getEntiyByPK(selectedApprovalDefinition.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public void doDelete() {
        try {
            this.approvalDefinitionService.delete(selectedApprovalDefinition);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            LOGGER.error("Error", ex);
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

}