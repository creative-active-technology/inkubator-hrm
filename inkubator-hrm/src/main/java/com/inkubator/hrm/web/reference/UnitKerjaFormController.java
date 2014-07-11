/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.reference;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.City;
import com.inkubator.hrm.entity.UnitKerja;
import com.inkubator.hrm.service.CityService;
import com.inkubator.hrm.service.UnitKerjaService;
import com.inkubator.hrm.util.MapUtil;
import com.inkubator.hrm.web.model.UnitKerjaModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.util.ArrayList;
import java.util.HashMap;
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
import org.primefaces.context.RequestContext;

/**
 *
 * @author deniarianto
 */
@ManagedBean(name = "unitKerjaFormController")
@ViewScoped
public class UnitKerjaFormController extends BaseController{
    @ManagedProperty(value = "#{unitKerjaService}")
    private UnitKerjaService unitKerjaService;
    @ManagedProperty(value = "#{cityService}")
    private CityService cityService;
    private UnitKerjaModel unitKerjaModel;
    private UnitKerja selectedUnitKerja;
    private Boolean isEdit;
    private Map<String, Long> listDropDownCity;
    private List<City> cityList = new ArrayList<>();
    private City city;

    public UnitKerjaService getUnitKerjaService() {
        return unitKerjaService;
    }

    public void setUnitKerjaService(UnitKerjaService unitKerjaService) {
        this.unitKerjaService = unitKerjaService;
    }

    public UnitKerjaModel getUnitKerjaModel() {
        return unitKerjaModel;
    }

    public void setUnitKerjaModel(UnitKerjaModel unitKerjaModel) {
        this.unitKerjaModel = unitKerjaModel;
    }
    

    public UnitKerja getSelectedUnitKerja() {
        return selectedUnitKerja;
    }

    public void setSelectedUnitKerja(UnitKerja selectedUnitKerja) {
        this.selectedUnitKerja = selectedUnitKerja;
    }

    public Boolean getIsEdit() {
        return isEdit;
    }

    public void setIsEdit(Boolean isEdit) {
        this.isEdit = isEdit;
    }

    public CityService getCityService() {
        return cityService;
    }

    public void setCityService(CityService cityService) {
        this.cityService = cityService;
    }

    public Map<String, Long> getListDropDownCity() {
        return listDropDownCity;
    }

    public void setListDropDownCity(Map<String, Long> listDropDownCity) {
        this.listDropDownCity = listDropDownCity;
    }
    
    public List<City> getCityList() {
        return cityList;
    }

    public void setCityList(List<City> cityList) {
        this.cityList = cityList;
    }
    
    
    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        String param = FacesUtil.getRequestParameter("param");
        unitKerjaModel = new UnitKerjaModel();
        listDropDownCity = new TreeMap<>();
        try {
            cityList = cityService.getAllData();
        } catch (Exception ex) {
            Logger.getLogger(UnitKerjaFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (City citiesList : cityList) {
            listDropDownCity.put(citiesList.getCityName(), citiesList.getId());                
        }
        MapUtil.sortByValue(listDropDownCity);
        try {
            if (param != null) {
                
                UnitKerja unitKerja = unitKerjaService.getEntiyByPK(Long.parseLong(param));
                isEdit = Boolean.TRUE;
                unitKerjaModel.setId(unitKerja.getId());
                unitKerjaModel.setCode(unitKerja.getCode());
                unitKerjaModel.setName(unitKerja.getName());
                unitKerjaModel.setLocation(unitKerja.getLocation());
                unitKerjaModel.setCityId(unitKerja.getCity().getId());
            } else {
                isEdit = Boolean.FALSE;
            }
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }
    
    public void doSave() throws Exception {
        UnitKerja unitKerja = getEntityFromViewModel(unitKerjaModel);
        try {
            if (isEdit) {
                unitKerjaService.update(unitKerja);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                unitKerjaService.save(unitKerja);
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
    
    private UnitKerja getEntityFromViewModel(UnitKerjaModel unitKerjaModel) throws Exception {
        UnitKerja unitKerja = new UnitKerja();
        if (unitKerjaModel.getId() != null) {
            unitKerja.setId(unitKerjaModel.getId());
        }
        unitKerja.setCode(unitKerjaModel.getCode());
        unitKerja.setName(unitKerjaModel.getName());
        unitKerja.setLocation(unitKerjaModel.getLocation());
        unitKerja.setCity(cityService.getEntiyByPK(unitKerjaModel.getCityId()));
        return unitKerja;
    }
    
    @PreDestroy
    private void cleanAndExit() {
        unitKerjaModel = null;
        unitKerjaService = null;
        selectedUnitKerja = null;
        isEdit = null;
        listDropDownCity = null;
        cityList = null;
    }
}
