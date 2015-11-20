package com.inkubator.hrm.web.search;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.inkubator.webcore.util.SearchParameter;

/**
 *
 * @author rizkykojek
 */
public class RmbsDisbursementSearchParameter extends SearchParameter {

	private Long empId;
    private String empNik;
    private String empName;
    private String rmbsApplicationCode;
    private String rmbsTypeName;
    private Date disbursementDate;

	public Long getEmpId() {
		return empId;
	}

	public void setEmpId(Long empId) {
		this.empId = empId;
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

	public String getRmbsApplicationCode() {
		if (StringUtils.equalsIgnoreCase(getKeyParam(), "rmbsApplicationCode")) {
			rmbsApplicationCode = getParameter();
        } else {
        	rmbsApplicationCode = null;
        }
		return rmbsApplicationCode;
	}

	public void setRmbsApplicationCode(String rmbsApplicationCode) {
		this.rmbsApplicationCode = rmbsApplicationCode;
	}

	public String getRmbsTypeName() {
		if (StringUtils.equalsIgnoreCase(getKeyParam(), "rmbsTypeName")) {
			rmbsTypeName = getParameter();
        } else {
        	rmbsTypeName = null;
        }
		return rmbsTypeName;
	}

	public void setRmbsTypeName(String rmbsTypeName) {
		this.rmbsTypeName = rmbsTypeName;
	}

	public Date getDisbursementDate() {
		if (StringUtils.equalsIgnoreCase(getKeyParam(), "disbursementDate")) {
			
        } else {
        	disbursementDate = null;
        }
		return disbursementDate;
	}

	public void setDisbursementDate(Date disbursementDate) {
		this.disbursementDate = disbursementDate;
	}
	
	

	

	
    
}
