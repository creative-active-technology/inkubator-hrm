package com.inkubator.hrm.web.personalia;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.AppraisalElement;
import com.inkubator.hrm.service.AppraisalElementService;
import com.inkubator.hrm.web.lazymodel.AppraisalElementLazyDataModel;
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
@ManagedBean(name = "appraisalElementViewController")
@ViewScoped
public class AppraisalElementViewController extends BaseController {

    private String parameter;
    private LazyDataModel<AppraisalElement> lazyDataAppraisalElement;
    private AppraisalElement selectedAppraisalElement;
    @ManagedProperty(value = "#{appraisalElementService}")
    private AppraisalElementService appraisalElementService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
    }

    @PreDestroy
    public void cleanAndExit() {
        appraisalElementService = null;
        parameter = null;
        lazyDataAppraisalElement = null;
        selectedAppraisalElement = null;
    }

    public void setAppraisalElementService(AppraisalElementService appraisalElementService) {
        this.appraisalElementService = appraisalElementService;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public LazyDataModel<AppraisalElement> getLazyDataAppraisalElement() {
        if (lazyDataAppraisalElement == null) {
            lazyDataAppraisalElement = new AppraisalElementLazyDataModel(parameter, appraisalElementService);
        }
        return lazyDataAppraisalElement;
    }

    public void setLazyDataAppraisalElement(LazyDataModel<AppraisalElement> lazyDataAppraisalElement) {
        this.lazyDataAppraisalElement = lazyDataAppraisalElement;
    }

    public AppraisalElement getSelectedAppraisalElement() {
        return selectedAppraisalElement;
    }

    public void setSelectedAppraisalElement(AppraisalElement selectedAppraisalElement) {
        this.selectedAppraisalElement = selectedAppraisalElement;
    }

    public void doSearch() {
        lazyDataAppraisalElement = null;
    }

    public void doDetail() {
        try {
            selectedAppraisalElement = this.appraisalElementService.getEntiyByPK(selectedAppraisalElement.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public void doDelete() {
        try {
            appraisalElementService.delete(selectedAppraisalElement);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            LOGGER.error("Error when doDelete appraisalElement ", ex);
        } catch (Exception ex) {
            LOGGER.error("Error when doDelete appraisalElement", ex);
        }
    }

    public void doAdd() {
        showDialog(null);
    }

    public void doUpdate() {
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> values = new ArrayList<>();
        values.add(String.valueOf(selectedAppraisalElement.getId()));
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
        RequestContext.getCurrentInstance().openDialog("appraisal_element_form", options, params);
    }

    @Override
    public void onDialogReturn(SelectEvent event) {
        //re-calculate searching
        doSearch();
        super.onDialogReturn(event);
    }
}
