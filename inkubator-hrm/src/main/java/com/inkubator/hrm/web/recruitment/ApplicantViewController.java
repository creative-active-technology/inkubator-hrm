package com.inkubator.hrm.web.recruitment;

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
import com.inkubator.hrm.entity.Applicant;
import com.inkubator.hrm.service.ApplicantService;
import com.inkubator.hrm.web.lazymodel.ApplicantLazyDataModel;
import com.inkubator.hrm.web.search.ApplicantSearchParameter;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "applicantViewController")
@ViewScoped
public class ApplicantViewController extends BaseController {

    private ApplicantSearchParameter parameter;
    private LazyDataModel<Applicant> lazyData;
    private Applicant selected;
    @ManagedProperty(value = "#{applicantService}")
    private ApplicantService applicantService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        parameter = new ApplicantSearchParameter();
    }

    @PreDestroy
    public void cleanAndExit() {
    	applicantService = null;
        parameter = null;
        lazyData = null;
        selected = null;
    }

	public void doSearch() {
        lazyData = null;
    }

    public String doDetail() {
        return "/protected/recruitment/applicant_detail.htm?faces-redirect=true&execution=e" + selected.getId();
    }
    
    public String doAdd() {
    	return "/protected/recruitment/applicant_form.htm?faces-redirect=true";
    }

    public String doUpdate() {
    	return "/protected/recruitment/applicant_form.htm?faces-redirect=true&execution=e" + selected.getId();
    }

    public void doSelectEntity() {
        try {
            selected = this.applicantService.getEntiyByPK(selected.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public void doDelete() {
        try {
        	applicantService.delete(selected);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            LOGGER.error("Error ", ex);
        } catch (Exception ex) {
            LOGGER.error("Error ", ex);
        }
    }

	public ApplicantSearchParameter getParameter() {
		return parameter;
	}

	public void setParameter(ApplicantSearchParameter parameter) {
		this.parameter = parameter;
	}

	public LazyDataModel<Applicant> getLazyData() {
		return lazyData;
	}

	public void setLazyData(LazyDataModel<Applicant> lazyData) {
		if(lazyData == null) {
			lazyData = new ApplicantLazyDataModel(parameter, applicantService);
		}
		this.lazyData = lazyData;
	}

	public Applicant getSelected() {
		return selected;
	}

	public void setSelected(Applicant selected) {
		this.selected = selected;
	}

	public ApplicantService getApplicantService() {
		return applicantService;
	}

	public void setApplicantService(ApplicantService applicantService) {
		this.applicantService = applicantService;
	}
    
}
