/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inkubator.hrm.web.search;

import com.inkubator.webcore.util.SearchParameter;

/**
 *
 * @author Deni Husni FR
 */
public class WtOverTimeSearchParameter extends SearchParameter{
    private String overTimecode;
    private String overTimeName;
    private Double minTime;
    private Double maxTime;

    public String getOverTimecode() {
         if (getKeyParam() != null) {
            if (getKeyParam().equalsIgnoreCase("overTimecode")) {
                overTimecode = getParameter();
            }else{
                overTimecode=null;
            }
        }
        return overTimecode;
    }

    public void setOverTimecode(String overTimecode) {
        this.overTimecode = overTimecode;
    }

    public String getOverTimeName() {
        if (getKeyParam() != null) {
            if (getKeyParam().equalsIgnoreCase("overTimeName")) {
                overTimeName = getParameter();
            }
        }
        return overTimeName;
    }

    public void setOverTimeName(String overTimeName) {
        this.overTimeName = overTimeName;
    }

    public Double getMinTime() {
         if (getKeyParam() != null) {
            if (getKeyParam().equalsIgnoreCase("minTime")&& getParameter()!=null) {
                minTime = Double.parseDouble(getParameter());
            }
        }
        return minTime;
    }

    public void setMinTime(Double minTime) {
        this.minTime = minTime;
    }

    public Double getMaxTime() {
        if (getKeyParam() != null) {
            if (getKeyParam().equalsIgnoreCase("maxTime")&& getParameter()!=null) {
                maxTime = Double.parseDouble(getParameter());
            }
        }
        return maxTime;
    }

    public void setMaxTime(Double maxTime) {
        this.maxTime = maxTime;
    }
    
    
}
