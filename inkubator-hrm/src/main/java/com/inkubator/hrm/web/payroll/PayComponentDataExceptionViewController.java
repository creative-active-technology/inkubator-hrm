/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.payroll;

import com.inkubator.hrm.entity.PayComponentDataException;
import com.inkubator.hrm.service.PayComponentDataExceptionService;
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
 * @author Deni
 */
@ManagedBean(name = "payComponentDataExceptionViewController")
@ViewScoped
public class PayComponentDataExceptionViewController extends BaseController{
    @ManagedProperty(value = "#{payComponentDataExceptionService}")
    private PayComponentDataExceptionService service;
    private PayComponentDataExceptionSearchParameter searchParameter;
    private LazyDataModel<PayComponentDataException> lazyDataModel;
    private PayComponentDataException selected;
    
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
    
    public void doSelectEntity() {
        try {
            selected = this.service.getEntiyByPK(selected.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }
    
    public String doDetail() {
        return "/protected/payroll/pay_comp_ex_detail.htm?faces-redirect=true&execution=e" + selected.getId();
    }

    public PayComponentDataExceptionService getService() {
        return service;
    }

    public void setService(PayComponentDataExceptionService service) {
        this.service = service;
    }

    public PayComponentDataExceptionSearchParameter getSearchParameter() {
        return searchParameter;
    }

    public void setSearchParameter(PayComponentDataExceptionSearchParameter searchParameter) {
        this.searchParameter = searchParameter;
    }

    public LazyDataModel<PayComponentDataException> getLazyDataModel() {
        return lazyDataModel;
    }

    public void setLazyDataModel(LazyDataModel<PayComponentDataException> lazyDataModel) {
        this.lazyDataModel = lazyDataModel;
    }

    public PayComponentDataException getSelected() {
        return selected;
    }

    public void setSelected(PayComponentDataException selected) {
        this.selected = selected;
    }
    
    
}
