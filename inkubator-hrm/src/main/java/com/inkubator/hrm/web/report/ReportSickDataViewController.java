/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.report;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.DualListModel;
import org.primefaces.model.LazyDataModel;

import com.inkubator.hrm.entity.Department;
import com.inkubator.hrm.entity.GolonganJabatan;
import com.inkubator.hrm.entity.MedicalCare;
import com.inkubator.hrm.service.DepartmentService;
import com.inkubator.hrm.service.GolonganJabatanService;
import com.inkubator.hrm.service.MedicalCareService;
import com.inkubator.hrm.web.lazymodel.ReportSickDataLazyDataModel;
import com.inkubator.hrm.web.search.ReportSickDataSearchParameter;
import com.inkubator.webcore.controller.BaseController;

import ch.lambdaj.Lambda;

/**
 *
 * @author arsyad_
 */

@ManagedBean(name = "reportSickDataViewController")
@ViewScoped
public class ReportSickDataViewController extends BaseController{
	
	private ReportSickDataSearchParameter searchParameter;
	
	private LazyDataModel<MedicalCare> lazyDataModel;
	private DualListModel<Department> departmentDualModel;
	private DualListModel<GolonganJabatan> golJabDualModel;
	
	@ManagedProperty(value = "#{medicalCareService}")
	private MedicalCareService medicalCareService;
	@ManagedProperty(value = "#{departmentService}")
	private DepartmentService departmentService;
	@ManagedProperty(value = "#{golonganJabatanService}")
	private GolonganJabatanService golonganJabatanService;
	
	@PostConstruct
	@Override
	public void initialization(){
		super.initialization();
		try{
			searchParameter = new ReportSickDataSearchParameter();
			List<Department> availableDepartment = departmentService.getAllData();
			List<GolonganJabatan> availableGolonganJabatan = golonganJabatanService.getAllData();
			
			departmentDualModel = new DualListModel<Department>(availableDepartment, new ArrayList<Department>());
			golJabDualModel = new DualListModel<GolonganJabatan>(availableGolonganJabatan, new ArrayList<GolonganJabatan>());
		} catch (Exception ex){
			LOGGER.error("Error ", ex);
		}
	}
	
	@PreDestroy
	public void cleanAndExit(){
		searchParameter = null;
		lazyDataModel = null;
		golJabDualModel = null;
		departmentDualModel = null;
		medicalCareService = null;
		golonganJabatanService = null;
		departmentService = null;
	}
	
	public void doSearch(){
		searchParameter.setListDepartment(Lambda.extract(departmentDualModel.getTarget(), Lambda.on(Department.class).getId()));
		searchParameter.setListGolJab(Lambda.extract(golJabDualModel.getTarget(), Lambda.on(GolonganJabatan.class).getId()));
		lazyDataModel = null;
	}
	
	public LazyDataModel<MedicalCare> getLazyDataModel(){
		if(lazyDataModel == null){
			lazyDataModel = new ReportSickDataLazyDataModel(searchParameter, medicalCareService);
		}
		return lazyDataModel;
	}

	public ReportSickDataSearchParameter getSearchParameter() {
		return searchParameter;
	}

	public void setSearchParameter(ReportSickDataSearchParameter searchParameter) {
		this.searchParameter = searchParameter;
	}


	public DualListModel<Department> getDepartmentDualModel() {
		return departmentDualModel;
	}

	public void setDepartmentDualModel(DualListModel<Department> departmentDualModel) {
		this.departmentDualModel = departmentDualModel;
	}

	public DualListModel<GolonganJabatan> getGolJabDualModel() {
		return golJabDualModel;
	}

	public void setGolJabDualModel(DualListModel<GolonganJabatan> golJabDualModel) {
		this.golJabDualModel = golJabDualModel;
	}

	public MedicalCareService getMedicalCareService() {
		return medicalCareService;
	}

	public void setMedicalCareService(MedicalCareService medicalCareService) {
		this.medicalCareService = medicalCareService;
	}

	public DepartmentService getDepartmentService() {
		return departmentService;
	}

	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	public GolonganJabatanService getGolonganJabatanService() {
		return golonganJabatanService;
	}

	public void setGolonganJabatanService(GolonganJabatanService golonganJabatanService) {
		this.golonganJabatanService = golonganJabatanService;
	}

	public void setLazyDataModel(LazyDataModel<MedicalCare> lazyDataModel) {
		this.lazyDataModel = lazyDataModel;
	}
	
	
	
}
