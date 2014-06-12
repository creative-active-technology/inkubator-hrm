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
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
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
               
            }
            isEdit = Boolean.FALSE;
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

    
}
