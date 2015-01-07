/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.sms.gateway.web;

import com.inkubator.sms.gateway.entity.LoginHistory;
import com.inkubator.sms.gateway.service.LoginHistoryService;
import com.inkubator.sms.gateway.web.lazymodel.LoginHistoryLazy;
import com.inkubator.webcore.controller.BaseController;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author deni
 */
@ManagedBean(name = "loginHistoryViewController")
@ViewScoped
public class LoginHistoryViewController extends BaseController {

    @ManagedProperty(value = "#{loginHistoryService}")
    private LoginHistoryService loginHistoryService;
    private LazyDataModel<LoginHistory> lazyDataModel;
    private String parameter;
    private LoginHistory selectedLoginHistory;

    @Override
    public void initialization() {
        super.initialization();
    }

    public void doSearch() {
        lazyDataModel = null;
    }

    public LoginHistoryService getLoginHistoryService() {
        return loginHistoryService;
    }

    public void setLoginHistoryService(LoginHistoryService loginHistoryService) {
        this.loginHistoryService = loginHistoryService;
    }

    public LazyDataModel<LoginHistory> getLazyDataModel() {
        if (lazyDataModel == null) {
            lazyDataModel = new LoginHistoryLazy(parameter, loginHistoryService);
        }
        return lazyDataModel;
    }

    public void setLazyDataModel(LazyDataModel<LoginHistory> lazyDataModel) {
        this.lazyDataModel = lazyDataModel;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public LoginHistory getSelectedLoginHistory() {
        return selectedLoginHistory;
    }

    public void setSelectedLoginHistory(LoginHistory selectedLoginHistory) {
        this.selectedLoginHistory = selectedLoginHistory;
    }

}
