package com.inkubator.hrm.web.appraisal;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.DualListModel;
import org.primefaces.model.LazyDataModel;

import com.inkubator.hrm.entity.AppraisalProgram;
import com.inkubator.hrm.entity.Department;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.GolonganJabatan;
import com.inkubator.hrm.entity.UnitKerja;
import com.inkubator.hrm.service.AppraisalProgramEmpService;
import com.inkubator.hrm.service.AppraisalProgramService;
import com.inkubator.hrm.service.DepartmentService;
import com.inkubator.hrm.service.GolonganJabatanService;
import com.inkubator.hrm.service.UnitKerjaService;
import com.inkubator.hrm.web.lazymodel.AppraisalProgramEmployeeLazyDataModel;
import com.inkubator.hrm.web.search.AppraisalProgramEmployeeSearchParameter;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;

import ch.lambdaj.Lambda;

/**
*
* @author rizkykojek
*/

@ManagedBean(name = "appraisalProgramDistributionFormController")
@ViewScoped
public class AppraisalProgramDistributionFormController extends BaseController{
	
	private	AppraisalProgramEmployeeSearchParameter searchParameter;
	private LazyDataModel<EmpData> lazy;
	private AppraisalProgram appraisalProgram;
	
	private DualListModel<String> golJabDualModel;
	private DualListModel<Department> departmentDualModel;
	private DualListModel<UnitKerja> unitKerjaDualModel;
	
	@ManagedProperty(value = "#{appraisalProgramEmpService}")
	private AppraisalProgramEmpService appraisalProgramEmpService;
	@ManagedProperty(value = "#{appraisalProgramService}")
	private AppraisalProgramService appraisalProgramService;
	@ManagedProperty(value = "#{golonganJabatanService}")
	private GolonganJabatanService golonganJabatanService;
	@ManagedProperty(value = "#{departmentService}")
	private DepartmentService departmentService;
	@ManagedProperty(value = "#{unitKerjaService}")
	private UnitKerjaService unitKerjaService;
	
	@Override
    public void initialization(){
        try {
			super.initialization();
	        searchParameter = new AppraisalProgramEmployeeSearchParameter();
	        List<GolonganJabatan> availableGolonganJabatans = golonganJabatanService.getAllData();
			List<Department> availableDepartments = departmentService.getAllData();
			List<UnitKerja> availableUnitKerjas = unitKerjaService.getAllData();
			
			golJabDualModel = new DualListModel<String>(Lambda.extract(availableGolonganJabatans, Lambda.on(GolonganJabatan.class).getCode()), new ArrayList<String>());
	    	departmentDualModel = new DualListModel<Department>(availableDepartments, new ArrayList<Department>());
	    	unitKerjaDualModel = new DualListModel<UnitKerja>(availableUnitKerjas, new ArrayList<UnitKerja>());
	        
	        String id = FacesUtil.getRequestParameter("execution");
	        if(StringUtils.isNotEmpty(id)){
	        	appraisalProgram = appraisalProgramService.getEntiyByPK(Long.parseLong(id.substring(1)));
	        }
		}catch(Exception ex){
			LOGGER.error("Error ", ex);
		}
    }
	
	@PreDestroy
    private void cleanAndExit(){
		golJabDualModel = null;
		departmentDualModel = null;
		unitKerjaDualModel = null;
		unitKerjaService = null;
		departmentService = null;
		golonganJabatanService = null;
        searchParameter = null;
        lazy = null;
        appraisalProgramEmpService = null;
        appraisalProgram = null;
        appraisalProgramService = null;
    }
	
	public void doSearch(){
        lazy = null;
    }
            
    public String doDistribute(){
    	return "/protected/appraisal/appraisal_program_distribution_view.htm?faces-redirect=true";
    }

	public AppraisalProgramEmployeeSearchParameter getSearchParameter() {
		return searchParameter;
	}

	public void setSearchParameter(AppraisalProgramEmployeeSearchParameter searchParameter) {
		this.searchParameter = searchParameter;
	}

	public LazyDataModel<EmpData> getLazy() {
		if(lazy == null){
			lazy = new AppraisalProgramEmployeeLazyDataModel(searchParameter, appraisalProgramEmpService);
		}
		return lazy;
	}

	public void setLazy(LazyDataModel<EmpData> lazy) {
		this.lazy = lazy;
	}

	public AppraisalProgramEmpService getAppraisalProgramEmpService() {
		return appraisalProgramEmpService;
	}

	public void setAppraisalProgramEmpService(AppraisalProgramEmpService appraisalProgramEmpService) {
		this.appraisalProgramEmpService = appraisalProgramEmpService;
	}

	public AppraisalProgram getAppraisalProgram() {
		return appraisalProgram;
	}

	public void setAppraisalProgram(AppraisalProgram appraisalProgram) {
		this.appraisalProgram = appraisalProgram;
	}

	public AppraisalProgramService getAppraisalProgramService() {
		return appraisalProgramService;
	}

	public void setAppraisalProgramService(AppraisalProgramService appraisalProgramService) {
		this.appraisalProgramService = appraisalProgramService;
	}

	public DualListModel<String> getGolJabDualModel() {
		return golJabDualModel;
	}

	public void setGolJabDualModel(DualListModel<String> golJabDualModel) {
		this.golJabDualModel = golJabDualModel;
	}

	public DualListModel<Department> getDepartmentDualModel() {
		return departmentDualModel;
	}

	public void setDepartmentDualModel(DualListModel<Department> departmentDualModel) {
		this.departmentDualModel = departmentDualModel;
	}

	public DualListModel<UnitKerja> getUnitKerjaDualModel() {
		return unitKerjaDualModel;
	}

	public void setUnitKerjaDualModel(DualListModel<UnitKerja> unitKerjaDualModel) {
		this.unitKerjaDualModel = unitKerjaDualModel;
	}

	public GolonganJabatanService getGolonganJabatanService() {
		return golonganJabatanService;
	}

	public void setGolonganJabatanService(GolonganJabatanService golonganJabatanService) {
		this.golonganJabatanService = golonganJabatanService;
	}

	public DepartmentService getDepartmentService() {
		return departmentService;
	}

	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	public UnitKerjaService getUnitKerjaService() {
		return unitKerjaService;
	}

	public void setUnitKerjaService(UnitKerjaService unitKerjaService) {
		this.unitKerjaService = unitKerjaService;
	}
	
}
