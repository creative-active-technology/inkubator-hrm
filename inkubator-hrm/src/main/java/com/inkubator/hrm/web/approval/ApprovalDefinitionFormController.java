/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.approval;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.web.model.ApprovalDefinitionModel;
import com.inkubator.webcore.controller.BaseController;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Deni Husni FR
 */
@ManagedBean(name = "approvalDefinitionFormController")
@ViewScoped
public class ApprovalDefinitionFormController extends BaseController {

    private ApprovalDefinitionModel approvalDefinitionModel;
    private Boolean onBehalf;
    private Boolean onProcess;
    private Boolean approverTypeIndividual;
    private Boolean approverTypePosition;
    private Boolean approverTypeDepartment;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        approvalDefinitionModel = new ApprovalDefinitionModel();
        onBehalf = Boolean.TRUE;
        onProcess = Boolean.TRUE;
        approverTypeIndividual = Boolean.TRUE;
        approverTypePosition = Boolean.TRUE;
        approverTypeDepartment = Boolean.TRUE;

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

    public void onChange() {
        onBehalf = approvalDefinitionModel.getAllowOnBehalf();
    }

    public void onProcessCange() {
        String approvalProcess = approvalDefinitionModel.getProcessType();
        if (StringUtils.equalsIgnoreCase(approvalProcess, HRMConstant.ON_APPROVE_INFO)
                || StringUtils.equalsIgnoreCase(approvalProcess, HRMConstant.ON_REJECT_INFO)) {
            onProcess = Boolean.TRUE;
        }
        if (StringUtils.equalsIgnoreCase(approvalProcess, HRMConstant.APPROVAL_PROCESS) || approvalProcess == null || approvalProcess.isEmpty()) {
            onProcess = Boolean.FALSE;
        }
    }

    public void onAppoverChange() {
        String apporverType = approvalDefinitionModel.getApproverType();
        if (StringUtils.equalsIgnoreCase(apporverType, HRMConstant.APPROVAL_TYPE_INDIVIDUAL)) {
            onProcess = Boolean.TRUE;
        }
    }

    public Boolean getOnProcess() {
        return onProcess;
    }

    public void setOnProcess(Boolean onProcess) {
        this.onProcess = onProcess;
    }

    public Boolean getApproverTypeIndividual() {
        return approverTypeIndividual;
    }

    public void setApproverTypeIndividual(Boolean approverTypeIndividual) {
        this.approverTypeIndividual = approverTypeIndividual;
    }

    public Boolean getApproverTypePosition() {
        return approverTypePosition;
    }

    public void setApproverTypePosition(Boolean approverTypePosition) {
        this.approverTypePosition = approverTypePosition;
    }

    public Boolean getApproverTypeDepartment() {
        return approverTypeDepartment;
    }

    public void setApproverTypeDepartment(Boolean approverTypeDepartment) {
        this.approverTypeDepartment = approverTypeDepartment;
    }

}
