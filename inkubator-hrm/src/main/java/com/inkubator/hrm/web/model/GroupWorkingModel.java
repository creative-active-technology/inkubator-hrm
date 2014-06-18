/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.model;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.webcore.util.FacesUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.TreeMap;

/**
 *
 * @author Deni Husni FR
 */
public class GroupWorkingModel implements Serializable {

    private Long id;
    private String code;
    private String name;
    private Date beginTime;
    private Date endTime;
    private Boolean isPeriodic;
    private String isPeriodicAsString;
    private Double workingTimePerday;
    private Double workingTimePerweek;
    private Boolean overtimeBasedOnAttendance;
    private String overtimeBasedOnAttendanceAsString;
    private Boolean overtimeBasedOnRequest;
    private String overtimeBasedOnRequestAsString;
    private List<ScheduleShiftModel> dataShiftModels = new ArrayList<>();
    private List<ScheduleShiftModel> dataToShow = new ArrayList<>();
    private Map<String, Long> mapData = new TreeMap<>();
    private int pageNumber;
    private Boolean isDisable;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Boolean getIsPeriodic() {
        return isPeriodic;
    }

    public void setIsPeriodic(Boolean isPeriodic) {
        this.isPeriodic = isPeriodic;
    }

    public Double getWorkingTimePerday() {
        return workingTimePerday;
    }

    public void setWorkingTimePerday(Double workingTimePerday) {
        this.workingTimePerday = workingTimePerday;
    }

    public Double getWorkingTimePerweek() {
        return workingTimePerweek;
    }

    public void setWorkingTimePerweek(Double workingTimePerweek) {
        this.workingTimePerweek = workingTimePerweek;
    }

    public Boolean getOvertimeBasedOnAttendance() {
        return overtimeBasedOnAttendance;
    }

    public void setOvertimeBasedOnAttendance(Boolean overtimeBasedOnAttendance) {
        this.overtimeBasedOnAttendance = overtimeBasedOnAttendance;
    }

    public Boolean getOvertimeBasedOnRequest() {
        return overtimeBasedOnRequest;
    }

    public void setOvertimeBasedOnRequest(Boolean overtimeBasedOnRequest) {
        this.overtimeBasedOnRequest = overtimeBasedOnRequest;
    }

    public List<ScheduleShiftModel> getDataShiftModels() {
        return dataShiftModels;
    }

    public void setDataShiftModels(List<ScheduleShiftModel> dataShiftModels) {
        this.dataShiftModels = dataShiftModels;
    }

    public List<ScheduleShiftModel> getDataToShow() {
        return dataToShow;
    }

    public void setDataToShow(List<ScheduleShiftModel> dataToShow) {
        this.dataToShow = dataToShow;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Map<String, Long> getMapData() {
        return mapData;
    }

    public void setMapData(Map<String, Long> mapData) {
        this.mapData = mapData;
    }

    public Boolean getIsDisable() {
        return isDisable;
    }

    public void setIsDisable(Boolean isDisable) {
        this.isDisable = isDisable;
    }

    public String getIsPeriodicAsString() {
        ResourceBundle messages = ResourceBundle.getBundle("messages", new Locale(FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString()));
        if (Objects.equals(isPeriodic, Boolean.TRUE)) {
            isPeriodicAsString = messages.getString("global.yes");
        } else {
            isPeriodicAsString = messages.getString("global.no");
        }
        return isPeriodicAsString;
    }

    public void setIsPeriodicAsString(String isPeriodicAsString) {
        this.isPeriodicAsString = isPeriodicAsString;
    }

    public String getOvertimeBasedOnAttendanceAsString() {
        ResourceBundle messages = ResourceBundle.getBundle("messages", new Locale(FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString()));
        if (Objects.equals(overtimeBasedOnAttendance, Boolean.TRUE)) {
            overtimeBasedOnAttendanceAsString = messages.getString("global.yes");
        } else {
            overtimeBasedOnAttendanceAsString = messages.getString("global.no");
        }
        return overtimeBasedOnAttendanceAsString;
    }

    public void setOvertimeBasedOnAttendanceAsString(String overtimeBasedOnAttendanceAsString) {
        this.overtimeBasedOnAttendanceAsString = overtimeBasedOnAttendanceAsString;
    }

    public String getOvertimeBasedOnRequestAsString() {
        ResourceBundle messages = ResourceBundle.getBundle("messages", new Locale(FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString()));
        if (Objects.equals(overtimeBasedOnRequest, Boolean.TRUE)) {
            overtimeBasedOnRequestAsString = messages.getString("global.yes");
        } else {
            overtimeBasedOnRequestAsString = messages.getString("global.no");
        }

        return overtimeBasedOnRequestAsString;
    }

    public void setOvertimeBasedOnRequestAsString(String overtimeBasedOnRequestAsString) {
        this.overtimeBasedOnRequestAsString = overtimeBasedOnRequestAsString;
    }

}
