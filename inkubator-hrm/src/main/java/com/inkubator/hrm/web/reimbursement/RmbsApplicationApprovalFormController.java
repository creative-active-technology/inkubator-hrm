/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.reimbursement;

import java.math.BigDecimal;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
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
import com.inkubator.hrm.service.RmbsTypeService;
import com.inkubator.securitycore.util.UserInfoUtil;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "rmbsApplicationApprovalFormController")
@ViewScoped
public class RmbsApplicationApprovalFormController extends BaseController {

	private RmbsSchema rmbsSchema;
    private RmbsApplication rmbsApplication;
    private RmbsSchemaListOfType rmbsSchemaListOfType; 
    private String comment;
    private Boolean isWaitingApproval;
    private Boolean isWaitingRevised;
    private Boolean isApprover;
    private Boolean isRequester;
    private Boolean isHaveAttachment;
    private ApprovalActivity selectedApprovalActivity;
    private BigDecimal totalRequestThisMoth;
    
    @ManagedProperty(value = "#{approvalActivityService}")
    private ApprovalActivityService approvalActivityService;
    @ManagedProperty(value = "#{rmbsApplicationService}")
    private RmbsApplicationService rmbsApplicationService;
    @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;
    @ManagedProperty(value = "#{rmbsTypeService}")
    private RmbsTypeService rmbsTypeService;
    @ManagedProperty(value = "#{rmbsSchemaListOfEmpService}")
    private RmbsSchemaListOfEmpService rmbsSchemaListOfEmpService;
    @ManagedProperty(value = "#{rmbsSchemaListOfTypeService}")
    private RmbsSchemaListOfTypeService rmbsSchemaListOfTypeService;
    @ManagedProperty(value = "#{currencyService}")
    private CurrencyService currencyService;
    

    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            
            /** initial data for approval activity tracking */
            String id = FacesUtil.getRequestParameter("execution");
            selectedApprovalActivity = approvalActivityService.getEntiyByPK(Long.parseLong(id.substring(1)));
            isWaitingApproval = selectedApprovalActivity.getApprovalStatus() == HRMConstant.APPROVAL_STATUS_WAITING_APPROVAL;
            isWaitingRevised = selectedApprovalActivity.getApprovalStatus() == HRMConstant.APPROVAL_STATUS_WAITING_REVISED;
            isApprover = StringUtils.equals(UserInfoUtil.getUserName(), selectedApprovalActivity.getApprovedBy());
            isRequester = StringUtils.equals(UserInfoUtil.getUserName(), selectedApprovalActivity.getRequestBy());            
            
            /** bind data needed from json to object */
            Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
            rmbsApplication = gson.fromJson(selectedApprovalActivity.getPendingData(), RmbsApplication.class);      
            EmpData empData = empDataService.getByEmpIdWithDetail(rmbsApplication.getEmpData().getId());
            rmbsApplication.setEmpData(empData);
            RmbsType rmbsType = rmbsTypeService.getEntiyByPK(rmbsApplication.getRmbsType().getId());
            rmbsApplication.setRmbsType(rmbsType);
            Currency currency = currencyService.getEntiyByPK(rmbsApplication.getCurrency().getId());
            rmbsApplication.setCurrency(currency);
            RmbsSchemaListOfEmp rmbsSchemaListOfEmp = rmbsSchemaListOfEmpService.getEntityByEmpDataId(empData.getId());
            rmbsSchema =  rmbsSchemaListOfEmp.getRmbsSchema();
            rmbsSchemaListOfType = rmbsSchemaListOfTypeService.getEntityByPk(new RmbsSchemaListOfTypeId(rmbsType.getId(), rmbsSchema.getId()));
            totalRequestThisMoth = rmbsApplicationService.getTotalNominalByThisMonth(empData.getId(), rmbsType.getId());
            JsonObject jsonObject = gson.fromJson(selectedApprovalActivity.getPendingData(), JsonObject.class);            
	    	JsonElement elReimbursementFileName = jsonObject.get("reimbursementFileName");
	    	isHaveAttachment = !elReimbursementFileName.isJsonNull();
            
            
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    @PreDestroy
    public void cleanAndExit() {
    	rmbsApplication = null;
        isWaitingRevised = null;
        selectedApprovalActivity = null;
        approvalActivityService = null;
        comment = null;
        isWaitingApproval = null;
        isApprover = null;
        isRequester = null;
        rmbsApplicationService = null;
        empDataService = null;
        rmbsSchemaListOfEmpService = null;
        rmbsSchema = null;
        rmbsSchemaListOfTypeService = null;
        rmbsSchemaListOfType = null;
        totalRequestThisMoth = null;
        currencyService = null;
        isHaveAttachment = null;
    }

    public String doBack() {
        return "/protected/reimbursement/rmbs_application_undisbursed_view.htm?faces-redirect=true";
    }

    public String doRevised() {
    	return "/protected/reimbursement/rmbs_application_form.htm?faces-redirect=true&activity=e" + selectedApprovalActivity.getId();
    }
    
    public String doAskingRevised() {
        try {
        	rmbsApplicationService.askingRevised(selectedApprovalActivity.getId(), comment);
            MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.approval_info", "global.process_successfully",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            return "/protected/reimbursement/rmbs_application_undisbursed_view.htm?faces-redirect=true";
        } catch (BussinessException ex) {            
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception e) {
            LOGGER.error("Error ", e);
        }
        return null;
    }
    
    public String doApproved() {
        try {
        	rmbsApplicationService.approved(selectedApprovalActivity.getId(), null, comment);
            MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.approval_info", "global.approved_successfully",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            return "/protected/reimbursement/rmbs_application_undisbursed_view.htm?faces-redirect=true";
        } catch (BussinessException ex) {            
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception e) {
            LOGGER.error("Error ", e);
        }
        return null;
    }

    public String doRejected() {
        try {
        	rmbsApplicationService.rejected(selectedApprovalActivity.getId(), comment);
            MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.approval_info", "global.rejected_successfully",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            return "/protected/reimbursement/rmbs_application_undisbursed_view.htm?faces-redirect=true";
        } catch (BussinessException ex) {            
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception e) {
            LOGGER.error("Error ", e);
        }
        return null;
    }

	public RmbsApplication getRmbsApplication() {
		return rmbsApplication;
	}

	public void setRmbsApplication(RmbsApplication rmbsApplication) {
		this.rmbsApplication = rmbsApplication;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Boolean getIsWaitingApproval() {
		return isWaitingApproval;
	}

	public void setIsWaitingApproval(Boolean isWaitingApproval) {
		this.isWaitingApproval = isWaitingApproval;
	}

	public Boolean getIsWaitingRevised() {
		return isWaitingRevised;
	}

	public void setIsWaitingRevised(Boolean isWaitingRevised) {
		this.isWaitingRevised = isWaitingRevised;
	}

	public Boolean getIsApprover() {
		return isApprover;
	}

	public void setIsApprover(Boolean isApprover) {
		this.isApprover = isApprover;
	}

	public Boolean getIsRequester() {
		return isRequester;
	}

	public void setIsRequester(Boolean isRequester) {
		this.isRequester = isRequester;
	}

	public ApprovalActivity getSelectedApprovalActivity() {
		return selectedApprovalActivity;
	}

	public void setSelectedApprovalActivity(
			ApprovalActivity selectedApprovalActivity) {
		this.selectedApprovalActivity = selectedApprovalActivity;
	}

	public ApprovalActivityService getApprovalActivityService() {
		return approvalActivityService;
	}

	public void setApprovalActivityService(
			ApprovalActivityService approvalActivityService) {
		this.approvalActivityService = approvalActivityService;
	}

	public RmbsApplicationService getRmbsApplicationService() {
		return rmbsApplicationService;
	}

	public void setRmbsApplicationService(
			RmbsApplicationService rmbsApplicationService) {
		this.rmbsApplicationService = rmbsApplicationService;
	}

	public EmpDataService getEmpDataService() {
		return empDataService;
	}

	public void setEmpDataService(EmpDataService empDataService) {
		this.empDataService = empDataService;
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

	public RmbsTypeService getRmbsTypeService() {
		return rmbsTypeService;
	}

	public void setRmbsTypeService(RmbsTypeService rmbsTypeService) {
		this.rmbsTypeService = rmbsTypeService;
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

	public BigDecimal getTotalRequestThisMoth() {
		return totalRequestThisMoth;
	}

	public void setTotalRequestThisMoth(BigDecimal totalRequestThisMoth) {
		this.totalRequestThisMoth = totalRequestThisMoth;
	}

	public CurrencyService getCurrencyService() {
		return currencyService;
	}

	public void setCurrencyService(CurrencyService currencyService) {
		this.currencyService = currencyService;
	}

	public Boolean getIsHaveAttachment() {
		return isHaveAttachment;
	}

	public void setIsHaveAttachment(Boolean isHaveAttachment) {
		this.isHaveAttachment = isHaveAttachment;
	}
	
}
