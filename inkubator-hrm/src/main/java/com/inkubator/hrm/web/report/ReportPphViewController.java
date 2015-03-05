/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.report;

import com.inkubator.hrm.entity.PayTempKalkulasiEmpPajak;
import com.inkubator.hrm.service.LogMonthEndTaxesService;
import com.inkubator.hrm.service.PayTempKalkulasiEmpPajakService;
import com.inkubator.hrm.web.lazymodel.ReportPphLazyDataModel;
import com.inkubator.hrm.web.model.PphReportModel;
import com.inkubator.hrm.web.search.LogMonthEndTaxesSearchParameter;
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
@ManagedBean(name = "reportPphViewController")
@ViewScoped
public class ReportPphViewController extends BaseController {

    @ManagedProperty(value = "#{logMonthEndTaxesService}")
    private LogMonthEndTaxesService service;
    private LogMonthEndTaxesSearchParameter searchParameter;
    private LazyDataModel<PphReportModel> lazyDataModel;
    private PayTempKalkulasiEmpPajak selected;
    private PphReportModel selectedModel;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
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

    public String doGenerateReportPph(){
        return "/protected/report/generated_pph_report.htm?faces-redirect=true&execution=e" + selectedModel.getEmpDataId();
    }
    
    public LogMonthEndTaxesService getService() {
        return service;
    }

    public void setService(LogMonthEndTaxesService service) {
        this.service = service;
    }

    public LogMonthEndTaxesSearchParameter getSearchParameter() {
        return searchParameter;
    }

    public void setSearchParameter(LogMonthEndTaxesSearchParameter searchParameter) {
        this.searchParameter = searchParameter;
    }

    public LazyDataModel<PphReportModel> getLazyDataModel() {
        if (lazyDataModel == null) {
            lazyDataModel = new ReportPphLazyDataModel(searchParameter, service);
        }
        return lazyDataModel;
    }

    public void setLazyDataModel(LazyDataModel<PphReportModel> lazyDataModel) {
        this.lazyDataModel = lazyDataModel;
    }

    public PayTempKalkulasiEmpPajak getSelected() {
        return selected;
    }

    public void setSelected(PayTempKalkulasiEmpPajak selected) {
        this.selected = selected;
    }

    public PphReportModel getSelectedModel() {
        return selectedModel;
    }

    public void setSelectedModel(PphReportModel selectedModel) {
        this.selectedModel = selectedModel;
    }


}
