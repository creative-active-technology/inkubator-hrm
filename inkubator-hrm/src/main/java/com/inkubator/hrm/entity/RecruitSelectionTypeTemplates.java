package com.inkubator.hrm.entity;
// Generated Apr 16, 2015 4:39:12 PM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
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
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

/**
 * RecruitSelectionTypeTemplates generated by hbm2java
 */
@Entity
@Table(name="recruit_selection_type_templates"
    ,catalog="hrm"
    , uniqueConstraints = @UniqueConstraint(columnNames="code") 
)
public class RecruitSelectionTypeTemplates  implements java.io.Serializable {


     private long id;
     private Integer version;
     private RecruitSelectionTypeTemplates recruitSelectionTypeTemplates;
     private SystemScoring systemScoring;
     private String code;
     private String name;
     private Boolean isCategory;
     private Double targetNilai;
     private String description;
     private Date createdOn;
     private String createdBy;
     private Date updatedOn;
     private String updatedBy;
     private Set<RecruitSelectionTypeTemplatesJobTitle> recruitSelectionTypeTemplatesJobTitles = new HashSet<RecruitSelectionTypeTemplatesJobTitle>(0);
     private Set<RecruitSelectionTypeTemplates> recruitSelectionTypeTemplateses = new HashSet<RecruitSelectionTypeTemplates>(0);

    public RecruitSelectionTypeTemplates() {
    }

	
    public RecruitSelectionTypeTemplates(long id, String code) {
        this.id = id;
        this.code = code;
    }
    public RecruitSelectionTypeTemplates(long id, RecruitSelectionTypeTemplates recruitSelectionTypeTemplates, SystemScoring systemScoring, String code, String name, Boolean isCategory, Double targetNilai, String description, Date createdOn, String createdBy, Date updatedOn, String updatedBy, Set<RecruitSelectionTypeTemplatesJobTitle> recruitSelectionTypeTemplatesJobTitles, Set<RecruitSelectionTypeTemplates> recruitSelectionTypeTemplateses) {
       this.id = id;
       this.recruitSelectionTypeTemplates = recruitSelectionTypeTemplates;
       this.systemScoring = systemScoring;
       this.code = code;
       this.name = name;
       this.isCategory = isCategory;
       this.targetNilai = targetNilai;
       this.description = description;
       this.createdOn = createdOn;
       this.createdBy = createdBy;
       this.updatedOn = updatedOn;
       this.updatedBy = updatedBy;
       this.recruitSelectionTypeTemplatesJobTitles = recruitSelectionTypeTemplatesJobTitles;
       this.recruitSelectionTypeTemplateses = recruitSelectionTypeTemplateses;
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
    @JoinColumn(name="parent_id")
    public RecruitSelectionTypeTemplates getRecruitSelectionTypeTemplates() {
        return this.recruitSelectionTypeTemplates;
    }
    
    public void setRecruitSelectionTypeTemplates(RecruitSelectionTypeTemplates recruitSelectionTypeTemplates) {
        this.recruitSelectionTypeTemplates = recruitSelectionTypeTemplates;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="system_scoring_id")
    public SystemScoring getSystemScoring() {
        return this.systemScoring;
    }
    
    public void setSystemScoring(SystemScoring systemScoring) {
        this.systemScoring = systemScoring;
    }

    
    @Column(name="code", unique=true, nullable=false, length=12)
    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }

    
    @Column(name="name", length=80)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    
    @Column(name="is_category")
    public Boolean getIsCategory() {
        return this.isCategory;
    }
    
    public void setIsCategory(Boolean isCategory) {
        this.isCategory = isCategory;
    }

    
    @Column(name="target_nilai", precision=22, scale=0)
    public Double getTargetNilai() {
        return this.targetNilai;
    }
    
    public void setTargetNilai(Double targetNilai) {
        this.targetNilai = targetNilai;
    }

    
    @Column(name="description")
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
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

@OneToMany(fetch=FetchType.LAZY, mappedBy="recruitSelectionTypeTemplates")
    public Set<RecruitSelectionTypeTemplatesJobTitle> getRecruitSelectionTypeTemplatesJobTitles() {
        return this.recruitSelectionTypeTemplatesJobTitles;
    }
    
    public void setRecruitSelectionTypeTemplatesJobTitles(Set<RecruitSelectionTypeTemplatesJobTitle> recruitSelectionTypeTemplatesJobTitles) {
        this.recruitSelectionTypeTemplatesJobTitles = recruitSelectionTypeTemplatesJobTitles;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="recruitSelectionTypeTemplates")
    public Set<RecruitSelectionTypeTemplates> getRecruitSelectionTypeTemplateses() {
        return this.recruitSelectionTypeTemplateses;
    }
    
    public void setRecruitSelectionTypeTemplateses(Set<RecruitSelectionTypeTemplates> recruitSelectionTypeTemplateses) {
        this.recruitSelectionTypeTemplateses = recruitSelectionTypeTemplateses;
    }




}


