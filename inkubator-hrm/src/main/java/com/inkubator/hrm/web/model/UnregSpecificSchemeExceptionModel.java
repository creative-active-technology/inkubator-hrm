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
public class UnregSpecificSchemeExceptionModel implements Serializable{
    private Long unregPayComponentId;
    private EmpData empData;
    private Double nominal;

	public Long getUnregPayComponentId() {
		return unregPayComponentId;
	}

	public void setUnregPayComponentId(Long unregPayComponentId) {
		this.unregPayComponentId = unregPayComponentId;
	}

	public EmpData getEmpData() {
		return empData;
	}

	public void setEmpData(EmpData empData) {
		this.empData = empData;
	}

	public Double getNominal() {
		return nominal;
	}

	public void setNominal(Double nominal) {
		this.nominal = nominal;
	}

}
