/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.payroll;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.PaySalaryComponent;
import com.inkubator.hrm.service.PaySalaryComponentService;
import com.inkubator.hrm.web.lazymodel.PaySalaryComponentLazyDataModel;
import com.inkubator.hrm.web.search.PaySalaryComponentSearchParameter;
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
 * @author Deni
 */
@ManagedBean(name = "paySalaryComponentViewController")
@ViewScoped
public class PaySalaryComponentViewController extends BaseController{
    @ManagedProperty(value = "#{paySalaryComponentService}")
    private PaySalaryComponentService service;
    private PaySalaryComponentSearchParameter searchParameter;
    private LazyDataModel<PaySalaryComponent> lazy;
    private PaySalaryComponent selected;
    
    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        searchParameter = new PaySalaryComponentSearchParameter();
    }
    
    @PreDestroy
    private void cleanAndExit() {
        searchParameter=null;
        lazy=null;
        service=null;
        selected=null;
        
    }
    
    public void doSearch() {
        lazy = null;
    }
    
    public void doSelectEntityWithDetail() {
        try {
            selected = this.service.getEntiyByPK(selected.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }
    
    public void doDelete() {
        try {
            this.service.delete(selected);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            LOGGER.error("Error", ex);
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }
    
    public void onDelete() {
        try {
            selected = this.service.getEntityByPkWithDetail(selected.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public String doAdd() {
        return "/protected/payroll/pay_salary_comp_form.htm?faces-redirect=true";
    }
    
    public String doEdit() {
        return "/protected/payroll/pay_salary_comp_form.htm?faces-redirect=true&execution=e" + selected.getId();
    }
    
    public String doDetail() {
        return "/protected/payroll/pay_salary_comp_detail.htm?faces-redirect=true&execution=e" + selected.getId();
    }
    
    public String doDetailForDataException() {
        return "/protected/payroll/pay_comp_ex_detail.htm?faces-redirect=true&execution=e" + selected.getId();
    }
    
    public PaySalaryComponentService getService() {
        return service;
    }

    public void setService(PaySalaryComponentService service) {
        this.service = service;
    }

    public PaySalaryComponentSearchParameter getSearchParameter() {
        return searchParameter;
    }

    public void setSearchParameter(PaySalaryComponentSearchParameter searchParameter) {
        this.searchParameter = searchParameter;
    }

    public LazyDataModel<PaySalaryComponent> getLazy() {
        if (lazy == null) {
            lazy = new PaySalaryComponentLazyDataModel(searchParameter, service);
        }
        return lazy;
    }

    public void setLazy(LazyDataModel<PaySalaryComponent> lazy) {
        this.lazy = lazy;
    }

    public PaySalaryComponent getSelected() {
        return selected;
    }

    public void setSelected(PaySalaryComponent selected) {
        this.selected = selected;
    }
    
    
}
