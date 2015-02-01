package com.inkubator.hrm.web.report;

import com.inkubator.hrm.entity.ApprovalActivity;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.LazyDataModel;

import com.inkubator.hrm.entity.PermitImplementation;
import com.inkubator.hrm.service.ApprovalActivityService;
import com.inkubator.hrm.service.PermitImplementationService;
import com.inkubator.hrm.util.HrmUserInfoUtil;
import com.inkubator.hrm.web.lazymodel.PermitImplementationReportSearchLazyDataModel;
import com.inkubator.hrm.web.model.ReportPermitHistoryModel;
import com.inkubator.hrm.web.search.PermitImplementationReportSearchParameter;
import com.inkubator.securitycore.util.UserInfoUtil;
import com.inkubator.webcore.controller.BaseController;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "reportPermitHistoryViewController")
@ViewScoped
public class ReportPermitHistoryViewController extends BaseController {

    private PermitImplementationReportSearchParameter searchParameter;
    private LazyDataModel<PermitImplementation> lazyDataModel;
    @ManagedProperty(value = "#{permitImplementationService}")
    private PermitImplementationService permitImplementationService;
    private PermitImplementation selectedPermitImplementation;
    @ManagedProperty(value = "#{approvalActivityService}")
    private ApprovalActivityService approvalActivityService;
    private List<ApprovalActivity> selectedApprovalActivity;
    private List<String> activityNumbers = new ArrayList<>();
    private ApprovalActivity approvalActivity;
    private Long empData;
    private List<PermitImplementation> listPermitImplementations;
    private List<ReportPermitHistoryModel> listReportPermitHistoryModels = new ArrayList<>();

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        searchParameter = new PermitImplementationReportSearchParameter();
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
        permitImplementationService = null;
        searchParameter = null;
        lazyDataModel = null;
        selectedPermitImplementation = null;
        approvalActivity = null;
        approvalActivityService = null;
        selectedApprovalActivity = null;
        activityNumbers = null;
        empData = null;
        listPermitImplementations = null;
        listReportPermitHistoryModels = null;
    }

    public List<ReportPermitHistoryModel> getListReportPermitHistoryModels() {
        return listReportPermitHistoryModels;
    }

    public void setListReportPermitHistoryModels(List<ReportPermitHistoryModel> listReportPermitHistoryModels) {
        this.listReportPermitHistoryModels = listReportPermitHistoryModels;
    }

    public Long getEmpData() {
        return empData;
    }

    public void setEmpData(Long empData) {
        this.empData = empData;
    }

    public List<PermitImplementation> getListPermitImplementations() {
        return listPermitImplementations;
    }

    public void setListPermitImplementations(List<PermitImplementation> listPermitImplementations) {
        this.listPermitImplementations = listPermitImplementations;
    }

    public PermitImplementationReportSearchParameter getSearchParameter() {
        return searchParameter;
    }

    public void setSearchParameter(PermitImplementationReportSearchParameter searchParameter) {
        this.searchParameter = searchParameter;
    }

    public ApprovalActivity getApprovalActivity() {
        return approvalActivity;
    }

    public void setApprovalActivity(ApprovalActivity approvalActivity) {
        this.approvalActivity = approvalActivity;
    }

    public LazyDataModel<PermitImplementation> getLazyDataModel() {
        if (lazyDataModel == null) {
            lazyDataModel = new PermitImplementationReportSearchLazyDataModel(searchParameter, activityNumbers, empData, permitImplementationService);
        }
        return lazyDataModel;
    }

    public void setLazyDataModel(LazyDataModel<PermitImplementation> lazyDataModel) {
        this.lazyDataModel = lazyDataModel;
    }

    public PermitImplementationService getPermitImplementationService() {
        return permitImplementationService;
    }

    public void setPermitImplementationService(PermitImplementationService permitImplementationService) {
        this.permitImplementationService = permitImplementationService;
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

    public PermitImplementation getSelectedPermitImplementation() {
        return selectedPermitImplementation;
    }

    public void setSelectedPermitImplementation(PermitImplementation selectedPermitImplementation) {
        this.selectedPermitImplementation = selectedPermitImplementation;
    }

    public void doSearch() throws Exception {
        lazyDataModel = null;
        activityNumbers.clear();
        listReportPermitHistoryModels.clear();
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
            selectedPermitImplementation = this.permitImplementationService.getEntityByPkWithDetail(selectedPermitImplementation.getId());
//            approvalActivity = this.approvalActivityService.getEntityByActivityNumberLastSequence(selectedPermitImplementation.getApprovalActivityNumber());
            approvalActivity.setApprovedBy(HrmUserInfoUtil.getRealNameByUserName(approvalActivity.getApprovedBy()));
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public void listReportHistory() {
        try {
            listPermitImplementations = permitImplementationService.getReportHistoryByParam(searchParameter, activityNumbers, empData);
            for (PermitImplementation l : listPermitImplementations) {
                ReportPermitHistoryModel reportPermitHistoryModel = new ReportPermitHistoryModel();
                reportPermitHistoryModel.setEndDate(l.getEndDate());
                reportPermitHistoryModel.setPermitClassification(l.getPermitClassification().getName());
                reportPermitHistoryModel.setNikWithFullName(l.getEmpData().getNikWithFullName());
                reportPermitHistoryModel.setNumberFilling(l.getNumberFilling());
                reportPermitHistoryModel.setStartDate(l.getStartDate());

                ApprovalActivity appActivity = this.approvalActivityService.getEntityByActivityNumberLastSequence(l.getNumberFilling());
                appActivity.setApprovedBy(HrmUserInfoUtil.getRealNameByUserName(appActivity.getApprovedBy()));
                
                reportPermitHistoryModel.setApprovalTime(appActivity.getApprovalTime());
                reportPermitHistoryModel.setApprovedBy(appActivity.getApprovedBy());
                reportPermitHistoryModel.setRequestTime(appActivity.getRequestTime());
                
                listReportPermitHistoryModels.add(reportPermitHistoryModel);
            }
        } catch (Exception ex) {
            Logger.getLogger(ReportPermitHistoryViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
