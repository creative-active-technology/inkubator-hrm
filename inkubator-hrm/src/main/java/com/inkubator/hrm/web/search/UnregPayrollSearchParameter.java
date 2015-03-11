package com.inkubator.hrm.web.search;

import com.inkubator.webcore.util.SearchParameter;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author rizkykojek
 */
public class UnregPayrollSearchParameter extends SearchParameter {

    private String name;
    private String nik;
    private Long unregSalaryId;

    public String getName() {
        if (StringUtils.equalsIgnoreCase(getKeyParam(), "name")) {
            name = getParameter();
        } else {
            name = null;
        }
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

	public Long getUnregSalaryId() {
		return unregSalaryId;
	}

	public void setUnregSalaryId(Long unregSalaryId) {
		this.unregSalaryId = unregSalaryId;
	}	

}
