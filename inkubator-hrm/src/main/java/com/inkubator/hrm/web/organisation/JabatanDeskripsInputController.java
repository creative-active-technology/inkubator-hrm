/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.organisation;

import com.inkubator.hrm.entity.Jabatan;
import com.inkubator.hrm.entity.JabatanDeskripsi;
import com.inkubator.hrm.service.JabatanService;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

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
//        options.put("closable", false);
//        options.put("height", "auto");

//        options.put("contentHeight", 340);
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
            System.out.println(" nilia user "+userId);
//          JabatanDeskripsi jabatanDeskripsi=(JabatanDeskripsi) event.getObject();
//            System.out.println(jabatanDeskripsi.getJabatan().getId());
//            userId = FacesUtil.getRequestParameter("execution");
            System.out.println(" niliadsfsdf "+userId.substring(1));
            selectedJabatan = jabatanService.getByIdWithJobDeskripsi(Long.parseLong(userId.substring(1)));
            jabatanDeskripsis = new ArrayList<>(selectedJabatan.getJabatanDeskripsis());
            super.onDialogReturn(event);
        } catch (Exception ex) {
           LOGGER.error("Error", ex);
        }
    }

}
