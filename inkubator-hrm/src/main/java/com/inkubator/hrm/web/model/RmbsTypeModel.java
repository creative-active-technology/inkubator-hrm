package com.inkubator.hrm.web.model;

import java.io.Serializable;

/**
 *
 * @author rizkykojek
 */
public class RmbsTypeModel implements Serializable {

	private Long id;
	private String name;
	private String code;
    private String description;
    private Integer roundDigit;
    private Boolean isActive;
    private Long costCenterId;
    private Integer daysOfAvailable;
    
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getRoundDigit() {
		return roundDigit;
	}
	public void setRoundDigit(Integer roundDigit) {
		this.roundDigit = roundDigit;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	public Long getCostCenterId() {
		return costCenterId;
	}
	public void setCostCenterId(Long costCenterId) {
		this.costCenterId = costCenterId;
	}
	public Integer getDaysOfAvailable() {
		return daysOfAvailable;
	}
	public void setDaysOfAvailable(Integer daysOfAvailable) {
		this.daysOfAvailable = daysOfAvailable;
	}
	
}
