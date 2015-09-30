/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author denifahri
 */
public class SchedulerConfigModel implements Serializable {

    private Long id;
    private String name;
    private String schedullerType;
    private String repeateType;
    private Date dateStartExecution;
    private Date startDate;
    private Date endDate;
    private Integer repeateNumber;
    private Date schedullerTime;
    private Double timeDivExecution;
    private Boolean isTimeDiv;
    private Boolean isActive;
    private Integer hourDiv;
    private Integer minuteDiv;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchedullerType() {
        return schedullerType;
    }

    public void setSchedullerType(String schedullerType) {
        this.schedullerType = schedullerType;
    }

    public String getRepeateType() {
        return repeateType;
    }

    public void setRepeateType(String repeateType) {
        this.repeateType = repeateType;
    }

    public Date getDateStartExecution() {
        return dateStartExecution;
    }

    public void setDateStartExecution(Date dateStartExecution) {
        this.dateStartExecution = dateStartExecution;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getRepeateNumber() {
        return repeateNumber;
    }

    public void setRepeateNumber(Integer repeateNumber) {
        this.repeateNumber = repeateNumber;
    }

    public Date getSchedullerTime() {
        return schedullerTime;
    }

    public void setSchedullerTime(Date schedullerTime) {
        this.schedullerTime = schedullerTime;
    }

    public Double getTimeDivExecution() {
        return timeDivExecution;
    }

    public void setTimeDivExecution(Double timeDivExecution) {
        this.timeDivExecution = timeDivExecution;
    }

    public Boolean getIsTimeDiv() {
        return isTimeDiv;
    }

    public void setIsTimeDiv(Boolean isTimeDiv) {
        this.isTimeDiv = isTimeDiv;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Integer getHourDiv() {
        return hourDiv;
    }

    public void setHourDiv(Integer hourDiv) {
        this.hourDiv = hourDiv;
    }

    public Integer getMinuteDiv() {
        return minuteDiv;
    }

    public void setMinuteDiv(Integer minuteDiv) {
        this.minuteDiv = minuteDiv;
    }

    
}
