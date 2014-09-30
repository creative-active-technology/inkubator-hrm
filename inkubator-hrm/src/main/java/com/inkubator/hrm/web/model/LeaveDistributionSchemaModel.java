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
public class LeaveDistributionSchemaModel implements Serializable {

    private Long id;
    private String empData;
    private Long empDataId;
    private Long leaveId;
    private Double balance;

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

    public Long getLeaveId() {
        return leaveId;
    }

    public void setLeaveId(Long leaveId) {
        this.leaveId = leaveId;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
    
    
}
