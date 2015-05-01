/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.recruitment;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.RecruitMppPeriod;
import com.inkubator.hrm.service.RecruitMppPeriodService;
import com.inkubator.hrm.web.lazymodel.RecruitMppPeriodLazyDataModel;
import com.inkubator.hrm.web.search.RecruitMppPeriodSearchParameter;
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
@ManagedBean(name = "recruitMppPeriodViewController")
@ViewScoped
public class RecruitMppPeriodViewController extends BaseController {

    @ManagedProperty(value = "#{recruitMppPeriodService}")
    private RecruitMppPeriodService recruitMppPeriodService;
    private RecruitMppPeriodSearchParameter searchParameter;
    private LazyDataModel<RecruitMppPeriod> lazyDataModel;
    private RecruitMppPeriod selected;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        searchParameter = new RecruitMppPeriodSearchParameter();
    }

    @PreDestroy
    private void cleanAndExit() {
        searchParameter = null;
        lazyDataModel = null;
        recruitMppPeriodService = null;
        selected = null;

    }

    public void doSearch() {
        doResetLazy();
    }

    public void doSelectEntity() {
        try {
            selected = this.recruitMppPeriodService.getEntiyByPK(selected.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public void doDelete() {
        try {
            this.recruitMppPeriodService.delete(selected);
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
        options.put("contentHeight", 310);
        RequestContext.getCurrentInstance().openDialog("recruit_mpp_form", options, params);
    }

    public void doAdd() {
        showDialog(null);
    }

    public void doEdit() {
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> dataIsi = new ArrayList<>();
        dataIsi.add(String.valueOf(selected.getId()));
        dataToSend.put("recruitMppId", dataIsi);
        showDialog(dataToSend);
    }

    @Override
    public void onDialogReturn(SelectEvent event) {
        doResetLazy();
        super.onDialogReturn(event);

    }

    public void onDelete() {
        try {
            selected = this.recruitMppPeriodService.getEntiyByPK(selected.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }


    public void doResetLazy(){
        lazyDataModel = null;
    }

    public RecruitMppPeriodService getRecruitMppPeriodService() {
        return recruitMppPeriodService;
    }

    public void setRecruitMppPeriodService(RecruitMppPeriodService recruitMppPeriodService) {
        this.recruitMppPeriodService = recruitMppPeriodService;
    }

    public RecruitMppPeriodSearchParameter getSearchParameter() {
        return searchParameter;
    }

    public void setSearchParameter(RecruitMppPeriodSearchParameter searchParameter) {
        this.searchParameter = searchParameter;
    }

    public LazyDataModel<RecruitMppPeriod> getLazyDataModel() {
        if(lazyDataModel == null){
            lazyDataModel = new RecruitMppPeriodLazyDataModel(searchParameter, recruitMppPeriodService);
        }
        return lazyDataModel;
    }

    public void setLazyDataModel(LazyDataModel<RecruitMppPeriod> lazyDataModel) {
        this.lazyDataModel = lazyDataModel;
    }

    public RecruitMppPeriod getSelected() {
        return selected;
    }

    public void setSelected(RecruitMppPeriod selected) {
        this.selected = selected;
    }
    
    
}
