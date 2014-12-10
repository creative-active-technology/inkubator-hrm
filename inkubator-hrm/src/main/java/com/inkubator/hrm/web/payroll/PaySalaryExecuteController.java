/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.payroll;

import com.inkubator.hrm.entity.PayTempKalkulasi;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.PayTempKalkulasiService;
import com.inkubator.hrm.web.lazymodel.PaySalaryExecuteLazyDataModel;
import com.inkubator.hrm.web.model.PayTempKalkulasiModel;
import com.inkubator.hrm.web.search.PayTempKalkulasiSearchParameter;
import com.inkubator.webcore.controller.BaseController;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author Deni
 */
@ManagedBean(name = "paySalaryExecuteController")
@ViewScoped
public class PaySalaryExecuteController extends BaseController {

    @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;
    @ManagedProperty(value = "#{payTempKalkulasiService}")
    private PayTempKalkulasiService payTempKalkulasiService;
    private PayTempKalkulasiSearchParameter searchParameter;
    private LazyDataModel<PayTempKalkulasiModel> lazyDataModel;
    private PayTempKalkulasi selected;
    private String parameter;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        searchParameter = new PayTempKalkulasiSearchParameter();

    }

    @PreDestroy
    public void cleanAndExit() {
        selected = null;
        lazyDataModel = null;
        searchParameter = null;
        payTempKalkulasiService = null;
        empDataService = null;
        parameter = null;
    }

    public void doSearch() {
        System.out.println(searchParameter.getPaySalaryComponent() + " hohohohooho" + parameter);
        lazyDataModel = null;
    }
    
    public void doSelectEntity() {
        try {
            selected = this.payTempKalkulasiService.getEntityByPkWithDetail(selected.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }
    
    public void calculatePayRoll() {
        try {
            payTempKalkulasiService.calculatePayRoll();
        } catch (Exception ex) {
            LOGGER.error(ex, ex);
        }
    }

    public String doDetail(){
        return "/protected/payroll/salary_execution_detail.htm?faces-redirect=true&execution=e" + selected.getId();
    }
    
    public void setEmpDataService(EmpDataService empDataService) {
        this.empDataService = empDataService;
    }

    public void setPayTempKalkulasiService(PayTempKalkulasiService payTempKalkulasiService) {
        this.payTempKalkulasiService = payTempKalkulasiService;
    }

    public PayTempKalkulasiSearchParameter getSearchParameter() {
        return searchParameter;
    }

    public void setSearchParameter(PayTempKalkulasiSearchParameter searchParameter) {
        this.searchParameter = searchParameter;
    }

    public LazyDataModel<PayTempKalkulasiModel> getLazyDataModel() {
        if(lazyDataModel == null){
            lazyDataModel = new PaySalaryExecuteLazyDataModel(parameter, payTempKalkulasiService);
        }
        return lazyDataModel;
    }

    public void setLazyDataModel(LazyDataModel<PayTempKalkulasiModel> lazyDataModel) {
        this.lazyDataModel = lazyDataModel;
    }

    public PayTempKalkulasi getSelected() {
        return selected;
    }

    public void setSelected(PayTempKalkulasi selected) {
        this.selected = selected;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    
}
