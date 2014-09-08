/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.approval;

import com.inkubator.hrm.entity.HrmUser;
import com.inkubator.hrm.service.HrmUserService;
import com.inkubator.webcore.controller.BaseController;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Deni Husni FR
 */
@ManagedBean(name = "userSearchController")
@ViewScoped
public class UserSearchController extends BaseController {

    @ManagedProperty(value = "#{hrmUserService}")
    private HrmUserService hrmUserService;
    private String userName;
    private List<HrmUser> userDataToShow = new ArrayList();

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();

    }

    public void doSearch() {
        try {
            userDataToShow = new ArrayList();
            userDataToShow = hrmUserService.getByName(userName);
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<HrmUser> getUserDataToShow() {
        return userDataToShow;
    }

    public void setUserDataToShow(List<HrmUser> userDataToShow) {
        this.userDataToShow = userDataToShow;
    }

    public void doSelect(HrmUser hrmUser) {
        RequestContext.getCurrentInstance().closeDialog(hrmUser);
        cleanAndExit();
    }

    public void setHrmUserService(HrmUserService hrmUserService) {
        this.hrmUserService = hrmUserService;
    }

    @PreDestroy
    public void cleanAndExit() {
        hrmUserService = null;
        userName = null;
        userDataToShow = null;

    }

}
