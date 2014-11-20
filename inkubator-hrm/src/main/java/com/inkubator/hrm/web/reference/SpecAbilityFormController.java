package com.inkubator.hrm.web.reference;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.SpecificationAbility;
import com.inkubator.hrm.service.SpecificationAbilityService;
import com.inkubator.hrm.web.model.SpecAbilityModel;
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

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "specAbilityFormController")
@ViewScoped
public class SpecAbilityFormController extends BaseController {

    private SpecAbilityModel specAbilityModel;
    private Boolean isUpdate;
    @ManagedProperty(value = "#{specificationAbilityService}")
    private SpecificationAbilityService specificationAbilityService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        String param = FacesUtil.getRequestParameter("execution");
        specAbilityModel = new SpecAbilityModel();
        isUpdate = Boolean.FALSE;
        if (StringUtils.isNotEmpty(param)) {
            try {
                SpecificationAbility specificationAbility = specificationAbilityService.getEntiyByPK(Long.parseLong(param.substring(1)));
                if (specificationAbility != null) {
                    getModelFromEntity(specificationAbility);
                    isUpdate = Boolean.TRUE;
                }
            } catch (Exception e) {
                LOGGER.error("Error", e);
            }
        }
    }

    @PreDestroy
    public void cleanAndExit() {
        specificationAbilityService = null;
        specAbilityModel = null;
        isUpdate = null;
    }

    public SpecAbilityModel getSpecAbilityModel() {
        return specAbilityModel;
    }

    public void setSpecAbilityModel(SpecAbilityModel specAbilityModel) {
        this.specAbilityModel = specAbilityModel;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public void setSpecificationAbilityService(SpecificationAbilityService specificationAbilityService) {
        this.specificationAbilityService = specificationAbilityService;
    }
    
    public void doReset() {
    	if(isUpdate) {
    		try {
    			SpecificationAbility specificationAbility = specificationAbilityService.getEntiyByPK(specAbilityModel.getId());
    			if (specificationAbility != null) {
                    getModelFromEntity(specificationAbility);
    			}
    		} catch (Exception ex) {
    			LOGGER.error("Error", ex);
    		}
    	} else {
    		specAbilityModel = new SpecAbilityModel();
    	}    	
    }

    public String doSave() {
        SpecificationAbility specificationAbility = getEntityFromViewModel(specAbilityModel);
        try {
            if (isUpdate) {
                specificationAbilityService.update(specificationAbility);
                MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.update_successfully",
                        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

            } else {
                specificationAbilityService.save(specificationAbility);
                MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully",
                        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            }
            return "/protected/reference/spec_ability_view.htm?faces-redirect=true";
        } catch (BussinessException ex) { 
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
        return null;
    }

    private SpecificationAbility getEntityFromViewModel(SpecAbilityModel model) {
        SpecificationAbility specificationAbility = new SpecificationAbility();
        if (model.getId() != null) {
            specificationAbility.setId(model.getId());
        }

        //sperate the value with delimiter
        StringBuffer buffOpts = new StringBuffer();
        StringBuffer buffScales = new StringBuffer();
        for (int i = 0; i < model.getTotalOption(); i++) {
            if (i != 0) {
                buffOpts.append(HRMConstant.DELIMITER);
                buffScales.append(HRMConstant.DELIMITER);
            }
            buffOpts.append(model.getOptions()[i]);
            buffScales.append(model.getScaleValue()[i]);
        }

        specificationAbility.setName(model.getName());
        specificationAbility.setOptionAbility(buffOpts.toString());
        specificationAbility.setScaleValue(buffScales.toString());

        return specificationAbility;
    }
    
    private void getModelFromEntity(SpecificationAbility specAbility){
    	specAbilityModel.setId(specAbility.getId());
        specAbilityModel.setName(specAbility.getName());
        String options[] = StringUtils.split(specAbility.getOptionAbility(), HRMConstant.DELIMITER);
        specAbilityModel.setTotalOption(options.length);
        specAbilityModel.setOptions(options);
        String scaleVals[] = StringUtils.split(specAbility.getScaleValue(), HRMConstant.DELIMITER);
        specAbilityModel.setScaleValue(scaleVals);
    	
    }

    /* rule, if total option is 4 -> then scale value 0, 33, 66, 100 
     *                          3 -> then scale value 0, 50, 100
     *                          6 -> then scale value 0, 20, 40, 60, 80, 100
     * */
    public void doAdjustScaleValue() {
        int scaleValue = 0;

        //find divisor value, only if totalOption is more than 1(one)
        int divisor = 0;
        if (specAbilityModel.getTotalOption() > 1) {
            divisor = 100 / (specAbilityModel.getTotalOption() - 1);
        }

        for (int i = 0; i < specAbilityModel.getTotalOption(); i++) {
            //adjust value to 100, only if end of loop or totalOtion is equal with 1(one)
            if (specAbilityModel.getTotalOption() == 1 || specAbilityModel.getTotalOption() == i + 1) {
                scaleValue = 100;
            }
            specAbilityModel.getScaleValue()[i] = String.valueOf(scaleValue);
            scaleValue = scaleValue + divisor;
        }
    }

    public String doBack() {
        return "/protected/reference/spec_ability_view.htm?faces-redirect=true";
    }
}
