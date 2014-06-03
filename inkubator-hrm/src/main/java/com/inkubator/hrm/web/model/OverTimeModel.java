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
 * @author Deni Husni FR
 */
public class OverTimeModel implements Serializable {

    private Long id;
    private String code;
    private String name;
    private String description;
    private Date minimumTime;
    private Date maximumTime;
    private Integer overTimeCalculation;
    private Integer otRounding;
    private Date startTimeFactor;
    private Date finishTimeFactor;
    private Double valuePrice;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getMinimumTime() {
        return minimumTime;
    }

    public void setMinimumTime(Date minimumTime) {
        this.minimumTime = minimumTime;
    }

    public Date getMaximumTime() {
        return maximumTime;
    }

    public void setMaximumTime(Date maximumTime) {
        this.maximumTime = maximumTime;
    }

    public Integer getOverTimeCalculation() {
        return overTimeCalculation;
    }

    public void setOverTimeCalculation(Integer overTimeCalculation) {
        this.overTimeCalculation = overTimeCalculation;
    }

    public Integer getOtRounding() {
        return otRounding;
    }

    public void setOtRounding(Integer otRounding) {
        this.otRounding = otRounding;
    }

    public Date getStartTimeFactor() {
        return startTimeFactor;
    }

    public void setStartTimeFactor(Date startTimeFactor) {
        this.startTimeFactor = startTimeFactor;
    }

    public Date getFinishTimeFactor() {
        return finishTimeFactor;
    }

    public void setFinishTimeFactor(Date finishTimeFactor) {
        this.finishTimeFactor = finishTimeFactor;
    }

    public Double getValuePrice() {
        return valuePrice;
    }

    public void setValuePrice(Double valuePrice) {
        this.valuePrice = valuePrice;
    }
    
    

}
