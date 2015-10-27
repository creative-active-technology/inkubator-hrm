package com.inkubator.hrm.entity;
// Generated Oct 26, 2015 3:21:02 PM by Hibernate Tools 4.3.1


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 * RecruitSelectionApplicantSchedulleDetailRealization generated by hbm2java
 */
@Entity
@Table(name="recruit_selection_applicant_schedulle_detail_realization"
    ,catalog="hrm"
)
public class RecruitSelectionApplicantSchedulleDetailRealization  implements java.io.Serializable {


     private long id;
     private Integer version;
     private RecruitSelectionApplicantSchedulleDetail recruitSelectionApplicantSchedulleDetail;
     private Date realizationDate;
     private Date realizationTimeStart;
     private Date realizationTimeEnd;
     private String realizationRoom;
     private String notes;
     private Date scoringDate;
     private Double scoringPoint;
     private EmpData scoringByEmpId;
     private String status;
     private Date createdOn;
     private String createdBy;
     private Date updatedOn;
     private String updatedBy;

    public RecruitSelectionApplicantSchedulleDetailRealization() {
    }

	
    public RecruitSelectionApplicantSchedulleDetailRealization(long id) {
        this.id = id;
    }
    public RecruitSelectionApplicantSchedulleDetailRealization(long id, RecruitSelectionApplicantSchedulleDetail recruitSelectionApplicantSchedulleDetail, Date realizationDate, Date realizationTimeStart, Date realizationTimeEnd, String realizationRoom, String notes, Date scoringDate, Double scoringPoint, EmpData scoringByEmpId, Date createdOn, String createdBy, Date updatedOn, String updatedBy) {
       this.id = id;
       this.recruitSelectionApplicantSchedulleDetail = recruitSelectionApplicantSchedulleDetail;
       this.realizationDate = realizationDate;
       this.realizationTimeStart = realizationTimeStart;
       this.realizationTimeEnd = realizationTimeEnd;
       this.realizationRoom = realizationRoom;
       this.notes = notes;
       this.scoringDate = scoringDate;
       this.scoringPoint = scoringPoint;
       this.scoringByEmpId = scoringByEmpId;
       this.createdOn = createdOn;
       this.createdBy = createdBy;
       this.updatedOn = updatedOn;
       this.updatedBy = updatedBy;
    }
   
     @Id 

    
    @Column(name="id", unique=true, nullable=false)
    public long getId() {
        return this.id;
    }
    
    public void setId(long id) {
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
    @JoinColumn(name="schedulle_detail_id")
    public RecruitSelectionApplicantSchedulleDetail getRecruitSelectionApplicantSchedulleDetail() {
        return this.recruitSelectionApplicantSchedulleDetail;
    }
    
    public void setRecruitSelectionApplicantSchedulleDetail(RecruitSelectionApplicantSchedulleDetail recruitSelectionApplicantSchedulleDetail) {
        this.recruitSelectionApplicantSchedulleDetail = recruitSelectionApplicantSchedulleDetail;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="realization_date", length=10)
    public Date getRealizationDate() {
        return this.realizationDate;
    }
    
    public void setRealizationDate(Date realizationDate) {
        this.realizationDate = realizationDate;
    }

    @Temporal(TemporalType.TIME)
    @Column(name="realization_time_start", length=8)
    public Date getRealizationTimeStart() {
        return this.realizationTimeStart;
    }
    
    public void setRealizationTimeStart(Date realizationTimeStart) {
        this.realizationTimeStart = realizationTimeStart;
    }

    @Temporal(TemporalType.TIME)
    @Column(name="realization_time_end", length=8)
    public Date getRealizationTimeEnd() {
        return this.realizationTimeEnd;
    }
    
    public void setRealizationTimeEnd(Date realizationTimeEnd) {
        this.realizationTimeEnd = realizationTimeEnd;
    }

    
    @Column(name="realization_room", length=100)
    public String getRealizationRoom() {
        return this.realizationRoom;
    }
    
    public void setRealizationRoom(String realizationRoom) {
        this.realizationRoom = realizationRoom;
    }

    
    @Column(name="notes")
    public String getNotes() {
        return this.notes;
    }
    
    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="scoring_date", length=10)
    public Date getScoringDate() {
        return this.scoringDate;
    }
    
    public void setScoringDate(Date scoringDate) {
        this.scoringDate = scoringDate;
    }

    
    @Column(name="scoring_point", precision=22, scale=0)
    public Double getScoringPoint() {
        return this.scoringPoint;
    }
    
    public void setScoringPoint(Double scoringPoint) {
        this.scoringPoint = scoringPoint;
    }

    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="scoring_by_emp_id")
    public EmpData getScoringByEmpId() {
        return this.scoringByEmpId;
    }
    
    public void setScoringByEmpId(EmpData scoringByEmpId) {
        this.scoringByEmpId = scoringByEmpId;
    }
    
    @Column(name="status", nullable = false)
    public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_on", length=19)
    public Date getCreatedOn() {
        return this.createdOn;
    }
    
    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    
    @Column(name="created_by", length=45)
    public String getCreatedBy() {
        return this.createdBy;
    }
    
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="updated_on", length=19)
    public Date getUpdatedOn() {
        return this.updatedOn;
    }
    
    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    
    @Column(name="updated_by", length=45)
    public String getUpdatedBy() {
        return this.updatedBy;
    }
    
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }




}


