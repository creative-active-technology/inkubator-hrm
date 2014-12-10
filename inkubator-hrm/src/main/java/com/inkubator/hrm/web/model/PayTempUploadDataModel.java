/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.model;

import java.io.Serializable;

import com.inkubator.hrm.entity.EmpData;

/**
 *
 * @author rizkykojek
 */
public class PayTempUploadDataModel implements Serializable{
    
	private Long id;
    private EmpData empData;
    private Double nominalValue;
    private Long paySalaryComponentId;
    private String paySalaryComponentName; 
    
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
	public Double getNominalValue() {
		return nominalValue;
	}
	public void setNominalValue(Double nominalValue) {
		this.nominalValue = nominalValue;
	}
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
	
}
