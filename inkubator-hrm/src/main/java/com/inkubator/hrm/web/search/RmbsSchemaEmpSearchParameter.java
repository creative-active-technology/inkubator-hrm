package com.inkubator.hrm.web.search;

import com.inkubator.webcore.util.SearchParameter;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author rizkykojek
 */
public class RmbsSchemaEmpSearchParameter extends SearchParameter {

    private String nik;
    private String empName;
    private String golJabatan;
    private String schemaCode;
    private String nomorSK;

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

	public String getEmpName() {
		if (StringUtils.equalsIgnoreCase(getKeyParam(), "empName")) {
			empName = getParameter();
        } else {
        	empName = null;
        }
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getGolJabatan() {
		if (StringUtils.equalsIgnoreCase(getKeyParam(), "golJabatan")) {
			golJabatan = getParameter();
        } else {
        	golJabatan = null;
        }
		return golJabatan;
	}

	public void setGolJabatan(String golJabatan) {
		this.golJabatan = golJabatan;
	}

	public String getSchemaCode() {
		if (StringUtils.equalsIgnoreCase(getKeyParam(), "schemaCode")) {
			schemaCode = getParameter();
        } else {
        	schemaCode = null;
        }
		return schemaCode;
	}

	public void setSchemaCode(String schemaCode) {
		this.schemaCode = schemaCode;
	}

	public String getNomorSK() {
		if (StringUtils.equalsIgnoreCase(getKeyParam(), "nomorSK")) {
			nomorSK = getParameter();
        } else {
        	nomorSK = null;
        }
		return nomorSK;
	}

	public void setNomorSK(String nomorSK) {
		this.nomorSK = nomorSK;
	}
    
}
