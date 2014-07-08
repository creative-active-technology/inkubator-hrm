package com.inkubator.hrm.web.reference;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.City;
import com.inkubator.hrm.service.CityService;
import com.inkubator.hrm.web.lazymodel.CityLazyDataModel;
import com.inkubator.hrm.web.search.CitySearchParameter;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.hibernate.exception.ConstraintViolationException;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
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

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        searchParameter = new CitySearchParameter();
    }

    @PreDestroy
    public void cleanAndExit() {
        cityService = null;
        searchParameter = null;
        lazyDataCity = null;
        selectedCity = null;
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

    public void doSearch() {
        lazyDataCity = null;
    }

    public String doDetail() {
//        emptyModel.getMarkers().clear();
//        try {
//            selectedCity = this.cityService.getCityByIdWithDetail(selectedCity.getId());
//            LatLng coord = new LatLng(Double.parseDouble(selectedCity.getLatitude()), Double.parseDouble(selectedCity.getLongitude()));
//
//            //Basic marker
//            emptyModel.addOverlay(new Marker(coord, selectedCity.getCityName()));
        return "/protected/reference/city_detail.htm?faces-redirect=true&execution=e" + selectedCity.getId();
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

    public String doAdd() {
        return "/protected/reference/city_form.htm?faces-redirect=true";
    }

    public String doUpdate() {
        return "/protected/reference/city_form.htm?faces-redirect=true&execution=e" + selectedCity.getId();
    }

    @Override
    public void onDialogReturn(SelectEvent event) {
        //re-calculate searching
        doSearch();
        super.onDialogReturn(event);
    }
}
