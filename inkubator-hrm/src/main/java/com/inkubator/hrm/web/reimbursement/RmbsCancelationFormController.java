package com.inkubator.hrm.web.reimbursement;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.hamcrest.Matchers;

import ch.lambdaj.Lambda;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.ApprovalActivity;
import com.inkubator.hrm.entity.Currency;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.HrmUser;
import com.inkubator.hrm.entity.RmbsApplication;
import com.inkubator.hrm.entity.RmbsCancelation;
import com.inkubator.hrm.entity.RmbsSchema;
import com.inkubator.hrm.entity.RmbsSchemaListOfEmp;
import com.inkubator.hrm.entity.RmbsSchemaListOfType;
import com.inkubator.hrm.entity.RmbsSchemaListOfTypeId;
import com.inkubator.hrm.entity.RmbsType;
import com.inkubator.hrm.entity.TransactionCodefication;
import com.inkubator.hrm.json.util.JsonUtil;
import com.inkubator.hrm.service.ApprovalActivityService;
import com.inkubator.hrm.service.CurrencyService;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.HrmUserService;
import com.inkubator.hrm.service.RmbsApplicationService;
import com.inkubator.hrm.service.RmbsSchemaListOfEmpService;
import com.inkubator.hrm.service.RmbsSchemaListOfTypeService;
import com.inkubator.hrm.service.RmbsTypeService;
import com.inkubator.hrm.service.TransactionCodeficationService;
import com.inkubator.hrm.util.HrmUserInfoUtil;
import com.inkubator.hrm.util.KodefikasiUtil;
import com.inkubator.hrm.web.model.RmbsCancelationModel;
import com.inkubator.securitycore.util.UserInfoUtil;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "rmbsCancelationFormController")
@ViewScoped
public class RmbsCancelationFormController extends BaseController {
		
	private RmbsCancelationModel model;
	private RmbsApplication rmbsApplication;
    private RmbsSchema rmbsSchema;
    private RmbsSchemaListOfType rmbsSchemaListOfType;
    private BigDecimal totalRequestThisMoth;
    private Boolean isHaveAttachment;
    private Boolean isAdministator;
    
    private List<EmpData> listApprover;
    private HashMap<Long, String> listActivity;
    
    @ManagedProperty(value = "#{rmbsApplicationService}")
    private RmbsApplicationService rmbsApplicationService;
    @ManagedProperty(value = "#{rmbsSchemaListOfEmpService}")
    private RmbsSchemaListOfEmpService rmbsSchemaListOfEmpService;
    @ManagedProperty(value = "#{rmbsSchemaListOfTypeService}")
    private RmbsSchemaListOfTypeService rmbsSchemaListOfTypeService;
    @ManagedProperty(value = "#{approvalActivityService}")
    private ApprovalActivityService approvalActivityService;
    @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;
    @ManagedProperty(value = "#{rmbsTypeService}")
    private RmbsTypeService rmbsTypeService;
    @ManagedProperty(value = "#{currencyService}")
    private CurrencyService currencyService;
    @ManagedProperty(value = "#{hrmUserService}")
    private HrmUserService hrmUserService;
    @ManagedProperty(value = "#{transactionCodeficationService}")
    private TransactionCodeficationService transactionCodeficationService;

    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            model = new RmbsCancelationModel();
            
            //set kodefikasi nomor
        	TransactionCodefication transactionCodefication = transactionCodeficationService.getEntityByModulCode(HRMConstant.REIMBURSEMENT_CANCEL_KODE);
        	if(!ObjectUtils.equals(transactionCodefication, null)){
        		model.setCode(KodefikasiUtil.getKodefikasiOnlyPattern(transactionCodefication.getCode()));
        	}
            
            isAdministator = Lambda.exists(UserInfoUtil.getRoles(), Matchers.containsString(HRMConstant.ADMINISTRATOR_ROLE));
            if(!isAdministator){
            	model.setEmpData(HrmUserInfoUtil.getEmpData());
            	listActivity = rmbsApplicationService.getAllDataNotApprovedYet(UserInfoUtil.getUserName());
            }
        } catch (Exception ex) {
            LOGGER.error("Error", ex);

        }
    }

    @PreDestroy
    public void cleanAndExit() {
    	rmbsApplication = null;
    	rmbsSchema = null;
    	rmbsSchemaListOfType = null;
    	totalRequestThisMoth = null;
    	rmbsApplicationService = null;
    	rmbsSchemaListOfEmpService = null;
    	rmbsSchemaListOfTypeService = null;
    	isHaveAttachment = null;
    	currencyService = null;
    	rmbsTypeService = null;
    	empDataService = null;
    	approvalActivityService = null;
    	listApprover = null;
    	listActivity = null;
    	isAdministator = null;
    	hrmUserService = null;
    	transactionCodeficationService = null;
    }   

    public String doBack() {
    	return "/protected/reimbursement/rmbs_cancelation_view.htm?faces-redirect=true";
    }
    
    public String doCancel() {
	    RmbsCancelation cancelation = getEntityFromModel(model);	    	
	    try {
	    	rmbsApplicationService.cancelled(model.getApprovalActivityId(), cancelation);
	        return "/protected/reimbursement/rmbs_application_undisbursed_view.htm?faces-redirect=true";
	            
	    } catch (BussinessException ex) { 
	    	MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
	    } catch (Exception ex) {
	    	LOGGER.error("Error", ex);
	    }       
	    return null;
    }
    
    private RmbsCancelation getEntityFromModel(RmbsCancelationModel model) {
		RmbsCancelation cancelation = new RmbsCancelation();
		cancelation.setCancelledDate(model.getCancelledDate());
		cancelation.setCode(model.getCode());
		cancelation.setReason(model.getReason());	
		return cancelation;
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
    
    public void onChangeEmployee(){
    	try {
    		HrmUser user = hrmUserService.getByEmpDataId(model.getEmpData().getId());
    		listActivity = rmbsApplicationService.getAllDataNotApprovedYet(user.getUserId());
    	} catch (Exception e) {
			LOGGER.error("Error", e);
		}
    	this.resetDetailInfo();
    }
    
    private void resetDetailInfo(){
    	rmbsApplication = null;
    	rmbsSchema = null;
    	rmbsSchemaListOfType = null;
    	totalRequestThisMoth = null;
    	listApprover = null; 
    	isHaveAttachment = Boolean.FALSE;
    }

	public void onChangeNumberApplication(){
    	try {
    		/** start binding data that needed (from json) to object */
	    	ApprovalActivity selectedApprovalActivity = approvalActivityService.getEntiyByPK(model.getApprovalActivityId());
	        Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
	        rmbsApplication = gson.fromJson(selectedApprovalActivity.getPendingData(), RmbsApplication.class);      
	        
	        //relational object
	        EmpData empData = empDataService.getByEmpIdWithDetail(rmbsApplication.getEmpData().getId());
	        rmbsApplication.setEmpData(empData);
	        RmbsType rmbsType = rmbsTypeService.getEntiyByPK(rmbsApplication.getRmbsType().getId());
	        rmbsApplication.setRmbsType(rmbsType);
	        Currency currency = currencyService.getEntiyByPK(rmbsApplication.getCurrency().getId());
	        rmbsApplication.setCurrency(currency);
	        
	        //additional information
	        RmbsSchemaListOfEmp rmbsSchemaListOfEmp = rmbsSchemaListOfEmpService.getEntityByEmpDataId(empData.getId());
	        rmbsSchema =  rmbsSchemaListOfEmp.getRmbsSchema();
	        rmbsSchemaListOfType = rmbsSchemaListOfTypeService.getEntityByPk(new RmbsSchemaListOfTypeId(rmbsType.getId(), rmbsSchema.getId()));
	        totalRequestThisMoth = rmbsApplicationService.getTotalNominalForOneMonth(rmbsApplication.getApplicationDate(), empData.getId(), rmbsType.getId());
	        listApprover = rmbsApplicationService.getListApproverByEmpDataId(empData.getId());
	        
	        //attachment file
	        JsonObject jsonObject = gson.fromJson(selectedApprovalActivity.getPendingData(), JsonObject.class);            
	    	JsonElement elReimbursementFileName = jsonObject.get("reimbursementFileName");
	    	isHaveAttachment = !elReimbursementFileName.isJsonNull();
    	} catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

	public RmbsCancelationModel getModel() {
		return model;
	}

	public void setModel(RmbsCancelationModel model) {
		this.model = model;
	}

	public RmbsApplication getRmbsApplication() {
		return rmbsApplication;
	}

	public void setRmbsApplication(RmbsApplication rmbsApplication) {
		this.rmbsApplication = rmbsApplication;
	}

	public RmbsSchema getRmbsSchema() {
		return rmbsSchema;
	}

	public void setRmbsSchema(RmbsSchema rmbsSchema) {
		this.rmbsSchema = rmbsSchema;
	}

	public RmbsSchemaListOfType getRmbsSchemaListOfType() {
		return rmbsSchemaListOfType;
	}

	public void setRmbsSchemaListOfType(RmbsSchemaListOfType rmbsSchemaListOfType) {
		this.rmbsSchemaListOfType = rmbsSchemaListOfType;
	}

	public BigDecimal getTotalRequestThisMoth() {
		return totalRequestThisMoth;
	}

	public void setTotalRequestThisMoth(BigDecimal totalRequestThisMoth) {
		this.totalRequestThisMoth = totalRequestThisMoth;
	}

	public Boolean getIsHaveAttachment() {
		return isHaveAttachment;
	}

	public void setIsHaveAttachment(Boolean isHaveAttachment) {
		this.isHaveAttachment = isHaveAttachment;
	}

	public RmbsApplicationService getRmbsApplicationService() {
		return rmbsApplicationService;
	}

	public void setRmbsApplicationService(
			RmbsApplicationService rmbsApplicationService) {
		this.rmbsApplicationService = rmbsApplicationService;
	}

	public RmbsSchemaListOfEmpService getRmbsSchemaListOfEmpService() {
		return rmbsSchemaListOfEmpService;
	}

	public void setRmbsSchemaListOfEmpService(
			RmbsSchemaListOfEmpService rmbsSchemaListOfEmpService) {
		this.rmbsSchemaListOfEmpService = rmbsSchemaListOfEmpService;
	}

	public RmbsSchemaListOfTypeService getRmbsSchemaListOfTypeService() {
		return rmbsSchemaListOfTypeService;
	}

	public void setRmbsSchemaListOfTypeService(
			RmbsSchemaListOfTypeService rmbsSchemaListOfTypeService) {
		this.rmbsSchemaListOfTypeService = rmbsSchemaListOfTypeService;
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

	public RmbsTypeService getRmbsTypeService() {
		return rmbsTypeService;
	}

	public void setRmbsTypeService(RmbsTypeService rmbsTypeService) {
		this.rmbsTypeService = rmbsTypeService;
	}

	public CurrencyService getCurrencyService() {
		return currencyService;
	}

	public void setCurrencyService(CurrencyService currencyService) {
		this.currencyService = currencyService;
	}

	public List<EmpData> getListApprover() {
		return listApprover;
	}

	public void setListApprover(List<EmpData> listApprover) {
		this.listApprover = listApprover;
	}

	public HashMap<Long, String> getListActivity() {
		return listActivity;
	}

	public void setListActivity(HashMap<Long, String> listActivity) {
		this.listActivity = listActivity;
	}

	public Boolean getIsAdministator() {
		return isAdministator;
	}

	public void setIsAdministator(Boolean isAdministator) {
		this.isAdministator = isAdministator;
	}

	public HrmUserService getHrmUserService() {
		return hrmUserService;
	}

	public void setHrmUserService(HrmUserService hrmUserService) {
		this.hrmUserService = hrmUserService;
	}

	public TransactionCodeficationService getTransactionCodeficationService() {
		return transactionCodeficationService;
	}

	public void setTransactionCodeficationService(TransactionCodeficationService transactionCodeficationService) {
		this.transactionCodeficationService = transactionCodeficationService;
	}	
	
}
