/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.sms.gateway.web;

import com.inkubator.sms.gateway.entity.User;
import com.inkubator.sms.gateway.service.UserService;
import com.inkubator.sms.gateway.web.lazymodel.UserLazy;
import com.inkubator.webcore.controller.BaseController;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author deni
 */
@ManagedBean(name = "userViewController")
@ViewScoped
public class UserViewController extends BaseController {

    @ManagedProperty(value = "#{userService}")
    private UserService userService;
    private LazyDataModel<User> lazyDataModel;
    private String parameter;
    private User selectedUser;

    @Override
    public void initialization() {
        super.initialization();
    }

    public void doSearch() {
        lazyDataModel = null;
    }

    public String doAdd() {
        return "/protected/account/user_form.htm?faces-redirect=true";
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public LazyDataModel<User> getLazyDataModel() {
        if (lazyDataModel == null) {
            lazyDataModel = new UserLazy(parameter, userService);
        }
        return lazyDataModel;
    }

    public void setLazyDataModel(LazyDataModel<User> lazyDataModel) {
        this.lazyDataModel = lazyDataModel;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public User getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }

}
