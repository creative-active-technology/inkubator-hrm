/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.personalia;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.TravelComponentCostRate;
import com.inkubator.hrm.service.TravelComponentCostRateService;
import com.inkubator.hrm.web.lazymodel.TravelComponentCostRateLazyDataModel;
import com.inkubator.hrm.web.search.TravelComponentCostRateSearchParameter;
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
@ManagedBean(name = "travelComponentCostRateViewController")
@ViewScoped
public class TravelComponentCostRateViewController extends BaseController{
    @ManagedProperty(value = "#{travelComponentCostRateService}")
    private TravelComponentCostRateService service;
    private TravelComponentCostRateSearchParameter searchParameter;
    private LazyDataModel<TravelComponentCostRate> lazy;
    private TravelComponentCostRate selected;

    
    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        searchParameter = new TravelComponentCostRateSearchParameter();
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
        values.add(String.valueOf(selected.getId()));
        dataToSend.put("travelComponentCostRateId", values);
        showDialog(dataToSend);

    }
    
    private void showDialog(Map<String, List<String>> params) {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 450);
        options.put("contentHeight", 500);
        RequestContext.getCurrentInstance().openDialog("travel_comp_cost_rate_form", options, params);
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

    public TravelComponentCostRateService getService() {
        return service;
    }

    public void setService(TravelComponentCostRateService service) {
        this.service = service;
    }

    public TravelComponentCostRateSearchParameter getSearchParameter() {
        return searchParameter;
    }

    public void setSearchParameter(TravelComponentCostRateSearchParameter searchParameter) {
        this.searchParameter = searchParameter;
    }

    public LazyDataModel<TravelComponentCostRate> getLazy() {
        if (lazy == null) {
            lazy = new TravelComponentCostRateLazyDataModel(searchParameter, service);
        }
        return lazy;
    }

    public void setLazy(LazyDataModel<TravelComponentCostRate> lazy) {
        this.lazy = lazy;
    }

    public TravelComponentCostRate getSelected() {
        return selected;
    }

    public void setSelected(TravelComponentCostRate selected) {
        this.selected = selected;
    }
    
     
}
