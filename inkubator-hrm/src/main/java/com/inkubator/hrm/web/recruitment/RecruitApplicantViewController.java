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
import com.inkubator.hrm.entity.RecruitApplicant;
import com.inkubator.hrm.service.RecruitApplicantService;
import com.inkubator.hrm.web.lazymodel.RecruitApplicantLazyDataModel;
import com.inkubator.hrm.web.search.RecruitApplicantSearchParameter;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "recruitApplicantViewController")
@ViewScoped
public class RecruitApplicantViewController extends BaseController {

    private RecruitApplicantSearchParameter parameter;
    private LazyDataModel<RecruitApplicant> lazyData;
    private RecruitApplicant selected;
    @ManagedProperty(value = "#{recruitApplicantService}")
    private RecruitApplicantService recruitApplicantService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        parameter = new RecruitApplicantSearchParameter();
    }

    @PreDestroy
    public void cleanAndExit() {
    	recruitApplicantService = null;
        parameter = null;
        lazyData = null;
        selected = null;
    }

	public void doSearch() {
        lazyData = null;
    }

    public String doDetail() {
        return "/protected/recruitment/recruit_applicant_detail.htm?faces-redirect=true&execution=e" + selected.getId();
    }
    
    public String doAdd() {
    	return "/protected/recruitment/recruit_applicant_form.htm?faces-redirect=true";
    }

    public String doUpdate() {
    	return "/protected/recruitment/recruit_applicant_form.htm?faces-redirect=true&execution=e" + selected.getId();
    }

    public void doSelectEntity() {
        try {
            selected = this.recruitApplicantService.getEntityByPkWithDetail(selected.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public void doDelete() {
        try {
        	recruitApplicantService.delete(selected);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            LOGGER.error("Error ", ex);
        } catch (Exception ex) {
            LOGGER.error("Error ", ex);
        }
    }

	public RecruitApplicantSearchParameter getParameter() {
		return parameter;
	}

	public void setParameter(RecruitApplicantSearchParameter parameter) {
		this.parameter = parameter;
	}

	public LazyDataModel<RecruitApplicant> getLazyData() {
		if(lazyData == null) {
			lazyData = new RecruitApplicantLazyDataModel(parameter, recruitApplicantService);
		}
		return lazyData;
	}

	public void setLazyData(LazyDataModel<RecruitApplicant> lazyData) {		
		this.lazyData = lazyData;
	}

	public RecruitApplicant getSelected() {
		return selected;
	}

	public void setSelected(RecruitApplicant selected) {
		this.selected = selected;
	}

	public RecruitApplicantService getRecruitApplicantService() {
		return recruitApplicantService;
	}

	public void setRecruitApplicantService(RecruitApplicantService recruitApplicantService) {
		this.recruitApplicantService = recruitApplicantService;
	}
    
}
