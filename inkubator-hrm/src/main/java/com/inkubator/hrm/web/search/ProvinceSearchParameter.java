package com.inkubator.hrm.web.search;

import org.apache.commons.lang3.StringUtils;

import com.inkubator.webcore.util.SearchParameter;

/**
 *
 * @author Taufik Hidayat
 */
public class ProvinceSearchParameter extends SearchParameter {
	
	private String provinceCode;	
	private String provinceName;
        private String countryName;
        
        public String getCountryName() {
		if(StringUtils.equalsIgnoreCase(getKeyParam(), "countryName")){
			countryName = getParameter();
		} else {
			countryName = null;
		}
    	return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}        
                
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

	public String getProvinceCode() {
		if(StringUtils.equalsIgnoreCase(getKeyParam(), "provinceCode")){
			provinceCode = getParameter();
		} else {
			provinceCode = null;
		}
    	return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

}
