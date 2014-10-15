package com.inkubator.hrm.web.workingtime;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.hibernate.exception.ConstraintViolationException;
import org.primefaces.model.LazyDataModel;
import org.springframework.dao.DataIntegrityViolationException;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.LeaveImplementation;
import com.inkubator.hrm.service.LeaveImplementationService;
import com.inkubator.hrm.web.lazymodel.LeaveImplementationLazyDataModel;
import com.inkubator.hrm.web.search.LeaveImplementationSearchParameter;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "leaveImplementationViewController")
@ViewScoped
public class LeaveImplementationViewController extends BaseController {

    private LeaveImplementationSearchParameter searchParameter;
    private LazyDataModel<LeaveImplementation> lazyDataLeaveImplementation;
    private LeaveImplementation selectedLeaveImplementation;
    @ManagedProperty(value = "#{leaveImplementationService}")
    private LeaveImplementationService leaveImplementationService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        searchParameter = new LeaveImplementationSearchParameter();
    }

    @PreDestroy
    public void cleanAndExit() {
    	leaveImplementationService = null;
        searchParameter = null;
        lazyDataLeaveImplementation = null;
        selectedLeaveImplementation = null;
    }

    
    public LazyDataModel<LeaveImplementation> getLazyDataLeaveImplementation() {
    	if(lazyDataLeaveImplementation == null){
    		lazyDataLeaveImplementation = new LeaveImplementationLazyDataModel(searchParameter, leaveImplementationService);
    	}
		return lazyDataLeaveImplementation;
	}

	public void setLazyDataLeaveImplementation(LazyDataModel<LeaveImplementation> lazyDataLeaveImplementation) {
		this.lazyDataLeaveImplementation = lazyDataLeaveImplementation;
	}

	public LeaveImplementation getSelectedLeaveImplementation() {
		return selectedLeaveImplementation;
	}

	public void setSelectedLeaveImplementation(
			LeaveImplementation selectedLeaveImplementation) {
		this.selectedLeaveImplementation = selectedLeaveImplementation;
	}

	public void setLeaveImplementationService(
			LeaveImplementationService leaveImplementationService) {
		this.leaveImplementationService = leaveImplementationService;
	}

	public LeaveImplementationSearchParameter getSearchParameter() {
		return searchParameter;
	}

	public void setSearchParameter(
			LeaveImplementationSearchParameter searchParameter) {
		this.searchParameter = searchParameter;
	}

	public void doSearch() {
        lazyDataLeaveImplementation = null;
    }

    public String doDetail() {
        return "/protected/working_time/leave_implementation_detail.htm?faces-redirect=true&execution=e" + selectedLeaveImplementation.getId();
    }

    public void doSelectEntity() {
        try {
            selectedLeaveImplementation = this.leaveImplementationService.getEntiyByPK(selectedLeaveImplementation.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public void doDelete() {
        try {
            leaveImplementationService.delete(selectedLeaveImplementation);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            LOGGER.error("Error when doDelete Leave", ex);
        } catch (Exception ex) {
            LOGGER.error("Error when doDelete Leave", ex);
        }
    }

    public String doAdd() {
        return "/protected/working_time/leave_implementation_form.htm?faces-redirect=true";
    }

    public String doUpdate() {
        return "/protected/working_time/leave_implementation_form.htm?faces-redirect=true&execution=e" + selectedLeaveImplementation.getId();
    }
}
