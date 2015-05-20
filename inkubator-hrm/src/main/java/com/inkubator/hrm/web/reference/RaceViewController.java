package com.inkubator.hrm.web.reference;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.Race;
import com.inkubator.hrm.service.RaceService;
import com.inkubator.hrm.web.lazymodel.RaceLazyDataModel;
import com.inkubator.hrm.web.search.RaceSearchParameter;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.hibernate.exception.ConstraintViolationException;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.springframework.dao.DataIntegrityViolationException;

/**
 *
 * @author Taufik Hidayat
 */
@ManagedBean(name = "raceViewController")
@ViewScoped
public class RaceViewController extends BaseController {

    private RaceSearchParameter searchParameter;
    private LazyDataModel<Race> lazyDataRace;
    private Race selectedRace;
    @ManagedProperty(value = "#{raceService}")
    private RaceService raceService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        searchParameter = new RaceSearchParameter();
    }

    @PreDestroy
    public void cleanAndExit() {
        raceService = null;
        searchParameter = null;
        lazyDataRace = null;
        selectedRace = null;
    }

    public void setRaceService(RaceService raceService) {
        this.raceService = raceService;
    }

    public RaceSearchParameter getSearchParameter() {
        return searchParameter;
    }

    public void setSearchParameter(RaceSearchParameter searchParameter) {
        this.searchParameter = searchParameter;
    }

    

    public LazyDataModel<Race> getLazyDataRace() {
        if (lazyDataRace == null) {
            lazyDataRace = new RaceLazyDataModel(searchParameter, raceService);
        }
        return lazyDataRace;
    }

    public void setLazyDataRace(LazyDataModel<Race> lazyDataRace) {
        this.lazyDataRace = lazyDataRace;
    }

    public Race getSelectedRace() {
        return selectedRace;
    }

    public void setSelectedRace(Race selectedRace) {
        this.selectedRace = selectedRace;
    }

    public void doSearch() {
        lazyDataRace = null;
    }

    public void doDetail() {
        try {
            selectedRace = this.raceService.getEntiyByPK(selectedRace.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public void doDelete() {
        try {
            raceService.delete(selectedRace);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            LOGGER.error("Error when doDelete race ", ex);
        } catch (Exception ex) {
            LOGGER.error("Error when doDelete race", ex);
        }
    }

    public void doAdd() {
        showDialog(null);
    }

    public void doUpdate() {
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> values = new ArrayList<>();
        values.add(String.valueOf(selectedRace.getId()));
        dataToSend.put("param", values);
        showDialog(dataToSend);
    }

    private void showDialog(Map<String, List<String>> params) {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 400);
        options.put("contentHeight", 385);
        RequestContext.getCurrentInstance().openDialog("race_form", options, params);
    }

    @Override
    public void onDialogReturn(SelectEvent event) {
        //re-calculate searching
        doSearch();
        super.onDialogReturn(event);
    }
}
