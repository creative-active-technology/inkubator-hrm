/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.personalia;

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
import org.springframework.dao.DataIntegrityViolationException;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.Company;
import com.inkubator.hrm.entity.CompanyBankAccount;
import com.inkubator.hrm.entity.FinancialPartner;
import com.inkubator.hrm.entity.OhsaEmpInvolve;
import com.inkubator.hrm.entity.OhsaIncident;
import com.inkubator.hrm.entity.OhsaIncidentDocument;
import com.inkubator.hrm.service.CompanyBankAccountService;
import com.inkubator.hrm.service.CompanyService;
import com.inkubator.hrm.service.FinancialPartnerService;
import com.inkubator.hrm.service.OhsaCategoryService;
import com.inkubator.hrm.service.OhsaEmpInvolveService;
import com.inkubator.hrm.service.OhsaIncidentDocumentService;
import com.inkubator.hrm.service.OhsaIncidentService;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
@ManagedBean(name = "ohsaIncidentDetailController")
@ViewScoped
public class OhsaIncidentDetailController extends BaseController {

    private OhsaIncident selectedIncident;
    private OhsaEmpInvolve selectedOhsaEmpInvolve;
    private OhsaIncidentDocument selectedOhsaIncidentDocument;
    private List<OhsaEmpInvolve> listOhsaEmpInvolve;
    private List<OhsaIncidentDocument> listOhsaIncidentDocument;
    @ManagedProperty(value = "#{ohsaIncidentService}")
    private OhsaIncidentService ohsaIncidentService;
    @ManagedProperty(value = "#{ohsaCategoryService}")
    private OhsaCategoryService ohsaCategoryService;
    @ManagedProperty(value = "#{ohsaEmpInvolveService}")
    private OhsaEmpInvolveService ohsaEmpInvolveService;
    @ManagedProperty(value = "#{ohsaIncidentDocumentService}")
    private OhsaIncidentDocumentService ohsaIncidentDocumentService;
    private String severityLevel;

    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            String id = FacesUtil.getRequestParameter("execution");
            selectedIncident = ohsaIncidentService.getEntityByPKWithDetail(Long.parseLong(id.substring(1))); 
            listOhsaEmpInvolve = ohsaEmpInvolveService.getListByOhsaIncidentId(selectedIncident.getId());
            listOhsaIncidentDocument = ohsaIncidentDocumentService.getListByOhsaIncidentId(selectedIncident.getId());
            
            if(null != selectedIncident.getSeverityLevel()){                
                switch(selectedIncident.getSeverityLevel()){
                    case 1:
                        severityLevel = "Rendah";
                        break;
                    case 2:
                        severityLevel = "Sedang";
                        break;
                    case 3:
                        severityLevel = "Tinggi";
                        break;
                        
                    default :
                        severityLevel = "-";
                        break;
                }
            }
            
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    @PreDestroy
    public void cleanAndExit() {
        selectedIncident = null;
        ohsaIncidentService = null;
        ohsaIncidentDocumentService = null;
        ohsaCategoryService = null;
        ohsaEmpInvolveService = null;        
        listOhsaEmpInvolve = null;
        listOhsaIncidentDocument = null;
        selectedOhsaEmpInvolve = null;
        selectedOhsaIncidentDocument = null;
        
    }    

    public OhsaIncident getSelectedIncident() {
        return selectedIncident;
    }

    public void setSelectedIncident(OhsaIncident selectedIncident) {
        this.selectedIncident = selectedIncident;
    }

    public OhsaEmpInvolve getSelectedOhsaEmpInvolve() {
        return selectedOhsaEmpInvolve;
    }

    public void setSelectedOhsaEmpInvolve(OhsaEmpInvolve selectedOhsaEmpInvolve) {
        this.selectedOhsaEmpInvolve = selectedOhsaEmpInvolve;
    }

    public OhsaIncidentDocument getSelectedOhsaIncidentDocument() {
        return selectedOhsaIncidentDocument;
    }

    public void setSelectedOhsaIncidentDocument(OhsaIncidentDocument selectedOhsaIncidentDocument) {
        this.selectedOhsaIncidentDocument = selectedOhsaIncidentDocument;
    }

    public List<OhsaEmpInvolve> getListOhsaEmpInvolve() {
        return listOhsaEmpInvolve;
    }

    public void setListOhsaEmpInvolve(List<OhsaEmpInvolve> listOhsaEmpInvolve) {
        this.listOhsaEmpInvolve = listOhsaEmpInvolve;
    }

    public List<OhsaIncidentDocument> getListOhsaIncidentDocument() {
        return listOhsaIncidentDocument;
    }

    public void setListOhsaIncidentDocument(List<OhsaIncidentDocument> listOhsaIncidentDocument) {
        this.listOhsaIncidentDocument = listOhsaIncidentDocument;
    }

    public OhsaIncidentService getOhsaIncidentService() {
        return ohsaIncidentService;
    }

    public void setOhsaIncidentService(OhsaIncidentService ohsaIncidentService) {
        this.ohsaIncidentService = ohsaIncidentService;
    }

    public OhsaCategoryService getOhsaCategoryService() {
        return ohsaCategoryService;
    }

    public void setOhsaCategoryService(OhsaCategoryService ohsaCategoryService) {
        this.ohsaCategoryService = ohsaCategoryService;
    }

    public OhsaEmpInvolveService getOhsaEmpInvolveService() {
        return ohsaEmpInvolveService;
    }

    public void setOhsaEmpInvolveService(OhsaEmpInvolveService ohsaEmpInvolveService) {
        this.ohsaEmpInvolveService = ohsaEmpInvolveService;
    }

    public OhsaIncidentDocumentService getOhsaIncidentDocumentService() {
        return ohsaIncidentDocumentService;
    }

    public void setOhsaIncidentDocumentService(OhsaIncidentDocumentService ohsaIncidentDocumentService) {
        this.ohsaIncidentDocumentService = ohsaIncidentDocumentService;
    }

    public String getSeverityLevel() {
        return severityLevel;
    }

    public void setSeverityLevel(String severityLevel) {
        this.severityLevel = severityLevel;
    }
    
    
	
    public String doBack() {
        return "/protected/personalia/ohsa_incident_view.htm?faces-redirect=true";
    }

    public String doUpdate() {
        return "/protected/organisation/company_form.htm?faces-redirect=true&execution=e" + selectedIncident.getId();
    }
    
    /**
     * START OhsaEmpInvolve tabView
     */
    public void doSelectOhsaEmpInvolve() {
        try {
        	selectedOhsaEmpInvolve = ohsaEmpInvolveService.getEntityByPKWithDetail(selectedOhsaEmpInvolve.getId());
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    public void doUpdateOhsaEmpInvolve() {

        List<String> listOhsaEmpInvolveId = new ArrayList<>();
        listOhsaEmpInvolveId.add(String.valueOf(selectedOhsaEmpInvolve.getId()));

        List<String> companyId = new ArrayList<>();
        companyId.add(String.valueOf(selectedIncident.getId()));

        Map<String, List<String>> dataToSend = new HashMap<>();
        dataToSend.put("listOhsaEmpInvolveId", listOhsaEmpInvolveId);
        dataToSend.put("selectedIncidentId", companyId);
        this.showDialogOhsaEmpInvolve(dataToSend);

    }

    public void doAddOhsaEmpInvolve() {
    	List<String> selectedIncidentId = new ArrayList<>();
        selectedIncidentId.add(String.valueOf(selectedIncident.getId()));

        Map<String, List<String>> dataToSend = new HashMap<>();
        dataToSend.put("selectedIncidentId", selectedIncidentId);
        this.showDialogOhsaEmpInvolve(dataToSend);
    }

    public void doDeleteOhsaEmpInvolve() {
        try {
            ohsaEmpInvolveService.delete(selectedOhsaEmpInvolve);
            listOhsaEmpInvolve = ohsaEmpInvolveService.getListByOhsaIncidentId(selectedIncident.getId());
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error when doDelete companyBankAccount", ex);
        }
    }

    private void showDialogOhsaEmpInvolve(Map<String, List<String>> params) {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 400);
        options.put("contentHeight", 350);
        RequestContext.getCurrentInstance().openDialog("ohsa_emp_involve_form", options, params);
    }

    public void onDialogReturnOhsaEmpInvolve(SelectEvent event) {
        try {
            listOhsaEmpInvolve = ohsaEmpInvolveService.getListByOhsaIncidentId(selectedIncident.getId());
            super.onDialogReturn(event);
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }
    /**
     * END OhsaEmpInvolve tabView
     */
    
    
    /**
     * START OhsaIcidentDocument tabView
     */
    public void doSelectOhsaIncidentDocument() {
        try {
        	selectedOhsaIncidentDocument = ohsaIncidentDocumentService.getEntityByPKWithDetail(selectedOhsaIncidentDocument.getId());
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    public void doUpdateOhsaIncidentDocument() {

        List<String> ohsaIncidentDocumentId = new ArrayList<>();
        ohsaIncidentDocumentId.add(String.valueOf(selectedOhsaIncidentDocument.getId()));

        List<String> selectedIncidentId = new ArrayList<>();
        selectedIncidentId.add(String.valueOf(selectedIncident.getId()));

        Map<String, List<String>> dataToSend = new HashMap<>();
        dataToSend.put("ohsaIncidentDocumentId", ohsaIncidentDocumentId);
        dataToSend.put("selectedIncidentId", selectedIncidentId);
        this.showDialogOhsaIncidentDocument(dataToSend);

    }

    public void doAddOhsaIncidentDocument() {
    	List<String> ohsaIncidentId = new ArrayList<>();
        ohsaIncidentId.add(String.valueOf(selectedIncident.getId()));

        Map<String, List<String>> dataToSend = new HashMap<>();
        dataToSend.put("ohsaIncidentId", ohsaIncidentId);
        this.showDialogOhsaIncidentDocument(dataToSend);
    }

    public void doDeleteOhsaIncidentDocument() {
        try {
        	ohsaIncidentDocumentService.delete(selectedOhsaIncidentDocument);
        	listOhsaIncidentDocument = ohsaIncidentDocumentService.getListByOhsaIncidentId(selectedIncident.getId());
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error when doDelete financialPartner", ex);
        }
    }

    private void showDialogOhsaIncidentDocument(Map<String, List<String>> params) {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 400);
        options.put("contentHeight", 500);
        RequestContext.getCurrentInstance().openDialog("ohsa_incident_doc_form", options, params);
    }

    public void onDialogReturnOhsaIncidentDocument(SelectEvent event) {
        try {
        	listOhsaIncidentDocument = ohsaIncidentDocumentService.getListByOhsaIncidentId(selectedIncident.getId());
            super.onDialogReturn(event);
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }
    /**
     * END OhsaIcidentDocument tabView
     */

}
