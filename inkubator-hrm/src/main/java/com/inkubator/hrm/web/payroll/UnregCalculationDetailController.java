/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.payroll;

import java.math.BigDecimal;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.LazyDataModel;

import com.inkubator.hrm.entity.PaySalaryComponent;
import com.inkubator.hrm.entity.TempUnregPayroll;
import com.inkubator.hrm.entity.UnregSalary;
import com.inkubator.hrm.service.PaySalaryComponentService;
import com.inkubator.hrm.service.TempUnregPayrollService;
import com.inkubator.hrm.service.UnregSalaryService;
import com.inkubator.hrm.web.lazymodel.UnregCalculationPayrollLazyDataModel;
import com.inkubator.hrm.web.search.UnregPayrollSearchParameter;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "unregCalculationDetailController")
@ViewScoped
public class UnregCalculationDetailController extends BaseController {

	private UnregSalary unregSalary;
	private PaySalaryComponent paySalaryComponent;
	private UnregPayrollSearchParameter parameter;
    private LazyDataModel<TempUnregPayroll> lazyDataModel;
    private Long totalEmployee;
    private BigDecimal totalNominal;
	@ManagedProperty(value = "#{tempUnregPayrollService}")
    private TempUnregPayrollService tempUnregPayrollService;
	@ManagedProperty(value = "#{unregSalaryService}")
    private UnregSalaryService unregSalaryService;
	@ManagedProperty(value = "#{paySalaryComponentService}")
    private PaySalaryComponentService paySalaryComponentService;
    

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        String unregSalaryId = FacesUtil.getRequestParameter("execution").substring(1);
        String taxComponentId = FacesUtil.getRequestParameter("comp").substring(1);
        parameter = new UnregPayrollSearchParameter();
        if (StringUtils.isNumeric(unregSalaryId) && StringUtils.isNumeric(taxComponentId)) {
            try {
            	paySalaryComponent = paySalaryComponentService.getEntiyByPK(Long.parseLong(taxComponentId));
                unregSalary = unregSalaryService.getEntiyByPK(Long.parseLong(unregSalaryId));
                parameter.setPaySalaryComponentId(paySalaryComponent.getId());
                parameter.setUnregSalaryId(unregSalary.getId());
                parameter.setKeyParam("nikOrName");
                
                totalEmployee = tempUnregPayrollService.getTotalEmployeeByUnregSalaryIdAndPaySalaryCompId(unregSalary.getId(), paySalaryComponent.getId());
                totalNominal =  tempUnregPayrollService.getTotalNominalByUnregSalaryIdAndPaySalaryCompId(unregSalary.getId(), paySalaryComponent.getId());
            } catch (Exception ex) {
            	LOGGER.error("Error", ex);
            }
        }
    }

    @PreDestroy
    public void cleanAndExit() {
        lazyDataModel = null;
        unregSalary = null;
        paySalaryComponent = null;
        tempUnregPayrollService = null;
        paySalaryComponentService = null;
        unregSalaryService = null;
        parameter = null;
        totalEmployee = null;
        totalNominal = null;
        
    }

    public void doSearch() {
        lazyDataModel = null;
    }
    
    public String doBack(){
    	return "/protected/payroll/unreg_calculation_exec.htm?faces-redirect=true&execution=e" + unregSalary.getId();
	}

	public UnregSalary getUnregSalary() {
		return unregSalary;
	}

	public void setUnregSalary(UnregSalary unregSalary) {
		this.unregSalary = unregSalary;
	}

	public PaySalaryComponent getPaySalaryComponent() {
		return paySalaryComponent;
	}

	public void setPaySalaryComponent(PaySalaryComponent paySalaryComponent) {
		this.paySalaryComponent = paySalaryComponent;
	}

	public TempUnregPayrollService getTempUnregPayrollService() {
		return tempUnregPayrollService;
	}

	public void setTempUnregPayrollService(
			TempUnregPayrollService tempUnregPayrollService) {
		this.tempUnregPayrollService = tempUnregPayrollService;
	}

	public UnregSalaryService getUnregSalaryService() {
		return unregSalaryService;
	}

	public void setUnregSalaryService(UnregSalaryService unregSalaryService) {
		this.unregSalaryService = unregSalaryService;
	}

	public PaySalaryComponentService getPaySalaryComponentService() {
		return paySalaryComponentService;
	}

	public void setPaySalaryComponentService(
			PaySalaryComponentService paySalaryComponentService) {
		this.paySalaryComponentService = paySalaryComponentService;
	}

	public UnregPayrollSearchParameter getParameter() {
		return parameter;
	}

	public void setParameter(UnregPayrollSearchParameter parameter) {
		this.parameter = parameter;
	}

	public LazyDataModel<TempUnregPayroll> getLazyDataModel() {
		if(lazyDataModel == null){
			lazyDataModel =  new UnregCalculationPayrollLazyDataModel(parameter, tempUnregPayrollService);
		}
		return lazyDataModel;
	}

	public void setLazyDataModel(LazyDataModel<TempUnregPayroll> lazyDataModel) {
		this.lazyDataModel = lazyDataModel;
	}

	public Long getTotalEmployee() {
		return totalEmployee;
	}

	public void setTotalEmployee(Long totalEmployee) {
		this.totalEmployee = totalEmployee;
	}

	public BigDecimal getTotalNominal() {
		return totalNominal;
	}

	public void setTotalNominal(BigDecimal totalNominal) {
		this.totalNominal = totalNominal;
	}
    
}
