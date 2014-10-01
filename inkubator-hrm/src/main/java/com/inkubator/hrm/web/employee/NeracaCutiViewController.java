/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.employee;

import com.inkubator.hrm.entity.NeracaCuti;
import com.inkubator.hrm.service.NeracaCutiService;
import com.inkubator.hrm.web.lazymodel.LeaveDistributionLazyDataModel;
import com.inkubator.hrm.web.lazymodel.NeracaCutiLazyDataModel;
import com.inkubator.hrm.web.search.NeracaCutiSearchParameter;
import com.inkubator.webcore.controller.BaseController;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author Deni
 */
@ManagedBean(name = "neracaCutiViewController")
@ViewScoped
public class NeracaCutiViewController extends BaseController {
    @ManagedProperty(value = "#{neracaCutiService}")
    private NeracaCutiService service;
    private NeracaCutiSearchParameter searchParameter;
    private LazyDataModel<NeracaCuti> lazy;
    private NeracaCuti selected;

    
    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        searchParameter = new NeracaCutiSearchParameter();
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

    public NeracaCutiService getService() {
        return service;
    }

    public void setService(NeracaCutiService service) {
        this.service = service;
    }

    public NeracaCutiSearchParameter getSearchParameter() {
        return searchParameter;
    }

    public void setSearchParameter(NeracaCutiSearchParameter searchParameter) {
        this.searchParameter = searchParameter;
    }

    public LazyDataModel<NeracaCuti> getLazy() {
        if (lazy == null) {
            lazy = new NeracaCutiLazyDataModel(searchParameter, service);
        }
        return lazy;
    }

    public void setLazy(LazyDataModel<NeracaCuti> lazy) {
        this.lazy = lazy;
    }

    public NeracaCuti getSelected() {
        return selected;
    }

    public void setSelected(NeracaCuti selected) {
        this.selected = selected;
    }
    
    
}
