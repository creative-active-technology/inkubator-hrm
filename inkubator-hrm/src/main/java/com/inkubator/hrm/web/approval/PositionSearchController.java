/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.approval;

import com.inkubator.hrm.entity.Jabatan;
import com.inkubator.hrm.service.JabatanService;
import com.inkubator.webcore.controller.BaseController;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Deni Husni FR
 */
@ManagedBean(name = "positionSearchController")
@ViewScoped
public class PositionSearchController extends BaseController {

    @ManagedProperty(value = "#{jabatanService}")
    private JabatanService jabatanService;
    private String jabatanName;
    private List<Jabatan> jabatanDataToShow = new ArrayList();

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();

    }

    public void doSearch() {
        try {
            jabatanDataToShow= new ArrayList();
            jabatanDataToShow = jabatanService.getByName(jabatanName);
        } catch (Exception ex) {
           LOGGER.error("Error", ex);
        }
    }

    public String getJabatanName() {
        return jabatanName;
    }

    public void setJabatanName(String jabatanName) {
        this.jabatanName = jabatanName;
    }

    public List<Jabatan> getJabatanDataToShow() {
        return jabatanDataToShow;
    }

    public void setJabatanDataToShow(List<Jabatan> jabatanDataToShow) {
        this.jabatanDataToShow = jabatanDataToShow;
    }
 

    public void setJabatanService(JabatanService jabatanService) {
        this.jabatanService = jabatanService;
    }

   
    
    public void doSelect(Jabatan jabatan){
        RequestContext.getCurrentInstance().closeDialog(jabatan);
    }
    
    
}
