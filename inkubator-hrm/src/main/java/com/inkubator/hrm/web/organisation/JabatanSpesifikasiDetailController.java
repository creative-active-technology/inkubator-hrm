/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.organisation;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.Jabatan;
import com.inkubator.hrm.entity.JabatanSpesifikasi;
import com.inkubator.hrm.service.JabatanService;
import com.inkubator.hrm.service.JabatanSpesifikasiService;
import com.inkubator.hrm.web.lazymodel.JabatanSpesifikasiLazyDataModel;
import com.inkubator.hrm.web.search.JabatanSpesifikasiSearchParameter;
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
 * @author Deni
 */
@ManagedBean(name = "jabatanSpesifikasiDetailController")
@ViewScoped
public class JabatanSpesifikasiDetailController extends BaseController{
    private Jabatan selectedJabatan;
    private JabatanSpesifikasi selectedJobSpec;
    @ManagedProperty(value = "#{jabatanService}")
    private JabatanService jabatanService;
    @ManagedProperty(value = "#{jabatanSpesifikasiService}")
    private JabatanSpesifikasiService jabatanSpecService;
    private List<JabatanSpesifikasi> listJabatanSpesifikasi;
    private LazyDataModel<JabatanSpesifikasi> lazyDataJabatanSpesifikasi;
    private JabatanSpesifikasiSearchParameter jabatanSpesifikasiSearchParameter;

    public JabatanSpesifikasiService getJabatanSpecService() {
        return jabatanSpecService;
    }

    public void setJabatanSpecService(JabatanSpesifikasiService jabatanSpecService) {
        this.jabatanSpecService = jabatanSpecService;
    }

    
    public JabatanSpesifikasi getSelectedJobSpec() {
        return selectedJobSpec;
    }

    public void setSelectedJobSpec(JabatanSpesifikasi selectedJobSpec) {
        this.selectedJobSpec = selectedJobSpec;
    }

    public JabatanSpesifikasiSearchParameter getJabatanSpesifikasiSearchParameter() {
        return jabatanSpesifikasiSearchParameter;
    }

    public void setJabatanSpesifikasiSearchParameter(JabatanSpesifikasiSearchParameter jabatanSpesifikasiSearchParameter) {
        this.jabatanSpesifikasiSearchParameter = jabatanSpesifikasiSearchParameter;
    }

    
    public LazyDataModel<JabatanSpesifikasi> getLazyDataJabatanSpesifikasi() {
        if(lazyDataJabatanSpesifikasi == null){
            lazyDataJabatanSpesifikasi = new JabatanSpesifikasiLazyDataModel(jabatanSpesifikasiSearchParameter, jabatanSpecService, selectedJabatan.getId());
        }
        return lazyDataJabatanSpesifikasi;
    }

    public void setLazyDataJabatanSpesifikasi(LazyDataModel<JabatanSpesifikasi> lazyDataJabatanSpesifikasi) {
        this.lazyDataJabatanSpesifikasi = lazyDataJabatanSpesifikasi;
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

    public List<JabatanSpesifikasi> getListJabatanSpesifikasi() {
        return listJabatanSpesifikasi;
    }

    public void setListJabatanSpesifikasi(List<JabatanSpesifikasi> listJabatanSpesifikasi) {
        this.listJabatanSpesifikasi = listJabatanSpesifikasi;
    }
    
    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            String userId = FacesUtil.getRequestParameter("execution");
            selectedJabatan = jabatanService.getJabatanByIdWithDetail(Long.parseLong(userId.substring(1)));
            jabatanSpesifikasiSearchParameter = new JabatanSpesifikasiSearchParameter();
        } catch (Exception ex) {
            LOGGER.error("Error", ex);

        }
    }

    public String doBack() {
        return "/protected/organisation/job_spesifikasi_view.htm?faces-redirect=true";
    }

    
    public void doAdd() throws Exception{
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 400);
        options.put("contentHeight", 280);
        List<String> dataIsi = new ArrayList<>();
        Map<String, List<String>> dataToSend = new HashMap<>();
        dataIsi.add("i" + String.valueOf(selectedJabatan.getId()));
        dataToSend.put("param", dataIsi);
        RequestContext.getCurrentInstance().openDialog("job_spesifikasi_form", options, dataToSend);
    }

    public void doEdit() {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", false);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 400);
        options.put("contentHeight", 280);
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> dataIsi = new ArrayList<>();
        dataIsi.add("e" + String.valueOf(selectedJobSpec.getId()));
        dataToSend.put("param", dataIsi);
        RequestContext.getCurrentInstance().openDialog("job_spesifikasi_form", options, dataToSend);
    }
    
    public void doDelete() {
        try {
            this.jabatanSpecService.delete(selectedJobSpec);
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
    
    public void onDelete() {
        try {
            selectedJobSpec = this.jabatanSpecService.getEntiyByPK(selectedJobSpec.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }
    
    @Override
    public void onDialogReturn(SelectEvent event) {
        lazyDataJabatanSpesifikasi = null;
       super.onDialogReturn(event);

    }
    
    @PreDestroy
    public void cleanAndExit() {
        selectedJabatan = null;
        jabatanService = null;
    }
    
    public void doDetail() {
        try {
            selectedJobSpec = this.jabatanSpecService.getDataByPK(selectedJobSpec.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }
}
