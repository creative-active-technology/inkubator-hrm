/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.search;

import com.inkubator.webcore.util.SearchParameter;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Deni Husni FR
 */
public class HolidaySearchParameter extends SearchParameter {

    private String religionName;
    private String holidayName;
    private Boolean isEveryYear;
    private Boolean isCollective;

    public String getReligionName() {
        if (StringUtils.equalsIgnoreCase(getKeyParam(), "religionName")) {
            religionName = getParameter();
        } else {
            religionName = null;
        }

        return religionName;
    }

    public void setReligionName(String religionName) {
        this.religionName = religionName;
    }

    public String getHolidayName() {

        if (StringUtils.equalsIgnoreCase(getKeyParam(), "holidayName")) {
            holidayName = getParameter();
        } else {
            holidayName = null;
        }

        return holidayName;
    }

    public void setHolidayName(String holidayName) {
        this.holidayName = holidayName;
    }

    public Boolean isIsEveryYear() {
        return isEveryYear;
    }

    public void setIsEveryYear(Boolean isEveryYear) {
        this.isEveryYear = isEveryYear;
    }

    public Boolean isIsCollective() {
        return isCollective;
    }

    public void setIsCollective(Boolean isCollective) {
        this.isCollective = isCollective;
    }

}
