package com.inkubator.hrm.web.search;

import org.apache.commons.lang3.StringUtils;

import com.inkubator.webcore.util.SearchParameter;

public class KompetensiJabatanSearchParameter extends SearchParameter{
	private String code;
	private String name;
	private String golonganJabatan;
	
	
	public String getCode() {
		if(StringUtils.equalsIgnoreCase(getKeyParam(), "code")){
			code = getParameter();
		} else {
			code = null;
		}
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
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
