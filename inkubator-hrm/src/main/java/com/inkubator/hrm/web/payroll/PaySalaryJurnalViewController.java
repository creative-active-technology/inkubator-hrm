package com.inkubator.hrm.web.payroll;

import com.inkubator.hrm.web.reference.*;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.PaySalaryJurnal;
import com.inkubator.hrm.service.PaySalaryJurnalService;
import com.inkubator.hrm.web.lazymodel.PaySalaryJurnalLazyDataModel;
import com.inkubator.hrm.web.search.PaySalaryJurnalSearchParameter;
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
@ManagedBean(name = "paySalaryJurnalViewController")
@ViewScoped
public class PaySalaryJurnalViewController extends BaseController {

    private PaySalaryJurnalSearchParameter searchParameter;
    private LazyDataModel<PaySalaryJurnal> lazyDataPaySalaryJurnal;
    private PaySalaryJurnal selectedPaySalaryJurnal;
    @ManagedProperty(value = "#{paySalaryJurnalService}")
    private PaySalaryJurnalService paySalaryJurnalService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        searchParameter = new PaySalaryJurnalSearchParameter();
    }

    @PreDestroy
    public void cleanAndExit() {
        paySalaryJurnalService = null;
        searchParameter = null;
        lazyDataPaySalaryJurnal = null;
        selectedPaySalaryJurnal = null;
    }

    public void setPaySalaryJurnalService(PaySalaryJurnalService paySalaryJurnalService) {
        this.paySalaryJurnalService = paySalaryJurnalService;
    }
    

    public PaySalaryJurnalSearchParameter getSearchParameter() {
        return searchParameter;
    }

    public void setSearchParameter(PaySalaryJurnalSearchParameter searchParameter) {
        this.searchParameter = searchParameter;
    }

    

    public LazyDataModel<PaySalaryJurnal> getLazyDataPaySalaryJurnal() {
        if (lazyDataPaySalaryJurnal == null) {
            lazyDataPaySalaryJurnal = new PaySalaryJurnalLazyDataModel(searchParameter, paySalaryJurnalService);
        }
        return lazyDataPaySalaryJurnal;
    }

    public void setLazyDataPaySalaryJurnal(LazyDataModel<PaySalaryJurnal> lazyDataPaySalaryJurnal) {
        this.lazyDataPaySalaryJurnal = lazyDataPaySalaryJurnal;
    }

    public PaySalaryJurnal getSelectedPaySalaryJurnal() {
        return selectedPaySalaryJurnal;
    }

    public void setSelectedPaySalaryJurnal(PaySalaryJurnal selectedPaySalaryJurnal) {
        this.selectedPaySalaryJurnal = selectedPaySalaryJurnal;
    }

    public void doSearch() {
        lazyDataPaySalaryJurnal = null;
    }

    public void doDetail() {
        try {
            selectedPaySalaryJurnal = this.paySalaryJurnalService.getEntityByPKWithDetail(selectedPaySalaryJurnal.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public void doDelete() {
        try {
            paySalaryJurnalService.delete(selectedPaySalaryJurnal);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            LOGGER.error("Error when doDelete paySalaryJurnal ", ex);
        } catch (Exception ex) {
            LOGGER.error("Error when doDelete paySalaryJurnal", ex);
        }
    }

    public void doAdd() {
        showDialog(null);
    }

    public void doUpdate() {
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> values = new ArrayList<>();
        values.add(String.valueOf(selectedPaySalaryJurnal.getId()));
        dataToSend.put("param", values);
        showDialog(dataToSend);
    }

    private void showDialog(Map<String, List<String>> params) {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 400);
        options.put("contentHeight", 400);
        RequestContext.getCurrentInstance().openDialog("pay_salary_jurnal_form", options, params);
    }

    @Override
    public void onDialogReturn(SelectEvent event) {
        //re-calculate searching
        doSearch();
        super.onDialogReturn(event);
    }
}
