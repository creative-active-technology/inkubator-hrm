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
import com.inkubator.securitycore.util.UserInfoUtil;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Deni Husni FR
 */
@ManagedBean(name = "homeController")
@RequestScoped
public class HomeController extends BaseController {

    @ManagedProperty(value = "#{riwayatAksesService}")
    private RiwayatAksesService riwayatAksesService;
    @ManagedProperty(value = "#{hrmUserService}")
    private HrmUserService hrmUserService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();

        /**
         * saving process of User Access History
         */
//        System.out.println(" ip user "+HrmUserInfoUtil.isValidRemoteAddress());
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

        try {
            Boolean isValid = HrmUserInfoUtil.isValidRemoteAddress();
            HrmUser hrmUser = hrmUserService.getByUserId(HrmUserInfoUtil.getUserName());
            if (hrmUser.getEmpData() != null && isValid) {
                return "/protected/check_in_out.htm?faces-redirect=true";
            } else {
                MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.error", "ceckinout.error_employee",
                        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

            }

        } catch (Exception ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void setHrmUserService(HrmUserService hrmUserService) {
        this.hrmUserService = hrmUserService;
    }

}
