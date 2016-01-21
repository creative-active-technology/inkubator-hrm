package com.inkubator.hrm.web.search;

import org.apache.commons.lang3.StringUtils;

import com.inkubator.webcore.util.SearchParameter;

public class PerformanceIndicatorJabatanSearchParameter extends SearchParameter{
	
	private String jabatanCode;
	private String jabatanName;
	private String golonganJabatan;
	
	public String getJabatanCode() {
		if(StringUtils.equalsIgnoreCase(getKeyParam(), "jabatanCode")){
			jabatanCode = getParameter();
		} else {
			jabatanCode = null;
		}
		return jabatanCode;
	}

	public void setJabatanCode(String jabatanCode) {
		this.jabatanCode = jabatanCode;
	}

	public String getJabatanName() {
		if(StringUtils.equalsIgnoreCase(getKeyParam(), "jabatanName")){
			jabatanName = getParameter();
		} else {
			jabatanName = null;
		}
		return jabatanName;
	}

	public void setJabatanName(String jabatanName) {
		this.jabatanName = jabatanName;
	}

	public String getGolonganJabatan() {
		if(StringUtils.equalsIgnoreCase(getKeyParam(), "golonganJabatan")){
			golonganJabatan = getParameter();
		} else {
			golonganJabatan = null;
		}
		return golonganJabatan;
	}
	
	public void setGolonganJabatan(String golonganJabatan) {
		this.golonganJabatan = golonganJabatan;
	}
	
	
}
