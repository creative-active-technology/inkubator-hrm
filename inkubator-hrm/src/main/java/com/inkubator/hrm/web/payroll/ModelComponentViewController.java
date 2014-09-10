package com.inkubator.hrm.web.payroll;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.ModelComponent;
import com.inkubator.hrm.service.ModelComponentService;
import com.inkubator.hrm.web.lazymodel.ModelComponentLazyDataModel;
import com.inkubator.hrm.web.search.ModelComponentSearchParameter;
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
@ManagedBean(name = "modelComponentViewController")
@ViewScoped
public class ModelComponentViewController extends BaseController {

    private ModelComponentSearchParameter searchParameter;
    private LazyDataModel<ModelComponent> lazyDataModelComponent;
    private ModelComponent selectedModelComponent;
    @ManagedProperty(value = "#{modelComponentService}")
    private ModelComponentService modelComponentService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        searchParameter = new ModelComponentSearchParameter();
    }

    @PreDestroy
    public void cleanAndExit() {
        modelComponentService = null;
        searchParameter = null;
        lazyDataModelComponent = null;
        selectedModelComponent = null;
    }

    public void setModelComponentService(ModelComponentService modelComponentService) {
        this.modelComponentService = modelComponentService;
    }

    public ModelComponentSearchParameter getSearchParameter() {
        return searchParameter;
    }

    public void setSearchParameter(ModelComponentSearchParameter searchParameter) {
        this.searchParameter = searchParameter;
    }

    

    public LazyDataModel<ModelComponent> getLazyDataModelComponent() {
        if (lazyDataModelComponent == null) {
            lazyDataModelComponent = new ModelComponentLazyDataModel(searchParameter, modelComponentService);
        }
        return lazyDataModelComponent;
    }

    public void setLazyDataModelComponent(LazyDataModel<ModelComponent> lazyDataModelComponent) {
        this.lazyDataModelComponent = lazyDataModelComponent;
    }

    public ModelComponent getSelectedModelComponent() {
        return selectedModelComponent;
    }

    public void setSelectedModelComponent(ModelComponent selectedModelComponent) {
        this.selectedModelComponent = selectedModelComponent;
    }

    public void doSearch() {
        lazyDataModelComponent = null;
    }

    public void doDetail() {
        try {
            selectedModelComponent = this.modelComponentService.getEntityByPKWithDetail(selectedModelComponent.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public void doDelete() {
        try {
            modelComponentService.delete(selectedModelComponent);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            LOGGER.error("Error when doDelete modelComponent ", ex);
        } catch (Exception ex) {
            LOGGER.error("Error when doDelete modelComponent", ex);
        }
    }

    public void doAdd() {
        showDialog(null);
    }

    public void doUpdate() {
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> values = new ArrayList<>();
        values.add(String.valueOf(selectedModelComponent.getId()));
        dataToSend.put("param", values);
        showDialog(dataToSend);
    }

    private void showDialog(Map<String, List<String>> params) {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 500);
        options.put("contentHeight", 400);
        RequestContext.getCurrentInstance().openDialog("model_component_form", options, params);
    }

    @Override
    public void onDialogReturn(SelectEvent event) {
        //re-calculate searching
        doSearch();
        super.onDialogReturn(event);
    }
}
