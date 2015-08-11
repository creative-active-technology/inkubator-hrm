/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.recruitment;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.ApprovalActivity;
import com.inkubator.hrm.entity.RecruitAdvertisementMedia;
import com.inkubator.hrm.entity.RecruitHireApply;
import com.inkubator.hrm.entity.RecruitVacancyAdvertisement;
import com.inkubator.hrm.entity.RecruitVacancyAdvertisementDetail;
import com.inkubator.hrm.json.util.JsonUtil;
import com.inkubator.hrm.service.ApprovalActivityService;
import com.inkubator.hrm.service.RecruitAdvertisementMediaService;
import com.inkubator.hrm.service.RecruitHireApplyService;
import com.inkubator.hrm.service.RecruitVacancyAdvertisementService;
import com.inkubator.securitycore.util.UserInfoUtil;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "vacancyAdvertisementApprovalFormController")
@ViewScoped
public class VacancyAdvertisementApprovalFormController extends BaseController {

	private RecruitVacancyAdvertisement vacancyAdvertisement;
    private String comment;
    private Boolean isWaitingApproval;
    private Boolean isWaitingRevised;
    private Boolean isApprover;
    private Boolean isRequester;
    private Boolean isHaveAttachment;
    private ApprovalActivity currentActivity;
    private ApprovalActivity askingRevisedActivity;
    
    @ManagedProperty(value = "#{approvalActivityService}")
    private ApprovalActivityService approvalActivityService;
    @ManagedProperty(value = "#{recruitAdvertisementMediaService}")
    private RecruitAdvertisementMediaService recruitAdvertisementMediaService;
    @ManagedProperty(value = "#{recruitVacancyAdvertisementService}")
    private RecruitVacancyAdvertisementService recruitVacancyAdvertisementService;
    @ManagedProperty(value = "#{recruitHireApplyService}")
    private RecruitHireApplyService recruitHireApplyService;
    

    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            
            /** initial data for approval activity tracking */
            String id = FacesUtil.getRequestParameter("execution");
            currentActivity = approvalActivityService.getEntiyByPK(Long.parseLong(id.substring(1)));
            askingRevisedActivity = approvalActivityService.getEntityByActivityNumberAndSequence(currentActivity.getActivityNumber(),
                    currentActivity.getSequence() - 1);
            isWaitingApproval = currentActivity.getApprovalStatus() == HRMConstant.APPROVAL_STATUS_WAITING_APPROVAL;
            isWaitingRevised = currentActivity.getApprovalStatus() == HRMConstant.APPROVAL_STATUS_WAITING_REVISED;
            isApprover = StringUtils.equals(UserInfoUtil.getUserName(), currentActivity.getApprovedBy());
            isRequester = StringUtils.equals(UserInfoUtil.getUserName(), currentActivity.getRequestBy());            
            
            /** start binding data that needed (from json) to object */
            vacancyAdvertisement = this.convertJsonToEntity(currentActivity.getPendingData());      
            
            //relational object
            RecruitAdvertisementMedia advertisementMedia = recruitAdvertisementMediaService.getEntiyByPK(vacancyAdvertisement.getAdvertisementMedia().getId());
            vacancyAdvertisement.setAdvertisementMedia(advertisementMedia);
                        
            
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    @PreDestroy
    public void cleanAndExit() {
    	vacancyAdvertisement = null;
        isWaitingRevised = null;
        currentActivity = null;
        approvalActivityService = null;
        comment = null;
        isWaitingApproval = null;
        isApprover = null;
        isRequester = null;
        isHaveAttachment = null;
        recruitVacancyAdvertisementService = null;
        recruitAdvertisementMediaService = null;
        recruitHireApplyService = null;
    }
    
    private RecruitVacancyAdvertisement convertJsonToEntity(String json) throws Exception{
		Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
		RecruitVacancyAdvertisement entity = gson.fromJson(json, RecruitVacancyAdvertisement.class);
		
		JsonObject jsonObject =  gson.fromJson(json, JsonObject.class);
		List<RecruitVacancyAdvertisementDetail> recruitVacancyAdvertisementDetails = gson.fromJson(jsonObject.get("recruitVacancyAdvertisementDetails"), new TypeToken<List<RecruitVacancyAdvertisementDetail>>(){}.getType());		
		
		//relational object
		for(RecruitVacancyAdvertisementDetail advertisementDetail: recruitVacancyAdvertisementDetails){
			RecruitHireApply hireApply = recruitHireApplyService.getEntityByPkWithDetail(advertisementDetail.getHireApply().getId());
			advertisementDetail.setHireApply(hireApply);
		}
		
		//convert List to HashSet
		entity.setRecruitVacancyAdvertisementDetails(new HashSet<RecruitVacancyAdvertisementDetail>(recruitVacancyAdvertisementDetails));
		
        return entity;
	}

    public String doBack() {
        return "/protected/home.htm?faces-redirect=true";
    }

    public void doRevised() {
    	try {
            ExternalContext red = FacesUtil.getExternalContext();
            red.redirect(red.getRequestContextPath() + "/flow-protected/vacancy_advertisement?activity=" + currentActivity.getId());
        } catch (IOException ex) {
          LOGGER.error("Erorr", ex);
        }
    }
    
    public String doAskingRevised() {
        try {
        	recruitVacancyAdvertisementService.askingRevised(currentActivity.getId(), comment);
            MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.approval_info", "global.process_successfully",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            return "/protected/recruitment/vacancy_advertisement_view.htm?faces-redirect=true";
        } catch (BussinessException ex) {            
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception e) {
            LOGGER.error("Error ", e);
        }
        return null;
    }
    
    public String doApproved() {
        try {
        	recruitVacancyAdvertisementService.approved(currentActivity.getId(), null, comment);
            MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.approval_info", "global.approved_successfully",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            return "/protected/recruitment/vacancy_advertisement_view.htm?faces-redirect=true";
        } catch (BussinessException ex) {            
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception e) {
            LOGGER.error("Error ", e);
        }
        return null;
    }

    public String doRejected() {
        try {
        	recruitVacancyAdvertisementService.rejected(currentActivity.getId(), comment);
            MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.approval_info", "global.rejected_successfully",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            return "/protected/recruitment/vacancy_advertisement_view.htm?faces-redirect=true";
        } catch (BussinessException ex) {            
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception e) {
            LOGGER.error("Error ", e);
        }
        return null;
    }

	public RecruitVacancyAdvertisement getVacancyAdvertisement() {
		return vacancyAdvertisement;
	}

	public void setVacancyAdvertisement(RecruitVacancyAdvertisement vacancyAdvertisement) {
		this.vacancyAdvertisement = vacancyAdvertisement;
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

	public Boolean getIsHaveAttachment() {
		return isHaveAttachment;
	}

	public void setIsHaveAttachment(Boolean isHaveAttachment) {
		this.isHaveAttachment = isHaveAttachment;
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

	public void setApprovalActivityService(ApprovalActivityService approvalActivityService) {
		this.approvalActivityService = approvalActivityService;
	}

	public RecruitAdvertisementMediaService getRecruitAdvertisementMediaService() {
		return recruitAdvertisementMediaService;
	}

	public void setRecruitAdvertisementMediaService(RecruitAdvertisementMediaService recruitAdvertisementMediaService) {
		this.recruitAdvertisementMediaService = recruitAdvertisementMediaService;
	}

	public RecruitVacancyAdvertisementService getRecruitVacancyAdvertisementService() {
		return recruitVacancyAdvertisementService;
	}

	public void setRecruitVacancyAdvertisementService(RecruitVacancyAdvertisementService recruitVacancyAdvertisementService) {
		this.recruitVacancyAdvertisementService = recruitVacancyAdvertisementService;
	}

	public RecruitHireApplyService getRecruitHireApplyService() {
		return recruitHireApplyService;
	}

	public void setRecruitHireApplyService(RecruitHireApplyService recruitHireApplyService) {
		this.recruitHireApplyService = recruitHireApplyService;
	}
	
}
