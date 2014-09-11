/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.approval;

import com.inkubator.hrm.entity.ApprovalActivity;
import com.inkubator.hrm.service.ApprovalActivityService;
import com.inkubator.hrm.web.lazymodel.ApprovalActivityLazyDataModel;
import com.inkubator.hrm.web.search.ApprovalActivitySearchParameter;
import com.inkubator.webcore.controller.BaseController;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author Deni
 */
@ManagedBean(name = "approvalActivityViewController")
@ViewScoped
public class ApprovalActivityViewController extends BaseController{
    @ManagedProperty(value = "#{approvalActivityService}")
    private ApprovalActivityService service;
    private ApprovalActivitySearchParameter searchParameter;
    private LazyDataModel<ApprovalActivity> lazy;
    private ApprovalActivity selected;

    
    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        searchParameter = new ApprovalActivitySearchParameter();
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
    
    public String doDetail(){
        return "/protected/approval/approval_activity_detail.htm?faces-redirect=true&execution=e" + selected.getId();
    }
    
    public void doSelectEntityWithAllRelation() {
        try {
            selected = this.service.getEntityByPkWithAllRelation(selected.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
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

    public ApprovalActivityService getService() {
        return service;
    }

    public void setService(ApprovalActivityService service) {
        this.service = service;
    }

    public ApprovalActivitySearchParameter getSearchParameter() {
        return searchParameter;
    }

    public void setSearchParameter(ApprovalActivitySearchParameter searchParameter) {
        this.searchParameter = searchParameter;
    }

    public LazyDataModel<ApprovalActivity> getLazy() {
        if (lazy == null) {
            lazy = new ApprovalActivityLazyDataModel(searchParameter, service);
        }
        return lazy;
    }

    public void setLazy(LazyDataModel<ApprovalActivity> lazy) {
        this.lazy = lazy;
    }

    public ApprovalActivity getSelected() {
        return selected;
    }

    public void setSelected(ApprovalActivity selected) {
        this.selected = selected;
    }

    
}
