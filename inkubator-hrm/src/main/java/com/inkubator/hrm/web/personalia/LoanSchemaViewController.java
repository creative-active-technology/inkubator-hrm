/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.personalia;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.LoanSchema;
import com.inkubator.hrm.service.LoanSchemaService;
import com.inkubator.hrm.web.lazymodel.LoanSchemaLazyDataModel;
import com.inkubator.hrm.web.search.LoanSchemaSearchParameter;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.hibernate.exception.ConstraintViolationException;
import org.primefaces.model.LazyDataModel;
import org.springframework.dao.DataIntegrityViolationException;

/**
 *
 * @author Deni
 */
@ManagedBean(name = "loanSchemaViewController")
@ViewScoped
public class LoanSchemaViewController extends BaseController {

    @ManagedProperty(value = "#{loanSchemaService}")
    private LoanSchemaService service;
    private LoanSchemaSearchParameter searchParameter;
    private LazyDataModel<LoanSchema> lazy;
    private LoanSchema selected;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        searchParameter = new LoanSchemaSearchParameter();
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

    public void doSelectEntityWithAllRelation() {
        try {
            selected = this.service.getEntityByPkWithAllRelation(selected.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public String doDetail() {
        return "/protected/personalia/loan_schema_detail.htm?faces-redirect=true&execution=e" + selected.getId();
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

    public String doAdd() {
        return "/protected/personalia/loan_schema_form.htm?faces-redirect=true";
    }

    public String doUpdate() {
        return "/protected/personalia/loan_schema_form.htm?faces-redirect=true&execution=e" + selected.getId();
    }

    public void onDelete() {
        try {
            selected = this.service.getEntiyByPK(selected.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public LoanSchemaService getService() {
        return service;
    }

    public void setService(LoanSchemaService service) {
        this.service = service;
    }

    public LoanSchemaSearchParameter getSearchParameter() {
        return searchParameter;
    }

    public void setSearchParameter(LoanSchemaSearchParameter searchParameter) {
        this.searchParameter = searchParameter;
    }

    public LazyDataModel<LoanSchema> getLazy() {
        if (lazy == null) {
            lazy = new LoanSchemaLazyDataModel(searchParameter, service);
        }
        return lazy;
    }

    public void setLazy(LazyDataModel<LoanSchema> lazy) {
        this.lazy = lazy;
    }

    public LoanSchema getSelected() {
        return selected;
    }

    public void setSelected(LoanSchema selected) {
        this.selected = selected;
    }
}
