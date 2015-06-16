package com.inkubator.hrm.entity;
// Generated Nov 21, 2014 4:26:05 PM by Hibernate Tools 3.6.0


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
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
 * Disease generated by hbm2java
 */
@Entity
@Table(name="disease"
    ,catalog="hrm_payroll"
    , uniqueConstraints = @UniqueConstraint(columnNames="code") 
)
public class Disease  implements java.io.Serializable {


     private long id;
     private Integer version;
     private String createdBy;
     private Date createdOn;
     private String description;
     private String code;
     private String name;
     private String updatedBy;
     private Date updatedOn;
     private Set<MedicalCare> medicalCares = new HashSet<MedicalCare>(0);

    public Disease() {
    }

	
    public Disease(long id) {
        this.id = id;
    }
    public Disease(long id, String createdBy, Date createdOn, String description, String code, String name, String updatedBy, Date updatedOn, Set<MedicalCare> medicalCares) {
       this.id = id;
       this.createdBy = createdBy;
       this.createdOn = createdOn;
       this.description = description;
       this.code = code;
       this.name = name;
       this.updatedBy = updatedBy;
       this.updatedOn = updatedOn;
       this.medicalCares = medicalCares;
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

    
    @Column(name="name", length=60)
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

@OneToMany(fetch=FetchType.LAZY, mappedBy="disease")
    public Set<MedicalCare> getMedicalCares() {
        return this.medicalCares;
    }
    
    public void setMedicalCares(Set<MedicalCare> medicalCares) {
        this.medicalCares = medicalCares;
    }




}


