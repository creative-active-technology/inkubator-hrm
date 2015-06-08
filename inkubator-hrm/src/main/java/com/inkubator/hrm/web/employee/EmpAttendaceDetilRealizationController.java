/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.employee;

import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.TempAttendanceRealization;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.TempAttendanceRealizationService;
import com.inkubator.hrm.web.model.DetilRealizationAttendanceModel;
import com.inkubator.hrm.web.model.RealizationAttendanceModel;
import com.inkubator.hrm.web.search.TempAttendanceRealizationSearchParameter;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author deni.fahri
 */
@ManagedBean(name = "empAttendaceDetilRealizationController")
@ViewScoped
public class EmpAttendaceDetilRealizationController extends BaseController {

    @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;
    @ManagedProperty(value = "#{tempAttendanceRealizationService}")
    private TempAttendanceRealizationService tempAttendanceRealizationService;
    private DetilRealizationAttendanceModel attendanceModel;
    private TempAttendanceRealizationSearchParameter tempAttendanceRealizationSearchParameter;
    private LazyDataModel<TempAttendanceRealization> tempAttendanceRealizationsLazyModel;
    private TempAttendanceRealization selectedTempAttendanceRealization;
    private Long empDataId;
    private EmpData empData;

    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            String empId = FacesUtil.getRequestParameter("execution");
            empData = empDataService.getByEmpIdWithDetail(Long.parseLong(empId.substring(1)));
            System.out.println(" nanananan " + empData.getBioData().getFullName());
            empDataId = empData.getBioData().getId();
            System.out.println(" nilianana  " + empDataId);
            attendanceModel = tempAttendanceRealizationService.getStatisticEmpAttendaceDetil(empData.getId());
        } catch (Exception ex) {
            LOGGER.error(ex, ex);
        }
    }

    public DetilRealizationAttendanceModel getAttendanceModel() {
        return attendanceModel;
    }

    public void setAttendanceModel(DetilRealizationAttendanceModel attendanceModel) {
        this.attendanceModel = attendanceModel;
    }

    public TempAttendanceRealizationSearchParameter getTempAttendanceRealizationSearchParameter() {
        return tempAttendanceRealizationSearchParameter;
    }

    public void setTempAttendanceRealizationSearchParameter(TempAttendanceRealizationSearchParameter tempAttendanceRealizationSearchParameter) {
        this.tempAttendanceRealizationSearchParameter = tempAttendanceRealizationSearchParameter;
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

        return "/protected/employee/emp_attendance_detail.htm?faces-redirect=true&activity=e" + selectedTempAttendanceRealization.getEmpData().getBioData().getId();
    }

    public void doSearch() {
        tempAttendanceRealizationsLazyModel = null;
    }

    public Long getEmpDataId() {
        return empDataId;
    }

    public void setEmpDataId(Long empDataId) {
        this.empDataId = empDataId;
    }

    public EmpData getEmpData() {
        return empData;
    }

    public void setEmpData(EmpData empData) {
        this.empData = empData;
    }

    public void setEmpDataService(EmpDataService empDataService) {
        this.empDataService = empDataService;
    }

    public void setTempAttendanceRealizationService(TempAttendanceRealizationService tempAttendanceRealizationService) {
        this.tempAttendanceRealizationService = tempAttendanceRealizationService;
    }

    public String doBack() {
        return "/protected/employee/emp_attendance_realization.htm?faces-redirect=true";
        
    }
}
