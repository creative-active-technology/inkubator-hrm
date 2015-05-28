/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.personalia;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.BioAddress;
import com.inkubator.hrm.entity.BioData;
import com.inkubator.hrm.entity.BioEducationHistory;
import com.inkubator.hrm.entity.BioEmergencyContact;
import com.inkubator.hrm.entity.BioFamilyRelationship;
import com.inkubator.hrm.entity.BioIdCard;
import com.inkubator.hrm.entity.BioKeahlian;
import com.inkubator.hrm.entity.BioPeopleInterest;
import com.inkubator.hrm.entity.BioRelasiPerusahaan;
import com.inkubator.hrm.entity.BioSpesifikasiAbility;
import com.inkubator.hrm.entity.BioSpesifikasiAbilityId;
import com.inkubator.hrm.service.BioAddressService;
import com.inkubator.hrm.service.BioDataService;
import com.inkubator.hrm.service.BioEducationHistoryService;
import com.inkubator.hrm.service.BioEmergencyContactService;
import com.inkubator.hrm.service.BioFamilyRelationshipService;
import com.inkubator.hrm.service.BioIdCardService;
import com.inkubator.hrm.service.BioKeahlianService;
import com.inkubator.hrm.service.BioPeopleInterestService;
import com.inkubator.hrm.service.BioRelasiPerusahaanService;
import com.inkubator.hrm.service.BioSpesifikasiAbilityService;
import com.inkubator.hrm.util.HrmUserInfoUtil;
import com.inkubator.hrm.web.model.BioEducationHistoryViewModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.logging.Level;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.hibernate.exception.ConstraintViolationException;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.springframework.dao.DataIntegrityViolationException;

/**
 *
 * @author Deni Husni FR
 */
@ManagedBean(name = "bioDataController")
@ViewScoped
public class BioDataController extends BaseController {

    @ManagedProperty(value = "#{bioDataService}")
    private BioDataService bioDataService;
    private BioData selectedBioData;
    
    //Start BioFamilyRelationship
    private List<BioFamilyRelationship> bioFamilyRelationships;
    @ManagedProperty(value = "#{bioFamilyRelationshipService}")
    private BioFamilyRelationshipService bioFamilyRelationshipService;
    private BioFamilyRelationship selectedBioFamilyRelationship;
    //End BioFamilyRelationship
    
    //Start BioAddress
    private List<BioAddress> bioAddresses;
    @ManagedProperty(value = "#{bioAddressService}")
    private BioAddressService bioAddressService;
    private BioAddress selectedBioAddress;
     //End BioAddress
    
    //Start. bio bank
    private BioIdCard selectedBioIdCard;
    private List<BioIdCard> bioIdCards;
    @ManagedProperty(value = "#{bioIdCardService}")
    private BioIdCardService bioIdCardService;
    //End. bio bank
    
    //Start. bio Emergency Contact
    @ManagedProperty(value = "#{bioEmergencyContactService}")
    private BioEmergencyContactService bioEmergencyContactService;
    private List<BioEmergencyContact> dataBioEmergencyContacs;
    private BioEmergencyContact selectedBioEmergencyContact;
    //End. bio Emergency Contact
    
    //Start. Bio Relasi Perusahaan
    @ManagedProperty(value = "#{bioRelasiPerusahaanService}")
    private BioRelasiPerusahaanService bioRelasiPerusahaanService;
    private List<BioRelasiPerusahaan> listBioRelasiPerusaan;
    private BioRelasiPerusahaan selectedBioRelasiPerusahaan;
    //End. Bio RelasiPerusahaan
    
    //Start Bio Education History 
     private BioEducationHistory selectedEduHistory;
    private BioEducationHistoryViewModel selectedBioEducationHistoryViewModel;
    private List<BioEducationHistoryViewModel> listBioEducationHistoryViewModel;
    @ManagedProperty(value = "#{bioEducationHistoryService}")
    private BioEducationHistoryService educationHistoryService;
    //End Bio Education History
    
    //Start. BioKeahlian
    private BioKeahlian selectedBioKeahlian;
    @ManagedProperty(value = "#{bioKeahlianService}")
    private BioKeahlianService bioKeahlianService;
    private List<BioKeahlian> listBioKeahlian;
    //End. BioKeahlian

    //Start. BioSpesifikasiAbility
    private BioSpesifikasiAbility selectedDioSpesifikasiAbility;
    @ManagedProperty(value = "#{bioSpesifikasiAbilityService}")
    private BioSpesifikasiAbilityService bioSpesifikasiAbilityService;
    private List<BioSpesifikasiAbility> listBioSpesifikasiAbility;
    //End. BioSpesifikasiAbility
    
    //Start BioPeopleInterest / minat
    private BioPeopleInterest selectedPeopleInterest;
    private List<BioPeopleInterest> listPeopleInterest;
    @ManagedProperty(value = "#{bioPeopleInterestService}")
    private BioPeopleInterestService peopleInterestService;
    //End BioPeopleInterest / minat

    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            Long bioDataId = HrmUserInfoUtil.getBioDataId();
            selectedBioData = bioDataService.getEntiyByPK(bioDataId);
            bioAddresses = bioAddressService.getAllDataByBioDataId(selectedBioData.getId());
            bioFamilyRelationships = bioFamilyRelationshipService.getAllDataByBioDataId(selectedBioData.getId());
            dataBioEmergencyContacs = bioEmergencyContactService.getAllDataByBioDataId(selectedBioData.getId());
            bioIdCards = bioIdCardService.getAllDataByBioDataId(selectedBioData.getId());
            listBioRelasiPerusaan = bioRelasiPerusahaanService.getAllDataByBioDataId(selectedBioData.getId());
            listBioEducationHistoryViewModel = educationHistoryService.getAllDataByBioDataId(selectedBioData.getId());
            listBioKeahlian = bioKeahlianService.getAllDataByBioDataId(selectedBioData.getId());
            listBioSpesifikasiAbility = bioSpesifikasiAbilityService.getAllDataByBiodataId(selectedBioData.getId());
        } catch (Exception ex) {
            LOGGER.error(ex, ex);
        }

    }

    public BioData getSelectedBioData() {
        return selectedBioData;
    }

    public void setSelectedBioData(BioData selectedBioData) {
        this.selectedBioData = selectedBioData;
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

    public BioAddress getSelectedBioAddress() {
        return selectedBioAddress;
    }

    public void setSelectedBioAddress(BioAddress selectedBioAddress) {
        this.selectedBioAddress = selectedBioAddress;
    }

    public void onDialogReturnBioAddress(SelectEvent event) {
        try {
            bioAddresses = bioAddressService.getAllDataByBioDataId(selectedBioData.getId());
            super.onDialogReturn(event);
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    public List<BioFamilyRelationship> getBioFamilyRelationships() {
        return bioFamilyRelationships;
    }

    public void setBioFamilyRelationships(List<BioFamilyRelationship> bioFamilyRelationships) {
        this.bioFamilyRelationships = bioFamilyRelationships;
    }

    public void setBioFamilyRelationshipService(BioFamilyRelationshipService bioFamilyRelationshipService) {
        this.bioFamilyRelationshipService = bioFamilyRelationshipService;
    }

    public BioFamilyRelationship getSelectedBioFamilyRelationship() {
        return selectedBioFamilyRelationship;
    }

    public void setSelectedBioFamilyRelationship(BioFamilyRelationship selectedBioFamilyRelationship) {
        this.selectedBioFamilyRelationship = selectedBioFamilyRelationship;
    }

    public void doSelectBioAddress() {
        try {
            selectedBioAddress = bioAddressService.getEntityByPKWithDetail(selectedBioAddress.getId());
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    public void doSelectBioFamilyRelationship() {
        try {
            selectedBioFamilyRelationship = bioFamilyRelationshipService.getEntityByPKWithDetail(selectedBioFamilyRelationship.getId());
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    public void doSelectBioContact() {
        try {
            selectedBioEmergencyContact = bioEmergencyContactService.getEntityByPKWithDetail(selectedBioEmergencyContact.getId());
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
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
        dataIsi.add("e" + String.valueOf(selectedBioEmergencyContact.getId()));
        dataToSend.put("param", dataIsi);
        RequestContext.getCurrentInstance().openDialog("bio_emerg_cont_form", options, dataToSend);
    }

    public void onDialogReturnContact(SelectEvent event) {
        try {
            dataBioEmergencyContacs = bioEmergencyContactService.getAllDataByBioDataId(selectedBioData.getId());
            super.onDialogReturn(event);
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    public void doDeleteBioContact() {
        try {
            this.bioEmergencyContactService.delete(selectedBioEmergencyContact);
            dataBioEmergencyContacs = bioEmergencyContactService.getAllDataByBioDataId(selectedBioData.getId());
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            LOGGER.error("Error", ex);
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }
    
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
        options.put("contentHeight", 410);
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
    
    /**
     * START Bio Relasi Perusahaan
     */
    public void doSelectBioRelasiPerusahaan() {
        try {
            selectedBioRelasiPerusahaan = bioRelasiPerusahaanService.getEntityByPkWithDetail(selectedBioRelasiPerusahaan.getId());
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    public void doDeleteBioRelasiPerusahaan() {
        try {
            bioRelasiPerusahaanService.delete(selectedBioRelasiPerusahaan);
            listBioRelasiPerusaan = bioRelasiPerusahaanService.getAllDataByBioDataId(selectedBioRelasiPerusahaan.getBioData().getId());
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error when doDelete bioProject", ex);
        }
    }

    public void doAddBioRelasiPerusahaan() {
        List<String> bioDataId = new ArrayList<>();
        bioDataId.add(String.valueOf(selectedBioData.getId()));

        Map<String, List<String>> dataToSend = new HashMap<>();
        dataToSend.put("bioDataId", bioDataId);
        showDialogBioRelasiPerusahaan(dataToSend);
    }

    public void doUpdateBioRelasiPerusahaan() {
        List<String> bioRelasiPerusahaan = new ArrayList<>();
        bioRelasiPerusahaan.add(String.valueOf(selectedBioRelasiPerusahaan.getId()));

        List<String> bioDataId = new ArrayList<>();
        bioDataId.add(String.valueOf(selectedBioData.getId()));

        Map<String, List<String>> dataToSend = new HashMap<>();
        dataToSend.put("bioRelasiPerusahaanId", bioRelasiPerusahaan);
        dataToSend.put("bioDataId", bioDataId);
        showDialogBioRelasiPerusahaan(dataToSend);
    }

    private void showDialogBioRelasiPerusahaan(Map<String, List<String>> params) {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 830);
        options.put("contentHeight", 520);
        RequestContext.getCurrentInstance().openDialog("bio_relasi_perusahaan", options, params);
    }
    
     public void onDialogReturnBioRelasiPerusahaan(SelectEvent event) {
        try {
            listBioRelasiPerusaan = bioRelasiPerusahaanService.getAllDataByBioDataId(selectedBioData.getId());
            super.onDialogReturn(event);
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    /**
     * END Bio Relasi Perusahaan
     */
     
      /**
     * START Bio Education History method
     */
    public void doSelectBioEduHistory() {
        try {
            selectedEduHistory = this.educationHistoryService.getAllDataByPK(selectedBioEducationHistoryViewModel.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public void doDetailBioEduHistory() {
        try {
            selectedBioEducationHistoryViewModel = this.educationHistoryService.getAllByPKByController(selectedBioEducationHistoryViewModel.getId());
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
        options.put("contentHeight", 440);
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> dataIsi = new ArrayList<>();
        dataIsi.add("e" + String.valueOf(selectedBioEducationHistoryViewModel.getId()));
        dataToSend.put("param", dataIsi);
        RequestContext.getCurrentInstance().openDialog("bio_edu_hist_form", options, dataToSend);
    }

    public void doAddBioEduHistory() {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 750);
        options.put("contentHeight", 440);
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> dataIsi = new ArrayList<>();
        dataIsi.add("i" + String.valueOf(selectedBioData.getId()));
        dataToSend.put("param", dataIsi);
        RequestContext.getCurrentInstance().openDialog("bio_edu_hist_form", options, dataToSend);
    }
    
    
    public void onDialogBioEducationHistoryReturn(SelectEvent event) {
        try {
            listBioEducationHistoryViewModel = educationHistoryService.getAllDataByBioDataId(selectedBioData.getId());
            //listPeopleInterest = peopleInterestService.getAllDataByBioDataId(Long.parseLong(userId.substring(1)));            
            super.onDialogReturn(event);
        } catch (Exception ex) {
            Logger.getLogger(BioDataController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
     /**
     * END Bio Education History method
     */

    public void doDeleteBioEduHistory() {
        try {
            this.educationHistoryService.delete(selectedEduHistory);
            listBioEducationHistoryViewModel = educationHistoryService.getAllDataByBioDataId(selectedBioData.getId());
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            LOGGER.error("Error", ex);
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    /**
     * END Bio Education History method
     */
    
    /**
     * START Bio Keahlian method
     */
    public void doSelectBioKeahlian() {
        try {
            selectedBioKeahlian = bioKeahlianService.getAllDataByPK(selectedBioKeahlian.getId());
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    public void doUpdateBioKeahlian() {

        List<String> bioKeahlian = new ArrayList<>();
        bioKeahlian.add(String.valueOf(selectedBioKeahlian.getId()));

        List<String> bioDataId = new ArrayList<>();
        bioDataId.add(String.valueOf(selectedBioData.getId()));

        Map<String, List<String>> dataToSend = new HashMap<>();
        dataToSend.put("bioKeahlianId", bioKeahlian);
        dataToSend.put("bioDataId", bioDataId);
        showDialogBioKahlian(dataToSend);

    }

    public void doAddBioKeahlian() {
        List<String> bioDataId = new ArrayList<>();
        bioDataId.add(String.valueOf(selectedBioData.getId()));

        Map<String, List<String>> dataToSend = new HashMap<>();
        dataToSend.put("bioDataId", bioDataId);
        showDialogBioKahlian(dataToSend);
    }

    public void doDeleteBioKeahlian() {
        try {
            bioKeahlianService.delete(selectedBioKeahlian);
            listBioKeahlian = bioKeahlianService.getAllDataByBioDataId(selectedBioKeahlian.getBiodata().getId());
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error when doDelete bioFamilyRelationship", ex);
        }
    }

    private void showDialogBioKahlian(Map<String, List<String>> params) {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 500);
        options.put("contentHeight", 250);
        RequestContext.getCurrentInstance().openDialog("bio_keahlian_form", options, params);
    }

    public void onDialogReturnBioKeahlian(SelectEvent event) {
        try {
            listBioKeahlian = bioKeahlianService.getAllDataByBioDataId(selectedBioData.getId());
            super.onDialogReturn(event);
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    /**
     * END Bio Keahlian method
     */
    
     /**
     * START Bio Spesifikasi Ability method
     */
    public void doSelectBioSpesifikasiAbility() {
        try {
            selectedDioSpesifikasiAbility = bioSpesifikasiAbilityService.getEntityByBioSpesifikasiAbilityId(new BioSpesifikasiAbilityId(selectedDioSpesifikasiAbility.getBioData().getId(), selectedDioSpesifikasiAbility.getSpecificationAbility().getId()));
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    public void doUpdateBioSpesifikasiAbility() {

        List<String> bioSpecAbi = new ArrayList<>();
        bioSpecAbi.add(String.valueOf(selectedDioSpesifikasiAbility.getSpecificationAbility().getId()));

        List<String> bioDataId = new ArrayList<>();
        bioDataId.add(String.valueOf(selectedBioData.getId()));

        Map<String, List<String>> dataToSend = new HashMap<>();
        dataToSend.put("bioSpecAbiId", bioSpecAbi);
        dataToSend.put("bioDataId", bioDataId);
        showDialogBioSpesifikasiAbility(dataToSend);

    }

    public void doAddBioSpesifikasiAbility() {
        List<String> bioDataId = new ArrayList<>();
        bioDataId.add(String.valueOf(selectedBioData.getId()));

        Map<String, List<String>> dataToSend = new HashMap<>();
        dataToSend.put("bioDataId", bioDataId);
        showDialogBioSpesifikasiAbility(dataToSend);
    }

    public void doDeleteBioSpesifikasiAbility() {
        try {
            bioSpesifikasiAbilityService.delete(selectedDioSpesifikasiAbility);
            listBioSpesifikasiAbility = bioSpesifikasiAbilityService.getAllDataByBiodataId(selectedDioSpesifikasiAbility.getBioData().getId());
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error when doDelete bioFamilyRelationship", ex);
        }
    }

    private void showDialogBioSpesifikasiAbility(Map<String, List<String>> params) {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 500);
        options.put("contentHeight", 250);
        RequestContext.getCurrentInstance().openDialog("bio_spec_ability_form", options, params);
    }

    public void onDialogReturnBioSpesifikasiAbility(SelectEvent event) {
        try {
            listBioSpesifikasiAbility = bioSpesifikasiAbilityService.getAllDataByBiodataId(selectedBioData.getId());
            super.onDialogReturn(event);
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    /**
     * END Bio Spesifikasi Ability method
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
            listPeopleInterest = peopleInterestService.getAllDataByBioDataId(selectedBioData.getId());
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            LOGGER.error("Error", ex);
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    /**
     * END BioPeople Interest method
     */

    public List<BioEmergencyContact> getDataBioEmergencyContacs() {
        return dataBioEmergencyContacs;
    }

    public void setDataBioEmergencyContacs(List<BioEmergencyContact> dataBioEmergencyContacs) {
        this.dataBioEmergencyContacs = dataBioEmergencyContacs;
    }

    public void setBioEmergencyContactService(BioEmergencyContactService bioEmergencyContactService) {
        this.bioEmergencyContactService = bioEmergencyContactService;
    }

    public BioEmergencyContact getSelectedBioEmergencyContact() {
        return selectedBioEmergencyContact;
    }

    public void setSelectedBioEmergencyContact(BioEmergencyContact selectedBioEmergencyContact) {
        this.selectedBioEmergencyContact = selectedBioEmergencyContact;
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

    public void setBioIdCardService(BioIdCardService bioIdCardService) {
        this.bioIdCardService = bioIdCardService;
    }

    public void setBioRelasiPerusahaanService(BioRelasiPerusahaanService bioRelasiPerusahaanService) {
        this.bioRelasiPerusahaanService = bioRelasiPerusahaanService;
    }

    public List<BioRelasiPerusahaan> getListBioRelasiPerusaan() {
        return listBioRelasiPerusaan;
    }

    public void setListBioRelasiPerusaan(List<BioRelasiPerusahaan> listBioRelasiPerusaan) {
        this.listBioRelasiPerusaan = listBioRelasiPerusaan;
    }

    public BioRelasiPerusahaan getSelectedBioRelasiPerusahaan() {
        return selectedBioRelasiPerusahaan;
    }

    public void setSelectedBioRelasiPerusahaan(BioRelasiPerusahaan selectedBioRelasiPerusahaan) {
        this.selectedBioRelasiPerusahaan = selectedBioRelasiPerusahaan;
    }

    public BioEducationHistory getSelectedEduHistory() {
        return selectedEduHistory;
    }

    public void setSelectedEduHistory(BioEducationHistory selectedEduHistory) {
        this.selectedEduHistory = selectedEduHistory;
    }

    public BioEducationHistoryViewModel getSelectedBioEducationHistoryViewModel() {
        return selectedBioEducationHistoryViewModel;
    }

    public void setSelectedBioEducationHistoryViewModel(BioEducationHistoryViewModel selectedBioEducationHistoryViewModel) {
        this.selectedBioEducationHistoryViewModel = selectedBioEducationHistoryViewModel;
    }

    public List<BioEducationHistoryViewModel> getListBioEducationHistoryViewModel() {
        return listBioEducationHistoryViewModel;
    }

    public void setListBioEducationHistoryViewModel(List<BioEducationHistoryViewModel> listBioEducationHistoryViewModel) {
        this.listBioEducationHistoryViewModel = listBioEducationHistoryViewModel;
    }

    public void setEducationHistoryService(BioEducationHistoryService educationHistoryService) {
        this.educationHistoryService = educationHistoryService;
    }

    public BioKeahlian getSelectedBioKeahlian() {
        return selectedBioKeahlian;
    }

    public void setSelectedBioKeahlian(BioKeahlian selectedBioKeahlian) {
        this.selectedBioKeahlian = selectedBioKeahlian;
    }

    public List<BioKeahlian> getListBioKeahlian() {
        return listBioKeahlian;
    }

    public void setListBioKeahlian(List<BioKeahlian> listBioKeahlian) {
        this.listBioKeahlian = listBioKeahlian;
    }

    public void setBioKeahlianService(BioKeahlianService bioKeahlianService) {
        this.bioKeahlianService = bioKeahlianService;
    }
    
     public void setBioDataService(BioDataService bioDataService) {
        this.bioDataService = bioDataService;
    }

    public BioSpesifikasiAbility getSelectedDioSpesifikasiAbility() {
        return selectedDioSpesifikasiAbility;
    }

    public void setSelectedDioSpesifikasiAbility(BioSpesifikasiAbility selectedDioSpesifikasiAbility) {
        this.selectedDioSpesifikasiAbility = selectedDioSpesifikasiAbility;
    }

    public List<BioSpesifikasiAbility> getListBioSpesifikasiAbility() {
        return listBioSpesifikasiAbility;
    }

    public void setListBioSpesifikasiAbility(List<BioSpesifikasiAbility> listBioSpesifikasiAbility) {
        this.listBioSpesifikasiAbility = listBioSpesifikasiAbility;
    }

    public void setBioSpesifikasiAbilityService(BioSpesifikasiAbilityService bioSpesifikasiAbilityService) {
        this.bioSpesifikasiAbilityService = bioSpesifikasiAbilityService;
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

    public void setPeopleInterestService(BioPeopleInterestService peopleInterestService) {
        this.peopleInterestService = peopleInterestService;
    }
     
    

}
