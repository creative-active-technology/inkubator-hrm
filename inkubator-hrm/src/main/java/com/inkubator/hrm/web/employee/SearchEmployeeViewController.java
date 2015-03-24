/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.employee;

import com.inkubator.hrm.entity.Department;
import com.inkubator.hrm.entity.GolonganJabatan;
import com.inkubator.hrm.service.DepartmentService;
import com.inkubator.hrm.service.GolonganJabatanService;
import com.inkubator.webcore.controller.BaseController;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.DualListModel;

/**
 *
 * @author Deni
 */
@ManagedBean(name = "searchEmployeeViewController")
@ViewScoped
public class SearchEmployeeViewController extends BaseController {

    @ManagedProperty(value = "#{departmentService}")
    private DepartmentService departmentService;
    @ManagedProperty(value = "#{golonganJabatanService}")
    private GolonganJabatanService golonganJabatanService;
    private DualListModel<Department> departementDualListModel = new DualListModel<>();
    private List<Department> sourceDepartment = new ArrayList<>();
    private DualListModel<GolonganJabatan> golonganJabatanDualListModel = new DualListModel<>();
    private List<GolonganJabatan> sourceGolJab = new ArrayList<>();

    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();

            //source list
            sourceDepartment = departmentService.getAllData();
            sourceGolJab = golonganJabatanService.getAllData();
            
            //dual list
            departementDualListModel.setSource(sourceDepartment);
            golonganJabatanDualListModel.setSource(sourceGolJab);
        } catch (Exception ex) {
            LOGGER.error("Error", ex);

        }
    }

    @PreDestroy
    public void cleanAndExit() {
        sourceGolJab = null;
        golonganJabatanDualListModel = null;
        sourceDepartment = null;
        departementDualListModel = null;
        golonganJabatanService = null;
        departmentService = null;
    }

    public DepartmentService getDepartmentService() {
        return departmentService;
    }

    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    public GolonganJabatanService getGolonganJabatanService() {
        return golonganJabatanService;
    }

    public void setGolonganJabatanService(GolonganJabatanService golonganJabatanService) {
        this.golonganJabatanService = golonganJabatanService;
    }

    public DualListModel<Department> getDepartementDualListModel() {
        return departementDualListModel;
    }

    public void setDepartementDualListModel(DualListModel<Department> departementDualListModel) {
        this.departementDualListModel = departementDualListModel;
    }

    public List<Department> getSourceDepartment() {
        return sourceDepartment;
    }

    public void setSourceDepartment(List<Department> sourceDepartment) {
        this.sourceDepartment = sourceDepartment;
    }

    public DualListModel<GolonganJabatan> getGolonganJabatanDualListModel() {
        return golonganJabatanDualListModel;
    }

    public void setGolonganJabatanDualListModel(DualListModel<GolonganJabatan> golonganJabatanDualListModel) {
        this.golonganJabatanDualListModel = golonganJabatanDualListModel;
    }

    public List<GolonganJabatan> getSourceGolJab() {
        return sourceGolJab;
    }

    public void setSourceGolJab(List<GolonganJabatan> sourceGolJab) {
        this.sourceGolJab = sourceGolJab;
    }
    
    
}
