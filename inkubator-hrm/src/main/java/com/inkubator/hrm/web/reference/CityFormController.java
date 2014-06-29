package com.inkubator.hrm.web.reference;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.Province;
import com.inkubator.hrm.entity.City;
import com.inkubator.hrm.service.ProvinceService;
import com.inkubator.hrm.service.CityService;
import com.inkubator.hrm.util.MapUtil;
import com.inkubator.hrm.web.model.CityModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.util.List;
import java.util.Map;
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
import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;
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
    private Map<String, Long> provinces = new TreeMap<>();
    private MapModel emptyModel;
    private double lat;
    private double lng;
    private double defaultLat;
    private double defaultLng;

    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            String param = FacesUtil.getRequestParameter("param");
            cityModel = new CityModel();
            isUpdate = Boolean.FALSE;
            emptyModel = new DefaultMapModel();

            List<Province> listProvinces = provinceService.getAllData();

            for (Province province : listProvinces) {
                provinces.put(province.getProvinceName(), province.getId());
            }

            MapUtil.sortByValue(provinces);

            defaultLat = -6.211551441520004D;
            defaultLng = 106.84444427490234D;
            if (StringUtils.isNumeric(param)) {
                try {
                    City city = cityService.getEntiyByPK(Long.parseLong(param));
                    if (city != null) {
                        cityModel.setId(city.getId());
                        cityModel.setCityCode(city.getCityCode());
                        cityModel.setCityName(city.getCityName());
                        cityModel.setProvinceId(city.getProvince().getId());
                        cityModel.setLatitude(city.getLatitude());
                        cityModel.setLongitude(city.getLongitude());
                        isUpdate = Boolean.TRUE;
                        
                        lat = Double.parseDouble(city.getLatitude());
                        lng = Double.parseDouble(city.getLongitude());
                        LatLng coord = new LatLng(lat, lng );

                        //Basic marker
                        emptyModel.addOverlay(new Marker(coord, city.getCityName()));

                        defaultLat = Double.parseDouble(city.getLatitude());
                        defaultLng = Double.parseDouble(city.getLongitude());
                    }

                } catch (Exception e) {
                    LOGGER.error("Error", e);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(CityFormController.class.getName()).log(Level.SEVERE, null, ex);
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

    public void setProvinceService(ProvinceService provinceService) {
        this.provinceService = provinceService;
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

    public void doSave() {
        City city = getEntityFromViewModel(cityModel);
        System.out.println("city lat :" + city.getLatitude());
        try {
            if (isUpdate) {
                cityService.update(city);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                cityService.save(city);
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

    private City getEntityFromViewModel(CityModel cityModel) {
        City city = new City();
        if (cityModel.getId() != null) {
            city.setId(cityModel.getId());
        }
        city.setCityCode(cityModel.getCityCode());
        city.setCityName(cityModel.getCityName());
        city.setProvince(new Province(cityModel.getProvinceId()));
        System.out.println("Latitude : " + lat);
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
}
