package com.inkubator.hrm.entity;
// Generated Aug 12, 2014 10:31:26 AM by Hibernate Tools 3.6.0


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
 * BioFamilyRelationship generated by hbm2java
 */
@Entity
@Table(name="bio_family_relationship"
    ,catalog="hrm_personalia"
)
public class BioFamilyRelationship  implements java.io.Serializable {


     private long id;
     private Integer version;
     private BioData bioData;
     private EducationLevel educationLevel;
     private FamilyRelation familyRelation;
     private String createdBy;
     private Date createdOn;
     private String updatedBy;
     private Date updatedOn;
     private String name;
     private Date dateOfBirth;
     private Integer gender;
     private Integer dependents;
     private String occupation;

    public BioFamilyRelationship() {
    }

	
    public BioFamilyRelationship(long id, BioData bioData, EducationLevel educationLevel, FamilyRelation familyRelation) {
        this.id = id;
        this.bioData = bioData;
        this.educationLevel = educationLevel;
        this.familyRelation = familyRelation;
    }
    public BioFamilyRelationship(long id, BioData bioData, EducationLevel educationLevel, FamilyRelation familyRelation, String createdBy, Date createdOn, String updatedBy, Date updatedOn, String name, Date dateOfBirth, Integer gender, Integer dependents, String occupation) {
       this.id = id;
       this.bioData = bioData;
       this.educationLevel = educationLevel;
       this.familyRelation = familyRelation;
       this.createdBy = createdBy;
       this.createdOn = createdOn;
       this.updatedBy = updatedBy;
       this.updatedOn = updatedOn;
       this.name = name;
       this.dateOfBirth = dateOfBirth;
       this.gender = gender;
       this.dependents = dependents;
       this.occupation = occupation;
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
    @JoinColumn(name="bio_data_id", nullable=false)
    public BioData getBioData() {
        return this.bioData;
    }
    
    public void setBioData(BioData bioData) {
        this.bioData = bioData;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="education_level_id", nullable=false)
    public EducationLevel getEducationLevel() {
        return this.educationLevel;
    }
    
    public void setEducationLevel(EducationLevel educationLevel) {
        this.educationLevel = educationLevel;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="family_relation_id", nullable=false)
    public FamilyRelation getFamilyRelation() {
        return this.familyRelation;
    }
    
    public void setFamilyRelation(FamilyRelation familyRelation) {
        this.familyRelation = familyRelation;
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

    
    @Column(name="name", length=60)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="date_of_birth", length=10)
    public Date getDateOfBirth() {
        return this.dateOfBirth;
    }
    
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    
    @Column(name="gender")
    public Integer getGender() {
        return this.gender;
    }
    
    public void setGender(Integer gender) {
        this.gender = gender;
    }

    
    @Column(name="dependents")
    public Integer getDependents() {
        return this.dependents;
    }
    
    public void setDependents(Integer dependents) {
        this.dependents = dependents;
    }

    
    @Column(name="occupation", length=60)
    public String getOccupation() {
        return this.occupation;
    }
    
    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }




}


