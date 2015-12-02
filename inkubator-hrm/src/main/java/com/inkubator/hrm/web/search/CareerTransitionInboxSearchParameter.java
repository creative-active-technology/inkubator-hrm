/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.search;

import com.inkubator.webcore.util.SearchParameter;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Deni
 */
public class CareerTransitionInboxSearchParameter extends SearchParameter {
    private String userId;
    private String empNik;
    private String empName;
    
    public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEmpName() {
		if (StringUtils.equalsIgnoreCase(getKeyParam(), "empName")) {
			empName = getParameter();
        } else {
        	empName = null;
        }
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpNik() {
		if (StringUtils.equalsIgnoreCase(getKeyParam(), "empNik")) {
			empNik = getParameter();
        } else {
        	empNik = null;
        }
		return empNik;
	}

	public void setEmpNik(String empNik) {
		this.empNik = empNik;
	}
}
