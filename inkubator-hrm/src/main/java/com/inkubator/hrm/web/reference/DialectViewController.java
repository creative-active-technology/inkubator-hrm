package com.inkubator.hrm.web.reference;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.Dialect;
import com.inkubator.hrm.service.DialectService;
import com.inkubator.hrm.web.lazymodel.DialectLazyDataModel;
import com.inkubator.hrm.web.search.DialectSearchParameter;
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
 * @author Taufik Hidayat
 */
@ManagedBean(name = "dialectViewController")
@ViewScoped
public class DialectViewController extends BaseController {

    private DialectSearchParameter searchParameter;
    private LazyDataModel<Dialect> lazyDataDialect;
    private Dialect selectedDialect;
    @ManagedProperty(value = "#{dialectService}")
    private DialectService dialectService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        searchParameter = new DialectSearchParameter();
    }

    @PreDestroy
    public void cleanAndExit() {
        dialectService = null;
        searchParameter = null;
        lazyDataDialect = null;
        selectedDialect = null;
    }

    public void setDialectService(DialectService dialectService) {
        this.dialectService = dialectService;
    }

    public DialectSearchParameter getSearchParameter() {
        return searchParameter;
    }

    public void setSearchParameter(DialectSearchParameter searchParameter) {
        this.searchParameter = searchParameter;
    }

    

    public LazyDataModel<Dialect> getLazyDataDialect() {
        if (lazyDataDialect == null) {
            lazyDataDialect = new DialectLazyDataModel(searchParameter, dialectService);
        }
        return lazyDataDialect;
    }

    public void setLazyDataDialect(LazyDataModel<Dialect> lazyDataDialect) {
        this.lazyDataDialect = lazyDataDialect;
    }

    public Dialect getSelectedDialect() {
        return selectedDialect;
    }

    public void setSelectedDialect(Dialect selectedDialect) {
        this.selectedDialect = selectedDialect;
    }

    public void doSearch() {
        lazyDataDialect = null;
    }

    public void doDetail() {
        try {
            selectedDialect = this.dialectService.getEntiyByPK(selectedDialect.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public void doDelete() {
        try {
            dialectService.delete(selectedDialect);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            LOGGER.error("Error when doDelete dialect ", ex);
        } catch (Exception ex) {
            LOGGER.error("Error when doDelete dialect", ex);
        }
    }

    public void doAdd() {
        showDialog(null);
    }

    public void doUpdate() {
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> values = new ArrayList<>();
        values.add(String.valueOf(selectedDialect.getId()));
        dataToSend.put("param", values);
        showDialog(dataToSend);
    }

    private void showDialog(Map<String, List<String>> params) {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 400);
        options.put("contentHeight", 350);
        RequestContext.getCurrentInstance().openDialog("dialect_form", options, params);
    }

    @Override
    public void onDialogReturn(SelectEvent event) {
        //re-calculate searching
        doSearch();
        super.onDialogReturn(event);
    }
}
