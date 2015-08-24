package com.inkubator.hrm.web.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import com.inkubator.hrm.HRMConstant;

/**
 *
 * @author rizkykojek
 */
public class BusinessTravelViewModel implements Serializable {
	
	private BigInteger approvalActivityId;
	private BigInteger businessTravelId;
	private String empNik;
	private String empName;
	private String businessTravelNo;
	private Integer approvalStatus;
	private Integer businessTravelStatus;
	private String destination;
	private Date startDate;
	private Date endDate;
	private String jsonData;
	private Boolean isEditable;
	private Boolean isRemoveable;
	
	public BigInteger getApprovalActivityId() {
		return approvalActivityId;
	}
	public void setApprovalActivityId(BigInteger approvalActivityId) {
		this.approvalActivityId = approvalActivityId;
	}
	public BigInteger getBusinessTravelId() {
		return businessTravelId;
	}
	public void setBusinessTravelId(BigInteger businessTravelId) {
		this.businessTravelId = businessTravelId;
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
	public String getBusinessTravelNo() {
		return businessTravelNo;
	}
	public void setBusinessTravelNo(String businessTravelNo) {
		this.businessTravelNo = businessTravelNo;
	}
	public Integer getApprovalStatus() {
		return approvalStatus;
	}
	public void setApprovalStatus(Integer approvalStatus) {
		this.approvalStatus = approvalStatus;
	}
	public Integer getBusinessTravelStatus() {
		if(businessTravelStatus == null){
			businessTravelStatus = approvalStatus;
		}
		return businessTravelStatus;
	}
	public void setBusinessTravelStatus(Integer businessTravelStatus) {
		this.businessTravelStatus = businessTravelStatus;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getJsonData() {
		return jsonData;
	}
	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}
	public Boolean getIsEditable() {
		/** hanya bisa di edit, jika statusnya sudah di approved tapi masih menunggu pelaksanaanya */
		boolean isEditable = businessTravelStatus != null && businessTravelStatus.equals(HRMConstant.BUSINESS_TRAVEL_STATUS_WAITING); 		
		return isEditable;
	}
	public void setIsEditable(Boolean isEditable) {
		this.isEditable = isEditable;
	}
	public Boolean getIsRemoveable() {
		/** hanya bisa di delete, jika statusnya sudah di approved (artinya sudah memiliki businessTravelId) */
		boolean isRemoveable = businessTravelId != null && businessTravelId.intValue() != 0;
		return isRemoveable;
	}
	public void setIsRemoveable(Boolean isRemoveable) {
		this.isRemoveable = isRemoveable;
	}
	
	
}
