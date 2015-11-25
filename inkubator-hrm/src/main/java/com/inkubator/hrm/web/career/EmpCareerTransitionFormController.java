package com.inkubator.hrm.web.career;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;
import org.hamcrest.Matchers;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.ApprovalActivity;
import com.inkubator.hrm.entity.CareerTransition;
import com.inkubator.hrm.entity.Department;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.EmployeeType;
import com.inkubator.hrm.entity.GolonganJabatan;
import com.inkubator.hrm.entity.Jabatan;
import com.inkubator.hrm.service.CareerTransitionService;
import com.inkubator.hrm.service.DepartmentService;
import com.inkubator.hrm.service.EmpCareerHistoryService;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.EmployeeTypeService;
import com.inkubator.hrm.service.GolonganJabatanService;
import com.inkubator.hrm.service.JabatanService;
import com.inkubator.hrm.util.HrmUserInfoUtil;
import com.inkubator.hrm.web.model.EmpCareerHistoryModel;
import com.inkubator.securitycore.util.UserInfoUtil;
import com.inkubator.webcore.controller.BaseController;

import ch.lambdaj.Lambda;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "empCareerTransitionFormController")
@ViewScoped
public class EmpCareerTransitionFormController extends BaseController {

	private Boolean isAdministator;
    private Boolean isRevised;
    private ApprovalActivity currentActivity;
    private ApprovalActivity askingRevisedActivity;
    private EmpCareerHistoryModel model;
    
    private List<CareerTransition> listCareerTransition;
    private List<Department> listDepartment;
    private List<Jabatan> listJabatan;
    private List<GolonganJabatan> listGolonganJabatan;
    private List<EmployeeType> listEmployeeType;
    
    @ManagedProperty(value = "#{empCareerHistoryService}")
    private EmpCareerHistoryService empCareerHistoryService;
    @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;
    @ManagedProperty(value = "#{departmentService}")
    private DepartmentService departmentService;
    @ManagedProperty(value = "#{jabatanService}")
    private JabatanService jabatanService;
    @ManagedProperty(value = "#{golonganJabatanService}")
    private GolonganJabatanService golonganJabatanService;
    @ManagedProperty(value = "#{employeeTypeService}")
    private EmployeeTypeService employeeTypeService;
    @ManagedProperty(value = "#{careerTransitionService}")
    private CareerTransitionService careerTransitionService;
    
	@PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try {
        	//initial
        	isAdministator = Lambda.exists(UserInfoUtil.getRoles(), Matchers.containsString(HRMConstant.ADMINISTRATOR_ROLE));
            isRevised = Boolean.FALSE;
            model = new EmpCareerHistoryModel();
            
            listCareerTransition = careerTransitionService.getAllData();
            listDepartment = departmentService.getAllDataWithoutSpecificCompany();
            listGolonganJabatan = golonganJabatanService.getAllWithDetail();
            listEmployeeType =  employeeTypeService.getAllData();
            
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }        
		
	}
	
	@PreDestroy
    public void cleanAndExit() {
		
	}
	
	public String doBack() {
        return "/protected/career/emp_career_transition_view.htm?faces-redirect=true";
    }
	
	public String doSave() {
		
		return null;
	}
	
	public String doRevised() {
		
		return null;
	}
	
	
	private void getModelFromJson(String json) {
		
	}
	
	public List<EmpData> doAutoCompleteEmployee(String param) {
        List<EmpData> empDatas = new ArrayList<EmpData>();
        try {
            empDatas = empDataService.getAllDataByNameOrNik(StringUtils.stripToEmpty(param));
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
        return empDatas;
    }

    public void onChangeEmployee() {
        try {
        	EmpData empData = empDataService.getByEmpIdWithDetail(model.getEmpData().getId());
        	model.setEmpData(empData);
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }
    
    public void onChangeDepartment() {
        try {
        	listJabatan = jabatanService.getByDepartementId(model.getDepartmentId());
        	Department department =  departmentService.getEntityByPkWithDetail(model.getDepartmentId());
        	model.setCompanyName(department.getCompany().getName());
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

	public Boolean getIsAdministator() {
		return isAdministator;
	}

	public void setIsAdministator(Boolean isAdministator) {
		this.isAdministator = isAdministator;
	}

	public Boolean getIsRevised() {
		return isRevised;
	}

	public void setIsRevised(Boolean isRevised) {
		this.isRevised = isRevised;
	}

	public ApprovalActivity getCurrentActivity() {
		return currentActivity;
	}

	public void setCurrentActivity(ApprovalActivity currentActivity) {
		this.currentActivity = currentActivity;
	}

	public ApprovalActivity getAskingRevisedActivity() {
		return askingRevisedActivity;
	}

	public void setAskingRevisedActivity(ApprovalActivity askingRevisedActivity) {
		this.askingRevisedActivity = askingRevisedActivity;
	}

	public EmpCareerHistoryModel getModel() {
		return model;
	}

	public void setModel(EmpCareerHistoryModel model) {
		this.model = model;
	}

	public EmpCareerHistoryService getEmpCareerHistoryService() {
		return empCareerHistoryService;
	}

	public void setEmpCareerHistoryService(EmpCareerHistoryService empCareerHistoryService) {
		this.empCareerHistoryService = empCareerHistoryService;
	}

	public EmpDataService getEmpDataService() {
		return empDataService;
	}

	public void setEmpDataService(EmpDataService empDataService) {
		this.empDataService = empDataService;
	}

	public DepartmentService getDepartmentService() {
		return departmentService;
	}

	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	public JabatanService getJabatanService() {
		return jabatanService;
	}

	public void setJabatanService(JabatanService jabatanService) {
		this.jabatanService = jabatanService;
	}

	public GolonganJabatanService getGolonganJabatanService() {
		return golonganJabatanService;
	}

	public void setGolonganJabatanService(GolonganJabatanService golonganJabatanService) {
		this.golonganJabatanService = golonganJabatanService;
	}

	public EmployeeTypeService getEmployeeTypeService() {
		return employeeTypeService;
	}

	public void setEmployeeTypeService(EmployeeTypeService employeeTypeService) {
		this.employeeTypeService = employeeTypeService;
	}

	public CareerTransitionService getCareerTransitionService() {
		return careerTransitionService;
	}

	public void setCareerTransitionService(CareerTransitionService careerTransitionService) {
		this.careerTransitionService = careerTransitionService;
	}

	public List<CareerTransition> getListCareerTransition() {
		return listCareerTransition;
	}

	public void setListCareerTransition(List<CareerTransition> listCareerTransition) {
		this.listCareerTransition = listCareerTransition;
	}

	public List<Department> getListDepartment() {
		return listDepartment;
	}

	public void setListDepartment(List<Department> listDepartment) {
		this.listDepartment = listDepartment;
	}

	public List<Jabatan> getListJabatan() {
		return listJabatan;
	}

	public void setListJabatan(List<Jabatan> listJabatan) {
		this.listJabatan = listJabatan;
	}

	public List<GolonganJabatan> getListGolonganJabatan() {
		return listGolonganJabatan;
	}

	public void setListGolonganJabatan(List<GolonganJabatan> listGolonganJabatan) {
		this.listGolonganJabatan = listGolonganJabatan;
	}

	public List<EmployeeType> getListEmployeeType() {
		return listEmployeeType;
	}

	public void setListEmployeeType(List<EmployeeType> listEmployeeType) {
		this.listEmployeeType = listEmployeeType;
	}
	
}
