/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.personalia;

import com.inkubator.hrm.entity.LoanNewSchema;
import com.inkubator.hrm.service.LoanNewSchemaService;
import com.inkubator.hrm.web.lazymodel.LoanNewSchemaLazyDataModel;
import com.inkubator.hrm.web.search.LoanNewSchemaSearchParameter;
import com.inkubator.webcore.controller.BaseController;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author Deni
 */
@ManagedBean(name = "loanNewSchemaViewController")
@ViewScoped
public class LoanNewSchemaViewController extends BaseController{
    @ManagedProperty(value = "#{loanNewSchemaService}")
    private LoanNewSchemaService service;
    private LoanNewSchemaSearchParameter searchParameter;
    private LazyDataModel<LoanNewSchema> lazyDataModel;
    private LoanNewSchema selected;
    
    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        searchParameter = new LoanNewSchemaSearchParameter();
    }

    @PreDestroy
    private void cleanAndExit() {
        searchParameter = null;
        lazyDataModel = null;
        service = null;
        selected = null;
    }
    
    public void doSearch() {
        lazyDataModel = null;
    }

    public void doSelectEntity() {
        try {
            selected = this.service.getEntiyByPK(selected.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public void doAdd() {
        showDialog(null);
    }
    
    public void doEdit() {
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> dataIsi = new ArrayList<>();
        dataIsi.add(String.valueOf(selected.getId()));
        dataToSend.put("loanNewSchemaId", dataIsi);
        showDialog(dataToSend);
    }
    
    public void showDialog(Map<String, List<String>> params){
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 500);
        options.put("contentHeight", 460);
        RequestContext.getCurrentInstance().openDialog("loan_new_schema_form", options, params);
    }
    
    @Override
    public void onDialogReturn(SelectEvent event) {
        doSearch();
        super.onDialogReturn(event);

    }
    
    public LoanNewSchemaService getService() {
        return service;
    }

    public void setService(LoanNewSchemaService service) {
        this.service = service;
    }

    public LoanNewSchemaSearchParameter getSearchParameter() {
        return searchParameter;
    }

    public void setSearchParameter(LoanNewSchemaSearchParameter searchParameter) {
        this.searchParameter = searchParameter;
    }

    public LazyDataModel<LoanNewSchema> getLazyDataModel() {
        if (lazyDataModel == null) {
            lazyDataModel = new LoanNewSchemaLazyDataModel(searchParameter, service);
        }
        return lazyDataModel;
    }

    public void setLazyDataModel(LazyDataModel<LoanNewSchema> lazyDataModel) {
        this.lazyDataModel = lazyDataModel;
    }

    public LoanNewSchema getSelected() {
        return selected;
    }

    public void setSelected(LoanNewSchema selected) {
        this.selected = selected;
    }
    
    
}
