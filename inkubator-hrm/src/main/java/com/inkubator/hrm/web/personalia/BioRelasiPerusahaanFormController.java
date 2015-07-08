/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.personalia;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.BioData;
import com.inkubator.hrm.entity.BioRelasiPerusahaan;
import com.inkubator.hrm.entity.City;
import com.inkubator.hrm.entity.Country;
import com.inkubator.hrm.entity.Province;
import com.inkubator.hrm.service.BioRelasiPerusahaanService;
import com.inkubator.hrm.service.CityService;
import com.inkubator.hrm.service.CountryService;
import com.inkubator.hrm.service.ProvinceService;
import com.inkubator.hrm.web.model.BioRelasiPerusahaanModel;
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
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Deni
 */
@ManagedBean(name = "bioRelasiPerusahaanFormController")
@ViewScoped
public class BioRelasiPerusahaanFormController extends BaseController {

    private List<Country> countries;
    private List<Province> provinces;
    private List<City> cities;
    private BioRelasiPerusahaanModel model;
    private Boolean isUpdate;
    private Boolean isCountrySelected;
    private Boolean isProvinceSelected;
    @ManagedProperty(value = "#{countryService}")
    private CountryService countryService;
    @ManagedProperty(value = "#{provinceService}")
    private ProvinceService provinceService;
    @ManagedProperty(value = "#{cityService}")
    private CityService cityService;
    @ManagedProperty(value = "#{bioRelasiPerusahaanService}")
    private BioRelasiPerusahaanService bioRelasiPerusahaanService;
    private UploadedFile fileUploadFile;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try {
            isUpdate = Boolean.FALSE;
            isCountrySelected = Boolean.FALSE;
            isProvinceSelected = Boolean.FALSE;
            model = new BioRelasiPerusahaanModel();
            countries = countryService.getAllData();
            provinces = new ArrayList<Province>();
            cities = new ArrayList<City>();

            String bioDataId = FacesUtil.getRequestParameter("bioDataId");
            model.setBioData(Long.parseLong(bioDataId));

            String bioRelasiPerusahaanId = FacesUtil.getRequestParameter("bioRelasiPerusahaanId");
            if (StringUtils.isNotEmpty(bioRelasiPerusahaanId)) {
                BioRelasiPerusahaan bioRelasiPerusahaan = bioRelasiPerusahaanService.getEntityByPkWithDetail(Long.parseLong(bioRelasiPerusahaanId));
                if (bioRelasiPerusahaan != null) {
                    model = getModelFromEntity(bioRelasiPerusahaan);
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
        countries = null;
        provinces = null;
        cities = null;
        model = null;
        isUpdate = null;
        bioRelasiPerusahaanService = null;
        countryService = null;
        provinceService = null;
        cityService = null;
        fileUploadFile = null;
    }

    public void doSave(){
        BioRelasiPerusahaan bioRelasiPerusahaan = getEntityFromViewModel(model);
        try {
            if (isUpdate) {
                bioRelasiPerusahaanService.update(bioRelasiPerusahaan, fileUploadFile);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                bioRelasiPerusahaanService.save(bioRelasiPerusahaan, fileUploadFile);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch (BussinessException ex) { 
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }
    
    private BioRelasiPerusahaanModel getModelFromEntity(BioRelasiPerusahaan entity) {
        BioRelasiPerusahaanModel model = new BioRelasiPerusahaanModel();
        model.setId(entity.getId());
        model.setBioData(entity.getBioData().getId());
        model.setCity(entity.getCity().getId());
        model.setRelasiAddress(entity.getRelasiAddress());
        model.setCountryId(entity.getCity().getProvince().getCountry().getId());
        model.setProvinceId(entity.getCity().getProvince().getId());
        model.setCityId(entity.getCity().getId());
        model.setRelasiCompany(entity.getRelasiCompany());
        model.setRelasiEmail(entity.getRelasiEmail());
        model.setPostCode(entity.getPostalCode());
        model.setRelasiJabatan(entity.getRelasiJabatan());
        model.setRelasiMobilePhone(entity.getRelasiMobilePhone());
        model.setRelasiName(entity.getRelasiName());
        model.setRelasiPhoneNumber(entity.getRelasiPhoneNumber());
        model.setRelasiAttachmentName(entity.getRelasiAttachmentName());
        return model;
    }
    
    private BioRelasiPerusahaan getEntityFromViewModel(BioRelasiPerusahaanModel model) {
        BioRelasiPerusahaan bioRelasiPerusahaan = new BioRelasiPerusahaan();
        if (model.getId() != null) {
            bioRelasiPerusahaan.setId(model.getId());
        }
        bioRelasiPerusahaan.setBioData(new BioData(model.getBioData()));
        bioRelasiPerusahaan.setCity(new City(model.getCityId()));
        bioRelasiPerusahaan.setRelasiAddress(model.getRelasiAddress());
        bioRelasiPerusahaan.setRelasiCompany(model.getRelasiCompany());
        bioRelasiPerusahaan.setRelasiEmail(model.getRelasiEmail());
        bioRelasiPerusahaan.setRelasiJabatan(model.getRelasiJabatan());
        bioRelasiPerusahaan.setRelasiMobilePhone(model.getRelasiMobilePhone());
        bioRelasiPerusahaan.setRelasiName(model.getRelasiName());
        bioRelasiPerusahaan.setPostalCode(model.getPostCode());
        bioRelasiPerusahaan.setRelasiPhoneNumber(model.getRelasiPhoneNumber());
        bioRelasiPerusahaan.setRelasiAttachmentName(model.getRelasiAttachmentName());
        return bioRelasiPerusahaan;
    }
    
    public void onChangeCountries() {
        try {
            provinces.clear();
            cities.clear();
            provinces = provinceService.getByCountryId(model.getCountryId());
            isCountrySelected = Boolean.TRUE;
            isProvinceSelected = Boolean.FALSE;
            if(provinces.isEmpty()){
            	MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_WARN, "global.error","companyRelation.warning_province_of_selected_country_still_empty", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            }
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    public void onChangeProvinces() {
        try {
            cities.clear();
            cities = cityService.getByProvinceId(model.getProvinceId());
            isProvinceSelected = Boolean.TRUE;
            if(cities.isEmpty()){
            	MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_WARN, "global.error","companyRelation.warning_city_of_selected_province_still_empty", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            }
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    public void handingFileUpload(FileUploadEvent fileUploadEvent) {
        fileUploadFile = fileUploadEvent.getFile();
        model.setRelasiAttachmentName(fileUploadFile.getFileName());
    }

    public UploadedFile getFileUploadFile() {
        return fileUploadFile;
    }

    public void setFileUploadFile(UploadedFile fileUploadFile) {
        this.fileUploadFile = fileUploadFile;
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

    public BioRelasiPerusahaanModel getModel() {
        return model;
    }

    public void setModel(BioRelasiPerusahaanModel model) {
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

    public BioRelasiPerusahaanService getBioRelasiPerusahaanService() {
        return bioRelasiPerusahaanService;
    }

    public void setBioRelasiPerusahaanService(BioRelasiPerusahaanService bioRelasiPerusahaanService) {
        this.bioRelasiPerusahaanService = bioRelasiPerusahaanService;
    }

	public Boolean getIsCountrySelected() {
		return isCountrySelected;
	}

	public void setIsCountrySelected(Boolean isCountrySelected) {
		this.isCountrySelected = isCountrySelected;
	}

	public Boolean getIsProvinceSelected() {
		return isProvinceSelected;
	}

	public void setIsProvinceSelected(Boolean isProvinceSelected) {
		this.isProvinceSelected = isProvinceSelected;
	}
    
    

}
