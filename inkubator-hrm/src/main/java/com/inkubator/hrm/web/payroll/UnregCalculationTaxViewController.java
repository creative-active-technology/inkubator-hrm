/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.payroll;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.inkubator.hrm.entity.UnregSalary;
import com.inkubator.hrm.service.TempUnregPayrollEmpPajakService;
import com.inkubator.hrm.service.UnregSalaryService;
import com.inkubator.hrm.web.model.UnregPayrollEmpPajakModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;

/**
 *
 * @author rikykojek
 */
@ManagedBean(name = "unregCalculationTaxViewController")
@ViewScoped
public class UnregCalculationTaxViewController extends BaseController {

	private UnregPayrollEmpPajakModel selected;
    private List<UnregPayrollEmpPajakModel> list;
    private UnregSalary unregSalary;
	@ManagedProperty(value = "#{tempUnregPayrollEmpPajakService}")
    private TempUnregPayrollEmpPajakService tempUnregPayrollEmpPajakService;
	@ManagedProperty(value = "#{unregSalaryService}")
    private UnregSalaryService unregSalaryService; 

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try {
        	String id = FacesUtil.getRequestParameter("execution");	
        	unregSalary = unregSalaryService.getEntiyByPK(Long.parseLong(id.substring(1)));
            list = tempUnregPayrollEmpPajakService.getAllDataGroupingTaxCompByUnregSalaryId(unregSalary.getId());
            
        } catch (Exception ex) {
            Logger.getLogger(UnregCalculationTaxViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @PreDestroy
    public void cleanAndExit() {
    	selected = null;
        tempUnregPayrollEmpPajakService = null;
        list = null;
        unregSalary = null;
        unregSalaryService = null;
    }

    public String doDetail() {
        return "/protected/payroll/unreg_calculation_tax_detail.htm?faces-redirect=true&execution=e" + selected.getUnregSalaryId() + "&comp=e" + selected.getTaxComponentId();
    }

	public UnregPayrollEmpPajakModel getSelected() {
		return selected;
	}

	public void setSelected(UnregPayrollEmpPajakModel selected) {
		this.selected = selected;
	}

	public List<UnregPayrollEmpPajakModel> getList() {
		return list;
	}

	public void setList(List<UnregPayrollEmpPajakModel> list) {
		this.list = list;
	}

	public UnregSalary getUnregSalary() {
		return unregSalary;
	}

	public void setUnregSalary(UnregSalary unregSalary) {
		this.unregSalary = unregSalary;
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
	
	
    
}
