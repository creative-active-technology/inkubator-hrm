/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.organisation;

import com.inkubator.hrm.entity.EducationLevel;
import com.inkubator.hrm.entity.Faculty;
import com.inkubator.hrm.entity.Jabatan;
import com.inkubator.hrm.entity.JabatanDeskripsi;
import com.inkubator.hrm.entity.JabatanSpesifikasi;
import com.inkubator.hrm.entity.Major;
import com.inkubator.hrm.entity.OccupationType;
import com.inkubator.hrm.service.EducationLevelService;
import com.inkubator.hrm.service.FacultyService;
import com.inkubator.hrm.service.JabatanService;
import com.inkubator.hrm.service.MajorService;
import com.inkubator.hrm.service.OccupationTypeService;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.DualListModel;

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
    private List<JabatanDeskripsi> jabatanDeskripsis;

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
            selectedJabatan = jabatanService.getByIdWithJobDeskripsi(Long.parseLong(userId.substring(1)));
            jabatanDeskripsis = new ArrayList<>(selectedJabatan.getJabatanDeskripsis());
            listJabatanSpesifikasi = new ArrayList<>(selectedJabatan.getJabatanSpesifikasis());

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
        listJabatanSpesifikasi = null;
    }

    public List<JabatanSpesifikasi> getListJabatanSpesifikasi() {
        return listJabatanSpesifikasi;
    }

    public void setListJabatanSpesifikasi(List<JabatanSpesifikasi> listJabatanSpesifikasi) {
        this.listJabatanSpesifikasi = listJabatanSpesifikasi;
    }

    public List<JabatanDeskripsi> getJabatanDeskripsis() {
        return jabatanDeskripsis;
    }

    public void setJabatanDeskripsis(List<JabatanDeskripsi> jabatanDeskripsis) {
        this.jabatanDeskripsis = jabatanDeskripsis;
    }

    
    
}
