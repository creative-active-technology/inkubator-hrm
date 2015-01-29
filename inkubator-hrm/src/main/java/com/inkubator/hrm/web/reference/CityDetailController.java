/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.reference;

import com.inkubator.hrm.entity.City;
import com.inkubator.hrm.service.CityService;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

/**
 *
 * @author Taufik Hidayat
 */
@ManagedBean(name = "cityDetailController")
@ViewScoped
public class CityDetailController extends BaseController {

    private City selectedCity;
    @ManagedProperty(value = "#{cityService}")
    private CityService cityService;
    private MapModel emptyModel;
    private double defaultLat;
    private double defaultLng;

    public City getSelectedCity() {
        return selectedCity;
    }

    public void setSelectedCity(City selectedCity) {
        this.selectedCity = selectedCity;
    }

    public void setCityService(CityService cityService) {
        this.cityService = cityService;
    }

    public MapModel getEmptyModel() {
        return emptyModel;
    }

    public void setEmptyModel(MapModel emptyModel) {
        this.emptyModel = emptyModel;
    }

    public double getDefaultLat() {
        return defaultLat;
    }

    public void setDefaultLat(double defaultLat) {
        this.defaultLat = defaultLat;
    }

    public double getDefaultLng() {
        return defaultLng;
    }

    public void setDefaultLng(double defaultLng) {
        this.defaultLng = defaultLng;
    }

    
    
    @PostConstruct
    @Override
    public void initialization() {
        try {
            emptyModel = new DefaultMapModel();
            super.initialization();
            String userId = FacesUtil.getRequestParameter("execution");
            
            selectedCity = cityService.getCityByIdWithDetail(Long.parseLong(userId.substring(1)));

            LatLng coord = new LatLng(Double.parseDouble(selectedCity.getLatitude()), Double.parseDouble(selectedCity.getLongitude()));
            //Basic marker
            emptyModel.addOverlay(new Marker(coord, selectedCity.getCityName()));

            defaultLat = Double.parseDouble(selectedCity.getLatitude());
            defaultLng = Double.parseDouble(selectedCity.getLongitude());
            
        } catch (Exception ex) {
            LOGGER.error("Error", ex);

        }
    }

    public String doBack() {
        return "/protected/reference/city_view.htm?faces-redirect=true";
    }

    public String doEdit() {
        return "/protected/reference/city_form.htm?faces-redirect=true&execution=e" + selectedCity.getId();
    }

    @PreDestroy
    public void cleanAndExit() {
        selectedCity = null;
        cityService = null;
        emptyModel = null;
    }

}
