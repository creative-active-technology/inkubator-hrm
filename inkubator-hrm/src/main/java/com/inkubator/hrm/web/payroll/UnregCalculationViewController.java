package com.inkubator.hrm.web.payroll;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.LazyDataModel;

import com.inkubator.hrm.entity.UnregSalary;
import com.inkubator.hrm.entity.WtPeriode;
import com.inkubator.hrm.service.UnregSalaryService;
import com.inkubator.hrm.service.WtPeriodeService;
import com.inkubator.hrm.web.lazymodel.UnregCalculationLazyDataModel;
import com.inkubator.hrm.web.search.UnregSalarySearchParameter;
import com.inkubator.webcore.controller.BaseController;

/**
*
* @author rizkykojek
*/
@ManagedBean(name = "unregCalculationViewController")
@ViewScoped
public class UnregCalculationViewController extends BaseController {

	private UnregSalary selectedModel;
	private LazyDataModel<UnregSalary> lazyDataModel;
	private UnregSalarySearchParameter parameter;
	private WtPeriode period;
	@ManagedProperty(value = "#{unregSalaryService}")
    private UnregSalaryService unregSalaryService;
	@ManagedProperty(value = "#{wtPeriodeService}")
    private WtPeriodeService wtPeriodeService;
	
	
	@PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try {
        	parameter = new UnregSalarySearchParameter();
        	period = wtPeriodeService.getEntityByPayrollTypeActive();
        } catch (Exception ex) {
			LOGGER.error("Error ", ex);
		}
	}
	
	@PreDestroy
    public void cleanAndExit() {
		lazyDataModel = null;
		unregSalaryService = null;
		selectedModel = null;
		wtPeriodeService = null;
		parameter = null;
		period = null;
	}

	public LazyDataModel<UnregSalary> getLazyDataModel() {
		if(lazyDataModel == null){
			lazyDataModel = new UnregCalculationLazyDataModel(unregSalaryService, period.getFromPeriode(), parameter);
		}
		return lazyDataModel;
	}

	public void setLazyDataModel(LazyDataModel<UnregSalary> lazyDataModel) {
		this.lazyDataModel = lazyDataModel;
	}

	public UnregSalary getSelectedModel() {
		return selectedModel;
	}

	public void setSelectedModel(UnregSalary selectedModel) {
		this.selectedModel = selectedModel;
	}

	public UnregSalarySearchParameter getParameter() {
		return parameter;
	}

	public void setParameter(UnregSalarySearchParameter parameter) {
		this.parameter = parameter;
	}

	public UnregSalaryService getUnregSalaryService() {
		return unregSalaryService;
	}

	public void setUnregSalaryService(UnregSalaryService unregSalaryService) {
		this.unregSalaryService = unregSalaryService;
	}

	public WtPeriodeService getWtPeriodeService() {
		return wtPeriodeService;
	}

	public void setWtPeriodeService(WtPeriodeService wtPeriodeService) {
		this.wtPeriodeService = wtPeriodeService;
	}

	public WtPeriode getPeriod() {
		return period;
	}

	public void setPeriod(WtPeriode period) {
		this.period = period;
	}

	public String doDetail(){
		return "/protected/payroll/unreg_calculation_exec.htm?faces-redirect=true&execution=e" + selectedModel.getId();
	}
	
	public void doSearch(){
		lazyDataModel = null;
	}
	
}
