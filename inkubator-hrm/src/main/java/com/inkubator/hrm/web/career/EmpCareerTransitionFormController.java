package com.inkubator.hrm.web.career;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;
import org.hamcrest.Matchers;

import com.google.gson.Gson;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.ApprovalActivity;
import com.inkubator.hrm.entity.Department;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.json.util.JsonUtil;
import com.inkubator.hrm.service.ApprovalActivityService;
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
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

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
    @ManagedProperty(value = "#{approvalActivityService}")
    private ApprovalActivityService approvalActivityService;
    
	@PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try {
        	//initial
        	isAdministator = Lambda.exists(UserInfoUtil.getRoles(), Matchers.containsString(HRMConstant.ADMINISTRATOR_ROLE));
            isRevised = Boolean.FALSE;
        	model = new EmpCareerHistoryModel();
            model.setListCareerTransition(careerTransitionService.getAllData());
            model.setListDepartment(departmentService.getAllDataWithoutSpecificCompany());
            model.setListGolonganJabatan(golonganJabatanService.getAllWithDetail());
            model.setListEmployeeType(employeeTypeService.getAllData());
                        
            //di cek terlebih dahulu, jika datangnya dari proses approval, artinya user akan melakukan revisi data yg masih dalam bentuk json	        
            String appActivityId = FacesUtil.getRequestParameter("activity");
            if (StringUtils.isNotEmpty(appActivityId)) {
                //parsing data from json to object 
                isRevised = Boolean.TRUE;
                currentActivity = approvalActivityService.getEntityByPkWithDetail(Long.parseLong(appActivityId.substring(1)));
                askingRevisedActivity = approvalActivityService.getEntityByActivityNumberAndSequence(currentActivity.getActivityNumber(),
                        currentActivity.getSequence() - 1);
                this.getModelFromJson(currentActivity.getPendingData());

            } else {            	
            	if (!isAdministator) { //jika bukan administrator, langsung di set empData berdasarkan yang login
            		model.setEmpData(HrmUserInfoUtil.getEmpData());
            		this.onChangeEmployee();
            	}
            	model.setSalaryChangesType(HRMConstant.SALARY_INCREASES); //default salary increases
            }
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }        
	}
	
	@PreDestroy
    public void cleanAndExit() {
		isAdministator = null;
	    isRevised = null;
	    currentActivity = null;
	    askingRevisedActivity = null;
	    model = null;
	    empCareerHistoryService = null;
	    empDataService = null;
	    departmentService = null;
	    jabatanService = null;
	    golonganJabatanService = null;
	    employeeTypeService = null;
	    careerTransitionService = null;
	    approvalActivityService = null;
	}
	
	public String doBack() {
        return "/protected/career/emp_career_transition_view.htm?faces-redirect=true";
    }
	
	public void doReset(){
		
	}
	
	public String doSave() {
        String path = "";
        try {
            String message = empCareerHistoryService.saveTransitionWithApproval(model);
            if (StringUtils.equals(message, "success_need_approval")) {
                path = "/protected/career/emp_career_transition_form.htm?faces-redirect=true";
                MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully_and_requires_approval", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

            } else {
                path = "/protected/career/emp_career_transition_form.htm?faces-redirect=true";
                MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            }
            return path;

        } catch (BussinessException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
        return null;
	}
	
	public String doRevised() {
		String path = "";
        try {
            String message = empCareerHistoryService.saveTransitionWithRevised(model, currentActivity.getId());
            if (StringUtils.equals(message, "success_need_approval")) {
                path = "/protected/career/emp_career_transition_form.htm?faces-redirect=true";
                MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully_and_requires_approval", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

            } else {
                path = "/protected/career/emp_career_transition_form.htm?faces-redirect=true";
                MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            }
            return path;

        } catch (BussinessException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
        return null;
	}
	
	
	private void getModelFromJson(String json) {
		try {
			Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
			EmpCareerHistoryModel m = gson.fromJson(json, EmpCareerHistoryModel.class);
			
			EmpData empData = empDataService.getByIdWithDetail(m.getEmpData().getId());
			EmpData copyOfLetterTo = empDataService.getByIdWithDetail(m.getCopyOfLetterTo().getId());
			Department department = departmentService.getEntityByPkWithDetail(m.getDepartmentId());
			
			model.setEmpData(empData);
			model.setCareerTransitionId(m.getCareerTransitionId());
			model.setEffectiveDate(m.getEffectiveDate());
			model.setCopyOfLetterTo(copyOfLetterTo);
			model.setDepartmentId(m.getDepartmentId());
			model.setJabatanId(m.getJabatanId());
			model.setGolonganJabatanId(m.getGolonganJabatanId());
			model.setEmployeeTypeId(m.getEmployeeTypeId());
			model.setJoinDate(m.getJoinDate());
			model.setSalaryChangesType(m.getSalaryChangesType());
			model.setSalaryChangesPercent(m.getSalaryChangesPercent());
			model.setNoSk(m.getNoSk());
			model.setNotes(m.getNotes());
			model.setCompanyName(department.getCompany().getName());
		
		} catch (Exception e) {
	        LOGGER.error("Error", e);
	    }   
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
        	model.setCurrentCompany(empData.getJabatanByJabatanId().getDepartment().getCompany().getName());
        	model.setCurrentDepartment(empData.getJabatanByJabatanId().getDepartment().getDepartmentName());
        	model.setCurrentEmployeeType(empData.getEmployeeType().getName());
        	model.setCurrentGolJab(empData.getGolonganJabatan().getCode() + " " + empData.getGolonganJabatan().getPangkat().getPangkatName());
        	model.setCurrentJabatan(empData.getJabatanByJabatanId().getName());
        	model.setCurrentJoinDate(empData.getJoinDate());
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }
    
    public void onChangeDepartment() {
        try {
        	model.setListJabatan(jabatanService.getByDepartementId(model.getDepartmentId()));
        	Department department =  departmentService.getEntityByPkWithDetail(model.getDepartmentId());
        	String companyName = department.getCompany() == null ? StringUtils.EMPTY : department.getCompany().getName();
        	model.setCompanyName(companyName);
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

	public ApprovalActivityService getApprovalActivityService() {
		return approvalActivityService;
	}

	public void setApprovalActivityService(ApprovalActivityService approvalActivityService) {
		this.approvalActivityService = approvalActivityService;
	}

}
