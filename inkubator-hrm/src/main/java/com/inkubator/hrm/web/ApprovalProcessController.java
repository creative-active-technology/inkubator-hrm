/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web;
//
//import com.inkubator.common.util.RandomNumberUtil;
//import com.inkubator.hrm.HRMConstant;
//import com.inkubator.hrm.entity.ProscessToApprove;
//import com.inkubator.hrm.service.ProscessToApproveService;
//import com.inkubator.hrm.web.lazymodel.ProcessApprovalLazyModel;
//import com.inkubator.hrm.web.search.ProscessToApproveSearchParameter;
//import com.inkubator.securitycore.util.UserInfoUtil;
//import com.inkubator.webcore.controller.BaseController;
//import com.inkubator.webcore.util.FacesUtil;
//import com.inkubator.webcore.util.MessagesResourceUtil;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import javax.annotation.PostConstruct;
//import javax.faces.application.FacesMessage;
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.ManagedProperty;
//import javax.faces.bean.ViewScoped;
//import org.primefaces.model.LazyDataModel;
//
///**
// *
// * @author Deni Husni FR
// */
//@ManagedBean(name = "approvalProcessController")
//@ViewScoped
//public class ApprovalProcessController extends BaseController {
//
//    private ProscessToApproveSearchParameter proscessToApproveSearchParameter;
//    private LazyDataModel<ProscessToApprove> lazyDataModelProscessToApprove;
//    private Map<String, String> mapProcess = new HashMap<>();
//    private List<String> listProcess = new ArrayList<>();
//    private String selectedProcess;
//    private String description;
//    private Boolean isEdit;
//    private ProscessToApprove selectedProscessToApprove;
//    @ManagedProperty(value = "#{proscessToApproveService}")
//    private ProscessToApproveService proscessToApproveService;
//
//    public void setProscessToApproveService(ProscessToApproveService proscessToApproveService) {
//        this.proscessToApproveService = proscessToApproveService;
//    }
//
//    public ProscessToApprove getSelectedProscessToApprove() {
//        return selectedProscessToApprove;
//    }
//
//    public void setSelectedProscessToApprove(ProscessToApprove selectedProscessToApprove) {
//        this.selectedProscessToApprove = selectedProscessToApprove;
//    }
//
//    public ProscessToApproveSearchParameter getProscessToApproveSearchParameter() {
//        return proscessToApproveSearchParameter;
//    }
//
//    public void setProscessToApproveSearchParameter(ProscessToApproveSearchParameter proscessToApproveSearchParameter) {
//        this.proscessToApproveSearchParameter = proscessToApproveSearchParameter;
//    }
//
//    public LazyDataModel<ProscessToApprove> getLazyDataModelProscessToApprove() {
//        if (lazyDataModelProscessToApprove == null) {
//            lazyDataModelProscessToApprove = new ProcessApprovalLazyModel(proscessToApproveSearchParameter, proscessToApproveService);
//        }
//        return lazyDataModelProscessToApprove;
//    }
//
//    public void setLazyDataModelProscessToApprove(LazyDataModel<ProscessToApprove> lazyDataModelProscessToApprove) {
//        this.lazyDataModelProscessToApprove = lazyDataModelProscessToApprove;
//    }
//
//    @PostConstruct
//    @Override
//    public void initialization() {
//        super.initialization();
//        if (FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).equals("in")) {
//            mapProcess.put(HRMConstant.APPROVAL_PROCESS_CREATE_USER_ID, "CREATE USER");
//            mapProcess.put(HRMConstant.APPROVAL_PROCESS_UPDATE_USER_ID, "UPDATE USER");
//            mapProcess.put(HRMConstant.APPROVAL_PROCESS_DELETE_USER_ID, "DELETE USER");
//            mapProcess.put(HRMConstant.APPROVAL_PROCESS_REQUEST_LEAP_ID, "LEAP REQUEST");
//            mapProcess.put(HRMConstant.APPROVAL_PROCESS_REQUEST_SICK_ID, "SICK REQUEST");
//            mapProcess.put(HRMConstant.APPROVAL_PROCESS_REQUEST_PERMIT_ID, "PERMIT REQUEST");
//        } else {
//            mapProcess.put(HRMConstant.APPROVAL_PROCESS_CREATE_USER_EN, "CREATE USER");
//            mapProcess.put(HRMConstant.APPROVAL_PROCESS_UPDATE_USER_EN, "UPDATE USER");
//            mapProcess.put(HRMConstant.APPROVAL_PROCESS_DELETE_USER_EN, "DELETE USER");
//            mapProcess.put(HRMConstant.APPROVAL_PROCESS_REQUEST_LEAP_EN, "LEAP REQUEST");
//            mapProcess.put(HRMConstant.APPROVAL_PROCESS_REQUEST_SICK_EN, "SICK REQUEST");
//            mapProcess.put(HRMConstant.APPROVAL_PROCESS_REQUEST_PERMIT_EN, "PERMIT REQUEST");
//        }
//        proscessToApproveSearchParameter = new ProscessToApproveSearchParameter();
//        isEdit = Boolean.FALSE;
//    }
//
//    public String getSelectedProcess() {
//        return selectedProcess;
//    }
//
//    public void setSelectedProcess(String selectedProcess) {
//        this.selectedProcess = selectedProcess;
//    }
//
//    public List<String> getListProcess() {
//        return listProcess;
//    }
//
//    public void setListProcess(List<String> listProcess) {
//        this.listProcess = listProcess;
//    }
//
//    private void doClearInput() {
//        selectedProcess = null;
//        description = null;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public Boolean getIsEdit() {
//        return isEdit;
//    }
//
//    public void setIsEdit(Boolean isEdit) {
//        this.isEdit = isEdit;
//    }
//
//    public ProscessToApprove fromPageUIToEntity() {
//        ProscessToApprove proscessToApprove = new ProscessToApprove();
//        proscessToApprove.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(15)));
//        proscessToApprove.setCode(selectedProcess);
//        proscessToApprove.setDescription(description);
//        return proscessToApprove;
//    }
//
//    public String doSave() {
//        String redirect;
//        ProscessToApprove proscessToApprove = fromPageUIToEntity();
//        try {
//
//            if (isEdit) {
//                proscessToApprove.setUpdatedBy(UserInfoUtil.getUserName());
//                proscessToApprove.setUpdatedOn(new Date());
//                proscessToApprove.setId(selectedProscessToApprove.getId());
//                proscessToApproveService.update(proscessToApprove);
//                MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save", "global.update_konfirmasi",
//                        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
//            } else {
//                System.out.println(selectedProcess);
//                proscessToApprove.setCreatedBy(UserInfoUtil.getUserName());
//                proscessToApprove.setCreatedOn(new Date());
//                proscessToApproveService.save(proscessToApprove);
//                MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save", "global.save_konfirmasi",
//                        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
//            }
//            lazyDataModelProscessToApprove = null;
//            doClearInput();
//            isEdit = Boolean.FALSE;
//            redirect = "/protected/approval/approval_process_detail.htm?faces-redirect=true&execution=e" + proscessToApprove.getId();
//        } catch (Exception e) {
//            LOGGER.error("Error", e);
//            if (e.getCause().toString().equalsIgnoreCase("org.hibernate.exception.ConstraintViolationException: could not execute statement")) {
//                MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.save_error", "roleform.error_duplicate",
//                        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
//            }
//            redirect = null;
//        }
//        return redirect;
//    }
//
//    public Map<String, String> getMapProcess() {
//        return mapProcess;
//    }
//
//    public void setMapProcess(Map<String, String> mapProcess) {
//        this.mapProcess = mapProcess;
//    }
//
//    public void doClearAndReset() {
//        initialization();
//        doClearInput();
//    }
//
//    public String doDetail() {
//        return "/protected/approval/approval_process_detail.htm?faces-redirect=true&execution=e" + selectedProscessToApprove.getId();
//
//    }
//
//    public void doEdit() {
//        System.out.println(selectedProscessToApprove.getId());
//        isEdit = Boolean.TRUE;
//        try {
////            selectedProscessToApprove = proscessToApproveService.getEntiyByPK(selectedProscessToApprove.getId());
//            selectedProcess = selectedProscessToApprove.getCode();
//            description = selectedProscessToApprove.getDescription();
//        } catch (Exception ex) {
//            LOGGER.error("Error", ex);
//        }
//    }
//
//    public void doDelete() {
//        try {
//            proscessToApproveService.delete(selectedProscessToApprove);
//            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_WARN, "global.delete", "global.delete_info",
//                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
//        } catch (Exception ex) {
//            LOGGER.error("Error", ex);
//        }
//    }
//
//    public void doSearch() {
//        lazyDataModelProscessToApprove = null;
//    }
//}
