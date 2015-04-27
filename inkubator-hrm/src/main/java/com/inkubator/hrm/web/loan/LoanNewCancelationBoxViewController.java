package com.inkubator.hrm.web.loan;

import com.inkubator.hrm.web.reimbursement.*;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.LazyDataModel;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.service.LoanNewCancelationService;
import com.inkubator.hrm.service.RmbsCancelationService;
import com.inkubator.hrm.web.lazymodel.LoanNewCancelationBoxLazyDataModel;
import com.inkubator.hrm.web.lazymodel.RmbsCancelationLazyDataModel;
import com.inkubator.hrm.web.model.LoanNewCancelationBoxViewModel;
import com.inkubator.hrm.web.model.RmbsCancelationViewModel;
import com.inkubator.hrm.web.search.LoanNewCancelationBoxSearchParameter;
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
    private LoanNewCancelationBoxSearchParameter parameter;
    private LazyDataModel<LoanNewCancelationBoxViewModel> lazyData;
    @ManagedProperty(value = "#{loanNewCancelationService}")
    private LoanNewCancelationService loanNewCancelationService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        parameter = new LoanNewCancelationBoxSearchParameter();
        isAdministrator = UserInfoUtil.hasRole(HRMConstant.ADMINISTRATOR_ROLE);
        if(!isAdministrator){
        	parameter.setUserId(UserInfoUtil.getUserName());
        }
    }

    @PreDestroy
    public void cleanAndExit() {
    	loanNewCancelationService = null;
        parameter = null;
        lazyData = null;
        isAdministrator = null;
    }

    public void doSearch() {
        lazyData = null;
    }

	public LoanNewCancelationBoxSearchParameter getParameter() {
		return parameter;
	}

	public void setParameter(LoanNewCancelationBoxSearchParameter parameter) {
		this.parameter = parameter;
	}

	public LazyDataModel<LoanNewCancelationBoxViewModel> getLazyData() {
		if(lazyData == null){
			lazyData = new LoanNewCancelationBoxLazyDataModel(parameter, loanNewCancelationService);
		}
		return lazyData;
	}

	public void setLazyData(LazyDataModel<LoanNewCancelationBoxViewModel> lazyData) {
		this.lazyData = lazyData;
	}

        public void setLoanNewCancelationService(LoanNewCancelationService loanNewCancelationService) {
            this.loanNewCancelationService = loanNewCancelationService;
        }

	
	public Boolean getIsAdministrator() {
		return isAdministrator;
	}

	public void setIsAdministrator(Boolean isAdministrator) {
		this.isAdministrator = isAdministrator;
	}
        
}
