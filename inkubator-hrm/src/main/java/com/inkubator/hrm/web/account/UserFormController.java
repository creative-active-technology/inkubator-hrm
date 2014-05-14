/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.account;

import com.inkubator.hrm.entity.HrmRole;
import com.inkubator.hrm.service.HrmRoleService;
import com.inkubator.hrm.service.HrmUserService;
import com.inkubator.hrm.web.model.UserModel;
import com.inkubator.webcore.controller.BaseController;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.DualListModel;

/**
 *
 * @author Deni Husni FR
 */
@ManagedBean(name = "userFormController")
@ViewScoped
public class UserFormController extends BaseController {

    private DualListModel<HrmRole> dualListModel = new DualListModel<>();
    @ManagedProperty(value = "#{hrmUserService}")
    private HrmUserService hrmUserService;
    @ManagedProperty(value = "#{hrmRoleService}")
    private HrmRoleService hrmRoleService;
    private UserModel userModel;

    public void setHrmUserService(HrmUserService hrmUserService) {
        this.hrmUserService = hrmUserService;
    }

    public void setHrmRoleService(HrmRoleService hrmRoleService) {
        this.hrmRoleService = hrmRoleService;
    }

    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            List<HrmRole> sourceSpiRole = this.hrmRoleService.getAllData();
            dualListModel.setSource(sourceSpiRole);
            userModel = new UserModel();
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public DualListModel<HrmRole> getDualListModel() {
        return dualListModel;
    }

    public void setDualListModel(DualListModel<HrmRole> dualListModel) {
        this.dualListModel = dualListModel;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public String doBack() {
        return "/protected/account/user_view.htm?faces-redirect=true";
    }

    public void doSave() {

    }

    public void doClear() {
        System.out.println(" hhsdhfhds");
        userModel = new UserModel();
    }
}
