/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.payroll;

import com.inkubator.hrm.entity.PaySalaryComponent;
import com.inkubator.hrm.entity.PayTempKalkulasi;
import com.inkubator.hrm.entity.WtPeriode;
import com.inkubator.hrm.service.PaySalaryComponentService;
import com.inkubator.hrm.service.PayTempKalkulasiService;
import com.inkubator.hrm.service.WtPeriodeService;
import com.inkubator.hrm.web.lazymodel.PaySalaryExecuteDetailLazyDataModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author deni
 */
@ManagedBean(name = "paySalaryExecuteDetailController")
@ViewScoped
public class PaySalaryExecuteDetailController extends BaseController {

    @ManagedProperty(value = "#{payTempKalkulasiService}")
    private PayTempKalkulasiService payTempKalkulasiService;
    @ManagedProperty(value = "#{paySalaryComponentService}")
    private PaySalaryComponentService paySalaryComponentService;
    @ManagedProperty(value = "#{wtPeriodeService}")
    private WtPeriodeService wtPeriodeService;
    private LazyDataModel<PayTempKalkulasi> lazyDataModel;
    private Long paySalaryComponentId;
    private String searchParameter;
    private String componentName;
    private Date startDate;
    private Date endDate;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        String paySalaryComponentIdEx = FacesUtil.getRequestParameter("execution");
        try {
            WtPeriode wtPeriode = wtPeriodeService.getEntityByStatusActive();
            if (paySalaryComponentIdEx != null) {
                paySalaryComponentId = Long.valueOf(paySalaryComponentIdEx.substring(1));
                PaySalaryComponent paySalaryComponent = paySalaryComponentService.getEntiyByPK(paySalaryComponentId);
                componentName = paySalaryComponent.getName();
            }
            if(wtPeriode != null){
                startDate = wtPeriode.getFromPeriode();
                endDate = wtPeriode.getUntilPeriode();
            }
        } catch (Exception ex) {
            Logger.getLogger(PaySalaryExecuteDetailController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @PreDestroy
    public void cleanAndExit() {
        payTempKalkulasiService = null;
        lazyDataModel = null;
        paySalaryComponentId = null;
        searchParameter = null;
        componentName = null;
        endDate = null;
        startDate = null;
        wtPeriodeService = null;
        paySalaryComponentService = null;
    }

    public void doSearch() {
        lazyDataModel = null;
    }
    
    public PayTempKalkulasiService getPayTempKalkulasiService() {
        return payTempKalkulasiService;
    }

    public void setPayTempKalkulasiService(PayTempKalkulasiService payTempKalkulasiService) {
        this.payTempKalkulasiService = payTempKalkulasiService;
    }

    public LazyDataModel<PayTempKalkulasi> getLazyDataModel() {
        if (lazyDataModel == null) {
            lazyDataModel = new PaySalaryExecuteDetailLazyDataModel(searchParameter, payTempKalkulasiService, paySalaryComponentId);
        }
        return lazyDataModel;
    }

    public void setLazyDataModel(LazyDataModel<PayTempKalkulasi> lazyDataModel) {
        this.lazyDataModel = lazyDataModel;
    }

    public Long getPaySalaryComponentId() {
        return paySalaryComponentId;
    }

    public void setPaySalaryComponentId(Long paySalaryComponentId) {
        this.paySalaryComponentId = paySalaryComponentId;
    }

    public String getSearchParameter() {
        return searchParameter;
    }

    public void setSearchParameter(String searchParameter) {
        this.searchParameter = searchParameter;
    }

    public String getComponentName() {
        return componentName;
    }

    public PaySalaryComponentService getPaySalaryComponentService() {
        return paySalaryComponentService;
    }

    public void setPaySalaryComponentService(PaySalaryComponentService paySalaryComponentService) {
        this.paySalaryComponentService = paySalaryComponentService;
    }

    public WtPeriodeService getWtPeriodeService() {
        return wtPeriodeService;
    }

    public void setWtPeriodeService(WtPeriodeService wtPeriodeService) {
        this.wtPeriodeService = wtPeriodeService;
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

    
}
