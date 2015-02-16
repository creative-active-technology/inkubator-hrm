/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.report;

import com.inkubator.hrm.entity.Department;
import com.inkubator.hrm.entity.EducationLevel;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.service.DepartmentService;
import com.inkubator.hrm.service.EducationLevelService;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.web.lazymodel.ReportEmployeeEducationLazyDataModel;
import com.inkubator.webcore.controller.BaseController;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.DualListModel;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author Deni
 */
@ManagedBean(name = "reportEmployeeEducationViewController")
@ViewScoped
public class ReportEmployeeEducationViewController extends BaseController {

    @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;
    @ManagedProperty(value = "#{departmentService}")
    private DepartmentService departmentService;
    @ManagedProperty(value = "#{educationLevelService}")
    private EducationLevelService educationLevelService;
    private DualListModel<EducationLevel> dualListEducationLevel = new DualListModel<>();
    private List<EducationLevel> sourceEducationLevel = new ArrayList<>();
    private DualListModel<Department> dualListDepartement = new DualListModel<>();
    private List<Department> sourceDepartement = new ArrayList<>();
    private LazyDataModel<EmpData> lazyDataModel;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try {

            sourceEducationLevel = educationLevelService.getAllDataOrderByLevel();
            sourceDepartement = departmentService.getAllData();
            dualListDepartement.setSource(sourceDepartement);
            dualListEducationLevel.setSource(sourceEducationLevel);

        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    @PreDestroy
    public void cleanAndExit() {
        dualListEducationLevel = null;
        sourceEducationLevel = null;
        dualListEducationLevel = null;
        dualListDepartement = null;
        sourceEducationLevel = null;
        sourceDepartement = null;
    }

    public void doSearch() {
        lazyDataModel = null;
    }

    public EmpDataService getEmpDataService() {
        return empDataService;
    }

    public void setEmpDataService(EmpDataService empDataService) {
        this.empDataService = empDataService;
    }

    public DepartmentService getDepartmentService() {
        return departmentService;
    }

    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    public EducationLevelService getEducationLevelService() {
        return educationLevelService;
    }

    public void setEducationLevelService(EducationLevelService educationLevelService) {
        this.educationLevelService = educationLevelService;
    }

    public DualListModel<EducationLevel> getDualListEducationLevel() {
        return dualListEducationLevel;
    }

    public void setDualListEducationLevel(DualListModel<EducationLevel> dualListEducationLevel) {
        this.dualListEducationLevel = dualListEducationLevel;
    }

    public List<EducationLevel> getSourceEducationLevel() {
        return sourceEducationLevel;
    }

    public void setSourceEducationLevel(List<EducationLevel> sourceEducationLevel) {
        this.sourceEducationLevel = sourceEducationLevel;
    }

    public DualListModel<Department> getDualListDepartement() {
        return dualListDepartement;
    }

    public void setDualListDepartement(DualListModel<Department> dualListDepartement) {
        this.dualListDepartement = dualListDepartement;
    }

    public List<Department> getSourceDepartement() {
        return sourceDepartement;
    }

    public void setSourceDepartement(List<Department> sourceDepartement) {
        this.sourceDepartement = sourceDepartement;
    }

    public LazyDataModel<EmpData> getLazyDataModel() {
        if (lazyDataModel == null) {
            lazyDataModel = new ReportEmployeeEducationLazyDataModel(dualListDepartement.getTarget(), dualListEducationLevel.getTarget(), empDataService);
        }
        return lazyDataModel;
    }

    public void setLazyDataModel(LazyDataModel<EmpData> lazyDataModel) {
        this.lazyDataModel = lazyDataModel;
    }

}
