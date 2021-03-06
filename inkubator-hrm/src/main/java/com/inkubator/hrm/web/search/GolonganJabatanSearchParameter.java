package com.inkubator.hrm.web.search;

import com.inkubator.webcore.util.SearchParameter;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author rizkykojek
 */
public class GolonganJabatanSearchParameter extends SearchParameter {

    private String code;
    private String pangkatName;

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

	public String getPangkatName() {
		if (StringUtils.equalsIgnoreCase(getKeyParam(), "pangkatName")) {
			pangkatName = getParameter();
        } else {
        	pangkatName = null;
        }
		return pangkatName;
	}

	public void setPangkatName(String pangkatName) {
		this.pangkatName = pangkatName;
	}
}
