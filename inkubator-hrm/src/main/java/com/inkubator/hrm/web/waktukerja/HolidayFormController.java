/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.waktukerja;

import com.inkubator.hrm.entity.Religion;
import com.inkubator.hrm.service.ReligionService;
import com.inkubator.hrm.service.WtHolidayService;
import com.inkubator.hrm.web.model.HolidayModel;
import com.inkubator.webcore.controller.BaseController;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Deni Husni FR
 */
@ManagedBean(name = "holidayFormController")
@ViewScoped
public class HolidayFormController extends BaseController {

    private HolidayModel holidayModel;
    @ManagedProperty(value = "#{wtHolidayService}")
    private WtHolidayService wtHolidayService;
    @ManagedProperty(value = "#{religionService}")
    private ReligionService religionService;
    private Map<String, Long> mapReligions = new TreeMap<>();

    public HolidayModel getHolidayModel() {
        return holidayModel;
    }

    public void setHolidayModel(HolidayModel holidayModel) {
        this.holidayModel = holidayModel;
    }

    public void setWtHolidayService(WtHolidayService wtHolidayService) {
        this.wtHolidayService = wtHolidayService;
    }

    public void setReligionService(ReligionService religionService) {
        this.religionService = religionService;
    }
    

    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            holidayModel=new HolidayModel();
            List<Religion> religions =religionService.getAllData();
            for (Religion religion : religions) {
               mapReligions.put(religion.getName(), religion.getId());
            }
            
        } catch (Exception ex) {
            LOGGER.error("Errot", ex);
        }

    }

    public Map<String, Long> getMapReligions() {
        return mapReligions;
    }

    public void setMapReligions(Map<String, Long> mapReligions) {
        this.mapReligions = mapReligions;
    }

    
}
