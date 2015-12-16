/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.personalia;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;

import org.apache.commons.lang.StringUtils;

import com.inkubator.hrm.entity.ApprovalActivity;
import com.inkubator.hrm.entity.Loan;
import com.inkubator.hrm.entity.LoanPaymentDetail;
import com.inkubator.hrm.service.ApprovalActivityService;
import com.inkubator.hrm.service.LoanPaymentDetailService;
import com.inkubator.hrm.service.LoanService;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "loanDetailController")
@ViewScoped
public class LoanDetailController extends BaseController {

    private ApprovalActivity selectedApprovalActivity;
    private Loan selectedLoan;
    private List<LoanPaymentDetail> loanPaymentDetails;
    @ManagedProperty(value = "#{loanService}")
    private LoanService loanService;
    @ManagedProperty(value = "#{loanPaymentDetailService}")
    private LoanPaymentDetailService loanPaymentDetailService;
    @ManagedProperty(value = "#{approvalActivityService}")
    private ApprovalActivityService approvalActivityService;

    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            String execution = FacesUtil.getRequestParameter("execution");
            String param = execution.substring(0, 1);
            if (StringUtils.equals(param, "e")) {
                /* parameter (id) ini datangnya dari loan Flow atau View */
                selectedLoan = loanService.getEntityByPkWithDetail(Long.parseLong(execution.substring(1)));

            } else {
                /* parameter (activityNumber) ini datangnya dari home approval request history View */
                selectedLoan = loanService.getEntityByApprovalActivityNumberWithDetail(execution.substring(1));
              
            }

            selectedApprovalActivity = approvalActivityService.getEntityByActivityNumberLastSequence(selectedLoan.getApprovalActivityNumber());
            loanPaymentDetails = loanPaymentDetailService.getAllDataByLoanId(selectedLoan.getId());
        
        } catch (Exception ex) {
            LOGGER.error("Error", ex);

        }
    }

    @PreDestroy
    public void cleanAndExit() {
        selectedLoan = null;
        selectedApprovalActivity = null;
        approvalActivityService = null;
        loanService = null;
        loanPaymentDetailService = null;
        loanPaymentDetails = null;
    }

    public Loan getSelectedLoan() {
        return selectedLoan;
    }

    public void setSelectedLoan(Loan selectedLoan) {
        this.selectedLoan = selectedLoan;
    }

    public List<LoanPaymentDetail> getLoanPaymentDetails() {
        return loanPaymentDetails;
    }

    public void setLoanPaymentDetails(List<LoanPaymentDetail> loanPaymentDetails) {
        this.loanPaymentDetails = loanPaymentDetails;
    }

    public void setLoanService(LoanService loanService) {
        this.loanService = loanService;
    }

    public void setLoanPaymentDetailService(
            LoanPaymentDetailService loanPaymentDetailService) {
        this.loanPaymentDetailService = loanPaymentDetailService;
    }

    public ApprovalActivity getSelectedApprovalActivity() {
        return selectedApprovalActivity;
    }

    public void setSelectedApprovalActivity(
            ApprovalActivity selectedApprovalActivity) {
        this.selectedApprovalActivity = selectedApprovalActivity;
    }

    public void setApprovalActivityService(
            ApprovalActivityService approvalActivityService) {
        this.approvalActivityService = approvalActivityService;
    }

    public Boolean getIsHaveApprovalActivity() {
        return selectedApprovalActivity != null;
    }

    public Boolean getIsPaginator() {
        return loanPaymentDetails.size() > 11;
    }

    public String doBack() {
        return "/protected/personalia/loan_view.htm?faces-redirect=true";
    }

    public void doUpdate() {
        try {
            ExternalContext red = FacesUtil.getExternalContext();
            red.redirect(red.getRequestContextPath() + "/flow-protected/loan?id=" + selectedLoan.getId());
        } catch (IOException ex) {
            LOGGER.error("Erorr", ex);
        }
    }

}
