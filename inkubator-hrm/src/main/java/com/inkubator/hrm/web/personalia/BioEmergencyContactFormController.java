/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.personalia;

import com.inkubator.hrm.entity.City;
import com.inkubator.hrm.entity.FamilyRelation;
import com.inkubator.hrm.service.BioEmergencyContactService;
import com.inkubator.hrm.service.CityService;
import com.inkubator.hrm.service.FamilyRelationService;
import com.inkubator.hrm.web.model.EmergencyContactModel;
import com.inkubator.webcore.controller.BaseController;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

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

    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            emergencyContactModel = new EmergencyContactModel();
            List<City> dataCity = cityService.getAllData();
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
       
   }
    

}
