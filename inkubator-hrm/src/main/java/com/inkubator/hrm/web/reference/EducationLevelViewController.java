package com.inkubator.hrm.web.reference;

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
import com.inkubator.hrm.entity.EducationLevel;
import com.inkubator.hrm.service.EducationLevelService;
import com.inkubator.hrm.web.lazymodel.EducationLevelLazyDataModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
*
* @author rizkykojek
*/
@ManagedBean(name = "educationLevelViewController")
@ViewScoped
public class EducationLevelViewController extends BaseController {

	private String parameter;
	private LazyDataModel<EducationLevel> lazyDataEducationLevel;	
	private EducationLevel selectedEducationLevel;
	@ManagedProperty(value = "#{educationLevelService}")
	private EducationLevelService educationLevelService;
	
	@PostConstruct
    @Override
    public void initialization() {
        super.initialization();
	}
	
	@PreDestroy
    public void cleanAndExit() {
		educationLevelService = null;
		parameter = null;
		lazyDataEducationLevel = null;
		selectedEducationLevel = null;
	}

	public LazyDataModel<EducationLevel> getLazyDataEducationLevel() {
		if(lazyDataEducationLevel == null){
			lazyDataEducationLevel = new EducationLevelLazyDataModel(parameter, educationLevelService);
		}
		return lazyDataEducationLevel;
	}

	public void setLazyDataEducationLevel(LazyDataModel<EducationLevel> lazyDataEducationLevel) {
		this.lazyDataEducationLevel = lazyDataEducationLevel;
	}

	public EducationLevel getSelectedEducationLevel() {
		return selectedEducationLevel;
	}

	public void setSelectedEducationLevel(EducationLevel selectedEducationLevel) {
		this.selectedEducationLevel = selectedEducationLevel;
	}

	public void setEducationLevelService(EducationLevelService educationLevelService) {
		this.educationLevelService = educationLevelService;
	}

	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}
	
	public void doSearch(){
		lazyDataEducationLevel = null;
	}
	
	public void doDelete(){
		try {
            educationLevelService.delete(selectedEducationLevel);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
        	MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            LOGGER.error("Error when doDelete educationLevel ", ex);
        } catch (Exception ex) {
        	LOGGER.error("Error when doDelete educationLevel", ex);
		}
	}
	
	public void doAdd(){
		showDialog(null);
	}
	
	public void doUpdate(){
		Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> values = new ArrayList<>();
        values.add(String.valueOf(selectedEducationLevel.getId()));
        dataToSend.put("param", values);
        showDialog(dataToSend);
	}
	
	private void showDialog(Map<String, List<String>> params){
		Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 400);
        options.put("contentHeight", 250);
        RequestContext.getCurrentInstance().openDialog("education_level_form", options, params);
	}
	
	public void onDialogClose(SelectEvent event){
		//re-calculate searching
		doSearch();
		
		//show growl message
		String condition = (String) event.getObject();
		if (condition.equalsIgnoreCase(HRMConstant.SAVE_CONDITION)) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } else if (condition.equalsIgnoreCase(HRMConstant.UPDATE_CONDITION)) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.save_info", "global.update_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        }
	}	
}
