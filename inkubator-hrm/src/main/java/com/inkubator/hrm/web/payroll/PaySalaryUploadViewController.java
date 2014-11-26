package com.inkubator.hrm.web.payroll;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.LazyDataModel;

import com.inkubator.hrm.entity.PaySalaryComponent;
import com.inkubator.hrm.service.PaySalaryComponentService;
import com.inkubator.hrm.web.lazymodel.PaySalaryUploadLazyDataModel;
import com.inkubator.hrm.web.search.PaySalaryComponentSearchParameter;
import com.inkubator.webcore.controller.BaseController;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "paySalaryUploadViewController")
@ViewScoped
public class PaySalaryUploadViewController extends BaseController {

    private PaySalaryComponentSearchParameter parameter;
    private LazyDataModel<PaySalaryComponent> lazyDataModel;
    private PaySalaryComponent selectedPaySalaryComponent;
    @ManagedProperty(value = "#{paySalaryComponentService}")
    private PaySalaryComponentService paySalaryComponentService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        parameter =  new PaySalaryComponentSearchParameter();
    }

    @PreDestroy
    public void cleanAndExit() {
    	paySalaryComponentService = null;
        parameter = null;
        lazyDataModel = null;
        selectedPaySalaryComponent = null;
    }

	public PaySalaryComponentSearchParameter getParameter() {
		return parameter;
	}

	public void setParameter(PaySalaryComponentSearchParameter parameter) {
		this.parameter = parameter;
	}

	public LazyDataModel<PaySalaryComponent> getLazyDataModel() {
		if(lazyDataModel == null){
			lazyDataModel =  new PaySalaryUploadLazyDataModel(parameter, paySalaryComponentService);
		}
		return lazyDataModel;
	}

	public void setLazyDataModel(LazyDataModel<PaySalaryComponent> lazyDataModel) {
		this.lazyDataModel = lazyDataModel;
	}

	public PaySalaryComponent getSelectedPaySalaryComponent() {
		return selectedPaySalaryComponent;
	}

	public void setSelectedPaySalaryComponent(
			PaySalaryComponent selectedPaySalaryComponent) {
		this.selectedPaySalaryComponent = selectedPaySalaryComponent;
	}

	public PaySalaryComponentService getPaySalaryComponentService() {
		return paySalaryComponentService;
	}

	public void setPaySalaryComponentService(
			PaySalaryComponentService paySalaryComponentService) {
		this.paySalaryComponentService = paySalaryComponentService;
	}

	public void doSearch() {
        lazyDataModel = null;
    }
    
    public String doDetail() {
        return "/protected/payroll/pay_salary_upload_detail.htm?faces-redirect=true&execution=e" + selectedPaySalaryComponent.getId();
    }
}
