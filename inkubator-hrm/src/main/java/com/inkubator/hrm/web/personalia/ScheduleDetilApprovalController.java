/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.personalia;

import com.inkubator.hrm.entity.TempJadwalKaryawan;
import com.inkubator.hrm.entity.WtGroupWorking;
import com.inkubator.hrm.entity.WtScheduleShift;
import com.inkubator.hrm.service.ApprovalActivityService;
import com.inkubator.hrm.service.WtGroupWorkingService;
import com.inkubator.hrm.service.WtScheduleShiftService;
import com.inkubator.hrm.web.lazymodel.WtScheduleShiftLazyModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author Deni Husni FR
 */
@ManagedBean(name = "scheduleDetilApprovalController")
@RequestScoped
public class ScheduleDetilApprovalController extends BaseController {

    @ManagedProperty(value = "#{wtScheduleShiftService}")
    private WtScheduleShiftService wtScheduleShiftService;
    private LazyDataModel<WtScheduleShift> wtScheduleShiftLazyDataModel;
    private List<TempJadwalKaryawan> listTempJadwalKaryawan;
    private String id;
    private WtGroupWorking selectedWtGroupWorking;
    @ManagedProperty(value = "#{wtGroupWorkingService}")
    private WtGroupWorkingService wtGroupWorkingService;
    @ManagedProperty(value = "#{approvalActivityService}")
    private ApprovalActivityService approvalActivityService;

    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            id = FacesUtil.getRequestParameter("id");
            listTempJadwalKaryawan=wtScheduleShiftService.getAllScheduleForView(Long.parseLong(id));
//            selectedWtGroupWorking = selectedWtGroupWorking = wtGroupWorkingService.getEntiyByPK(Long.parseLong(id));
           
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

    public List<TempJadwalKaryawan> getListTempJadwalKaryawan() {
        return listTempJadwalKaryawan;
    }

    public void setListTempJadwalKaryawan(List<TempJadwalKaryawan> listTempJadwalKaryawan) {
        this.listTempJadwalKaryawan = listTempJadwalKaryawan;
    }

    
    public void setWtGroupWorkingService(WtGroupWorkingService wtGroupWorkingService) {
        this.wtGroupWorkingService = wtGroupWorkingService;
    }

    public void setApprovalActivityService(ApprovalActivityService approvalActivityService) {
        this.approvalActivityService = approvalActivityService;
    }

}
