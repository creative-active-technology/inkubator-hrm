package com.inkubator.hrm.web.payroll;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.LazyDataModel;

import com.inkubator.hrm.entity.LogMonthEndPayroll;
import com.inkubator.hrm.entity.WtPeriode;
import com.inkubator.hrm.service.LogMonthEndPayrollService;
import com.inkubator.hrm.service.WtPeriodeService;
import com.inkubator.hrm.web.lazymodel.LogMonthEndPayrollLazyDataModel;
import com.inkubator.hrm.web.search.LogMonthEndPayrollSearchParameter;
import com.inkubator.webcore.controller.BaseController;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "logMonthEndPayrollViewController")
@ViewScoped
public class LogMonthEndPayrollViewController extends BaseController {

	private WtPeriode periode;
	private Long totalEmployee;
	private Double totalNominal;
    private LogMonthEndPayrollSearchParameter parameter;
    private LazyDataModel<LogMonthEndPayroll> lazyDataModel;
    @ManagedProperty(value = "#{logMonthEndPayrollService}")
    private LogMonthEndPayrollService logMonthEndPayrollService;
    @ManagedProperty(value = "#{wtPeriodeService}")
    private WtPeriodeService wtPeriodeService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        
        try {
        	parameter =  new LogMonthEndPayrollSearchParameter();
			periode = wtPeriodeService.getEntityByPayrollTypeActive();
			totalEmployee = 0L;
			totalNominal = 0.0;
		} catch (Exception e) {
			LOGGER.error("Error", e);
		}
    }

    @PreDestroy
    public void cleanAndExit() {
    	logMonthEndPayrollService = null;
        parameter = null;
        lazyDataModel = null;
        periode = null;
        totalEmployee = null;
        totalNominal = null;
        wtPeriodeService = null;
    }
    
	public LazyDataModel<LogMonthEndPayroll> getLazyDataModel() {
    	if(lazyDataModel == null) {
    		lazyDataModel = new LogMonthEndPayrollLazyDataModel(parameter, logMonthEndPayrollService);
    	}
		return lazyDataModel;
	}

	public LogMonthEndPayrollSearchParameter getParameter() {
		return parameter;
	}

	public void setParameter(LogMonthEndPayrollSearchParameter parameter) {
		this.parameter = parameter;
	}

	public void setLazyDataModel(LazyDataModel<LogMonthEndPayroll> lazyDataModel) {
		this.lazyDataModel = lazyDataModel;
	}

	public void setLogMonthEndPayrollService(
			LogMonthEndPayrollService logMonthEndPayrollService) {
		this.logMonthEndPayrollService = logMonthEndPayrollService;
	}

	public WtPeriode getPeriode() {
		return periode;
	}

	public void setPeriode(WtPeriode periode) {
		this.periode = periode;
	}

	public Long getTotalEmployee() {
		return totalEmployee;
	}

	public void setTotalEmployee(Long totalEmployee) {
		this.totalEmployee = totalEmployee;
	}

	public Double getTotalNominal() {
		return totalNominal;
	}

	public void setTotalNominal(Double totalNominal) {
		this.totalNominal = totalNominal;
	}

	public LogMonthEndPayrollService getLogMonthEndPayrollService() {
		return logMonthEndPayrollService;
	}

	public WtPeriodeService getWtPeriodeService() {
		return wtPeriodeService;
	}

	public void setWtPeriodeService(WtPeriodeService wtPeriodeService) {
		this.wtPeriodeService = wtPeriodeService;
	}

	public void doSearch() {
        lazyDataModel = null;
    }

    public void doMonthEndProcess(){
    	
    }
}
