package com.inkubator.hrm.web.personalia;

import com.inkubator.hrm.web.reference.*;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.TravelZone;
import com.inkubator.hrm.service.TravelZoneService;
import com.inkubator.hrm.web.model.TravelZoneModel;
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
@ManagedBean(name = "travelZoneFormController")
@ViewScoped
public class TravelZoneFormController extends BaseController {

    private TravelZoneModel travelZoneModel;
    private Boolean isUpdate;
    @ManagedProperty(value = "#{travelZoneService}")
    private TravelZoneService travelZoneService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        String param = FacesUtil.getRequestParameter("param");
        travelZoneModel = new TravelZoneModel();
        isUpdate = Boolean.FALSE;
        if (StringUtils.isNumeric(param)) {
            try {
                TravelZone travelZone = travelZoneService.getEntiyByPK(Long.parseLong(param));
                if (travelZone != null) {
                    travelZoneModel.setId(travelZone.getId());
                    travelZoneModel.setCode(travelZone.getCode());
                    travelZoneModel.setName(travelZone.getName());
                    travelZoneModel.setCategory(travelZone.getCategory());
                    travelZoneModel.setDistance(travelZone.getDistance());
                    travelZoneModel.setDescription(travelZone.getDescription());
                    isUpdate = Boolean.TRUE;
                }
            } catch (Exception e) {
                LOGGER.error("Error", e);
            }
        }
    }

    @PreDestroy
    public void cleanAndExit() {
        travelZoneService = null;
//        travelZoneModel = null;
        isUpdate = null;
    }

    public TravelZoneModel getTravelZoneModel() {
        return travelZoneModel;
    }

    public void setTravelZoneModel(TravelZoneModel travelZoneModel) {
        this.travelZoneModel = travelZoneModel;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public void setTravelZoneService(TravelZoneService travelZoneService) {
        this.travelZoneService = travelZoneService;
    }

    public void doSave() {
        TravelZone travelZone = getEntityFromViewModel(travelZoneModel);
        try {
            if (isUpdate) {
                travelZoneService.update(travelZone);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                travelZoneService.save(travelZone);
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

    private TravelZone getEntityFromViewModel(TravelZoneModel travelZoneModel) {
        TravelZone travelZone = new TravelZone();
        if (travelZoneModel.getId() != null) {
            travelZone.setId(travelZoneModel.getId());
        }
        travelZone.setCode(travelZoneModel.getCode());
        travelZone.setName(travelZoneModel.getName());
        travelZone.setCategory(travelZoneModel.getCategory());
        travelZone.setDistance(travelZoneModel.getDistance());
        travelZone.setDescription(travelZoneModel.getDescription());
        return travelZone;
    }
}
