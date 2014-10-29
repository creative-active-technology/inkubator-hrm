package com.inkubator.hrm.web.report;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.LazyDataModel;

import com.inkubator.hrm.entity.Department;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.service.DepartmentService;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.web.lazymodel.ReportEmpWorkingGroupLazyDataModel;
import com.inkubator.hrm.web.search.ReportEmpWorkingGroupParameter;
import com.inkubator.webcore.controller.BaseController;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "reportEmpWorkingGroupViewController")
@ViewScoped
public class ReportEmpWorkingGroupViewController extends BaseController {

    private ReportEmpWorkingGroupParameter searchParameter;
    private LazyDataModel<EmpData> lazyDataModel;
    private List<Department> listDepartment;
    @ManagedProperty(value = "#{departmentService}")
    private DepartmentService departmentService;
    @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        searchParameter = new ReportEmpWorkingGroupParameter();
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

	public ReportEmpWorkingGroupParameter getSearchParameter() {
		return searchParameter;
	}

	public void setSearchParameter(ReportEmpWorkingGroupParameter searchParameter) {
		this.searchParameter = searchParameter;
	}

	public LazyDataModel<EmpData> getLazyDataModel() {
		if(lazyDataModel == null){
			lazyDataModel =  new ReportEmpWorkingGroupLazyDataModel(searchParameter, empDataService);
		}
		return lazyDataModel;
	}

	public void setLazyDataModel(LazyDataModel<EmpData> lazyDataModel) {
		this.lazyDataModel = lazyDataModel;
	}

	public List<Department> getListDepartment() {
		return listDepartment;
	}

	public void setListDepartment(List<Department> listDepartment) {
		this.listDepartment = listDepartment;
	}

	public EmpDataService getEmpDataService() {
		return empDataService;
	}

	public void setEmpDataService(EmpDataService empDataService) {
		this.empDataService = empDataService;
	}

	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	public void doSearch() {
        lazyDataModel = null;
    }
}
