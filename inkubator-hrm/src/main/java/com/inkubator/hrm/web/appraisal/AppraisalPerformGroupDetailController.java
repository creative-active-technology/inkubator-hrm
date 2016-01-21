/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.appraisal;


import com.inkubator.hrm.entity.AppraisalPerformanceGroup;
import com.inkubator.hrm.entity.AppraisalPerformanceIndicator;
import com.inkubator.hrm.entity.RecruitMppApply;
import com.inkubator.hrm.service.AppraisalPerformanceGroupService;
import com.inkubator.hrm.service.AppraisalPerformanceIndicatorService;
import com.inkubator.hrm.web.model.RecruitMppApplyModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
@ManagedBean(name = "appraisalPerformGroupDetailController")
@ViewScoped
public class AppraisalPerformGroupDetailController extends BaseController {

    
    @ManagedProperty(value = "#{appraisalPerformanceGroupService}")
    private AppraisalPerformanceGroupService appraisalPerformanceGroupService;
    @ManagedProperty(value = "#{appraisalPerformanceIndicatorService}")
    private AppraisalPerformanceIndicatorService appraisalPerformanceIndicatorService;
    private List<AppraisalPerformanceIndicator> listPerformanceAppraisalIndicator;
    private AppraisalPerformanceGroup selectedAppraisalPerformanceGroup;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try {

        	listPerformanceAppraisalIndicator = new ArrayList<AppraisalPerformanceIndicator>();
            String idAppraisalPerformanceGroup = FacesUtil.getRequestParameter("execution");
            if (StringUtils.isNotBlank(idAppraisalPerformanceGroup)) {
                selectedAppraisalPerformanceGroup = appraisalPerformanceGroupService.getEntiyByPK(Long.valueOf(idAppraisalPerformanceGroup));
            }

        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

   

    @PreDestroy
    public void cleanAndExit() {
        appraisalPerformanceGroupService = null;
        appraisalPerformanceIndicatorService = null;
        selectedAppraisalPerformanceGroup = null;
        listPerformanceAppraisalIndicator = null;
        
    }

    public String doBack() {
        cleanAndExit();
        return "/protected/appraisal/appraisal_perform_group_view.htm?faces-redirect=true";
    }

    public String doEdit() {
        //return "/protected/recruitment/recruit_mpp_apply_form.htm?faces-redirect=true&execution=" + activityNumber;
    	return "/protected/recruitment/recruit_mpp_apply_view.htm?faces-redirect=true";
    }

    

   

    private RecruitMppApplyModel convertEntityToModel(RecruitMppApply entity) throws Exception {
        RecruitMppApplyModel model = new RecruitMppApplyModel();
      /*  model.setApplyDate(entity.getApplyDate());

        RecruitMppPeriod period = recruitMppPeriodService.getEntiyByPK(entity.getRecruitMppPeriod().getId());
        model.setMppPeriodId(period.getId());
        model.setSelectedRecruitMppPeriod(period);

        model.setRecruitMppApplyCode(entity.getRecruitMppApplyCode());
        model.setRecruitMppApplyName(entity.getRecruitMppApplyName());
        model.setReason(entity.getReason());
        model.setUploadPath(entity.getAttachmentDocPath());
        model.setRecruitMppApplyFileName(entity.getRecruitMppApplyName());*/

        return model;
    }

	public List<AppraisalPerformanceIndicator> getListPerformanceAppraisalIndicator() {
		return listPerformanceAppraisalIndicator;
	}

	public void setListPerformanceAppraisalIndicator(
			List<AppraisalPerformanceIndicator> listPerformanceAppraisalIndicator) {
		this.listPerformanceAppraisalIndicator = listPerformanceAppraisalIndicator;
	}

	public AppraisalPerformanceGroup getSelectedAppraisalPerformanceGroup() {
		return selectedAppraisalPerformanceGroup;
	}

	public void setSelectedAppraisalPerformanceGroup(AppraisalPerformanceGroup selectedAppraisalPerformanceGroup) {
		this.selectedAppraisalPerformanceGroup = selectedAppraisalPerformanceGroup;
	}

	public void setAppraisalPerformanceGroupService(AppraisalPerformanceGroupService appraisalPerformanceGroupService) {
		this.appraisalPerformanceGroupService = appraisalPerformanceGroupService;
	}

	public void setAppraisalPerformanceIndicatorService(
			AppraisalPerformanceIndicatorService appraisalPerformanceIndicatorService) {
		this.appraisalPerformanceIndicatorService = appraisalPerformanceIndicatorService;
	}
    
 
}
