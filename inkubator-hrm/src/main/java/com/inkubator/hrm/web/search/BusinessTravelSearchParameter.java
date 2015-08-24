package com.inkubator.hrm.web.search;

import com.inkubator.webcore.util.SearchParameter;

import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author rizkykojek
 */
public class BusinessTravelSearchParameter extends SearchParameter {

	private String userId;
	private Long companyId;
    private String businessTravelNumber;
    private String employee;
    private String destination;
    
	
    public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getBusinessTravelNumber() {
    	if (StringUtils.equalsIgnoreCase(getKeyParam(), "businessTravelNumber")) {
    		businessTravelNumber = getParameter();
        } else {
        	businessTravelNumber = null;
        }
		return businessTravelNumber;
	}

	public void setBusinessTravelNumber(String businessTravelNumber) {
		this.businessTravelNumber = businessTravelNumber;
	}

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

	public String getDestination() {
		if (StringUtils.equalsIgnoreCase(getKeyParam(), "destination")) {
			destination = getParameter();
        } else {
        	destination = null;
        }
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}
	
}
