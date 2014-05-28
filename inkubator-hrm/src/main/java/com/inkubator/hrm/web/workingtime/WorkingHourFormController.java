package com.inkubator.hrm.web.workingtime;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.WtWorkingHour;
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
    Boolean isUpdate;
    @ManagedProperty(value = "#{wtWorkingHourService}")
    private WtWorkingHourService workingHourService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        String param = FacesUtil.getRequestParameter("execution");
        model = new WorkingHourModel();
        isUpdate = Boolean.FALSE;
        if (StringUtils.isNotEmpty(param)) {
            try {
                WtWorkingHour workingHour = workingHourService.getEntiyByPK(Long.parseLong(param.substring(1)));
                if (workingHour != null) {
                	model.setId(workingHour.getId());
                	model.setCode(workingHour.getCode());
                	model.setName(workingHour.getName());
                    isUpdate = Boolean.TRUE;
                }
            } catch (Exception e) {
                LOGGER.error("Error", e);
            }
        }
    }

    @PreDestroy
    public void cleanAndExit() {
        workingHourService = null;
        model = null;
        isUpdate = null;
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

    public void setWorkingHourService(WtWorkingHourService workingHourService) {
		this.workingHourService = workingHourService;
	}

	public void doClear() {
        //clear all data except the id (if any)
        Long tempId = model.getId();
        model = new WorkingHourModel();
        model.setId(tempId);
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
            return "/protected/working_time/working_hour_view.htm?faces-redirect=true";
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
        
        return workingHour;
    }

    public String doBack() {
        return "/protected/working_time/working_hour_view.htm?faces-redirect=true";
    }
}
