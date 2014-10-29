/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.account;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.IpPermit;
import com.inkubator.hrm.service.IpPermitService;
import com.inkubator.hrm.web.lazymodel.IpPermitLazyDataModel;
import com.inkubator.hrm.web.search.IpPermitSearchParameter;
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
@ManagedBean(name = "ipPermitViewController")
@ViewScoped
public class IpPermitViewController extends BaseController{
    @ManagedProperty(value = "#{ipPermitService}")
    private IpPermitService service;
    private IpPermitSearchParameter searchParameter;
    private LazyDataModel<IpPermit> lazy;
    private IpPermit selected;
    
    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        searchParameter = new IpPermitSearchParameter();
    }
    
    @PreDestroy
    private void cleanAndExit() {
        searchParameter = null;
        lazy = null;
        service = null;
        selected = null;
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
    
    public void showDialog(Map<String, List<String>> params){
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 400);
        options.put("contentHeight", 280);
        RequestContext.getCurrentInstance().openDialog("ip_permit_form", options, params);
    }
    
    public void doAdd() {
        showDialog(null);
    }
    
    public void doEdit() {
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> values = new ArrayList<>();
        values.add(String.valueOf(selected.getId()));
        dataToSend.put("param", values);
        showDialog(dataToSend);
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

    public IpPermitService getService() {
        return service;
    }

    public void setService(IpPermitService service) {
        this.service = service;
    }

    public IpPermitSearchParameter getSearchParameter() {
        return searchParameter;
    }

    public void setSearchParameter(IpPermitSearchParameter searchParameter) {
        this.searchParameter = searchParameter;
    }

    public LazyDataModel<IpPermit> getLazy() {
        if (lazy == null) {
            lazy = new IpPermitLazyDataModel(searchParameter, service);
        }
        return lazy;
    }

    public void setLazy(LazyDataModel<IpPermit> lazy) {
        this.lazy = lazy;
    }

    public IpPermit getSelected() {
        return selected;
    }

    public void setSelected(IpPermit selected) {
        this.selected = selected;
    }
     
     
}
