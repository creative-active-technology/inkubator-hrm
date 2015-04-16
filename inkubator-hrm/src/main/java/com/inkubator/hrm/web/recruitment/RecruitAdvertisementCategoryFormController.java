/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.recruitment;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.RecruitAdvertisementCategory;
import com.inkubator.hrm.entity.SavingType;
import com.inkubator.hrm.service.RecruitAdvertisementCategoryService;
import com.inkubator.hrm.web.model.RecruitAdvertisementCategoryModel;
import com.inkubator.hrm.web.model.SavingTypeModel;
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
@ManagedBean(name = "recruitAdvertisementCategoryFormController")
@ViewScoped
public class RecruitAdvertisementCategoryFormController extends BaseController {
    
    @ManagedProperty(value = "#{recruitAdvertisementCategoryService}")
    private RecruitAdvertisementCategoryService service;
    private RecruitAdvertisementCategory selected;
    private RecruitAdvertisementCategoryModel model;
    private Boolean isUpdate;
    
    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try{
            String recruitAdvertisementCategoryId = FacesUtil.getRequestParameter("recruitmentId");
            model = new RecruitAdvertisementCategoryModel();
            isUpdate = Boolean.FALSE;
            if (StringUtils.isNotEmpty(recruitAdvertisementCategoryId)) {
                RecruitAdvertisementCategory recruitAdvertisementCategory = service.getEntiyByPK(Long.parseLong(recruitAdvertisementCategoryId));
                if(recruitAdvertisementCategory != null){
                    model = getModelFromEntity(recruitAdvertisementCategory);
                    isUpdate = Boolean.TRUE;
                }
            }
            
        }catch (Exception e){
            LOGGER.error("Error", e);
        }
    }
    
    @PreDestroy
    public void cleanAndExit() {
        isUpdate = null;
        model = null;
        service = null;
        selected = null;
    }
    
    private RecruitAdvertisementCategoryModel getModelFromEntity(RecruitAdvertisementCategory entity) {
        RecruitAdvertisementCategoryModel model = new RecruitAdvertisementCategoryModel();
        model.setId(entity.getId());
        model.setCode(entity.getCode());
        model.setName(entity.getName());
        model.setDescription(entity.getDescription());
        model.setIsOnline(entity.getIsOnline());
        return model;
    }
    
    private RecruitAdvertisementCategory getEntityFromViewModel(RecruitAdvertisementCategoryModel model) {
        RecruitAdvertisementCategory recruitAdvertisementCategory = new RecruitAdvertisementCategory();
        if (model.getId() != null) {
            recruitAdvertisementCategory.setId(model.getId());
        }
        recruitAdvertisementCategory.setCode(model.getCode());
        recruitAdvertisementCategory.setName(model.getName());
        recruitAdvertisementCategory.setDescription(model.getDescription());
        recruitAdvertisementCategory.setIsOnline(model.getIsOnline());
        return recruitAdvertisementCategory;
    }
    
    public void doSave() {
        
        RecruitAdvertisementCategory recruitAdvertisementCategory = getEntityFromViewModel(model);
        try {
            if (isUpdate) {
                service.update(recruitAdvertisementCategory);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                service.save(recruitAdvertisementCategory);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch (BussinessException ex) { 
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public RecruitAdvertisementCategoryService getService() {
        return service;
    }

    public void setService(RecruitAdvertisementCategoryService service) {
        this.service = service;
    }

    public RecruitAdvertisementCategory getSelected() {
        return selected;
    }

    public void setSelected(RecruitAdvertisementCategory selected) {
        this.selected = selected;
    }

    public RecruitAdvertisementCategoryModel getModel() {
        return model;
    }

    public void setModel(RecruitAdvertisementCategoryModel model) {
        this.model = model;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }
    
    
}
