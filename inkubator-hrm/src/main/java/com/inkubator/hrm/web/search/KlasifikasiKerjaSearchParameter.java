package com.inkubator.hrm.web.search;

import org.apache.commons.lang3.StringUtils;

import com.inkubator.webcore.util.SearchParameter;

/**
 *
 * @author Taufik Hidayat
 */
public class KlasifikasiKerjaSearchParameter extends SearchParameter {
	
	private String klasifikasiKerjaCode;	
	private String klasifikasiKerjaName;
        
	public String getKlasifikasiKerjaName() {
		if(StringUtils.equalsIgnoreCase(getKeyParam(), "klasifikasiKerjaName")){
			klasifikasiKerjaName = getParameter();
		} else {
			klasifikasiKerjaName = null;
		}
    	return klasifikasiKerjaName;
	}

	public void setKlasifikasiKerjaName(String klasifikasiKerjaName) {
		this.klasifikasiKerjaName = klasifikasiKerjaName;
	}

	public String getKlasifikasiKerjaCode() {
		if(StringUtils.equalsIgnoreCase(getKeyParam(), "klasifikasiKerjaCode")){
			klasifikasiKerjaCode = getParameter();
		} else {
			klasifikasiKerjaCode = null;
		}
    	return klasifikasiKerjaCode;
	}

	public void setKlasifikasiKerjaCode(String klasifikasiKerjaCode) {
		this.klasifikasiKerjaCode = klasifikasiKerjaCode;
	}

}
