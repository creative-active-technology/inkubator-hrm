package com.inkubator.hrm.web.model;

import java.io.Serializable;

/**
 *
 * @author rizkykojek
 */
public class PangkatModel implements Serializable {

	private Long id;
	private String pangkatName;
	private String pangkatCode;
	private Integer level;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPangkatName() {
		return pangkatName;
	}
	public void setPangkatName(String pangkatName) {
		this.pangkatName = pangkatName;
	}
	public String getPangkatCode() {
		return pangkatCode;
	}
	public void setPangkatCode(String pangkatCode) {
		this.pangkatCode = pangkatCode;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	
		
	
}
