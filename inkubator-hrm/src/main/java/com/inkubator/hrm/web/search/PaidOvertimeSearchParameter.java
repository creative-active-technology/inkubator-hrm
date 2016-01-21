package com.inkubator.hrm.web.search;

import com.inkubator.webcore.util.SearchParameter;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
public class PaidOvertimeSearchParameter extends SearchParameter {
	
    private String nik;
    private String empName;
    
    public String getNik() {
    	if (StringUtils.equalsIgnoreCase(getKeyParam(), "nik")) {
    		nik = getParameter();
        } else {
        	nik = null;
        }
		return nik;
	}

	public void setNik(String nik) {
		this.nik = nik;
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

}
