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
public class HolidayModel implements Serializable{
    private Long id;
    private String holidayName;
    private Date holidayDate;
    private Long religionId;
    private Boolean isEveryYear;
    private Boolean isCollective;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHolidayName() {
        return holidayName;
    }

    public void setHolidayName(String holidayName) {
        this.holidayName = holidayName;
    }

    public Date getHolidayDate() {
        return holidayDate;
    }

    public void setHolidayDate(Date holidayDate) {
        this.holidayDate = holidayDate;
    }

    public Long getReligionId() {
        return religionId;
    }

    public void setReligionId(Long religionId) {
        this.religionId = religionId;
    }

    public Boolean getIsEveryYear() {
        return isEveryYear;
    }

    public void setIsEveryYear(Boolean isEveryYear) {
        this.isEveryYear = isEveryYear;
    }

    public Boolean getIsCollective() {
        return isCollective;
    }

    public void setIsCollective(Boolean isCollective) {
        this.isCollective = isCollective;
    }
    
    
    
}
