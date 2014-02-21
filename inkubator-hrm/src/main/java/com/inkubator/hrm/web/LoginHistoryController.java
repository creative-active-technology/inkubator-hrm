/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.LoginHistory;
import com.inkubator.hrm.service.LoginHistoryService;
import com.inkubator.hrm.web.lazymodel.LoginHistoryLazyModel;
import com.inkubator.hrm.web.search.LoginHistorySearchParameter;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author Deni Husni FR
 */
@ManagedBean(name = "loginHistoryController")
@ViewScoped
public class LoginHistoryController extends BaseController {

    @ManagedProperty(value = "#{loginHistoryService}")
    private LoginHistoryService loginHistoryService;
    private LoginHistorySearchParameter loginHistorySearchParameter;
    private LazyDataModel<LoginHistory> lazyDataLoginHistory;
    private PieChartModel pieModel;
    private Long totalLogin;
    private Long totalLoginTahun;
    private Long totalLoginBulan;
    private Long totalLogingMinggu;
    private Long totalLogingHari;

    public Long getTotalLogin() {
        try {
            totalLogin = this.loginHistoryService.getTotalLogin();
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
        return totalLogin;
    }

    public Long getTotalLoginTahun() {
        try {
            totalLoginTahun = this.loginHistoryService.getToalLoginThisYear();
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
        return totalLoginTahun;
    }

    public Long getTotalLoginBulan() {
        try {
            totalLoginBulan = this.loginHistoryService.getTotalLoginThisMonth();
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
        return totalLoginBulan;
    }

    public Long getTotalLogingMinggu() {
        try {
            totalLogingMinggu = this.loginHistoryService.getTotalLoginThisMonth();
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
        return totalLogingMinggu;
    }

    public Long getTotalLogingHari() {
        try {
            totalLogingHari = this.loginHistoryService.getTotalLoginThisDay();
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
        return totalLogingHari;
    }

    public void setLoginHistoryService(LoginHistoryService loginHistoryService) {
        this.loginHistoryService = loginHistoryService;
    }

    public LoginHistorySearchParameter getLoginHistorySearchParameter() {
        return loginHistorySearchParameter;
    }

    public void setLoginHistorySearchParameter(LoginHistorySearchParameter loginHistorySearchParameter) {
        this.loginHistorySearchParameter = loginHistorySearchParameter;
    }

    public LazyDataModel<LoginHistory> getLazyDataLoginHistory() {
        if (lazyDataLoginHistory == null) {
            System.out.println("nilai " + loginHistorySearchParameter);
            System.out.println("nilai service " + loginHistoryService);
            lazyDataLoginHistory = new LoginHistoryLazyModel(loginHistorySearchParameter, loginHistoryService);
        }
        return lazyDataLoginHistory;
    }

    public void setLazyDataLoginHistory(LazyDataModel<LoginHistory> lazyDataLoginHistory) {
        this.lazyDataLoginHistory = lazyDataLoginHistory;
    }

    public PieChartModel getPieModel() {
        return pieModel;
    }

    public void setPieModel(PieChartModel pieModel) {
        this.pieModel = pieModel;
    }

    @PostConstruct
    @Override
    public void initialization() {
        System.out.println("tereksekusiisis");
        bahasa = (String) FacesUtil.getSessionAttribute("bahasa_active");
        FacesUtil.getFacesContext().getViewRoot().setLocale(new Locale(bahasa));
        loginHistorySearchParameter = new LoginHistorySearchParameter();
        pieModel = new PieChartModel();
        if (FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString().equalsIgnoreCase("en")) {
            pieModel.set("Week 1", 540);
            pieModel.set("Week 2", 325);
            pieModel.set("Week 3", 702);
            pieModel.set("Week 4", 421);
            pieModel.set("Week 5", 400);
        } else {
            pieModel.set("Minggu 1", 540);
            pieModel.set("Minggu 2", 325);
            pieModel.set("Minggu 3", 702);
            pieModel.set("Minggu 4", 421);
            pieModel.set("Minggu 5", 400);
        }

    }

}
