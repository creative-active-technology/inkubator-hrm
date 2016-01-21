package com.inkubator.hrm.web.model;

import java.io.Serializable;

/**
*
* @author rizkykojek
*/
public class PerformanceIndicatorJabatanViewModel implements Serializable{
	private Long jabatanId;
	private String jabatanCode;
	private String jabatanName;
	private String golonganJabatan;
	private Long totalEmployee;
	
	public Long getJabatanId() {
		return jabatanId;
	}
	public void setJabatanId(Long jabatanId) {
		this.jabatanId = jabatanId;
	}
	public String getJabatanCode() {
		return jabatanCode;
	}
	public void setJabatanCode(String jabatanCode) {
		this.jabatanCode = jabatanCode;
	}
	public String getJabatanName() {
		return jabatanName;
	}
	public void setJabatanName(String jabatanName) {
		this.jabatanName = jabatanName;
	}
	public String getGolonganJabatan() {
		return golonganJabatan;
	}
	public void setGolonganJabatan(String golonganJabatan) {
		this.golonganJabatan = golonganJabatan;
	}
	public Long getTotalEmployee() {
		return totalEmployee;
	}
	public void setTotalEmployee(Long totalEmployee) {
		this.totalEmployee = totalEmployee;
	}
	
}
