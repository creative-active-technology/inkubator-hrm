package com.inkubator.hrm.entity;
// Generated Apr 16, 2015 4:39:12 PM by Hibernate Tools 4.3.1


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
 * RecruitSelectionTypeTemplatesJobTitle generated by hbm2java
 */
@Entity
@Table(name="recruit_selection_type_templates_job_title"
    ,catalog="hrm"
)
public class RecruitSelectionTypeTemplatesJobTitle  implements java.io.Serializable {


     private RecruitSelectionTypeTemplatesJobTitleId id;
     private Integer version;
     private Jabatan jabatan;
     private RecruitSelectionTypeTemplates recruitSelectionTypeTemplates;
     private Date createdOn;
     private String createdBy;

    public RecruitSelectionTypeTemplatesJobTitle() {
    }

	
    public RecruitSelectionTypeTemplatesJobTitle(RecruitSelectionTypeTemplatesJobTitleId id, Jabatan jabatan, RecruitSelectionTypeTemplates recruitSelectionTypeTemplates) {
        this.id = id;
        this.jabatan = jabatan;
        this.recruitSelectionTypeTemplates = recruitSelectionTypeTemplates;
    }
    public RecruitSelectionTypeTemplatesJobTitle(RecruitSelectionTypeTemplatesJobTitleId id, Jabatan jabatan, RecruitSelectionTypeTemplates recruitSelectionTypeTemplates, Date createdOn, String createdBy) {
       this.id = id;
       this.jabatan = jabatan;
       this.recruitSelectionTypeTemplates = recruitSelectionTypeTemplates;
       this.createdOn = createdOn;
       this.createdBy = createdBy;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="templatesId", column=@Column(name="templates_id", nullable=false) ), 
        @AttributeOverride(name="jabatanId", column=@Column(name="jabatan_id", nullable=false) ) } )
    public RecruitSelectionTypeTemplatesJobTitleId getId() {
        return this.id;
    }
    
    public void setId(RecruitSelectionTypeTemplatesJobTitleId id) {
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
    @JoinColumn(name="jabatan_id", nullable=false, insertable=false, updatable=false)
    public Jabatan getJabatan() {
        return this.jabatan;
    }
    
    public void setJabatan(Jabatan jabatan) {
        this.jabatan = jabatan;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="templates_id", nullable=false, insertable=false, updatable=false)
    public RecruitSelectionTypeTemplates getRecruitSelectionTypeTemplates() {
        return this.recruitSelectionTypeTemplates;
    }
    
    public void setRecruitSelectionTypeTemplates(RecruitSelectionTypeTemplates recruitSelectionTypeTemplates) {
        this.recruitSelectionTypeTemplates = recruitSelectionTypeTemplates;
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




}


