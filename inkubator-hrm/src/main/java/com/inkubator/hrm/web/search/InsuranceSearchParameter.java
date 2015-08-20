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
 * @author Deni
 */
public class InsuranceSearchParameter extends SearchParameter {
    private String code;
    private String name;
    private String insuranceProduct;
    
	public String getCode() {
		if(StringUtils.equalsIgnoreCase(getKeyParam(), "code")){
			code = getParameter();
		} else {
			code = null;
		}
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		if(StringUtils.equalsIgnoreCase(getKeyParam(), "name")){
			name = getParameter();
		} else {
			name = null;
		}
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getInsuranceProduct() {
		if(StringUtils.equalsIgnoreCase(getKeyParam(), "insuranceProduct")){
			insuranceProduct = getParameter();
		} else {
			insuranceProduct = null;
		}
		return insuranceProduct;
	}
	public void setInsuranceProduct(String insuranceProduct) {
		this.insuranceProduct = insuranceProduct;
	}
    
    
}
