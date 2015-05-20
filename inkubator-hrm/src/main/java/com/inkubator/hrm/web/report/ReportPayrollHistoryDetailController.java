/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.report;

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
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.HorizontalBarChartModel;
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
    private BarChartModel cartModelSalaryEveryMonth;
    private List<PayrollHistoryReportModel> payrollHistoryReportModelList = new ArrayList<>();
    private HorizontalBarChartModel cartModelSalaryDistributionPerDep;
    private List<SalaryPerDepartmentReportModel> listSalaryPerDep;
    private PieChartModel bankTransferPieModel;
    private List<BankTransferDistributionReportModel> listBankTransferDistribution;
    private Long totalBankTransfer;
    private Long periodeId;

    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();

            //Get selected periodeId
            periodeId = Long.valueOf(FacesUtil.getRequestParameter("execution").substring(1));
            SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM yyyy");
            
            //Initialize cartModelSalaryEveryMonth
            cartModelSalaryEveryMonth = new BarChartModel();
            cartModelSalaryEveryMonth.setStacked(Boolean.FALSE);
            cartModelSalaryEveryMonth.setAnimate(Boolean.TRUE);
            cartModelSalaryEveryMonth.setShowDatatip(Boolean.TRUE);
            cartModelSalaryEveryMonth.setLegendPosition("ne");
            cartModelSalaryEveryMonth.setLegendCols(1);
            Axis xAxisCartModelSalaryEveryMonth = cartModelSalaryEveryMonth.getAxis(AxisType.X);
            Axis yAxisCartModelSalaryEveryMonth = cartModelSalaryEveryMonth.getAxis(AxisType.Y);
            xAxisCartModelSalaryEveryMonth.setLabel("Month");
            yAxisCartModelSalaryEveryMonth.setLabel("Salary");
            yAxisCartModelSalaryEveryMonth.setMin(0);
            yAxisCartModelSalaryEveryMonth.setMax(1000);
            
            //Initialize cartModelSalaryDistributionPerDep
            cartModelSalaryDistributionPerDep = new HorizontalBarChartModel();
            cartModelSalaryDistributionPerDep.setStacked(Boolean.FALSE);
            cartModelSalaryDistributionPerDep.setLegendPosition("ne");
            cartModelSalaryDistributionPerDep.setSeriesColors("003366");
            cartModelSalaryDistributionPerDep.setAnimate(Boolean.TRUE);
            cartModelSalaryDistributionPerDep.setShowDatatip(Boolean.TRUE);
            cartModelSalaryDistributionPerDep.setLegendCols(6);
            Axis xAxis = cartModelSalaryDistributionPerDep.getAxis(AxisType.X);
            Axis yAxis = cartModelSalaryDistributionPerDep.getAxis(AxisType.Y);
            xAxis.setLabel("Salary");
            yAxis.setLabel("Department");
            xAxis.setMin(0);
            xAxis.setMax(300);
            
            //Initialize bankTransferPieModel
            bankTransferPieModel = new PieChartModel();
            bankTransferPieModel.setFill(Boolean.TRUE);
            bankTransferPieModel.setLegendPosition("e");
            bankTransferPieModel.setShowDataLabels(Boolean.TRUE);
            bankTransferPieModel.setSeriesColors("66cc00,629de1,003366,990000,cccc00,6600cc");
            bankTransferPieModel.setSliceMargin(4);
            bankTransferPieModel.setDiameter(240);

            //Get List payrollHistoryReportModel
            payrollHistoryReportModelList = logMonthEndPayrollService.getDataForPayrollHistoryReport();

            ChartSeries chartSeriesPayrollPerMonth = new ChartSeries();
            chartSeriesPayrollPerMonth.setLabel("Gaji Perbulan (Dalam Jutaan Rupiah)");

            //fill data from payrollModel
            for (PayrollHistoryReportModel payrollModel : payrollHistoryReportModelList) {
                chartSeriesPayrollPerMonth.set(dateFormat.format(payrollModel.getTglAwalPeriode()), payrollModel.getNominal().doubleValue() / 1000000);
            }

            cartModelSalaryEveryMonth.addSeries(chartSeriesPayrollPerMonth);
            selectedPayrollHistoryReportModel = logMonthEndPayrollService.getDataPayrollHistoryReportModelByPeriodeId(periodeId);

            //Get Salary Distribution per Department of selected periodeId
            listSalaryPerDep = logMonthEndPayrollService.getSalaryPerDepartmentPayrollHistoryReport(selectedPayrollHistoryReportModel.getPeriodeId());

            ChartSeries chartSeries = new ChartSeries();
            chartSeries.setLabel("Distribusi Gaji Per Departemen (Dalam Jutaan Rupiah)");

            //fill data salary distribution
            for (SalaryPerDepartmentReportModel salaryPerDep : listSalaryPerDep) {
                chartSeries.set(salaryPerDep.getDepartmentName(), salaryPerDep.getNominal().doubleValue() / 1000000);
            }

            cartModelSalaryDistributionPerDep.addSeries(chartSeries);

            //Get list bank transfer Distribution of selected periodeId
            listBankTransferDistribution = logListOfTransferService.getBankTransferDistributionByPayrollHistoryReport(selectedPayrollHistoryReportModel.getPeriodeId());

            //Get total transfer from whole Bank of selected periodeId, this is for count  the percentage of total transfer from each bank
            totalBankTransfer = logListOfTransferService.getTotalBankTransferByPayrollHistoryReport(selectedPayrollHistoryReportModel.getPeriodeId());

            // variable for percentage lower than 5%
            Double totalPercentageLainLain = 0.0;

            for (BankTransferDistributionReportModel bankTransferModel : listBankTransferDistribution) {

                //count the percentage of each bank
                Double bankPercentage = bankTransferModel.getTotalAccountNumber().doubleValue() / totalBankTransfer.doubleValue();

                //set to pie model if greater than 5%
                if (bankPercentage >= 0.05) {
                    bankTransferPieModel.set(bankTransferModel.getBankName(), bankPercentage);
                } else {
                    //accumulate to totalPercentageLainLain if lower than 5%
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

    public BarChartModel getCartModelSalaryEveryMonth() {
        return cartModelSalaryEveryMonth;
    }

    public void setCartModelSalaryEveryMonth(BarChartModel cartModelSalaryEveryMonth) {
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

    public HorizontalBarChartModel getCartModelSalaryDistributionPerDep() {
        return cartModelSalaryDistributionPerDep;
    }

    public void setCartModelSalaryDistributionPerDep(HorizontalBarChartModel cartModelSalaryDistributionPerDep) {
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
