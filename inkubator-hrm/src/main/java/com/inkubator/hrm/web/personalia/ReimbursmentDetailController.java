/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.personalia;

import com.inkubator.hrm.entity.ApprovalActivity;
import com.inkubator.hrm.entity.Reimbursment;
import com.inkubator.hrm.service.ApprovalActivityService;
import com.inkubator.hrm.service.ReimbursmentService;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Deni
 */
@ManagedBean(name = "reimbursmentDetailController")
@ViewScoped
public class ReimbursmentDetailController extends BaseController{
    private Reimbursment selectedReimbursment;
    private ApprovalActivity selectedApprovalActivity;
    @ManagedProperty(value = "#{reimbursmentService}")
    private ReimbursmentService reimbursmentService;
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
            	/* parameter (businessTravelNo) ini datangnya dari businesstravel Flow atau View */
            	selectedReimbursment = reimbursmentService.getEntityByReimbursmentNoWithDetail(execution.substring(1));
            } else {
            	/* parameter (activityNumber) ini datangnya dari home approval request history View */
            	selectedReimbursment = reimbursmentService.getEntityByApprovalActivityNumberWithDetail(execution.substring(1));
            }
            
            selectedApprovalActivity = approvalActivityService.getEntityByActivityNumberLastSequence(selectedReimbursment.getApprovalActivityNumber());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);

        }
    }
    
    @PreDestroy
    public void cleanAndExit() {
        reimbursmentService = null;
        approvalActivityService = null;
        selectedApprovalActivity = null;
        selectedReimbursment = null;
    }

    public String doBack() {
        return "/protected/personalia/reimbursment_view.htm?faces-redirect=true";
    } 
    
    public String doEdit(){
        return "/protected/personalia/reimbursment_form.htm?faces-redirect=true&execution=e" + selectedReimbursment.getId();
    }
    
    public Reimbursment getSelectedReimbursment() {
        return selectedReimbursment;
    }

    public void setSelectedReimbursment(Reimbursment selectedReimbursment) {
        this.selectedReimbursment = selectedReimbursment;
    }

    public ApprovalActivity getSelectedApprovalActivity() {
        return selectedApprovalActivity;
    }

    public void setSelectedApprovalActivity(ApprovalActivity selectedApprovalActivity) {
        this.selectedApprovalActivity = selectedApprovalActivity;
    }

    public ReimbursmentService getReimbursmentService() {
        return reimbursmentService;
    }

    public void setReimbursmentService(ReimbursmentService reimbursmentService) {
        this.reimbursmentService = reimbursmentService;
    }

    public ApprovalActivityService getApprovalActivityService() {
        return approvalActivityService;
    }

    public void setApprovalActivityService(ApprovalActivityService approvalActivityService) {
        this.approvalActivityService = approvalActivityService;
    }
    
    public Boolean getIsHaveApprovalActivity(){
        return selectedApprovalActivity != null;
    }
}
