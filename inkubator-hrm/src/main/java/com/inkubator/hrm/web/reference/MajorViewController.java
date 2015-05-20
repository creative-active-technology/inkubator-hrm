package com.inkubator.hrm.web.reference;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.Major;
import com.inkubator.hrm.service.MajorService;
import com.inkubator.hrm.web.lazymodel.MajorLazyDataModel;
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
@ManagedBean(name = "majorViewController")
@ViewScoped
public class MajorViewController extends BaseController {

    private String parameter;
    private LazyDataModel<Major> lazyDataMajor;
    private Major selectedMajor;
    @ManagedProperty(value = "#{majorService}")
    private MajorService majorService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
    }

    @PreDestroy
    public void cleanAndExit() {
        majorService = null;
        parameter = null;
        lazyDataMajor = null;
        selectedMajor = null;
    }

    public void setMajorService(MajorService majorService) {
        this.majorService = majorService;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public LazyDataModel<Major> getLazyDataMajor() {
        if (lazyDataMajor == null) {
            lazyDataMajor = new MajorLazyDataModel(parameter, majorService);
        }
        return lazyDataMajor;
    }

    public void setLazyDataMajor(LazyDataModel<Major> lazyDataMajor) {
        this.lazyDataMajor = lazyDataMajor;
    }

    public Major getSelectedMajor() {
        return selectedMajor;
    }

    public void setSelectedMajor(Major selectedMajor) {
        this.selectedMajor = selectedMajor;
    }

    public void doSearch() {
        lazyDataMajor = null;
    }

    public void doDetail() {
        try {
            selectedMajor = this.majorService.getEntiyByPK(selectedMajor.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public void doDelete() {
        try {
            majorService.delete(selectedMajor);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            LOGGER.error("Error when doDelete major ", ex);
        } catch (Exception ex) {
            LOGGER.error("Error when doDelete major", ex);
        }
    }

    public void doAdd() {
        showDialog(null);
    }

    public void doUpdate() {
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> values = new ArrayList<>();
        values.add(String.valueOf(selectedMajor.getId()));
        dataToSend.put("param", values);
        showDialog(dataToSend);
    }

    private void showDialog(Map<String, List<String>> params) {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 400);
        options.put("contentHeight", 335);
        RequestContext.getCurrentInstance().openDialog("major_form", options, params);
    }

    @Override
    public void onDialogReturn(SelectEvent event) {
        //re-calculate searching
        doSearch();
        super.onDialogReturn(event);
    }
}
