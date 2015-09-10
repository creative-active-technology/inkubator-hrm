/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.payroll;


import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.ApprovalActivity;
import com.inkubator.hrm.entity.Loan;
import com.inkubator.hrm.service.ApprovalActivityService;
import com.inkubator.hrm.service.LoanPaymentDetailService;
import com.inkubator.hrm.service.LoanService;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
@ManagedBean(name = "loanDisbursementFormController")
@ViewScoped
public class LoanDisbursementFormController extends BaseController{
    @ManagedProperty(value = "#{loanService}")
    private LoanService loanService;
    @ManagedProperty(value = "#{approvalActivityService}")
    private ApprovalActivityService approvalActivityService;    
    @ManagedProperty(value = "#{loanPaymentDetailService}")
    private LoanPaymentDetailService loanPaymentDetailService;
    private Loan selectedLoan;
    private Double installment;  
    private Boolean agree;
    private List<Loan> loanList = new ArrayList<>();    
    private Date approvalTime;
    private Date dateDisbursed;
    private String approvedBy;    
    
    @PostConstruct
    @Override
    public void initialization() {
     
        super.initialization();        
        try { 
            String param = FacesUtil.getRequestParameter("param");           
            selectedLoan = loanService.getEntityByPkWithDetail(Long.parseLong(param));
            agree = Boolean.TRUE;
            dateDisbursed = selectedLoan.getLoanPaymentDate();
            
            //Ambil nilai angsuran perbulan dari Loan yang dipilih
            installment = loanPaymentDetailService.getInstallmentByLoanId(selectedLoan.getId());
            
            //Cek approvalActivityNumber  jika ada isi approvalTime dan approvedBy dari ApprovalActivity yang berelasi dengan Loan-nya
            if(null != selectedLoan.getApprovalActivityNumber()){
                ApprovalActivity selectedApprovalActivity = approvalActivityService.getEntityByActivityNumberLastSequence(selectedLoan.getApprovalActivityNumber());
                approvalTime = selectedApprovalActivity.getApprovalTime();
                approvedBy = selectedApprovalActivity.getApprovedBy();
            }else{
                approvalTime = null;
                approvedBy = "-";
            }
            
                        
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }
    
    
    public void doSave() {
       
        try {           
            if(agree){
                loanService.disbursement(selectedLoan.getId(), dateDisbursed);       
            }
                    
            RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);           
            cleanAndExit();
        } catch (BussinessException ex) { 
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }
    
    
    @PreDestroy
    private void cleanAndExit() {        
        selectedLoan  = null;
        approvalTime = null;
        approvedBy = null;
        installment = null;
        loanService = null;
        loanList = null;
        dateDisbursed = null;
    }

    public LoanService getLoanService() {
        return loanService;
    }

    public void setLoanService(LoanService loanService) {
        this.loanService = loanService;
    }
   

    public List<Loan> getLoanList() {
        return loanList;
    }

    public void setLoanList(List<Loan> loanList) {
        this.loanList = loanList;
    }

    public ApprovalActivityService getApprovalActivityService() {
        return approvalActivityService;
    }

    public void setApprovalActivityService(ApprovalActivityService approvalActivityService) {
        this.approvalActivityService = approvalActivityService;
    }

    public Loan getSelectedLoan() {
        return selectedLoan;
    }

    public void setSelectedLoan(Loan selectedLoan) {
        this.selectedLoan = selectedLoan;
    }

    public LoanPaymentDetailService getLoanPaymentDetailService() {
        return loanPaymentDetailService;
    }

    public void setLoanPaymentDetailService(LoanPaymentDetailService loanPaymentDetailService) {
        this.loanPaymentDetailService = loanPaymentDetailService;
    }

    public Double getInstallment() {
        return installment;
    }

    public void setInstallment(Double installment) {
        this.installment = installment;
    }

    public Date getApprovalTime() {
        return approvalTime;
    }

    public void setApprovalTime(Date approvalTime) {
        this.approvalTime = approvalTime;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public Boolean getAgree() {
        return agree;
    }

    public void setAgree(Boolean agree) {
        this.agree = agree;
    }  

    public Date getDateDisbursed() {
        return dateDisbursed;
    }

    public void setDateDisbursed(Date dateDisbursed) {
        this.dateDisbursed = dateDisbursed;
    }
    
    
    
}


