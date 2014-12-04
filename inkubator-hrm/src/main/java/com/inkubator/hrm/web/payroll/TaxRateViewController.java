package com.inkubator.hrm.web.payroll;

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

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.TaxRate;
import com.inkubator.hrm.service.TaxRateService;
import com.inkubator.hrm.web.lazymodel.TaxRateLazyDataModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "taxRateViewController")
@ViewScoped
public class TaxRateViewController extends BaseController {

    private LazyDataModel<TaxRate> lazyDataModel;
    private TaxRate selectedTaxRate;
    @ManagedProperty(value = "#{taxRateService}")
    private TaxRateService taxRateService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
    }

    @PreDestroy
    public void cleanAndExit() {
        taxRateService = null;
        lazyDataModel = null;
        selectedTaxRate = null;
    }

	public LazyDataModel<TaxRate> getLazyDataModel() {
		if(lazyDataModel == null){
			lazyDataModel = new TaxRateLazyDataModel(taxRateService);
		}
		return lazyDataModel;
	}

	public void setLazyDataModel(LazyDataModel<TaxRate> lazyDataModel) {
		this.lazyDataModel = lazyDataModel;
	}

	public TaxRate getSelectedTaxRate() {
		return selectedTaxRate;
	}

	public void setSelectedTaxRate(TaxRate selectedTaxRate) {
		this.selectedTaxRate = selectedTaxRate;
	}

	public TaxRateService getTaxRateService() {
		return taxRateService;
	}

	public void setTaxRateService(TaxRateService taxRateService) {
		this.taxRateService = taxRateService;
	}

	public void doSearch() {
        lazyDataModel = null;
    }

    public void doSelectEntity() {
        try {
            selectedTaxRate = this.taxRateService.getEntiyByPK(selectedTaxRate.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public void doDelete() {
        try {
            taxRateService.delete(selectedTaxRate);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            LOGGER.error("Error when doDelete ", ex);
        } catch (Exception ex) {
            LOGGER.error("Error when doDelete ", ex);
        }
    }

    public void doAdd() {
        showDialog(null);
    }

    public void doUpdate() {
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> values = new ArrayList<>();
        values.add(String.valueOf(selectedTaxRate.getId()));
        dataToSend.put("param", values);
        showDialog(dataToSend);
    }

    private void showDialog(Map<String, List<String>> params) {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 400);
        options.put("contentHeight", 320);
        RequestContext.getCurrentInstance().openDialog("tax_rate_form", options, params);
    }

    @Override
    public void onDialogReturn(SelectEvent event) {
        //re-calculate searching
        doSearch();
        super.onDialogReturn(event);
    }
}
