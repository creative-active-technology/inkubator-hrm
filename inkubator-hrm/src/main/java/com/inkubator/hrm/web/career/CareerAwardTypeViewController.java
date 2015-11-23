package com.inkubator.hrm.web.career;

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
import com.inkubator.hrm.entity.CareerAwardType;
import com.inkubator.hrm.service.CareerAwardTypeService;
import com.inkubator.hrm.web.lazymodel.CareerAwardTypeLazyDataModel;
import com.inkubator.hrm.web.search.CareerAwardTypeSearchParameter;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

@ManagedBean(name = "careerAwardTypeViewController")
@ViewScoped
public class CareerAwardTypeViewController extends BaseController{
	@ManagedProperty(value = "#{careerAwardTypeService}")
	private CareerAwardTypeService service;
	private	CareerAwardTypeSearchParameter searchParameter;
	private LazyDataModel<CareerAwardType> lazy;
	private CareerAwardType selected;
	
	@Override
    public void initialization(){
        super.initialization();
        searchParameter = new CareerAwardTypeSearchParameter();
    }
	
	@PreDestroy
    private void cleanAndExit(){
        searchParameter = null;
        lazy = null;
        service = null;
        selected = null;
    }
	
	public void doSearch(){
        lazy = null;
    }
    
    public void doSelectEntity(){
        try{
            selected = this.service.getEntityByPkWithDetail(selected.getId());
        } catch (Exception ex){
            LOGGER.error("Error", ex);
        }
    }
    
    public void doDelete(){
        try{
            this.service.delete(selected);
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
        RequestContext.getCurrentInstance().openDialog("career_award_form", options, params);
    }
    
    public void doAdd(){
        showDialog(null);
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
            selected = this.service.getEntiyByPK(selected.getId());
        } catch (Exception ex){
            LOGGER.error("Error", ex);
        }
    }

	public CareerAwardTypeService getService() {
		return service;
	}

	public void setService(CareerAwardTypeService service) {
		this.service = service;
	}

	public CareerAwardTypeSearchParameter getSearchParameter() {
		return searchParameter;
	}

	public void setSearchParameter(CareerAwardTypeSearchParameter searchParameter) {
		this.searchParameter = searchParameter;
	}

	public LazyDataModel<CareerAwardType> getLazy() {
		 if(lazy == null){
			 lazy = new CareerAwardTypeLazyDataModel(searchParameter, service);
	     }
		return lazy;
	}

	public void setLazy(LazyDataModel<CareerAwardType> lazy) {
		this.lazy = lazy;
	}

	public CareerAwardType getSelected() {
		return selected;
	}

	public void setSelected(CareerAwardType selected) {
		this.selected = selected;
	}
    
    
	
}
