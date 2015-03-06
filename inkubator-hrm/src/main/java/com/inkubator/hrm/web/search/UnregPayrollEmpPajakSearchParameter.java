package com.inkubator.hrm.web.search;

import com.inkubator.webcore.util.SearchParameter;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author rizkykojek
 */
public class UnregPayrollEmpPajakSearchParameter extends SearchParameter {

    private String nikOrName;
    private Long unregSalaryId;
    private Long taxComponentId;    

    public String getNikOrName() {
    	if (StringUtils.equalsIgnoreCase(getKeyParam(), "nikOrName")) {
    		nikOrName = getParameter();
        } else {
        	nikOrName = null;
        }
		return nikOrName;
	}

	public void setNikOrName(String nikOrName) {
		this.nikOrName = nikOrName;
	}

	public Long getUnregSalaryId() {
		return unregSalaryId;
	}

	public void setUnregSalaryId(Long unregSalaryId) {
		this.unregSalaryId = unregSalaryId;
	}

	public Long getTaxComponentId() {
		return taxComponentId;
	}

	public void setTaxComponentId(Long taxComponentId) {
		this.taxComponentId = taxComponentId;
	}

	
}
