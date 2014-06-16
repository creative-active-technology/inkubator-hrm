/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.account;

import com.inkubator.hrm.entity.PasswordHistory;
import com.inkubator.hrm.service.PasswordHistoryService;
import com.inkubator.hrm.web.lazymodel.PasswordHistoryLazyDataModel;
import com.inkubator.hrm.web.search.PasswordHistorySearchParameter;
import com.inkubator.webcore.controller.BaseController;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author deniarianto
 */
@ManagedBean(name = "passwordHistoryController")
@ViewScoped
public class PasswordHistoryController extends BaseController{
    @ManagedProperty(value = "#{passwordHistoryService}")
    private PasswordHistoryService passwordHistoryService;
    private PasswordHistorySearchParameter passwordHistorySearchParameter;
    private LazyDataModel<PasswordHistory> lazyDataPasswordHistory;
    private PieChartModel pieModel;
    
    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        passwordHistorySearchParameter = new PasswordHistorySearchParameter();
    }
    
    @PreDestroy
    public void cleanAndExit() {
    	passwordHistoryService = null;
    	passwordHistorySearchParameter = null;
    	lazyDataPasswordHistory = null;
    	pieModel = null;
    }

    public PasswordHistoryService getPasswordHistoryService() {
        return passwordHistoryService;
    }

    public void setPasswordHistoryService(PasswordHistoryService passwordHistoryService) {
        this.passwordHistoryService = passwordHistoryService;
    }

    public PasswordHistorySearchParameter getPasswordHistorySearchParameter() {
        return passwordHistorySearchParameter;
    }

    public void setPasswordHistorySearchParameter(PasswordHistorySearchParameter passwordHistorySearchParameter) {
        this.passwordHistorySearchParameter = passwordHistorySearchParameter;
    }

    public LazyDataModel<PasswordHistory> getLazyDataPasswordHistory() {
        if (lazyDataPasswordHistory == null) {
            lazyDataPasswordHistory = new PasswordHistoryLazyDataModel(passwordHistorySearchParameter, passwordHistoryService);
        }
        return lazyDataPasswordHistory;
    }

    public void setLazyDataPasswordHistory(LazyDataModel<PasswordHistory> lazyDataPasswordHistory) {
        this.lazyDataPasswordHistory = lazyDataPasswordHistory;
    }

    public PieChartModel getPieModel() {
        return pieModel;
    }

    public void setPieModel(PieChartModel pieModel) {
        this.pieModel = pieModel;
    }
    
    public void doSearch() {
        lazyDataPasswordHistory = null;
    }
}
