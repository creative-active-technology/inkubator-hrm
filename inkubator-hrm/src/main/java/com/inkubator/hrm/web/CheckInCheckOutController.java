/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web;

import com.inkubator.common.CommonUtilConstant;
import com.inkubator.common.util.DateTimeUtil;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.CheckInAttendance;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.HrmUser;
import com.inkubator.hrm.entity.TempJadwalKaryawan;
import com.inkubator.hrm.service.CheckInAttendanceService;
import com.inkubator.hrm.service.HrmUserService;
import com.inkubator.hrm.service.TempJadwalKaryawanService;
import com.inkubator.hrm.util.HrmUserInfoUtil;
import com.inkubator.hrm.web.model.CheckInOutModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;

/**
 *
 * @author Deni Husni FR
 */
@ManagedBean(name = "checkInCheckOutController")
@RequestScoped
public class CheckInCheckOutController extends BaseController {

    private String employeeName;
    private Long empId;
    @ManagedProperty(value = "#{hrmUserService}")
    private HrmUserService hrmUserService;
    @ManagedProperty(value = "#{tempJadwalKaryawanService}")
    private TempJadwalKaryawanService tempJadwalKaryawanService;
    @ManagedProperty(value = "#{checkInAttendanceService}")
    private CheckInAttendanceService checkInAttendanceService;
    private CheckInOutModel checkInOutModel;
    private Boolean isCheckIn;
    private String labelButton;
    private int bufferCheckOut;

    @PostConstruct
    @Override
    public void initialization() {
        try {
            checkInOutModel = new CheckInOutModel();
            Boolean isValid = HrmUserInfoUtil.isValidRemoteAddress();
            HrmUser hrmUser = hrmUserService.getUserWithDetail(HrmUserInfoUtil.getUserName());
            if (hrmUser.getEmpData() == null) {
                ExternalContext context = FacesUtil.getExternalContext();
                context.redirect(context.getRequestContextPath() + "/protected/home.htm");
            } else {
                String stringDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                Date date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(stringDate + " 00:00:00");
                TempJadwalKaryawan jadwalKaryawan = tempJadwalKaryawanService.getByEmpId(hrmUser.getEmpData().getId(), date);

                if (isValid) {
                    CheckInAttendance attendance = checkInAttendanceService.getByEmpIdAndCheckIn(hrmUser.getEmpData().getId(), date);
                    if (attendance == null) {
                        checkInOutModel.setEmpId(hrmUser.getEmpData().getId());
                        checkInOutModel.setUserName(hrmUser.getRealName());
                        checkInOutModel.setJadwalKerja(jadwalKaryawan.getTanggalWaktuKerja());
                        checkInOutModel.setBeginHour(jadwalKaryawan.getWtWorkingHour().getWorkingHourBegin());
                        checkInOutModel.setEndHour(jadwalKaryawan.getWtWorkingHour().getWorkingHourEnd());
                        checkInOutModel.setBreakTime(jadwalKaryawan.getWtWorkingHour().getBreakHourBegin() + " - " + jadwalKaryawan.getWtWorkingHour().getBreakHourEnd());
                        labelButton = "Check In";
                        isCheckIn = Boolean.FALSE;
                    } else {
                        checkInOutModel.setId(attendance.getId());
                        checkInOutModel.setEmpId(attendance.getEmpData().getId());
                        checkInOutModel.setUserName(hrmUser.getRealName());
                        checkInOutModel.setJadwalKerja(attendance.getCheckDate());
                        checkInOutModel.setTimeCheckIn(attendance.getCheckInTime());
                        checkInOutModel.setTimeCheckOut(attendance.getCheckOutTime());
                        checkInOutModel.setBeginHour(jadwalKaryawan.getWtWorkingHour().getWorkingHourBegin());
                        checkInOutModel.setEndHour(jadwalKaryawan.getWtWorkingHour().getWorkingHourEnd());
                        checkInOutModel.setBreakTime(jadwalKaryawan.getWtWorkingHour().getBreakHourBegin() + " - " + jadwalKaryawan.getWtWorkingHour().getBreakHourEnd());
                        isCheckIn = Boolean.TRUE;
                        labelButton = "Check Out";
                        bufferCheckOut = jadwalKaryawan.getWtWorkingHour().getGoHomeLimitBegin();
                    }

//                FacesUtil.getResponse().sendRedirect(FacesUtil.getRequest().getContextPath() + "/protected/check_in_out.htm");
                } else {
//                    MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully",
//                            FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
                    ExternalContext context = FacesUtil.getExternalContext();
                    context.redirect(context.getRequestContextPath() + "/protected/home.htm");
                }

            }

            super.initialization();
        } catch (Exception ex) {
            LOGGER.error("error", ex);
        }

    }

    @PreDestroy
    public void cleanAndExit() {

    }

    public void setHrmUserService(HrmUserService hrmUserService) {
        this.hrmUserService = hrmUserService;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    public void setTempJadwalKaryawanService(TempJadwalKaryawanService tempJadwalKaryawanService) {
        this.tempJadwalKaryawanService = tempJadwalKaryawanService;
    }

    public CheckInOutModel getCheckInOutModel() {
        return checkInOutModel;
    }

    public void setCheckInOutModel(CheckInOutModel checkInOutModel) {
        this.checkInOutModel = checkInOutModel;
    }

    public String doChechInOut() {
        try {
            CheckInAttendance attendance = new CheckInAttendance();
            String stringDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            // harus sperti ini membading kan 2 jam... yg berbeda
            Date date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(stringDate + " " + checkInOutModel.getBeginHour());
            if (!isCheckIn) {

                attendance.setCheckDate(new Date());
                attendance.setCheckInTime(new Date());

                if (date.after(new Date())) {

                    attendance.setNote(HRMConstant.CHECK_IN_EARLY);

                }
                if (date.before(new Date())) {
                    attendance.setNote(HRMConstant.CHECK_IN_LATE);
                }

                if (date.equals(new Date())) {
                    attendance.setNote(HRMConstant.CHECK_IN_ON_TIME);
                }
                attendance.setEmpData(new EmpData(checkInOutModel.getEmpId()));
                attendance.setIpAddress(HrmUserInfoUtil.getRequestRemoteAddrByJSF());
                attendance.setStatus(HRMConstant.CHECK_IN);
                checkInAttendanceService.save(attendance);
                MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "ceckinout.checkin_success",
                        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
                return "/protected/home.htm?faces-redirect=true";
            } else {
                Date date1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(stringDate + " " + checkInOutModel.getEndHour());
                Date canGoHome = DateTimeUtil.getDateFrom(date1, bufferCheckOut, CommonUtilConstant.DATE_FORMAT_MINUTES);
                if (canGoHome.after(new Date())) {
                    MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.error", "ceckinout.checkin_error_out",
                            FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
                    System.out.println(" ini terjadi");
                } else {
                    attendance.setId(checkInOutModel.getId());
                    attendance.setCheckOutTime(new Date());
                    checkInAttendanceService.update(attendance);
                    MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "ceckinout.checkout_success",
                            FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
                    return "/protected/home.htm?faces-redirect=true";
                }

            }

//           
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
        return null;
    }

    public void setCheckInAttendanceService(CheckInAttendanceService checkInAttendanceService) {
        this.checkInAttendanceService = checkInAttendanceService;
    }

    public Boolean getIsCheckIn() {
        return isCheckIn;
    }

    public void setIsCheckIn(Boolean isCheckIn) {
        this.isCheckIn = isCheckIn;
    }

    public String getLabelButton() {
        return labelButton;
    }

    public void setLabelButton(String labelButton) {
        this.labelButton = labelButton;
    }

}
