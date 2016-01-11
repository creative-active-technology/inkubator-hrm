/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.report;


import com.inkubator.hrm.service.LogMonthEndPayrollService;
import com.inkubator.hrm.web.model.PayrollHistoryReportModel;
import com.inkubator.hrm.web.search.ReportPayrollHistorySearchParameter;
import com.inkubator.webcore.controller.BaseController;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.BarChartSeries;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
@ManagedBean(name = "chartPayrollHistoryReportController")
@ViewScoped
public class ChartPayrollHistoryReportController extends BaseController {

    private LineChartModel cartModelSalaryEveryMonth;
    private List<PayrollHistoryReportModel> payrollHistoryReportModelList = new ArrayList<>();
    @ManagedProperty(value = "#{logMonthEndPayrollService}")
    private LogMonthEndPayrollService logMonthEndPayrollService;   
    private ReportPayrollHistorySearchParameter reportParameter;
    private SimpleDateFormat  dateFormat;
    private Date startDate;
    private Date endDate;
    
    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();            
            dateFormat = new SimpleDateFormat("MMM yyyy");
            reportParameter = new ReportPayrollHistorySearchParameter();
            payrollHistoryReportModelList = logMonthEndPayrollService.getDataForPayrollHistoryReport();
            //Initalize cartModelSalaryEveryMonth
            cartModelSalaryEveryMonth = new LineChartModel();
            cartModelSalaryEveryMonth.setLegendPosition("ne");
            cartModelSalaryEveryMonth.setStacked(Boolean.FALSE);
            cartModelSalaryEveryMonth.setAnimate(Boolean.TRUE);
            cartModelSalaryEveryMonth.setShowDatatip(Boolean.TRUE);
            cartModelSalaryEveryMonth.setShowPointLabels(Boolean.TRUE);
            cartModelSalaryEveryMonth.setLegendCols(1);
            cartModelSalaryEveryMonth.getAxes().put(AxisType.X, new CategoryAxis("Month"));
            Axis yAxisCartModelSalaryEveryMonth = cartModelSalaryEveryMonth.getAxis(AxisType.Y);
            yAxisCartModelSalaryEveryMonth.setLabel("Salary");
            yAxisCartModelSalaryEveryMonth.setMin(0);
            yAxisCartModelSalaryEveryMonth.setMax(1200);
 
            ChartSeries lineChartSeriesPayrollPerMonth = new ChartSeries();
            lineChartSeriesPayrollPerMonth.setLabel("Grafik Gaji Perbulan (Dalam Jutaan Rupiah)");
            
            
            //fill data from payrollModel
            for(PayrollHistoryReportModel payrollModel : payrollHistoryReportModelList){   
                lineChartSeriesPayrollPerMonth.set(dateFormat.format(payrollModel.getTglAwalPeriode()), (int)payrollModel.getNominal().doubleValue()/1000000);         
            }
                        
            cartModelSalaryEveryMonth.addSeries(lineChartSeriesPayrollPerMonth);
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }

    }
   
    public void doShowChart(){      
        
        //get list based on reportParameter
        payrollHistoryReportModelList = logMonthEndPayrollService.getByParamForPayrollHistoryReport(reportParameter);  
        
        //clean chartSeries before render
        cartModelSalaryEveryMonth.clear();

        LineChartSeries lineChartSeriesPayrollPerMonth = new LineChartSeries();
        lineChartSeriesPayrollPerMonth.setLabel("Grafik Gaji Perbulan (Dalam Jutaan Rupiah)");
        
        //fill data from payrollModel
        for (PayrollHistoryReportModel payrollModel : payrollHistoryReportModelList) {           
            lineChartSeriesPayrollPerMonth.set(dateFormat.format(payrollModel.getTglAwalPeriode()), payrollModel.getNominal().doubleValue() / 1000000);
        }

        cartModelSalaryEveryMonth.addSeries(lineChartSeriesPayrollPerMonth);
    }

    @PreDestroy
    private void cleanAndExit() {
        logMonthEndPayrollService = null;
        cartModelSalaryEveryMonth = null;
        payrollHistoryReportModelList = null;     
        reportParameter = null;
    }

    public ReportPayrollHistorySearchParameter getReportParameter() {
        return reportParameter;
    }

    public void setReportParameter(ReportPayrollHistorySearchParameter reportParameter) {
        this.reportParameter = reportParameter;
    }

    public SimpleDateFormat getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(SimpleDateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    
    public LineChartModel getCartModelSalaryEveryMonth() {
        return cartModelSalaryEveryMonth;
    }

    public void setCartModelSalaryEveryMonth(LineChartModel cartModelSalaryEveryMonth) {
        this.cartModelSalaryEveryMonth = cartModelSalaryEveryMonth;
    }

    public List<PayrollHistoryReportModel> getPayrollHistoryReportModelList() {
        return payrollHistoryReportModelList;
    }

    public void setPayrollHistoryReportModelList(List<PayrollHistoryReportModel> payrollHistoryReportModelList) {
        this.payrollHistoryReportModelList = payrollHistoryReportModelList;
    }

    public LogMonthEndPayrollService getLogMonthEndPayrollService() {
        return logMonthEndPayrollService;
    }

    public void setLogMonthEndPayrollService(LogMonthEndPayrollService logMonthEndPayrollService) {
        this.logMonthEndPayrollService = logMonthEndPayrollService;
    }
    
    
}
