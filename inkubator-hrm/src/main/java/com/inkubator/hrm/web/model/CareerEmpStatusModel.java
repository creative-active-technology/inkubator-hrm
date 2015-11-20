package com.inkubator.hrm.web.model;

import java.io.Serializable;

/**
 *
 * @author rizkykojek
 */
public class CareerEmpStatusModel implements Serializable {

	private Long id;
    private String code;
    private String name;
    private Integer limitTime;
    private Boolean isAutoMove;
    private Long empTypeId;
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
	public Integer getLimitTime() {
		return limitTime;
	}
	public void setLimitTime(Integer limitTime) {
		this.limitTime = limitTime;
	}
	public Boolean getIsAutoMove() {
		return isAutoMove;
	}
	public void setIsAutoMove(Boolean isAutoMove) {
		this.isAutoMove = isAutoMove;
	}
	public Long getEmpTypeId() {
		return empTypeId;
	}
	public void setEmpTypeId(Long empTypeId) {
		this.empTypeId = empTypeId;
	}
	public Long getSystemLetterReferenceId() {
		return systemLetterReferenceId;
	}
	public void setSystemLetterReferenceId(Long systemLetterReferenceId) {
		this.systemLetterReferenceId = systemLetterReferenceId;
	}
    
}
