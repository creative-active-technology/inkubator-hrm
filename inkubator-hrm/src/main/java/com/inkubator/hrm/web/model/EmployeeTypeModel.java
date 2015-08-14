package com.inkubator.hrm.web.model;

import java.io.Serializable;

/**
 *
 * @author rizkykojek
 */
public class EmployeeTypeModel implements Serializable {

	private Long id;
	private String name;
	private Boolean directTask;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Boolean getDirectTask() {
		return directTask;
	}
	public void setDirectTask(Boolean directTask) {
		this.directTask = directTask;
	}
	
	
	
	
}
