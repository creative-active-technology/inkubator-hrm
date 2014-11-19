package com.inkubator.hrm.web.reference;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.City;
import com.inkubator.hrm.entity.Country;
import com.inkubator.hrm.entity.Province;
import com.inkubator.hrm.service.CityService;
import com.inkubator.hrm.service.CountryService;
import com.inkubator.hrm.service.ProvinceService;
import com.inkubator.hrm.util.MapUtil;
import com.inkubator.hrm.web.model.CityModel;
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
import org.primefaces.event.map.PointSelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

/**
 *
 * @author Taufik Hidayat
 */
@ManagedBean(name = "cityFormController")
@ViewScoped
public class CityFormController extends BaseController {

    private CityModel cityModel;
    private Boolean isUpdate;
    @ManagedProperty(value = "#{cityService}")
    private CityService cityService;
    @ManagedProperty(value = "#{provinceService}")
    private ProvinceService provinceService;
    @ManagedProperty(value = "#{countryService}")
    private CountryService countryService;
    private Map<String, Long> countrys = new TreeMap<>();
    private Map<String, Long> provinces = new TreeMap<>();
    private MapModel emptyModel;
    private double lat;
    private double lng;
    private double defaultLat;
    private double defaultLng;
    private Boolean disabled;
    private String locale;
    private ResourceBundle messages;

    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            String param = FacesUtil.getRequestParameter("execution");
            cityModel = new CityModel();
            isUpdate = Boolean.FALSE;
            emptyModel = new DefaultMapModel();
            disabled = Boolean.TRUE;
            locale = FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString();
            messages = ResourceBundle.getBundle("messages", new Locale(locale));
            List<Country> listCountrys = countryService.getAllData();

            for (Country country : listCountrys) {
                countrys.put(country.getCountryName(), country.getId());
            }

            MapUtil.sortByValue(countrys);

            defaultLat = -6.211551441520004D;
            defaultLng = 106.84444427490234D;

            if (param != null) {
                try {
                    City city = cityService.getCityByIdWithDetail(Long.parseLong(param.substring(1)));
                    cityModel.setId(city.getId());
                    cityModel.setCityCode(city.getCityCode());
                    cityModel.setCityName(city.getCityName());
                    cityModel.setProvinceId(city.getProvince().getId());
                    cityModel.setCountryId(city.getProvince().getCountry().getId());
                    cityModel.setLatitude(city.getLatitude());
                    cityModel.setLongitude(city.getLongitude());
                    isUpdate = Boolean.TRUE;

                    lat = Double.parseDouble(city.getLatitude());
                    lng = Double.parseDouble(city.getLongitude());
                    LatLng coord = new LatLng(lat, lng);

                    //Basic marker
                    emptyModel.addOverlay(new Marker(coord, city.getCityName()));

                    defaultLat = Double.parseDouble(city.getLatitude());
                    defaultLng = Double.parseDouble(city.getLongitude());

                } catch (Exception e) {
                    LOGGER.error("Error", e);
                }
            }

        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    @PreDestroy
    public void cleanAndExit() {
        cityService = null;
//        cityModel = null;
        isUpdate = null;
    }

    public CityModel getCityModel() {
        return cityModel;
    }

    public void setCityModel(CityModel cityModel) {
        this.cityModel = cityModel;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public void setCityService(CityService cityService) {
        this.cityService = cityService;
    }

    public void setCountryService(CountryService countryService) {
        this.countryService = countryService;
    }

    public void setProvinceService(ProvinceService provinceService) {
        this.provinceService = provinceService;
    }

    public Map<String, Long> getCountrys() {
        return countrys;
    }

    public void setCountrys(Map<String, Long> countrys) {
        this.countrys = countrys;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    public Map<String, Long> getProvinces() {
        return provinces;
    }

    public void setProvinces(Map<String, Long> provinces) {
        this.provinces = provinces;
    }

    public MapModel getEmptyModel() {
        return emptyModel;
    }

    public void setEmptyModel(MapModel emptyModel) {
        this.emptyModel = emptyModel;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getDefaultLat() {
        return defaultLat;
    }

    public void setDefaultLat(double defaultLat) {
        this.defaultLat = defaultLat;
    }

    public double getDefaultLng() {
        return defaultLng;
    }

    public void setDefaultLng(double defaultLng) {
        this.defaultLng = defaultLng;
    }

    public String doBack() {
        return "/protected/reference/city_view.htm?faces-redirect=true";
    }

    public String doSave() {

        try {
            City city = getEntityFromViewModel(cityModel);
          
            if (isUpdate) {
                cityService.update(city);
                MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.update_successfully",
                        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            } else {
                cityService.save(city);
                MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully",
                        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            }
            return "/protected/reference/city_detail.htm?faces-redirect=true&execution=e" + city.getId();
        } catch (BussinessException ex) { 
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
        return null;
    }

    private City getEntityFromViewModel(CityModel cityModel) throws BussinessException {
        City city = new City();


        if (cityModel.getId() != null) {
            city.setId(cityModel.getId());
        }
        
        if (cityModel.getProvinceId() == null) {
            throw new BussinessException("city.province_should_not_be_empty");
        }

        if (String.valueOf(lat).equals("0.0")) {
            throw new BussinessException("city.latitude_should_not_be_empty");
        }

        if (String.valueOf(lng).equals("0.0")) {
            throw new BussinessException("city.longitude_should_not_be_empty");
        }
        
        city.setCityCode(cityModel.getCityCode());
        city.setCityName(cityModel.getCityName());
        city.setProvince(new Province(cityModel.getProvinceId()));
    
        city.setLatitude(String.valueOf(lat));
        city.setLongitude(String.valueOf(lng));
        return city;
    }

    public void onPointSelect(PointSelectEvent event) {
//        System.out.println("Map Clicked");
        emptyModel.getMarkers().clear();
        LatLng location = event.getLatLng();

        lat = location.getLat();
        lng = location.getLng();
        defaultLat = lat;
        defaultLng = lng;
        emptyModel.addOverlay(new Marker(location, cityModel.getCityName()));
    }

    public void countryChanged(ValueChangeEvent event) {
        try {
           

            Country country = countryService.getEntiyByPK(Long.parseLong(String.valueOf(event.getNewValue())));

            defaultLat = Double.parseDouble(country.getLatitude());
            defaultLng = Double.parseDouble(country.getLongitude());

            List<Province> listProvinces = provinceService.getByCountryIdWithDetail(Long.parseLong(String.valueOf(event.getNewValue())));
            System.out.println("list province " + listProvinces);
            System.out.println("ukuran " + listProvinces.size());
            if (listProvinces.isEmpty() || listProvinces == null) {
                disabled = Boolean.TRUE;
                
                FacesContext.getCurrentInstance().addMessage("formCityFormId:provinceId", new FacesMessage(FacesMessage.SEVERITY_ERROR, messages.getString("global.error"), messages.getString("city.province_is_empty")));

            } else {
                disabled = Boolean.FALSE;
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
}
