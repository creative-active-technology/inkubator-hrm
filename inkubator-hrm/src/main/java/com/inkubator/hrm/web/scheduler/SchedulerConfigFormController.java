/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.scheduler;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.SchedulerConfig;
import com.inkubator.hrm.service.SchedulerConfigService;
import com.inkubator.hrm.web.model.SchedulerConfigModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author denifahri
 */
@ManagedBean(name = "schedulerConfigFormController")
@ViewScoped
public class SchedulerConfigFormController extends BaseController {
    
    private SchedulerConfigModel schedulerConfigModel;
    @ManagedProperty(value = "#{schedulerConfigService}")
    private SchedulerConfigService schedulerConfigService;
    private Boolean isDisableRepeatType;
    private Boolean isDisableDateRange;
    private Boolean isDisabaleJeda;
    private Boolean isEdit;
    private SchedulerConfig selectedSchedulerConfig;
    
    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            schedulerConfigModel = new SchedulerConfigModel();
            String scheduleId = FacesUtil.getRequestParameter("execution");
            if (scheduleId != null) {
                selectedSchedulerConfig = schedulerConfigService.getEntiyByPK(Long.parseLong(scheduleId.substring(1)));
                schedulerConfigModel = getModelFormEntity(selectedSchedulerConfig);
                isEdit = Boolean.TRUE;
                isDisabaleJeda = schedulerConfigModel.getIsTimeDiv();
                doChangeTypeSchedule();
            } else {
                isEdit = Boolean.FALSE;
                schedulerConfigModel.setRepeateNumber(1);
                schedulerConfigModel.setHourDiv(0);
                schedulerConfigModel.setMinuteDiv(0);
                isDisableRepeatType = Boolean.TRUE;
                isDisableDateRange = Boolean.TRUE;
                isDisabaleJeda = Boolean.TRUE;
                schedulerConfigModel.setIsTimeDiv(Boolean.TRUE);
            }
        } catch (Exception ex) {
            Logger.getLogger(SchedulerConfigFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public SchedulerConfigModel getSchedulerConfigModel() {
        return schedulerConfigModel;
    }
    
    public void setSchedulerConfigModel(SchedulerConfigModel schedulerConfigModel) {
        this.schedulerConfigModel = schedulerConfigModel;
    }
    
    public void setSchedulerConfigService(SchedulerConfigService schedulerConfigService) {
        this.schedulerConfigService = schedulerConfigService;
    }
    
    public Boolean getIsDisableRepeatType() {
        return isDisableRepeatType;
    }
    
    public void setIsDisableRepeatType(Boolean isDisableRepeatType) {
        this.isDisableRepeatType = isDisableRepeatType;
    }
    
    public Boolean getIsDisableDateRange() {
        return isDisableDateRange;
    }
    
    public void setIsDisableDateRange(Boolean isDisableDateRange) {
        this.isDisableDateRange = isDisableDateRange;
    }
    
    public void doChangeTypeSchedule() {
        
        if ("ONCE".equalsIgnoreCase(schedulerConfigModel.getSchedullerType()) || schedulerConfigModel.getSchedullerType().equals(" ")) {
            isDisableRepeatType = Boolean.TRUE;
//            isDisableDateRange = Boolean.FALSE;
        } else {
            isDisableRepeatType = Boolean.FALSE;
            isDisableDateRange = Boolean.TRUE;
        }
        if ("RANGE_TIME".equalsIgnoreCase(schedulerConfigModel.getSchedullerType()) || schedulerConfigModel.getSchedullerType().equals(" ")) {
            isDisableDateRange = Boolean.FALSE;
        } else {
//            isDisableRepeatType = Boolean.FALSE;
            isDisableDateRange = Boolean.TRUE;
        }
        if (schedulerConfigModel.getSchedullerType().isEmpty() || schedulerConfigModel.getSchedullerType().equals("")) {
            isDisableRepeatType = Boolean.TRUE;
            isDisableDateRange = Boolean.TRUE;
        }
        
    }
    
    public String doSave() {
        SchedulerConfig config = getEntityFromModel(schedulerConfigModel);
        try {
            if (isEdit) {
                schedulerConfigService.update(config);
                MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.update_successfully",
                        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
                return "/protected/scheduler/scheduler_config_detail.htm?faces-redirect=true&execution=e" + config.getId();
            } else {
                schedulerConfigService.save(config);
                MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully",
                        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
                return "/protected/scheduler/scheduler_config_detail.htm?faces-redirect=true&execution=e" + config.getId();
            }
            
        } catch (BussinessException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
        
        return null;
    }
    
    public String doBack() {
        return "/protected/scheduler/scheduler_config_view.htm";
    }
    
    public Boolean getIsDisabaleJeda() {
        return isDisabaleJeda;
    }
    
    public void setIsDisabaleJeda(Boolean isDisabaleJeda) {
        this.isDisabaleJeda = isDisabaleJeda;
    }
    
    public void onChange() {
        isDisabaleJeda = schedulerConfigModel.getIsTimeDiv();
        
    }
    
    private SchedulerConfig getEntityFromModel(SchedulerConfigModel configModel) {
        SchedulerConfig schedulerConfig = new SchedulerConfig();
        if (configModel.getId() != null) {
            schedulerConfig.setId(configModel.getId());
        }
        schedulerConfig.setDateStartExecution(configModel.getDateStartExecution());
        schedulerConfig.setEndDate(configModel.getEndDate());
        schedulerConfig.setIsTimeDiv(configModel.getIsTimeDiv());
        schedulerConfig.setName(configModel.getName());
        schedulerConfig.setRepeateNumber(configModel.getRepeateNumber());
        schedulerConfig.setRepeateType(configModel.getRepeateType());
        schedulerConfig.setSchedullerTime(configModel.getSchedullerTime());
        schedulerConfig.setSchedullerType(configModel.getSchedullerType());
        schedulerConfig.setStartDate(configModel.getStartDate());
        schedulerConfig.setHourDiv(configModel.getHourDiv());
        schedulerConfig.setMinuteDiv(configModel.getMinuteDiv());
        schedulerConfig.setIsActive(configModel.getIsActive());
        return schedulerConfig;
    }
    
    private SchedulerConfigModel getModelFormEntity(SchedulerConfig schedulerConfig) {
        SchedulerConfigModel configModel = new SchedulerConfigModel();
        configModel.setDateStartExecution(schedulerConfig.getDateStartExecution());
        configModel.setEndDate(schedulerConfig.getEndDate());
        configModel.setId(schedulerConfig.getId());
        configModel.setIsTimeDiv(schedulerConfig.getIsTimeDiv());
        configModel.setName(schedulerConfig.getName());
        configModel.setRepeateNumber(schedulerConfig.getRepeateNumber());
        configModel.setRepeateType(schedulerConfig.getRepeateType());
        configModel.setSchedullerTime(schedulerConfig.getSchedullerTime());
        configModel.setSchedullerType(schedulerConfig.getSchedullerType());
        configModel.setStartDate(schedulerConfig.getStartDate());
        configModel.setHourDiv(schedulerConfig.getHourDiv());
        configModel.setMinuteDiv(schedulerConfig.getMinuteDiv());
        configModel.setIsActive(schedulerConfig.getIsActive());
        return configModel;
    }
    
    @PreDestroy
    public void cleanAndExit() {
        schedulerConfigModel = null;
        schedulerConfigService = null;
        isDisableRepeatType = null;
        isDisableDateRange = null;
        isDisabaleJeda = null;
    }
    
    public Boolean getIsEdit() {
        return isEdit;
    }
    
    public void setIsEdit(Boolean isEdit) {
        this.isEdit = isEdit;
    }
    
    public SchedulerConfig getSelectedSchedulerConfig() {
        return selectedSchedulerConfig;
    }
    
    public void setSelectedSchedulerConfig(SchedulerConfig selectedSchedulerConfig) {
        this.selectedSchedulerConfig = selectedSchedulerConfig;
    }
    
}
