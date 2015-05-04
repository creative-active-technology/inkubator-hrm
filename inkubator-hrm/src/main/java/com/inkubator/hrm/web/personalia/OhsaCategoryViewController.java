/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.personalia;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.OhsaCategory;
import com.inkubator.hrm.service.OhsaCategoryService;
import com.inkubator.hrm.web.lazymodel.OhsaCategoryLazyDataModel;
import com.inkubator.hrm.web.search.OhsaCategorySearchParameter;
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
 * @author EKA
 */
@ManagedBean(name = "ohsaCategoryViewController")
@ViewScoped
public class OhsaCategoryViewController extends BaseController {

    @ManagedProperty(value = "#{ohsaCategoryService}")
    private OhsaCategoryService ohsaCategoryService;
    private OhsaCategorySearchParameter searchParameter;
    private LazyDataModel<OhsaCategory> lazy;
    private OhsaCategory selected;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        searchParameter = new OhsaCategorySearchParameter();
    }

    @PreDestroy
    private void cleandAndExit() {
        searchParameter = null;
        lazy = null;
        ohsaCategoryService = null;
        selected = null;
    }

    public void doSearch() {
        lazy = null;
    }

    public void doSelectEntity() {
        try {
            selected = this.ohsaCategoryService.getEntiyByPK(selected.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public void doDelete() {
        try {
            this.ohsaCategoryService.delete(selected);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "global.delete_constraint",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            LOGGER.error("Error", ex);
        } catch (Exception ex) {
            LOGGER.error("error", ex);
        }
    }

    public void showDialog(Map<String, List<String>> params) {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", true);
        options.put("contentWidth", 400);
        options.put("contentHeight", 360);
        RequestContext.getCurrentInstance().openDialog("ohsa_cat_form", options, params);
    }

    public void doAdd() {
        showDialog(null);
    }

    public void doEdit() {
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> dataIsi = new ArrayList<>();
        dataIsi.add(String.valueOf(selected.getId()));
        dataToSend.put("ohsaCategoryId", dataIsi);
        showDialog(dataToSend);
    }

    @Override
    public void onDialogReturn(SelectEvent event) {
        lazy = null;
        super.onDialogReturn(event);
    }

    public void onDelete() {
        try {
            selected = this.ohsaCategoryService.getEntiyByPK(selected.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public void setOhsaCategoryService(OhsaCategoryService ohsaCategoryService) {
        this.ohsaCategoryService = ohsaCategoryService;
    }

    public OhsaCategorySearchParameter getSearchParameter() {
        return searchParameter;
    }

    public void setSearchParameter(OhsaCategorySearchParameter searchParameter) {
        this.searchParameter = searchParameter;
    }

    public LazyDataModel<OhsaCategory> getLazy() {
        if (lazy == null) {
            lazy = new OhsaCategoryLazyDataModel(searchParameter, ohsaCategoryService);
        }
        return lazy;
    }

    public void setLazy(LazyDataModel<OhsaCategory> lazy) {
        this.lazy = lazy;
    }

    public OhsaCategory getSelected() {
        return selected;
    }

    public void setSelected(OhsaCategory selected) {
        this.selected = selected;
    }

}
