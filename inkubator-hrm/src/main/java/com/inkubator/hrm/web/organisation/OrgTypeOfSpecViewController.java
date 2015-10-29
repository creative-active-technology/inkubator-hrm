package com.inkubator.hrm.web.organisation;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.OrgTypeOfSpec;
import com.inkubator.hrm.service.OrgTypeOfSpecService;
import com.inkubator.hrm.web.lazymodel.OrgTypeOfSpecLazyDataModel;
import com.inkubator.hrm.web.search.OrgTypeOfSpecSearchParameter;
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author EKA
 */
@ManagedBean(name = "orgTypeOfSpecViewController")
@ViewScoped
public class OrgTypeOfSpecViewController extends BaseController{
    @ManagedProperty(value="#{orgTypeOfSpecService}")
    private OrgTypeOfSpecService service;
    private OrgTypeOfSpecSearchParameter searchParameter;
    private LazyDataModel<OrgTypeOfSpec> lazy;
    private OrgTypeOfSpec selected;
    
    @PostConstruct
    @Override
    public void initialization(){
        super.initialization();;
        searchParameter = new OrgTypeOfSpecSearchParameter();
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
            selected = this.service.getEntiyByPK(selected.getId());
        }catch(Exception ex){
            LOGGER.error("Error", ex);
        }
    }
    
    public void doDelete(){
        try{
            this.service.delete(selected);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (ConstraintViolationException | DataIntegrityViolationException ex){
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            LOGGER.error("Error", ex);
        } catch (Exception ex){
            LOGGER.error("Error", ex);
        }
    }
    
    public void showDialog(Map<String, List<String>> params){
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", true);
        options.put("contentWidth", 440);
        options.put("contentHeight", 360);
        RequestContext.getCurrentInstance().openDialog("org_type_of_spec_form", options, params);
    }
    
    public void doAdd(){
        showDialog(null);
    }
    
    public void doEdit(){
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> dataIsi = new ArrayList<>();
        dataIsi.add(String.valueOf(selected.getId()));
        dataToSend.put("orgTypeOfSpecId", dataIsi);
        showDialog(dataToSend);
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
            LOGGER.error("Error", ex);
        }
    }

    public OrgTypeOfSpecService getService() {
        return service;
    }

    public void setService(OrgTypeOfSpecService service) {
        this.service = service;
    }

    public OrgTypeOfSpecSearchParameter getSearchParameter() {
        return searchParameter;
    }

    public void setSearchParameter(OrgTypeOfSpecSearchParameter searchParameter) {
        this.searchParameter = searchParameter;
    }

    public LazyDataModel<OrgTypeOfSpec> getLazy() {
        if(lazy==null){
            lazy = new OrgTypeOfSpecLazyDataModel(searchParameter, service);
        }
        return lazy;
    }

    public void setLazy(LazyDataModel<OrgTypeOfSpec> lazy) {
        this.lazy = lazy;
    }

    public OrgTypeOfSpec getSelected() {
        return selected;
    }

    public void setSelected(OrgTypeOfSpec selected) {
        this.selected = selected;
    }
    
    
}
