package com.inkubator.hrm.web.employee;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.inkubator.hrm.service.TempAttendanceRealizationService;
import com.inkubator.hrm.web.model.WorkingTimeDeviationDetailModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;



@ManagedBean(name = "workingTimeDeviationDetailController")
@ViewScoped
public class WorkingTimeDeviationDetailController extends BaseController{
    
	@ManagedProperty(value = "#{tempAttendanceRealizationService}")
    private TempAttendanceRealizationService tempAttendanceRealizationService;
	private WorkingTimeDeviationDetailModel workingTimeDeviationDetailModel;
	
	@PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        String empDataId = FacesUtil.getRequestParameter("execution");
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

	
	
	
	
}