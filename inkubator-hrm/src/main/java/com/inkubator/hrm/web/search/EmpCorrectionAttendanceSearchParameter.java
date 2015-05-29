package com.inkubator.hrm.web.search;

import org.apache.commons.lang3.StringUtils;

import com.inkubator.webcore.util.SearchParameter;

/**
 *
 * @author rizkykojek
 */
public class EmpCorrectionAttendanceSearchParameter extends SearchParameter {
    private Long empDataId;
    private String employee;
    
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
	public Long getEmpDataId() {
		return empDataId;
	}
	public void setEmpDataId(Long empDataId) {
		this.empDataId = empDataId;
	}
        
}
