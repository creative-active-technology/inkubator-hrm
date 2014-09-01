/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.model;

import java.io.Serializable;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Email;

/**
 *
 * @author Deni Husni FR
 */
public class UserModel implements Serializable {

    private Long id;
    private String userId;
    private String realName;
    private String emailAddress;
    private String phoneNumber;
    private String password;
    private String oldPassword;
    private Boolean isActive;
    private Boolean isExpired;
    private Boolean isLock;
    private Long empDataId;
    private String empDataFullName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    @Email(message = "{errorr_mail}")
    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Pattern(regexp = "^[+][\\d() -]+", message = "{errorr_phone}")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

//    @Pattern(regexp = "^(?=.{8,})(?=.*[a-z])(?=.*[A-Z])(?=.*[\\d])(?=.*[\\W]).*$", message = "{error_strengh_password}")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Boolean getIsExpired() {
        return isExpired;
    }

    public void setIsExpired(Boolean isExpired) {
        this.isExpired = isExpired;
    }

    public Boolean getIsLock() {
        return isLock;
    }

    public void setIsLock(Boolean isLock) {
        this.isLock = isLock;
    }

	public Long getEmpDataId() {
		return empDataId;
	}

	public void setEmpDataId(Long empDataId) {
		this.empDataId = empDataId;
	}

	public String getEmpDataFullName() {
		return empDataFullName;
	}

	public void setEmpDataFullName(String empDataFullName) {
		this.empDataFullName = empDataFullName;
	}

}
