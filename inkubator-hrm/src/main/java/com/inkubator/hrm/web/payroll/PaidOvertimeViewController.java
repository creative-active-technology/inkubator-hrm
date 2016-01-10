package com.inkubator.hrm.web.payroll;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.LazyDataModel;

import com.inkubator.hrm.entity.TempAttendanceRealization;
import com.inkubator.hrm.entity.WtPeriode;
import com.inkubator.hrm.service.TempAttendanceRealizationService;
import com.inkubator.hrm.service.WtPeriodeService;
import com.inkubator.hrm.web.lazymodel.PaidOvertimeLazyDataModel;
import com.inkubator.hrm.web.search.PaidOvertimeSearchParameter;
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
    private LazyDataModel<TempAttendanceRealization> lazyDataModel;
    private PaidOvertimeSearchParameter searchParameter;
    @ManagedProperty(value = "#{wtPeriodeService}")
    private WtPeriodeService wtPeriodeService;
    @ManagedProperty(value = "#{tempAttendanceRealizationService}")
    private TempAttendanceRealizationService tempAttendanceRealizationService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try {
        	searchParameter = new PaidOvertimeSearchParameter();
			period = wtPeriodeService.getEntityByPayrollTypeActive();
			totalEmployee = tempAttendanceRealizationService.getTotalPaidOvertimeByParam(searchParameter);
		} catch (Exception e) {
			LOGGER.error("Error", e);
		}
    }

    @PreDestroy
    public void cleanAndExit() {
    	period = null;
    	lazyDataModel = null;
    	wtPeriodeService = null;
    	tempAttendanceRealizationService = null;
    	totalEmployee = null;
    }
    
    public void doSearch(){
    	lazyDataModel = null;
    }

	public WtPeriode getPeriod() {
		return period;
	}

	public void setPeriod(WtPeriode period) {
		this.period = period;
	}

	public LazyDataModel<TempAttendanceRealization> getLazyDataModel() {
		if(lazyDataModel == null){
			lazyDataModel = new PaidOvertimeLazyDataModel(searchParameter, tempAttendanceRealizationService);
		}
		return lazyDataModel;
	}

	public void setLazyDataModel(LazyDataModel<TempAttendanceRealization> lazyDataModel) {
		this.lazyDataModel = lazyDataModel;
	}

	public WtPeriodeService getWtPeriodeService() {
		return wtPeriodeService;
	}

	public void setWtPeriodeService(WtPeriodeService wtPeriodeService) {
		this.wtPeriodeService = wtPeriodeService;
	}

	public TempAttendanceRealizationService getTempAttendanceRealizationService() {
		return tempAttendanceRealizationService;
	}

	public void setTempAttendanceRealizationService(TempAttendanceRealizationService tempAttendanceRealizationService) {
		this.tempAttendanceRealizationService = tempAttendanceRealizationService;
	}

	public Long getTotalEmployee() {
		return totalEmployee;
	}

	public void setTotalEmployee(Long totalEmployee) {
		this.totalEmployee = totalEmployee;
	}

	public PaidOvertimeSearchParameter getSearchParameter() {
		return searchParameter;
	}

	public void setSearchParameter(PaidOvertimeSearchParameter searchParameter) {
		this.searchParameter = searchParameter;
	}
	
	
}
