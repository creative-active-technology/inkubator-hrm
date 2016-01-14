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
import com.inkubator.hrm.entity.AppraisalProgram;
import com.inkubator.hrm.service.AppraisalProgramService;
import com.inkubator.hrm.web.lazymodel.AppraisalProgramLazyDataModel;
import com.inkubator.hrm.web.search.AppraisalProgramSearchParameter;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
*
* @author rizkykojek
*/

@ManagedBean(name = "appraisalProgramViewController")
@ViewScoped
public class AppraisalProgramViewController extends BaseController{
	
	private	AppraisalProgramSearchParameter searchParameter;
	private LazyDataModel<AppraisalProgram> lazy;
	private AppraisalProgram selected;
	
	@ManagedProperty(value = "#{appraisalProgramService}")
	private AppraisalProgramService appraisalProgramService;
	
	@Override
    public void initialization(){
        super.initialization();
        searchParameter = new AppraisalProgramSearchParameter();
    }
	
	@PreDestroy
    private void cleanAndExit(){
        searchParameter = null;
        lazy = null;
        appraisalProgramService = null;
        selected = null;
    }
	
	public void doSearch(){
        lazy = null;
    }
    
    public void doDelete(){
        try{
            this.appraisalProgramService.delete(selected);
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
            selected = appraisalProgramService.getEntityByIdWithDetail(selected.getId());
        } catch (Exception ex){
            LOGGER.error("Error", ex);
        }
    }
    
    public String doAdd(){
    	return "/protected/appraisal/appraisal_program_form.htm?faces-redirect=true";
    }
            
    public String doEdit(){
    	return "/protected/appraisal/appraisal_program_form.htm?faces-redirect=true&execution=e" + selected.getId();
    }
    
    public String doDetail(){
    	return "/protected/appraisal/appraisal_program_detail.htm?faces-redirect=true&execution=e" + selected.getId();
    }

	public AppraisalProgramSearchParameter getSearchParameter() {
		return searchParameter;
	}

	public void setSearchParameter(AppraisalProgramSearchParameter searchParameter) {
		this.searchParameter = searchParameter;
	}

	public LazyDataModel<AppraisalProgram> getLazy() {
		if(lazy == null){
			lazy = new AppraisalProgramLazyDataModel(searchParameter, appraisalProgramService);
		}
		return lazy;
	}

	public void setLazy(LazyDataModel<AppraisalProgram> lazy) {
		this.lazy = lazy;
	}

	public AppraisalProgram getSelected() {
		return selected;
	}

	public void setSelected(AppraisalProgram selected) {
		this.selected = selected;
	}

	public AppraisalProgramService getAppraisalProgramService() {
		return appraisalProgramService;
	}

	public void setAppraisalProgramService(AppraisalProgramService appraisalProgramService) {
		this.appraisalProgramService = appraisalProgramService;
	}
   
        
        public String doDistribusiJabatan(){
            return "/protected/appraisal/appraisal_program_employee.htm?faces-redirect=true&execution=e" + selected.getId();
      
        }
}
