package com.inkubator.hrm.web.search;

import org.apache.commons.lang3.StringUtils;

import com.inkubator.webcore.util.SearchParameter;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
public class WtAttendanceCalculationSearchParameter extends SearchParameter {
	private String code;
    private String name;
    private String wtGroupWorkingName;
    private String nik;
    private String empName;
    
    public String getWtGroupWorkingName() {
    	 if (StringUtils.equalsIgnoreCase(getKeyParam(), "wtGroupWorkingName")) {
    		 wtGroupWorkingName = getParameter();
         } else {
        	 wtGroupWorkingName = null;
         }
		return wtGroupWorkingName;
	}

	public void setWtGroupWorkingName(String wtGroupWorkingName) {
		this.wtGroupWorkingName = wtGroupWorkingName;
	}

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
