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
import com.inkubator.hrm.entity.Leave;
import com.inkubator.hrm.service.LeaveService;
import com.inkubator.hrm.web.lazymodel.LeaveLazyDataModel;
import com.inkubator.hrm.web.search.LeaveSearchParameter;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "leaveViewController")
@ViewScoped
public class LeaveViewController extends BaseController {

    private LeaveSearchParameter searchParameter;
    private LazyDataModel<Leave> lazyDataLeave;
    private Leave selectedLeave;
    @ManagedProperty(value = "#{leaveService}")
    private LeaveService leaveService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        searchParameter = new LeaveSearchParameter();
    }

    @PreDestroy
    public void cleanAndExit() {
        leaveService = null;
        searchParameter = null;
        lazyDataLeave = null;
        selectedLeave = null;
    }

    public LazyDataModel<Leave> getLazyDataLeave() {
    	if (lazyDataLeave == null) {
            lazyDataLeave = new LeaveLazyDataModel(searchParameter, leaveService);
        }
        return lazyDataLeave;
	}

	public void setLazyDataLeave(LazyDataModel<Leave> lazyDataLeave) {
		this.lazyDataLeave = lazyDataLeave;
	}

	public Leave getSelectedLeave() {
		return selectedLeave;
	}

	public void setSelectedLeave(Leave selectedLeave) {
		this.selectedLeave = selectedLeave;
	}

	public void setLeaveService(LeaveService leaveService) {
		this.leaveService = leaveService;
	}

	public void setSearchParameter(LeaveSearchParameter searchParameter) {
		this.searchParameter = searchParameter;
	}

    public LeaveSearchParameter getSearchParameter() {
		return searchParameter;
	}

	public void doSearch() {
        lazyDataLeave = null;
    }

    public String doDetail() {
        return "/protected/working_time/leave_detail.htm?faces-redirect=true&execution=e" + selectedLeave.getId();
    }

    public void doSelectEntity() {
        try {
            selectedLeave = this.leaveService.getEntiyByPK(selectedLeave.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public void doDelete() {
        try {
            leaveService.delete(selectedLeave);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            LOGGER.error("Error when doDelete Leave", ex);
        } catch (Exception ex) {
            LOGGER.error("Error when doDelete Leave", ex);
        }
    }

    public String doAdd() {
        return "/protected/working_time/leave_form.htm?faces-redirect=true";
    }

    public String doUpdate() {
        return "/protected/working_time/leave_form.htm?faces-redirect=true&execution=e" + selectedLeave.getId();
    }
}
