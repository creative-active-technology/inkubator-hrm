/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.organisation;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.KlasifikasiKerjaLevel;
import com.inkubator.hrm.service.KlasifikasiKerjaLevelService;
import com.inkubator.hrm.web.lazymodel.KlasifikasiKerjaLevelLazyDataModel;
import com.inkubator.hrm.web.search.KlasifikasiKerjaLevelSearchParameter;
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
@ManagedBean(name = "klasifikasiKerjaLevelViewController")
@ViewScoped
public class KlasifikasiKerjaLevelViewController extends BaseController{
    @ManagedProperty(value = "#{klasifikasiKerjaLevelService}")
    private KlasifikasiKerjaLevelService service;
    private KlasifikasiKerjaLevelSearchParameter searchParameter;
    private LazyDataModel<KlasifikasiKerjaLevel> lazy;
    private KlasifikasiKerjaLevel selected;
    
    @Override
    public void initialization(){
        super.initialization();
        searchParameter = new KlasifikasiKerjaLevelSearchParameter();
    }
    
    @PreDestroy
    public void cleanAndExit(){
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
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint",
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
        RequestContext.getCurrentInstance().openDialog("klaskerja_level_form", options, params);
    }
    
    public void doAdd(){
        showDialog(null);
    }
    
    public void doEdit(){
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> dataIsi = new ArrayList<>();
        dataIsi.add(String.valueOf(selected.getId()));
        dataToSend.put("klasifikasiKerjaLevelId", dataIsi);
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
        } catch(Exception ex){
            LOGGER.error("Error", ex);
        }
    }
    
    public String doDetail(){
        return "/protected/reference/klaskerja_level_detail.htm?faces-redirect=true&execution=e" + selected.getId();
    }

    public KlasifikasiKerjaLevelService getService() {
        return service;
    }

    public void setService(KlasifikasiKerjaLevelService service) {
        this.service = service;
    }

    public KlasifikasiKerjaLevelSearchParameter getSearchParameter() {
        return searchParameter;
    }

    public void setSearchParameter(KlasifikasiKerjaLevelSearchParameter searchParameter) {
        this.searchParameter = searchParameter;
    }

    public LazyDataModel<KlasifikasiKerjaLevel> getLazy() {
        if(lazy == null){
            lazy = new KlasifikasiKerjaLevelLazyDataModel(searchParameter, service);
        }
        return lazy;
    }

    public void setLazy(LazyDataModel<KlasifikasiKerjaLevel> lazy) {
        this.lazy = lazy;
    }

    public KlasifikasiKerjaLevel getSelected() {
        return selected;
    }

    public void setSelected(KlasifikasiKerjaLevel selected) {
        this.selected = selected;
    }
    
    
}