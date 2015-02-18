/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.reference;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.ResourceName;
import com.inkubator.hrm.service.ResourceNameService;
import com.inkubator.hrm.web.lazymodel.ResourceNameLazyDataModel;
import com.inkubator.hrm.web.search.ResourceNameSearchParameter;
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
@ManagedBean(name = "resourceNameViewController")
@ViewScoped
public class ResourceNameViewController extends BaseController{
    @ManagedProperty(value = "#{resourceNameService}")
    private ResourceNameService service;
    private ResourceNameSearchParameter searchParameter;
    private LazyDataModel<ResourceName> lazy;
    private ResourceName selected;

    
    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        searchParameter = new ResourceNameSearchParameter();
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
            selected = this.service.getEntityByPkWithDetail(selected.getId());
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
    
    public void showDialog(Map<String, List<String>> params){
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 460);
        options.put("contentHeight", 370);
        RequestContext.getCurrentInstance().openDialog("resource_name_form", options, params);
    }
    
    public void doAdd() {
        showDialog(null);
    }
    
    public void doEdit() {
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> dataIsi = new ArrayList<>();
        dataIsi.add(String.valueOf(selected.getId()));
        dataToSend.put("resourceNameId", dataIsi);
        showDialog(dataToSend);
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

    public ResourceNameService getService() {
        return service;
    }

    public void setService(ResourceNameService service) {
        this.service = service;
    }

    public ResourceNameSearchParameter getSearchParameter() {
        return searchParameter;
    }

    public void setSearchParameter(ResourceNameSearchParameter searchParameter) {
        this.searchParameter = searchParameter;
    }

    public LazyDataModel<ResourceName> getLazy() {
        if(lazy == null){
            lazy = new ResourceNameLazyDataModel(searchParameter, service);
        }
        return lazy;
    }

    public void setLazy(LazyDataModel<ResourceName> lazy) {
        this.lazy = lazy;
    }

    public ResourceName getSelected() {
        return selected;
    }

    public void setSelected(ResourceName selected) {
        this.selected = selected;
    }
     
     
}
