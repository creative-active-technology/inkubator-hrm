/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.report;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.DualListModel;
import org.primefaces.model.LazyDataModel;

import ch.lambdaj.Lambda;

import com.inkubator.hrm.entity.Department;
import com.inkubator.hrm.entity.EmployeeType;
import com.inkubator.hrm.entity.GolonganJabatan;
import com.inkubator.hrm.entity.PayTempKalkulasiEmpPajak;
import com.inkubator.hrm.entity.WtPeriode;
import com.inkubator.hrm.service.DepartmentService;
import com.inkubator.hrm.service.EmployeeTypeService;
import com.inkubator.hrm.service.GolonganJabatanService;
import com.inkubator.hrm.service.LogMonthEndTaxesService;
import com.inkubator.hrm.service.WtPeriodeService;
import com.inkubator.hrm.web.lazymodel.ReportPphLazyDataModel;
import com.inkubator.hrm.web.model.PphReportModel;
import com.inkubator.hrm.web.search.LogMonthEndTaxesSearchParameter;
import com.inkubator.webcore.controller.BaseController;

/**
 *
 * @author Deni
 */
@ManagedBean(name = "reportPphViewController")
@ViewScoped
public class ReportPphViewController extends BaseController {

    @ManagedProperty(value = "#{logMonthEndTaxesService}")
    private LogMonthEndTaxesService service;
    private LogMonthEndTaxesSearchParameter searchParameter;
    private LazyDataModel<PphReportModel> lazyDataModel;
    private PayTempKalkulasiEmpPajak selected;
    private PphReportModel selectedModel;
	private DualListModel<String> golJabDualModel;
	private DualListModel<Department> departmentDualModel;
	private DualListModel<EmployeeType> empTypeDualModel;
	@ManagedProperty(value = "#{golonganJabatanService}")
    private GolonganJabatanService golonganJabatanService;
	@ManagedProperty(value = "#{departmentService}")
    private DepartmentService departmentService;
	@ManagedProperty(value = "#{wtPeriodeService}")
    private WtPeriodeService wtPeriodeService;
	@ManagedProperty(value = "#{employeeTypeService}")
    private EmployeeTypeService employeeTypeService;
	private String periodeMonth;
    private String periodeYear;
    private List<WtPeriode> listPeriodeYears;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
		try {
			searchParameter = new LogMonthEndTaxesSearchParameter();
			List<GolonganJabatan> availableGolonganJabatans = golonganJabatanService.getAllData();
			List<Department> availableDepartments = departmentService.getAllData();
	    	List<EmployeeType> availableEmployeeType = employeeTypeService.getAllData();
	    	
	    	golJabDualModel = new DualListModel<String>(Lambda.extract(availableGolonganJabatans, Lambda.on(GolonganJabatan.class).getCode()), new ArrayList<String>());
	    	departmentDualModel = new DualListModel<Department>(availableDepartments, new ArrayList<Department>());
	    	empTypeDualModel = new DualListModel<EmployeeType>(availableEmployeeType, new ArrayList<EmployeeType>());
	    	listPeriodeYears = wtPeriodeService.getAllYears();   
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}        	
    	
    }

    @PreDestroy
    private void cleanAndExit() {
    	listPeriodeYears = null;
    	periodeMonth = null;
    	periodeYear = null;
        searchParameter = null;
        lazyDataModel = null;
        service = null;
        selected = null;
        searchParameter = null;
		lazyDataModel = null;
		golJabDualModel = null;
		golonganJabatanService = null;
		departmentService = null;
		wtPeriodeService = null;	
		departmentDualModel = null;
		empTypeDualModel = null;
		employeeTypeService = null;
		selectedModel = null;
    }

    public void doSearch() {
    	Long periodeId = null;
		try {
			if(!StringUtils.isEmpty(periodeMonth) && !StringUtils.isEmpty(periodeYear)){
				WtPeriode p = wtPeriodeService.getEntityByMonthAndYear(Integer.parseInt(periodeMonth), periodeYear);
				periodeId = p == null ? 0 : p.getId();
			}
		} catch (Exception ex) {
			LOGGER.error("Error", ex);
		}
		searchParameter.setPeriodeId(periodeId);
    	searchParameter.setListGolJab(golJabDualModel.getTarget());
		searchParameter.setListEmpType(Lambda.extract(empTypeDualModel.getTarget(), Lambda.on(EmployeeType.class).getId()));
		searchParameter.setListDepartment(Lambda.extract(departmentDualModel.getTarget(), Lambda.on(Department.class).getId()));
        lazyDataModel = null;
    }

    public String doGenerateReportPph(){
        return "/protected/report/generated_pph_report.htm?faces-redirect=true&execution=e" + selectedModel.getEmpDataId();
    }
    
    public void onChangeMonth(){
		if(StringUtils.isEmpty(periodeMonth)){
			periodeYear = null;
		}
	}
    
    public LogMonthEndTaxesService getService() {
        return service;
    }

    public void setService(LogMonthEndTaxesService service) {
        this.service = service;
    }

    public LogMonthEndTaxesSearchParameter getSearchParameter() {
        return searchParameter;
    }

    public void setSearchParameter(LogMonthEndTaxesSearchParameter searchParameter) {
        this.searchParameter = searchParameter;
    }

    public LazyDataModel<PphReportModel> getLazyDataModel() {
        if (lazyDataModel == null) {
            lazyDataModel = new ReportPphLazyDataModel(searchParameter, service);
        }
        return lazyDataModel;
    }

    public void setLazyDataModel(LazyDataModel<PphReportModel> lazyDataModel) {
        this.lazyDataModel = lazyDataModel;
    }

    public PayTempKalkulasiEmpPajak getSelected() {
        return selected;
    }

    public void setSelected(PayTempKalkulasiEmpPajak selected) {
        this.selected = selected;
    }

    public PphReportModel getSelectedModel() {
        return selectedModel;
    }

    public void setSelectedModel(PphReportModel selectedModel) {
        this.selectedModel = selectedModel;
    }

	public DualListModel<String> getGolJabDualModel() {
		return golJabDualModel;
	}

	public void setGolJabDualModel(DualListModel<String> golJabDualModel) {
		this.golJabDualModel = golJabDualModel;
	}

	public DualListModel<Department> getDepartmentDualModel() {
		return departmentDualModel;
	}

	public void setDepartmentDualModel(DualListModel<Department> departmentDualModel) {
		this.departmentDualModel = departmentDualModel;
	}

	public DualListModel<EmployeeType> getEmpTypeDualModel() {
		return empTypeDualModel;
	}

	public void setEmpTypeDualModel(DualListModel<EmployeeType> empTypeDualModel) {
		this.empTypeDualModel = empTypeDualModel;
	}

	public GolonganJabatanService getGolonganJabatanService() {
		return golonganJabatanService;
	}

	public void setGolonganJabatanService(
			GolonganJabatanService golonganJabatanService) {
		this.golonganJabatanService = golonganJabatanService;
	}

	public DepartmentService getDepartmentService() {
		return departmentService;
	}

	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	public WtPeriodeService getWtPeriodeService() {
		return wtPeriodeService;
	}

	public void setWtPeriodeService(WtPeriodeService wtPeriodeService) {
		this.wtPeriodeService = wtPeriodeService;
	}

	public EmployeeTypeService getEmployeeTypeService() {
		return employeeTypeService;
	}

	public void setEmployeeTypeService(EmployeeTypeService employeeTypeService) {
		this.employeeTypeService = employeeTypeService;
	}

	public String getPeriodeMonth() {
		return periodeMonth;
	}

	public void setPeriodeMonth(String periodeMonth) {
		this.periodeMonth = periodeMonth;
	}

	public String getPeriodeYear() {
		return periodeYear;
	}

	public void setPeriodeYear(String periodeYear) {
		this.periodeYear = periodeYear;
	}

	public List<WtPeriode> getListPeriodeYears() {
		return listPeriodeYears;
	}

	public void setListPeriodeYears(List<WtPeriode> listPeriodeYears) {
		this.listPeriodeYears = listPeriodeYears;
	}


}
