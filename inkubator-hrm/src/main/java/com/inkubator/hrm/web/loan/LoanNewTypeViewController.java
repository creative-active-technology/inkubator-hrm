/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.loan;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.LoanNewType;
import com.inkubator.hrm.service.LoanNewTypeService;
import com.inkubator.hrm.web.lazymodel.LoanNewTypeLazyDataModel;
import com.inkubator.hrm.web.search.LoanNewTypeSearchParameter;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
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
import org.primefaces.model.LazyDataModel;
import org.springframework.dao.DataIntegrityViolationException;

/**
 *
 * @author Deni
 */
@ManagedBean(name = "loanNewTypeViewController")
@ViewScoped
public class LoanNewTypeViewController extends BaseController{
    private LoanNewTypeSearchParameter searchParameter;
    private LazyDataModel<LoanNewType> lazyDataLoanType;
    private LoanNewType selectedLoanType;
    @ManagedProperty(value = "#{loanNewTypeService}")
    private LoanNewTypeService loanTypeService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        searchParameter = new LoanNewTypeSearchParameter();
    }

    @PreDestroy
    public void cleanAndExit() {
        loanTypeService = null;
        searchParameter = null;
        lazyDataLoanType = null;
        selectedLoanType = null;
    }

    public void doSearch() {
        lazyDataLoanType = null;
    }

    public void doDetail() {
        try {
            selectedLoanType = this.loanTypeService.getEntityWithRelationByPk(selectedLoanType.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public void doDelete() {
        try {
            loanTypeService.delete(selectedLoanType);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            LOGGER.error("Error when doDelete LoanType ", ex);
        } catch (Exception ex) {
            LOGGER.error("Error when doDelete LoanType", ex);
        }
    }

    public void doAdd() {
        showDialog(null);
    }

    public void doUpdate() {
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> values = new ArrayList<>();
        values.add(String.valueOf(selectedLoanType.getId()));
        dataToSend.put("param", values);
        showDialog(dataToSend);
    }

    private void showDialog(Map<String, List<String>> params) {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 550);
        options.put("contentHeight", 500);
        RequestContext.getCurrentInstance().openDialog("loan_new_type_form", options, params);
    }

    @Override
    public void onDialogReturn(SelectEvent event) {
        //re-calculate searching
        doSearch();
        super.onDialogReturn(event);
    }

    public LoanNewTypeSearchParameter getSearchParameter() {
        return searchParameter;
    }

    public void setSearchParameter(LoanNewTypeSearchParameter searchParameter) {
        this.searchParameter = searchParameter;
    }

    public LazyDataModel<LoanNewType> getLazyDataLoanType() {
        if (lazyDataLoanType == null) {
            lazyDataLoanType = new LoanNewTypeLazyDataModel(searchParameter, loanTypeService);
        }
        return lazyDataLoanType;
    }

    public void setLazyDataLoanType(LazyDataModel<LoanNewType> lazyDataLoanType) {
        this.lazyDataLoanType = lazyDataLoanType;
    }

    public LoanNewType getSelectedLoanType() {
        return selectedLoanType;
    }

    public void setSelectedLoanType(LoanNewType selectedLoanType) {
        this.selectedLoanType = selectedLoanType;
    }

    public LoanNewTypeService getLoanTypeService() {
        return loanTypeService;
    }

    public void setLoanTypeService(LoanNewTypeService loanTypeService) {
        this.loanTypeService = loanTypeService;
    }

    
    
}

