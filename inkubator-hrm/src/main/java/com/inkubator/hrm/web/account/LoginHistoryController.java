/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.account;

import com.inkubator.hrm.entity.LoginHistory;
import com.inkubator.hrm.service.LoginHistoryService;
import com.inkubator.hrm.web.lazymodel.LoginHistoryLazyDataModel;
import com.inkubator.hrm.web.search.LoginHistorySearchParameter;
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
 * @author Deni Husni FR,rizkykojek
 */
@ManagedBean(name = "loginHistoryController")
@ViewScoped
public class LoginHistoryController extends BaseController {

    @ManagedProperty(value = "#{loginHistoryService}")
    private LoginHistoryService loginHistoryService;
    private LoginHistorySearchParameter loginHistorySearchParameter;
    private LazyDataModel<LoginHistory> lazyDataLoginHistory;
    private PieChartModel pieModel;
    
    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        loginHistorySearchParameter = new LoginHistorySearchParameter();
       
    }
    
    @PreDestroy
    public void cleanAndExit() {
    	loginHistoryService = null;
    	loginHistorySearchParameter = null;
    	lazyDataLoginHistory = null;
    	pieModel = null;
    }
    
   
    public void setLoginHistoryService(LoginHistoryService loginHistoryService) {
        this.loginHistoryService = loginHistoryService;
    }

    public LoginHistorySearchParameter getLoginHistorySearchParameter() {
        return loginHistorySearchParameter;
    }

    public void setLoginHistorySearchParameter(LoginHistorySearchParameter loginHistorySearchParameter) {
        this.loginHistorySearchParameter = loginHistorySearchParameter;
    }

    public LazyDataModel<LoginHistory> getLazyDataLoginHistory() {
        if (lazyDataLoginHistory == null) {
            lazyDataLoginHistory = new LoginHistoryLazyDataModel(loginHistorySearchParameter, loginHistoryService);
        }
        return lazyDataLoginHistory;
    }

    public void setLazyDataLoginHistory(LazyDataModel<LoginHistory> lazyDataLoginHistory) {
        this.lazyDataLoginHistory = lazyDataLoginHistory;
    }

    public PieChartModel getPieModel() {
        return pieModel;
    }

    public void setPieModel(PieChartModel pieModel) {
        this.pieModel = pieModel;
    }    

    public void doSearch() {
        lazyDataLoginHistory = null;
    }
}
