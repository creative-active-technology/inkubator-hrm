/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.employee;

import com.inkubator.hrm.service.TempAttendanceRealizationService;
import com.inkubator.hrm.web.lazymodel.WorkingTimeDeviationLazyDataModel;
import com.inkubator.hrm.web.model.WorkingTimeDeviation;
import com.inkubator.hrm.web.search.TempAttendanceRealizationSearchParameter;
import com.inkubator.webcore.controller.BaseController;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author deni.fahri
 */
@ManagedBean(name = "workingTimeDeviationController")
@ViewScoped
public class WorkingTimeDeviationController extends BaseController {

    @ManagedProperty(value = "#{tempAttendanceRealizationService}")
    private TempAttendanceRealizationService tempAttendanceRealizationService;
//    private List<WorkingTimeDeviation> deviations;
    private LazyDataModel<WorkingTimeDeviation> workingTimeDeviationLazyDataModel;
    private TempAttendanceRealizationSearchParameter tempAttendanceRealizationSearchParameter;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        tempAttendanceRealizationSearchParameter=new TempAttendanceRealizationSearchParameter();
    }

    public void setTempAttendanceRealizationService(TempAttendanceRealizationService tempAttendanceRealizationService) {
        this.tempAttendanceRealizationService = tempAttendanceRealizationService;
    }

    public LazyDataModel<WorkingTimeDeviation> getWorkingTimeDeviationLazyDataModel() {
        if (workingTimeDeviationLazyDataModel == null) {
            workingTimeDeviationLazyDataModel = new WorkingTimeDeviationLazyDataModel(tempAttendanceRealizationSearchParameter, tempAttendanceRealizationService);
        }
        return workingTimeDeviationLazyDataModel;
    }

    public void setWorkingTimeDeviationLazyDataModel(LazyDataModel<WorkingTimeDeviation> workingTimeDeviationLazyDataModel) {
        this.workingTimeDeviationLazyDataModel = workingTimeDeviationLazyDataModel;
    }

    public TempAttendanceRealizationSearchParameter getTempAttendanceRealizationSearchParameter() {
        return tempAttendanceRealizationSearchParameter;
    }

    public void setTempAttendanceRealizationSearchParameter(TempAttendanceRealizationSearchParameter tempAttendanceRealizationSearchParameter) {
        this.tempAttendanceRealizationSearchParameter = tempAttendanceRealizationSearchParameter;
    }

}
