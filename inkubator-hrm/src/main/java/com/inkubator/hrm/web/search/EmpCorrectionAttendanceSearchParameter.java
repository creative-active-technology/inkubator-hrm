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
    private String status;
    
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
	public String getStatus() {
		if (StringUtils.equalsIgnoreCase(getKeyParam(), "status")) {
			status = getParameter();
        } else {
        	status = null;
        }
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getEmpDataId() {
		return empDataId;
	}
	public void setEmpDataId(Long empDataId) {
		this.empDataId = empDataId;
	}
        
}
