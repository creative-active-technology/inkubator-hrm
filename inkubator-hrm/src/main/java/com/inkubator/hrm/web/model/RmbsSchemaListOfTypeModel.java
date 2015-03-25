package com.inkubator.hrm.web.model;


/**
 *
 * @author rizkykojek
 */
public class RmbsSchemaListOfTypeModel {

	private Long rmbsSchemaId;
	private Long rmbsTypeId;
	private String rmbsTypeName;
	private Double limitPerClaim;
	private Double maxPerMonth;
	private Integer periodMethod;
		
	public Long getRmbsSchemaId() {
		return rmbsSchemaId;
	}
	public void setRmbsSchemaId(Long rmbsSchemaId) {
		this.rmbsSchemaId = rmbsSchemaId;
	}
	public Long getRmbsTypeId() {
		return rmbsTypeId;
	}
	public void setRmbsTypeId(Long rmbsTypeId) {
		this.rmbsTypeId = rmbsTypeId;
	}
	public String getRmbsTypeName() {
		return rmbsTypeName;
	}
	public void setRmbsTypeName(String rmbsTypeName) {
		this.rmbsTypeName = rmbsTypeName;
	}
	public Double getLimitPerClaim() {
		return limitPerClaim;
	}
	public void setLimitPerClaim(Double limitPerClaim) {
		this.limitPerClaim = limitPerClaim;
	}
	public Double getMaxPerMonth() {
		return maxPerMonth;
	}
	public void setMaxPerMonth(Double maxPerMonth) {
		this.maxPerMonth = maxPerMonth;
	}
	public Integer getPeriodMethod() {
		return periodMethod;
	}
	public void setPeriodMethod(Integer periodMethod) {
		this.periodMethod = periodMethod;
	}	
	
}
