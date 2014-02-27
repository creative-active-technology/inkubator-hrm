/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web;

import com.inkubator.hrm.entity.HrmRole;
import com.inkubator.hrm.service.HrmRoleService;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Deni Husni FR
 */
@ManagedBean(name = "roleDetailController")
@RequestScoped
public class RoleDetailController extends BaseController {

    private HrmRole selectedHrmRole;
    @ManagedProperty(value = "#{hrmRoleService}")
    private HrmRoleService hrmRoleService;

    public void setHrmRoleService(HrmRoleService hrmRoleService) {
        this.hrmRoleService = hrmRoleService;
    }

    public HrmRole getSelectedHrmRole() {
        return selectedHrmRole;
    }

    public void setSelectedHrmRole(HrmRole selectedHrmRole) {
        this.selectedHrmRole = selectedHrmRole;
    }
    

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        String redirectParam = FacesUtil.getRequestParameter("execution");
        if (redirectParam != null) {
            try {
               selectedHrmRole= hrmRoleService.getEntiyByPK(Long.parseLong(redirectParam.substring(1)));
            } catch (Exception ex) {
                Logger.getLogger(RoleDetailController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
    
    public String backToView(){
        return "/protected/account/role.htm?faces-redirect=true";
    }
    
}
