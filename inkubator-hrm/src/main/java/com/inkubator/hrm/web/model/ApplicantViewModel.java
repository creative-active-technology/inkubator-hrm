package com.inkubator.hrm.web.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author rizkykojek
 */
public class ApplicantViewModel implements Serializable {

	private Long totalInternal;
	private Long totalExternal;
	private BigDecimal totalInternalDecimal;
	private BigDecimal totalExternalDecimal;
	private Long totalFreshGraduate;
	private Long totalNotFreshGraduate;
	private String name;
	
	public Long getTotalInternal() {
		return totalInternal;
	}
	public void setTotalInternal(Long totalInternal) {
		this.totalInternal = totalInternal;
	}
	public Long getTotalExternal() {
		return totalExternal;
	}
	public void setTotalExternal(Long totalExternal) {
		this.totalExternal = totalExternal;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getTotalFreshGraduate() {
		return totalFreshGraduate;
	}
	public void setTotalFreshGraduate(Long totalFreshGraduate) {
		this.totalFreshGraduate = totalFreshGraduate;
	}
	public Long getTotalNotFreshGraduate() {
		return totalNotFreshGraduate;
	}
	public void setTotalNotFreshGraduate(Long totalNotFreshGraduate) {
		this.totalNotFreshGraduate = totalNotFreshGraduate;
	}
	public BigDecimal getTotalInternalDecimal() {
		return totalInternalDecimal;
	}
	public void setTotalInternalDecimal(BigDecimal totalInternalDecimal) {
		this.totalInternalDecimal = totalInternalDecimal;
	}
	public BigDecimal getTotalExternalDecimal() {
		return totalExternalDecimal;
	}
	public void setTotalExternalDecimal(BigDecimal totalExternalDecimal) {
		this.totalExternalDecimal = totalExternalDecimal;
	}	
}
