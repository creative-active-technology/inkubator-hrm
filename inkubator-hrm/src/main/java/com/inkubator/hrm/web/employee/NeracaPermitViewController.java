/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.employee;

import com.inkubator.hrm.entity.NeracaPermit;
import com.inkubator.hrm.service.NeracaPermitService;
import com.inkubator.hrm.web.lazymodel.LeaveDistributionLazyDataModel;
import com.inkubator.hrm.web.lazymodel.NeracaPermitLazyDataModel;
import com.inkubator.hrm.web.search.NeracaPermitSearchParameter;
import com.inkubator.webcore.controller.BaseController;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author Taufik
 */
@ManagedBean(name = "neracaPermitViewController")
@ViewScoped
public class NeracaPermitViewController extends BaseController {
    @ManagedProperty(value = "#{neracaPermitService}")
    private NeracaPermitService service;
    private NeracaPermitSearchParameter searchParameter;
    private LazyDataModel<NeracaPermit> lazy;
    private NeracaPermit selected;

    
    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        searchParameter = new NeracaPermitSearchParameter();
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
            selected = this.service.getEntityByParamWithDetail(selected.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public NeracaPermitService getService() {
        return service;
    }

    public void setService(NeracaPermitService service) {
        this.service = service;
    }

    public NeracaPermitSearchParameter getSearchParameter() {
        return searchParameter;
    }

    public void setSearchParameter(NeracaPermitSearchParameter searchParameter) {
        this.searchParameter = searchParameter;
    }

    public LazyDataModel<NeracaPermit> getLazy() {
        if (lazy == null) {
            lazy = new NeracaPermitLazyDataModel(searchParameter, service);
        }
        return lazy;
    }

    public void setLazy(LazyDataModel<NeracaPermit> lazy) {
        this.lazy = lazy;
    }

    public NeracaPermit getSelected() {
        return selected;
    }

    public void setSelected(NeracaPermit selected) {
        this.selected = selected;
    }
    
    
}
