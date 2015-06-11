/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.employee;

import com.inkubator.hrm.entity.TempAttendanceRealization;
import com.inkubator.hrm.service.TempAttendanceRealizationService;
import com.inkubator.hrm.web.lazymodel.TempAttendanceRealizationLazyDataModel;
import com.inkubator.hrm.web.model.RealizationAttendanceModel;
import com.inkubator.hrm.web.model.WorkingTimeDeviation;
import com.inkubator.hrm.web.search.TempAttendanceRealizationSearchParameter;
import com.inkubator.webcore.controller.BaseController;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author deni.fahri
 */
@ManagedBean(name = "empAttendaceRealizationController")
@ViewScoped
public class EmpAttendaceRealizationController extends BaseController {

    @ManagedProperty(value = "#{tempAttendanceRealizationService}")
    private TempAttendanceRealizationService tempAttendanceRealizationService;
    private RealizationAttendanceModel attendanceModel;
    private TempAttendanceRealizationSearchParameter tempAttendanceRealizationSearchParameter;
    private LazyDataModel<TempAttendanceRealization> tempAttendanceRealizationsLazyModel;
    private TempAttendanceRealization selectedTempAttendanceRealization;

    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            attendanceModel = tempAttendanceRealizationService.getStatisticEmpAttendaceRealization();
            tempAttendanceRealizationSearchParameter = new TempAttendanceRealizationSearchParameter();
            List<WorkingTimeDeviation> deviations = tempAttendanceRealizationService.getWorkingHourDeviation(tempAttendanceRealizationSearchParameter, 0, 10, null);

            for (WorkingTimeDeviation deviation : deviations) {
                System.out.println(deviation);
            }
        } catch (Exception ex) {
            LOGGER.error(ex, ex);
        }
    }

    public RealizationAttendanceModel getAttendanceModel() {
        return attendanceModel;
    }

    public void setAttendanceModel(RealizationAttendanceModel attendanceModel) {
        this.attendanceModel = attendanceModel;
    }

    public void setTempAttendanceRealizationService(TempAttendanceRealizationService tempAttendanceRealizationService) {
        this.tempAttendanceRealizationService = tempAttendanceRealizationService;
    }

    public TempAttendanceRealizationSearchParameter getTempAttendanceRealizationSearchParameter() {
        return tempAttendanceRealizationSearchParameter;
    }

    public void setTempAttendanceRealizationSearchParameter(TempAttendanceRealizationSearchParameter tempAttendanceRealizationSearchParameter) {
        this.tempAttendanceRealizationSearchParameter = tempAttendanceRealizationSearchParameter;
    }

    public LazyDataModel<TempAttendanceRealization> getTempAttendanceRealizationsLazyModel() {
        if (tempAttendanceRealizationsLazyModel == null) {
            tempAttendanceRealizationsLazyModel = new TempAttendanceRealizationLazyDataModel(tempAttendanceRealizationSearchParameter, tempAttendanceRealizationService);
        }
        return tempAttendanceRealizationsLazyModel;
    }

    public void setTempAttendanceRealizationsLazyModel(LazyDataModel<TempAttendanceRealization> tempAttendanceRealizationsLazyModel) {
        this.tempAttendanceRealizationsLazyModel = tempAttendanceRealizationsLazyModel;
    }

    public TempAttendanceRealization getSelectedTempAttendanceRealization() {
        return selectedTempAttendanceRealization;
    }

    public void setSelectedTempAttendanceRealization(TempAttendanceRealization selectedTempAttendanceRealization) {
        this.selectedTempAttendanceRealization = selectedTempAttendanceRealization;
    }

    public String doDetail() {

        return "/protected/employee/emp_attendance_detail.htm?faces-redirect=true&execution=e" + selectedTempAttendanceRealization.getEmpData().getId();
    }

    public void doSearch() {
        
        tempAttendanceRealizationsLazyModel = null;
    }

}
