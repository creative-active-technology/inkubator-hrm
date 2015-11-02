/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.recruitment;

import ch.lambdaj.Lambda;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.Jabatan;
import com.inkubator.hrm.entity.RecruitMppApply;
import com.inkubator.hrm.entity.RecruitMppApplyDetail;
import com.inkubator.hrm.service.RecruitHireApplyDetailService;
import com.inkubator.hrm.service.RecruitHireApplyService;
import com.inkubator.hrm.service.RecruitMppApplyDetailService;
import com.inkubator.hrm.service.RecruitMppApplyService;
import com.inkubator.hrm.service.RecruitSelectionApplicantInitialService;
import com.inkubator.hrm.web.lazymodel.RecruitmentScheduleSettingLazyDataModel;
import com.inkubator.hrm.web.model.RecruitmentScheduleSettingViewModel;
import com.inkubator.hrm.web.search.RecruitmentScheduleSettingSearchParameter;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;

import org.hibernate.exception.ConstraintViolationException;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.springframework.dao.DataIntegrityViolationException;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
@ManagedBean(name = "recruitmentScheduleSettingController")
@ViewScoped
public class RecruitmentScheduleSettingController extends BaseController {

 
    @ManagedProperty(value = "#{recruitMppApplyService}")
    private RecruitMppApplyService recruitMppApplyService;
    @ManagedProperty(value = "#{recruitMppApplyDetailService}")
    private RecruitMppApplyDetailService recruitMppApplyDetailService;
    @ManagedProperty(value = "#{recruitSelectionApplicantInitialService}")
    private RecruitSelectionApplicantInitialService recruitSelectionApplicantInitialService;
    private RecruitmentScheduleSettingSearchParameter searchParameter;
    private LazyDataModel<RecruitmentScheduleSettingViewModel> lazyDataModel;
    private RecruitmentScheduleSettingViewModel selected;
    private Map<String, Long> mapRecruitPlan = new HashMap<>();
    
    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try {
			searchParameter = new RecruitmentScheduleSettingSearchParameter();
			List<RecruitMppApply> listRecruitMppApplies = recruitMppApplyService.getListWithDetailByApprovalStatus(HRMConstant.APPROVAL_STATUS_APPROVED);
			for(RecruitMppApply recruitMppApply : listRecruitMppApplies){
				mapRecruitPlan.put(recruitMppApply.getRecruitMppApplyName(), recruitMppApply.getId());
			}
		} catch (Exception e) {
			LOGGER.error("Error", e);
		}
    }

    @PreDestroy
    private void cleanAndExit() {
        searchParameter = null;
        lazyDataModel = null;
        recruitMppApplyService = null;
        selected = null;
    }

    public void doSearch() {
    	try{
    		
    		if(searchParameter.getRecruitMppApplyId() != null){
    			List<RecruitMppApplyDetail> listRecruitMppApplyDetail = recruitMppApplyDetailService.getListWithDetailByRecruitMppApplyId(searchParameter.getRecruitMppApplyId());
    			List<Long> listJabatanId = Lambda.extract(listRecruitMppApplyDetail, Lambda.on(RecruitMppApplyDetail.class).getJabatan().getId());
    			searchParameter.setListJabatanOnSelectedMppApply(listJabatanId);
    		}
    		
    		lazyDataModel = null;
    		
    	}catch(Exception ex){
    		LOGGER.error("Error", ex);
    	}
    }


    public void doSetupSchedule(){
    	try {
            ExternalContext red = FacesUtil.getExternalContext();
            red.redirect(red.getRequestContextPath() + "/flow-protected/recruitment_schedule_setting?id=" + selected.getId());
        } catch (IOException ex) {
          LOGGER.error("Erorr", ex);
        }
    }

    public void resetLazy(){
        lazyDataModel = null;
    }
    
    public RecruitmentScheduleSettingSearchParameter getSearchParameter() {
        return searchParameter;
    }

    public void setSearchParameter(RecruitmentScheduleSettingSearchParameter searchParameter) {
        this.searchParameter = searchParameter;
    }

    public LazyDataModel<RecruitmentScheduleSettingViewModel> getLazyDataModel() {
        if(lazyDataModel == null){
            lazyDataModel = new RecruitmentScheduleSettingLazyDataModel(searchParameter, recruitSelectionApplicantInitialService);
        }
        return lazyDataModel;
    }

    public void setLazyDataModel(LazyDataModel<RecruitmentScheduleSettingViewModel> lazyDataModel) {
        this.lazyDataModel = lazyDataModel;
    }

    public RecruitmentScheduleSettingViewModel getSelected() {
        return selected;
    }

    public void setSelected(RecruitmentScheduleSettingViewModel selected) {
        this.selected = selected;
    }

	public void setRecruitMppApplyService(RecruitMppApplyService recruitMppApplyService) {
		this.recruitMppApplyService = recruitMppApplyService;
	}

	public Map<String, Long> getMapRecruitPlan() {
		return mapRecruitPlan;
	}

	public void setMapRecruitPlan(Map<String, Long> mapRecruitPlan) {
		this.mapRecruitPlan = mapRecruitPlan;
	}

	public void setRecruitMppApplyDetailService(RecruitMppApplyDetailService recruitMppApplyDetailService) {
		this.recruitMppApplyDetailService = recruitMppApplyDetailService;
	}

	public void setRecruitSelectionApplicantInitialService(RecruitSelectionApplicantInitialService recruitSelectionApplicantInitialService) {
		this.recruitSelectionApplicantInitialService = recruitSelectionApplicantInitialService;
	}

	
	
	

    
}
