package com.inkubator.hrm.web.reimbursement;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;
import org.hamcrest.Matchers;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import ch.lambdaj.Lambda;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.ApprovalActivity;
import com.inkubator.hrm.entity.Currency;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.RmbsApplication;
import com.inkubator.hrm.entity.RmbsSchema;
import com.inkubator.hrm.entity.RmbsSchemaListOfEmp;
import com.inkubator.hrm.entity.RmbsSchemaListOfType;
import com.inkubator.hrm.entity.RmbsSchemaListOfTypeId;
import com.inkubator.hrm.entity.RmbsType;
import com.inkubator.hrm.json.util.JsonUtil;
import com.inkubator.hrm.service.ApprovalActivityService;
import com.inkubator.hrm.service.CurrencyService;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.RmbsApplicationService;
import com.inkubator.hrm.service.RmbsSchemaListOfEmpService;
import com.inkubator.hrm.service.RmbsSchemaListOfTypeService;
import com.inkubator.hrm.util.HrmUserInfoUtil;
import com.inkubator.hrm.util.UploadFilesUtil;
import com.inkubator.hrm.web.model.RmbsApplicationModel;
import com.inkubator.securitycore.util.UserInfoUtil;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "rmbsApplicationFormController")
@ViewScoped
public class RmbsApplicationFormController extends BaseController {

	private Boolean isAdministator;
	private Boolean isRevised;
		
    private RmbsApplicationModel model;
    private UploadedFile reimbursementFile;
    private RmbsSchema rmbsSchema;
    private RmbsSchemaListOfType rmbsSchemaListOfType;
    private BigDecimal totalRequestThisMoth;    
    private ApprovalActivity currentActivity;
    private ApprovalActivity askingRevisedActivity;
    
    private List<RmbsType> listRmbsType;
    private List<Currency> listCurrency;
    private List<EmpData> listApprover;
    
    @ManagedProperty(value = "#{rmbsApplicationService}")
    private RmbsApplicationService rmbsApplicationService;
    @ManagedProperty(value = "#{rmbsSchemaListOfEmpService}")
    private RmbsSchemaListOfEmpService rmbsSchemaListOfEmpService;
    @ManagedProperty(value = "#{rmbsSchemaListOfTypeService}")
    private RmbsSchemaListOfTypeService rmbsSchemaListOfTypeService;
    @ManagedProperty(value = "#{currencyService}")
    private CurrencyService currencyService;
    @ManagedProperty(value = "#{uploadFilesUtil}")
	private UploadFilesUtil uploadFilesUtil;
    @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;
    @ManagedProperty(value = "#{approvalActivityService}")
    private ApprovalActivityService approvalActivityService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try {        	
        	//initial
        	listCurrency = currencyService.getAllData();	        
	        isAdministator = Lambda.exists(UserInfoUtil.getRoles(), Matchers.containsString(HRMConstant.ADMINISTRATOR_ROLE));
	        isRevised = Boolean.FALSE;
	        model = new RmbsApplicationModel();
	        model.setCode(HRMConstant.REIMBURSEMENT_KODE +"-"+ RandomNumberUtil.getRandomNumber(9));
	        
	        //di cek terlebih dahulu, jika datangnya dari proses approval, artinya user akan melakukan revisi data yg masih dalam bentuk json	        
	        String appActivityId = FacesUtil.getRequestParameter("activity");
        	if(StringUtils.isNotEmpty(appActivityId)) {
        		//parsing data from json to object 
        		isRevised = Boolean.TRUE;
        		currentActivity = approvalActivityService.getEntityByPkWithDetail(Long.parseLong(appActivityId.substring(1)));
        		this.getModelFromJson(currentActivity.getPendingData());
        		askingRevisedActivity = approvalActivityService.getEntityByActivityNumberAndSequence(currentActivity.getActivityNumber(), 
        				currentActivity.getSequence()-1);        	
        	
        	} else if(!isAdministator){ //jika bukan administrator, langsung di set empData berdasarkan yang login
        		model.setEmpData(HrmUserInfoUtil.getEmpData());
        		this.onChangeEmployee();
        	}
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    @PreDestroy
    public void cleanAndExit() {
    	model = null;
    	reimbursementFile = null;
    	listRmbsType = null;
    	listCurrency = null;
    	rmbsSchema = null;
    	rmbsSchemaListOfType = null;
    	rmbsApplicationService = null;
    	rmbsSchemaListOfEmpService = null;
    	rmbsSchemaListOfTypeService = null;
    	uploadFilesUtil = null;
    	currencyService = null;
    	empDataService = null;
    	totalRequestThisMoth = null;
    	isRevised = null;
    	askingRevisedActivity = null;
    	approvalActivityService = null;
    	currentActivity = null;
    	isAdministator = null;
    	listApprover = null;
	}
    
    public String doSave() {
	    RmbsApplication rmbsApplication = getEntityFromModel(model);
	    String path = "";	    	
	    try {
	    	String message = rmbsApplicationService.saveWithApproval(rmbsApplication, reimbursementFile);
	        if(StringUtils.equals(message, "success_need_approval")){
	        	path = "/protected/reimbursement/rmbs_application_undisbursed_view.htm?faces-redirect=true";
	        	MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully_and_requires_approval",FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
	        	
	        } else {
	        	path = "/protected/reimbursement/rmbs_application_undisbursed_view.htm?faces-redirect=true";
	        	MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully",FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());	        
	        }
	        return path;
	            
	    } catch (BussinessException ex) { 
	    	MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
	    } catch (Exception ex) {
	    	LOGGER.error("Error", ex);
	    }       
	    return null;
    }
    
    public String doRevised() {
    	RmbsApplication rmbsApplication = getEntityFromModel(model);
    	String path = "";
	    try {
	    	String message = rmbsApplicationService.saveWithRevised(rmbsApplication, reimbursementFile, currentActivity.getId());
	        if(StringUtils.equals(message, "success_need_approval")){
	        	path = "/protected/reimbursement/rmbs_application_undisbursed_view.htm?faces-redirect=true";
	        	MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully_and_requires_approval",FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
	        	
	        } else {
	        	path = "/protected/reimbursement/rmbs_application_undisbursed_view.htm?faces-redirect=true";
	        	MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully",FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());	        
	        }
	        return path;
	            
	    } catch (BussinessException ex) { 
	    	MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
	    } catch (Exception ex) {
	    	LOGGER.error("Error", ex);
	    }       
        return null;
    }

    private RmbsApplication getEntityFromModel(RmbsApplicationModel m) {
    	RmbsApplication rmbsApplication = new RmbsApplication();
        if (m.getId() != null) {
            rmbsApplication.setId(m.getId());
        }
        rmbsApplication.setCode(m.getCode());
        rmbsApplication.setEmpData(m.getEmpData());
        rmbsApplication.setRmbsType(new RmbsType(m.getRmbsTypeId()));
        rmbsApplication.setCurrency(new Currency(m.getCurrencyId()));
        rmbsApplication.setPurpose(m.getPurpose());
        rmbsApplication.setDescription(m.getDescription());
        rmbsApplication.setApplicationDate(m.getApplicationDate());
        rmbsApplication.setApplicationStatus(m.getApplicationStatus());
        rmbsApplication.setNominal(m.getNominal());
        return rmbsApplication;
    }
    
    private void getModelFromJson(String json){
    	try {
	    	Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
	    	RmbsApplication entity = gson.fromJson(json, RmbsApplication.class);
	    	model.setApplicationDate(entity.getApplicationDate());
	    	model.setCode(entity.getCode());
	    	model.setCurrencyId(entity.getCurrency().getId());
	    	model.setDescription(entity.getDescription());
	    	model.setEmpData(empDataService.getByEmpIdWithDetail(entity.getEmpData().getId()));
	    	model.setNominal(entity.getNominal());
	    	model.setPurpose(entity.getPurpose());	    	
	    	model.setRmbsTypeId(entity.getRmbsType().getId());
	    	
	    	RmbsSchemaListOfEmp rmbsSchemaListOfEmp = rmbsSchemaListOfEmpService.getEntityByEmpDataId(model.getEmpData().getId());
	    	rmbsSchema = rmbsSchemaListOfEmp.getRmbsSchema();
			listRmbsType = Lambda.extract(rmbsSchemaListOfTypeService.getAllDataByRmbsSchemaId(rmbsSchema.getId()), Lambda.on(RmbsSchemaListOfType.class).getRmbsType())  ;
			rmbsSchemaListOfType = rmbsSchemaListOfTypeService.getEntityByPk(new RmbsSchemaListOfTypeId(model.getRmbsTypeId(), rmbsSchema.getId()));
    		totalRequestThisMoth = rmbsApplicationService.getTotalNominalByThisMonth(model.getEmpData().getId(), model.getRmbsTypeId());
    		
	    	//get file attachment
	    	JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
            JsonElement elReimbursementFileName = jsonObject.get("reimbursementFileName");
            String reimbursementFileName = elReimbursementFileName.isJsonNull() ? StringUtils.EMPTY : elReimbursementFileName.getAsString();
            if(StringUtils.isNotEmpty(reimbursementFileName)){
            	reimbursementFile = rmbsApplicationService.convertFileToUploadedFile(json);
            	model.setReimbursementFileName(reimbursementFile.getFileName());
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
    		RmbsSchemaListOfEmp rmbsSchemaListOfEmp = rmbsSchemaListOfEmpService.getEntityByEmpDataId(model.getEmpData().getId());
    		if(rmbsSchemaListOfEmp != null){
    			rmbsSchema = rmbsSchemaListOfEmp.getRmbsSchema();
    			listRmbsType = Lambda.extract(rmbsSchemaListOfTypeService.getAllDataByRmbsSchemaId(rmbsSchema.getId()), Lambda.on(RmbsSchemaListOfType.class).getRmbsType())  ;
    			listApprover = rmbsApplicationService.getListApproverByEmpDataId(model.getEmpData().getId());
    		} else {
    			rmbsSchema = null;
    			listRmbsType = new ArrayList<RmbsType>();
    			listApprover = new ArrayList<EmpData>();
    		}
    	} catch (Exception e) {
	    	LOGGER.error("Error", e);
		}
    }
    
    public void onChangeRmbsType(){
    	try {
    		rmbsSchemaListOfType = rmbsSchemaListOfTypeService.getEntityByPk(new RmbsSchemaListOfTypeId(model.getRmbsTypeId(), rmbsSchema.getId()));
    		totalRequestThisMoth = rmbsApplicationService.getTotalNominalByThisMonth(model.getEmpData().getId(), model.getRmbsTypeId());
    	} catch (Exception e) {
	    	LOGGER.error("Error", e);
		}
    }
    
    public void handingFileUpload(FileUploadEvent fileUploadEvent) {
		Map<String, String> results = uploadFilesUtil.checkUploadFileSizeLimit(fileUploadEvent.getFile());
		if(StringUtils.equals(results.get("result"),"true")){
			reimbursementFile = fileUploadEvent.getFile();
			model.setReimbursementFileName(reimbursementFile.getFileName());                        
		} else {
			ResourceBundle messages = ResourceBundle.getBundle("Messages", new Locale(FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString()));
			String errorMsg = messages.getString("global.file_size_should_not_bigger_than") + " " + results.get("sizeMax");
			MessagesResourceUtil.setMessagesFromException(FacesMessage.SEVERITY_ERROR, "global.error", errorMsg, FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
		}
	}

	public RmbsApplicationModel getModel() {
		return model;
	}

	public void setModel(RmbsApplicationModel model) {
		this.model = model;
	}

	public UploadedFile getReimbursementFile() {
		return reimbursementFile;
	}

	public void setReimbursementFile(UploadedFile reimbursementFile) {
		this.reimbursementFile = reimbursementFile;
	}

	public List<RmbsType> getListRmbsType() {
		return listRmbsType;
	}

	public void setListRmbsType(List<RmbsType> listRmbsType) {
		this.listRmbsType = listRmbsType;
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

	public UploadFilesUtil getUploadFilesUtil() {
		return uploadFilesUtil;
	}

	public void setUploadFilesUtil(UploadFilesUtil uploadFilesUtil) {
		this.uploadFilesUtil = uploadFilesUtil;
	}

	public List<Currency> getListCurrency() {
		return listCurrency;
	}

	public void setListCurrency(List<Currency> listCurrency) {
		this.listCurrency = listCurrency;
	}

	public CurrencyService getCurrencyService() {
		return currencyService;
	}

	public void setCurrencyService(CurrencyService currencyService) {
		this.currencyService = currencyService;
	}

	public EmpDataService getEmpDataService() {
		return empDataService;
	}

	public void setEmpDataService(EmpDataService empDataService) {
		this.empDataService = empDataService;
	}

	public BigDecimal getTotalRequestThisMoth() {
		return totalRequestThisMoth;
	}

	public void setTotalRequestThisMoth(BigDecimal totalRequestThisMoth) {
		this.totalRequestThisMoth = totalRequestThisMoth;
	}

	public Boolean getIsRevised() {
		return isRevised;
	}

	public void setIsRevised(Boolean isRevised) {
		this.isRevised = isRevised;
	}

	public ApprovalActivity getAskingRevisedActivity() {
		return askingRevisedActivity;
	}

	public void setAskingRevisedActivity(ApprovalActivity askingRevisedActivity) {
		this.askingRevisedActivity = askingRevisedActivity;
	}

	public ApprovalActivityService getApprovalActivityService() {
		return approvalActivityService;
	}

	public void setApprovalActivityService(
			ApprovalActivityService approvalActivityService) {
		this.approvalActivityService = approvalActivityService;
	}

	public ApprovalActivity getCurrentActivity() {
		return currentActivity;
	}

	public void setCurrentActivity(ApprovalActivity currentActivity) {
		this.currentActivity = currentActivity;
	}

	public Boolean getIsAdministator() {
		return isAdministator;
	}

	public void setIsAdministator(Boolean isAdministator) {
		this.isAdministator = isAdministator;
	}

	public List<EmpData> getListApprover() {
		return listApprover;
	}

	public void setListApprover(List<EmpData> listApprover) {
		this.listApprover = listApprover;
	}	
        
}
