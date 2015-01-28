package com.inkubator.hrm.web.personalia;

import com.inkubator.hrm.web.reference.*;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.TravelZone;
import com.inkubator.hrm.service.TravelZoneService;
import com.inkubator.hrm.web.lazymodel.TravelZoneLazyDataModel;
import com.inkubator.hrm.web.search.TravelZoneSearchParameter;
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
@ManagedBean(name = "travelZoneViewController")
@ViewScoped
public class TravelZoneViewController extends BaseController {

    private TravelZoneSearchParameter searchParameter;
    private LazyDataModel<TravelZone> lazyDataTravelZone;
    private TravelZone selectedTravelZone;
    @ManagedProperty(value = "#{travelZoneService}")
    private TravelZoneService travelZoneService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        searchParameter = new TravelZoneSearchParameter();
    }

    @PreDestroy
    public void cleanAndExit() {
        travelZoneService = null;
        searchParameter = null;
        lazyDataTravelZone = null;
        selectedTravelZone = null;
    }

    public void setTravelZoneService(TravelZoneService travelZoneService) {
        this.travelZoneService = travelZoneService;
    }

    public TravelZoneSearchParameter getSearchParameter() {
        return searchParameter;
    }

    public void setSearchParameter(TravelZoneSearchParameter searchParameter) {
        this.searchParameter = searchParameter;
    }

    

    public LazyDataModel<TravelZone> getLazyDataTravelZone() {
        if (lazyDataTravelZone == null) {
            lazyDataTravelZone = new TravelZoneLazyDataModel(searchParameter, travelZoneService);
        }
        return lazyDataTravelZone;
    }

    public void setLazyDataTravelZone(LazyDataModel<TravelZone> lazyDataTravelZone) {
        this.lazyDataTravelZone = lazyDataTravelZone;
    }

    public TravelZone getSelectedTravelZone() {
        return selectedTravelZone;
    }

    public void setSelectedTravelZone(TravelZone selectedTravelZone) {
        this.selectedTravelZone = selectedTravelZone;
    }

    public void doSearch() {
        lazyDataTravelZone = null;
    }

    public void doDetail() {
        try {
            selectedTravelZone = this.travelZoneService.getEntiyByPK(selectedTravelZone.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public void doDelete() {
        try {
            travelZoneService.delete(selectedTravelZone);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            LOGGER.error("Error when doDelete travelZone ", ex);
        } catch (Exception ex) {
            LOGGER.error("Error when doDelete travelZone", ex);
        }
    }

    public void doAdd() {
        showDialog(null);
    }

    public void doUpdate() {
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> values = new ArrayList<>();
        values.add(String.valueOf(selectedTravelZone.getId()));
        dataToSend.put("param", values);
        showDialog(dataToSend);
    }

    private void showDialog(Map<String, List<String>> params) {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 500);
        options.put("contentHeight", 450);
        RequestContext.getCurrentInstance().openDialog("travel_zone_form", options, params);
    }

    @Override
    public void onDialogReturn(SelectEvent event) {
        //re-calculate searching
        doSearch();
        super.onDialogReturn(event);
    }
}
