package com.inkubator.hrm.web.recruitment;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.Department;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.EmployeeType;
import com.inkubator.hrm.entity.GolonganJabatan;
import com.inkubator.hrm.entity.PaySalaryGrade;
import com.inkubator.hrm.entity.RecruitSelectionApplicantPassed;
import com.inkubator.hrm.entity.RecruitSelectionApplicantPassedId;
import com.inkubator.hrm.entity.RmbsSchema;
import com.inkubator.hrm.service.DepartmentService;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.EmployeeTypeService;
import com.inkubator.hrm.service.GolonganJabatanService;
import com.inkubator.hrm.service.PaySalaryGradeService;
import com.inkubator.hrm.service.RecruitSelectionApplicantPassedService;
import com.inkubator.hrm.web.model.RecruitSelectionApplicantPassedModel;
import com.inkubator.hrm.web.model.RmbsSchemaModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
@ManagedBean(name = "recruitmentEmployeeSetupFormController")
@ViewScoped
public class RecruitmentEmployeeSetupFormController extends BaseController {
	
	@ManagedProperty(value = "#{recruitSelectionApplicantPassedService}")
    private RecruitSelectionApplicantPassedService recruitSelectionApplicantPassedService;
	@ManagedProperty(value = "#{departmentService}")
    private DepartmentService departmentService;
	@ManagedProperty(value = "#{golonganJabatanService}")
    private GolonganJabatanService golonganJabatanService;
	@ManagedProperty(value = "#{employeeTypeService}")
    private EmployeeTypeService employeeTypeService;
	@ManagedProperty(value = "#{paySalaryGradeService}")
    private PaySalaryGradeService paySalaryGradeService;
	@ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;
    private RecruitSelectionApplicantPassedModel model;
    private Map<String, Long> mapDepartment = new HashMap<>();
    private Map<String, Long> mapGolonganJabatan = new HashMap<>();
    private Map<String, Long> mapEmployeeType = new HashMap<>();
    private Map<String, Long> mapSalaryGrade = new HashMap<>();
    private Boolean isUpdate;
    private Boolean isInternalCandidate;
    
    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try {
	        model = new RecruitSelectionApplicantPassedModel();
	        isUpdate = Boolean.FALSE;
	        
	        List<Department> listDepartment = departmentService.getAllData();
	        listDepartment.stream().forEach(department -> mapDepartment.put(department.getDepartmentName(), department.getId()));
	        
	        List<GolonganJabatan> listGolonganJabatan = golonganJabatanService.getAllData();
	        listGolonganJabatan.stream().forEach(golJabatan -> mapGolonganJabatan.put(golJabatan.getCode(), golJabatan.getId()));
	        
	        List<EmployeeType> listEmployeeTypes = employeeTypeService.getAllData();
	        listEmployeeTypes.stream().forEach(empType -> mapEmployeeType.put(empType.getName(), empType.getId()));
	        
	        List<PaySalaryGrade> listPaySalaryGrades = paySalaryGradeService.getAllData();
	        listPaySalaryGrades.stream().forEach(salaryGrade -> mapSalaryGrade.put(salaryGrade.getGradeSalary() + " - (" + salaryGrade.getMinSalary().longValue() + " - " + salaryGrade.getMaxSalary().longValue() + ")", salaryGrade.getId()));
	        
	        //Parameter yang di lempar ada 2, applicantId & hireApplyId
	        String applicantIdParam = FacesUtil.getRequestParameter("applicantId");
	        String hireApplyIdParam = FacesUtil.getRequestParameter("hireApplyId");
	        
	        Boolean isApplicantIdValid = StringUtils.isNotEmpty(applicantIdParam) && StringUtils.isNumeric(applicantIdParam.substring(1));
	        Boolean isHireApplyIdValid = StringUtils.isNotEmpty(hireApplyIdParam) && StringUtils.isNumeric(hireApplyIdParam.substring(1));
	        
			if (isApplicantIdValid && isHireApplyIdValid) {
				
				Long applicantId = Long.valueOf(applicantIdParam.substring(1));
				Long hireApplyId = Long.valueOf(hireApplyIdParam.substring(1));
				
				//Get Entity by Composite OK (applicantId, hireApplyId)
				RecruitSelectionApplicantPassed selectionApplicantPassed = recruitSelectionApplicantPassedService.getEntityWithDetailByRecruitSelectionApplicantPassedId(new RecruitSelectionApplicantPassedId(hireApplyId,applicantId));
				if (selectionApplicantPassed != null) {
					this.getModelFromEntity(selectionApplicantPassed);
					isUpdate = Boolean.TRUE;
				}
			}
			
        } catch (Exception e) {
        	
            LOGGER.error("Error Init", e);
        }
    }

    @PreDestroy
    public void cleanAndExit() {
    	recruitSelectionApplicantPassedService = null;
        model = null;
		isUpdate = null;
	}

    public String doBack() {
        return "/protected/recruitment/recruitment_employee_setup_view.htm?faces-redirect=true";
    }
    
    public String doSave() {
        try {
        	
        	String result = recruitSelectionApplicantPassedService.setupEmployee(model, isInternalCandidate);
        	MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        	return "/protected/recruitment/recruitment_employee_setup_view.htm?faces-redirect=true";
            
        } catch (BussinessException ex) { 
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
            ex.printStackTrace();
        }
        
        return null;
    }

    
    private void getModelFromEntity(RecruitSelectionApplicantPassed entity) throws Exception{
    	model.setApplicantId(entity.getId().getApplicantId());
    	model.setHireApplyId(entity.getHireApply().getId());
    	
    	model.setApplicantName(entity.getApplicant().getBioData().getFullName());
    	model.setDateOfBirth(entity.getApplicant().getBioData().getDateOfBirth());
    	model.setDepartmentId(entity.getHireApply().getJabatan().getDepartment().getId());
    	model.setGolonganJabatanId(entity.getHireApply().getJabatan().getGolonganJabatan().getId());
    	model.setEmployeeTypeId(entity.getHireApply().getEmployeeType().getId());
    	model.setJabatanId(entity.getHireApply().getJabatan().getId());
    	model.setJabatanName(entity.getHireApply().getJabatan().getName());
    	isInternalCandidate = entity.getApplicant().getCareerCandidate() == HRMConstant.RECRUIT_APPLICANT_CAREER_CANDIDATE_INTERNAL;
    	if(isInternalCandidate){
    		EmpData empData = empDataService.getByBioDataIdWithDepartment(entity.getApplicant().getBioData().getId());
    		model.setNik(empData.getNik());
    		model.setTmbDate(empData.getJoinDate());
    	}
    }

	public RecruitSelectionApplicantPassedModel getModel() {
		return model;
	}

	public void setModel(RecruitSelectionApplicantPassedModel model) {
		this.model = model;
	}

	public Boolean getIsUpdate() {
		return isUpdate;
	}

	public void setIsUpdate(Boolean isUpdate) {
		this.isUpdate = isUpdate;
	}

	public void setRecruitSelectionApplicantPassedService(
			RecruitSelectionApplicantPassedService recruitSelectionApplicantPassedService) {
		this.recruitSelectionApplicantPassedService = recruitSelectionApplicantPassedService;
	}

	public Map<String, Long> getMapDepartment() {
		return mapDepartment;
	}

	public void setMapDepartment(Map<String, Long> mapDepartment) {
		this.mapDepartment = mapDepartment;
	}

	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	public Map<String, Long> getMapGolonganJabatan() {
		return mapGolonganJabatan;
	}

	public void setMapGolonganJabatan(Map<String, Long> mapGolonganJabatan) {
		this.mapGolonganJabatan = mapGolonganJabatan;
	}

	public void setGolonganJabatanService(GolonganJabatanService golonganJabatanService) {
		this.golonganJabatanService = golonganJabatanService;
	}

	public void setEmployeeTypeService(EmployeeTypeService employeeTypeService) {
		this.employeeTypeService = employeeTypeService;
	}

	public Map<String, Long> getMapEmployeeType() {
		return mapEmployeeType;
	}

	public void setMapEmployeeType(Map<String, Long> mapEmployeeType) {
		this.mapEmployeeType = mapEmployeeType;
	}

	public Map<String, Long> getMapSalaryGrade() {
		return mapSalaryGrade;
	}

	public void setMapSalaryGrade(Map<String, Long> mapSalaryGrade) {
		this.mapSalaryGrade = mapSalaryGrade;
	}

	public void setPaySalaryGradeService(PaySalaryGradeService paySalaryGradeService) {
		this.paySalaryGradeService = paySalaryGradeService;
	}

	public Boolean getIsInternalCandidate() {
		return isInternalCandidate;
	}

	public void setIsInternalCandidate(Boolean isInternalCandidate) {
		this.isInternalCandidate = isInternalCandidate;
	}

	public void setEmpDataService(EmpDataService empDataService) {
		this.empDataService = empDataService;
	}

	
    
}
