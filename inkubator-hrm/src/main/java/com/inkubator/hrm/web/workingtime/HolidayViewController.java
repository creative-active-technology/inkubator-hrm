/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.workingtime;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.WtHoliday;
import com.inkubator.hrm.service.WtHolidayService;
import com.inkubator.hrm.web.lazymodel.WtHolidayLazyModel;
import com.inkubator.hrm.web.search.HolidaySearchParameter;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.hibernate.exception.ConstraintViolationException;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.springframework.dao.DataIntegrityViolationException;

/**
 *
 * @author Deni Husni FR
 */
@ManagedBean(name = "holidayViewController")
@ViewScoped
public class HolidayViewController extends BaseController {
    
    private HolidaySearchParameter holidaySearchParameter;
    private LazyDataModel<WtHoliday> wtHolidayLazyDataModel;
    @ManagedProperty(value = "#{wtHolidayService}")
    private WtHolidayService wtHolidayService;
    private WtHoliday selecWtHoliday;
    
    public HolidaySearchParameter getHolidaySearchParameter() {
        return holidaySearchParameter;
    }
    
    public void setHolidaySearchParameter(HolidaySearchParameter holidaySearchParameter) {
        this.holidaySearchParameter = holidaySearchParameter;
    }
    
    public LazyDataModel<WtHoliday> getWtPeriodelazyDataModel() {
        if (wtHolidayLazyDataModel == null) {
            wtHolidayLazyDataModel = new WtHolidayLazyModel(holidaySearchParameter, wtHolidayService);
        }
        return wtHolidayLazyDataModel;
    }
    
    public void setWtPeriodelazyDataModel(LazyDataModel<WtHoliday> wtPeriodelazyDataModel) {
        this.wtHolidayLazyDataModel = wtPeriodelazyDataModel;
    }
    
    public WtHoliday getSelecWtHoliday() {
        return selecWtHoliday;
    }
    
    public void setSelecWtHoliday(WtHoliday selecWtHoliday) {
        this.selecWtHoliday = selecWtHoliday;
    }
    
    public void setWtHolidayService(WtHolidayService wtHolidayService) {
        this.wtHolidayService = wtHolidayService;
    }
    
    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        holidaySearchParameter = new HolidaySearchParameter();
        
    }
    
    public void doSearch() {
        wtHolidayLazyDataModel = null;
    }
    
    public void doDetail() {
        try {
            selecWtHoliday = wtHolidayService.getEntiyByPK(selecWtHoliday.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }
    
    public void doDelete() {
        try {
            this.wtHolidayService.delete(selecWtHoliday);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            
        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            LOGGER.error("Error", ex);
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }
    
    public void doAdd() {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 400);
        options.put("contentHeight", 360);
        
        RequestContext.getCurrentInstance().openDialog("holiday_form", options, null);
    }
    
    public void doEdit() {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 400);
        options.put("contentHeight", 360);
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> dataIsi = new ArrayList<>();
        dataIsi.add(String.valueOf(selecWtHoliday.getId()));
        dataToSend.put("param", dataIsi);
        RequestContext.getCurrentInstance().openDialog("holiday_form", options, dataToSend);
    }
    
    @Override
    public void onDialogReturn(SelectEvent event) {
        wtHolidayLazyDataModel = null;
        super.onDialogReturn(event);
    }
    
    public void onDelete() {
        try {
            selecWtHoliday = wtHolidayService.getEntiyByPK(selecWtHoliday.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }
    
    public void doChangeYear() {
        wtHolidayLazyDataModel = null;
    }
    
    public void doChangeMonth() {
        wtHolidayLazyDataModel = null;
    }
    
    @PreDestroy
    private void cleanAndExit() {
        holidaySearchParameter = null;
        wtHolidayService = null;
        wtHolidayLazyDataModel = null;
        selecWtHoliday = null;
    }
}
