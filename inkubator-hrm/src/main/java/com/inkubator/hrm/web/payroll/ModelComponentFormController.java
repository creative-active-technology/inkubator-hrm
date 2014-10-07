package com.inkubator.hrm.web.payroll;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.BenefitGroup;
import com.inkubator.hrm.entity.ModelComponent;
import com.inkubator.hrm.service.BenefitGroupService;
import com.inkubator.hrm.service.ModelComponentService;
import com.inkubator.hrm.util.MapUtil;
import com.inkubator.hrm.web.model.ModelComponentModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
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
@ManagedBean(name = "modelComponentFormController")
@ViewScoped
public class ModelComponentFormController extends BaseController {

    private ModelComponentModel modelComponentModel;
    private Boolean isUpdate;
    @ManagedProperty(value = "#{modelComponentService}")
    private ModelComponentService modelComponentService;
    @ManagedProperty(value = "#{benefitGroupService}")
    private BenefitGroupService benefitGroupService;
    private Map<String, Long> benefits = new TreeMap<>();

    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            String param = FacesUtil.getRequestParameter("param");
            modelComponentModel = new ModelComponentModel();
            isUpdate = Boolean.FALSE;

            List<BenefitGroup> listBenefit = benefitGroupService.getAllData();

            for (BenefitGroup benefit : listBenefit) {
                benefits.put(benefit.getName(), benefit.getId());
            }

            MapUtil.sortByValue(benefits);

            if (StringUtils.isNumeric(param)) {
                ModelComponent modelComponent = modelComponentService.getEntityByPKWithDetail(Long.parseLong(param));
                if (modelComponent != null) {
                    modelComponentModel.setId(modelComponent.getId());
                    modelComponentModel.setCode(modelComponent.getCode());
                    modelComponentModel.setName(modelComponent.getName());
                    modelComponentModel.setDescription(modelComponent.getDescription());
                    modelComponentModel.setSpesific(modelComponent.getSpesific());
                    modelComponentModel.setBenefitGroupId(modelComponent.getBenefitGroup().getId());
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
//        modelComponentModel = null;
        isUpdate = null;
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

    public Map<String, Long> getBenefits() {
        return benefits;
    }

    public void setBenefits(Map<String, Long> benefits) {
        this.benefits = benefits;
    }

    public void setBenefitGroupService(BenefitGroupService benefitGroupService) {
        this.benefitGroupService = benefitGroupService;
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
        } catch (BussinessException ex) { //data already exist(duplicate)
            LOGGER.error("Error", ex);
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
        modelComponent.setBenefitGroup(new BenefitGroup(modelComponentModel.getBenefitGroupId()));
        return modelComponent;
    }
}