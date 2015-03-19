package com.inkubator.hrm.web.personalia;

import com.inkubator.hrm.web.organisation.*;
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
import com.inkubator.hrm.entity.Company;
import com.inkubator.hrm.entity.OhsaIncident;
import com.inkubator.hrm.service.CompanyService;
import com.inkubator.hrm.service.OhsaEmpInvolveService;
import com.inkubator.hrm.service.OhsaIncidentService;
import com.inkubator.hrm.web.lazymodel.CompanyLazyDataModel;
import com.inkubator.hrm.web.lazymodel.OhsaIncidentLazyDataModel;
import com.inkubator.hrm.web.search.CompanySearchParameter;
import com.inkubator.hrm.web.search.OhsaIncidentSearchParameter;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "ohsaIncidentViewController")
@ViewScoped
public class OhsaIncidentViewController extends BaseController {

    private OhsaIncidentSearchParameter searchParameter;
    private LazyDataModel<OhsaIncident> lazyDataModel;
    private OhsaIncident selectedOhsaIncident;
    @ManagedProperty(value = "#{ohsaIncidentService}")
    private OhsaIncidentService ohsaIncidentService;
    @ManagedProperty(value = "#{ohsaEmpInvolveService}")
    private OhsaEmpInvolveService ohsaEmpInvolveService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        searchParameter = new OhsaIncidentSearchParameter();
    }

    @PreDestroy
    public void cleanAndExit() {
        ohsaIncidentService = null;
        searchParameter = null;
        lazyDataModel = null;
        selectedOhsaIncident = null;
    }

    public LazyDataModel<OhsaIncident> getLazyDataModel() {
    	if (lazyDataModel == null) {
            lazyDataModel = new OhsaIncidentLazyDataModel(searchParameter, ohsaIncidentService, ohsaEmpInvolveService);
        }
        return lazyDataModel;
	}

	public void setLazyDataModel(LazyDataModel<OhsaIncident> lazyDataModel) {
		this.lazyDataModel = lazyDataModel;
	}

	public OhsaIncidentSearchParameter getSearchParameter() {
		return searchParameter;
	}

	public void setSearchParameter(OhsaIncidentSearchParameter searchParameter) {
		this.searchParameter = searchParameter;
	}

    public OhsaIncident getSelectedOhsaIncident() {
        return selectedOhsaIncident;
    }

    public void setSelectedOhsaIncident(OhsaIncident selectedOhsaIncident) {
        this.selectedOhsaIncident = selectedOhsaIncident;
    }

    public OhsaIncidentService getOhsaIncidentService() {
        return ohsaIncidentService;
    }

    public void setOhsaIncidentService(OhsaIncidentService ohsaIncidentService) {
        this.ohsaIncidentService = ohsaIncidentService;
    }

    public OhsaEmpInvolveService getOhsaEmpInvolveService() {
        return ohsaEmpInvolveService;
    }

    public void setOhsaEmpInvolveService(OhsaEmpInvolveService ohsaEmpInvolveService) {
        this.ohsaEmpInvolveService = ohsaEmpInvolveService;
    }

    @Override
    public void onDialogReturn(SelectEvent event) {
        
        doSearch();
        super.onDialogReturn(event);
    }
	
    public void doSearch() {
        lazyDataModel = null;
    }

    public String doDetail() {
        return "/protected/personalia/ohsa_incident_detail.htm?faces-redirect=true&execution=e" + selectedOhsaIncident.getId();
    }

    public void doSelectEntity() {
        try {
            selectedOhsaIncident = this.ohsaIncidentService.getEntiyByPK(selectedOhsaIncident.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public void doDelete() {
        try {
            ohsaIncidentService.delete(selectedOhsaIncident);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            
        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            LOGGER.error("Error when doDelete Leave", ex);
        } catch (Exception ex) {
            LOGGER.error("Error when doDelete Leave", ex);
        }
    }

    public void doAdd() {
        showDialog(null);
    }

    public void doUpdate() {
        //return "/protected/organisation/company_form.htm?faces-redirect=true&execution=e" + selectedOhsaIncident.getId();
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> values = new ArrayList<>();
        values.add(String.valueOf(selectedOhsaIncident.getId()));
        dataToSend.put("param", values);
        showDialog(dataToSend);
    }
    
    private void showDialog(Map<String, List<String>> params) {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 550);
        options.put("contentHeight", 500);
        RequestContext.getCurrentInstance().openDialog("ohsa_incident_form", options, params);
    }
    
}
