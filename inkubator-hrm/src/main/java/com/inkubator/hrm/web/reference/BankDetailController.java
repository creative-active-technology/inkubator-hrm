/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.reference;

import com.inkubator.hrm.entity.Bank;
import com.inkubator.hrm.service.BankService;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author EKA
 */
@ManagedBean(name = "bankDetailController")
@ViewScoped
public class BankDetailController extends BaseController{
    
    private Bank selectedBank;
    @ManagedProperty(value = "#{bankService}")
    private BankService bankService;
    
    @PostConstruct
    @Override
    public void initialization(){
        try {
            super.initialization();
            String execution = FacesUtil.getRequestParameter("execution");
            String param = execution.substring(0, 1);
            if(StringUtils.equals(param, "e")){
                selectedBank = bankService.getEntityWithDetail(Long.parseLong(execution.substring(1)));
            }
        } catch (Exception ex){
            LOGGER.error("error", ex);
        }
    }
    
    @PreDestroy
    public void cleanAndExit(){
        selectedBank = null;
        bankService = null;
    }

    public Bank getSelectedBank() {
        return selectedBank;
    }

    public void setSelectedBank(Bank selectedBank) {
        this.selectedBank = selectedBank;
    }

    public BankService getBankService() {
        return bankService;
    }

    public void setBankService(BankService bankService) {
        this.bankService = bankService;
    }
    
    public String doBack(){
        return "/protected/reference/bank_view.htm?faces-redirect=true";
    }
    
    public String doUpdates(){
        return "/protected/reference/bank_form.htm?faces-redirect=true&execution=e" + selectedBank.getId();
    }
}
