/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.personalia;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.SystemScoring;
import com.inkubator.hrm.service.SystemScoringService;
import com.inkubator.hrm.web.lazymodel.SystemScoringLazyDataModel;
import com.inkubator.hrm.web.search.SystemScoringSearchParameter;
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
@ManagedBean(name = "systemScoringViewController")
@ViewScoped
public class SystemScoringViewController extends BaseController {

    @ManagedProperty(value = "#{systemScoringService}")
    private SystemScoringService service;
    private SystemScoringSearchParameter searchParameter;
    private LazyDataModel<SystemScoring> lazyDataModel;
    private SystemScoring selected;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        searchParameter = new SystemScoringSearchParameter();
    }

    @PreDestroy
    private void cleanAndExit() {
        searchParameter = null;
        lazyDataModel = null;
        service = null;
        selected = null;

    }

    public void doSearch() {
        resetLazy();
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
        options.put("contentHeight", 370);
        RequestContext.getCurrentInstance().openDialog("system_scoring_form", options, params);
    }

    public void doAdd() {
        showDialog(null);
    }

    public String doDetail(){
        return "/protected/personalia/system_scoring_detail.htm?faces-redirect=true&execution=e" + selected.getId();
    }
    
    public void doEdit() {
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> dataIsi = new ArrayList<>();
        dataIsi.add(String.valueOf(selected.getId()));
        dataToSend.put("systemScoringId", dataIsi);
        showDialog(dataToSend);
    }

    @Override
    public void onDialogReturn(SelectEvent event) {
        resetLazy();
        super.onDialogReturn(event);

    }

    public void resetLazy(){
        lazyDataModel = null;
    }
    
    public void onDelete() {
        try {
            selected = this.service.getEntiyByPK(selected.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public SystemScoringService getService() {
        return service;
    }

    public void setService(SystemScoringService service) {
        this.service = service;
    }

    public SystemScoringSearchParameter getSearchParameter() {
        return searchParameter;
    }

    public void setSearchParameter(SystemScoringSearchParameter searchParameter) {
        this.searchParameter = searchParameter;
    }

    public LazyDataModel<SystemScoring> getLazyDataModel() {
        if(lazyDataModel == null){
            lazyDataModel = new SystemScoringLazyDataModel(searchParameter, service);
        }
        return lazyDataModel;
    }

    public void setLazyDataModel(LazyDataModel<SystemScoring> lazyDataModel) {
        this.lazyDataModel = lazyDataModel;
    }

    public SystemScoring getSelected() {
        return selected;
    }

    public void setSelected(SystemScoring selected) {
        this.selected = selected;
    }
    
    
}
