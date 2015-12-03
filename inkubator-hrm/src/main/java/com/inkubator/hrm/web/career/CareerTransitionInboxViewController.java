/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.career;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.LazyDataModel;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.service.EmpCareerHistoryService;
import com.inkubator.hrm.web.lazymodel.CareerTransitionInboxLazyDataModel;
import com.inkubator.hrm.web.model.CareerTransitionInboxViewModel;
import com.inkubator.hrm.web.search.CareerTransitionInboxSearchParameter;
import com.inkubator.webcore.controller.BaseController;

/**
 *
 * @author Deni
 */
@ManagedBean(name = "careerTransitionInboxViewController")
@ViewScoped
public class CareerTransitionInboxViewController extends BaseController {
    @ManagedProperty(value = "#{empCareerHistoryService}")
    private EmpCareerHistoryService service;
    private CareerTransitionInboxSearchParameter searchParameter;
    private LazyDataModel<CareerTransitionInboxViewModel> lazyDataModel;
    private CareerTransitionInboxViewModel selected;
    
    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        searchParameter = new CareerTransitionInboxSearchParameter();
    }
    
    @PreDestroy
    private void cleanAndExit() {
        searchParameter=null;
        lazyDataModel=null;
        service=null;
        selected=null;
    }
    
    public void doSearch() {
        lazyDataModel = null;
    }
    
    public String doDetail(){
    	if(selected.getApprovalStatus() == HRMConstant.APPROVAL_STATUS_APPROVED){
    		return "/protected/career/career_transition_inbox_detail.htm?faces-redirect=true&execution=e"+selected.getApprovalActivityId();
    	}else{
    		return "/protected/career/emp_career_transition_approval_form.htm?faces-redirect=true&execution=e"+selected.getApprovalActivityId();
    	}
	}
    
    public void doSelectEntity() {
//        try {
//            selected = this.service.getEntiyByPK(selected.get());
//        } catch (Exception ex) {
//            LOGGER.error("Error", ex);
//        }
    }

    

    public EmpCareerHistoryService getService() {
		return service;
	}

	public void setService(EmpCareerHistoryService service) {
		this.service = service;
	}

	public CareerTransitionInboxSearchParameter getSearchParameter() {
        return searchParameter;
    }

    public void setSearchParameter(CareerTransitionInboxSearchParameter searchParameter) {
        this.searchParameter = searchParameter;
    }

    public LazyDataModel<CareerTransitionInboxViewModel> getLazyDataModel() {
    	if(lazyDataModel == null){
    		lazyDataModel = new CareerTransitionInboxLazyDataModel(searchParameter, service);
    	}
        return lazyDataModel;
    }

    public void setLazyDataModel(LazyDataModel<CareerTransitionInboxViewModel> lazyDataModel) {
        this.lazyDataModel = lazyDataModel;
    }

    public CareerTransitionInboxViewModel getSelected() {
        return selected;
    }

    public void setSelected(CareerTransitionInboxViewModel selected) {
        this.selected = selected;
    }
    
    
}
