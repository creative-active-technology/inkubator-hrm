package com.inkubator.hrm.web.organisation;

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
import com.inkubator.hrm.entity.OrganizationLetter;
import com.inkubator.hrm.service.OrganizationLetterService;
import com.inkubator.hrm.web.lazymodel.OrganizationLetterLazyDataModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "organizationLetterViewController")
@ViewScoped
public class OrganizationLetterViewController extends BaseController {
    
    private String parameter;
    private LazyDataModel<OrganizationLetter> lazyDataOrganizationLetter;
    private OrganizationLetter selectedOrganizationLetter;
    @ManagedProperty(value = "#{organizationLetterService}")
    private OrganizationLetterService organizationLetterService;
    
    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
    }
    
    @PreDestroy
    public void cleanAndExit() {
        organizationLetterService = null;
        parameter = null;
        lazyDataOrganizationLetter = null;
        selectedOrganizationLetter = null;
    }
    
    public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	public LazyDataModel<OrganizationLetter> getLazyDataOrganizationLetter() {
		if(lazyDataOrganizationLetter == null){
			lazyDataOrganizationLetter = new OrganizationLetterLazyDataModel(parameter, organizationLetterService);
		}
		return lazyDataOrganizationLetter;
	}

	public void setLazyDataOrganizationLetter(LazyDataModel<OrganizationLetter> lazyDataOrganizationLetter) {
		this.lazyDataOrganizationLetter = lazyDataOrganizationLetter;
	}

	public OrganizationLetter getSelectedOrganizationLetter() {
		return selectedOrganizationLetter;
	}

	public void setSelectedOrganizationLetter(OrganizationLetter selectedOrganizationLetter) {
		this.selectedOrganizationLetter = selectedOrganizationLetter;
	}

	public void setOrganizationLetterService(OrganizationLetterService organizationLetterService) {
		this.organizationLetterService = organizationLetterService;
	}   
    
    public void doSearch() {
        lazyDataOrganizationLetter = null;
    }
    
    public void doDetail() {
        try {
            selectedOrganizationLetter = this.organizationLetterService.getEntiyByPK(selectedOrganizationLetter.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }    
    
    public void doDelete() {
        try {
            organizationLetterService.delete(selectedOrganizationLetter);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            
        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            LOGGER.error("Error when doDelete organizationLetter ", ex);
        } catch (Exception ex) {
            LOGGER.error("Error when doDelete organizationLetter", ex);
        }
    }
    
    public void doAdd() {
        showDialog(null);
    }
    
    public void doUpdate() {
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> values = new ArrayList<>();
        values.add(String.valueOf(selectedOrganizationLetter.getId()));
        dataToSend.put("param", values);
        showDialog(dataToSend);
    }
    
    private void showDialog(Map<String, List<String>> params) {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 350);
        options.put("contentHeight", 270);
        RequestContext.getCurrentInstance().openDialog("organization_letter_form", options, params);
    }
    
    @Override
    public void onDialogReturn(SelectEvent event) {
        //re-calculate searching
        doSearch();
        super.onDialogReturn(event);
    }
}
