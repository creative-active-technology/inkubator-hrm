package com.inkubator.hrm.web.appraisal;

import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.hibernate.exception.ConstraintViolationException;
import org.primefaces.model.LazyDataModel;
import org.springframework.dao.DataIntegrityViolationException;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.AppraisalCompetencyGroup;
import com.inkubator.hrm.service.AppraisalCompetencyGroupService;
import com.inkubator.hrm.web.lazymodel.CompetencyGroupLazyDataModel;
import com.inkubator.hrm.web.search.CompetencyGroupSearchParameter;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
*
* @author rizkykojek
*/

@ManagedBean(name = "competencyGroupViewController")
@ViewScoped
public class CompetencyGroupViewController extends BaseController{
	
	private	CompetencyGroupSearchParameter searchParameter;
	private LazyDataModel<AppraisalCompetencyGroup> lazy;
	private AppraisalCompetencyGroup selected;
	
	@ManagedProperty(value = "#{appraisalCompetencyGroupService}")
	private AppraisalCompetencyGroupService appraisalCompetencyGroupService;
	
	@Override
    public void initialization(){
        super.initialization();
        searchParameter = new CompetencyGroupSearchParameter();
    }
	
	@PreDestroy
    private void cleanAndExit(){
        searchParameter = null;
        lazy = null;
        appraisalCompetencyGroupService = null;
        selected = null;
    }
	
	public void doSearch(){
        lazy = null;
    }
    
    public void doDelete(){
        try{
            this.appraisalCompetencyGroupService.delete(selected);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (ConstraintViolationException | DataIntegrityViolationException ex){
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            LOGGER.error("Error", ex);
        } catch (BussinessException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex){
            LOGGER.error("Error", ex);
        }
    }
    
    public void doSelectEntity(){
        try{
            selected = appraisalCompetencyGroupService.getEntityByIdWithDetail(selected.getId());
        } catch (Exception ex){
            LOGGER.error("Error", ex);
        }
    }
    
    public String doAdd(){
    	return "/protected/appraisal/competency_group_form.htm?faces-redirect=true";
    }
            
    public String doEdit(){
    	return "/protected/appraisal/competency_group_form.htm?faces-redirect=true&execution=e" + selected.getId();
    }

	public CompetencyGroupSearchParameter getSearchParameter() {
		return searchParameter;
	}

	public void setSearchParameter(CompetencyGroupSearchParameter searchParameter) {
		this.searchParameter = searchParameter;
	}

	public LazyDataModel<AppraisalCompetencyGroup> getLazy() {
		if(lazy == null){
			lazy = new CompetencyGroupLazyDataModel(searchParameter, appraisalCompetencyGroupService);
		}
		return lazy;
	}

	public void setLazy(LazyDataModel<AppraisalCompetencyGroup> lazy) {
		this.lazy = lazy;
	}

	public AppraisalCompetencyGroup getSelected() {
		return selected;
	}

	public void setSelected(AppraisalCompetencyGroup selected) {
		this.selected = selected;
	}

	public AppraisalCompetencyGroupService getAppraisalCompetencyGroupService() {
		return appraisalCompetencyGroupService;
	}

	public void setAppraisalCompetencyGroupService(AppraisalCompetencyGroupService appraisalCompetencyGroupService) {
		this.appraisalCompetencyGroupService = appraisalCompetencyGroupService;
	}
    
}
