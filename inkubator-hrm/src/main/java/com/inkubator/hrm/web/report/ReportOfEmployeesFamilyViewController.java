/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.report;

import com.inkubator.hrm.entity.BioFamilyRelationship;
import com.inkubator.hrm.entity.Department;
import com.inkubator.hrm.service.BioFamilyRelationshipService;
import com.inkubator.hrm.service.DepartmentService;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.web.lazymodel.ReportOfEmployeesFamilyLazyDataModel;
import com.inkubator.hrm.web.search.ReportOfEmployeesFamilySearchParameter;
import com.inkubator.webcore.controller.BaseController;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author Deni
 */
@ManagedBean(name = "reportOfEmployeesFamilyViewController")
@ViewScoped
public class ReportOfEmployeesFamilyViewController extends BaseController {

    private ReportOfEmployeesFamilySearchParameter searchParameter;
    private LazyDataModel<BioFamilyRelationship> lazyDataModel;
    private List<Department> listDepartment;
    @ManagedProperty(value = "#{departmentService}")
    private DepartmentService departmentService;
    @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;
    @ManagedProperty(value = "#{bioFamilyRelationshipService}")
    private BioFamilyRelationshipService bioFamilyRelationshipService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        searchParameter = new ReportOfEmployeesFamilySearchParameter();
        try {
            listDepartment = departmentService.getAllData();
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    @PreDestroy
    public void cleanAndExit() {
        empDataService = null;
        searchParameter = null;
        lazyDataModel = null;
        listDepartment = null;
    }

    public void doSearch() {
        lazyDataModel = null;
    }
    
    public ReportOfEmployeesFamilySearchParameter getSearchParameter() {
        return searchParameter;
    }

    public void setSearchParameter(ReportOfEmployeesFamilySearchParameter searchParameter) {
        this.searchParameter = searchParameter;
    }

    public LazyDataModel<BioFamilyRelationship> getLazyDataModel() {
        if (lazyDataModel == null) {
            lazyDataModel = new ReportOfEmployeesFamilyLazyDataModel(searchParameter, bioFamilyRelationshipService);
        }
        return lazyDataModel;
    }

    public void setLazyDataModel(LazyDataModel<BioFamilyRelationship> lazyDataModel) {
        this.lazyDataModel = lazyDataModel;
    }

    public List<Department> getListDepartment() {
        return listDepartment;
    }

    public void setListDepartment(List<Department> listDepartment) {
        this.listDepartment = listDepartment;
    }

    public DepartmentService getDepartmentService() {
        return departmentService;
    }

    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    public EmpDataService getEmpDataService() {
        return empDataService;
    }

    public void setEmpDataService(EmpDataService empDataService) {
        this.empDataService = empDataService;
    }

    public BioFamilyRelationshipService getBioFamilyRelationshipService() {
        return bioFamilyRelationshipService;
    }

    public void setBioFamilyRelationshipService(BioFamilyRelationshipService bioFamilyRelationshipService) {
        this.bioFamilyRelationshipService = bioFamilyRelationshipService;
    }
    
    
}
