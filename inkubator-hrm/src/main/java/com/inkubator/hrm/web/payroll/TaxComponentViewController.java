package com.inkubator.hrm.web.payroll;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.TaxComponent;
import com.inkubator.hrm.service.TaxComponentService;
import com.inkubator.hrm.web.lazymodel.TaxComponentLazyDataModel;
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
@ManagedBean(name = "taxComponentViewController")
@ViewScoped
public class TaxComponentViewController extends BaseController {

    private String parameter;
    private LazyDataModel<TaxComponent> lazyDataTaxComponent;
    private TaxComponent selectedTaxComponent;
    @ManagedProperty(value = "#{taxComponentService}")
    private TaxComponentService taxComponentService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
    }

    @PreDestroy
    public void cleanAndExit() {
        taxComponentService = null;
        parameter = null;
        lazyDataTaxComponent = null;
        selectedTaxComponent = null;
    }

    public void setTaxComponentService(TaxComponentService taxComponentService) {
        this.taxComponentService = taxComponentService;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public LazyDataModel<TaxComponent> getLazyDataTaxComponent() {
        if (lazyDataTaxComponent == null) {
            lazyDataTaxComponent = new TaxComponentLazyDataModel(parameter, taxComponentService);
        }
        return lazyDataTaxComponent;
    }

    public void setLazyDataTaxComponent(LazyDataModel<TaxComponent> lazyDataTaxComponent) {
        this.lazyDataTaxComponent = lazyDataTaxComponent;
    }

    public TaxComponent getSelectedTaxComponent() {
        return selectedTaxComponent;
    }

    public void setSelectedTaxComponent(TaxComponent selectedTaxComponent) {
        this.selectedTaxComponent = selectedTaxComponent;
    }

    public void doSearch() {
        lazyDataTaxComponent = null;
    }

    public void doDetail() {
        try {
            selectedTaxComponent = this.taxComponentService.getEntiyByPK(selectedTaxComponent.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public void doDelete() {
        try {
            taxComponentService.delete(selectedTaxComponent);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            LOGGER.error("Error when doDelete taxComponent ", ex);
        } catch (Exception ex) {
            LOGGER.error("Error when doDelete taxComponent", ex);
        }
    }

    public void doAdd() {
        showDialog(null);
    }

    public void doUpdate() {
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> values = new ArrayList<>();
        values.add(String.valueOf(selectedTaxComponent.getId()));
        dataToSend.put("param", values);
        showDialog(dataToSend);
    }

    private void showDialog(Map<String, List<String>> params) {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 400);
        options.put("contentHeight", 325);
        RequestContext.getCurrentInstance().openDialog("tax_component_form", options, params);
    }

    @Override
    public void onDialogReturn(SelectEvent event) {
        //re-calculate searching
        doSearch();
        super.onDialogReturn(event);
    }
}
