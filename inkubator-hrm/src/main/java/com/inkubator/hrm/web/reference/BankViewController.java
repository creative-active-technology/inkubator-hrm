package com.inkubator.hrm.web.reference;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.Bank;
import com.inkubator.hrm.service.BankService;
import com.inkubator.hrm.web.lazymodel.BankLazyDataModel;
import com.inkubator.hrm.web.search.BankSearchParameter;
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
@ManagedBean(name = "bankViewController")
@ViewScoped
public class BankViewController extends BaseController {

    private BankSearchParameter searchParameter;
    private LazyDataModel<Bank> lazyDataBank;
    private Bank selectedBank;
    @ManagedProperty(value = "#{bankService}")
    private BankService bankService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        searchParameter = new BankSearchParameter();
    }

    @PreDestroy
    public void cleanAndExit() {
        bankService = null;
        searchParameter = null;
        lazyDataBank = null;
        selectedBank = null;
    }

    public void setBankService(BankService bankService) {
        this.bankService = bankService;
    }

    public BankSearchParameter getSearchParameter() {
        return searchParameter;
    }

    public void setSearchParameter(BankSearchParameter searchParameter) {
        this.searchParameter = searchParameter;
    }

    

    public LazyDataModel<Bank> getLazyDataBank() {
        if (lazyDataBank == null) {
            lazyDataBank = new BankLazyDataModel(searchParameter, bankService);
        }
        return lazyDataBank;
    }

    public void setLazyDataBank(LazyDataModel<Bank> lazyDataBank) {
        this.lazyDataBank = lazyDataBank;
    }

    public Bank getSelectedBank() {
        return selectedBank;
    }

    public void setSelectedBank(Bank selectedBank) {
        this.selectedBank = selectedBank;
    }

    public void doSearch() {
        lazyDataBank = null;
    }

    public void doDetail() {
        try {
            selectedBank = this.bankService.getEntiyByPK(selectedBank.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public void doDelete() {
        try {
            bankService.delete(selectedBank);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            LOGGER.error("Error when doDelete bank ", ex);
        } catch (Exception ex) {
            LOGGER.error("Error when doDelete bank", ex);
        }
    }

    public void doAdd() {
        showDialog(null);
    }

    public void doUpdate() {
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> values = new ArrayList<>();
        values.add(String.valueOf(selectedBank.getId()));
        dataToSend.put("param", values);
        showDialog(dataToSend);
    }

    private void showDialog(Map<String, List<String>> params) {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 550);
        options.put("contentHeight", 475);
        RequestContext.getCurrentInstance().openDialog("bank_form", options, params);
    }

    @Override
    public void onDialogReturn(SelectEvent event) {
        //re-calculate searching
        doSearch();
        super.onDialogReturn(event);
    }
}
