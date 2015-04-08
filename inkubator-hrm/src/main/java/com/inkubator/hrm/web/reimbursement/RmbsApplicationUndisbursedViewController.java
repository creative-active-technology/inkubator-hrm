package com.inkubator.hrm.web.reimbursement;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.hamcrest.Matchers;
import org.primefaces.model.LazyDataModel;

import ch.lambdaj.Lambda;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.service.RmbsApplicationService;
import com.inkubator.hrm.web.lazymodel.RmbsApplicationUndisbursedLazyDataModel;
import com.inkubator.hrm.web.model.RmbsApplicationUndisbursedViewModel;
import com.inkubator.hrm.web.search.RmbsApplicationUndisbursedSearchParameter;
import com.inkubator.securitycore.util.UserInfoUtil;
import com.inkubator.webcore.controller.BaseController;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "rmbsApplicationUndisbursedViewController")
@ViewScoped
public class RmbsApplicationUndisbursedViewController extends BaseController {

	private Boolean isAdministator;
    private RmbsApplicationUndisbursedSearchParameter parameter;
    private LazyDataModel<RmbsApplicationUndisbursedViewModel> lazyData;
    private RmbsApplicationUndisbursedViewModel selected;
    
    @ManagedProperty(value = "#{rmbsApplicationService}")
    private RmbsApplicationService rmbsApplicationService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        parameter = new RmbsApplicationUndisbursedSearchParameter();
        isAdministator = Lambda.exists(UserInfoUtil.getRoles(), Matchers.containsString(HRMConstant.ADMINISTRATOR_ROLE));
        if(!isAdministator){ //kalo bukan administrator, maka set userId di parameter searchingnya
        	parameter.setUserId(UserInfoUtil.getUserName());
        }
    }

    @PreDestroy
    public void cleanAndExit() {
    	rmbsApplicationService = null;
        parameter = null;
        lazyData = null;
        selected = null;
    }

    public void doSearch() {
        lazyData = null;
    }

    public String doDetailApproval() { 
        return "/protected/reimbursement/rmbs_application_approval_form.htm?faces-redirect=true&execution=e" + selected.getApprovalActivityId();
    }
    
    public String doDetailEntity() { 
        return "/protected/reimbursement/rmbs_application_detail.htm?faces-redirect=true&execution=e" + selected.getRmbsApplicationId();
    }

	public RmbsApplicationUndisbursedSearchParameter getParameter() {
		return parameter;
	}

	public void setParameter(RmbsApplicationUndisbursedSearchParameter parameter) {
		this.parameter = parameter;
	}

	public LazyDataModel<RmbsApplicationUndisbursedViewModel> getLazyData() {
		if(lazyData == null){
			lazyData = new RmbsApplicationUndisbursedLazyDataModel(parameter, rmbsApplicationService);
		}
		return lazyData;
	}

	public void setLazyData(LazyDataModel<RmbsApplicationUndisbursedViewModel> lazyData) {
		this.lazyData = lazyData;
	}

	public RmbsApplicationUndisbursedViewModel getSelected() {
		return selected;
	}

	public void setSelected(RmbsApplicationUndisbursedViewModel selected) {
		this.selected = selected;
	}

	public RmbsApplicationService getRmbsApplicationService() {
		return rmbsApplicationService;
	}

	public void setRmbsApplicationService(RmbsApplicationService rmbsApplicationService) {
		this.rmbsApplicationService = rmbsApplicationService;
	}

	public Boolean getIsAdministator() {
		return isAdministator;
	}

	public void setIsAdministator(Boolean isAdministator) {
		this.isAdministator = isAdministator;
	}
    
}
