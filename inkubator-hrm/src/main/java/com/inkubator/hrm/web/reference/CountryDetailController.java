/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.reference;

import com.inkubator.hrm.entity.Country;
import com.inkubator.hrm.service.CountryService;
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
@ManagedBean(name = "countryDetailController")
@ViewScoped
public class CountryDetailController extends BaseController {

    private Country selectedCountry;
    @ManagedProperty(value = "#{countryService}")
    private CountryService countryService;
    private MapModel emptyModel;
    

    public Country getSelectedCountry() {
        return selectedCountry;
    }

    public void setSelectedCountry(Country selectedCountry) {
        this.selectedCountry = selectedCountry;
    }

    public void setCountryService(CountryService countryService) {
        this.countryService = countryService;
    }

    public MapModel getEmptyModel() {
        return emptyModel;
    }

    public void setEmptyModel(MapModel emptyModel) {
        this.emptyModel = emptyModel;
    }
    
    

    @PostConstruct
    @Override
    public void initialization() {
        try {
            emptyModel = new DefaultMapModel();
            super.initialization();
            String userId = FacesUtil.getRequestParameter("execution");
            System.out.println("country id nya : " + userId);
            selectedCountry = countryService.getEntiyByPK(Long.parseLong(userId.substring(1)));

            LatLng coord = new LatLng(Double.parseDouble(selectedCountry.getLatitude()), Double.parseDouble(selectedCountry.getLongitude()));
            //Basic marker
            emptyModel.addOverlay(new Marker(coord, selectedCountry.getCountryName()));

            
        } catch (Exception ex) {
            LOGGER.error("Error", ex);

        }
    }

    public String doBack() {
        return "/protected/reference/country_view.htm?faces-redirect=true";
    }

    public String doEdit() {
        return "/protected/reference/country_form.htm?faces-redirect=true&execution=e" + selectedCountry.getId();
    }

    @PreDestroy
    public void cleanAndExit() {
        selectedCountry = null;
        countryService = null;
        emptyModel = null;
    }

    
    
}
