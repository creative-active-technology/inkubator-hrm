/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.organisation;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.Jabatan;
import com.inkubator.hrm.entity.JabatanDeskripsi;
import com.inkubator.hrm.service.JabatanDeskripsiService;
import com.inkubator.hrm.web.model.JabatanDeskripsiModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Deni Husni FR
 */
@ManagedBean(name = "jabatanDeskripsiFormController")
@ViewScoped
public class JabatanDeskripsiFormController extends BaseController {

    private JabatanDeskripsiModel jabatanDeskripsiModel;
    @ManagedProperty(value = "#{jabatanDeskripsiService}")
    private JabatanDeskripsiService jabatanDeskripsiService;
    private long jobsId;
    private Boolean isEdit;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        String param = FacesUtil.getRequestParameter("param");
        if (param.contains("i")) {
            jobsId = Long.parseLong(param.substring(1));
            isEdit = Boolean.FALSE;
            System.out.println(" ini lhooooo");
            jabatanDeskripsiModel = new JabatanDeskripsiModel();
        }
        if (param.contains("e")) {
            try {
                long jobDeskId = Long.parseLong(param.substring(1));
                JabatanDeskripsi jabatanDeskripsi = jabatanDeskripsiService.getEntiyByPK(jobDeskId);
                isEdit = Boolean.TRUE;
                jabatanDeskripsiModel = new JabatanDeskripsiModel();
                jabatanDeskripsiModel.setId(jabatanDeskripsi.getId());
                jabatanDeskripsiModel.setCategoryTugas(jabatanDeskripsi.getKategoryTugas());
                jabatanDeskripsiModel.setDeskripsi(jabatanDeskripsi.getDescription());
                jabatanDeskripsiModel.setJabatanId(jabatanDeskripsi.getJabatan().getId());
                jabatanDeskripsiModel.setTypeWaktu(jabatanDeskripsi.getKategoryTugas());
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }

        }

    }

    public JabatanDeskripsiModel getJabatanDeskripsiModel() {
        return jabatanDeskripsiModel;
    }

    public void setJabatanDeskripsiModel(JabatanDeskripsiModel jabatanDeskripsiModel) {
        this.jabatanDeskripsiModel = jabatanDeskripsiModel;
    }

    public void setJabatanDeskripsiService(JabatanDeskripsiService jabatanDeskripsiService) {
        this.jabatanDeskripsiService = jabatanDeskripsiService;
    }

    public void doSave() {
        JabatanDeskripsi jabatanDeskripsi = getEntityFromViewModel(jabatanDeskripsiModel);
        try {
            if (isEdit) {
                jabatanDeskripsiService.update(jabatanDeskripsi);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                jabatanDeskripsiService.save(jabatanDeskripsi);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch (BussinessException ex) { //data already exist(duplicate)
            LOGGER.error("Error", ex);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }

    }

    public long getJobsId() {
        return jobsId;
    }

    public void setJobsId(long jobsId) {
        this.jobsId = jobsId;
    }

    public Boolean getIsEdit() {
        return isEdit;
    }

    public void setIsEdit(Boolean isEdit) {
        this.isEdit = isEdit;
    }

    @PreDestroy
    public void cleanAndExit() {
        jabatanDeskripsiModel = null;
        jabatanDeskripsiService = null;
        isEdit = null;

    }

    private JabatanDeskripsi getEntityFromViewModel(JabatanDeskripsiModel model) {
        JabatanDeskripsi jabatanDeskripsi = new JabatanDeskripsi();
        if (model.getId() != null) {
            jabatanDeskripsi.setId(model.getId());
            jabatanDeskripsi.setJabatan(new Jabatan(model.getJabatanId()));
        } else {

            jabatanDeskripsi.setJabatan(new Jabatan(jobsId));
        }
        jabatanDeskripsi.setDescription(model.getDeskripsi());
        jabatanDeskripsi.setKategoryTugas(model.getCategoryTugas());
        jabatanDeskripsi.setTypeWaktu(model.getTypeWaktu());
        return jabatanDeskripsi;
    }

}
