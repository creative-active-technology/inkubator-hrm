/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.loan;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.LoanNewSchemaListOfEmp;
import com.inkubator.hrm.service.LoanNewSchemaListOfEmpService;
import com.inkubator.hrm.web.lazymodel.LoanNewSchemaListOfEmpLazyDataModel;
import com.inkubator.hrm.web.model.LoanNewSchemaListOfEmpModel;
import com.inkubator.hrm.web.model.LoanNewSchemaListOfEmpViewModel;
import com.inkubator.hrm.web.search.LoanNewSchemaListOfEmpSearchParameter;
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
 * @author Deni
 */
@ManagedBean(name = "loanNewSchemaListOfEmpViewController")
@ViewScoped
public class LoanNewSchemaListOfEmpViewController extends BaseController {

    @ManagedProperty(value = "#{loanNewSchemaListOfEmpService}")
    private LoanNewSchemaListOfEmpService service;
    private LoanNewSchemaListOfEmpSearchParameter searchParameter;
    private LazyDataModel<LoanNewSchemaListOfEmpViewModel> lazyDataModel;
    private LoanNewSchemaListOfEmp selected;
    private LoanNewSchemaListOfEmpViewModel selectedListOfEmp;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        searchParameter = new LoanNewSchemaListOfEmpSearchParameter();
    }

    @PreDestroy
    private void cleanAndExit() {
        searchParameter = null;
        lazyDataModel = null;
        service = null;
        selected = null;

    }

    public void doSearch() {
        lazyDataModel = null;
    }

    public void doSelectEntity() {
//        try {
//            selected = this.service.getEntiyByPK(selected.getId());
//        } catch (Exception ex) {
//            LOGGER.error("Error", ex);
//        }
    }

    public String doDetail() {
        return "/protected/personalia/loan_emp_detail.htm?faces-redirect=true&execution=e" + selectedListOfEmp.getIdEmp();
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

    public void showDialog(Map<String, List<String>> params) {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 500);
        options.put("contentHeight", 460);
        RequestContext.getCurrentInstance().openDialog("loan_emp_form", options, params);
    }

    public void doAdd() {
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> dataIsi = new ArrayList<>();
        dataIsi.add(String.valueOf(selectedListOfEmp.getIdEmp()));
        dataToSend.put("empDataId", dataIsi);
        showDialog(dataToSend);
    }

    public void doEdit() {
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> dataIsi = new ArrayList<>();
        dataIsi.add(String.valueOf(selectedListOfEmp.getIdEmp()));
        dataToSend.put("empDataId", dataIsi);
        showDialog(dataToSend);
    }

    @Override
    public void onDialogReturn(SelectEvent event) {
        lazyDataModel = null;
        super.onDialogReturn(event);

    }

    public void onDelete() {
//        try {
//            selected = this.service.getEntiyByPK(selected.getId());
//        } catch (Exception ex) {
//            LOGGER.error("Error", ex);
//        }
    }

    public LoanNewSchemaListOfEmpService getService() {
        return service;
    }

    public void setService(LoanNewSchemaListOfEmpService service) {
        this.service = service;
    }

    public LoanNewSchemaListOfEmpSearchParameter getSearchParameter() {
        return searchParameter;
    }

    public void setSearchParameter(LoanNewSchemaListOfEmpSearchParameter searchParameter) {
        this.searchParameter = searchParameter;
    }

    public LazyDataModel<LoanNewSchemaListOfEmpViewModel> getLazyDataModel() {
        if (lazyDataModel == null) {
            lazyDataModel = new LoanNewSchemaListOfEmpLazyDataModel(searchParameter, service);
        }
        return lazyDataModel;
    }

    public void setLazyDataModel(LazyDataModel<LoanNewSchemaListOfEmpViewModel> lazyDataModel) {
        this.lazyDataModel = lazyDataModel;
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

}
