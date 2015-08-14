package com.inkubator.hrm.web.reference;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.EducationLevelDao;
import com.inkubator.hrm.entity.EducationLevel;
import com.inkubator.hrm.service.EducationLevelService;
import com.inkubator.hrm.web.model.EducationLevelModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

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
 * @author rizkykojek
 */
@ManagedBean(name = "educationLevelFormController")
@ViewScoped
public class EducationLevelFormController extends BaseController {

    private EducationLevelModel educationLevelModel;
    private Boolean isUpdate;
    @ManagedProperty(value = "#{educationLevelService}")
    private EducationLevelService educationLevelService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        String param = FacesUtil.getRequestParameter("param");
        educationLevelModel = new EducationLevelModel();
        isUpdate = Boolean.FALSE;
        if (StringUtils.isNumeric(param)) {
            try {
                EducationLevel educationLevel = educationLevelService.getEntiyByPK(Long.parseLong(param));
                if (educationLevel != null) {
                	Integer maxLevel = educationLevelService.getCurrentMaxLevel();
                    educationLevelModel.setId(educationLevel.getId());
                    educationLevelModel.setName(educationLevel.getName());
                    educationLevelModel.setLevel(educationLevel.getLevel());
                    educationLevelModel.setCode(educationLevel.getCode());
                    educationLevelModel.setDescription(educationLevel.getDescription());
                    educationLevelModel.setIsActive(educationLevel.getIsActive());
                    System.out.println(educationLevelModel.getLevel() + " = " + maxLevel);
                    if(maxLevel == educationLevel.getLevel() || educationLevel.getLevel() == null){
                		educationLevelModel.setIsActiveEditable(Boolean.FALSE);
                    }else{
                    	educationLevelModel.setIsActiveEditable(Boolean.TRUE);
                    }
                    isUpdate = Boolean.TRUE;
                }
            } catch (Exception e) {
                LOGGER.error("Error", e);
            }
        }
    }

    @PreDestroy
    public void cleanAndExit() {
        educationLevelService = null;
        educationLevelModel = null;
        isUpdate = null;
    }

    public void doChangeLevel() throws Exception{
    	if(educationLevelModel.getIsActive() && educationLevelModel.getLevel() == null){
    		Integer maxLevel = educationLevelService.getCurrentMaxLevel();
    		educationLevelModel.setLevel(maxLevel + 1);
    	}
    }
    
    public EducationLevelModel getEducationLevelModel() {
        return educationLevelModel;
    }

    public void setEducationLevelModel(EducationLevelModel educationLevelModel) {
        this.educationLevelModel = educationLevelModel;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public void setEducationLevelService(EducationLevelService educationLevelService) {
        this.educationLevelService = educationLevelService;
    }

    public void doSave() {
        EducationLevel educationLevel = getEntityFromViewModel(educationLevelModel);
        try {
            if (isUpdate) {
                educationLevelService.update(educationLevel);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                educationLevelService.save(educationLevel);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch (BussinessException ex) { 
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    private EducationLevel getEntityFromViewModel(EducationLevelModel model) {
        EducationLevel educationLevel = new EducationLevel();
        if (model.getId() != null) {
            educationLevel.setId(model.getId());
        }
        educationLevel.setName(model.getName());
        educationLevel.setLevel(model.getLevel());
        educationLevel.setCode(model.getCode());
        educationLevel.setDescription(model.getDescription());
        educationLevel.setIsActive(model.getIsActive());
        return educationLevel;
    }
}
