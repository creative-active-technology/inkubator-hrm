/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.personalia;

import com.inkubator.hrm.entity.BioAddress;
import com.inkubator.hrm.entity.BioData;
import com.inkubator.hrm.entity.BioEmergencyContact;
import com.inkubator.hrm.entity.BioFamilyRelationship;
import com.inkubator.hrm.service.BioAddressService;
import com.inkubator.hrm.service.BioDataService;
import com.inkubator.hrm.service.BioEmergencyContactService;
import com.inkubator.hrm.service.BioFamilyRelationshipService;
import com.inkubator.hrm.util.HrmUserInfoUtil;
import com.inkubator.webcore.controller.BaseController;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

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
    private List<BioAddress> bioAddresses;
    @ManagedProperty(value = "#{bioAddressService}")
    private BioAddressService bioAddressService;
    private BioAddress selectedBioAddress;
    private List<BioFamilyRelationship> bioFamilyRelationships;
    @ManagedProperty(value = "#{bioFamilyRelationshipService}")
    private BioFamilyRelationshipService bioFamilyRelationshipService;
    private BioFamilyRelationship selectedBioFamilyRelationship;
    private List<BioEmergencyContact> dataBioEmergencyContacs;
    @ManagedProperty(value = "#{bioEmergencyContactService}")
    private BioEmergencyContactService bioEmergencyContactService;

    public void setBioDataService(BioDataService bioDataService) {
        this.bioDataService = bioDataService;
    }

    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            selectedBioData = bioDataService.getEntiyByPK(HrmUserInfoUtil.getBioDataId());
            bioAddresses = bioAddressService.getAllDataByBioDataId(selectedBioData.getId());
            bioFamilyRelationships = bioFamilyRelationshipService.getAllDataByBioDataId(selectedBioData.getId());
            dataBioEmergencyContacs = bioEmergencyContactService.getAllDataByBioDataId(selectedBioData.getId());
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

    public List<BioEmergencyContact> getDataBioEmergencyContacs() {
        return dataBioEmergencyContacs;
    }

    public void setDataBioEmergencyContacs(List<BioEmergencyContact> dataBioEmergencyContacs) {
        this.dataBioEmergencyContacs = dataBioEmergencyContacs;
    }

    public void setBioEmergencyContactService(BioEmergencyContactService bioEmergencyContactService) {
        this.bioEmergencyContactService = bioEmergencyContactService;
    }

}
