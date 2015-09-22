/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.account;

import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.HrmRole;
import com.inkubator.hrm.entity.HrmUser;
import com.inkubator.hrm.entity.HrmUserRole;
import com.inkubator.hrm.entity.HrmUserRoleId;
import com.inkubator.hrm.entity.PasswordComplexity;
import com.inkubator.hrm.service.HrmRoleService;
import com.inkubator.hrm.service.HrmUserService;
import com.inkubator.hrm.service.PasswordComplexityService;
import com.inkubator.hrm.web.model.UserModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
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
    private PasswordComplexity passwordComplexity;
    @ManagedProperty(value = "#{passwordComplexityService}")
    private PasswordComplexityService passwordComplexityService;
    private Boolean isEdit;

    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            String userId = FacesUtil.getRequestParameter("execution");
            if (userId != null) {
                isEdit = Boolean.TRUE;
                HrmUser selectedHrmUser = hrmUserService.getEntiyByPkWithDetail(Long.parseLong(userId.substring(1)));
                userModel = getUserModelFromEntity(selectedHrmUser);
                List<HrmRole> sourceSpiRole = this.hrmRoleService.getAllData();
                List<HrmRole> targetRole = selectedHrmUser.getRoles();
                boolean a=sourceSpiRole.removeAll(targetRole);
                
                dualListModel = new DualListModel<>(sourceSpiRole, targetRole);
            } else {
                List<HrmRole> sourceSpiRole = this.hrmRoleService.getAllData();
                dualListModel.setSource(sourceSpiRole);
                userModel = new UserModel();
                isEdit = Boolean.FALSE;
            }
            passwordComplexity = passwordComplexityService.getByCode(HRMConstant.PASSWORD_CONFIG_CODE);
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }
    
    @PreDestroy
    public void cleanAndExit() {
        dualListModel = null;
        hrmUserService = null;
        hrmRoleService = null;
        passwordComplexity = null;
        passwordComplexityService = null;
        userModel = null;
        isEdit = null;
    }
    
    public void setPasswordComplexityService(PasswordComplexityService passwordComplexityService) {
        this.passwordComplexityService = passwordComplexityService;
    }
   
    public void setHrmUserService(HrmUserService hrmUserService) {
        this.hrmUserService = hrmUserService;
    }

    public void setHrmRoleService(HrmRoleService hrmRoleService) {
        this.hrmRoleService = hrmRoleService;
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
    
    public Boolean getIsEdit() {
        return isEdit;
    }

    public void setIsEdit(Boolean isEdit) {
        this.isEdit = isEdit;
    }
    
    public PasswordComplexity getPasswordComplexity() {
        return passwordComplexity;
    }

    public void setPasswordComplexity(PasswordComplexity passwordComplexity) {
        this.passwordComplexity = passwordComplexity;
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
                hrmUserService.saveAndNotification(hrmUser);
                MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully",
                        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
                return "/protected/account/user_detail.htm?faces-redirect=true&execution=e" + hrmUser.getId();

            }
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
        return null;
    }

    private String doUpdate(HrmUser hrmUser) {
        try {
            HrmUser hrmUserExixting = hrmUserService.getEntiyByPK(hrmUser.getId());
            boolean isDuplicateUserId = (hrmUserService.getByUserId(hrmUser.getUserId()) != null && !StringUtils.equals(hrmUserExixting.getUserId(), hrmUser.getUserId()));
            boolean isDuplicateEmailAddress = (hrmUserService.getByEmailAddress(hrmUser.getEmailAddress()) != null && !StringUtils.equals(hrmUserExixting.getEmailAddress(), hrmUser.getEmailAddress()));
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
                hrmUserService.update(hrmUser);
                MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.update_successfully",
                        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
                return "/protected/account/user_detail.htm?faces-redirect=true&execution=e" + hrmUser.getId();
            }
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
        return null;
    }
    
    public void doSearchEmployee() {        
        Map<String, Object> options = new HashMap<>();
        options.put("modal", false);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 700);
        options.put("contentHeight", 350);
        RequestContext.getCurrentInstance().openDialog("user_employee_search", options, null);
    }

    public void onDialogReturnEmployee(SelectEvent event) {
        EmpData empData = (EmpData) event.getObject();
        userModel.setEmpDataId(empData.getId());
        userModel.setEmpDataFullName(empData.getNikWithFullName());
        userModel.setRealName(empData.getBioData().getFullName());
    }

    public HrmUser getEntityFromView(UserModel userModel) {
        HrmUser hrmUser = new HrmUser();
        String phoneNumber = userModel.getCountryPhoneCode()+""+userModel.getPhoneNumbers();
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
        hrmUser.setPhoneNumber(phoneNumber);
        hrmUser.setPhoneCode(userModel.getCountryPhoneCode());
        hrmUser.setRealName(userModel.getRealName());
        hrmUser.setUserId(userModel.getUserId());
        if(userModel.getEmpDataId() != null){
        	hrmUser.setEmpData(new EmpData(userModel.getEmpDataId()));
        }
        return hrmUser;
    }

    public UserModel getUserModelFromEntity(HrmUser hrmUser) {
        UserModel us = new UserModel();
      //replace phone number, ex: from +6285720123456 to 085720123456
    	if(hrmUser.getPhoneCode() != null){
	    	String phoneWithCode = hrmUser.getPhoneNumber();
	    	System.out.println(phoneWithCode + " - " + hrmUser.getPhoneNumber() + " - " + hrmUser.getPhoneCode().length() + " - " + hrmUser.getPhoneNumber().length());
	    	String phoneNumberWithoutCode = phoneWithCode.substring(hrmUser.getPhoneCode().length(), hrmUser.getPhoneNumber().length());
	        System.out.println(phoneNumberWithoutCode);
	        us.setPhoneNumbers(Long.valueOf(phoneNumberWithoutCode));
	        us.setCountryPhoneCode(hrmUser.getPhoneCode());
    	}
        us.setId(hrmUser.getId());
        us.setEmailAddress(hrmUser.getEmailAddress());
        if (Objects.equals(hrmUser.getIsActive(), HRMConstant.ACTIVE)) {
            us.setIsActive(Boolean.TRUE);
        } else {
            us.setIsActive(Boolean.FALSE);
        }
        if (Objects.equals(hrmUser.getIsExpired(), HRMConstant.EXPIRED)) {
            us.setIsExpired(Boolean.TRUE);
        } else {
            us.setIsExpired(Boolean.FALSE);
        }
        if (Objects.equals(hrmUser.getIsLock(), HRMConstant.LOCK)) {
            us.setIsLock(Boolean.TRUE);
        } else {
            us.setIsLock(Boolean.FALSE);
        }
        us.setPassword(hrmUser.getPassword());
        us.setPhoneNumber(hrmUser.getPhoneNumber());
        us.setRealName(hrmUser.getRealName());
        us.setUserId(hrmUser.getUserId());
        if(hrmUser.getEmpData() != null){
	        us.setEmpDataId(hrmUser.getEmpData().getId());
	        us.setEmpDataFullName(hrmUser.getEmpData().getNikWithFullName());
        }
        return us;
    }    
}
