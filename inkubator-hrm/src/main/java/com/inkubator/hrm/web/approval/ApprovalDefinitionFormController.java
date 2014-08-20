/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.approval;

import com.inkubator.hrm.web.model.ApprovalDefinitionModel;
import com.inkubator.webcore.controller.BaseController;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Deni Husni FR
 */
@ManagedBean(name = "approvalDefinitionFormController")
@ViewScoped
public class ApprovalDefinitionFormController extends BaseController {

    private ApprovalDefinitionModel approvalDefinitionModel;
    private Boolean onBehalf;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        approvalDefinitionModel = new ApprovalDefinitionModel();
        onBehalf=Boolean.TRUE;
        System.out.println(" hahahahha");

    }

    public ApprovalDefinitionModel getApprovalDefinitionModel() {
        return approvalDefinitionModel;
    }

    public void setApprovalDefinitionModel(ApprovalDefinitionModel approvalDefinitionModel) {
        this.approvalDefinitionModel = approvalDefinitionModel;
    }

    public Boolean getOnBehalf() {
        return onBehalf;
    }

    public void setOnBehalf(Boolean onBehalf) {
        this.onBehalf = onBehalf;
    }

    public void onChange(){
       onBehalf=approvalDefinitionModel.getAllowOnBehalf();
    }
}
