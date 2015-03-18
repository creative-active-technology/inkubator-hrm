package com.inkubator.hrm.web.search;

import com.inkubator.webcore.util.SearchParameter;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author rizkykojek
 */
public class RmbsTypeSearchParameter extends SearchParameter {

    private String name;
    private String code;
    private String coa;

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

	public String getCoa() {
		if (StringUtils.equalsIgnoreCase(getKeyParam(), "coa")) {
			coa = getParameter();
        } else {
        	coa = null;
        }
		return coa;
	}

	public void setCoa(String coa) {
		this.coa = coa;
	}
    
    
}
