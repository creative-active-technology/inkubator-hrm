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
     private BioData bioData;
     private EducationLevel educationLevel;
     private InstitutionEducation institutionEducation;
     private Faculty faculty;
     private Major major;
     private String certificateNumber;
     private String pathFoto;
     private Double score;
     private String createdBy;
     private Date createdOn;
     private String updatedBy;
     private Date updatedOn;
     private Integer yearIn;
     private Integer yearOut;
     private City city;

    public BioEducationHistory() {
    }

    public BioEducationHistory(long id) {
        this.id = id;
    }

    public BioEducationHistory(long id, Integer version, BioData bioData, EducationLevel educationLevel, InstitutionEducation institutionEducation, Faculty faculty, Major major, String certificateNumber, String pathFoto, Double score, String createdBy, Date createdOn, String updatedBy, Date updatedOn, Integer yearIn, Integer yearOut, City city) {
        this.id = id;
        this.version = version;
        this.bioData = bioData;
        this.educationLevel = educationLevel;
        this.institutionEducation = institutionEducation;
        this.faculty = faculty;
        this.major = major;
        this.certificateNumber = certificateNumber;
        this.pathFoto = pathFoto;
        this.score = score;
        this.createdBy = createdBy;
        this.createdOn = createdOn;
        this.updatedBy = updatedBy;
        this.updatedOn = updatedOn;
        this.yearIn = yearIn;
        this.yearOut = yearOut;
        this.city = city;
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
    public BioData getBioData() {
        return bioData;
    }

    public void setBioData(BioData bioData) {
        this.bioData = bioData;
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

    @Column(name="year_in", length=5)
    public Integer getYearIn() {
        return yearIn;
    }

    public void setYearIn(Integer yearIn) {
        this.yearIn = yearIn;
    }

    @Column(name="year_out", length=5)
    public Integer getYearOut() {
        return yearOut;
    }

    public void setYearOut(Integer yearOut) {
        this.yearOut = yearOut;
    }

    @Column(name = "path_foto", length = 100)
    public String getPathFoto() {
        return pathFoto;
    }

    public void setPathFoto(String pathFoto) {
        this.pathFoto = pathFoto;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
     
     
}
