/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web;

import com.inkubator.hrm.service.LoginHistoryService;
import com.inkubator.hrm.web.search.LoginHistorySearchParameter;
import com.inkubator.webcore.controller.BaseController;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Deni Husni FR
 */
@ManagedBean(name = "loginHistoryController")
@ViewScoped
public class LoginHistoryController extends BaseController {

    @ManagedProperty(value = "#{loginHistoryService}")
    private LoginHistoryService loginHistoryService;
    private LoginHistorySearchParameter loginHistorySearchParameter;

    public void setLoginHistoryService(LoginHistoryService loginHistoryService) {
        this.loginHistoryService = loginHistoryService;
    }

    
    
}
