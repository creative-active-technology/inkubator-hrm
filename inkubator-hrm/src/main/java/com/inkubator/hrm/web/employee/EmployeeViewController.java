/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.employee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;
import org.primefaces.model.LazyDataModel;

import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.web.lazymodel.EmployeeLazyDataModel;
import com.inkubator.hrm.web.search.EmpDataSearchParameter;
import com.inkubator.webcore.controller.BaseController;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "employeeViewController")
@ViewScoped
public class EmployeeViewController extends BaseController {

    private EmpDataSearchParameter empDataSearchParameter;
    private LazyDataModel<EmpData> empDataLazyDataModel;
    @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;
    private EmpData selectedEmpData;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        empDataSearchParameter = new EmpDataSearchParameter();

    }
    
    public void doEmployeeTimeSchedule() {
        List<String> values = new ArrayList<>();
        values.add(String.valueOf(selectedEmpData.getId()));
        Map<String, List<String>> dataToSend = new HashMap<>();
        dataToSend.put("empId", values);
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 450);
        options.put("contentHeight", 270);
        RequestContext.getCurrentInstance().openDialog("emp_schedule_form", options, dataToSend);

    }

    @PreDestroy
    public void cleanAndExit() {
        empDataSearchParameter = null;
        empDataLazyDataModel = null;
        empDataService = null;
        selectedEmpData = null;

    }

    public String doDetailRenumeration() {
        return "/protected/payroll/basic_renumeration_detail.htm?faces-redirect=true&execution=e" + selectedEmpData.getId();
    }
    
    public String doDetailShedule() {
        return "/protected/employee/emp_schedule_detail.htm?faces-redirect=true&execution=r" + selectedEmpData.getId();
    }
    
    public String doPlacementOfEmployee() {
        return "/protected/employee/work_schedule_form.htm?faces-redirect=true";
    }

    public void doSearch() {
    	empDataLazyDataModel = null;
    }

    public EmpData getSelectedEmpData() {
        return selectedEmpData;
    }

    public void setSelectedEmpData(EmpData selectedEmpData) {
        this.selectedEmpData = selectedEmpData;
    }
    
    public EmpDataSearchParameter getEmpDataSearchParameter() {
        return empDataSearchParameter;
    }

    public void setEmpDataSearchParameter(EmpDataSearchParameter empDataSearchParameter) {
        this.empDataSearchParameter = empDataSearchParameter;
    }

    public void setEmpDataService(EmpDataService empDataService) {
        this.empDataService = empDataService;
    }

    public EmpDataService getEmpDataService() {
		return empDataService;
	}

	public LazyDataModel<EmpData> getEmpDataLazyDataModel() {
        if (empDataLazyDataModel == null) {
            empDataLazyDataModel = new EmployeeLazyDataModel(empDataSearchParameter, empDataService);
        }
        return empDataLazyDataModel;
    }

    public void setEmpDataLazyDataModel(LazyDataModel<EmpData> empDataLazyDataModel) {
        this.empDataLazyDataModel = empDataLazyDataModel;
    }    
}
