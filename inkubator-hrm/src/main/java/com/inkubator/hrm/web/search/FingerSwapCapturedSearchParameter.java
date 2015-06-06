package com.inkubator.hrm.web.search;

import java.util.Date;

import com.inkubator.hrm.entity.EmpData;
import com.inkubator.webcore.util.SearchParameter;

/**
 *
 * @author rizkykojek
 */
public class FingerSwapCapturedSearchParameter extends SearchParameter {
    private Date startPeriod;
    private Date endPeriod;
    private Long machineFingerId;  
    private EmpData empData;
	
	public Date getStartPeriod() {
		return startPeriod;
	}
	public void setStartPeriod(Date startPeriod) {
		this.startPeriod = startPeriod;
	}
	public Date getEndPeriod() {
		return endPeriod;
	}
	public void setEndPeriod(Date endPeriod) {
		this.endPeriod = endPeriod;
	}
	public Long getMachineFingerId() {
		return machineFingerId;
	}
	public void setMachineFingerId(Long machineFingerId) {
		this.machineFingerId = machineFingerId;
	}
	public EmpData getEmpData() {
		return empData;
	}
	public void setEmpData(EmpData empData) {
		this.empData = empData;
	}
	
	
	
        
}
