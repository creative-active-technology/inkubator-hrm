package com.inkubator.hrm.web.search;

import com.inkubator.webcore.util.SearchParameter;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author rizkykojek
 */
public class DataFingerRealizationSearchParameter extends SearchParameter {

	private String nik;
    private String employeeName;
    private String workingGroupName;  

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

	public String getEmployeeName() {
		if (StringUtils.equalsIgnoreCase(getKeyParam(), "employeeName")) {
			employeeName = getParameter();
        } else {
        	employeeName = null;
        }
        return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getWorkingGroupName() {
		if (StringUtils.equalsIgnoreCase(getKeyParam(), "workingGroupName")) {
			workingGroupName = getParameter();
        } else {
        	workingGroupName = null;
        }
        return workingGroupName;
	}

	public void setWorkingGroupName(String workingGroupName) {
		this.workingGroupName = workingGroupName;
	}
	
}
