/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.model;

import java.io.Serializable;

/**
 *
 * @author rizkykojek
 */
public class PaySalaryUploadModel implements Serializable{

    private Long paySalaryComponentId;
    private String paySalaryComponentName;
    private Double totalSalary;
    private Long totalEmployee;
    
	public Long getPaySalaryComponentId() {
		return paySalaryComponentId;
	}
	public void setPaySalaryComponentId(Long paySalaryComponentId) {
		this.paySalaryComponentId = paySalaryComponentId;
	}
	public String getPaySalaryComponentName() {
		return paySalaryComponentName;
	}
	public void setPaySalaryComponentName(String paySalaryComponentName) {
		this.paySalaryComponentName = paySalaryComponentName;
	}
	public Double getTotalSalary() {
		return totalSalary;
	}
	public void setTotalSalary(Double totalSalary) {
		this.totalSalary = totalSalary;
	}
	public Long getTotalEmployee() {
		return totalEmployee;
	}
	public void setTotalEmployee(Long totalEmployee) {
		this.totalEmployee = totalEmployee;
	}
    
    
    
    
}
