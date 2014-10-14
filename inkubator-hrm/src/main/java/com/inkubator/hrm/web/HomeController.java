/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web;

import com.inkubator.hrm.entity.RiwayatAkses;
import com.inkubator.hrm.service.RiwayatAksesService;
import com.inkubator.securitycore.util.UserInfoUtil;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import java.util.Date;
import javax.annotation.PostConstruct;
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

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();

        /**
         * saving process of User Access History
         */
        StringBuffer urlPath = FacesUtil.getRequest().getRequestURL();
        System.out.println(" ini nilai url nya "+urlPath);
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
}
