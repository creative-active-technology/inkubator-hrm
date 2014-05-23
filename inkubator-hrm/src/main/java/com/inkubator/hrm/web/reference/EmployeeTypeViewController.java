package com.inkubator.hrm.web.reference;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.EmployeeType;
import com.inkubator.hrm.service.EmployeeTypeService;
import com.inkubator.hrm.web.lazymodel.EmployeeTypeLazyDataModel;
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
 * @author rizkykojek
 */
@ManagedBean(name = "employeeTypeViewController")
@ViewScoped
public class EmployeeTypeViewController extends BaseController {

    private String parameter;
    private LazyDataModel<EmployeeType> lazyDataEmployeeType;
    private EmployeeType selectedEmployeeType;
    @ManagedProperty(value = "#{employeeTypeService}")
    private EmployeeTypeService employeeTypeService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
    }

    @PreDestroy
    public void cleanAndExit() {
        employeeTypeService = null;
        parameter = null;
        lazyDataEmployeeType = null;
        selectedEmployeeType = null;
    }

    public void setEmployeeTypeService(EmployeeTypeService employeeTypeService) {
        this.employeeTypeService = employeeTypeService;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public LazyDataModel<EmployeeType> getLazyDataEmployeeType() {
        if (lazyDataEmployeeType == null) {
            lazyDataEmployeeType = new EmployeeTypeLazyDataModel(parameter, employeeTypeService);
        }
        return lazyDataEmployeeType;
    }

    public void setLazyDataEmployeeType(LazyDataModel<EmployeeType> lazyDataEmployeeType) {
        this.lazyDataEmployeeType = lazyDataEmployeeType;
    }

    public EmployeeType getSelectedEmployeeType() {
        return selectedEmployeeType;
    }

    public void setSelectedEmployeeType(EmployeeType selectedEmployeeType) {
        this.selectedEmployeeType = selectedEmployeeType;
    }

    public void doSearch() {
        lazyDataEmployeeType = null;
    }
    
    public void doDetail() {
        try {
        	selectedEmployeeType = this.employeeTypeService.getEntiyByPK(selectedEmployeeType.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    } 

    public void doDelete() {
        try {
            employeeTypeService.delete(selectedEmployeeType);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            LOGGER.error("Error when doDelete employeeType ", ex);
        } catch (Exception ex) {
            LOGGER.error("Error when doDelete employeeType", ex);
        }
    }

    public void doAdd() {
        showDialog(null);
    }

    public void doUpdate() {
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> values = new ArrayList<>();
        values.add(String.valueOf(selectedEmployeeType.getId()));
        dataToSend.put("param", values);
        showDialog(dataToSend);
    }

    private void showDialog(Map<String, List<String>> params) {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 450);
        options.put("contentHeight", 250);
        RequestContext.getCurrentInstance().openDialog("employee_type_form", options, params);
    }

    public void onDialogClose(SelectEvent event) {
        //re-calculate searching
        doSearch();

        //show growl message
        String condition = (String) event.getObject();
        if (condition.equalsIgnoreCase(HRMConstant.SAVE_CONDITION)) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } else if (condition.equalsIgnoreCase(HRMConstant.UPDATE_CONDITION)) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.save_info", "global.update_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        }
    }
}
