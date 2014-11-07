/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.reference;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.City;
import com.inkubator.hrm.entity.Country;
import com.inkubator.hrm.entity.FinancialNonBanking;
import com.inkubator.hrm.entity.Province;
import com.inkubator.hrm.service.CityService;
import com.inkubator.hrm.service.CountryService;
import com.inkubator.hrm.service.FinancialNonBankingService;
import com.inkubator.hrm.service.ProvinceService;
import com.inkubator.hrm.web.model.FinancialNonBankingModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.util.ArrayList;
import java.util.List;
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
 * @author Deni
 */
@ManagedBean(name = "financialNonBankingFormController")
@ViewScoped
public class FinancialNonBankingFormController extends BaseController{
    @ManagedProperty(value = "#{financialNonBankingService}")
    private FinancialNonBankingService financialNonBankingService;
    @ManagedProperty(value = "#{countryService}")
    private CountryService countryService;
    @ManagedProperty(value = "#{provinceService}")
    private ProvinceService provinceService;
    @ManagedProperty(value = "#{cityService}")
    private CityService cityService;
    private FinancialNonBanking financialNonBanking;
    private FinancialNonBankingModel model;
    private Boolean isUpdate;
    private List<Country> countries;
    private List<Province> provinces;
    private List<City> cities;
    
    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try{
            String financialNonBankingId = FacesUtil.getRequestParameter("financialNonBankingId");
            model = new FinancialNonBankingModel();
            isUpdate = Boolean.FALSE;
            countries = countryService.getAllData();
            provinces = new ArrayList<Province>();
            cities = new ArrayList<City>();
            if (StringUtils.isNotEmpty(financialNonBankingId)) {
                FinancialNonBanking financialNonBanking = financialNonBankingService.getEntityByPkWithDetail(Long.parseLong(financialNonBankingId));
                if(financialNonBankingId != null){
                    model = getModelFromEntity(financialNonBanking);
                    provinces = provinceService.getByCountryId(model.getCountryId());
                    cities = cityService.getByProvinceId(model.getProvinceId());
                    isUpdate = Boolean.TRUE;
                }
            }
            
        }catch (Exception e){
            LOGGER.error("Error", e);
        }
    }
    
    @PreDestroy
    public void cleanAndExit() {
        countries = null;
        provinces = null;
        cities = null;
        isUpdate = null;
        model = null;
        financialNonBankingService = null;
        financialNonBanking = null;
        countryService = null;
        provinceService = null; 
        cityService = null;
    }
    
    private FinancialNonBankingModel getModelFromEntity(FinancialNonBanking entity) {
        FinancialNonBankingModel model = new FinancialNonBankingModel();
        model.setId(entity.getId());
        model.setCode(entity.getCode());
        model.setName(entity.getName());
        model.setAddress(entity.getAddress());
        model.setFinancialService(entity.getFinancialService());
        model.setCountryId(entity.getCity().getProvince().getCountry().getId());
        model.setProvinceId(entity.getCity().getProvince().getId());
        model.setCityId(entity.getCity().getId());
        return model;
    }
    
    private FinancialNonBanking getEntityFromViewModel(FinancialNonBankingModel model) {
        FinancialNonBanking financialNonBanking = new FinancialNonBanking();
        if (model.getId() != null) {
            financialNonBanking.setId(model.getId());
        }
        financialNonBanking.setCode(model.getCode());
        financialNonBanking.setName(model.getName());
        financialNonBanking.setAddress(model.getAddress());
        financialNonBanking.setFinancialService(model.getFinancialService());
        financialNonBanking.setCity(new City(model.getCityId()));
        return financialNonBanking;
    }
    
    public void doSave() {
        System.out.println("masuk dosave");
        FinancialNonBanking financialNonBanking = getEntityFromViewModel(model);
        try {
            if (isUpdate) {
                financialNonBankingService.update(financialNonBanking);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                financialNonBankingService.save(financialNonBanking);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch (BussinessException ex) { //data already exist(duplicate)
            LOGGER.error("Error", ex);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public void onChangeCountries(){
        try {
                provinces.clear();
                cities.clear();
                provinces = provinceService.getByCountryId(model.getCountryId());
        } catch (Exception e) {
                LOGGER.error("Error", e);
        }
    }

    public void onChangeProvinces(){
            try {
                    cities.clear();
                    cities = cityService.getByProvinceId(model.getProvinceId());
            } catch (Exception e) {
                    LOGGER.error("Error", e);
            }
    }
        
    public FinancialNonBankingService getFinancialNonBankingService() {
        return financialNonBankingService;
    }

    public void setFinancialNonBankingService(FinancialNonBankingService financialNonBankingService) {
        this.financialNonBankingService = financialNonBankingService;
    }

    public FinancialNonBanking getFinancialNonBanking() {
        return financialNonBanking;
    }

    public void setFinancialNonBanking(FinancialNonBanking financialNonBanking) {
        this.financialNonBanking = financialNonBanking;
    }

    public FinancialNonBankingModel getModel() {
        return model;
    }

    public void setModel(FinancialNonBankingModel model) {
        this.model = model;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public CountryService getCountryService() {
        return countryService;
    }

    public void setCountryService(CountryService countryService) {
        this.countryService = countryService;
    }

    public ProvinceService getProvinceService() {
        return provinceService;
    }

    public void setProvinceService(ProvinceService provinceService) {
        this.provinceService = provinceService;
    }

    public CityService getCityService() {
        return cityService;
    }

    public void setCityService(CityService cityService) {
        this.cityService = cityService;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

    public List<Province> getProvinces() {
        return provinces;
    }

    public void setProvinces(List<Province> provinces) {
        this.provinces = provinces;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }
    
    
}
