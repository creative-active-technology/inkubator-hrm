/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.model;

import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.RecruitCommChannels;
import com.inkubator.hrm.entity.RecruitLetterComChannel;
import com.inkubator.hrm.entity.RecruitSelectionType;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author denifahri
 */
public class OfferingAndProhabitModel implements Serializable {

    private Long id;
    private List<RecruitSelectionType> listSelectionTypes;
    private String code;
    private String formatLetterNumber;
    private int expiryDays;
    private String content;
    private List<RecruitCommChannels> lsitSendingType;
    private Boolean isActive;
    private Boolean IsSendingViaSMS;
    private EmpData empData;
    private int leterTypeId;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<RecruitSelectionType> getListSelectionTypes() {
        return listSelectionTypes;
    }

    public void setListSelectionTypes(List<RecruitSelectionType> listSelectionTypes) {
        this.listSelectionTypes = listSelectionTypes;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFormatLetterNumber() {
        return formatLetterNumber;
    }

    public void setFormatLetterNumber(String formatLetterNumber) {
        this.formatLetterNumber = formatLetterNumber;
    }

    public int getExpiryDays() {
        return expiryDays;
    }

    public void setExpiryDays(int expiryDays) {
        this.expiryDays = expiryDays;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public List<RecruitCommChannels> getLsitSendingType() {
        return lsitSendingType;
    }

    public void setLsitSendingType(List<RecruitCommChannels> lsitSendingType) {
        this.lsitSendingType = lsitSendingType;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Boolean getIsSendingViaSMS() {
        return IsSendingViaSMS;
    }

    public void setIsSendingViaSMS(Boolean IsSendingViaSMS) {
        this.IsSendingViaSMS = IsSendingViaSMS;
    }

    public EmpData getEmpData() {
        return empData;
    }

    public void setEmpData(EmpData empData) {
        this.empData = empData;
    }

    public int getLeterTypeId() {
        return leterTypeId;
    }

    public void setLeterTypeId(int leterTypeId) {
        this.leterTypeId = leterTypeId;
    }


}
