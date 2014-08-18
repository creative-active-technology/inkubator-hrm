/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.approval;

import com.inkubator.hrm.entity.ApprovalDefinition;
import com.inkubator.hrm.service.ApprovalDefinitionService;
import com.inkubator.hrm.web.lazymodel.ApprovalDefinitionLazyDataModel;
import com.inkubator.hrm.web.search.ApprovalDefinitionSearchParameter;
import com.inkubator.webcore.controller.BaseController;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.LazyDataModel;

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

    public void setApprovalDefinitionService(ApprovalDefinitionService approvalDefinitionService) {
        this.approvalDefinitionService = approvalDefinitionService;
    }

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        approvalDefinitionSearchParameter=new ApprovalDefinitionSearchParameter();
        
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

}
