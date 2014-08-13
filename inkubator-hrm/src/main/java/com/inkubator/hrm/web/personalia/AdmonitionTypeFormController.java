package com.inkubator.hrm.web.personalia;

import com.inkubator.hrm.web.reference.*;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.AdmonitionType;
import com.inkubator.hrm.service.AdmonitionTypeService;
import com.inkubator.hrm.web.model.AdmonitionTypeModel;
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
@ManagedBean(name = "admonitionTypeFormController")
@ViewScoped
public class AdmonitionTypeFormController extends BaseController {

    private AdmonitionTypeModel admonitionTypeModel;
    private Boolean isUpdate;
    @ManagedProperty(value = "#{admonitionTypeService}")
    private AdmonitionTypeService admonitionTypeService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        String param = FacesUtil.getRequestParameter("param");
        admonitionTypeModel = new AdmonitionTypeModel();
        isUpdate = Boolean.FALSE;
        if (StringUtils.isNumeric(param)) {
            try {
                AdmonitionType admonitionType = admonitionTypeService.getEntiyByPK(Long.parseLong(param));
                if (admonitionType != null) {
                    admonitionTypeModel.setId(admonitionType.getId());
                    admonitionTypeModel.setCode(admonitionType.getCode());
                    admonitionTypeModel.setName(admonitionType.getName());
                    admonitionTypeModel.setDescription(admonitionType.getDescription());
                    isUpdate = Boolean.TRUE;
                }
            } catch (Exception e) {
                LOGGER.error("Error", e);
            }
        }
    }

    @PreDestroy
    public void cleanAndExit() {
        admonitionTypeService = null;
//        admonitionTypeModel = null;
        isUpdate = null;
    }

    public AdmonitionTypeModel getAdmonitionTypeModel() {
        return admonitionTypeModel;
    }

    public void setAdmonitionTypeModel(AdmonitionTypeModel admonitionTypeModel) {
        this.admonitionTypeModel = admonitionTypeModel;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public void setAdmonitionTypeService(AdmonitionTypeService admonitionTypeService) {
        this.admonitionTypeService = admonitionTypeService;
    }

    public void doSave() {
        AdmonitionType admonitionType = getEntityFromViewModel(admonitionTypeModel);
        try {
            if (isUpdate) {
                admonitionTypeService.update(admonitionType);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                admonitionTypeService.save(admonitionType);
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

    private AdmonitionType getEntityFromViewModel(AdmonitionTypeModel admonitionTypeModel) {
        AdmonitionType admonitionType = new AdmonitionType();
        if (admonitionTypeModel.getId() != null) {
            admonitionType.setId(admonitionTypeModel.getId());
        }
        admonitionType.setCode(admonitionTypeModel.getCode());
        admonitionType.setName(admonitionTypeModel.getName());
        admonitionType.setDescription(admonitionTypeModel.getDescription());
        return admonitionType;
    }
}
