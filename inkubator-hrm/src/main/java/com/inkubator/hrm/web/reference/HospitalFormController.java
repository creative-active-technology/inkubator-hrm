package com.inkubator.hrm.web.reference;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.City;
import com.inkubator.hrm.entity.Country;
import com.inkubator.hrm.entity.Currency;
import com.inkubator.hrm.entity.Hospital;
import com.inkubator.hrm.entity.Province;
import com.inkubator.hrm.service.CityService;
import com.inkubator.hrm.service.CountryService;
import com.inkubator.hrm.service.HospitalService;
import com.inkubator.hrm.service.ProvinceService;
import com.inkubator.hrm.util.MapUtil;
import com.inkubator.hrm.web.model.HospitalModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;
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
@ManagedBean(name = "hospitalFormController")
@ViewScoped
public class HospitalFormController extends BaseController {

    private HospitalModel hospitalModel;
    private Boolean isUpdate;
    @ManagedProperty(value = "#{hospitalService}")
    private HospitalService hospitalService;
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
        super.initialization();
        try {
            String param = FacesUtil.getRequestParameter("param");
            hospitalModel = new HospitalModel();
            isUpdate = Boolean.FALSE;

            disabledCity = Boolean.TRUE;
            disabledProvince = Boolean.TRUE;
            locale = FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString();
            messages = ResourceBundle.getBundle("Messages", new Locale(locale));
            List<Country> listCountrys = countryService.getAllData();

            for (Country country : listCountrys) {
                countrys.put(country.getCountryName(), country.getId());
            }

            MapUtil.sortByValue(countrys);
            if (StringUtils.isNumeric(param)) {

                Hospital hospital = hospitalService.getEntityByPKWithDetail(Long.parseLong(param));
                if (hospital != null) {
                    disabledCity = Boolean.FALSE;
                    disabledProvince = Boolean.FALSE;
                    hospitalModel.setId(hospital.getId());
                    hospitalModel.setCode(hospital.getCode());
                    hospitalModel.setName(hospital.getName());
                    hospitalModel.setAddress(hospital.getAddress());
                    hospitalModel.setPhone(hospital.getPhone());
                    hospitalModel.setCountryId(hospital.getCity().getProvince().getCountry().getId());
                    hospitalModel.setProvinceId(hospital.getCity().getProvince().getId());
                    hospitalModel.setCityId(hospital.getCity().getId());
                    hospitalModel.setType(hospital.getType());
                    hospitalModel.setPostalCode(hospital.getPostalCode());
                    List<Province> listProvinces = provinceService.getByCountryIdWithDetail(hospitalModel.getCountryId());
                    for (Province province : listProvinces) {
                        provinces.put(province.getProvinceName(), province.getId());
                    }

                    MapUtil.sortByValue(provinces);

                    List<City> listCities = cityService.getByProvinceIdWithDetail(hospitalModel.getProvinceId());

                    for (City city : listCities) {
                        citys.put(city.getCityName(), city.getId());
                    }

                    MapUtil.sortByValue(citys);
                    isUpdate = Boolean.TRUE;
                }
            }
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }

    }

    @PreDestroy
    public void cleanAndExit() {
        hospitalService = null;
//        hospitalModel = null;
        isUpdate = null;
        cityService = null;
        provinceService = null;
        countryService = null;
        countrys = null;
        provinces = null;
        citys = null;
        disabledProvince = null;
        disabledCity = null;
        locale = null;
        messages = null;
    }

    public HospitalModel getHospitalModel() {
        return hospitalModel;
    }

    public void setHospitalModel(HospitalModel hospitalModel) {
        this.hospitalModel = hospitalModel;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public void setHospitalService(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }

    public void doReset() throws Exception {
        if (hospitalModel.getId() != null) {
            Hospital hospital = hospitalService.getEntityByPKWithDetail(hospitalModel.getId());
            disabledCity = Boolean.FALSE;
            disabledProvince = Boolean.FALSE;
            hospitalModel.setId(hospital.getId());
            hospitalModel.setCode(hospital.getCode());
            hospitalModel.setName(hospital.getName());
            hospitalModel.setAddress(hospital.getAddress());
            hospitalModel.setPhone(hospital.getPhone());
            hospitalModel.setCountryId(hospital.getCity().getProvince().getCountry().getId());
            hospitalModel.setProvinceId(hospital.getCity().getProvince().getId());
            hospitalModel.setCityId(hospital.getCity().getId());
            hospitalModel.setType(hospital.getType());
            hospitalModel.setPostalCode(hospital.getPostalCode());
        }else{
            disabledCity = Boolean.TRUE;
            disabledProvince = Boolean.TRUE;
            hospitalModel.setId(null);
            hospitalModel.setCode(null);
            hospitalModel.setName(null);
            hospitalModel.setAddress(null);
            hospitalModel.setPhone(null);
            hospitalModel.setCountryId(null);
            hospitalModel.setProvinceId(null);
            hospitalModel.setCityId(null);
            hospitalModel.setType(null);
            hospitalModel.setPostalCode(null);
        }
    }

    public void doSave() {
        Hospital hospital = getEntityFromViewModel(hospitalModel);
        try {
            if (isUpdate) {
                hospitalService.update(hospital);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                hospitalService.save(hospital);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch (BussinessException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
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

    public void setCityService(CityService cityService) {
        this.cityService = cityService;
    }

    public void setProvinceService(ProvinceService provinceService) {
        this.provinceService = provinceService;
    }

    public void setCountryService(CountryService countryService) {
        this.countryService = countryService;
    }

    public void setDisabledProvince(Boolean disabledProvince) {
        this.disabledProvince = disabledProvince;
    }

    public void setDisabledCity(Boolean disabledCity) {
        this.disabledCity = disabledCity;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public void setMessages(ResourceBundle messages) {
        this.messages = messages;
    }

    public String getLocale() {
        return locale;
    }

    public ResourceBundle getMessages() {
        return messages;
    }

    public Boolean getDisabledProvince() {
        return disabledProvince;
    }

    public Boolean getDisabledCity() {
        return disabledCity;
    }

    private Hospital getEntityFromViewModel(HospitalModel hospitalModel) {
        Hospital hospital = new Hospital();
        if (hospitalModel.getId() != null) {
            hospital.setId(hospitalModel.getId());
        }
        hospital.setCode(hospitalModel.getCode());
        hospital.setName(hospitalModel.getName());
        hospital.setAddress(hospitalModel.getAddress());
        hospital.setPhone(hospitalModel.getPhone());
        hospital.setCity(new City(hospitalModel.getCityId()));
        hospital.setType(hospitalModel.getType());
        hospital.setPostalCode(hospitalModel.getPostalCode());
        return hospital;
    }

    public void countryChanged(ValueChangeEvent event) {
        try {

            Country country = countryService.getEntiyByPK(Long.parseLong(String.valueOf(event.getNewValue())));

            List<Province> listProvinces = provinceService.getByCountryIdWithDetail(Long.parseLong(String.valueOf(event.getNewValue())));

            if (listProvinces.isEmpty() || listProvinces == null) {
                disabledProvince = Boolean.TRUE;

                FacesContext.getCurrentInstance().addMessage("formBioBankAccountFormId:provinceId", new FacesMessage(FacesMessage.SEVERITY_ERROR, messages.getString("global.error"), messages.getString("city.province_is_empty")));

            } else {
                disabledProvince = Boolean.FALSE;
                provinces.clear();
                for (Province province : listProvinces) {
                    provinces.put(province.getProvinceName(), province.getId());
                }
                MapUtil.sortByValue(provinces);
            }
            
            disabledCity = Boolean.TRUE;
            hospitalModel.setProvinceId(null);
            hospitalModel.setCityId(null);
            hospitalModel.setPostalCode(null);

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

                FacesContext.getCurrentInstance().addMessage("formBioBankAccountFormId:cityId", new FacesMessage(FacesMessage.SEVERITY_ERROR, messages.getString("global.error"), messages.getString("city.province_is_empty")));

            } else {
                disabledCity = Boolean.FALSE;
                citys.clear();
                for (City city : listCities) {
                    citys.put(city.getCityName(), city.getId());
                }
                MapUtil.sortByValue(citys);
                
                hospitalModel.setCityId(null);
                hospitalModel.setPostalCode(null);
            }

        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }
}
