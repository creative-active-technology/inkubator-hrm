/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.organisation;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.Jabatan;
import com.inkubator.hrm.entity.JabatanDeskripsi;
import com.inkubator.hrm.service.JabatanDeskripsiService;
import com.inkubator.hrm.service.JabatanService;
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
import org.springframework.dao.DataIntegrityViolationException;

/**
 *
 * @author Deni Husni FR
 */
@ManagedBean(name = "jabatanDeskripsInputController")
@ViewScoped
public class JabatanDeskripsInputController extends BaseController {

    private Jabatan selectedJabatan;
    @ManagedProperty(value = "#{jabatanService}")
    private JabatanService jabatanService;
    @ManagedProperty(value = "#{jabatanDeskripsiService}")
    private JabatanDeskripsiService jabatanDeskripsiService;
    private List<JabatanDeskripsi> jabatanDeskripsis;
    private JabatanDeskripsi selectedJabatanDeskripsi;
    private String userId;

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
            userId = FacesUtil.getRequestParameter("execution");
            selectedJabatan = jabatanService.getByIdWithJobDeskripsi(Long.parseLong(userId.substring(1)));
            jabatanDeskripsis = new ArrayList<>(selectedJabatan.getJabatanDeskripsis());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);

        }
    }

    public String doBack() {
        return "/protected/organisation/job_description_view.htm?faces-redirect=true";
    }

    @PreDestroy
    public void cleanAndExit() {
        selectedJabatan = null;
        jabatanService = null;
        jabatanDeskripsiService=null;
        jabatanDeskripsis=null;
        selectedJabatanDeskripsi=null;
        userId=null;
        
    }

    public List<JabatanDeskripsi> getJabatanDeskripsis() {
        return jabatanDeskripsis;
    }

    public void setJabatanDeskripsis(List<JabatanDeskripsi> jabatanDeskripsis) {
        this.jabatanDeskripsis = jabatanDeskripsis;
    }

    public void doAdd() {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", false);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 400);
        options.put("contentHeight", 360);
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> dataIsi = new ArrayList<>();
        dataIsi.add("i" + String.valueOf(selectedJabatan.getId()));
        dataToSend.put("param", dataIsi);
        RequestContext.getCurrentInstance().openDialog("job_description_form", options, dataToSend);
    }

    public void doEdit() {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", false);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 400);
        options.put("contentHeight", 360);
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> dataIsi = new ArrayList<>();
        dataIsi.add("e" + String.valueOf(selectedJabatanDeskripsi.getId()));
        dataToSend.put("param", dataIsi);
        RequestContext.getCurrentInstance().openDialog("job_description_form", options, dataToSend);
    }

    public JabatanDeskripsi getSelectedJabatanDeskripsi() {
        return selectedJabatanDeskripsi;
    }

    public void setSelectedJabatanDeskripsi(JabatanDeskripsi selectedJabatanDeskripsi) {
        this.selectedJabatanDeskripsi = selectedJabatanDeskripsi;
    }

    @Override
    public void onDialogReturn(SelectEvent event) {
        try {
            selectedJabatan = jabatanService.getByIdWithJobDeskripsi(Long.parseLong(userId.substring(1)));
            jabatanDeskripsis = new ArrayList<>(selectedJabatan.getJabatanDeskripsis());
            super.onDialogReturn(event);
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public void doDelete() {
        try {
            jabatanDeskripsiService.delete(selectedJabatanDeskripsi);
            selectedJabatan = jabatanService.getByIdWithJobDeskripsi(Long.parseLong(userId.substring(1)));
            jabatanDeskripsis = new ArrayList<>(selectedJabatan.getJabatanDeskripsis());
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
//            LOGGER.error("Error", ex);
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public void onDelete() {
        try {
            selectedJabatanDeskripsi = jabatanDeskripsiService.getEntiyByPK(selectedJabatanDeskripsi.getId());

        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public void setJabatanDeskripsiService(JabatanDeskripsiService jabatanDeskripsiService) {
        this.jabatanDeskripsiService = jabatanDeskripsiService;
    }

}
