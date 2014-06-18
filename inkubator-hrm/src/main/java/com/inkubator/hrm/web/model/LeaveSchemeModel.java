package com.inkubator.hrm.web.model;

import java.io.Serializable;
import java.util.List;

import com.inkubator.hrm.entity.Leave;

/**
 *
 * @author rizkykojek
 */
public class LeaveSchemeModel implements Serializable {

	private Long id;
	private String code;
    private String name;
    private String description;
    private Integer totalDays;
    private Long leaveId;
    private List<Leave> leaves;
    
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
	public Integer getTotalDays() {
		return totalDays;
	}
	public void setTotalDays(Integer totalDays) {
		this.totalDays = totalDays;
	}
	public Long getLeaveId() {
		return leaveId;
	}
	public void setLeaveId(Long leaveId) {
		this.leaveId = leaveId;
	}
	public List<Leave> getLeaves() {
		return leaves;
	}
	public void setLeaves(List<Leave> leaves) {
		this.leaves = leaves;
	}
}
