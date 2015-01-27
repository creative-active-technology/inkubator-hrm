/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.employee;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.ImplementationOfOverTime;
import com.inkubator.hrm.service.ImplementationOfOverTimeService;
import com.inkubator.hrm.web.lazymodel.ImplementationOfOverTimeLazyDataModel;
import com.inkubator.hrm.web.search.ImplementationOfOvertimeSearchParameter;
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
import org.primefaces.model.LazyDataModel;
import org.springframework.dao.DataIntegrityViolationException;

/**
 *
 * @author Deni
 */
@ManagedBean(name = "implementationOfOverTimeViewController")
@ViewScoped
public class ImplementationOfOverTimeViewController extends BaseController{
    @ManagedProperty(value = "#{implementationOfOverTimeService}")
    private ImplementationOfOverTimeService service;
    private ImplementationOfOvertimeSearchParameter searchParameter;
    private LazyDataModel<ImplementationOfOverTime> lazy;
    private ImplementationOfOverTime selected;
    
            
    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        searchParameter = new ImplementationOfOvertimeSearchParameter();
    }
    
    @PreDestroy
    private void cleanAndExit() {
        searchParameter=null;
        lazy=null;
        service=null;
        selected=null;
        
    }
    
    public void doSearch() {
        lazy = null;
    }
    
    public void doSelectEntity() {
        try {
            selected = this.service.getEntiyByPK(selected.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }
    
    public void doSelectEntityWithDetail() {
        try {
            selected = this.service.getEntityByPkWithDetail(selected.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    //    public String doDetail(){
    //        return "/protected/personalia/reimbursment_detail.htm?faces-redirect=true&execution=e" + selected.getCode();
    //    }
    //    public String add(){
    //        return "/protected/personalia/reimbursment_form.htm?faces-redirect=true";
    //    }
    //
    //    public String edit(){
    //        return "/protected/personalia/reimbursment_form.htm?faces-redirect=true&execution=e" + selected.getId();
    //    }
    //
        public void doDelete() {
            try {
                this.service.delete(selected);
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
            showDialog(null);
        }
    
        public void doUpdate() {
            Map<String, List<String>> dataToSend = new HashMap<>();
            List<String> values = new ArrayList<>();
            values.add(String.valueOf(selected.getId()));
            dataToSend.put("implementOfOTId", values);
            showDialog(dataToSend);
        }
    
        private void showDialog(Map<String, List<String>> params) {
            Map<String, Object> options = new HashMap<>();
            options.put("modal", true);
            options.put("draggable", true);
            options.put("resizable", false);
            options.put("contentWidth", 450);
            options.put("contentHeight", 380);
            RequestContext.getCurrentInstance().openDialog("ot_implementation_form", options, params);
        }
    //    @Override
    //    public void onDialogReturn(SelectEvent event) {
    //        lazy = null;
    //       super.onDialogReturn(event);
    //
    //    }
    //     public void onDelete() {
    //        try {
    //            selected = this.service.getEntiyByPK(selected.getId());
    //        } catch (Exception ex) {
    //            LOGGER.error("Error", ex);
    //        }
    //    }
    public ImplementationOfOverTimeService getService() {
        return service;
    }

    public void setService(ImplementationOfOverTimeService service) {
        this.service = service;
    }

    public ImplementationOfOvertimeSearchParameter getSearchParameter() {
        return searchParameter;
    }

    public void setSearchParameter(ImplementationOfOvertimeSearchParameter searchParameter) {
        this.searchParameter = searchParameter;
    }

    public LazyDataModel<ImplementationOfOverTime> getLazy() {
        if (lazy == null) {
            lazy = new ImplementationOfOverTimeLazyDataModel(searchParameter, service);
        }
        return lazy;
    }

    public void setLazy(LazyDataModel<ImplementationOfOverTime> lazy) {
        this.lazy = lazy;
    }

    public ImplementationOfOverTime getSelected() {
        return selected;
    }

    public void setSelected(ImplementationOfOverTime selected) {
        this.selected = selected;
    }
    
    
}
