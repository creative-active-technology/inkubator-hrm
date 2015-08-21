/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.reference;

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

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.Bank;
import com.inkubator.hrm.entity.BioAddress;
import com.inkubator.hrm.entity.BioData;
import com.inkubator.hrm.entity.City;
import com.inkubator.hrm.entity.Country;
import com.inkubator.hrm.entity.Insurance;
import com.inkubator.hrm.entity.Province;
import com.inkubator.hrm.service.CityService;
import com.inkubator.hrm.service.CountryService;
import com.inkubator.hrm.service.InsuranceService;
import com.inkubator.hrm.service.ProvinceService;
import com.inkubator.hrm.web.model.BioAddressModel;
import com.inkubator.hrm.web.model.InsuranceModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
 *
 * @author Deni
 */
@ManagedBean(name = "insuranceFormController")
@ViewScoped
public class InsuranceFormController extends BaseController {
	
	private InsuranceModel insuranceModel;
    private Boolean isUpdate;
    @ManagedProperty(value = "#{insuranceService}")
    private InsuranceService insuranceService;
	@ManagedProperty(value = "#{countryService}")
	private CountryService countryService;
	@ManagedProperty(value = "#{provinceService}")
	private ProvinceService provinceService;
	@ManagedProperty(value = "#{cityService}")
	private CityService cityService;
	private List<Country> countries;
	private List<Province> provinces;
	private List<City> cities;
	
	@PostConstruct
    @Override
    public void initialization() {
        super.initialization();

        try {
			countries = countryService.getAllData();
			provinces = new ArrayList<Province>();
	        cities = new ArrayList<City>();
	        String param = FacesUtil.getRequestParameter("execution");
	        insuranceModel = new InsuranceModel();
	        isUpdate = Boolean.FALSE;
	        
	        if (StringUtils.isNotEmpty(param)) {
            	Insurance insurance = insuranceService.getEntityByPkWithDetail(Long.parseLong(param.substring(1)));
                if (insurance != null) {
                    //do edit insurance
                	insuranceModel = getModelFromEntity(insurance);
                	isUpdate = Boolean.TRUE;
                	provinces = provinceService.getByCountryId(insuranceModel.getCountryId());
            		cities = cityService.getByProvinceId(insuranceModel.getProvinceId());
                }
            }
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
    }
	
	@PreDestroy
    public void cleanAndExit() {
		insuranceModel = null;
		insuranceService = null;
        isUpdate = null;
        countryService = null;
        provinceService = null;
        cityService = null;
        countries = null;
        provinces = null;
        cities = null;
    }
	
	public void onChangeCountries(){
		try {
			provinces.clear();
			cities.clear();
			provinces = provinceService.getByCountryId(insuranceModel.getCountryId());
		} catch (Exception e) {
			LOGGER.error("Error", e);
		}
	}
	
	public void onChangeProvinces(){
		try {
			cities.clear();
			cities = cityService.getByProvinceId(insuranceModel.getProvinceId());
		} catch (Exception e) {
			LOGGER.error("Error", e);
		}
	}
	
	public void doReset() {
        if (isUpdate) {
            try {
                Insurance insurance = insuranceService.getEntiyByPK(insuranceModel.getId());
                if (insurance != null) {
                    insuranceModel = getModelFromEntity(insurance);
                    provinces = provinceService.getByCountryId(insuranceModel.getCountryId());
            		cities = cityService.getByProvinceId(insuranceModel.getProvinceId());
                }
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        } else {
            insuranceModel = new InsuranceModel();
            provinces.clear();
            cities.clear();            
        }
    }
	
	public String doSave() {
        Insurance insurance = getEntityFromViewModel(insuranceModel);
        try {
            if (isUpdate) {
                insuranceService.update(insurance);
            } else {
            	insuranceService.save(insurance);
            }
            cleanAndExit();

            MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            return "/protected/reference/insurance_view.htm?faces-redirect=true";
        } catch (BussinessException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }

        return null;
    }

	private InsuranceModel getModelFromEntity(Insurance entity) {
		InsuranceModel insuranceModel = new InsuranceModel();
		if(entity.getId() != null){
			insuranceModel.setId(entity.getId());
		}
		insuranceModel.setAddress(entity.getAddress());
		insuranceModel.setCityId(entity.getCity().getId());
		insuranceModel.setProvinceId(entity.getCity().getProvince().getId());
		insuranceModel.setCountryId(entity.getCity().getProvince().getCountry().getId());
		insuranceModel.setCode(entity.getCode());
		insuranceModel.setDescription(entity.getDescription());
		insuranceModel.setInsuranceProduct(entity.getInsuranceProduct());
		insuranceModel.setName(entity.getName());
		insuranceModel.setPolisNo(entity.getPolisNo());
		insuranceModel.setPostalCode(entity.getPostalCode());
		return insuranceModel;
		
	}
	
	private Insurance getEntityFromViewModel(InsuranceModel model) {
		Insurance insurance = new Insurance();
		if(model.getId() != null){
			insurance.setId(model.getId());
		}
		insurance.setAddress(model.getAddress());
		insurance.setCity(new City(model.getCityId()));
		insurance.setCode(model.getCode());
		insurance.setDescription(model.getDescription());
		insurance.setInsuranceProduct(model.getInsuranceProduct());
		insurance.setName(model.getName());
		insurance.setPolisNo(model.getPolisNo());
		insurance.setPostalCode(model.getPostalCode());
	    return insurance;
	}
	
	public InsuranceModel getInsuranceModel() {
		return insuranceModel;
	}

	public void setInsuranceModel(InsuranceModel insuranceModel) {
		this.insuranceModel = insuranceModel;
	}

	public Boolean getIsUpdate() {
		return isUpdate;
	}

	public void setIsUpdate(Boolean isUpdate) {
		this.isUpdate = isUpdate;
	}

	public InsuranceService getInsuranceService() {
		return insuranceService;
	}

	public void setInsuranceService(InsuranceService insuranceService) {
		this.insuranceService = insuranceService;
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
	
	public String doBack(){
		return "/protected/reference/insurance_view.htm?faces-redirect=true";
	}
	
}
