package com.inkubator.hrm.web.reference;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.Country;
import com.inkubator.hrm.entity.Province;
import com.inkubator.hrm.service.CountryService;
import com.inkubator.hrm.service.ProvinceService;
import com.inkubator.hrm.util.MapUtil;
import com.inkubator.hrm.web.model.ProvinceModel;
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
import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Taufik Hidayat
 */
@ManagedBean(name = "provinceFormController")
@ViewScoped
public class ProvinceFormController extends BaseController {

    private ProvinceModel provinceModel;
    private Boolean isUpdate;
    @ManagedProperty(value = "#{provinceService}")
    private ProvinceService provinceService;
    @ManagedProperty(value = "#{countryService}")
    private CountryService countryService;
    private Map<String, Long> countrys = new TreeMap<>();

    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            String param = FacesUtil.getRequestParameter("param");
            provinceModel = new ProvinceModel();
            isUpdate = Boolean.FALSE;
            List<Country> listCountrys = countryService.getAllData();
            
            for (Country country : listCountrys) {
                countrys.put(country.getCountryName(), country.getId());
            }
            
            MapUtil.sortByValue(countrys);
            if (StringUtils.isNumeric(param)) {
                try {
                    Province province = provinceService.getEntiyByPK(Long.parseLong(param));
                    if (province != null) {
                        provinceModel.setId(province.getId());
                        provinceModel.setProvinceCode(province.getProvinceCode());
                        provinceModel.setProvinceName(province.getProvinceName());
                        provinceModel.setCountryId(province.getCountry().getId());
                        isUpdate = Boolean.TRUE;
                    }
                    
                } catch (Exception e) {
                    LOGGER.error("Error", e);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(ProvinceFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @PreDestroy
    public void cleanAndExit() {
        provinceService = null;
//        provinceModel = null;
        isUpdate = null;
    }

    public ProvinceModel getProvinceModel() {
        return provinceModel;
    }

    public void setProvinceModel(ProvinceModel provinceModel) {
        this.provinceModel = provinceModel;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public void setProvinceService(ProvinceService provinceService) {
        this.provinceService = provinceService;
    }

    public void setCountryService(CountryService countryService) {
        this.countryService = countryService;
    }

    
    
    public Map<String, Long> getCountrys() {
        return countrys;
    }

    public void setCountrys(Map<String, Long> countrys) {
        this.countrys = countrys;
    }

    public void doSave() {
        Province province = getEntityFromViewModel(provinceModel);
        try {
            if (isUpdate) {
                provinceService.update(province);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                provinceService.save(province);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch (BussinessException ex) { 
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    private Province getEntityFromViewModel(ProvinceModel provinceModel) {
        Province province = new Province();
        if (provinceModel.getId() != null) {
            province.setId(provinceModel.getId());
        }
        province.setProvinceCode(provinceModel.getProvinceCode());
        province.setProvinceName(provinceModel.getProvinceName());
        province.setCountry(new Country(provinceModel.getCountryId()));
        return province;
    }
}
