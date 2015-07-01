package com.inkubator.hrm.web.report;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.DualListModel;
import org.primefaces.model.LazyDataModel;

import ch.lambdaj.Lambda;

import com.inkubator.hrm.entity.Department;
import com.inkubator.hrm.entity.GolonganJabatan;
import com.inkubator.hrm.service.DepartmentService;
import com.inkubator.hrm.service.GolonganJabatanService;
import com.inkubator.hrm.service.PermitImplementationService;
import com.inkubator.hrm.web.lazymodel.ReportPermitHistoryLazyDataModel;
import com.inkubator.hrm.web.model.ReportPermitHistoryModel;
import com.inkubator.hrm.web.search.ReportPermitHistorySearchParameter;
import com.inkubator.webcore.controller.BaseController;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "reportPermitHistoryViewController")
@ViewScoped
public class ReportPermitHistoryViewController extends BaseController {

    private ReportPermitHistorySearchParameter searchParameter;
    private LazyDataModel<ReportPermitHistoryModel> lazyDataModel;
    
    @ManagedProperty(value = "#{permitImplementationService}")
    private PermitImplementationService permitImplementationService;
    @ManagedProperty(value = "#{golonganJabatanService}")
    private GolonganJabatanService golonganJabatanService;
	@ManagedProperty(value = "#{departmentService}")
    private DepartmentService departmentService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try {
        	searchParameter = new ReportPermitHistorySearchParameter();
        	List<GolonganJabatan> availableGolonganJabatans =  golonganJabatanService.getAllData();        	
        	List<Department> availableDepartments = departmentService.getAllData();
        	searchParameter.setGolJabDualModel(new DualListModel<String>(Lambda.extract(availableGolonganJabatans, Lambda.on(GolonganJabatan.class).getCode()), new ArrayList<String>()));
        	searchParameter.setDepartmentDualModel(new DualListModel<Department>(availableDepartments, new ArrayList<Department>()));
        	
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    @PreDestroy
    public void cleanAndExit() {
    	searchParameter = null;
    	lazyDataModel = null;
    	permitImplementationService = null;
    	golonganJabatanService = null;
    	departmentService = null;
    }
    
    public void doSearch(){		
		lazyDataModel = null;		
	}

	public ReportPermitHistorySearchParameter getSearchParameter() {
		return searchParameter;
	}

	public void setSearchParameter(ReportPermitHistorySearchParameter searchParameter) {
		this.searchParameter = searchParameter;
	}

	public LazyDataModel<ReportPermitHistoryModel> getLazyDataModel() {
		if(lazyDataModel == null){
			lazyDataModel =  new ReportPermitHistoryLazyDataModel(searchParameter, permitImplementationService);
		}
		return lazyDataModel;
	}

	public void setLazyDataModel(LazyDataModel<ReportPermitHistoryModel> lazyDataModel) {
		this.lazyDataModel = lazyDataModel;
	}

	public PermitImplementationService getPermitImplementationService() {
		return permitImplementationService;
	}

	public void setPermitImplementationService(PermitImplementationService permitImplementationService) {
		this.permitImplementationService = permitImplementationService;
	}

	public GolonganJabatanService getGolonganJabatanService() {
		return golonganJabatanService;
	}

	public void setGolonganJabatanService(GolonganJabatanService golonganJabatanService) {
		this.golonganJabatanService = golonganJabatanService;
	}

	public DepartmentService getDepartmentService() {
		return departmentService;
	}

	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}
    
}
