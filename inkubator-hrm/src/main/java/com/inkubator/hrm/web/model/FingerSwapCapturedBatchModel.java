/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author rizkykojek
 */
@XmlRootElement(name = "Row")
public class FingerSwapCapturedBatchModel implements Serializable{
    
	private String nik;
    private String dateTime;
    private String createdBy;
    private Long machineId;
	
    @XmlElement(name = "PIN")
	public String getNik() {
		return nik;
	}
	public void setNik(String nik) {
		this.nik = nik;
	}
	@XmlElement(name = "DateTime")
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}		
	public String getCreatedBy() {
		return createdBy;
	}	
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Long getMachineId() {
		return machineId;
	}
	public void setMachineId(Long machineId) {
		this.machineId = machineId;
	}
	
	
}
