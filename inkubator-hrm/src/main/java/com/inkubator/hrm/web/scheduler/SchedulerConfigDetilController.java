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
            String schedulerConfigId = FacesUtil.getRequestParameter("execution");
            selected = schedulerConfigService.getEntiyByPK(Long.parseLong(schedulerConfigId.substring(1)));
            /*String param = schedulerConfigId.substring(0, 1);
             if(StringUtils.equals(param, "e")){
             selected = schedulerConfigService.getEntiyByPK(Long.parseLong(schedulerConfigId.substring(1)));
             }*/
        } catch (Exception ex) {
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

    public SchedulerConfig getSelected() {
        return selected;
    }

    public void setSelected(SchedulerConfig selected) {
        this.selected = selected;
    }

    public String doBack() {
        return "/protected/scheduler/scheduler_config_view.htm?faces-redirect=true";
    }

    public String doEdit() {
        return "/protected/scheduler/scheduler_config_form.htm?faces-redirect=true&execution=e" + selected.getId();
    }

    public String doUpdates() {
        return "/protected/reference/scheduler_config_form.htm?faces-redirect=true&execution=e" + selected.getId();
    }

}
