/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.scheduler;

import com.inkubator.hrm.entity.SchedulerConfig;
import com.inkubator.hrm.service.SchedulerConfigService;
import com.inkubator.hrm.web.model.SchedulerConfigModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.apache.commons.lang3.StringUtils;

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
    private SchedulerConfig selected;


    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
       try {
            super.initialization();
            String execution = FacesUtil.getRequestParameter("execution");
            String param = execution.substring(0, 1);
            if(StringUtils.equals(param, "e")){
                selected = schedulerConfigService.getEntityWithDetail(Long.parseLong(execution.substring(1)));
            }
        } catch (Exception ex){
            LOGGER.error("error", ex);
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

}
