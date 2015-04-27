/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.HrmUser;
import com.inkubator.hrm.entity.RiwayatAkses;
import com.inkubator.hrm.service.HrmMenuService;
import com.inkubator.hrm.service.HrmUserService;
import com.inkubator.hrm.service.RiwayatAksesService;
import com.inkubator.hrm.util.HrmUserInfoUtil;
import com.inkubator.hrm.util.ResourceBundleUtil;
import com.inkubator.securitycore.util.UserInfoUtil;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.DynamicMenuModel;
import org.primefaces.model.menu.MenuModel;

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
//    @ManagedProperty(value = "#{hrmMenuService}")
//    private HrmMenuService hrmMenuService;
//    private MenuModel menuModel;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();

        /**
         * saving process of User Access History
         */
        StringBuffer urlPath = FacesUtil.getRequest().getRequestURL();
        RiwayatAkses akses = new RiwayatAkses();
        akses.setDateAccess(new Date());
        akses.setPathUrl(urlPath.toString());
        akses.setUserId(UserInfoUtil.getUserName());
        if (!FacesContext.getCurrentInstance().isPostback()) {
            RequestContext.getCurrentInstance().execute("bar.show()");
        }
        try {
            riwayatAksesService.doSaveAccess(akses);
//            menuModel = hrmMenuService.getMenuHirarki();
            System.out.println(" nsdfdsnfsdnfsdnfnsdfnsdfn " + UserInfoUtil.getRoles());
        } catch (Exception ex) {
            LOGGER.error("Error when saving User Access History", ex);
        }

//        DefaultSubMenu dsm = new DefaultSubMenu();
//        DefaultSubMenu dsm1 = new DefaultSubMenu();
//        DefaultMenuItem dmi = new DefaultMenuItem();
//        DefaultMenuItem dmi2 = new DefaultMenuItem(ResourceBundleUtil.getAsString("menu.user_view"));
//
////        dmi2.setValue(akses);
//        dmi.setIcon("ui-icon-home");
//        dmi.setUrl("/protected/home.htm");
//        dmi.setTitle("Back To Home");
//
//        dsm.setLabel(ResourceBundleUtil.getAsString("menu.system"));
//        dsm.setStyleClass("menu");
//        dsm.setIcon("img-user-account");
//        dsm.addElement(dsm1);
//        dsm1.setLabel(ResourceBundleUtil.getAsString("menu.account"));
//        dsm1.setStyleClass("menu_child");
//        dsm1.addElement(dmi2);
//        menuModel.addElement(dmi);
//        menuModel.addElement(dsm);
//        menuModel.addElement(dsm1);
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

//    public MenuModel getMenuModel() {
//        return menuModel;
//    }
//
//    public void setMenuModel(MenuModel menuModel) {
//        this.menuModel = menuModel;
//    }
//
//    public void setHrmMenuService(HrmMenuService hrmMenuService) {
//        this.hrmMenuService = hrmMenuService;
//    }

}
