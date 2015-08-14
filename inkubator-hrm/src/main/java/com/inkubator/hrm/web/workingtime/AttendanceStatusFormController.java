/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.workingtime;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.AttendanceStatus;
import com.inkubator.hrm.service.AttendanceStatusService;
import com.inkubator.hrm.web.model.AttendanceStatusModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Deni Husni FR
 */
@ManagedBean(name = "attendanceStatusFormController")
@ViewScoped
public class AttendanceStatusFormController extends BaseController {

    private AttendanceStatusModel attendanceStatusModel;
    @ManagedProperty(value = "#{attendanceStatusService}")
    private AttendanceStatusService attendanceStatusService;
    private Boolean isEdit;

    public AttendanceStatusModel getAttendanceStatusModel() {
        return attendanceStatusModel;
    }

    public void setAttendanceStatusModel(AttendanceStatusModel attendanceStatusModel) {
        this.attendanceStatusModel = attendanceStatusModel;
    }

    public void setAttendanceStatusService(AttendanceStatusService attendanceStatusService) {
        this.attendanceStatusService = attendanceStatusService;
    }

    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            String param = FacesUtil.getRequestParameter("param");
            attendanceStatusModel = new AttendanceStatusModel();
            if (param != null) {
                isEdit = Boolean.TRUE;
                AttendanceStatus attendanceStatus = attendanceStatusService.getEntiyByPK(Long.parseLong(param));
                attendanceStatusModel.setId(attendanceStatus.getId());
                attendanceStatusModel.setDescription(attendanceStatus.getDescription());
                attendanceStatusModel.setKodeStatus(attendanceStatus.getCode());
                attendanceStatusModel.setNamaStatus(attendanceStatus.getStatusKehadrian());
                attendanceStatusModel.setIsActive(attendanceStatus.getIsActive());

                if (attendanceStatus.getIsPay() == 1) {
                    attendanceStatusModel.setIsPay(Boolean.TRUE);
                }
                if (attendanceStatus.getIsPay() == 0) {
                    attendanceStatusModel.setIsPay(Boolean.FALSE);
                }

                if (attendanceStatus.getIsPresent() == 1) {
                    attendanceStatusModel.setIsPresent(Boolean.TRUE);
                }
                if (attendanceStatus.getIsPresent() == 0) {
                    attendanceStatusModel.setIsPresent(Boolean.FALSE);
                }
            } else {
                isEdit = Boolean.FALSE;
            }

        } catch (Exception ex) {
            LOGGER.error("Errot", ex);
        }

    }

    public void doSave() {
        AttendanceStatus attendanceStatus = getEntityFromViewModel(attendanceStatusModel);
        try {
            if (isEdit) {
                attendanceStatusService.update(attendanceStatus);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                attendanceStatusService.save(attendanceStatus);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch (BussinessException ex) { 
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
        cleanAndExit();
    }

    public Boolean getIsEdit() {
        return isEdit;
    }

    public void setIsEdit(Boolean isEdit) {
        this.isEdit = isEdit;
    }

    private AttendanceStatus getEntityFromViewModel(AttendanceStatusModel attendanceStatusModel) {
        AttendanceStatus attendanceStatus = new AttendanceStatus();
        if (attendanceStatusModel.getId() != null) {
            attendanceStatus.setId(attendanceStatusModel.getId());
        }
        attendanceStatus.setCode(attendanceStatusModel.getKodeStatus());
        attendanceStatus.setDescription(attendanceStatusModel.getDescription());
        if (attendanceStatusModel.getIsPay()) {
            attendanceStatus.setIsPay(1);
        } else {
            attendanceStatus.setIsPay(0);
        }
        if (attendanceStatusModel.getIsPresent()) {
            attendanceStatus.setIsPresent(1);
        } else {
            attendanceStatus.setIsPresent(0);
        }
        attendanceStatus.setStatusKehadrian(attendanceStatusModel.getNamaStatus());
        attendanceStatus.setIsActive(attendanceStatusModel.getIsActive());
        return attendanceStatus;
    }

    @PreDestroy
    private void cleanAndExit() {
        attendanceStatusModel = null;
        attendanceStatusService = null;
        isEdit = null;
    }
}
