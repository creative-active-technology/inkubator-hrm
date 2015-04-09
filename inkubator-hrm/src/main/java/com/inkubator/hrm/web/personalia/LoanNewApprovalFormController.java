/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.personalia;

import com.google.gson.Gson;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.ApprovalActivity;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.Loan;
import com.inkubator.hrm.entity.LoanNewApplication;
import com.inkubator.hrm.entity.LoanNewApplicationInstallment;
import com.inkubator.hrm.entity.LoanPaymentDetail;
import com.inkubator.hrm.json.util.JsonUtil;
import com.inkubator.hrm.service.ApprovalActivityService;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.LoanNewApplicationService;
import com.inkubator.hrm.service.LoanService;
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
@ManagedBean(name = "loanNewApprovalFormController")
@ViewScoped
public class LoanNewApprovalFormController extends BaseController {

    private LoanNewApplication selectedLoanNewApplication;
    //private List<LoanPaymentDetail> loanPaymentDetails;
    private List<LoanNewApplicationInstallment> listLoanInstallment;
    private String comment;
    private Boolean isWaitingApproval;
    private Boolean isWaitingRevised;
    private Boolean isApprover;
    private Boolean isRequester;
    private ApprovalActivity selectedApprovalActivity;
    @ManagedProperty(value = "#{loanNewApplicationService}")
    private LoanNewApplicationService loanNewApplicationService;
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
            isWaitingRevised = selectedApprovalActivity.getApprovalStatus() == HRMConstant.APPROVAL_STATUS_WAITING_REVISED;            
            isApprover = StringUtils.equals(UserInfoUtil.getUserName(), selectedApprovalActivity.getApprovedBy());
            isRequester = StringUtils.equals(UserInfoUtil.getUserName(), selectedApprovalActivity.getRequestBy());
            
            Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
            selectedLoanNewApplication = gson.fromJson(selectedApprovalActivity.getPendingData(), LoanNewApplication.class);
            EmpData empData = empDataService.getByIdWithDetail(selectedLoanNewApplication.getEmpData().getId());
            selectedLoanNewApplication.setEmpData(empData);
            listLoanInstallment = loanNewApplicationService.getAllDataLoanNewApplicationInstallment(selectedLoanNewApplication.getLoanNewType().getInterest().doubleValue(), selectedLoanNewApplication.getTermin(), selectedLoanNewApplication.getFirstLoanPaymentDate(), selectedLoanNewApplication.getNominalPrincipal(), selectedLoanNewApplication.getLoanNewType().getInterestMethod());           
        } catch (Exception ex) {
            LOGGER.error("Error", ex);

        }
    }

    @PreDestroy
    public void cleanAndExit() {
        selectedLoanNewApplication = null;
        listLoanInstallment = null;
        loanNewApplicationService = null;
        selectedApprovalActivity = null;
        approvalActivityService = null;
        comment = null;
        empDataService = null;
        isWaitingApproval = null;
        isApprover = null;
        isRequester = null;
    }

    

    public ApprovalActivity getSelectedApprovalActivity() {
        return selectedApprovalActivity;
    }

    public void setSelectedApprovalActivity(
            ApprovalActivity selectedApprovalActivity) {
        this.selectedApprovalActivity = selectedApprovalActivity;
    }

    public LoanNewApplication getSelectedLoanNewApplication() {
        return selectedLoanNewApplication;
    }

    public void setSelectedLoanNewApplication(LoanNewApplication selectedLoanNewApplication) {
        this.selectedLoanNewApplication = selectedLoanNewApplication;
    }

    public void setLoanNewApplicationService(LoanNewApplicationService loanNewApplicationService) {
        this.loanNewApplicationService = loanNewApplicationService;
    }

    public List<LoanNewApplicationInstallment> getListLoanInstallment() {
        return listLoanInstallment;
    }

    public void setListLoanInstallment(List<LoanNewApplicationInstallment> listLoanInstallment) {
        this.listLoanInstallment = listLoanInstallment;
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

	public Boolean getIsRequester() {
		return isRequester;
	}

	public void setIsRequester(Boolean isRequester) {
		this.isRequester = isRequester;
	}

    public Boolean getIsWaitingRevised() {
        return isWaitingRevised;
    }

    public void setIsWaitingRevised(Boolean isWaitingRevised) {
        this.isWaitingRevised = isWaitingRevised;
    }
        
        

    public String doBack() {
        return "/protected/home.htm?faces-redirect=true";
    }
    
    public String doRevised() {
    	return "/protected/personalia/loan_application_form.htm?faces-redirect=true&activity=e" + selectedApprovalActivity.getId();
    }
    
    public Boolean getIsPaginator() {
        return listLoanInstallment.size() > 11;
    }

    public String doApproved() {
        try {
            loanNewApplicationService.approved(selectedApprovalActivity.getId(), null, comment);
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
            loanNewApplicationService.rejected(selectedApprovalActivity.getId(), comment);
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
        	loanNewApplicationService.cancelled(selectedApprovalActivity.getId(), comment);
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
    
    public String doAskingRevised() {
        try {
            loanNewApplicationService.askingRevised(selectedApprovalActivity.getId(), comment);
            MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.approval_info", "global.process_successfully",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            return "/protected/home.htm?faces-redirect=true";
        } catch (BussinessException ex) {            
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception e) {
            LOGGER.error("Error ", e);
        }
        return null;
    }

}
