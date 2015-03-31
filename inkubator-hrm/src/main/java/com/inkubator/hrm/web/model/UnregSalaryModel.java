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
 * @author deni
 */
public class UnregSalaryModel implements Serializable{
    private Long id;
    private String code;
    private String name;
    private Date startPeriodDate;
    private Date endPeriodDate;
    private Date salaryDate;
    private Integer month;
    private String year;
    private Long payComponentId;

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

    public Date getStartPeriodDate() {
        return startPeriodDate;
    }

    public void setStartPeriodDate(Date startPeriodDate) {
        this.startPeriodDate = startPeriodDate;
    }

    public Date getEndPeriodDate() {
        return endPeriodDate;
    }

    public void setEndPeriodDate(Date endPeriodDate) {
        this.endPeriodDate = endPeriodDate;
    }

    public Date getSalaryDate() {
        return salaryDate;
    }

    public void setSalaryDate(Date salaryDate) {
        this.salaryDate = salaryDate;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Long getPayComponentId() {
        return payComponentId;
    }

    public void setPayComponentId(Long payComponentId) {
        this.payComponentId = payComponentId;
    }
}
