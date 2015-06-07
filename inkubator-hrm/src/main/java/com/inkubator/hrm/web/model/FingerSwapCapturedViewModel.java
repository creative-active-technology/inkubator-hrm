
package com.inkubator.hrm.web.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

/**
 *
 * @author rizkykojek
 */
public class FingerSwapCapturedViewModel implements Serializable {

	private BigInteger id;
	private String nik;
	private String employeeName;
	private String machineFingerName;
	private Date fingerSwapDate;
		
	
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	public String getNik() {
		return nik;
	}
	public void setNik(String nik) {
		this.nik = nik;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getMachineFingerName() {
		return machineFingerName;
	}
	public void setMachineFingerName(String machineFingerName) {
		this.machineFingerName = machineFingerName;
	}
	public Date getFingerSwapDate() {
		return fingerSwapDate;
	}
	public void setFingerSwapDate(Date fingerSwapDate) {
		this.fingerSwapDate = fingerSwapDate;
	}		
	
}
