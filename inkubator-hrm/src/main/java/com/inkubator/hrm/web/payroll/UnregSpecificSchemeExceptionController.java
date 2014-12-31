/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.payroll;

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
import com.inkubator.hrm.entity.UnregPayComponents;
import com.inkubator.hrm.entity.UnregPayComponentsException;
import com.inkubator.hrm.entity.UnregPayComponentsExceptionId;
import com.inkubator.hrm.service.UnregPayComponentsExceptionService;
import com.inkubator.hrm.service.UnregPayComponentsService;
import com.inkubator.hrm.web.lazymodel.UnregSpecificSchemeExceptionLazyDataModel;
import com.inkubator.hrm.web.search.UnregPayComponentExceptionSearchParameter;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "unregSpecificSchemeExceptionController")
@ViewScoped
public class UnregSpecificSchemeExceptionController extends BaseController {

    @ManagedProperty(value = "#{unregPayComponentsExceptionService}")
    private UnregPayComponentsExceptionService unregPayComponentsExceptionService;
    @ManagedProperty(value = "#{unregPayComponentsService}")
    private UnregPayComponentsService unregPayComponentsService;
    private UnregPayComponentExceptionSearchParameter searchParameter;
    private LazyDataModel<UnregPayComponentsException> lazyDataModel;
    private UnregPayComponentsException selected;
    private UnregPayComponents unregPayComponents;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try {
	        searchParameter = new UnregPayComponentExceptionSearchParameter();
	        Long unregPayComponentsId = Long.parseLong(FacesUtil.getRequestParameter("execution").substring(1));
	        unregPayComponents = unregPayComponentsService.getEntityByPkWithDetail(unregPayComponentsId);
	        searchParameter.setUnregPayComponentsId(unregPayComponentsId);
    	} catch (Exception e) {
    		LOGGER.error("Error", e);
    	}
    }

    @PreDestroy
    private void cleanAndExit() {
        searchParameter = null;
        lazyDataModel = null;
        unregPayComponentsExceptionService = null;
        selected = null;
        unregPayComponents = null;
        unregPayComponentsService = null;
    }

    public void doSearch() {
        lazyDataModel = null;
    }

    public void doSelectEntity() {
        try {
            selected = this.unregPayComponentsExceptionService.getEntityByPK(selected.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }
        
    public String doBack(){
    	return "/protected/payroll/unreg_specific_scheme_detail.htm?faces-redirect=true&execution=e" + unregPayComponents.getUnregSalary().getId();
    }
    
    public void doAdd() {
    	Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> unregPayComponentsId = new ArrayList<>();
        unregPayComponentsId.add(String.valueOf(unregPayComponents.getId()));
        dataToSend.put("unregPayComponentsId", unregPayComponentsId);
        showDialog(dataToSend);
    }

    public void doUpdate() {
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> unregPayComponentsId = new ArrayList<>();
        unregPayComponentsId.add(String.valueOf(unregPayComponents.getId()));
        dataToSend.put("unregPayComponentsId", unregPayComponentsId);
        List<String> empDataId = new ArrayList<>();
        empDataId.add(String.valueOf(selected.getId().getEmpDataId()));
        dataToSend.put("empDataId", empDataId);
        showDialog(dataToSend);
    }

    private void showDialog(Map<String, List<String>> params) {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 400);
        options.put("contentHeight", 250);
        RequestContext.getCurrentInstance().openDialog("unreg_specific_scheme_exception_form", options, params);
    }
    
    @Override
    public void onDialogReturn(SelectEvent event) {
        //re-calculate searching
        doSearch();
        super.onDialogReturn(event);
    }
    
    public void doDetail() {
        try {
        	UnregPayComponentsExceptionId id = new UnregPayComponentsExceptionId(selected.getUnregPayComponents().getId(), selected.getEmpData().getId());
        	selected = this.unregPayComponentsExceptionService.getEntityByPK(id);
        	System.out.println("s");
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public void doDelete() {
        try {
        	unregPayComponentsExceptionService.delete(selected);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            LOGGER.error("Error when doDelete religion ", ex);
        } catch (Exception ex) {
            LOGGER.error("Error when doDelete religion", ex);
        }
    }

	public UnregPayComponentsExceptionService getUnregPayComponentsExceptionService() {
		return unregPayComponentsExceptionService;
	}

	public void setUnregPayComponentsExceptionService(UnregPayComponentsExceptionService unregPayComponentsExceptionService) {
		this.unregPayComponentsExceptionService = unregPayComponentsExceptionService;
	}

	public UnregPayComponentExceptionSearchParameter getSearchParameter() {
		return searchParameter;
	}

	public void setSearchParameter(UnregPayComponentExceptionSearchParameter searchParameter) {
		this.searchParameter = searchParameter;
	}

	public LazyDataModel<UnregPayComponentsException> getLazyDataModel() {
		if(lazyDataModel == null) {
			lazyDataModel =  new UnregSpecificSchemeExceptionLazyDataModel(searchParameter, unregPayComponentsExceptionService);
		}
		return lazyDataModel;
	}

	public void setLazyDataModel(LazyDataModel<UnregPayComponentsException> lazyDataModel) {
		this.lazyDataModel = lazyDataModel;
	}

	public UnregPayComponentsException getSelected() {
		return selected;
	}

	public void setSelected(UnregPayComponentsException selected) {
		this.selected = selected;
	}

	public UnregPayComponentsService getUnregPayComponentsService() {
		return unregPayComponentsService;
	}

	public void setUnregPayComponentsService(
			UnregPayComponentsService unregPayComponentsService) {
		this.unregPayComponentsService = unregPayComponentsService;
	}

	public UnregPayComponents getUnregPayComponents() {
		return unregPayComponents;
	}

	public void setUnregPayComponents(UnregPayComponents unregPayComponents) {
		this.unregPayComponents = unregPayComponents;
	}
    
}
