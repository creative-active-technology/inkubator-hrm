/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.loan;

import java.util.List;

import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.LoanNewSchemaListOfEmp;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.LoanNewApplicationService;
import com.inkubator.hrm.service.LoanNewSchemaListOfEmpService;
import com.inkubator.hrm.service.LoanNewSchemaListOfTypeService;
import com.inkubator.hrm.util.HrmUserInfoUtil;
import com.inkubator.hrm.web.model.LoanHistoryViewModel;
import com.inkubator.hrm.web.model.LoanUsageHistoryViewModel;
import com.inkubator.webcore.controller.BaseController;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
@ManagedBean(name = "loanHistoryViewController")
@ViewScoped
public class LoanHistoryViewController extends BaseController {

    @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;
    @ManagedProperty(value = "#{loanNewApplicationService}")
    private LoanNewApplicationService loanNewApplicationService;
    @ManagedProperty(value = "#{loanNewSchemaListOfTypeService}")
    private LoanNewSchemaListOfTypeService loanNewSchemaListOfTypeService;
    @ManagedProperty(value = "#{loanNewSchemaListOfEmpService}")
    private LoanNewSchemaListOfEmpService loanNewSchemaListOfEmpService;
    private List<LoanHistoryViewModel> listLoanHistoryViewModel;
    private List<LoanUsageHistoryViewModel> listLoanUsageHistoryViewModel;
    private EmpData selectedEmpData;
   

    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            Long empDataId = HrmUserInfoUtil.getEmpId();
            selectedEmpData = empDataService.getByEmpIdWithDetail(empDataId);
            listLoanHistoryViewModel = loanNewApplicationService.getListLoanHistoryByEmpDataId(selectedEmpData.getId());
            LoanNewSchemaListOfEmp loanNewSchemaListOfEmp = loanNewSchemaListOfEmpService.getEntityByEmpDataId(empDataId);
            listLoanUsageHistoryViewModel = loanNewSchemaListOfTypeService.getListLoanUsageHistoryByLoanNewSchemaAndEmpDataIdWhereStatusActive(loanNewSchemaListOfEmp.getLoanNewSchema().getId(), empDataId);
        } catch (Exception ex) {
            LOGGER.error(ex, ex);
        }

    }
    
    @PreDestroy
    public void cleanAndExit() {
        loanNewApplicationService = null;
        loanNewSchemaListOfTypeService = null;
        loanNewSchemaListOfEmpService = null;
        empDataService = null;
        listLoanHistoryViewModel = null;
        listLoanUsageHistoryViewModel = null;
        selectedEmpData = null;
    }
    
    public String doExit() {
        cleanAndExit();
        return "/protected/home.htm?faces-redirect=true";
    }

    public EmpData getSelectedEmpData() {
		return selectedEmpData;
	}

	public void setSelectedEmpData(EmpData selectedEmpData) {
		this.selectedEmpData = selectedEmpData;
	}
	
	public List<LoanHistoryViewModel> getListLoanHistoryViewModel() {
		return listLoanHistoryViewModel;
	}

	public void setListLoanHistoryViewModel(List<LoanHistoryViewModel> listLoanHistoryViewModel) {
		this.listLoanHistoryViewModel = listLoanHistoryViewModel;
	}

	public void setEmpDataService(EmpDataService empDataService) {
		this.empDataService = empDataService;
	}

	public void setLoanNewApplicationService(LoanNewApplicationService loanNewApplicationService) {
		this.loanNewApplicationService = loanNewApplicationService;
	}

	public List<LoanUsageHistoryViewModel> getListLoanUsageHistoryViewModel() {
		return listLoanUsageHistoryViewModel;
	}

	public void setListLoanUsageHistoryViewModel(List<LoanUsageHistoryViewModel> listLoanUsageHistoryViewModel) {
		this.listLoanUsageHistoryViewModel = listLoanUsageHistoryViewModel;
	}

	public void setLoanNewSchemaListOfTypeService(LoanNewSchemaListOfTypeService loanNewSchemaListOfTypeService) {
		this.loanNewSchemaListOfTypeService = loanNewSchemaListOfTypeService;
	}

	public void setLoanNewSchemaListOfEmpService(LoanNewSchemaListOfEmpService loanNewSchemaListOfEmpService) {
		this.loanNewSchemaListOfEmpService = loanNewSchemaListOfEmpService;
	}

}
