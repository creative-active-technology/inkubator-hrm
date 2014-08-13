package com.inkubator.hrm.web.reference;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.City;
import com.inkubator.hrm.entity.Country;
import com.inkubator.hrm.entity.InstitutionEducation;
import com.inkubator.hrm.entity.Province;
import com.inkubator.hrm.service.CityService;
import com.inkubator.hrm.service.CountryService;
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
    private Map<String, Long> countrys = new TreeMap<>();
    private Map<String, Long> provinces = new TreeMap<>();
    private Map<String, Long> citys = new TreeMap<>();
    private Boolean disabledProvince;
    private Boolean disabledCity;
    private String locale;
    private ResourceBundle messages;

    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            String param = FacesUtil.getRequestParameter("param");
            institutionEducationModel = new InstitutionEducationModel();
            isUpdate = Boolean.FALSE;
            disabledCity = Boolean.TRUE;
            disabledProvince = Boolean.TRUE;
            locale = FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString();
            messages = ResourceBundle.getBundle("messages", new Locale(locale));
            List<Country> listCountrys = countryService.getAllData();

            for (Country country : listCountrys) {
                countrys.put(country.getCountryName(), country.getId());
            }

            MapUtil.sortByValue(countrys);

            if (StringUtils.isNumeric(param)) {
                try {
                    InstitutionEducation institutionEducation = institutionEducationService.getInstitutionEducationByIdWithDetail(Long.parseLong(param));
                    if (institutionEducation != null) {
                        institutionEducationModel.setId(institutionEducation.getId());
                        institutionEducationModel.setInstitutionEducationCode(institutionEducation.getInstitutionEducationCode());
                        institutionEducationModel.setInstitutionEducationName(institutionEducation.getInstitutionEducationName());
                        institutionEducationModel.setCountryId(institutionEducation.getCity().getProvince().getCountry().getId());
                        institutionEducationModel.setProvinceId(institutionEducation.getCity().getProvince().getId());
                        institutionEducationModel.setCityId(institutionEducation.getCity().getId());
                        institutionEducationModel.setAddress(institutionEducation.getAddress());
                        institutionEducationModel.setPostalCode(institutionEducation.getPostalCode());
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

    public void doSave() {
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
        } catch (BussinessException ex) { //data already exist(duplicate)
            LOGGER.error("Error", ex);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    private InstitutionEducation getEntityFromViewModel(InstitutionEducationModel institutionEducationModel) {
        InstitutionEducation institutionEducation = new InstitutionEducation();
        if (institutionEducationModel.getId() != null) {
            institutionEducation.setId(institutionEducationModel.getId());
        }
        institutionEducation.setInstitutionEducationCode(institutionEducationModel.getInstitutionEducationCode());
        institutionEducation.setInstitutionEducationName(institutionEducationModel.getInstitutionEducationName());
        institutionEducation.setCity(new City(institutionEducationModel.getCityId()));
        institutionEducation.setAddress(institutionEducationModel.getAddress());
        institutionEducation.setPostalCode(institutionEducationModel.getPostalCode());
        return institutionEducation;
    }

    public void countryChanged(ValueChangeEvent event) {
        try {
            System.out.println("New value: " + event.getNewValue());
            System.out.println("Country Id  " + institutionEducationModel.getCountryId());

            Country country = countryService.getEntiyByPK(Long.parseLong(String.valueOf(event.getNewValue())));

            List<Province> listProvinces = provinceService.getByCountryIdWithDetail(Long.parseLong(String.valueOf(event.getNewValue())));
            System.out.println("list province " + listProvinces);
            System.out.println("ukuran " + listProvinces.size());
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
            System.out.println("Province Id  " + institutionEducationModel.getProvinceId());

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
}
