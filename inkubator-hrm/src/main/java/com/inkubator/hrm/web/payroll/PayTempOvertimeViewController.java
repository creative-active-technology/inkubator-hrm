/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.payroll;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.PayTempOvertime;
import com.inkubator.hrm.service.PayTempOvertimeService;
import com.inkubator.hrm.web.lazymodel.PayTempOvertimeLazyDataModel;
import com.inkubator.hrm.web.search.PayTempOvertimeSearchParameter;
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
@ManagedBean(name = "payTempOvertimeViewController")
@ViewScoped
public class PayTempOvertimeViewController extends BaseController {

    @ManagedProperty(value = "#{payTempOvertimeService}")
    private PayTempOvertimeService service;
    private PayTempOvertimeSearchParameter searchParameter;
    private LazyDataModel<PayTempOvertime> lazyDataModel;
    private PayTempOvertime selected;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        searchParameter = new PayTempOvertimeSearchParameter();
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

    public void showDialog(Map<String, List<String>> params) {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 400);
        options.put("contentHeight", 360);
        RequestContext.getCurrentInstance().openDialog("pay_temp_overtime_form", options, params);
    }

    public void doAdd() {
        showDialog(null);
    }

    public void doEdit() {
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> dataIsi = new ArrayList<>();
        dataIsi.add(String.valueOf(selected.getId()));
        dataToSend.put("employeeId", dataIsi);
        showDialog(dataToSend);
    }

    @Override
    public void onDialogReturn(SelectEvent event) {
        lazyDataModel = null;
        super.onDialogReturn(event);

    }

    public void onDelete() {
        try {
            selected = this.service.getEntiyByPK(selected.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public PayTempOvertimeService getService() {
        return service;
    }

    public void setService(PayTempOvertimeService service) {
        this.service = service;
    }

    public PayTempOvertimeSearchParameter getSearchParameter() {
        return searchParameter;
    }

    public void setSearchParameter(PayTempOvertimeSearchParameter searchParameter) {
        this.searchParameter = searchParameter;
    }

    public LazyDataModel<PayTempOvertime> getLazyDataModel() {
        if (lazyDataModel == null) {
            lazyDataModel = new PayTempOvertimeLazyDataModel(searchParameter, service);
        }
        return lazyDataModel;
    }

    public void setLazyDataModel(LazyDataModel<PayTempOvertime> lazyDataModel) {
        this.lazyDataModel = lazyDataModel;
    }

    public PayTempOvertime getSelected() {
        return selected;
    }

    public void setSelected(PayTempOvertime selected) {
        this.selected = selected;
    }

}
