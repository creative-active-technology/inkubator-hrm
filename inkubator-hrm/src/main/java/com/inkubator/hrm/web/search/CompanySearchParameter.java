package com.inkubator.hrm.web.search;

import com.inkubator.webcore.util.SearchParameter;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author rizkykojek
 */
public class CompanySearchParameter extends SearchParameter {

    private String name;
    private String country;

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

	public String getCountry() {
		if (StringUtils.equalsIgnoreCase(getKeyParam(), "country")) {
			country = getParameter();
        } else {
        	country = null;
        }
        return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

    
}
