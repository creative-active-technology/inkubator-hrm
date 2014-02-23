/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web;

import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.PasswordComplexity;
import com.inkubator.hrm.service.PasswordComplexityService;
import com.inkubator.securitycore.util.UserInfoUtil;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Deni Husni FR
 */
@ManagedBean(name = "passwordConfigController")
@ViewScoped
public class PasswordConfigController extends BaseController {
    
    private Integer maxCharacter;
    private Integer minCharacter;
    private Boolean isMustHaveNumber;
    private Boolean isMustHaveSPCharacter;
    private Boolean isMustHaveUpperCase;
    private Boolean isMustHaveLowerCase;
    private Boolean isPasswordMustDifferent;
    private Boolean isDisable;
    private Boolean isEdit;
    private Integer expiredPeriod;
    private Integer notificationPeriod;
    List<Integer> period = new ArrayList<>();
    List<String> notificationSelection = new ArrayList<>();
    @ManagedProperty(value = "#{passwordComplexityService}")
    private PasswordComplexityService passwordComplexityService;
    
    public Boolean getIsEdit() {
        return isEdit;
    }
    
    public void setIsEdit(Boolean isEdit) {
        this.isEdit = isEdit;
    }
    
    public void setPasswordComplexityService(PasswordComplexityService passwordComplexityService) {
        this.passwordComplexityService = passwordComplexityService;
    }
    
    public Boolean getIsPasswordMustDifferent() {
        return isPasswordMustDifferent;
    }
    
    public Boolean getIsDisable() {
        return isDisable;
    }
    
    public void setIsDisable(Boolean isDisable) {
        this.isDisable = isDisable;
    }
    
    public void setIsPasswordMustDifferent(Boolean isPasswordMustDifferent) {
        this.isPasswordMustDifferent = isPasswordMustDifferent;
    }
    
    public List<String> getNotificationSelection() {
        return notificationSelection;
    }
    
    public void setNotificationSelection(List<String> notificationSelection) {
        this.notificationSelection = notificationSelection;
    }
    
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
    
    public List<Integer> getPeriod() {
        return period;
    }
    
    public void setPeriod(List<Integer> period) {
        this.period = period;
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
    
    public Boolean getIsMustHaveNumber() {
        return isMustHaveNumber;
    }
    
    public void setIsMustHaveNumber(Boolean isMustHaveNumber) {
        this.isMustHaveNumber = isMustHaveNumber;
    }
    
    public Boolean getIsMustHaveSPCharacter() {
        return isMustHaveSPCharacter;
    }
    
    public void setIsMustHaveSPCharacter(Boolean isMustHaveSPCharacter) {
        this.isMustHaveSPCharacter = isMustHaveSPCharacter;
    }
    
    public Boolean getIsMustHaveUpperCase() {
        return isMustHaveUpperCase;
    }
    
    public void setIsMustHaveUpperCase(Boolean isMustHaveUpperCase) {
        this.isMustHaveUpperCase = isMustHaveUpperCase;
    }
    
    public Boolean getIsMustHaveLowerCase() {
        return isMustHaveLowerCase;
    }
    
    public void setIsMustHaveLowerCase(Boolean isMustHaveLowerCase) {
        this.isMustHaveLowerCase = isMustHaveLowerCase;
    }
    
    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        for (int i = 1; i <= 12; i++) {
            period.add(i);
        }
        try {
            PasswordComplexity passwordComplexity = passwordComplexityService.getByCode(HRMConstant.PASSWORD_CONFIG_CODE);
            if (passwordComplexity == null) {
                isDisable = Boolean.FALSE;
            } else {
                fromEntityToPageUI(passwordComplexity);
                isDisable = Boolean.TRUE;
            }
            isEdit = Boolean.FALSE;
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
        
    }
    
    @PreDestroy
    public void onPostClose() {
        LOGGER.info("Bersih-bersih");
        expiredPeriod = null;
        isDisable = null;
        isEdit = null;
        isMustHaveLowerCase = null;
        isMustHaveNumber = null;
        isMustHaveSPCharacter = null;
        isMustHaveUpperCase = null;
        isPasswordMustDifferent = null;
        maxCharacter = null;
        minCharacter = null;
        notificationPeriod = null;
        notificationSelection = null;
        passwordComplexityService = null;
        period = null;
    }
    
    public void doSave() {
        PasswordComplexity passwordComplexity = fromPageUIToEntity();
        try {
            if (isEdit) {
                passwordComplexity.setUpdatedBy(UserInfoUtil.getUserName());
                passwordComplexity.setUpdatedOn(new Date());
                passwordComplexityService.update(passwordComplexity);
                MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.save", "global.update_konfirmasi",
                        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            } else {
                passwordComplexity.setCreatedBy(UserInfoUtil.getUserName());
                passwordComplexity.setCreatedOn(new Date());
                passwordComplexityService.save(passwordComplexity);
                MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.save", "global.save_konfirmasi",
                        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            }
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
        isDisable = Boolean.TRUE;
        isEdit = Boolean.FALSE;
        
    }
    
    public void doEdit() {
        isDisable = Boolean.FALSE;
        isEdit = Boolean.TRUE;
    }
    
    private PasswordComplexity fromPageUIToEntity() {
        PasswordComplexity passwordComplexity = new PasswordComplexity();
        passwordComplexity.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(5)));
        passwordComplexity.setCode(HRMConstant.PASSWORD_CONFIG_CODE);
        if (notificationSelection.contains("Email")) {
            passwordComplexity.setEmailNotification(1);
        } else {
            passwordComplexity.setEmailNotification(0);
        }
        if (notificationSelection.contains("SMS")) {
            passwordComplexity.setSmsNotification(1);
        } else {
            passwordComplexity.setSmsNotification(0);
        }
        passwordComplexity.setExpiredPeriod(expiredPeriod);
        if (isMustHaveLowerCase) {
            passwordComplexity.setHasLowerCase(1);
        } else {
            passwordComplexity.setHasLowerCase(0);
        }
        if (isMustHaveNumber) {
            passwordComplexity.setHasNumber(1);
        } else {
            passwordComplexity.setHasNumber(0);
        }
        
        if (isMustHaveSPCharacter) {
            passwordComplexity.setHasSpecialCharacter(1);
        } else {
            passwordComplexity.setHasSpecialCharacter(0);
        }
        
        if (isMustHaveUpperCase) {
            passwordComplexity.setHasUpperCase(1);
        } else {
            passwordComplexity.setHasUpperCase(0);
        }
        passwordComplexity.setMaxCharacter(maxCharacter);
        passwordComplexity.setMinCharacter(minCharacter);
        passwordComplexity.setNotificationPeriod(notificationPeriod);
        if (isPasswordMustDifferent) {
            passwordComplexity.setPasswordMustDifferent(1);
        } else {
            passwordComplexity.setPasswordMustDifferent(0);
        }
        return passwordComplexity;
    }
    
    private void fromEntityToPageUI(PasswordComplexity passwordComplexity) {
        maxCharacter = passwordComplexity.getMaxCharacter();
        minCharacter = passwordComplexity.getMinCharacter();
        expiredPeriod = passwordComplexity.getExpiredPeriod();
        notificationPeriod = passwordComplexity.getNotificationPeriod();
        if (passwordComplexity.getEmailNotification() == 1) {
            notificationSelection.add("Email");
        }
        if (passwordComplexity.getSmsNotification() == 1) {
            notificationSelection.add("SMS");
        }
        if (passwordComplexity.getHasLowerCase() == 1) {
            isMustHaveLowerCase = Boolean.TRUE;
        }
        if (passwordComplexity.getHasNumber() == 1) {
            isMustHaveNumber = Boolean.TRUE;
        }
        if (passwordComplexity.getHasSpecialCharacter() == 1) {
            isMustHaveSPCharacter = Boolean.TRUE;
        }
        if (passwordComplexity.getHasUpperCase() == 1) {
            isMustHaveUpperCase = Boolean.TRUE;
        }
        
        if (passwordComplexity.getPasswordMustDifferent() == 1) {
            isPasswordMustDifferent = Boolean.TRUE;
        }
    }
}
