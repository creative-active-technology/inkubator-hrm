package com.inkubator.hrm.web.search;

import org.apache.commons.lang3.StringUtils;

import com.inkubator.webcore.util.SearchParameter;

/**
 *
 * @author Taufik Hidayat
 */
public class CitySearchParameter extends SearchParameter {
	
	private String cityCode;	
	private String cityName;
        private String provinceName;
        
        public String getProvinceName() {
		if(StringUtils.equalsIgnoreCase(getKeyParam(), "provinceName")){
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
		if(StringUtils.equalsIgnoreCase(getKeyParam(), "cityName")){
			cityName = getParameter();
		} else {
			cityName = null;
		}
    	return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCityCode() {
		if(StringUtils.equalsIgnoreCase(getKeyParam(), "cityCode")){
			cityCode = getParameter();
		} else {
			cityCode = null;
		}
    	return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

}
