package com.inkubator.hrm.web.workingtime;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.AttendanceStatus;
import com.inkubator.hrm.entity.Leave;
import com.inkubator.hrm.service.AttendanceStatusService;
import com.inkubator.hrm.service.LeaveService;
import com.inkubator.hrm.web.model.LeaveModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "leaveFormController")
@ViewScoped
public class LeaveFormController extends BaseController {

    private LeaveModel model;
    private Boolean isUpdate;
    private Boolean isRenderAvailabilityDate;
    @ManagedProperty(value = "#{leaveService}")
    private LeaveService leaveService;
    @ManagedProperty(value = "#{attendanceStatusService}")
    private AttendanceStatusService attendanceStatusService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try {
	        String param = FacesUtil.getRequestParameter("execution");
	        model = new LeaveModel();
	        List<AttendanceStatus> attendanceStatusList = attendanceStatusService.getAllData();
	        model.setAttendanceStatusList(attendanceStatusList);
	        isUpdate = Boolean.FALSE;
	        isRenderAvailabilityDate = Boolean.FALSE;
	        if (StringUtils.isNotEmpty(param)) {
	        	Leave leave = leaveService.getEntiyByPK(Long.parseLong(param.substring(1)));
				if (leave != null) {
					getModelFromEntity(leave);
					isUpdate = Boolean.TRUE;
					isRenderAvailabilityDate = model.getAvailability() == HRMConstant.LEAVE_AVAILABILITY_INCREASES_CERTAIN_DATE;
				}
	        }
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    @PreDestroy
    public void cleanAndExit() {
        leaveService = null;
        model = null;
        isUpdate = null;
        isRenderAvailabilityDate = null;
        attendanceStatusService = null;
    }

    public LeaveModel getModel() {
        return model;
    }

    public void setModel(LeaveModel model) {
        this.model = model;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public Boolean getIsRenderAvailabilityDate() {
		return isRenderAvailabilityDate;
	}

	public void setIsRenderAvailabilityDate(Boolean isRenderAvailabilityDate) {
		this.isRenderAvailabilityDate = isRenderAvailabilityDate;
	}

	public void setLeaveService(LeaveService leaveService) {
		this.leaveService = leaveService;
	}
	
	public void setAttendanceStatusService(AttendanceStatusService attendanceStatusService) {
		this.attendanceStatusService = attendanceStatusService;
	}

	public void doReset() {
    	if(isUpdate) {
    		try {
    			Leave leave = leaveService.getEntiyByPK(model.getId());
    			if (leave != null) {
                    getModelFromEntity(leave);
    			}
    		} catch (Exception ex) {
    			LOGGER.error("Error", ex);
    		}
    	} else {
    		model = new LeaveModel();
    	}    	
    }

    public String doSave() {
        Leave leave = getEntityFromViewModel(model);
        try {
            if (isUpdate) {
                leaveService.update(leave);
                MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.update_successfully",
                        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

            } else {
                leaveService.save(leave);
                MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully",
                        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            }
            return "/protected/working_time/leave_detail.htm?faces-redirect=true&execution=e" + leave.getId();
        } catch (BussinessException ex) { //data already exist(duplicate)
            LOGGER.error("Error", ex);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
        return null;
    }

    private Leave getEntityFromViewModel(LeaveModel model) {
        Leave leave = new Leave();
        if (model.getId() != null) {
            leave.setId(model.getId());
        }
        leave.setCode(model.getCode());
        leave.setName(model.getName());
        leave.setDescription(model.getDescription());

        return leave;
    }

    private void getModelFromEntity(Leave leave) {
        model.setId(leave.getId());
        model.setCode(leave.getCode());
        model.setName(leave.getName());
        model.setDescription(leave.getDescription());
    }

    public String doBack() {
        return "/protected/working_time/leave_view.htm?faces-redirect=true";
    }
    
    public void onChangeAvailabilty(){
    	isRenderAvailabilityDate = (model.getAvailability()!=null && 
    			model.getAvailability() == HRMConstant.LEAVE_AVAILABILITY_INCREASES_CERTAIN_DATE);
    }
}
