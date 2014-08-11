package com.inkubator.hrm.web.personalia;

import com.inkubator.hrm.web.reference.*;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.TerminationType;
import com.inkubator.hrm.service.TerminationTypeService;
import com.inkubator.hrm.web.lazymodel.TerminationTypeLazyDataModel;
import com.inkubator.hrm.web.search.TerminationTypeSearchParameter;
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
@ManagedBean(name = "terminationTypeViewController")
@ViewScoped
public class TerminationTypeViewController extends BaseController {

    private TerminationTypeSearchParameter searchParameter;
    private LazyDataModel<TerminationType> lazyDataTerminationType;
    private TerminationType selectedTerminationType;
    @ManagedProperty(value = "#{terminationTypeService}")
    private TerminationTypeService terminationTypeService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        searchParameter = new TerminationTypeSearchParameter();
    }

    @PreDestroy
    public void cleanAndExit() {
        terminationTypeService = null;
        searchParameter = null;
        lazyDataTerminationType = null;
        selectedTerminationType = null;
    }

    public void setTerminationTypeService(TerminationTypeService terminationTypeService) {
        this.terminationTypeService = terminationTypeService;
    }

    public TerminationTypeSearchParameter getSearchParameter() {
        return searchParameter;
    }

    public void setSearchParameter(TerminationTypeSearchParameter searchParameter) {
        this.searchParameter = searchParameter;
    }

    

    public LazyDataModel<TerminationType> getLazyDataTerminationType() {
        if (lazyDataTerminationType == null) {
            lazyDataTerminationType = new TerminationTypeLazyDataModel(searchParameter, terminationTypeService);
        }
        return lazyDataTerminationType;
    }

    public void setLazyDataTerminationType(LazyDataModel<TerminationType> lazyDataTerminationType) {
        this.lazyDataTerminationType = lazyDataTerminationType;
    }

    public TerminationType getSelectedTerminationType() {
        return selectedTerminationType;
    }

    public void setSelectedTerminationType(TerminationType selectedTerminationType) {
        this.selectedTerminationType = selectedTerminationType;
    }

    public void doSearch() {
        lazyDataTerminationType = null;
    }

    public void doDetail() {
        try {
            selectedTerminationType = this.terminationTypeService.getEntiyByPK(selectedTerminationType.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public void doDelete() {
        try {
            terminationTypeService.delete(selectedTerminationType);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            LOGGER.error("Error when doDelete terminationType ", ex);
        } catch (Exception ex) {
            LOGGER.error("Error when doDelete terminationType", ex);
        }
    }

    public void doAdd() {
        showDialog(null);
    }

    public void doUpdate() {
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> values = new ArrayList<>();
        values.add(String.valueOf(selectedTerminationType.getId()));
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
        RequestContext.getCurrentInstance().openDialog("termination_type_form", options, params);
    }

    @Override
    public void onDialogReturn(SelectEvent event) {
        //re-calculate searching
        doSearch();
        super.onDialogReturn(event);
    }
}
