/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.employee;

import com.inkubator.common.util.DateTimeUtil;
import com.inkubator.hrm.entity.BioData;
import com.inkubator.hrm.entity.WtPeriode;
import com.inkubator.hrm.service.BioDataService;
import com.inkubator.hrm.service.WtPeriodeService;
import com.inkubator.hrm.web.model.PayTempAttendanceStatusModel;
import com.inkubator.webcore.controller.BaseController;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
@ManagedBean(name = "totalAttendanceEditController")
@ViewScoped
public class TotalAttendanceEditController extends BaseController {

    @ManagedProperty(value = "#{wtPeriodeService}")
    private WtPeriodeService wtPeriodeService;
    private Date startDate;
    private Date endDate;
    private WtPeriode wtPeriode;
    private Integer jumlahhariKerja;

    public Integer getJumlahhariKerja() {
        return jumlahhariKerja;
    }

    public void setJumlahhariKerja(Integer jumlahhariKerja) {
        this.jumlahhariKerja = jumlahhariKerja;
    }
   
    @PostConstruct
    @Override
    public void initialization() {
        try {        
            super.initialization();
            wtPeriode = wtPeriodeService.getEntityByPayrollTypeActive();
            if(wtPeriode != null){
                startDate = wtPeriode.getFromPeriode();
                endDate = wtPeriode.getUntilPeriode();                
                if(null != wtPeriode.getWorkingDays()){
                    jumlahhariKerja = wtPeriode.getWorkingDays();
                }else{
                    jumlahhariKerja = DateTimeUtil.getTotalWorkingDay(startDate, endDate, 0, 0);
                }                                              
            }else{
                jumlahhariKerja = 0;
            }            
        } catch (Exception ex) {
            Logger.getLogger(TotalAttendanceEditController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void doSearch() {
        try {
           
        } catch (Exception ex) {
           LOGGER.error("Error", ex);
        }
    }

    public WtPeriodeService getWtPeriodeService() {
        return wtPeriodeService;
    }

    public void setWtPeriodeService(WtPeriodeService wtPeriodeService) {
        this.wtPeriodeService = wtPeriodeService;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    
    public void doEditTotalAttendance(){
        try {           
            wtPeriode.setWorkingDays(jumlahhariKerja);
            wtPeriodeService.saveOrUpdate(wtPeriode);
            RequestContext.getCurrentInstance().closeDialog(jumlahhariKerja);
        } catch (Exception ex) {
            Logger.getLogger(TotalAttendanceEditController.class.getName()).log(Level.SEVERE, null, ex);
            RequestContext.getCurrentInstance().closeDialog(jumlahhariKerja);
        }
    }
    
    public void doReload(){
        try {
            if (wtPeriode != null) {
                startDate = wtPeriode.getFromPeriode();
                endDate = wtPeriode.getUntilPeriode();
                if (null != wtPeriode.getWorkingDays()) {
                    jumlahhariKerja = wtPeriode.getWorkingDays();
                } else {
                    jumlahhariKerja = DateTimeUtil.getTotalWorkingDay(startDate, endDate, 0, 0);
                }
            } else {
                jumlahhariKerja = 0;
            }
        }catch (Exception ex) {
            Logger.getLogger(TotalAttendanceEditController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
   
    
     @PreDestroy
    public void cleanAndExit() {
        wtPeriodeService=null;
        startDate=null;
        endDate=null;
        jumlahhariKerja=null;
    }
}
