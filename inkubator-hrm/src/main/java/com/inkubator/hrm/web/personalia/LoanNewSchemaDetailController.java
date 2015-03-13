/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.personalia;

import com.inkubator.hrm.entity.LoanNewSchema;
import com.inkubator.hrm.service.LoanNewSchemaService;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Deni
 */
@ManagedBean(name = "loanNewSchemaDetailController")
@ViewScoped
public class LoanNewSchemaDetailController extends BaseController {
    private LoanNewSchema selectedLoanSchema;
    @ManagedProperty(value = "#{loanNewSchemaService}")
    private LoanNewSchemaService loanNewSchemaService;

    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            String loanNewSchemaId = FacesUtil.getRequestParameter("execution");
            selectedLoanSchema = loanNewSchemaService.getEntiyByPK(Long.parseLong(loanNewSchemaId.substring(1)));
        } catch (Exception ex) {
            LOGGER.error("Error", ex);

        }
    }
    
    @PreDestroy
    public void cleanAndExit() {
        selectedLoanSchema = null;
        loanNewSchemaService = null;
    }
    
    public String doBack() {
        return "/protected/personalia/loan_new_schema_view.htm?faces-redirect=true";
    }
    
    public LoanNewSchema getSelectedLoanSchema() {
        return selectedLoanSchema;
    }

    public void setSelectedLoanSchema(LoanNewSchema selectedLoanSchema) {
        this.selectedLoanSchema = selectedLoanSchema;
    }

    public LoanNewSchemaService getLoanNewSchemaService() {
        return loanNewSchemaService;
    }

    public void setLoanNewSchemaService(LoanNewSchemaService loanNewSchemaService) {
        this.loanNewSchemaService = loanNewSchemaService;
    }
    
    
}
