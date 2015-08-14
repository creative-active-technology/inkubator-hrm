/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.model;

import java.io.Serializable;
import java.math.BigInteger;

/**
 *
 * @author Deni
 */
public class LeaveImplementationDateModel implements Serializable {
	private Long totalData;
	private Integer total;
	private BigInteger totalPakai;
	private String leaveDistributionName;
	private Double balance;
	private Double percentage;
	
	public Long getTotalData() {
		return totalData;
	}
	public void setTotalData(Long totalData) {
		this.totalData = totalData;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public BigInteger getTotalPakai() {
		return totalPakai;
	}
	public void setTotalPakai(BigInteger totalPakai) {
		this.totalPakai = totalPakai;
	}
	public String getLeaveDistributionName() {
		return leaveDistributionName;
	}
	public void setLeaveDistributionName(String leaveDistributionName) {
		this.leaveDistributionName = leaveDistributionName;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public Double getPercentage() {
		return percentage;
	}
	public void setPercentage(Double percentage) {
		this.percentage = percentage;
	}
	
	
    
}
