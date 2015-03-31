/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.organisation;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.OrgTypeOfSpec;
import com.inkubator.hrm.service.OrgTypeOfSpecService;
import com.inkubator.hrm.web.model.OrgTypeOfSpecModel;
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
@ManagedBean(name = "orgTypeOfSpecFormController")
@ViewScoped
public class OrgTypeOfSpecFormController extends BaseController{
    @ManagedProperty(value = "#{orgTypeOfSpecService}")
    private OrgTypeOfSpecService service;
    private OrgTypeOfSpec selected;
    private OrgTypeOfSpecModel model;
    private Boolean isUpdate;
    
    @PostConstruct
    @Override
    public void initialization(){
        super.initialization();
        try{
            String orgTypeOfSpecId = FacesUtil.getRequestParameter("orgTypeOfSpecId");
            model = new OrgTypeOfSpecModel();
            isUpdate = Boolean.FALSE;
            if(StringUtils.isNotEmpty(orgTypeOfSpecId)){
                OrgTypeOfSpec orgTypeOfSpec = service.getEntiyByPK(Long.parseLong(orgTypeOfSpecId));
                if(orgTypeOfSpecId != null){
                    model = getModelFromEntity(orgTypeOfSpec);
                    isUpdate = Boolean.TRUE;
                }
            } 
        } catch(Exception e){
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
    
    private OrgTypeOfSpecModel getModelFromEntity(OrgTypeOfSpec entity){
        OrgTypeOfSpecModel model = new OrgTypeOfSpecModel();
        model.setId(entity.getId());
        model.setCode(entity.getCode());
        model.setName(entity.getName());
        model.setDescription(entity.getDescription());
        return model;
    }
    
    private OrgTypeOfSpec getEntityFromViewModel(OrgTypeOfSpecModel model){
        OrgTypeOfSpec orgTypeOfSpec = new OrgTypeOfSpec();
        if(model.getId() != null){
            orgTypeOfSpec.setId(model.getId());
        }
        orgTypeOfSpec.setCode(model.getCode());
        orgTypeOfSpec.setName(model.getName());
        orgTypeOfSpec.setDescription(model.getDescription());
        return orgTypeOfSpec;
    }
    
    public void doSave(){
        OrgTypeOfSpec orgTypeOfSpec = getEntityFromViewModel(model);
        try{
            if(isUpdate){
                service.update(orgTypeOfSpec);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                service.save(orgTypeOfSpec);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch (BussinessException ex){
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex){
            LOGGER.error("Error", ex);
        }
    }

    public OrgTypeOfSpecService getService() {
        return service;
    }

    public void setService(OrgTypeOfSpecService service) {
        this.service = service;
    }

    public OrgTypeOfSpec getSelected() {
        return selected;
    }

    public void setSelected(OrgTypeOfSpec selected) {
        this.selected = selected;
    }

    public OrgTypeOfSpecModel getModel() {
        return model;
    }

    public void setModel(OrgTypeOfSpecModel model) {
        this.model = model;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }
    
    
}
