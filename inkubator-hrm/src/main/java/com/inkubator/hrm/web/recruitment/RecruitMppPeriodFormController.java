/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.recruitment;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.RecruitMppPeriod;
import com.inkubator.hrm.entity.SavingType;
import com.inkubator.hrm.service.RecruitMppPeriodService;
import com.inkubator.hrm.web.model.RecruitMppPeriodModel;
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
@ManagedBean(name = "recruitMppPeriodFormController")
@ViewScoped
public class RecruitMppPeriodFormController extends BaseController{
    @ManagedProperty(value = "#{recruitMppPeriodService}")
    private RecruitMppPeriodService recruitMppPeriodService;
    private RecruitMppPeriodModel model;
    private Boolean isUpdate;
    
    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try{
            String recruitMppId = FacesUtil.getRequestParameter("recruitMppId");
            model = new RecruitMppPeriodModel();
            isUpdate = Boolean.FALSE;
            if (StringUtils.isNotEmpty(recruitMppId)) {
                RecruitMppPeriod recruitMppPeriod = recruitMppPeriodService.getEntiyByPK(Long.parseLong(recruitMppId));
                if(recruitMppPeriod != null){
                    model = getModelFromEntity(recruitMppPeriod);
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
        recruitMppPeriodService = null;
    }
    
    private RecruitMppPeriodModel getModelFromEntity(RecruitMppPeriod entity) {
        RecruitMppPeriodModel model = new RecruitMppPeriodModel();
        model.setId(entity.getId());
        model.setCode(entity.getCode());
        model.setName(entity.getName());
        model.setPeriodeEnd(entity.getPeriodeEnd());
        model.setPeriodeStart(entity.getPeriodeStart());
        return model;
    }
    
    private RecruitMppPeriod getEntityFromViewModel(RecruitMppPeriodModel model) {
        RecruitMppPeriod recruitMppPeriod = new RecruitMppPeriod();
        if (model.getId() != null) {
            recruitMppPeriod.setId(model.getId());
        }
        recruitMppPeriod.setCode(model.getCode());
        recruitMppPeriod.setName(model.getName());
        recruitMppPeriod.setPeriodeEnd(model.getPeriodeEnd());
        recruitMppPeriod.setPeriodeStart(model.getPeriodeStart());
        return recruitMppPeriod;
    }
    
    public void doSave() {
        
        RecruitMppPeriod recruitMppPeriod = getEntityFromViewModel(model);
        try {
            if (isUpdate) {
                recruitMppPeriodService.update(recruitMppPeriod);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                recruitMppPeriodService.save(recruitMppPeriod);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch (BussinessException ex) { 
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public RecruitMppPeriodService getRecruitMppPeriodService() {
        return recruitMppPeriodService;
    }

    public void setRecruitMppPeriodService(RecruitMppPeriodService recruitMppPeriodService) {
        this.recruitMppPeriodService = recruitMppPeriodService;
    }

    public RecruitMppPeriodModel getModel() {
        return model;
    }

    public void setModel(RecruitMppPeriodModel model) {
        this.model = model;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }
    
    
}
