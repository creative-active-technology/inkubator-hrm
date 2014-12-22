/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.payroll;

import com.inkubator.hrm.entity.PayTempKalkulasiEmpPajak;
import com.inkubator.hrm.entity.TaxComponent;
import com.inkubator.hrm.entity.WtPeriode;
import com.inkubator.hrm.service.PayTempKalkulasiEmpPajakService;
import com.inkubator.hrm.service.TaxComponentService;
import com.inkubator.hrm.service.WtPeriodeService;
import com.inkubator.hrm.web.lazymodel.PayTempKalkulasiEmpPajakLazyDataModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import java.util.Date;
import java.util.logging.Level;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.apache.log4j.Logger;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author deni
 */
@ManagedBean(name = "PayTempKalkulasiEmpPajakDetailController")
@ViewScoped
public class PayTempKalkulasiEmpPajakDetailController extends BaseController {

    @ManagedProperty(value = "#{payTempKalkulasiEmpPajakService}")
    private PayTempKalkulasiEmpPajakService payTempKalkulasiEmpPajakService;
    @ManagedProperty(value = "#{taxComponentService}")
    private TaxComponentService taxComponentService;
    @ManagedProperty(value = "#{wtPeriodeService}")
    private WtPeriodeService wtPeriodeService;
    private String searchParameter;
    private LazyDataModel<PayTempKalkulasiEmpPajak> lazyDataModel;
    private Long taxComponentId;
    private Date startDate;
    private Date endDate;
    private String taxComponentName;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        String taxComponent = FacesUtil.getRequestParameter("execution");
        if (taxComponent != null) {
            try {
                WtPeriode wtPeriode = wtPeriodeService.getEntityByPayrollTypeActive();
                
                if (wtPeriode != null) {
                    startDate = wtPeriode.getFromPeriode();
                    endDate = wtPeriode.getUntilPeriode();
                }
                if (taxComponent != null) {
                taxComponentId = Long.valueOf(taxComponent.substring(1));
                taxComponentName = taxComponentService.getTaxComponentNameByPk(taxComponentId);
            }
            } catch (Exception ex) {
                java.util.logging.Logger.getLogger(PayTempKalkulasiEmpPajakDetailController.class.getName()).log(Level.SEVERE, null, ex);
            }

//                PaySalaryComponent paySalaryComponent = paySalaryComponentService.getEntiyByPK(paySalaryComponentId);
//                componentName = paySalaryComponent.getName();
        }
    }

    @PreDestroy
    public void cleanAndExit() {
        lazyDataModel = null;
        taxComponentId = null;
        searchParameter = null;
        taxComponentService = null;
        payTempKalkulasiEmpPajakService = null;
        taxComponentName = null;
        startDate = null;
        endDate = null;
    }

    public void doSearch() {
        lazyDataModel = null;
    }

    public PayTempKalkulasiEmpPajakService getPayTempKalkulasiEmpPajakService() {
        return payTempKalkulasiEmpPajakService;
    }

    public void setPayTempKalkulasiEmpPajakService(PayTempKalkulasiEmpPajakService payTempKalkulasiEmpPajakService) {
        this.payTempKalkulasiEmpPajakService = payTempKalkulasiEmpPajakService;
    }

    public TaxComponentService getTaxComponentService() {
        return taxComponentService;
    }

    public void setTaxComponentService(TaxComponentService taxComponentService) {
        this.taxComponentService = taxComponentService;
    }

    public String getSearchParameter() {
        return searchParameter;
    }

    public void setSearchParameter(String searchParameter) {
        this.searchParameter = searchParameter;
    }

    public LazyDataModel<PayTempKalkulasiEmpPajak> getLazyDataModel() {
        if (lazyDataModel == null) {
            lazyDataModel = new PayTempKalkulasiEmpPajakLazyDataModel(searchParameter, payTempKalkulasiEmpPajakService, taxComponentId);
        }
        return lazyDataModel;
    }

    public void setLazyDataModel(LazyDataModel<PayTempKalkulasiEmpPajak> lazyDataModel) {
        this.lazyDataModel = lazyDataModel;
    }

    public WtPeriodeService getWtPeriodeService() {
        return wtPeriodeService;
    }

    public void setWtPeriodeService(WtPeriodeService wtPeriodeService) {
        this.wtPeriodeService = wtPeriodeService;
    }

    public Long getTaxComponentId() {
        return taxComponentId;
    }

    public void setTaxComponentId(Long taxComponentId) {
        this.taxComponentId = taxComponentId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getTaxComponentName() {
        return taxComponentName;
    }

    public void setTaxComponentName(String taxComponentName) {
        this.taxComponentName = taxComponentName;
    }

    
}
