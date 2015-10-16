package com.inkubator.hrm.web.payroll;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.DualListModel;
import org.primefaces.model.LazyDataModel;

import com.inkubator.hrm.entity.Department;
import com.inkubator.hrm.entity.GolonganJabatan;
import com.inkubator.hrm.entity.LoanNewApplication;
import com.inkubator.hrm.service.DepartmentService;
import com.inkubator.hrm.service.GolonganJabatanService;
import com.inkubator.hrm.service.LoanNewApplicationService;
import com.inkubator.hrm.web.lazymodel.LoanStatusLazyDataModel;
import com.inkubator.hrm.web.search.LoanStatusSearchParameter;
import com.inkubator.webcore.controller.BaseController;

@ManagedBean(name = "loanStatusViewController")
@ViewScoped
public class LoanStatusViewController extends BaseController {
	@ManagedProperty(value = "#{loanNewApplicationService}")
    private LoanNewApplicationService loanNewApplicationService;
	@ManagedProperty(value = "#{departmentService}")
    private DepartmentService departmentService;
	@ManagedProperty(value = "#{golonganJabatanService}")
    private GolonganJabatanService golonganJabatanService;
	private LoanStatusSearchParameter loanStatusSearchParameter;
	private DualListModel<Department> dualListDepartment = new DualListModel<>();
	private DualListModel<GolonganJabatan> dualListGolonganJabatan = new DualListModel<>();
	private LazyDataModel<LoanNewApplication> lazyDataModel;
	
	@PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        loanStatusSearchParameter = new LoanStatusSearchParameter();
        try {
			doSetDualListModel();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }
    
	public void doSetDualListModel() throws Exception{
		List<Department> listDepartment = departmentService.getAllData();
		List<GolonganJabatan> listGolonganJabatan = golonganJabatanService.getAllData();
		dualListDepartment.setSource(listDepartment);
		dualListGolonganJabatan.setSource(listGolonganJabatan);
	}
	
	public void doSearch(){
		loanStatusSearchParameter.setListDepartment(dualListDepartment.getTarget());
		loanStatusSearchParameter.setListGolonganJabatan(dualListGolonganJabatan.getTarget());
		lazyDataModel = null;
	}
	
    @PreDestroy
    private void cleanAndExit() {
    	loanStatusSearchParameter=null;
    	loanNewApplicationService=null;
    	departmentService = null;
    	dualListDepartment = null;
    	golonganJabatanService = null;
    	dualListGolonganJabatan = null;
    }

	public LazyDataModel<LoanNewApplication> getLazyDataModel() {
		if(lazyDataModel == null){
			lazyDataModel = new LoanStatusLazyDataModel(loanStatusSearchParameter, loanNewApplicationService);
        }
		return lazyDataModel;
	}

	public void setLazyDataModel(LazyDataModel<LoanNewApplication> lazyDataModel) {
		this.lazyDataModel = lazyDataModel;
	}

	public LoanNewApplicationService getLoanNewApplicationService() {
		return loanNewApplicationService;
	}

	public void setLoanNewApplicationService(
			LoanNewApplicationService loanNewApplicationService) {
		this.loanNewApplicationService = loanNewApplicationService;
	}

	public LoanStatusSearchParameter getLoanStatusSearchParameter() {
		return loanStatusSearchParameter;
	}

	public void setLoanStatusSearchParameter(
			LoanStatusSearchParameter loanStatusSearchParameter) {
		this.loanStatusSearchParameter = loanStatusSearchParameter;
	}

	public DepartmentService getDepartmentService() {
		return departmentService;
	}

	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	public DualListModel<Department> getDualListDepartment() {
		return dualListDepartment;
	}

	public void setDualListDepartment(DualListModel<Department> dualListDepartment) {
		this.dualListDepartment = dualListDepartment;
	}

	public GolonganJabatanService getGolonganJabatanService() {
		return golonganJabatanService;
	}

	public void setGolonganJabatanService(
			GolonganJabatanService golonganJabatanService) {
		this.golonganJabatanService = golonganJabatanService;
	}

	public DualListModel<GolonganJabatan> getDualListGolonganJabatan() {
		return dualListGolonganJabatan;
	}

	public void setDualListGolonganJabatan(
			DualListModel<GolonganJabatan> dualListGolonganJabatan) {
		this.dualListGolonganJabatan = dualListGolonganJabatan;
	}
    
    
	
}