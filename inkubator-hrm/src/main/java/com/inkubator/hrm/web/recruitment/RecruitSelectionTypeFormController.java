/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.recruitment;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.RecruitDynamicField;
import com.inkubator.hrm.entity.RecruitSelectionType;
import com.inkubator.hrm.entity.RecruitSelectionTypeField;
import com.inkubator.hrm.service.RecruitDynamicFieldService;
import com.inkubator.hrm.service.RecruitSelectionTypeFieldService;
import com.inkubator.hrm.service.RecruitSelectionTypeService;
import com.inkubator.hrm.web.model.RecruitSelectionTypeModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Deni
 */
@ManagedBean(name = "recruitmentTypeFormController")
@ViewScoped
public class RecruitSelectionTypeFormController extends BaseController {

    @ManagedProperty(value = "#{recruitSelectionTypeService}")
    private RecruitSelectionTypeService recruitmentTypeService;
    @ManagedProperty(value = "#{recruitDynamicFieldService}")
    private RecruitDynamicFieldService recruitDynamicFieldService;
    @ManagedProperty(value = "#{recruitSelectionTypeFieldService}")
    private RecruitSelectionTypeFieldService recruitSelectionTypeFieldService;
    private RecruitSelectionTypeModel model;
    private Boolean isUpdate;
    private List<RecruitDynamicField> listRecruitDynamicField;
    private List<RecruitDynamicField> selectedRecruitDynamicField;
    private int totalData;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try {
            String recruitSelectionTypeId = FacesUtil.getRequestParameter("execution");
            model = new RecruitSelectionTypeModel();
            isUpdate = Boolean.FALSE;
            listRecruitDynamicField = recruitDynamicFieldService.getAllData();
            if (StringUtils.isNotEmpty(recruitSelectionTypeId)) {
                RecruitSelectionType recruitSelectionType = recruitmentTypeService.getEntiyByPK(Long.parseLong(recruitSelectionTypeId));
                if (recruitSelectionType != null) {
                    model = getModelFromEntity(recruitSelectionType);
                    List<RecruitSelectionTypeField> listFieldFromSelectionTypeField = recruitSelectionTypeFieldService.getEntityByRecruitSelectionTypeId(Long.parseLong(recruitSelectionTypeId));
                    selectedRecruitDynamicField = new ArrayList<RecruitDynamicField>();
                    RecruitDynamicField recruitDynamicField;
                    //add selected data in RecruitSelectionTypeField and add to list selectedRecruitDynamicField
                    for (RecruitSelectionTypeField selectionTypeField : listFieldFromSelectionTypeField) {
                        recruitDynamicField = recruitDynamicFieldService.getEntiyByPK(selectionTypeField.getRecruitDynamicField().getId());
                        selectedRecruitDynamicField.add(recruitDynamicField);
                    }
                    
                    //remove all list from listRecruitDynamicField by selectedRecruitDynamicField to prevent duplicate data
                    listRecruitDynamicField.removeAll(selectedRecruitDynamicField);
                    
                    //add selected with fieldNewLabel property
                    for (RecruitSelectionTypeField selectionTypeField : listFieldFromSelectionTypeField) {
                        recruitDynamicField = recruitDynamicFieldService.getEntiyByPK(selectionTypeField.getRecruitDynamicField().getId());
                        recruitDynamicField.setFieldNewLabel(selectionTypeField.getFieldNewLabel());
                        listRecruitDynamicField.add(recruitDynamicField);
                    }
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
        recruitmentTypeService = null;
        recruitDynamicFieldService = null;
        selectedRecruitDynamicField = null;
        recruitSelectionTypeFieldService = null;
    }

    private RecruitSelectionTypeModel getModelFromEntity(RecruitSelectionType entity) {
        RecruitSelectionTypeModel model = new RecruitSelectionTypeModel();
        model.setId(entity.getId());
        model.setName(entity.getName());
        model.setCode(entity.getCode());
        model.setCost(entity.getCost());
        model.setUseLibrary(entity.getUseLibrary());
        return model;
    }

    private RecruitSelectionType getEntityFromViewModel(RecruitSelectionTypeModel model) {
        RecruitSelectionType recruitSelectionType = new RecruitSelectionType();
        if (model.getId() != null) {
            recruitSelectionType.setId(model.getId());
        }
        recruitSelectionType.setName(model.getName());
        recruitSelectionType.setCode(model.getCode());
        recruitSelectionType.setUseLibrary(model.getUseLibrary());
        recruitSelectionType.setCost(model.getCost());
        return recruitSelectionType;

    }

    public String doSave() {

        RecruitSelectionType recruitSelectionType = getEntityFromViewModel(model);
        try {
            if (isUpdate) {
                recruitmentTypeService.update(recruitSelectionType, selectedRecruitDynamicField);
                MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.update_successfully",
                        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            } else {
                recruitmentTypeService.save(recruitSelectionType, selectedRecruitDynamicField);
                MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully",
                        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            }
            cleanAndExit();
            return "/protected/recruitment/recruit_selection_view.htm?faces-redirect=true";
        } catch (BussinessException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
        return null;
    }

    public String doBack() {
        return "/protected/recruitment/recruit_selection_view.htm?faces-redirect=true";
    }
    
    public void doReset() {
        model.setCode(null);
        model.setName(null);
        model.setUseLibrary(null);
        model.setCost(null);
    }
    public RecruitSelectionTypeService getRecruitmentTypeService() {
        return recruitmentTypeService;
    }

    public void setRecruitmentTypeService(RecruitSelectionTypeService recruitmentTypeService) {
        this.recruitmentTypeService = recruitmentTypeService;
    }

    public RecruitSelectionTypeModel getModel() {
        return model;
    }

    public void setModel(RecruitSelectionTypeModel model) {
        this.model = model;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public RecruitDynamicFieldService getRecruitDynamicFieldService() {
        return recruitDynamicFieldService;
    }

    public void setRecruitDynamicFieldService(RecruitDynamicFieldService recruitDynamicFieldService) {
        this.recruitDynamicFieldService = recruitDynamicFieldService;
    }

    public List<RecruitDynamicField> getListRecruitDynamicField() {
        return listRecruitDynamicField;
    }

    public void setListRecruitDynamicField(List<RecruitDynamicField> listRecruitDynamicField) {
        this.listRecruitDynamicField = listRecruitDynamicField;
    }

    public int getTotalData() {
        return totalData;
    }

    public void setTotalData(int totalData) {
        this.totalData = totalData;
    }

    public List<RecruitDynamicField> getSelectedRecruitDynamicField() {
        return selectedRecruitDynamicField;
    }

    public void setSelectedRecruitDynamicField(List<RecruitDynamicField> selectedRecruitDynamicField) {
        this.selectedRecruitDynamicField = selectedRecruitDynamicField;
    }

    public RecruitSelectionTypeFieldService getRecruitSelectionTypeFieldService() {
        return recruitSelectionTypeFieldService;
    }

    public void setRecruitSelectionTypeFieldService(RecruitSelectionTypeFieldService recruitSelectionTypeFieldService) {
        this.recruitSelectionTypeFieldService = recruitSelectionTypeFieldService;
    }

}
