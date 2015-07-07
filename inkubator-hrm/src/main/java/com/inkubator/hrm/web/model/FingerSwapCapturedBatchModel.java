/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.model;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author rizkykojek
 */
@XmlRootElement(name = "Row")
public class FingerSwapCapturedBatchModel implements Serializable{
    
	private String fingerIndexId;
    private String dateTime;
    private String createdBy;
    private Date createdOn;
    private Long machineId;
	
    @XmlElement(name = "PIN")
    public String getFingerIndexId() {
		return fingerIndexId;
	}
	public void setFingerIndexId(String fingerIndexId) {
		this.fingerIndexId = fingerIndexId;
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
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}	
	
}
