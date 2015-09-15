package com.inkubator.hrm.web.payroll;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.ModelComponent;
import com.inkubator.hrm.service.ModelComponentService;
import com.inkubator.hrm.web.model.ModelComponentModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
 *
 * @author Taufik Hidayat
 */
@ManagedBean(name = "modelComponentFormController")
@ViewScoped
public class ModelComponentFormController extends BaseController {

    private ModelComponentModel modelComponentModel;
    private Boolean isUpdate;
    @ManagedProperty(value = "#{modelComponentService}")
    private ModelComponentService modelComponentService;

    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            String param = FacesUtil.getRequestParameter("param");
            modelComponentModel = new ModelComponentModel();
            modelComponentModel.setHasException(Boolean.TRUE); //default is true
            isUpdate = Boolean.FALSE;

            if (StringUtils.isNumeric(param)) {
                ModelComponent modelComponent = modelComponentService.getEntityByPKWithDetail(Long.parseLong(param));
                if (modelComponent != null) {
                    modelComponentModel.setId(modelComponent.getId());
                    modelComponentModel.setCode(modelComponent.getCode());
                    modelComponentModel.setName(modelComponent.getName());
                    modelComponentModel.setDescription(modelComponent.getDescription());
                    modelComponentModel.setSpesific(modelComponent.getSpesific());
                    modelComponentModel.setHasException(modelComponent.getHasException());
                    
                    isUpdate = Boolean.TRUE;
                }

            }
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    @PreDestroy
    public void cleanAndExit() {
        modelComponentService = null;
        modelComponentModel = null;
        isUpdate = null;
    }
    
    public ModelComponentService getModelComponentService() {
		return modelComponentService;
	}

	public ModelComponentModel getModelComponentModel() {
        return modelComponentModel;
    }

    public void setModelComponentModel(ModelComponentModel modelComponentModel) {
        this.modelComponentModel = modelComponentModel;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public void setModelComponentService(ModelComponentService modelComponentService) {
        this.modelComponentService = modelComponentService;
    }    

    public void doSave() {
        ModelComponent modelComponent = getEntityFromViewModel(modelComponentModel);
        try {
            if (isUpdate) {
                modelComponentService.update(modelComponent);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                modelComponentService.save(modelComponent);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch (BussinessException ex) { 
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    private ModelComponent getEntityFromViewModel(ModelComponentModel modelComponentModel) {
        ModelComponent modelComponent = new ModelComponent();
        if (modelComponentModel.getId() != null) {
            modelComponent.setId(modelComponentModel.getId());
        }
        modelComponent.setCode(modelComponentModel.getCode());
        modelComponent.setName(modelComponentModel.getName());
        modelComponent.setSpesific(modelComponentModel.getSpesific());
        modelComponent.setDescription(modelComponentModel.getDescription());
        modelComponent.setHasException(modelComponentModel.getHasException());
        return modelComponent;
    }
    
}
