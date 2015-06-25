package com.inkubator.hrm.web.employee;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.LazyDataModel;

import com.inkubator.hrm.service.TempAttendanceRealizationService;
import com.inkubator.hrm.web.lazymodel.WorkingTimeDeviationListDetailModelLazyDataModel;
import com.inkubator.hrm.web.model.WorkingTimeDeviationDetailModel;
import com.inkubator.hrm.web.model.WorkingTimeDeviationListDetailModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;

@ManagedBean(name = "workingTimeDeviationDetailController")
@ViewScoped
public class WorkingTimeDeviationDetailController extends BaseController {

    @ManagedProperty(value = "#{tempAttendanceRealizationService}")
    private TempAttendanceRealizationService tempAttendanceRealizationService;
    private WorkingTimeDeviationDetailModel workingTimeDeviationDetailModel;
    private LazyDataModel<WorkingTimeDeviationListDetailModel> lazyDataModel;
    private Long id;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        String empDataId = FacesUtil.getRequestParameter("execution");
        id = Long.valueOf(empDataId.substring(1));
        try {
            workingTimeDeviationDetailModel = tempAttendanceRealizationService.getEntityByEmpDataId(Long.valueOf(empDataId.substring(1)));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @PreDestroy
    public void cleanAndExit() {
        tempAttendanceRealizationService = null;
        workingTimeDeviationDetailModel = null;
    }

    public TempAttendanceRealizationService getTempAttendanceRealizationService() {
        return tempAttendanceRealizationService;
    }

    public void setTempAttendanceRealizationService(
            TempAttendanceRealizationService tempAttendanceRealizationService) {
        this.tempAttendanceRealizationService = tempAttendanceRealizationService;
    }

    public WorkingTimeDeviationDetailModel getWorkingTimeDeviationDetailModel() {
        return workingTimeDeviationDetailModel;
    }

    public void setWorkingTimeDeviationDetailModel(
            WorkingTimeDeviationDetailModel workingTimeDeviationDetailModel) {
        this.workingTimeDeviationDetailModel = workingTimeDeviationDetailModel;
    }

    public LazyDataModel<WorkingTimeDeviationListDetailModel> getLazyDataModel() {
        if (lazyDataModel == null) {
            lazyDataModel = new WorkingTimeDeviationListDetailModelLazyDataModel(id, tempAttendanceRealizationService);
        }
        return lazyDataModel;
    }

    public void setLazyDataModel(LazyDataModel<WorkingTimeDeviationListDetailModel> lazyDataModel) {
        this.lazyDataModel = lazyDataModel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
