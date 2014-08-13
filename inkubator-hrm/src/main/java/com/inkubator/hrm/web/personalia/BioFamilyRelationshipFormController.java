package com.inkubator.hrm.web.personalia;

import com.inkubator.hrm.web.reference.*;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.BioData;
import com.inkubator.hrm.entity.BioFamilyRelationship;
import com.inkubator.hrm.entity.EducationLevel;
import com.inkubator.hrm.entity.FamilyRelation;
import com.inkubator.hrm.service.BioFamilyRelationshipService;
import com.inkubator.hrm.service.EducationLevelService;
import com.inkubator.hrm.service.FamilyRelationService;
import com.inkubator.hrm.util.MapUtil;
import com.inkubator.hrm.web.model.BioFamilyRelationshipModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Taufik Hidayat
 */
@ManagedBean(name = "bioFamilyRelationshipFormController")
@ViewScoped
public class BioFamilyRelationshipFormController extends BaseController {

    private BioFamilyRelationshipModel bioFamilyRelationshipModel;
    private Boolean isUpdate;
    @ManagedProperty(value = "#{bioFamilyRelationshipService}")
    private BioFamilyRelationshipService bioFamilyRelationshipService;
    @ManagedProperty(value = "#{familyRelationService}")
    private FamilyRelationService familyRelationService;
    @ManagedProperty(value = "#{educationLevelService}")
    private EducationLevelService educationLevelService;
    private Map<String, Long> familyRelations = new TreeMap<>();
    private Map<String, Long> educationLevels = new TreeMap<>();

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try {
            bioFamilyRelationshipModel = new BioFamilyRelationshipModel();
            isUpdate = Boolean.FALSE;
            String bioDataId = FacesUtil.getRequestParameter("bioDataId");
            bioFamilyRelationshipModel.setBioDataId(Long.parseLong(bioDataId));


            List<FamilyRelation> listFamilyRelation = familyRelationService.getAllData();

            for (FamilyRelation familyRelation : listFamilyRelation) {
                familyRelations.put(familyRelation.getRelasiName(), familyRelation.getId());
            }

            MapUtil.sortByValue(familyRelations);
            
            List<EducationLevel> listEducationLevel = educationLevelService.getAllData();

            for (EducationLevel educationLevel : listEducationLevel) {
                educationLevels.put(educationLevel.getName(), educationLevel.getId());
            }

            MapUtil.sortByValue(educationLevels);

            String bioFamilyRelationshipId = FacesUtil.getRequestParameter("bioFamilyRelationshipId");
            if (StringUtils.isNotEmpty(bioFamilyRelationshipId)) {
                BioFamilyRelationship bioFamilyRelationship = bioFamilyRelationshipService.getEntityByPKWithDetail(Long.parseLong(bioFamilyRelationshipId));
                if (bioFamilyRelationshipId != null) {
                    bioFamilyRelationshipModel = getModelFromEntity(bioFamilyRelationship);
                    isUpdate = Boolean.TRUE;
                }
            }
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    @PreDestroy
    public void cleanAndExit() {
        bioFamilyRelationshipService = null;
//        bioFamilyRelationshipModel = null;
        isUpdate = null;
    }

    public BioFamilyRelationshipModel getBioFamilyRelationshipModel() {
        return bioFamilyRelationshipModel;
    }

    public void setBioFamilyRelationshipModel(BioFamilyRelationshipModel bioFamilyRelationshipModel) {
        this.bioFamilyRelationshipModel = bioFamilyRelationshipModel;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public void setBioFamilyRelationshipService(BioFamilyRelationshipService bioFamilyRelationshipService) {
        this.bioFamilyRelationshipService = bioFamilyRelationshipService;
    }

    
    public void setFamilyRelationService(FamilyRelationService familyRelationService) {
        this.familyRelationService = familyRelationService;
    }

    public Map<String, Long> getFamilyRelations() {
        return familyRelations;
    }

    public void setFamilyRelations(Map<String, Long> familyRelations) {
        this.familyRelations = familyRelations;
    }

    public void setEducationLevelService(EducationLevelService educationLevelService) {
        this.educationLevelService = educationLevelService;
    }

    public Map<String, Long> getEducationLevels() {
        return educationLevels;
    }

    public void setEducationLevels(Map<String, Long> educationLevels) {
        this.educationLevels = educationLevels;
    }
    
    

    public void doSave() {
        BioFamilyRelationship bioFamilyRelationship = getEntityFromViewModel(bioFamilyRelationshipModel);
        try {
            if (isUpdate) {
                bioFamilyRelationshipService.update(bioFamilyRelationship);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                bioFamilyRelationshipService.save(bioFamilyRelationship);
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

    private BioFamilyRelationship getEntityFromViewModel(BioFamilyRelationshipModel bioFamilyRelationshipModel) {
        BioFamilyRelationship bioFamilyRelationship = new BioFamilyRelationship();
        if (bioFamilyRelationshipModel.getId() != null) {
            bioFamilyRelationship.setId(bioFamilyRelationshipModel.getId());
        }
        bioFamilyRelationship.setBioData(new BioData(bioFamilyRelationshipModel.getBioDataId()));
        bioFamilyRelationship.setFamilyRelation(new FamilyRelation(bioFamilyRelationshipModel.getFamilyRelationId()));
        bioFamilyRelationship.setName(bioFamilyRelationshipModel.getName());
        bioFamilyRelationship.setDateOfBirth(bioFamilyRelationshipModel.getDateOfBirth());
        bioFamilyRelationship.setGender(bioFamilyRelationshipModel.getGender());
        bioFamilyRelationship.setDependents(bioFamilyRelationshipModel.getDependents());
        bioFamilyRelationship.setEducationLevel(new EducationLevel(bioFamilyRelationshipModel.getEducationLevelId()));
        bioFamilyRelationship.setOccupation(bioFamilyRelationshipModel.getOccupation());
        return bioFamilyRelationship;
    }

    private BioFamilyRelationshipModel getModelFromEntity(BioFamilyRelationship entity) {
        BioFamilyRelationshipModel bioFamilyRelationshipModel = new BioFamilyRelationshipModel();
        bioFamilyRelationshipModel.setId(entity.getId());
        bioFamilyRelationshipModel.setBioDataId(entity.getBioData().getId());
        bioFamilyRelationshipModel.setFamilyRelationId(entity.getFamilyRelation().getId());
        bioFamilyRelationshipModel.setName(entity.getName());
        bioFamilyRelationshipModel.setDateOfBirth(entity.getDateOfBirth());
        bioFamilyRelationshipModel.setGender(entity.getGender());
        bioFamilyRelationshipModel.setDependents(entity.getDependents());
        bioFamilyRelationshipModel.setEducationLevelId(entity.getEducationLevel().getId());
        bioFamilyRelationshipModel.setOccupation(entity.getOccupation());
        return bioFamilyRelationshipModel;
    }

    
}
