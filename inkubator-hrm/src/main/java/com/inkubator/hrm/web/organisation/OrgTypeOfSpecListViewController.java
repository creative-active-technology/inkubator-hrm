/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.organisation;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.OrgTypeOfSpecList;
import com.inkubator.hrm.service.OrgTypeOfSpecListService;
import com.inkubator.hrm.web.lazymodel.OrgTypeOfSpecListLazyDataModel;
import com.inkubator.hrm.web.search.OrgTypeOfSpecListSearchParameter;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
 * @author EKA
 */
@ManagedBean(name = "orgTypeOfSpecListViewController")
@ViewScoped
public class OrgTypeOfSpecListViewController extends BaseController{
    @ManagedProperty(value = "#{orgTypeOfSpecListService}")
    private OrgTypeOfSpecListService service;
    private OrgTypeOfSpecListSearchParameter searchParameter;
    private LazyDataModel<OrgTypeOfSpecList> lazy;
    private OrgTypeOfSpecList selected;
    
    @Override
    public void initialization(){
        super.initialization();
        searchParameter = new OrgTypeOfSpecListSearchParameter();
    }
    
    @PreDestroy
    private void cleanAndExit(){
        searchParameter = null;
        lazy = null;
        service = null;
        selected = null;
    }
    
    public void doSearch(){
        lazy = null;
    }
    
    public void doSelectEntity(){
        try{
            selected = this.service.getSpecTypeNameByOrgTypeOfSpecListId(selected.getId());
        } catch (Exception ex){
            LOGGER.error("Error", ex);
        }
    }
    
    public void doDelete(){
        try{
            this.service.delete(selected);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (ConstraintViolationException | DataIntegrityViolationException ex){
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.error", "error.delete_constraint",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex){
            LOGGER.error("Error", ex);
        }
    }
    
    public void showDialog(Map<String, List<String>> params){
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", true);
        options.put("contentWidth", 400);
        options.put("contentHeight", 360);
        RequestContext.getCurrentInstance().openDialog("org_typespec_list_form", options, params);
    }
    
    /*public void doAdd(){
        showDialog(null);
    }*/
    
    public String doAdd(){
        return "/protected/organisation/org_typespec_list_form.htm?faces-redirect=true";
    }
    
    public String doUpdate() {
        return "/protected/organisation/org_typespec_list_form.htm?faces-redirect=true&execution=e" + selected.getId();
    }
    
/*    public void doEdit(){
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> dataIsi = new ArrayList<>();
        dataIsi.add(String.valueOf(selected.getId()));
        dataToSend.put("orgTypeOfSpecListId", dataIsi);
        showDialog(dataToSend);
    }*/
    
    public String doDetail(){
    	return "/protected/organisation/org_typespec_list_detail.htm?faces-redirect=true&execution=e" + selected.getId();
    }
    
    @Override
    public void onDialogReturn(SelectEvent event){
        lazy = null;
        super.onDialogReturn(event);
    }
    
    public void onDelete(){
        try{
            selected = this.service.getEntiyByPK(selected.getId());
        } catch (Exception ex){
            LOGGER.error("error", ex);
        }
    }

    public OrgTypeOfSpecListService getService() {
        return service;
    }

    public void setService(OrgTypeOfSpecListService service) {
        this.service = service;
    }

    public OrgTypeOfSpecListSearchParameter getSearchParameter() {
        return searchParameter;
    }

    public void setSearchParameter(OrgTypeOfSpecListSearchParameter searchParameter) {
        this.searchParameter = searchParameter;
    }

    public LazyDataModel<OrgTypeOfSpecList> getLazy() {
        if(lazy == null){
            lazy = new OrgTypeOfSpecListLazyDataModel(searchParameter, service);
        }
        return lazy;
    }

    public void setLazy(LazyDataModel<OrgTypeOfSpecList> lazy) {
        this.lazy = lazy;
    }

    public OrgTypeOfSpecList getSelected() {
        return selected;
    }

    public void setSelected(OrgTypeOfSpecList selected) {
        this.selected = selected;
    }
    
    
}
