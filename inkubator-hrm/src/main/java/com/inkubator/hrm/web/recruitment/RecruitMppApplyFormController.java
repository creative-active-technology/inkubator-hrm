/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.recruitment;

import ch.lambdaj.Lambda;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.ApprovalActivity;
import com.inkubator.hrm.entity.RecruitMppApply;
import com.inkubator.hrm.entity.RecruitMppApplyDetail;
import com.inkubator.hrm.entity.RecruitMppPeriod;
import com.inkubator.hrm.json.util.JsonUtil;
import com.inkubator.hrm.service.ApprovalActivityService;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.RecruitMppApplyService;
import com.inkubator.hrm.service.RecruitMppPeriodService;
import com.inkubator.hrm.service.RecruitMppApplyDetailService;
import com.inkubator.hrm.util.UploadFilesUtil;
import com.inkubator.hrm.web.model.RecruitMppApplyModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
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
import org.apache.commons.lang3.StringUtils;
import org.hamcrest.Matchers;
import org.hibernate.exception.ConstraintViolationException;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.dao.DataIntegrityViolationException;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
@ManagedBean(name = "recruitMppApplyFormController")
@ViewScoped
public class RecruitMppApplyFormController extends BaseController {

    private RecruitMppApplyModel recruitMppApplyModel;
    private Boolean isUpdate;
    private Boolean rounding;
    @ManagedProperty(value = "#{recruitMppApplyService}")
    private RecruitMppApplyService recruitMppApplyService;
    @ManagedProperty(value = "#{recruitMppPeriodService}")
    private RecruitMppPeriodService recruitMppPeriodService;   
    @ManagedProperty(value = "#{approvalActivityService}")
    private ApprovalActivityService approvalActivityService;
    @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;
    @ManagedProperty(value = "#{uploadFilesUtil}")
    private UploadFilesUtil uploadFilesUtil;
    private List<RecruitMppApplyDetail> listMppDetail;
    private RecruitMppApplyDetail selectedRecruitMppApplyDetail;
    private Map<String, Long> mapMppPeriode = new HashMap<>();
    private Long mppPeriodeId;
    private String activityNumber = StringUtils.EMPTY;    
    private UploadedFile mppApplyFile;
    private ApprovalActivity selectedApprovalActivity;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try {

            activityNumber = FacesUtil.getRequestParameter("execution");
            recruitMppApplyModel = new RecruitMppApplyModel();
            isUpdate = Boolean.FALSE;
            listMppDetail = new ArrayList<>();

            List<RecruitMppPeriod> mppPeriodeList = recruitMppPeriodService.getAllData();
            for (RecruitMppPeriod mppPeriod : mppPeriodeList) {
                mapMppPeriode.put(mppPeriod.getName(), mppPeriod.getId());
            }
            
            //if activityNumber is not empty, it means to edit existing MPP process
            if (StringUtils.isNotBlank(activityNumber)) {
                
                selectedApprovalActivity = approvalActivityService.getEntityByActivityNumberLastSequence(activityNumber);
                if (null != selectedApprovalActivity) {

                    isUpdate = Boolean.TRUE;
                    
                    //Make sure only process who have not been approved that can be modified.
                    if (selectedApprovalActivity.getApprovalStatus() != HRMConstant.APPROVAL_STATUS_APPROVED) {
                        recruitMppApplyModel = convertJsonToModel(selectedApprovalActivity.getPendingData());
                        mppPeriodeId = recruitMppApplyModel.getMppPeriodId();
                    }

                }

            }
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    @PreDestroy
    public void cleanAndExit() {
        recruitMppApplyService = null;
        recruitMppPeriodService = null;
        mppPeriodeId = null;
        recruitMppApplyModel = null;
        isUpdate = null;
    }

    public String doSave() {

        try {
            String result = StringUtils.EMPTY;
            RecruitMppApply recruitMppApply = getEntityFromViewModel(recruitMppApplyModel);

            if (isUpdate) {
                result = recruitMppApplyService.updateRecruitMppApplytWithApproval(recruitMppApply, listMppDetail, mppApplyFile, activityNumber);
            } else {
                result = recruitMppApplyService.saveRecruitMppApplytWithApproval(recruitMppApply, listMppDetail, mppApplyFile);
            }
            cleanAndExit();

            if (StringUtils.equals(result, "success_need_approval")) {
                MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully_and_requires_approval", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            } else {
                MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            }

            return "/protected/recruitment/recruit_mpp_apply_view.htm?faces-redirect=true";
        } catch (BussinessException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
        return null;
    }

    public void updateMppPeriod() {
        try {
            RecruitMppPeriod period = recruitMppPeriodService.getEntiyByPK(mppPeriodeId);
            recruitMppApplyModel.setSelectedRecruitMppPeriod(period);

        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    private RecruitMppApply getEntityFromViewModel(RecruitMppApplyModel recruitMppApplyModel) throws Exception {
        RecruitMppApply recruitMppApply = new RecruitMppApply();

        recruitMppApply.setRecruitMppApplyCode(recruitMppApplyModel.getRecruitMppApplyCode());
        recruitMppApply.setRecruitMppApplyName(recruitMppApplyModel.getRecruitMppApplyName());
        RecruitMppPeriod recruitMppPeriod = recruitMppPeriodService.getEntiyByPK(mppPeriodeId);
        recruitMppApply.setRecruitMppPeriod(recruitMppPeriod);
        recruitMppApply.setApplyDate(recruitMppApplyModel.getApplyDate());
        recruitMppApply.setAttachmentDocPath(recruitMppApplyModel.getUploadPath());
        recruitMppApply.setReason(recruitMppApplyModel.getReason());

        return recruitMppApply;
    }

    public void handleFileUpload(FileUploadEvent fileUploadEvent) {
        Map<String, String> results = uploadFilesUtil.checkUploadFileSizeLimit(fileUploadEvent.getFile());
        if (StringUtils.equals(results.get("result"), "true")) {
            mppApplyFile = fileUploadEvent.getFile();
            recruitMppApplyModel.setRecruitMppApplyFileName(mppApplyFile.getFileName());

        } else {
            ResourceBundle messages = ResourceBundle.getBundle("Messages", new Locale(FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString()));
            String errorMsg = messages.getString("global.file_size_should_not_bigger_than") + " " + results.get("sizeMax");
            MessagesResourceUtil.setMessagesFromException(FacesMessage.SEVERITY_ERROR, "global.error", errorMsg, FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        }
    }

    public void doSelectRecruitMppApplyDetail(RecruitMppApplyDetail data) {
        try {            
            selectedRecruitMppApplyDetail = data;           
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    public void doDeleteRecruitMppApplyDetail() {
        try {
            listMppDetail.remove(selectedRecruitMppApplyDetail);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error when doDelete recruitMppApplyDetail", ex);
        }
    }

    public void doEditRecruitMppApplyDetail() {
       
        List<String> mppApplyCode = new ArrayList<>();
        mppApplyCode.add(recruitMppApplyModel.getRecruitMppApplyCode());

        List<String> mppApplyName = new ArrayList<>();
        mppApplyName.add(recruitMppApplyModel.getRecruitMppApplyName());

        List<String> mppApplyPeriod = new ArrayList<>();
        mppApplyPeriod.add(String.valueOf(mppPeriodeId));

        List<String> jabatan = new ArrayList<>();
        jabatan.add(String.valueOf(selectedRecruitMppApplyDetail.getJabatan().getId()));

        List<String> departemen = new ArrayList<>();
        departemen.add(String.valueOf(selectedRecruitMppApplyDetail.getJabatan().getDepartment().getId()));

        List<String> idDetail = new ArrayList<>();
        idDetail.add(String.valueOf(selectedRecruitMppApplyDetail.getId()));

        List<String> numberOfActualPosition = new ArrayList<>();
        numberOfActualPosition.add(String.valueOf(selectedRecruitMppApplyDetail.getActualNumber()));

        List<String> numberOfPlanningPosition = new ArrayList<>();
        numberOfPlanningPosition.add(String.valueOf(selectedRecruitMppApplyDetail.getRecruitPlan()));

        List<String> isUpdate = new ArrayList<>();
        isUpdate.add(String.valueOf("true"));

        Map<String, List<String>> dataToSend = new HashMap<>();
        dataToSend.put("mppApplyCode", mppApplyCode);
        dataToSend.put("mppApplyName", mppApplyName);
        dataToSend.put("mppApplyPeriod", mppApplyPeriod);
        dataToSend.put("isUpdate", isUpdate);
        dataToSend.put("idDetail", idDetail);
        dataToSend.put("jabatan", jabatan);
        dataToSend.put("departemen", departemen);
        dataToSend.put("numberOfActualPosition", numberOfActualPosition);
        dataToSend.put("numberOfPlanningPosition", numberOfPlanningPosition);

        showDialogRecruitMppApplyDetail(dataToSend);

    }

    public void doAddRecruitMppApplyDetail() {
        List<String> mppApplyCode = new ArrayList<>();
        mppApplyCode.add(recruitMppApplyModel.getRecruitMppApplyCode());

        List<String> mppApplyName = new ArrayList<>();
        mppApplyName.add(recruitMppApplyModel.getRecruitMppApplyName());

        List<String> mppApplyPeriod = new ArrayList<>();
        mppApplyPeriod.add(String.valueOf(mppPeriodeId));

        List<String> isUpdate = new ArrayList<>();
        isUpdate.add(String.valueOf("false"));

        Map<String, List<String>> dataToSend = new HashMap<>();
        dataToSend.put("mppApplyCode", mppApplyCode);
        dataToSend.put("mppApplyName", mppApplyName);
        dataToSend.put("mppApplyPeriod", mppApplyPeriod);
        dataToSend.put("isUpdate", isUpdate);
        showDialogRecruitMppApplyDetail(dataToSend);
    }

    private void showDialogRecruitMppApplyDetail(Map<String, List<String>> params) {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 500);
        options.put("contentHeight", 380);
        RequestContext.getCurrentInstance().openDialog("mpp_apply_detail_form", options, params);
    }

    public void onDialogReturnRecruitMppApplyDetail(SelectEvent event) {
        try {

            RecruitMppApplyDetail recruitMppApplyDetail = (RecruitMppApplyDetail) event.getObject();
            List<Long> listIdDetail = Lambda.extract(listMppDetail, Lambda.on(RecruitMppApplyDetail.class).getId());
            
            // if id is already exist, it means that this is update process
            if (listIdDetail.contains(recruitMppApplyDetail.getId())) {               
                int index = listMppDetail.indexOf(Lambda.selectFirst(listMppDetail, Lambda.having(Lambda.on(RecruitMppApplyDetail.class).getId(), Matchers.equalTo(recruitMppApplyDetail.getId()))));                
                
                // remove old object of RecruitMppApplyDetail  and replace with new updated one
                if (index != -1) {
                    listMppDetail.remove(index);
                    listMppDetail.add(index, recruitMppApplyDetail);
                }

            } else {   
                // if id doesn't exist, it means that this is process of create new RecruitMppApplyDetail
                listMppDetail.add(recruitMppApplyDetail);
            }

        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    public String doBack() {       
        cleanAndExit();
        return "/protected/recruitment/recruit_mpp_apply_view.htm?faces-redirect=true";
    }

    private RecruitMppApplyModel convertJsonToModel(String jsonData) throws Exception {
        RecruitMppApplyModel model = null;
        Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();

        JsonParser parser = new JsonParser();
        JsonObject jsonObject = (JsonObject) parser.parse(jsonData);
        RecruitMppApply recruitMppApply = gson.fromJson(jsonObject, RecruitMppApply.class);
        model = convertEntityToModel(recruitMppApply);
        JsonArray arrayDetailMpp = jsonObject.getAsJsonArray("listMppDetail");

        if (StringUtils.isNotEmpty(recruitMppApply.getAttachmentDocPath())) {
            mppApplyFile = recruitMppApplyService.convertFileToUploadedFile(recruitMppApply.getAttachmentDocPath());
            model.setRecruitMppApplyFileName(mppApplyFile.getFileName());
        }

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

        return model;
    }

    public RecruitMppApplyModel getRecruitMppApplyModel() {
        return recruitMppApplyModel;
    }

    public void setRecruitMppApplyModel(RecruitMppApplyModel recruitMppApplyModel) {
        this.recruitMppApplyModel = recruitMppApplyModel;
    }

    public Map<String, Long> getMapMppPeriode() {
        return mapMppPeriode;
    }

    public void setMapMppPeriode(Map<String, Long> mapMppPeriode) {
        this.mapMppPeriode = mapMppPeriode;
    }

    public Long getMppPeriodeId() {
        return mppPeriodeId;
    }

    public void setMppPeriodeId(Long mppPeriodeId) {
        this.mppPeriodeId = mppPeriodeId;
    }

    public Boolean getRounding() {
        return rounding;
    }

    public void setRounding(Boolean rounding) {
        this.rounding = rounding;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
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

    public void setUploadFilesUtil(UploadFilesUtil uploadFilesUtil) {
        this.uploadFilesUtil = uploadFilesUtil;
    }

    public String getActivityNumber() {
        return activityNumber;
    }

    public void setActivityNumber(String activityNumber) {
        this.activityNumber = activityNumber;
    }

    public UploadedFile getMppApplyFile() {
        return mppApplyFile;
    }

    public void setMppApplyFile(UploadedFile mppApplyFile) {
        this.mppApplyFile = mppApplyFile;
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

    public EmpDataService getEmpDataService() {
        return empDataService;
    }

    public void setEmpDataService(EmpDataService empDataService) {
        this.empDataService = empDataService;
    }

}
