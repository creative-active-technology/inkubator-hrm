package com.inkubator.hrm.web.personalia;

import com.inkubator.hrm.web.reference.*;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.TravelComponent;
import com.inkubator.hrm.service.TravelComponentService;
import com.inkubator.hrm.web.lazymodel.TravelComponentLazyDataModel;
import com.inkubator.hrm.web.search.TravelComponentSearchParameter;
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
@ManagedBean(name = "travelComponentViewController")
@ViewScoped
public class TravelComponentViewController extends BaseController {

    private TravelComponentSearchParameter searchParameter;
    private LazyDataModel<TravelComponent> lazyDataTravelComponent;
    private TravelComponent selectedTravelComponent;
    @ManagedProperty(value = "#{travelComponentService}")
    private TravelComponentService travelComponentService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        searchParameter = new TravelComponentSearchParameter();
    }

    @PreDestroy
    public void cleanAndExit() {
        travelComponentService = null;
        searchParameter = null;
        lazyDataTravelComponent = null;
        selectedTravelComponent = null;
    }

    public void setTravelComponentService(TravelComponentService travelComponentService) {
        this.travelComponentService = travelComponentService;
    }

    public TravelComponentSearchParameter getSearchParameter() {
        return searchParameter;
    }

    public void setSearchParameter(TravelComponentSearchParameter searchParameter) {
        this.searchParameter = searchParameter;
    }

    

    public LazyDataModel<TravelComponent> getLazyDataTravelComponent() {
        if (lazyDataTravelComponent == null) {
            lazyDataTravelComponent = new TravelComponentLazyDataModel(searchParameter, travelComponentService);
        }
        return lazyDataTravelComponent;
    }

    public void setLazyDataTravelComponent(LazyDataModel<TravelComponent> lazyDataTravelComponent) {
        this.lazyDataTravelComponent = lazyDataTravelComponent;
    }

    public TravelComponent getSelectedTravelComponent() {
        return selectedTravelComponent;
    }

    public void setSelectedTravelComponent(TravelComponent selectedTravelComponent) {
        this.selectedTravelComponent = selectedTravelComponent;
    }

    public void doSearch() {
        lazyDataTravelComponent = null;
    }

    public void doDetail() {
        try {
            selectedTravelComponent = this.travelComponentService.getEntiyByPK(selectedTravelComponent.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public void doDelete() {
        try {
            travelComponentService.delete(selectedTravelComponent);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            LOGGER.error("Error when doDelete travelComponent ", ex);
        } catch (Exception ex) {
            LOGGER.error("Error when doDelete travelComponent", ex);
        }
    }

    public void doAdd() {
        showDialog(null);
    }

    public void doUpdate() {
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> values = new ArrayList<>();
        values.add(String.valueOf(selectedTravelComponent.getId()));
        dataToSend.put("param", values);
        showDialog(dataToSend);
    }

    private void showDialog(Map<String, List<String>> params) {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 400);
        options.put("contentHeight", 500);
        RequestContext.getCurrentInstance().openDialog("travel_component_form", options, params);
    }

    @Override
    public void onDialogReturn(SelectEvent event) {
        //re-calculate searching
        doSearch();
        super.onDialogReturn(event);
    }
}
