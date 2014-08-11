/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.personalia;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.BioData;
import com.inkubator.hrm.entity.BioEmergencyContact;
import com.inkubator.hrm.entity.City;
import com.inkubator.hrm.entity.FamilyRelation;
import com.inkubator.hrm.service.BioEmergencyContactService;
import com.inkubator.hrm.service.CityService;
import com.inkubator.hrm.service.FamilyRelationService;
import com.inkubator.hrm.web.model.EmergencyContactModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Deni Husni FR
 */
@ManagedBean(name = "bioEmergencyContactFormController")
@ViewScoped
public class BioEmergencyContactFormController extends BaseController {

    private EmergencyContactModel emergencyContactModel;
    @ManagedProperty(value = "#{bioEmergencyContactService}")
    private BioEmergencyContactService bioEmergencyContactService;
    @ManagedProperty(value = "#{cityService}")
    private CityService cityService;
    @ManagedProperty(value = "#{familyRelationService}")
    private FamilyRelationService familyRelationService;
    private Map<String, Long> mapRelationFamily = new HashMap<>();
    private Map<String, Long> mapCity = new HashMap<>();
    private Boolean isEdit;
    private Long bioDataId;

        @PreDestroy
    private void cleanAndExit() {
        emergencyContactModel = null;
        bioEmergencyContactService = null;
        cityService = null;
        familyRelationService = null;
        mapRelationFamily = null;
        mapCity = null;
        isEdit = null;
        bioDataId = null;
    }
        
    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            emergencyContactModel = new EmergencyContactModel();
            List<City> dataCity = cityService.getAllData();
            String param = FacesUtil.getRequestParameter("param");
            if(param.contains("e")){
                BioEmergencyContact emergencyContact = bioEmergencyContactService.getEntiyByPK(Long.parseLong(param));
                isEdit = Boolean.TRUE;
                emergencyContactModel.setId(emergencyContact.getId());
                emergencyContactModel.setName(emergencyContact.getContactName());
                emergencyContactModel.setAddress(emergencyContact.getAddress());
                emergencyContactModel.setPhoneNumber(emergencyContact.getPhoneNumber());
                emergencyContactModel.setCityId(emergencyContact.getCity().getId());
                emergencyContactModel.setFamilyRelationId(emergencyContact.getFamilyRelation().getId());
                emergencyContactModel.setIsSameHouse(emergencyContact.getIsSameHouse());
                emergencyContactModel.setBioDataId(emergencyContact.getBioData().getId());
            }
            if(param.contains("i")){
                bioDataId = Long.parseLong(param.substring(1));
                isEdit = Boolean.FALSE;
            }
            for (City city : dataCity) {
                mapCity.put(city.getCityName(), city.getId());
            }
            
            List<FamilyRelation>dataFamily=familyRelationService.getAllData();
            for (FamilyRelation familyRelation : dataFamily) {
                mapRelationFamily.put(familyRelation.getRelasiName(), familyRelation.getId());
            }
        } catch (Exception ex) {
            LOGGER.error("Erro", ex);
        }
    }

    public void setBioEmergencyContactService(BioEmergencyContactService bioEmergencyContactService) {
        this.bioEmergencyContactService = bioEmergencyContactService;
    }

    public void setCityService(CityService cityService) {
        this.cityService = cityService;
    }

    public void setFamilyRelationService(FamilyRelationService familyRelationService) {
        this.familyRelationService = familyRelationService;
    }

    public EmergencyContactModel getEmergencyContactModel() {
        return emergencyContactModel;
    }

    public void setEmergencyContactModel(EmergencyContactModel emergencyContactModel) {
        this.emergencyContactModel = emergencyContactModel;
    }

    public Map<String, Long> getMapRelationFamily() {
        return mapRelationFamily;
    }

    public void setMapRelationFamily(Map<String, Long> mapRelationFamily) {
        this.mapRelationFamily = mapRelationFamily;
    }

    public Map<String, Long> getMapCity() {
        return mapCity;
    }

    public void setMapCity(Map<String, Long> mapCity) {
        this.mapCity = mapCity;
    }
    
   public void doSave(){
       System.out.println("masuk dosave");
        BioEmergencyContact emergencyContact = getEntityFromViewModel(emergencyContactModel);
        try {
            if (isEdit) {
                bioEmergencyContactService.update(emergencyContact);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                bioEmergencyContactService.save(emergencyContact);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch (BussinessException ex) { //data already exist(duplicate)
            LOGGER.error("Error", ex);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }
   
    private BioEmergencyContact getEntityFromViewModel(EmergencyContactModel model) {
        BioEmergencyContact emergencyContact = new BioEmergencyContact();
        if (model.getId() != null) {
            emergencyContact.setId(model.getId());
        }
        emergencyContact.setBioData(new BioData(bioDataId));
        emergencyContact.setContactName(model.getName());
        emergencyContact.setPhoneNumber(model.getPhoneNumber());
        emergencyContact.setAddress(model.getAddress());
        emergencyContact.setCity(new City(model.getCityId()));
        emergencyContact.setFamilyRelation(new FamilyRelation(model.getFamilyRelationId()));
        emergencyContact.setIsSameHouse(model.getIsSameHouse());
        return emergencyContact;
    }
    

}
