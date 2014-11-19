package com.inkubator.hrm.web.reference;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.Race;
import com.inkubator.hrm.service.RaceService;
import com.inkubator.hrm.web.model.RaceModel;
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
@ManagedBean(name = "raceFormController")
@ViewScoped
public class RaceFormController extends BaseController {

    private RaceModel raceModel;
    private Boolean isUpdate;
    @ManagedProperty(value = "#{raceService}")
    private RaceService raceService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        String param = FacesUtil.getRequestParameter("param");
        raceModel = new RaceModel();
        isUpdate = Boolean.FALSE;
        if (StringUtils.isNumeric(param)) {
            try {
                Race race = raceService.getEntiyByPK(Long.parseLong(param));
                if (race != null) {
                    raceModel.setId(race.getId());
                    raceModel.setRaceCode(race.getRaceCode());
                    raceModel.setRaceName(race.getRaceName());
                    raceModel.setDescription(race.getDescription());
                    isUpdate = Boolean.TRUE;
                }
            } catch (Exception e) {
                LOGGER.error("Error", e);
            }
        }
    }

    @PreDestroy
    public void cleanAndExit() {
        raceService = null;
        raceModel = null;
        isUpdate = null;
    }

    public RaceModel getRaceModel() {
        return raceModel;
    }

    public void setRaceModel(RaceModel raceModel) {
        this.raceModel = raceModel;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public void setRaceService(RaceService raceService) {
        this.raceService = raceService;
    }

    public void doSave() {
        Race race = getEntityFromViewModel(raceModel);
        try {
            if (isUpdate) {
                raceService.update(race);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                raceService.save(race);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch (BussinessException ex) { 
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    private Race getEntityFromViewModel(RaceModel raceModel) {
        Race race = new Race();
        if (raceModel.getId() != null) {
            race.setId(raceModel.getId());
        }
        race.setRaceCode(raceModel.getRaceCode());
        race.setRaceName(raceModel.getRaceName());
        race.setDescription(raceModel.getDescription());
        return race;
    }
}
