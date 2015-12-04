package com.inkubator.hrm.web.appraisal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.inkubator.hrm.entity.AppraisalCompetencyType;
import com.inkubator.hrm.service.AppraisalCompetencyTypeService;
import com.inkubator.hrm.web.lazymodel.AppraisalCompetencyTypeLazyDataModel;
import com.inkubator.hrm.web.search.AppraisalCompetencyTypeSearchParameter;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
*
* @author Ahmad Mudzakkir Amal
*/

@ManagedBean(name = "competenceTypeViewController")
@ViewScoped
public class CompetenceTypeViewController extends BaseController{
	@ManagedProperty(value = "#{appraisalCompetencyTypeService}")
	private AppraisalCompetencyTypeService appraisalCompetencyTypeService;
	private	AppraisalCompetencyTypeSearchParameter searchParameter;
	private LazyDataModel<AppraisalCompetencyType> lazy;
	private AppraisalCompetencyType selected;
	
	@Override
    public void initialization(){
        super.initialization();
        searchParameter = new AppraisalCompetencyTypeSearchParameter();
    }
	
	@PreDestroy
    private void cleanAndExit(){
        searchParameter = null;
        lazy = null;
        appraisalCompetencyTypeService = null;
        selected = null;
    }
	
	public void doSearch(){
        lazy = null;
    }
    
    public void doSelectEntity(){
        try{
            selected = appraisalCompetencyTypeService.getEntiyByPK(selected.getId());
        } catch (Exception ex){
            LOGGER.error("Error", ex);
        }
    }
    
    public void doDelete(){
        try{
            this.appraisalCompetencyTypeService.delete(selected);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (ConstraintViolationException | DataIntegrityViolationException ex){
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            LOGGER.error("Error", ex);
        } catch (Exception ex){
            LOGGER.error("Error", ex);
        }
    }
    
    public void showDialog(Map<String, List<String>> params){
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", true);
        options.put("contentWidth", 470);
        options.put("contentHeight", 530);
        RequestContext.getCurrentInstance().openDialog("competence_type_form", options, params);
    }
    
    public String doAdd(){
    	return "/protected/appraisal/competence_type_form.htm?faces-redirect=true";
       // showDialog(null);
    }
            
    public void doEdit(){
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> dataIsi = new ArrayList<>();
        dataIsi.add(String.valueOf(selected.getId()));
        dataToSend.put("careerAwardTypeId", dataIsi);
        showDialog(dataToSend);
    }
    
    @Override
    public void onDialogReturn(SelectEvent event){
        lazy = null;
        super.onDialogReturn(event);
    }
    
    public void onDelete(){
        try{
            selected = this.appraisalCompetencyTypeService.getEntiyByPK(selected.getId());
        } catch (Exception ex){
            LOGGER.error("Error", ex);
        }
    }

	public void setAppraisalCompetencyTypeService(AppraisalCompetencyTypeService appraisalCompetencyTypeService) {
		this.appraisalCompetencyTypeService = appraisalCompetencyTypeService;
	}

	public AppraisalCompetencyTypeSearchParameter getSearchParameter() {
		return searchParameter;
	}

	public void setSearchParameter(AppraisalCompetencyTypeSearchParameter searchParameter) {
		this.searchParameter = searchParameter;
	}

	public LazyDataModel<AppraisalCompetencyType> getLazy() {
		 if(lazy == null){
			 lazy = new AppraisalCompetencyTypeLazyDataModel(searchParameter, appraisalCompetencyTypeService);
	     }
		return lazy;
	}

	public void setLazy(LazyDataModel<AppraisalCompetencyType> lazy) {
		this.lazy = lazy;
	}

	public AppraisalCompetencyType getSelected() {
		return selected;
	}

	public void setSelected(AppraisalCompetencyType selected) {
		this.selected = selected;
	}
	
}
