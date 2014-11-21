/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.workingtime;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.WtFingerException;
import com.inkubator.hrm.service.WtFingerExceptionService;
import com.inkubator.hrm.web.lazymodel.WtFingerExceptionLazyDataModel;
import com.inkubator.hrm.web.search.WtFingerExceptionSearchParameter;
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
@ManagedBean(name = "wtFingerExceptionViewController")
@ViewScoped
public class WtFingerExceptionViewController extends BaseController {
    @ManagedProperty(value = "#{wtFingerExceptionService}")
    private WtFingerExceptionService service;
    private WtFingerExceptionSearchParameter searchParameter;
    private LazyDataModel<WtFingerException> lazyDataModel;
    private WtFingerException selected;
    
    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        searchParameter = new WtFingerExceptionSearchParameter();
    }
    
    @PreDestroy
    private void cleanAndExit() {
        searchParameter = null;
        lazyDataModel = null;
        service = null;
        selected = null;
    }
    
    public String doAdd() {
        return "/protected/working_time/wt_finger_exception_form.htm?faces-redirect=true";
    }
    
     public void doUpdate() {
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> values = new ArrayList<>();
        values.add(String.valueOf(selected.getId()));
        dataToSend.put("wtFingerExceptionId", values);
        showDialog(dataToSend);
    }
     
    private void showDialog(Map<String, List<String>> params) {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 420);
        options.put("contentHeight", 300);
        RequestContext.getCurrentInstance().openDialog("wt_finger_exception_edit", options, params);
    }
    
    @Override
    public void onDialogReturn(SelectEvent event) {
       lazyDataModel = null;
       super.onDialogReturn(event);

    }
     
    public void doSearch() {
        lazyDataModel = null;
    }
    
    public void doSelectEntityWithDetail() {
        try {
            selected = this.service.getEntityByParamWithDetail(selected.getId());
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
            selected = this.service.getEntiyByPK(selected.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public WtFingerExceptionService getService() {
        return service;
    }

    public void setService(WtFingerExceptionService service) {
        this.service = service;
    }

    public WtFingerExceptionSearchParameter getSearchParameter() {
        return searchParameter;
    }

    public void setSearchParameter(WtFingerExceptionSearchParameter searchParameter) {
        this.searchParameter = searchParameter;
    }

    public LazyDataModel<WtFingerException> getLazyDataModel() {
        if (lazyDataModel == null) {
            lazyDataModel = new WtFingerExceptionLazyDataModel(searchParameter, service);
        }
        return lazyDataModel;
    }

    public void setLazyDataModel(LazyDataModel<WtFingerException> lazyDataModel) {
        this.lazyDataModel = lazyDataModel;
    }

    public WtFingerException getSelected() {
        return selected;
    }

    public void setSelected(WtFingerException selected) {
        this.selected = selected;
    }
    
    
}
