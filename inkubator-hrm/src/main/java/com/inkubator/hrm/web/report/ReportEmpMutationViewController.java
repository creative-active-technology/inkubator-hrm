package com.inkubator.hrm.web.report;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.LazyDataModel;

import com.inkubator.hrm.entity.Department;
import com.inkubator.hrm.entity.EmpCareerHistory;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.EmpRotasi;
import com.inkubator.hrm.service.DepartmentService;
import com.inkubator.hrm.service.EmpCareerHistoryService;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.EmpRotasiService;
import com.inkubator.hrm.web.lazymodel.ReportEmpMutationLazyDataModel;
import com.inkubator.hrm.web.lazymodel.ReportEmpWorkingGroupLazyDataModel;
import com.inkubator.hrm.web.search.ReportEmpMutationParameter;
import com.inkubator.hrm.web.search.ReportEmpWorkingGroupParameter;
import com.inkubator.webcore.controller.BaseController;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
@ManagedBean(name = "reportEmpMutationViewController")
@ViewScoped
public class ReportEmpMutationViewController extends BaseController {

    private ReportEmpMutationParameter searchParameter;
    private LazyDataModel<EmpCareerHistory> lazyDataModel; 
    @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;
    @ManagedProperty(value = "#{empCareerHistoryService}")
    private EmpCareerHistoryService empCareerHistoryService;
    

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        searchParameter = new ReportEmpMutationParameter();        
    }

    @PreDestroy
    public void cleanAndExit() {
    	empDataService = null;
        searchParameter = null;
        lazyDataModel = null;
        empCareerHistoryService = null;       
    }

	public ReportEmpMutationParameter getSearchParameter() {
		return searchParameter;
	}

	public void setSearchParameter(ReportEmpMutationParameter searchParameter) {
		this.searchParameter = searchParameter;
	}

	public LazyDataModel<EmpCareerHistory> getLazyDataModel() {
		if(lazyDataModel == null){
			lazyDataModel =  new ReportEmpMutationLazyDataModel(searchParameter, empCareerHistoryService);
		}
		return lazyDataModel;
	}

	public void setLazyDataModel(LazyDataModel<EmpCareerHistory> lazyDataModel) {
		this.lazyDataModel = lazyDataModel;
	}

    public EmpCareerHistoryService getEmpCareerHistoryService() {
        return empCareerHistoryService;
    }

    public void setEmpCareerHistoryService(EmpCareerHistoryService empCareerHistoryService) {
        this.empCareerHistoryService = empCareerHistoryService;
    }

    

	public EmpDataService getEmpDataService() {
		return empDataService;
	}

	public void setEmpDataService(EmpDataService empDataService) {
		this.empDataService = empDataService;
	}

	

	public void doSearch() {
        lazyDataModel = null;
    }
}
