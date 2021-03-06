/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.workingtime;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.WtOverTime;
import com.inkubator.hrm.service.WtOverTimeService;
import com.inkubator.hrm.web.lazymodel.WtOverTimeLazyModel;
import com.inkubator.hrm.web.search.WtOverTimeSearchParameter;
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
@ManagedBean(name = "overTimeViewController")
@ViewScoped
public class OverTimeViewController extends BaseController {

    private WtOverTimeSearchParameter wtOverTimeSearchParameter;
    private LazyDataModel<WtOverTime> wtOverTimesLazyDataModel;
    @ManagedProperty(value = "#{wtOverTimeService}")
    private WtOverTimeService wtOverTimeService;
    private WtOverTime selectedWtOverTime;

    public void setWtOverTimeService(WtOverTimeService wtOverTimeService) {
        this.wtOverTimeService = wtOverTimeService;
    }

    public WtOverTimeSearchParameter getWtOverTimeSearchParameter() {
        return wtOverTimeSearchParameter;
    }

    public void setWtOverTimeSearchParameter(WtOverTimeSearchParameter wtOverTimeSearchParameter) {
        this.wtOverTimeSearchParameter = wtOverTimeSearchParameter;
    }

    public LazyDataModel<WtOverTime> getWtOverTimesLazyDataModel() {
        if (wtOverTimesLazyDataModel == null) {
            wtOverTimesLazyDataModel = new WtOverTimeLazyModel(wtOverTimeSearchParameter, wtOverTimeService);
        }
        return wtOverTimesLazyDataModel;
    }

    public void setWtOverTimesLazyDataModel(LazyDataModel<WtOverTime> wtOverTimesLazyDataModel) {
        this.wtOverTimesLazyDataModel = wtOverTimesLazyDataModel;
    }

    public WtOverTime getSelectedWtOverTime() {
        return selectedWtOverTime;
    }

    public void setSelectedWtOverTime(WtOverTime selectedWtOverTime) {
        this.selectedWtOverTime = selectedWtOverTime;
    }

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        wtOverTimeSearchParameter = new WtOverTimeSearchParameter();

    }

    public void doSearch() {
        wtOverTimesLazyDataModel = null;
    }

    public String doDetail() {
        return "/protected/working_time/over_time_detail.htm?faces-redirect=true&execution=e" + selectedWtOverTime.getId();
    }

    public void doDelete() {
        try {
            this.wtOverTimeService.delete(selectedWtOverTime);
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

    public String doAdd() {
        return "/protected/working_time/over_time_form.htm?faces-redirect=true";
    }

    public String doUpdate() {
        return "/protected/working_time/over_time_form.htm?faces-redirect=true&execution=e" + selectedWtOverTime.getId();
    }
    
    public void doEdit() {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 400);
        options.put("contentHeight", 555);
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> dataIsi = new ArrayList<>();
        dataIsi.add(String.valueOf(selectedWtOverTime.getId()));
        dataToSend.put("param", dataIsi);
        RequestContext.getCurrentInstance().openDialog("over_time_form", options, dataToSend);
    }

    @Override
    public void onDialogReturn(SelectEvent event) {
        wtOverTimesLazyDataModel = null;
        super.onDialogReturn(event);
    }

    public void onDelete() {
        try {
            selectedWtOverTime = wtOverTimeService.getEntiyByPK(selectedWtOverTime.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    @PreDestroy
    private void cleanAndExit() {
        wtOverTimeSearchParameter = null;
        wtOverTimesLazyDataModel = null;
        wtOverTimeService = null;
        selectedWtOverTime = null;
    }
}
