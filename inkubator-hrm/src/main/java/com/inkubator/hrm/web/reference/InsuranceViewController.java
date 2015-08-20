/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.reference;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.hibernate.exception.ConstraintViolationException;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.springframework.dao.DataIntegrityViolationException;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.Insurance;
import com.inkubator.hrm.service.InsuranceService;
import com.inkubator.hrm.web.lazymodel.InsuranceLazyDataModel;
import com.inkubator.hrm.web.search.InsuranceSearchParameter;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
 *
 * @author Deni
 */
@ManagedBean(name = "insuranceViewController")
@ViewScoped
public class InsuranceViewController extends BaseController {
	private InsuranceSearchParameter searchParameter;
    private LazyDataModel<Insurance> lazyDataModel;
    private Insurance selectedInsurance;
    @ManagedProperty(value = "#{insuranceService}")
    private InsuranceService insuranceService;
    
    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        searchParameter = new InsuranceSearchParameter();
    }

    @PreDestroy
    public void cleanAndExit() {
    	insuranceService = null;
        searchParameter = null;
        lazyDataModel = null;
        selectedInsurance = null;
    }

    public void doSearch() {
        lazyDataModel = null;
    }
    
    public String doAdd() {
      return "/protected/reference/insurance_form.htm?faces-redirect=true";
    }
    
    public String doUpdate(){
    	return "/protected/reference/insurance_form.htm?faces-redirect=true&execution=e" + selectedInsurance.getId();
    }
    
    public void doDelete() {
        try {
            insuranceService.delete(selectedInsurance);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            LOGGER.error("Error when doDelete bank ", ex);
        } catch (Exception ex) {
            LOGGER.error("Error when doDelete bank", ex);
        }
    }
    
    public void doSelectEntity() throws Exception{
    	selectedInsurance = insuranceService.getEntiyByPK(selectedInsurance.getId());
    }
    
    public void doSelectEntityWithDetail() throws Exception {
    	selectedInsurance = insuranceService.getEntityByPkWithDetail(selectedInsurance.getId());
    }
    
    @Override
    public void onDialogReturn(SelectEvent event) {
        //re-calculate searching
        doSearch();
        super.onDialogReturn(event);
    }
    
	public InsuranceSearchParameter getSearchParameter() {
		return searchParameter;
	}

	public void setSearchParameter(InsuranceSearchParameter searchParameter) {
		this.searchParameter = searchParameter;
	}

	public LazyDataModel<Insurance> getLazyDataModel() {
		if (lazyDataModel == null) {
            lazyDataModel = new InsuranceLazyDataModel(searchParameter, insuranceService);
        }
		return lazyDataModel;
	}

	public void setLazyDataModel(LazyDataModel<Insurance> lazyDataModel) {
		this.lazyDataModel = lazyDataModel;
	}

	public Insurance getSelectedInsurance() {
		return selectedInsurance;
	}

	public void setSelectedInsurance(Insurance selectedInsurance) {
		this.selectedInsurance = selectedInsurance;
	}

	public InsuranceService getInsuranceService() {
		return insuranceService;
	}

	public void setInsuranceService(InsuranceService insuranceService) {
		this.insuranceService = insuranceService;
	}
    
    
}
