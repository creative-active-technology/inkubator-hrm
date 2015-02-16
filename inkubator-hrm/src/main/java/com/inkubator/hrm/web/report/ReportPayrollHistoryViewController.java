package com.inkubator.hrm.web.report;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.LazyDataModel;

import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.LogMonthEndPayrollService;
import com.inkubator.hrm.service.PayTempKalkulasiService;
import com.inkubator.hrm.service.WtPeriodeService;
import com.inkubator.hrm.web.lazymodel.PayrollHistoryReportLazyDataModel;
import com.inkubator.hrm.web.model.PayrollHistoryReportModel;
import com.inkubator.webcore.controller.BaseController;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
@ManagedBean(name = "reportPayrollHistoryViewController")
@ViewScoped
public class ReportPayrollHistoryViewController extends BaseController {

    private String searchParameter;
    private LazyDataModel<PayrollHistoryReportModel> lazyDataModel;
    private PayrollHistoryReportModel selectedPayrollHistData;
    @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;
    @ManagedProperty(value = "#{payTempKalkulasiService}")
    private PayTempKalkulasiService payTempKalkulasiService;
    @ManagedProperty(value = "#{logMonthEndPayrollService}")
    private LogMonthEndPayrollService logMonthEndPayrollService;
    @ManagedProperty(value = "#{wtPeriodeService}")
    private WtPeriodeService wtPeriodeService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
    }

    @PreDestroy
    public void cleanAndExit() {
        empDataService = null;
        searchParameter = null;
        lazyDataModel = null;

    }

    public LogMonthEndPayrollService getLogMonthEndPayrollService() {
        return logMonthEndPayrollService;
    }

    public void setLogMonthEndPayrollService(LogMonthEndPayrollService logMonthEndPayrollService) {
        this.logMonthEndPayrollService = logMonthEndPayrollService;
    }
    
    public void doShowChart(){
         
    }
    
    public String doDetail() {       
        return "/protected/report/report_payroll_history_detail.htm?faces-redirect=true&execution=e" + selectedPayrollHistData.getId();
        
    }

    public PayrollHistoryReportModel getSelectedPayrollHistData() {
        return selectedPayrollHistData;
    }

    public void setSelectedPayrollHistData(PayrollHistoryReportModel selectedPayrollHistData) {
        this.selectedPayrollHistData = selectedPayrollHistData;
    }
    
    
    public String getSearchParameter() {
        return searchParameter;
    }

    public void setSearchParameter(String searchParameter) {
        this.searchParameter = searchParameter;
    }

    public LazyDataModel<PayrollHistoryReportModel> getLazyDataModel() {
        if (lazyDataModel == null) {
            lazyDataModel = new PayrollHistoryReportLazyDataModel(searchParameter, logMonthEndPayrollService);
        }
        return lazyDataModel;
    }

    public void setLazyDataModel(LazyDataModel<PayrollHistoryReportModel> lazyDataModel) {
        this.lazyDataModel = lazyDataModel;
    }

    public EmpDataService getEmpDataService() {
        return empDataService;
    }

    public void setEmpDataService(EmpDataService empDataService) {
        this.empDataService = empDataService;
    }

    public PayTempKalkulasiService getPayTempKalkulasiService() {
        return payTempKalkulasiService;
    }

    public void setPayTempKalkulasiService(PayTempKalkulasiService payTempKalkulasiService) {
        this.payTempKalkulasiService = payTempKalkulasiService;
    }

    public WtPeriodeService getWtPeriodeService() {
        return wtPeriodeService;
    }

    public void setWtPeriodeService(WtPeriodeService wtPeriodeService) {
        this.wtPeriodeService = wtPeriodeService;
    }
    
    
    public void doSearch() {
        lazyDataModel = null;
    }
}
