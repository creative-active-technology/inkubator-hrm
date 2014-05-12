/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.account;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.HrmRole;
import com.inkubator.hrm.service.HrmRoleService;
import com.inkubator.hrm.web.lazymodel.HrmRoleLazyModel;
import com.inkubator.hrm.web.search.HrmRoleSearchParameter;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author Deni Husni FR
 */
@ManagedBean(name = "roleController")
@ViewScoped
public class RoleController extends BaseController {

    private HrmRoleSearchParameter hrmRoleSearchParameter;
    private LazyDataModel<HrmRole> lazyDataHrmRole;
    @ManagedProperty(value = "#{hrmRoleService}")
    private HrmRoleService hrmRoleService;
    private HrmRole selectedHrmRole;

    public HrmRole getSelectedHrmRole() {
        return selectedHrmRole;
    }

    public void setSelectedHrmRole(HrmRole selectedHrmRole) {
        this.selectedHrmRole = selectedHrmRole;
    }

    public void setHrmRoleService(HrmRoleService hrmRoleService) {
        this.hrmRoleService = hrmRoleService;
    }

    public HrmRoleSearchParameter getHrmRoleSearchParameter() {
        return hrmRoleSearchParameter;
    }

    public void setHrmRoleSearchParameter(HrmRoleSearchParameter hrmRoleSearchParameter) {
        this.hrmRoleSearchParameter = hrmRoleSearchParameter;
    }

    public LazyDataModel<HrmRole> getLazyDataHrmRole() {
        if (lazyDataHrmRole == null) {
            lazyDataHrmRole = new HrmRoleLazyModel(hrmRoleSearchParameter, hrmRoleService);
        }
        return lazyDataHrmRole;
    }

    public void setLazyDataHrmRole(LazyDataModel<HrmRole> lazyDataHrmRole) {
        this.lazyDataHrmRole = lazyDataHrmRole;
    }

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        hrmRoleSearchParameter = new HrmRoleSearchParameter();

    }

//    public String doSave() {
//        String redirect = null;
//        HrmRole hrmRole = fromPageUIToEntity();
//        try {
//            if (isEdit) {
//                hrmRole.setUpdatedBy(UserInfoUtil.getUserName());
//                hrmRole.setUpdatedOn(new Date());
//                hrmRole.setId(selectedHrmRole.getId());
//                hrmRoleService.update(hrmRole);
//                MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save", "global.update_konfirmasi",
//                        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
//            } else {
//                hrmRole.setCreatedBy(UserInfoUtil.getUserName());
//                hrmRole.setCreatedOn(new Date());
//                hrmRoleService.save(hrmRole);
//                MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save", "global.save_konfirmasi",
//                        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
//            }
//            lazyDataHrmRole = null;
//            doClearInput();
//            isEdit = Boolean.FALSE;
//            redirect = "/protected/account/role_detail.htm?faces-redirect=true&execution=e" + hrmRole.getId();
//        } catch (Exception ex) {
//            LOGGER.error("Error", ex);
//            if (ex.getCause().toString().equalsIgnoreCase("org.hibernate.exception.ConstraintViolationException: could not execute statement")) {
//                MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_FATAL, "global.save_error", "roleform.error_duplicate",
//                        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
//            }
//            redirect = null;
//        }
//        return redirect;
//    }
//    private HrmRole fromPageUIToEntity() {
//        HrmRole hrmRole = new HrmRole();
//        hrmRole.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(15)));
//        hrmRole.setRoleName(roleModel.getRoleName());
//        hrmRole.setDescription(roleModel.getDescription());
//        return hrmRole;
//    }
//    private void doClearInput() {
//        roleModel = new RoleModel();
//    }
    public void doSearch() {
        lazyDataHrmRole = null;
    }

    @PreDestroy
    public void onPostClose() {
        System.out.println("Bersih -berisesfsdhfkh");
    }

    public String doDetail() {
        String redirect = null;
        try {
//            selectedHrmRole = hrmRoleService.getEntiyByPK(selectedHrmRole.getId());
            redirect = "/protected/account/role_detail.htm?faces-redirect=true&execution=e" + selectedHrmRole.getId();
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
        return redirect;
    }

//    public void doEdit() {
//        isEdit = Boolean.TRUE;
//        try {
////            selectedHrmRole = hrmRoleService.getEntiyByPK(selectedHrmRole.getId());
//            roleModel.setRoleName(selectedHrmRole.getRoleName());
//            roleModel.setDescription(selectedHrmRole.getDescription());
//        } catch (Exception ex) {
//            LOGGER.error("Error", ex);
//        }
//    }
//    public void onDetail() {
//        try {
////            selectedHrmRole = hrmRoleService.getEntiyByPK(selectedHrmRole.getId());
//        } catch (Exception ex) {
//            LOGGER.error("Error", ex);
//        }
//    }
    public void doDelete() {
        try {
            hrmRoleService.delete(selectedHrmRole);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_WARN, "global.delete", "global.delete_info",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

//    public void newData() {
//        doClearInput();
//        isEdit = Boolean.FALSE;
//    }
    public void doClearAndReset() {
        initialization();
//        doClearInput();
    }

    public void doAdd() {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 400);
        options.put("contentHeight", 340);
//        Map<String, List<String>> dataToSend = new HashMap<>();
//        List<String> dataIsi = new ArrayList<>();
//        dataIsi.add("hahahah");
////        dataIsi.add("srwrwerwer");
//        dataToSend.put("data1", dataIsi);
////        options.put("height", "400");
//        options.put("contentHeight", "auto");
        RequestContext.getCurrentInstance().openDialog("role_form", options, null);
    }
    
    
    public void onDialogReturn(SelectEvent event) {
        System.out.println("hahahahhaaaaaaaaaaaaaaa");
    }
}
