/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web;

import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Deni Husni FR
 */
@ManagedBean(name = "homeController")
@RequestScoped
public class HomeController extends BaseController {

    private String haha;

    public String getHaha() {
        return haha;
    }

    public void setHaha(String haha) {
        this.haha = haha;
    }

    @PostConstruct
    @Override
    public void initialization() {
        bahasa = (String) FacesUtil.getSessionAttribute("bahasa_active");
        FacesUtil.getFacesContext().getViewRoot().setLocale(new Locale(bahasa));
        
    }

}
