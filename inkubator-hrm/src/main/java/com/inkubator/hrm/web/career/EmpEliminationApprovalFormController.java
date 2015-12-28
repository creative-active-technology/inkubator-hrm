/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.career;

import com.google.gson.Gson;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.ApprovalActivity;
import com.inkubator.hrm.entity.CareerEmpElimination;
import com.inkubator.hrm.entity.CareerTerminationType;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.json.util.JsonUtil;
import com.inkubator.hrm.service.ApprovalActivityService;
import com.inkubator.hrm.service.CareerEmpEliminationService;
import com.inkubator.hrm.service.CareerTerminationTypeService;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.web.model.EmpEliminationModel;
import com.inkubator.securitycore.util.UserInfoUtil;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
@ManagedBean(name = "empEliminationApprovalFormController")
@ViewScoped
public class EmpEliminationApprovalFormController extends BaseController {

    private EmpEliminationModel model;
    private String comment;
    private Boolean isWaitingApproval;
    private Boolean isApprover;
    private ApprovalActivity selectedApprovalActivity;
    @ManagedProperty(value = "#{careerEmpEliminationService}")
    private CareerEmpEliminationService careerEmpEliminationService;
    @ManagedProperty(value = "#{approvalActivityService}")
    private ApprovalActivityService approvalActivityService;
    @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;
    @ManagedProperty(value = "#{careerTerminationTypeService}")
    private CareerTerminationTypeService careerTerminationTypeService;

    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            String id = FacesUtil.getRequestParameter("execution");
            selectedApprovalActivity = approvalActivityService.getEntiyByPK(Long.parseLong(id.substring(1)));
            model = new EmpEliminationModel();
            isWaitingApproval = selectedApprovalActivity.getApprovalStatus() == HRMConstant.APPROVAL_STATUS_WAITING_APPROVAL;
            isApprover = StringUtils.equals(UserInfoUtil.getUserName(), selectedApprovalActivity.getApprovedBy());
            
            generateEmpEliminationModel();
            
        } catch (Exception ex) {
            LOGGER.error("Error", ex);

        }
    }
    
    private void generateEmpEliminationModel() throws Exception{
    	 Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
         CareerEmpElimination selectedCareerEmpElimination = gson.fromJson(selectedApprovalActivity.getPendingData(), CareerEmpElimination.class);
         EmpData empData = empDataService.getByIdWithDetail(selectedCareerEmpElimination.getEmpData().getId());
         CareerTerminationType careerTerminationType = careerTerminationTypeService.getEntiyByPK(selectedCareerEmpElimination.getCareerTerminationType().getId());
         model = empDataService.generateEmpEliminationModelByEmpDataId(empData.getId());
         model.setEmpData(empData);
         model.setJabatanName(empData.getJabatanByJabatanId().getName());
         model.setTerminationTypeId(selectedCareerEmpElimination.getCareerTerminationType().getId());
         model.setTerminationTypeName(careerTerminationType.getName());
         model.setReason(selectedCareerEmpElimination.getReason());
         model.setSeparationPay(selectedCareerEmpElimination.getSeparationPay());
         model.setRemainSeparationPay(selectedCareerEmpElimination.getSeparationPay() - selectedCareerEmpElimination.getPendingLoan());
         model.setEffectiveDate(selectedCareerEmpElimination.getEffectiveDate());
    }

    @PreDestroy
    public void cleanAndExit() {
        careerEmpEliminationService = null;
        selectedApprovalActivity = null;
        approvalActivityService = null;
        isWaitingApproval = null;
        empDataService = null;
        isApprover = null;
        comment = null;
        model = null;
    }

    public String doApproved() {
        try {
            careerEmpEliminationService.approved(selectedApprovalActivity.getId(), null, comment);
            MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.approval_info", "global.approved_successfully",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            return "/protected/home.htm?faces-redirect=true";
        } catch (BussinessException ex) {            
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception e) {
            LOGGER.error("Error when approved process ", e);
        }
        return null;
    }

    public String doRejected() {
        try {
        	careerEmpEliminationService.rejected(selectedApprovalActivity.getId(), comment);
            MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.approval_info", "global.rejected_successfully",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            return "/protected/home.htm?faces-redirect=true";
        } catch (BussinessException ex) {            
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception e) {
            LOGGER.error("Error when rejected process ", e);
        }
        return null;
    }
    
    public String doBack() {
        return "/protected/home.htm?faces-redirect=true";
    }
    
    public void setCareerEmpEliminationService(CareerEmpEliminationService careerEmpEliminationService) {
		this.careerEmpEliminationService = careerEmpEliminationService;
	}

	public ApprovalActivity getSelectedApprovalActivity() {
        return selectedApprovalActivity;
    }

    public void setSelectedApprovalActivity(
            ApprovalActivity selectedApprovalActivity) {
        this.selectedApprovalActivity = selectedApprovalActivity;
    }

    public EmpEliminationModel getModel() {
		return model;
	}

	public void setModel(EmpEliminationModel model) {
		this.model = model;
	}

	public void setApprovalActivityService(
            ApprovalActivityService approvalActivityService) {
        this.approvalActivityService = approvalActivityService;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setEmpDataService(EmpDataService empDataService) {
        this.empDataService = empDataService;
    }

    public Boolean getIsWaitingApproval() {
        return isWaitingApproval;
    }

    public void setIsWaitingApproval(Boolean isWaitingApproval) {
        this.isWaitingApproval = isWaitingApproval;
    }
    
    public Boolean getIsApprover() {
		return isApprover;
	}

	public void setIsApprover(Boolean isApprover) {
		this.isApprover = isApprover;
	}

	public void setCareerTerminationTypeService(CareerTerminationTypeService careerTerminationTypeService) {
		this.careerTerminationTypeService = careerTerminationTypeService;
	}
    

}
