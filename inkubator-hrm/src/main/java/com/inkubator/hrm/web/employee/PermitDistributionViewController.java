/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.employee;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.PermitDistribution;
import com.inkubator.hrm.service.PermitDistributionService;
import com.inkubator.hrm.web.lazymodel.PermitDistributionLazyDataModel;
import com.inkubator.hrm.web.search.PermitDistributionSearchParameter;
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
@ManagedBean(name = "permitDistributionViewController")
@ViewScoped
public class PermitDistributionViewController extends BaseController {
    @ManagedProperty(value = "#{permitDistributionService}")
    private PermitDistributionService permitDistributionService;
    private PermitDistributionSearchParameter searchParameter;
    private LazyDataModel<PermitDistribution> lazy;
    private PermitDistribution selected;

    
    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        searchParameter = new PermitDistributionSearchParameter();
    }
    
    @PreDestroy
    private void cleanAndExit() {
        searchParameter=null;
        lazy=null;
        permitDistributionService=null;
        selected=null;
        
    }
    
    public void doSearch() {
        lazy = null;
    }
    
    public void doSelectEntity() {
        try {
            selected = this.permitDistributionService.getEntityByParamWithDetail(selected.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }
    
    public void doDelete() {
        try {
            this.permitDistributionService.delete(selected);
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
        return "/protected/employee/permit_distribution_form.htm?faces-redirect=true";
    }
    
    public void doUpdate() {
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> values = new ArrayList<>();
        values.add(String.valueOf(selected.getId()));
        dataToSend.put("empDataId", values);
        showDialog(dataToSend);
    }
    
    private void showDialog(Map<String, List<String>> params) {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 400);
        options.put("contentHeight", 300);
        RequestContext.getCurrentInstance().openDialog("permit_distribution_edit", options, params);
    }
    
    @Override
    public void onDialogReturn(SelectEvent event) {
        lazy = null;
       super.onDialogReturn(event);

    }
    
     public void onDelete() {
        try {
            selected = this.permitDistributionService.getEntityByParamWithDetail(selected.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }


    public PermitDistributionSearchParameter getSearchParameter() {
        return searchParameter;
    }

    public void setSearchParameter(PermitDistributionSearchParameter searchParameter) {
        this.searchParameter = searchParameter;
    }

    public LazyDataModel<PermitDistribution> getLazy() {
        if (lazy == null) {
            lazy = new PermitDistributionLazyDataModel(searchParameter, permitDistributionService);
        }
        return lazy;
    }

    public void setLazy(LazyDataModel<PermitDistribution> lazy) {
        this.lazy = lazy;
    }

    public PermitDistribution getSelected() {
        return selected;
    }

    public void setSelected(PermitDistribution selected) {
        this.selected = selected;
    }

    public void setPermitDistributionService(PermitDistributionService permitDistributionService) {
        this.permitDistributionService = permitDistributionService;
    }
     
     
}
