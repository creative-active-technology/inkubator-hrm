/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.personalia;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.ApprovalActivity;
import com.inkubator.hrm.entity.WtGroupWorking;
import com.inkubator.hrm.service.ApprovalActivityService;
import com.inkubator.hrm.service.WtGroupWorkingService;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;
import org.primefaces.json.JSONObject;

/**
 *
 * @author Deni Husni FR
 */
@ManagedBean(name = "scheduleApprovalFormController")
@ViewScoped
public class ScheduleApprovalFormController extends BaseController {

//    private Loan selectedLoan;
//    private List<LoanPaymentDetail> loanPaymentDetails;
//    private String comment;
    private ApprovalActivity selectedApprovalActivity;
//    @ManagedProperty(value = "#{loanService}")
//    private LoanService loanService;
    @ManagedProperty(value = "#{approvalActivityService}")
    private ApprovalActivityService approvalActivityService;
    @ManagedProperty(value = "#{wtGroupWorkingService}")
    private WtGroupWorkingService wtGroupWorkingService;
//    @ManagedProperty(value = "#{empDataService}")
//    private EmpDataService empDataService;

    private WtGroupWorking selectedWtGroupWorking;

    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            String id = FacesUtil.getRequestParameter("execution");
            selectedApprovalActivity = approvalActivityService.getEntiyByPK(Long.parseLong(id.substring(1)));
            JSONObject jSONObject = new JSONObject(selectedApprovalActivity.getPendingData());
            long workingGroupId = Long.parseLong(jSONObject.getString("groupWorkingId"));
            selectedWtGroupWorking = selectedWtGroupWorking = wtGroupWorkingService.getEntiyByPK(workingGroupId);
        } catch (Exception ex) {
            LOGGER.error("Error", ex);

        }
    }

    @PreDestroy
    public void cleanAndExit() {
//    	selectedLoan =null;
//    	loanPaymentDetails = null;
//    	loanService = null;
        selectedApprovalActivity = null;
        approvalActivityService = null;
//        comment = null;
//        empDataService = null;
    }

//	public Loan getSelectedLoan() {
//		return selectedLoan;
//	}
//
//	public void setSelectedLoan(Loan selectedLoan) {
//		this.selectedLoan = selectedLoan;
//	}
//
//	public List<LoanPaymentDetail> getLoanPaymentDetails() {
//		return loanPaymentDetails;
//	}
//
//	public void setLoanPaymentDetails(List<LoanPaymentDetail> loanPaymentDetails) {
//		this.loanPaymentDetails = loanPaymentDetails;
//	}
    public ApprovalActivity getSelectedApprovalActivity() {
        return selectedApprovalActivity;
    }

    public void setSelectedApprovalActivity(
            ApprovalActivity selectedApprovalActivity) {
        this.selectedApprovalActivity = selectedApprovalActivity;
    }

//	public void setLoanService(LoanService loanService) {
//		this.loanService = loanService;
//	}
    public void setApprovalActivityService(
            ApprovalActivityService approvalActivityService) {
        this.approvalActivityService = approvalActivityService;
    }
//
//	public String getComment() {
//		return comment;
//	}
//
//	public void setComment(String comment) {
//		this.comment = comment;
//	}
//
//	public void setEmpDataService(EmpDataService empDataService) {
//		this.empDataService = empDataService;
//	}
//	
//	public Boolean getIsPaginator(){
//		return loanPaymentDetails.size() > 11;
//	}

    public String doBack() {
        return "/protected/home.htm";
    }

    public String doApproved() {
        try {
//    		loanService.approved(selectedApprovalActivity.getId(), null, comment);
            MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.approval_info", "global.approved_successfully",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            return "/protected/home.htm?faces-redirect=true";
        } catch (Exception e) {
            LOGGER.error("Error when approved process ", e);
        }
        return null;
    }

    public String doRejected() {
        try {
//			loanService.rejected(selectedApprovalActivity.getId(), comment);
            MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.approval_info", "global.rejected_successfully",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            return "/protected/home.htm?faces-redirect=true";
        } catch (Exception e) {
            LOGGER.error("Error when rejected process ", e);
        }
        return null;
    }

    public void setWtGroupWorkingService(WtGroupWorkingService wtGroupWorkingService) {
        this.wtGroupWorkingService = wtGroupWorkingService;
    }

    public WtGroupWorking getSelectedWtGroupWorking() {
        return selectedWtGroupWorking;
    }

    public void setSelectedWtGroupWorking(WtGroupWorking selectedWtGroupWorking) {
        this.selectedWtGroupWorking = selectedWtGroupWorking;
    }

    public void doDetail() {
        List<String> values = new ArrayList<>();
        values.add(String.valueOf(selectedWtGroupWorking.getId()));
        Map<String, List<String>> dataToSend = new HashMap<>();
        dataToSend.put("id", values);
        Map<String, Object> options = new HashMap<>();
        options.put("modal", false);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 800);
        options.put("contentHeight", 500);
        RequestContext.getCurrentInstance().openDialog("schedule_shift_detail", options, dataToSend);

    }
}
