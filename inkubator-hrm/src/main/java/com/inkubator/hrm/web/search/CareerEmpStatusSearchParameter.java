package com.inkubator.hrm.web.search;

import com.inkubator.webcore.util.SearchParameter;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author rizkykojek
 */
public class CareerEmpStatusSearchParameter extends SearchParameter {

    private String name;
    private String code;
    private String empTypeName;

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

	public String getEmpTypeName() {
		if (StringUtils.equalsIgnoreCase(getKeyParam(), "empTypeName")) {
			empTypeName = getParameter();
        } else {
        	empTypeName = null;
        }
		return empTypeName;
	}

	public void setEmpTypeName(String empTypeName) {
		this.empTypeName = empTypeName;
	}
    
}
