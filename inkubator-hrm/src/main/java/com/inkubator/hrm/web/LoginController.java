/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web;

import com.inkubator.common.util.DateFormatter;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 *
 * @author Deni Husni FR
 */
@ManagedBean(name = "loginController")
@RequestScoped
public class LoginController extends BaseController {

    private String userId;
    private String password;
    private String bahasaTerpilih;
    private Map<String, String> pilihanBahasa = new HashMap<>();
    @ManagedProperty(value = "#{dateFormatter}")
    private DateFormatter dateFormatter;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBahasaTerpilih() {
        return bahasaTerpilih;
    }

    public void setBahasaTerpilih(String bahasaTerpilih) {
        this.bahasaTerpilih = bahasaTerpilih;
    }

    public Map<String, String> getPilihanBahasa() {
        return pilihanBahasa;
    }

    public void setPilihanBahasa(Map<String, String> pilihanBahasa) {
        this.pilihanBahasa = pilihanBahasa;
    }

    public void setDateFormatter(DateFormatter dateFormatter) {
        this.dateFormatter = dateFormatter;
    }

    @PostConstruct
    @Override
    public void initialization() {
        if (FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE) == null) {
            bahasaTerpilih = "in";
            FacesUtil.setSessionAttribute(HRMConstant.BAHASA_ACTIVE, bahasaTerpilih);
        } else {
            bahasaTerpilih = (String) FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE);
        }
        FacesUtil.getFacesContext().getViewRoot().setLocale(new Locale(bahasaTerpilih));
    }

    public void doChageLanguange() {
        FacesUtil.setSessionAttribute(HRMConstant.BAHASA_ACTIVE, bahasaTerpilih);
        String bahasa1 = (String) FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE);
        FacesUtil.getFacesContext().getViewRoot().setLocale(new Locale(bahasa1));
    }

    public String doLogin() {
        ExternalContext context = FacesUtil.getExternalContext();
        RequestDispatcher dispatcher = ((ServletRequest) context.getRequest())
                .getRequestDispatcher("/" + HRMConstant.SPRING_SECURITY_CHECK);
        try {
            dispatcher.forward((ServletRequest) context.getRequest(), (ServletResponse) context.getResponse());
        } catch (ServletException | IOException ex) {
            LOGGER.error("Error", ex);
        }
        FacesUtil.setSessionAttribute(HRMConstant.LOGIN_DATE, dateFormatter.getDateFullAsStringsWithActiveLocale(new Date(),
                new Locale(bahasaTerpilih)));
       
        FacesUtil.getFacesContext().responseComplete();
        return null;
    }
}
