/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.employee;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.LeaveDistribution;
import com.inkubator.hrm.service.LeaveDistributionService;
import com.inkubator.hrm.web.lazymodel.LeaveDistributionLazyDataModel;
import com.inkubator.hrm.web.search.LeaveDistributionSearchParameter;
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
@ManagedBean(name = "leaveDistributionViewController")
@ViewScoped
public class LeaveDistributionViewController extends BaseController {
    @ManagedProperty(value = "#{leaveDistributionService}")
    private LeaveDistributionService service;
    private LeaveDistributionSearchParameter searchParameter;
    private LazyDataModel<LeaveDistribution> lazy;
    private LeaveDistribution selected;

    
    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        searchParameter = new LeaveDistributionSearchParameter();
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
            selected = this.service.getEntityByParamWithDetail(selected.getEmpData().getId());
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
        showDialog(null);
    }
    
    public void doUpdate() {
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> values = new ArrayList<>();
        values.add(String.valueOf(selected.getEmpData().getId()));
        dataToSend.put("empDataId", values);
        showDialog(dataToSend);
    }
    
    private void showDialog(Map<String, List<String>> params) {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 400);
        options.put("contentHeight", 250);
        RequestContext.getCurrentInstance().openDialog("leave_distribution_view", options, params);
    }
    
    @Override
    public void onDialogReturn(SelectEvent event) {
        lazy = null;
       super.onDialogReturn(event);

    }
    
     public void onDelete() {
        try {
            selected = this.service.getEntiyByPK(selected.getEmpData().getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public LeaveDistributionService getService() {
        return service;
    }

    public void setService(LeaveDistributionService service) {
        this.service = service;
    }

    public LeaveDistributionSearchParameter getSearchParameter() {
        return searchParameter;
    }

    public void setSearchParameter(LeaveDistributionSearchParameter searchParameter) {
        this.searchParameter = searchParameter;
    }

    public LazyDataModel<LeaveDistribution> getLazy() {
        if (lazy == null) {
            lazy = new LeaveDistributionLazyDataModel(searchParameter, service);
        }
        return lazy;
    }

    public void setLazy(LazyDataModel<LeaveDistribution> lazy) {
        this.lazy = lazy;
    }

    public LeaveDistribution getSelected() {
        return selected;
    }

    public void setSelected(LeaveDistribution selected) {
        this.selected = selected;
    }
     
     
}
