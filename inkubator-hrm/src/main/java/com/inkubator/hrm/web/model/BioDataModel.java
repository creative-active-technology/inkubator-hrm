/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.model;

import java.io.Serializable;
import java.util.Date;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Email;

/**
 *
 * @author Deni Husni FR
 */
public class BioDataModel implements Serializable {

    private Long id;
    private long nationalitiId;
    private long doialekId;
    private long religionId;
    private long cityid;
    private long raceId;
    private long maritalStatusId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String title;
    private String nickname;
    private Integer gender;
    private Integer bloodType;
    private Date dateOfBirth;
    private String personalEmail;
    private String mobilePhone;
    private Double bodyTall;
    private Double bodyWeight;
    private String createdBy;
    private Date createdOn;
    private String updatedBy;
    private Date updatedOn;
    private String pathFoto;
    private String pathFinger;
    private String pathSignature;
    private String noKK;
    private String jamsostek;
    private String npwp;
    private Date nextBirthday;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getNationalitiId() {
        return nationalitiId;
    }

    public void setNationalitiId(long nationalitiId) {
        this.nationalitiId = nationalitiId;
    }

    public long getDoialekId() {
        return doialekId;
    }

    public void setDoialekId(long doialekId) {
        this.doialekId = doialekId;
    }

    public long getReligionId() {
        return religionId;
    }

    public void setReligionId(long religionId) {
        this.religionId = religionId;
    }

    public long getCityid() {
        return cityid;
    }

    public void setCityid(long cityid) {
        this.cityid = cityid;
    }

    public long getRaceId() {
        return raceId;
    }

    public void setRaceId(long raceId) {
        this.raceId = raceId;
    }

    public long getMaritalStatusId() {
        return maritalStatusId;
    }

    public void setMaritalStatusId(long maritalStatusId) {
        this.maritalStatusId = maritalStatusId;
    }

    @Pattern(regexp = "^[A-Za-z][A-Za-z\\s]+", message = "{errorr_first_name}")
    public String getFirstName() {
        return firstName;

    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }    
    
    @Pattern(regexp = "(^[A-Za-z][A-Za-z\\s]+|)", message = "{errorr_middle_name}")
    public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

    @Pattern(regexp = "(^[A-Za-z][A-Za-z\\s]+|)", message = "{errorr_last_name}")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getBloodType() {
        return bloodType;
    }

    public void setBloodType(Integer bloodType) {
        this.bloodType = bloodType;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Email(message = "{errorr_mail}")
    public String getPersonalEmail() {
        return personalEmail;
    }

    public void setPersonalEmail(String personalEmail) {
        this.personalEmail = personalEmail;
    }

    @Pattern(regexp = "^[+][\\d() -]+", message = "{errorr_phone}")
    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public Double getBodyTall() {
        return bodyTall;
    }

    public void setBodyTall(Double bodyTall) {
        this.bodyTall = bodyTall;
    }

    public Double getBodyWeight() {
        return bodyWeight;
    }

    public void setBodyWeight(Double bodyWeight) {
        this.bodyWeight = bodyWeight;
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

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public String getPathFoto() {
        return pathFoto;
    }

    public void setPathFoto(String pathFoto) {
        this.pathFoto = pathFoto;
    }

    public String getPathFinger() {
        return pathFinger;
    }

    public void setPathFinger(String pathFinger) {
        this.pathFinger = pathFinger;
    }

    public String getPathSignature() {
        return pathSignature;
    }

    public void setPathSignature(String pathSignature) {
        this.pathSignature = pathSignature;
    }

    public String getNoKK() {
        return noKK;
    }

    public void setNoKK(String noKK) {
        this.noKK = noKK;
    }

    public String getJamsostek() {
        return jamsostek;
    }

    public void setJamsostek(String jamsostek) {
        this.jamsostek = jamsostek;
    }

    public String getNpwp() {
        return npwp;
    }

    public void setNpwp(String npwp) {
        this.npwp = npwp;
    }

    public Date getNextBirthday() {
        return nextBirthday;
    }

    public void setNextBirthday(Date nextBirthday) {
        this.nextBirthday = nextBirthday;
    }

}
