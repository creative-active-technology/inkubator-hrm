package com.inkubator.hrm.web.report;

import com.inkubator.hrm.entity.ApprovalActivity;
import com.inkubator.hrm.entity.ApprovalDefinitionLeave;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.LazyDataModel;

import com.inkubator.hrm.entity.Department;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.Leave;
import com.inkubator.hrm.entity.LeaveImplementation;
import com.inkubator.hrm.service.ApprovalActivityService;
import com.inkubator.hrm.service.ApprovalDefinitionLeaveService;
import com.inkubator.hrm.service.DepartmentService;
import com.inkubator.hrm.service.LeaveImplementationService;
import com.inkubator.hrm.util.HrmUserInfoUtil;
import com.inkubator.hrm.web.lazymodel.LeaveImplementationReportSearchLazyDataModel;
import com.inkubator.hrm.web.model.ReportLeaveHistoryModel;
import com.inkubator.hrm.web.search.LeaveImplementationReportSearchParameter;
import com.inkubator.securitycore.util.UserInfoUtil;
import com.inkubator.webcore.controller.BaseController;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Email;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "reportLeaveHistoryViewController")
@ViewScoped
public class ReportLeaveHistoryViewController1 extends BaseController {

    private LeaveImplementationReportSearchParameter searchParameter;
    private LazyDataModel<LeaveImplementation> lazyDataModel;
    @ManagedProperty(value = "#{leaveImplementationService}")
    private LeaveImplementationService leaveImplementationService;
    private LeaveImplementation selectedLeaveImplementation;
    @ManagedProperty(value = "#{approvalActivityService}")
    private ApprovalActivityService approvalActivityService;
    private List<ApprovalActivity> selectedApprovalActivity;
    private List<String> activityNumbers = new ArrayList<>();
    private ApprovalActivity approvalActivity;
    private Long empData;
    private List<LeaveImplementation> listLeaveImplementations;
    private List<ReportLeaveHistoryModel> listReportLeaveHistoryModels = new ArrayList<>();

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        searchParameter = new LeaveImplementationReportSearchParameter();
        empData = 0L;
        for (String role : UserInfoUtil.getRoles()) {
            System.out.println("Role : " + role);
            if (UserInfoUtil.hasRole("BASIC_USER")) {
                empData = HrmUserInfoUtil.getEmpData().getId();
            }
        }
        listReportHistory();

    }

    @PreDestroy
    public void cleanAndExit() {
        leaveImplementationService = null;
        searchParameter = null;
        lazyDataModel = null;
    }

    public List<ReportLeaveHistoryModel> getListReportLeaveHistoryModels() {
        return listReportLeaveHistoryModels;
    }

    public void setListReportLeaveHistoryModels(List<ReportLeaveHistoryModel> listReportLeaveHistoryModels) {
        this.listReportLeaveHistoryModels = listReportLeaveHistoryModels;
    }

    public Long getEmpData() {
        return empData;
    }

    public void setEmpData(Long empData) {
        this.empData = empData;
    }

    public List<LeaveImplementation> getListLeaveImplementations() {
        return listLeaveImplementations;
    }

    public void setListLeaveImplementations(List<LeaveImplementation> listLeaveImplementations) {
        this.listLeaveImplementations = listLeaveImplementations;
    }

    public LeaveImplementationReportSearchParameter getSearchParameter() {
        return searchParameter;
    }

    public void setSearchParameter(LeaveImplementationReportSearchParameter searchParameter) {
        this.searchParameter = searchParameter;
    }

    public ApprovalActivity getApprovalActivity() {
        return approvalActivity;
    }

    public void setApprovalActivity(ApprovalActivity approvalActivity) {
        this.approvalActivity = approvalActivity;
    }

    public LazyDataModel<LeaveImplementation> getLazyDataModel() {
        if (lazyDataModel == null) {
            lazyDataModel = new LeaveImplementationReportSearchLazyDataModel(searchParameter, activityNumbers, empData, leaveImplementationService);
        }
        return lazyDataModel;
    }

    public void setLazyDataModel(LazyDataModel<LeaveImplementation> lazyDataModel) {
        this.lazyDataModel = lazyDataModel;
    }

    public LeaveImplementationService getLeaveImplementationService() {
        return leaveImplementationService;
    }

    public void setLeaveImplementationService(LeaveImplementationService leaveImplementationService) {
        this.leaveImplementationService = leaveImplementationService;
    }

    public ApprovalActivityService getApprovalActivityService() {
        return approvalActivityService;
    }

    public void setApprovalActivityService(ApprovalActivityService approvalActivityService) {
        this.approvalActivityService = approvalActivityService;
    }

    public List<ApprovalActivity> getSelectedApprovalActivity() {
        return selectedApprovalActivity;
    }

    public void setSelectedApprovalActivity(List<ApprovalActivity> selectedApprovalActivity) {
        this.selectedApprovalActivity = selectedApprovalActivity;
    }

    public List<String> getActivityNumbers() {
        return activityNumbers;
    }

    public void setActivityNumbers(List<String> activityNumbers) {
        this.activityNumbers = activityNumbers;
    }

    public LeaveImplementation getSelectedLeaveImplementation() {
        return selectedLeaveImplementation;
    }

    public void setSelectedLeaveImplementation(LeaveImplementation selectedLeaveImplementation) {
        this.selectedLeaveImplementation = selectedLeaveImplementation;
    }

    public void doSearch() throws Exception {
        lazyDataModel = null;
        activityNumbers.clear();
        listReportLeaveHistoryModels.clear();
        if (StringUtils.isNotEmpty(searchParameter.getApprovalStatus())) {
            selectedApprovalActivity = approvalActivityService.getByApprovalStatus(Integer.parseInt(searchParameter.getApprovalStatus()));
            System.out.println("app act " + selectedApprovalActivity.size());
            for (ApprovalActivity a : selectedApprovalActivity) {

                activityNumbers.add(a.getActivityNumber());

            }
        }
        listReportHistory();
    }

    public void doDetail() {
        try {
            selectedLeaveImplementation = this.leaveImplementationService.getEntityByPkWithDetail(selectedLeaveImplementation.getId());
            approvalActivity = this.approvalActivityService.getEntityByActivityNumberLastSequence(selectedLeaveImplementation.getApprovalActivityNumber());
            approvalActivity.setApprovedBy(HrmUserInfoUtil.getRealNameByUserName(approvalActivity.getApprovedBy()));
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public void listReportHistory() {
        try {
            listLeaveImplementations = leaveImplementationService.getReportHistoryByParam(searchParameter, activityNumbers, empData);
            for (LeaveImplementation l : listLeaveImplementations) {
                ReportLeaveHistoryModel reportLeaveHistoryModel = new ReportLeaveHistoryModel();
                reportLeaveHistoryModel.setEndDate(l.getEndDate());
                reportLeaveHistoryModel.setLeaveScheme(l.getLeave().getName());
                reportLeaveHistoryModel.setNikWithFullName(l.getEmpData().getNikWithFullName());
                reportLeaveHistoryModel.setNumberFilling(l.getNumberFilling());
                reportLeaveHistoryModel.setStartDate(l.getStartDate());

                ApprovalActivity appActivity = this.approvalActivityService.getEntityByActivityNumberLastSequence(l.getApprovalActivityNumber());
                appActivity.setApprovedBy(HrmUserInfoUtil.getRealNameByUserName(appActivity.getApprovedBy()));
                
                reportLeaveHistoryModel.setApprovalTime(appActivity.getApprovalTime());
                reportLeaveHistoryModel.setApprovedBy(appActivity.getApprovedBy());
                reportLeaveHistoryModel.setRequestTime(appActivity.getRequestTime());
                
                listReportLeaveHistoryModels.add(reportLeaveHistoryModel);
            }
        } catch (Exception ex) {
            Logger.getLogger(ReportLeaveHistoryViewController1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
