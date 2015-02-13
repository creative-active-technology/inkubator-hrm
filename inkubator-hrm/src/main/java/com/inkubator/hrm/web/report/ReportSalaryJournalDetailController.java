package com.inkubator.hrm.web.report;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.LazyDataModel;

import com.inkubator.hrm.entity.LogSalaryJournal;
import com.inkubator.hrm.entity.WtPeriode;
import com.inkubator.hrm.service.LogSalaryJournalService;
import com.inkubator.hrm.service.WtPeriodeService;
import com.inkubator.hrm.web.lazymodel.ReportSalaryJournalLazyDataModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;

/**
*
* @author rizkykojek
*/
@ManagedBean(name = "reportSalaryJournalDetailController")
@ViewScoped
public class ReportSalaryJournalDetailController extends BaseController {

	private WtPeriode period;
	private LazyDataModel<LogSalaryJournal> lazyDataModel;
	@ManagedProperty(value = "#{logSalaryJournalService}")
    private LogSalaryJournalService logSalaryJournalService;
	@ManagedProperty(value = "#{wtPeriodeService}")
    private WtPeriodeService wtPeriodeService;
	
	@PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try {
	        String param = FacesUtil.getRequestParameter("execution");
	        if (StringUtils.isNotEmpty(param)) {
	        	period = wtPeriodeService.getEntiyByPK(Long.parseLong(param.substring(1)));
	        }
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
	}
	
	@PreDestroy
    public void cleanAndExit() {
		period = null;
		wtPeriodeService = null;
		lazyDataModel = null;
		logSalaryJournalService = null;
	}

	public LazyDataModel<LogSalaryJournal> getLazyDataModel() {
		if(lazyDataModel == null){
			lazyDataModel = new ReportSalaryJournalLazyDataModel(logSalaryJournalService, period.getId());
		}
		return lazyDataModel;
	}

	public void setLazyDataModel(LazyDataModel<LogSalaryJournal> lazyDataModel) {
		this.lazyDataModel = lazyDataModel;
	}

	public LogSalaryJournalService getLogSalaryJournalService() {
		return logSalaryJournalService;
	}

	public void setLogSalaryJournalService(LogSalaryJournalService logSalaryJournalService) {
		this.logSalaryJournalService = logSalaryJournalService;
	}

	public WtPeriode getPeriod() {
		return period;
	}

	public void setPeriod(WtPeriode period) {
		this.period = period;
	}

	public WtPeriodeService getWtPeriodeService() {
		return wtPeriodeService;
	}

	public void setWtPeriodeService(WtPeriodeService wtPeriodeService) {
		this.wtPeriodeService = wtPeriodeService;
	}
	
	public String doBack(){
		return "/protected/report/report_salaryjournal_view.htm?faces-redirect=true";
	}
	
}
