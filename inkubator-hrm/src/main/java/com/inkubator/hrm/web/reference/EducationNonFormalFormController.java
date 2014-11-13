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
import com.inkubator.hrm.entity.City;
import com.inkubator.hrm.entity.Country;
import com.inkubator.hrm.entity.EducationNonFormal;
import com.inkubator.hrm.entity.Province;
import com.inkubator.hrm.service.CityService;
import com.inkubator.hrm.service.CountryService;
import com.inkubator.hrm.service.EducationNonFormalService;
import com.inkubator.hrm.service.ProvinceService;
import com.inkubator.hrm.web.model.EducationNonFormalModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "educationNonFormalFormController")
@ViewScoped
public class EducationNonFormalFormController extends BaseController {

    private EducationNonFormalModel model;
    private Boolean isUpdate;
    private List<Country> countries;
	private List<Province> provinces;
	private List<City> cities;
    @ManagedProperty(value = "#{educationNonFormalService}")
    private EducationNonFormalService educationNonFormalService;
    @ManagedProperty(value = "#{countryService}")
	private CountryService countryService;
	@ManagedProperty(value = "#{provinceService}")
	private ProvinceService provinceService;
	@ManagedProperty(value = "#{cityService}")
	private CityService cityService;	

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try {
	        model = new EducationNonFormalModel();
	        isUpdate = Boolean.FALSE;
	        countries = countryService.getAllData();
	        provinces = new ArrayList<Province>();
	        cities = new ArrayList<City>();
        
	        String param = FacesUtil.getRequestParameter("param");
	        if (StringUtils.isNumeric(param)) {            
                EducationNonFormal educationNonFormal = educationNonFormalService.getEntityByPkWithDetail(Long.parseLong(param));
                if (educationNonFormal != null) {
                	getModelFromEntity(educationNonFormal);
                    provinces = provinceService.getByCountryId(model.getCountryId());
            		cities = cityService.getByProvinceId(model.getProvinceId());
                    isUpdate = Boolean.TRUE;
                }            
	        }
	        
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }    

    @PreDestroy
    public void cleanAndExit() {
        educationNonFormalService = null;
        model = null;
        isUpdate = null;
        countries = null;
        provinces = null;
        cities = null;
        countryService = null;
        provinceService = null;
        cityService = null;
    }	

    public EducationNonFormalModel getModel() {
		return model;
	}

	public void setModel(EducationNonFormalModel model) {
		this.model = model;
	}

	public Boolean getIsUpdate() {
		return isUpdate;
	}

	public void setIsUpdate(Boolean isUpdate) {
		this.isUpdate = isUpdate;
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

	public EducationNonFormalService getEducationNonFormalService() {
		return educationNonFormalService;
	}

	public void setEducationNonFormalService(
			EducationNonFormalService educationNonFormalService) {
		this.educationNonFormalService = educationNonFormalService;
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

	public void doSave() {
        EducationNonFormal educationNonFormal = getEntityFromViewModel(model);
        try {
            if (isUpdate) {
                educationNonFormalService.update(educationNonFormal);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                educationNonFormalService.save(educationNonFormal);
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

	private void getModelFromEntity(EducationNonFormal educationNonFormal) {
		model.setId(educationNonFormal.getId());
		model.setCode(educationNonFormal.getCode());
		model.setName(educationNonFormal.getName());
		model.setDescription(educationNonFormal.getDescription());
		model.setAddress(educationNonFormal.getAddress());
		model.setCountryId(educationNonFormal.getCity().getProvince().getCountry().getId());
		model.setProvinceId(educationNonFormal.getCity().getProvince().getId());
		model.setCityId(educationNonFormal.getCity().getId());
		model.setPostalCode(educationNonFormal.getPostalCode());
		model.setOfficialPhoneNo(educationNonFormal.getOfficialPhoneNo());
		model.setOfficialEmail(educationNonFormal.getOfficialEmail());		
	}
	
    private EducationNonFormal getEntityFromViewModel(EducationNonFormalModel model) {
    	EducationNonFormal educationNonFormal = new EducationNonFormal();
        if (model.getId() != null) {
            educationNonFormal.setId(model.getId());
        }
        educationNonFormal.setCode(model.getCode());
        educationNonFormal.setName(model.getName());
        educationNonFormal.setDescription(model.getDescription());
        educationNonFormal.setAddress(model.getAddress());
        educationNonFormal.setCity(new City(model.getCityId()));
        educationNonFormal.setPostalCode(model.getPostalCode());
        educationNonFormal.setOfficialPhoneNo(model.getOfficialPhoneNo());
        educationNonFormal.setOfficialEmail(model.getOfficialEmail());
        
        return educationNonFormal;
    }
}
