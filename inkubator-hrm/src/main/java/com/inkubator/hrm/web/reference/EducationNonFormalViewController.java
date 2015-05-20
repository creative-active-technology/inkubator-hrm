package com.inkubator.hrm.web.reference;

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

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.EducationNonFormal;
import com.inkubator.hrm.service.EducationNonFormalService;
import com.inkubator.hrm.web.lazymodel.EducationNonFormalLazyDataModel;
import com.inkubator.hrm.web.search.EducationNonFormalSearchParameter;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "educationNonFormalViewController")
@ViewScoped
public class EducationNonFormalViewController extends BaseController {

    private EducationNonFormalSearchParameter parameter;
    private LazyDataModel<EducationNonFormal> lazyDataModel;
    private EducationNonFormal selectedEducationNonFormal;
    @ManagedProperty(value = "#{educationNonFormalService}")
    private EducationNonFormalService educationNonFormalService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        parameter = new EducationNonFormalSearchParameter();
    }

    @PreDestroy
    public void cleanAndExit() {
        educationNonFormalService = null;
        parameter = null;
        lazyDataModel = null;
        selectedEducationNonFormal = null;
    }

    public LazyDataModel<EducationNonFormal> getLazyDataModel() {
        if (lazyDataModel == null) {
            lazyDataModel = new EducationNonFormalLazyDataModel(parameter, educationNonFormalService);
        }
        return lazyDataModel;
    }

    public void setLazyDataModel(LazyDataModel<EducationNonFormal> lazyDataModel) {
        this.lazyDataModel = lazyDataModel;
    }

    public EducationNonFormal getSelectedEducationNonFormal() {
		return selectedEducationNonFormal;
	}

	public void setSelectedEducationNonFormal(
			EducationNonFormal selectedEducationNonFormal) {
		this.selectedEducationNonFormal = selectedEducationNonFormal;
	}

	public void setEducationNonFormalService(
			EducationNonFormalService educationNonFormalService) {
		this.educationNonFormalService = educationNonFormalService;
	}

    public void doSearch() {
        lazyDataModel = null;
    }

    public void doDetail() {
        try {
            selectedEducationNonFormal = this.educationNonFormalService.getEntityByPkWithDetail(selectedEducationNonFormal.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public void doDelete() {
        try {
            educationNonFormalService.delete(selectedEducationNonFormal);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            LOGGER.error("Error when doDelete educationNonFormal ", ex);
        } catch (Exception ex) {
            LOGGER.error("Error when doDelete educationNonFormal", ex);
        }
    }

    public void doAdd() {
        showDialog(null);
    }

    public void doUpdate() {
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> values = new ArrayList<>();
        values.add(String.valueOf(selectedEducationNonFormal.getId()));
        dataToSend.put("param", values);
        showDialog(dataToSend);
    }

    private void showDialog(Map<String, List<String>> params) {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 550);
        options.put("contentHeight", 600);
        RequestContext.getCurrentInstance().openDialog("edu_non_formal_form", options, params);
    }

    @Override
    public void onDialogReturn(SelectEvent event) {
        //re-calculate searching
        doSearch();
        super.onDialogReturn(event);
    }

    public EducationNonFormalSearchParameter getParameter() {
        return parameter;
    }

    public void setParameter(EducationNonFormalSearchParameter parameter) {
        this.parameter = parameter;
    }
}
