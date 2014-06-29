package com.inkubator.hrm.web.reference;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.City;
import com.inkubator.hrm.service.CityService;
import com.inkubator.hrm.web.lazymodel.CityLazyDataModel;
import com.inkubator.hrm.web.search.CitySearchParameter;
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
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import org.springframework.dao.DataIntegrityViolationException;

/**
 *
 * @author Taufik Hidayat
 */
@ManagedBean(name = "cityViewController")
@ViewScoped
public class CityViewController extends BaseController {

    private CitySearchParameter searchParameter;
    private LazyDataModel<City> lazyDataCity;
    private City selectedCity;
    @ManagedProperty(value = "#{cityService}")
    private CityService cityService;
    private MapModel emptyModel;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        searchParameter = new CitySearchParameter();
        emptyModel = new DefaultMapModel();
    }

    @PreDestroy
    public void cleanAndExit() {
        cityService = null;
        searchParameter = null;
        lazyDataCity = null;
        selectedCity = null;
        emptyModel = null;
    }

    public void setCityService(CityService cityService) {
        this.cityService = cityService;
    }

    public CitySearchParameter getSearchParameter() {
        return searchParameter;
    }

    public void setSearchParameter(CitySearchParameter searchParameter) {
        this.searchParameter = searchParameter;
    }

    public LazyDataModel<City> getLazyDataCity() {
        if (lazyDataCity == null) {
            lazyDataCity = new CityLazyDataModel(searchParameter, cityService);
        }
        return lazyDataCity;
    }

    public void setLazyDataCity(LazyDataModel<City> lazyDataCity) {
        this.lazyDataCity = lazyDataCity;
    }

    public City getSelectedCity() {
        return selectedCity;
    }

    public void setSelectedCity(City selectedCity) {
        this.selectedCity = selectedCity;
    }

    public MapModel getEmptyModel() {
        return emptyModel;
    }

    public void setEmptyModel(MapModel emptyModel) {
        this.emptyModel = emptyModel;
    }

    public void doSearch() {
        lazyDataCity = null;
    }

    public void doDetail() {
        emptyModel.getMarkers().clear();
        try {
            selectedCity = this.cityService.getCityByIdWithDetail(selectedCity.getId());
            LatLng coord = new LatLng(Double.parseDouble(selectedCity.getLatitude()), Double.parseDouble(selectedCity.getLongitude()));

            //Basic marker
            emptyModel.addOverlay(new Marker(coord, selectedCity.getCityName()));
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public void doDelete() {
        try {
            cityService.delete(selectedCity);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            LOGGER.error("Error when doDelete city ", ex);
        } catch (Exception ex) {
            LOGGER.error("Error when doDelete city", ex);
        }
    }

    public void doAdd() {
        showDialog(null);
    }

    public void doUpdate() {
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> values = new ArrayList<>();
        values.add(String.valueOf(selectedCity.getId()));
        dataToSend.put("param", values);
        showDialog(dataToSend);
    }

    private void showDialog(Map<String, List<String>> params) {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 1000);
        options.put("contentHeight", 450);
        RequestContext.getCurrentInstance().openDialog("city_form", options, params);
    }

    @Override
    public void onDialogReturn(SelectEvent event) {
        //re-calculate searching
        doSearch();
        super.onDialogReturn(event);
    }
}
