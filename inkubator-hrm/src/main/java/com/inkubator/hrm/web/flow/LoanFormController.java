package com.inkubator.hrm.web.flow;

import com.inkubator.common.CommonUtilConstant;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.webflow.execution.RequestContext;

import com.inkubator.common.util.AESUtil;
import com.inkubator.common.util.DateTimeUtil;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.Loan;
import com.inkubator.hrm.entity.LoanPaymentDetail;
import com.inkubator.hrm.entity.LoanSchema;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.LoanPaymentDetailService;
import com.inkubator.hrm.service.LoanSchemaService;
import com.inkubator.hrm.service.LoanService;
import com.inkubator.hrm.util.HrmUserInfoUtil;
import com.inkubator.hrm.web.model.LoanModel;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
 *
 * @author rizkykojek
 */
@Component(value = "loanFormController")
@Lazy
public class LoanFormController implements Serializable{

	org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(getClass());
	@Autowired
	private LoanService loanService;
	@Autowired
	private LoanPaymentDetailService loanPaymentDetailService;
	@Autowired
	private LoanSchemaService loanSchemaService;
	@Autowired
	private EmpDataService empDataService;
	
	
	public void initLoanProcessFlow(RequestContext context){
		try {			
			//binding value to model
			Long id = context.getFlowScope().getLong("id");	
			LoanModel model = new LoanModel();
			model.setTermin(1);
			if(id != null){			
				Loan loan = loanService.getEntityByPkWithDetail(id);
				model = bindEntityToModel(loan);
				
				List<LoanSchema> loanSchemas = loanSchemaService.getAllDataByEmployeeTypeId(loan.getEmpData().getEmployeeType().getId());
				context.getFlowScope().put("loanSchemas", loanSchemas);
			}
			context.getFlowScope().put("loanModel", model);
			
		} catch (Exception ex) {
			LOGGER.error("Error", ex);
		}
	}
	
	public LoanModel setLoanPaymentDetails(RequestContext context){
		LoanModel model = (LoanModel) context.getFlowScope().get("loanModel");
		
		try {
			/**
			 * bind list of loan payment detail to model.list 
			 * set hanya jika loan payment detail size == 0, artinya jika size > 0 maka value list nya sudah terdapat di flowSession tidak perlu getList lagi
			 * */			
			if(model.getLoanPaymentDetails().size() == 0){				
				List<LoanPaymentDetail> listLoanPaymentDetails = loanService.getAllDataLoanPaymentDetails(model.getInterestRate(), model.getTermin(), DateTimeUtil.getDateFrom(model.getLoanPaymentDate(), 1, CommonUtilConstant.DATE_FORMAT_MONTH), model.getNominalPrincipal(), model.getTypeOfInterest());
				model.setLoanPaymentDetails(listLoanPaymentDetails);
				
			}
		} catch (Exception ex) {
			LOGGER.error("Error", ex);
		}
		
		return model;
	}
	
	public String doLoanFormVerification(RequestContext context){
		String message = "success";
		LoanModel model = (LoanModel) context.getFlowScope().get("loanModel");
		if(model.getNominalPrincipal() > model.getMaxNominalPrincipal()){
			MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "loan.error_nominal_principal", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
			message = "error";
			
		} else if(model.getTermin() > model.getMaxTermin()){
			MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "loan.error_period", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
			message = "error";
			
		}		
		return message;
	}
	public String doSave(RequestContext context) {
		String message = "error";
		
		LoanModel model = (LoanModel) context.getFlowScope().get("loanModel");
		Loan loan = new Loan();
		loan.setId(model.getId());
		loan.setEmpData(new EmpData(model.getEmpData().getId()));
		loan.setLoanSchema(new LoanSchema(model.getLoanSchemaId()));
		loan.setNominalPrincipal(model.getNominalPrincipal());
		loan.setLoanPaymentDate(model.getLoanPaymentDate());
		loan.setLoanDate(model.getLoanDate());
		loan.setInterestRate(model.getInterestRate());
		loan.setTypeOfInterest(model.getTypeOfInterest());
		loan.setTermin(model.getTermin());
		loan.setStatusPencairan(HRMConstant.LOAN_UNDISBURSED);
		
		try {
			if(loan.getId() == null) {
				message = loanService.save(loan, false);
				
				//tujuannya agar waktu redirect dari flow ke detail, sudah didapatkan id-nya
				model.setId(loan.getId());
				context.getFlowScope().put("loanModel", model);
			} else {
				loanService.update(loan);
				message = "success_without_approval";
			}
		} catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
		
		return message;
	}
	
	public List<EmpData> doAutoCompleteEmployee(String param){
		List<EmpData> empDatas = new ArrayList<EmpData>();
		try {
			empDatas = empDataService.getAllDataByNameOrNik(StringUtils.stripToEmpty(param), HrmUserInfoUtil.getCompanyId());
		} catch (Exception e) {
			LOGGER.error("Error", e);
		}
		return empDatas;
	}
	
	public void onChangeEmployee(RequestContext context){		
		try {
			LoanModel model = (LoanModel) context.getFlowScope().get("loanModel");
			List<LoanSchema> loanSchemas = loanSchemaService.getAllDataByEmployeeTypeId(model.getEmpData().getEmployeeType().getId());
			context.getFlowScope().put("loanSchemas", loanSchemas);
			
			model.setLoanSchemaId(null);
			context.getFlowScope().put("loanModel", model);
		} catch (Exception e) {
			LOGGER.error("Error", e);
		}
	}
	
	public void onChangeLoanSchema(RequestContext context){
		try {
			LoanModel model = (LoanModel) context.getFlowScope().get("loanModel");	
			LoanSchema loanSchema = loanSchemaService.getEntiyByPK(model.getLoanSchemaId());			
			model.setMaxNominalPrincipal(this.getMaxNominalPrincipal(loanSchema, model.getEmpData()));
			model.setMaxTermin(loanSchema.getMaxPeriode());
			model.setInterestRate(loanSchema.getInterestRate());			
			model.setTypeOfInterest(loanSchema.getTypeOfInterest());
			model.getLoanPaymentDetails().clear(); //clear loan payment details
			context.getFlowScope().put("loanModel", model);
		} catch (Exception e) {
			LOGGER.error("Error", e);
		}
	}
	
	public void doResetLoanPaymentDetailForm(RequestContext context){
		LoanModel model = (LoanModel) context.getFlowScope().get("loanModel");
		model.getLoanPaymentDetails().clear();
		context.getFlowScope().put("loanModel", model);
	}
	
	private Double getMaxNominalPrincipal(LoanSchema loanSchema, EmpData empData){
		double maxNominalPrincipal = 0.0;
		
		if(loanSchema.getBasicValue() == HRMConstant.NOMINAL){
			maxNominalPrincipal = loanSchema.getMaxNominal().doubleValue();
		} else if(loanSchema.getBasicValue() == HRMConstant.SALARY){
			String salaryDecripted = empData.getBasicSalaryDecrypted();
			maxNominalPrincipal = Double.parseDouble(salaryDecripted);
		}
		
		return maxNominalPrincipal;
	}
	
	public void doResetLoanForm(RequestContext context){
		LoanModel model = (LoanModel) context.getFlowScope().get("loanModel");
		if(model.getId() == null){
			model = new LoanModel();
		} else {
			try {
				Loan loan = loanService.getEntityByPkWithDetail(model.getId());
				model = bindEntityToModel(loan);
			} catch (Exception e) {
				LOGGER.error("Error", e);
			}
		}
		
		context.getFlowScope().put("businessTravelModel", model);
	}
	
	private LoanModel bindEntityToModel(Loan loan) throws Exception{
		LoanModel model = new LoanModel();
		
		//bind loan payment details to model
		List<LoanPaymentDetail> listLoanPaymentDetail = loanPaymentDetailService.getAllDataByLoanId(loan.getId());
		model.setLoanPaymentDetails(listLoanPaymentDetail);
				
		//bind loan to model	
		model.setId(loan.getId());
		model.setEmpData(loan.getEmpData());
		model.setLoanSchemaId(loan.getLoanSchema().getId());
		model.setNominalPrincipal(loan.getNominalPrincipal());
		model.setTermin(loan.getTermin());
		model.setLoanPaymentDate(loan.getLoanPaymentDate());
		model.setLoanDate(loan.getLoanDate());		
		model.setMaxNominalPrincipal(this.getMaxNominalPrincipal(loan.getLoanSchema(), model.getEmpData()));
		model.setMaxTermin(loan.getLoanSchema().getMaxPeriode());
		model.setInterestRate(loan.getInterestRate());
		model.setTypeOfInterest(loan.getTypeOfInterest());
		return model;
	}
}
