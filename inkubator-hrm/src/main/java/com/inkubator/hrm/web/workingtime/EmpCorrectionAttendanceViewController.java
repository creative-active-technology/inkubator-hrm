package com.inkubator.hrm.web.workingtime;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.hamcrest.Matchers;
import org.primefaces.model.LazyDataModel;

import ch.lambdaj.Lambda;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.WtEmpCorrectionAttendance;
import com.inkubator.hrm.service.WtEmpCorrectionAttendanceService;
import com.inkubator.hrm.util.HrmUserInfoUtil;
import com.inkubator.hrm.web.lazymodel.EmpCorrectionAttendanceLazyDataModel;
import com.inkubator.hrm.web.search.EmpCorrectionAttendanceSearchParameter;
import com.inkubator.securitycore.util.UserInfoUtil;
import com.inkubator.webcore.controller.BaseController;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "empCorrectionAttendanceViewController")
@ViewScoped
public class EmpCorrectionAttendanceViewController extends BaseController {

	private Boolean isAdministator;
    private EmpCorrectionAttendanceSearchParameter parameter;
    private LazyDataModel<WtEmpCorrectionAttendance> lazyData;
    private WtEmpCorrectionAttendance selected;
    
    @ManagedProperty(value = "#{wtEmpCorrectionAttendanceService}")
    private WtEmpCorrectionAttendanceService wtEmpCorrectionAttendanceService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        parameter = new EmpCorrectionAttendanceSearchParameter();
        parameter.setKeyParam("employee");
        isAdministator = Lambda.exists(UserInfoUtil.getRoles(), Matchers.containsString(HRMConstant.ADMINISTRATOR_ROLE));
        if(!isAdministator){ //kalo bukan administrator, maka set empDataId di parameter searchingnya
        	parameter.setEmpDataId(HrmUserInfoUtil.getEmpId());
        }
    }

    @PreDestroy
    public void cleanAndExit() {
    	wtEmpCorrectionAttendanceService = null;
        parameter = null;
        lazyData = null;
        selected = null;
        isAdministator = null;
    }

    public void doSearch() {
        lazyData = null;
    }
    
    public String doDetailEntity() { 
        return "/protected/working_time/emp_correction_attendance_detail.htm?faces-redirect=true&execution=e" + selected.getId();
    }

	public Boolean getIsAdministator() {
		return isAdministator;
	}

	public void setIsAdministator(Boolean isAdministator) {
		this.isAdministator = isAdministator;
	}

	public EmpCorrectionAttendanceSearchParameter getParameter() {
		return parameter;
	}

	public void setParameter(EmpCorrectionAttendanceSearchParameter parameter) {
		this.parameter = parameter;
	}

	public LazyDataModel<WtEmpCorrectionAttendance> getLazyData() {
		if(lazyData == null){
			lazyData = new EmpCorrectionAttendanceLazyDataModel(parameter, wtEmpCorrectionAttendanceService);
		}
		return lazyData;
	}

	public void setLazyData(LazyDataModel<WtEmpCorrectionAttendance> lazyData) {
		this.lazyData = lazyData;
	}

	public WtEmpCorrectionAttendance getSelected() {
		return selected;
	}

	public void setSelected(WtEmpCorrectionAttendance selected) {
		this.selected = selected;
	}

	public WtEmpCorrectionAttendanceService getWtEmpCorrectionAttendanceService() {
		return wtEmpCorrectionAttendanceService;
	}

	public void setWtEmpCorrectionAttendanceService(WtEmpCorrectionAttendanceService wtEmpCorrectionAttendanceService) {
		this.wtEmpCorrectionAttendanceService = wtEmpCorrectionAttendanceService;
	}
    
}
