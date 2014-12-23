/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.payroll;

import com.inkubator.hrm.service.PaySalaryComponentService;
import com.inkubator.hrm.web.lazymodel.PayComponentDataExceptionViewModelLazyDataModel;
import com.inkubator.hrm.web.lazymodel.PaySalaryComponentLazyDataModel;
import com.inkubator.hrm.web.model.PayComponentDataExceptionModelView;
import com.inkubator.hrm.web.search.PayComponentDataExceptionSearchParameter;
import com.inkubator.webcore.controller.BaseController;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author deni
 */
@ManagedBean(name = "payDataComponentExceptionViewController")
@ViewScoped
public class PayDataComponentExceptionViewController extends BaseController {

    @ManagedProperty(value = "#{paySalaryComponentService}")
    private PaySalaryComponentService service;
    private PayComponentDataExceptionSearchParameter searchParameter;
    private LazyDataModel<PayComponentDataExceptionModelView> lazyDataModel;
    private PayComponentDataExceptionModelView selected;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        searchParameter = new PayComponentDataExceptionSearchParameter();
    }

    @PreDestroy
    private void cleanAndExit() {
        searchParameter = null;
        lazyDataModel = null;
        service = null;
        selected = null;
    }

    public void doSearch() {
        lazyDataModel = null;
    }

    public String doDetailForDataException() {
        return "/protected/payroll/pay_component_exception_detail.htm?faces-redirect=true&execution=e" + selected.getPaySalaryComponentId();
    }
    
    public PaySalaryComponentService getService() {
        return service;
    }

    public void setService(PaySalaryComponentService service) {
        this.service = service;
    }

    public PayComponentDataExceptionSearchParameter getSearchParameter() {
        return searchParameter;
    }

    public void setSearchParameter(PayComponentDataExceptionSearchParameter searchParameter) {
        this.searchParameter = searchParameter;
    }

    public LazyDataModel<PayComponentDataExceptionModelView> getLazyDataModel() {
        if (lazyDataModel == null) {
            lazyDataModel = new PayComponentDataExceptionViewModelLazyDataModel(service, searchParameter);
        }
        return lazyDataModel;
    }

    public void setLazyDataModel(LazyDataModel<PayComponentDataExceptionModelView> lazyDataModel) {
        this.lazyDataModel = lazyDataModel;
    }

    public PayComponentDataExceptionModelView getSelected() {
        return selected;
    }

    public void setSelected(PayComponentDataExceptionModelView selected) {
        this.selected = selected;
    }

}
