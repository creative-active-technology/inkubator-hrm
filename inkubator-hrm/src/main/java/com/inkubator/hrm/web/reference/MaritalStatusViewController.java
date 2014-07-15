/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.reference;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.MaritalStatus;
import com.inkubator.hrm.service.MaritalStatusService;
import com.inkubator.hrm.web.lazymodel.MaritalStatusLazyDataModel;
import com.inkubator.hrm.web.search.MaritalStatusSearchParameter;
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
@ManagedBean(name = "maritalStatusController")
@ViewScoped
public class MaritalStatusViewController extends BaseController{
    @ManagedProperty(value = "#{maritalStatusService}")
    private MaritalStatusService service;
    private MaritalStatusSearchParameter search;
    private LazyDataModel<MaritalStatus> lazy;
    private MaritalStatus selected;

    
    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        search = new MaritalStatusSearchParameter();
    }
    
    @PreDestroy
    private void cleanAndExit() {
        search=null;
        lazy=null;
        service=null;
        selected=null;
        
    }
    
    public void doSearch() {
        lazy = null;
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
    
    public void doAdd() {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 400);
        options.put("contentHeight", 280);
        RequestContext.getCurrentInstance().openDialog("marital_status_form", options, null);
    }
    
    public void doEdit() {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 400);
        options.put("contentHeight", 280);
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> dataIsi = new ArrayList<>();
        dataIsi.add(String.valueOf(selected.getId()));
        dataToSend.put("param", dataIsi);
        RequestContext.getCurrentInstance().openDialog("marital_status_form", options, dataToSend);
    }
    
    @Override
    public void onDialogReturn(SelectEvent event) {
        lazy = null;
       super.onDialogReturn(event);

    }
    
     public void onDelete() {
        try {
            selected = this.service.getEntiyByPK(selected.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }
     
    
     
    public MaritalStatusService getService() {
        return service;
    }

    public void setService(MaritalStatusService service) {
        this.service = service;
    }

    public MaritalStatusSearchParameter getSearch() {
        return search;
    }

    public void setSearch(MaritalStatusSearchParameter search) {
        this.search = search;
    }

    public LazyDataModel<MaritalStatus> getLazy() {
        if(lazy == null){
            lazy = new MaritalStatusLazyDataModel(search, service);
        }
        return lazy;
    }

    public void setLazy(LazyDataModel<MaritalStatus> lazy) {
        this.lazy = lazy;
    }

    public MaritalStatus getSelected() {
        return selected;
    }

    public void setSelected(MaritalStatus selected) {
        this.selected = selected;
    }
    
    
}
