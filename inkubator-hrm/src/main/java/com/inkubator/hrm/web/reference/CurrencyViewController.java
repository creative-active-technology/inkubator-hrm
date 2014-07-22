/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.reference;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.Currency;
import com.inkubator.hrm.service.CurrencyService;
import com.inkubator.hrm.web.lazymodel.CurrencyLazyDataModel;
import com.inkubator.hrm.web.search.CurrencySearchParameter;
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
@ManagedBean(name = "currencyViewController")
@ViewScoped
public class CurrencyViewController extends BaseController{
    @ManagedProperty(value = "#{currencyService}")
    private CurrencyService service;
    private CurrencySearchParameter search;
    private LazyDataModel<Currency> lazy;
    private Currency selected;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        search = new CurrencySearchParameter();
    }

    @PreDestroy
    public void cleanAndExit() {
        service = null;
        search = null;
        lazy = null;
        selected = null;
    }

    public CurrencyService getService() {
        return service;
    }

    public void setService(CurrencyService service) {
        this.service = service;
    }

    public CurrencySearchParameter getSearch() {
        return search;
    }

    public void setSearch(CurrencySearchParameter search) {
        this.search = search;
    }

    public LazyDataModel<Currency> getLazy() {
        if (lazy == null) {
            lazy = new CurrencyLazyDataModel(search, service);
        }
        return lazy;
    }

    public void setLazy(LazyDataModel<Currency> lazy) {
        this.lazy = lazy;
    }

    public Currency getSelected() {
        return selected;
    }

    public void setSelected(Currency selected) {
        this.selected = selected;
    }
    
    public void doSearch() {
        lazy = null;
    }
    
    public void doSelectEntity() {
        try {
            selected = this.service.getCurrencyByIdWithCountry(selected.getId());
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
    
    public void doAdd() {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 400);
        options.put("contentHeight", 430);
        RequestContext.getCurrentInstance().openDialog("currency_form", options, null);
    }
    
    public void doEdit() {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 400);
        options.put("contentHeight", 430);
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> dataIsi = new ArrayList<>();
        dataIsi.add(String.valueOf(selected.getId()));
        dataToSend.put("param", dataIsi);
        RequestContext.getCurrentInstance().openDialog("currency_form", options, dataToSend);
    }
    
    @Override
    public void onDialogReturn(SelectEvent event) {
        lazy = null;
       super.onDialogReturn(event);

    }
}
