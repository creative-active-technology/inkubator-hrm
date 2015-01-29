/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.reference;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.Country;
import com.inkubator.hrm.entity.Currency;
import com.inkubator.hrm.service.CountryService;
import com.inkubator.hrm.service.CurrencyService;
import com.inkubator.hrm.util.MapUtil;
import com.inkubator.hrm.web.model.CurrencyModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Deni
 */
@ManagedBean(name = "currencyFormController")
@ViewScoped
public class CurrencyFormController extends BaseController{
    @ManagedProperty(value = "#{currencyService}")
    private CurrencyService service;
    @ManagedProperty(value = "#{countryService}")
    private CountryService countryService;
    private Currency selected;
    private CurrencyModel model;
    private Boolean isEdit;
    private Map<String, Long> listCountries;
    private List<Country> listCountry = new ArrayList<>();

    @PreDestroy
    private void cleanAndExit() {
        model = null;
        service = null;
        selected = null;
        isEdit = null;
        countryService = null;
        listCountries = null;
        listCountry = null;
    }
    
    @PostConstruct
    @Override
    public void initialization() {
        
        super.initialization();
        String param = FacesUtil.getRequestParameter("param");
        model = new CurrencyModel();
        listCountries = new TreeMap<String, Long>();
        try {
            listCountry = countryService.getAllData();
            if (param != null) {

                isEdit = Boolean.TRUE;
                Currency currency = service.getEntiyByPK(Long.parseLong(param));
                model.setId(currency.getId());
                model.setName(currency.getName());
                model.setCode(currency.getCode());
                model.setCountry(currency.getCountry().getId());
                model.setDescription(currency.getDescription());
            } else {
                isEdit = Boolean.FALSE;
            }
            listParent();
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }
    
    public void listParent(){
        for (Country country : listCountry) {
            String countryCut = "";
            if(country.getCountryName().length() > 25){
                countryCut = country.getCountryName().substring(0,25) + "...";
            }else{
                countryCut = country.getCountryName();
            }      
            listCountries.put(countryCut, country.getId());
        }
        MapUtil.sortByValue(listCountries);
    }
    
    public void doSave() {
        
        Currency currency = getEntityFromViewModel(model);
        try {
            if (isEdit) {
                service.update(currency);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                service.save(currency);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch (BussinessException ex) { 
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }
    
    private Currency getEntityFromViewModel(CurrencyModel model) {
        Currency currency = new Currency();
        if (model.getId() != null) {
            currency.setId(model.getId());
        }
        currency.setCode(model.getCode());
        currency.setName(model.getName());
        currency.setDescription(model.getDescription());
        currency.setCountry(new Country(model.getCountry()));
        return currency;
    }

    public CountryService getCountryService() {
        return countryService;
    }

    public void setCountryService(CountryService countryService) {
        this.countryService = countryService;
    }

    public Map<String, Long> getListCountries() {
        return listCountries;
    }

    public void setListCountries(Map<String, Long> listCountries) {
        this.listCountries = listCountries;
    }

    public List<Country> getListCountry() {
        return listCountry;
    }

    public void setListCountry(List<Country> listCountry) {
        this.listCountry = listCountry;
    }
    
    public CurrencyService getService() {
        return service;
    }

    public void setService(CurrencyService service) {
        this.service = service;
    }

    public Currency getSelected() {
        return selected;
    }

    public void setSelected(Currency selected) {
        this.selected = selected;
    }

    public CurrencyModel getModel() {
        return model;
    }

    public void setModel(CurrencyModel model) {
        this.model = model;
    }

    public Boolean getIsEdit() {
        return isEdit;
    }

    public void setIsEdit(Boolean isEdit) {
        this.isEdit = isEdit;
    }
    
    
}
