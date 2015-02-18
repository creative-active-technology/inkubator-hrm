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

import com.inkubator.hrm.entity.Bank;
import com.inkubator.hrm.entity.Department;
import com.inkubator.hrm.entity.GolonganJabatan;
import com.inkubator.hrm.entity.LogListOfTransfer;
import com.inkubator.hrm.entity.WtPeriode;
import com.inkubator.hrm.service.BankService;
import com.inkubator.hrm.service.DepartmentService;
import com.inkubator.hrm.service.GolonganJabatanService;
import com.inkubator.hrm.service.LogListOfTransferService;
import com.inkubator.hrm.service.WtPeriodeService;
import com.inkubator.hrm.web.lazymodel.ReportBankTransferDataLazyDataModel;
import com.inkubator.hrm.web.search.ReportBankTransferDataSearchParameter;
import com.inkubator.webcore.controller.BaseController;

/**
*
* @author rizkykojek
*/
@ManagedBean(name = "reportBankTransferDataViewController")
@ViewScoped
public class ReportBankTransferDataViewController extends BaseController {

	private ReportBankTransferDataSearchParameter searchParameter;
	private LazyDataModel<LogListOfTransfer> lazyDataModel;
	private DualListModel<String> golJabDualModel;
	private List<Department> listDepartment;
	private List<WtPeriode> listPeriode;
	private List<Bank> listBank;
	@ManagedProperty(value = "#{logListOfTransferService}")
    private LogListOfTransferService logListOfTransferService;
	@ManagedProperty(value = "#{golonganJabatanService}")
    private GolonganJabatanService golonganJabatanService;
	@ManagedProperty(value = "#{departmentService}")
    private DepartmentService departmentService;
	@ManagedProperty(value = "#{wtPeriodeService}")
    private WtPeriodeService wtPeriodeService;
	@ManagedProperty(value = "#{bankService}")
    private BankService bankService;
	
	@PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try {
        	searchParameter = new ReportBankTransferDataSearchParameter();
        	List<GolonganJabatan> listGolonganJabatans =  golonganJabatanService.getAllData();
        	golJabDualModel = new DualListModel<String>(Lambda.extract(listGolonganJabatans, Lambda.on(GolonganJabatan.class).getCode()), new ArrayList<String>());
        	listBank =  bankService.getAllData();
        	listDepartment = departmentService.getAllData();
        	listPeriode = wtPeriodeService.getAllData();
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
	}
	
	@PreDestroy
    public void cleanAndExit() {
		lazyDataModel = null;
		searchParameter = null;
		lazyDataModel = null;
		golJabDualModel = null;
		logListOfTransferService = null;
		golonganJabatanService = null;
		listDepartment = null;
		listPeriode = null;
		listBank = null;
		wtPeriodeService = null;
		bankService = null;
	}

	public LazyDataModel<LogListOfTransfer> getLazyDataModel() {
		if(lazyDataModel == null){
			lazyDataModel = new ReportBankTransferDataLazyDataModel(logListOfTransferService, searchParameter);
		}
		return lazyDataModel;
	}
	
	public ReportBankTransferDataSearchParameter getSearchParameter() {
		return searchParameter;
	}

	public void setSearchParameter(
			ReportBankTransferDataSearchParameter searchParameter) {
		this.searchParameter = searchParameter;
	}

	public LogListOfTransferService getLogListOfTransferService() {
		return logListOfTransferService;
	}

	public void setLogListOfTransferService(
			LogListOfTransferService logListOfTransferService) {
		this.logListOfTransferService = logListOfTransferService;
	}

	public void setLazyDataModel(LazyDataModel<LogListOfTransfer> lazyDataModel) {
		this.lazyDataModel = lazyDataModel;
	}
	
	public DualListModel<String> getGolJabDualModel() {
		return golJabDualModel;
	}

	public void setGolJabDualModel(DualListModel<String> golJabDualModel) {
		this.golJabDualModel = golJabDualModel;
	}

	public List<Department> getListDepartment() {
		return listDepartment;
	}

	public void setListDepartment(List<Department> listDepartment) {
		this.listDepartment = listDepartment;
	}

	public List<WtPeriode> getListPeriode() {
		return listPeriode;
	}

	public void setListPeriode(List<WtPeriode> listPeriode) {
		this.listPeriode = listPeriode;
	}

	public List<Bank> getListBank() {
		return listBank;
	}

	public void setListBank(List<Bank> listBank) {
		this.listBank = listBank;
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

	public BankService getBankService() {
		return bankService;
	}

	public void setBankService(BankService bankService) {
		this.bankService = bankService;
	}

	public void doSearch(){
		searchParameter.setListGolJab(golJabDualModel.getTarget());
		lazyDataModel = null;		
	}
	
}
