package com.inkubator.hrm.web.search;

import org.apache.commons.lang3.StringUtils;

import com.inkubator.webcore.util.SearchParameter;

/**
 *
 * @author Taufik Hidayat
 */
public class RaceSearchParameter extends SearchParameter {
	
	private String raceCode;	
	private String raceName;
        
	public String getRaceName() {
		if(StringUtils.equalsIgnoreCase(getKeyParam(), "raceName")){
			raceName = getParameter();
		} else {
			raceName = null;
		}
    	return raceName;
	}

	public void setRaceName(String raceName) {
		this.raceName = raceName;
	}

	public String getRaceCode() {
		if(StringUtils.equalsIgnoreCase(getKeyParam(), "raceCode")){
			raceCode = getParameter();
		} else {
			raceCode = null;
		}
    	return raceCode;
	}

	public void setRaceCode(String raceCode) {
		this.raceCode = raceCode;
	}

}
