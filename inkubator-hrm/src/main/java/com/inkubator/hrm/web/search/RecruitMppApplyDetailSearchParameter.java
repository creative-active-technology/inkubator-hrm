/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.search;

import com.inkubator.webcore.util.SearchParameter;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
public class RecruitMppApplyDetailSearchParameter extends SearchParameter {
	
    private String jabatanCode;
    private String jabatanName;
    private String periode;

	public String getJabatanCode() {
		
		if (StringUtils.equalsIgnoreCase(getKeyParam(), "jabatanCode")) {
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
		
		if (StringUtils.equalsIgnoreCase(getKeyParam(), "jabatanName")) {
			jabatanName = getParameter();
		} else {
			jabatanName = null;
		}
		return jabatanName;
	}

	public void setJabatanName(String jabatanName) {
		this.jabatanName = jabatanName;
	}

	public String getPeriode() {
		if (StringUtils.equalsIgnoreCase(getKeyParam(), "periode")) {
			periode = getParameter();
		} else {
			periode = null;
		}
		return periode;
	}

	public void setPeriode(String periode) {
		this.periode = periode;
	}

   
    
}
