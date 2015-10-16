package com.inkubator.hrm.web.model;

import java.io.Serializable;

/**
 *
 * @author rizkykojek
 */
public class ApplicantViewModel implements Serializable {

	private Long totalInternal;
	private Long totalExternal;
	private String name;
	
	public Long getTotalInternal() {
		return totalInternal;
	}
	public void setTotalInternal(Long totalInternal) {
		this.totalInternal = totalInternal;
	}
	public Long getTotalExternal() {
		return totalExternal;
	}
	public void setTotalExternal(Long totalExternal) {
		this.totalExternal = totalExternal;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
