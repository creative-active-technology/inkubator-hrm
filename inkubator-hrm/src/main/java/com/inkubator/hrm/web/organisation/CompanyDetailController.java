/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.organisation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.hibernate.exception.ConstraintViolationException;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.springframework.dao.DataIntegrityViolationException;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.Company;
import com.inkubator.hrm.entity.CompanyBankAccount;
import com.inkubator.hrm.entity.CompanyCommisioner;
import com.inkubator.hrm.entity.FinancialPartner;
import com.inkubator.hrm.service.CompanyBankAccountService;
import com.inkubator.hrm.service.CompanyCommisionerService;
import com.inkubator.hrm.service.CompanyService;
import com.inkubator.hrm.service.FinancialPartnerService;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "companyDetailController")
@ViewScoped
public class CompanyDetailController extends BaseController {

    private Company selectedCompany;
    private CompanyBankAccount selectedCompanyBankAccount;
    private FinancialPartner selectedFinancialPartner;
    private List<CompanyBankAccount> companyBankAccounts;
    private List<FinancialPartner> financialPartners;
    private List<CompanyCommisioner> listCompanyCommmisioner;
    @ManagedProperty(value = "#{companyService}")
    private CompanyService companyService;
    @ManagedProperty(value = "#{companyBankAccountService}")
    private CompanyBankAccountService companyBankAccountService;
    @ManagedProperty(value = "#{financialPartnerService}")
    private FinancialPartnerService financialPartnerService;
    @ManagedProperty(value = "#{companyCommisionerService}")
    private CompanyCommisionerService companyCommisionerService;

    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            String id = FacesUtil.getRequestParameter("execution");
            selectedCompany = companyService.getEntityByPKWithDetail(Long.parseLong(id.substring(1))); 
            companyBankAccounts = companyBankAccountService.getAllDataByCompanyId(selectedCompany.getId());
            financialPartners = financialPartnerService.getAllDataByCompanyId(selectedCompany.getId());
            listCompanyCommmisioner = companyCommisionerService.getEntityByCompanyId(selectedCompany.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    @PreDestroy
    public void cleanAndExit() {
        selectedCompany = null;
        companyService = null;
        financialPartnerService = null;
        companyBankAccounts = null;
        financialPartners = null;
        selectedCompanyBankAccount = null;
        selectedFinancialPartner = null;
        companyBankAccountService = null;
        listCompanyCommmisioner = null;
        companyCommisionerService = null;
    }    

	public Company getSelectedCompany() {
		return selectedCompany;
	}

	public void setSelectedCompany(Company selectedCompany) {
		this.selectedCompany = selectedCompany;
	}

	public void setCompanyService(CompanyService companyService) {
		this.companyService = companyService;
	}

	public List<CompanyBankAccount> getCompanyBankAccounts() {
		return companyBankAccounts;
	}

	public void setCompanyBankAccounts(List<CompanyBankAccount> companyBankAccounts) {
		this.companyBankAccounts = companyBankAccounts;
	}

	public List<FinancialPartner> getFinancialPartners() {
		return financialPartners;
	}

	public void setFinancialPartners(List<FinancialPartner> financialPartners) {
		this.financialPartners = financialPartners;
	}

	public CompanyBankAccount getSelectedCompanyBankAccount() {
		return selectedCompanyBankAccount;
	}

	public void setSelectedCompanyBankAccount(
			CompanyBankAccount selectedCompanyBankAccount) {
		this.selectedCompanyBankAccount = selectedCompanyBankAccount;
	}
	
	public void setCompanyBankAccountService(
			CompanyBankAccountService companyBankAccountService) {
		this.companyBankAccountService = companyBankAccountService;
	}

	public void setSelectedFinancialPartner(
			FinancialPartner selectedFinancialPartner) {
		this.selectedFinancialPartner = selectedFinancialPartner;
	}

	public FinancialPartner getSelectedFinancialPartner() {
		return selectedFinancialPartner;
	}

	public void setFinancialPartnerService(
			FinancialPartnerService financialPartnerService) {
		this.financialPartnerService = financialPartnerService;
	}

	public String doBack() {
        return "/protected/organisation/company_view.htm?faces-redirect=true";
    }

    public String doUpdate() {
        return "/protected/organisation/company_form.htm?faces-redirect=true&execution=e" + selectedCompany.getId();
    }
    
    /**
     * START Company Bank Account tabView
     */
    public void doSelectCompanyBankAccount() {
        try {
        	selectedCompanyBankAccount = companyBankAccountService.getEntityByPKWithDetail(selectedCompanyBankAccount.getId());
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    public void doUpdateCompanyBankAccount() {

        List<String> companyBankAccountId = new ArrayList<>();
        companyBankAccountId.add(String.valueOf(selectedCompanyBankAccount.getId()));

        List<String> companyId = new ArrayList<>();
        companyId.add(String.valueOf(selectedCompany.getId()));

        Map<String, List<String>> dataToSend = new HashMap<>();
        dataToSend.put("companyBankAccountId", companyBankAccountId);
        dataToSend.put("companyId", companyId);
        this.showDialogCompanyBankAccount(dataToSend);

    }

    public void doAddCompanyBankAccount() {
    	List<String> companyId = new ArrayList<>();
        companyId.add(String.valueOf(selectedCompany.getId()));

        Map<String, List<String>> dataToSend = new HashMap<>();
        dataToSend.put("companyId", companyId);
        this.showDialogCompanyBankAccount(dataToSend);
    }

    public void doDeleteCompanyBankAccount() {
        try {
            companyBankAccountService.delete(selectedCompanyBankAccount);
            companyBankAccounts = companyBankAccountService.getAllDataByCompanyId(selectedCompany.getId());
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error when doDelete companyBankAccount", ex);
        }
    }

    private void showDialogCompanyBankAccount(Map<String, List<String>> params) {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 550);
        options.put("contentHeight", 350);
        RequestContext.getCurrentInstance().openDialog("company_bank_account", options, params);
    }

    public void onDialogReturnCompanyBankAccount(SelectEvent event) {
        try {
        	companyBankAccounts = companyBankAccountService.getAllDataByCompanyId(selectedCompany.getId());
            super.onDialogReturn(event);
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }
    /**
     * END Company Bank Account tabView
     */
    
    
    /**
     * START FinancialPartner tabView
     */
    public void doSelectFinancialPartner() {
        try {
        	selectedFinancialPartner = financialPartnerService.getEntityByPKWithDetail(selectedFinancialPartner.getId());
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    public void doUpdateFinancialPartner() {

        List<String> financialPartnerId = new ArrayList<>();
        financialPartnerId.add(String.valueOf(selectedFinancialPartner.getId()));

        List<String> companyId = new ArrayList<>();
        companyId.add(String.valueOf(selectedCompany.getId()));

        Map<String, List<String>> dataToSend = new HashMap<>();
        dataToSend.put("financialPartnerId", financialPartnerId);
        dataToSend.put("companyId", companyId);
        this.showDialogFinancialPartner(dataToSend);

    }

    public void doAddFinancialPartner() {
    	List<String> companyId = new ArrayList<>();
        companyId.add(String.valueOf(selectedCompany.getId()));

        Map<String, List<String>> dataToSend = new HashMap<>();
        dataToSend.put("companyId", companyId);
        this.showDialogFinancialPartner(dataToSend);
    }

    public void doDeleteFinancialPartner() {
        try {
        	financialPartnerService.delete(selectedFinancialPartner);
        	financialPartners = financialPartnerService.getAllDataByCompanyId(selectedCompany.getId());
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error when doDelete financialPartner", ex);
        }
    }

    private void showDialogFinancialPartner(Map<String, List<String>> params) {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 500);
        options.put("contentHeight", 350);
        RequestContext.getCurrentInstance().openDialog("company_financial_partner", options, params);
    }

    public void onDialogReturnFinancialPartner(SelectEvent event) {
        try {
        	financialPartners = financialPartnerService.getAllDataByCompanyId(selectedCompany.getId());
            super.onDialogReturn(event);
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }
    /**
     * END FinancialPartner tabView
     */
    
    /**
     * START Commisioner tabView
     */
    
    public void doAddCommisioner() {
    	List<String> companyId = new ArrayList<>();
        companyId.add(String.valueOf(selectedCompany.getId()));

        Map<String, List<String>> dataToSend = new HashMap<>();
        dataToSend.put("companyId", companyId);
        this.showDialogCompanyCommisioner(dataToSend);
    }
    
    private void showDialogCompanyCommisioner(Map<String, List<String>> params) {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 500);
        options.put("contentHeight", 350);
        RequestContext.getCurrentInstance().openDialog("commisioner_form", options, params);
    }
    
    public void onDialogReturnCommisioner(SelectEvent event) {
        try {
        	listCompanyCommmisioner = companyCommisionerService.getEntityByCompanyId(selectedCompany.getId());
            super.onDialogReturn(event);
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }
    
    public List<CompanyCommisioner> getListCompanyCommmisioner() {
        return listCompanyCommmisioner;
    }

    public void setListCompanyCommmisioner(List<CompanyCommisioner> listCompanyCommmisioner) {
        this.listCompanyCommmisioner = listCompanyCommmisioner;
    }

    public CompanyCommisionerService getCompanyCommisionerService() {
        return companyCommisionerService;
    }

    public void setCompanyCommisionerService(CompanyCommisionerService companyCommisionerService) {
        this.companyCommisionerService = companyCommisionerService;
    }
    
    
}
