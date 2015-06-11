/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.account;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.inkubator.hrm.entity.HrmRole;
import com.inkubator.hrm.service.HrmRoleService;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;

/**
 *
 * @author Deni Husni FR
 */
@ManagedBean(name = "roleDetailController")
@ViewScoped
public class RoleDetailController extends BaseController {

    private HrmRole hrmRole;
    @ManagedProperty(value = "#{hrmRoleService}")
    private HrmRoleService hrmRoleService;

    @PostConstruct
    @Override
    public void initialization() {
        try {
            String param = FacesUtil.getRequestParameter("execution");
            System.out.println(param.substring(1) + " paramnya");
            hrmRole = hrmRoleService.getEntityByPkWithMenus(Long.parseLong(param.substring(1)));
        } catch (Exception ex) {
           LOGGER.error("Error", ex);
        }
    }
    
    @PreDestroy
    private void cleanAndExit(){
        hrmRoleService=null;
        hrmRole=null;
    }

    public HrmRole getHrmRole() {
        return hrmRole;
    }

    public void setHrmRole(HrmRole hrmRole) {
        this.hrmRole = hrmRole;
    }

    public void setHrmRoleService(HrmRoleService hrmRoleService) {
        this.hrmRoleService = hrmRoleService;
    }
    
    public String doBack() {
        return "/protected/account/role_view.htm?faces-redirect=true";
    }

    public String doUpdate() {
        return "/protected/account/role_form.htm?faces-redirect=true&execution=e" + hrmRole.getId();
    }
}
