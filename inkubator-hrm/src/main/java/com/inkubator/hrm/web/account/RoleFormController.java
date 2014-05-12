/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.account;

import com.inkubator.hrm.entity.HrmRole;
import com.inkubator.hrm.service.HrmRoleService;
import com.inkubator.hrm.web.model.RoleModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Deni Husni FR
 */
@ManagedBean(name = "roleFormController")
@RequestScoped
public class RoleFormController extends BaseController {

    private RoleModel roleModel;
    private Boolean isEdit;
    @ManagedProperty(value = "#{hrmRoleService}")
    private HrmRoleService hrmRoleService;

    public void setHrmRoleService(HrmRoleService hrmRoleService) {
        this.hrmRoleService = hrmRoleService;
    }
    

    @PostConstruct
    @Override
    public void initialization() {
        String param = FacesUtil.getRequestParameter("param");
        roleModel = new RoleModel();
        if (param != null) {
            try {
                HrmRole hrmRole = hrmRoleService.getEntiyByPK(Long.parseLong(param));
                
            } catch (Exception ex) {
               LOGGER.error("Error", ex);
            }
        }

    }

    public RoleModel getRoleModel() {
        return roleModel;
    }

    public void setRoleModel(RoleModel roleModel) {
        this.roleModel = roleModel;
    }

    public Boolean getIsEdit() {
        return isEdit;
    }

    public void setIsEdit(Boolean isEdit) {
        this.isEdit = isEdit;
    }

    public void doSave() {

        RequestContext.getCurrentInstance().closeDialog("");
    }

    public void doClear() {
        roleModel = new RoleModel();
    }
}
