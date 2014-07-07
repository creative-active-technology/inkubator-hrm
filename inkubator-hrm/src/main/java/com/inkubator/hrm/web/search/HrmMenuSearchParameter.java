package com.inkubator.hrm.web.search;

import com.inkubator.webcore.util.SearchParameter;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author rizkykojek
 */
public class HrmMenuSearchParameter extends SearchParameter {

    private String name;

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

	
}
