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

import com.inkubator.hrm.entity.Bank;
import com.inkubator.hrm.entity.Department;
import com.inkubator.hrm.entity.GolonganJabatan;
import com.inkubator.hrm.entity.LogListOfTransfer;
import com.inkubator.hrm.entity.WtPeriode;
import com.inkubator.hrm.service.BankService;
import com.inkubator.hrm.service.DepartmentService;
import com.inkubator.hrm.service.GolonganJabatanService;
import com.inkubator.hrm.service.LeaveImplementationService;
import com.inkubator.hrm.service.LogListOfTransferService;
import com.inkubator.hrm.service.WtPeriodeService;
import com.inkubator.hrm.web.lazymodel.ReportBankTransferDataLazyDataModel;
import com.inkubator.hrm.web.lazymodel.ReportLeaveDataLazyDataModel;
import com.inkubator.hrm.web.model.ReportLeaveDataViewModel;
import com.inkubator.hrm.web.search.ReportBankTransferDataSearchParameter;
import com.inkubator.hrm.web.search.ReportDataComponentSearchParameter;
import com.inkubator.hrm.web.search.ReportLeaveDataSearchParameter;
import com.inkubator.webcore.controller.BaseController;

/**
*
* @author Ahmad Mudzakkir Amal
*/
@ManagedBean(name = "reportLeaveDataViewController")
@ViewScoped
public class ReportLeaveDataViewController extends BaseController {

	private ReportLeaveDataSearchParameter searchParameter;
	private LazyDataModel<ReportLeaveDataViewModel> lazyDataModel;
	private DualListModel<String> golJabDualModel;
	private DualListModel<Department> departmentDualModel;
	
	@ManagedProperty(value = "#{leaveImplementationService}")
    private LeaveImplementationService leaveImplementationService;
	@ManagedProperty(value = "#{golonganJabatanService}")
    private GolonganJabatanService golonganJabatanService;
	@ManagedProperty(value = "#{departmentService}")
    private DepartmentService departmentService;
	
	@PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try {
        	
        	searchParameter = new ReportLeaveDataSearchParameter();
        	
        	//Inisialisasi DualListModel Golongan Jabatan
        	List<GolonganJabatan> listGolonganJabatans =  golonganJabatanService.getAllData();
        	golJabDualModel = new DualListModel<String>(Lambda.extract(listGolonganJabatans, Lambda.on(GolonganJabatan.class).getCode()), new ArrayList<String>());
        	
        	//Inisialisasi DualListModel Departemen
        	List<Department> listDepartment = departmentService.getAllData();
        	departmentDualModel = new DualListModel<Department>(listDepartment, new ArrayList<Department>());
        	
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
	}
	
	public void doSearch(){
		searchParameter.setListGolJab(golJabDualModel.getTarget());
		searchParameter.setListDepartment(departmentDualModel.getTarget());
		lazyDataModel = null;		
	}
	
	
	@PreDestroy
    public void cleanAndExit() {
		searchParameter = null;
		lazyDataModel = null;
		golJabDualModel = null;
		departmentDualModel = null;
		golonganJabatanService = null;
		departmentService = null;
	}

	public LazyDataModel<ReportLeaveDataViewModel> getLazyDataModel() {
		if(lazyDataModel == null){
			lazyDataModel = new ReportLeaveDataLazyDataModel(leaveImplementationService, searchParameter);
		}
		return lazyDataModel;
	}
	
	public ReportLeaveDataSearchParameter getSearchParameter() {
		return searchParameter;
	}

	public void setSearchParameter(ReportLeaveDataSearchParameter searchParameter) {
		this.searchParameter = searchParameter;
	}

	public void setLazyDataModel(LazyDataModel<ReportLeaveDataViewModel> lazyDataModel) {
		this.lazyDataModel = lazyDataModel;
	}
	
	public DualListModel<String> getGolJabDualModel() {
		return golJabDualModel;
	}

	public void setGolJabDualModel(DualListModel<String> golJabDualModel) {
		this.golJabDualModel = golJabDualModel;
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

	public DualListModel<Department> getDepartmentDualModel() {
		return departmentDualModel;
	}

	public void setDepartmentDualModel(DualListModel<Department> departmentDualModel) {
		this.departmentDualModel = departmentDualModel;
	}

	public void setLeaveImplementationService(LeaveImplementationService leaveImplementationService) {
		this.leaveImplementationService = leaveImplementationService;
	}

		
}
