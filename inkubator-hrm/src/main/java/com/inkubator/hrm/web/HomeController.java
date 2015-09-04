/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.HrmUser;
import com.inkubator.hrm.entity.RiwayatAkses;
import com.inkubator.hrm.service.HrmUserService;
import com.inkubator.hrm.service.RiwayatAksesService;
import com.inkubator.hrm.util.HrmUserInfoUtil;
import com.inkubator.hrm.util.StringsUtils;
import com.inkubator.securitycore.util.UserInfoUtil;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

import java.io.IOException;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Deni Husni FR
 */
@ManagedBean(name = "homeController")
@ViewScoped
public class HomeController extends BaseController {

    @ManagedProperty(value = "#{riwayatAksesService}")
    private RiwayatAksesService riwayatAksesService;
    @ManagedProperty(value = "#{hrmUserService}")
    private HrmUserService hrmUserService;
    private String roleUser;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();

        /**
         * saving process of User Access History
         */
        roleUser = HrmUserInfoUtil.getRolesString();
        roleUser = StringsUtils.substringBefore(roleUser, ",");
        StringBuffer urlPath = FacesUtil.getRequest().getRequestURL();
        RiwayatAkses akses = new RiwayatAkses();
        akses.setDateAccess(new Date());
        akses.setPathUrl(urlPath.toString());
        akses.setUserId(UserInfoUtil.getUserName());
        try {
            riwayatAksesService.doSaveAccess(akses);
        } catch (Exception ex) {
            LOGGER.error("Error when saving User Access History", ex);
        }

    }

    public void setRiwayatAksesService(RiwayatAksesService riwayatAksesService) {
        this.riwayatAksesService = riwayatAksesService;
    }

    public String doCheckInOut() {
//
        try {
            Boolean isValid = HrmUserInfoUtil.isValidRemoteAddress();
            LOGGER.info("Begin redirecting");
            LOGGER.info("Kondisi" + isValid);
            HrmUser hrmUser = hrmUserService.getByUserId(HrmUserInfoUtil.getUserName());
            if (hrmUser.getEmpData() != null && isValid) {
                LOGGER.info("redirec ok");
                return "/protected/check_in_out.htm?faces-redirect=true";
            } else {
                MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.error", "ceckinout.error_employee",
                        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

            }
//
        } catch (Exception ex) {
            LOGGER.error(ex, ex);
        }
        return null;
    }

    public void setHrmUserService(HrmUserService hrmUserService) {
        this.hrmUserService = hrmUserService;
    }

    public String doChangeLanguageSession() {
        try {
            String languange = FacesUtil.getRequestParameter("languange");
            FacesUtil.setSessionAttribute(HRMConstant.BAHASA_ACTIVE, languange);
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
        } catch (Exception ex) {
            LOGGER.error(ex, ex);
        }

        return null;
    }

    public String getRoleUser() {
        return roleUser;
    }

    public void setRoleUser(String roleUser) {
        this.roleUser = roleUser;
    }

}
