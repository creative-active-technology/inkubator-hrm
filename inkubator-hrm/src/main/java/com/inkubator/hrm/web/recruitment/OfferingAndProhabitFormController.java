/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.recruitment;

import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.RecruitCommChannels;
import com.inkubator.hrm.entity.RecruitLetterComChannel;
import com.inkubator.hrm.entity.RecruitLetterComChannelId;
import com.inkubator.hrm.entity.RecruitLetterSelection;
import com.inkubator.hrm.entity.RecruitLetterSelectionId;
import com.inkubator.hrm.entity.RecruitLetters;
import com.inkubator.hrm.entity.RecruitSelectionType;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.RecruitCommChannelsService;
import com.inkubator.hrm.service.RecruitLettersService;
import com.inkubator.hrm.service.RecruitSelectionTypeService;
import com.inkubator.hrm.util.HrmUserInfoUtil;
import com.inkubator.hrm.web.model.OfferingAndProhabitModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.DualListModel;

/**
 *
 * @author denifahri
 */
@ManagedBean(name = "offeringAndProhabitFormController")
@ViewScoped
public class OfferingAndProhabitFormController extends BaseController {

    private OfferingAndProhabitModel offeringAndProhabitModel;
    private DualListModel<RecruitSelectionType> dualListModel = new DualListModel<>();
    @ManagedProperty(value = "#{recruitSelectionTypeService}")
    private RecruitSelectionTypeService recruitSelectionTypeService;
    @ManagedProperty(value = "#{recruitCommChannelsService}")
    private RecruitCommChannelsService recruitCommChannelsService;
    @ManagedProperty(value = "#{recruitLettersService}")
    private RecruitLettersService recruitLettersService;
    private DualListModel<RecruitCommChannels> dualListModelCom = new DualListModel<>();
    @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;
    private Boolean isEdit;

    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            isEdit = Boolean.FALSE;
            offeringAndProhabitModel = new OfferingAndProhabitModel();
            offeringAndProhabitModel.setExpiryDays(1);
            List<RecruitSelectionType> sourceDualLis = recruitSelectionTypeService.getAllData();
            List<RecruitCommChannels> sourceDualLisCom = recruitCommChannelsService.getAllData();
            dualListModel.setSource(sourceDualLis);
            dualListModelCom.setSource(sourceDualLisCom);
        } catch (Exception ex) {
            LOGGER.error(ex, ex);
        }
    }

    @PreDestroy
    private void cleanAndExit() {
        dualListModel = null;
        offeringAndProhabitModel = null;
    }

    public OfferingAndProhabitModel getOfferingAndProhabitModel() {
        return offeringAndProhabitModel;
    }

    public void setOfferingAndProhabitModel(OfferingAndProhabitModel offeringAndProhabitModel) {
        this.offeringAndProhabitModel = offeringAndProhabitModel;
    }

    public DualListModel<RecruitSelectionType> getDualListModel() {
        return dualListModel;
    }

    public void setDualListModel(DualListModel<RecruitSelectionType> dualListModel) {
        this.dualListModel = dualListModel;
    }

    public void setRecruitSelectionTypeService(RecruitSelectionTypeService recruitSelectionTypeService) {
        this.recruitSelectionTypeService = recruitSelectionTypeService;
    }

    public DualListModel<RecruitCommChannels> getDualListModelCom() {
        return dualListModelCom;
    }

    public void setDualListModelCom(DualListModel<RecruitCommChannels> dualListModelCom) {
        this.dualListModelCom = dualListModelCom;
    }

    public void setRecruitCommChannelsService(RecruitCommChannelsService recruitCommChannelsService) {
        this.recruitCommChannelsService = recruitCommChannelsService;
    }

    public List<EmpData> completeEmpData(String query) {
        try {
            List<EmpData> allEmpData = empDataService.getAllDataByNameOrNik(StringUtils.stripToEmpty(query), HrmUserInfoUtil.getCompanyId());
            return allEmpData;
        } catch (Exception ex) {
            LOGGER.error(ex, ex);
        }
        return null;
    }

    public void setEmpDataService(EmpDataService empDataService) {
        this.empDataService = empDataService;
    }

    public String doSave() {
        RecruitLetters letters = getEntityFromViewModel(offeringAndProhabitModel);
        if (isEdit) {
            return doUpdate(letters);
        } else {
            return doInsert(letters);
        }
    }

    private RecruitLetters getEntityFromViewModel(OfferingAndProhabitModel model) {
        RecruitLetters recruitLetters = new RecruitLetters();
        if (model.getId() != null) {
            recruitLetters.setId(model.getId());
        }
        recruitLetters.setCode(model.getCode());
        recruitLetters.setContentHtml(model.getContent());
        recruitLetters.setEmpData(model.getEmpData());
        recruitLetters.setExpiryDays(model.getExpiryDays());
        recruitLetters.setFormatNumber(model.getFormatLetterNumber());
        recruitLetters.setIsActive(model.getIsActive());
        recruitLetters.setLeterTypeId(model.getLeterTypeId());
        recruitLetters.setSmsNotif(model.getIsActive());
        recruitLetters.setSmsNotif(model.getIsSendingViaSMS());
        return recruitLetters;
    }

    public Boolean getIsEdit() {
        return isEdit;
    }

    public void setIsEdit(Boolean isEdit) {
        this.isEdit = isEdit;
    }

    private String doInsert(RecruitLetters letters) {
        try {
            letters.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(12)));
            Set<RecruitLetterSelection> recruitLetterSelections = new HashSet<>();
            List<RecruitSelectionType> sourceDSelectType = dualListModel.getTarget();
            for (RecruitSelectionType rst : sourceDSelectType) {
                RecruitLetterSelection recruitLetterSelection = new RecruitLetterSelection();
                recruitLetterSelection.setId(new RecruitLetterSelectionId(letters.getId(), rst.getId()));
                recruitLetterSelection.setRecruitLetters(letters);
                recruitLetterSelection.setRecruitSelectionType(rst);
                recruitLetterSelections.add(recruitLetterSelection);
            }
            letters.setRecruitLetterSelections(recruitLetterSelections);
            Set<RecruitLetterComChannel> recruitLetterComChannels = new HashSet<>();
            List<RecruitCommChannels> targetRecrutiCom = dualListModelCom.getTarget();
            for (RecruitCommChannels channels : targetRecrutiCom) {
                RecruitLetterComChannel comChannel = new RecruitLetterComChannel();
                comChannel.setId(new RecruitLetterComChannelId(channels.getId(), letters.getId()));
                comChannel.setRecruitCommChannels(channels);
                comChannel.setRecruitLetters(letters);
                recruitLetterComChannels.add(comChannel);
            }
            letters.setRecruitLetterComChannels(recruitLetterComChannels);
            recruitLettersService.save(letters);
            MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            return "/protected/recruitment/offering_prohabit_detail.htm?faces-redirect=true&execution=e" + letters.getId();
        } catch (BussinessException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
        return null;
    }

    private String doUpdate(RecruitLetters letters) {
        return null;
    }

    public void setRecruitLettersService(RecruitLettersService recruitLettersService) {
        this.recruitLettersService = recruitLettersService;
    }

}
