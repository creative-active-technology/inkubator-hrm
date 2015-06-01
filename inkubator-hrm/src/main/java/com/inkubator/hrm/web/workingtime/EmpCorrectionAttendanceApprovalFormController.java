/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.workingtime;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.ApprovalActivity;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.WtEmpCorrectionAttendance;
import com.inkubator.hrm.entity.WtEmpCorrectionAttendanceDetail;
import com.inkubator.hrm.json.util.DateJsonDeserializer;
import com.inkubator.hrm.json.util.JsonUtil;
import com.inkubator.hrm.service.ApprovalActivityService;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.WtEmpCorrectionAttendanceService;
import com.inkubator.securitycore.util.UserInfoUtil;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "empCorrectionAttendanceApprovalFormController")
@ViewScoped
public class EmpCorrectionAttendanceApprovalFormController extends BaseController {

	private WtEmpCorrectionAttendance empCorrectionAttendance;
	private List<WtEmpCorrectionAttendanceDetail> listDetail;
    private String comment;
    private Boolean isWaitingApproval;
    private Boolean isWaitingRevised;
    private Boolean isApprover;
    private Boolean isRequester;
    private ApprovalActivity currentActivity;
    private ApprovalActivity askingRevisedActivity;
    
    @ManagedProperty(value = "#{approvalActivityService}")
    private ApprovalActivityService approvalActivityService;    
    @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;   
    @ManagedProperty(value = "#{wtEmpCorrectionAttendanceService}")
    private WtEmpCorrectionAttendanceService wtEmpCorrectionAttendanceService;

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
            Gson gson = JsonUtil.getHibernateEntityGsonBuilder().registerTypeAdapter(Date.class, new DateJsonDeserializer()).create();
            empCorrectionAttendance = gson.fromJson(currentActivity.getPendingData(), WtEmpCorrectionAttendance.class);      
            JsonObject jsonObject = gson.fromJson(currentActivity.getPendingData(), JsonObject.class);
            listDetail = gson.fromJson(jsonObject.get("listDetail").getAsString(), new TypeToken<List<WtEmpCorrectionAttendanceDetail>>() {}.getType());
            
            //relational object
            EmpData empData = empDataService.getByEmpIdWithDetail(empCorrectionAttendance.getEmpData().getId());
            empCorrectionAttendance.setEmpData(empData);                   
            
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    @PreDestroy
    public void cleanAndExit() {
        isWaitingRevised = null;
        currentActivity = null;
        askingRevisedActivity = null;
        approvalActivityService = null;
        comment = null;
        isWaitingApproval = null;
        isApprover = null;
        isRequester = null;
        empDataService = null;
        empCorrectionAttendance = null;
        listDetail = null;
    }

    public String doBack() {
        return "/protected/working_time/emp_correction_attendance_view.htm?faces-redirect=true";
    }

    public String doRevised() {
    	return "/protected/working_time/emp_correction_attendance_form.htm?faces-redirect=true&activity=e" + currentActivity.getId();
    }
    
    public String doAskingRevised() {
        try {
        	wtEmpCorrectionAttendanceService.askingRevised(currentActivity.getId(), comment);
            MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.approval_info", "global.process_successfully",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            return "/protected/working_time/emp_correction_attendance_view.htm?faces-redirect=true";
        } catch (BussinessException ex) {            
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception e) {
            LOGGER.error("Error ", e);
        }
        return null;
    }
    
    public String doApproved() {
        try {
        	wtEmpCorrectionAttendanceService.approved(currentActivity.getId(), null, comment);
            MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.approval_info", "global.approved_successfully",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            return "/protected/working_time/emp_correction_attendance_view.htm?faces-redirect=true";
        } catch (BussinessException ex) {            
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception e) {
            LOGGER.error("Error ", e);
        }
        return null;
    }

    public String doRejected() {
        try {
        	wtEmpCorrectionAttendanceService.rejected(currentActivity.getId(), comment);
            MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.approval_info", "global.rejected_successfully",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            return "/protected/working_time/emp_correction_attendance_view.htm?faces-redirect=true";
        } catch (BussinessException ex) {            
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception e) {
            LOGGER.error("Error ", e);
        }
        return null;
    }

	public WtEmpCorrectionAttendance getEmpCorrectionAttendance() {
		return empCorrectionAttendance;
	}

	public void setEmpCorrectionAttendance(WtEmpCorrectionAttendance empCorrectionAttendance) {
		this.empCorrectionAttendance = empCorrectionAttendance;
	}

	public List<WtEmpCorrectionAttendanceDetail> getListDetail() {
		return listDetail;
	}

	public void setListDetail(List<WtEmpCorrectionAttendanceDetail> listDetail) {
		this.listDetail = listDetail;
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

	public ApprovalActivityService getApprovalActivityService() {
		return approvalActivityService;
	}

	public void setApprovalActivityService(ApprovalActivityService approvalActivityService) {
		this.approvalActivityService = approvalActivityService;
	}

	public EmpDataService getEmpDataService() {
		return empDataService;
	}

	public void setEmpDataService(EmpDataService empDataService) {
		this.empDataService = empDataService;
	}

	public WtEmpCorrectionAttendanceService getWtEmpCorrectionAttendanceService() {
		return wtEmpCorrectionAttendanceService;
	}

	public void setWtEmpCorrectionAttendanceService(WtEmpCorrectionAttendanceService wtEmpCorrectionAttendanceService) {
		this.wtEmpCorrectionAttendanceService = wtEmpCorrectionAttendanceService;
	}
	
}
