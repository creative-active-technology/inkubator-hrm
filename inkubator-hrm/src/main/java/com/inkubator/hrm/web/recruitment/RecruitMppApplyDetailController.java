/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.recruitment;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.ApprovalActivity;
import com.inkubator.hrm.entity.Jabatan;
import com.inkubator.hrm.entity.RecruitMppApply;
import com.inkubator.hrm.entity.RecruitMppApplyDetail;
import com.inkubator.hrm.entity.RecruitMppPeriod;
import com.inkubator.hrm.json.util.JsonUtil;
import com.inkubator.hrm.service.ApprovalActivityService;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.JabatanService;
import com.inkubator.hrm.service.RecruitMppApplyService;
import com.inkubator.hrm.service.RecruitMppPeriodService;
import com.inkubator.hrm.service.RecruitMppApplyDetailService;
import com.inkubator.hrm.util.UploadFilesUtil;
import com.inkubator.hrm.web.model.RecruitMppApplyModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.exception.ConstraintViolationException;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.springframework.dao.DataIntegrityViolationException;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
@ManagedBean(name = "recruitMppApplyDetailController")
@ViewScoped
public class RecruitMppApplyDetailController extends BaseController {

    private RecruitMppApplyModel recruitMppApplyModel;
    private ApprovalActivity selectedApprovalActivity;
    @ManagedProperty(value = "#{recruitMppApplyService}")
    private RecruitMppApplyService recruitMppApplyService;
    @ManagedProperty(value = "#{recruitMppPeriodService}")
    private RecruitMppPeriodService recruitMppPeriodService;
    @ManagedProperty(value = "#{approvalActivityService}")
    private ApprovalActivityService approvalActivityService;
    @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;
    private List<RecruitMppApplyDetail> listMppDetail;
    private String activityNumber = StringUtils.EMPTY;
    private Long mppPeriodeId;
    private RecruitMppApplyDetail selectedRecruitMppApplyDetail;
    private UploadedFile mppApplyFile;
    private Boolean isWaitingApproval;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try {

            recruitMppApplyModel = new RecruitMppApplyModel();
            listMppDetail = new ArrayList<>();
            activityNumber = FacesUtil.getRequestParameter("execution");
            if (StringUtils.isNotEmpty(activityNumber)) {
                selectedApprovalActivity = approvalActivityService.getEntityByActivityNumberLastSequence(activityNumber);

                if (null != selectedApprovalActivity) {
                	isWaitingApproval = selectedApprovalActivity.getApprovalStatus() == HRMConstant.APPROVAL_STATUS_WAITING_APPROVAL;
                    recruitMppApplyModel = convertJsonToModel(selectedApprovalActivity.getPendingData());                   
                }
            }

        } catch (Exception e) {
            LOGGER.error("Error", e);
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
        recruitMppApplyService = null;
        recruitMppPeriodService = null;
        approvalActivityService = null;
        empDataService = null;
        mppPeriodeId = null;
        recruitMppApplyModel = null;
        selectedApprovalActivity = null;
        activityNumber = null;
        mppApplyFile = null;
        isWaitingApproval = null;
        listMppDetail = null;
        selectedRecruitMppApplyDetail = null;
    }

    public String doBack() {
        cleanAndExit();
        return "/protected/recruitment/recruit_mpp_apply_view.htm?faces-redirect=true";
    }

    public String doEdit() {
        return "/protected/recruitment/recruit_mpp_apply_form.htm?faces-redirect=true&execution=" + activityNumber;
    }

    public void updateMppPeriod() {
        try {
            RecruitMppPeriod period = recruitMppPeriodService.getEntiyByPK(mppPeriodeId);
            recruitMppApplyModel.setSelectedRecruitMppPeriod(period);

        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    private RecruitMppApplyModel convertJsonToModel(String jsonData) throws Exception {

        Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();

        JsonParser parser = new JsonParser();
        JsonObject jsonObject = (JsonObject) parser.parse(jsonData);
        RecruitMppApply recruitMppApply = gson.fromJson(jsonObject, RecruitMppApply.class);
        RecruitMppApplyModel model = convertEntityToModel(recruitMppApply);
        model.setApprovalStatus(selectedApprovalActivity.getApprovalStatus());
        
        if (StringUtils.isNotEmpty(recruitMppApply.getAttachmentDocPath())) {
            mppApplyFile = recruitMppApplyService.convertFileToUploadedFile(recruitMppApply.getAttachmentDocPath());            
            model.setRecruitMppApplyFileName(mppApplyFile.getFileName());
        }

        JsonArray arrayDetailMpp = jsonObject.getAsJsonArray("listMppDetail");
        for (int i = 0; i < arrayDetailMpp.size(); i++) {
            RecruitMppApplyDetail detail = gson.fromJson(arrayDetailMpp.get(i), RecruitMppApplyDetail.class);

            Integer actual = empDataService.getTotalKaryawanByJabatanId(detail.getJabatan().getId()).intValue();
            Integer difference = detail.getRecruitPlan() == actual ? 0 : detail.getRecruitPlan() > actual ? (detail.getRecruitPlan() - actual) : (actual - detail.getRecruitPlan());
            detail.setActualNumber(actual);
            detail.setDifference(difference);
            listMppDetail.add(detail);
        }
        return model;
    }

    private RecruitMppApplyModel convertEntityToModel(RecruitMppApply entity) throws Exception {
        RecruitMppApplyModel model = new RecruitMppApplyModel();
        model.setApplyDate(entity.getApplyDate());

        RecruitMppPeriod period = recruitMppPeriodService.getEntiyByPK(entity.getRecruitMppPeriod().getId());
        model.setMppPeriodId(period.getId());
        model.setSelectedRecruitMppPeriod(period);

        model.setRecruitMppApplyCode(entity.getRecruitMppApplyCode());
        model.setRecruitMppApplyName(entity.getRecruitMppApplyName());
        model.setReason(entity.getReason());
        model.setUploadPath(entity.getAttachmentDocPath());
        model.setRecruitMppApplyFileName(entity.getRecruitMppApplyName());

        return model;
    }


    public RecruitMppApplyModel getRecruitMppApplyModel() {
        return recruitMppApplyModel;
    }

    public void setRecruitMppApplyModel(RecruitMppApplyModel recruitMppApplyModel) {
        this.recruitMppApplyModel = recruitMppApplyModel;
    }

    public Long getMppPeriodeId() {
        return mppPeriodeId;
    }

    public void setMppPeriodeId(Long mppPeriodeId) {
        this.mppPeriodeId = mppPeriodeId;
    }

    public void setRecruitMppApplyService(RecruitMppApplyService recruitMppApplyService) {
        this.recruitMppApplyService = recruitMppApplyService;
    }

    public void setRecruitMppPeriodService(RecruitMppPeriodService recruitMppPeriodService) {
        this.recruitMppPeriodService = recruitMppPeriodService;
    }

    public List<RecruitMppApplyDetail> getListMppDetail() {
        return listMppDetail;
    }

    public void setListMppDetail(List<RecruitMppApplyDetail> listMppDetail) {
        this.listMppDetail = listMppDetail;
    }

    public RecruitMppApplyDetail getSelectedRecruitMppApplyDetail() {
        return selectedRecruitMppApplyDetail;
    }

    public void setSelectedRecruitMppApplyDetail(RecruitMppApplyDetail selectedRecruitMppApplyDetail) {
        this.selectedRecruitMppApplyDetail = selectedRecruitMppApplyDetail;
    }

    
    public void setApprovalActivityService(ApprovalActivityService approvalActivityService) {
        this.approvalActivityService = approvalActivityService;
    }

    public ApprovalActivity getSelectedApprovalActivity() {
        return selectedApprovalActivity;
    }

    public void setSelectedApprovalActivity(ApprovalActivity selectedApprovalActivity) {
        this.selectedApprovalActivity = selectedApprovalActivity;
    }

    public UploadedFile getMppApplyFile() {
        return mppApplyFile;
    }

    public void setMppApplyFile(UploadedFile mppApplyFile) {
        this.mppApplyFile = mppApplyFile;
    }

    public void setEmpDataService(EmpDataService empDataService) {
        this.empDataService = empDataService;
    }

	public Boolean getIsWaitingApproval() {
		return isWaitingApproval;
	}

	public void setIsWaitingApproval(Boolean isWaitingApproval) {
		this.isWaitingApproval = isWaitingApproval;
	}

}
