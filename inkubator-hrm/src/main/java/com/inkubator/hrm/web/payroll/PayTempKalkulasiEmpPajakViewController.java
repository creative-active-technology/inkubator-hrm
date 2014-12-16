/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.payroll;

import com.inkubator.hrm.service.PayTempKalkulasiEmpPajakService;
import com.inkubator.hrm.web.model.PayTempKalkulasiEmpPajakModel;
import com.inkubator.webcore.controller.BaseController;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author deni
 */
@ManagedBean(name = "PayTempKalkulasiEmpPajakViewController")
@ViewScoped
public class PayTempKalkulasiEmpPajakViewController extends BaseController {

    @ManagedProperty(value = "#{payTempKalkulasiEmpPajakService}")
    private PayTempKalkulasiEmpPajakService payTempKalkulasiEmpPajakService;
    private String parameter;
    private PayTempKalkulasiEmpPajakModel payTempKalkulasiEmpPajakModel;
    private List<PayTempKalkulasiEmpPajakModel> payTempKalkulasiPajakList;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        payTempKalkulasiEmpPajakModel = new PayTempKalkulasiEmpPajakModel();
        try {
            payTempKalkulasiPajakList = payTempKalkulasiEmpPajakService.getByParam();
        } catch (Exception ex) {
            Logger.getLogger(PayTempKalkulasiEmpPajakViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @PreDestroy
    public void cleanAndExit() {
        parameter = null;
        payTempKalkulasiEmpPajakService = null;
        payTempKalkulasiEmpPajakModel = null;
    }

    public String doDetail() {
        return "/protected/payroll/tax_detail.htm?faces-redirect=true&execution=e" + payTempKalkulasiEmpPajakModel.getTaxId();
    }
        
    public PayTempKalkulasiEmpPajakService getPayTempKalkulasiEmpPajakService() {
        return payTempKalkulasiEmpPajakService;
    }

    public void setPayTempKalkulasiEmpPajakService(PayTempKalkulasiEmpPajakService payTempKalkulasiEmpPajakService) {
        this.payTempKalkulasiEmpPajakService = payTempKalkulasiEmpPajakService;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public PayTempKalkulasiEmpPajakModel getPayTempKalkulasiEmpPajakModel() {
        return payTempKalkulasiEmpPajakModel;
    }

    public void setPayTempKalkulasiEmpPajakModel(PayTempKalkulasiEmpPajakModel payTempKalkulasiEmpPajakModel) {
        this.payTempKalkulasiEmpPajakModel = payTempKalkulasiEmpPajakModel;
    }

    public List<PayTempKalkulasiEmpPajakModel> getPayTempKalkulasiPajakList() {
        return payTempKalkulasiPajakList;
    }

    public void setPayTempKalkulasiPajakList(List<PayTempKalkulasiEmpPajakModel> payTempKalkulasiPajakList) {
        this.payTempKalkulasiPajakList = payTempKalkulasiPajakList;
    }

    
}
