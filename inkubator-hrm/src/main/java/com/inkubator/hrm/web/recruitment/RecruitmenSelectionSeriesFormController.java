/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.recruitment;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.RecruitmenSelectionSeries;
import com.inkubator.hrm.service.RecruitmenSelectionSeriesService;
import com.inkubator.hrm.web.model.RecruitmenSelectionSeriesModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Deni
 */
@ManagedBean(name = "recruitmenSelectionSeriesFormController")
@ViewScoped
public class RecruitmenSelectionSeriesFormController extends BaseController {

    @ManagedProperty(value = "#{recruitmenSelectionSeriesService}")
    private RecruitmenSelectionSeriesService recruitAdvertisementCategoryService;
    private RecruitmenSelectionSeriesModel model;
    private Boolean isUpdate;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try {
            String recruitSelectionSeriesId = FacesUtil.getRequestParameter("recruitSelectionSeriesId");
            model = new RecruitmenSelectionSeriesModel();
            isUpdate = Boolean.FALSE;
            if (StringUtils.isNotEmpty(recruitSelectionSeriesId)) {
                RecruitmenSelectionSeries recruitmenSelectionSeries = recruitAdvertisementCategoryService.getEntiyByPK(Long.parseLong(recruitSelectionSeriesId));
                if(recruitmenSelectionSeries != null){
                    model = getModelFromEntity(recruitmenSelectionSeries);
                    isUpdate = Boolean.TRUE;
                }
            }

        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    @PreDestroy
    public void cleanAndExit() {
        isUpdate = null;
        model = null;
        recruitAdvertisementCategoryService = null;
    }

    private RecruitmenSelectionSeriesModel getModelFromEntity(RecruitmenSelectionSeries entity) {
        RecruitmenSelectionSeriesModel model = new RecruitmenSelectionSeriesModel();
        model.setId(entity.getId());
        model.setName(entity.getName());
        model.setCode(entity.getCode());
        model.setDescription(entity.getDescription());
        return model;
    }

    private RecruitmenSelectionSeries getEntityFromViewModel(RecruitmenSelectionSeriesModel model) {
        RecruitmenSelectionSeries recruitAdvertisementSeries = new RecruitmenSelectionSeries();
        if (model.getId() != null) {
            recruitAdvertisementSeries.setId(model.getId());
        }
        recruitAdvertisementSeries.setName(model.getName());
        recruitAdvertisementSeries.setCode(model.getCode());
        recruitAdvertisementSeries.setDescription(model.getDescription());
        return recruitAdvertisementSeries;
    }

    public void doSave() {
        RecruitmenSelectionSeries recruitmenSelectionSeries = getEntityFromViewModel(model);
        try {
            if (isUpdate) {
                recruitAdvertisementCategoryService.update(recruitmenSelectionSeries);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                recruitAdvertisementCategoryService.save(recruitmenSelectionSeries);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch (BussinessException ex) { 
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public RecruitmenSelectionSeriesService getRecruitAdvertisementCategoryService() {
        return recruitAdvertisementCategoryService;
    }

    public void setRecruitAdvertisementCategoryService(RecruitmenSelectionSeriesService recruitAdvertisementCategoryService) {
        this.recruitAdvertisementCategoryService = recruitAdvertisementCategoryService;
    }

    public RecruitmenSelectionSeriesModel getModel() {
        return model;
    }

    public void setModel(RecruitmenSelectionSeriesModel model) {
        this.model = model;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

}
