/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.workingtime;

import com.inkubator.hrm.entity.ApprovalDefinitionOT;
import com.inkubator.hrm.entity.WtOverTime;
import com.inkubator.hrm.service.ApprovalDefinitionOTService;
import com.inkubator.hrm.service.WtOverTimeService;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Deni
 */
@ManagedBean(name = "overTimeDetailController")
@ViewScoped
public class OverTimeDetailController extends BaseController{
    @ManagedProperty(value = "#{wtOverTimeService}")
    private WtOverTimeService wtOverTimeService;
    @ManagedProperty(value = "#{approvalDefinitionOTService}")
    private ApprovalDefinitionOTService approvalDefinitionOTService;
    WtOverTime selectedWtOverTime;
    private List<ApprovalDefinitionOT> selectedApprovalDefinitionOT;
    
    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            String id = FacesUtil.getRequestParameter("execution");
            selectedWtOverTime = wtOverTimeService.getEntityByPkWithDetail(Long.parseLong(id.substring(1)));
            selectedApprovalDefinitionOT = approvalDefinitionOTService.getByOverTimeId(Long.parseLong(id.substring(1)));
        } catch (Exception ex) {
            LOGGER.error("Error", ex);

        }
    }
    
    @PreDestroy
    public void cleanAndExit() {
        selectedWtOverTime = null;
        wtOverTimeService = null;
        selectedApprovalDefinitionOT = null;
        approvalDefinitionOTService = null;
    } 

    public WtOverTimeService getWtOverTimeService() {
        return wtOverTimeService;
    }

    public void setWtOverTimeService(WtOverTimeService wtOverTimeService) {
        this.wtOverTimeService = wtOverTimeService;
    }

    public WtOverTime getSelectedWtOverTime() {
        return selectedWtOverTime;
    }

    public void setSelectedWtOverTime(WtOverTime selectedWtOverTime) {
        this.selectedWtOverTime = selectedWtOverTime;
    }
    
    public String doBack() {
        return "/protected/working_time/over_time_view.htm?faces-redirect=true";
    }

    public String doUpdate() {
        return "/protected/working_time/over_time_form.htm?faces-redirect=true&execution=e" + selectedWtOverTime.getId();
    }

    public ApprovalDefinitionOTService getApprovalDefinitionOTService() {
        return approvalDefinitionOTService;
    }

    public void setApprovalDefinitionOTService(ApprovalDefinitionOTService approvalDefinitionOTService) {
        this.approvalDefinitionOTService = approvalDefinitionOTService;
    }

    public List<ApprovalDefinitionOT> getSelectedApprovalDefinitionOT() {
        return selectedApprovalDefinitionOT;
    }

    public void setSelectedApprovalDefinitionOT(List<ApprovalDefinitionOT> selectedApprovalDefinitionOT) {
        this.selectedApprovalDefinitionOT = selectedApprovalDefinitionOT;
    }
    
    
}
