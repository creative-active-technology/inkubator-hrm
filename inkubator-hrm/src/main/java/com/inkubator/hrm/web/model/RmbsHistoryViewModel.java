package com.inkubator.hrm.web.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author rizkykojek
 */
public class RmbsHistoryViewModel implements Serializable {
	
	private Long rmbsTypeId;
	private String rmbsTypeName;
	private BigDecimal totalNominal;
	private BigDecimal maxNominal;	
	private Double percentage;
	
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
	public BigDecimal getTotalNominal() {
		return totalNominal;
	}
	public void setTotalNominal(BigDecimal totalNominal) {
		this.totalNominal = totalNominal;
	}
	public BigDecimal getMaxNominal() {
		return maxNominal;
	}
	public void setMaxNominal(BigDecimal maxNominal) {
		this.maxNominal = maxNominal;
	}
	public Double getPercentage() {
		BigDecimal per = totalNominal.divide(maxNominal, 4, RoundingMode.UP).multiply(new BigDecimal(100));
		return per.doubleValue();
	}
	public void setPercentage(Double percentage) {
		this.percentage = percentage;
	}
	
}
