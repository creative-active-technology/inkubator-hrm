/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.personalia;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.Loan;
import com.inkubator.hrm.service.LoanService;
import com.inkubator.hrm.web.lazymodel.LoanCanceledProcessLazyDataModel;
import com.inkubator.hrm.web.lazymodel.LoanLazyDataModel;
import com.inkubator.hrm.web.search.LoanSearchParameter;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.hibernate.exception.ConstraintViolationException;
import org.primefaces.model.LazyDataModel;
import org.springframework.dao.DataIntegrityViolationException;

/**
 *
 * @author deni
 */
@ManagedBean(name = "loanCanceledProcessViewController")
@ViewScoped
public class LoanCanceledProcessViewController extends BaseController {

    private LoanSearchParameter searchParameter;
    private LazyDataModel<Loan> lazyDataLoan;
    private Loan selectedLoan;
    @ManagedProperty(value = "#{loanService}")
    private LoanService loanService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        searchParameter = new LoanSearchParameter();
    }

    @PreDestroy
    public void cleanAndExit() {
        searchParameter = null;
        lazyDataLoan = null;
        selectedLoan = null;
        loanService = null;
    }

    public LoanSearchParameter getSearchParameter() {
        return searchParameter;
    }

    public void setSearchParameter(LoanSearchParameter searchParameter) {
        this.searchParameter = searchParameter;
    }

    public LazyDataModel<Loan> getLazyDataLoan() {
        if (lazyDataLoan == null) {
            lazyDataLoan = new LoanCanceledProcessLazyDataModel(searchParameter, loanService);
        }
        return lazyDataLoan;
    }

    public void setLazyDataLoan(LazyDataModel<Loan> lazyDataLoan) {
        this.lazyDataLoan = lazyDataLoan;
    }

    public Loan getSelectedLoan() {
        return selectedLoan;
    }

    public void setSelectedLoan(Loan selectedLoan) {
        this.selectedLoan = selectedLoan;
    }

    public void setLoanService(LoanService loanService) {
        this.loanService = loanService;
    }

    public void doSearch() {
        lazyDataLoan = null;
    }

    public String doCanceled() {
        return "/protected/personalia/loan_canceled_form.htm?faces-redirect=true&execution=e" + selectedLoan.getId();
    }

    public void doSelectEntity() {
        try {
            selectedLoan = this.loanService.getEntiyByPK(selectedLoan.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public void doDelete() {
        try {
            loanService.delete(selectedLoan);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            LOGGER.error("Error when doDelete BusinessTravel", ex);
        } catch (Exception ex) {
            LOGGER.error("Error when doDelete BusinessTravel", ex);
        }
    }
    
    
}
