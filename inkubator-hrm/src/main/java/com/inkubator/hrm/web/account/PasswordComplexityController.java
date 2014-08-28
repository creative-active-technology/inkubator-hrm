/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.account;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.PasswordComplexity;
import com.inkubator.hrm.service.PasswordComplexityService;
import com.inkubator.hrm.web.model.PasswordComplexityModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Deni Husni FR
 */
@ManagedBean(name = "passwordComplexityController")
@ViewScoped
public class PasswordComplexityController extends BaseController {

    private List<Integer> period = new ArrayList<>();
    private List<String> notificationSelection = new ArrayList<>();
    @ManagedProperty(value = "#{passwordComplexityService}")
    private PasswordComplexityService passwordComplexityService;
    private PasswordComplexityModel passwordComplexityModel;
    private Boolean isDisable;
    private Boolean isEdit;

    public void setPasswordComplexityService(PasswordComplexityService passwordComplexityService) {
        this.passwordComplexityService = passwordComplexityService;
    }

    public List<Integer> getPeriod() {
        return period;
    }

    public void setPeriod(List<Integer> period) {
        this.period = period;
    }

    public List<String> getNotificationSelection() {
        return notificationSelection;
    }

    public void setNotificationSelection(List<String> notificationSelection) {
        this.notificationSelection = notificationSelection;
    }

    public PasswordComplexityModel getPasswordComplexityModel() {
        return passwordComplexityModel;
    }

    public void setPasswordComplexityModel(PasswordComplexityModel passwordComplexityModel) {
        this.passwordComplexityModel = passwordComplexityModel;
    }

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        passwordComplexityModel = new PasswordComplexityModel();
        for (int i = 1; i <= 12; i++) {
            period.add(i);
        }
        try {
            PasswordComplexity passwordComplexity = passwordComplexityService.getByCode(HRMConstant.PASSWORD_CONFIG_CODE);
            if (passwordComplexity == null) {
                isDisable = Boolean.FALSE;
                isEdit = Boolean.FALSE;
            } else {
                passwordComplexityModel.setExpiredPeriod(passwordComplexity.getExpiredPeriod());
                passwordComplexityModel.setId(passwordComplexity.getId());
                passwordComplexityModel.setIsMustHaveLowerCase(passwordComplexity.getHasLowerCase());
                passwordComplexityModel.setIsMustHaveNumber(passwordComplexity.getHasNumber());
                passwordComplexityModel.setIsMustHaveSPCharacter(passwordComplexity.getHasSpecialCharacter());
                passwordComplexityModel.setIsMustHaveUpperCase(passwordComplexity.getHasUpperCase());
                passwordComplexityModel.setIsPasswordMustDifferent(passwordComplexity.getPasswordMustDifferent());
                passwordComplexityModel.setMaxCharacter(passwordComplexity.getMaxCharacter());
                passwordComplexityModel.setMinCharacter(passwordComplexity.getMinCharacter());
                passwordComplexityModel.setNotificationPeriod(passwordComplexity.getNotificationPeriod());
                if (passwordComplexity.getEmailNotification()) {
                    notificationSelection.add("email");
                }
                if (passwordComplexity.getSmsNotification()) {
                    notificationSelection.add("sms");
                }
                isDisable = Boolean.TRUE;
                isEdit = Boolean.TRUE;
            }

        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public Boolean getIsDisable() {
        return isDisable;
    }

    public void setIsDisable(Boolean isDisable) {
        this.isDisable = isDisable;
    }

    public Boolean getIsEdit() {
        return isEdit;
    }

    public void setIsEdit(Boolean isEdit) {
        this.isEdit = isEdit;
    }

    public void doEdit() {
        isDisable = Boolean.FALSE;
        isEdit = Boolean.TRUE;
    }

    public void doSave() {
      
        PasswordComplexity passwordComplexity = fromPageUIToEntity();
        try {
            if (isEdit) {
                passwordComplexityService.update(passwordComplexity);
                MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.update_successfully",
                        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            } else {
                passwordComplexityService.save(passwordComplexity);
                MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully",
                        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            }
            isDisable = Boolean.TRUE;
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }

    }

    private PasswordComplexity fromPageUIToEntity() {
        PasswordComplexity passwordComplexity = new PasswordComplexity();
        if (passwordComplexityModel.getId() != null) {
            passwordComplexity.setId(passwordComplexityModel.getId());
        }
        passwordComplexity.setCode(HRMConstant.PASSWORD_CONFIG_CODE);
        if (notificationSelection.contains("email")) {
            passwordComplexity.setEmailNotification(Boolean.TRUE);
        } else {
            passwordComplexity.setEmailNotification(Boolean.FALSE);
        }

        if (notificationSelection.contains("sms")) {
            passwordComplexity.setSmsNotification(Boolean.TRUE);
        } else {
            passwordComplexity.setSmsNotification(Boolean.FALSE);
        }
        passwordComplexity.setExpiredPeriod(passwordComplexityModel.getExpiredPeriod());
        passwordComplexity.setHasLowerCase(passwordComplexityModel.getIsMustHaveLowerCase());
        passwordComplexity.setHasNumber(passwordComplexityModel.getIsMustHaveNumber());
        passwordComplexity.setHasSpecialCharacter(passwordComplexityModel.getIsMustHaveSPCharacter());
        passwordComplexity.setHasUpperCase(passwordComplexityModel.getIsMustHaveUpperCase());
        passwordComplexity.setMaxCharacter(passwordComplexityModel.getMaxCharacter());
        passwordComplexity.setMinCharacter(passwordComplexityModel.getMinCharacter());
        passwordComplexity.setNotificationPeriod(passwordComplexityModel.getNotificationPeriod());
        passwordComplexity.setPasswordMustDifferent(passwordComplexityModel.getIsPasswordMustDifferent());
        return passwordComplexity;
    }
}
