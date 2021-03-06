/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.workingtime;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.DualListModel;

import ch.lambdaj.Lambda;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.ApprovalActivity;
import com.inkubator.hrm.entity.LeaveImplementation;
import com.inkubator.hrm.entity.LeaveImplementationDate;
import com.inkubator.hrm.service.ApprovalActivityService;
import com.inkubator.hrm.service.LeaveImplementationService;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "leaveImplementationCancelController")
@ViewScoped
public class LeaveImplementationCancelController extends BaseController {

    private LeaveImplementation selectedLeaveImplementation;
    private ApprovalActivity selectedApprovalActivity;
    private String descriptionCancellation;
    private Boolean isAllowCancel;
    private DualListModel<LeaveImplementationDate> leaveDatesDualModel;
    @ManagedProperty(value = "#{leaveImplementationService}")
    private LeaveImplementationService leaveImplementationService;
    @ManagedProperty(value = "#{approvalActivityService}")
    private ApprovalActivityService approvalActivityService;

    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            String execution = FacesUtil.getRequestParameter("execution");
            selectedLeaveImplementation = leaveImplementationService.getEntityByPkWithDetail(Long.parseLong(execution.substring(1)));            
            selectedApprovalActivity = approvalActivityService.getEntityByActivityNumberLastSequence(selectedLeaveImplementation.getApprovalActivityNumber());
            Set<LeaveImplementationDate> leaveImplementationDates = selectedLeaveImplementation.getLeaveImplementationDates();
            
            List<LeaveImplementationDate> leaveDatesActual = new ArrayList<LeaveImplementationDate>();
            List<LeaveImplementationDate> leaveDatesCancel = new ArrayList<LeaveImplementationDate>();
            for(LeaveImplementationDate leaveDate : leaveImplementationDates){
            	if(leaveDate.getIsCancelled()){
            		leaveDatesCancel.add(leaveDate);
            	} else {
            		leaveDatesActual.add(leaveDate);
            	}
            }            
            leaveDatesActual = Lambda.sort(leaveDatesActual, Lambda.on(LeaveImplementationDate.class).getActualDate());
            leaveDatesCancel = Lambda.sort(leaveDatesCancel, Lambda.on(LeaveImplementationDate.class).getActualDate());
            leaveDatesDualModel = new DualListModel<LeaveImplementationDate>(leaveDatesActual, leaveDatesCancel);
            
            isAllowCancel = Boolean.TRUE;
        	if(selectedApprovalActivity != null && 
        			(ObjectUtils.notEqual(selectedApprovalActivity.getApprovalStatus(), HRMConstant.APPROVAL_STATUS_APPROVED) || approvalActivityService.isStillHaveWaitingStatus(selectedApprovalActivity.getActivityNumber()))){
        		isAllowCancel = Boolean.FALSE;
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
        descriptionCancellation = null;
        isAllowCancel = null;
    }    
    
	public LeaveImplementation getSelectedLeaveImplementation() {
		return selectedLeaveImplementation;
	}

	public void setSelectedLeaveImplementation(LeaveImplementation selectedLeaveImplementation) {
		this.selectedLeaveImplementation = selectedLeaveImplementation;
	}

	public void setLeaveImplementationService(LeaveImplementationService leaveImplementationService) {
		this.leaveImplementationService = leaveImplementationService;
	}

	public ApprovalActivity getSelectedApprovalActivity() {
		return selectedApprovalActivity;
	}

	public void setSelectedApprovalActivity(ApprovalActivity selectedApprovalActivity) {
		this.selectedApprovalActivity = selectedApprovalActivity;
	}

	public void setApprovalActivityService(ApprovalActivityService approvalActivityService) {
		this.approvalActivityService = approvalActivityService;
	}

	public DualListModel<LeaveImplementationDate> getLeaveDatesDualModel() {
		return leaveDatesDualModel;
	}

	public void setLeaveDatesDualModel(
			DualListModel<LeaveImplementationDate> leaveDatesDualModel) {
		this.leaveDatesDualModel = leaveDatesDualModel;
	}

	public String getDescriptionCancellation() {
		return descriptionCancellation;
	}

	public void setDescriptionCancellation(String descriptionCancellation) {
		this.descriptionCancellation = descriptionCancellation;
	}

	public Boolean getIsAllowCancel() {
		return isAllowCancel;
	}

	public void setIsAllowCancel(Boolean isAllowCancel) {
		this.isAllowCancel = isAllowCancel;
	}

	public String doBack() {
        return "/protected/working_time/leave_impl_view.htm?faces-redirect=true";
    }
	
	public String doCancellation(){		
		try {
			String message = leaveImplementationService.cancellation(selectedLeaveImplementation.getId(), leaveDatesDualModel.getSource(), leaveDatesDualModel.getTarget(), descriptionCancellation);
			if(StringUtils.equals(message, "success_need_approval")){
        		MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully_and_requires_approval",
        				FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        	} else {
        		MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.update_successfully",
        				FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        	}
			return "/protected/working_time/leave_impl_view.htm?faces-redirect=true";
		} catch (BussinessException ex) { 
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
        return null;		
	}
    
    public Boolean getIsHaveApprovalActivity(){
		return selectedApprovalActivity != null;
	}
}
