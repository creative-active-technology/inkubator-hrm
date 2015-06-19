/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web;

import com.inkubator.hrm.entity.WtPeriode;
import com.inkubator.hrm.service.DepartmentService;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.TempAttendanceRealizationService;
import com.inkubator.hrm.util.HrmUserInfoUtil;
import com.inkubator.hrm.service.WtPeriodeService;
import com.inkubator.hrm.util.ResourceBundleUtil;
import com.inkubator.hrm.web.model.BioDataModel;
import com.inkubator.hrm.web.model.DepAttendanceRealizationViewModel;
import com.inkubator.hrm.web.model.LoginHistoryModel;
import com.inkubator.hrm.web.model.RealizationAttendanceModel;
import com.inkubator.webcore.controller.BaseController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

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
 * @author rizkykojek
 */
@ManagedBean(name = "homeDashboardController")
@RequestScoped
public class HomeDashboardController extends BaseController {

    private Date lastUpdateEmpDistByGender;
    private Date lastUpdateEmpDistByDepartment;
    private Date lastUpdateEmpDistByAge;
    private Long totalMale;
    private Long totalFemale;
    private Long totalEmp;
    private BioDataModel nearestBirthDate;
    private PieChartModel pieModel;
    private CartesianChartModel distribusiKaryawanPerDepartment;
    private CartesianChartModel presensiModel;
    private CartesianChartModel persentasiKehadiranPerWeek;
    private BarChartModel barChartModel;
    private HorizontalBarChartModel presensiBarChartModel;
    private BarChartModel barChartDistribusiByDept;
    private List<LoginHistoryModel> logHistorys = new ArrayList<>();
    @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;
    @ManagedProperty(value = "#{departmentService}")
    private DepartmentService departmentService;

    @ManagedProperty(value = "#{tempAttendanceRealizationService}")
    private TempAttendanceRealizationService tempAttendanceRealizationService;
    private RealizationAttendanceModel attendanceModel;
    private Double totalPersent;

    @ManagedProperty(value = "#{wtPeriodeService}")
    private WtPeriodeService wtPeriodeService;
    
    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        
        distribusiKaryawanPerDepartment = new CartesianChartModel();
        barChartDistribusiByDept = new BarChartModel();
        pieModel = new PieChartModel();
        totalMale = new Long(0);
        totalFemale = new Long(0);
        try {
            /**
             * calculate employee distribution based on GENDER
             */
            Map<String, Long> employeesByGender = empDataService.getTotalByGender(HrmUserInfoUtil.getCompanyId());
            totalFemale = employeesByGender.get("male");
            totalMale = employeesByGender.get("female");
            lastUpdateEmpDistByGender = new Date(employeesByGender.get("lastUpdate"));
            /**
             * calculate employee distribution based on DEPARTMENT
             */
            Map<String, Long> employeesByDepartment = empDataService.getTotalByDepartment(HrmUserInfoUtil.getCompanyId());
            int i = 0;
            for (Map.Entry<String, Long> entry : employeesByDepartment.entrySet()) {

                /**
                 * only 8 department is allowed to show atau jika entry key nya
                 * "lastUpdate" berarti itu akhir dari list
                 */
                if (i == 8 || StringUtils.equals(entry.getKey(), "lastUpdate")) {
                    break;
                }
                i++;

                ChartSeries deptSeries = new ChartSeries();
                deptSeries.setLabel(entry.getKey());
                deptSeries.set("Department", entry.getValue());
                barChartDistribusiByDept.addSeries(deptSeries);

            }
            barChartDistribusiByDept.setLegendPosition("ne");
            barChartDistribusiByDept.setStacked(false);
            barChartDistribusiByDept.setShowDatatip(true);
            barChartDistribusiByDept.setLegendCols(8);
            barChartDistribusiByDept.setSeriesColors("66cc00,629de1,003366,990000,cccc00,6600cc,006666");
            lastUpdateEmpDistByDepartment = new Date(employeesByDepartment.get("lastUpdate"));

            /**
             * calculate employee distribution based on AGE
             */
            Map<String, Long> employeesByAge = empDataService.getTotalByAge(HrmUserInfoUtil.getCompanyId());
            pieModel.set("< 26", employeesByAge.get("lessThan26"));
            pieModel.set("25-30", employeesByAge.get("between26And30"));
            pieModel.set("31-35", employeesByAge.get("between31And35"));
            pieModel.set("36-40", employeesByAge.get("between36And40"));
            pieModel.set("> 40", employeesByAge.get("moreThan40"));
            pieModel.setLegendPosition("e");
            pieModel.setFill(false);
            pieModel.setShowDataLabels(true);
            pieModel.setSliceMargin(4);
            pieModel.setDiameter(120);
            pieModel.setSeriesColors("66cc00,629de1,003366,990000,cccc00,6600cc");
            lastUpdateEmpDistByAge = new Date(employeesByAge.get("lastUpdate"));

            System.out.println(" Service nya " + tempAttendanceRealizationService);
            attendanceModel = tempAttendanceRealizationService.getStatisticEmpAttendaceRealization();
            double totalPresent = Double.parseDouble(String.valueOf(attendanceModel.getTotaldayPresent()));
            double totalSchedule = Double.parseDouble(String.valueOf(attendanceModel.getTotaldaySchedule()));
            totalPersent = (totalPresent / totalSchedule);

            
            //Get Period Active
            WtPeriode activeWtPeriode = wtPeriodeService.getEntityByPayrollTypeActive();
            persentasiKehadiranPerWeek = new CartesianChartModel();
            barChartModel = new BarChartModel();
            barChartModel.setStacked(false);
            barChartModel.setLegendPosition("ne");
            barChartModel.setLegendCols(6);
            barChartModel.setSeriesColors("66cc00,629de1,003366,990000,cccc00,6600cc");
            barChartModel.setShowDatatip(true);
            barChartModel.setShadow(true);
            barChartModel.setShowPointLabels(true);
            Axis yAxis = barChartModel.getAxis(AxisType.Y);
            yAxis.setMax(150);
            yAxis.setMin(0);
            
            //Get Attendance Percentation per Department on Active Period
            Map<String, List<DepAttendanceRealizationViewModel>> mapResult = empDataService.getListDepAttendanceByDepartmentIdAndRangeDate(activeWtPeriode.getFromPeriode(), activeWtPeriode.getUntilPeriode());
            
            //Looping and render it
            for (Map.Entry<String, List<DepAttendanceRealizationViewModel>> entry : mapResult.entrySet()) {
            	
            	ChartSeries charDepartmentSeries = new ChartSeries();
            	charDepartmentSeries.setLabel(entry.getKey());
            	
            	for(DepAttendanceRealizationViewModel depAttendanceModel : entry.getValue()){            		
            		charDepartmentSeries.set(ResourceBundleUtil.getAsString("global.week") + "  " +depAttendanceModel.getWeekNumber(), depAttendanceModel.getAttendancePercentage().doubleValue());
            	}
            	
            	barChartModel.addSeries(charDepartmentSeries);            	
            }


        } catch (Exception e) {
            LOGGER.error("Error when calculate employee distribution based on Gender, Age or Department", e);
        }


        persentasiKehadiranPerWeek = new CartesianChartModel();       

        presensiModel = new CartesianChartModel();
        presensiBarChartModel = new HorizontalBarChartModel();
        ChartSeries cutiDanDinas = new ChartSeries();
        cutiDanDinas.setLabel("Cuti & Dinas");
        cutiDanDinas.set("18-03-2014", 10);
        cutiDanDinas.set("19-03-2014", 5);
        cutiDanDinas.set("20-03-2014", 0);
        cutiDanDinas.set("21-03-2014", 23);
        cutiDanDinas.set("22-03-2014", 3);

        ChartSeries ijinDanSakit = new ChartSeries();
        ijinDanSakit.setLabel("Izin & Sakit");
        ijinDanSakit.set("18-03-2014", 3);
        ijinDanSakit.set("19-03-2014", 20);
        ijinDanSakit.set("20-03-2014", 13);
        ijinDanSakit.set("21-03-2014", 3);
        ijinDanSakit.set("22-03-2014", 3);

        ChartSeries hadir = new ChartSeries();
        ChartSeries tanpaKeterangan = new ChartSeries();
        tanpaKeterangan.setLabel("Alpha");
        tanpaKeterangan.set("18-03-2014", 3);
        tanpaKeterangan.set("19-03-2014", 2);
        tanpaKeterangan.set("20-03-2014", 1);
        tanpaKeterangan.set("21-03-2014", 3);
        tanpaKeterangan.set("22-03-2014", 1);

        hadir.setLabel("Hadir");
        hadir.set("18-03-2014", 193);
        hadir.set("19-03-2014", 181);
        hadir.set("20-03-2014", 193);
        hadir.set("21-03-2014", 180);
        hadir.set("22-03-2014", 200);
        presensiBarChartModel.addSeries(hadir);
        presensiBarChartModel.addSeries(cutiDanDinas);
        presensiBarChartModel.addSeries(ijinDanSakit);
        presensiBarChartModel.addSeries(tanpaKeterangan);
        presensiBarChartModel.setStacked(true);
        presensiBarChartModel.setShowDatatip(true);
        presensiBarChartModel.setLegendPosition("se");
        presensiBarChartModel.setSeriesColors("66cc00,629de1,003366,990000,cccc00,6600cc,d500d5,ff2a55");

        Axis xAxis = presensiBarChartModel.getAxis(AxisType.X);
        xAxis.setMax(300);
        xAxis.setTickInterval("20");
        xAxis.setMin(0);

    }

    public Date getLastUpdateEmpDistByGender() {
        return lastUpdateEmpDistByGender;
    }

    public void setLastUpdateEmpDistByGender(Date lastUpdateEmpDistByGender) {
        this.lastUpdateEmpDistByGender = lastUpdateEmpDistByGender;
    }

    public Date getLastUpdateEmpDistByDepartment() {
        return lastUpdateEmpDistByDepartment;
    }

    public void setLastUpdateEmpDistByDepartment(Date lastUpdateEmpDistByDepartment) {
        this.lastUpdateEmpDistByDepartment = lastUpdateEmpDistByDepartment;
    }

    public Date getLastUpdateEmpDistByAge() {
        return lastUpdateEmpDistByAge;
    }

    public void setLastUpdateEmpDistByAge(Date lastUpdateEmpDistByAge) {
        this.lastUpdateEmpDistByAge = lastUpdateEmpDistByAge;
    }

    public PieChartModel getPieModel() {
        return pieModel;
    }

    public void setPieModel(PieChartModel pieModel) {
        this.pieModel = pieModel;
    }

    public CartesianChartModel getPresensiModel() {
        return presensiModel;
    }

    public void setPresensiModel(CartesianChartModel presensiModel) {
        this.presensiModel = presensiModel;
    }

    public List<LoginHistoryModel> getLogHistorys() {
        return logHistorys;
    }

    public void setLogHistorys(List<LoginHistoryModel> logHistorys) {
        this.logHistorys = logHistorys;
    }

    public CartesianChartModel getPersentasiKehadiranPerWeek() {
        return persentasiKehadiranPerWeek;
    }

    public void setPersentasiKehadiranPerWeek(CartesianChartModel persentasiKehadiranPerWeek) {
        this.persentasiKehadiranPerWeek = persentasiKehadiranPerWeek;
    }

    public Long getTotalMale() {
        return totalMale;
    }

    public void setTotalMale(Long totalMale) {
        this.totalMale = totalMale;
    }

    public Long getTotalFemale() {
        return totalFemale;
    }

    public void setTotalFemale(Long totalFemale) {
        this.totalFemale = totalFemale;
    }

    public void setEmpDataService(EmpDataService empDataService) {
        this.empDataService = empDataService;
    }

    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    public CartesianChartModel getDistribusiKaryawanPerDepartment() {
        return distribusiKaryawanPerDepartment;
    }

    public void setDistribusiKaryawanPerDepartment(CartesianChartModel distribusiKaryawanPerDepartment) {
        this.distribusiKaryawanPerDepartment = distribusiKaryawanPerDepartment;
    }

    public BioDataModel getNearestBirthDate() {
        this.nearestBirthDate = empDataService.getEmpNameWithNearestBirthDate();
        return nearestBirthDate;
    }

    public void setNearestBirthDate(BioDataModel nearestBirthDate) {
        this.nearestBirthDate = nearestBirthDate;
    }

    public Long getTotalEmp() throws Exception {
        this.totalEmp = empDataService.getTotalEmpDataNotTerminate();
        return totalEmp;
    }

    public void setTotalEmp(Long totalEmp) {
        this.totalEmp = totalEmp;
    }

    public BarChartModel getBarChartModel() {
        return barChartModel;
    }

    public void setBarChartModel(BarChartModel barChartModel) {
        this.barChartModel = barChartModel;
    }

    public HorizontalBarChartModel getPresensiBarChartModel() {
        return presensiBarChartModel;
    }

    public void setPresensiBarChartModel(HorizontalBarChartModel presensiBarChartModel) {
        this.presensiBarChartModel = presensiBarChartModel;
    }

    public BarChartModel getBarChartDistribusiByDept() {
        return barChartDistribusiByDept;
    }

    public void setBarChartDistribusiByDept(BarChartModel barChartDistribusiByDept) {
        this.barChartDistribusiByDept = barChartDistribusiByDept;
    }

    public void setTempAttendanceRealizationService(TempAttendanceRealizationService tempAttendanceRealizationService) {
        this.tempAttendanceRealizationService = tempAttendanceRealizationService;
    }

    public RealizationAttendanceModel getAttendanceModel() {
        return attendanceModel;
    }

    public void setAttendanceModel(RealizationAttendanceModel attendanceModel) {
        this.attendanceModel = attendanceModel;
    }

    public Double getTotalPersent() {
        return totalPersent;
    }

    public void setTotalPersent(Double totalPersent) {
        this.totalPersent = totalPersent;
    }

	public void setWtPeriodeService(WtPeriodeService wtPeriodeService) {
		this.wtPeriodeService = wtPeriodeService;
	}    
    

}
