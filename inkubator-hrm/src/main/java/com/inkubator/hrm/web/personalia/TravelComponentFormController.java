package com.inkubator.hrm.web.personalia;

import com.inkubator.hrm.web.reference.*;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.TravelComponent;
import com.inkubator.hrm.service.TravelComponentService;
import com.inkubator.hrm.web.model.TravelComponentModel;
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
@ManagedBean(name = "travelComponentFormController")
@ViewScoped
public class TravelComponentFormController extends BaseController {

    private TravelComponentModel travelComponentModel;
    private Boolean isUpdate;
    @ManagedProperty(value = "#{travelComponentService}")
    private TravelComponentService travelComponentService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        String param = FacesUtil.getRequestParameter("param");
        travelComponentModel = new TravelComponentModel();
        isUpdate = Boolean.FALSE;
        if (StringUtils.isNumeric(param)) {
            try {
                TravelComponent travelComponent = travelComponentService.getEntiyByPK(Long.parseLong(param));
                if (travelComponent != null) {
                    travelComponentModel.setId(travelComponent.getId());
                    travelComponentModel.setCode(travelComponent.getCode());
                    travelComponentModel.setName(travelComponent.getName());
                    travelComponentModel.setMeasurement(travelComponent.getMeasurement());
                    travelComponentModel.setDescription(travelComponent.getDescription());
                    isUpdate = Boolean.TRUE;
                }
            } catch (Exception e) {
                LOGGER.error("Error", e);
            }
        }
    }

    @PreDestroy
    public void cleanAndExit() {
        travelComponentService = null;
//        travelComponentModel = null;
        isUpdate = null;
    }

    public TravelComponentModel getTravelComponentModel() {
        return travelComponentModel;
    }

    public void setTravelComponentModel(TravelComponentModel travelComponentModel) {
        this.travelComponentModel = travelComponentModel;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public void setTravelComponentService(TravelComponentService travelComponentService) {
        this.travelComponentService = travelComponentService;
    }

    public void doSave() {
        TravelComponent travelComponent = getEntityFromViewModel(travelComponentModel);
        try {
            if (isUpdate) {
                travelComponentService.update(travelComponent);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                travelComponentService.save(travelComponent);
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

    private TravelComponent getEntityFromViewModel(TravelComponentModel travelComponentModel) {
        TravelComponent travelComponent = new TravelComponent();
        if (travelComponentModel.getId() != null) {
            travelComponent.setId(travelComponentModel.getId());
        }
        travelComponent.setCode(travelComponentModel.getCode());
        travelComponent.setName(travelComponentModel.getName());
        travelComponent.setMeasurement(travelComponentModel.getMeasurement());
        travelComponent.setDescription(travelComponentModel.getDescription());
        return travelComponent;
    }
}
