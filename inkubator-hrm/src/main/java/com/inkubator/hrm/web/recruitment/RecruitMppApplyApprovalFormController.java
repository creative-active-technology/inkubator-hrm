/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.recruitment;

import ch.lambdaj.Lambda;
import ch.lambdaj.group.Group;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.inkubator.common.util.DateFormatter;
import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.ApprovalActivity;
import com.inkubator.hrm.entity.Currency;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.EmployeeType;
import com.inkubator.hrm.entity.HrmUser;
import com.inkubator.hrm.entity.Jabatan;
import com.inkubator.hrm.entity.OrgTypeOfSpec;
import com.inkubator.hrm.entity.OrgTypeOfSpecList;
import com.inkubator.hrm.entity.RecruitHireApply;
import com.inkubator.hrm.entity.RecruitHireApplyDetail;
import com.inkubator.hrm.entity.RecruitMppApply;
import com.inkubator.hrm.entity.RecruitMppApplyDetail;
import com.inkubator.hrm.entity.RecruitMppPeriod;
import com.inkubator.hrm.json.util.JsonUtil;
import com.inkubator.hrm.service.ApprovalActivityService;
import com.inkubator.hrm.service.CurrencyService;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.EmployeeTypeService;
import com.inkubator.hrm.service.HrmUserService;
import com.inkubator.hrm.service.JabatanService;
import com.inkubator.hrm.service.OrgTypeOfSpecListService;
import com.inkubator.hrm.service.OrgTypeOfSpecService;
import com.inkubator.hrm.service.RecruitHireApplyService;
import com.inkubator.hrm.service.RecruitMppApplyDetailService;
import com.inkubator.hrm.service.RecruitMppApplyService;
import com.inkubator.hrm.service.RecruitMppPeriodService;
import com.inkubator.hrm.web.model.RecruitHireApplyModel;
import com.inkubator.hrm.web.model.RecruitMppApplyModel;
import com.inkubator.securitycore.util.UserInfoUtil;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.apache.commons.lang3.StringUtils;
import org.hamcrest.Matchers;
import org.hamcrest.text.StringContains;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.DualListModel;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
@ManagedBean(name = "recruitMppApplyApprovalFormController")
@ViewScoped
public class RecruitMppApplyApprovalFormController extends BaseController {

    @ManagedProperty(value = "#{recruitMppPeriodService}")
    private RecruitMppPeriodService recruitMppPeriodService;
    @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;
    @ManagedProperty(value = "#{recruitMppApplyService}")
    private RecruitMppApplyService recruitMppApplyService;
    @ManagedProperty(value = "#{hrmUserService}")
    private HrmUserService hrmUserService;
    @ManagedProperty(value = "#{approvalActivityService}")
    private ApprovalActivityService approvalActivityService;
    private RecruitMppApplyModel model;
    private String idApprovalActivity;
    private ApprovalActivity selectedApprovalActivity;
    private Boolean isWaitingApproval;
    private String comment;
    private UploadedFile mppApplyFile;

    @PostConstruct
    @Override
    public void initialization() {
        try {

            model = new RecruitMppApplyModel();

            idApprovalActivity = FacesUtil.getRequestParameter("execution").substring(1);

            //if activityNumber is not empty, it means to edit existing Recruit MPP Apply Request Data
            if (StringUtils.isNotBlank(idApprovalActivity)) {
                selectedApprovalActivity = approvalActivityService.getEntiyByPK(Long.parseLong(idApprovalActivity));

                if (null != selectedApprovalActivity) {
                    isWaitingApproval = selectedApprovalActivity.getApprovalStatus() == HRMConstant.APPROVAL_STATUS_WAITING_APPROVAL;
                    //Make sure only process who have not been approved that can be modified.
                    if (selectedApprovalActivity.getApprovalStatus() != HRMConstant.APPROVAL_STATUS_APPROVED) {
                        model = convertJsonToModel(selectedApprovalActivity.getPendingData());
                    }
                }
            }

        } catch (Exception ex) {
            LOGGER.error(ex, ex);
        }
    }

    public StreamedContent getRecruitMppApplyFile() throws IOException {

        if (mppApplyFile == null) {
            return new DefaultStreamedContent();
        } else {
            try {
                return new DefaultStreamedContent(mppApplyFile.getInputstream(), null, mppApplyFile.getFileName());
            } catch (Exception ex) {
                LOGGER.error(ex, ex);
                return new DefaultStreamedContent();
            }
        }
    }

    @PreDestroy
    public void cleanAndExit() {
        empDataService = null;
        approvalActivityService = null;
        hrmUserService = null;
        recruitMppApplyService = null;
        recruitMppPeriodService = null;
        idApprovalActivity = null;
        selectedApprovalActivity = null;
        isWaitingApproval = null;
        model = null;
    }

    public String doBack() {
        cleanAndExit();
        return "/protected/recruitment/recruit_mpp_apply_view.htm?faces-redirect=true";
    }

    public String doApproved() {
        try {
            recruitMppApplyService.approved(selectedApprovalActivity.getId(), null, comment);
            MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.approval_info", "global.approved_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            return "/protected/recruitment/recruit_mpp_apply_view.htm?faces-redirect=true";
        } catch (BussinessException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception e) {
            LOGGER.error("Error ", e);
        }
        return null;
    }

    public String doRejected() {
        try {
            recruitMppApplyService.rejected(selectedApprovalActivity.getId(), comment);
            MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.approval_info", "global.rejected_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            return "/protected/recruitment/recruitment_req_history_view.htm?faces-redirect=true";
        } catch (BussinessException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception e) {
            LOGGER.error("Error ", e);
        }
        return null;
    }

    private RecruitMppApplyModel convertEntityToModel(RecruitMppApply recruitMppApply) throws Exception {
        HrmUser user = hrmUserService.getUserWithDetail(selectedApprovalActivity.getRequestBy());
        EmpData employeeApplier = empDataService.getByIdWithDetail(user.getEmpData().getId());
        RecruitMppPeriod recruitMppPeriod = recruitMppPeriodService.getEntiyByPK(recruitMppApply.getRecruitMppPeriod().getId());

        RecruitMppApplyModel recruitMppApplyModel = new RecruitMppApplyModel();
        recruitMppApplyModel.setApplyDate(recruitMppApply.getApplyDate());
        recruitMppApplyModel.setApprovalStatus(selectedApprovalActivity.getApprovalStatus());
        recruitMppApplyModel.setMppPeriodId(recruitMppApply.getRecruitMppPeriod().getId());
        recruitMppApplyModel.setReason(recruitMppApply.getReason());
        recruitMppApplyModel.setRecruitMppApplyCode(recruitMppApply.getRecruitMppApplyCode());
        recruitMppApplyModel.setRecruitMppApplyName(recruitMppApply.getRecruitMppApplyName());
        recruitMppApplyModel.setSelectedRecruitMppPeriod(recruitMppPeriod);
        recruitMppApplyModel.setUploadPath(recruitMppApply.getAttachmentDocPath());
        recruitMppApplyModel.setEmpDataApplier(employeeApplier);

        if (StringUtils.isNotEmpty(recruitMppApply.getAttachmentDocPath())) {
            mppApplyFile = recruitMppApplyService.convertFileToUploadedFile(recruitMppApply.getAttachmentDocPath());
            recruitMppApplyModel.setRecruitMppApplyFileName(mppApplyFile.getFileName());
        }

        return recruitMppApplyModel;
    }

    private RecruitMppApplyModel convertJsonToModel(String jsonData) throws Exception {

        RecruitMppApplyModel model = null;
        Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();

        JsonParser parser = new JsonParser();
        JsonObject jsonObject = (JsonObject) parser.parse(jsonData);
        RecruitMppApply recruitMppApply = gson.fromJson(jsonObject, RecruitMppApply.class);
        model = convertEntityToModel(recruitMppApply);

        JsonArray arrayDetailRecruitMppApplyRequest = jsonObject.getAsJsonArray("listMppDetail");
        List<RecruitMppApplyDetail> listMppApplyDetail = new ArrayList<>();

        if (null != arrayDetailRecruitMppApplyRequest) {
            for (int i = 0; i < arrayDetailRecruitMppApplyRequest.size(); i++) {
                RecruitMppApplyDetail detail = gson.fromJson(arrayDetailRecruitMppApplyRequest.get(i), RecruitMppApplyDetail.class);
                listMppApplyDetail.add(detail);
            }
        }

        model.setListMppDetail(listMppApplyDetail);
        return model;
    }

    public void setRecruitMppPeriodService(RecruitMppPeriodService recruitMppPeriodService) {
        this.recruitMppPeriodService = recruitMppPeriodService;
    }

    public void setRecruitMppApplyService(RecruitMppApplyService recruitMppApplyService) {
        this.recruitMppApplyService = recruitMppApplyService;
    }

    public RecruitMppApplyModel getModel() {
        return model;
    }

    public void setModel(RecruitMppApplyModel model) {
        this.model = model;
    }

    public void setEmpDataService(EmpDataService empDataService) {
        this.empDataService = empDataService;
    }

    public void setHrmUserService(HrmUserService hrmUserService) {
        this.hrmUserService = hrmUserService;
    }

    public String getIdApprovalActivity() {
        return idApprovalActivity;
    }

    public void setIdApprovalActivity(String idApprovalActivity) {
        this.idApprovalActivity = idApprovalActivity;
    }

    public ApprovalActivity getSelectedApprovalActivity() {
        return selectedApprovalActivity;
    }

    public void setSelectedApprovalActivity(ApprovalActivity selectedApprovalActivity) {
        this.selectedApprovalActivity = selectedApprovalActivity;
    }

    public void setApprovalActivityService(ApprovalActivityService approvalActivityService) {
        this.approvalActivityService = approvalActivityService;
    }

    public Boolean getIsWaitingApproval() {
        return isWaitingApproval;
    }

    public void setIsWaitingApproval(Boolean isWaitingApproval) {
        this.isWaitingApproval = isWaitingApproval;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public UploadedFile getMppApplyFile() {
        return mppApplyFile;
    }

    public void setMppApplyFile(UploadedFile mppApplyFile) {
        this.mppApplyFile = mppApplyFile;
    }

}
