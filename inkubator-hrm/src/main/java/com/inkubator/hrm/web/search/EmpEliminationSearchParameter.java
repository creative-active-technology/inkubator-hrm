package com.inkubator.hrm.web.search;

import org.apache.commons.lang3.StringUtils;

import com.inkubator.webcore.util.SearchParameter;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
public class EmpEliminationSearchParameter extends SearchParameter {

    private String nik;
    private String empName;
    private String lastJabatanName;
    
    

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

	public String getLastJabatanName() {
		if (StringUtils.equalsIgnoreCase(getKeyParam(), "lastJabatanName")) {
			lastJabatanName = getParameter();
        } else {
        	lastJabatanName = null;
        }
		return lastJabatanName;
	}

	public void setLastJabatanName(String lastJabatanName) {
		this.lastJabatanName = lastJabatanName;
	}


}
