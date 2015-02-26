package com.inkubator.hrm.web.report;


import com.inkubator.hrm.entity.Department;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.EmployeeType;
import com.inkubator.hrm.service.DepartmentService;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.LazyDataModel;

import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.EmployeeTypeService;
import com.inkubator.hrm.web.lazymodel.ReportPensionPreparementLazyDataModel;
import com.inkubator.hrm.web.lazymodel.ReportRekapJabatanEmpLazyDataModel;
import com.inkubator.hrm.web.model.ReportEmpPensionPreparationModel;
import com.inkubator.hrm.web.search.ReportPensionPreparementSearchParameter;
import com.inkubator.hrm.web.search.ReportRekapJabatanEmpSearchParameter;
import com.inkubator.webcore.controller.BaseController;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.primefaces.model.DualListModel;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
@ManagedBean(name = "reportPensionPreparementViewController")
@ViewScoped
public class ReportPensionPreparementViewController extends BaseController {
    
    private DualListModel<Department> dualListModelDepartment = new DualListModel<>();
    private DualListModel<EmployeeType> dualListModelEmpType = new DualListModel<>();
    private DualListModel<Integer> dualListModelEmpAges = new DualListModel<>();
    private ReportPensionPreparementSearchParameter searchParameter;
    private LazyDataModel<ReportEmpPensionPreparationModel> lazyDataModel;    
    private List<Long> listDepartmentId;
    private List<Long> listEmployeeTypeId;
    private List<Integer> listEmpAges;
    private Date reportDate;
    @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;
    @ManagedProperty(value = "#{departmentService}")
    private DepartmentService departmentService;
    @ManagedProperty(value = "#{employeeTypeService}")
    private EmployeeTypeService employeeTypeService;    
   
    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try {            
            listDepartmentId = new ArrayList<>();
            listEmployeeTypeId = new ArrayList<>();
            listEmpAges = new ArrayList<>();
            reportDate = new Date();
            searchParameter = new ReportPensionPreparementSearchParameter();
            searchParameter.setListDepartmentId(listDepartmentId);
            searchParameter.setListEmployeeTypeId(listEmployeeTypeId);
            searchParameter.setListEmployeeAges(listEmpAges);
            
            // Inisialisasi DualListModel Department
            List<Department> listDepTarget = new ArrayList<>();
            List<Department> listDepartments = departmentService.getAllData();
            dualListModelDepartment.setSource(listDepartments);
            dualListModelDepartment.setTarget(listDepTarget);
            
            // Inisialisasi DualListModel EmployeeType
             List<EmployeeType> listEmpTarget = new ArrayList<>();
            List<EmployeeType> listEmployeeTypes = employeeTypeService.getAllData();
            dualListModelEmpType.setSource(listEmployeeTypes);
            dualListModelEmpType.setTarget(listEmpTarget);
            
            List<Integer> listEmpAgesSource = new ArrayList<>();
            List<Integer> listEmpAgesTarget = new ArrayList<>();
            for(int i = 45; i<=55; i++){
                listEmpAgesSource.add(i);
            }
            
            dualListModelEmpAges.setSource(listEmpAgesSource);
            dualListModelEmpAges.setTarget(listEmpAgesTarget);
            
        } catch (Exception ex) {
            Logger.getLogger(ReportPensionPreparementViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @PreDestroy
    public void cleanAndExit() {
        empDataService = null;
        searchParameter = null;
        lazyDataModel = null;
        listDepartmentId = null;
        listEmployeeTypeId = null;
    }
    
    void fillId(){
        //Clear previous selectedIds
        listDepartmentId.clear();
        listEmployeeTypeId.clear();
        listEmpAges.clear();
        
        //Re-add id of each department from target
        if(!dualListModelDepartment.getTarget().isEmpty()){
            List<Department> listDep = dualListModelDepartment.getTarget();
           for(Department department : listDep){
               listDepartmentId.add(department.getId());
           }
        }
        
        //Re-add id of each employee type from target
        if(!dualListModelEmpType.getTarget().isEmpty()){
           List<EmployeeType> listEmpType = dualListModelEmpType.getTarget();
           for(EmployeeType empType : listEmpType){
               listEmployeeTypeId.add(empType.getId());
           }
        }
        
        //Re-add ages of each employee ages from target
        if(!dualListModelEmpAges.getTarget().isEmpty()){
           List<Integer> listEmployeeAges = dualListModelEmpAges.getTarget();
           for(Integer i : listEmployeeAges){
               listEmpAges.add(i);
           }
        }
        
        //set to searchParameter
        searchParameter.setListDepartmentId(listDepartmentId);
        searchParameter.setListEmployeeTypeId(listEmployeeTypeId);
        searchParameter.setListEmployeeAges(listEmpAges);
    }
    public void doSearch() {        
        fillId();        
        lazyDataModel = null;
    }
    
    public DualListModel<EmployeeType> getDualListModelEmpType() {
        return dualListModelEmpType;
    }

    public void setDualListModelEmpType(DualListModel<EmployeeType> dualListModelEmpType) {
        this.dualListModelEmpType = dualListModelEmpType;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }
    
    
    public DualListModel<Department> getDualListModelDepartment() {
        return dualListModelDepartment;
    }

    public void setDualListModelDepartment(DualListModel<Department> dualListModelDepartment) {
        this.dualListModelDepartment = dualListModelDepartment;
    }

    public DepartmentService getDepartmentService() {
        return departmentService;
    }

    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    public EmployeeTypeService getEmployeeTypeService() {
        return employeeTypeService;
    }

    public void setEmployeeTypeService(EmployeeTypeService employeeTypeService) {
        this.employeeTypeService = employeeTypeService;
    }
    

    public ReportPensionPreparementSearchParameter getSearchParameter() {
        return searchParameter;
    }

    public void setSearchParameter(ReportPensionPreparementSearchParameter searchParameter) {
        this.searchParameter = searchParameter;
    }

    public LazyDataModel<ReportEmpPensionPreparationModel> getLazyDataModel() {
        if (lazyDataModel == null) {          
            lazyDataModel = new ReportPensionPreparementLazyDataModel(searchParameter, empDataService);
        }
        return lazyDataModel;
    }

    public void setLazyDataModel(LazyDataModel<ReportEmpPensionPreparationModel> lazyDataModel) {
        this.lazyDataModel = lazyDataModel;
    }

    public EmpDataService getEmpDataService() {
        return empDataService;
    }

    public void setEmpDataService(EmpDataService empDataService) {
        this.empDataService = empDataService;
    }

    public DualListModel<Integer> getDualListModelEmpAges() {
        return dualListModelEmpAges;
    }

    public void setDualListModelEmpAges(DualListModel<Integer> dualListModelEmpAges) {
        this.dualListModelEmpAges = dualListModelEmpAges;
    }
    
    
  
}
