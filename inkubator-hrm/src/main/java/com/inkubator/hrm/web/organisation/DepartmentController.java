/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.organisation;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.Department;
import com.inkubator.hrm.service.DepartmentService;
import com.inkubator.hrm.web.lazymodel.DepartmentLazyDataModel;
import com.inkubator.hrm.web.search.DepartmentSearchParameter;
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
 * @author deniarianto
 */
@ManagedBean(name = "departmentController")
@ViewScoped
public class DepartmentController extends BaseController{
    @ManagedProperty(value = "#{departmentService}")
    private DepartmentService departmentService;
    private DepartmentSearchParameter departmentSearchParameter;
    private LazyDataModel<Department> lazyDataDepartment;
    private Department selectedDepartment;

    public DepartmentService getDepartmentService() {
        return departmentService;
    }

    public void setDepartmentService(DepartmentService departmentService) {
        System.out.println("test");
        this.departmentService = departmentService;
    }

    public DepartmentSearchParameter getDepartmentSearchParameter() {
        return departmentSearchParameter;
    }

    public void setDepartmentSearchParameter(DepartmentSearchParameter departmentSearchParameter) {
        this.departmentSearchParameter = departmentSearchParameter;
    }

    public LazyDataModel<Department> getLazyDataDepartment() {
        if(lazyDataDepartment == null){
            lazyDataDepartment = new DepartmentLazyDataModel(departmentSearchParameter, departmentService);
        }
        return lazyDataDepartment;
    }

    public void setLazyDataDepartment(LazyDataModel<Department> lazyDataDepartment) {
        this.lazyDataDepartment = lazyDataDepartment;
    }

    public Department getSelectedDepartment() {
        return selectedDepartment;
    }

    public void setSelectedDepartment(Department selectedDepartment) {
        this.selectedDepartment = selectedDepartment;
    }
    
    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        departmentSearchParameter = new DepartmentSearchParameter();
    }
    
    public void doSearch() {
        lazyDataDepartment = null;
    }
    
    public void doDetail() {
        try {
            selectedDepartment = this.departmentService.getEntiyByPK(selectedDepartment.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }
    
    public void doDelete() {
        try {
            this.departmentService.delete(selectedDepartment);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            LOGGER.error("Error", ex);
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }
    
    public void doAdd() {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 400);
        options.put("contentHeight", 340);
//        options.put("closable", false);
//        options.put("height", "auto");

//        options.put("contentHeight", 340);
        RequestContext.getCurrentInstance().openDialog("department_form", options, null);
    }
        
    public void doEdit() {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 400);
        options.put("contentHeight", 340);
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> dataIsi = new ArrayList<>();
        dataIsi.add(String.valueOf(selectedDepartment.getId()));
        dataToSend.put("param", dataIsi);
        RequestContext.getCurrentInstance().openDialog("department_form", options, dataToSend);
    }
    
    @Override
    public void onDialogReturn(SelectEvent event) {
        lazyDataDepartment = null;
       super.onDialogReturn(event);

    }

    public void onDelete() {
        try {
            selectedDepartment = this.departmentService.getEntiyByPK(selectedDepartment.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    @PreDestroy
    private void cleanAndExit() {
        departmentSearchParameter=null;
        lazyDataDepartment=null;
        departmentService=null;
        selectedDepartment=null;
        
    }
    
    public void doSelectEntity() {
        try {
            selectedDepartment = this.departmentService.getEntiyByPK(selectedDepartment.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }
    
}
