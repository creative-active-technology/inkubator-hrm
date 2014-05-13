/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.account;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.HrmRole;
import com.inkubator.hrm.service.HrmRoleService;
import com.inkubator.hrm.web.model.RoleModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Deni Husni FR
 */
@ManagedBean(name = "roleFormController")
@ViewScoped
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
        isEdit = Boolean.FALSE;
        if (param != null) {
            try {
                HrmRole hrmRole = hrmRoleService.getEntiyByPK(Long.parseLong(param));
                roleModel.setId(hrmRole.getId());
                roleModel.setRoleName(hrmRole.getRoleName());
                roleModel.setDescription(hrmRole.getDescription());
                isEdit = Boolean.TRUE;

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
        HrmRole hrmRole = getEntityFromView(roleModel);
        if (isEdit) {
            doUpdate(hrmRole);
        } else {
            doInsert(hrmRole);
        }
        cleanAndExit();
    }

    public void doClear() {
        roleModel = new RoleModel();
    }

    public void doInsert(HrmRole hrmRole) {
        try {
            boolean isDuplicate = hrmRoleService.getByRoleName(roleModel.getRoleName()) != null;
            if (isDuplicate) {
                System.out.println(" data sudah ada");
                MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "role_form.error_duplicate_role_name",
                        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            } else {
                hrmRoleService.save(hrmRole);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }

        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public void doUpdate(HrmRole hrmRole) {

        try {
            HrmRole hrmRoleExisting = hrmRoleService.getEntiyByPK(hrmRole.getId());
            boolean isDuplicate = (hrmRoleService.getByRoleName(hrmRole.getRoleName()) != null && !StringUtils.equals(hrmRoleExisting.getRoleName(), hrmRole.getRoleName()));
            if (isDuplicate) {
                MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "role_form.error_duplicate_role_name",
                        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            } else {
                System.out.println(" tidak duplicate");
                hrmRoleService.update(hrmRole);
                System.out.println(" beres update");
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
                System.out.println(" beres update");
            }
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }

    }

    public HrmRole getEntityFromView(RoleModel roleModel) {
        HrmRole hrmRole = new HrmRole();
        if (roleModel.getId() != null) {
            hrmRole.setId(roleModel.getId());
        }
        hrmRole.setRoleName(roleModel.getRoleName());
        hrmRole.setDescription(roleModel.getDescription());
        return hrmRole;
    }

    @PreDestroy
    private void cleanAndExit() {
        roleModel = null;
        isEdit = null;
        hrmRoleService = null;
        System.out.println(" ahhahaha");
    }
}
