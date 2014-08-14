/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;

import com.inkubator.hrm.entity.RiwayatAkses;
import com.inkubator.hrm.service.DepartmentService;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.RiwayatAksesService;
import com.inkubator.hrm.web.model.LoginHistoryModel;
import com.inkubator.securitycore.util.UserInfoUtil;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;

/**
 *
 * @author Deni Husni FR
 */
@ManagedBean(name = "homeController")
@RequestScoped
public class HomeController extends BaseController {

	private Long totalMale;
	private Long totalFemale;
    private PieChartModel pieModel;
    private CartesianChartModel distribusiKaryawanPerDepartment;
    private CartesianChartModel presensiModel;
    private CartesianChartModel persentasiKehadiranPerWeek;
    private List<LoginHistoryModel> logHistorys = new ArrayList<>();
    @ManagedProperty(value = "#{riwayatAksesService}")
    private RiwayatAksesService riwayatAksesService;
    @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;
    @ManagedProperty(value = "#{departmentService}")
    private DepartmentService departmentService;

    public void setRiwayatAksesService(RiwayatAksesService riwayatAksesService) {
        this.riwayatAksesService = riwayatAksesService;
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

    @PostConstruct
    @Override
    public void initialization() {
        StringBuffer urlPath = FacesUtil.getRequest().getRequestURL();
        RiwayatAkses akses=new RiwayatAkses();
        akses.setDateAccess(new Date());
        akses.setPathUrl(urlPath.toString());
        akses.setUserId(UserInfoUtil.getUserName());
        try {
            riwayatAksesService.doSaveAccess(akses);
        } catch (Exception ex) {
           LOGGER.error("Error", ex);
        }
        super.initialization();
        
        distribusiKaryawanPerDepartment = new CartesianChartModel();
        pieModel = new PieChartModel();
        totalMale = (long) 0;
        totalFemale = (long) 0;
        try {
        	Map<String, Long> employeesByGender = empDataService.getTotalByGender();
        	totalFemale = employeesByGender.get("male");
        	totalMale = employeesByGender.get("female");
        	
	        Map<String, Long> employeesByDepartment = empDataService.getTotalByDepartment();	        
	        int i = 0;
	        for(Map.Entry<String, Long> entry : employeesByDepartment.entrySet()){
	        	ChartSeries deptSeries = new ChartSeries();
	            deptSeries.setLabel(entry.getKey());
	            deptSeries.set("Department", entry.getValue());
	            distribusiKaryawanPerDepartment.addSeries(deptSeries);
	            
	            i++;
	            if(i == 8){ // only 8 department is allowed to show
	            	break;
	            }
	        }
	
	        Map<String, Long> employeesByAge = empDataService.getTotalByAge();	        
	        pieModel.set("< 26", employeesByAge.get("lessThan26"));
	        pieModel.set("25-30", employeesByAge.get("between26And30"));
	        pieModel.set("31-35", employeesByAge.get("between31And35"));
	        pieModel.set("36-40", employeesByAge.get("between36And40"));
	        pieModel.set("> 40", employeesByAge.get("moreThan40"));
        
        } catch (Exception e) {
			
			e.printStackTrace();
		}
        
        persentasiKehadiranPerWeek = new CartesianChartModel();
        ChartSeries itpercent = new ChartSeries();
        itpercent.setLabel("IT & RND");
        itpercent.set("Week 1", 98);
        itpercent.set("Week 2", 90);
        itpercent.set("Week 3", 80);
        itpercent.set("Week 4", 95);
        itpercent.set("Week 5", 100);
        itpercent.set("Week 6", 80);

        ChartSeries hrgaPercent = new ChartSeries();
        hrgaPercent.setLabel("HR & GA");
        hrgaPercent.set("Week 1", 70);
        hrgaPercent.set("Week 2", 77);
        hrgaPercent.set("Week 3", 80);
        hrgaPercent.set("Week 4", 87);
        hrgaPercent.set("Week 5", 100);
        hrgaPercent.set("Week 6", 77);

        ChartSeries marketingPercent = new ChartSeries();
        marketingPercent.setLabel("MARKETING");
        marketingPercent.set("Week 1", 66);
        marketingPercent.set("Week 2", 55);
        marketingPercent.set("Week 3", 88);
        marketingPercent.set("Week 4", 47);
        marketingPercent.set("Week 5", 69);
        marketingPercent.set("Week 6", 45);

        ChartSeries finacePercent = new ChartSeries();
        finacePercent.setLabel("FINANCE");
        finacePercent.set("Week 1", 66);
        finacePercent.set("Week 2", 55);
        finacePercent.set("Week 3", 66);
        finacePercent.set("Week 4", 47);
        finacePercent.set("Week 5", 99);
        finacePercent.set("Week 6", 90);
        
        ChartSeries designPercent = new ChartSeries();
        designPercent.setLabel("DESIGN");
        designPercent.set("Week 1", 56);
        designPercent.set("Week 2", 77);
        designPercent.set("Week 3", 89);
        designPercent.set("Week 4", 99);
        designPercent.set("Week 5", 78);
        designPercent.set("Week 6", 100);
        
        ChartSeries productionPercent = new ChartSeries();
        productionPercent.setLabel("PRODUCTION");
        productionPercent.set("Week 1", 89);
        productionPercent.set("Week 2", 80);
        productionPercent.set("Week 3", 99);
        productionPercent.set("Week 4", 100);
        productionPercent.set("Week 5", 89);
        productionPercent.set("Week 6", 77);

        persentasiKehadiranPerWeek.addSeries(itpercent);
        persentasiKehadiranPerWeek.addSeries(hrgaPercent);
        persentasiKehadiranPerWeek.addSeries(marketingPercent);
        persentasiKehadiranPerWeek.addSeries(finacePercent);
        persentasiKehadiranPerWeek.addSeries(designPercent);
        persentasiKehadiranPerWeek.addSeries(productionPercent);

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
    
}
