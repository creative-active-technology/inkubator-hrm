/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.employee;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.LazyDataModel;

import com.inkubator.hrm.entity.Department;
import com.inkubator.hrm.entity.GolonganJabatan;
import com.inkubator.hrm.entity.ImplementationOfOverTime;
import com.inkubator.hrm.entity.WtOverTime;
import com.inkubator.hrm.service.DepartmentService;
import com.inkubator.hrm.service.GolonganJabatanService;
import com.inkubator.hrm.service.ImplementationOfOverTimeService;
import com.inkubator.hrm.service.WtOverTimeService;
import com.inkubator.hrm.web.lazymodel.OvertimeImplSearchingLazyDataModel;
import com.inkubator.hrm.web.model.OvertimeImplSearchingModel;
import com.inkubator.webcore.controller.BaseController;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
@ManagedBean(name = "overtimeImplSearchingViewController")
@ViewScoped
public class OvertimeImplSearchingViewController extends BaseController {

    
    @ManagedProperty(value = "#{implementationOfOverTimeService}")
    private ImplementationOfOverTimeService implementationOfOverTimeService;
    @ManagedProperty(value = "#{golonganJabatanService}")
    private GolonganJabatanService golonganJabatanService;
    @ManagedProperty(value = "#{departmentService}")
    private DepartmentService departmentService;
    @ManagedProperty(value = "#{wtOverTimeService}")
    private WtOverTimeService wtOverTimeService;
    private LazyDataModel<ImplementationOfOverTime> lazyDataModel;
    private OvertimeImplSearchingModel overtimeImplSearchingModel;
    
    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        overtimeImplSearchingModel = new OvertimeImplSearchingModel();
        try {
        	
        	//Initialize dualListWtOvertime
        	overtimeImplSearchingModel.getDualListWtOverTime().setSource(wtOverTimeService.getAllData());
        	overtimeImplSearchingModel.getDualListWtOverTime().setTarget(new ArrayList<WtOverTime>());
        	
        	//Initialize dualListGolonganJabatan
        	overtimeImplSearchingModel.getDualListGolonganJabatan().setSource(golonganJabatanService.getAllData());
        	overtimeImplSearchingModel.getDualListGolonganJabatan().setTarget(new ArrayList<GolonganJabatan>());
        	
        	///Initialize dualListDepartment
        	overtimeImplSearchingModel.getDualListDepartemen().setSource(departmentService.getAllData());
        	overtimeImplSearchingModel.getDualListDepartemen().setTarget(new ArrayList<Department>());
        	
        } catch (Exception ex) {
            Logger.getLogger(OvertimeImplSearchingViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @PreDestroy
    public void cleanAndExit() {
        lazyDataModel = null;
        overtimeImplSearchingModel = null;
        implementationOfOverTimeService = null;
        wtOverTimeService = null;
        golonganJabatanService = null;
        departmentService = null;
    }

    public void doSearch() {
		
    	overtimeImplSearchingModel.setListSelectedWtOvertime(overtimeImplSearchingModel.getDualListWtOverTime().getTarget());
    	overtimeImplSearchingModel.setListSelectedGolJabatan(overtimeImplSearchingModel.getDualListGolonganJabatan().getTarget());
    	overtimeImplSearchingModel.setListSelectedDepartment(overtimeImplSearchingModel.getDualListDepartemen().getTarget());
        lazyDataModel = null;
    }

    public LazyDataModel<ImplementationOfOverTime> getLazyDataModel() {
        if (lazyDataModel == null) {
            lazyDataModel = new OvertimeImplSearchingLazyDataModel(overtimeImplSearchingModel, implementationOfOverTimeService);
        }
        return lazyDataModel;
    }

    public void setLazyDataModel(LazyDataModel<ImplementationOfOverTime> lazyDataModel) {
        this.lazyDataModel = lazyDataModel;
    }

	public OvertimeImplSearchingModel getOvertimeImplSearchingModel() {
		return overtimeImplSearchingModel;
	}

	public void setOvertimeImplSearchingModel(OvertimeImplSearchingModel overtimeImplSearchingModel) {
		this.overtimeImplSearchingModel = overtimeImplSearchingModel;
	}

	public void setImplementationOfOverTimeService(ImplementationOfOverTimeService implementationOfOverTimeService) {
		this.implementationOfOverTimeService = implementationOfOverTimeService;
	}

	public void setGolonganJabatanService(GolonganJabatanService golonganJabatanService) {
		this.golonganJabatanService = golonganJabatanService;
	}

	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	public void setWtOverTimeService(WtOverTimeService wtOverTimeService) {
		this.wtOverTimeService = wtOverTimeService;
	}
	
}
