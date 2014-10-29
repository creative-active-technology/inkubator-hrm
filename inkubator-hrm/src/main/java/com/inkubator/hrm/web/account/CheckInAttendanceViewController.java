/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.account;

import com.inkubator.hrm.entity.CheckInAttendance;
import com.inkubator.hrm.service.CheckInAttendanceService;
import com.inkubator.hrm.web.lazymodel.CheckInAttendanceLazyDataModel;
import com.inkubator.hrm.web.search.CheckInAttendanceSearchParameter;
import com.inkubator.webcore.controller.BaseController;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author Deni
 */
@ManagedBean(name = "checkInAttendanceViewController")
@ViewScoped
public class CheckInAttendanceViewController extends BaseController {
    @ManagedProperty(value = "#{checkInAttendanceService}")
    private CheckInAttendanceService service;
    private CheckInAttendanceSearchParameter searchParameter;
    private LazyDataModel<CheckInAttendance> lazy;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        searchParameter = new CheckInAttendanceSearchParameter();
       
    }
    
    @PreDestroy
    public void cleanAndExit() {
    	service = null;
    	searchParameter = null;
    	lazy = null;
    }
    
    public CheckInAttendanceService getService() {
        return service;
    }

    public void setService(CheckInAttendanceService service) {
        this.service = service;
    }

    public CheckInAttendanceSearchParameter getSearchParameter() {
        return searchParameter;
    }

    public void setSearchParameter(CheckInAttendanceSearchParameter searchParameter) {
        this.searchParameter = searchParameter;
    }

    public LazyDataModel<CheckInAttendance> getLazy() {
        if (lazy == null) {
            lazy = new CheckInAttendanceLazyDataModel(searchParameter, service);
        }
        return lazy;
    }

    public void setLazy(LazyDataModel<CheckInAttendance> lazy) {
        this.lazy = lazy;
    }
    
    
    
}
