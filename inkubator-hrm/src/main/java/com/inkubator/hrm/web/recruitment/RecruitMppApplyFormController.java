/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.recruitment;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.Currency;
import com.inkubator.hrm.entity.LoanNewType;
import com.inkubator.hrm.entity.RecruitMppApply;
import com.inkubator.hrm.entity.RecruitMppPeriod;

import com.inkubator.hrm.service.RecruitMppApplyService;
import com.inkubator.hrm.service.RecruitMppPeriodService;
import com.inkubator.hrm.web.model.LoanNewTypeModel;
import com.inkubator.hrm.web.model.RecruitMppApplyModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;

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
    private Map<String, Long> mapMppPeriode = new HashMap<String, Long>();
    private Long mppPeriodeId;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try {
            String param = FacesUtil.getRequestParameter("param");
            recruitMppApplyModel = new RecruitMppApplyModel();
            isUpdate = Boolean.FALSE;
            
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


    public void doSave() {

        try {
            RecruitMppApply recruitMppApply = getEntityFromViewModel(recruitMppApplyModel);
            if (isUpdate) {
                recruitMppApplyService.update(recruitMppApply);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                recruitMppApplyService.save(recruitMppApply);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch (BussinessException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
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
}
