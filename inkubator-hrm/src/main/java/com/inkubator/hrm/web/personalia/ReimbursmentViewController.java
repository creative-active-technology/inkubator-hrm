/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.personalia;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.Reimbursment;
import com.inkubator.hrm.entity.ReimbursmentSchema;
import com.inkubator.hrm.service.ReimbursmentService;
import com.inkubator.hrm.web.lazymodel.ReimbursmentLazyDataModel;
import com.inkubator.hrm.web.search.ReimbursmentSearchParameter;
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
 * @author Deni
 */
@ManagedBean(name = "reimbursmentViewController")
@ViewScoped
public class ReimbursmentViewController extends BaseController{
    @ManagedProperty(value = "#{reimbursmentService}")
    private ReimbursmentService service;
    private ReimbursmentSearchParameter searchParameter;
    private LazyDataModel<Reimbursment> lazy;
    private Reimbursment selected;
    
    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        searchParameter = new ReimbursmentSearchParameter();
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
    
    public String doDetail(){
        return "/protected/personalia/reimbursment_detail.htm?faces-redirect=true&execution=e" + selected.getId();
    }
    
    public String add(){
        return "/protected/personalia/reimbursment_form.htm?faces-redirect=true";
    }
    
    public String edit(){
        return "/protected/personalia/reimbursment_form.htm?faces-redirect=true&execution=e" + selected.getId();
    }
    
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
//        return "/protected/personalia/reimbursment_form.htm?faces-redirect=true";
    }
    
    public void doUpdate() {
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> values = new ArrayList<>();
        values.add(String.valueOf(selected.getId()));
        dataToSend.put("reimbursmentId", values);
        showDialog(dataToSend);
//        return "/protected/personalia/reimbursment_form.htm?faces-redirect=true&execution=e" + selected.getId();
    }
    
    private void showDialog(Map<String, List<String>> params) {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 430);
        options.put("contentHeight", 380);
        RequestContext.getCurrentInstance().openDialog("reimbursment_form", options, params);
    }
    
    @Override
    public void onDialogReturn(SelectEvent event) {
        lazy = null;
       super.onDialogReturn(event);

    }
    
     public void onDelete() {
        try {
            selected = this.service.getEntiyByPK(selected.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public ReimbursmentService getService() {
        return service;
    }

    public void setService(ReimbursmentService service) {
        this.service = service;
    }

    public ReimbursmentSearchParameter getSearchParameter() {
        return searchParameter;
    }

    public void setSearchParameter(ReimbursmentSearchParameter searchParameter) {
        this.searchParameter = searchParameter;
    }

    public LazyDataModel<Reimbursment> getLazy() {
        if (lazy == null) {
            lazy = new ReimbursmentLazyDataModel(searchParameter, service);
        }
        return lazy;
    }

    public void setLazy(LazyDataModel<Reimbursment> lazy) {
        this.lazy = lazy;
    }

    public Reimbursment getSelected() {
        return selected;
    }

    public void setSelected(Reimbursment selected) {
        this.selected = selected;
    }
     
    
}
