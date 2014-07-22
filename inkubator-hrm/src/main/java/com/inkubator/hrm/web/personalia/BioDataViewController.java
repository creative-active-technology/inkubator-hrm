/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.personalia;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.BioData;
import com.inkubator.hrm.service.BioDataService;
import com.inkubator.hrm.web.lazymodel.BioDataLazyDataModel;
import com.inkubator.hrm.web.search.BioDataSearchParameter;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.io.File;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.hibernate.exception.ConstraintViolationException;
import org.primefaces.model.LazyDataModel;
import org.springframework.dao.DataIntegrityViolationException;

/**
 *
 * @author Deni Husni FR
 */
@ManagedBean(name = "bioDataViewController")
@ViewScoped
public class BioDataViewController extends BaseController {

    @ManagedProperty(value = "#{bioDataService}")
    private BioDataService bioDataService;
    private BioDataSearchParameter bioDataSearchParameter;
    private LazyDataModel<BioData> lazyDataBioData;
    private BioData selectedBioData;

    public void setBioDataService(BioDataService bioDataService) {
        this.bioDataService = bioDataService;
    }

    public BioDataSearchParameter getBioDataSearchParameter() {
        return bioDataSearchParameter;
    }

    public void setBioDataSearchParameter(BioDataSearchParameter bioDataSearchParameter) {
        this.bioDataSearchParameter = bioDataSearchParameter;
    }

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        bioDataSearchParameter = new BioDataSearchParameter();

    }

    public LazyDataModel<BioData> getLazyDataBioData() {
        if (lazyDataBioData == null) {
            lazyDataBioData = new BioDataLazyDataModel(bioDataSearchParameter, bioDataService);
        }
        return lazyDataBioData;
    }

    public void setLazyDataBioData(LazyDataModel<BioData> lazyDataBioData) {
        this.lazyDataBioData = lazyDataBioData;
    }

    public void doSearch() {
        lazyDataBioData = null;
    }

    public String doDetail() {
        return "/protected/personalia/biodata_detail.htm?faces-redirect=true&execution=e" + selectedBioData.getId();
    }

    public BioData getSelectedBioData() {
        return selectedBioData;
    }

    public void setSelectedBioData(BioData selectedBioData) {
        this.selectedBioData = selectedBioData;
    }

    public void onDelete() {
        try {
            selectedBioData = this.bioDataService.getEntiyByPK(selectedBioData.getId());

        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public String doEdit() {
          return "/protected/personalia/biodata_form.htm?faces-redirect=true&execution=e" + selectedBioData.getId();
    }

    public void doDelete() {
        try {
            this.bioDataService.delete(selectedBioData);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            File fileFoto = new File(selectedBioData.getPathFoto());
            fileFoto.delete();
//        }

//        if (entity.getPathSignature() != null || entity.getPathSignature().isEmpty()) {
            File fileSignature = new File(selectedBioData.getPathSignature());
            fileSignature.delete();
//        }

//        if (entity.getPathFinger() != null || entity.getPathFinger().isEmpty()) {
            File fileFinger = new File(selectedBioData.getPathFinger());
            fileFinger.delete();
        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
//            LOGGER.error("Error", ex);
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public String doAdd() {
        return "/protected/personalia/biodata_form.htm?faces-redirect=true";
    }
}
