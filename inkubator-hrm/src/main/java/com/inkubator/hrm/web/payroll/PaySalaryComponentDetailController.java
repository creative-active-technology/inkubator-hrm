/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.payroll;

import com.inkubator.hrm.entity.PaySalaryComponent;
import com.inkubator.hrm.entity.PaySalaryEmpType;
import com.inkubator.hrm.service.PaySalaryComponentEmployeeTypeService;
import com.inkubator.hrm.service.PaySalaryComponentService;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
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
@ManagedBean(name = "paySalaryComponentDetailController")
@ViewScoped
public class PaySalaryComponentDetailController extends BaseController{
    private PaySalaryComponent selectedPaySalaryComponent;
    @ManagedProperty(value = "#{paySalaryComponentService}")
    private PaySalaryComponentService paySalaryComponentService;
    @ManagedProperty(value = "#{paySalaryComponentEmployeeTypeService}")
    private PaySalaryComponentEmployeeTypeService paySalaryComponentEmployeeTypeService;
    private List<PaySalaryEmpType> listPaySalaryComponentEmpType;
    
    @PostConstruct
    @Override
    public void initialization() {
        try {
            
            super.initialization();
            String paySalaryEmpType = FacesUtil.getRequestParameter("execution");
            selectedPaySalaryComponent = paySalaryComponentService.getEntityByPkWithDetail(Long.parseLong(paySalaryEmpType.substring(1)));
            listPaySalaryComponentEmpType = paySalaryComponentEmployeeTypeService.getEntityByPaySalaryComponentId(Long.parseLong(paySalaryEmpType.substring(1)));            
            System.out.println(listPaySalaryComponentEmpType.size());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);

        }
    }
    
    @PreDestroy
    public void cleanAndExit() {
        selectedPaySalaryComponent = null;
        paySalaryComponentService = null;
        paySalaryComponentEmployeeTypeService = null;
        listPaySalaryComponentEmpType = null;
    }

    public String doBack() {
        return "/protected/payroll/pay_salary_component_view.htm?faces-redirect=true";
    }
    
    public String doEdit() {
        return "/protected/payroll/pay_salary_component_form.htm?faces-redirect=true&execution=e" + selectedPaySalaryComponent.getId();
    }
    
    public PaySalaryComponent getSelectedPaySalaryComponent() {
        return selectedPaySalaryComponent;
    }

    public void setSelectedPaySalaryComponent(PaySalaryComponent selectedPaySalaryComponent) {
        this.selectedPaySalaryComponent = selectedPaySalaryComponent;
    }

    public PaySalaryComponentService getPaySalaryComponentService() {
        return paySalaryComponentService;
    }

    public void setPaySalaryComponentService(PaySalaryComponentService paySalaryComponentService) {
        this.paySalaryComponentService = paySalaryComponentService;
    }

    public PaySalaryComponentEmployeeTypeService getPaySalaryComponentEmployeeTypeService() {
        return paySalaryComponentEmployeeTypeService;
    }

    public void setPaySalaryComponentEmployeeTypeService(PaySalaryComponentEmployeeTypeService paySalaryComponentEmployeeTypeService) {
        this.paySalaryComponentEmployeeTypeService = paySalaryComponentEmployeeTypeService;
    }

    public List<PaySalaryEmpType> getListPaySalaryComponentEmpType() {
        return listPaySalaryComponentEmpType;
    }

    public void setListPaySalaryComponentEmpType(List<PaySalaryEmpType> listPaySalaryComponentEmpType) {
        this.listPaySalaryComponentEmpType = listPaySalaryComponentEmpType;
    }
    
    
    
}
