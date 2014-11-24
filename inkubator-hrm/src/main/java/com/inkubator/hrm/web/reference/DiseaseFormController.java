package com.inkubator.hrm.web.reference;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.Disease;
import com.inkubator.hrm.service.DiseaseService;
import com.inkubator.hrm.web.model.DiseaseModel;
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
 * @author Taufik Hidayat
 */
@ManagedBean(name = "diseaseFormController")
@ViewScoped
public class DiseaseFormController extends BaseController {

    private DiseaseModel diseaseModel;
    private Boolean isUpdate;
    @ManagedProperty(value = "#{diseaseService}")
    private DiseaseService diseaseService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        String param = FacesUtil.getRequestParameter("param");
        diseaseModel = new DiseaseModel();
        isUpdate = Boolean.FALSE;
        if (StringUtils.isNumeric(param)) {
            try {
                Disease disease = diseaseService.getEntiyByPK(Long.parseLong(param));
                if (disease != null) {
                    diseaseModel.setId(disease.getId());
                    diseaseModel.setCode(disease.getCode());
                    diseaseModel.setName(disease.getName());
                    diseaseModel.setDescription(disease.getDescription());
                    isUpdate = Boolean.TRUE;
                }
            } catch (Exception e) {
                LOGGER.error("Error", e);
            }
        }
    }

    @PreDestroy
    public void cleanAndExit() {
        diseaseService = null;
//        diseaseModel = null;
        isUpdate = null;
    }

    public DiseaseModel getDiseaseModel() {
        return diseaseModel;
    }

    public void setDiseaseModel(DiseaseModel diseaseModel) {
        this.diseaseModel = diseaseModel;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public void setDiseaseService(DiseaseService diseaseService) {
        this.diseaseService = diseaseService;
    }

    public void doSave() {
        Disease disease = getEntityFromViewModel(diseaseModel);
        try {
            if (isUpdate) {
                diseaseService.update(disease);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                diseaseService.save(disease);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch (BussinessException ex) { 
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    private Disease getEntityFromViewModel(DiseaseModel diseaseModel) {
        Disease disease = new Disease();
        if (diseaseModel.getId() != null) {
            disease.setId(diseaseModel.getId());
        }
        disease.setCode(diseaseModel.getCode());
        disease.setName(diseaseModel.getName());
        disease.setDescription(diseaseModel.getDescription());
        return disease;
    }
}
