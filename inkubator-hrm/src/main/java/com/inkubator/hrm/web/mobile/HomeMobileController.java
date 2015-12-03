/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.mobile;

import com.inkubator.webcore.controller.BaseController;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author denifahri
 */
@ManagedBean(name = "homeMobileController")
@ViewScoped
public class HomeMobileController extends BaseController {
private Integer totalRequestHistory;
private Integer totalPendingTask;
private Integer totalPendingRequest;



    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        

    }

    public Integer getTotalRequestHistory() {
        return totalRequestHistory;
    }

    public void setTotalRequestHistory(Integer totalRequestHistory) {
        this.totalRequestHistory = totalRequestHistory;
    }

    public Integer getTotalPendingTask() {
        return totalPendingTask;
    }

    public void setTotalPendingTask(Integer totalPendingTask) {
        this.totalPendingTask = totalPendingTask;
    }

    public Integer getTotalPendingRequest() {
        return totalPendingRequest;
    }

    public void setTotalPendingRequest(Integer totalPendingRequest) {
        this.totalPendingRequest = totalPendingRequest;
    }
    
    
}
