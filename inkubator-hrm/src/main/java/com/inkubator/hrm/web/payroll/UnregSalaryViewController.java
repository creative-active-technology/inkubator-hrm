/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.payroll;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.UnregSalary;
import com.inkubator.hrm.service.UnregSalaryService;
import com.inkubator.hrm.web.lazymodel.UnregSalaryLazyDataModel;
import com.inkubator.hrm.web.search.UnregSalarySearchParameter;
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
 * @author deni
 */
@ManagedBean(name = "unregSalaryViewController")
@ViewScoped
public class UnregSalaryViewController extends BaseController {

    @ManagedProperty(value = "#{unregSalaryService}")
    private UnregSalaryService unregSalaryService;
    private UnregSalarySearchParameter searchParameter;
    private LazyDataModel<UnregSalary> lazyDataModel;
    private UnregSalary selected;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        searchParameter = new UnregSalarySearchParameter();
    }

    @PreDestroy
    private void cleanAndExit() {
        searchParameter = null;
        lazyDataModel = null;
        unregSalaryService = null;
        selected = null;
    }

    public void doSearch() {
        lazyDataModel = null;
    }

    public void doSelectEntity() {
        try {
            selected = this.unregSalaryService.getEntiyByPK(selected.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public void doDelete() {
        try {
            this.unregSalaryService.delete(selected);
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

    public void showDialog(Map<String, List<String>> params) {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 500);
        options.put("contentHeight", 360);
        RequestContext.getCurrentInstance().openDialog("unreg_salary_form", options, params);
    }

    public void doAdd() {
        showDialog(null);
    }

    public void doEdit() {
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> dataIsi = new ArrayList<>();
        dataIsi.add(String.valueOf(selected.getId()));
        dataToSend.put("unregSalaryId", dataIsi);
        showDialog(dataToSend);
    }

    @Override
    public void onDialogReturn(SelectEvent event) {
        lazyDataModel = null;
        super.onDialogReturn(event);

    }

    public void onDelete() {
        try {
            selected = this.unregSalaryService.getEntiyByPK(selected.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public String doEmployeeSetting() {
        return "/protected/payroll/unreg_emp_setting_form.htm?faces-redirect=true&execution=e" + selected.getId();
    }
    
    public UnregSalaryService getUnregSalaryService() {
        return unregSalaryService;
    }

    public void setUnregSalaryService(UnregSalaryService unregSalaryService) {
        this.unregSalaryService = unregSalaryService;
    }

    public UnregSalarySearchParameter getSearchParameter() {
        return searchParameter;
    }

    public void setSearchParameter(UnregSalarySearchParameter searchParameter) {
        this.searchParameter = searchParameter;
    }

    public LazyDataModel<UnregSalary> getLazyDataModel() {
        if (lazyDataModel == null) {
            lazyDataModel = new UnregSalaryLazyDataModel(searchParameter, unregSalaryService);
        }
        return lazyDataModel;
    }

    public void setLazyDataModel(LazyDataModel<UnregSalary> lazyDataModel) {
        this.lazyDataModel = lazyDataModel;
    }

    public UnregSalary getSelected() {
        return selected;
    }

    public void setSelected(UnregSalary selected) {
        this.selected = selected;
    }

}
