/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.payroll;

import com.inkubator.hrm.web.employee.*;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.Loan;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.LoanService;
import com.inkubator.hrm.web.lazymodel.EmpDataLazyDataModel;
import com.inkubator.hrm.web.lazymodel.LoanCanceledProcessLazyDataModel;
import com.inkubator.hrm.web.lazymodel.LoanLazyDataModel;
import com.inkubator.hrm.web.lazymodel.LoanDisbursementLazyDataModel;
import com.inkubator.hrm.web.model.LoanModel;
import com.inkubator.hrm.web.search.EmpDataSearchParameter;
import com.inkubator.hrm.web.search.LoanSearchParameter;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
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
 * @author Ahmad Mudzakkir Amal
 */
@ManagedBean(name = "loanDisbursementViewController")
@ViewScoped
public class LoanDisbursementViewController extends BaseController {

    @ManagedProperty(value = "#{loanService}")
    private LoanService loanService;   
    private LoanSearchParameter loanSearchParameter;
    private LazyDataModel<Loan> lazy;
    private Loan selected;
    private LoanModel loanModel;     
    
    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            loanSearchParameter = new LoanSearchParameter();
            loanModel = new LoanModel();                     
            
        } catch (Exception ex) {
            Logger.getLogger(LoanDisbursementViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @PreDestroy
    private void cleanAndExit() {       
        lazy = null;
        loanSearchParameter = null;
        loanService = null;        
        selected = null;
        loanModel = null;
        
    }
    
    @Override
    public void onDialogReturn(SelectEvent event) {
        try {
            lazy = null;  
             super.onDialogReturn(event);
        } catch (Exception ex) {
            Logger.getLogger(LoanDisbursementViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void doSearch() {
        lazy = null;
    }    
   
    
    public void doDetail() {
        
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> dataIsi = new ArrayList<>();
        dataIsi.add(String.valueOf(selected.getId()));
        dataToSend.put("param", dataIsi);        
        
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 450);
        options.put("contentHeight",420);       
         
        RequestContext.getCurrentInstance().openDialog("loan_disbursement_form", options, dataToSend);       
    }    
    
    
    public LoanService getLoanService() {
        return loanService;
    }

    public void setLoanService(LoanService loanService) {
        this.loanService = loanService;
    }

    public LoanSearchParameter getLoanSearchParameter() {
        return loanSearchParameter;
    }

    public void setLoanSearchParameter(LoanSearchParameter loanSearchParameter) {
        this.loanSearchParameter = loanSearchParameter;
    }

    public Loan getSelected() {
        return selected;
    }

    public void setSelected(Loan selected) {
        this.selected = selected;
    }

    public LoanModel getLoanModel() {
        return loanModel;
    }

    public void setLoanModel(LoanModel loanModel) {
        this.loanModel = loanModel;
    }
    
    public LazyDataModel<Loan> getLazy() {
        if (lazy == null) {
            lazy = new LoanDisbursementLazyDataModel(loanSearchParameter, loanService);
        }
        return lazy;
    }

    public void setLazy(LazyDataModel<Loan> lazy) {
        this.lazy = lazy;
    }
}
