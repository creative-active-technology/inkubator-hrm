/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.payroll;

import ch.lambdaj.Lambda;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.BioBankAccount;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.PayReceiverBankAccount;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.PayReceiverBankAccountService;
import com.inkubator.hrm.web.model.PayReceiverBankAccountModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
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
    private List<PayReceiverBankAccount> dataToCalculate = new ArrayList<>();
    private Double totalPercent;
    private Boolean isEdit;

    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            String empId = FacesUtil.getRequestParameter("execution");

            empData = empDataService.getByPKBankTransfer(Long.parseLong(empId.substring(1)));
            dataToCalculate = this.payReceiverBankAccountService.getAllByEmpId(empData.getId());

            LOGGER.info("Jumlah nya " + dataToCalculate.size());
            totalPercent = Lambda.sum(dataToCalculate, Lambda.on(PayReceiverBankAccount.class).getPersen());
            if (dataToCalculate.isEmpty()) {
                dataToCalculate = new ArrayList<>();
                Set<BioBankAccount> data = empData.getBioData().getBioBankAccounts();
                for (BioBankAccount bankAccount : data) {
                    PayReceiverBankAccount account = new PayReceiverBankAccount();
                    account.setBioBankAccount(bankAccount);
                    account.setEmpData(empData);
                    account.setPersen(0.0);
                    dataToCalculate.add(account);
                }
                isEdit = Boolean.FALSE;
            } else {
                isEdit = Boolean.TRUE;
                Set<BioBankAccount> data = empData.getBioData().getBioBankAccounts();
                List<BioBankAccount> acuanAccount = new ArrayList<>(data);
                List<BioBankAccount> targerAxxount = new ArrayList<>();
                LOGGER.info("Ukueran target " + targerAxxount.size());
                LOGGER.info("Ukueran acuaan " + acuanAccount.size());
                for (PayReceiverBankAccount dataToCalculate1 : dataToCalculate) {
                    targerAxxount.add(dataToCalculate1.getBioBankAccount());
                }
                acuanAccount.removeAll(targerAxxount);
                LOGGER.info("ukrannaya " + acuanAccount.size());
                List<PayReceiverBankAccount> addNew = new ArrayList<>();
                for (BioBankAccount a : acuanAccount) {
                    PayReceiverBankAccount account = new PayReceiverBankAccount();
                    account.setBioBankAccount(a);
                    account.setEmpData(empData);
                    account.setPersen(0.0);
                    addNew.add(account);
                }
                payReceiverBankAccountService.saveListPayBankReceive(addNew);
                dataToCalculate = new ArrayList<>();
                dataToCalculate = this.payReceiverBankAccountService.getAllByEmpId(empData.getId());

            }

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

    public List<PayReceiverBankAccount> getDataToCalculate() {
        return dataToCalculate;
    }

    public void setDataToCalculate(List<PayReceiverBankAccount> dataToCalculate) {
        this.dataToCalculate = dataToCalculate;
    }

    public void doChange() {
        totalPercent = Lambda.sum(dataToCalculate, Lambda.on(PayReceiverBankAccount.class).getPersen());
        if (totalPercent > 100) {
            MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_ERROR, "global.error", "distribution.error_range",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        }
    }

    public String doSave() {

        totalPercent = Lambda.sum(dataToCalculate, Lambda.on(PayReceiverBankAccount.class).getPersen());
        if (totalPercent == 100) {
            if (isEdit) {
                try {
                    this.payReceiverBankAccountService.updateList(dataToCalculate);
                    MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save", "global.update_successfully",
                            FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
                    return "/protected/payroll/pay_receiver_view.htm?faces-redirect=true";
                } catch (Exception ex) {
                    LOGGER.error(ex, ex);
                }
            } else {
                try {
                    this.payReceiverBankAccountService.saveList(dataToCalculate);
                    MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save", "global.added_successfully",
                            FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

                    return "/protected/payroll/pay_receiver_view.htm?faces-redirect=true";
                } catch (Exception ex) {

                }
            }
        } else {

            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.error", "distribution.error_range_not100",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        }

        return null;
    }

    public Double getTotalPercent() {
        return totalPercent;
    }

    public void setTotalPercent(Double totalPercent) {
        this.totalPercent = totalPercent;
    }

    public String doBack() {
        return "/protected/payroll/pay_receiver_view.htm?faces-redirect=true";

    }

    public Boolean getIsEdit() {
        return isEdit;
    }

    public void setIsEdit(Boolean isEdit) {
        this.isEdit = isEdit;
    }

}
