/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.recruitment;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.RecruitAdvertisementCategory;
import com.inkubator.hrm.entity.RecruitAdvertisementMedia;
import com.inkubator.hrm.service.RecruitAdvertisementCategoryService;
import com.inkubator.hrm.service.RecruitAdvertisementMediaService;
import com.inkubator.hrm.web.model.RecruitAdvertisementMediaModel;
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
@ManagedBean(name = "recruitAdvertisementMediaFormController")
@ViewScoped
public class RecruitAdvertisementMediaFormController extends BaseController {
    
    @ManagedProperty(value = "#{recruitAdvertisementMediaService}")
    private RecruitAdvertisementMediaService recruitAdvertisementMediaService;
    @ManagedProperty(value = "#{recruitAdvertisementCategoryService}")
    private RecruitAdvertisementCategoryService recruitAdvertisementCategoryService;
    private RecruitAdvertisementMedia selected;
    private RecruitAdvertisementMediaModel model;
    private Boolean isUpdate;
    private List<RecruitAdvertisementCategory> listAdvertisementCategory;
    private Map<String, Long> dropDownAdvCategory = new TreeMap<String, Long>();
    
    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try{
            String recruitAdvertisementMediaId = FacesUtil.getRequestParameter("recruitmentId");
            model = new RecruitAdvertisementMediaModel();
            isUpdate = Boolean.FALSE;
            if (StringUtils.isNotEmpty(recruitAdvertisementMediaId)) {
                RecruitAdvertisementMedia recruitAdvertisementMedia = recruitAdvertisementMediaService.getEntiyByPK(Long.parseLong(recruitAdvertisementMediaId));
                if(recruitAdvertisementMedia != null){
                    model = getModelFromEntity(recruitAdvertisementMedia);
                    isUpdate = Boolean.TRUE;
                }
            }
            listAdvertisementCategory = recruitAdvertisementCategoryService.getAllData();
            for (RecruitAdvertisementCategory advCategory : listAdvertisementCategory) {
                dropDownAdvCategory.put(advCategory.getName(), advCategory.getId());
            }
        }catch (Exception e){
            LOGGER.error("Error", e);
        }
    }
    
    @PreDestroy
    public void cleanAndExit() {
        isUpdate = null;
        model = null;
        recruitAdvertisementMediaService = null;
        recruitAdvertisementCategoryService = null;
        selected = null;
        listAdvertisementCategory = null;
        dropDownAdvCategory = null;
        
    }
    
    private RecruitAdvertisementMediaModel getModelFromEntity(RecruitAdvertisementMedia entity) {
        RecruitAdvertisementMediaModel model = new RecruitAdvertisementMediaModel();
        model.setId(entity.getId());
        model.setName(entity.getName());
        model.setCode(entity.getCode());
        model.setRecruitAdvertisementCategoryId(entity.getRecruitAdvertisementCategory().getId());
        model.setMediaAddress(entity.getMediaAddress());
        model.setContactPerson(entity.getContactPerson());
        model.setDescription(entity.getDescription());
        model.setAddress(entity.getAddress());
        model.setPhone(entity.getPhone());
        model.setTypeOfMedia(entity.getTypeOfMedia());
        return model;
    }
    
    private RecruitAdvertisementMedia getEntityFromViewModel(RecruitAdvertisementMediaModel model) {
        RecruitAdvertisementMedia advertisementMedia = new RecruitAdvertisementMedia();
        if(model.getId() != null){
            advertisementMedia.setId(model.getId());
        }
        advertisementMedia.setName(model.getName());
        advertisementMedia.setCode(model.getCode());
        advertisementMedia.setRecruitAdvertisementCategory(new RecruitAdvertisementCategory(model.getRecruitAdvertisementCategoryId()));
        advertisementMedia.setMediaAddress(model.getMediaAddress());
        advertisementMedia.setContactPerson(model.getContactPerson());
        advertisementMedia.setDescription(model.getDescription());
        advertisementMedia.setAddress(model.getAddress());
        advertisementMedia.setPhone(model.getPhone());
        advertisementMedia.setTypeOfMedia(model.getTypeOfMedia());
        return advertisementMedia;
        
    }
    
    public void doSave() {
        
        RecruitAdvertisementMedia recruitAdvertisementMedia = getEntityFromViewModel(model);
        try {
            if (isUpdate) {
                recruitAdvertisementMediaService.update(recruitAdvertisementMedia);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                recruitAdvertisementMediaService.save(recruitAdvertisementMedia);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch (BussinessException ex) { 
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public RecruitAdvertisementMediaService getRecruitAdvertisementMediaService() {
        return recruitAdvertisementMediaService;
    }

    public void setRecruitAdvertisementMediaService(RecruitAdvertisementMediaService recruitAdvertisementMediaService) {
        this.recruitAdvertisementMediaService = recruitAdvertisementMediaService;
    }

    public List<RecruitAdvertisementCategory> getListAdvertisementCategory() {
        return listAdvertisementCategory;
    }

    public void setListAdvertisementCategory(List<RecruitAdvertisementCategory> listAdvertisementCategory) {
        this.listAdvertisementCategory = listAdvertisementCategory;
    }

    public Map<String, Long> getDropDownAdvCategory() {
        return dropDownAdvCategory;
    }

    public void setDropDownAdvCategory(Map<String, Long> dropDownAdvCategory) {
        this.dropDownAdvCategory = dropDownAdvCategory;
    }

    public RecruitAdvertisementMedia getSelected() {
        return selected;
    }

    public void setSelected(RecruitAdvertisementMedia selected) {
        this.selected = selected;
    }

    public RecruitAdvertisementMediaModel getModel() {
        return model;
    }

    public void setModel(RecruitAdvertisementMediaModel model) {
        this.model = model;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public RecruitAdvertisementCategoryService getRecruitAdvertisementCategoryService() {
        return recruitAdvertisementCategoryService;
    }

    public void setRecruitAdvertisementCategoryService(RecruitAdvertisementCategoryService recruitAdvertisementCategoryService) {
        this.recruitAdvertisementCategoryService = recruitAdvertisementCategoryService;
    }
    
    
}
