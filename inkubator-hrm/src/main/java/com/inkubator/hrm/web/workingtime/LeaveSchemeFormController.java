package com.inkubator.hrm.web.workingtime;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.Leave;
import com.inkubator.hrm.entity.LeaveScheme;
import com.inkubator.hrm.entity.WtWorkingHour;
import com.inkubator.hrm.service.LeaveSchemeService;
import com.inkubator.hrm.web.model.LeaveSchemeModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "leaveSchemeFormController")
@ViewScoped
public class LeaveSchemeFormController extends BaseController {

    private LeaveSchemeModel model;
    private Boolean isUpdate;
    @ManagedProperty(value = "#{leaveSchemeService}")
    private LeaveSchemeService leaveSchemeService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        String param = FacesUtil.getRequestParameter("param");
        model = new LeaveSchemeModel();
        isUpdate = Boolean.FALSE;
        if (StringUtils.isNumeric(param)) {
            try {
                LeaveScheme leaveScheme = leaveSchemeService.getEntiyByPK(Long.parseLong(param));
                if (leaveScheme != null) {
                    getModelFromEntity(leaveScheme);
                    isUpdate = Boolean.TRUE;
                }
            } catch (Exception e) {
                LOGGER.error("Error", e);
            }
        }
    }

    @PreDestroy
    public void cleanAndExit() {
        leaveSchemeService = null;
        model = null;
        isUpdate = null;
    }

	public LeaveSchemeModel getModel() {
		return model;
	}

	public void setModel(LeaveSchemeModel model) {
		this.model = model;
	}

	public Boolean getIsUpdate() {
		return isUpdate;
	}

	public void setIsUpdate(Boolean isUpdate) {
		this.isUpdate = isUpdate;
	}

	public void setLeaveSchemeService(LeaveSchemeService leaveSchemeService) {
		this.leaveSchemeService = leaveSchemeService;
	}

	public void doSave() {
        LeaveScheme leaveScheme = getEntityFromViewModel(model);
        try {
            if (isUpdate) {
                leaveSchemeService.update(leaveScheme);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                leaveSchemeService.save(leaveScheme);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch (BussinessException ex) { //data already exist(duplicate)
            LOGGER.error("Error", ex);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    private LeaveScheme getEntityFromViewModel(LeaveSchemeModel model) {
    	LeaveScheme leaveScheme = new LeaveScheme();
    	if(model.getId() != null){
    		leaveScheme.setId(model.getId());
    	}
    	leaveScheme.setCode(model.getCode());
    	leaveScheme.setName(model.getName());
    	leaveScheme.setDescription(model.getDescription());
    	leaveScheme.setTotalDays(model.getTotalDays());
    	
    	Leave leave = new Leave();
    	leave.setId(model.getId());
    	leaveScheme.setLeave(leave);
    	
        return leaveScheme;
    }
    
    private void getModelFromEntity(LeaveScheme leaveScheme) {
    	model.setId(leaveScheme.getId());
    	model.setCode(leaveScheme.getCode());
    	model.setName(leaveScheme.getName());
    	model.setDescription(leaveScheme.getDescription());
    	model.setTotalDays(leaveScheme.getTotalDays());
    	model.setLeaveId(leaveScheme.getLeave().getId());
    }
}
