package com.inkubator.hrm.web.model;

import java.io.Serializable;

public class CareerDisciplineTypeModel implements Serializable{
	private Long id;
	private String code;
	private String name;
	private String description;
	private Double point;
	private Integer validity;
	private Long systemLetterReferenceId;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getPoint() {
		return point;
	}
	public void setPoint(Double point) {
		this.point = point;
	}
	public Integer getValidity() {
		return validity;
	}
	public void setValidity(Integer validity) {
		this.validity = validity;
	}
	public Long getSystemLetterReferenceId() {
		return systemLetterReferenceId;
	}
	public void setSystemLetterReferenceId(Long systemLetterReferenceId) {
		this.systemLetterReferenceId = systemLetterReferenceId;
	}
	
}	
