/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.recruitment;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.RecruitmenSelectionSeries;
import com.inkubator.hrm.service.RecruitmenSelectionSeriesService;
import com.inkubator.hrm.web.lazymodel.RecruitmenSelectionSeriesLazyDataModel;
import com.inkubator.hrm.web.search.RecruitmenSelectionSeriesSearchParameter;
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
@ManagedBean(name = "recruitmenSelectionSeriesViewController")
@ViewScoped
public class RecruitmenSelectionSeriesViewController extends BaseController{
    @ManagedProperty(value = "#{recruitmenSelectionSeriesService}")
    private RecruitmenSelectionSeriesService service;
    private RecruitmenSelectionSeriesSearchParameter searchParameter;
    private LazyDataModel<RecruitmenSelectionSeries> lazyDataModel;
    private RecruitmenSelectionSeries selected;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        searchParameter = new RecruitmenSelectionSeriesSearchParameter();
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
        return "/protected/recruitment/recruit_selection_series_detail.htm?faces-redirect=true&execution=" + selected.getId();
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
        RequestContext.getCurrentInstance().openDialog("recruit_selection_series_form", options, params);
    }

    public void doAdd() {
        showDialog(null);
    }

    public void doEdit() {
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> dataIsi = new ArrayList<>();
        dataIsi.add(String.valueOf(selected.getId()));
        dataToSend.put("recruitSelectionSeriesId", dataIsi);
        showDialog(dataToSend);
    }

    @Override
    public void onDialogReturn(SelectEvent event) {
        resetLazy();
        super.onDialogReturn(event);
    }

    public RecruitmenSelectionSeriesService getService() {
        return service;
    }

    public void setService(RecruitmenSelectionSeriesService service) {
        this.service = service;
    }

    public RecruitmenSelectionSeriesSearchParameter getSearchParameter() {
        return searchParameter;
    }

    public void setSearchParameter(RecruitmenSelectionSeriesSearchParameter searchParameter) {
        this.searchParameter = searchParameter;
    }

    public LazyDataModel<RecruitmenSelectionSeries> getLazyDataModel() {
        if (lazyDataModel == null) {
            lazyDataModel = new RecruitmenSelectionSeriesLazyDataModel(searchParameter, service);
        }
        return lazyDataModel;
    }

    public void setLazyDataModel(LazyDataModel<RecruitmenSelectionSeries> lazyDataModel) {
        this.lazyDataModel = lazyDataModel;
    }

    public RecruitmenSelectionSeries getSelected() {
        return selected;
    }

    public void setSelected(RecruitmenSelectionSeries selected) {
        this.selected = selected;
    }
    
    
}
