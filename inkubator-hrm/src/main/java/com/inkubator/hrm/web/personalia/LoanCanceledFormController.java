/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.personalia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.hamcrest.Matchers;

import ch.lambdaj.Lambda;

import com.google.gson.Gson;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.ApprovalActivity;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.HrmUser;
import com.inkubator.hrm.entity.Loan;
import com.inkubator.hrm.entity.LoanCanceled;
import com.inkubator.hrm.entity.LoanPaymentDetail;
import com.inkubator.hrm.entity.TransactionCodefication;
import com.inkubator.hrm.json.util.JsonUtil;
import com.inkubator.hrm.service.ApprovalActivityService;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.HrmUserService;
import com.inkubator.hrm.service.LoanCanceledService;
import com.inkubator.hrm.service.LoanService;
import com.inkubator.hrm.service.TransactionCodeficationService;
import com.inkubator.hrm.util.HrmUserInfoUtil;
import com.inkubator.hrm.util.KodefikasiUtil;
import com.inkubator.hrm.web.model.LoanCanceledModel;
import com.inkubator.securitycore.util.UserInfoUtil;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
 *
 * @author deni
 */
@ManagedBean(name = "loanCanceledFormController")
@ViewScoped
public class LoanCanceledFormController extends BaseController {

    @ManagedProperty(value = "#{loanService}")
    private LoanService loanService;
    @ManagedProperty(value = "#{loanCanceledService}")
    private LoanCanceledService loanCanceledService;
    @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;
    @ManagedProperty(value = "#{approvalActivityService}")
    private ApprovalActivityService approvalActivityService;
    @ManagedProperty(value = "#{transactionCodeficationService}")
    private TransactionCodeficationService transactionCodeficationService;
    @ManagedProperty(value = "#{hrmUserService}")
    private HrmUserService hrmUserService;
    private LoanCanceledModel loanCanceledModel;
    private Boolean isAdministator;
    private HashMap<Long, String> listActivity;
    private Loan loan;
    private List<LoanPaymentDetail> loanPaymentDetails;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try {
        	
        	loanPaymentDetails = new ArrayList<LoanPaymentDetail>();
            loanCanceledModel = new LoanCanceledModel();
            
            TransactionCodefication transactionCodefication = transactionCodeficationService.getEntityByModulCode(HRMConstant.LOAN_CANCELLATION_KODE);
            if(!ObjectUtils.equals(transactionCodefication, null)){
                loanCanceledModel.setCode((KodefikasiUtil.getKodefikasiOnlyPattern(transactionCodefication.getCode())));
            }
            
            isAdministator = Lambda.exists(UserInfoUtil.getRoles(), Matchers.containsString(HRMConstant.ADMINISTRATOR_ROLE));
            if(!isAdministator){
            	System.out.println("masuk if");
            	loanCanceledModel.setEmpData(HrmUserInfoUtil.getEmpData());
            	listActivity = loanService.getAllDataNotApprovedYet(UserInfoUtil.getUserName());
            }
            
            
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    public List<EmpData> doAutoCompleteEmployee(String param){
		List<EmpData> empDatas = new ArrayList<EmpData>();
		try {
			empDatas = empDataService.getAllDataByNameOrNik(StringUtils.stripToEmpty(param));
		} catch (Exception e) {
			LOGGER.error("Error", e);
		}
		return empDatas;
	}
    
    public void onChangeEmployee(){
    	try {
    		HrmUser user = hrmUserService.getByEmpDataId(loanCanceledModel.getEmpData().getId());
    		listActivity = loanService.getAllDataNotApprovedYet(user.getUserId());
    	} catch (Exception e) {
			LOGGER.error("Error", e);
		}
    	this.resetDetailInfo();
    }
    
    private void resetDetailInfo(){
    	loan = null;
    	loanPaymentDetails = new ArrayList<LoanPaymentDetail>();
    	
    }
    
    @PreDestroy
    public void cleanAndExit() {
        loanService = null;
        loanCanceledModel = null;
        approvalActivityService = null;
        isAdministator = null;
        listActivity = null;
        empDataService = null;
        loan = null;
        loanPaymentDetails = null;
    }

    public Boolean getIsPaginator() {
        return loanPaymentDetails.size() > 11;
    }
    
    public LoanCanceledModel getModelFromEntity(Loan loan) {
    	loanCanceledModel.setId(loan.getId());
    	loanCanceledModel.setNomor(loan.getNomor());
    	loanCanceledModel.setLoanDate(loan.getLoanDate());
    	loanCanceledModel.setLoanName(loan.getLoanSchema().getName());
    	loanCanceledModel.setNominalPrincipal(loan.getNominalPrincipal());
    	loanCanceledModel.setTermin(loan.getTermin());
    	loanCanceledModel.setApprovalTime(loan.getApprovalTime());
    	loanCanceledModel.setInterestRate(loan.getInterestRate());
    	loanCanceledModel.setTypeOfInterest(loan.getTypeOfInterest());
        return loanCanceledModel;
    }

    public String doCancel() {
	    LoanCanceled loanCancel = getEntityFromModel(loanCanceledModel);	    	
	    try {
	    	loanService.cancelled(loanCanceledModel.getApprovalActivityId(), loanCancel);
	    	MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "loanCanceled.loan_cancelled_successfull", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
	        return "/protected/personalia/loan_view.htm?faces-redirect=true";
	            
	    } catch (BussinessException ex) { 
	    	MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
	    } catch (Exception ex) {
	    	LOGGER.error("Error", ex);
	    }       
	    return null;
    }

    private LoanCanceled getEntityFromModel(LoanCanceledModel loanCanceledModel) {
    	LoanCanceled cancelation = new LoanCanceled();
		cancelation.setCancelledDate(loanCanceledModel.getCancelledDate());
		cancelation.setReason(loanCanceledModel.getReason());	
		cancelation.setInterestRate(loanCanceledModel.getInterestRate());
		return cancelation;
	}
    
    public void onChangeNumberApplication(){
    	try {
    		/** start binding data that needed (from json) to object */
	    	ApprovalActivity selectedApprovalActivity = approvalActivityService.getEntiyByPK(loanCanceledModel.getApprovalActivityId());
	        Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
	        loan = gson.fromJson(selectedApprovalActivity.getPendingData(), Loan.class);      
	        
	        loanCanceledModel = getModelFromEntity(loan);
	        loanPaymentDetails = loanService.getAllDataLoanPaymentDetails(loan.getInterestRate(), loan.getTermin(), loan.getLoanPaymentDate(), loan.getNominalPrincipal(), loan.getTypeOfInterest());
	        
    	} catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
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

	public Boolean getIsAdministator() {
		return isAdministator;
	}

	public void setIsAdministator(Boolean isAdministator) {
		this.isAdministator = isAdministator;
	}

	public HashMap<Long, String> getListActivity() {
		return listActivity;
	}

	public void setListActivity(HashMap<Long, String> listActivity) {
		this.listActivity = listActivity;
	}

	public EmpDataService getEmpDataService() {
		return empDataService;
	}

	public void setEmpDataService(EmpDataService empDataService) {
		this.empDataService = empDataService;
	}

	public Loan getLoan() {
		return loan;
	}

	public void setLoan(Loan loan) {
		this.loan = loan;
	}

	public List<LoanPaymentDetail> getLoanPaymentDetails() {
		return loanPaymentDetails;
	}

	public void setLoanPaymentDetails(List<LoanPaymentDetail> loanPaymentDetails) {
		this.loanPaymentDetails = loanPaymentDetails;
	}

	public TransactionCodeficationService getTransactionCodeficationService() {
		return transactionCodeficationService;
	}

	public void setTransactionCodeficationService(
			TransactionCodeficationService transactionCodeficationService) {
		this.transactionCodeficationService = transactionCodeficationService;
	}

	public void setLoanCanceledService(LoanCanceledService loanCanceledService) {
		this.loanCanceledService = loanCanceledService;
	}

	public void setHrmUserService(HrmUserService hrmUserService) {
		this.hrmUserService = hrmUserService;
	}
	
	
    
}
