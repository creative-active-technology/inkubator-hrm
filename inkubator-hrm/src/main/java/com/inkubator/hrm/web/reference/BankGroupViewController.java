/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.reference;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.BankGroup;
import com.inkubator.hrm.service.BankGroupService;
import com.inkubator.hrm.web.lazymodel.BankGroupLazyDataModel;
import com.inkubator.hrm.web.search.BankGroupSearchParameter;
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
 * @author EKA
 */
@ManagedBean(name = "bankGroupViewController")
@ViewScoped
public class BankGroupViewController extends BaseController{
    @ManagedProperty(value = "#{bankGroupService}")
    private BankGroupService service;
    private BankGroupSearchParameter searchParameter;
    private LazyDataModel<BankGroup> lazy;
    private BankGroup selected;
    
    @PostConstruct
    @Override
    public void initialization(){
        super.initialization();
        searchParameter = new BankGroupSearchParameter();
    }
    
    @PreDestroy
    private void cleanAndExit(){
        searchParameter = null;
        lazy = null;
        service = null;
        selected = null;
    }
    
    public void doSearch(){
        System.out.println("search");
        lazy = null;
    }
    
    public void doSelectEntity(){
        try{
            selected = this.service.getEntiyByPK(selected.getId());
        }catch (Exception ex){
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
        options.put("contentWidth", 400);
        options.put("contentHeight", 360);
        RequestContext.getCurrentInstance().openDialog("bank_group_form", options, params);
    }
    
    public void doAdd(){
        showDialog(null);
    }
    
    public void doEdit(){
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> dataIsi = new ArrayList<>();
        dataIsi.add(String.valueOf(selected.getId()));
        dataToSend.put("bankGroupId", dataIsi);
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

    public BankGroupService getService() {
        return service;
    }

    public void setService(BankGroupService service) {
        this.service = service;
    }

    public BankGroupSearchParameter getSearchParameter() {
        return searchParameter;
    }

    public void setSearchParameter(BankGroupSearchParameter searchParameter) {
        this.searchParameter = searchParameter;
    }

    public LazyDataModel<BankGroup> getLazy() {
        if(lazy == null){
            lazy = new BankGroupLazyDataModel(searchParameter, service);
        }
        return lazy;
    }

    public void setLazy(LazyDataModel<BankGroup> lazy) {
        this.lazy = lazy;
    }

    public BankGroup getSelected() {
        return selected;
    }

    public void setSelected(BankGroup selected) {
        this.selected = selected;
    }
    
    
}
