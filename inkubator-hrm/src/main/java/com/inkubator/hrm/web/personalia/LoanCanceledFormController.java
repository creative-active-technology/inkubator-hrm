/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.personalia;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.ApprovalActivity;
import com.inkubator.hrm.entity.Loan;
import com.inkubator.hrm.service.ApprovalActivityService;
import com.inkubator.hrm.service.LoanService;
import com.inkubator.hrm.web.model.LoanCanceledModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.apache.commons.lang.StringUtils;
import org.primefaces.context.RequestContext;

/**
 *
 * @author deni
 */
@ManagedBean(name = "loanCanceledFormController")
@ViewScoped
public class LoanCanceledFormController extends BaseController {

    @ManagedProperty(value = "#{loanService}")
    private LoanService loanService;
    @ManagedProperty(value = "#{approvalActivityService}")
    private ApprovalActivityService approvalActivityService;
    private LoanCanceledModel loanCanceledModel;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try {
            loanCanceledModel = new LoanCanceledModel();
            String loanId = FacesUtil.getRequestParameter("execution");
            if (StringUtils.isNotEmpty(loanId)) {
                Loan loan = loanService.getEntityByPkWithDetail(Long.valueOf(loanId.substring(1)));
                ApprovalActivity approvalActivity = approvalActivityService.getApprovalTimeByApprovalActivityNumber(loan.getApprovalActivityNumber());
                if (loan != null) {
                    loanCanceledModel = getModelFromEntity(loan);
                    loanCanceledModel.setApprovalTime(approvalActivity.getApprovalTime());
                    loanCanceledModel.setApprovalActivityNumber(approvalActivity.getActivityNumber());
                    loanCanceledModel.setEmpData(loan.getEmpData().getId());
                    loanCanceledModel.setLoanSchema(loan.getLoanSchema().getId());
                }
            }
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    @PreDestroy
    public void cleanAndExit() {
        loanService = null;
        loanCanceledModel = null;
        approvalActivityService = null;
    }

    public LoanCanceledModel getModelFromEntity(Loan loan) {
        LoanCanceledModel model = new LoanCanceledModel();
        model.setId(loan.getId());
        model.setNomor(loan.getNomor());
        model.setLoanDate(loan.getLoanDate());
        model.setLoanName(loan.getLoanSchema().getName());
        model.setNominalPrincipal(loan.getNominalPrincipal());
        model.setTermin(loan.getTermin());
        model.setApprovalTime(loan.getApprovalTime());
        model.setInterestRate(loan.getInterestRate());
        model.setTypeOfInterest(loan.getTypeOfInterest());
        return model;
    }

    public String doSave() {
        
        try {
            loanService.UpdateLoanAndsaveLoanCanceled(loanCanceledModel);
            MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.update_successfully",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

            cleanAndExit();
            return "/protected/personalia/loan_canceled_view.htm?faces-redirect=true";
        } catch (BussinessException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
        return null;
    }

    public String doBack(){
        return "/protected/personalia/loan_canceled_proc_view.htm?faces-redirect=true";
    }
    
    public LoanService getLoanService() {
        return loanService;
    }

    public void setLoanService(LoanService loanService) {
        this.loanService = loanService;
    }

    public LoanCanceledModel getLoanCanceledModel() {
        return loanCanceledModel;
    }

    public void setLoanCanceledModel(LoanCanceledModel loanCanceledModel) {
        this.loanCanceledModel = loanCanceledModel;
    }

    public ApprovalActivityService getApprovalActivityService() {
        return approvalActivityService;
    }

    public void setApprovalActivityService(ApprovalActivityService approvalActivityService) {
        this.approvalActivityService = approvalActivityService;
    }

}
