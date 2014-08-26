/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.employee;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.EmpPersonAchievement;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.EmpPersonAchievementService;
import com.inkubator.hrm.web.model.EmpPersonAchievementModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Deni
 */
@ManagedBean(name = "empPersonAchievementFormController")
@ViewScoped
public class EmpPersonAchievementFormController extends BaseController{
    private EmpPersonAchievementModel model;
    private Boolean isUpdate;
    @ManagedProperty(value = "#{empPersonAchievementService}")
    private EmpPersonAchievementService empPersonAchievementService;
    @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;
    
    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try {
            model = new EmpPersonAchievementModel();
            isUpdate = Boolean.FALSE;
            String empPersonId = FacesUtil.getRequestParameter("empPersonId");

            if (StringUtils.isNotEmpty(empPersonId)) {
                EmpPersonAchievement empPersonAchievement = empPersonAchievementService.getEntityByPkWithEmployee(Long.parseLong(empPersonId));
                if (empPersonId != null) {
                    model = getModelFromEntity(empPersonAchievement);
                    isUpdate = Boolean.TRUE;
                }
            }
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }
    
    @PreDestroy
    public void cleanAndExit() {
        empPersonAchievementService = null;
        isUpdate = null;
        model = null;
        empDataService = null;
    }
    
    private EmpPersonAchievementModel getModelFromEntity(EmpPersonAchievement entity) {
        EmpPersonAchievementModel achievementModel = new EmpPersonAchievementModel();
        achievementModel.setId(entity.getId());
        achievementModel.setEmpData(entity.getEmpData());
        achievementModel.setDescription(entity.getDescription());
        achievementModel.setAchievementName(entity.getAchievementName());
        achievementModel.setDateAchievement(entity.getDateAchievement());
        return achievementModel;
    }
    
    public List<EmpData> completeEmployee(String query) {
            
        try {
            List<EmpData> allEmployee;
            allEmployee = empDataService.getAllDataWithRelation();
            List<EmpData> queried = new ArrayList<EmpData>();
            
            for (EmpData empData : allEmployee) {
                if (empData.getNik().toLowerCase().startsWith(query)  || empData.getNik().endsWith(query) && empData.getNik() != null ) {
                    queried.add(empData);
                }
            }
            return queried;
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(EmpPersonAchievementFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
       return null;
    }
    
    public List<String> completeAchievement(String query) {
        try {
            List<EmpPersonAchievement> allBioMedicalHistory;
            allBioMedicalHistory = empPersonAchievementService.getAllData();
            List<String> queried = new ArrayList<>();
            
            for (EmpPersonAchievement personAchievement : allBioMedicalHistory) {
                if (personAchievement.getAchievementName().toLowerCase().startsWith(query)  || personAchievement.getAchievementName().startsWith(query)) {
                    queried.add(personAchievement.getAchievementName());
                }
            }
            Set<String> setCompany = new HashSet<>(queried);
            List<String> listCompany = new ArrayList<>(setCompany);
            return listCompany;
            
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(EmpPersonAchievementFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        
        return null;
    }
    
    public void doSave() throws Exception {
        EmpPersonAchievement  empPersonAchievement = getEntityFromViewModel(model);
        try {
            if (isUpdate) {
                empPersonAchievementService.update(empPersonAchievement);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                empPersonAchievementService.save(empPersonAchievement);
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

    private EmpPersonAchievement getEntityFromViewModel(EmpPersonAchievementModel model) throws Exception {
        EmpPersonAchievement personAchievement = new EmpPersonAchievement();
        if (model.getId() != null) {
            personAchievement.setId(model.getId());
        }
        personAchievement.setAchievementName(model.getAchievementName());
        personAchievement.setDateAchievement(model.getDateAchievement());
        personAchievement.setDescription(model.getDescription());
        personAchievement.setEmpData(model.getEmpData());
        return personAchievement;
    }
    
    public EmpPersonAchievementModel getModel() {
        return model;
    }

    public void setModel(EmpPersonAchievementModel model) {
        this.model = model;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public EmpPersonAchievementService getEmpPersonAchievementService() {
        return empPersonAchievementService;
    }

    public void setEmpPersonAchievementService(EmpPersonAchievementService empPersonAchievementService) {
        this.empPersonAchievementService = empPersonAchievementService;
    }

    public EmpDataService getEmpDataService() {
        return empDataService;
    }

    public void setEmpDataService(EmpDataService empDataService) {
        this.empDataService = empDataService;
    }

    
    
}
