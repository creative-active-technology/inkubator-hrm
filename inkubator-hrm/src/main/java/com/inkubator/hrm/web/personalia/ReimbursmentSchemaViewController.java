/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.personalia;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.ReimbursmentSchema;
import com.inkubator.hrm.service.ReimbursmentSchemaService;
import com.inkubator.hrm.service.impl.ReimbursmentSchemaServiceImpl;
import com.inkubator.hrm.web.lazymodel.ReimbursmentSchemaLazyDataModel;
import com.inkubator.hrm.web.search.ReimbursmentSchemaSearchParameter;
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
@ManagedBean(name = "reimbursmentSchemaViewController")
@ViewScoped
public class ReimbursmentSchemaViewController extends BaseController{
    @ManagedProperty(value = "#{reimbursmentSchemaService}")
    private ReimbursmentSchemaService service;
    private ReimbursmentSchemaSearchParameter searchParameter;
    private LazyDataModel<ReimbursmentSchema> lazy;
    private ReimbursmentSchema selected;
    
    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        searchParameter = new ReimbursmentSchemaSearchParameter();
    }
    
    @PreDestroy
    private void cleanAndExit() {
        searchParameter=null;
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
    
    public void doSelectEntityWithAllRelation() {
        try {
            selected = this.service.getEntityByPkWithAllRelation(selected.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }
    
    public String doDetail(){
        return "/protected/personalia/reimbursment_schema_detail.htm?faces-redirect=true&execution=e" + selected.getId();
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
    
    public String doAdd() {
//        showDialog(null);
        return "/protected/personalia/reimbursment_schema_form.htm?faces-redirect=true";
    }
    
    public String doUpdate() {
        return "/protected/personalia/reimbursment_schema_form.htm?faces-redirect=true&execution=e" + selected.getId();
    }
    
    private void showDialog(Map<String, List<String>> params) {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 500);
        options.put("contentHeight", 500);
        RequestContext.getCurrentInstance().openDialog("reimbursment_schema_form", options, params);
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

    public ReimbursmentSchemaService getService() {
        return service;
    }

    public void setService(ReimbursmentSchemaService service) {
        this.service = service;
    }

    public ReimbursmentSchemaSearchParameter getSearchParameter() {
        return searchParameter;
    }

    public void setSearchParameter(ReimbursmentSchemaSearchParameter searchParameter) {
        this.searchParameter = searchParameter;
    }

    public LazyDataModel<ReimbursmentSchema> getLazy() {
        if (lazy == null) {
            lazy = new ReimbursmentSchemaLazyDataModel(searchParameter, service);
        }
        return lazy;
    }

    public void setLazy(LazyDataModel<ReimbursmentSchema> lazy) {
        this.lazy = lazy;
    }

    public ReimbursmentSchema getSelected() {
        return selected;
    }

    public void setSelected(ReimbursmentSchema selected) {
        this.selected = selected;
    }
}
