/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.search;

import java.util.Date;

import com.inkubator.webcore.util.SearchParameter;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
public class WtPeriodeEmpSearchParameter extends SearchParameter {
	
	private Date startPeriod;
	private Date endPeriod;
	private String absenStatus;

	public Date getStartPeriod() {
		return startPeriod;
	}

	public void setStartPeriod(Date startPeriod) {
		this.startPeriod = startPeriod;
	}

	public Date getEndPeriod() {
		return endPeriod;
	}

	public void setEndPeriod(Date endPeriod) {
		this.endPeriod = endPeriod;
	}

	public String getAbsenStatus() {
	  if (getKeyParam() != null) {
            if (getKeyParam().equalsIgnoreCase("absenStatus")) {
            	absenStatus = getParameter();
            }else{
                absenStatus=null;
            }
        }
		return absenStatus;
	}

	public void setAbsenStatus(String absenStatus) {
		this.absenStatus = absenStatus;
	}

	
}
