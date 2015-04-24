package com.inkubator.hrm.web.loan;

import com.inkubator.hrm.web.reimbursement.*;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.LazyDataModel;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.service.RmbsCancelationService;
import com.inkubator.hrm.web.lazymodel.RmbsCancelationLazyDataModel;
import com.inkubator.hrm.web.model.RmbsCancelationViewModel;
import com.inkubator.hrm.web.search.RmbsCancelationSearchParameter;
import com.inkubator.securitycore.util.UserInfoUtil;
import com.inkubator.webcore.controller.BaseController;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
@ManagedBean(name = "loanNewCancelationBoxViewController")
@ViewScoped
public class LoanNewCancelationBoxViewController extends BaseController {

	private Boolean isAdministrator;
    private RmbsCancelationSearchParameter parameter;
    private LazyDataModel<RmbsCancelationViewModel> lazyData;
    @ManagedProperty(value = "#{rmbsCancelationService}")
    private RmbsCancelationService rmbsCancelationService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        parameter = new RmbsCancelationSearchParameter();
        isAdministrator = UserInfoUtil.hasRole(HRMConstant.ADMINISTRATOR_ROLE);
        if(!isAdministrator){
        	parameter.setUserId(UserInfoUtil.getUserName());
        }
    }

    @PreDestroy
    public void cleanAndExit() {
    	rmbsCancelationService = null;
        parameter = null;
        lazyData = null;
        isAdministrator = null;
    }

    public void doSearch() {
        lazyData = null;
    }

	public RmbsCancelationSearchParameter getParameter() {
		return parameter;
	}

	public void setParameter(RmbsCancelationSearchParameter parameter) {
		this.parameter = parameter;
	}

	public LazyDataModel<RmbsCancelationViewModel> getLazyData() {
		if(lazyData == null){
			lazyData = new RmbsCancelationLazyDataModel(parameter, rmbsCancelationService);
		}
		return lazyData;
	}

	public void setLazyData(LazyDataModel<RmbsCancelationViewModel> lazyData) {
		this.lazyData = lazyData;
	}

	public RmbsCancelationService getRmbsCancelationService() {
		return rmbsCancelationService;
	}

	public void setRmbsCancelationService(
			RmbsCancelationService rmbsCancelationService) {
		this.rmbsCancelationService = rmbsCancelationService;
	}

	public Boolean getIsAdministrator() {
		return isAdministrator;
	}

	public void setIsAdministrator(Boolean isAdministrator) {
		this.isAdministrator = isAdministrator;
	}
        
}
