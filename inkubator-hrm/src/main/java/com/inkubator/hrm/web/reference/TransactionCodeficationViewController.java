package com.inkubator.hrm.web.reference;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.TransactionCodefication;
import com.inkubator.hrm.service.TransactionCodeficationService;
import com.inkubator.hrm.web.lazymodel.TransactionCodeficationLazyDataModel;
import com.inkubator.hrm.web.search.TransactionCodeficationSearchParameter;
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
@ManagedBean(name = "transactionCodeficationViewController")
@ViewScoped
public class TransactionCodeficationViewController extends BaseController {

    private TransactionCodeficationSearchParameter searchParameter;
    private LazyDataModel<TransactionCodefication> lazyDataTransactionCodefication;
    private TransactionCodefication selectedTransactionCodefication;
    @ManagedProperty(value = "#{transactionCodeficationService}")
    private TransactionCodeficationService transactionCodeficationService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        searchParameter = new TransactionCodeficationSearchParameter();
    }

    @PreDestroy
    public void cleanAndExit() {
        transactionCodeficationService = null;
        searchParameter = null;
        lazyDataTransactionCodefication = null;
        selectedTransactionCodefication = null;
    }

    public void setTransactionCodeficationService(TransactionCodeficationService transactionCodeficationService) {
        this.transactionCodeficationService = transactionCodeficationService;
    }

    public TransactionCodeficationSearchParameter getSearchParameter() {
        return searchParameter;
    }

    public void setSearchParameter(TransactionCodeficationSearchParameter searchParameter) {
        this.searchParameter = searchParameter;
    }

    

    public LazyDataModel<TransactionCodefication> getLazyDataTransactionCodefication() {
        if (lazyDataTransactionCodefication == null) {
            lazyDataTransactionCodefication = new TransactionCodeficationLazyDataModel(searchParameter, transactionCodeficationService);
        }
        return lazyDataTransactionCodefication;
    }

    public void setLazyDataTransactionCodefication(LazyDataModel<TransactionCodefication> lazyDataTransactionCodefication) {
        this.lazyDataTransactionCodefication = lazyDataTransactionCodefication;
    }

    public TransactionCodefication getSelectedTransactionCodefication() {
        return selectedTransactionCodefication;
    }

    public void setSelectedTransactionCodefication(TransactionCodefication selectedTransactionCodefication) {
        this.selectedTransactionCodefication = selectedTransactionCodefication;
    }

    public void doSearch() {
        lazyDataTransactionCodefication = null;
    }

    public void doDetail() {
        try {
            selectedTransactionCodefication = this.transactionCodeficationService.getEntiyByPK(selectedTransactionCodefication.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public void doDelete() {
        try {
            transactionCodeficationService.delete(selectedTransactionCodefication);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            LOGGER.error("Error when doDelete transactionCodefication ", ex);
        } catch (Exception ex) {
            LOGGER.error("Error when doDelete transactionCodefication", ex);
        }
    }

    public void doAdd() {
        showDialog(null);
    }

    public void doUpdate() {
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> values = new ArrayList<>();
        values.add(String.valueOf(selectedTransactionCodefication.getId()));
        dataToSend.put("param", values);
        showDialog(dataToSend);
    }

    private void showDialog(Map<String, List<String>> params) {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 470);
        options.put("contentHeight", 390);
        RequestContext.getCurrentInstance().openDialog("codefication_form", options, params);
    }

    @Override
    public void onDialogReturn(SelectEvent event) {
        //re-calculate searching
        doSearch();
        super.onDialogReturn(event);
    }
}
