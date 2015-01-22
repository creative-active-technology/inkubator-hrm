/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.personalia;

import com.inkubator.hrm.entity.LoanCanceled;
import com.inkubator.hrm.service.LoanCanceledService;
import com.inkubator.hrm.web.lazymodel.LoanCanceledLazyDataModel;
import com.inkubator.hrm.web.search.LoanCanceledSearchParameter;
import com.inkubator.webcore.controller.BaseController;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author deni
 */
@ManagedBean(name = "loanCanceledViewController")
@ViewScoped
public class LoanCanceledViewController extends BaseController {

    private LoanCanceledSearchParameter searchParameter;
    private LazyDataModel<LoanCanceled> lazyDataModel;
    private LoanCanceled selectedLoan;
    @ManagedProperty(value = "#{loanCanceledService}")
    private LoanCanceledService loanCanceledService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        searchParameter = new LoanCanceledSearchParameter();
    }

    @PreDestroy
    public void cleanAndExit() {
        searchParameter = null;
        lazyDataModel = null;
        selectedLoan = null;
        loanCanceledService = null;
    }

    public void doSearch() {
        lazyDataModel = null;
    }

    public void doSelectEntity() {
        try {
            selectedLoan = this.loanCanceledService.getEntityByPkWithDetail(selectedLoan.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public LoanCanceledSearchParameter getSearchParameter() {
        return searchParameter;
    }

    public void setSearchParameter(LoanCanceledSearchParameter searchParameter) {
        this.searchParameter = searchParameter;
    }

    public LazyDataModel<LoanCanceled> getLazyDataModel() {
        if (lazyDataModel == null) {
            lazyDataModel = new LoanCanceledLazyDataModel(searchParameter, loanCanceledService);
        }
        return lazyDataModel;
    }

    public void setLazyDataModel(LazyDataModel<LoanCanceled> lazyDataModel) {
        this.lazyDataModel = lazyDataModel;
    }

    public LoanCanceled getSelectedLoan() {
        return selectedLoan;
    }

    public void setSelectedLoan(LoanCanceled selectedLoan) {
        this.selectedLoan = selectedLoan;
    }

    public LoanCanceledService getLoanCanceledService() {
        return loanCanceledService;
    }

    public void setLoanCanceledService(LoanCanceledService loanCanceledService) {
        this.loanCanceledService = loanCanceledService;
    }

}
