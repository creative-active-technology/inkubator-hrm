/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.search;

import org.apache.commons.lang3.StringUtils;

import com.inkubator.webcore.util.SearchParameter;

/**
 *
 * @author rizkykojek
 */
public class PayTempUploadDataSearchParameter extends SearchParameter{
    private String nik;
    private String name;
    private Long paySalaryComponentId;
    
    public PayTempUploadDataSearchParameter(Long paySalaryComponentId){
    	this.paySalaryComponentId = paySalaryComponentId;
    }

	public String getNik() {
		nik = StringUtils.equalsIgnoreCase(getKeyParam(), "nik") ? getParameter() : null;
		return nik;
	}

	public void setNik(String nik) {
		this.nik = nik;
	}

	public String getName() {
		name = StringUtils.equalsIgnoreCase(getKeyParam(), "name") ? getParameter() : null;
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getPaySalaryComponentId() {
		return paySalaryComponentId;
	}

	public void setPaySalaryComponentId(Long paySalaryComponentId) {
		this.paySalaryComponentId = paySalaryComponentId;
	}
    
	
}
