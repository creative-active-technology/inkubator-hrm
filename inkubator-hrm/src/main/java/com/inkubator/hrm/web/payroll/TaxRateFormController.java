package com.inkubator.hrm.web.payroll;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.TaxRate;
import com.inkubator.hrm.service.TaxRateService;
import com.inkubator.hrm.web.model.TaxRateModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "taxRateFormController")
@ViewScoped
public class TaxRateFormController extends BaseController {

    private TaxRateModel model;
    private Boolean isUpdate;
    @ManagedProperty(value = "#{taxRateService}")
    private TaxRateService taxRateService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        String param = FacesUtil.getRequestParameter("param");
        model = new TaxRateModel();
        isUpdate = Boolean.FALSE;
        if (StringUtils.isNumeric(param)) {
            try {
                TaxRate taxRate = taxRateService.getEntiyByPK(Long.parseLong(param));
                if (taxRate != null) {
                    getViewModelFromEntity(taxRate);
                    isUpdate = Boolean.TRUE;
                }
            } catch (Exception e) {
                LOGGER.error("Error", e);
            }
        }
    }

    @PreDestroy
    public void cleanAndExit() {
    	taxRateService = null;
        model = null;
        isUpdate = null;
    }

	public TaxRateModel getModel() {
		return model;
	}

	public void setModel(TaxRateModel model) {
		this.model = model;
	}

	public Boolean getIsUpdate() {
		return isUpdate;
	}

	public void setIsUpdate(Boolean isUpdate) {
		this.isUpdate = isUpdate;
	}

	public TaxRateService getTaxRateService() {
		return taxRateService;
	}

	public void setTaxRateService(TaxRateService taxRateService) {
		this.taxRateService = taxRateService;
	}

	public void doSave() {
		TaxRate taxRate = getEntityFromViewModel(model);
        try {
            if (isUpdate) {
            	taxRateService.update(taxRate);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
            	taxRateService.save(taxRate);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch (BussinessException ex) { 
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    private TaxRate getEntityFromViewModel(TaxRateModel model) {
    	TaxRate taxRate = new TaxRate();
        if (model.getId() != null) {
        	taxRate.setId(model.getId());
        }
        taxRate.setLowRate(model.getLowRate());
        taxRate.setTopRate(model.getTopRate());
        taxRate.setPercentRate(model.getPercentRate());
        return taxRate;
    }
    
    private void getViewModelFromEntity(TaxRate taxRate){
    	model.setId(taxRate.getId());
    	model.setLowRate(taxRate.getLowRate());
    	model.setTopRate(taxRate.getTopRate());
    	model.setPercentRate(taxRate.getPercentRate());
    }
}
