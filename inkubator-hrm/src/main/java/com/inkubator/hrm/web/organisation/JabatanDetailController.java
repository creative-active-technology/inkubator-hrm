/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.organisation;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.Jabatan;
import com.inkubator.hrm.entity.JabatanDeskripsi;
import com.inkubator.hrm.entity.JabatanSpesifikasi;
import com.inkubator.hrm.entity.OrgTypeOfSpecJabatan;
import com.inkubator.hrm.entity.OrgTypeOfSpecJabatanId;
import com.inkubator.hrm.service.JabatanService;
import com.inkubator.hrm.service.OrgTypeOfSpecJabatanService;
import com.inkubator.hrm.web.lazymodel.OrgTypeOfSpecJabatanLazyDataModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.hibernate.exception.ConstraintViolationException;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.springframework.dao.DataIntegrityViolationException;

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
    @ManagedProperty(value = "#{orgTypeOfSpecJabatanService}")
    private OrgTypeOfSpecJabatanService orgTypeOfSpecJabatanService;
    private List<JabatanSpesifikasi> listJabatanSpesifikasi;
    private List<JabatanDeskripsi> jabatanDeskripsis;
    private LazyDataModel<OrgTypeOfSpecJabatan> lazyDataModel;
    private OrgTypeOfSpecJabatan selectedOrgTypeSpecJabatan;

    public OrgTypeOfSpecJabatan getSelectedOrgTypeSpecJabatan() {
        return selectedOrgTypeSpecJabatan;
    }

    public void setSelectedOrgTypeSpecJabatan(OrgTypeOfSpecJabatan selectedOrgTypeSpecJabatan) {
        this.selectedOrgTypeSpecJabatan = selectedOrgTypeSpecJabatan;
    }
    
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
            String jabatanId = FacesUtil.getRequestParameter("execution");
            selectedJabatan = jabatanService.getByIdWithJobDeskripsi(Long.parseLong(jabatanId.substring(1)));
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
        selectedOrgTypeSpecJabatan = null;
        selectedJabatan = null;
        jabatanService = null;
        orgTypeOfSpecJabatanService = null;
        lazyDataModel = null;
        listJabatanSpesifikasi = null;
        jabatanDeskripsis = null;
    }

    public void showDialog(Map<String, List<String>> params){
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 700);
        options.put("contentHeight", 500);
        RequestContext.getCurrentInstance().openDialog("org_jab_spec_form", options, params);
    }
    
    public void doAdd() {
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> dataIsi = new ArrayList<>();
        dataIsi.add(String.valueOf(selectedJabatan.getId()));
        dataToSend.put("jabatanId", dataIsi);
        showDialog(dataToSend);
    }
    
    @Override
    public void onDialogReturn(SelectEvent event) {
        lazyDataModel = null;
        super.onDialogReturn(event);
    }

    public void doSelectEntity() {
        try {
            selectedOrgTypeSpecJabatan = this.orgTypeOfSpecJabatanService.getEntityByPK(new OrgTypeOfSpecJabatanId(selectedOrgTypeSpecJabatan.getOrgTypeOfSpecList().getId(), selectedOrgTypeSpecJabatan.getJabatan().getId()));
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }
    
    public void doDelete() {
        try {
            this.orgTypeOfSpecJabatanService.delete(selectedOrgTypeSpecJabatan);
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

    public LazyDataModel<OrgTypeOfSpecJabatan> getLazyDataModel() {
        if (lazyDataModel == null) {
            lazyDataModel = new OrgTypeOfSpecJabatanLazyDataModel(selectedJabatan.getId(), orgTypeOfSpecJabatanService);
        }
        return lazyDataModel;
    }

    public void setLazyDataModel(LazyDataModel<OrgTypeOfSpecJabatan> lazyDataModel) {
        this.lazyDataModel = lazyDataModel;
    }

    public OrgTypeOfSpecJabatanService getOrgTypeOfSpecJabatanService() {
        return orgTypeOfSpecJabatanService;
    }

    public void setOrgTypeOfSpecJabatanService(OrgTypeOfSpecJabatanService orgTypeOfSpecJabatanService) {
        this.orgTypeOfSpecJabatanService = orgTypeOfSpecJabatanService;
    }

}
