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

import com.inkubator.hrm.entity.CarreerTransition;
import com.inkubator.hrm.service.CareerTransitionService;
import com.inkubator.hrm.web.lazymodel.CareerTransitionLazyDataModel;
import com.inkubator.hrm.web.search.CareerTransitionSearchParameter;
import com.inkubator.webcore.controller.BaseController;

/**
 *
 * @author Deni
 */
@ManagedBean(name = "careerTransitionViewController")
@ViewScoped
public class CareerTransitionViewController extends BaseController{
	
	private CareerTransitionSearchParameter searchParameter;
    private LazyDataModel<CarreerTransition> lazyDataModel;
    private CarreerTransition selectedCareerTransition;
    @ManagedProperty(value = "#{careerTransitionService}")
    private CareerTransitionService careerTransitionService;
    
    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        searchParameter = new CareerTransitionSearchParameter();
    }

    @PreDestroy
    public void cleanAndExit() {
    	careerTransitionService = null;
        searchParameter = null;
        lazyDataModel = null;
        selectedCareerTransition = null;
    }

    public String doAdd(){
    	return "/protected/career/career_transition_form.htm?faces-redirect=true";
    }
    
	public CareerTransitionSearchParameter getSearchParameter() {
		return searchParameter;
	}

	public void setSearchParameter(CareerTransitionSearchParameter searchParameter) {
		this.searchParameter = searchParameter;
	}

	public LazyDataModel<CarreerTransition> getLazyDataModel() {
		if (lazyDataModel == null) {
			lazyDataModel = new CareerTransitionLazyDataModel(searchParameter, careerTransitionService);
        }
		return lazyDataModel;
	}

	public void setLazyDataModel(LazyDataModel<CarreerTransition> lazyDataModel) {
		this.lazyDataModel = lazyDataModel;
	}

	public CarreerTransition getSelectedCareerTransition() {
		return selectedCareerTransition;
	}

	public void setSelectedCareerTransition(CarreerTransition selectedCareerTransition) {
		this.selectedCareerTransition = selectedCareerTransition;
	}

	public CareerTransitionService getCareerTransitionService() {
		return careerTransitionService;
	}

	public void setCareerTransitionService(CareerTransitionService careerTransitionService) {
		this.careerTransitionService = careerTransitionService;
	}
    
    
}
