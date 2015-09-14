/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.payroll;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.ObjectUtils;

import ch.lambdaj.Lambda;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.service.BenefitGroupService;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.web.model.BenefitGroupRenumerationModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;

/**
 *
 * @author Deni Husni FR
 */
@ManagedBean(name = "basicRenumerationDetailController")
@ViewScoped
public class BasicRenumerationDetailController extends BaseController {

    @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;
    private EmpData selectedEmpData;

    //personal discipline
    @ManagedProperty(value = "#{benefitGroupService}")
    private BenefitGroupService benefitGroupService;

    private List<BenefitGroupRenumerationModel> listAllRenumeration;
    private List<BenefitGroupRenumerationModel> listSubsidy;
    private List<BenefitGroupRenumerationModel> listBenefit;
    private Double totalBenefit;
    private Double totalSubsidy;

    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();            
            String empId = FacesUtil.getRequestParameter("execution");
            selectedEmpData = empDataService.getByEmpIdWithDetail(Long.parseLong(empId.substring(1)));
            
            listBenefit = new ArrayList<BenefitGroupRenumerationModel>();
            listSubsidy = new ArrayList<BenefitGroupRenumerationModel>();
            listAllRenumeration = benefitGroupService.getAllDataRenumeration(selectedEmpData.getId());
            for(BenefitGroupRenumerationModel model : listAllRenumeration){
            	if(ObjectUtils.equals(model.getComponentCategory(), HRMConstant.PAY_SALARY_COMPONENT_TUNJANGAN)){
            		listBenefit.add(model);
            	} else if(ObjectUtils.equals(model.getComponentCategory(), HRMConstant.PAY_SALARY_COMPONENT_SUBSIDI)){
            		listSubsidy.add(model);
            	}
            }
            totalBenefit = Lambda.sum(listBenefit, Lambda.on(BenefitGroupRenumerationModel.class).getNominal());
            totalSubsidy = Lambda.sum(listSubsidy, Lambda.on(BenefitGroupRenumerationModel.class).getNominal());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }

    }
    
    @PreDestroy
    public void cleanAndExit() {
        empDataService = null;
        selectedEmpData = null;
        benefitGroupService = null;
        listAllRenumeration = null;
        listSubsidy = null;
        listBenefit = null;
        totalBenefit = null;
        totalSubsidy = null;
    }

    public String doBack() {
        return "/protected/payroll/basic_renumeration_view.htm?faces-redirect=true";
    }

    public void doSelectEmpCardName() {
        try {
            selectedEmpData = empDataService.getByEmpIdWithDetail(selectedEmpData.getId());
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

	public EmpDataService getEmpDataService() {
		return empDataService;
	}

	public void setEmpDataService(EmpDataService empDataService) {
		this.empDataService = empDataService;
	}

	public EmpData getSelectedEmpData() {
		return selectedEmpData;
	}

	public void setSelectedEmpData(EmpData selectedEmpData) {
		this.selectedEmpData = selectedEmpData;
	}

	public BenefitGroupService getBenefitGroupService() {
		return benefitGroupService;
	}

	public void setBenefitGroupService(BenefitGroupService benefitGroupService) {
		this.benefitGroupService = benefitGroupService;
	}

	public List<BenefitGroupRenumerationModel> getListAllRenumeration() {
		return listAllRenumeration;
	}

	public void setListAllRenumeration(List<BenefitGroupRenumerationModel> listAllRenumeration) {
		this.listAllRenumeration = listAllRenumeration;
	}

	public List<BenefitGroupRenumerationModel> getListSubsidy() {
		return listSubsidy;
	}

	public void setListSubsidy(List<BenefitGroupRenumerationModel> listSubsidy) {
		this.listSubsidy = listSubsidy;
	}

	public List<BenefitGroupRenumerationModel> getListBenefit() {
		return listBenefit;
	}

	public void setListBenefit(List<BenefitGroupRenumerationModel> listBenefit) {
		this.listBenefit = listBenefit;
	}
	
	public Double getTotalBenefit() {
		return totalBenefit;
	}

	public void setTotalBenefit(Double totalBenefit) {
		this.totalBenefit = totalBenefit;
	}
	
	public Double getTotalSubsidy() {
		return totalSubsidy;
	}

	public void setTotalSubsidy(Double totalSubsidy) {
		this.totalSubsidy = totalSubsidy;
	}

}
