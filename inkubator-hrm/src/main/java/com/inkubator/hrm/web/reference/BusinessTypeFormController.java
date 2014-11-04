/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.reference;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.BusinessType;
import com.inkubator.hrm.service.BusinessTypeService;
import com.inkubator.hrm.web.model.BusinessTypeModel;
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
@ManagedBean(name = "businessTypeFormController")
@ViewScoped
public class BusinessTypeFormController extends BaseController{
    @ManagedProperty(value = "#{businessTypeService}")
    private BusinessTypeService service;
    private BusinessType selected;
    private BusinessTypeModel model;
    private Boolean isUpdate;
    
    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try{
            String businessTypeId = FacesUtil.getRequestParameter("businessTypeId");
            model = new BusinessTypeModel();
            isUpdate = Boolean.FALSE;
            if (StringUtils.isNotEmpty(businessTypeId)) {
                BusinessType businessType = service.getEntiyByPK(Long.parseLong(businessTypeId));
                if(businessTypeId != null){
                    model = getModelFromEntity(businessType);
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
    
    private BusinessTypeModel getModelFromEntity(BusinessType entity) {
        BusinessTypeModel model = new BusinessTypeModel();
        model.setId(entity.getId());
        model.setCode(entity.getCode());
        model.setName(entity.getName());
        model.setDescription(entity.getDescription());
        return model;
    }
    
    private BusinessType getEntityFromViewModel(BusinessTypeModel model) {
        BusinessType businessType = new BusinessType();
        if (model.getId() != null) {
            businessType.setId(model.getId());
        }
        businessType.setCode(model.getCode());
        businessType.setName(model.getName());
        businessType.setDescription(model.getDescription());
        return businessType;
    }
    
    public void doSave() {
        System.out.println("masuk dosave");
        BusinessType businessType = getEntityFromViewModel(model);
        try {
            if (isUpdate) {
                service.update(businessType);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                service.save(businessType);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch (BussinessException ex) { //data already exist(duplicate)
            LOGGER.error("Error", ex);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public BusinessTypeService getService() {
        return service;
    }

    public void setService(BusinessTypeService service) {
        this.service = service;
    }

    public BusinessType getSelected() {
        return selected;
    }

    public void setSelected(BusinessType selected) {
        this.selected = selected;
    }

    public BusinessTypeModel getModel() {
        return model;
    }

    public void setModel(BusinessTypeModel model) {
        this.model = model;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }
    
    
}
