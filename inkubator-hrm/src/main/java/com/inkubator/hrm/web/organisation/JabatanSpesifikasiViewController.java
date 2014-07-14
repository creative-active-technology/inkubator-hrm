/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.organisation;

import com.inkubator.hrm.entity.Jabatan;
import com.inkubator.hrm.service.JabatanService;
import com.inkubator.hrm.web.lazymodel.JabatanLazyDataModel;
import com.inkubator.hrm.web.search.JabatanSearchParameter;
import com.inkubator.webcore.controller.BaseController;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author Deni
 */
@ManagedBean(name = "jabatanSpesifikasiViewController")
@ViewScoped
public class JabatanSpesifikasiViewController extends BaseController{
    private JabatanSearchParameter jabatanSearchParameter;
    private LazyDataModel<Jabatan> lazyJabatanDataModel;
    @ManagedProperty(value = "#{jabatanService}")
    private JabatanService jabatanService;
    private Jabatan selectedJabatan;

    public JabatanSearchParameter getJabatanSearchParameter() {
        return jabatanSearchParameter;
    }

    public void setJabatanSearchParameter(JabatanSearchParameter jabatanSearchParameter) {
        this.jabatanSearchParameter = jabatanSearchParameter;
    }

    public LazyDataModel<Jabatan> getLazyJabatanDataModel() {
        if (lazyJabatanDataModel == null) {
            lazyJabatanDataModel = new JabatanLazyDataModel(jabatanSearchParameter, jabatanService);
        }
        return lazyJabatanDataModel;
    }

    public void setLazyJabatanDataModel(LazyDataModel<Jabatan> lazyJabatanDataModel) {
        this.lazyJabatanDataModel = lazyJabatanDataModel;
    }

    public JabatanService getJabatanService() {
        return jabatanService;
    }

    public void setJabatanService(JabatanService jabatanService) {
        this.jabatanService = jabatanService;
    }

    public Jabatan getSelectedJabatan() {
        return selectedJabatan;
    }

    public void setSelectedJabatan(Jabatan selectedJabatan) {
        this.selectedJabatan = selectedJabatan;
    }
    
    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        jabatanSearchParameter = new JabatanSearchParameter();

    }
    
    public void doSearch() {
        lazyJabatanDataModel = null;
    }

    public String doDetail() {
        return "/protected/organisation/job_spesifikasi_detail.htm?faces-redirect=true&execution=e" + selectedJabatan.getId();
    }

    public String doAdd() {
        return "/protected/organisation/job_spesifikasi_form.htm?faces-redirect=true";
    }

    public String doEdit() {
        return "/protected/organisation/job_spesifikasi_form.htm?faces-redirect=true&execution=e" + selectedJabatan.getId();
    }

    @PreDestroy
    private void cleanAndExit() {

    }

    public String doViewShema() {
        return "/protected/organisation/job_title_diagram.htm?faces-redirect=true";
    }
    
}
