/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.model;

import com.inkubator.hrm.entity.EmpData;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Deni
 */
public class ImplementationOfOverTimeModel implements Serializable {
    private Long id;
    private Long empDataId;
    private EmpData empData;
    private Long wtOverTimeId;
    private String implementationNumber;
    private Date implementationDate;
    private Date startTime;
    private Date endTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EmpData getEmpData() {
        return empData;
    }

    public void setEmpData(EmpData empData) {
        this.empData = empData;
    }
    
    
    public Long getEmpDataId() {
        return empDataId;
    }

    public void setEmpDataId(Long empDataId) {
        this.empDataId = empDataId;
    }

    public Long getWtOverTimeId() {
        return wtOverTimeId;
    }

    public void setWtOverTimeId(Long wtOverTimeId) {
        this.wtOverTimeId = wtOverTimeId;
    }

    public String getImplementationNumber() {
        return implementationNumber;
    }

    public void setImplementationNumber(String implementationNumber) {
        this.implementationNumber = implementationNumber;
    }

    public Date getImplementationDate() {
        return implementationDate;
    }

    public void setImplementationDate(Date implementationDate) {
        this.implementationDate = implementationDate;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
    
    
}
