/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.organisation;

import com.inkubator.hrm.entity.Jabatan;
import com.inkubator.hrm.entity.JabatanSpesifikasi;
import com.inkubator.hrm.service.JabatanService;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Deni Husni FR
 */
@ManagedBean(name = "jabatanDetailController")
@ViewScoped
public class JabatanDetailController extends BaseController {

    private Jabatan selectedJabatan;
    @ManagedProperty(value = "#{jabatanService}")
    private JabatanService jabatanService;
    private List<JabatanSpesifikasi> listJabatanSpesifikasi;

    public Jabatan getSelectedJabatan() {
        return selectedJabatan;
    }

    public void setSelectedJabatan(Jabatan selectedJabatan) {
        this.selectedJabatan = selectedJabatan;
    }

    public void setJabatanService(JabatanService jabatanService) {
        this.jabatanService = jabatanService;
    }

    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            String userId = FacesUtil.getRequestParameter("execution");
            selectedJabatan = jabatanService.getJabatanByIdWithDetail(Long.parseLong(userId.substring(1)));
            
        } catch (Exception ex) {
            LOGGER.error("Error", ex);

        }
    }

    public String doBack() {
        return "/protected/organisation/job_title_view.htm?faces-redirect=true";
    }

    public String doEdit() {
        return "/protected/organisation/job_title_form.htm?faces-redirect=true&execution=e" + selectedJabatan.getId();
    }

    @PreDestroy
    public void cleanAndExit() {
        selectedJabatan = null;
        jabatanService = null;
    }

    public List<JabatanSpesifikasi> getListJabatanSpesifikasi() {
        return listJabatanSpesifikasi;
    }

    public void setListJabatanSpesifikasi(List<JabatanSpesifikasi> listJabatanSpesifikasi) {
        this.listJabatanSpesifikasi = listJabatanSpesifikasi;
    }
    
    
}
