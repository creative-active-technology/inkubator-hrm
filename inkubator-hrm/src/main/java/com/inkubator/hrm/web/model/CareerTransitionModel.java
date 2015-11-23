package com.inkubator.hrm.web.model;

import java.io.Serializable;

public class CareerTransitionModel implements Serializable {
	private Long id;
	private Integer roleTransitionId;
	private Long roleTransitionDetailId;
	private String transitionCode;
	private String transitionName;
	private String description;
	private Long systemLetterReferenceId;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getRoleTransitionId() {
		return roleTransitionId;
	}
	public void setRoleTransitionId(Integer roleTransitionId) {
		this.roleTransitionId = roleTransitionId;
	}
	public Long getRoleTransitionDetailId() {
		return roleTransitionDetailId;
	}
	public void setRoleTransitionDetailId(Long roleTransitionDetailId) {
		this.roleTransitionDetailId = roleTransitionDetailId;
	}
	public String getTransitionCode() {
		return transitionCode;
	}
	public void setTransitionCode(String transitionCode) {
		this.transitionCode = transitionCode;
	}
	public String getTransitionName() {
		return transitionName;
	}
	public void setTransitionName(String transitionName) {
		this.transitionName = transitionName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getSystemLetterReferenceId() {
		return systemLetterReferenceId;
	}
	public void setSystemLetterReferenceId(Long systemLetterReferenceId) {
		this.systemLetterReferenceId = systemLetterReferenceId;
	}

	
}
