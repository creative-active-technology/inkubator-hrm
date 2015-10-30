/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.recruitment;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.RecruitSelectionType;
import com.inkubator.hrm.entity.RecruitmenSelectionSeries;
import com.inkubator.hrm.entity.RecruitmenSelectionSeriesDetail;
import com.inkubator.hrm.entity.RecruitmenSelectionSeriesDetailId;
import com.inkubator.hrm.entity.SystemLetterReference;
import com.inkubator.hrm.service.RecruitSelectionTypeService;
import com.inkubator.hrm.service.RecruitmenSelectionSeriesDetailService;
import com.inkubator.hrm.service.SystemLetterReferenceService;
import com.inkubator.hrm.web.model.RecruitmenSelectionSeriesDetailModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
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
@ManagedBean(name = "recruitmenSelectionSeriesDetailFormController")
@ViewScoped
public class RecruitmenSelectionSeriesDetailFormController extends BaseController {

    @ManagedProperty(value = "#{recruitmenSelectionSeriesDetailService}")
    private RecruitmenSelectionSeriesDetailService recruitmenSelectionSeriesDetailService;
    @ManagedProperty(value = "#{systemLetterReferenceService}")
    private SystemLetterReferenceService systemLetterReferenceService;
    @ManagedProperty(value = "#{recruitSelectionTypeService}")
    private RecruitSelectionTypeService recruitSelectionTypeService;
    private RecruitmenSelectionSeriesDetailModel model;
    private Boolean isUpdate;
    private Map<String, Long> dropDownSelectionType = new TreeMap<String, Long>();
    private Map<String, Long> dropDownAcceptAndRejectLetter = new TreeMap<String, Long>();

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try {
            String recruitSelectionSeriesId = FacesUtil.getRequestParameter("recruitSelectionSeriesId");
            String recruitSelectedTypeId = FacesUtil.getRequestParameter("recruitSelectedTypeId");
            model = new RecruitmenSelectionSeriesDetailModel();
            isUpdate = Boolean.FALSE;
            if (StringUtils.isNotEmpty(recruitSelectedTypeId)) {
                RecruitmenSelectionSeriesDetail recruitmenSelectionSeriesDetail = recruitmenSelectionSeriesDetailService.getEntityByPk(new RecruitmenSelectionSeriesDetailId(Long.valueOf(recruitSelectionSeriesId), Long.valueOf(recruitSelectedTypeId)));
                if(recruitmenSelectionSeriesDetail != null){
                    model = getModelFromEntity(recruitmenSelectionSeriesDetail);
                    isUpdate = Boolean.TRUE;
                }
            }
            //set recruitment series id
            model.setRecruitmenSelectionSeriesId(Long.valueOf(recruitSelectionSeriesId));
            //drop down
            addDropDown();
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    @PreDestroy
    public void cleanAndExit() {
        isUpdate = null;
        model = null;
        recruitmenSelectionSeriesDetailService = null;
        dropDownAcceptAndRejectLetter = null;
        dropDownSelectionType = null;
        recruitSelectionTypeService = null;
        systemLetterReferenceService = null;
    }

    public void addDropDown() throws Exception {
        //selection type
        List<RecruitSelectionType> listSelectionType = recruitSelectionTypeService.getAllData();
        for (RecruitSelectionType recruitSelectionType : listSelectionType) {
            dropDownSelectionType.put(recruitSelectionType.getName(), recruitSelectionType.getId());
        }

        //accept and reject dropdown
        List<SystemLetterReference> listSystemLetterReference = systemLetterReferenceService.getAllData();
        for (SystemLetterReference systemLetterReference : listSystemLetterReference) {
            dropDownAcceptAndRejectLetter.put(systemLetterReference.getName(), systemLetterReference.getId());
        }
    }

    private RecruitmenSelectionSeriesDetailModel getModelFromEntity(RecruitmenSelectionSeriesDetail entity) {
        RecruitmenSelectionSeriesDetailModel model = new RecruitmenSelectionSeriesDetailModel();
        model.setRecruitmenSelectionSeriesId(entity.getRecruitmenSelectionSeries().getId());
        model.setRecruitSelectionTypeId(entity.getRecruitSelectionType().getId());
//        model.setSystemLetterReferenceByAcceptLetterId(entity.getSystemLetterReferenceByAcceptLetterId().getId());
//        model.setSystemLetterReferenceByRejectLetterId(entity.getSystemLetterReferenceByRejectLetterId().getId());
        model.setNote(entity.getNote());
        return model;
    }

    private RecruitmenSelectionSeriesDetail getEntityFromViewModel(RecruitmenSelectionSeriesDetailModel model) {
        RecruitmenSelectionSeriesDetail recruitmenSelectionSeriesDetail = new RecruitmenSelectionSeriesDetail();
        recruitmenSelectionSeriesDetail.setRecruitmenSelectionSeries(new RecruitmenSelectionSeries(model.getRecruitmenSelectionSeriesId()));
        recruitmenSelectionSeriesDetail.setRecruitSelectionType(new RecruitSelectionType(model.getRecruitSelectionTypeId()));
//        recruitmenSelectionSeriesDetail.setSystemLetterReferenceByAcceptLetterId(new SystemLetterReference(model.getSystemLetterReferenceByAcceptLetterId()));
//        recruitmenSelectionSeriesDetail.setSystemLetterReferenceByRejectLetterId(new SystemLetterReference(model.getSystemLetterReferenceByRejectLetterId()));
        recruitmenSelectionSeriesDetail.setNote(model.getNote());
        return recruitmenSelectionSeriesDetail;
    }

    public void doSave() {
        RecruitmenSelectionSeriesDetail recruitmenSelectionSeriesDetail = getEntityFromViewModel(model);
        try {
            if (isUpdate) {
                recruitmenSelectionSeriesDetailService.update(recruitmenSelectionSeriesDetail);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                recruitmenSelectionSeriesDetailService.save(recruitmenSelectionSeriesDetail);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch (BussinessException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public String doBack(){
        return "/protected/recruitment/recruit_selection_series_view.htm?faces-redirect=true";
    }
    
    public RecruitmenSelectionSeriesDetailService getRecruitmenSelectionSeriesDetailService() {
        return recruitmenSelectionSeriesDetailService;
    }

    public void setRecruitmenSelectionSeriesDetailService(RecruitmenSelectionSeriesDetailService recruitmenSelectionSeriesDetailService) {
        this.recruitmenSelectionSeriesDetailService = recruitmenSelectionSeriesDetailService;
    }

    public RecruitmenSelectionSeriesDetailModel getModel() {
        return model;
    }

    public void setModel(RecruitmenSelectionSeriesDetailModel model) {
        this.model = model;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public SystemLetterReferenceService getSystemLetterReferenceService() {
        return systemLetterReferenceService;
    }

    public void setSystemLetterReferenceService(SystemLetterReferenceService systemLetterReferenceService) {
        this.systemLetterReferenceService = systemLetterReferenceService;
    }

    public RecruitSelectionTypeService getRecruitSelectionTypeService() {
        return recruitSelectionTypeService;
    }

    public void setRecruitSelectionTypeService(RecruitSelectionTypeService recruitSelectionTypeService) {
        this.recruitSelectionTypeService = recruitSelectionTypeService;
    }

    public Map<String, Long> getDropDownSelectionType() {
        return dropDownSelectionType;
    }

    public void setDropDownSelectionType(Map<String, Long> dropDownSelectionType) {
        this.dropDownSelectionType = dropDownSelectionType;
    }

    public Map<String, Long> getDropDownAcceptAndRejectLetter() {
        return dropDownAcceptAndRejectLetter;
    }

    public void setDropDownAcceptAndRejectLetter(Map<String, Long> dropDownAcceptAndRejectLetter) {
        this.dropDownAcceptAndRejectLetter = dropDownAcceptAndRejectLetter;
    }

    
}
