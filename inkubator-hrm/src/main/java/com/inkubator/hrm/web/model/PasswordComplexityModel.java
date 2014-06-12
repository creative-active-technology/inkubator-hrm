/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.model;

import java.io.Serializable;

/**
 *
 * @author Deni Husni FR
 */
public class PasswordComplexityModel implements Serializable {

    private Long id;
    private Integer maxCharacter;
    private Integer minCharacter;
    private Boolean isMustHaveNumber;
    private Boolean isMustHaveSPCharacter;
    private Boolean isMustHaveUpperCase;
    private Boolean isMustHaveLowerCase;
    private Boolean isPasswordMustDifferent;
//    private Boolean isDisable;
//    private Boolean isEdit;
    private Integer expiredPeriod;
    private Integer notificationPeriod;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMaxCharacter() {
        return maxCharacter;
    }

    public void setMaxCharacter(Integer maxCharacter) {
        this.maxCharacter = maxCharacter;
    }

    public Integer getMinCharacter() {
        return minCharacter;
    }

    public void setMinCharacter(Integer minCharacter) {
        this.minCharacter = minCharacter;
    }

    public Boolean isIsMustHaveNumber() {
        return isMustHaveNumber;
    }

    public void setIsMustHaveNumber(Boolean isMustHaveNumber) {
        this.isMustHaveNumber = isMustHaveNumber;
    }

    public Boolean isIsMustHaveSPCharacter() {
        return isMustHaveSPCharacter;
    }

    public void setIsMustHaveSPCharacter(Boolean isMustHaveSPCharacter) {
        this.isMustHaveSPCharacter = isMustHaveSPCharacter;
    }

    public Boolean isIsMustHaveUpperCase() {
        return isMustHaveUpperCase;
    }

    public void setIsMustHaveUpperCase(Boolean isMustHaveUpperCase) {
        this.isMustHaveUpperCase = isMustHaveUpperCase;
    }

    public Boolean isIsMustHaveLowerCase() {
        return isMustHaveLowerCase;
    }

    public void setIsMustHaveLowerCase(Boolean isMustHaveLowerCase) {
        this.isMustHaveLowerCase = isMustHaveLowerCase;
    }

    public Boolean getIsPasswordMustDifferent() {
        return isPasswordMustDifferent;
    }

    public void setIsPasswordMustDifferent(Boolean isPasswordMustDifferent) {
        this.isPasswordMustDifferent = isPasswordMustDifferent;
    }

//    public Boolean getIsDisable() {
//        return isDisable;
//    }
//
//    public void setIsDisable(Boolean isDisable) {
//        this.isDisable = isDisable;
//    }
//
//    public Boolean getIsEdit() {
//        return isEdit;
//    }
//
//    public void setIsEdit(Boolean isEdit) {
//        this.isEdit = isEdit;
//    }

    public Integer getExpiredPeriod() {
        return expiredPeriod;
    }

    public void setExpiredPeriod(Integer expiredPeriod) {
        this.expiredPeriod = expiredPeriod;
    }

    public Integer getNotificationPeriod() {
        return notificationPeriod;
    }

    public void setNotificationPeriod(Integer notificationPeriod) {
        this.notificationPeriod = notificationPeriod;
    }
    
    
}
