/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.mobile;

import com.inkubator.hrm.service.ApprovalActivityService;
import com.inkubator.hrm.service.LeaveImplementationDateService;
import com.inkubator.hrm.util.HrmUserInfoUtil;
import com.inkubator.hrm.web.model.LeaveImplementationDateModel;
import com.inkubator.securitycore.util.UserInfoUtil;
import com.inkubator.webcore.controller.BaseController;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author denifahri
 */
@ManagedBean(name = "homeMobileController")
@ViewScoped
public class HomeMobileController extends BaseController {

    @ManagedProperty(value = "#{approvalActivityService}")
    private ApprovalActivityService approvalActivityService;
    @ManagedProperty(value = "#{leaveImplementationDateService}")
    private LeaveImplementationDateService leaveImplementationDateService;
    private Long totalRequestHistory;
    private Long totalPendingTask;
    private Long totalPendingRequest;
    private Integer totalLeaveType;

    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            totalRequestHistory = approvalActivityService.getTotalRequestHistory(UserInfoUtil.getUserName());
            totalPendingTask = approvalActivityService.getTotalPendingTask(UserInfoUtil.getUserName());
            totalPendingRequest = approvalActivityService.getTotalPendingRequest(UserInfoUtil.getUserName());
            Long empDataId = HrmUserInfoUtil.getEmpId();
            List<LeaveImplementationDateModel> listLeaveImplementationDateModel = leaveImplementationDateService.getAllDataWithTotalTakenLeaveByEmpDataId(empDataId);
            totalLeaveType = listLeaveImplementationDateModel.size();
            System.out.println(" Jumlah nya adalah " + totalRequestHistory);
            System.out.println(" Jumlah nya adalah " + totalPendingTask);
            System.out.println(" Jumlah nya adalah " + totalPendingRequest);
            System.out.println(" Jumlah nya adalah " + totalLeaveType);
        } catch (Exception ex) {
            LOGGER.error(ex, ex);
        }
    }

    public Long getTotalRequestHistory() {
        return totalRequestHistory;
    }

    public void setTotalRequestHistory(Long totalRequestHistory) {
        this.totalRequestHistory = totalRequestHistory;
    }

    public Long getTotalPendingTask() {
        return totalPendingTask;
    }

    public void setTotalPendingTask(Long totalPendingTask) {
        this.totalPendingTask = totalPendingTask;
    }

    public Long getTotalPendingRequest() {
        return totalPendingRequest;
    }

    public void setTotalPendingRequest(Long totalPendingRequest) {
        this.totalPendingRequest = totalPendingRequest;
    }

    public void setApprovalActivityService(ApprovalActivityService approvalActivityService) {
        this.approvalActivityService = approvalActivityService;
    }

    public void setLeaveImplementationDateService(LeaveImplementationDateService leaveImplementationDateService) {
        this.leaveImplementationDateService = leaveImplementationDateService;
    }

    public Integer getTotalLeaveType() {
        return totalLeaveType;
    }

    public void setTotalLeaveType(Integer totalLeaveType) {
        this.totalLeaveType = totalLeaveType;
    }

}
