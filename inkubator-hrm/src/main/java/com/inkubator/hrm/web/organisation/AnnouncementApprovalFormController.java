/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.organisation;

import com.inkubator.common.util.JsonConverter;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.ApprovalActivity;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.service.AnnouncementService;
import com.inkubator.hrm.service.ApprovalActivityService;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.web.lazymodel.EmpDataAnnouncementLazyDataModel;
import com.inkubator.hrm.web.model.AnnouncementModelJson;
import com.inkubator.securitycore.util.UserInfoUtil;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author Deni
 */
@ManagedBean(name = "announcementApprovalFormController")
@ViewScoped
public class AnnouncementApprovalFormController extends BaseController {

    @ManagedProperty(value = "#{announcementService}")
    private AnnouncementService announcementService;
    @ManagedProperty(value = "#{approvalActivityService}")
    private ApprovalActivityService approvalActivityService;
    @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;
    private String comment;
    private Boolean isWaitingApproval;
    private Boolean isApprover;
    private Boolean isRequester;
    private ApprovalActivity selectedApprovalActivity;
    private AnnouncementModelJson announcementModelJson;
    private LazyDataModel<EmpData> lazyDataModel;

    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            String id = FacesUtil.getRequestParameter("execution");
            selectedApprovalActivity = approvalActivityService.getEntiyByPK(Long.parseLong(id.substring(1)));
            isWaitingApproval = selectedApprovalActivity.getApprovalStatus() == HRMConstant.APPROVAL_STATUS_WAITING_APPROVAL;
            isApprover = StringUtils.equals(UserInfoUtil.getUserName(), selectedApprovalActivity.getApprovedBy());
            isRequester = StringUtils.equals(UserInfoUtil.getUserName(), selectedApprovalActivity.getRequestBy());
            announcementModelJson = (AnnouncementModelJson) JsonConverter.getClassFromJson(selectedApprovalActivity.getPendingData(), AnnouncementModelJson.class, "dd-MM-yyyy");
//            List<EmpData> listEmployee = empDataService.getAllDataByEmployeeTypeOrGolonganJabatanOrUnitKerja(announcementModelJson.getListEmployeeType(), announcementModelJson.getListGolonganJabatan(), announcementModelJson.getListUnitKerja());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);

        }
    }

    @PreDestroy
    public void cleanAndExit() {
        selectedApprovalActivity = null;
        approvalActivityService = null;
        comment = null;
        lazyDataModel = null;
        isWaitingApproval = null;
        isApprover = null;
        isRequester = null;
        announcementModelJson = null;
        announcementService = null;
        empDataService = null;
    }

    public String doApproved() {
        try {
            announcementService.approved(selectedApprovalActivity.getId(), null, comment);
            MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.approval_info", "global.approved_successfully",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            return "/protected/home.htm?faces-redirect=true";
        } catch (BussinessException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception e) {
            LOGGER.error("Error when approved process ", e);
        }
        return null;
    }

    public ApprovalActivityService getApprovalActivityService() {
        return approvalActivityService;
    }

    public void setApprovalActivityService(ApprovalActivityService approvalActivityService) {
        this.approvalActivityService = approvalActivityService;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Boolean getIsWaitingApproval() {
        return isWaitingApproval;
    }

    public void setIsWaitingApproval(Boolean isWaitingApproval) {
        this.isWaitingApproval = isWaitingApproval;
    }

    public Boolean getIsApprover() {
        return isApprover;
    }

    public void setIsApprover(Boolean isApprover) {
        this.isApprover = isApprover;
    }

    public Boolean getIsRequester() {
        return isRequester;
    }

    public void setIsRequester(Boolean isRequester) {
        this.isRequester = isRequester;
    }

    public ApprovalActivity getSelectedApprovalActivity() {
        return selectedApprovalActivity;
    }

    public void setSelectedApprovalActivity(ApprovalActivity selectedApprovalActivity) {
        this.selectedApprovalActivity = selectedApprovalActivity;
    }

    public AnnouncementModelJson getAnnouncementModelJson() {
        return announcementModelJson;
    }

    public void setAnnouncementModelJson(AnnouncementModelJson announcementModelJson) {
        this.announcementModelJson = announcementModelJson;
    }

    public AnnouncementService getAnnouncementService() {
        return announcementService;
    }

    public void setAnnouncementService(AnnouncementService announcementService) {
        this.announcementService = announcementService;
    }

    public EmpDataService getEmpDataService() {
        return empDataService;
    }

    public void setEmpDataService(EmpDataService empDataService) {
        this.empDataService = empDataService;
    }

    public LazyDataModel<EmpData> getLazyDataModel() {
        if(lazyDataModel == null){
            lazyDataModel = new EmpDataAnnouncementLazyDataModel(empDataService, announcementModelJson.getListEmployeeType(), announcementModelJson.getListGolonganJabatan(), announcementModelJson.getListUnitKerja());
        }
        return lazyDataModel;
    }

    public void setLazyDataModel(LazyDataModel<EmpData> lazyDataModel) {
        this.lazyDataModel = lazyDataModel;
    }

    
}
