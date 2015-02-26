package com.inkubator.hrm.web.report;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.DualListModel;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.StreamedContent;

import ch.lambdaj.Lambda;

import com.inkubator.hrm.entity.Department;
import com.inkubator.hrm.entity.EmployeeType;
import com.inkubator.hrm.entity.GolonganJabatan;
import com.inkubator.hrm.entity.WtPeriode;
import com.inkubator.hrm.service.DepartmentService;
import com.inkubator.hrm.service.EmployeeTypeService;
import com.inkubator.hrm.service.GolonganJabatanService;
import com.inkubator.hrm.service.LogMonthEndPayrollService;
import com.inkubator.hrm.service.WtPeriodeService;
import com.inkubator.hrm.web.ReportStreamController;
import com.inkubator.hrm.web.lazymodel.ReportSalaryNoteLazyDataModel;
import com.inkubator.hrm.web.model.ReportSalaryNoteModel;
import com.inkubator.hrm.web.search.ReportSalaryNoteSearchParameter;
import com.inkubator.webcore.controller.BaseController;

/**
*
* @author rizkykojek
*/
@ManagedBean(name = "reportSalaryNoteViewController")
@ViewScoped
public class ReportSalaryNoteViewController extends BaseController {

	private ReportSalaryNoteSearchParameter searchParameter;
	private LazyDataModel<ReportSalaryNoteModel> lazyDataModel;
	private DualListModel<String> golJabDualModel;
	private DualListModel<Department> departmentDualModel;
	private DualListModel<EmployeeType> empTypeDualModel;
	private List<WtPeriode> listPeriodeYears;
	private String periodeMonth;
    private String periodeYear;
    private ReportSalaryNoteModel selectedModel;
	@ManagedProperty(value = "#{logMonthEndPayrollService}")
    private LogMonthEndPayrollService logMonthEndPayrollService;
	@ManagedProperty(value = "#{golonganJabatanService}")
    private GolonganJabatanService golonganJabatanService;
	@ManagedProperty(value = "#{departmentService}")
    private DepartmentService departmentService;
	@ManagedProperty(value = "#{wtPeriodeService}")
    private WtPeriodeService wtPeriodeService;
	@ManagedProperty(value = "#{employeeTypeService}")
    private EmployeeTypeService employeeTypeService;
	@ManagedProperty(value = "#{reportStreamController}")
    private ReportStreamController reportStreamController;
	
	@PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try {
        	searchParameter = new ReportSalaryNoteSearchParameter();
        	List<GolonganJabatan> availableGolonganJabatans =  golonganJabatanService.getAllData();        	
        	List<Department> availableDepartments = departmentService.getAllData();
        	List<EmployeeType> availableEmployeeType = employeeTypeService.getAllData();
        	
        	golJabDualModel = new DualListModel<String>(Lambda.extract(availableGolonganJabatans, Lambda.on(GolonganJabatan.class).getCode()), new ArrayList<String>());
        	departmentDualModel = new DualListModel<Department>(availableDepartments, new ArrayList<Department>());
        	empTypeDualModel = new DualListModel<EmployeeType>(availableEmployeeType, new ArrayList<EmployeeType>());
        	listPeriodeYears = wtPeriodeService.getAllYears();       	
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
	}
	
	@PreDestroy
    public void cleanAndExit() {
		searchParameter = null;
		lazyDataModel = null;
		golJabDualModel = null;
		listPeriodeYears = null;
		periodeMonth = null;
		periodeYear = null;
		logMonthEndPayrollService = null;
		golonganJabatanService = null;
		departmentService = null;
		wtPeriodeService = null;	
		departmentDualModel = null;
		empTypeDualModel = null;
		employeeTypeService = null;
		selectedModel = null;
	}

	public LazyDataModel<ReportSalaryNoteModel> getLazyDataModel() {
		if(lazyDataModel == null){
			lazyDataModel = new ReportSalaryNoteLazyDataModel(searchParameter, logMonthEndPayrollService);
		}
		return lazyDataModel;
	}	
	
	public ReportSalaryNoteSearchParameter getSearchParameter() {
		return searchParameter;
	}

	public void setSearchParameter(ReportSalaryNoteSearchParameter searchParameter) {
		this.searchParameter = searchParameter;
	}

	public DualListModel<Department> getDepartmentDualModel() {
		return departmentDualModel;
	}

	public void setDepartmentDualModel(DualListModel<Department> departmentDualModel) {
		this.departmentDualModel = departmentDualModel;
	}

	public DualListModel<EmployeeType> getEmpTypeDualModel() {
		return empTypeDualModel;
	}

	public void setEmpTypeDualModel(DualListModel<EmployeeType> empTypeDualModel) {
		this.empTypeDualModel = empTypeDualModel;
	}

	public EmployeeTypeService getEmployeeTypeService() {
		return employeeTypeService;
	}

	public void setEmployeeTypeService(EmployeeTypeService employeeTypeService) {
		this.employeeTypeService = employeeTypeService;
	}

	public LogMonthEndPayrollService getLogMonthEndPayrollService() {
		return logMonthEndPayrollService;
	}

	public void setLogMonthEndPayrollService(
			LogMonthEndPayrollService logMonthEndPayrollService) {
		this.logMonthEndPayrollService = logMonthEndPayrollService;
	}

	public void setLazyDataModel(LazyDataModel<ReportSalaryNoteModel> lazyDataModel) {
		this.lazyDataModel = lazyDataModel;
	}

	public DualListModel<String> getGolJabDualModel() {
		return golJabDualModel;
	}

	public void setGolJabDualModel(DualListModel<String> golJabDualModel) {
		this.golJabDualModel = golJabDualModel;
	}

	public List<WtPeriode> getListPeriodeYears() {
		return listPeriodeYears;
	}

	public void setListPeriodeYears(List<WtPeriode> listPeriodeYears) {
		this.listPeriodeYears = listPeriodeYears;
	}

	public GolonganJabatanService getGolonganJabatanService() {
		return golonganJabatanService;
	}

	public void setGolonganJabatanService(
			GolonganJabatanService golonganJabatanService) {
		this.golonganJabatanService = golonganJabatanService;
	}

	public DepartmentService getDepartmentService() {
		return departmentService;
	}

	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	public WtPeriodeService getWtPeriodeService() {
		return wtPeriodeService;
	}

	public void setWtPeriodeService(WtPeriodeService wtPeriodeService) {
		this.wtPeriodeService = wtPeriodeService;
	}

	public String getPeriodeMonth() {
		return periodeMonth;
	}

	public void setPeriodeMonth(String periodeMonth) {
		this.periodeMonth = periodeMonth;
	}

	public String getPeriodeYear() {
		return periodeYear;
	}

	public void setPeriodeYear(String periodeYear) {
		this.periodeYear = periodeYear;
	}

	public ReportSalaryNoteModel getSelectedModel() {
		return selectedModel;
	}

	public void setSelectedModel(ReportSalaryNoteModel selectedModel) {
		this.selectedModel = selectedModel;
	}

	public ReportStreamController getReportStreamController() {
		return reportStreamController;
	}

	public void setReportStreamController(
			ReportStreamController reportStreamController) {
		this.reportStreamController = reportStreamController;
	}

	public void doSearch(){
		Long periodeId = null;
		try {
			if(!StringUtils.isEmpty(periodeMonth) && !StringUtils.isEmpty(periodeYear)){
				WtPeriode p = wtPeriodeService.getEntityByMonthAndYear(Integer.parseInt(periodeMonth), periodeYear);
				periodeId = p == null ? 0 : p.getId();
			}
		} catch (Exception ex) {
			LOGGER.error("Error", ex);
		}
		searchParameter.setPeriodeId(periodeId);
		searchParameter.setListGolJab(golJabDualModel.getTarget());
		searchParameter.setListEmpType(Lambda.extract(empTypeDualModel.getTarget(), Lambda.on(EmployeeType.class).getId()));
		searchParameter.setListDepartment(Lambda.extract(departmentDualModel.getTarget(), Lambda.on(Department.class).getId()));
		
		lazyDataModel = null;		
	}
	
	public void onChangeMonth(){
		if(StringUtils.isEmpty(periodeMonth)){
			periodeYear = null;
		}
	}
	
	public StreamedContent doPrintSlip(){
		StreamedContent file = null;
    	try {
    		file = reportStreamController.getFileSalarySlip(selectedModel.getPeriodId(), selectedModel.getEmpDataId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return file;
		
	}
		
}
