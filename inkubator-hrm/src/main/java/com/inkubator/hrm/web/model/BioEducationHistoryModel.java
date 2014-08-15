/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.model;

import com.inkubator.hrm.entity.City;
import java.io.Serializable;

/**
 *
 * @author Deni
 */
public class BioEducationHistoryModel implements Serializable{
     private Long id;
     private Long biodataId;
     private Long educationLevelId;
     private Long institutionEducationId;
     private Long facultyId;
     private Long majorId;
     private String certificateNumber;
     private Double score;
     private Integer yearIn;
     private Integer yearOut;
     private Long cityId;
     private City city;

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

     
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

    public Integer getYearIn() {
        return yearIn;
    }

    public void setYearIn(Integer yearIn) {
        this.yearIn = yearIn;
    }

    public Integer getYearOut() {
        return yearOut;
    }

    public void setYearOut(Integer yearOut) {
        this.yearOut = yearOut;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
     
     
}
