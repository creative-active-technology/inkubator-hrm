/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.recruitment;

import com.inkubator.hrm.web.loan.*;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.ApprovalActivity;
import com.inkubator.hrm.entity.LoanNewType;
import com.inkubator.hrm.entity.RecruitMppApply;
import com.inkubator.hrm.service.ApprovalActivityService;
import com.inkubator.hrm.service.LoanNewTypeService;
import com.inkubator.hrm.service.RecruitMppApplyService;
import com.inkubator.hrm.web.lazymodel.LoanNewTypeLazyDataModel;
import com.inkubator.hrm.web.lazymodel.RecruitMppApplyViewLazyDataModel;
import com.inkubator.hrm.web.model.RecruitMppApplyViewModel;
import com.inkubator.hrm.web.search.LoanNewTypeSearchParameter;
import com.inkubator.hrm.web.search.RecruitMppApplySearchParameter;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
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
import org.hibernate.exception.ConstraintViolationException;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.springframework.dao.DataIntegrityViolationException;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
@ManagedBean(name = "recruitMppApplyViewController")
@ViewScoped
public class RecruitMppApplyViewController extends BaseController {

    private RecruitMppApplySearchParameter searchParameter;
    private LazyDataModel<RecruitMppApplyViewModel> lazyData;
    private RecruitMppApplyViewModel selected;
    @ManagedProperty(value = "#{recruitMppApplyService}")
    private RecruitMppApplyService recruitMppApplyService;
    @ManagedProperty(value = "#{approvalActivityService}")
    private ApprovalActivityService approvalActivityService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        searchParameter = new RecruitMppApplySearchParameter();
    }

    @PreDestroy
    public void cleanAndExit() {
        recruitMppApplyService = null;
        approvalActivityService = null;
        searchParameter = null;
        lazyData = null;      
        selected = null;
    }

    public void doSearch() {
        lazyData = null;
    }
    
    public String doAdd() {
        return "/protected/recruitment/recruit_mpp_apply_form.htm?faces-redirect=true";
    }
    
    public String doDetail() {
    	
        return "/protected/recruitment/recruit_mpp_apply_detail.htm?faces-redirect=true&execution=" + selected.getActivityNumber();
    }
    
    public String doEdit() {
        return "/protected/recruitment/recruit_mpp_apply_form.htm?faces-redirect=true&execution=" + selected.getActivityNumber();
    }
    public void doSelectDataToDelete(RecruitMppApplyViewModel selected) {
        this.selected = selected;
    }

    public void doDelete() {
        try {
            ApprovalActivity selectedApprovalActivity = approvalActivityService.getEntityByActivityNumberLastSequence(selected.getActivityNumber());
            if (selectedApprovalActivity.getApprovalStatus() == HRMConstant.APPROVAL_STATUS_APPROVED) {
                MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "mpp_recruitment.error_activity_already_approved", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            } else {
                approvalActivityService.delete(selectedApprovalActivity);                
                MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            }

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            LOGGER.error("Error when doDelete ApprovalActivity ", ex);
        } catch (Exception ex) {
            LOGGER.error("Error when doDelete ApprovalActivity ", ex);
        }
    }


    @Override
    public void onDialogReturn(SelectEvent event) {
        //re-calculate searching
        doSearch();
        super.onDialogReturn(event);
    }

    public RecruitMppApplySearchParameter getSearchParameter() {
        return searchParameter;
    }

    public void setSearchParameter(RecruitMppApplySearchParameter searchParameter) {
        this.searchParameter = searchParameter;
    }

    public LazyDataModel<RecruitMppApplyViewModel> getLazyData() {
        if (lazyData == null) {
            lazyData = new RecruitMppApplyViewLazyDataModel(searchParameter, recruitMppApplyService);
        }
        return lazyData;
    }

    public void setLazyData(LazyDataModel<RecruitMppApplyViewModel> lazyData) {
        this.lazyData = lazyData;
    }

    public void setRecruitMppApplyService(RecruitMppApplyService recruitMppApplyService) {
        this.recruitMppApplyService = recruitMppApplyService;
    }

    public RecruitMppApplyViewModel getSelected() {
        return selected;
    }

    public void setSelected(RecruitMppApplyViewModel selected) {
        this.selected = selected;
    }

    public void setApprovalActivityService(ApprovalActivityService approvalActivityService) {
        this.approvalActivityService = approvalActivityService;
    }

}
