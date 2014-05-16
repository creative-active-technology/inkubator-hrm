/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.account;

import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.HrmRole;
import com.inkubator.hrm.entity.HrmUser;
import com.inkubator.hrm.entity.HrmUserRole;
import com.inkubator.hrm.entity.HrmUserRoleId;
import com.inkubator.hrm.service.HrmRoleService;
import com.inkubator.hrm.service.HrmUserService;
import com.inkubator.hrm.web.model.UserModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.DualListModel;

/**
 *
 * @author Deni Husni FR
 */
@ManagedBean(name = "userFormController")
@ViewScoped
public class UserFormController extends BaseController {

    private DualListModel<HrmRole> dualListModel = new DualListModel<>();
    @ManagedProperty(value = "#{hrmUserService}")
    private HrmUserService hrmUserService;
    @ManagedProperty(value = "#{hrmRoleService}")
    private HrmRoleService hrmRoleService;
    private UserModel userModel;
    private Boolean isEdit;

    public void setHrmUserService(HrmUserService hrmUserService) {
        this.hrmUserService = hrmUserService;
    }

    public void setHrmRoleService(HrmRoleService hrmRoleService) {
        this.hrmRoleService = hrmRoleService;
    }

    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            List<HrmRole> sourceSpiRole = this.hrmRoleService.getAllData();
            dualListModel.setSource(sourceSpiRole);
            userModel = new UserModel();
            isEdit = Boolean.FALSE;
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public DualListModel<HrmRole> getDualListModel() {
        return dualListModel;
    }

    public void setDualListModel(DualListModel<HrmRole> dualListModel) {
        this.dualListModel = dualListModel;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public String doBack() {
        return "/protected/account/user_view.htm?faces-redirect=true";
    }

    public String doSave() {
        HrmUser hrmUser = getEntityFromView(userModel);
        if (isEdit) {
            return doUpdate(hrmUser);
        } else {
            return doInsert(hrmUser);
        }
    }

    private String doInsert(HrmUser hrmUser) {
        hrmUser.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(12)));
        try {
            boolean isDuplicateUserId = hrmUserService.getByUserId(hrmUser.getUserId()) != null;
            boolean isDuplicateEmailAddress = hrmUserService.getByEmailAddress(hrmUser.getEmailAddress()) != null;
            if (isDuplicateUserId) {
                MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "user_form.duplicate_user_name",
                        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
                return null;
            } else if (isDuplicateEmailAddress) {
                MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "user_form.duplicate_email_address",
                        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
                return null;
            } else {
                Set<HrmUserRole> dataToSave = new HashSet<>();
                List<HrmRole> hrmRoles = dualListModel.getTarget();
                for (HrmRole hrmRole : hrmRoles) {
                    HrmUserRole hrmUserRole = new HrmUserRole();
                    hrmUserRole.setId(new HrmUserRoleId(hrmUser.getId(), hrmRole.getId()));
                    hrmUserRole.setHrmRole(hrmRole);
                    hrmUserRole.setHrmUser(hrmUser);
                    dataToSave.add(hrmUserRole);
                }
                hrmUser.setHrmUserRoles(dataToSave);
//                PasswordHistory passwordHistory = new PasswordHistory();
//                 passwordHistory.setPassword(AESUtil.getAESEncription(passwordHistory.getPassword(), HRMConstant.KEYVALUE, HRMConstant.AES_ALGO));
//                passwordHistory.setEmailNotification(0);
//                passwordHistory.setEmailAddress(hrmUser.getEmailAddress());
//                passwordHistory.setRequestType(HRMConstant.USER_NEW);
                hrmUserService.saveAndNotification(hrmUser);
                return "/protected/account/user_detail.htm?faces-redirect=true&execution=e" + hrmUser.getId();

            }
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
        return null;
    }

    private String doUpdate(HrmUser hrmUser) {
        return null;
    }

    public void doClear() {
        System.out.println(" hhsdhfhds");
        userModel = new UserModel();
    }

    public Boolean getIsEdit() {
        return isEdit;
    }

    public void setIsEdit(Boolean isEdit) {
        this.isEdit = isEdit;
    }

    public HrmUser getEntityFromView(UserModel userModel) {
        HrmUser hrmUser = new HrmUser();
        if (userModel.getId() != null) {
            hrmUser.setId(userModel.getId());
        }
        hrmUser.setEmailAddress(userModel.getEmailAddress());
        if (userModel.getIsActive()) {
            hrmUser.setIsActive(HRMConstant.ACTIVE);
        } else {
            hrmUser.setIsActive(HRMConstant.NOTACTIVE);
        }

        if (userModel.getIsExpired()) {
            hrmUser.setIsExpired(HRMConstant.EXPIRED);
        } else {
            hrmUser.setIsExpired(HRMConstant.NOTEXPIRED);
        }

        if (userModel.getIsLock()) {
            hrmUser.setIsLock(HRMConstant.LOCK);
        } else {
            hrmUser.setIsLock(HRMConstant.NOTLOCK);
        }
        hrmUser.setPassword(userModel.getPassword());
        hrmUser.setPhoneNumber(userModel.getPhoneNumber());
        hrmUser.setRealName(userModel.getUserName());
        hrmUser.setUserId(userModel.getUserId());
        return hrmUser;
    }

}
