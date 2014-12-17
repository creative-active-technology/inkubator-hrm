/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.payroll;

import com.inkubator.hrm.service.PayReceiverBankAccountService;
import com.inkubator.hrm.web.lazymodel.PayReceiverBankAccountLazyDataModel;
import com.inkubator.hrm.web.model.PayReceiverBankAccountModel;
import com.inkubator.hrm.web.search.PayReceiverBankAccountSearchParameter;
import com.inkubator.webcore.controller.BaseController;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author denifahri
 */
@ManagedBean(name = "payReceiverBankViewController")
@ViewScoped
public class PayReceiverBankViewController extends BaseController {

    private PayReceiverBankAccountSearchParameter parameter;
    @ManagedProperty(value = "#{payReceiverBankAccountService}")
    private PayReceiverBankAccountService payReceiverBankAccountService;
    private LazyDataModel<PayReceiverBankAccountModel> lazyDataModel;
    private PayReceiverBankAccountModel selectedPayReceiverBankAccountModel;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        parameter = new PayReceiverBankAccountSearchParameter();

    }

    public PayReceiverBankAccountSearchParameter getParameter() {
        return parameter;
    }

    public void setParameter(PayReceiverBankAccountSearchParameter parameter) {
        this.parameter = parameter;
    }

    public LazyDataModel<PayReceiverBankAccountModel> getLazyDataModel() {
        if (lazyDataModel == null) {
            lazyDataModel = new PayReceiverBankAccountLazyDataModel(parameter, payReceiverBankAccountService);
        }
        return lazyDataModel;
    }

    public void setLazyDataModel(LazyDataModel<PayReceiverBankAccountModel> lazyDataModel) {
        this.lazyDataModel = lazyDataModel;
    }

    public void setPayReceiverBankAccountService(PayReceiverBankAccountService payReceiverBankAccountService) {
        this.payReceiverBankAccountService = payReceiverBankAccountService;
    }

    public void doSearch() {
        lazyDataModel = null;
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
}
