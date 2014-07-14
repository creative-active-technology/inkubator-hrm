/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.workingtime;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.WtGroupWorking;
import com.inkubator.hrm.entity.WtScheduleShift;
import com.inkubator.hrm.service.WtGroupWorkingService;
import com.inkubator.hrm.service.WtScheduleShiftService;
import com.inkubator.hrm.web.lazymodel.WtScheduleShiftLazyModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.io.IOException;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author Deni Husni FR
 */
@ManagedBean(name = "groupWorkingDetilController")
@ViewScoped
public class GroupWorkingDetilController extends BaseController {

    @ManagedProperty(value = "#{wtGroupWorkingService}")
    private WtGroupWorkingService wtGroupWorkingService;
    @ManagedProperty(value = "#{wtScheduleShiftService}")
    private WtScheduleShiftService wtScheduleShiftService;
    private WtGroupWorking selectedWtGroupWorking;
    private LazyDataModel<WtScheduleShift> wtScheduleShiftLazyDataModel;

    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            String id = FacesUtil.getRequestParameter("execution");
            if (id.contains("s")) {
                System.out.println(" hehehe");
                MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully",
                        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            }
            selectedWtGroupWorking = wtGroupWorkingService.getByCode(id.substring(1));

        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public void setWtGroupWorkingService(WtGroupWorkingService wtGroupWorkingService) {
        this.wtGroupWorkingService = wtGroupWorkingService;
    }

    public WtGroupWorking getSelectedWtGroupWorking() {
        return selectedWtGroupWorking;
    }

    public void setSelectedWtGroupWorking(WtGroupWorking selectedWtGroupWorking) {
        this.selectedWtGroupWorking = selectedWtGroupWorking;
    }

    public void setWtScheduleShiftService(WtScheduleShiftService wtScheduleShiftService) {
        this.wtScheduleShiftService = wtScheduleShiftService;
    }

    public LazyDataModel<WtScheduleShift> getWtScheduleShiftLazyDataModel() {
        if (wtScheduleShiftLazyDataModel == null) {
            wtScheduleShiftLazyDataModel = new WtScheduleShiftLazyModel(selectedWtGroupWorking.getId(), wtScheduleShiftService);
        }
        return wtScheduleShiftLazyDataModel;
    }

    public void setWtScheduleShiftLazyDataModel(LazyDataModel<WtScheduleShift> wtScheduleShiftLazyDataModel) {
        this.wtScheduleShiftLazyDataModel = wtScheduleShiftLazyDataModel;
    }

    public String doBack() {
        return "/protected/working_time/working_group_view.htm?faces-redirect=true";
    }

    public void doEdit() {
        ExternalContext red = FacesUtil.getExternalContext();
        try {
            red.redirect(red.getRequestContextPath() + "/flow-protected/working_group?otherParam=" + selectedWtGroupWorking.getId());
        } catch (IOException ex) {
            LOGGER.error("Error", ex);
        }
    }

    @PreDestroy
    private void cleanAndExit() {
        System.out.println(" di eksekuskiiii");
        wtGroupWorkingService = null;
        wtScheduleShiftService = null;
        selectedWtGroupWorking = null;
        wtScheduleShiftLazyDataModel = null;
    }
}
