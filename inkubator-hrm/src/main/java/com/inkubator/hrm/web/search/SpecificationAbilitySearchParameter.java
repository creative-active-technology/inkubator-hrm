package com.inkubator.hrm.web.search;

import org.apache.commons.lang3.StringUtils;

import com.inkubator.webcore.util.SearchParameter;

/**
 *
 * @author rizkykojek
 */
public class SpecificationAbilitySearchParameter extends SearchParameter {
	
	private String name;	
	private String option;

	public String getName() {
		if(StringUtils.equalsIgnoreCase(getKeyParam(), "name")){
			name = getParameter();
		} else {
			name = null;
		}
    	return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOption() {
		if(StringUtils.equalsIgnoreCase(getKeyParam(), "option")){
			option = getParameter();
		} else {
			option = null;
		}
    	return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

}
