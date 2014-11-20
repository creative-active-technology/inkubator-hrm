package com.inkubator.hrm.web.reference;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.FamilyRelation;
import com.inkubator.hrm.service.FamilyRelationService;
import com.inkubator.hrm.web.model.FamilyRelationModel;
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
 * @author Deni Husni FR
 */
@ManagedBean(name = "familyRelationFormController")
@ViewScoped
public class FamilyRelationFormController extends BaseController {

    private FamilyRelationModel familyRelationModel;
    private Boolean isUpdate;
    @ManagedProperty(value = "#{familyRelationService}")
    private FamilyRelationService familyRelationService;

    public void setFamilyRelationService(FamilyRelationService familyRelationService) {
        this.familyRelationService = familyRelationService;
    }

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        String param = FacesUtil.getRequestParameter("param");
        familyRelationModel = new FamilyRelationModel();
        isUpdate = Boolean.FALSE;
        if (StringUtils.isNumeric(param)) {
            try {
                FamilyRelation religion = familyRelationService.getEntiyByPK(Long.parseLong(param));
                if (religion != null) {
                    familyRelationModel.setId(religion.getId());
                    familyRelationModel.setRelationName(religion.getRelasiName());
                    isUpdate = Boolean.TRUE;
                }
            } catch (Exception e) {
                LOGGER.error("Error", e);
            }
        }
    }

    @PreDestroy
    public void cleanAndExit() {
        familyRelationService = null;
        familyRelationModel = null;
        isUpdate = null;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

  
    public void doSave() {
        FamilyRelation familyRelation = getEntityFromViewModel(familyRelationModel);
        try {
            if (isUpdate) {
                familyRelationService.update(familyRelation);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                familyRelationService.save(familyRelation);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch (BussinessException ex) { 
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    private FamilyRelation getEntityFromViewModel(FamilyRelationModel religionModel) {
        FamilyRelation familyRelation = new FamilyRelation();
        if (religionModel.getId() != null) {
            familyRelation.setId(religionModel.getId());
        }
        familyRelation.setRelasiName(religionModel.getRelationName());
        return familyRelation;
    }

    public FamilyRelationModel getFamilyRelationModel() {
        return familyRelationModel;
    }

    public void setFamilyRelationModel(FamilyRelationModel familyRelationModel) {
        this.familyRelationModel = familyRelationModel;
    }
    
    
}
