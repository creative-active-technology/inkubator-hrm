/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.payroll;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.LazyDataModel;

import com.inkubator.hrm.entity.UnregSalary;
import com.inkubator.hrm.service.UnregSalaryService;
import com.inkubator.hrm.web.lazymodel.UnregSpecificSchemeSalaryLazyDataModel;
import com.inkubator.hrm.web.search.UnregSalarySearchParameter;
import com.inkubator.webcore.controller.BaseController;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "unregSpecificSchemeViewController")
@ViewScoped
public class UnregSpecificSchemeViewController extends BaseController {

    @ManagedProperty(value = "#{unregSalaryService}")
    private UnregSalaryService unregSalaryService;
    private UnregSalarySearchParameter searchParameter;
    private LazyDataModel<UnregSalary> lazyDataModel;
    private UnregSalary selected;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        searchParameter = new UnregSalarySearchParameter();
    }

    @PreDestroy
    private void cleanAndExit() {
        searchParameter = null;
        lazyDataModel = null;
        unregSalaryService = null;
        selected = null;
    }

    public void doSearch() {
        lazyDataModel = null;
    }

    public void doSelectEntity() {
        try {
            selected = this.unregSalaryService.getEntiyByPK(selected.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }
    
    public String doDetail(){
    	return "/protected/payroll/unreg_specific_scheme_detail.htm?faces-redirect=true&execution=e" + selected.getId();
    }
    
    public UnregSalaryService getUnregSalaryService() {
        return unregSalaryService;
    }

    public void setUnregSalaryService(UnregSalaryService unregSalaryService) {
        this.unregSalaryService = unregSalaryService;
    }

    public UnregSalarySearchParameter getSearchParameter() {
        return searchParameter;
    }

    public void setSearchParameter(UnregSalarySearchParameter searchParameter) {
        this.searchParameter = searchParameter;
    }

    public LazyDataModel<UnregSalary> getLazyDataModel() {
        if (lazyDataModel == null) {
            lazyDataModel = new UnregSpecificSchemeSalaryLazyDataModel(searchParameter, unregSalaryService);
        }
        return lazyDataModel;
    }

    public void setLazyDataModel(LazyDataModel<UnregSalary> lazyDataModel) {
        this.lazyDataModel = lazyDataModel;
    }

    public UnregSalary getSelected() {
        return selected;
    }

    public void setSelected(UnregSalary selected) {
        this.selected = selected;
    }

}
