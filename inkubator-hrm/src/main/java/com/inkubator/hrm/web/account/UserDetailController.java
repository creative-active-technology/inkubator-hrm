/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.account;

import com.inkubator.hrm.entity.HrmUser;
import com.inkubator.hrm.service.HrmUserService;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Deni Husni FR
 */
@ManagedBean(name = "userDetailController")
@ViewScoped
public class UserDetailController extends BaseController {

    private HrmUser selectedHrmUser;
    @ManagedProperty(value = "#{hrmUserService}")
    private HrmUserService hrmUserService;

    public void setHrmUserService(HrmUserService hrmUserService) {
        this.hrmUserService = hrmUserService;
    }

    public HrmUser getSelectedHrmUser() {
        return selectedHrmUser;
    }

    public void setSelectedHrmUser(HrmUser selectedHrmUser) {
        this.selectedHrmUser = selectedHrmUser;
    }

    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            String userId = FacesUtil.getRequestParameter("execution");
            System.out.println(" Niai nya juga guery" + FacesUtil.getRequest().getQueryString());
            selectedHrmUser = hrmUserService.getEntiyByPkWithDetail(Long.parseLong(userId.substring(1)));
        } catch (Exception ex) {
            LOGGER.error("Error", ex);

        }
    }

    public String doBack() {
        return "/protected/account/user_view.htm?faces-redirect=true";
    }

    public String doEdit() {
        return "/protected/account/user_form.htm?faces-redirect=true&execution=e" + selectedHrmUser.getId();
    }

    @PreDestroy
    public void cleanAndExit() {
        selectedHrmUser = null;
        hrmUserService = null;
    }
}
