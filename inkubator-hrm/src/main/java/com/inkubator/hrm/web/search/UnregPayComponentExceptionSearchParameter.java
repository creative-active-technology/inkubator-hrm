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
 * @author rizkykojek
 */
public class UnregPayComponentExceptionSearchParameter extends SearchParameter{

    private String nik;
    private String name;
    private Long unregPayComponentsId;

    public String getNik() {
    	if(StringUtils.equalsIgnoreCase(getKeyParam(), "nik")){
    		nik = getParameter();
        } else {
        	nik = null;
        }
        return nik;
	}

	public void setNik(String nik) {
		this.nik = nik;
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

	public Long getUnregPayComponentsId() {
		return unregPayComponentsId;
	}

	public void setUnregPayComponentsId(Long unregPayComponentsId) {
		this.unregPayComponentsId = unregPayComponentsId;
	}
    
    
}
