package com.inkubator.hrm.entity;
// Generated Apr 22, 2015 3:42:31 PM by Hibernate Tools 4.3.1


import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

/**
 * RecruitMppApply generated by hbm2java
 */
@Entity
@Table(name="recruit_mpp_apply"
    ,catalog="hrm"
    , uniqueConstraints = @UniqueConstraint(columnNames="recruit_mpp_apply_code") 
)
public class RecruitMppApply  implements java.io.Serializable {


     private Long id;
     private Integer version;
     private RecruitMppPeriod recruitMppPeriod;
     private String recruitMppApplyCode;
     private String recruitMppApplyName;
     private Date applyDate;
     private String reason;
     private String attachmentDocPath;
     private Date createdOn;
     private String createdBy;
     private String updatedBy;
     private Date updatedOn;
     private Long totalDetailJabatan;
     private String approvalActivityNumber;
     private Integer applicationStatus;
     private Set<RecruitMppApplyDetail> recruitMppApplyDetails = new HashSet<RecruitMppApplyDetail>(0);
     private List<RecruitMppApplyDetail> listRecruitMppApplyDetail = new ArrayList<RecruitMppApplyDetail>();

    public RecruitMppApply() {
    }

	
    public RecruitMppApply(Long id, RecruitMppPeriod recruitMppPeriod, String recruitMppApplyCode, String recruitMppApplyName) {
        this.id = id;
        this.recruitMppPeriod = recruitMppPeriod;
        this.recruitMppApplyCode = recruitMppApplyCode;
        this.recruitMppApplyName = recruitMppApplyName;
    }
    public RecruitMppApply(long id, RecruitMppPeriod recruitMppPeriod, String recruitMppApplyCode, String recruitMppApplyName, Date applyDate, String reason, String attachmentDocPath, Date createdOn, String createdBy, String updatedBy, Date updatedOn, Set<RecruitMppApplyDetail> recruitMppApplyDetails) {
       this.id = id;
       this.recruitMppPeriod = recruitMppPeriod;
       this.recruitMppApplyCode = recruitMppApplyCode;
       this.recruitMppApplyName = recruitMppApplyName;
       this.applyDate = applyDate;
       this.reason = reason;
       this.attachmentDocPath = attachmentDocPath;
       this.createdOn = createdOn;
       this.createdBy = createdBy;
       this.updatedBy = updatedBy;
       this.updatedOn = updatedOn;
       this.recruitMppApplyDetails = recruitMppApplyDetails;
    }
   
     @Id    
    @Column(name="id", unique=true, nullable=false)
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
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
    @JoinColumn(name="recruit_mpp_period_id", nullable=false)
    public RecruitMppPeriod getRecruitMppPeriod() {
        return this.recruitMppPeriod;
    }
    
    public void setRecruitMppPeriod(RecruitMppPeriod recruitMppPeriod) {
        this.recruitMppPeriod = recruitMppPeriod;
    }

    
    @Column(name="recruit_mpp_apply_code", unique=true, nullable=false, length=12)
    public String getRecruitMppApplyCode() {
        return this.recruitMppApplyCode;
    }
    
    public void setRecruitMppApplyCode(String recruitMppApplyCode) {
        this.recruitMppApplyCode = recruitMppApplyCode;
    }

    
    @Column(name="recruit_mpp_apply_name", nullable=false, length=100)
    public String getRecruitMppApplyName() {
        return this.recruitMppApplyName;
    }
    
    public void setRecruitMppApplyName(String recruitMppApplyName) {
        this.recruitMppApplyName = recruitMppApplyName;
    }
    
    @Column(name = "approval_activity_number", length = 45, unique = true)
    public String getApprovalActivityNumber() {
        return approvalActivityNumber;
    }

    public void setApprovalActivityNumber(String approvalActivityNumber) {
        this.approvalActivityNumber = approvalActivityNumber;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="apply_date", length=10)
    public Date getApplyDate() {
        return this.applyDate;
    }
    
    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    
    @Column(name="reason")
    public String getReason() {
        return this.reason;
    }
    
    public void setReason(String reason) {
        this.reason = reason;
    }

    
    @Column(name="attachment_doc_path", length=120)
    public String getAttachmentDocPath() {
        return this.attachmentDocPath;
    }
    
    public void setAttachmentDocPath(String attachmentDocPath) {
        this.attachmentDocPath = attachmentDocPath;
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

    
    @Column(name="updated_by", length=45)
    public String getUpdatedBy() {
        return this.updatedBy;
    }
    
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="updated_on", length=19)
    public Date getUpdatedOn() {
        return this.updatedOn;
    }
    
    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="recruitMppApply")
    public Set<RecruitMppApplyDetail> getRecruitMppApplyDetails() {
        return this.recruitMppApplyDetails;
    }
    
    public void setRecruitMppApplyDetails(Set<RecruitMppApplyDetail> recruitMppApplyDetails) {
        this.recruitMppApplyDetails = recruitMppApplyDetails;
    }
    
    @Transient
    public Long getTotalDetailJabatan() {
        return totalDetailJabatan;
    }

    public void setTotalDetailJabatan(Long totalDetailJabatan) {
        this.totalDetailJabatan = totalDetailJabatan;
    }


    @Column(name = "application_status")
    public Integer getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(Integer applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

    @Transient
	public List<RecruitMppApplyDetail> getListRecruitMppApplyDetail() {
		return listRecruitMppApplyDetail;
	}


	public void setListRecruitMppApplyDetail(
			List<RecruitMppApplyDetail> listRecruitMppApplyDetail) {
		this.listRecruitMppApplyDetail = listRecruitMppApplyDetail;
	}

    
}


