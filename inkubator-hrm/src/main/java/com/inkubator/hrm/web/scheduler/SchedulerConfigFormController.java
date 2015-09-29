/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.scheduler;

import com.inkubator.hrm.service.SchedulerConfigService;
import com.inkubator.hrm.web.model.SchedulerConfigModel;
import com.inkubator.webcore.controller.BaseController;
import java.util.Map;
import java.util.TreeMap;
import javax.annotation.PostConstruct;
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

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        schedulerConfigModel = new SchedulerConfigModel();
        schedulerConfigModel.setRepeateNumber(1);
        isDisableRepeatType = Boolean.TRUE;
        isDisableDateRange = Boolean.TRUE;
        isDisabaleJeda = Boolean.TRUE;
        schedulerConfigModel.setIsTimeDiv(Boolean.TRUE);
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
        System.out.println("Nilai nya " + schedulerConfigModel.getSchedullerType());
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

    public void doSave() {

    }

    public void doBack() {

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
}
