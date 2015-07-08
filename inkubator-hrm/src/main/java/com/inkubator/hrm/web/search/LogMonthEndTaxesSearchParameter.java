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
public class LogMonthEndTaxesSearchParameter extends SearchParameter {
	private String department;
	private String goljab;
	private String name;

	public String getDepartment() {
		if (StringUtils.equalsIgnoreCase(getKeyParam(), "department")) {
			department = getParameter();
		} else {
			department = null;
		}
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getGoljab() {
		if (StringUtils.equalsIgnoreCase(getKeyParam(), "goljab")) {
			goljab = getParameter();
		} else {
			goljab = null;
		}
		return goljab;
	}

	public void setGoljab(String goljab) {
		this.goljab = goljab;
	}

	public String getName() {
		if (StringUtils.equalsIgnoreCase(getKeyParam(), "name")) {
			name = getParameter();
		} else {
			name = null;
		}
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
