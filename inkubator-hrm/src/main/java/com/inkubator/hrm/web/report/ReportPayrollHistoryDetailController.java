/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.report;

import com.inkubator.hrm.web.payroll.*;
import com.inkubator.common.util.AESUtil;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.BenefitGroupRate;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.BenefitGroupRateService;
import com.inkubator.hrm.service.LogMonthEndPayrollService;
import com.inkubator.hrm.web.model.BenefitGroupRenumerationModel;
import com.inkubator.hrm.web.model.PayrollHistoryReportModel;
import com.inkubator.hrm.web.model.SalaryPerDepartmentReportModel;
import com.inkubator.hrm.web.model.SubsidiModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;


/**
 *
 * @author Ahmad Mudzakkir Amal
 */
@ManagedBean(name = "reportPayrollHistoryDetailController")
@ViewScoped
public class ReportPayrollHistoryDetailController extends BaseController {

    @ManagedProperty(value = "#{logMonthEndPayrollService}")
    private LogMonthEndPayrollService logMonthEndPayrollService;
    private PayrollHistoryReportModel selectedPayrollHistoryReportModel;    
    private CartesianChartModel cartModelSalaryDistributionPerDep;
    private List<SalaryPerDepartmentReportModel> listSalaryPerDep;
    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();            
            selectedPayrollHistoryReportModel = (PayrollHistoryReportModel) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("selectedPayrollHistData");
            listSalaryPerDep = logMonthEndPayrollService.getSalaryPerDepartmentPayrollHistoryReport(selectedPayrollHistoryReportModel.getPeriodeId());
            cartModelSalaryDistributionPerDep = new CartesianChartModel();
            
//            for(SalaryPerDepartmentReportModel salaryPerDep : listSalaryPerDep){
//             ChartSeries chartSeries = new ChartSeries();
//            chartSeries.setLabel(salaryPerDep.getDepartmentName());
//            chartSeries.set(salaryPerDep.getDepartmentName(), salaryPerDep.getNominal().doubleValue()/1000000);
//            cartModelSalaryDistributionPerDep.addSeries(chartSeries);
//            }
            ChartSeries chartSeries = new ChartSeries();
            chartSeries.setLabel("Distribusi Gaji Per Departemen (Dalam Jutaan Rupiah)");
            
            for(SalaryPerDepartmentReportModel salaryPerDep : listSalaryPerDep){           
                chartSeries.set(salaryPerDep.getDepartmentName(), salaryPerDep.getNominal().doubleValue()/1000000);            
            }
            
            cartModelSalaryDistributionPerDep.addSeries(chartSeries);
            
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public CartesianChartModel getCartModelSalaryDistributionPerDep() {
        return cartModelSalaryDistributionPerDep;
    }

    public void setCartModelSalaryDistributionPerDep(CartesianChartModel cartModelSalaryDistributionPerDep) {
        this.cartModelSalaryDistributionPerDep = cartModelSalaryDistributionPerDep;
    }

    public LogMonthEndPayrollService getLogMonthEndPayrollService() {
        return logMonthEndPayrollService;
    }

    public void setLogMonthEndPayrollService(LogMonthEndPayrollService logMonthEndPayrollService) {
        this.logMonthEndPayrollService = logMonthEndPayrollService;
    }

    public PayrollHistoryReportModel getSelectedPayrollHistoryReportModel() {
        return selectedPayrollHistoryReportModel;
    }

    public void setSelectedPayrollHistoryReportModel(PayrollHistoryReportModel selectedPayrollHistoryReportModel) {
        this.selectedPayrollHistoryReportModel = selectedPayrollHistoryReportModel;
    }

    public String doBack() {
        return "/protected/report/report_payroll_hist_view.htm?faces-redirect=true";
    }

    @PreDestroy
    public void cleanAndExit() {
        logMonthEndPayrollService = null;
        selectedPayrollHistoryReportModel = null;       
    }

}
