/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.organisation;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.Jabatan;
import com.inkubator.hrm.service.JabatanService;
import com.inkubator.hrm.web.lazymodel.JabatanLazyDataModel;
import com.inkubator.hrm.web.search.JabatanSearchParameter;
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

    public void doDetail() {
        try {
            selectedJabatan = jabatanService.getEntiyByPK(selectedJabatan.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
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

    public void doAdd() {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 400);
        options.put("contentHeight", 360);

        RequestContext.getCurrentInstance().openDialog("holiday_form", options, null);
    }

    public void doEdit() {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 400);
        options.put("contentHeight", 360);
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> dataIsi = new ArrayList<>();
        dataIsi.add(String.valueOf(selectedJabatan.getId()));
        dataToSend.put("param", dataIsi);
        RequestContext.getCurrentInstance().openDialog("holiday_form", options, dataToSend);
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

}
