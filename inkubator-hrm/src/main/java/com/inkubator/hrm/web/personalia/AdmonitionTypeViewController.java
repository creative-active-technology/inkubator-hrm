package com.inkubator.hrm.web.personalia;

import com.inkubator.hrm.web.reference.*;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.AdmonitionType;
import com.inkubator.hrm.service.AdmonitionTypeService;
import com.inkubator.hrm.web.lazymodel.AdmonitionTypeLazyDataModel;
import com.inkubator.hrm.web.search.AdmonitionTypeSearchParameter;
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
@ManagedBean(name = "admonitionTypeViewController")
@ViewScoped
public class AdmonitionTypeViewController extends BaseController {

    private AdmonitionTypeSearchParameter searchParameter;
    private LazyDataModel<AdmonitionType> lazyDataAdmonitionType;
    private AdmonitionType selectedAdmonitionType;
    @ManagedProperty(value = "#{admonitionTypeService}")
    private AdmonitionTypeService admonitionTypeService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        searchParameter = new AdmonitionTypeSearchParameter();
    }

    @PreDestroy
    public void cleanAndExit() {
        admonitionTypeService = null;
        searchParameter = null;
        lazyDataAdmonitionType = null;
        selectedAdmonitionType = null;
    }

    public void setAdmonitionTypeService(AdmonitionTypeService admonitionTypeService) {
        this.admonitionTypeService = admonitionTypeService;
    }

    public AdmonitionTypeSearchParameter getSearchParameter() {
        return searchParameter;
    }

    public void setSearchParameter(AdmonitionTypeSearchParameter searchParameter) {
        this.searchParameter = searchParameter;
    }

    

    public LazyDataModel<AdmonitionType> getLazyDataAdmonitionType() {
        if (lazyDataAdmonitionType == null) {
            lazyDataAdmonitionType = new AdmonitionTypeLazyDataModel(searchParameter, admonitionTypeService);
        }
        return lazyDataAdmonitionType;
    }

    public void setLazyDataAdmonitionType(LazyDataModel<AdmonitionType> lazyDataAdmonitionType) {
        this.lazyDataAdmonitionType = lazyDataAdmonitionType;
    }

    public AdmonitionType getSelectedAdmonitionType() {
        return selectedAdmonitionType;
    }

    public void setSelectedAdmonitionType(AdmonitionType selectedAdmonitionType) {
        this.selectedAdmonitionType = selectedAdmonitionType;
    }

    public void doSearch() {
        lazyDataAdmonitionType = null;
    }

    public void doDetail() {
        try {
            selectedAdmonitionType = this.admonitionTypeService.getEntiyByPK(selectedAdmonitionType.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public void doDelete() {
        try {
            admonitionTypeService.delete(selectedAdmonitionType);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            LOGGER.error("Error when doDelete admonitionType ", ex);
        } catch (Exception ex) {
            LOGGER.error("Error when doDelete admonitionType", ex);
        }
    }

    public void doAdd() {
        showDialog(null);
    }

    public void doUpdate() {
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> values = new ArrayList<>();
        values.add(String.valueOf(selectedAdmonitionType.getId()));
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
        RequestContext.getCurrentInstance().openDialog("admonition_type_form", options, params);
    }

    @Override
    public void onDialogReturn(SelectEvent event) {
        //re-calculate searching
        doSearch();
        super.onDialogReturn(event);
    }
}
