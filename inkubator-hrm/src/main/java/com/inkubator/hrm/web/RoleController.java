/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web;

import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.HrmRole;
import com.inkubator.hrm.service.HrmRoleService;
import com.inkubator.hrm.web.lazymodel.HrmRoleLazyModel;
import com.inkubator.hrm.web.search.HrmRoleSearchParameter;
import com.inkubator.securitycore.util.UserInfoUtil;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author Deni Husni FR
 */
@ManagedBean(name = "roleController")
@ViewScoped
public class RoleController extends BaseController {

    private HrmRoleSearchParameter hrmRoleSearchParameter;
    private LazyDataModel<HrmRole> lazyDataHrmRole;
    @ManagedProperty(value = "#{hrmRoleService}")
    private HrmRoleService hrmRoleService;
    private String roleName;
    private String description;
    private Boolean isEdit;
    private String defaultCommand;
    private HrmRole selectedHrmRole;

    public HrmRole getSelectedHrmRole() {
        return selectedHrmRole;
    }

    public void setSelectedHrmRole(HrmRole selectedHrmRole) {
        this.selectedHrmRole = selectedHrmRole;
    }

    public String getDefaultCommand() {
        return defaultCommand;
    }

    public void setDefaultCommand(String defaultCommand) {
        this.defaultCommand = defaultCommand;
    }

    public Boolean isIsEdit() {
        return isEdit;
    }

    public void setIsEdit(Boolean isEdit) {
        this.isEdit = isEdit;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setHrmRoleService(HrmRoleService hrmRoleService) {
        this.hrmRoleService = hrmRoleService;
    }

    public HrmRoleSearchParameter getHrmRoleSearchParameter() {
        return hrmRoleSearchParameter;
    }

    public void setHrmRoleSearchParameter(HrmRoleSearchParameter hrmRoleSearchParameter) {
        this.hrmRoleSearchParameter = hrmRoleSearchParameter;
    }

    public LazyDataModel<HrmRole> getLazyDataHrmRole() {
        System.out.println("Nisdifjsdf " + hrmRoleService);
        if (lazyDataHrmRole == null) {
            lazyDataHrmRole = new HrmRoleLazyModel(hrmRoleSearchParameter, hrmRoleService);
        }
        return lazyDataHrmRole;
    }

    public void setLazyDataHrmRole(LazyDataModel<HrmRole> lazyDataHrmRole) {
        this.lazyDataHrmRole = lazyDataHrmRole;
    }

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        hrmRoleSearchParameter = new HrmRoleSearchParameter();
        isEdit = Boolean.FALSE;

    }

    public String doSave() {
        String redirect = null;
        HrmRole hrmRole = fromPageUIToEntity();
        try {
            if (isEdit) {
                hrmRole.setUpdatedBy(UserInfoUtil.getUserName());
                hrmRole.setUpdatedOn(new Date());
                hrmRole.setId(selectedHrmRole.getId());
                hrmRoleService.update(hrmRole);
                MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save", "global.update_konfirmasi",
                        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            } else {
                System.out.println(" inii save kondosid");
                hrmRole.setCreatedBy(UserInfoUtil.getUserName());
                hrmRole.setCreatedOn(new Date());
                hrmRoleService.save(hrmRole);
                MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save", "global.save_konfirmasi",
                        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            }
            lazyDataHrmRole = null;
            doClearInput();
            isEdit = Boolean.FALSE;
            redirect = "/protected/account/role_detail.htm?faces-redirect=true&execution=e" + hrmRole.getId();
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
            if (ex.getCause().toString().equalsIgnoreCase("org.hibernate.exception.ConstraintViolationException: could not execute statement")) {
                MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.save_error", "roleform.error_duplicate",
                        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            }
            redirect = null;
        }
        return redirect;
    }

    private HrmRole fromPageUIToEntity() {
        HrmRole hrmRole = new HrmRole();
        hrmRole.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(12)));
        hrmRole.setRoleName(roleName);
        hrmRole.setDescription(description);
        return hrmRole;
    }

    private void doClearInput() {
        roleName = null;
        description = null;
    }

    public void doSearch() {
        lazyDataHrmRole = null;
    }

    @PreDestroy
    public void onPostClose() {
        System.out.println("Bersih -berisesfsdhfkh");
    }

    public String doDetail() {
        String redirect = null;
        try {
            selectedHrmRole = hrmRoleService.getEntiyByPK(selectedHrmRole.getId());
            redirect = "/protected/account/role_detail.htm?faces-redirect=true&execution=e" + selectedHrmRole.getId();
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
        return redirect;
    }

    public void doEdit() {
        isEdit = Boolean.TRUE;
        try {
            selectedHrmRole = hrmRoleService.getEntiyByPK(selectedHrmRole.getId());
            roleName = selectedHrmRole.getRoleName();
            description = selectedHrmRole.getDescription();
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public void onDetail() {
        try {
            selectedHrmRole = hrmRoleService.getEntiyByPK(selectedHrmRole.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public void doDelete() {
        try {
            hrmRoleService.delete(selectedHrmRole);
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public void newData() {
        System.out.println("snkdsjkdsjfdsjfdk");
        doClearInput();
        isEdit = Boolean.FALSE;
    }
}
