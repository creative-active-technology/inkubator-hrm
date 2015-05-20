package com.inkubator.hrm.web.workingtime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.hibernate.exception.ConstraintViolationException;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.springframework.dao.DataIntegrityViolationException;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.LeaveScheme;
import com.inkubator.hrm.service.LeaveSchemeService;
import com.inkubator.hrm.web.lazymodel.LeaveSchemeLazyDataModel;
import com.inkubator.hrm.web.search.LeaveSchemeSearchParameter;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "leaveSchemeViewController")
@ViewScoped
public class LeaveSchemeViewController extends BaseController {
    
    private LeaveSchemeSearchParameter parameter;
    private LazyDataModel<LeaveScheme> lazyDataLeaveScheme;
    private LeaveScheme selectedLeaveScheme;
    @ManagedProperty(value = "#{leaveSchemeService}")
    private LeaveSchemeService leaveSchemeService;
    
    @PostConstruct
    @Override
    public void initialization() {
    	parameter = new LeaveSchemeSearchParameter();
        super.initialization();
    }
    
    @PreDestroy
    public void cleanAndExit() {
        leaveSchemeService = null;
        parameter = null;
        lazyDataLeaveScheme = null;
        selectedLeaveScheme = null;
    }

	public LeaveSchemeSearchParameter getParameter() {
		return parameter;
	}

	public void setParameter(LeaveSchemeSearchParameter parameter) {
		this.parameter = parameter;
	}

	public LazyDataModel<LeaveScheme> getLazyDataLeaveScheme() {
		if (lazyDataLeaveScheme == null) {
            lazyDataLeaveScheme = new LeaveSchemeLazyDataModel(parameter, leaveSchemeService);
        }
        return lazyDataLeaveScheme;
	}

	public void setLazyDataLeaveScheme(LazyDataModel<LeaveScheme> lazyDataLeaveScheme) {
		this.lazyDataLeaveScheme = lazyDataLeaveScheme;
	}

	public LeaveScheme getSelectedLeaveScheme() {
		return selectedLeaveScheme;
	}

	public void setSelectedLeaveScheme(LeaveScheme selectedLeaveScheme) {
		this.selectedLeaveScheme = selectedLeaveScheme;
	}

	public void setLeaveSchemeService(LeaveSchemeService leaveSchemeService) {
		this.leaveSchemeService = leaveSchemeService;
	}
    
    public void doSearch() {
        lazyDataLeaveScheme = null;
    }
    
    public void doDetail() {
        try {
            selectedLeaveScheme = this.leaveSchemeService.getEntiyByPkFetchLeave(selectedLeaveScheme.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }    
    
    public void doDelete() {
        try {
            leaveSchemeService.delete(selectedLeaveScheme);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            
        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            LOGGER.error("Error when doDelete employeeType ", ex);
        } catch (Exception ex) {
            LOGGER.error("Error when doDelete employeeType", ex);
        }
    }
    
    public void doAdd() {
        showDialog(null);
    }
    
    public void doUpdate() {
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> values = new ArrayList<>();
        values.add(String.valueOf(selectedLeaveScheme.getId()));
        dataToSend.put("param", values);
        showDialog(dataToSend);
    }
    
    private void showDialog(Map<String, List<String>> params) {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 450);
        options.put("contentHeight", 410);
        RequestContext.getCurrentInstance().openDialog("leave_scheme_form", options, params);
    }
    
    @Override
    public void onDialogReturn(SelectEvent event) {
        //re-calculate searching
        doSearch();
        super.onDialogReturn(event);
    }
}
