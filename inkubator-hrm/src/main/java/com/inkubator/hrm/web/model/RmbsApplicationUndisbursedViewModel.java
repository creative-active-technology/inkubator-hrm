package com.inkubator.hrm.web.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 *
 * @author rizkykojek
 */
public class RmbsApplicationUndisbursedViewModel implements Serializable{

	private BigInteger approvalActivityId;
	private BigInteger rmbsApplicationId;
	private String empNik;
	private String empName;
	private String rmbsApplicationCode;
	private BigInteger rmbsTypeId;
	private String rmbsTypeName;
	private String attachment;
	private BigDecimal nominal;
	private Integer approvalStatus;
	private Boolean isHaveAttachment;
	private String jsonData;
	
	public BigInteger getApprovalActivityId() {
		return approvalActivityId;
	}
	public void setApprovalActivityId(BigInteger approvalActivityId) {
		this.approvalActivityId = approvalActivityId;
	}
	public String getEmpNik() {
		return empNik;
	}
	public void setEmpNik(String empNik) {
		this.empNik = empNik;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getRmbsApplicationCode() {
		return rmbsApplicationCode;
	}
	public void setRmbsApplicationCode(String rmbsApplicationCode) {
		this.rmbsApplicationCode = rmbsApplicationCode;
	}
	public String getRmbsTypeName() {
		return rmbsTypeName;
	}
	public void setRmbsTypeName(String rmbsTypeName) {
		this.rmbsTypeName = rmbsTypeName;
	}
	public String getAttachment() {
		return attachment;
	}
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}
	public BigDecimal getNominal() {
		return nominal;
	}
	public void setNominal(BigDecimal nominal) {
		this.nominal = nominal;
	}
	public Integer getApprovalStatus() {
		return approvalStatus;
	}
	public void setApprovalStatus(Integer approvalStatus) {
		this.approvalStatus = approvalStatus;
	}
	public BigInteger getRmbsTypeId() {
		return rmbsTypeId;
	}
	public void setRmbsTypeId(BigInteger rmbsTypeId) {
		this.rmbsTypeId = rmbsTypeId;
	}
	public Boolean getIsHaveAttachment() {
		return isHaveAttachment;
	}
	public void setIsHaveAttachment(Boolean isHaveAttachment) {
		this.isHaveAttachment = isHaveAttachment;
	}
	public String getJsonData() {
		return jsonData;
	}
	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}
	public BigInteger getRmbsApplicationId() {
		return rmbsApplicationId;
	}
	public void setRmbsApplicationId(BigInteger rmbsApplicationId) {
		this.rmbsApplicationId = rmbsApplicationId;
	}
	
}
