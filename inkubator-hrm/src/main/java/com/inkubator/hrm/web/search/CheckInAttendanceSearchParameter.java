/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.search;

import org.apache.commons.lang3.StringUtils;

import com.inkubator.webcore.util.SearchParameter;

/**
 *
 * @author Deni
 */
public class CheckInAttendanceSearchParameter extends SearchParameter{
     private String empData;
     private String ipAddress;
     private String status;

    public String getEmpData() {
    	if(StringUtils.equalsIgnoreCase(getKeyParam(), "empData")){
    		empData = getParameter();
        } else {
        	empData = null;
        }
        return empData;
    }

    public void setEmpData(String empData) {
        this.empData = empData;
    }

    public String getIpAddress() {
    	if(StringUtils.equalsIgnoreCase(getKeyParam(), "ipAddress")){
    		ipAddress = getParameter();
        } else {
        	ipAddress = null;
        }
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getStatus() {
    	if(StringUtils.equalsIgnoreCase(getKeyParam(), "status")){
    		status = getParameter();
        } else {
        	status = null;
        }
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
     
     
}
