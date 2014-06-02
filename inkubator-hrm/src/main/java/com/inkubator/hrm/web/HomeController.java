/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web;

import com.inkubator.hrm.web.model.LoginHistoryModel;
import com.inkubator.webcore.controller.BaseController;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author Deni Husni FR
 */
@ManagedBean(name = "homeController")
@RequestScoped
public class HomeController extends BaseController {

    private PieChartModel pieModel;
    private CartesianChartModel distribusiKaryawanPerDepartment;
    private CartesianChartModel presensiModel;
    private PieChartModel distibusiPerDepartment;
    private CartesianChartModel persentasiKehadiranPerWeek;
    private List<LoginHistoryModel> logHistorys = new ArrayList<>();

    public CartesianChartModel getDistribusiKaryawanPerDepartment() {
        return distribusiKaryawanPerDepartment;
    }

    public void setDistribusiKaryawanPerDepartment(CartesianChartModel distribusiKaryawanPerDepartment) {
        this.distribusiKaryawanPerDepartment = distribusiKaryawanPerDepartment;
    }

    private String haha;

    public String getHaha() {
        return haha;
    }

    public void setHaha(String haha) {
        this.haha = haha;
    }

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        System.out.println("tereksekusi");
        distribusiKaryawanPerDepartment = new CartesianChartModel();
        persentasiKehadiranPerWeek = new CartesianChartModel();
        ChartSeries itpercent = new ChartSeries();
        itpercent.setLabel("IT & RND");
        itpercent.set("Week 1", 98);
        itpercent.set("Week 2", 90);
        itpercent.set("Week 3", 80);
        itpercent.set("Week 4", 95);
        itpercent.set("Week 5", 100);
        itpercent.set("Week 6", 80);
//        itpercent.set("Week 7", 70);
//        itpercent.set("Week 8", 75);
//        itpercent.set("Week 9", 77);
//        itpercent.set("Week 10", 98);

        ChartSeries hrgaPercent = new ChartSeries();
        hrgaPercent.setLabel("HR & GA");
        hrgaPercent.set("Week 1", 70);
        hrgaPercent.set("Week 2", 77);
        hrgaPercent.set("Week 3", 80);
        hrgaPercent.set("Week 4", 87);
        hrgaPercent.set("Week 5", 100);
        hrgaPercent.set("Week 6", 77);
//        hrgaPercent.set("Week 7", 78);
//        hrgaPercent.set("Week 8", 75);
//        hrgaPercent.set("Week 9", 77);
//        hrgaPercent.set("Week 10", 98);

        ChartSeries marketingPercent = new ChartSeries();
        marketingPercent.setLabel("MARKETING");
        marketingPercent.set("Week 1", 66);
        marketingPercent.set("Week 2", 55);
        marketingPercent.set("Week 3", 88);
        marketingPercent.set("Week 4", 47);
        marketingPercent.set("Week 5", 69);
        marketingPercent.set("Week 6", 45);
//        marketingPercent.set("Week 7", 77);
//        marketingPercent.set("Week 8", 99);
//        marketingPercent.set("Week 9", 67);
//        marketingPercent.set("Week 10", 98);

        ChartSeries finacePercent = new ChartSeries();
        finacePercent.setLabel("FINANCE");
        finacePercent.set("Week 1", 66);
        finacePercent.set("Week 2", 55);
        finacePercent.set("Week 3", 66);
        finacePercent.set("Week 4", 47);
        finacePercent.set("Week 5", 99);
        finacePercent.set("Week 6", 90);
//        finacePercent.set("Week 7", 77);
//        finacePercent.set("Week 8", 99);
//        finacePercent.set("Week 9", 100);
//        finacePercent.set("Week 10", 98);
        ChartSeries designPercent = new ChartSeries();
        designPercent.setLabel("DESIGN");
        designPercent.set("Week 1", 56);
        designPercent.set("Week 2", 77);
        designPercent.set("Week 3", 89);
        designPercent.set("Week 4", 99);
        designPercent.set("Week 5", 78);
        designPercent.set("Week 6", 100);
//        designPercent.set("Week 7", 77);
//        designPercent.set("Week 8", 75);
//        designPercent.set("Week 9", 77);
//        designPercent.set("Week 10", 66);
        ChartSeries productionPercent = new ChartSeries();
        productionPercent.setLabel("PRODUCTION");
        productionPercent.set("Week 1", 89);
        productionPercent.set("Week 2", 80);
        productionPercent.set("Week 3", 99);
        productionPercent.set("Week 4", 100);
        productionPercent.set("Week 5", 89);
        productionPercent.set("Week 6", 77);
//        productionPercent.set("Week 7", 89);
//        productionPercent.set("Week 8", 60);
//        productionPercent.set("Week 9", 99);
//        productionPercent.set("Week 10", 88);

        persentasiKehadiranPerWeek.addSeries(itpercent);
        persentasiKehadiranPerWeek.addSeries(hrgaPercent);
        persentasiKehadiranPerWeek.addSeries(marketingPercent);
        persentasiKehadiranPerWeek.addSeries(finacePercent);
        persentasiKehadiranPerWeek.addSeries(designPercent);
        persentasiKehadiranPerWeek.addSeries(productionPercent);

        ChartSeries it = new ChartSeries();
        it.setLabel("IT & RND");
        it.set("Departement", 60);

        ChartSeries hrga = new ChartSeries();
        hrga.setLabel("HR & GA");
        hrga.set("Departement", 20);

        ChartSeries finance = new ChartSeries();
        finance.setLabel("FINANCE");
        finance.set("Departement", 15);

        ChartSeries marketing = new ChartSeries();
        marketing.setLabel("MARKETING");
        marketing.set("Departement", 25);

        ChartSeries production = new ChartSeries();
        production.setLabel("PRODUCTION");
        production.set("Departement", 100);

        ChartSeries design = new ChartSeries();
        design.setLabel("DESIGN");
        design.set("Departement", 10);
        ChartSeries warehouse = new ChartSeries();
        warehouse.setLabel("GUDANG");
        warehouse.set("Gudang", 24);

        distribusiKaryawanPerDepartment.addSeries(it);
        distribusiKaryawanPerDepartment.addSeries(hrga);
        distribusiKaryawanPerDepartment.addSeries(finance);
        distribusiKaryawanPerDepartment.addSeries(marketing);
        distribusiKaryawanPerDepartment.addSeries(production);
        distribusiKaryawanPerDepartment.addSeries(design);
        distribusiKaryawanPerDepartment.addSeries(warehouse);

        pieModel = new PieChartModel();

        pieModel.set("< 25 Th", 540);
        pieModel.set("25-30 Th", 325);
        pieModel.set("30-35 Th", 702);
        pieModel.set("35-40 Th", 421);
        pieModel.set("> 40 Th", 333);

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

        distibusiPerDepartment = new PieChartModel();
        distibusiPerDepartment.set("IT & RND", 40);
        distibusiPerDepartment.set("HR & GA", 15);
        distibusiPerDepartment.set("FINANCE", 10);
        distibusiPerDepartment.set("MARKETING", 30);
        distibusiPerDepartment.set("PRODUCTION", 70);

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

    public PieChartModel getDistibusiPerDepartment() {
        return distibusiPerDepartment;
    }

    public void setDistibusiPerDepartment(PieChartModel distibusiPerDepartment) {
        this.distibusiPerDepartment = distibusiPerDepartment;
    }

    public CartesianChartModel getPersentasiKehadiranPerWeek() {
        return persentasiKehadiranPerWeek;
    }

    public void setPersentasiKehadiranPerWeek(CartesianChartModel persentasiKehadiranPerWeek) {
        this.persentasiKehadiranPerWeek = persentasiKehadiranPerWeek;
    }

 
}
