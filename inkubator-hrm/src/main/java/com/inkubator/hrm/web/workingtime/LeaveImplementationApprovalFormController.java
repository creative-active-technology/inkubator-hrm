/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.workingtime;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.DualListModel;

import ch.lambdaj.Lambda;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.ApprovalActivity;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.LeaveImplementation;
import com.inkubator.hrm.entity.LeaveImplementationDate;
import com.inkubator.hrm.json.util.JsonUtil;
import com.inkubator.hrm.service.ApprovalActivityService;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.LeaveImplementationService;
import com.inkubator.securitycore.util.UserInfoUtil;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "leaveImplementationApprovalFormController")
@ViewScoped
public class LeaveImplementationApprovalFormController extends BaseController {

    private LeaveImplementation selectedLeaveImplementation;
    private String comment;
    private Boolean isWaitingApproval;
    private Boolean isApprover;
    private Boolean isRequester;
    private Boolean isCancellationProcess;
    private String cancellationDescription;
    private DualListModel<LeaveImplementationDate> leaveDatesDualModel;
    private ApprovalActivity selectedApprovalActivity;
    @ManagedProperty(value = "#{leaveImplementationService}")
    private LeaveImplementationService leaveImplementationService;
    @ManagedProperty(value = "#{approvalActivityService}")
    private ApprovalActivityService approvalActivityService;
    @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;

    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            String id = FacesUtil.getRequestParameter("execution");
            selectedApprovalActivity = approvalActivityService.getEntiyByPK(Long.parseLong(id.substring(1)));
            isWaitingApproval = selectedApprovalActivity.getApprovalStatus() == HRMConstant.APPROVAL_STATUS_WAITING_APPROVAL;
            isApprover = StringUtils.equals(UserInfoUtil.getUserName(), selectedApprovalActivity.getApprovedBy());
            isRequester = StringUtils.equals(UserInfoUtil.getUserName(), selectedApprovalActivity.getRequestBy());
            
            Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
            JsonObject jsonObject =  gson.fromJson(selectedApprovalActivity.getPendingData(), JsonObject.class);
            isCancellationProcess = jsonObject.get("isCancellationProcess") != null;
            if(isCancellationProcess) {
            	List<LeaveImplementationDate> cancellationDates = gson.fromJson(jsonObject.get("cancellationDates"), new TypeToken<List<LeaveImplementationDate>>(){}.getType());
            	List<LeaveImplementationDate> actualDates = gson.fromJson(jsonObject.get("actualDates"), new TypeToken<List<LeaveImplementationDate>>(){}.getType());
            	//sort by actual date
            	cancellationDates = Lambda.sort(cancellationDates, Lambda.on(LeaveImplementationDate.class).getActualDate());            	
            	actualDates = Lambda.sort(actualDates, Lambda.on(LeaveImplementationDate.class).getActualDate());
            	
            	leaveDatesDualModel = new DualListModel<LeaveImplementationDate>(actualDates, cancellationDates);
            	cancellationDescription = jsonObject.get("cancellationDescription").getAsString();
            }
            selectedLeaveImplementation = gson.fromJson(selectedApprovalActivity.getPendingData(), LeaveImplementation.class);
            EmpData empData = empDataService.getByIdWithDetail(selectedLeaveImplementation.getEmpData().getId());
            selectedLeaveImplementation.setEmpData(empData);
            
            if(selectedLeaveImplementation.getTemporaryActing() != null){
            	EmpData temporaryActing = empDataService.getByIdWithDetail(selectedLeaveImplementation.getTemporaryActing().getId());            
            	selectedLeaveImplementation.setTemporaryActing(temporaryActing);
            }
        } catch (Exception ex) {
            LOGGER.error("Error", ex);

        }
    }

    @PreDestroy
    public void cleanAndExit() {
    	selectedLeaveImplementation = null;
    	leaveImplementationService = null;
        selectedApprovalActivity = null;
        approvalActivityService = null;
        comment = null;
        empDataService = null;
        isWaitingApproval = null;
        isApprover = null;
        isRequester = null;
        isCancellationProcess = null;
        leaveDatesDualModel = null;
        cancellationDescription = null;
    }

    public LeaveImplementation getSelectedLeaveImplementation() {
		return selectedLeaveImplementation;
	}

	public void setSelectedLeaveImplementation(
			LeaveImplementation selectedLeaveImplementation) {
		this.selectedLeaveImplementation = selectedLeaveImplementation;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public ApprovalActivity getSelectedApprovalActivity() {
		return selectedApprovalActivity;
	}

	public void setSelectedApprovalActivity(
			ApprovalActivity selectedApprovalActivity) {
		this.selectedApprovalActivity = selectedApprovalActivity;
	}

	public void setLeaveImplementationService(
			LeaveImplementationService leaveImplementationService) {
		this.leaveImplementationService = leaveImplementationService;
	}

	public void setApprovalActivityService(
			ApprovalActivityService approvalActivityService) {
		this.approvalActivityService = approvalActivityService;
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

	public Boolean getIsRequester() {
		return isRequester;
	}

	public void setIsRequester(Boolean isRequester) {
		this.isRequester = isRequester;
	}

	public Boolean getIsCancellationProcess() {
		return isCancellationProcess;
	}

	public void setIsCancellationProcess(Boolean isCancellationProcess) {
		this.isCancellationProcess = isCancellationProcess;
	}

	public DualListModel<LeaveImplementationDate> getLeaveDatesDualModel() {
		return leaveDatesDualModel;
	}

	public void setLeaveDatesDualModel(
			DualListModel<LeaveImplementationDate> leaveDatesDualModel) {
		this.leaveDatesDualModel = leaveDatesDualModel;
	}

	public String getCancellationDescription() {
		return cancellationDescription;
	}

	public void setCancellationDescription(String cancellationDescription) {
		this.cancellationDescription = cancellationDescription;
	}

	public String doBack() {
        return "/protected/home.htm?faces-redirect=true";
    }

    public String doApproved() {
        try {
        	leaveImplementationService.approved(selectedApprovalActivity.getId(), null, comment);
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
        	leaveImplementationService.rejected(selectedApprovalActivity.getId(), comment);
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
    
    public String doCancelled() {
        try {
        	leaveImplementationService.cancelled(selectedApprovalActivity.getId(), comment);
            MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.approval_info", "global.cancelled_successfully",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            return "/protected/home.htm?faces-redirect=true";
        } catch (BussinessException ex) {            
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception e) {
            LOGGER.error("Error when rejected process ", e);
        }
        return null;
    }

}
