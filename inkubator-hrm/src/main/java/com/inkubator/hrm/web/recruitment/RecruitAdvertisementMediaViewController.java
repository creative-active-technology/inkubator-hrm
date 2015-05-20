/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.recruitment;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.RecruitAdvertisementMedia;
import com.inkubator.hrm.service.RecruitAdvertisementMediaService;
import com.inkubator.hrm.web.lazymodel.RecruitAdvertisementMediaLazyDataModel;
import com.inkubator.hrm.web.search.RecruitAdvertisementMediaSearchParameter;
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
@ManagedBean(name = "recruitAdvertisementMediaViewController")
@ViewScoped
public class RecruitAdvertisementMediaViewController extends BaseController {

    @ManagedProperty(value = "#{recruitAdvertisementMediaService}")
    private RecruitAdvertisementMediaService service;
    private RecruitAdvertisementMediaSearchParameter searchParameter;
    private LazyDataModel<RecruitAdvertisementMedia> lazyDataModel;
    private RecruitAdvertisementMedia selected;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        searchParameter = new RecruitAdvertisementMediaSearchParameter();
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

    public void doSelectEntity() {
        try {
            selected = this.service.getEntityByPkWithDetail(selected.getId());
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
        options.put("contentWidth", 500);
        options.put("contentHeight", 530);
        RequestContext.getCurrentInstance().openDialog("recruitment_media_form", options, params);
    }

    public void doAdd() {
        showDialog(null);
    }

    public void doEdit() {
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> dataIsi = new ArrayList<>();
        dataIsi.add(String.valueOf(selected.getId()));
        dataToSend.put("recruitmentId", dataIsi);
        showDialog(dataToSend);
    }

    @Override
    public void onDialogReturn(SelectEvent event) {
        resetLazy();
        super.onDialogReturn(event);

    }

    public void resetLazy(){
        lazyDataModel = null;
    }
    
    public void onDelete() {
        try {
            selected = this.service.getEntiyByPK(selected.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public RecruitAdvertisementMediaService getService() {
        return service;
    }

    public void setService(RecruitAdvertisementMediaService service) {
        this.service = service;
    }

    public RecruitAdvertisementMediaSearchParameter getSearchParameter() {
        return searchParameter;
    }

    public void setSearchParameter(RecruitAdvertisementMediaSearchParameter searchParameter) {
        this.searchParameter = searchParameter;
    }

    public LazyDataModel<RecruitAdvertisementMedia> getLazyDataModel() {
        if(lazyDataModel == null){
            lazyDataModel = new RecruitAdvertisementMediaLazyDataModel(searchParameter, service);
        }
        return lazyDataModel;
    }

    public void setLazyDataModel(LazyDataModel<RecruitAdvertisementMedia> lazyDataModel) {
        this.lazyDataModel = lazyDataModel;
    }

    public RecruitAdvertisementMedia getSelected() {
        return selected;
    }

    public void setSelected(RecruitAdvertisementMedia selected) {
        this.selected = selected;
    }
    
    
}
