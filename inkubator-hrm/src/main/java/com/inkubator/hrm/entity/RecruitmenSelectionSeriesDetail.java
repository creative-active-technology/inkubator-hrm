package com.inkubator.hrm.entity;
// Generated Apr 15, 2015 4:13:35 PM by Hibernate Tools 4.3.1


import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 * RecruitmenSelectionSeriesDetail generated by hbm2java
 */
@Entity
@Table(name="recruitmen_selection_series_detail"
    ,catalog="hrm"
)
public class RecruitmenSelectionSeriesDetail  implements java.io.Serializable {

     private RecruitmenSelectionSeriesDetailId id;
     private Integer version;
     private RecruitLetters recruitLettersByAcceptLetterId;
     private RecruitLetters recruitLettersByRejectLetterId;
     private RecruitLetters recruitLettersBySchedulleLetterId;
     private RecruitSelectionType recruitSelectionType;
     private RecruitmenSelectionSeries recruitmenSelectionSeries;
     private String note;
     private String createdBy;
     private Date createdOn;
     private String updatedOn;
     private Date updatedBy;
     private Integer listOrder;

    public RecruitmenSelectionSeriesDetail() {
    }

	
    public RecruitmenSelectionSeriesDetail(RecruitmenSelectionSeriesDetailId id, RecruitSelectionType recruitSelectionType, RecruitmenSelectionSeries recruitmenSelectionSeries) {
        this.id = id;
        this.recruitSelectionType = recruitSelectionType;
        this.recruitmenSelectionSeries = recruitmenSelectionSeries;
    }
    public RecruitmenSelectionSeriesDetail(RecruitmenSelectionSeriesDetailId id, RecruitLetters recruitLettersByAcceptLetterId, RecruitLetters recruitLettersByRejectLetterId, RecruitSelectionType recruitSelectionType, RecruitmenSelectionSeries recruitmenSelectionSeries, String note, String createdBy, Date createdOn, String updatedOn, Date updatedBy, Integer listOrder) {
       this.id = id;
       this.recruitLettersByAcceptLetterId = recruitLettersByAcceptLetterId;
       this.recruitLettersByRejectLetterId = recruitLettersByRejectLetterId;
       this.recruitSelectionType = recruitSelectionType;
       this.recruitmenSelectionSeries = recruitmenSelectionSeries;
       this.note = note;
       this.createdBy = createdBy;
       this.createdOn = createdOn;
       this.updatedOn = updatedOn;
       this.updatedBy = updatedBy;
       this.listOrder = listOrder;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="recruitmenSelectionSeriesId", column=@Column(name="recruitmen_selection_series_id", nullable=false) ), 
        @AttributeOverride(name="selectionTypeId", column=@Column(name="selection_type_id", nullable=false) ) } )
    public RecruitmenSelectionSeriesDetailId getId() {
        return this.id;
    }
    
    public void setId(RecruitmenSelectionSeriesDetailId id) {
        this.id = id;
    }

    @Version
    @Column(name="version")
    public Integer getVersion() {
        return this.version;
    }
    
    public void setVersion(Integer version) {
        this.version = version;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="accept_letter_id")
    public RecruitLetters getRecruitLettersByAcceptLetterId() {
        return this.recruitLettersByAcceptLetterId;
    }
    
    public void setRecruitLettersByAcceptLetterId(RecruitLetters recruitLettersByAcceptLetterId) {
        this.recruitLettersByAcceptLetterId = recruitLettersByAcceptLetterId;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="reject_letter_id")
    public RecruitLetters getRecruitLettersByRejectLetterId() {
        return this.recruitLettersByRejectLetterId;
    }
    
    public void setRecruitLettersByRejectLetterId(RecruitLetters recruitLettersByRejectLetterId) {
        this.recruitLettersByRejectLetterId = recruitLettersByRejectLetterId;
    }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="schedule_letter_id")
	public RecruitLetters getRecruitLettersBySchedulleLetterId() {
		return recruitLettersBySchedulleLetterId;
	}


	public void setRecruitLettersBySchedulleLetterId(RecruitLetters recruitLettersBySchedulleLetterId) {
		this.recruitLettersBySchedulleLetterId = recruitLettersBySchedulleLetterId;
	}
	
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="selection_type_id", nullable=false, insertable=false, updatable=false)
    public RecruitSelectionType getRecruitSelectionType() {
        return this.recruitSelectionType;
    }
    
    public void setRecruitSelectionType(RecruitSelectionType recruitSelectionType) {
        this.recruitSelectionType = recruitSelectionType;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="recruitmen_selection_series_id", nullable=false, insertable=false, updatable=false)
    public RecruitmenSelectionSeries getRecruitmenSelectionSeries() {
        return this.recruitmenSelectionSeries;
    }
    
    public void setRecruitmenSelectionSeries(RecruitmenSelectionSeries recruitmenSelectionSeries) {
        this.recruitmenSelectionSeries = recruitmenSelectionSeries;
    }

    
    @Column(name="note")
    public String getNote() {
        return this.note;
    }
    
    public void setNote(String note) {
        this.note = note;
    }

    
    @Column(name="created_by", length=45)
    public String getCreatedBy() {
        return this.createdBy;
    }
    
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_on", length=19)
    public Date getCreatedOn() {
        return this.createdOn;
    }
    
    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    
    @Column(name="updated_on", length=45)
    public String getUpdatedOn() {
        return this.updatedOn;
    }
    
    public void setUpdatedOn(String updatedOn) {
        this.updatedOn = updatedOn;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="updated_by", length=19)
    public Date getUpdatedBy() {
        return this.updatedBy;
    }
    
    public void setUpdatedBy(Date updatedBy) {
        this.updatedBy = updatedBy;
    }

    
    @Column(name="list_order")
    public Integer getListOrder() {
        return this.listOrder;
    }
    
    public void setListOrder(Integer listOrder) {
        this.listOrder = listOrder;
    }







}


