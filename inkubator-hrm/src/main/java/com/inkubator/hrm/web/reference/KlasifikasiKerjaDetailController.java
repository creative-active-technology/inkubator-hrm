/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.reference;

import com.inkubator.hrm.entity.GolonganJabatan;
import com.inkubator.hrm.entity.KlasifikasiKerja;
import com.inkubator.hrm.service.KlasifikasiKerjaService;
import com.inkubator.hrm.web.model.KlasifikasiKerjaModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import java.util.List;
import java.util.logging.Level;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.primefaces.model.DualListModel;

/**
 *
 * @author Deni
 */
@ManagedBean(name = "klasifikasiKerjaDetailController")
@ViewScoped
public class KlasifikasiKerjaDetailController extends BaseController {

    @ManagedProperty(value = "#{klasifikasiKerjaService}")
    private KlasifikasiKerjaService klasifikasiKerjaService;
    private KlasifikasiKerja selectedKlasifikasiKerja;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        String param = FacesUtil.getRequestParameter("execution");

        try {
            selectedKlasifikasiKerja = klasifikasiKerjaService.getEntityByPkWithDetail(Long.parseLong(param.substring(1)));
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(KlasifikasiKerjaDetailController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @PreDestroy
    public void cleanAndExit() {
        klasifikasiKerjaService = null;
        selectedKlasifikasiKerja = null;
    }
    
    public String doBack() {
        return "/protected/reference/job_family_view.htm?faces-redirect=true";
    }
    
    public String doEdit() {
    	return "/protected/reference/job_family_form.htm?faces-redirect=true&execution=e" + selectedKlasifikasiKerja.getId();
    }

    public KlasifikasiKerjaService getKlasifikasiKerjaService() {
        return klasifikasiKerjaService;
    }

    public void setKlasifikasiKerjaService(KlasifikasiKerjaService klasifikasiKerjaService) {
        this.klasifikasiKerjaService = klasifikasiKerjaService;
    }

    public KlasifikasiKerja getSelectedKlasifikasiKerja() {
        return selectedKlasifikasiKerja;
    }

    public void setSelectedKlasifikasiKerja(KlasifikasiKerja selectedKlasifikasiKerja) {
        this.selectedKlasifikasiKerja = selectedKlasifikasiKerja;
    }
    
    
}
