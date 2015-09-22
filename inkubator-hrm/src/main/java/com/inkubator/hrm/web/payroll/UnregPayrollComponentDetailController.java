package com.inkubator.hrm.web.payroll;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.hamcrest.Matchers;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.LogUnregPayroll;
import com.inkubator.hrm.entity.PaySalaryComponent;
import com.inkubator.hrm.entity.PayTempKalkulasiEmpPajak;
import com.inkubator.hrm.entity.UnregSalary;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.LogUnregPayrollService;
import com.inkubator.hrm.service.UnregSalaryService;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;

import ch.lambdaj.Lambda;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "unregPayrollComponentDetailController")
@ViewScoped
public class UnregPayrollComponentDetailController extends BaseController {

	private UnregSalary unregSalary;
	private EmpData empData;
	private BigDecimal totalNominal;
	private List<LogUnregPayroll> listLogUnregPayroll;
    @ManagedProperty(value = "#{logUnregPayrollService}")
    private LogUnregPayrollService logUnregPayrollService;
    @ManagedProperty(value = "#{unregSalaryService}")
    private UnregSalaryService unregSalaryService;
    @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();        
        try {
        	String empDataId = FacesUtil.getRequestParameter("execution");
        	String unregSalaryId = FacesUtil.getRequestParameter("unreg");
        	unregSalary = unregSalaryService.getEntiyByPK(Long.parseLong(unregSalaryId.substring(1))); 
        	empData = empDataService.getEmpDataWithBiodata(Long.parseLong(empDataId.substring(1)));
        	listLogUnregPayroll = logUnregPayrollService.getAllDataByEmpDataIdAndUnregSalaryId(empData.getId(),unregSalary.getId());        	
        	
        	/** take home pay == total dibayar */
        	LogUnregPayroll takeHomePay = Lambda.selectFirst(listLogUnregPayroll, Lambda.having(Lambda.on(LogUnregPayroll.class).getModelCompSpecific(), Matchers.equalTo(HRMConstant.MODEL_COMP_TAKE_HOME_PAY)));
        	totalNominal = takeHomePay.getNominal();
        	
        	/** 
        	 * By the default, Tiga Kompenen, yaitu Take Home Pay, Pembulatan(CSR) dan Pajak, masuk dalam komponen
        	 *  untuk detail list komponen, exclude Take Home Pay dan Pembulatan(CSR) 
        	 * */        	
        	listLogUnregPayroll = Lambda.select(listLogUnregPayroll, Lambda.having(Lambda.on(LogUnregPayroll.class).getModelCompSpecific(), Matchers.not(Matchers.isIn(new Integer[]{HRMConstant.MODEL_COMP_TAKE_HOME_PAY, HRMConstant.MODEL_COMP_CEIL}))));
		} catch (Exception e) {
			LOGGER.error("Error", e);
		}
    }

    @PreDestroy
    public void cleanAndExit() {
    	logUnregPayrollService = null;
        unregSalary = null;
        totalNominal = null;
        unregSalaryService = null;
    }

    public String doBack(){
    	return "/protected/payroll/unreg_payroll_detail.htm?faces-redirect=true&execution=e" + unregSalary.getId();
	}

	public UnregSalary getUnregSalary() {
		return unregSalary;
	}

	public void setUnregSalary(UnregSalary unregSalary) {
		this.unregSalary = unregSalary;
	}

	public EmpData getEmpData() {
		return empData;
	}

	public void setEmpData(EmpData empData) {
		this.empData = empData;
	}

	public BigDecimal getTotalNominal() {
		return totalNominal;
	}

	public void setTotalNominal(BigDecimal totalNominal) {
		this.totalNominal = totalNominal;
	}

	public List<LogUnregPayroll> getListLogUnregPayroll() {
		return listLogUnregPayroll;
	}

	public void setListLogUnregPayroll(List<LogUnregPayroll> listLogUnregPayroll) {
		this.listLogUnregPayroll = listLogUnregPayroll;
	}

	public LogUnregPayrollService getLogUnregPayrollService() {
		return logUnregPayrollService;
	}

	public void setLogUnregPayrollService(LogUnregPayrollService logUnregPayrollService) {
		this.logUnregPayrollService = logUnregPayrollService;
	}

	public UnregSalaryService getUnregSalaryService() {
		return unregSalaryService;
	}

	public void setUnregSalaryService(UnregSalaryService unregSalaryService) {
		this.unregSalaryService = unregSalaryService;
	}

	public EmpDataService getEmpDataService() {
		return empDataService;
	}

	public void setEmpDataService(EmpDataService empDataService) {
		this.empDataService = empDataService;
	}
    
}
