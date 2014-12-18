/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.payroll;

import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.PayReceiverBankAccountService;
import com.inkubator.hrm.web.model.PayReceiverBankAccountModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author denifahri
 */
@ManagedBean(name = "distributionBankController")
@ViewScoped
public class DistributionBankController extends BaseController {

    @ManagedProperty(value = "#{payReceiverBankAccountService}")
    private PayReceiverBankAccountService payReceiverBankAccountService;
    private PayReceiverBankAccountModel selectedPayReceiverBankAccountModel;
    @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;
    private EmpData empData;

    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            String empId = FacesUtil.getRequestParameter("execution");
            System.out.println(" Hehrehehheehhe " +empId);
            empData = empDataService.getByPKBankTransfer(Long.parseLong(empId.substring(1)));

        } catch (Exception ex) {
         LOGGER.error(ex, ex);
        }

    }

    public void setPayReceiverBankAccountService(PayReceiverBankAccountService payReceiverBankAccountService) {
        this.payReceiverBankAccountService = payReceiverBankAccountService;
    }

    public String doDetail() {
        return null;
    }

    public PayReceiverBankAccountModel getSelectedPayReceiverBankAccountModel() {
        return selectedPayReceiverBankAccountModel;
    }

    public void setSelectedPayReceiverBankAccountModel(PayReceiverBankAccountModel selectedPayReceiverBankAccountModel) {
        this.selectedPayReceiverBankAccountModel = selectedPayReceiverBankAccountModel;
    }

    public String doDistribution() {
        return "/protected/payroll/distribution_bank_transfer.htm?faces-redirect=true&execution=e" + selectedPayReceiverBankAccountModel.getEmpId();
    }

    public void setEmpDataService(EmpDataService empDataService) {
        this.empDataService = empDataService;
    }

    public EmpData getEmpData() {
        return empData;
    }

    public void setEmpData(EmpData empData) {
        this.empData = empData;
    }

}
