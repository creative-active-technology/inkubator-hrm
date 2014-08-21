package com.inkubator.hrm.web.personalia;

import com.inkubator.hrm.web.reference.*;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.TravelType;
import com.inkubator.hrm.service.TravelTypeService;
import com.inkubator.hrm.web.lazymodel.TravelTypeLazyDataModel;
import com.inkubator.hrm.web.search.TravelTypeSearchParameter;
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
@ManagedBean(name = "travelTypeViewController")
@ViewScoped
public class TravelTypeViewController extends BaseController {

    private TravelTypeSearchParameter searchParameter;
    private LazyDataModel<TravelType> lazyDataTravelType;
    private TravelType selectedTravelType;
    @ManagedProperty(value = "#{travelTypeService}")
    private TravelTypeService travelTypeService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        searchParameter = new TravelTypeSearchParameter();
    }

    @PreDestroy
    public void cleanAndExit() {
        travelTypeService = null;
        searchParameter = null;
        lazyDataTravelType = null;
        selectedTravelType = null;
    }

    public void setTravelTypeService(TravelTypeService travelTypeService) {
        this.travelTypeService = travelTypeService;
    }

    public TravelTypeSearchParameter getSearchParameter() {
        return searchParameter;
    }

    public void setSearchParameter(TravelTypeSearchParameter searchParameter) {
        this.searchParameter = searchParameter;
    }

    

    public LazyDataModel<TravelType> getLazyDataTravelType() {
        if (lazyDataTravelType == null) {
            lazyDataTravelType = new TravelTypeLazyDataModel(searchParameter, travelTypeService);
        }
        return lazyDataTravelType;
    }

    public void setLazyDataTravelType(LazyDataModel<TravelType> lazyDataTravelType) {
        this.lazyDataTravelType = lazyDataTravelType;
    }

    public TravelType getSelectedTravelType() {
        return selectedTravelType;
    }

    public void setSelectedTravelType(TravelType selectedTravelType) {
        this.selectedTravelType = selectedTravelType;
    }

    public void doSearch() {
        lazyDataTravelType = null;
    }

    public void doDetail() {
        try {
            selectedTravelType = this.travelTypeService.getEntityByPKWithDetail(selectedTravelType.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public void doDelete() {
        try {
            travelTypeService.delete(selectedTravelType);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            LOGGER.error("Error when doDelete travelType ", ex);
        } catch (Exception ex) {
            LOGGER.error("Error when doDelete travelType", ex);
        }
    }

    public void doAdd() {
        showDialog(null);
    }

    public void doUpdate() {
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> values = new ArrayList<>();
        values.add(String.valueOf(selectedTravelType.getId()));
        dataToSend.put("param", values);
        showDialog(dataToSend);
    }

    private void showDialog(Map<String, List<String>> params) {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 450);
        options.put("contentHeight", 475);
        RequestContext.getCurrentInstance().openDialog("travel_type_form", options, params);
    }

    @Override
    public void onDialogReturn(SelectEvent event) {
        //re-calculate searching
        doSearch();
        super.onDialogReturn(event);
    }
}
