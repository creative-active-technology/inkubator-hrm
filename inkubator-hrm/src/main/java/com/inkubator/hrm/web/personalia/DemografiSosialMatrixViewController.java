/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.personalia;

import com.inkubator.hrm.service.DemografiSosialMatrixService;
import com.inkubator.hrm.service.EducationLevelService;
import com.inkubator.hrm.web.model.EmpDataMatrixModel;
import com.inkubator.webcore.controller.BaseController;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Deni
 */
@ManagedBean(name = "demografiSosialMatrixViewController")
@ViewScoped
public class DemografiSosialMatrixViewController extends BaseController {

    private List<String> dataAbsis;
    private List<String> dataOrdinate;
    private List<List<String>> dataValue;
    private List<EmpDataMatrixModel> listDataAbsis;
    private String absis;
    private String ordinate;
    @ManagedProperty(value = "#{demografiSosialMatrixService}")
    private DemografiSosialMatrixService demografiSosialMatrixService;
    private Integer x;
    private Integer y;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();

        dataAbsis = new ArrayList<String>();
        dataOrdinate = new ArrayList<String>();
        listDataAbsis = new ArrayList<EmpDataMatrixModel>();
//        x = 1;
//        EmpDataMatrixModel empDataMatrixModel = new EmpDataMatrixModel();
//        empDataMatrixModel.setFemale("M");
//        empDataMatrixModel.setMale("F");
//        listDataAbsis.add(empDataMatrixModel);
//        dataOrdinate.add("haha");
//        dataOrdinate.add("hihi");
//        dataAbsis.add("F");
//        dataAbsis.add("M");
    }

    @PreDestroy
    public void cleanAndExit() {
        dataAbsis = null;
        demografiSosialMatrixService = null;
        dataOrdinate = null;
        x = null;
        y = null;
    }

    public void updateMatrix() throws Exception {
        //get absis
        if (!dataAbsis.isEmpty()) {
            dataAbsis.clear();
        }
        dataAbsis = demografiSosialMatrixService.getAllNameByParamOrderByLevelForAbsis(absis);
        //get ordinate
        if (!listDataAbsis.isEmpty()) {
            listDataAbsis.clear();
        }
        if (ordinate.equals("pendidikan")) {
            listDataAbsis = demografiSosialMatrixService.getAllNameOrderByLevelWithModel();
        } else if (ordinate.equals("gender")) {
            listDataAbsis = demografiSosialMatrixService.getAllNameByGenderOrderByLevelWithModel();
        }

    }

    public List<String> getDataAbsis() {
        return dataAbsis;
    }

    public void setDataAbsis(List<String> dataAbsis) {
        this.dataAbsis = dataAbsis;
    }

    public List<EmpDataMatrixModel> getListDataAbsis() {
        return listDataAbsis;
    }

    public void setListDataAbsis(List<EmpDataMatrixModel> listDataAbsis) {
        this.listDataAbsis = listDataAbsis;
    }

    public String getAbsis() {
        return absis;
    }

    public void setAbsis(String absis) {
        this.absis = absis;
    }

    public String getOrdinate() {
        return ordinate;
    }

    public void setOrdinate(String ordinate) {
        this.ordinate = ordinate;
    }

    public DemografiSosialMatrixService getDemografiSosialMatrixService() {
        return demografiSosialMatrixService;
    }

    public void setDemografiSosialMatrixService(DemografiSosialMatrixService demografiSosialMatrixService) {
        this.demografiSosialMatrixService = demografiSosialMatrixService;
    }

    public List<String> getDataOrdinate() {
        return dataOrdinate;
    }

    public void setDataOrdinate(List<String> dataOrdinate) {
        this.dataOrdinate = dataOrdinate;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public List<List<String>> getDataValue() {
        return dataValue;
    }

    public void setDataValue(List<List<String>> dataValue) {
        this.dataValue = dataValue;
    }

}
