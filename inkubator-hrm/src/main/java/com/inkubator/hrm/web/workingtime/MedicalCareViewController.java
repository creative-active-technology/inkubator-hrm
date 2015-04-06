package com.inkubator.hrm.web.workingtime;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.hibernate.exception.ConstraintViolationException;
import org.primefaces.model.LazyDataModel;
import org.springframework.dao.DataIntegrityViolationException;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.MedicalCare;
import com.inkubator.hrm.service.MedicalCareService;
import com.inkubator.hrm.web.lazymodel.MedicalCareLazyDataModel;
import com.inkubator.hrm.web.search.MedicalCareSearchParameter;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
 *
 * @author Taufik
 */
@ManagedBean(name = "medicalCareViewController")
@ViewScoped
public class MedicalCareViewController extends BaseController {

    private MedicalCareSearchParameter searchParameter;
    private LazyDataModel<MedicalCare> lazyDataMedicalCare;
    private MedicalCare selectedMedicalCare;
    @ManagedProperty(value = "#{medicalCareService}")
    private MedicalCareService medicalCareService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        searchParameter = new MedicalCareSearchParameter();
    }

    @PreDestroy
    public void cleanAndExit() {
        medicalCareService = null;
        searchParameter = null;
        lazyDataMedicalCare = null;
        selectedMedicalCare = null;
    }

    public LazyDataModel<MedicalCare> getLazyDataMedicalCare() {
        if (lazyDataMedicalCare == null) {
            lazyDataMedicalCare = new MedicalCareLazyDataModel(searchParameter, medicalCareService);
        }
        return lazyDataMedicalCare;
    }

    public void setLazyDataMedicalCare(LazyDataModel<MedicalCare> lazyDataMedicalCare) {
        this.lazyDataMedicalCare = lazyDataMedicalCare;
    }

    public MedicalCare getSelectedMedicalCare() {
        return selectedMedicalCare;
    }

    public void setSelectedMedicalCare(
            MedicalCare selectedMedicalCare) {
        this.selectedMedicalCare = selectedMedicalCare;
    }

    public void setMedicalCareService(
            MedicalCareService medicalCareService) {
        this.medicalCareService = medicalCareService;
    }

    public MedicalCareSearchParameter getSearchParameter() {
        return searchParameter;
    }

    public void setSearchParameter(MedicalCareSearchParameter searchParameter) {
        this.searchParameter = searchParameter;
    }

    public void doSearch() {
        lazyDataMedicalCare = null;
    }

    public String doDetail() {
        return "/protected/working_time/medical_care_detail.htm?faces-redirect=true&execution=e" + selectedMedicalCare.getId();
    }

    public void doSelectEntity() {
        try {
            selectedMedicalCare = this.medicalCareService.getEntityWithNameAndNik(selectedMedicalCare.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public void doDelete() {
        try {
            medicalCareService.delete(selectedMedicalCare);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            LOGGER.error("Error when doDelete Leave", ex);
        } catch (Exception ex) {
            LOGGER.error("Error when doDelete Leave", ex);
        }
    }

    public String doAdd() {
        return "/protected/working_time/medical_care_form.htm?faces-redirect=true";
    }

    public String doUpdate() {
        return "/protected/working_time/medical_care_form.htm?faces-redirect=true&execution=e" + selectedMedicalCare.getId();
    }
}
