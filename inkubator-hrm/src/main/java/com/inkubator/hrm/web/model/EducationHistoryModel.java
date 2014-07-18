/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.model;

/**
 *
 * @author Deni
 */
public class EducationHistoryModel {
     private Long id;
     private Long biodataId;
     private Long educationLevelId;
     private Long institutionEducationId;
     private Long facultyId;
     private Long majorId;
     private String certificateNumber;
     private Double score;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBiodataId() {
        return biodataId;
    }

    public void setBiodataId(Long biodataId) {
        this.biodataId = biodataId;
    }

    public Long getEducationLevelId() {
        return educationLevelId;
    }

    public void setEducationLevelId(Long educationLevelId) {
        this.educationLevelId = educationLevelId;
    }

    public Long getInstitutionEducationId() {
        return institutionEducationId;
    }

    public void setInstitutionEducationId(Long institutionEducationId) {
        this.institutionEducationId = institutionEducationId;
    }

    public Long getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(Long facultyId) {
        this.facultyId = facultyId;
    }

    public Long getMajorId() {
        return majorId;
    }

    public void setMajorId(Long majorId) {
        this.majorId = majorId;
    }

    public String getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }
     
     
}
