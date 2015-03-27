package com.inkubator.hrm.web.search;

import com.inkubator.webcore.util.SearchParameter;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author rizkykojek
 */
public class RmbsApplicationSearchParameter extends SearchParameter {

    private String nik;
    private String empName;
    private String code;
    private String rmbsType;
    
    public String getCode() {
        if (StringUtils.equalsIgnoreCase(getKeyParam(), "code")) {
            code = getParameter();
        } else {
            code = null;
        }
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

	public String getRmbsType() {
		if (StringUtils.equalsIgnoreCase(getKeyParam(), "rmbsType")) {
			rmbsType = getParameter();
        } else {
        	rmbsType = null;
        }
		return rmbsType;
	}

	public void setRmbsType(String rmbsType) {
		this.rmbsType = rmbsType;
	}
    
}
