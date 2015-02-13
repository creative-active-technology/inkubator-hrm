package com.inkubator.hrm.web.reference;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.Religion;
import com.inkubator.hrm.service.ReligionService;
import com.inkubator.hrm.web.model.ReligionModel;
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
@ManagedBean(name = "religionFormController")
@ViewScoped
public class ReligionFormController extends BaseController {

    private ReligionModel religionModel;
    private Boolean isUpdate;
    @ManagedProperty(value = "#{religionService}")
    private ReligionService religionService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        String param = FacesUtil.getRequestParameter("param");
        religionModel = new ReligionModel();
        isUpdate = Boolean.FALSE;
        if (StringUtils.isNumeric(param)) {
            try {
                Religion religion = religionService.getEntiyByPK(Long.parseLong(param));
                if (religion != null) {
                    religionModel.setId(religion.getId());
                    religionModel.setName(religion.getName());
                    isUpdate = Boolean.TRUE;
                }
            } catch (Exception e) {
                LOGGER.error("Error", e);
            }
        }
    }

    @PreDestroy
    public void cleanAndExit() {
        religionService = null;
        religionModel = null;
        isUpdate = null;
    }

    public ReligionModel getReligionModel() {
        return religionModel;
    }

    public void setReligionModel(ReligionModel religionModel) {
        this.religionModel = religionModel;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public void setReligionService(ReligionService religionService) {
        this.religionService = religionService;
    }

    public void doSave() {
        Religion religion = getEntityFromViewModel(religionModel);
        try {
            if (isUpdate) {
                religionService.update(religion);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                religionService.save(religion);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch (BussinessException ex) { 
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    private Religion getEntityFromViewModel(ReligionModel religionModel) {
        Religion religion = new Religion();
        if (religionModel.getId() != null) {
            religion.setId(religionModel.getId());
        }
        religion.setCode(religionModel.getCode());
        religion.setName(religionModel.getName());
        religion.setDescription(religionModel.getDescription());
        return religion;
    }
}
