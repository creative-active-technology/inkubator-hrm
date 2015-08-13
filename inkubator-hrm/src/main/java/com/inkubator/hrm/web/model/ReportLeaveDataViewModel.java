/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
public class ReportLeaveDataViewModel implements Serializable{
    private Long id;
    private Date leaveDate;
    private Long leaveImplementationId;
    private String nik;
    private String firstName;
    private String lastName;
    private Long leaveId;
    private String leaveName;
    private String numberFilling;
    private String activityNumber;
    private String lastApproverNik;
    private String lastApproverName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

   
	public Date getLeaveDate() {
		return leaveDate;
	}

	public void setLeaveDate(Date leaveDate) {
		this.leaveDate = leaveDate;
	}

	public String getNik() {
		return nik;
	}

	public void setNik(String nik) {
		this.nik = nik;
	}

	public Long getLeaveId() {
		return leaveId;
	}

	public void setLeaveId(Long leaveId) {
		this.leaveId = leaveId;
	}

	public String getLeaveName() {
		return leaveName;
	}

	public void setLeaveName(String leaveName) {
		this.leaveName = leaveName;
	}

	public String getNumberFilling() {
		return numberFilling;
	}

	public void setNumberFilling(String numberFilling) {
		this.numberFilling = numberFilling;
	}

	public String getLastApproverNik() {
		return lastApproverNik;
	}

	public void setLastApproverNik(String lastApproverNik) {
		this.lastApproverNik = lastApproverNik;
	}

	public String getLastApproverName() {
		return lastApproverName;
	}

	public void setLastApproverName(String lastApproverName) {
		this.lastApproverName = lastApproverName;
	}

	public Long getLeaveImplementationId() {
		return leaveImplementationId;
	}

	public void setLeaveImplementationId(Long leaveImplementationId) {
		this.leaveImplementationId = leaveImplementationId;
	}

	public String getActivityNumber() {
		return activityNumber;
	}

	public void setActivityNumber(String activityNumber) {
		this.activityNumber = activityNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	

}
