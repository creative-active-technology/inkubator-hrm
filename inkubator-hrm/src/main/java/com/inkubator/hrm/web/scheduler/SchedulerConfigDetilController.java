/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.scheduler;

import com.inkubator.hrm.service.SchedulerConfigService;
import com.inkubator.hrm.web.model.SchedulerConfigModel;
import com.inkubator.webcore.controller.BaseController;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author denifahri
 */
@ManagedBean(name = "schedulerConfigDetilController")
@ViewScoped
public class SchedulerConfigDetilController extends BaseController {

    private SchedulerConfigModel schedulerConfigModel;
    @ManagedProperty(value = "#{schedulerConfigService}")
    private SchedulerConfigService schedulerConfigService;


    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
       
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

}
