package com.inkubator.hrm.web.personalia;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.BioData;
import com.inkubator.hrm.entity.BioProject;
import com.inkubator.hrm.service.BioProjectService;
import com.inkubator.hrm.web.model.BioProjectModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
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
@ManagedBean(name = "bioProjectFormController")
@ViewScoped
public class BioProjectFormController extends BaseController {

    private BioProjectModel bioProjectModel;
    private Boolean isUpdate;
    @ManagedProperty(value = "#{bioProjectService}")
    private BioProjectService bioProjectService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try {
            bioProjectModel = new BioProjectModel();
            isUpdate = Boolean.FALSE;
            String bioDataId = FacesUtil.getRequestParameter("bioDataId");
            bioProjectModel.setBioDataId(Long.parseLong(bioDataId));

            String bioProjectId = FacesUtil.getRequestParameter("bioProjectId");
            if (StringUtils.isNotEmpty(bioProjectId)) {
                BioProject bioProject = bioProjectService.getEntityByPKWithDetail(Long.parseLong(bioProjectId));
                if (bioProjectId != null) {
                    bioProjectModel = getModelFromEntity(bioProject);
                    isUpdate = Boolean.TRUE;
                }
            }
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    @PreDestroy
    public void cleanAndExit() {
        bioProjectService = null;
//        bioProjectModel = null;
        isUpdate = null;
    }

    public BioProjectModel getBioProjectModel() {
        return bioProjectModel;
    }

    public void setBioProjectModel(BioProjectModel bioProjectModel) {
        this.bioProjectModel = bioProjectModel;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public void setBioProjectService(BioProjectService bioProjectService) {
        this.bioProjectService = bioProjectService;
    }

    public void doSave() {
        BioProject bioProject = getEntityFromViewModel(bioProjectModel);
        try {
            if (isUpdate) {
                bioProjectService.update(bioProject);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                bioProjectService.save(bioProject);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch (BussinessException ex) { 
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    private BioProject getEntityFromViewModel(BioProjectModel bioProjectModel) {
        try {
            BioProject bioProject = new BioProject();
            if (bioProjectModel.getId() != null) {
                bioProject.setId(bioProjectModel.getId());
            }
            bioProject.setBioData(new BioData(bioProjectModel.getBioDataId()));
            bioProject.setCode(bioProjectModel.getCode());
            bioProject.setName(bioProjectModel.getName());
            bioProject.setStartDate(bioProjectModel.getStartDate());
            bioProject.setEndDate(bioProjectModel.getEndDate());
            bioProject.setPosition(bioProjectModel.getPosition());
            bioProject.setCompanyName(bioProjectModel.getCompanyName());
            bioProject.setDescription(bioProjectModel.getDescription());
            return bioProject;
        } catch (Exception ex) {
            Logger.getLogger(BioProjectFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private BioProjectModel getModelFromEntity(BioProject entity) {
        BioProjectModel bioProjectModel = new BioProjectModel();
        bioProjectModel.setId(entity.getId());
        bioProjectModel.setBioDataId(entity.getBioData().getId());
        bioProjectModel.setCode(entity.getCode());
        bioProjectModel.setName(entity.getName());
        bioProjectModel.setStartDate(entity.getStartDate());
        bioProjectModel.setEndDate(entity.getEndDate());
        bioProjectModel.setPosition(entity.getPosition());
        bioProjectModel.setCompanyName(entity.getCompanyName());
        bioProjectModel.setDescription(entity.getDescription());
        return bioProjectModel;
    }

   
}
