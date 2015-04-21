/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.personalia;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.SystemScoring;
import com.inkubator.hrm.service.SystemScoringService;
import com.inkubator.hrm.web.model.SystemScoringModel;
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
@ManagedBean(name = "systemScoringFormController")
@ViewScoped
public class SystemScoringFormController extends BaseController {
    
    @ManagedProperty(value = "#{systemScoringService}")
    private SystemScoringService service;
    private SystemScoringModel model;
    private Boolean isUpdate;
    
    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try{
            String systemScoringId = FacesUtil.getRequestParameter("systemScoringId");
            model = new SystemScoringModel();
            isUpdate = Boolean.FALSE;
            if (StringUtils.isNotEmpty(systemScoringId)) {
                SystemScoring systemScoring = service.getEntiyByPK(Long.parseLong(systemScoringId));
                if(systemScoring != null){
                    model = getModelFromEntity(systemScoring);
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
    }
    
    private SystemScoringModel getModelFromEntity(SystemScoring entity) {
        SystemScoringModel model = new SystemScoringModel();
        model.setId(entity.getId());
        model.setCode(entity.getCode());
        model.setName(entity.getName());
        model.setDescription(entity.getDescription());
        return model;
    }
    
    private SystemScoring getEntityFromViewModel(SystemScoringModel model) {
        SystemScoring systemScoring = new SystemScoring();
        if (model.getId() != null) {
            systemScoring.setId(model.getId());
        }
        systemScoring.setCode(model.getCode());
        systemScoring.setName(model.getName());
        systemScoring.setDescription(model.getDescription());
        return systemScoring;
    }
    
    public void doSave() {
        
        SystemScoring systemScoring = getEntityFromViewModel(model);
        try {
            if (isUpdate) {
                service.update(systemScoring);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                service.save(systemScoring);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch (BussinessException ex) { 
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public SystemScoringService getService() {
        return service;
    }

    public void setService(SystemScoringService service) {
        this.service = service;
    }

    public SystemScoringModel getModel() {
        return model;
    }

    public void setModel(SystemScoringModel model) {
        this.model = model;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }
    
    
}
