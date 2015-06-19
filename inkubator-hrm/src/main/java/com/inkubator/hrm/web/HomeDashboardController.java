/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web;

import com.inkubator.hrm.entity.WtPeriode;
import com.inkubator.hrm.service.DepartmentService;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.WtPeriodeService;
import com.inkubator.hrm.util.ResourceBundleUtil;
import com.inkubator.hrm.web.model.BioDataModel;
import com.inkubator.hrm.web.model.DepAttendanceRealizationViewModel;
import com.inkubator.hrm.web.model.LoginHistoryModel;
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
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;
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
    private List<LoginHistoryModel> logHistorys = new ArrayList<>();
    @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;
    @ManagedProperty(value = "#{departmentService}")
    private DepartmentService departmentService;
    @ManagedProperty(value = "#{wtPeriodeService}")
    private WtPeriodeService wtPeriodeService;
    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        
        distribusiKaryawanPerDepartment = new CartesianChartModel();
        pieModel = new PieChartModel();
        totalMale = new Long(0);
        totalFemale = new Long(0);
        try {
            /**
             * calculate employee distribution based on GENDER
             */
            Map<String, Long> employeesByGender = empDataService.getTotalByGender();
            totalFemale = employeesByGender.get("male");
            totalMale = employeesByGender.get("female");
            lastUpdateEmpDistByGender = new Date(employeesByGender.get("lastUpdate"));
            /**
             * calculate employee distribution based on DEPARTMENT
             */
            Map<String, Long> employeesByDepartment = empDataService.getTotalByDepartment();
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
                distribusiKaryawanPerDepartment.addSeries(deptSeries);
            }
            lastUpdateEmpDistByDepartment = new Date(employeesByDepartment.get("lastUpdate"));

            /**
             * calculate employee distribution based on AGE
             */
            Map<String, Long> employeesByAge = empDataService.getTotalByAge();
            pieModel.set("< 26", employeesByAge.get("lessThan26"));
            pieModel.set("25-30", employeesByAge.get("between26And30"));
            pieModel.set("31-35", employeesByAge.get("between31And35"));
            pieModel.set("36-40", employeesByAge.get("between36And40"));
            pieModel.set("> 40", employeesByAge.get("moreThan40"));
            lastUpdateEmpDistByAge = new Date(employeesByAge.get("lastUpdate"));
            
            //Get Period Active
            WtPeriode activeWtPeriode = wtPeriodeService.getEntityByPayrollTypeActive();
            persentasiKehadiranPerWeek = new CartesianChartModel();
            
            //Get Attendance Percentation per Department on Active Period
            Map<String, List<DepAttendanceRealizationViewModel>> mapResult = empDataService.getListDepAttendanceByDepartmentIdAndRangeDate(activeWtPeriode.getFromPeriode(), activeWtPeriode.getUntilPeriode());
            
            //Looping and render it
            for (Map.Entry<String, List<DepAttendanceRealizationViewModel>> entry : mapResult.entrySet()) {
            	
            	ChartSeries charDepartmentSeries = new ChartSeries();
            	charDepartmentSeries.setLabel(entry.getKey());
            	
            	for(DepAttendanceRealizationViewModel depAttendanceModel : entry.getValue()){            		
            		charDepartmentSeries.set(ResourceBundleUtil.getAsString("global.week") + "  " +depAttendanceModel.getWeekNumber(), depAttendanceModel.getAttendancePercentage().doubleValue());
            	}
            	
            	persentasiKehadiranPerWeek.addSeries(charDepartmentSeries);            	
            }

        } catch (Exception e) {
            LOGGER.error("Error when calculate employee distribution based on Gender, Age or Department", e);
        }

        

        presensiModel = new CartesianChartModel();
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
        presensiModel.addSeries(hadir);
        presensiModel.addSeries(cutiDanDinas);
        presensiModel.addSeries(ijinDanSakit);
        presensiModel.addSeries(tanpaKeterangan);

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

	public void setWtPeriodeService(WtPeriodeService wtPeriodeService) {
		this.wtPeriodeService = wtPeriodeService;
	}
    
    
}
