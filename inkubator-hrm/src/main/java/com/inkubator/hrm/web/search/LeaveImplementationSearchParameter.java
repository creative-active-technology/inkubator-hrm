package com.inkubator.hrm.web.search;

import org.apache.commons.lang3.StringUtils;

import com.inkubator.webcore.util.SearchParameter;

/**
 *
 * @author rizkykojek
 */
public class LeaveImplementationSearchParameter extends SearchParameter {

	private String numberFilling;
    private String leave;
    private String employee;
    
	public String getNumberFilling() {
		if (StringUtils.equalsIgnoreCase(getKeyParam(), "numberFilling")) {
			numberFilling = getParameter();
        } else {
        	numberFilling = null;
        }
		return numberFilling;
	}

	public void setNumberFilling(String numberFilling) {
		this.numberFilling = numberFilling;
	}

	public String getLeave() {
		if (StringUtils.equalsIgnoreCase(getKeyParam(), "leave")) {
			leave = getParameter();
        } else {
        	leave = null;
        }
		return leave;
	}

	public void setLeave(String leave) {
		this.leave = leave;
	}

	public String getEmployee() {
		if (StringUtils.equalsIgnoreCase(getKeyParam(), "employee")) {
			employee = getParameter();
        } else {
        	employee = null;
        }
		return employee;
	}

	public void setEmployee(String employee) {
		this.employee = employee;
	}

	

	
}
