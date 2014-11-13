package com.inkubator.hrm.web.search;

import com.inkubator.webcore.util.SearchParameter;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author rizkykojek
 */
public class CompanyPolicySearchParameter extends SearchParameter {

    private String subject;
    private String department;
        

    public String getSubject() {
    	if (StringUtils.equalsIgnoreCase(getKeyParam(), "subject")) {
    		subject = getParameter();
        } else {
        	subject = null;
        }
        return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

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
	
}
