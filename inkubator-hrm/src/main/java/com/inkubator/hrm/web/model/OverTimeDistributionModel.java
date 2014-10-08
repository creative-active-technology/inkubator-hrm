/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.model;

import java.io.Serializable;

/**
 *
 * @author Deni
 */
public class OverTimeDistributionModel implements Serializable {

    private Long oldEmpId;
    private Long oldOverTimeId;
    private Long id;
    private String empData;
    private Long empDataId;
    private Long wtOverTimeId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmpData() {
        return empData;
    }

    public void setEmpData(String empData) {
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

    public Long getOldEmpId() {
        return oldEmpId;
    }

    public void setOldEmpId(Long oldEmpId) {
        this.oldEmpId = oldEmpId;
    }

    public Long getOldOverTimeId() {
        return oldOverTimeId;
    }

    public void setOldOverTimeId(Long oldOverTimeId) {
        this.oldOverTimeId = oldOverTimeId;
    }
    
    
}
