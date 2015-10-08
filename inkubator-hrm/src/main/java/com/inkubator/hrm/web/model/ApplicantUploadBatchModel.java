package com.inkubator.hrm.web.model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author rizkykojek
 */
public class ApplicantUploadBatchModel implements Serializable {
	
	//Applicant
    private String educationLevel;
    private String institutionEducation;
    private String educationStartYear;
    private String educationEndYear;
    private Double score;
    private Double scale;
    private String certificateNumber;
	
	//Bio Data
	private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String cityOfBirth;
    private String gender;
    private String phoneNumber;
    private String emailAddress;
    
    //Additional
    private Long vacancyAdvertisementId;
    private String createdBy;
    private Date createdOn;
    private String uploadPath;
    
	public String getEducationLevel() {
		return educationLevel;
	}
	public void setEducationLevel(String educationLevel) {
		this.educationLevel = educationLevel;
	}
	public String getInstitutionEducation() {
		return institutionEducation;
	}
	public void setInstitutionEducation(String institutionEducation) {
		this.institutionEducation = institutionEducation;
	}
	public String getEducationStartYear() {
		return educationStartYear;
	}
	public void setEducationStartYear(String educationStartYear) {
		this.educationStartYear = educationStartYear;
	}
	public String getEducationEndYear() {
		return educationEndYear;
	}
	public void setEducationEndYear(String educationEndYear) {
		this.educationEndYear = educationEndYear;
	}
	public Double getScore() {
		return score;
	}
	public void setScore(Double score) {
		this.score = score;
	}
	public Double getScale() {
		return scale;
	}
	public void setScale(Double scale) {
		this.scale = scale;
	}
	public String getCertificateNumber() {
		return certificateNumber;
	}
	public void setCertificateNumber(String certificateNumber) {
		this.certificateNumber = certificateNumber;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getCityOfBirth() {
		return cityOfBirth;
	}
	public void setCityOfBirth(String cityOfBirth) {
		this.cityOfBirth = cityOfBirth;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public Long getVacancyAdvertisementId() {
		return vacancyAdvertisementId;
	}
	public void setVacancyAdvertisementId(Long vacancyAdvertisementId) {
		this.vacancyAdvertisementId = vacancyAdvertisementId;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public String getUploadPath() {
		return uploadPath;
	}
	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}
    
}
