package com.inkubator.hrm.web.reference;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.City;
import com.inkubator.hrm.entity.Country;
import com.inkubator.hrm.entity.EducationLevel;
import com.inkubator.hrm.entity.InstitutionEducation;
import com.inkubator.hrm.entity.Province;
import com.inkubator.hrm.service.CityService;
import com.inkubator.hrm.service.CountryService;
import com.inkubator.hrm.service.EducationLevelService;
import com.inkubator.hrm.service.InstitutionEducationService;
import com.inkubator.hrm.service.ProvinceService;
import com.inkubator.hrm.util.MapUtil;
import com.inkubator.hrm.web.model.InstitutionEducationModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

import java.util.List;
import java.util.Locale;
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
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Taufik Hidayat
 */
@ManagedBean(name = "institutionEducationFormController")
@ViewScoped
public class InstitutionEducationFormController extends BaseController {

    private InstitutionEducationModel institutionEducationModel;
    private Boolean isUpdate;
    @ManagedProperty(value = "#{institutionEducationService}")
    private InstitutionEducationService institutionEducationService;
    @ManagedProperty(value = "#{cityService}")
    private CityService cityService;
    @ManagedProperty(value = "#{provinceService}")
    private ProvinceService provinceService;
    @ManagedProperty(value = "#{countryService}")
    private CountryService countryService;
    @ManagedProperty(value = "#{educationLevelService}")
    private EducationLevelService educationLevelService;
    private Map<String, Long> countrys = new TreeMap<>();
    private Map<String, Long> provinces = new TreeMap<>();
    private Map<String, Long> citys = new TreeMap<>();
    private Map<String, Long> dropDownEducationLevel = new TreeMap<>();
    private Boolean disabledProvince;
    private Boolean disabledCity;
    private String locale;
    private ResourceBundle messages;

    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            String param = FacesUtil.getRequestParameter("execution");
            institutionEducationModel = new InstitutionEducationModel();
            isUpdate = Boolean.FALSE;
            disabledCity = Boolean.TRUE;
            disabledProvince = Boolean.TRUE;
            locale = FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString();
            messages = ResourceBundle.getBundle("Messages", new Locale(locale));
            List<Country> listCountrys = countryService.getAllData();

            for (Country country : listCountrys) {
                countrys.put(country.getCountryName(), country.getId());
            }

            List<EducationLevel> listEducationLevel = educationLevelService.getAllData();

            for (EducationLevel educationLevel : listEducationLevel) {
                dropDownEducationLevel.put(educationLevel.getName(), educationLevel.getId());
            }
            MapUtil.sortByValue(dropDownEducationLevel);
            MapUtil.sortByValue(countrys);

            if (StringUtils.isNotEmpty(param)) {
                try {
                    InstitutionEducation institutionEducation = institutionEducationService.getInstitutionEducationByIdWithDetail(Long.parseLong(param.substring(1)));
                    if (institutionEducation != null) {
                        institutionEducationModel.setId(institutionEducation.getId());
                        institutionEducationModel.setInstitutionEducationCode(institutionEducation.getInstitutionEducationCode());
                        institutionEducationModel.setInstitutionEducationName(institutionEducation.getInstitutionEducationName());
                        institutionEducationModel.setCountryId(institutionEducation.getCity().getProvince().getCountry().getId());
                        institutionEducationModel.setProvinceId(institutionEducation.getCity().getProvince().getId());
                        institutionEducationModel.setCityId(institutionEducation.getCity().getId());
                        institutionEducationModel.setAddress(institutionEducation.getAddress());
                        if (institutionEducation.getEducationLevel() != null) {
                            institutionEducationModel.setEducationLevelId(institutionEducation.getEducationLevel().getId());
                        }
                        institutionEducationModel.setPostalCode(institutionEducation.getPostalCode());
                        institutionEducationModel.setDescription(institutionEducation.getDescription());
                        isUpdate = Boolean.TRUE;
                        disabledCity = Boolean.FALSE;
                        disabledProvince = Boolean.FALSE;
                        List<Province> listProvinces = provinceService.getByCountryIdWithDetail(institutionEducationModel.getCountryId());
                        for (Province province : listProvinces) {
                            provinces.put(province.getProvinceName(), province.getId());
                        }

                        MapUtil.sortByValue(provinces);

                        List<City> listCities = cityService.getByProvinceIdWithDetail(institutionEducationModel.getProvinceId());

                        for (City city : listCities) {
                            citys.put(city.getCityName(), city.getId());
                        }

                        MapUtil.sortByValue(citys);
                    }

                } catch (Exception e) {
                    LOGGER.error("Error", e);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(InstitutionEducationFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @PreDestroy
    public void cleanAndExit() {
        institutionEducationService = null;
//        institutionEducationModel = null;
        isUpdate = null;
        cityService = null;
        provinceService = null;
        countryService = null;
        countrys = null;
        provinces = null;
        citys = null;
        locale = null;
        messages = null;
        educationLevelService = null;
        dropDownEducationLevel = null;
    }

    public InstitutionEducationModel getInstitutionEducationModel() {
        return institutionEducationModel;
    }

    public void setInstitutionEducationModel(InstitutionEducationModel institutionEducationModel) {
        this.institutionEducationModel = institutionEducationModel;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public void setInstitutionEducationService(InstitutionEducationService institutionEducationService) {
        this.institutionEducationService = institutionEducationService;
    }

    public void setProvinceService(ProvinceService provinceService) {
        this.provinceService = provinceService;
    }

    public void setCountryService(CountryService countryService) {
        this.countryService = countryService;
    }

    public void setCityService(CityService cityService) {
        this.cityService = cityService;

    }

    public Map<String, Long> getCountrys() {
        return countrys;
    }

    public void setCountrys(Map<String, Long> countrys) {
        this.countrys = countrys;
    }

    public Map<String, Long> getProvinces() {
        return provinces;
    }

    public void setProvinces(Map<String, Long> provinces) {
        this.provinces = provinces;
    }

    public Map<String, Long> getCitys() {
        return citys;
    }

    public void setCitys(Map<String, Long> citys) {
        this.citys = citys;
    }

    public Boolean getDisabledProvince() {
        return disabledProvince;
    }

    public void setDisabledProvince(Boolean disabledProvince) {
        this.disabledProvince = disabledProvince;
    }

    public Boolean getDisabledCity() {
        return disabledCity;
    }

    public void setDisabledCity(Boolean disabledCity) {
        this.disabledCity = disabledCity;
    }

    /*public void doSave() {
        InstitutionEducation institutionEducation = getEntityFromViewModel(institutionEducationModel);
        try {
            if (isUpdate) {
                institutionEducationService.update(institutionEducation);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                institutionEducationService.save(institutionEducation);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch (BussinessException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }
    */
    public String doSave() {
        try {
        	InstitutionEducation institutionEducation = getEntityFromViewModel(institutionEducationModel);
            if (isUpdate) {
            	institutionEducationService.update(institutionEducation);
                MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.update_successfully",
                        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            } else {
            	institutionEducationService.save(institutionEducation);
                MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully",
                        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            }

            return "/protected/reference/inst_edu_view.htm?faces-redirect=true";
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
        return null;
    }

    private InstitutionEducation getEntityFromViewModel(InstitutionEducationModel institutionEducationModel) {
        InstitutionEducation institutionEducation = new InstitutionEducation();
        if (institutionEducationModel.getId() != null) {
            institutionEducation.setId(institutionEducationModel.getId());
        }
        institutionEducation.setInstitutionEducationCode(institutionEducationModel.getInstitutionEducationCode());
        institutionEducation.setInstitutionEducationName(institutionEducationModel.getInstitutionEducationName());
        institutionEducation.setCity(new City(institutionEducationModel.getCityId()));
        institutionEducation.setEducationLevel(new EducationLevel(institutionEducationModel.getEducationLevelId()));
        institutionEducation.setAddress(institutionEducationModel.getAddress());
        institutionEducation.setPostalCode(institutionEducationModel.getPostalCode());
        institutionEducation.setDescription(institutionEducationModel.getDescription());
        return institutionEducation;
    }

    public void countryChanged(ValueChangeEvent event) {
        try {

            Country country = countryService.getEntiyByPK(Long.parseLong(String.valueOf(event.getNewValue())));

            List<Province> listProvinces = provinceService.getByCountryIdWithDetail(Long.parseLong(String.valueOf(event.getNewValue())));

            if (listProvinces.isEmpty() || listProvinces == null) {
                disabledProvince = Boolean.TRUE;

                FacesContext.getCurrentInstance().addMessage("formInstitutionEducationFormId:provinceId", new FacesMessage(FacesMessage.SEVERITY_ERROR, messages.getString("global.error"), messages.getString("city.province_is_empty")));

            } else {
                disabledProvince = Boolean.FALSE;
                provinces.clear();
                for (Province province : listProvinces) {
                    provinces.put(province.getProvinceName(), province.getId());
                }

                MapUtil.sortByValue(provinces);
            }

        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public void provinceChanged(ValueChangeEvent event) {
        try {

            Province province = provinceService.getEntiyByPK(Long.parseLong(String.valueOf(event.getNewValue())));

            List<City> listCities = cityService.getByProvinceIdWithDetail(Long.parseLong(String.valueOf(event.getNewValue())));

            if (listCities.isEmpty() || listCities == null) {
                disabledCity = Boolean.TRUE;

                FacesContext.getCurrentInstance().addMessage("formInstitutionEducationFormId:cityId", new FacesMessage(FacesMessage.SEVERITY_ERROR, messages.getString("global.error"), messages.getString("city.province_is_empty")));

            } else {
                disabledCity = Boolean.FALSE;
                citys.clear();
                for (City city : listCities) {
                    citys.put(city.getCityName(), city.getId());
                }

                MapUtil.sortByValue(citys);
            }

        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public EducationLevelService getEducationLevelService() {
        return educationLevelService;
    }

    public void setEducationLevelService(EducationLevelService educationLevelService) {
        this.educationLevelService = educationLevelService;
    }

    public Map<String, Long> getDropDownEducationLevel() {
        return dropDownEducationLevel;
    }

    public void setDropDownEducationLevel(Map<String, Long> dropDownEducationLevel) {
        this.dropDownEducationLevel = dropDownEducationLevel;
    }

    public void doReset() throws Exception {
        if (isUpdate) {
            InstitutionEducation institutionEducation = institutionEducationService.getInstitutionEducationByIdWithDetail(institutionEducationModel.getId());

            institutionEducationModel.setAddress(institutionEducation.getAddress());
            institutionEducationModel.setCountryId(institutionEducation.getCity().getProvince().getCountry().getId());
            institutionEducationModel.setDescription(institutionEducation.getDescription());
            if (institutionEducation.getEducationLevel() != null) {
                institutionEducationModel.setEducationLevelId(institutionEducation.getEducationLevel().getId());
                institutionEducationModel.setInstitutionEducationCode(institutionEducation.getInstitutionEducationCode());
                institutionEducationModel.setInstitutionEducationName(institutionEducation.getInstitutionEducationName());
            }
            institutionEducationModel.setPostalCode(institutionEducation.getPostalCode());
            List<Province> listProvinces = provinceService.getByCountryIdWithDetail(institutionEducation.getCity().getProvince().getCountry().getId());

            if (listProvinces.isEmpty() || listProvinces == null) {
                disabledProvince = Boolean.TRUE;

                FacesContext.getCurrentInstance().addMessage("formInstitutionEducationFormId:provinceId", new FacesMessage(FacesMessage.SEVERITY_ERROR, messages.getString("global.error"), messages.getString("city.province_is_empty")));

            } else {
                disabledProvince = Boolean.FALSE;
                provinces.clear();
                for (Province province : listProvinces) {
                    provinces.put(province.getProvinceName(), province.getId());
                }

                MapUtil.sortByValue(provinces);
            }
            institutionEducationModel.setProvinceId(institutionEducation.getCity().getProvince().getId());
            List<City> listCities = cityService.getByProvinceIdWithDetail(institutionEducation.getCity().getProvince().getId());

            if (listCities.isEmpty() || listCities == null) {
                disabledCity = Boolean.TRUE;

                FacesContext.getCurrentInstance().addMessage("formInstitutionEducationFormId:cityId", new FacesMessage(FacesMessage.SEVERITY_ERROR, messages.getString("global.error"), messages.getString("city.province_is_empty")));

            } else {
                disabledCity = Boolean.FALSE;
                citys.clear();
                for (City city : listCities) {
                    citys.put(city.getCityName(), city.getId());
                }

                MapUtil.sortByValue(citys);
            }
            institutionEducationModel.setCityId(institutionEducation.getCity().getId());
        } else {
            institutionEducationModel.setAddress(null);
            institutionEducationModel.setCityId(null);
            institutionEducationModel.setCountryId(null);
            institutionEducationModel.setDescription(null);
            institutionEducationModel.setEducationLevelId(null);
            institutionEducationModel.setInstitutionEducationCode(null);
            institutionEducationModel.setInstitutionEducationName(null);
            institutionEducationModel.setPostalCode(null);
            institutionEducationModel.setProvinceId(null);
        }
    }
}
