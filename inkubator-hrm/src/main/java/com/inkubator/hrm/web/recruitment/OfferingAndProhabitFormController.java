/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.recruitment;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DualListModel;

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
import com.inkubator.hrm.util.ResourceBundleUtil;
import com.inkubator.hrm.web.model.OfferingAndProhabitModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

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
    private String warningMessage;

    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            List<RecruitSelectionType> sourceDualLis = recruitSelectionTypeService.getAllData();
            List<RecruitCommChannels> sourceDualLisCom = recruitCommChannelsService.getAllData();

            String offeringLetterId = FacesUtil.getRequestParameter("execution");
            if (offeringLetterId != null) {
                isEdit = Boolean.TRUE;
                RecruitLetters recruitLetters = recruitLettersService.getByPkWithDetail(Long.parseLong(offeringLetterId.substring(1)));
                offeringAndProhabitModel = getModelFromEntity(recruitLetters);
                List<RecruitSelectionType> targetSelectionType = recruitLetters.getRecruitSelectionTypes();
                List<RecruitCommChannels> recruitCommChannelss = recruitLetters.getRecruitCommChannelss();
                sourceDualLis.removeAll(targetSelectionType);
                sourceDualLisCom.removeAll(recruitCommChannelss);
                dualListModel = new DualListModel<>(sourceDualLis, targetSelectionType);
                dualListModelCom = new DualListModel<>(sourceDualLisCom, recruitCommChannelss);
            } else {
                isEdit = Boolean.FALSE;
                offeringAndProhabitModel = new OfferingAndProhabitModel();
                offeringAndProhabitModel.setExpiryDays(1);
                dualListModel.setSource(sourceDualLis);
                dualListModelCom.setSource(sourceDualLisCom);
            }
            warningMessage = "";
        } catch (Exception ex) {
            LOGGER.error(ex, ex);
        }
    }

    @PreDestroy
    private void cleanAndExit() {
        dualListModel = null;
        offeringAndProhabitModel = null;
        recruitSelectionTypeService = null;
        recruitCommChannelsService = null;
        recruitLettersService = null;
        dualListModelCom = null;
        empDataService = null;
        isEdit = null;

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

    public void doWarningNullSignature(SelectEvent e){
    	EmpData empData = (EmpData) e.getObject();
    	System.out.println(empData.getNik());
    	if(offeringAndProhabitModel.getEmpData().getBioData().getPathSignature() == null){
    		warningMessage = ResourceBundleUtil.getAsString("offeringLetter.warning_null_signature");
    	}else{
    		warningMessage = "Ok";
    	}
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

    public void doGetValue(){
    	System.out.println(offeringAndProhabitModel.getCode() + " hahihuheho");
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
        recruitLetters.setLeterTypeId(Integer.parseInt(model.getLeterTypeId()));
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
            List<Long> data = new ArrayList<>();

            for (RecruitSelectionType rst : sourceDSelectType) {
                RecruitLetterSelection recruitLetterSelection = new RecruitLetterSelection();
                recruitLetterSelection.setId(new RecruitLetterSelectionId(letters.getId(), rst.getId()));
                recruitLetterSelection.setRecruitLetters(letters);
                recruitLetterSelection.setRecruitSelectionType(rst);
                recruitLetterSelections.add(recruitLetterSelection);
                data.add(rst.getId());
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
            System.out.println("dosave");
            recruitLettersService.save(letters);
            System.out.println("abis dosave");
            MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            return "/protected/recruitment/offering_prohabit_detail.htm?faces-redirect=true&execution=e" + letters.getId();
        } catch (BussinessException ex) {
        	System.out.println("error business");
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
        	System.out.println("error exception");
            LOGGER.error("Error", ex);
        }
        return null;
    }

    private String doUpdate(RecruitLetters letters) {
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
        try {
            recruitLettersService.update(letters);
            MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.update_successfully",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            return "/protected/recruitment/offering_prohabit_detail.htm?faces-redirect=true&execution=e" + letters.getId();
        } catch (BussinessException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
        return null;
    }

    public void setRecruitLettersService(RecruitLettersService recruitLettersService) {
        this.recruitLettersService = recruitLettersService;
    }

    private OfferingAndProhabitModel getModelFromEntity(RecruitLetters letters) {
        OfferingAndProhabitModel model = new OfferingAndProhabitModel();
        model.setCode(letters.getCode());
        model.setContent(letters.getContentHtml());
        model.setEmpData(letters.getEmpData());
        model.setExpiryDays(letters.getExpiryDays());
        model.setFormatLetterNumber(letters.getFormatNumber());
        model.setId(letters.getId());
        model.setIsActive(letters.getIsActive());
        model.setIsSendingViaSMS(letters.getSmsNotif());
        model.setLeterTypeId(String.valueOf(letters.getLeterTypeId()));
        return model;
    }

    public String doBack() {
        return "/protected/recruitment/offering_letter_view.htm?faces-redirect=true";
    }

	public String getWarningMessage() {
		return warningMessage;
	}

	public void setWarningMessage(String warningMessage) {
		this.warningMessage = warningMessage;
	}
    
    
}
