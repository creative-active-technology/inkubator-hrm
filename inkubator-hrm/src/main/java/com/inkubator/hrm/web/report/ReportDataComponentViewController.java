package com.inkubator.hrm.web.report;


import com.inkubator.hrm.entity.Department;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.EmployeeType;
import com.inkubator.hrm.entity.GolonganJabatan;
import com.inkubator.hrm.entity.LogMonthEndPayroll;
import com.inkubator.hrm.entity.PaySalaryComponent;
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
import com.inkubator.hrm.service.GolonganJabatanService;
import com.inkubator.hrm.service.LogMonthEndPayrollService;
import com.inkubator.hrm.service.PaySalaryComponentService;
import com.inkubator.hrm.web.lazymodel.ReportDataKomponenLazyDataModel;
import com.inkubator.hrm.web.lazymodel.ReportRekapJabatanEmpLazyDataModel;
import com.inkubator.hrm.web.search.ReportDataComponentSearchParameter;
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
@ManagedBean(name = "reportDataComponentViewController")
@ViewScoped
public class ReportDataComponentViewController extends BaseController {
    
    private DualListModel<Department> dualListModelDepartment = new DualListModel<>();
    private DualListModel<PaySalaryComponent> dualListSalaryComponent = new DualListModel<>();
     private DualListModel<GolonganJabatan> dualListGolJabatan = new DualListModel<>();
    private DualListModel<EmployeeType> dualListModelEmpType = new DualListModel<>();
    
    private ReportDataComponentSearchParameter searchParameter;
    private LazyDataModel<LogMonthEndPayroll> lazyDataModel;    
    private List<Long> listDepartmentId;
    private List<Long> listPaySalaryCompId;
    private List<Long> listGolJabatanId;
    private List<Long> listEmployeeTypeId;
    
    private Date reportDate;
    @ManagedProperty(value = "#{logMonthEndPayrollService}")
    private LogMonthEndPayrollService logMonthEndPayrollService;
    @ManagedProperty(value = "#{departmentService}")
    private DepartmentService departmentService;
    @ManagedProperty(value = "#{employeeTypeService}")
    private EmployeeTypeService employeeTypeService;   
    @ManagedProperty(value = "#{paySalaryComponentService}")
    private PaySalaryComponentService paySalaryComponentService;   
    @ManagedProperty(value = "#{employeeTypeService}")
    private GolonganJabatanService golonganJabatanService;   
    
   
    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try {            
            listDepartmentId = new ArrayList<>();
            listPaySalaryCompId = new ArrayList<>();
            listGolJabatanId = new ArrayList<>();
            listEmployeeTypeId = new ArrayList<>();
            
            reportDate = new Date();
            searchParameter = new ReportDataComponentSearchParameter();
            searchParameter.setListDepartmentId(listDepartmentId);
            searchParameter.setListPaySalaryCompId(listPaySalaryCompId);
            searchParameter.setListGolJabatanId(listGolJabatanId);
            searchParameter.setListEmployeeTypeId(listEmployeeTypeId);
            
            // Inisialisasi DualListModel Department
            List<Department> listDepTarget = new ArrayList<>();
            List<Department> listDepartments = departmentService.getAllData();
            dualListModelDepartment.setSource(listDepartments);
            dualListModelDepartment.setTarget(listDepTarget);
            
            // Inisialisasi DualListModel Komponen Gaji
            List<PaySalaryComponent> listPaySalaryCompTarget = new ArrayList<>();
            List<PaySalaryComponent> listPaySalaryComponents = paySalaryComponentService.getAllData();
            dualListSalaryComponent.setSource(listPaySalaryComponents);
            dualListSalaryComponent.setTarget(listPaySalaryCompTarget);
            
            // Inisialisasi DualListModel Golongan Jabatan
            List<GolonganJabatan> listGolonganJabatanTarget = new ArrayList<>();
            List<GolonganJabatan> listGolonganJabatans = golonganJabatanService.getAllData();
            dualListGolJabatan.setSource(listGolonganJabatans);
            dualListGolJabatan.setTarget(listGolonganJabatanTarget);
            
            // Inisialisasi DualListModel EmployeeType
             List<EmployeeType> listEmpTarget = new ArrayList<>();
            List<EmployeeType> listEmployeeTypes = employeeTypeService.getAllData();
            dualListModelEmpType.setSource(listEmployeeTypes);
            dualListModelEmpType.setTarget(listEmpTarget);
            
        } catch (Exception ex) {
            Logger.getLogger(ReportDataComponentViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @PreDestroy
    public void cleanAndExit() {
        logMonthEndPayrollService = null;
        searchParameter = null;
        lazyDataModel = null;
        listDepartmentId = null;
        listEmployeeTypeId = null;
    }
    
    void fillId(){
        //Clear previous selectedIds
        listDepartmentId.clear();
        listGolJabatanId.clear();
        listPaySalaryCompId.clear();
        listEmployeeTypeId.clear();
        
        //Re-add id of each department from target
        if(!dualListModelDepartment.getTarget().isEmpty()){
            List<Department> listDep = dualListModelDepartment.getTarget();
           for(Department department : listDep){
               listDepartmentId.add(department.getId());
           }
        }
        
        //Re-add id of each Salary Component from target
        if(!dualListSalaryComponent.getTarget().isEmpty()){
            List<PaySalaryComponent> listPaySalaryComponents = dualListSalaryComponent.getTarget();
           for(PaySalaryComponent component : listPaySalaryComponents){
               listPaySalaryCompId.add(component.getId());
           }
        }
        
         //Re-add id of each Golongan Jabatan from target
        if(!dualListGolJabatan.getTarget().isEmpty()){
            List<GolonganJabatan> listGolonganJabatans = dualListGolJabatan.getTarget();
           for(GolonganJabatan golJabatan : listGolonganJabatans){
               listGolJabatanId.add(golJabatan.getId());
           }
        }
        
        //Re-add id of each employee type from target
        if(!dualListModelEmpType.getTarget().isEmpty()){
           List<EmployeeType> listEmpType = dualListModelEmpType.getTarget();
           for(EmployeeType empType : listEmpType){
               listEmployeeTypeId.add(empType.getId());
           }
        }
        
        //set to searchParameter
        searchParameter.setListDepartmentId(listDepartmentId);
        searchParameter.setListPaySalaryCompId(listPaySalaryCompId);
        searchParameter.setListGolJabatanId(listGolJabatanId);
        searchParameter.setListEmployeeTypeId(listEmployeeTypeId);
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

    public DualListModel<PaySalaryComponent> getDualListSalaryComponent() {
        return dualListSalaryComponent;
    }

    public void setDualListSalaryComponent(DualListModel<PaySalaryComponent> dualListSalaryComponent) {
        this.dualListSalaryComponent = dualListSalaryComponent;
    }

    public DualListModel<GolonganJabatan> getDualListGolJabatan() {
        return dualListGolJabatan;
    }

    public void setDualListGolJabatan(DualListModel<GolonganJabatan> dualListGolJabatan) {
        this.dualListGolJabatan = dualListGolJabatan;
    }
    
    public ReportDataComponentSearchParameter getSearchParameter() {
        return searchParameter;
    }

    public void setSearchParameter(ReportDataComponentSearchParameter searchParameter) {
        this.searchParameter = searchParameter;
    }

    public LazyDataModel<LogMonthEndPayroll> getLazyDataModel() {
        if (lazyDataModel == null) {          
            lazyDataModel = new ReportDataKomponenLazyDataModel(searchParameter, logMonthEndPayrollService);
        }
        return lazyDataModel;
    }

    public void setLazyDataModel(LazyDataModel<LogMonthEndPayroll> lazyDataModel) {
        this.lazyDataModel = lazyDataModel;
    }

    

    public List<Long> getListDepartmentId() {
        return listDepartmentId;
    }

    public void setListDepartmentId(List<Long> listDepartmentId) {
        this.listDepartmentId = listDepartmentId;
    }

    public List<Long> getListPaySalaryCompId() {
        return listPaySalaryCompId;
    }

    public void setListPaySalaryCompId(List<Long> listPaySalaryCompId) {
        this.listPaySalaryCompId = listPaySalaryCompId;
    }

    public List<Long> getListGolJabatanId() {
        return listGolJabatanId;
    }

    public void setListGolJabatanId(List<Long> listGolJabatanId) {
        this.listGolJabatanId = listGolJabatanId;
    }

    public List<Long> getListEmployeeTypeId() {
        return listEmployeeTypeId;
    }

    public void setListEmployeeTypeId(List<Long> listEmployeeTypeId) {
        this.listEmployeeTypeId = listEmployeeTypeId;
    }

    public PaySalaryComponentService getPaySalaryComponentService() {
        return paySalaryComponentService;
    }

    public void setPaySalaryComponentService(PaySalaryComponentService paySalaryComponentService) {
        this.paySalaryComponentService = paySalaryComponentService;
    }

    public GolonganJabatanService getGolonganJabatanService() {
        return golonganJabatanService;
    }

    public void setGolonganJabatanService(GolonganJabatanService golonganJabatanService) {
        this.golonganJabatanService = golonganJabatanService;
    }

    public LogMonthEndPayrollService getLogMonthEndPayrollService() {
        return logMonthEndPayrollService;
    }

    public void setLogMonthEndPayrollService(LogMonthEndPayrollService logMonthEndPayrollService) {
        this.logMonthEndPayrollService = logMonthEndPayrollService;
    }
    
    
    
  
}
