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
import com.inkubator.hrm.entity.PermitImplementation;
import com.inkubator.hrm.service.PermitImplementationService;
import com.inkubator.hrm.web.lazymodel.PermitImplementationLazyDataModel;
import com.inkubator.hrm.web.search.PermitImplementationSearchParameter;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
 *
 * @author Taufik
 */
@ManagedBean(name = "permitImplementationViewController")
@ViewScoped
public class PermitImplementationViewController extends BaseController {

    private PermitImplementationSearchParameter searchParameter;
    private LazyDataModel<PermitImplementation> lazyDataPermitImplementation;
    private PermitImplementation selectedPermitImplementation;
    @ManagedProperty(value = "#{permitImplementationService}")
    private PermitImplementationService permitImplementationService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        searchParameter = new PermitImplementationSearchParameter();
    }

    @PreDestroy
    public void cleanAndExit() {
    	permitImplementationService = null;
        searchParameter = null;
        lazyDataPermitImplementation = null;
        selectedPermitImplementation = null;
    }

    
    public LazyDataModel<PermitImplementation> getLazyDataPermitImplementation() {
    	if(lazyDataPermitImplementation == null){
    		lazyDataPermitImplementation = new PermitImplementationLazyDataModel(searchParameter, permitImplementationService);
    	}
		return lazyDataPermitImplementation;
	}

	public void setLazyDataPermitImplementation(LazyDataModel<PermitImplementation> lazyDataPermitImplementation) {
		this.lazyDataPermitImplementation = lazyDataPermitImplementation;
	}

	public PermitImplementation getSelectedPermitImplementation() {
		return selectedPermitImplementation;
	}

	public void setSelectedPermitImplementation(
			PermitImplementation selectedPermitImplementation) {
		this.selectedPermitImplementation = selectedPermitImplementation;
	}

	public void setPermitImplementationService(
			PermitImplementationService permitImplementationService) {
		this.permitImplementationService = permitImplementationService;
	}

	public PermitImplementationSearchParameter getSearchParameter() {
		return searchParameter;
	}

	public void setSearchParameter(
			PermitImplementationSearchParameter searchParameter) {
		this.searchParameter = searchParameter;
	}

	public void doSearch() {
        lazyDataPermitImplementation = null;
    }

    public String doDetail() {
        return "/protected/working_time/permit_impl_detail.htm?faces-redirect=true&execution=e" + selectedPermitImplementation.getId();
    }

    public void doSelectEntity() {
        try {
            selectedPermitImplementation = this.permitImplementationService.getEntiyByPK(selectedPermitImplementation.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public void doDelete() {
        try {
            permitImplementationService.delete(selectedPermitImplementation);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            LOGGER.error("Error when doDelete Permit", ex);
        } catch (Exception ex) {
            LOGGER.error("Error when doDelete Permit", ex);
        }
    }
    
    public String doCancellation() {
    	return "/protected/working_time/permit_implementation_cancel.htm?faces-redirect=true&execution=e" + selectedPermitImplementation.getId();
    }

    public String doAdd() {
        return "/protected/working_time/permit_impl_form.htm?faces-redirect=true";
    }

    public String doUpdate() {
        return "/protected/working_time/permit_impl_form.htm?faces-redirect=true&execution=e" + selectedPermitImplementation.getId();
    }
}
