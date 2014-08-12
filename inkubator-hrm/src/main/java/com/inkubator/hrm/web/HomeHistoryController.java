/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web;

import com.inkubator.hrm.entity.LoginHistory;
import com.inkubator.hrm.entity.RiwayatAkses;
import com.inkubator.hrm.service.LoginHistoryService;
import com.inkubator.hrm.service.RiwayatAksesService;
import com.inkubator.securitycore.util.UserInfoUtil;
import com.inkubator.webcore.controller.BaseController;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import org.hibernate.criterion.Order;

/**
 *
 * @author Deni Husni FR
 */
@ManagedBean(name = "homeHistoryController")
@RequestScoped
public class HomeHistoryController extends BaseController {

	@ManagedProperty(value = "#{loginHistoryService}")
	private LoginHistoryService loginHistoryService;
    @ManagedProperty(value = "#{riwayatAksesService}")
    private RiwayatAksesService riwayatAksesService;
    private List<RiwayatAkses> dataRiwayatAkses;
    private List<LoginHistory> loginHistories;

    public void setRiwayatAksesService(RiwayatAksesService riwayatAksesService) {
        this.riwayatAksesService = riwayatAksesService;
    }

    @PostConstruct
    @Override
    public void initialization() {
        try {
            dataRiwayatAkses = riwayatAksesService.getDataByUserId(UserInfoUtil.getUserName(), 0, 4, Order.desc("dateAccess"));
            loginHistories = loginHistoryService.getByParam(0, 4, Order.desc("loginDate"));
        } catch (Exception ex) {
           LOGGER.error("Error", ex);
        }
    }

    @PreDestroy
    public void cleanAndExit(){
        dataRiwayatAkses=null;
        riwayatAksesService=null;
        loginHistoryService = null;
        loginHistories = null;
        
    }

    public List<RiwayatAkses> getDataRiwayatAkses() {
        return dataRiwayatAkses;
    }

    public void setDataRiwayatAkses(List<RiwayatAkses> dataRiwayatAkses) {
        this.dataRiwayatAkses = dataRiwayatAkses;
    }    
    
    public List<LoginHistory> getLoginHistories() {
		return loginHistories;
	}

	public void setLoginHistories(List<LoginHistory> loginHistories) {
		this.loginHistories = loginHistories;
	}

	public void setLoginHistoryService(LoginHistoryService loginHistoryService) {
		this.loginHistoryService = loginHistoryService;
	}
    
}
