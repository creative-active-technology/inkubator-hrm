/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.payroll;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.UnregPayComponents;
import com.inkubator.hrm.entity.UnregPayComponentsId;
import com.inkubator.hrm.service.UnregPayComponentService;
import com.inkubator.hrm.web.lazymodel.UnregPayComponentLazyDataModel;
import com.inkubator.hrm.web.search.UnregPayComponentSearchParameter;
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
 * @author deni
 */
@ManagedBean(name = "unregPayComponentViewController")
@ViewScoped
public class UnregPayComponentViewController extends BaseController {

    @ManagedProperty(value = "#{unregPayComponentService}")
    private UnregPayComponentService unregPayComponentService;
    private UnregPayComponentSearchParameter searchParameter;
    private LazyDataModel<UnregPayComponents> lazyDataModel;
    private UnregPayComponents selected;
    private Long unregSalaryId;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        String unregSalary = FacesUtil.getRequestParameter("execution");
        unregSalaryId = Long.valueOf(unregSalary.substring(1));
        searchParameter = new UnregPayComponentSearchParameter();
    }

    @PreDestroy
    private void cleanAndExit() {
        searchParameter = null;
        lazyDataModel = null;
        unregPayComponentService = null;
        selected = null;
    }

    public void doSearch() {
        lazyDataModel = null;
    }

    public void showDialog(Map<String, List<String>> params) {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 500);
        options.put("contentHeight", 360);
        RequestContext.getCurrentInstance().openDialog("unreg_component_setting_form", options, params);
    }

    public void doAdd() {
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> dataIsi = new ArrayList<>();
        dataIsi.add(String.valueOf(unregSalaryId));
        dataToSend.put("unregSalaryId", dataIsi);
        showDialog(dataToSend);
    }

    public void doEdit() {
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> dataIsi = new ArrayList<>();
        dataIsi.add(String.valueOf(unregSalaryId));
        dataToSend.put("unregSalaryId", dataIsi);
        showDialog(dataToSend);
    }

    @Override
    public void onDialogReturn(SelectEvent event) {
        lazyDataModel = null;
        super.onDialogReturn(event);

    }

    public void doSelectEntity() {
        try {
            selected = this.unregPayComponentService.getEntityByPK(new UnregPayComponentsId(selected.getUnregSalary().getId(), selected.getPaySalaryComponent().getId()));
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }
    
    public void doDelete() {
        try {
            this.unregPayComponentService.delete(selected);
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
        
    public UnregPayComponentService getUnregPayComponentService() {
        return unregPayComponentService;
    }

    public void setUnregPayComponentService(UnregPayComponentService unregPayComponentService) {
        this.unregPayComponentService = unregPayComponentService;
    }

    public UnregPayComponentSearchParameter getSearchParameter() {
        return searchParameter;
    }

    public void setSearchParameter(UnregPayComponentSearchParameter searchParameter) {
        this.searchParameter = searchParameter;
    }

    public LazyDataModel<UnregPayComponents> getLazyDataModel() {
        if (lazyDataModel == null) {
            lazyDataModel = new UnregPayComponentLazyDataModel(searchParameter, unregPayComponentService, unregSalaryId);
        }
        return lazyDataModel;
    }

    public void setLazyDataModel(LazyDataModel<UnregPayComponents> lazyDataModel) {
        this.lazyDataModel = lazyDataModel;
    }

    public UnregPayComponents getSelected() {
        return selected;
    }

    public void setSelected(UnregPayComponents selected) {
        this.selected = selected;
    }

    public Long getUnregSalaryId() {
        return unregSalaryId;
    }

    public void setUnregSalaryId(Long unregSalaryId) {
        this.unregSalaryId = unregSalaryId;
    }

}
