/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.personalia;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.OhsaCategory;
import com.inkubator.hrm.service.OhsaCategoryService;
import com.inkubator.hrm.web.model.OhsaCategoryModel;
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
 * @author EKA
 */
@ManagedBean(name = "ohsaCategoryFormController")
@ViewScoped
public class OhsaCategoryFormController extends BaseController{
    @ManagedProperty(value = "#{ohsaCategoryService}")
    private OhsaCategoryService service;
    private OhsaCategory selected;
    private OhsaCategoryModel model;
    private Boolean isUpdate;
    
    @PostConstruct
    @Override
    public void initialization(){
        super.initialization();
        try{
            String ohsaCategoryId = FacesUtil.getRequestParameter("ohsaCategoryId");
            model = new OhsaCategoryModel();
            isUpdate = Boolean.FALSE;
            if(StringUtils.isNotEmpty(ohsaCategoryId)){
                OhsaCategory ohsaCategory = service.getEntiyByPK(Long.parseLong(ohsaCategoryId));
                if(ohsaCategoryId != null){
                    model = getModelFromEntity(ohsaCategory);
                    isUpdate = Boolean.TRUE;
                }
            }
        } catch (Exception e){
            LOGGER.error("Error", e);
        }
    }
    
    @PreDestroy
    public void cleanAndExit(){
        isUpdate = null;
        model = null;
        service = null;
        selected = null;
    }
    
    private OhsaCategoryModel getModelFromEntity(OhsaCategory entity){
        OhsaCategoryModel model = new OhsaCategoryModel();
        model.setId(entity.getId());
        model.setCode(entity.getCode());
        model.setName(entity.getName());
        model.setDescription(entity.getDescription());
        return model;
    }
    
    private OhsaCategory getEntityFromViewModel(OhsaCategoryModel model){
        OhsaCategory ohsaCategory = new OhsaCategory();
        if(model.getId() != null){
            ohsaCategory.setId(model.getId());
        }
        ohsaCategory.setCode(model.getCode());
        ohsaCategory.setName(model.getName());
        ohsaCategory.setDescription(model.getDescription());
        return ohsaCategory;
    }
    
    public void doSave(){
        OhsaCategory ohsaCategory = getEntityFromViewModel(model);
        try{
            if(isUpdate){
                service.update(ohsaCategory);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                service.save(ohsaCategory);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch(BussinessException ex){
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex){
        LOGGER.error("Error", ex);
        }
    }

    public OhsaCategoryService getService() {
        return service;
    }

    public void setService(OhsaCategoryService service) {
        this.service = service;
    }

    public OhsaCategory getSelected() {
        return selected;
    }

    public void setSelected(OhsaCategory selected) {
        this.selected = selected;
    }

    public OhsaCategoryModel getModel() {
        return model;
    }

    public void setModel(OhsaCategoryModel model) {
        this.model = model;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }
    
    
}
