package com.inkubator.hrm.web.workingtime;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.AttendanceStatus;
import com.inkubator.hrm.entity.WtOverTime;
import com.inkubator.hrm.entity.WtWorkingHour;
import com.inkubator.hrm.service.AttendanceStatusService;
import com.inkubator.hrm.service.WtOverTimeService;
import com.inkubator.hrm.service.WtWorkingHourService;
import com.inkubator.hrm.web.model.WorkingHourModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "workingHourFormController")
@ViewScoped
public class WorkingHourFormController extends BaseController {

    private WorkingHourModel model;
    private Boolean isUpdate;
    private Boolean isDisabledBreakConf;
    private Boolean isDisabledOvertimeConf;
    private List<AttendanceStatus> attendanceStatusList;
    private List<WtOverTime> overTimeList;
    private List<WtWorkingHour> exchangeWorkingHourList;
    @ManagedProperty(value = "#{wtWorkingHourService}")
    private WtWorkingHourService workingHourService;
    @ManagedProperty(value = "#{attendanceStatusService}")
    private AttendanceStatusService attendanceStatusService;
    @ManagedProperty(value = "#{wtOverTimeService}")
    private WtOverTimeService overTimeService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try {
        	String param = FacesUtil.getRequestParameter("execution");
        	
	        isUpdate = Boolean.FALSE;
	        isDisabledBreakConf = Boolean.TRUE;
	        isDisabledOvertimeConf = Boolean.TRUE;
	        
	        model = new WorkingHourModel();
	        attendanceStatusList = attendanceStatusService.getAllData();
	        overTimeList = overTimeService.getAllData();
	        exchangeWorkingHourList = StringUtils.isEmpty(param) ? workingHourService.getAllData() : workingHourService.getAllDataExceptId(Long.parseLong(param.substring(1)));
	        
	        if (StringUtils.isNotEmpty(param)) {	            
                WtWorkingHour workingHour = workingHourService.getEntiyByPK(Long.parseLong(param.substring(1)));
                if (workingHour != null) {
                    getModelFromEntity(workingHour);
                    isUpdate = Boolean.TRUE;
                    isDisabledBreakConf = !workingHour.getIsManageBreakTime();
                    isDisabledOvertimeConf = !workingHour.getIsManageOvertime();
                }            
	        }
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    @PreDestroy
    public void cleanAndExit() {
        workingHourService = null;
        attendanceStatusService = null;
        model = null;
        isUpdate = null;
        isDisabledBreakConf = null;
        isDisabledOvertimeConf = null;
        attendanceStatusList = null;
        overTimeList = null;
        exchangeWorkingHourList = null;
        overTimeService = null;
    }

    public WorkingHourModel getModel() {
        return model;
    }

    public void setModel(WorkingHourModel model) {
        this.model = model;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public Boolean getIsDisabledBreakConf() {
        return isDisabledBreakConf;
    }

    public void setIsDisabledBreakConf(Boolean isDisabledBreakConf) {
        this.isDisabledBreakConf = isDisabledBreakConf;
    }

    public Boolean getIsDisabledOvertimeConf() {
		return isDisabledOvertimeConf;
	}

	public void setIsDisabledOvertimeConf(Boolean isDisabledOvertimeConf) {
		this.isDisabledOvertimeConf = isDisabledOvertimeConf;
	}

	public void setWorkingHourService(WtWorkingHourService workingHourService) {
        this.workingHourService = workingHourService;
    }
	
	public void setAttendanceStatusService(AttendanceStatusService attendanceStatusService) {
		this.attendanceStatusService = attendanceStatusService;
	}

	public List<AttendanceStatus> getAttendanceStatusList() {
		return attendanceStatusList;
	}

	public void setAttendanceStatusList(List<AttendanceStatus> attendanceStatusList) {
		this.attendanceStatusList = attendanceStatusList;
	}

	public List<WtOverTime> getOverTimeList() {
		return overTimeList;
	}

	public void setOverTimeList(List<WtOverTime> overTimeList) {
		this.overTimeList = overTimeList;
	}

	public List<WtWorkingHour> getExchangeWorkingHourList() {
		return exchangeWorkingHourList;
	}

	public void setExchangeWorkingHourList(List<WtWorkingHour> exchangeWorkingHourList) {
		this.exchangeWorkingHourList = exchangeWorkingHourList;
	}

	public void setOverTimeService(WtOverTimeService overTimeService) {
		this.overTimeService = overTimeService;
	}

	public void doReset() {
    	if(isUpdate) {
    		try {
    			WtWorkingHour workingHour = workingHourService.getEntiyByPK(model.getId());
    			if (workingHour != null) {
                    getModelFromEntity(workingHour);
                    isDisabledBreakConf = BooleanUtils.isNotTrue(model.getIsManageBreakTime());
                    isDisabledOvertimeConf = BooleanUtils.isNotTrue(model.getIsManageOvertime());
    			}
    		} catch (Exception ex) {
    			LOGGER.error("Error", ex);
    		}
    	} else {
    		model = new WorkingHourModel();
    		//default is true
    		isDisabledBreakConf = Boolean.TRUE;
    		isDisabledOvertimeConf = Boolean.TRUE;
    	}    	
    }

    public String doSave() {
        WtWorkingHour workingHour = getEntityFromViewModel(model);
        try {
            if (isUpdate) {
                workingHourService.update(workingHour);
                MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.update_successfully",
                        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

            } else {
                workingHourService.save(workingHour);
                MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully",
                        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            }
            return "/protected/working_time/working_hour_detail.htm?faces-redirect=true&execution=e" + workingHour.getId();
        } catch (BussinessException ex) { //data already exist(duplicate)
            LOGGER.error("Error", ex);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
        return null;
    }

    private WtWorkingHour getEntityFromViewModel(WorkingHourModel model) {
        WtWorkingHour workingHour = new WtWorkingHour();
        if (model.getId() != null) {
            workingHour.setId(model.getId());
        }
        workingHour.setCode(model.getCode());
        workingHour.setName(model.getName());
        workingHour.setDescription(model.getDescription());
        workingHour.setWorkingHourBegin(model.getWorkingHourBegin());
        workingHour.setWorkingHourEnd(model.getWorkingHourEnd());
        workingHour.setMaxHour(model.getMaxHour());
        workingHour.setArriveLimitBegin(model.getArriveLimitBegin());
        workingHour.setArriveLimitEnd(model.getArriveLimitEnd());
        workingHour.setGoHomeLimitBegin(model.getGoHomeLimitBegin());
        workingHour.setGoHomeLimitEnd(model.getGoHomeLimitEnd());
        workingHour.setIsPenaltyArriveLate(model.getIsPenaltyArriveLate());
        workingHour.setIsPenaltyGoHomeEarly(model.getIsPenaltyGoHomeEarly());
        workingHour.setIsPenaltyBreakStartEarly(model.getIsPenaltyBreakStartEarly());
        workingHour.setIsPenaltyBreakFinishLate(model.getIsPenaltyBreakFinishLate());
        workingHour.setAttendanceStatus(new AttendanceStatus(model.getAttendanceStatusId()));
        workingHour.setIsManageBreakTime(model.getIsManageBreakTime());
        workingHour.setBreakHourBegin(model.getBreakHourBegin());
        workingHour.setBreakHourEnd(model.getBreakHourEnd());
        workingHour.setBreakStartLimitBegin(model.getBreakStartLimitBegin());
        workingHour.setBreakStartLimitEnd(model.getBreakStartLimitEnd());
        workingHour.setBreakFinishLimitBegin(model.getBreakFinishLimitBegin());
        workingHour.setBreakFinishLimitEnd(model.getBreakFinishLimitEnd());
        workingHour.setIsPenaltyBreakStartEarly(model.getIsPenaltyBreakStartEarly());
        workingHour.setIsPenaltyBreakFinishLate(model.getIsPenaltyBreakFinishLate());
        workingHour.setIsManageOvertime(model.getIsManageOvertime());
		workingHour.setStartOvertime(model.getStartOvertime());
		workingHour.setEndOvertime(model.getEndOvertime());
		workingHour.setWtOverTime(model.getOverTimeId() != null ? new WtOverTime(model.getOverTimeId()) : null);
		workingHour.setExchangeWorkingHour(model.getExchangeWorkingHourId() != null ? new WtWorkingHour(model.getExchangeWorkingHourId()) : null);
		
        return workingHour;
    }

    private void getModelFromEntity(WtWorkingHour workingHour) {
        model.setId(workingHour.getId());
        model.setCode(workingHour.getCode());
        model.setName(workingHour.getName());
        model.setDescription(workingHour.getDescription());
        model.setWorkingHourBegin(workingHour.getWorkingHourBegin());
        model.setWorkingHourEnd(workingHour.getWorkingHourEnd());
        model.setMaxHour(workingHour.getMaxHour());
        model.setArriveLimitBegin(workingHour.getArriveLimitBegin());
        model.setArriveLimitEnd(workingHour.getArriveLimitEnd());
        model.setGoHomeLimitBegin(workingHour.getGoHomeLimitBegin());
        model.setGoHomeLimitEnd(workingHour.getGoHomeLimitEnd());
        model.setIsPenaltyArriveLate(workingHour.getIsPenaltyArriveLate());
        model.setIsPenaltyGoHomeEarly(workingHour.getIsPenaltyGoHomeEarly());
        model.setIsPenaltyBreakStartEarly(workingHour.getIsPenaltyBreakStartEarly());
        model.setIsPenaltyBreakFinishLate(workingHour.getIsPenaltyBreakFinishLate());
        model.setAttendanceStatusId(workingHour.getAttendanceStatus().getId());
        model.setIsManageBreakTime(workingHour.getIsManageBreakTime());
        model.setBreakHourBegin(workingHour.getBreakHourBegin());
        model.setBreakHourEnd(workingHour.getBreakHourEnd());
        model.setBreakStartLimitBegin(workingHour.getBreakStartLimitBegin());
        model.setBreakStartLimitEnd(workingHour.getBreakStartLimitEnd());
        model.setBreakFinishLimitBegin(workingHour.getBreakFinishLimitBegin());
        model.setBreakFinishLimitEnd(workingHour.getBreakFinishLimitEnd());
        model.setIsPenaltyBreakStartEarly(workingHour.getIsPenaltyBreakStartEarly());
        model.setIsPenaltyBreakFinishLate(workingHour.getIsPenaltyBreakFinishLate());
        model.setIsManageOvertime(workingHour.getIsManageOvertime());
        model.setStartOvertime(workingHour.getStartOvertime());
        model.setEndOvertime(workingHour.getEndOvertime());
        if(workingHour.getWtOverTime() != null) {
        	model.setOverTimeId(workingHour.getWtOverTime().getId());
        }
        if(workingHour.getExchangeWorkingHour() != null){
        	model.setExchangeWorkingHourId(workingHour.getExchangeWorkingHour().getId());
        }
    }

    public String doBack() {
        return "/protected/working_time/working_hour_view.htm?faces-redirect=true";
    }

    public void onChangeManageBreakTime() {
        isDisabledBreakConf = BooleanUtils.isNotTrue(model.getIsManageBreakTime());
        if (isDisabledBreakConf) {
            model.setBreakHourBegin(null);
            model.setBreakHourEnd(null);
            model.setBreakStartLimitBegin(null);
            model.setBreakStartLimitEnd(null);
            model.setBreakFinishLimitBegin(null);
            model.setBreakFinishLimitEnd(null);
            model.setIsPenaltyBreakStartEarly(Boolean.FALSE);
            model.setIsPenaltyBreakFinishLate(Boolean.FALSE);
        }
    }
    
    public void onChangeManageOvertime() {
        isDisabledOvertimeConf = BooleanUtils.isNotTrue(model.getIsManageOvertime());
        if (isDisabledOvertimeConf) {
        	model.setStartOvertime(null);
        	model.setEndOvertime(null);
        	model.setOverTimeId(null);
        }
    }
}
