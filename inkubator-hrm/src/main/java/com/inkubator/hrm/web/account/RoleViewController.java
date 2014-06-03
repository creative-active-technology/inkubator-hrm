/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.account;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.HrmRole;
import com.inkubator.hrm.service.HrmRoleService;
import com.inkubator.hrm.web.lazymodel.HrmRoleLazyModel;
import com.inkubator.hrm.web.search.HrmRoleSearchParameter;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.hibernate.exception.ConstraintViolationException;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.springframework.dao.DataIntegrityViolationException;

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

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        hrmRoleSearchParameter = new HrmRoleSearchParameter();

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

    public void doAdd() {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 400);
        options.put("contentHeight", 340);
//        options.put("closable", false);
//        options.put("height", "auto");

//        options.put("contentHeight", 340);
        RequestContext.getCurrentInstance().openDialog("role_form", options, null);
    }

    public void doEdit() {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 400);
        options.put("contentHeight", 340);
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> dataIsi = new ArrayList<>();
        dataIsi.add(String.valueOf(selectedHrmRole.getId()));
        dataToSend.put("param", dataIsi);
        RequestContext.getCurrentInstance().openDialog("role_form", options, dataToSend);
    }

    @Override
    public void onDialogReturn(SelectEvent event) {
        lazyDataHrmRole = null;
       super.onDialogReturn(event);

    }

    public void onDelete() {
        try {
            selectedHrmRole = this.hrmRoleService.getEntiyByPK(selectedHrmRole.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    @PreDestroy
    private void cleanAndExit() {
        hrmRoleSearchParameter=null;
        lazyDataHrmRole=null;
        hrmRoleService=null;
        selectedHrmRole=null;
        
    }
}
