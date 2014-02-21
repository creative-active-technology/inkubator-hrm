/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web;

import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
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
    private Integer expiredPeriod;
    private Integer notificationPeriod;
    List<Integer> period = new ArrayList<>();
    List<String> notificationSelection = new ArrayList<>();

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
        bahasa = (String) FacesUtil.getSessionAttribute("bahasa_active");
        FacesUtil.getFacesContext().getViewRoot().setLocale(new Locale(bahasa));
        for (int i = 1; i <= 12; i++) {
            period.add(i);
        }
        isDisable = Boolean.TRUE;
    }

    public void doSave() {
        System.out.println("doing save");
        isDisable = Boolean.TRUE;

    }

    public void doEdit() {
        isDisable = Boolean.FALSE;
    }

}
