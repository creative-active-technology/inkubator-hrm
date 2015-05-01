/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.recruitment;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.RecruitMppApply;
import com.inkubator.hrm.entity.RecruitMppApplyDetail;
import com.inkubator.hrm.entity.RecruitMppPeriod;
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
    private  RecruitMppApplyService recruitMppApplyService;
    @ManagedProperty(value = "#{recruitMppPeriodService}")
    private RecruitMppPeriodService recruitMppPeriodService;
    @ManagedProperty(value = "#{recruitMppApplyDetailService}")
    private RecruitMppApplyDetailService recruitMppApplyDetailService;
    @ManagedProperty(value = "#{uploadFilesUtil}")
    private UploadFilesUtil uploadFilesUtil;
    private List<RecruitMppApplyDetail> listMppDetail;
    private Map<String, Long> mapMppPeriode = new HashMap<>();
    private Long mppPeriodeId;
    private RecruitMppApplyDetail selectedRecruitMppApplyDetail;
    private UploadedFile mppApplyFile;
    
    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try {
            String param = FacesUtil.getRequestParameter("param");
            recruitMppApplyModel = new RecruitMppApplyModel();
            isUpdate = Boolean.FALSE;
            listMppDetail = new ArrayList<>();
            
            List<RecruitMppPeriod> mppPeriodeList = recruitMppPeriodService.getAllData();
            for (RecruitMppPeriod mppPeriod : mppPeriodeList) {
                mapMppPeriode.put(mppPeriod.getName(), mppPeriod.getId());
            }
            
            if (StringUtils.isNumeric(param)) {
                RecruitMppApply recruitMppApply = recruitMppApplyService.getEntityWithDetailById(Long.parseLong(param));
                if (recruitMppApply != null) {
                    recruitMppApplyModel.setId(recruitMppApply.getId());
                    recruitMppApplyModel.setRecruitMppApplyCode(recruitMppApply.getRecruitMppApplyCode());
                    recruitMppApplyModel.setRecruitMppApplyName(recruitMppApply.getRecruitMppApplyName());
                    recruitMppApplyModel.setApplyDate(recruitMppApply.getApplyDate());
                    recruitMppApplyModel.setReason(recruitMppApply.getReason());
                    recruitMppApplyModel.setUploadPath(recruitMppApply.getAttachmentDocPath());
                    recruitMppApplyModel.setMppPeriodId(recruitMppApply.getRecruitMppPeriod().getId());
                    
                    isUpdate = Boolean.TRUE;                    
                    mppPeriodeId = recruitMppApply.getRecruitMppPeriod().getId();
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

    
    public void doSave() {

        try {
            RecruitMppApply recruitMppApply = getEntityFromViewModel(recruitMppApplyModel);
            if (isUpdate) {
                recruitMppApplyService.updateRecruitMppApplytWithApproval(recruitMppApply, listMppDetail, mppApplyFile);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                recruitMppApplyService.saveRecruitMppApplytWithApproval(recruitMppApply, listMppDetail, mppApplyFile);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch (BussinessException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }
    
    public void updateMppPeriod(){
        try{
            System.out.println("mppPeriodeId : " + mppPeriodeId);
            RecruitMppPeriod period = recruitMppPeriodService.getEntiyByPK(mppPeriodeId);
            recruitMppApplyModel.setSelectedRecruitMppPeriod(period);
            
        }catch(Exception e){
            LOGGER.error("Error", e);
        }
    }

    private RecruitMppApply getEntityFromViewModel(RecruitMppApplyModel recruitMppApplyModel) throws Exception {
        RecruitMppApply recruitMppApply = new RecruitMppApply();
        if (recruitMppApply.getId() != null) {
            recruitMppApply.setId(recruitMppApplyModel.getId());
        }
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
    
    public void doSelectRecruitMppApplyDetail() {
        try {
            selectedRecruitMppApplyDetail = recruitMppApplyDetailService.getEntiyByPK(selectedRecruitMppApplyDetail.getId());
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }
    
    
    public void doDeleteRecruitMppApplyDetail() {
        try {
            
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error when doDelete bioEmploymentHistory", ex);
        }
    }
    
    public void doEditRecruitMppApplyDetail() {

        List<String> bioEmploymentHistoryId = new ArrayList<>();
        bioEmploymentHistoryId.add(String.valueOf(selectedRecruitMppApplyDetail.getId()));

//        List<String> bioDataId = new ArrayList<>();
//        bioDataId.add(String.valueOf(selectedBioData.getId()));

        Map<String, List<String>> dataToSend = new HashMap<>();
        dataToSend.put("bioEmploymentHistoryId", bioEmploymentHistoryId);
        //dataToSend.put("bioDataId", bioDataId);
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
        mppApplyPeriod.add(String.valueOf("true"));

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
        options.put("contentWidth", 550);
        options.put("contentHeight", 450);
        RequestContext.getCurrentInstance().openDialog("mpp_apply_detail_form", options, params);
//        bio_emp_hist_form
    }
    
     public void onDialogReturnRecruitMppApplyDetail(SelectEvent event) {
        try {
            
            RecruitMppApplyDetail recruitMppApplyDetail = (RecruitMppApplyDetail) event.getObject();          
            listMppDetail.add(recruitMppApplyDetail);            
            
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
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

    public void setRecruitMppApplyDetailService(RecruitMppApplyDetailService recruitMppApplyDetailService) {
        this.recruitMppApplyDetailService = recruitMppApplyDetailService;
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
    
    
}
