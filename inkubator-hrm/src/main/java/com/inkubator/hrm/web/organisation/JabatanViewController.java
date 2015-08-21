/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.organisation;

import java.io.IOException;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.Jabatan;
import com.inkubator.hrm.service.JabatanService;
import com.inkubator.hrm.web.lazymodel.JabatanLazyDataModel;
import com.inkubator.hrm.web.search.JabatanSearchParameter;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;

import org.hibernate.exception.ConstraintViolationException;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.springframework.dao.DataIntegrityViolationException;

/**
 *
 * @author Deni Husni FR
 */
@ManagedBean(name = "jabatanViewController")
@ViewScoped
public class JabatanViewController extends BaseController {

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

    public void setJabatanService(JabatanService jabatanService) {
        this.jabatanService = jabatanService;
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
        return "/protected/organisation/job_title_detil.htm?faces-redirect=true&execution=e" + selectedJabatan.getId();
    }

    public void doAdd() {
    	System.out.println("Masuk doAdd");
        //return "/protected/organisation/job_title_form.htm?faces-redirect=true";
    	//return "/flow-protected/job";
    	 try {
             ExternalContext red = FacesUtil.getExternalContext();
             red.redirect(red.getRequestContextPath() + "/flow-protected/job");
         } catch (IOException ex) {
           LOGGER.error("Erorr", ex);
         }
    }

    public String doEdit() {
        return "/protected/organisation/job_title_form.htm?faces-redirect=true&execution=e" + selectedJabatan.getId();
    }

    public void doDelete() {
        try {
            this.jabatanService.delete(selectedJabatan);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            LOGGER.error("Error", ex);
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    @Override
    public void onDialogReturn(SelectEvent event) {
        lazyJabatanDataModel = null;
        super.onDialogReturn(event);
    }

    public void onDelete() {
        try {
            selectedJabatan = jabatanService.getEntiyByPK(selectedJabatan.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public void doChangeYear() {
        lazyJabatanDataModel = null;
    }

    public void doChangeMonth() {
        lazyJabatanDataModel = null;
    }

    @PreDestroy
    private void cleanAndExit() {
        jabatanSearchParameter = null;
        jabatanService = null;
        lazyJabatanDataModel = null;
        selectedJabatan = null;
    }

    public Jabatan getSelectedJabatan() {
        return selectedJabatan;
    }

    public void setSelectedJabatan(Jabatan selectedJabatan) {
        this.selectedJabatan = selectedJabatan;
    }

    public String doViewShema() {
        return "/protected/organisation/job_title_diagram.htm?faces-redirect=true";
    }
}
