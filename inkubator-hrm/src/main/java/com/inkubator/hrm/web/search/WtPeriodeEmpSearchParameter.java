/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.search;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.inkubator.webcore.util.SearchParameter;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
public class WtPeriodeEmpSearchParameter extends SearchParameter {
	
	private Date searchDate;

	public Date getStartPeriod() {
		Date result = null;
		if (StringUtils.equalsIgnoreCase(getKeyParam(), "startPeriod")) {
			result = this.searchDate;
        }
		return result;
	}

	public Date getEndPeriod() {
		Date result = null;
		if (StringUtils.equalsIgnoreCase(getKeyParam(), "endPeriod")) {
			result = this.searchDate;
        }
		return result;
	}

	public Date getSearchDate() {
		return searchDate;
	}

	public void setSearchDate(Date searchDate) {
		this.searchDate = searchDate;
	}

}
