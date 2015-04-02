package com.inkubator.hrm.web.search;

import java.math.BigInteger;

import com.inkubator.webcore.util.SearchParameter;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author rizkykojek
 */
public class RmbsApplicationUndisbursedSearchParameter extends SearchParameter {

	private String userId;
    private String empNik;
    private String empName;
    private String rmbsType;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public String getEmpNik() {
		if (StringUtils.equalsIgnoreCase(getKeyParam(), "empNik")) {
			empNik = getParameter();
        } else {
        	empNik = null;
        }
		return empNik;
	}

	public void setEmpNik(String empNik) {
		this.empNik = empNik;
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
