package com.inkubator.hrm.web.reference;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.OccupationType;
import com.inkubator.hrm.service.OccupationTypeService;
import com.inkubator.hrm.web.model.OccupationTypeModel;
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
@ManagedBean(name = "occupationTypeFormController")
@ViewScoped
public class OccupationTypeFormController extends BaseController {

    private OccupationTypeModel occupationTypeModel;
    private Boolean isUpdate;
    @ManagedProperty(value = "#{occupationTypeService}")
    private OccupationTypeService occupationTypeService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        String param = FacesUtil.getRequestParameter("param");
        occupationTypeModel = new OccupationTypeModel();
        isUpdate = Boolean.FALSE;
        if (StringUtils.isNumeric(param)) {
            try {
                OccupationType occupationType = occupationTypeService.getEntiyByPK(Long.parseLong(param));
                if (occupationType != null) {
                    occupationTypeModel.setId(occupationType.getId());
                    occupationTypeModel.setOccupationTypeName(occupationType.getOccupationTypeName());
                    occupationTypeModel.setDescription(occupationType.getDescription());
                    isUpdate = Boolean.TRUE;
                }
            } catch (Exception e) {
                LOGGER.error("Error", e);
            }
        }
    }

    @PreDestroy
    public void cleanAndExit() {
        occupationTypeService = null;
        occupationTypeModel = null;
        isUpdate = null;
    }

    public OccupationTypeModel getOccupationTypeModel() {
        return occupationTypeModel;
    }

    public void setOccupationTypeModel(OccupationTypeModel occupationTypeModel) {
        this.occupationTypeModel = occupationTypeModel;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public void setOccupationTypeService(OccupationTypeService occupationTypeService) {
        this.occupationTypeService = occupationTypeService;
    }

    public void doSave() {
        OccupationType occupationType = getEntityFromViewModel(occupationTypeModel);
        try {
            if (isUpdate) {
                occupationTypeService.update(occupationType);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                occupationTypeService.save(occupationType);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch (BussinessException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    private OccupationType getEntityFromViewModel(OccupationTypeModel occupationTypeModel) {
        OccupationType occupationType = new OccupationType();
        if (occupationTypeModel.getId() != null) {
            occupationType.setId(occupationTypeModel.getId());
        }
        occupationType.setOccupationTypeName(occupationTypeModel.getOccupationTypeName());
        occupationType.setDescription(occupationTypeModel.getDescription());
        return occupationType;
    }
}
