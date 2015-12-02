/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.career;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.ApprovalActivity;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.GolonganJabatan;
import com.inkubator.hrm.json.util.JsonUtil;
import com.inkubator.hrm.service.ApprovalActivityService;
import com.inkubator.hrm.service.CareerTransitionService;
import com.inkubator.hrm.service.DepartmentService;
import com.inkubator.hrm.service.EmpCareerHistoryService;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.EmployeeTypeService;
import com.inkubator.hrm.service.GolonganJabatanService;
import com.inkubator.hrm.service.JabatanService;
import com.inkubator.hrm.web.model.EmpCareerHistoryModel;
import com.inkubator.securitycore.util.UserInfoUtil;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "empCareerTransitionApprovalFormController")
@ViewScoped
public class EmpCareerTransitionApprovalFormController extends BaseController {

	private EmpCareerHistoryModel model; 
    private String comment;
    private Boolean isWaitingApproval;
    private Boolean isWaitingRevised;
    private Boolean isApprover;
    private Boolean isRequester;
    private ApprovalActivity currentActivity;
    private ApprovalActivity askingRevisedActivity;
    
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
        try {
            super.initialization();
            
            /** initial data for approval activity tracking */
            String id = FacesUtil.getRequestParameter("execution");
            currentActivity = approvalActivityService.getEntiyByPK(Long.parseLong(id.substring(1)));
            askingRevisedActivity = approvalActivityService.getEntityByActivityNumberAndSequence(currentActivity.getActivityNumber(),
                    currentActivity.getSequence() - 1);
            isWaitingApproval = currentActivity.getApprovalStatus() == HRMConstant.APPROVAL_STATUS_WAITING_APPROVAL;
            isWaitingRevised = currentActivity.getApprovalStatus() == HRMConstant.APPROVAL_STATUS_WAITING_REVISED;
            isApprover = StringUtils.equals(UserInfoUtil.getUserName(), currentActivity.getApprovedBy());
            isRequester = StringUtils.equals(UserInfoUtil.getUserName(), currentActivity.getRequestBy());            
            
            /** start binding data that needed (from json) to object */
            Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
            model = gson.fromJson(currentActivity.getPendingData(), EmpCareerHistoryModel.class);      
            
            //relational object
            EmpData empData = empDataService.getByEmpIdWithDetail(model.getEmpData().getId());
            model.setEmpData(empData);
            model.setCompanyName(empData.getJabatanByJabatanId().getDepartment().getCompany().getName());
            model.setCopyOfLetterTo(empDataService.getByEmpIdWithDetail(model.getCopyOfLetterTo().getId()));
            model.setCareerTransitionName(careerTransitionService.getEntiyByPK(model.getCareerTransitionId()).getTransitionName());
            model.setDepartmentName(departmentService.getEntiyByPK(model.getDepartmentId()).getDepartmentName());
            model.setJabatanName(jabatanService.getEntiyByPK(model.getJabatanId()).getName());
            GolonganJabatan golonganJabatan = golonganJabatanService.getEntityByPkFetchAttendPangkat(model.getGolonganJabatanId());
            model.setGolonganJabatanName(golonganJabatan.getCode() + " " + golonganJabatan.getPangkat().getPangkatName());
            model.setEmployeeTypeName(employeeTypeService.getEntiyByPK(model.getEmployeeTypeId()).getName());
            
            
            //additional information
            model.setCurrentCompany(empData.getJabatanByJabatanId().getDepartment().getCompany().getName());
        	model.setCurrentDepartment(empData.getJabatanByJabatanId().getDepartment().getDepartmentName());
        	model.setCurrentEmployeeType(empData.getEmployeeType().getName());
        	model.setCurrentGolJab(empData.getGolonganJabatan().getCode() + " " + empData.getGolonganJabatan().getPangkat().getPangkatName());
        	model.setCurrentJabatan(empData.getJabatanByJabatanId().getName());
        	model.setCurrentJoinDate(empData.getJoinDate());
        	model.setCurrentNik(empData.getNik());
            
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    @PreDestroy
    public void cleanAndExit() {
    	model = null;
        isWaitingRevised = null;
        currentActivity = null;
        approvalActivityService = null;
        comment = null;
        isWaitingApproval = null;
        isApprover = null;
        isRequester = null;
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

    public String doRevised() {
    	return "/protected/career/emp_career_transition_form.htm?faces-redirect=true&activity=e" + currentActivity.getId();
    }
    
    public String doAskingRevised() {
        try {
        	empCareerHistoryService.askingRevised(currentActivity.getId(), comment);
            MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.approval_info", "global.process_successfully",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            return "/protected/career/emp_career_transition_view.htm?faces-redirect=true";
        } catch (BussinessException ex) {            
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception e) {
            LOGGER.error("Error ", e);
        }
        return null;
    }
    
    public String doApproved() {
        try {
        	empCareerHistoryService.approved(currentActivity.getId(), null, comment);
            MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.approval_info", "global.approved_successfully",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            return "/protected/career/emp_career_transition_view.htm?faces-redirect=true";
        } catch (BussinessException ex) {            
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception e) {
            LOGGER.error("Error ", e);
        }
        return null;
    }

    public String doRejected() {
        try {
        	empCareerHistoryService.rejected(currentActivity.getId(), comment);
            MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.approval_info", "global.rejected_successfully",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            return "/protected/career/emp_career_transition_view.htm?faces-redirect=true";
        } catch (BussinessException ex) {            
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception e) {
            LOGGER.error("Error ", e);
        }
        return null;
    }

	public EmpCareerHistoryModel getModel() {
		return model;
	}

	public void setModel(EmpCareerHistoryModel model) {
		this.model = model;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Boolean getIsWaitingApproval() {
		return isWaitingApproval;
	}

	public void setIsWaitingApproval(Boolean isWaitingApproval) {
		this.isWaitingApproval = isWaitingApproval;
	}

	public Boolean getIsWaitingRevised() {
		return isWaitingRevised;
	}

	public void setIsWaitingRevised(Boolean isWaitingRevised) {
		this.isWaitingRevised = isWaitingRevised;
	}

	public Boolean getIsApprover() {
		return isApprover;
	}

	public void setIsApprover(Boolean isApprover) {
		this.isApprover = isApprover;
	}

	public Boolean getIsRequester() {
		return isRequester;
	}

	public void setIsRequester(Boolean isRequester) {
		this.isRequester = isRequester;
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
