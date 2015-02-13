package com.inkubator.hrm.web.report;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.LazyDataModel;

import com.inkubator.hrm.service.LogSalaryJournalService;
import com.inkubator.hrm.web.lazymodel.ReportSalaryJournalGroupingLazyDataModel;
import com.inkubator.hrm.web.model.ReportSalaryJounalModel;
import com.inkubator.webcore.controller.BaseController;

/**
*
* @author rizkykojek
*/
@ManagedBean(name = "reportSalaryJournalViewController")
@ViewScoped
public class ReportSalaryJournalViewController extends BaseController {

	private ReportSalaryJounalModel selectedModel;
	private LazyDataModel<ReportSalaryJounalModel> lazyDataModel;
	@ManagedProperty(value = "#{logSalaryJournalService}")
    private LogSalaryJournalService logSalaryJournalService;
	
	@PostConstruct
    @Override
    public void initialization() {
        super.initialization();
	}
	
	@PreDestroy
    public void cleanAndExit() {
		lazyDataModel = null;
		logSalaryJournalService = null;
		selectedModel = null;
	}

	public LazyDataModel<ReportSalaryJounalModel> getLazyDataModel() {
		if(lazyDataModel == null){
			lazyDataModel = new ReportSalaryJournalGroupingLazyDataModel(logSalaryJournalService);
		}
		return lazyDataModel;
	}

	public void setLazyDataModel(LazyDataModel<ReportSalaryJounalModel> lazyDataModel) {
		this.lazyDataModel = lazyDataModel;
	}

	public LogSalaryJournalService getLogSalaryJournalService() {
		return logSalaryJournalService;
	}

	public void setLogSalaryJournalService(
			LogSalaryJournalService logSalaryJournalService) {
		this.logSalaryJournalService = logSalaryJournalService;
	}
	
	public ReportSalaryJounalModel getSelectedModel() {
		return selectedModel;
	}

	public void setSelectedModel(ReportSalaryJounalModel selectedModel) {
		this.selectedModel = selectedModel;
	}

	public String doDetail(){
		return "/protected/report/report_salaryjournal_detail.htm?faces-redirect=true&execution=e" + selectedModel.getPeriodId();
	}
	
	
	
	
}
