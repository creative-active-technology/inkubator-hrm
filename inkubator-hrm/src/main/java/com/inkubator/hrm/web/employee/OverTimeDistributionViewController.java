/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.employee;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.OverTimeDistribution;
import com.inkubator.hrm.service.OverTimeDistributionService;
import com.inkubator.hrm.web.lazymodel.OverTimeDistributionLazyDataModel;
import com.inkubator.hrm.web.search.OverTimeDistributionSearchParameter;
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
@ManagedBean(name = "overTimeDistributionViewController")
@ViewScoped
public class OverTimeDistributionViewController extends BaseController{
    @ManagedProperty(value = "#{overTimeDistributionService}")
    private OverTimeDistributionService overTimeDistributionService;
    private OverTimeDistributionSearchParameter searchParameter;
    private LazyDataModel<OverTimeDistribution> lazy;
    private OverTimeDistribution selected;
    
    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        searchParameter = new OverTimeDistributionSearchParameter();
    }
    
    @PreDestroy
    private void cleanAndExit() {
        searchParameter=null;
        lazy=null;
        overTimeDistributionService=null;
        selected=null;
        
    }
    
    public void doSearch() {
        lazy = null;
    }
    
    public void doSelectEntity() {
        try {
            selected = this.overTimeDistributionService.getEntityByParamWithDetail(selected.getEmpData().getId(), selected.getWtOverTime().getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }
    
    public void doDelete() {
        try {
            this.overTimeDistributionService.delete(selected);
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
        return "/protected/employee/over_time_distribution_form.htm?faces-redirect=true";
    }
    
    public void doUpdate() {
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> empDataId = new ArrayList<>();
        List<String> overTimeId = new ArrayList<>();
        empDataId.add(String.valueOf(selected.getEmpData().getId()));
        overTimeId.add(String.valueOf(selected.getWtOverTime().getId()));
        dataToSend.put("empDataId", empDataId);
        dataToSend.put("overTimeId", overTimeId);
        showDialog(dataToSend);
    }
    
    private void showDialog(Map<String, List<String>> params) {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 400);
        options.put("contentHeight", 300);
        RequestContext.getCurrentInstance().openDialog("over_time_distribution_edit_form", options, params);
    }
    
    @Override
    public void onDialogReturn(SelectEvent event) {
        lazy = null;
       super.onDialogReturn(event);

    }
    
     public void onDelete() {
        try {
            selected = this.overTimeDistributionService.getEntityByParamWithDetail(selected.getEmpData().getId(), selected.getWtOverTime().getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public OverTimeDistributionService getOverTimeDistributionService() {
        return overTimeDistributionService;
    }

    public void setOverTimeDistributionService(OverTimeDistributionService overTimeDistributionService) {
        this.overTimeDistributionService = overTimeDistributionService;
    }

    public OverTimeDistributionSearchParameter getSearchParameter() {
        return searchParameter;
    }

    public void setSearchParameter(OverTimeDistributionSearchParameter searchParameter) {
        this.searchParameter = searchParameter;
    }

    public LazyDataModel<OverTimeDistribution> getLazy() {
        if (lazy == null) {
            lazy = new OverTimeDistributionLazyDataModel(searchParameter, overTimeDistributionService);
        }
        return lazy;
    }

    public void setLazy(LazyDataModel<OverTimeDistribution> lazy) {
        this.lazy = lazy;
    }

    public OverTimeDistribution getSelected() {
        return selected;
    }

    public void setSelected(OverTimeDistribution selected) {
        this.selected = selected;
    }
     
     
}
