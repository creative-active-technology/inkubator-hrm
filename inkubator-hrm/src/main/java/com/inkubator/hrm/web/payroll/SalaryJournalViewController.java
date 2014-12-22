/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.payroll;

import com.inkubator.hrm.entity.PayTempKalkulasi;
import com.inkubator.hrm.entity.SavingType;
import com.inkubator.hrm.service.PayTempKalkulasiService;
import com.inkubator.hrm.service.SavingTypeService;
import com.inkubator.hrm.web.lazymodel.SalaryJournalLazyDataModel;
import com.inkubator.hrm.web.model.SalaryJournalModel;
import com.inkubator.hrm.web.search.SavingTypeSearchParameter;
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
@ManagedBean(name = "salaryJournalViewController")
@ViewScoped
public class SalaryJournalViewController extends BaseController {

    @ManagedProperty(value = "#{payTempKalkulasiService}")
    private PayTempKalkulasiService payTempKalkulasiService;
    private String searchParameter;
    private LazyDataModel<SalaryJournalModel> lazyDataModel;
    private PayTempKalkulasi selected;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
    }

    @PreDestroy
    private void cleanAndExit() {
        searchParameter = null;
        lazyDataModel = null;
        payTempKalkulasiService = null;
        selected = null;
    }

    public void doSearch() {
        lazyDataModel = null;
    }

    public PayTempKalkulasiService getPayTempKalkulasiService() {
        return payTempKalkulasiService;
    }

    public void setPayTempKalkulasiService(PayTempKalkulasiService payTempKalkulasiService) {
        this.payTempKalkulasiService = payTempKalkulasiService;
    }

    public String getSearchParameter() {
        return searchParameter;
    }

    public void setSearchParameter(String searchParameter) {
        this.searchParameter = searchParameter;
    }

    public LazyDataModel<SalaryJournalModel> getLazyDataModel() {
        if (lazyDataModel == null) {
            lazyDataModel = new SalaryJournalLazyDataModel(searchParameter, payTempKalkulasiService);
        }
        return lazyDataModel;
    }

    public void setLazyDataModel(LazyDataModel<SalaryJournalModel> lazyDataModel) {
        this.lazyDataModel = lazyDataModel;
    }

    public PayTempKalkulasi getSelected() {
        return selected;
    }

    public void setSelected(PayTempKalkulasi selected) {
        this.selected = selected;
    }

}
