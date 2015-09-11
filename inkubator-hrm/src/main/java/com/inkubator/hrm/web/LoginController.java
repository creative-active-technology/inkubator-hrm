/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web;

import com.inkubator.common.util.DateFormatter;
import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.HrmUser;
import com.inkubator.hrm.service.HrmUserService;
import com.inkubator.hrm.util.ResourceBundleUtil;
import com.inkubator.hrm.util.StringsUtils;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.io.IOException;
import java.util.Date;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.primefaces.context.RequestContext;
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.DeviceResolver;

/**
 *
 * @author Deni Husni FR
 */
@ManagedBean(name = "loginController")
@RequestScoped
public class LoginController extends BaseController {

    private String userId;
    private String password;
    private String emailAddress;
    private String selectedLanguage;
    @ManagedProperty(value = "#{dateFormatter}")
    private DateFormatter dateFormatter;
    @ManagedProperty(value = "#{hrmUserService}")
    private HrmUserService userService;
    @ManagedProperty(value = "#{deviceResolver}")
    private DeviceResolver deviceResolver;
    private String info;

    @PostConstruct
    @Override
    public void initialization() {
        Device device = deviceResolver.resolveDevice(FacesUtil.getRequest());

        if (device.isMobile()) {
            try {
                LOGGER.info("Mobile");
                FacesUtil.getExternalContext().redirect("/MOBILE-HR");
            } catch (IOException ex) {
                LOGGER.error(ex, ex);
            }
        }
        if (device.isNormal()) {
            LOGGER.info("NOrmal Desktop");
            if (FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE) == null) {
                selectedLanguage = "in";
                FacesUtil.setSessionAttribute(HRMConstant.BAHASA_ACTIVE, selectedLanguage);
            } else {
                selectedLanguage = (String) FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE);
            }
            FacesUtil.getFacesContext().getViewRoot().setLocale(new Locale(selectedLanguage));
        }
        if (device.isTablet()) {
            LOGGER.info("TABLET");
        }

        String userAgent = FacesUtil.getRequest().getHeader("User-Agent");

        LOGGER.info("Data " + userAgent);
//        if (StringsUtils.isContain(userAgent, "Chrome") || StringsUtils.isContain(userAgent, "Firefox")) {
//            info = ResourceBundleUtil.getAsString("browser.info");
//        }
        if (StringsUtils.isContain(userAgent, "Edge")) {
            info = ResourceBundleUtil.getAsString("browser.info_invalid");
        }

    }

    @PreDestroy
    public void cleanAndExit() {
        userId = null;
        password = null;
        emailAddress = null;
        selectedLanguage = null;
        dateFormatter = null;
        userService = null;
    }

    public void setDeviceResolver(DeviceResolver deviceResolver) {
        this.deviceResolver = deviceResolver;
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

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getSelectedLanguage() {
        return selectedLanguage;
    }

    public void setSelectedLanguage(String selectedLanguage) {
        this.selectedLanguage = selectedLanguage;
    }

    public void setDateFormatter(DateFormatter dateFormatter) {
        this.dateFormatter = dateFormatter;
    }

    public void setUserService(HrmUserService userService) {
        this.userService = userService;
    }

    public void doChageLanguange() {
        FacesUtil.setSessionAttribute(HRMConstant.BAHASA_ACTIVE, selectedLanguage);
        String bahasa1 = (String) FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE);
        FacesUtil.getFacesContext().getViewRoot().setLocale(new Locale(bahasa1));
        String userAgent = FacesUtil.getRequest().getHeader("User-Agent");
        if (StringsUtils.isContain(userAgent, "Edge")) {
            info = ResourceBundleUtil.getAsString("browser.info_invalid");
        }
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
                new Locale(selectedLanguage)));

        FacesUtil.getFacesContext().responseComplete();
        return null;
    }

    public void doResetPassword() {
        RequestContext context = FacesUtil.getRequestContext();
        Boolean emailIsExist = Boolean.FALSE;
        try {
            HrmUser user = userService.getByEmailAddressInNotLock(emailAddress);
            if (user == null) {
                MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.error", "error.email_not_registered",
                        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            } else {
                emailIsExist = Boolean.TRUE;
                user.setPassword("Inkuba" + RandomNumberUtil.getRandomNumber(7));
                userService.resetPassword(user);
                MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.reset_successful", "global.please_check_your_email",
                        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

            }

        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
        context.addCallbackParam("emailIsExist", emailIsExist);
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

}
