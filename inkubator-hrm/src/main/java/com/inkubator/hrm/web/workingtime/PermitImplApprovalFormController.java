package com.inkubator.hrm.web.workingtime;

import java.util.Date;

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
import com.inkubator.hrm.entity.PermitClassification;
import com.inkubator.hrm.entity.PermitImplementation;
import com.inkubator.hrm.json.util.DateJsonDeserializer;
import com.inkubator.hrm.json.util.JsonUtil;
import com.inkubator.hrm.service.ApprovalActivityService;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.PermitClassificationService;
import com.inkubator.hrm.service.PermitImplementationService;
import com.inkubator.securitycore.util.UserInfoUtil;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;



@ManagedBean(name = "permitImplApprovalFormController")
@ViewScoped
public class PermitImplApprovalFormController extends BaseController {
	 	private PermitImplementation selectedPermitImplementation;
	    private String comment;
	    private Boolean isWaitingApproval;
	    private Boolean isApprover;
	    private Boolean isRequester;
	    private ApprovalActivity selectedApprovalActivity;
	    @ManagedProperty(value = "#{permitImplementationService}")
	    private PermitImplementationService permitImplementationService;
	    @ManagedProperty(value = "#{approvalActivityService}")
	    private ApprovalActivityService approvalActivityService;
	    @ManagedProperty(value = "#{empDataService}")
	    private EmpDataService empDataService;
	    @ManagedProperty(value = "#{permitClassificationService}")
	    private PermitClassificationService permitClassificationService;
	    
	    @PostConstruct
	    @Override
	    public void initialization() {
	        try {
	            super.initialization();
	            String id = FacesUtil.getRequestParameter("execution");
	            selectedApprovalActivity = approvalActivityService.getEntiyByPK(Long.parseLong(id.substring(1)));
	            isWaitingApproval = selectedApprovalActivity.getApprovalStatus() == HRMConstant.APPROVAL_STATUS_WAITING;
	            isApprover = StringUtils.equals(UserInfoUtil.getUserName(), selectedApprovalActivity.getApprovedBy());
	            isRequester = StringUtils.equals(UserInfoUtil.getUserName(), selectedApprovalActivity.getRequestBy());
	            
	            Gson gson = JsonUtil.getHibernateEntityGsonBuilder().registerTypeAdapter(Date.class, new DateJsonDeserializer()).create();
	            selectedPermitImplementation = gson.fromJson(selectedApprovalActivity.getPendingData(), PermitImplementation.class);
	            EmpData empData = empDataService.getByIdWithDetail(selectedPermitImplementation.getEmpData().getId());
	            PermitClassification permitClassification = permitClassificationService.getEntiyByPK(selectedPermitImplementation.getPermitClassification().getId());
	            selectedPermitImplementation.setEmpData(empData);  
	            selectedPermitImplementation.setPermitClassification(permitClassification);
	        } catch (Exception ex) {
	            LOGGER.error("Error", ex);

	        }
	    }
	    
	    @PreDestroy
	    public void cleanAndExit() {
	    	selectedPermitImplementation = null;
	    	permitImplementationService = null;
	        selectedApprovalActivity = null;
	        approvalActivityService = null;
	        permitClassificationService = null;
	        comment = null;
	        empDataService = null;
	        isWaitingApproval = null;
	        isApprover = null;
	        isRequester = null;
	    }

	    public String doApproved() {
	        try {
	        	permitImplementationService.approved(selectedApprovalActivity.getId(), null, comment);
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
	        	permitImplementationService.rejected(selectedApprovalActivity.getId(), comment);
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
	        	permitImplementationService.cancelled(selectedApprovalActivity.getId(), comment);
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
	    
	    public String doBack() {
	        return "/protected/home.htm?faces-redirect=true";
	    }

		public PermitImplementation getSelectedPermitImplementation() {
			return selectedPermitImplementation;
		}

		public void setSelectedPermitImplementation(
				PermitImplementation selectedPermitImplementation) {
			this.selectedPermitImplementation = selectedPermitImplementation;
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
		public ApprovalActivity getSelectedApprovalActivity() {
			return selectedApprovalActivity;
		}
		public void setSelectedApprovalActivity(
				ApprovalActivity selectedApprovalActivity) {
			this.selectedApprovalActivity = selectedApprovalActivity;
		}
		public PermitImplementationService getPermitImplementationService() {
			return permitImplementationService;
		}
		public void setPermitImplementationService(
				PermitImplementationService permitImplementationService) {
			this.permitImplementationService = permitImplementationService;
		}
		public ApprovalActivityService getApprovalActivityService() {
			return approvalActivityService;
		}
		public void setApprovalActivityService(
				ApprovalActivityService approvalActivityService) {
			this.approvalActivityService = approvalActivityService;
		}
		public EmpDataService getEmpDataService() {
			return empDataService;
		}
		public void setEmpDataService(EmpDataService empDataService) {
			this.empDataService = empDataService;
		}

		public PermitClassificationService getPermitClassificationService() {
			return permitClassificationService;
		}

		public void setPermitClassificationService(
				PermitClassificationService permitClassificationService) {
			this.permitClassificationService = permitClassificationService;
		}
	    
	    
}