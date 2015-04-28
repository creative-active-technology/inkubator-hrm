/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.model;

import java.io.Serializable;

/**
 *
 * @author Deni
 */
public class RecruitmenSelectionSeriesDetailModel implements Serializable {

    private Long id;
    private Long recruitmenSelectionSeriesId;
    private Long recruitSelectionTypeId;
    private Long systemLetterReferenceByRejectLetterId;
    private Long systemLetterReferenceByAcceptLetterId;
    private String note;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRecruitmenSelectionSeriesId() {
        return recruitmenSelectionSeriesId;
    }

    public void setRecruitmenSelectionSeriesId(Long recruitmenSelectionSeriesId) {
        this.recruitmenSelectionSeriesId = recruitmenSelectionSeriesId;
    }

    public Long getSystemLetterReferenceByRejectLetterId() {
        return systemLetterReferenceByRejectLetterId;
    }

    public void setSystemLetterReferenceByRejectLetterId(Long systemLetterReferenceByRejectLetterId) {
        this.systemLetterReferenceByRejectLetterId = systemLetterReferenceByRejectLetterId;
    }

    public Long getSystemLetterReferenceByAcceptLetterId() {
        return systemLetterReferenceByAcceptLetterId;
    }

    public void setSystemLetterReferenceByAcceptLetterId(Long systemLetterReferenceByAcceptLetterId) {
        this.systemLetterReferenceByAcceptLetterId = systemLetterReferenceByAcceptLetterId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Long getRecruitSelectionTypeId() {
        return recruitSelectionTypeId;
    }

    public void setRecruitSelectionTypeId(Long recruitSelectionTypeId) {
        this.recruitSelectionTypeId = recruitSelectionTypeId;
    }

    
}
