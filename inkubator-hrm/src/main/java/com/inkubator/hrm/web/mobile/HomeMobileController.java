/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.mobile;

import com.inkubator.hrm.service.ApprovalActivityService;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.LeaveImplementationDateService;
import com.inkubator.hrm.util.HrmUserInfoUtil;
import com.inkubator.hrm.web.model.LeaveImplementationDateModel;
import com.inkubator.securitycore.util.UserInfoUtil;
import com.inkubator.webcore.controller.BaseController;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.chart.PieChartModel;

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
    private PieChartModel pieModel;
    private Date lastUpdateEmpDistByAge;
     @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;

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
            pieModel = new PieChartModel();
             Map<String, Long> employeesByAge = empDataService.getTotalByAge(HrmUserInfoUtil.getCompanyId());
            pieModel.set("< 26", employeesByAge.get("lessThan26"));
            pieModel.set("25-30", employeesByAge.get("between26And30"));
            pieModel.set("31-35", employeesByAge.get("between31And35"));
            pieModel.set("36-40", employeesByAge.get("between36And40"));
            pieModel.set("> 40", employeesByAge.get("moreThan40"));
            pieModel.setLegendPosition("e");
            pieModel.setFill(true);
            pieModel.setShowDataLabels(true);
            pieModel.setSliceMargin(4);
            pieModel.setDiameter(150);
            pieModel.setSeriesColors("66cc00,629de1,003366,990000,cccc00,6600cc");
            lastUpdateEmpDistByAge = new Date(employeesByAge.get("lastUpdate"));

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

    public PieChartModel getPieModel() {
        return pieModel;
    }

    public void setPieModel(PieChartModel pieModel) {
        this.pieModel = pieModel;
    }

    public Date getLastUpdateEmpDistByAge() {
        return lastUpdateEmpDistByAge;
    }

    public void setLastUpdateEmpDistByAge(Date lastUpdateEmpDistByAge) {
        this.lastUpdateEmpDistByAge = lastUpdateEmpDistByAge;
    }

    public void setEmpDataService(EmpDataService empDataService) {
        this.empDataService = empDataService;
    }

}
