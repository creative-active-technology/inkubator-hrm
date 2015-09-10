package com.inkubator.hrm.web.reimbursement;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.hamcrest.Matchers;

import ch.lambdaj.Lambda;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.ApprovalActivity;
import com.inkubator.hrm.entity.RmbsDisbursement;
import com.inkubator.hrm.entity.TransactionCodefication;
import com.inkubator.hrm.entity.WtPeriode;
import com.inkubator.hrm.json.util.JsonUtil;
import com.inkubator.hrm.service.ApprovalActivityService;
import com.inkubator.hrm.service.RmbsApplicationService;
import com.inkubator.hrm.service.RmbsDisbursementService;
import com.inkubator.hrm.service.TransactionCodeficationService;
import com.inkubator.hrm.service.WtPeriodeService;
import com.inkubator.hrm.util.KodefikasiUtil;
import com.inkubator.hrm.web.lazymodel.RmbsApplicationUndisbursedLazyDataModel;
import com.inkubator.hrm.web.model.RmbsDisbursementModel;
import com.inkubator.securitycore.util.UserInfoUtil;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "rmbsDisbursementFormController")
@ViewScoped
public class RmbsDisbursementFormController extends BaseController {

	private Boolean isAdministator;
	private Boolean isRevised;
	private Boolean isRequester;
	
	private Date minimumBackDate;
	private WtPeriode period;
	private RmbsApplicationUndisbursedLazyDataModel lazyData;
    private RmbsDisbursementModel model;
    private ApprovalActivity currentActivity;
    private ApprovalActivity askingRevisedActivity;
    
    @ManagedProperty(value = "#{rmbsDisbursementService}")
    private RmbsDisbursementService rmbsDisbursementService;
    @ManagedProperty(value = "#{rmbsApplicationService}")
    private RmbsApplicationService rmbsApplicationService;
    @ManagedProperty(value = "#{wtPeriodeService}")
    private WtPeriodeService wtPeriodeService;
    @ManagedProperty(value = "#{approvalActivityService}")
    private ApprovalActivityService approvalActivityService;
    @ManagedProperty(value = "#{transactionCodeficationService}")
    private TransactionCodeficationService transactionCodeficationService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try {	        
	        //initial
	        model = new RmbsDisbursementModel();
	        model.setDisbursementDate(new Date());      
        	period = wtPeriodeService.getEntityByPayrollTypeActive();
        	isAdministator = Lambda.exists(UserInfoUtil.getRoles(), Matchers.containsString(HRMConstant.ADMINISTRATOR_ROLE));        
	        isRevised = Boolean.FALSE;
	        isRequester = Boolean.FALSE;
	        
	        //Minimum backdate paling lambat awal bulan dari bulan selanjutnya dari periode penggajian yang aktif
            WtPeriode activeWtPeriode = wtPeriodeService.getEntityByPayrollTypeActive();
            minimumBackDate = DateUtils.addDays(activeWtPeriode.getUntilPeriode(), 1);
            
        	//di cek terlebih dahulu, jika datangnya dari proses approval, artinya user akan melakukan revisi data yg masih dalam bentuk json	        
	        String appActivityId = FacesUtil.getRequestParameter("activity");
        	if(StringUtils.isNotEmpty(appActivityId)) {
        		//parsing data from json to object         		
        		currentActivity = approvalActivityService.getEntityByPkWithDetail(Long.parseLong(appActivityId.substring(1)));
        		this.getModelFromJson(currentActivity.getPendingData());
        		askingRevisedActivity = approvalActivityService.getEntityByActivityNumberAndSequence(currentActivity.getActivityNumber(), 
        				currentActivity.getSequence()-1);     
        		isRevised = Boolean.TRUE;
        		isRequester = StringUtils.equals(UserInfoUtil.getUserName(), currentActivity.getRequestBy());
        	} else {
        		//set kodefikasi nomor
            	TransactionCodefication transactionCodefication = transactionCodeficationService.getEntityByModulCode(HRMConstant.REIMBURSEMENT_DISBURSED_KODE);
            	if(!ObjectUtils.equals(transactionCodefication, null)){
            		model.setCode(KodefikasiUtil.getKodefikasiOnlyPattern(transactionCodefication.getCode()));
            	}
        		
        	}
        	
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    @PreDestroy
    public void cleanAndExit() {
    	rmbsApplicationService = null;
        model = null;
        lazyData = null;
        isAdministator = null;
        isRevised = null;
        isRequester = null;
        wtPeriodeService = null;
        period = null;
        rmbsDisbursementService = null;
        approvalActivityService = null;
        currentActivity = null;
        askingRevisedActivity = null;
        transactionCodeficationService = null;
        minimumBackDate = null;
	}

    public String doBack() {
        return "/protected/reimbursement/rmbs_application_undisbursed_view.htm?faces-redirect=true";
    }
    
    public String doDisbursement() {
    	RmbsDisbursement disbursement = getEntityFromModel(model);
        try {
        	String message = rmbsDisbursementService.disbursementWithApproval(model.getListRmbsApplicationId(), disbursement);
        	if(StringUtils.equals(message, "success_need_approval")){
	        	MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully_and_requires_approval",FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
	        } else {
	        	MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully",FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());	        
	        }
            return "/protected/reimbursement/rmbs_application_undisbursed_view.htm?faces-redirect=true";
            
        } catch (BussinessException ex) { 
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
        
        return null;
    }
    
    public String doRevised() {
    	RmbsDisbursement disbursement = getEntityFromModel(model);
	    try {
	    	String message = rmbsDisbursementService.disbursementWithRevised(model.getListRmbsApplicationId(), disbursement, currentActivity.getId());
	        if(StringUtils.equals(message, "success_need_approval")){
	        	MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully_and_requires_approval",FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
	        } else {
	        	MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully",FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());	        
	        }
	        return "/protected/reimbursement/rmbs_application_undisbursed_view.htm?faces-redirect=true";
	            
	    } catch (BussinessException ex) { 
	    	MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
	    } catch (Exception ex) {
	    	LOGGER.error("Error", ex);
	    }       
        return null;
    }

    private RmbsDisbursement getEntityFromModel(RmbsDisbursementModel m) {
    	RmbsDisbursement entity = new RmbsDisbursement();
    	entity.setCode(m.getCode());
        entity.setDisbursementDate(m.getDisbursementDate());
        entity.setPayrollPeriodDate(m.getPayrollPeriodDate());
        entity.setDescription(m.getDescription());
        return entity;
    }
    
    private void getModelFromJson(String json){
    	Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
    	RmbsDisbursement entity = gson.fromJson(json, RmbsDisbursement.class);
    	JsonObject jsonObject = (JsonObject) gson.fromJson(json, JsonObject.class);
    	List<Long> listRmbsApplicationId = new GsonBuilder().create().fromJson(jsonObject.get("listRmbsApplicationId").getAsString(), new TypeToken<List<Long>>() {}.getType());
		
		model.setCode(entity.getCode());
		model.setDisbursementDate(entity.getDisbursementDate());
		model.setPayrollPeriodDate(entity.getPayrollPeriodDate());
		model.setDescription(entity.getDescription());
		
		Map<Long, Boolean> selectedIds = new HashMap<Long, Boolean>();
		for(Long rmbsApplicationId : listRmbsApplicationId){
			selectedIds.put(rmbsApplicationId, true);
		}
		model.setSelectedIds(selectedIds);
    }

	public RmbsApplicationUndisbursedLazyDataModel getLazyData() {
		if(lazyData == null){
			lazyData = new RmbsApplicationUndisbursedLazyDataModel(rmbsApplicationService);
		}
		return lazyData;
	}

	public void setLazyData(RmbsApplicationUndisbursedLazyDataModel lazyData) {
		this.lazyData = lazyData;
	}

	public RmbsDisbursementModel getModel() {
		return model;
	}

	public void setModel(RmbsDisbursementModel model) {
		this.model = model;
	}

	public RmbsApplicationService getRmbsApplicationService() {
		return rmbsApplicationService;
	}

	public void setRmbsApplicationService(RmbsApplicationService rmbsApplicationService) {
		this.rmbsApplicationService = rmbsApplicationService;
	}

	public Boolean getIsAdministator() {
		return isAdministator;
	}

	public void setIsAdministator(Boolean isAdministator) {
		this.isAdministator = isAdministator;
	}

	public WtPeriode getPeriod() {
		return period;
	}

	public void setPeriod(WtPeriode period) {
		this.period = period;
	}

	public WtPeriodeService getWtPeriodeService() {
		return wtPeriodeService;
	}

	public void setWtPeriodeService(WtPeriodeService wtPeriodeService) {
		this.wtPeriodeService = wtPeriodeService;
	}

	public RmbsDisbursementService getRmbsDisbursementService() {
		return rmbsDisbursementService;
	}

	public void setRmbsDisbursementService(RmbsDisbursementService rmbsDisbursementService) {
		this.rmbsDisbursementService = rmbsDisbursementService;
	}

	public Boolean getIsRevised() {
		return isRevised;
	}

	public void setIsRevised(Boolean isRevised) {
		this.isRevised = isRevised;
	}

	public ApprovalActivity getCurrentActivity() {
		return currentActivity;
	}

	public void setCurrentActivity(ApprovalActivity currentActivity) {
		this.currentActivity = currentActivity;
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

	public Boolean getIsRequester() {
		return isRequester;
	}

	public void setIsRequester(Boolean isRequester) {
		this.isRequester = isRequester;
	}

	public TransactionCodeficationService getTransactionCodeficationService() {
		return transactionCodeficationService;
	}

	public void setTransactionCodeficationService(TransactionCodeficationService transactionCodeficationService) {
		this.transactionCodeficationService = transactionCodeficationService;
	}

	public Date getMinimumBackDate() {
		return minimumBackDate;
	}

	public void setMinimumBackDate(Date minimumBackDate) {
		this.minimumBackDate = minimumBackDate;
	}
	
}
