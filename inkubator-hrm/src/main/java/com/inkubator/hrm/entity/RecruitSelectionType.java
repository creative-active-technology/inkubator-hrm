package com.inkubator.hrm.entity;
// Generated Apr 15, 2015 10:42:07 AM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

/**
 * RecruitSelectionType generated by hbm2java
 */
@Entity
@Table(name="recruit_selection_type"
    ,catalog="hrm"
    , uniqueConstraints = @UniqueConstraint(columnNames="code") 
)
public class RecruitSelectionType  implements java.io.Serializable {


     private long id;
     private Integer version;
     private String code;
     private String name;
     private Long cost;
     private Boolean useLibrary;
     private String createdBy;
     private Date createdOn;
     private String updatedBy;
     private Date updatedOn;
     private Set<RecruitSelectionTypeField> recruitSelectionTypeFields = new HashSet<RecruitSelectionTypeField>(0);

    public RecruitSelectionType() {
    }

	
    public RecruitSelectionType(long id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }
    public RecruitSelectionType(long id, String code, String name, Long cost, Boolean useLibrary, String createdBy, Date createdOn, String updatedBy, Date updatedOn, Set<RecruitSelectionTypeField> recruitSelectionTypeFields) {
       this.id = id;
       this.code = code;
       this.name = name;
       this.cost = cost;
       this.useLibrary = useLibrary;
       this.createdBy = createdBy;
       this.createdOn = createdOn;
       this.updatedBy = updatedBy;
       this.updatedOn = updatedOn;
       this.recruitSelectionTypeFields = recruitSelectionTypeFields;
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

    
    @Column(name="code", unique=true, nullable=false, length=12)
    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }

    
    @Column(name="name", nullable=false, length=60)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    
    @Column(name="cost", precision=10, scale=0)
    public Long getCost() {
        return this.cost;
    }
    
    public void setCost(Long cost) {
        this.cost = cost;
    }

    
    @Column(name="use_library")
    public Boolean getUseLibrary() {
        return this.useLibrary;
    }
    
    public void setUseLibrary(Boolean useLibrary) {
        this.useLibrary = useLibrary;
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

@OneToMany(fetch=FetchType.LAZY, mappedBy="recruitSelectionType", cascade=CascadeType.REMOVE)
    public Set<RecruitSelectionTypeField> getRecruitSelectionTypeFields() {
        return this.recruitSelectionTypeFields;
    }
    
    public void setRecruitSelectionTypeFields(Set<RecruitSelectionTypeField> recruitSelectionTypeFields) {
        this.recruitSelectionTypeFields = recruitSelectionTypeFields;
    }




}


