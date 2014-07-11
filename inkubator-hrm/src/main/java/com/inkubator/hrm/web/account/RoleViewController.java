/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.account;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.hibernate.exception.ConstraintViolationException;
import org.primefaces.model.LazyDataModel;
import org.springframework.dao.DataIntegrityViolationException;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.HrmRole;
import com.inkubator.hrm.service.HrmRoleService;
import com.inkubator.hrm.web.lazymodel.HrmRoleLazyModel;
import com.inkubator.hrm.web.search.HrmRoleSearchParameter;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
 *
 * @author Deni Husni FR
 */
@ManagedBean(name = "roleViewController")
@ViewScoped
public class RoleViewController extends BaseController {

    private HrmRoleSearchParameter hrmRoleSearchParameter;
    private LazyDataModel<HrmRole> lazyDataHrmRole;
    @ManagedProperty(value = "#{hrmRoleService}")
    private HrmRoleService hrmRoleService;
    private HrmRole selectedHrmRole;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        hrmRoleSearchParameter = new HrmRoleSearchParameter();
    }
    
    @PreDestroy
    private void cleanAndExit() {
        hrmRoleSearchParameter=null;
        lazyDataHrmRole=null;
        hrmRoleService=null;
        selectedHrmRole=null;
        
    }
    
    public HrmRole getSelectedHrmRole() {
        return selectedHrmRole;
    }

    public void setSelectedHrmRole(HrmRole selectedHrmRole) {
        this.selectedHrmRole = selectedHrmRole;
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
        if (lazyDataHrmRole == null) {
            lazyDataHrmRole = new HrmRoleLazyModel(hrmRoleSearchParameter, hrmRoleService);
        }
        return lazyDataHrmRole;
    }

    public void setLazyDataHrmRole(LazyDataModel<HrmRole> lazyDataHrmRole) {
        this.lazyDataHrmRole = lazyDataHrmRole;
    }

    public void doSearch() {
        lazyDataHrmRole = null;
    }

    public void doDetail() {
        try {
            selectedHrmRole = this.hrmRoleService.getEntiyByPK(selectedHrmRole.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public void doDelete() {
        try {
            this.hrmRoleService.delete(selectedHrmRole);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            LOGGER.error("Error", ex);
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public String doAdd() {
    	return "/protected/account/role_form.htm?faces-redirect=true";
    }

    public String doEdit() {
    	return "/protected/account/role_form.htm?faces-redirect=true&execution=e" + selectedHrmRole.getId();
    }

    public void onDelete() {
        try {
            selectedHrmRole = this.hrmRoleService.getEntiyByPK(selectedHrmRole.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    } 
}
