/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.search;

import com.inkubator.webcore.util.SearchParameter;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
public class WtPeriodeEmpSearchParameter extends SearchParameter {
	
	private String startPeriod;
	private String endPeriod;
	private String absenStatus;
    
    public String getStartPeriod() {
    	if (getKeyParam() != null) {
            if (getKeyParam().equalsIgnoreCase("startPeriod")) {
            	startPeriod = getParameter();
            }
        }
		return startPeriod;
	}

	public void setStartPeriod(String startPeriod) {
		this.startPeriod = startPeriod;
	}

	public String getEndPeriod() {
		if (getKeyParam() != null) {
            if (getKeyParam().equalsIgnoreCase("endPeriod")) {
            	endPeriod = getParameter();
            }
        }
		return endPeriod;
	}

	public void setEndPeriod(String endPeriod) {
		this.endPeriod = endPeriod;
	}

	public String getAbsenStatus() {
	  if (getKeyParam() != null) {
            if (getKeyParam().equalsIgnoreCase("absenStatus")) {
            	absenStatus = getParameter();
            }
        }
		return absenStatus;
	}

	public void setAbsenStatus(String absenStatus) {
		this.absenStatus = absenStatus;
	}

	
}
