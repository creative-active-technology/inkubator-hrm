/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web;

import com.inkubator.hrm.entity.WtHoliday;
import com.inkubator.hrm.service.WtHolidayService;
import com.inkubator.webcore.controller.BaseController;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.LazyScheduleModel;
import org.primefaces.model.ScheduleModel;

/**
 *
 * @author Deni Husni FR
 */
@ManagedBean(name = "calendarController")
@RequestScoped
public class CalendarController extends BaseController {

    private ScheduleModel lazyScheduleModel;
    @ManagedProperty(value = "#{wtHolidayService}")
    private WtHolidayService wtHolidayService;

    public void setWtHolidayService(WtHolidayService wtHolidayService) {
        this.wtHolidayService = wtHolidayService;
    }

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        lazyScheduleModel = new LazyScheduleModel() {
            List<WtHoliday> data =new ArrayList<>();
            @Override
            public void loadEvents(Date start, Date end) {
                try {
                    data = wtHolidayService.getBetweenDate(start, end);
                    Thread.sleep(300);
                } catch (Exception ex) {
                    LOGGER.error("Erorr", ex);
                }
                clear();
                for (WtHoliday wtHoliday : data) {
                    addEvent(new DefaultScheduleEvent(wtHoliday.getHolidayName(), wtHoliday.getHolidayDate(), wtHoliday.getHolidayDate(),true)); 
                }
               
            }
        };

    }

    public ScheduleModel getLazyScheduleModel() {
        return lazyScheduleModel;
    }

    public void setLazyScheduleModel(ScheduleModel lazyScheduleModel) {
        this.lazyScheduleModel = lazyScheduleModel;
    }

}
