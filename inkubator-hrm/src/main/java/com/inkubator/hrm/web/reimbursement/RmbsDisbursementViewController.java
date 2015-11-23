package com.inkubator.hrm.web.reimbursement;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.LazyDataModel;

import com.inkubator.hrm.entity.RmbsApplicationDisbursement;
import com.inkubator.hrm.service.RmbsDisbursementService;
import com.inkubator.hrm.web.lazymodel.RmbsDisbursementLazyDataModel;
import com.inkubator.hrm.web.search.RmbsDisbursementSearchParameter;
import com.inkubator.webcore.controller.BaseController;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "rmbsDisbursementViewController")
@ViewScoped
public class RmbsDisbursementViewController extends BaseController {

    private RmbsDisbursementSearchParameter parameter;
    private LazyDataModel<RmbsApplicationDisbursement> lazyData;
    private RmbsApplicationDisbursement selected;
    @ManagedProperty(value = "#{rmbsDisbursementService}")
    private RmbsDisbursementService rmbsDisbursementService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        parameter = new RmbsDisbursementSearchParameter();
    }

    @PreDestroy
    public void cleanAndExit() {
    	rmbsDisbursementService = null;
        parameter = null;
        lazyData = null;
        selected = null;
    }

    public void doSearch() {
        lazyData = null;
    }

	public RmbsDisbursementSearchParameter getParameter() {
		return parameter;
	}

	public void setParameter(RmbsDisbursementSearchParameter parameter) {
		this.parameter = parameter;
	}

	public LazyDataModel<RmbsApplicationDisbursement> getLazyData() {
		if(lazyData == null){
			lazyData = new RmbsDisbursementLazyDataModel(parameter, rmbsDisbursementService);
		}
		return lazyData;
	}

	public void setLazyData(LazyDataModel<RmbsApplicationDisbursement> lazyData) {
		this.lazyData = lazyData;
	}

	public RmbsApplicationDisbursement getSelected() {
		return selected;
	}

	public void setSelected(RmbsApplicationDisbursement selected) {
		this.selected = selected;
	}

	public RmbsDisbursementService getRmbsDisbursementService() {
		return rmbsDisbursementService;
	}

	public void setRmbsDisbursementService(RmbsDisbursementService rmbsDisbursementService) {
		this.rmbsDisbursementService = rmbsDisbursementService;
	}

}
