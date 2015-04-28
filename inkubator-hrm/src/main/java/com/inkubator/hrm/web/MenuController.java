/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web;

import com.inkubator.hrm.service.HrmMenuService;
import com.inkubator.webcore.controller.BaseController;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import org.primefaces.model.menu.MenuModel;

/**
 *
 * @author deni.fahri
 */
@ManagedBean(name = "menuController")
@SessionScoped
public class MenuController extends BaseController {

    @ManagedProperty(value = "#{hrmMenuService}")
    private HrmMenuService hrmMenuService;
    private MenuModel menuModel;

    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            menuModel = hrmMenuService.getMenuHirarki();
        } catch (Exception ex) {
           LOGGER.error(ex, ex);
        }

    }

    public MenuModel getMenuModel() {
        return menuModel;
    }

    public void setMenuModel(MenuModel menuModel) {
        this.menuModel = menuModel;
    }

    public void setHrmMenuService(HrmMenuService hrmMenuService) {
        this.hrmMenuService = hrmMenuService;
    }
    
    
}
