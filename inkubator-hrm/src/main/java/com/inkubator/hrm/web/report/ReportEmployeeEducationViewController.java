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
import com.inkubator.hrm.web.model.ReportEmployeeEducationViewModel;
import com.inkubator.webcore.controller.BaseController;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private DualListModel<Department> dualListDepartment = new DualListModel<>();
    private List<Department> sourceDepartment = new ArrayList<>();
    private LazyDataModel<ReportEmployeeEducationViewModel> lazyDataModel;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try {
            sourceEducationLevel = educationLevelService.getAllDataOrderByLevel();
            sourceDepartment = departmentService.getAllData();
            
            dualListDepartment.setSource(sourceDepartment);
            dualListEducationLevel.setSource(sourceEducationLevel);
        } catch (Exception ex) {
            Logger.getLogger(ReportEmployeeEducationViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @PreDestroy
    private void cleanAndExit() {
       empDataService = null;
       departmentService = null;
       educationLevelService = null;
       dualListDepartment = null;
       dualListEducationLevel = null;
       sourceDepartment = null;
       sourceEducationLevel = null;
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

    public DualListModel<Department> getDualListDepartment() {
        return dualListDepartment;
    }

    public void setDualListDepartment(DualListModel<Department> dualListDepartment) {
        this.dualListDepartment = dualListDepartment;
    }

    public List<Department> getSourceDepartment() {
        return sourceDepartment;
    }

    public void setSourceDepartment(List<Department> sourceDepartment) {
        this.sourceDepartment = sourceDepartment;
    }

    public LazyDataModel<ReportEmployeeEducationViewModel> getLazyDataModel() {
        if(lazyDataModel == null){
            lazyDataModel = new ReportEmployeeEducationLazyDataModel(dualListDepartment.getTarget(), dualListEducationLevel.getTarget(), empDataService);
        }
        return lazyDataModel;
    }

    public void setLazyDataModel(LazyDataModel<ReportEmployeeEducationViewModel> lazyDataModel) {
        this.lazyDataModel = lazyDataModel;
    }

    
}
