package com.inkubator.hrm.entity;
// Generated Sep 4, 2014 4:27:34 PM by Hibernate Tools 3.6.0


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
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

/**
 * ModelComponent generated by hbm2java
 */
@Entity
@Table(name="model_component"
    ,catalog="hrm_personalia"
    , uniqueConstraints = {@UniqueConstraint(columnNames="code"), @UniqueConstraint(columnNames="name")} 
)
public class ModelComponent  implements java.io.Serializable {


     private long id;
     private Integer version;
     private BenefitGroup benefitGroup;
     private String createdBy;
     private Date createdOn;
     private String description;
     private String code;
     private String name;
     private String updatedBy;
     private Date updatedOn;
     private Integer spesific;

    public ModelComponent() {
    }

	
    public ModelComponent(long id) {
        this.id = id;
    }
    public ModelComponent(long id, BenefitGroup benefitGroup, String createdBy, Date createdOn, String description, String code, String name, String updatedBy, Date updatedOn, Integer spesific) {
       this.id = id;
       this.benefitGroup = benefitGroup;
       this.createdBy = createdBy;
       this.createdOn = createdOn;
       this.description = description;
       this.code = code;
       this.name = name;
       this.updatedBy = updatedBy;
       this.updatedOn = updatedOn;
       this.spesific = spesific;
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
    @JoinColumn(name="benefit_group_id")
    public BenefitGroup getBenefitGroup() {
        return this.benefitGroup;
    }
    
    public void setBenefitGroup(BenefitGroup benefitGroup) {
        this.benefitGroup = benefitGroup;
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

    
    @Column(name="description")
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    
    @Column(name="code", unique=true, length=8)
    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }

    
    @Column(name="name", unique=true, length=60)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
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

    
    @Column(name="spesific")
    public Integer getSpesific() {
        return this.spesific;
    }
    
    public void setSpesific(Integer spesific) {
        this.spesific = spesific;
    }




}


