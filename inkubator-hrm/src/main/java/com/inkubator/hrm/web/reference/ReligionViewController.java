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
import com.inkubator.hrm.entity.Religion;
import com.inkubator.hrm.service.ReligionService;
import com.inkubator.hrm.web.lazymodel.ReligionDataModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
*
* @author rizkykojek
*/
@ManagedBean(name = "religionViewController")
@ViewScoped
public class ReligionViewController extends BaseController {

	private String parameter;
	private LazyDataModel<Religion> lazyDataReligion;	
	private Religion selectedReligion;
	@ManagedProperty(value = "#{religionService}")
	private ReligionService religionService;
	
	@PostConstruct
    @Override
    public void initialization() {
        super.initialization();
	}
	
	@PreDestroy
    public void cleanAndExit() {
		religionService = null;
		parameter = null;
		lazyDataReligion = null;
		selectedReligion = null;
	}
	
	public void setReligionService(ReligionService religionService) {
		this.religionService = religionService;
	}

	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	public LazyDataModel<Religion> getLazyDataReligion() {
		if(lazyDataReligion == null){
			lazyDataReligion = new ReligionDataModel(parameter, religionService);
		}
		return lazyDataReligion;
	}

	public void setLazyDataReligion(LazyDataModel<Religion> lazyDataReligion) {
		this.lazyDataReligion = lazyDataReligion;
	}
	
	public Religion getSelectedReligion() {
		return selectedReligion;
	}

	public void setSelectedReligion(Religion selectedReligion) {
		this.selectedReligion = selectedReligion;
	}

	public void doSearch(){
		lazyDataReligion = null;
	}
	
	public void doDelete(){
		try {
            religionService.delete(selectedReligion);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
        	MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            LOGGER.error("Error when doDelete religion ", ex);
        } catch (Exception ex) {
        	LOGGER.error("Error when doDelete religion", ex);
		}
	}
	
	public void doAdd(){
		showDialog(null);
	}
	
	public void doUpdate(){
		Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> values = new ArrayList<>();
        values.add(String.valueOf(selectedReligion.getId()));
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
        RequestContext.getCurrentInstance().openDialog("religion_form", options, params);
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
