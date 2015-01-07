/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.payroll;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.LazyDataModel;

import com.inkubator.hrm.entity.UnregPayComponents;
import com.inkubator.hrm.service.UnregPayComponentsService;
import com.inkubator.hrm.web.lazymodel.UnregSpecificSchemeComponentsLazyDataModel;
import com.inkubator.hrm.web.search.UnregPayComponentsSearchParameter;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "unregSpecificSchemeDetailController")
@ViewScoped
public class UnregSpecificSchemeDetailController extends BaseController {

    @ManagedProperty(value = "#{unregPayComponentsService}")
    private UnregPayComponentsService unregPayComponentsService;
    private UnregPayComponentsSearchParameter searchParameter;
    private LazyDataModel<UnregPayComponents> lazyDataModel;
    private UnregPayComponents selected;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        searchParameter = new UnregPayComponentsSearchParameter();
        String unregSalaryId = FacesUtil.getRequestParameter("execution");
        searchParameter.setUnregSalaryId(Long.parseLong(unregSalaryId.substring(1)));
    }

    @PreDestroy
    private void cleanAndExit() {
        searchParameter = null;
        lazyDataModel = null;
        unregPayComponentsService = null;
        selected = null;
    }

    public void doSearch() {
        lazyDataModel = null;
    }

    public void doSelectEntity() {
        try {
            selected = this.unregPayComponentsService.getEntiyByPK(selected.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }
    
    public String doDetail(){
    	return "/protected/payroll/unreg_specific_scheme_exception.htm?faces-redirect=true&execution=e" + selected.getId();
    }
    
    public String doBack(){
    	return "/protected/payroll/unreg_specific_scheme_view.htm?faces-redirect=true";
    }

	public UnregPayComponentsService getUnregPayComponentsService() {
		return unregPayComponentsService;
	}

	public void setUnregPayComponentsService(UnregPayComponentsService unregPayComponentsService) {
		this.unregPayComponentsService = unregPayComponentsService;
	}

	public UnregPayComponentsSearchParameter getSearchParameter() {
		return searchParameter;
	}

	public void setSearchParameter(UnregPayComponentsSearchParameter searchParameter) {
		this.searchParameter = searchParameter;
	}

	public LazyDataModel<UnregPayComponents> getLazyDataModel() {
		if(lazyDataModel == null){
			lazyDataModel = new UnregSpecificSchemeComponentsLazyDataModel(searchParameter, unregPayComponentsService);
		}
		return lazyDataModel;
	}

	public void setLazyDataModel(LazyDataModel<UnregPayComponents> lazyDataModel) {
		this.lazyDataModel = lazyDataModel;
	}

	public UnregPayComponents getSelected() {
		return selected;
	}

	public void setSelected(UnregPayComponents selected) {
		this.selected = selected;
	}
    
    
}
