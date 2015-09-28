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
public class SchedullerConfigModel implements Serializable {

    private long id;
    private String name;
    private String schedullerType;
    private String repeateType;
    private Date dateStartExecution;
    private Date startDate;
    private Date endDate;
    private Integer repeateNumber;
    private Date schedullerTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    
    
}
