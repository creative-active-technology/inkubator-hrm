package com.inkubator.hrm.web.search;

import org.apache.commons.lang3.StringUtils;

import com.inkubator.webcore.util.SearchParameter;

/**
 *
 * @author Taufik Hidayat
 */
public class PublicHolidayExceptionSearchParameter extends SearchParameter {
	
	private String publicHolidayCode;
    private String empDataName;
    private String publicHolidayName;

    public String getEmpDataName() {
        if (StringUtils.equalsIgnoreCase(getKeyParam(), "empDataName")) {
            empDataName = getParameter();
        } else {
            empDataName = null;
        }
        return empDataName;
    }
       

    public void setEmpDataName(String empDataName) {
        this.empDataName = empDataName;
    }

    public String getPublicHolidayName() {
        if (StringUtils.equalsIgnoreCase(getKeyParam(), "publicHolidayName")) {
            publicHolidayName = getParameter();
        } else {
            publicHolidayName = null;
        }
        return publicHolidayName;
    }

    public void setPublicHolidayName(String publicHolidayName) {
        this.publicHolidayName = publicHolidayName;
    }


	public String getPublicHolidayCode() {
		if (StringUtils.equalsIgnoreCase(getKeyParam(), "publicHolidayCode")) {
			publicHolidayCode = getParameter();
        } else {
        	publicHolidayCode = null;
        }
		return publicHolidayCode;
	}


	public void setPublicHolidayCode(String publicHolidayCode) {
		this.publicHolidayCode = publicHolidayCode;
	}
    
    
}
