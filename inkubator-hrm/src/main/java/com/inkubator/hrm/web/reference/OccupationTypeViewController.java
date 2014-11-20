package com.inkubator.hrm.web.reference;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.OccupationType;
import com.inkubator.hrm.service.OccupationTypeService;
import com.inkubator.hrm.web.lazymodel.OccupationTypeLazyDataModel;
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
@ManagedBean(name = "occupationTypeViewController")
@ViewScoped
public class OccupationTypeViewController extends BaseController {

    private String parameter;
    private LazyDataModel<OccupationType> lazyDataOccupationType;
    private OccupationType selectedOccupationType;
    @ManagedProperty(value = "#{occupationTypeService}")
    private OccupationTypeService occupationTypeService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
    }

    @PreDestroy
    public void cleanAndExit() {
        occupationTypeService = null;
        parameter = null;
        lazyDataOccupationType = null;
        selectedOccupationType = null;
    }

    public void setOccupationTypeService(OccupationTypeService occupationTypeService) {
        this.occupationTypeService = occupationTypeService;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public LazyDataModel<OccupationType> getLazyDataOccupationType() {
        if (lazyDataOccupationType == null) {
            lazyDataOccupationType = new OccupationTypeLazyDataModel(parameter, occupationTypeService);
        }
        return lazyDataOccupationType;
    }

    public void setLazyDataOccupationType(LazyDataModel<OccupationType> lazyDataOccupationType) {
        this.lazyDataOccupationType = lazyDataOccupationType;
    }

    public OccupationType getSelectedOccupationType() {
        return selectedOccupationType;
    }

    public void setSelectedOccupationType(OccupationType selectedOccupationType) {
        this.selectedOccupationType = selectedOccupationType;
    }

    public void doSearch() {
        lazyDataOccupationType = null;
    }

    public void doDetail() {
        try {
            selectedOccupationType = this.occupationTypeService.getEntiyByPK(selectedOccupationType.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public void doDelete() {
        try {
            occupationTypeService.delete(selectedOccupationType);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            LOGGER.error("Error when doDelete occupationType ", ex);
        } catch (Exception ex) {
            LOGGER.error("Error when doDelete occupationType", ex);
        }
    }

    public void doAdd() {
        showDialog(null);
    }

    public void doUpdate() {
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> values = new ArrayList<>();
        values.add(String.valueOf(selectedOccupationType.getId()));
        dataToSend.put("param", values);
        showDialog(dataToSend);
    }

    private void showDialog(Map<String, List<String>> params) {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 490);
        options.put("contentHeight", 335);
        RequestContext.getCurrentInstance().openDialog("occupation_type_form", options, params);
    }

    @Override
    public void onDialogReturn(SelectEvent event) {
        //re-calculate searching
        doSearch();
        super.onDialogReturn(event);
    }
}
