/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.organisation;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.HrmUser;
import com.inkubator.hrm.service.DepartmentService;
import com.inkubator.hrm.service.UnitKerjaService;
import com.inkubator.hrm.web.model.JabatanModel;
import com.inkubator.hrm.web.model.UserModel;
import com.inkubator.webcore.controller.BaseController;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Deni Husni FR
 */
@ManagedBean(name = "jabatanFormController")
@ViewScoped
public class JabatanFormController extends BaseController {

    @ManagedProperty(value = "#{departmentService}")
    private DepartmentService departmentService;
    @ManagedProperty(value = "#{unitKerjaService}")
    private UnitKerjaService unitKerjaService;

    private Boolean isEdit;
    private JabatanModel jabatanModel;
    private Map<String, Long> untiKerjas = new TreeMap<>();
    private Map<String, Long> golJabatans = new TreeMap<>();
    private Map<String, Long> departments = new TreeMap<>();
    private Map<String, Long> posBiayas = new TreeMap<>();

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        jabatanModel = new JabatanModel();

    }

    public String doBack() {
        return "/protected/account/user_view.htm?faces-redirect=true";
    }

    public String doSave() {
//        HrmUser hrmUser = getEntityFromView(userModel);
//        if (isEdit) {
//            return doUpdate(hrmUser);
//        } else {
//            return doInsert(hrmUser);
//        }
        return null;
    }

//    private String doInsert(HrmUser hrmUser) {
//        hrmUser.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(12)));
//        try {
//            boolean isDuplicateUserId = hrmUserService.getByUserId(hrmUser.getUserId()) != null;
//            boolean isDuplicateEmailAddress = hrmUserService.getByEmailAddress(hrmUser.getEmailAddress()) != null;
//            if (isDuplicateUserId) {
//                MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "user_form.duplicate_user_name",
//                        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
//                return null;
//            } else if (isDuplicateEmailAddress) {
//                MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "user_form.duplicate_email_address",
//                        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
//                return null;
//            } else {
//                Set<HrmUserRole> dataToSave = new HashSet<>();
//                List<HrmRole> hrmRoles = dualListModel.getTarget();
//                for (HrmRole hrmRole : hrmRoles) {
//                    HrmUserRole hrmUserRole = new HrmUserRole();
//                    hrmUserRole.setId(new HrmUserRoleId(hrmUser.getId(), hrmRole.getId()));
//                    hrmUserRole.setHrmRole(hrmRole);
//                    hrmUserRole.setHrmUser(hrmUser);
//                    dataToSave.add(hrmUserRole);
//                }
//                hrmUser.setHrmUserRoles(dataToSave);
//                hrmUserService.saveAndNotification(hrmUser);
//                MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully",
//                        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
//                return "/protected/account/user_detail.htm?faces-redirect=true&execution=e" + hrmUser.getId();
//
//            }
//        } catch (Exception ex) {
//            LOGGER.error("Error", ex);
//        }
//        return null;
//    }
//
//    private String doUpdate(HrmUser hrmUser) {
//        try {
//            HrmUser hrmUserExixting = hrmUserService.getEntiyByPK(hrmUser.getId());
//            boolean isDuplicateUserId = (hrmUserService.getByUserId(hrmUser.getUserId()) != null && !StringUtils.equals(hrmUserExixting.getUserId(), hrmUser.getUserId()));
//            boolean isDuplicateEmailAddress = (hrmUserService.getByEmailAddress(hrmUser.getEmailAddress()) != null && !StringUtils.equals(hrmUserExixting.getEmailAddress(), hrmUser.getEmailAddress()));
//            if (isDuplicateUserId) {
//                MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "user_form.duplicate_user_name",
//                        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
//                return null;
//            } else if (isDuplicateEmailAddress) {
//                MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "user_form.duplicate_email_address",
//                        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
//                return null;
//            } else {
//                Set<HrmUserRole> dataToSave = new HashSet<>();
//                List<HrmRole> hrmRoles = dualListModel.getTarget();
//                for (HrmRole hrmRole : hrmRoles) {
//                    HrmUserRole hrmUserRole = new HrmUserRole();
//                    hrmUserRole.setId(new HrmUserRoleId(hrmUser.getId(), hrmRole.getId()));
//                    hrmUserRole.setHrmRole(hrmRole);
//                    hrmUserRole.setHrmUser(hrmUser);
//                    dataToSave.add(hrmUserRole);
//                }
//                hrmUser.setHrmUserRoles(dataToSave);
//                hrmUserService.update(hrmUser);
//                MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.update_successfully",
//                        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
//                return "/protected/account/user_detail.htm?faces-redirect=true&execution=e" + hrmUser.getId();
//            }
//        } catch (Exception ex) {
//            LOGGER.error("Error", ex);
//        }
//        return null;
//    }
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
        hrmUser.setRealName(userModel.getRealName());
        hrmUser.setUserId(userModel.getUserId());
        return hrmUser;
    }

    public UserModel getUserModelFromEntity(HrmUser hrmUser) {
        UserModel us = new UserModel();
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
        return us;
    }

    @PreDestroy
    public void cleanAndExit() {

    }

    public JabatanModel getJabatanModel() {
        return jabatanModel;
    }

    public void setJabatanModel(JabatanModel jabatanModel) {
        this.jabatanModel = jabatanModel;
    }

    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    public void setUnitKerjaService(UnitKerjaService unitKerjaService) {
        this.unitKerjaService = unitKerjaService;
    }
    
    

}
