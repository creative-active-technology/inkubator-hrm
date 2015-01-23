package com.inkubator.hrm.web.search;

import org.apache.commons.lang3.StringUtils;

import com.inkubator.webcore.util.SearchParameter;

/**
 *
 * @author rizkykojek
 */
public class LoanSearchParameter extends SearchParameter {

    private String employee;
    private String loanSchema;
    
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
	public String getLoanSchema() {
		if (StringUtils.equalsIgnoreCase(getKeyParam(), "loanSchema")) {
			loanSchema = getParameter();
        } else {
        	loanSchema = null;
        }
		return loanSchema;
	}
	public void setLoanSchema(String loanSchema) {
		this.loanSchema = loanSchema;
	}    
}
