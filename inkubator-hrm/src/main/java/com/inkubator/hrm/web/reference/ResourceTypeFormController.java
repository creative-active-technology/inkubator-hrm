/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.reference;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.ResourceType;
import com.inkubator.hrm.service.ResourceTypeService;
import com.inkubator.hrm.web.model.ResourceTypeModel;
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
@ManagedBean(name = "resourceTypeFormController")
@ViewScoped
public class ResourceTypeFormController extends BaseController{
    @ManagedProperty(value = "#{resourceTypeService}")
    private ResourceTypeService service;
    private ResourceType selected;
    private ResourceTypeModel model;
    private Boolean isUpdate;
    
    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try{
            String resourceTypeId = FacesUtil.getRequestParameter("resourceTypeId");
            model = new ResourceTypeModel();
            isUpdate = Boolean.FALSE;
            if (StringUtils.isNotEmpty(resourceTypeId)) {
                ResourceType resourceType = service.getEntiyByPK(Long.parseLong(resourceTypeId));
                if(resourceTypeId != null){
                    model = getModelFromEntity(resourceType);
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
    
    private ResourceTypeModel getModelFromEntity(ResourceType entity) {
        ResourceTypeModel model = new ResourceTypeModel();
        model.setId(entity.getId());
        model.setCode(entity.getCode());
        model.setResourceType(entity.getResourceType());
        return model;
    }
    
    private ResourceType getEntityFromViewModel(ResourceTypeModel model) {
        ResourceType resourceType = new ResourceType();
        if (model.getId() != null) {
            resourceType.setId(model.getId());
        }
        resourceType.setCode(model.getCode());
        resourceType.setResourceType(model.getResourceType());
        return resourceType;
    }
    
    public void doSave() {
        System.out.println("masuk dosave");
        ResourceType resourceType = getEntityFromViewModel(model);
        try {
            if (isUpdate) {
                service.update(resourceType);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                service.save(resourceType);
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

    public ResourceTypeService getService() {
        return service;
    }

    public void setService(ResourceTypeService service) {
        this.service = service;
    }

    public ResourceType getSelected() {
        return selected;
    }

    public void setSelected(ResourceType selected) {
        this.selected = selected;
    }

    public ResourceTypeModel getModel() {
        return model;
    }

    public void setModel(ResourceTypeModel model) {
        this.model = model;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }
    
    
}
