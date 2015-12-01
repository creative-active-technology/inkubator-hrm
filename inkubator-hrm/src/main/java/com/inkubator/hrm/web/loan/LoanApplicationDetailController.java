/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.loan;


import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.google.gson.Gson;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.ApprovalActivity;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.HrmUser;
import com.inkubator.hrm.entity.LoanNewApplication;
import com.inkubator.hrm.entity.LoanNewApplicationInstallment;
import com.inkubator.hrm.entity.LoanNewType;
import com.inkubator.hrm.entity.WtPeriode;
import com.inkubator.hrm.json.util.JsonUtil;
import com.inkubator.hrm.service.ApprovalActivityService;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.HrmUserService;
import com.inkubator.hrm.service.LoanNewApplicationService;
import com.inkubator.hrm.service.LoanNewTypeService;
import com.inkubator.hrm.service.WtPeriodeService;
import com.inkubator.hrm.web.model.LoanApplicationDetailModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
@ManagedBean(name = "loanApplicationDetailController")
@ViewScoped
public class LoanApplicationDetailController extends BaseController {
	
    private LoanApplicationDetailModel loanDetailModel;
    private ApprovalActivity lastApprovalActivity;
    private List<LoanNewApplicationInstallment> listLoanInstallment;
    
    @ManagedProperty(value = "#{loanNewApplicationService}")
    private LoanNewApplicationService loanNewApplicationService;
    @ManagedProperty(value = "#{hrmUserService}")
    private HrmUserService hrmUserService;
    @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;
    @ManagedProperty(value = "#{approvalActivityService}")
    private ApprovalActivityService approvalActivityService;
    @ManagedProperty(value = "#{loanNewTypeService}")
    private LoanNewTypeService loanNewTypeService;
    @ManagedProperty(value = "#{wtPeriodeService}")
    private WtPeriodeService wtPeriodeService;

    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            WtPeriode wtPeriode = wtPeriodeService.getEntityByPayrollTypeActive();
            String loanApprovalActivityNumber = FacesUtil.getRequestParameter("execution").substring(1);
            lastApprovalActivity = approvalActivityService.getEntityByActivityNumberLastSequence(loanApprovalActivityNumber);
            LoanNewApplication loanNewApplication = null; 
            Boolean isStillPending = lastApprovalActivity.getApprovalStatus() == HRMConstant.APPROVAL_STATUS_WAITING_APPROVAL || lastApprovalActivity.getApprovalStatus() == HRMConstant.APPROVAL_STATUS_WAITING_REVISED;
            if(isStillPending){
            	loanNewApplication = convertJsonPendingDataToLoanNewApplication(lastApprovalActivity.getPendingData());
            }else{
            	loanNewApplication = loanNewApplicationService.getEntityWithDetailByActivityNumber(loanApprovalActivityNumber);
            }
            LoanNewType loanNewType = loanNewTypeService.getEntiyByPK(loanNewApplication.getLoanNewType().getId());
            loanDetailModel = convertDataToDetailModel(loanNewApplication, lastApprovalActivity, loanNewType);
            listLoanInstallment = loanNewApplicationService.getAllDataLoanNewApplicationInstallment(loanNewType.getInterest().doubleValue(), loanNewApplication.getTermin(), loanNewApplication.getFirstLoanPaymentDate(), loanNewApplication.getNominalPrincipal(), loanNewType.getInterestMethod());
            
            if(isStillPending){
            	listLoanInstallment.stream().forEach(installment -> installment.setPaidStatus(HRMConstant.LOAN_INSTALLMENT_NOT_YET_PAID));
            }else{
            	 listLoanInstallment.stream().forEach(installment -> {
                 	if(installment.getInstallmentDate().before(wtPeriode.getUntilPeriode())){
                 		installment.setPaidStatus(HRMConstant.LOAN_INSTALLMENT_PAID);
                 	}else{
                 		installment.setPaidStatus(HRMConstant.LOAN_INSTALLMENT_NOT_YET_PAID);
                 	}
                 } );
            }
           
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    @PreDestroy
    public void cleanAndExit() {
    	loanDetailModel = null;
    	loanNewApplicationService = null;
    	hrmUserService = null;
    	loanNewTypeService = null;
    	empDataService = null;
    	wtPeriodeService = null;
    }   

    public String doBack() {
    	return "/protected/loan/loan_new_application_box_view.htm?faces-redirect=true";
    }
    
    private LoanNewApplication convertJsonPendingDataToLoanNewApplication(String jsonPendingData){
    	Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
        LoanNewApplication loanNewApplication = gson.fromJson(jsonPendingData, LoanNewApplication.class);
    	return loanNewApplication;
    }
	private LoanApplicationDetailModel convertDataToDetailModel(LoanNewApplication loanNewApplication, ApprovalActivity approvalActivity, LoanNewType loanNewType) throws Exception{
		
		HrmUser userApprover = hrmUserService.getByUserIdWithEmpIdAndBioId(approvalActivity.getApprovedBy());
		EmpData empData = empDataService.getByEmpIdWithDetail(loanNewApplication.getEmpData().getId());
		LoanApplicationDetailModel loanApplicationDetailModel = new LoanApplicationDetailModel();
		
		loanApplicationDetailModel.setId(loanNewApplication.getId());
		loanApplicationDetailModel.setApprovalActivityId(approvalActivity.getId());
		loanApplicationDetailModel.setApprovalActivityNumber(approvalActivity.getActivityNumber());
		loanApplicationDetailModel.setApprovalStatus(approvalActivity.getApprovalStatus());
		loanApplicationDetailModel.setApprovedDate(approvalActivity.getApprovalTime());
		loanApplicationDetailModel.setApproverName(userApprover.getRealName());
		loanApplicationDetailModel.setApproverNik(userApprover.getEmpData().getNik());
		loanApplicationDetailModel.setDisbursementStatus(loanNewApplication.getLoanStatus());
		loanApplicationDetailModel.setEmpDataName(empData.getBioData().getFullName());
		loanApplicationDetailModel.setNik(empData.getNik());
		loanApplicationDetailModel.setLoanNominal(loanNewApplication.getNominalPrincipal());
		loanApplicationDetailModel.setLoanNumber(loanNewApplication.getNomor());
		loanApplicationDetailModel.setLoanTypeName(loanNewType.getLoanTypeName());
		loanApplicationDetailModel.setSubmissionDate(loanNewApplication.getApplicationDate());
		loanApplicationDetailModel.setTermin(loanNewApplication.getTermin());
		
		return loanApplicationDetailModel;
	}

	public LoanApplicationDetailModel getLoanDetailModel() {
		return loanDetailModel;
	}

	public void setLoanDetailModel(LoanApplicationDetailModel loanDetailModel) {
		this.loanDetailModel = loanDetailModel;
	}

	public void setLoanNewApplicationService(LoanNewApplicationService loanNewApplicationService) {
		this.loanNewApplicationService = loanNewApplicationService;
	}

	public void setHrmUserService(HrmUserService hrmUserService) {
		this.hrmUserService = hrmUserService;
	}

	public void setLoanNewTypeService(LoanNewTypeService loanNewTypeService) {
		this.loanNewTypeService = loanNewTypeService;
	}

	public ApprovalActivity getLastApprovalActivity() {
		return lastApprovalActivity;
	}

	public void setLastApprovalActivity(ApprovalActivity lastApprovalActivity) {
		this.lastApprovalActivity = lastApprovalActivity;
	}

	public void setApprovalActivityService(ApprovalActivityService approvalActivityService) {
		this.approvalActivityService = approvalActivityService;
	}

	public void setEmpDataService(EmpDataService empDataService) {
		this.empDataService = empDataService;
	}

	public List<LoanNewApplicationInstallment> getListLoanInstallment() {
		return listLoanInstallment;
	}

	public void setListLoanInstallment(List<LoanNewApplicationInstallment> listLoanInstallment) {
		this.listLoanInstallment = listLoanInstallment;
	}

	public void setWtPeriodeService(WtPeriodeService wtPeriodeService) {
		this.wtPeriodeService = wtPeriodeService;
	}
	
	
}
