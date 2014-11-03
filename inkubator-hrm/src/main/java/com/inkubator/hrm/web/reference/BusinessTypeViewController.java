/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.reference;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.BusinessType;
import com.inkubator.hrm.service.BusinessTypeService;
import com.inkubator.hrm.web.lazymodel.BusinessTypeLazyDataModel;
import com.inkubator.hrm.web.search.BusinessTypeSearchParameter;
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
@ManagedBean(name = "businessTypeViewController")
@ViewScoped
public class BusinessTypeViewController  extends BaseController{
    @ManagedProperty(value = "#{businessTypeService}")
    private BusinessTypeService service;
    private BusinessTypeSearchParameter searchParameter;
    private LazyDataModel<BusinessType> lazy;
    private BusinessType selected;

    
    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        searchParameter = new BusinessTypeSearchParameter();
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
        options.put("contentWidth", 400);
        options.put("contentHeight", 360);
        RequestContext.getCurrentInstance().openDialog("business_type_form", options, params);
    }
    
    public void doAdd() {
        showDialog(null);
    }
    
    public void doEdit() {
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> dataIsi = new ArrayList<>();
        dataIsi.add(String.valueOf(selected.getId()));
        dataToSend.put("businessTypeId", dataIsi);
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

    public BusinessTypeService getService() {
        return service;
    }

    public void setService(BusinessTypeService service) {
        this.service = service;
    }

    public BusinessTypeSearchParameter getSearchParameter() {
        return searchParameter;
    }

    public void setSearchParameter(BusinessTypeSearchParameter searchParameter) {
        this.searchParameter = searchParameter;
    }

    public LazyDataModel<BusinessType> getLazy() {
        if(lazy == null){
            lazy = new BusinessTypeLazyDataModel(searchParameter, service);
        }
        return lazy;
    }

    public void setLazy(LazyDataModel<BusinessType> lazy) {
        this.lazy = lazy;
    }

    public BusinessType getSelected() {
        return selected;
    }

    public void setSelected(BusinessType selected) {
        this.selected = selected;
    }

    
     
     
}
