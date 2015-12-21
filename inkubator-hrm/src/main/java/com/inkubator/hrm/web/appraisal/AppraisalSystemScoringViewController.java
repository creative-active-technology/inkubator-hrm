package com.inkubator.hrm.web.appraisal;

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
import com.inkubator.hrm.entity.AppraisalSystemScoring;
import com.inkubator.hrm.service.AppraisalSystemScoringService;
import com.inkubator.hrm.web.lazymodel.AppraisalSystemScoringLazyDataModel;
import com.inkubator.hrm.web.search.AppraisalSystemScoringSearchParameter;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

@ManagedBean(name = "appraisalSystemScoringViewController")
@ViewScoped
public class AppraisalSystemScoringViewController extends BaseController  {
    
	@ManagedProperty(value = "#{appraisalSystemScoringService}")
    private AppraisalSystemScoringService appraisalSystemScoringService;
	private AppraisalSystemScoringSearchParameter searchParameter;
    private LazyDataModel<AppraisalSystemScoring> lazyDataModel;
    private AppraisalSystemScoring selectedAppraisalSystemScoring;
    
    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        searchParameter = new AppraisalSystemScoringSearchParameter();
    }
    
    @PreDestroy
    private void cleanAndExit() {
        searchParameter=null;
        lazyDataModel=null;
        appraisalSystemScoringService=null;
        selectedAppraisalSystemScoring=null;
    }
    
    public void doSearch() {
    	lazyDataModel = null;
    }

    public void doSelectEntity() {
        try {
        	selectedAppraisalSystemScoring = this.appraisalSystemScoringService.getEntiyByPK(selectedAppraisalSystemScoring.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }
    
    public String doAdd(){
    	return "/protected/appraisal/appraisal_system_scoring_form.htm?faces-redirect=true";
    }
    
    public String doDetail(){
        return "/protected/appraisal/appraisal_system_scoring_detail.htm?faces-redirect=true&execution=e" + selectedAppraisalSystemScoring.getId();
    }
    
    public void doDelete() {
        try {
            this.appraisalSystemScoringService.delete(selectedAppraisalSystemScoring);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            LOGGER.error("Error", ex);
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }
    
	public AppraisalSystemScoringService getAppraisalSystemScoringService() {
		return appraisalSystemScoringService;
	}

	public void setAppraisalSystemScoringService(AppraisalSystemScoringService appraisalSystemScoringService) {
		this.appraisalSystemScoringService = appraisalSystemScoringService;
	}

	public AppraisalSystemScoringSearchParameter getSearchParameter() {
		return searchParameter;
	}

	public void setSearchParameter(AppraisalSystemScoringSearchParameter searchParameter) {
		this.searchParameter = searchParameter;
	}

	public LazyDataModel<AppraisalSystemScoring> getLazyDataModel() {
		
		if(lazyDataModel == null){
			lazyDataModel = new AppraisalSystemScoringLazyDataModel(searchParameter, appraisalSystemScoringService);
		}
		return lazyDataModel;
	}

	public void setLazyDataModel(LazyDataModel<AppraisalSystemScoring> lazyDataModel) {
		this.lazyDataModel = lazyDataModel;
	}

	public AppraisalSystemScoring getSelectedAppraisalSystemScoring() {
		return selectedAppraisalSystemScoring;
	}

	public void setSelectedAppraisalSystemScoring(AppraisalSystemScoring selectedAppraisalSystemScoring) {
		this.selectedAppraisalSystemScoring = selectedAppraisalSystemScoring;
	}
    
    
}
