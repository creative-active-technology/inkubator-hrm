/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.reference;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.SavingType;
import com.inkubator.hrm.service.SavingTypeService;
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
@ManagedBean(name = "savingTypeFormController")
@ViewScoped
public class SavingTypeFormController extends BaseController{
    @ManagedProperty(value = "#{savingTypeService}")
    private SavingTypeService service;
    private SavingType selected;
    private SavingTypeModel model;
    private Boolean isUpdate;
    
    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try{
            String savingTypeId = FacesUtil.getRequestParameter("savingTypeId");
            model = new SavingTypeModel();
            isUpdate = Boolean.FALSE;
            if (StringUtils.isNotEmpty(savingTypeId)) {
                SavingType savingType = service.getEntiyByPK(Long.parseLong(savingTypeId));
                if(savingTypeId != null){
                    model = getModelFromEntity(savingType);
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
    
    private SavingTypeModel getModelFromEntity(SavingType entity) {
        SavingTypeModel model = new SavingTypeModel();
        model.setId(entity.getId());
        model.setCode(entity.getCode());
        model.setName(entity.getName());
        model.setDescription(entity.getDescription());
        return model;
    }
    
    private SavingType getEntityFromViewModel(SavingTypeModel model) {
        SavingType savingType = new SavingType();
        if (model.getId() != null) {
            savingType.setId(model.getId());
        }
        savingType.setCode(model.getCode());
        savingType.setName(model.getName());
        savingType.setDescription(model.getDescription());
        return savingType;
    }
    
    public void doSave() {
        System.out.println("masuk dosave");
        SavingType savingType = getEntityFromViewModel(model);
        try {
            if (isUpdate) {
                service.update(savingType);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                service.save(savingType);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch (BussinessException ex) { 
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public SavingTypeService getService() {
        return service;
    }

    public void setService(SavingTypeService service) {
        this.service = service;
    }

    public SavingType getSelected() {
        return selected;
    }

    public void setSelected(SavingType selected) {
        this.selected = selected;
    }

    public SavingTypeModel getModel() {
        return model;
    }

    public void setModel(SavingTypeModel model) {
        this.model = model;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }
    
    
}
