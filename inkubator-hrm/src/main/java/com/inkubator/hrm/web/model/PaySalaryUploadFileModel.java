/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.model;

import java.io.Serializable;

/**
 *
 * @author rizkykojek
 */
public class PaySalaryUploadFileModel implements Serializable{
    
	private String nik;
    private String nominal;
    private Long paySalaryComponentId;
    private String pathUpload;
    private String createdBy;    	
	
	public String getNik() {
		return nik;
	}
	public void setNik(String nik) {
		this.nik = nik;
	}
	public String getNominal() {
		return nominal;
	}
	public void setNominal(String nominal) {
		this.nominal = nominal;
	}
	public Long getPaySalaryComponentId() {
		return paySalaryComponentId;
	}
	public void setPaySalaryComponentId(Long paySalaryComponentId) {
		this.paySalaryComponentId = paySalaryComponentId;
	}
	public String getPathUpload() {
		return pathUpload;
	}
	public void setPathUpload(String pathUpload) {
		this.pathUpload = pathUpload;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
}
