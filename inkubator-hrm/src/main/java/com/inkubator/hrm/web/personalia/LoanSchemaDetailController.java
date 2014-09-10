/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.personalia;

import com.inkubator.hrm.entity.LoanSchema;
import com.inkubator.hrm.entity.LoanSchemaEmployeeType;
import com.inkubator.hrm.service.LoanSchemaService;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Deni
 */
@ManagedBean(name = "loanSchemaDetailController")
@ViewScoped
public class LoanSchemaDetailController extends BaseController{
    private LoanSchema selectedLoanSchema;
    @ManagedProperty(value = "#{loanSchemaService}")
    private LoanSchemaService loanSchemaService;
    private List<LoanSchema> listLoanSchema;
    private List<LoanSchemaEmployeeType> listLoanSchemaEmployeeType;
    
    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            String loanId = FacesUtil.getRequestParameter("execution");
            selectedLoanSchema = loanSchemaService.getEntityByPkWithAllRelation(Long.parseLong(loanId.substring(1)));
            listLoanSchemaEmployeeType = new ArrayList<>(selectedLoanSchema.getLoanSchemaEmployeeTypes());
            
        } catch (Exception ex) {
            LOGGER.error("Error", ex);

        }
    }
    
    @PreDestroy
    public void cleanAndExit() {
        selectedLoanSchema = null;
        loanSchemaService = null;
        listLoanSchema = null;
        listLoanSchemaEmployeeType = null;
    }
    
    public String doBack() {
        return "/protected/personalia/loan_schema_view.htm?faces-redirect=true";
    }

    public String doEdit() {
        return "/protected/personalia/loan_schema_form.htm?faces-redirect=true&execution=e" + selectedLoanSchema.getId();
    }
    
    public LoanSchema getSelectedLoanSchema() {
        return selectedLoanSchema;
    }

    public void setSelectedLoanSchema(LoanSchema selectedLoanSchema) {
        this.selectedLoanSchema = selectedLoanSchema;
    }

    public LoanSchemaService getLoanSchemaService() {
        return loanSchemaService;
    }

    public void setLoanSchemaService(LoanSchemaService loanSchemaService) {
        this.loanSchemaService = loanSchemaService;
    }

    public List<LoanSchema> getListLoanSchema() {
        return listLoanSchema;
    }

    public void setListLoanSchema(List<LoanSchema> listLoanSchema) {
        this.listLoanSchema = listLoanSchema;
    }

    public List<LoanSchemaEmployeeType> getListLoanSchemaEmployeeType() {
        return listLoanSchemaEmployeeType;
    }

    public void setListLoanSchemaEmployeeType(List<LoanSchemaEmployeeType> listLoanSchemaEmployeeType) {
        this.listLoanSchemaEmployeeType = listLoanSchemaEmployeeType;
    }
    
    
}
