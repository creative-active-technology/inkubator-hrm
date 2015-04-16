/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.personalia;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.SystemScoring;
import com.inkubator.hrm.entity.SystemScoringIndex;
import com.inkubator.hrm.service.SystemScoringIndexService;
import com.inkubator.hrm.web.model.SystemScoringIndexModel;
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
@ManagedBean(name = "systemScoringIndexFormController")
@ViewScoped
public class SystemScoringIndexFormController extends BaseController {
    @ManagedProperty(value = "#{systemScoringIndexService}")
    private SystemScoringIndexService systemScoringIndexService;
    private SystemScoringIndexModel model;
    private Boolean isUpdate;
    
    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try{
            String systemScoringId = FacesUtil.getRequestParameter("systemScoringId");
            String systemScoringIndexId = FacesUtil.getRequestParameter("systemScoringIndexId");
            model = new SystemScoringIndexModel();
            isUpdate = Boolean.FALSE;
            if (StringUtils.isNotEmpty(systemScoringIndexId)) {
                SystemScoringIndex systemScoringIndex = systemScoringIndexService.getEntiyByPK(Long.parseLong(systemScoringIndexId));
                if(systemScoringIndex != null){
                    model = getModelFromEntity(systemScoringIndex);
                    isUpdate = Boolean.TRUE;
                }
            }
            model.setSystemScoringId(Long.valueOf(systemScoringId));
        }catch (Exception e){
            LOGGER.error("Error", e);
        }
    }
    
    @PreDestroy
    public void cleanAndExit() {
        isUpdate = null;
        model = null;
        systemScoringIndexService = null;
    }
    
    private SystemScoringIndexModel getModelFromEntity(SystemScoringIndex entity) {
        SystemScoringIndexModel model = new SystemScoringIndexModel();
        model.setId(entity.getId());
        model.setValue(entity.getValue());
        model.setName(entity.getLabelMask());
        model.setDescription(entity.getDescription());
        return model;
    }
    
    private SystemScoringIndex getEntityFromViewModel(SystemScoringIndexModel model) {
        SystemScoringIndex systemScoringIndex = new SystemScoringIndex();
        if (model.getId() != null) {
            systemScoringIndex.setId(model.getId());
        }
        systemScoringIndex.setValue(model.getValue());
        systemScoringIndex.setLabelMask(model.getName());
        systemScoringIndex.setSystemScoring(new SystemScoring(model.getSystemScoringId()));
        systemScoringIndex.setDescription(model.getDescription());
        return systemScoringIndex;
    }

    public void doSave() {
        
        SystemScoringIndex systemScoringIndex = getEntityFromViewModel(model);
        try {
            if (isUpdate) {
                systemScoringIndexService.update(systemScoringIndex);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                systemScoringIndexService.save(systemScoringIndex);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch (BussinessException ex) { 
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }
    
    public SystemScoringIndexService getSystemScoringIndexService() {
        return systemScoringIndexService;
    }

    public void setSystemScoringIndexService(SystemScoringIndexService systemScoringIndexService) {
        this.systemScoringIndexService = systemScoringIndexService;
    }

    public SystemScoringIndexModel getModel() {
        return model;
    }

    public void setModel(SystemScoringIndexModel model) {
        this.model = model;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }
    
    
}
