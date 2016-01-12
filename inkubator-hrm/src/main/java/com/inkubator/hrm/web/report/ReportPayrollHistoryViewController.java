package com.inkubator.hrm.web.report;

import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.LazyDataModel;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.LogMonthEndPayrollService;
import com.inkubator.hrm.service.PayTempKalkulasiService;
import com.inkubator.hrm.service.WtPeriodeService;
import com.inkubator.hrm.util.MapUtil;
import com.inkubator.hrm.web.lazymodel.PayrollHistoryReportLazyDataModel;
import com.inkubator.hrm.web.model.PayrollHistoryReportModel;
import com.inkubator.hrm.web.search.ReportPayrollHistoryViewSearchParameter;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
@ManagedBean(name = "reportPayrollHistoryViewController")
@ViewScoped
public class ReportPayrollHistoryViewController extends BaseController {

	private ReportPayrollHistoryViewSearchParameter searchParameter;
    private LazyDataModel<PayrollHistoryReportModel> lazyDataModel;
    private PayrollHistoryReportModel selectedPayrollHistData;
    @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;
    @ManagedProperty(value = "#{payTempKalkulasiService}")
    private PayTempKalkulasiService payTempKalkulasiService;
    @ManagedProperty(value = "#{logMonthEndPayrollService}")
    private LogMonthEndPayrollService logMonthEndPayrollService;
    @ManagedProperty(value = "#{wtPeriodeService}")
    private WtPeriodeService wtPeriodeService;
    private Map<String, Integer> mapMonths = new TreeMap<>();
    private List<Integer> listTahun = new ArrayList<>();

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        searchParameter = new ReportPayrollHistoryViewSearchParameter();
        String[] month = DateFormatSymbols.getInstance(Locale.forLanguageTag((String) FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE))).getMonths();

        for (int i = 0; i < month.length - 1; i++) {
            mapMonths.put(month[i], i + 1);
            listTahun.add(2013 + i);
        }
        mapMonths = MapUtil.sortByValue(mapMonths);
    }

    @PreDestroy
    public void cleanAndExit() {
        empDataService = null;
        searchParameter = null;
        lazyDataModel = null;

    }

    public LogMonthEndPayrollService getLogMonthEndPayrollService() {
        return logMonthEndPayrollService;
    }

    public void setLogMonthEndPayrollService(LogMonthEndPayrollService logMonthEndPayrollService) {
        this.logMonthEndPayrollService = logMonthEndPayrollService;
    }
    
    public void doShowChart(){
        Map<String, Object> options = new HashMap<>(); 
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 700);
        options.put("contentHeight", 460);
        RequestContext.getCurrentInstance().openDialog("chart_payroll_hist_report", options, null);
    }
    
    public String doDetail() {       
        return "/protected/report/report_payroll_history_detail.htm?faces-redirect=true&execution=e" + selectedPayrollHistData.getPeriodeId();
        
    }
    
    public void doSearch() {
        lazyDataModel = null;
    }
    
    public void doChangeYear() {
    	lazyDataModel = null;
    }

    public void doChangeMonth() {
    	lazyDataModel = null;
    }
    
    public Map<String, Integer> getMapMonths() {
		return mapMonths;
	}

	public void setMapMonths(Map<String, Integer> mapMonths) {
		this.mapMonths = mapMonths;
	}

	public List<Integer> getListTahun() {
		return listTahun;
	}

	public void setListTahun(List<Integer> listTahun) {
		this.listTahun = listTahun;
	}

	public PayrollHistoryReportModel getSelectedPayrollHistData() {
        return selectedPayrollHistData;
    }

    public void setSelectedPayrollHistData(PayrollHistoryReportModel selectedPayrollHistData) {
        this.selectedPayrollHistData = selectedPayrollHistData;
    }
    
    
    public ReportPayrollHistoryViewSearchParameter getSearchParameter() {
        return searchParameter;
    }

    public void setSearchParameter(ReportPayrollHistoryViewSearchParameter searchParameter) {
        this.searchParameter = searchParameter;
    }

    public LazyDataModel<PayrollHistoryReportModel> getLazyDataModel() {
        if (lazyDataModel == null) {
            lazyDataModel = new PayrollHistoryReportLazyDataModel(searchParameter, logMonthEndPayrollService);
        }
        return lazyDataModel;
    }

    public void setLazyDataModel(LazyDataModel<PayrollHistoryReportModel> lazyDataModel) {
        this.lazyDataModel = lazyDataModel;
    }

    public EmpDataService getEmpDataService() {
        return empDataService;
    }

    public void setEmpDataService(EmpDataService empDataService) {
        this.empDataService = empDataService;
    }

    public PayTempKalkulasiService getPayTempKalkulasiService() {
        return payTempKalkulasiService;
    }

    public void setPayTempKalkulasiService(PayTempKalkulasiService payTempKalkulasiService) {
        this.payTempKalkulasiService = payTempKalkulasiService;
    }

    public WtPeriodeService getWtPeriodeService() {
        return wtPeriodeService;
    }

    public void setWtPeriodeService(WtPeriodeService wtPeriodeService) {
        this.wtPeriodeService = wtPeriodeService;
    }
    
    
}
