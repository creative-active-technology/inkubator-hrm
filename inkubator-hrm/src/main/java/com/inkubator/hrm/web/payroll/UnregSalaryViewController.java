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
import com.inkubator.hrm.web.model.UnregSalaryViewModel;
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
    private LazyDataModel<UnregSalaryViewModel> lazyDataModel;
    private UnregSalary selected;
    private UnregSalaryViewModel selectedViewModel;

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
        selectedViewModel = null;
    }

    public void doSearch() {
        lazyDataModel = null;
    }

    public void doSelectEntity() {
        try {
            selected = this.unregSalaryService.getEntiyByPK(Long.valueOf(String.valueOf(selectedViewModel.getUnregSalaryId())));
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
        options.put("contentHeight", 320);
        RequestContext.getCurrentInstance().openDialog("unreg_salary_form", options, params);
    }

    public void doAdd() {
        showDialog(null);
    }

    public void doEdit() {
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> dataIsi = new ArrayList<>();
        dataIsi.add(String.valueOf(selectedViewModel.getUnregSalaryId()));
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
            selected = this.unregSalaryService.getEntiyByPK(Long.valueOf(String.valueOf(selectedViewModel.getUnregSalaryId())));
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public String doEmployeeSetting() {
        return "/protected/payroll/unreg_emp_setting_form.htm?faces-redirect=true&execution=e" + selectedViewModel.getUnregSalaryId();
    }
    
    public String doComponentSetting(){
        return "/protected/payroll/unreg_component_setting.htm?faces-redirect=true&execution=e" + selectedViewModel.getUnregSalaryId();
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

    public LazyDataModel<UnregSalaryViewModel> getLazyDataModel() {
        if (lazyDataModel == null) {
            lazyDataModel = new UnregSalaryLazyDataModel(searchParameter, unregSalaryService);
        }
        return lazyDataModel;
    }

    public void setLazyDataModel(LazyDataModel<UnregSalaryViewModel> lazyDataModel) {
        this.lazyDataModel = lazyDataModel;
    }

    public UnregSalary getSelected() {
        return selected;
    }

    public void setSelected(UnregSalary selected) {
        this.selected = selected;
    }

    public UnregSalaryViewModel getSelectedViewModel() {
        return selectedViewModel;
    }

    public void setSelectedViewModel(UnregSalaryViewModel selectedViewModel) {
        this.selectedViewModel = selectedViewModel;
    }

    
}
