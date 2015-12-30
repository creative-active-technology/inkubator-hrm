/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.search;

import com.inkubator.webcore.util.SearchParameter;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Taufik
 */
public class NeracaPermitSearchParameter extends SearchParameter{

    private String empData;
    private String permit;
    private String nik;
    private String jabatanName;

    public String getEmpData() {
        if (StringUtils.equalsIgnoreCase(getKeyParam(), "empData")) {
            empData = getParameter();
        } else {
            empData = null;
        }
        return empData;
    }

    public void setEmpData(String empData) {
        this.empData = empData;
    }

    public String getPermit() {
        if (StringUtils.equalsIgnoreCase(getKeyParam(), "permitName")) {
            permit = getParameter();
        } else {
            permit = null;
        }
        return permit;
    }

    public void setPermit(String permit) {
        this.permit = permit;
    }

    public String getNik() {
        if (StringUtils.equalsIgnoreCase(getKeyParam(), "nik")) {
            nik = getParameter();
        } else {
            nik = null;
        }
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
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

    
    
    
    
}
