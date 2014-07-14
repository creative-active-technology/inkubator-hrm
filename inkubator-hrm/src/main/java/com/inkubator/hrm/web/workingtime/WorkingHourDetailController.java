/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.workingtime;

import com.inkubator.hrm.entity.WtWorkingHour;
import com.inkubator.hrm.service.WtWorkingHourService;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "workingHourDetailController")
@ViewScoped
public class WorkingHourDetailController extends BaseController {

    private WtWorkingHour selectedWorkingHour;
    @ManagedProperty(value = "#{wtWorkingHourService}")
    private WtWorkingHourService workingHourService;

    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            String id = FacesUtil.getRequestParameter("execution");
            selectedWorkingHour = workingHourService.getEntiyByPK(Long.parseLong(id.substring(1)));
          
        } catch (Exception ex) {
            LOGGER.error("Error", ex);

        }
    }

    @PreDestroy
    public void cleanAndExit() {
        selectedWorkingHour = null;
        workingHourService = null;
    }

    public WtWorkingHour getSelectedWorkingHour() {
        return selectedWorkingHour;
    }

    public void setSelectedWorkingHour(WtWorkingHour selectedWorkingHour) {
        this.selectedWorkingHour = selectedWorkingHour;
    }

    public void setWorkingHourService(WtWorkingHourService workingHourService) {
        this.workingHourService = workingHourService;
    }

    public String doBack() {
        return "/protected/working_time/working_hour_view.htm?faces-redirect=true";
    }

    public String doUpdate() {
        return "/protected/working_time/working_hour_form.htm?faces-redirect=true&execution=e" + selectedWorkingHour.getId();
    }

}
