/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.payroll;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.LazyDataModel;

import com.inkubator.hrm.entity.TaxComponent;
import com.inkubator.hrm.entity.TempUnregPayrollEmpPajak;
import com.inkubator.hrm.entity.UnregSalary;
import com.inkubator.hrm.service.TaxComponentService;
import com.inkubator.hrm.service.TempUnregPayrollEmpPajakService;
import com.inkubator.hrm.service.UnregSalaryService;
import com.inkubator.hrm.web.lazymodel.UnregCalculationTaxLazyDataModel;
import com.inkubator.hrm.web.search.UnregPayrollEmpPajakSearchParameter;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "unregCalculationTaxDetailController")
@ViewScoped
public class UnregCalculationTaxDetailController extends BaseController {

	private UnregSalary unregSalary;
	private TaxComponent taxComponent;
	@ManagedProperty(value = "#{tempUnregPayrollEmpPajakService}")
    private TempUnregPayrollEmpPajakService tempUnregPayrollEmpPajakService;
	@ManagedProperty(value = "#{unregSalaryService}")
    private UnregSalaryService unregSalaryService;
	@ManagedProperty(value = "#{taxComponentService}")
    private TaxComponentService taxComponentService;
    private UnregPayrollEmpPajakSearchParameter parameter;
    private LazyDataModel<TempUnregPayrollEmpPajak> lazyDataModel;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        String unregSalaryId = FacesUtil.getRequestParameter("execution").substring(1);
        String taxComponentId = FacesUtil.getRequestParameter("comp").substring(1);
        parameter = new UnregPayrollEmpPajakSearchParameter();
        if (StringUtils.isNumeric(unregSalaryId) && StringUtils.isNumeric(taxComponentId)) {
            try {
                taxComponent = taxComponentService.getEntiyByPK(Long.parseLong(taxComponentId));
                unregSalary = unregSalaryService.getEntiyByPK(Long.parseLong(unregSalaryId));
                parameter.setTaxComponentId(taxComponent.getId());
                parameter.setUnregSalaryId(unregSalary.getId());
                parameter.setKeyParam("nikOrName");
            } catch (Exception ex) {
            	LOGGER.error("Error", ex);
            }
        }
    }

    @PreDestroy
    public void cleanAndExit() {
        lazyDataModel = null;
        unregSalary = null;
        taxComponent = null;
        taxComponentService = null;
        tempUnregPayrollEmpPajakService = null;
        unregSalaryService = null;
        taxComponentService = null;
        parameter = null;
    }

    public void doSearch() {
        lazyDataModel = null;
    }
    
    public String doBack(){
    	return "/protected/payroll/unreg_calculation_tax_view.htm?faces-redirect=true&execution=e" + unregSalary.getId();
	}

	public UnregSalary getUnregSalary() {
		return unregSalary;
	}

	public void setUnregSalary(UnregSalary unregSalary) {
		this.unregSalary = unregSalary;
	}

	public TaxComponent getTaxComponent() {
		return taxComponent;
	}

	public void setTaxComponent(TaxComponent taxComponent) {
		this.taxComponent = taxComponent;
	}

	public TempUnregPayrollEmpPajakService getTempUnregPayrollEmpPajakService() {
		return tempUnregPayrollEmpPajakService;
	}

	public void setTempUnregPayrollEmpPajakService(
			TempUnregPayrollEmpPajakService tempUnregPayrollEmpPajakService) {
		this.tempUnregPayrollEmpPajakService = tempUnregPayrollEmpPajakService;
	}

	public UnregSalaryService getUnregSalaryService() {
		return unregSalaryService;
	}

	public void setUnregSalaryService(UnregSalaryService unregSalaryService) {
		this.unregSalaryService = unregSalaryService;
	}

	public TaxComponentService getTaxComponentService() {
		return taxComponentService;
	}

	public void setTaxComponentService(TaxComponentService taxComponentService) {
		this.taxComponentService = taxComponentService;
	}

	public UnregPayrollEmpPajakSearchParameter getParameter() {
		return parameter;
	}

	public void setParameter(UnregPayrollEmpPajakSearchParameter parameter) {
		this.parameter = parameter;
	}

	public LazyDataModel<TempUnregPayrollEmpPajak> getLazyDataModel() {
		if(lazyDataModel == null){
			lazyDataModel = new UnregCalculationTaxLazyDataModel(parameter, tempUnregPayrollEmpPajakService);
		}
		return lazyDataModel;
	}

	public void setLazyDataModel(
			LazyDataModel<TempUnregPayrollEmpPajak> lazyDataModel) {
		this.lazyDataModel = lazyDataModel;
	}
    
}
