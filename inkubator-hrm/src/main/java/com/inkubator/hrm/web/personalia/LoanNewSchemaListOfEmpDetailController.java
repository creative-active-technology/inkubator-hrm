/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.personalia;

import com.inkubator.hrm.entity.LoanNewSchemaListOfEmp;
import com.inkubator.hrm.entity.LoanNewSchemaListOfType;
import com.inkubator.hrm.entity.LoanNewType;
import com.inkubator.hrm.service.LoanNewSchemaListOfEmpService;
import com.inkubator.hrm.web.model.LoanNewSchemaListOfEmpViewModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Deni
 */
@ManagedBean(name = "loanNewSchemaListOfEmpDetailController")
@ViewScoped
public class LoanNewSchemaListOfEmpDetailController extends BaseController {

    @ManagedProperty(value = "#{loanNewSchemaListOfEmpService}")
    private LoanNewSchemaListOfEmpService service;
    private LoanNewSchemaListOfEmp selected;
    private List<LoanNewSchemaListOfType> listLoanNewSchemaType;
    private LoanNewSchemaListOfEmpViewModel selectedListOfEmp;
    private List<LoanNewType> listNewType;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try {
            String empDataId = FacesUtil.getRequestParameter("execution");
            selected = service.getEntityByEmpDataIdAndLoanSchemaId(Long.valueOf(empDataId.substring(1)), Long.valueOf(empDataId.substring(1)));
//            listLoanNewSchemaType = new ArrayList<>(selected.getListLoanNewType());
            listLoanNewSchemaType = selected.getListLoanNewType();
        } catch (Exception ex) {
            Logger.getLogger(LoanNewSchemaListOfEmpDetailController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @PreDestroy
    private void cleanAndExit() {
        service = null;
        selected = null;
        listLoanNewSchemaType = null;
        listNewType = null;
    }
    
    public String doBack(){
        return "/protected/personalia/loan_emp_view.htm?faces-redirect=true";
    }

    public LoanNewSchemaListOfEmpService getService() {
        return service;
    }

    public void setService(LoanNewSchemaListOfEmpService service) {
        this.service = service;
    }

    public LoanNewSchemaListOfEmp getSelected() {
        return selected;
    }

    public void setSelected(LoanNewSchemaListOfEmp selected) {
        this.selected = selected;
    }

    public LoanNewSchemaListOfEmpViewModel getSelectedListOfEmp() {
        return selectedListOfEmp;
    }

    public void setSelectedListOfEmp(LoanNewSchemaListOfEmpViewModel selectedListOfEmp) {
        this.selectedListOfEmp = selectedListOfEmp;
    }

    public List<LoanNewType> getListNewType() {
        return listNewType;
    }

    public void setListNewType(List<LoanNewType> listNewType) {
        this.listNewType = listNewType;
    }

    public List<LoanNewSchemaListOfType> getListLoanNewSchemaType() {
        return listLoanNewSchemaType;
    }

    public void setListLoanNewSchemaType(List<LoanNewSchemaListOfType> listLoanNewSchemaType) {
        this.listLoanNewSchemaType = listLoanNewSchemaType;
    }

    
}
