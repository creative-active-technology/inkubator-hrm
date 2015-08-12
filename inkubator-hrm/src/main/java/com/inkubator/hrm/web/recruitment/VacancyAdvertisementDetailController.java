/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.recruitment;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;

import org.apache.commons.lang.StringUtils;

import com.inkubator.hrm.entity.ApprovalActivity;
import com.inkubator.hrm.entity.RecruitVacancyAdvertisement;
import com.inkubator.hrm.entity.RecruitVacancyAdvertisementDetail;
import com.inkubator.hrm.service.ApprovalActivityService;
import com.inkubator.hrm.service.RecruitVacancyAdvertisementService;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "vacancyAdvertisementDetailController")
@ViewScoped
public class VacancyAdvertisementDetailController extends BaseController {
	
    private RecruitVacancyAdvertisement vacancyAdvertisement;
    private List<RecruitVacancyAdvertisementDetail> listAdvertisementDetail;
    private ApprovalActivity lastApprovalActivity;
    
    @ManagedProperty(value = "#{recruitVacancyAdvertisementService}")
    private RecruitVacancyAdvertisementService recruitVacancyAdvertisementService;
    @ManagedProperty(value = "#{approvalActivityService}")
    private ApprovalActivityService approvalActivityService;

    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            String execution = FacesUtil.getRequestParameter("execution");
            String param = execution.substring(0, 1);
            if(StringUtils.equals(param, "e")){
            	/* parameter (id) ini datangnya dari loan Flow atau View */
            	vacancyAdvertisement = recruitVacancyAdvertisementService.getEntityByPkWithDetail(Long.parseLong(execution.substring(1)));
            } else {
            	/* parameter (activityNumber) ini datangnya dari home approval request history View */
            	vacancyAdvertisement = recruitVacancyAdvertisementService.getEntityByApprovalActivityNumberWithDetail(execution.substring(1));
            }
            
            lastApprovalActivity = approvalActivityService.getEntityByActivityNumberLastSequence(vacancyAdvertisement.getApprovalActivityNumber());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);

        }
    }

    @PreDestroy
    public void cleanAndExit() {
    	recruitVacancyAdvertisementService = null;
    	listAdvertisementDetail = null;
    	recruitVacancyAdvertisementService = null;
    	lastApprovalActivity = null;
    	approvalActivityService = null;
    }   

    public String doBack() {
        return "/protected/recruitment/vacancy_advertisement_view.htm?faces-redirect=true";
    }

    public void doUpdate() {
    	try {
            ExternalContext red = FacesUtil.getExternalContext();
            red.redirect(red.getRequestContextPath() + "/flow-protected/vacancy_advertisement?id=" + vacancyAdvertisement.getId());
        } catch (IOException ex) {
          LOGGER.error("Erorr", ex);
        }
    }

	public RecruitVacancyAdvertisement getVacancyAdvertisement() {
		return vacancyAdvertisement;
	}

	public void setVacancyAdvertisement(RecruitVacancyAdvertisement vacancyAdvertisement) {
		this.vacancyAdvertisement = vacancyAdvertisement;
	}

	public List<RecruitVacancyAdvertisementDetail> getListAdvertisementDetail() {
		return listAdvertisementDetail;
	}

	public void setListAdvertisementDetail(List<RecruitVacancyAdvertisementDetail> listAdvertisementDetail) {
		this.listAdvertisementDetail = listAdvertisementDetail;
	}

	public RecruitVacancyAdvertisementService getRecruitVacancyAdvertisementService() {
		return recruitVacancyAdvertisementService;
	}

	public void setRecruitVacancyAdvertisementService(RecruitVacancyAdvertisementService recruitVacancyAdvertisementService) {
		this.recruitVacancyAdvertisementService = recruitVacancyAdvertisementService;
	}

	public ApprovalActivity getLastApprovalActivity() {
		return lastApprovalActivity;
	}

	public void setLastApprovalActivity(ApprovalActivity lastApprovalActivity) {
		this.lastApprovalActivity = lastApprovalActivity;
	}

	public ApprovalActivityService getApprovalActivityService() {
		return approvalActivityService;
	}

	public void setApprovalActivityService(ApprovalActivityService approvalActivityService) {
		this.approvalActivityService = approvalActivityService;
	}
	
}
