/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.personalia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

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
import com.inkubator.hrm.entity.BioAddress;
import com.inkubator.hrm.entity.BioData;
import com.inkubator.hrm.entity.EducationHistory;
import com.inkubator.hrm.service.BioAddressService;
import com.inkubator.hrm.service.BioDataService;
import com.inkubator.hrm.service.EducationHistoryService;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
 *
 * @author Deni Husni FR
 */


@ManagedBean(name = "bioDataDetilController")
@ViewScoped
public class BioDataDetilController extends BaseController {

    private BioData selectedBioData;
    private BioAddress selectedBioAddress;
    private List<BioAddress> bioAddresses;
    private EducationHistory selectedEduHistory;
    private List<EducationHistory> educationHistory;

    @ManagedProperty(value = "#{bioDataService}")
    private BioDataService bioDataService;
    @ManagedProperty(value = "#{bioAddressService}")
    private BioAddressService bioAddressService;
    @ManagedProperty(value = "#{educationHistoryService}")
    private EducationHistoryService educationHistoryService;
    private String userId;

    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            userId = FacesUtil.getRequestParameter("execution");
            selectedBioData = bioDataService.getEntiyByPK(Long.parseLong(userId.substring(1)));
            bioAddresses = bioAddressService.getAllDataByBioDataId(selectedBioData.getId());
            educationHistory = educationHistoryService.getAllDataByBioDataId(selectedBioData.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    @PreDestroy
    public void cleanAndExit() {
        userId = null;

    }

    public BioAddress getSelectedBioAddress() {
        return selectedBioAddress;
    }

    public EducationHistory getSelectedEduHistory() {
        return selectedEduHistory;
    }

    public void setSelectedEduHistory(EducationHistory selectedEduHistory) {
        this.selectedEduHistory = selectedEduHistory;
    }

    public List<EducationHistory> getEducationHistory() {
        return educationHistory;
    }

    public void setEducationHistory(List<EducationHistory> educationHistory) {
        this.educationHistory = educationHistory;
    }

    public EducationHistoryService getEducationHistoryService() {
        return educationHistoryService;
    }

    public void setEducationHistoryService(EducationHistoryService educationHistoryService) {
        this.educationHistoryService = educationHistoryService;
    }

   

    public void setSelectedBioAddress(BioAddress selectedBioAddress) {
        this.selectedBioAddress = selectedBioAddress;
    }

    public List<BioAddress> getBioAddresses() {
        return bioAddresses;
    }

    public void setBioAddresses(List<BioAddress> bioAddresses) {
        this.bioAddresses = bioAddresses;
    }

    public void setBioAddressService(BioAddressService bioAddressService) {
        this.bioAddressService = bioAddressService;
    }

    public void setBioDataService(BioDataService bioDataService) {
        this.bioDataService = bioDataService;
    }

    public BioData getSelectedBioData() {
        return selectedBioData;
    }

    public void setSelectedBioData(BioData selectedBioData) {
        this.selectedBioData = selectedBioData;
    }

    public String doDetail() {
        return "/protected/personalia/biodata_detail.htm?faces-redirect=true&execution=e" + selectedBioData.getId();
    }

    public void onDelete() {
        try {
            selectedBioData = this.bioDataService.getEntiyByPK(selectedBioData.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public String doEdit() {
        return null;
    }

    public String doBack() {
        return "/protected/personalia/biodata_view.htm?faces-redirect=true";
    }

    /**
     * START Bio Address method
     */
    public void doUpdateBioAddressMap() {

    }
    
    public void doSelectBioAddress(){
    	try {
			selectedBioAddress = bioAddressService.getEntiyByPK(selectedBioAddress.getId());
		} catch (Exception e) {
			LOGGER.error("Error", e);
		}
    }
    
    public void doUpdateBioAddress(){
    	
        List<String> bioAddressId = new ArrayList<>();
        bioAddressId.add(String.valueOf(selectedBioAddress.getId()));
        
        List<String> bioDataId = new ArrayList<>();
        bioDataId.add(String.valueOf(selectedBioData.getId()));
        
        Map<String, List<String>> dataToSend = new HashMap<>();
        dataToSend.put("bioAddressId", bioAddressId);
        dataToSend.put("bioDataId", bioDataId);
        showDialogBioAddress(dataToSend);

    }
    
    public void doAddBioAddress(){
    	List<String> bioDataId = new ArrayList<>();
        bioDataId.add(String.valueOf(selectedBioData.getId()));
        
        Map<String, List<String>> dataToSend = new HashMap<>();
        dataToSend.put("bioDataId", bioDataId);
        showDialogBioAddress(dataToSend);
    }
    
    public void doDeleteBioAddress(){
    	try {
    		bioAddressService.delete(selectedBioAddress);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error when doDelete bioAddress", ex);
        }
    }
    
    private void showDialogBioAddress(Map<String, List<String>> params) {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 500);
        options.put("contentHeight", 600);
        RequestContext.getCurrentInstance().openDialog("bio_address_form", options, params);
    }
    /** END Bio Address method */
 
    
    /**
     * START Bio Edu History method
     */
    public void doSelectBioEduHistory() {
        try {
            selectedEduHistory = this.educationHistoryService.getAllDataByPK(selectedEduHistory.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public void doUpdateBioEduHistory() {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 400);
        options.put("contentHeight", 370);
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> dataIsi = new ArrayList<>();
        dataIsi.add("e" + String.valueOf(selectedEduHistory.getId()));
        dataToSend.put("param", dataIsi);
        RequestContext.getCurrentInstance().openDialog("education_history_form", options, dataToSend);
    }

    public void doAddBioEduHistory() {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 400);
        options.put("contentHeight", 370);
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> dataIsi = new ArrayList<>();
        dataIsi.add("i" + String.valueOf(selectedBioData.getId()));
        dataToSend.put("param", dataIsi);
        RequestContext.getCurrentInstance().openDialog("education_history_form", options, dataToSend);
    }

    public void doDeleteBioEduHistory() {
        try {
            this.educationHistoryService.delete(selectedEduHistory);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            LOGGER.error("Error", ex);
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }
    /** END Bio Address method */
    
    
    @Override
    public void onDialogReturn(SelectEvent event) {
        try {
            educationHistory = educationHistoryService.getAllDataByBioDataId(Long.parseLong(userId.substring(1)));
            super.onDialogReturn(event);
        } catch (Exception ex) {
            Logger.getLogger(BioDataDetilController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
  
}
