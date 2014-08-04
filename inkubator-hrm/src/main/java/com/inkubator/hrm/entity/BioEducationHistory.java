/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.entity;

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
 *
 * @author Deni
 */
@Entity
@Table(name="bio_education_history"
    ,catalog="hrm"
)
public class BioEducationHistory implements java.io.Serializable{
     private long id;
     private Integer version;
     private BioData biodata;
     private EducationLevel educationLevel;
     private InstitutionEducation institutionEducation;
     private Faculty faculty;
     private Major major;
     private String certificateNumber;
     private Double score;
     private String createdBy;
     private Date createdOn;
     private String updatedBy;
     private Date updatedOn;

    public BioEducationHistory() {
    }

    public BioEducationHistory(long id) {
        this.id = id;
    }

    public BioEducationHistory(long id, Integer version, BioData biodata, EducationLevel educationLevel, InstitutionEducation institutionEducation, Faculty faculty, Major major, String createdBy, Date createdOn, String updatedBy, Date updatedOn, String certificateNumber, Double score) {
        this.id = id;
        this.version = version;
        this.biodata = biodata;
        this.educationLevel = educationLevel;
        this.institutionEducation = institutionEducation;
        this.faculty = faculty;
        this.major = major;
        this.createdBy = createdBy;
        this.createdOn = createdOn;
        this.updatedBy = updatedBy;
        this.updatedOn = updatedOn;
        this.certificateNumber = certificateNumber;
        this.score = score;
    }

    @Id 

    
    @Column(name="id", unique=true, nullable=false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Version
    @Column(name="version")
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "biodata_id")
    public BioData getBiodata() {
        return biodata;
    }

    public void setBiodata(BioData biodata) {
        this.biodata = biodata;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pendidikan_level_id")
    public EducationLevel getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(EducationLevel educationLevel) {
        this.educationLevel = educationLevel;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "institution_education_id")
    public InstitutionEducation getInstitutionEducation() {
        return institutionEducation;
    }

    public void setInstitutionEducation(InstitutionEducation institutionEducation) {
        this.institutionEducation = institutionEducation;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "faculty_id")
    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "major_id")
    public Major getMajor() {
        return major;
    }

    public void setMajor(Major major) {
        this.major = major;
    }

        @Column(name="created_by", length=45)
    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_on", length = 19)
    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    @Column(name="updated_by", length=45)
    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_on", length = 19)
    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }
        
    @Column(name="certificate_number", length=100)
    public String getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
    }

    @Column(name="score", length=30)
    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }
     
     
}
