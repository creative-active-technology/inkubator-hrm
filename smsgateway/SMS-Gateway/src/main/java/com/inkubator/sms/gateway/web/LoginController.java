/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.sms.gateway.web;

import com.inkubator.common.util.DateFormatter;
import com.inkubator.sms.gateway.SMSGATEWAY;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import java.io.IOException;
import java.util.Date;
import java.util.Locale;
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
    private String selectedLanguage;
    @ManagedProperty(value = "#{dateFormatter}")
    private DateFormatter dateFormatter;

//    public String doLogin() {
//        System.out.println(" hahahahah");
//        return "/protected/home.htm?faces-redirect=true";
//    }
    @PostConstruct
    @Override
    public void initialization() {
        selectedLanguage = "in";
        FacesUtil.setSessionAttribute(SMSGATEWAY.BAHASA_ACTIVE, selectedLanguage);
        FacesUtil.getFacesContext().getViewRoot().setLocale(new Locale(selectedLanguage));
        System.out.println(" Hehrherhehreh");
    }

    public String doLogin() {
        System.out.println(" do login");
        ExternalContext context = FacesUtil.getExternalContext();
        RequestDispatcher dispatcher = ((ServletRequest) context.getRequest())
                .getRequestDispatcher("/" + SMSGATEWAY.SPRING_SECURITY_CHECK);
        try {
            dispatcher.forward((ServletRequest) context.getRequest(), (ServletResponse) context.getResponse());
        } catch (ServletException | IOException ex) {
            LOGGER.error("Error", ex);
        }
        FacesUtil.setSessionAttribute(SMSGATEWAY.LOGIN_DATE, dateFormatter.getDateFullAsStringsWithActiveLocale(new Date(),
                new Locale(selectedLanguage)));

        FacesUtil.getFacesContext().responseComplete();
        return null;
    }

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

    public void setDateFormatter(DateFormatter dateFormatter) {
        this.dateFormatter = dateFormatter;
    }

}
