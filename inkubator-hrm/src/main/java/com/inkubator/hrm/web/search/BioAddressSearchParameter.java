package com.inkubator.hrm.web.search;

import com.inkubator.webcore.util.SearchParameter;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author rizkykojek
 */
public class BioAddressSearchParameter extends SearchParameter {

    private String addressDetail;
    private String provinceName;
    private String cityName;

    public String getAddressDetail() {
    	if (StringUtils.equalsIgnoreCase(getKeyParam(), "addressDetail")) {
    		addressDetail = getParameter();
        } else {
        	addressDetail = null;
        }
    	return addressDetail;
	}

	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}

	public String getProvinceName() {
		if (StringUtils.equalsIgnoreCase(getKeyParam(), "provinceName")) {
			provinceName = getParameter();
        } else {
        	provinceName = null;
        }
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityName() {
		if (StringUtils.equalsIgnoreCase(getKeyParam(), "cityName")) {
			cityName = getParameter();
        } else {
        	cityName = null;
        }
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}	
}
