/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.employee;

import com.inkubator.hrm.entity.ApprovalActivity;
import com.inkubator.hrm.entity.ImplementationOfOverTime;
import com.inkubator.hrm.service.ApprovalActivityService;
import com.inkubator.hrm.service.ImplementationOfOverTimeService;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Deni
 */
@ManagedBean(name = "implementationOfOverTimeDetailController")
@ViewScoped
public class ImplementationOfOverTimeDetailController extends BaseController{
    private ImplementationOfOverTime selectedImplementationOfOverTime;
    private ApprovalActivity selectedApprovalActivity;
    @ManagedProperty(value = "#{implementationOfOverTimeService}")
    private ImplementationOfOverTimeService implementationOfOverTimeService;
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
            	/* parameter (id) ini datangnya dari leave implementation View */
            	selectedImplementationOfOverTime = implementationOfOverTimeService.getEntityByPkWithDetail(Long.parseLong(execution.substring(1)));
            } else {
            	/* parameter (activityNumber) ini datangnya dari home approval request history View */
            	selectedImplementationOfOverTime = implementationOfOverTimeService.getEntityByApprovalActivityNumberWithDetail(execution.substring(1));
            }
            
            selectedApprovalActivity = approvalActivityService.getEntityByActivityNumberLastSequence(selectedImplementationOfOverTime.getApprovalActivityNumber());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);

        }
    }
    
    @PreDestroy
    public void cleanAndExit() {
    	selectedImplementationOfOverTime = null;
        implementationOfOverTimeService = null;
        selectedApprovalActivity = null;
        approvalActivityService = null;
    }  

    public ImplementationOfOverTime getSelectedImplementationOfOverTime() {
        return selectedImplementationOfOverTime;
    }

    public void setSelectedImplementationOfOverTime(ImplementationOfOverTime selectedImplementationOfOverTime) {
        this.selectedImplementationOfOverTime = selectedImplementationOfOverTime;
    }

    public ApprovalActivity getSelectedApprovalActivity() {
        return selectedApprovalActivity;
    }

    public void setSelectedApprovalActivity(ApprovalActivity selectedApprovalActivity) {
        this.selectedApprovalActivity = selectedApprovalActivity;
    }

    public ImplementationOfOverTimeService getImplementationOfOverTimeService() {
        return implementationOfOverTimeService;
    }

    public void setImplementationOfOverTimeService(ImplementationOfOverTimeService implementationOfOverTimeService) {
        this.implementationOfOverTimeService = implementationOfOverTimeService;
    }

    public ApprovalActivityService getApprovalActivityService() {
        return approvalActivityService;
    }

    public void setApprovalActivityService(ApprovalActivityService approvalActivityService) {
        this.approvalActivityService = approvalActivityService;
    }
    
    public String doBack() {
        return "/protected/employee/overtime_implementation_view.htm?faces-redirect=true";
    }

    public String doUpdate() {
        return "/protected/employee/overtime_implementation_form.htm?faces-redirect=true&execution=e" + selectedImplementationOfOverTime.getId();
    }
    
    public Boolean getIsHaveApprovalActivity(){
		return selectedApprovalActivity != null;
	}
}
