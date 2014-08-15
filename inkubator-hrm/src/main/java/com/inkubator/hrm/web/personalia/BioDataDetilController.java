/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.personalia;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.BioAddress;
import com.inkubator.hrm.entity.BioBankAccount;
import com.inkubator.hrm.entity.BioData;
import com.inkubator.hrm.entity.BioDocument;
import com.inkubator.hrm.entity.BioEducationHistory;
import com.inkubator.hrm.entity.BioEmergencyContact;
import com.inkubator.hrm.entity.BioEmploymentHistory;
import com.inkubator.hrm.entity.BioFamilyRelationship;
import com.inkubator.hrm.entity.BioIdCard;
import com.inkubator.hrm.entity.BioInsurance;
import com.inkubator.hrm.entity.BioMedicalHistory;
import com.inkubator.hrm.entity.BioPeopleInterest;
import com.inkubator.hrm.service.BioAddressService;
import com.inkubator.hrm.service.BioBankAccountService;
import com.inkubator.hrm.service.BioDataService;
import com.inkubator.hrm.service.BioDocumentService;
import com.inkubator.hrm.service.BioEducationHistoryService;
import com.inkubator.hrm.service.BioEmergencyContactService;
import com.inkubator.hrm.service.BioEmploymentHistoryService;
import com.inkubator.hrm.service.BioFamilyRelationshipService;
import com.inkubator.hrm.service.BioIdCardService;
import com.inkubator.hrm.service.BioInsuranceService;
import com.inkubator.hrm.service.BioMedicalHistoryService;
import com.inkubator.hrm.service.BioPeopleInterestService;
import com.inkubator.hrm.web.model.BioEducationHistoryViewModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
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
import org.primefaces.model.StreamedContent;
import org.springframework.dao.DataIntegrityViolationException;

/**
 *
 * @author Deni Husni FR
 */
@ManagedBean(name = "bioDataDetilController")
@ViewScoped
public class BioDataDetilController extends BaseController {

    private BioData selectedBioData;
    private BioEducationHistory selectedEduHistory;
    private BioEducationHistoryViewModel selectedBioEducationHistoryViewController;
    private List<BioEducationHistoryViewModel> educationHistory;
    @ManagedProperty(value = "#{bioDataService}")
    private BioDataService bioDataService;
    @ManagedProperty(value = "#{bioEducationHistoryService}")
    private BioEducationHistoryService educationHistoryService;
    private String userId;

//start. bio address
    private BioAddress selectedBioAddress;
    private List<BioAddress> bioAddresses;
    @ManagedProperty(value = "#{bioAddressService}")
    private BioAddressService bioAddressService;
//end. bio address

//start. bio document
    private BioDocument selectedBioDocument;
    private List<BioDocument> bioDocuments;
    @ManagedProperty(value = "#{bioDocumentService}")
    private BioDocumentService bioDocumentService;
//end. bio document

//people interest / minat
    private BioPeopleInterest selectedPeopleInterest;
    private List<BioPeopleInterest> listPeopleInterest;
    @ManagedProperty(value = "#{bioPeopleInterestService}")
    private BioPeopleInterestService peopleInterestService;
//end people interest / minat

    //start. bio insurance
    private BioInsurance selectedBioInsurance;
    private List<BioInsurance> bioInsurances;
    @ManagedProperty(value = "#{bioInsuranceService}")
    private BioInsuranceService bioInsuranceService;
//end. bio insurance

//start. bio emergency 
    private List<BioEmergencyContact> dataBioEmergencyContacs;
    @ManagedProperty(value = "#{bioEmergencyContactService}")
    private BioEmergencyContactService bioEmergencyContactService;
    private BioEmergencyContact seleBioEmergencyContact;
//end. bio emergency 

//start. report
    private List<BioData> bioDataList;
    private Map<String, Object> params;
    private String bioId;
    private StreamedContent fileContent;
//end. report

    //start. bio medical
    private BioMedicalHistory selectedBioMedicalHistory;
    private List<BioMedicalHistory> bioMedicalHistorys;
    @ManagedProperty(value = "#{bioMedicalHistoryService}")
    private BioMedicalHistoryService bioMedicalHistoryService;
//end. bio medical

    //start. bio employment
    private BioEmploymentHistory selectedBioEmploymentHistory;
    private List<BioEmploymentHistory> bioEmploymentHistorys;
    @ManagedProperty(value = "#{bioEmploymentHistoryService}")
    private BioEmploymentHistoryService bioEmploymentHistoryService;
//end. bio employment

    //start. bio family
    private BioFamilyRelationship selectedBioFamilyRelationship;
    private List<BioFamilyRelationship> bioFamilyRelationships;
    @ManagedProperty(value = "#{bioFamilyRelationshipService}")
    private BioFamilyRelationshipService bioFamilyRelationshipService;
//end. bio family

    //start. bio bank
    private BioBankAccount selectedBioBankAccount;
    private List<BioBankAccount> bioBankAccounts;
    @ManagedProperty(value = "#{bioBankAccountService}")
    private BioBankAccountService bioBankAccountService;
//end. bio bank

    //start. bio bank
    private BioIdCard selectedBioIdCard;
    private List<BioIdCard> bioIdCards;
    @ManagedProperty(value = "#{bioIdCardService}")
    private BioIdCardService bioIdCardService;
//end. bio bank

    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            userId = FacesUtil.getRequestParameter("execution");
            selectedBioData = bioDataService.getEntiyByPK(Long.parseLong(userId.substring(1)));
            bioAddresses = bioAddressService.getAllDataByBioDataId(selectedBioData.getId());
            bioDocuments = bioDocumentService.getAllDataByBioDataId(selectedBioData.getId());
            bioInsurances = bioInsuranceService.getAllDataByBioDataId(selectedBioData.getId());
            bioMedicalHistorys = bioMedicalHistoryService.getAllDataByBioDataId(selectedBioData.getId());
            bioEmploymentHistorys = bioEmploymentHistoryService.getAllDataByBioDataId(selectedBioData.getId());
            bioFamilyRelationships = bioFamilyRelationshipService.getAllDataByBioDataId(selectedBioData.getId());
            bioBankAccounts = bioBankAccountService.getAllDataByBioDataId(selectedBioData.getId());
            bioIdCards = bioIdCardService.getAllDataByBioDataId(selectedBioData.getId());
            educationHistory = educationHistoryService.getAllDataByBioDataId(selectedBioData.getId());
            listPeopleInterest = peopleInterestService.getAllDataByBioDataId(selectedBioData.getId());
            dataBioEmergencyContacs = bioEmergencyContactService.getAllDataByBioDataId(selectedBioData.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    @PreDestroy
    public void cleanAndExit() {
        selectedBioData = null;
        selectedBioAddress = null;
        bioAddresses = null;
        listPeopleInterest = null;
        selectedEduHistory = null;
        educationHistory = null;
        bioDataService = null;
        bioAddressService = null;
        educationHistoryService = null;
        userId = null;
        selectedPeopleInterest = null;
        selectedBioDocument = null;
        bioDocuments = null;
        bioDocumentService = null;
        selectedBioEducationHistoryViewController = null;
        selectedBioInsurance = null;
        bioInsurances = null;
        bioInsuranceService = null;
        seleBioEmergencyContact = null;
        bioId = null;
        selectedBioMedicalHistory = null;
        bioMedicalHistorys = null;
        bioMedicalHistoryService = null;
        selectedBioEmploymentHistory = null;
        bioEmploymentHistorys = null;
        bioEmploymentHistoryService = null;
        selectedBioFamilyRelationship = null;
        bioFamilyRelationships = null;
        bioFamilyRelationshipService = null;
        selectedBioBankAccount = null;
        bioBankAccounts = null;
        bioBankAccountService = null;
        selectedBioIdCard = null;
        bioIdCards = null;
        bioIdCardService = null;
    }

    public BioAddress getSelectedBioAddress() {
        return selectedBioAddress;
    }

    public BioEducationHistory getSelectedEduHistory() {
        return selectedEduHistory;
    }

    public void setSelectedEduHistory(BioEducationHistory selectedEduHistory) {
        this.selectedEduHistory = selectedEduHistory;
    }

    public List<BioEducationHistoryViewModel> getEducationHistory() {
        return educationHistory;
    }

    public void setEducationHistory(List<BioEducationHistoryViewModel> educationHistory) {
        this.educationHistory = educationHistory;
    }

    public BioEducationHistoryService getEducationHistoryService() {
        return educationHistoryService;
    }

    public void setEducationHistoryService(BioEducationHistoryService educationHistoryService) {
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

    public BioDocument getSelectedBioDocument() {
        return selectedBioDocument;
    }

    public void setSelectedBioDocument(BioDocument selectedBioDocument) {
        this.selectedBioDocument = selectedBioDocument;
    }

    public List<BioDocument> getBioDocuments() {
        return bioDocuments;
    }

    public void setBioDocuments(List<BioDocument> bioDocuments) {
        this.bioDocuments = bioDocuments;
    }

    public void setBioDocumentService(BioDocumentService bioDocumentService) {
        this.bioDocumentService = bioDocumentService;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public BioPeopleInterest getSelectedPeopleInterest() {
        return selectedPeopleInterest;
    }

    public void setSelectedPeopleInterest(BioPeopleInterest selectedPeopleInterest) {
        this.selectedPeopleInterest = selectedPeopleInterest;
    }

    public List<BioPeopleInterest> getListPeopleInterest() {
        return listPeopleInterest;
    }

    public void setListPeopleInterest(List<BioPeopleInterest> listPeopleInterest) {
        this.listPeopleInterest = listPeopleInterest;
    }

    public BioPeopleInterestService getPeopleInterestService() {
        return peopleInterestService;
    }

    public void setPeopleInterestService(BioPeopleInterestService peopleInterestService) {
        this.peopleInterestService = peopleInterestService;
    }

    public BioInsurance getSelectedBioInsurance() {
        return selectedBioInsurance;
    }

    public void setSelectedBioInsurance(BioInsurance selectedBioInsurance) {
        this.selectedBioInsurance = selectedBioInsurance;
    }

    public List<BioInsurance> getBioInsurances() {
        return bioInsurances;
    }

    public void setBioInsurances(List<BioInsurance> bioInsurances) {
        this.bioInsurances = bioInsurances;
    }

    public BioInsuranceService getBioInsuranceService() {
        return bioInsuranceService;
    }

    public void setBioInsuranceService(BioInsuranceService bioInsuranceService) {
        this.bioInsuranceService = bioInsuranceService;
    }

    public BioMedicalHistory getSelectedBioMedicalHistory() {
        return selectedBioMedicalHistory;
    }

    public void setSelectedBioMedicalHistory(BioMedicalHistory selectedBioMedicalHistory) {
        this.selectedBioMedicalHistory = selectedBioMedicalHistory;
    }

    public List<BioMedicalHistory> getBioMedicalHistorys() {
        return bioMedicalHistorys;
    }

    public void setBioMedicalHistorys(List<BioMedicalHistory> bioMedicalHistorys) {
        this.bioMedicalHistorys = bioMedicalHistorys;
    }

    public BioMedicalHistoryService getBioMedicalHistoryService() {
        return bioMedicalHistoryService;
    }

    public void setBioMedicalHistoryService(BioMedicalHistoryService bioMedicalHistoryService) {
        this.bioMedicalHistoryService = bioMedicalHistoryService;
    }

    public BioEmploymentHistory getSelectedBioEmploymentHistory() {
        return selectedBioEmploymentHistory;
    }

    public void setSelectedBioEmploymentHistory(BioEmploymentHistory selectedBioEmploymentHistory) {
        this.selectedBioEmploymentHistory = selectedBioEmploymentHistory;
    }

    public List<BioEmploymentHistory> getBioEmploymentHistorys() {
        return bioEmploymentHistorys;
    }

    public void setBioEmploymentHistorys(List<BioEmploymentHistory> bioEmploymentHistorys) {
        this.bioEmploymentHistorys = bioEmploymentHistorys;
    }

    public BioEmploymentHistoryService getBioEmploymentHistoryService() {
        return bioEmploymentHistoryService;
    }

    public void setBioEmploymentHistoryService(BioEmploymentHistoryService bioEmploymentHistoryService) {
        this.bioEmploymentHistoryService = bioEmploymentHistoryService;
    }

    public BioFamilyRelationship getSelectedBioFamilyRelationship() {
        return selectedBioFamilyRelationship;
    }

    public void setSelectedBioFamilyRelationship(BioFamilyRelationship selectedBioFamilyRelationship) {
        this.selectedBioFamilyRelationship = selectedBioFamilyRelationship;
    }

    public List<BioFamilyRelationship> getBioFamilyRelationships() {
        return bioFamilyRelationships;
    }

    public void setBioFamilyRelationships(List<BioFamilyRelationship> bioFamilyRelationships) {
        this.bioFamilyRelationships = bioFamilyRelationships;
    }

    public BioFamilyRelationshipService getBioFamilyRelationshipService() {
        return bioFamilyRelationshipService;
    }

    public void setBioFamilyRelationshipService(BioFamilyRelationshipService bioFamilyRelationshipService) {
        this.bioFamilyRelationshipService = bioFamilyRelationshipService;
    }

    public BioBankAccount getSelectedBioBankAccount() {
        return selectedBioBankAccount;
    }

    public void setSelectedBioBankAccount(BioBankAccount selectedBioBankAccount) {
        this.selectedBioBankAccount = selectedBioBankAccount;
    }

    public List<BioBankAccount> getBioBankAccounts() {
        return bioBankAccounts;
    }

    public void setBioBankAccounts(List<BioBankAccount> bioBankAccounts) {
        this.bioBankAccounts = bioBankAccounts;
    }

    public BioBankAccountService getBioBankAccountService() {
        return bioBankAccountService;
    }

    public void setBioBankAccountService(BioBankAccountService bioBankAccountService) {
        this.bioBankAccountService = bioBankAccountService;
    }

    public BioIdCard getSelectedBioIdCard() {
        return selectedBioIdCard;
    }

    public void setSelectedBioIdCard(BioIdCard selectedBioIdCard) {
        this.selectedBioIdCard = selectedBioIdCard;
    }

    public List<BioIdCard> getBioIdCards() {
        return bioIdCards;
    }

    public void setBioIdCards(List<BioIdCard> bioIdCards) {
        this.bioIdCards = bioIdCards;
    }

    public BioIdCardService getBioIdCardService() {
        return bioIdCardService;
    }

    public void setBioIdCardService(BioIdCardService bioIdCardService) {
        this.bioIdCardService = bioIdCardService;
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
        return "/protected/personalia/biodata_form.htm?faces-redirect=true&execution=e" + selectedBioData.getId();
    }

    public String doBack() {
        return "/protected/personalia/biodata_view.htm?faces-redirect=true";
    }

    /**
     * START Bio Address method
     */
    public void doUpdateBioAddressMap() {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 800);
        options.put("contentHeight", 500);

        Map<String, List<String>> params = new HashMap<>();
        List<String> bioAddressId = new ArrayList<>();
        bioAddressId.add(String.valueOf(selectedBioAddress.getId()));
        params.put("bioAddressId", bioAddressId);

        RequestContext.getCurrentInstance().openDialog("bio_address_map", options, params);
    }

    public void doSelectBioAddress() {
        try {
            selectedBioAddress = bioAddressService.getEntityByPKWithDetail(selectedBioAddress.getId());
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    public void doUpdateBioAddress() {

        List<String> bioAddressId = new ArrayList<>();
        bioAddressId.add(String.valueOf(selectedBioAddress.getId()));

        List<String> bioDataId = new ArrayList<>();
        bioDataId.add(String.valueOf(selectedBioData.getId()));

        Map<String, List<String>> dataToSend = new HashMap<>();
        dataToSend.put("bioAddressId", bioAddressId);
        dataToSend.put("bioDataId", bioDataId);
        showDialogBioAddress(dataToSend);

    }

    public void doAddBioAddress() {
        List<String> bioDataId = new ArrayList<>();
        bioDataId.add(String.valueOf(selectedBioData.getId()));

        Map<String, List<String>> dataToSend = new HashMap<>();
        dataToSend.put("bioDataId", bioDataId);
        showDialogBioAddress(dataToSend);
    }

    public void doDeleteBioAddress() {
        try {
            bioAddressService.delete(selectedBioAddress);
            bioAddresses = bioAddressService.getAllDataByBioDataId(selectedBioAddress.getBioData().getId());
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
        options.put("contentWidth", 900);
        options.put("contentHeight", 400);
        RequestContext.getCurrentInstance().openDialog("bio_address_form", options, params);
    }

    public void onDialogReturnBioAddress(SelectEvent event) {
        try {
            bioAddresses = bioAddressService.getAllDataByBioDataId(selectedBioData.getId());
            super.onDialogReturn(event);
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    /**
     * END Bio Address method
     */
    /**
     * START Bio Document method
     */
    public void doSelectBioDocument() {
        try {
            selectedBioDocument = bioDocumentService.getEntiyByPK(selectedBioDocument.getId());
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    public void doUpdateBioDocument() {
        List<String> bioDocumentId = new ArrayList<>();
        bioDocumentId.add(String.valueOf(selectedBioDocument.getId()));

        List<String> bioDataId = new ArrayList<>();
        bioDataId.add(String.valueOf(selectedBioData.getId()));

        Map<String, List<String>> dataToSend = new HashMap<>();
        dataToSend.put("bioDocumentId", bioDocumentId);
        dataToSend.put("bioDataId", bioDataId);
        showDialogBioDocument(dataToSend);
    }

    public void doAddBioDocument() {
        List<String> bioDataId = new ArrayList<>();
        bioDataId.add(String.valueOf(selectedBioData.getId()));

        Map<String, List<String>> dataToSend = new HashMap<>();
        dataToSend.put("bioDataId", bioDataId);
        showDialogBioDocument(dataToSend);
    }

    public void doDeleteBioDocument() {
        try {
            bioDocumentService.delete(selectedBioDocument);
            bioDocuments = bioDocumentService.getAllDataByBioDataId(selectedBioDocument.getBioData().getId());
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error when doDelete bioDocument", ex);
        }
    }

    private void showDialogBioDocument(Map<String, List<String>> params) {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 600);
        options.put("contentHeight", 420);
        RequestContext.getCurrentInstance().openDialog("bio_document_form", options, params);
    }

    public void onDialogReturnBioDocument(SelectEvent event) {
        try {
            bioDocuments = bioDocumentService.getAllDataByBioDataId(selectedBioData.getId());
            super.onDialogReturn(event);
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    /**
     * END Bio Document method
     */
    /**
     * START Bio Edu History method
     */
    public void doSelectBioEduHistory() {
        try {
            selectedEduHistory = this.educationHistoryService.getAllDataByPK(selectedBioEducationHistoryViewController.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public void doDetailBioEduHistory() {
        try {
            selectedBioEducationHistoryViewController = this.educationHistoryService.getAllByPKByController(selectedBioEducationHistoryViewController.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public void doUpdateBioEduHistory() {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 700);
        options.put("contentHeight", 430);
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> dataIsi = new ArrayList<>();
        dataIsi.add("e" + String.valueOf(selectedBioEducationHistoryViewController.getId()));
        dataToSend.put("param", dataIsi);
        RequestContext.getCurrentInstance().openDialog("bio_education_history_form", options, dataToSend);
    }

    public void doAddBioEduHistory() {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 700);
        options.put("contentHeight", 430);
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> dataIsi = new ArrayList<>();
        dataIsi.add("i" + String.valueOf(selectedBioData.getId()));
        dataToSend.put("param", dataIsi);
        RequestContext.getCurrentInstance().openDialog("bio_education_history_form", options, dataToSend);
    }

    public void doDeleteBioEduHistory() {
        try {
            this.educationHistoryService.delete(selectedEduHistory);
            educationHistory = educationHistoryService.getAllDataByBioDataId(Long.parseLong(userId.substring(1)));
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

    /**
     * END Bio Address method
     */
    /**
     * START Bio People Interest method
     */
    public void doSelectBioPeopleInterest() {
        try {
            selectedPeopleInterest = this.peopleInterestService.getAllDataByPK(selectedPeopleInterest.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public void doUpdateBioPeopleInterest() {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 400);
        options.put("contentHeight", 250);
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> dataIsi = new ArrayList<>();
        dataIsi.add("e" + String.valueOf(selectedPeopleInterest.getId()));
        dataToSend.put("param", dataIsi);
        RequestContext.getCurrentInstance().openDialog("bio_people_interest_form", options, dataToSend);
    }

    public void doAddBioPeopleInterest() {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 400);
        options.put("contentHeight", 250);
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> dataIsi = new ArrayList<>();
        dataIsi.add("i" + String.valueOf(selectedBioData.getId()));
        dataToSend.put("param", dataIsi);
        RequestContext.getCurrentInstance().openDialog("bio_people_interest_form", options, dataToSend);
    }

    public void doDeleteBioPeopleInterest() {
        try {
            this.peopleInterestService.delete(selectedPeopleInterest);
            listPeopleInterest = peopleInterestService.getAllDataByBioDataId(Long.parseLong(userId.substring(1)));
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

    /**
     * END BioPeople Interest method
     */
    @Override
    public void onDialogReturn(SelectEvent event) {
        try {
            educationHistory = educationHistoryService.getAllDataByBioDataId(Long.parseLong(userId.substring(1)));
            listPeopleInterest = peopleInterestService.getAllDataByBioDataId(Long.parseLong(userId.substring(1)));
            super.onDialogReturn(event);
        } catch (Exception ex) {
            Logger.getLogger(BioDataDetilController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public BioEducationHistoryViewModel getSelectedBioEducationHistoryViewController() {
        return selectedBioEducationHistoryViewController;
    }

    public void setSelectedBioEducationHistoryViewController(BioEducationHistoryViewModel selectedBioEducationHistoryViewController) {
        this.selectedBioEducationHistoryViewController = selectedBioEducationHistoryViewController;
    }

    public void doAddEmergencyContact() {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 500);
        options.put("contentHeight", 435);
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> dataIsi = new ArrayList<>();
        dataIsi.add("i" + String.valueOf(selectedBioData.getId()));
        dataToSend.put("param", dataIsi);
        RequestContext.getCurrentInstance().openDialog("bio_emergency_contact_form", options, dataToSend);
    }

    public void doUpdateBioContact() {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 700);
        options.put("contentHeight", 430);
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> dataIsi = new ArrayList<>();
        dataIsi.add("e" + String.valueOf(seleBioEmergencyContact.getId()));
        dataToSend.put("param", dataIsi);
        RequestContext.getCurrentInstance().openDialog("bio_emergency_contact_form", options, dataToSend);
    }

    public void onDialogReturnContact(SelectEvent event) {
        try {
            dataBioEmergencyContacs = bioEmergencyContactService.getAllDataByBioDataId(selectedBioData.getId());
            super.onDialogReturn(event);
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    public void doSelectBioContact() {
        try {
            seleBioEmergencyContact = bioEmergencyContactService.getEntityByPKWithDetail(seleBioEmergencyContact.getId());
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    public void doDeleteBioContact() {
        try {
            this.bioEmergencyContactService.delete(seleBioEmergencyContact);
            dataBioEmergencyContacs = bioEmergencyContactService.getAllDataByBioDataId(Long.parseLong(userId.substring(1)));
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

    public void setBioEmergencyContactService(BioEmergencyContactService bioEmergencyContactService) {
        this.bioEmergencyContactService = bioEmergencyContactService;
    }

    public List<BioEmergencyContact> getDataBioEmergencyContacs() {
        return dataBioEmergencyContacs;
    }

    public void setDataBioEmergencyContacs(List<BioEmergencyContact> dataBioEmergencyContacs) {
        this.dataBioEmergencyContacs = dataBioEmergencyContacs;
    }

    /**
     * START Bio Insurance method
     */
    public void doSelectBioInsurance() {
        try {
            selectedBioInsurance = bioInsuranceService.getEntiyByPK(selectedBioInsurance.getId());
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    public void doUpdateBioInsurance() {

        List<String> bioInsuranceId = new ArrayList<>();
        bioInsuranceId.add(String.valueOf(selectedBioInsurance.getId()));

        List<String> bioDataId = new ArrayList<>();
        bioDataId.add(String.valueOf(selectedBioData.getId()));

        Map<String, List<String>> dataToSend = new HashMap<>();
        dataToSend.put("bioInsuranceId", bioInsuranceId);
        dataToSend.put("bioDataId", bioDataId);
        showDialogBioInsurance(dataToSend);

    }

    public void doAddBioInsurance() {
        List<String> bioDataId = new ArrayList<>();
        bioDataId.add(String.valueOf(selectedBioData.getId()));

        Map<String, List<String>> dataToSend = new HashMap<>();
        dataToSend.put("bioDataId", bioDataId);
        showDialogBioInsurance(dataToSend);
    }

    public void doDeleteBioInsurance() {
        try {
            bioInsuranceService.delete(selectedBioInsurance);
            bioInsurances = bioInsuranceService.getAllDataByBioDataId(selectedBioInsurance.getBioData().getId());
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error when doDelete bioInsurance", ex);
        }
    }

    private void showDialogBioInsurance(Map<String, List<String>> params) {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 450);
        options.put("contentHeight", 420);
        RequestContext.getCurrentInstance().openDialog("bio_insurance_form", options, params);
    }

    public void onDialogReturnBioInsurance(SelectEvent event) {
        try {
            bioInsurances = bioInsuranceService.getAllDataByBioDataId(selectedBioData.getId());
            super.onDialogReturn(event);
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    /**
     * END Bio Insurance method
     */
    /*
     *Start Report BioData
     */
    public BioEmergencyContact getSeleBioEmergencyContact() {
        return seleBioEmergencyContact;
    }

    public void setSeleBioEmergencyContact(BioEmergencyContact seleBioEmergencyContact) {
        this.seleBioEmergencyContact = seleBioEmergencyContact;
    }

    public StreamedContent getFileContent() {
        return fileContent;
    }

    public String doReportBiodata() {
        return "/protected/personalia/bio_report_view.htm?faces-redirect=true&execution=e" + selectedBioData.getId();

    }

    public void onDialogReturnReport(SelectEvent event) {
        try {
            bioDataList = bioDataService.getEntityByPKWithDetail(selectedBioData.getId());
            super.onDialogReturn(event);
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    /*
     *End Report BioData
     */
    public List<BioData> getBioDataList() {
        return bioDataList;
    }

    public void setBioDataList(List<BioData> bioDataList) {
        this.bioDataList = bioDataList;
    }

    /**
     * START Bio MedicalHistory method
     */
    public void doSelectBioMedicalHistory() {
        try {
            selectedBioMedicalHistory = bioMedicalHistoryService.getEntiyByPK(selectedBioMedicalHistory.getId());
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    public void doUpdateBioMedicalHistory() {

        List<String> bioMedicalHistoryId = new ArrayList<>();
        bioMedicalHistoryId.add(String.valueOf(selectedBioMedicalHistory.getId()));

        List<String> bioDataId = new ArrayList<>();
        bioDataId.add(String.valueOf(selectedBioData.getId()));

        Map<String, List<String>> dataToSend = new HashMap<>();
        dataToSend.put("bioMedicalHistoryId", bioMedicalHistoryId);
        dataToSend.put("bioDataId", bioDataId);
        showDialogBioMedicalHistory(dataToSend);

    }

    public void doAddBioMedicalHistory() {
        List<String> bioDataId = new ArrayList<>();
        bioDataId.add(String.valueOf(selectedBioData.getId()));

        Map<String, List<String>> dataToSend = new HashMap<>();
        dataToSend.put("bioDataId", bioDataId);
        showDialogBioMedicalHistory(dataToSend);
    }

    public void doDeleteBioMedicalHistory() {
        try {
            bioMedicalHistoryService.delete(selectedBioMedicalHistory);
            bioMedicalHistorys = bioMedicalHistoryService.getAllDataByBioDataId(selectedBioMedicalHistory.getBioData().getId());
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error when doDelete bioMedicalHistory", ex);
        }
    }

    private void showDialogBioMedicalHistory(Map<String, List<String>> params) {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 450);
        options.put("contentHeight", 420);
        RequestContext.getCurrentInstance().openDialog("bio_medical_history_form", options, params);
    }

    public void onDialogReturnBioMedicalHistory(SelectEvent event) {
        try {
            bioMedicalHistorys = bioMedicalHistoryService.getAllDataByBioDataId(selectedBioData.getId());
            super.onDialogReturn(event);
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    /**
     * END Bio MedicalHistory method
     */
    /**
     * START Bio EmploymentHistory method
     */
    public void doSelectBioEmploymentHistory() {
        try {
            selectedBioEmploymentHistory = bioEmploymentHistoryService.getEntityByPKWithDetail(selectedBioEmploymentHistory.getId());
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    public void doUpdateBioEmploymentHistory() {

        List<String> bioEmploymentHistoryId = new ArrayList<>();
        bioEmploymentHistoryId.add(String.valueOf(selectedBioEmploymentHistory.getId()));

        List<String> bioDataId = new ArrayList<>();
        bioDataId.add(String.valueOf(selectedBioData.getId()));

        Map<String, List<String>> dataToSend = new HashMap<>();
        dataToSend.put("bioEmploymentHistoryId", bioEmploymentHistoryId);
        dataToSend.put("bioDataId", bioDataId);
        showDialogBioEmploymentHistory(dataToSend);

    }

    public void doAddBioEmploymentHistory() {
        List<String> bioDataId = new ArrayList<>();
        bioDataId.add(String.valueOf(selectedBioData.getId()));

        Map<String, List<String>> dataToSend = new HashMap<>();
        dataToSend.put("bioDataId", bioDataId);
        showDialogBioEmploymentHistory(dataToSend);
    }

    public void doDeleteBioEmploymentHistory() {
        try {
            bioEmploymentHistoryService.delete(selectedBioEmploymentHistory);
            bioEmploymentHistorys = bioEmploymentHistoryService.getAllDataByBioDataId(selectedBioEmploymentHistory.getBioData().getId());
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error when doDelete bioEmploymentHistory", ex);
        }
    }

    private void showDialogBioEmploymentHistory(Map<String, List<String>> params) {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 550);
        options.put("contentHeight", 450);
        RequestContext.getCurrentInstance().openDialog("bio_employment_history_form", options, params);
    }

    public void onDialogReturnBioEmploymentHistory(SelectEvent event) {
        try {
            bioEmploymentHistorys = bioEmploymentHistoryService.getAllDataByBioDataId(selectedBioData.getId());
            super.onDialogReturn(event);
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    /**
     * END Bio EmploymentHistory method
     */
    /**
     * START Bio FamilyRelationship method
     */
    public void doSelectBioFamilyRelationship() {
        try {
            selectedBioFamilyRelationship = bioFamilyRelationshipService.getEntityByPKWithDetail(selectedBioFamilyRelationship.getId());
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    public void doUpdateBioFamilyRelationship() {

        List<String> bioFamilyRelationshipId = new ArrayList<>();
        bioFamilyRelationshipId.add(String.valueOf(selectedBioFamilyRelationship.getId()));

        List<String> bioDataId = new ArrayList<>();
        bioDataId.add(String.valueOf(selectedBioData.getId()));

        Map<String, List<String>> dataToSend = new HashMap<>();
        dataToSend.put("bioFamilyRelationshipId", bioFamilyRelationshipId);
        dataToSend.put("bioDataId", bioDataId);
        showDialogBioFamilyRelationship(dataToSend);

    }

    public void doAddBioFamilyRelationship() {
        List<String> bioDataId = new ArrayList<>();
        bioDataId.add(String.valueOf(selectedBioData.getId()));

        Map<String, List<String>> dataToSend = new HashMap<>();
        dataToSend.put("bioDataId", bioDataId);
        showDialogBioFamilyRelationship(dataToSend);
    }

    public void doDeleteBioFamilyRelationship() {
        try {
            bioFamilyRelationshipService.delete(selectedBioFamilyRelationship);
            bioFamilyRelationships = bioFamilyRelationshipService.getAllDataByBioDataId(selectedBioFamilyRelationship.getBioData().getId());
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error when doDelete bioFamilyRelationship", ex);
        }
    }

    private void showDialogBioFamilyRelationship(Map<String, List<String>> params) {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 500);
        options.put("contentHeight", 500);
        RequestContext.getCurrentInstance().openDialog("bio_family_relationship_form", options, params);
    }

    public void onDialogReturnBioFamilyRelationship(SelectEvent event) {
        try {
            bioFamilyRelationships = bioFamilyRelationshipService.getAllDataByBioDataId(selectedBioData.getId());
            super.onDialogReturn(event);
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    /**
     * END Bio FamilyRelationship method
     */
    /**
     * START Bio BankAccount method
     */
    public void doSelectBioBankAccount() {
        try {
            selectedBioBankAccount = bioBankAccountService.getEntityByPKWithDetail(selectedBioBankAccount.getId());
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    public void doUpdateBioBankAccount() {

        List<String> bioBankAccountId = new ArrayList<>();
        bioBankAccountId.add(String.valueOf(selectedBioBankAccount.getId()));

        List<String> bioDataId = new ArrayList<>();
        bioDataId.add(String.valueOf(selectedBioData.getId()));

        Map<String, List<String>> dataToSend = new HashMap<>();
        dataToSend.put("bioBankAccountId", bioBankAccountId);
        dataToSend.put("bioDataId", bioDataId);
        showDialogBioBankAccount(dataToSend);

    }

    public void doAddBioBankAccount() {
        List<String> bioDataId = new ArrayList<>();
        bioDataId.add(String.valueOf(selectedBioData.getId()));

        Map<String, List<String>> dataToSend = new HashMap<>();
        dataToSend.put("bioDataId", bioDataId);
        showDialogBioBankAccount(dataToSend);
    }

    public void doDeleteBioBankAccount() {
        try {
            bioBankAccountService.delete(selectedBioBankAccount);
            bioBankAccounts = bioBankAccountService.getAllDataByBioDataId(selectedBioBankAccount.getBioData().getId());
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error when doDelete bioBankAccount", ex);
        }
    }

    private void showDialogBioBankAccount(Map<String, List<String>> params) {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 500);
        options.put("contentHeight", 500);
        RequestContext.getCurrentInstance().openDialog("bio_bank_account_form", options, params);
    }

    public void onDialogReturnBioBankAccount(SelectEvent event) {
        try {
            bioBankAccounts = bioBankAccountService.getAllDataByBioDataId(selectedBioData.getId());
            super.onDialogReturn(event);
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    /**
     * END Bio BankAccount method
     */
    /**
     * START Bio IdCard method
     */
    public void doSelectBioIdCard() {
        try {
            selectedBioIdCard = bioIdCardService.getEntityByPKWithDetail(selectedBioIdCard.getId());
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    public void doUpdateBioIdCard() {

        List<String> bioIdCardId = new ArrayList<>();
        bioIdCardId.add(String.valueOf(selectedBioIdCard.getId()));

        List<String> bioDataId = new ArrayList<>();
        bioDataId.add(String.valueOf(selectedBioData.getId()));

        Map<String, List<String>> dataToSend = new HashMap<>();
        dataToSend.put("bioIdCardId", bioIdCardId);
        dataToSend.put("bioDataId", bioDataId);
        showDialogBioIdCard(dataToSend);

    }

    public void doAddBioIdCard() {
        List<String> bioDataId = new ArrayList<>();
        bioDataId.add(String.valueOf(selectedBioData.getId()));

        Map<String, List<String>> dataToSend = new HashMap<>();
        dataToSend.put("bioDataId", bioDataId);
        showDialogBioIdCard(dataToSend);
    }

    public void doDeleteBioIdCard() {
        try {
            bioIdCardService.delete(selectedBioIdCard);
            bioIdCards = bioIdCardService.getAllDataByBioDataId(selectedBioIdCard.getBioData().getId());
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error when doDelete bioIdCard", ex);
        }
    }

    private void showDialogBioIdCard(Map<String, List<String>> params) {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 500);
        options.put("contentHeight", 500);
        RequestContext.getCurrentInstance().openDialog("bio_id_card_form", options, params);
    }

    public void onDialogReturnBioIdCard(SelectEvent event) {
        try {
            bioIdCards = bioIdCardService.getAllDataByBioDataId(selectedBioData.getId());
            super.onDialogReturn(event);
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    /**
     * END Bio IdCard method
     */
}
