/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.personalia;

import com.inkubator.hrm.entity.WtScheduleShift;
import com.inkubator.hrm.service.WtScheduleShiftService;
import com.inkubator.hrm.web.lazymodel.WtScheduleShiftLazyModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author Deni Husni FR
 */
@ManagedBean(name = "scheduleDetilApprovalController")
@ViewScoped
public class ScheduleDetilApprovalController extends BaseController {

    @ManagedProperty(value = "#{wtScheduleShiftService}")
    private WtScheduleShiftService wtScheduleShiftService;
    private LazyDataModel<WtScheduleShift> wtScheduleShiftLazyDataModel;
private String id;
    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            id = FacesUtil.getRequestParameter("execution");
            System.out.println("ini nilai id nya "+id);

        } catch (Exception ex) {
            LOGGER.error("Error", ex);

        }
    }

    @PreDestroy
    public void cleanAndExit() {
//    	selectedLoan =null;
//    	loanPaymentDetails = null;
//    	loanService = null;
//        comment = null;
//        empDataService = null;
    }

    public void setWtScheduleShiftService(WtScheduleShiftService wtScheduleShiftService) {
        this.wtScheduleShiftService = wtScheduleShiftService;
    }

    public LazyDataModel<WtScheduleShift> getWtScheduleShiftLazyDataModel() {
        if (wtScheduleShiftLazyDataModel == null) {
            wtScheduleShiftLazyDataModel = new WtScheduleShiftLazyModel(Long.parseLong(id), wtScheduleShiftService);
        }
        return wtScheduleShiftLazyDataModel;
    }

    public void setWtScheduleShiftLazyDataModel(LazyDataModel<WtScheduleShift> wtScheduleShiftLazyDataModel) {
        this.wtScheduleShiftLazyDataModel = wtScheduleShiftLazyDataModel;
    }
}
