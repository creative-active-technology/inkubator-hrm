package com.inkubator.hrm.web.model;

import java.io.Serializable;

public class KompetensiJabatanViewModel implements Serializable{
	private Long id;
	private String jabatanCode;
	private String jabatanName;
	private String golonganJabatanCode;
	private Integer jumlahPejabat;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getGolonganJabatanCode() {
		return golonganJabatanCode;
	}
	public void setGolonganJabatanCode(String golonganJabatanCode) {
		this.golonganJabatanCode = golonganJabatanCode;
	}
	public Integer getJumlahPejabat() {
		return jumlahPejabat;
	}
	public void setJumlahPejabat(Integer jumlahPejabat) {
		this.jumlahPejabat = jumlahPejabat;
	}
	
	
}
