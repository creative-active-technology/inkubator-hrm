/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inkubator.hrm.web.search;

import com.inkubator.webcore.util.SearchParameter;

/**
 *
 * @author rizkykojek
 */
public class ReportEmpWorkingGroupParameter extends SearchParameter{
    
	private Long departmentId;
    private String nikStart;
    private String nikEnd;
    
	public Long getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
	public String getNikStart() {
		return nikStart;
	}
	public void setNikStart(String nikStart) {
		this.nikStart = nikStart;
	}
	public String getNikEnd() {
		return nikEnd;
	}
	public void setNikEnd(String nikEnd) {
		this.nikEnd = nikEnd;
	}
    
    
            
}
