/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.organisation;

import com.inkubator.hrm.service.JabatanDeskripsiService;
import com.inkubator.hrm.web.model.JabatanDeskripsiModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Deni Husni FR
 */
@ManagedBean(name = "jabatanDeskripsiFormController")
@ViewScoped
public class JabatanDeskripsiFormController extends BaseController {

    private JabatanDeskripsiModel jabatanDeskripsiModel;
    @ManagedProperty(value = "#{jabatanDeskripsiService}")
    private JabatanDeskripsiService jabatanDeskripsiService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        String param = FacesUtil.getRequestParameter("param");
        System.out.println(" nilai " + param);
        jabatanDeskripsiModel = new JabatanDeskripsiModel();

    }

    public JabatanDeskripsiModel getJabatanDeskripsiModel() {
        return jabatanDeskripsiModel;
    }

    public void setJabatanDeskripsiModel(JabatanDeskripsiModel jabatanDeskripsiModel) {
        this.jabatanDeskripsiModel = jabatanDeskripsiModel;
    }

    public void setJabatanDeskripsiService(JabatanDeskripsiService jabatanDeskripsiService) {
        this.jabatanDeskripsiService = jabatanDeskripsiService;
    }

    public void doSave() {

    }

}
