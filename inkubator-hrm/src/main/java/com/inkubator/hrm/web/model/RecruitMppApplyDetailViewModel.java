package com.inkubator.hrm.web.model;

import java.io.Serializable;

/**
*
* @author Ahmad Mudzakkir Amal
*/
public class RecruitMppApplyDetailViewModel implements Serializable {
	
	private Long id;
	private Long jabatanId;
	private String jabatanKode;
	private String jabatanName;
	private Long recruitMppApplyId;
	private Long mppPeriodId;
	private Integer actual;
	private Integer mpp;
	private Integer difference;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getJabatanId() {
		return jabatanId;
	}
	public void setJabatanId(Long jabatanId) {
		this.jabatanId = jabatanId;
	}
	public String getJabatanKode() {
		return jabatanKode;
	}
	public void setJabatanKode(String jabatanKode) {
		this.jabatanKode = jabatanKode;
	}
	public String getJabatanName() {
		return jabatanName;
	}
	public void setJabatanName(String jabatanName) {
		this.jabatanName = jabatanName;
	}
	public Long getRecruitMppApplyId() {
		return recruitMppApplyId;
	}
	public void setRecruitMppApplyId(Long recruitMppApplyId) {
		this.recruitMppApplyId = recruitMppApplyId;
	}
	public Long getMppPeriodId() {
		return mppPeriodId;
	}
	public void setMppPeriodId(Long mppPeriodId) {
		this.mppPeriodId = mppPeriodId;
	}
	public Integer getActual() {
		return actual;
	}
	public void setActual(Integer actual) {
		this.actual = actual;
	}
	public Integer getMpp() {
		return mpp;
	}
	public void setMpp(Integer mpp) {
		this.mpp = mpp;
	}
	public Integer getDifference() {
		return difference;
	}
	public void setDifference(Integer difference) {
		this.difference = difference;
	}
	
	
	
}
