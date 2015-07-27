/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.personalia;

import ch.lambdaj.Lambda;
import com.google.gson.Gson;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.ApprovalActivity;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.Loan;
import com.inkubator.hrm.entity.LoanCanceled;
import com.inkubator.hrm.entity.LoanPaymentDetail;
import com.inkubator.hrm.json.util.JsonUtil;
import com.inkubator.hrm.service.ApprovalActivityService;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.LoanService;
import com.inkubator.hrm.util.HrmUserInfoUtil;
import com.inkubator.hrm.web.model.LoanCanceledModel;
import com.inkubator.securitycore.util.UserInfoUtil;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
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
import org.hamcrest.Matchers;
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
    @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;
    @ManagedProperty(value = "#{approvalActivityService}")
    private ApprovalActivityService approvalActivityService;
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
            isAdministator = Lambda.exists(UserInfoUtil.getRoles(), Matchers.containsString(HRMConstant.ADMINISTRATOR_ROLE));
            if(!isAdministator){
            	System.out.println("masuk if");
            	loanCanceledModel.setEmpData(HrmUserInfoUtil.getEmpData());
            	listActivity = loanService.getAllDataNotApprovedYet(UserInfoUtil.getUserName());
            }
            
            //            String loanId = FacesUtil.getRequestParameter("execution");
            /*if (StringUtils.isNotEmpty(loanId)) {
                Loan loan = loanService.getEntityByPkWithDetail(Long.valueOf(loanId.substring(1)));
                ApprovalActivity approvalActivity = approvalActivityService.getApprovalTimeByApprovalActivityNumber(loan.getApprovalActivityNumber());
                if (loan != null) {
                    loanCanceledModel = getModelFromEntity(loan);
                    loanCanceledModel.setApprovalTime(approvalActivity.getApprovalTime());
                    loanCanceledModel.setApprovalActivityNumber(approvalActivity.getActivityNumber());
                    loanCanceledModel.setEmpData(loan.getEmpData().getId());
                    loanCanceledModel.setLoanSchema(loan.getLoanSchema().getId());
                }
            }*/
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
    
    public String doCancel() {
	    LoanCanceled loanCancel = getEntityFromModel(loanCanceledModel);	    	
	    try {
	    	loanService.cancelled(loanCanceledModel.getApprovalActivityId(), loanCancel);
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
		return cancelation;
	}
    
    public void onChangeNumberApplication(){
    	try {
    		/** start binding data that needed (from json) to object */
	    	ApprovalActivity selectedApprovalActivity = approvalActivityService.getEntiyByPK(loanCanceledModel.getApprovalActivityId());
	        Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
	        loan = gson.fromJson(selectedApprovalActivity.getPendingData(), Loan.class);      
	        
	        
	        loanPaymentDetails = loanService.getAllDataLoanPaymentDetails(loan.getInterestRate(), loan.getTermin(), loan.getLoanPaymentDate(), loan.getNominalPrincipal(), loan.getTypeOfInterest());
	        //relational object
	        /*EmpData empData = empDataService.getByEmpIdWithDetail(rmbsApplication.getEmpData().getId());
	        rmbsApplication.setEmpData(empData);
	        RmbsType rmbsType = rmbsTypeService.getEntiyByPK(rmbsApplication.getRmbsType().getId());
	        rmbsApplication.setRmbsType(rmbsType);
	        Currency currency = currencyService.getEntiyByPK(rmbsApplication.getCurrency().getId());
	        rmbsApplication.setCurrency(currency);
	        
	        //additional information
	        RmbsSchemaListOfEmp rmbsSchemaListOfEmp = rmbsSchemaListOfEmpService.getEntityByEmpDataId(empData.getId());
	        rmbsSchema =  rmbsSchemaListOfEmp.getRmbsSchema();
	        rmbsSchemaListOfType = rmbsSchemaListOfTypeService.getEntityByPk(new RmbsSchemaListOfTypeId(rmbsType.getId(), rmbsSchema.getId()));
	        totalRequestThisMoth = rmbsApplicationService.getTotalNominalByThisMonth(empData.getId(), rmbsType.getId());
	        listApprover = rmbsApplicationService.getListApproverByEmpDataId(empData.getId());
	        
	        //attachment file
	        JsonObject jsonObject = gson.fromJson(selectedApprovalActivity.getPendingData(), JsonObject.class);            
	    	JsonElement elReimbursementFileName = jsonObject.get("reimbursementFileName");
	    	isHaveAttachment = !elReimbursementFileName.isJsonNull();*/
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

    
}
