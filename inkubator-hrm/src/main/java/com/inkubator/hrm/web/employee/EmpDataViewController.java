/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.employee;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.web.lazymodel.EmpDataLazyDataModel;
import com.inkubator.hrm.web.search.EmpDataSearchParameter;
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
 * @author Deni Husni FR
 */
@ManagedBean(name = "empDataViewController")
@ViewScoped
public class EmpDataViewController extends BaseController {

    private EmpDataSearchParameter empDataSearchParameter;
    private LazyDataModel<EmpData> empDataLazyDataModel;
    @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;
    private EmpData selectedEmpData;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        empDataSearchParameter = new EmpDataSearchParameter();

    }

    public String doAdd() {
        return "/protected/employee/emp_placement_form.htm?faces-redirect=true";
    }

    public EmpDataSearchParameter getEmpDataSearchParameter() {
        return empDataSearchParameter;
    }

        public void setEmpDataSearchParameter(EmpDataSearchParameter empDataSearchParameter) {
        this.empDataSearchParameter = empDataSearchParameter;
    }

    public void setEmpDataService(EmpDataService empDataService) {
        this.empDataService = empDataService;
    }

    public LazyDataModel<EmpData> getEmpDataLazyDataModel() {
        if (empDataLazyDataModel == null) {
            empDataLazyDataModel = new EmpDataLazyDataModel(empDataSearchParameter, empDataService);
        }
        return empDataLazyDataModel;
    }

    public void setEmpDataLazyDataModel(LazyDataModel<EmpData> empDataLazyDataModel) {
        this.empDataLazyDataModel = empDataLazyDataModel;
    }

    public void doSearch() {

    }

    public void onDelete() {
        try {
            selectedEmpData = this.empDataService.getEntiyByPK(selectedEmpData.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public EmpData getSelectedEmpData() {
        return selectedEmpData;
    }

    public void setSelectedEmpData(EmpData selectedEmpData) {
        this.selectedEmpData = selectedEmpData;
    }

    public void doDelete() {

        try {
            this.empDataService.delete(selectedEmpData);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
//            LOGGER.error("Error", ex);
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }

    }

    public String doDetail() {
        return "/protected/employee/emp_placement_detail.htm?faces-redirect=true&execution=e" + selectedEmpData.getId();
    }
    
    public String doDetailEmpBackground(){
        return "/protected/personalia/emp_background_detail.htm?faces-redirect=true&execution=e" + selectedEmpData.getId();
    }

    public String doEdit() {
        return "/protected/employee/emp_placement_form.htm?faces-redirect=true&execution=e" + selectedEmpData.getId();
    }

    public String doRotasi() {
        return "/protected/employee/emp_rotasi_form.htm?faces-redirect=true&execution=r" + selectedEmpData.getId() + "&from=rotasi";

    }

    @PreDestroy
    public void cleanAndExit() {
        empDataSearchParameter = null;
        empDataLazyDataModel = null;
        empDataService = null;
        selectedEmpData = null;

    }
}
