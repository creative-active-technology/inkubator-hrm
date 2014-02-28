/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.ProscessToApprove;
import com.inkubator.hrm.service.ProscessToApproveService;
import com.inkubator.hrm.web.lazymodel.ProcessApprovalLazyModel;
import com.inkubator.hrm.web.search.ProscessToApproveSearchParameter;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author Deni Husni FR
 */
@ManagedBean(name = "approvalProcessController")
@ViewScoped
public class ApprovalProcessController extends BaseController {

    private ProscessToApproveSearchParameter proscessToApproveSearchParameter;
    private LazyDataModel<ProscessToApprove> lazyDataModelProscessToApprove;
    private List<String> listProcess = new ArrayList<>();
    private String selectedProcess;
    @ManagedProperty(value = "#{proscessToApproveService}")
    private ProscessToApproveService proscessToApproveService;

    public void setProscessToApproveService(ProscessToApproveService proscessToApproveService) {
        this.proscessToApproveService = proscessToApproveService;
    }

    public ProscessToApproveSearchParameter getProscessToApproveSearchParameter() {
        return proscessToApproveSearchParameter;
    }

    public void setProscessToApproveSearchParameter(ProscessToApproveSearchParameter proscessToApproveSearchParameter) {
        this.proscessToApproveSearchParameter = proscessToApproveSearchParameter;
    }

    public LazyDataModel<ProscessToApprove> getLazyDataModelProscessToApprove() {
        if (lazyDataModelProscessToApprove == null) {
            lazyDataModelProscessToApprove = new ProcessApprovalLazyModel(proscessToApproveSearchParameter, proscessToApproveService);
        }
        return lazyDataModelProscessToApprove;
    }

    public void setLazyDataModelProscessToApprove(LazyDataModel<ProscessToApprove> lazyDataModelProscessToApprove) {
        this.lazyDataModelProscessToApprove = lazyDataModelProscessToApprove;
    }

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        if (FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).equals("in")) {
            listProcess.add(HRMConstant.APPROVAL_PROCESS_CREATE_USER_ID);
            listProcess.add(HRMConstant.APPROVAL_PROCESS_UPDATE_USER_ID);
            listProcess.add(HRMConstant.APPROVAL_PROCESS_DELETE_USER_ID);
        } else {
            listProcess.add(HRMConstant.APPROVAL_PROCESS_CREATE_USER_EN);
            listProcess.add(HRMConstant.APPROVAL_PROCESS_UPDATE_USER_EN);
            listProcess.add(HRMConstant.APPROVAL_PROCESS_DELETE_USER_EN);
        }
        proscessToApproveSearchParameter = new ProscessToApproveSearchParameter();
    }

    public String getSelectedProcess() {
        return selectedProcess;
    }

    public void setSelectedProcess(String selectedProcess) {
        this.selectedProcess = selectedProcess;
    }

    public List<String> getListProcess() {
        return listProcess;
    }

    public void setListProcess(List<String> listProcess) {
        this.listProcess = listProcess;
    }

}
