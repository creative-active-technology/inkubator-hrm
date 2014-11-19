/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.reference;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.ResourceName;
import com.inkubator.hrm.entity.ResourceType;
import com.inkubator.hrm.service.ResourceNameService;
import com.inkubator.hrm.service.ResourceTypeService;
import com.inkubator.hrm.web.model.ResourceNameModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.util.ArrayList;
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
@ManagedBean(name = "resourceNameFormController")
@ViewScoped
public class ResourceNameFormController extends BaseController{
    @ManagedProperty(value = "#{resourceTypeService}")
    private ResourceTypeService resourceTypeService;
    @ManagedProperty(value = "#{resourceNameService}")
    private ResourceNameService resourceNameService;
    private ResourceName selected;
    private ResourceNameModel model;
    private Boolean isUpdate;
    
    private Map<String, Long> dropDownResourceType = new TreeMap<String, Long>();;
    private List<ResourceType> resourceTypeList = new ArrayList<>();
    
    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try{
            String resourceNameId = FacesUtil.getRequestParameter("resourceNameId");
            model = new ResourceNameModel();
            isUpdate = Boolean.FALSE;
            if (StringUtils.isNotEmpty(resourceNameId)) {
                ResourceName resourceName = resourceNameService.getEntiyByPK(Long.parseLong(resourceNameId));
                if(resourceNameId != null){
                    model = getModelFromEntity(resourceName);
                    isUpdate = Boolean.TRUE;
                }
            }
            
            resourceTypeList = resourceTypeService.getAllData();
            for (ResourceType resourceType : resourceTypeList) {
                dropDownResourceType.put(resourceType.getResourceType(), resourceType.getId());
            }
        }catch (Exception e){
            LOGGER.error("Error", e);
        }
    }
    
    @PreDestroy
    public void cleanAndExit() {
        isUpdate = null;
        model = null;
        resourceTypeService = null;
        resourceNameService = null;
        selected = null;
        dropDownResourceType = null;
        resourceTypeList = null;
    }
    
    private ResourceNameModel getModelFromEntity(ResourceName entity) {
        ResourceNameModel model = new ResourceNameModel();
        model.setId(entity.getId());
        model.setCode(entity.getCode());
        model.setName(entity.getName());
        model.setDateOfOwnership(entity.getDateOfOwnership());
        model.setResourceTypeId(entity.getResourceType().getId());
        model.setDescription(entity.getDescription());
        model.setIsActive(entity.getIsActive());
        return model;
    }
    
    private ResourceName getEntityFromViewModel(ResourceNameModel model) {
        ResourceName resourceName = new ResourceName();
        if (model.getId() != null) {
            resourceName.setId(model.getId());
        }
        resourceName.setCode(model.getCode());
        resourceName.setName(model.getName());
        resourceName.setDateOfOwnership(model.getDateOfOwnership());
        resourceName.setResourceType(new ResourceType(model.getResourceTypeId()));
        resourceName.setDescription(model.getDescription());
        resourceName.setIsActive(model.getIsActive());
        return resourceName;
    }
    
    public void doSave() {
        System.out.println("masuk dosave");
        ResourceName resourceName = getEntityFromViewModel(model);
        try {
            if (isUpdate) {
                resourceNameService.update(resourceName);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                resourceNameService.save(resourceName);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch (BussinessException ex) { 
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public ResourceTypeService getResourceTypeService() {
        return resourceTypeService;
    }

    public void setResourceTypeService(ResourceTypeService resourceTypeService) {
        this.resourceTypeService = resourceTypeService;
    }

    public ResourceNameService getResourceNameService() {
        return resourceNameService;
    }

    public void setResourceNameService(ResourceNameService resourceNameService) {
        this.resourceNameService = resourceNameService;
    }

    public ResourceName getSelected() {
        return selected;
    }

    public void setSelected(ResourceName selected) {
        this.selected = selected;
    }

    public ResourceNameModel getModel() {
        return model;
    }

    public void setModel(ResourceNameModel model) {
        this.model = model;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public Map<String, Long> getDropDownResourceType() {
        return dropDownResourceType;
    }

    public void setDropDownResourceType(Map<String, Long> dropDownResourceType) {
        this.dropDownResourceType = dropDownResourceType;
    }

    public List<ResourceType> getResourceTypeList() {
        return resourceTypeList;
    }

    public void setResourceTypeList(List<ResourceType> resourceTypeList) {
        this.resourceTypeList = resourceTypeList;
    }
    
    
}
