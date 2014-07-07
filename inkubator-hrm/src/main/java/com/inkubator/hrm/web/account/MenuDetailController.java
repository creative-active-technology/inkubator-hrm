/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.account;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.inkubator.hrm.entity.HrmMenu;
import com.inkubator.hrm.service.HrmMenuService;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "menuDetailController")
@ViewScoped
public class MenuDetailController extends BaseController {

    private HrmMenu selectedMenu;
    @ManagedProperty(value = "#{hrmMenuService}")
    private HrmMenuService hrmMenuService;

    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            String id = FacesUtil.getRequestParameter("execution");
            selectedMenu = hrmMenuService.getEntiyByPK(Long.parseLong(id.substring(1)));
        } catch (Exception ex) {
            LOGGER.error("Error", ex);

        }
    }

    @PreDestroy
    public void cleanAndExit() {
        selectedMenu = null;
        hrmMenuService = null;
    }

    public HrmMenu getSelectedMenu() {
		return selectedMenu;
	}

	public void setSelectedMenu(HrmMenu selectedMenu) {
		this.selectedMenu = selectedMenu;
	}

	public void setHrmMenuService(HrmMenuService hrmMenuService) {
		this.hrmMenuService = hrmMenuService;
	}

	public String doBack() {
        return "/protected/account/menu_view.htm?faces-redirect=true";
    }

    public String doUpdate() {
        return "/protected/account/menu_form.htm?faces-redirect=true&execution=e" + selectedMenu.getId();
    }

}
