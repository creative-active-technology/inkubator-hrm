/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.account;

import com.inkubator.hrm.entity.HrmRole;
import com.inkubator.hrm.service.HrmRoleService;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
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

    private HrmRole hrmRole;
    @ManagedProperty(value = "#{hrmRoleService}")
    private HrmRoleService hrmRoleService;

    public void setHrmRoleService(HrmRoleService hrmRoleService) {
        this.hrmRoleService = hrmRoleService;
    }

    @PostConstruct
    @Override
    public void initialization() {
        try {
            String param = FacesUtil.getRequestParameter("param");
            hrmRole = hrmRoleService.getEntiyByPK(Long.parseLong(param));
        } catch (Exception ex) {
           LOGGER.error("Error", ex);
        }
    }

    public HrmRole getHrmRole() {
        return hrmRole;
    }

    public void setHrmRole(HrmRole hrmRole) {
        this.hrmRole = hrmRole;
    }
    
    
    @PreDestroy
    private void cleanAndExit(){
        hrmRoleService=null;
        hrmRole=null;
    }

}
