/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.workingtime;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.AttendanceStatus;
import com.inkubator.hrm.service.AttendanceStatusService;
import com.inkubator.hrm.web.lazymodel.AttendanceStatusLazyDataModel;
import com.inkubator.hrm.web.search.AttendanceStatusSearchParamater;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.hibernate.exception.ConstraintViolationException;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.springframework.dao.DataIntegrityViolationException;

/**
 *
 * @author Deni Husni FR
 */
@ManagedBean(name = "mecineFingerViewController")
@ViewScoped
public class MecineFingerViewController extends BaseController {

    private AttendanceStatusSearchParamater attendanceStatusSearchParamater;
    private LazyDataModel<AttendanceStatus> attendanceStatusesLazyDataModel;
    @ManagedProperty(value = "#{attendanceStatusService}")
    private AttendanceStatusService attendanceStatusService;
    private AttendanceStatus selecedAttendanceStatus;

    public void setAttendanceStatusService(AttendanceStatusService attendanceStatusService) {
        this.attendanceStatusService = attendanceStatusService;
    }

    public AttendanceStatusSearchParamater getAttendanceStatusSearchParamater() {
        return attendanceStatusSearchParamater;
    }

    public void setAttendanceStatusSearchParamater(AttendanceStatusSearchParamater attendanceStatusSearchParamater) {
        this.attendanceStatusSearchParamater = attendanceStatusSearchParamater;
    }

    public LazyDataModel<AttendanceStatus> getAttendanceStatusesLazyDataModel() {
        if (attendanceStatusesLazyDataModel == null) {
            attendanceStatusesLazyDataModel = new AttendanceStatusLazyDataModel(attendanceStatusSearchParamater, attendanceStatusService);
        }
        return attendanceStatusesLazyDataModel;
    }

    public void setAttendanceStatusesLazyDataModel(LazyDataModel<AttendanceStatus> attendanceStatusesLazyDataModel) {
        this.attendanceStatusesLazyDataModel = attendanceStatusesLazyDataModel;
    }

    public AttendanceStatus getSelecedAttendanceStatus() {
        return selecedAttendanceStatus;
    }

    public void setSelecedAttendanceStatus(AttendanceStatus selecedAttendanceStatus) {
        this.selecedAttendanceStatus = selecedAttendanceStatus;
    }

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        attendanceStatusSearchParamater = new AttendanceStatusSearchParamater();

    }

    public void doSearch() {
        attendanceStatusesLazyDataModel = null;
    }

   
    public void doDetail() {
        try {
            selecedAttendanceStatus = attendanceStatusService.getEntiyByPK(selecedAttendanceStatus.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public void doDelete() {
        try {
            this.attendanceStatusService.delete(selecedAttendanceStatus);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            LOGGER.error("Error", ex);
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public void doAdd() {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 400);
        options.put("contentHeight", 440);
        RequestContext.getCurrentInstance().openDialog("attendance_status_form", options, null);
    }

    public void doEdit() {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 400);
        options.put("contentHeight", 440);
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> dataIsi = new ArrayList<>();
        dataIsi.add(String.valueOf(selecedAttendanceStatus.getId()));
        dataToSend.put("param", dataIsi);
        RequestContext.getCurrentInstance().openDialog("attendance_status_form", options, dataToSend);
    }

    @Override
    public void onDialogReturn(SelectEvent event) {
        attendanceStatusesLazyDataModel = null;
       super.onDialogReturn(event);
    }

    public void onDelete() {
        try {
            selecedAttendanceStatus = attendanceStatusService.getEntiyByPK(selecedAttendanceStatus.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    @PreDestroy
    private void cleanAndExit() {
        attendanceStatusSearchParamater = null;
        attendanceStatusesLazyDataModel=null;
        attendanceStatusService=null;
        selecedAttendanceStatus=null;
        
        
    }
}
