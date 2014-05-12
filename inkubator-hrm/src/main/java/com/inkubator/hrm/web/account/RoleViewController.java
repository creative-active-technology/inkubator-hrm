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
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;

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

    @PreDestroy
    public void onPostClose() {
        System.out.println("Bersih -berisesfsdhfkh");
    }

    public void doDetail() {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 400);
//        options.put("contentHeight", 340);
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> dataIsi = new ArrayList<>();
        dataIsi.add(String.valueOf(selectedHrmRole.getId()));
        dataToSend.put("param", dataIsi);
        RequestContext.getCurrentInstance().openDialog("role_detail", options, dataToSend);
    }

    public void doDelete() {
        try {
            hrmRoleService.delete(selectedHrmRole);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_WARN, "global.delete", "global.delete_info",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
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

    public void onDialogReturn(SelectEvent event) {
        lazyDataHrmRole = null;
        System.out.println(" shhsdfhsdhdsfhdsfhdh");
        String condition = (String) event.getObject();
        System.out.println(" kodisi " + condition);
        if (condition.equalsIgnoreCase(HRMConstant.SAVE_CONDITION)) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        }
        if (condition.equalsIgnoreCase(HRMConstant.UPDATE_CONDITION)) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.save_info", "global.update_successfully",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        }

    }
}
