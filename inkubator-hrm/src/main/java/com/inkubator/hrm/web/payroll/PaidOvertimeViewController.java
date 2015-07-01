package com.inkubator.hrm.web.payroll;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.LazyDataModel;

import com.inkubator.hrm.entity.LogWtAttendanceRealization;
import com.inkubator.hrm.entity.WtPeriode;
import com.inkubator.hrm.service.LogWtAttendanceRealizationService;
import com.inkubator.hrm.service.WtPeriodeService;
import com.inkubator.hrm.web.lazymodel.PaidOvertimeLazyDataModel;
import com.inkubator.webcore.controller.BaseController;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "paidOvertimeViewController")
@ViewScoped
public class PaidOvertimeViewController extends BaseController {

	private WtPeriode period;
	private Long totalEmployee;
    private LazyDataModel<LogWtAttendanceRealization> lazyDataModel;
    @ManagedProperty(value = "#{wtPeriodeService}")
    private WtPeriodeService wtPeriodeService;
    @ManagedProperty(value = "#{logWtAttendanceRealizationService}")
    private LogWtAttendanceRealizationService logWtAttendanceRealizationService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try {
			period = wtPeriodeService.getEntityByPayrollTypeActive();
			totalEmployee = logWtAttendanceRealizationService.getTotalPaidOvertimeByParam(period.getId());
		} catch (Exception e) {
			LOGGER.error("Error", e);
		}
    }

    @PreDestroy
    public void cleanAndExit() {
    	period = null;
    	lazyDataModel = null;
    	wtPeriodeService = null;
    	logWtAttendanceRealizationService = null;
    	totalEmployee = null;
    }

	public WtPeriode getPeriod() {
		return period;
	}

	public void setPeriod(WtPeriode period) {
		this.period = period;
	}

	public LazyDataModel<LogWtAttendanceRealization> getLazyDataModel() {
		if(lazyDataModel == null){
			lazyDataModel = new PaidOvertimeLazyDataModel(period.getId(), logWtAttendanceRealizationService);
		}
		return lazyDataModel;
	}

	public void setLazyDataModel(LazyDataModel<LogWtAttendanceRealization> lazyDataModel) {
		this.lazyDataModel = lazyDataModel;
	}

	public WtPeriodeService getWtPeriodeService() {
		return wtPeriodeService;
	}

	public void setWtPeriodeService(WtPeriodeService wtPeriodeService) {
		this.wtPeriodeService = wtPeriodeService;
	}

	public LogWtAttendanceRealizationService getLogWtAttendanceRealizationService() {
		return logWtAttendanceRealizationService;
	}

	public void setLogWtAttendanceRealizationService(LogWtAttendanceRealizationService logWtAttendanceRealizationService) {
		this.logWtAttendanceRealizationService = logWtAttendanceRealizationService;
	}

	public Long getTotalEmployee() {
		return totalEmployee;
	}

	public void setTotalEmployee(Long totalEmployee) {
		this.totalEmployee = totalEmployee;
	}
	
}
