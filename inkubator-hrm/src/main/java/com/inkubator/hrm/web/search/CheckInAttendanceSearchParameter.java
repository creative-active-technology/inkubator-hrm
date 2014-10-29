/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.search;

import com.inkubator.webcore.util.SearchParameter;

/**
 *
 * @author Deni
 */
public class CheckInAttendanceSearchParameter extends SearchParameter{
     private String empData;
     private String ipAddress;
     private Integer status;

    public String getEmpData() {
        return empData;
    }

    public void setEmpData(String empData) {
        this.empData = empData;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
     
     
}
