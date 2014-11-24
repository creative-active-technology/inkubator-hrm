package com.inkubator.hrm.web.reference;

import com.inkubator.hrm.web.personalia.*;
import com.inkubator.hrm.web.reference.*;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.Disease;
import com.inkubator.hrm.service.DiseaseService;
import com.inkubator.hrm.web.lazymodel.DiseaseLazyDataModel;
import com.inkubator.hrm.web.search.DiseaseSearchParameter;
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
@ManagedBean(name = "diseaseViewController")
@ViewScoped
public class DiseaseViewController extends BaseController {

    private DiseaseSearchParameter searchParameter;
    private LazyDataModel<Disease> lazyDataDisease;
    private Disease selectedDisease;
    @ManagedProperty(value = "#{diseaseService}")
    private DiseaseService diseaseService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        searchParameter = new DiseaseSearchParameter();
    }

    @PreDestroy
    public void cleanAndExit() {
        diseaseService = null;
        searchParameter = null;
        lazyDataDisease = null;
        selectedDisease = null;
    }

    public void setDiseaseService(DiseaseService diseaseService) {
        this.diseaseService = diseaseService;
    }

    public DiseaseSearchParameter getSearchParameter() {
        return searchParameter;
    }

    public void setSearchParameter(DiseaseSearchParameter searchParameter) {
        this.searchParameter = searchParameter;
    }

    

    public LazyDataModel<Disease> getLazyDataDisease() {
        if (lazyDataDisease == null) {
            lazyDataDisease = new DiseaseLazyDataModel(searchParameter, diseaseService);
        }
        return lazyDataDisease;
    }

    public void setLazyDataDisease(LazyDataModel<Disease> lazyDataDisease) {
        this.lazyDataDisease = lazyDataDisease;
    }

    public Disease getSelectedDisease() {
        return selectedDisease;
    }

    public void setSelectedDisease(Disease selectedDisease) {
        this.selectedDisease = selectedDisease;
    }

    public void doSearch() {
        lazyDataDisease = null;
    }

    public void doDetail() {
        try {
            selectedDisease = this.diseaseService.getEntiyByPK(selectedDisease.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public void doDelete() {
        try {
            diseaseService.delete(selectedDisease);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            LOGGER.error("Error when doDelete disease ", ex);
        } catch (Exception ex) {
            LOGGER.error("Error when doDelete disease", ex);
        }
    }

    public void doAdd() {
        showDialog(null);
    }

    public void doUpdate() {
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> values = new ArrayList<>();
        values.add(String.valueOf(selectedDisease.getId()));
        dataToSend.put("param", values);
        showDialog(dataToSend);
    }

    private void showDialog(Map<String, List<String>> params) {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 400);
        options.put("contentHeight", 400);
        RequestContext.getCurrentInstance().openDialog("disease_form", options, params);
    }

    @Override
    public void onDialogReturn(SelectEvent event) {
        //re-calculate searching
        doSearch();
        super.onDialogReturn(event);
    }
}
