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
import com.inkubator.hrm.service.LogListOfTransferService;
import com.inkubator.hrm.service.LogMonthEndPayrollService;
import com.inkubator.hrm.web.model.BankTransferDistributionReportModel;
import com.inkubator.hrm.web.model.BenefitGroupRenumerationModel;
import com.inkubator.hrm.web.model.PayrollHistoryReportModel;
import com.inkubator.hrm.web.model.SalaryPerDepartmentReportModel;
import com.inkubator.hrm.web.model.SubsidiModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;


/**
 *
 * @author Ahmad Mudzakkir Amal
 */
@ManagedBean(name = "reportPayrollHistoryDetailController")
@ViewScoped
public class ReportPayrollHistoryDetailController extends BaseController {

    @ManagedProperty(value = "#{logMonthEndPayrollService}")
    private LogMonthEndPayrollService logMonthEndPayrollService;
    @ManagedProperty(value = "#{logListOfTransferService}")
    private LogListOfTransferService logListOfTransferService;
    private PayrollHistoryReportModel selectedPayrollHistoryReportModel;    
    private CartesianChartModel cartModelSalaryEveryMonth;
    private List<PayrollHistoryReportModel> payrollHistoryReportModelList = new ArrayList<>();
    private CartesianChartModel cartModelSalaryDistributionPerDep;
    private List<SalaryPerDepartmentReportModel> listSalaryPerDep;
    private PieChartModel bankTransferPieModel;
    private List<BankTransferDistributionReportModel> listBankTransferDistribution;
    private Long totalBankTransfer;
    private Long idLogMonthEnd;
    
    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization(); 
            idLogMonthEnd = Long.valueOf(FacesUtil.getRequestParameter("execution").substring(1));
            SimpleDateFormat  dateFormat = new SimpleDateFormat("MMMM yyyy");
            payrollHistoryReportModelList = logMonthEndPayrollService.getDataForPayrollHistoryReport();
            cartModelSalaryEveryMonth = new CartesianChartModel();
            
            ChartSeries chartSeriesPayrollPerMonth = new ChartSeries();
            chartSeriesPayrollPerMonth.setLabel("Gaji Perbulan (Dalam Jutaan Rupiah)");
            
            for(PayrollHistoryReportModel payrollModel : payrollHistoryReportModelList){
                chartSeriesPayrollPerMonth.set(dateFormat.format(payrollModel.getTglAwalPeriode()), payrollModel.getNominal().doubleValue()/1000000);         
            }
            
            cartModelSalaryEveryMonth.addSeries(chartSeriesPayrollPerMonth);
            selectedPayrollHistoryReportModel = logMonthEndPayrollService.getDataPayrollHistoryReportModelByIdLogMonthEnd(idLogMonthEnd);
            ///selectedPayrollHistoryReportModel = (PayrollHistoryReportModel) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("selectedPayrollHistData");
            listSalaryPerDep = logMonthEndPayrollService.getSalaryPerDepartmentPayrollHistoryReport(selectedPayrollHistoryReportModel.getPeriodeId());
            cartModelSalaryDistributionPerDep = new CartesianChartModel();            

            ChartSeries chartSeries = new ChartSeries();
            chartSeries.setLabel("Distribusi Gaji Per Departemen (Dalam Jutaan Rupiah)");
            
            for(SalaryPerDepartmentReportModel salaryPerDep : listSalaryPerDep){           
                chartSeries.set(salaryPerDep.getDepartmentName(), salaryPerDep.getNominal().doubleValue()/1000000);            
            }
            
            cartModelSalaryDistributionPerDep.addSeries(chartSeries);
            
            bankTransferPieModel = new PieChartModel();
            listBankTransferDistribution = logListOfTransferService.getBankTransferDistributionByPayrollHistoryReport(selectedPayrollHistoryReportModel.getPeriodeId());
            totalBankTransfer = logListOfTransferService.getTotalBankTransferByPayrollHistoryReport(selectedPayrollHistoryReportModel.getPeriodeId());
            Double totalPercentageLainLain = 0.0;
            
            for(BankTransferDistributionReportModel bankTransferModel : listBankTransferDistribution){
                Double bankPercentage = bankTransferModel.getTotalAccountNumber().doubleValue() / totalBankTransfer.doubleValue();
                LOGGER.info("Bank : " + bankTransferModel.getBankName() +
                        " totalAccountNumber : " + bankTransferModel.getTotalAccountNumber() + 
                        " bankPercentage : " + bankPercentage);
                if(bankPercentage >= 0.05){
                    bankTransferPieModel.set(bankTransferModel.getBankName(), bankPercentage);
                }else{
                    totalPercentageLainLain += bankPercentage;
                }                
            }
            
            bankTransferPieModel.set("Lain - Lain", totalPercentageLainLain);
            
            
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public List<PayrollHistoryReportModel> getPayrollHistoryReportModelList() {
        return payrollHistoryReportModelList;
    }

    public void setPayrollHistoryReportModelList(List<PayrollHistoryReportModel> payrollHistoryReportModelList) {
        this.payrollHistoryReportModelList = payrollHistoryReportModelList;
    }
    
    
    public CartesianChartModel getCartModelSalaryEveryMonth() {
        return cartModelSalaryEveryMonth;
    }

    public void setCartModelSalaryEveryMonth(CartesianChartModel cartModelSalaryEveryMonth) {
        this.cartModelSalaryEveryMonth = cartModelSalaryEveryMonth;
    }
    
    
    public LogListOfTransferService getLogListOfTransferService() {
        return logListOfTransferService;
    }

    public void setLogListOfTransferService(LogListOfTransferService logListOfTransferService) {
        this.logListOfTransferService = logListOfTransferService;
    }

    public List<SalaryPerDepartmentReportModel> getListSalaryPerDep() {
        return listSalaryPerDep;
    }

    public void setListSalaryPerDep(List<SalaryPerDepartmentReportModel> listSalaryPerDep) {
        this.listSalaryPerDep = listSalaryPerDep;
    }

    public PieChartModel getBankTransferPieModel() {
        return bankTransferPieModel;
    }

    public void setBankTransferPieModel(PieChartModel bankTransferPieModel) {
        this.bankTransferPieModel = bankTransferPieModel;
    }

    

    public List<BankTransferDistributionReportModel> getListBankTransferDistribution() {
        return listBankTransferDistribution;
    }

    public void setListBankTransferDistribution(List<BankTransferDistributionReportModel> listBankTransferDistribution) {
        this.listBankTransferDistribution = listBankTransferDistribution;
    }

    public Long getTotalBankTransfer() {
        return totalBankTransfer;
    }

    public void setTotalBankTransfer(Long totalBankTransfer) {
        this.totalBankTransfer = totalBankTransfer;
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
