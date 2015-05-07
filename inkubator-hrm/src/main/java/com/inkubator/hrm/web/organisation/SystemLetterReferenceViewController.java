/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.organisation;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.SystemLetterReference;
import com.inkubator.hrm.service.SystemLetterReferenceService;
import com.inkubator.hrm.web.lazymodel.SystemLetterReferenceLazyDataModel;
import com.inkubator.hrm.web.search.SystemLetterReferenceSearchParameter;
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
 * @author Deni
 */
@ManagedBean(name = "systemLetterReferenceViewController")
@ViewScoped
public class SystemLetterReferenceViewController extends BaseController {

    @ManagedProperty(value = "#{systemLetterReferenceService}")
    private SystemLetterReferenceService service;
    private SystemLetterReferenceSearchParameter searchParameter;
    private LazyDataModel<SystemLetterReference> lazyDataModel;
    private SystemLetterReference selected;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        searchParameter = new SystemLetterReferenceSearchParameter();
    }

    @PreDestroy
    private void cleanAndExit() {
        searchParameter = null;
        lazyDataModel = null;
        service = null;
        selected = null;

    }

    public void doResetLazy() {
        lazyDataModel = null;
    }

    public void doSearch() {
        doResetLazy();
    }

    public void doSelectEntity() {
        try {
            selected = this.service.getEntiyByPK(selected.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public void doDelete() {
        try {
            this.service.delete(selected);
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

    public void showDialog(Map<String, List<String>> params) {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 500);
        options.put("contentHeight", 460);
        RequestContext.getCurrentInstance().openDialog("system_letter_form", options, params);
    }

    public String doAdd() {
        return "/protected/organisation/system_letter_form.htm?faces-redirect=true";
    }

    public String doEdit() {
        return "/protected/organisation/system_letter_form.htm?faces-redirect=true&execution=e" + selected.getId();
    }

    @Override
    public void onDialogReturn(SelectEvent event) {
        doResetLazy();
        super.onDialogReturn(event);

    }

    public void onDelete() {
        try {
            selected = this.service.getEntiyByPK(selected.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public SystemLetterReferenceService getService() {
        return service;
    }

    public void setService(SystemLetterReferenceService service) {
        this.service = service;
    }

    public SystemLetterReferenceSearchParameter getSearchParameter() {
        return searchParameter;
    }

    public void setSearchParameter(SystemLetterReferenceSearchParameter searchParameter) {
        this.searchParameter = searchParameter;
    }

    public LazyDataModel<SystemLetterReference> getLazyDataModel() {
        if(lazyDataModel == null){
            lazyDataModel = new SystemLetterReferenceLazyDataModel(searchParameter, service);
        }
        return lazyDataModel;
    }

    public void setLazyDataModel(LazyDataModel<SystemLetterReference> lazyDataModel) {
        this.lazyDataModel = lazyDataModel;
    }

    public SystemLetterReference getSelected() {
        return selected;
    }

    public void setSelected(SystemLetterReference selected) {
        this.selected = selected;
    }
    
    
}