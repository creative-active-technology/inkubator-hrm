/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.search;

import org.apache.commons.lang.StringUtils;

import com.inkubator.webcore.util.SearchParameter;

/**
 *
 * @author rizkykojek
 */
public class CompetencyGroupSearchParameter extends SearchParameter{
    private String code;
    private String name;
    private String competencyTypeName;
    
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
	public String getCompetencyTypeName() {
		if(StringUtils.equalsIgnoreCase(getKeyParam(), "competencyTypeName")){
			competencyTypeName = getParameter();
		} else {
			competencyTypeName = null;
		}
		return competencyTypeName;
	}
	public void setCompetencyTypeName(String competencyTypeName) {
		this.competencyTypeName = competencyTypeName;
	}
	
    
    
}
