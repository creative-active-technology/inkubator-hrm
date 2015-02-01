package com.inkubator.hrm.web.reference;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.InstitutionEducation;
import com.inkubator.hrm.service.InstitutionEducationService;
import com.inkubator.hrm.web.lazymodel.InstitutionEducationLazyDataModel;
import com.inkubator.hrm.web.search.InstitutionEducationSearchParameter;
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
 * @author Taufik Hidayat
 */
@ManagedBean(name = "institutionEducationViewController")
@ViewScoped
public class InstitutionEducationViewController extends BaseController {

    private InstitutionEducationSearchParameter searchParameter;
    private LazyDataModel<InstitutionEducation> lazyDataInstitutionEducation;
    private InstitutionEducation selectedInstitutionEducation;
    @ManagedProperty(value = "#{institutionEducationService}")
    private InstitutionEducationService institutionEducationService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        searchParameter = new InstitutionEducationSearchParameter();
    }

    @PreDestroy
    public void cleanAndExit() {
        institutionEducationService = null;
        searchParameter = null;
        lazyDataInstitutionEducation = null;
        selectedInstitutionEducation = null;
    }

    public void setInstitutionEducationService(InstitutionEducationService institutionEducationService) {
        this.institutionEducationService = institutionEducationService;
    }

    public InstitutionEducationSearchParameter getSearchParameter() {
        return searchParameter;
    }

    public void setSearchParameter(InstitutionEducationSearchParameter searchParameter) {
        this.searchParameter = searchParameter;
    }

    

    public LazyDataModel<InstitutionEducation> getLazyDataInstitutionEducation() {
        if (lazyDataInstitutionEducation == null) {
            lazyDataInstitutionEducation = new InstitutionEducationLazyDataModel(searchParameter, institutionEducationService);
        }
        return lazyDataInstitutionEducation;
    }

    public void setLazyDataInstitutionEducation(LazyDataModel<InstitutionEducation> lazyDataInstitutionEducation) {
        this.lazyDataInstitutionEducation = lazyDataInstitutionEducation;
    }

    public InstitutionEducation getSelectedInstitutionEducation() {
        return selectedInstitutionEducation;
    }

    public void setSelectedInstitutionEducation(InstitutionEducation selectedInstitutionEducation) {
        this.selectedInstitutionEducation = selectedInstitutionEducation;
    }

    public void doSearch() {
        lazyDataInstitutionEducation = null;
    }

    public void doDetail() {
        try {
            selectedInstitutionEducation = this.institutionEducationService.getInstitutionEducationByIdWithDetail(selectedInstitutionEducation.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public void doDelete() {
        try {
            institutionEducationService.delete(selectedInstitutionEducation);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            LOGGER.error("Error when doDelete institutionEducation ", ex);
        } catch (Exception ex) {
            LOGGER.error("Error when doDelete institutionEducation", ex);
        }
    }

    public void doAdd() {
        showDialog(null);
    }

    public void doUpdate() {
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> values = new ArrayList<>();
        values.add(String.valueOf(selectedInstitutionEducation.getId()));
        dataToSend.put("param", values);
        showDialog(dataToSend);
    }

    private void showDialog(Map<String, List<String>> params) {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 550);
        options.put("contentHeight", 520);
        RequestContext.getCurrentInstance().openDialog("inst_edu_form", options, params);
    }

    @Override
    public void onDialogReturn(SelectEvent event) {
        //re-calculate searching
        doSearch();
        super.onDialogReturn(event);
    }
}
