package com.inkubator.hrm.web.personalia;

import com.inkubator.hrm.web.reference.*;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.Bank;
import com.inkubator.hrm.entity.LoanType;
import com.inkubator.hrm.service.BankService;
import com.inkubator.hrm.service.LoanTypeService;
import com.inkubator.hrm.web.lazymodel.BankLazyDataModel;
import com.inkubator.hrm.web.lazymodel.LoanTypeLazyDataModel;
import com.inkubator.hrm.web.search.BankSearchParameter;
import com.inkubator.hrm.web.search.LoanTypeSearchParameter;
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
 * @author Ahmad Mudzakkir Amal
 */
@ManagedBean(name = "loanTypeViewController")
@ViewScoped
public class LoanTypeViewController extends BaseController {

    private LoanTypeSearchParameter searchParameter;
    private LazyDataModel<LoanType> lazyDataLoanType;
    private LoanType selectedLoanType;
    @ManagedProperty(value = "#{loanTypeService}")
    private LoanTypeService loanTypeService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        searchParameter = new LoanTypeSearchParameter();
    }

    @PreDestroy
    public void cleanAndExit() {
        loanTypeService = null;
        searchParameter = null;
        lazyDataLoanType = null;
        selectedLoanType = null;
    }

    

    public LoanTypeSearchParameter getSearchParameter() {
        return searchParameter;
    }

    public void setSearchParameter(LoanTypeSearchParameter searchParameter) {
        this.searchParameter = searchParameter;
    }

    public LazyDataModel<LoanType> getLazyDataLoanType() {
         if (lazyDataLoanType == null) {
            lazyDataLoanType = new LoanTypeLazyDataModel(searchParameter, loanTypeService);
        }
        return lazyDataLoanType;
    }

    public void setLazyDataLoanType(LazyDataModel<LoanType> lazyDataLoanType) {
        this.lazyDataLoanType = lazyDataLoanType;
    }

    public LoanTypeService getLoanTypeService() {
        return loanTypeService;
    }

    public void setLoanTypeService(LoanTypeService loanTypeService) {
        this.loanTypeService = loanTypeService;
    }

    

    public void doSearch() {
        lazyDataLoanType = null;
    }

    public void doDetail() {
        try {
            selectedLoanType = this.loanTypeService.getEntiyByPK(selectedLoanType.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public void doDelete() {
        try {
            loanTypeService.delete(selectedLoanType);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            LOGGER.error("Error when doDelete LoanType ", ex);
        } catch (Exception ex) {
            LOGGER.error("Error when doDelete LoanType", ex);
        }
    }

    public void doAdd() {
        showDialog(null);
    }

    public void doUpdate() {
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> values = new ArrayList<>();
        values.add(String.valueOf(selectedLoanType.getId()));
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
        RequestContext.getCurrentInstance().openDialog("loan_type_form", options, params);
    }

    @Override
    public void onDialogReturn(SelectEvent event) {
        //re-calculate searching
        doSearch();
        super.onDialogReturn(event);
    }

    public LoanType getSelectedLoanType() {
        return selectedLoanType;
    }

    public void setSelectedLoanType(LoanType selectedLoanType) {
        this.selectedLoanType = selectedLoanType;
    }
    
    
}
