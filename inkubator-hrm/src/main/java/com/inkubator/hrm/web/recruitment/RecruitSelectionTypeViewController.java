/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.recruitment;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.RecruitSelectionType;
import com.inkubator.hrm.service.RecruitSelectionTypeService;
import com.inkubator.hrm.web.lazymodel.RecruitSelectionTypeLazyDataModel;
import com.inkubator.hrm.web.search.RecruitSelectionTypeSearchParameter;
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
@ManagedBean(name = "recruitSelectionTypeViewController")
@ViewScoped
public class RecruitSelectionTypeViewController extends BaseController {

    @ManagedProperty(value = "#{recruitSelectionTypeService}")
    private RecruitSelectionTypeService service;
    private RecruitSelectionTypeSearchParameter searchParameter;
    private LazyDataModel<RecruitSelectionType> lazyDataModel;
    private RecruitSelectionType selected;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        searchParameter = new RecruitSelectionTypeSearchParameter();
    }

    @PreDestroy
    private void cleanAndExit() {
        searchParameter = null;
        lazyDataModel = null;
        service = null;
        selected = null;

    }

    public void doSearch() {
        resetLazy();
    }

    public void resetLazy() {
        lazyDataModel = null;
    }

    public void onDelete() {
        try {
            selected = this.service.getEntiyByPK(selected.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public void doSelectEntity() {
        try {
            selected = this.service.getEntiyByPK(selected.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public String doDetail() {
        return "/protected/recruitment/recruit_selection_detail.htm?faces-redirect=true&execution=" + selected.getId();
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
        options.put("contentHeight", 370);
        RequestContext.getCurrentInstance().openDialog("recruitment_category_form", options, params);
    }

    public String doAdd() {
        return "/protected/recruitment/recruit_selection_form.htm?faces-redirect=true";
    }

    public String doEdit() {
        return "/protected/recruitment/recruit_selection_form.htm?faces-redirect=true&execution=" + selected.getId();
    }

    @Override
    public void onDialogReturn(SelectEvent event) {
        resetLazy();
        super.onDialogReturn(event);
    }

    public RecruitSelectionTypeService getService() {
        return service;
    }

    public void setService(RecruitSelectionTypeService service) {
        this.service = service;
    }

    public RecruitSelectionTypeSearchParameter getSearchParameter() {
        return searchParameter;
    }

    public void setSearchParameter(RecruitSelectionTypeSearchParameter searchParameter) {
        this.searchParameter = searchParameter;
    }

    public LazyDataModel<RecruitSelectionType> getLazyDataModel() {
        if (lazyDataModel == null) {
            lazyDataModel = new RecruitSelectionTypeLazyDataModel(searchParameter, service);
        }
        return lazyDataModel;
    }

    public void setLazyDataModel(LazyDataModel<RecruitSelectionType> lazyDataModel) {
        this.lazyDataModel = lazyDataModel;
    }

    public RecruitSelectionType getSelected() {
        return selected;
    }

    public void setSelected(RecruitSelectionType selected) {
        this.selected = selected;
    }

}
