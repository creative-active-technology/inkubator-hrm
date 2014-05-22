package com.inkubator.hrm.web.search;

import org.apache.commons.lang3.StringUtils;

import com.inkubator.webcore.util.SearchParameter;

/**
 *
 * @author rizkykojek
 */
public class SpecificationAbilitySearchParameter extends SearchParameter {
	
	private String name;	
	private String optionAbility;
	private String scaleValue;

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

	public String getOptionAbility() {
		if(StringUtils.equalsIgnoreCase(getKeyParam(), "optionAbility")){
			optionAbility = getParameter();
		} else {
			optionAbility = null;
		}
    	return optionAbility;
	}

	public void setOptionAbility(String optionAbility) {
		this.optionAbility = optionAbility;
	}

	public String getScaleValue() {
		if(StringUtils.equalsIgnoreCase(getKeyParam(), "scaleValue")){
			scaleValue = getParameter();
		} else {
			scaleValue = null;
		}
    	return scaleValue;
	}

	public void setScaleValue(String scaleValue) {
		this.scaleValue = scaleValue;
	}

}
