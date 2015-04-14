/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.reference;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.City;
import com.inkubator.hrm.entity.Country;
import com.inkubator.hrm.entity.Province;
import com.inkubator.hrm.entity.UnitKerja;
import com.inkubator.hrm.service.CityService;
import com.inkubator.hrm.service.CountryService;
import com.inkubator.hrm.service.ProvinceService;
import com.inkubator.hrm.service.UnitKerjaService;
import com.inkubator.hrm.util.MapUtil;
import com.inkubator.hrm.web.model.UnitKerjaModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author deniarianto
 */
@ManagedBean(name = "unitKerjaFormController")
@ViewScoped
public class UnitKerjaFormController extends BaseController{
    @ManagedProperty(value = "#{unitKerjaService}")
    private UnitKerjaService unitKerjaService;
    @ManagedProperty(value = "#{cityService}")
    private CityService cityService;
    @ManagedProperty(value = "#{provinceService}")
    private ProvinceService provinceService;
    @ManagedProperty(value = "#{countryService}")
    private CountryService countryService;
    private UnitKerjaModel unitKerjaModel;
    private UnitKerja selectedUnitKerja;
    private Boolean isEdit;
    private Map<String, Long> listDropDownCity = new TreeMap<>();
    private Map<String, Long> listDropDownProvince = new TreeMap<>();
    private Map<String, Long> listDropDownCountry = new TreeMap<>();
    private List<City> cityList = new ArrayList<>();
    private List<Province> provinceList = new ArrayList<>();
    private List<Country> countryList = new ArrayList<>();
    private City city;
    private Boolean disabled;
    private ResourceBundle messages;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        String param = FacesUtil.getRequestParameter("param");
        unitKerjaModel = new UnitKerjaModel();
        
        listDropDownCity = new TreeMap<>();
        try {
            cityList = cityService.getAllData();
        } catch (Exception ex) {
            Logger.getLogger(UnitKerjaFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (City citiesList : cityList) {
            listDropDownCity.put(citiesList.getCityName(), citiesList.getId());                
        }
        MapUtil.sortByValue(listDropDownCity);
        
        try {
            if (param != null) {
                
                UnitKerja unitKerja = unitKerjaService.getEntiyByPK(Long.parseLong(param));
                isEdit = Boolean.TRUE;
                unitKerjaModel.setId(unitKerja.getId());
                unitKerjaModel.setCode(unitKerja.getCode());
                unitKerjaModel.setName(unitKerja.getName());
                unitKerjaModel.setLocation(unitKerja.getLocation());
                unitKerjaModel.setCityId(unitKerja.getCity().getId());
                unitKerjaModel.setPhoneNumber(unitKerja.getPhoneNumber());
                unitKerjaModel.setEmailAddress(unitKerja.getEmailAddress());
                unitKerjaModel.setFaxNumber(unitKerja.getFaxNumber());
            } else {
                isEdit = Boolean.FALSE;
            }
            doSelectOneMenuCountry();
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }
    
    public void doSave() throws Exception {
        UnitKerja unitKerja = getEntityFromViewModel(unitKerjaModel);
        try {
            if (isEdit) {
                unitKerjaService.update(unitKerja);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                unitKerjaService.save(unitKerja);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch (BussinessException ex) { 
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }
    
    private UnitKerja getEntityFromViewModel(UnitKerjaModel unitKerjaModel) throws Exception {
        UnitKerja unitKerja = new UnitKerja();
        if (unitKerjaModel.getId() != null) {
            unitKerja.setId(unitKerjaModel.getId());
        }
        unitKerja.setCode(unitKerjaModel.getCode());
        unitKerja.setName(unitKerjaModel.getName());
        unitKerja.setLocation(unitKerjaModel.getLocation());
        unitKerja.setCity(cityService.getEntiyByPK(unitKerjaModel.getCityId()));
        unitKerja.setPhoneNumber(unitKerjaModel.getPhoneNumber());
        unitKerja.setEmailAddress((unitKerjaModel.getEmailAddress()));
        unitKerja.setFaxNumber(unitKerjaModel.getFaxNumber());
        return unitKerja;
    }
    
    @PreDestroy
    private void cleanAndExit() {
        unitKerjaModel = null;
        unitKerjaService = null;
        selectedUnitKerja = null;
        isEdit = null;
        listDropDownCity = null;
        listDropDownCountry = null;
        listDropDownProvince = null;
        cityList = null;
        provinceList = null;
        countryList = null;
    }
    
    public void doSelectOneMenuCountry() throws Exception{
        countryList = countryService.getAllData();
        listDropDownCountry = new TreeMap<>();
        for(Country countries : countryList){
            listDropDownCountry.put(countries.getCountryName(), countries.getId());
        }
    }
    
    public void countryChanged() {
        try {

            Country country = countryService.getEntiyByPK(Long.parseLong(String.valueOf(unitKerjaModel.getCountryId())));
            List<Province> listProvinces = provinceService.getByCountryIdWithDetail(Long.parseLong(String.valueOf(unitKerjaModel.getCountryId())));

            if (listProvinces.isEmpty() || listProvinces == null) {
                disabled = Boolean.TRUE;
                unitKerjaModel.setProvinceId(null);
                FacesContext.getCurrentInstance().addMessage("formCityFormId:provinceId", new FacesMessage(FacesMessage.SEVERITY_ERROR, messages.getString("global.error"), messages.getString("city.province_is_empty")));

            } else {
                disabled = Boolean.FALSE;
                listDropDownProvince.clear();
                for (Province province : listProvinces) {
                    listDropDownProvince.put(province.getProvinceName(), province.getId());
                }

                MapUtil.sortByValue(listDropDownProvince);
            }

        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }
    
    public void provinceChanged() {
        try {

            Province province = provinceService.getEntiyByPK(Long.parseLong(String.valueOf(unitKerjaModel.getProvinceId())));
            List<City> listCities = cityService.getByProvinceIdWithDetail(Long.parseLong(String.valueOf(unitKerjaModel.getProvinceId())));

            if (listCities.isEmpty() || listCities == null) {
                disabled = Boolean.TRUE;
                unitKerjaModel.setCityId(null);
                FacesContext.getCurrentInstance().addMessage("formCityFormId:provinceId", new FacesMessage(FacesMessage.SEVERITY_ERROR, messages.getString("global.error"), messages.getString("city.province_is_empty")));

            } else {
                disabled = Boolean.FALSE;
                listDropDownCity.clear();
                for (City cities : listCities) {
                    listDropDownCity.put(cities.getCityName(), cities.getId());
                }

                MapUtil.sortByValue(listDropDownCity);
            }

        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }
    
    public UnitKerjaService getUnitKerjaService() {
        return unitKerjaService;
    }

    public void setUnitKerjaService(UnitKerjaService unitKerjaService) {
        this.unitKerjaService = unitKerjaService;
    }

    public UnitKerjaModel getUnitKerjaModel() {
        return unitKerjaModel;
    }

    public void setUnitKerjaModel(UnitKerjaModel unitKerjaModel) {
        this.unitKerjaModel = unitKerjaModel;
    }
    

    public UnitKerja getSelectedUnitKerja() {
        return selectedUnitKerja;
    }

    public void setSelectedUnitKerja(UnitKerja selectedUnitKerja) {
        this.selectedUnitKerja = selectedUnitKerja;
    }

    public Boolean getIsEdit() {
        return isEdit;
    }

    public void setIsEdit(Boolean isEdit) {
        this.isEdit = isEdit;
    }

    public CityService getCityService() {
        return cityService;
    }

    public void setCityService(CityService cityService) {
        this.cityService = cityService;
    }

    public Map<String, Long> getListDropDownCity() {
        return listDropDownCity;
    }

    public void setListDropDownCity(Map<String, Long> listDropDownCity) {
        this.listDropDownCity = listDropDownCity;
    }
    
    public List<City> getCityList() {
        return cityList;
    }

    public void setCityList(List<City> cityList) {
        this.cityList = cityList;
    }

    public ProvinceService getProvinceService() {
        return provinceService;
    }

    public void setProvinceService(ProvinceService provinceService) {
        this.provinceService = provinceService;
    }

    public CountryService getCountryService() {
        return countryService;
    }

    public void setCountryService(CountryService countryService) {
        this.countryService = countryService;
    }

    public Map<String, Long> getListDropDownProvince() {
        return listDropDownProvince;
    }

    public void setListDropDownProvince(Map<String, Long> listDropDownProvince) {
        this.listDropDownProvince = listDropDownProvince;
    }

    public Map<String, Long> getListDropDownCountry() {
        return listDropDownCountry;
    }

    public void setListDropDownCountry(Map<String, Long> listDropDownCountry) {
        this.listDropDownCountry = listDropDownCountry;
    }

    public List<Province> getProvinceList() {
        return provinceList;
    }

    public void setProvinceList(List<Province> provinceList) {
        this.provinceList = provinceList;
    }

    public List<Country> getCountryList() {
        return countryList;
    }

    public void setCountryList(List<Country> countryList) {
        this.countryList = countryList;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
    
}
