package com.inkubator.hrm.web.search;

import org.apache.commons.lang3.StringUtils;

import com.inkubator.webcore.util.SearchParameter;

/**
 *
 * @author rizkykojek
 */
public class RecruitSelectionApplicantRealizationSearchParameter extends SearchParameter {

	private String name;
    private String phone;
    private String jabatanApply;
    private String jabatanLast;

	public String getName() {
		if (StringUtils.equalsIgnoreCase(getKeyParam(), "name")) {
			name = getParameter();
        } else {
        	name = null;
        }
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		if (StringUtils.equalsIgnoreCase(getKeyParam(), "phone")) {
			phone = getParameter();
        } else {
        	phone = null;
        }
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getJabatanApply() {
		if (StringUtils.equalsIgnoreCase(getKeyParam(), "jabatanApply")) {
			jabatanApply = getParameter();
        } else {
        	jabatanApply = null;
        }
		return jabatanApply;
	}

	public void setJabatanApply(String jabatanApply) {
		this.jabatanApply = jabatanApply;
	}

	public String getJabatanLast() {
		if (StringUtils.equalsIgnoreCase(getKeyParam(), "jabatanLast")) {
			jabatanLast = getParameter();
        } else {
        	jabatanLast = null;
        }
		return jabatanLast;
	}

	public void setJabatanLast(String jabatanLast) {
		this.jabatanLast = jabatanLast;
	}
    
}
