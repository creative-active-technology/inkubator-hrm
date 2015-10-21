package com.inkubator.hrm.web.model;

import java.io.Serializable;

/**
 *
 * @author Ahmad mudzakkir Amal
 */
public class FingerMatchEmpViewModel implements Serializable{

    private Long empDataId;
    private String machineNikOrFingerIndex;
    private Long machineFingerId;
    private String empDataNik;
    private String empFullName;
    private Long jabatanId;
    private String jabatanName;

	public Long getEmpDataId() {
		return empDataId;
	}

	public void setEmpDataId(Long empDataId) {
		this.empDataId = empDataId;
	}

	public String getMachineNikOrFingerIndex() {
		return machineNikOrFingerIndex;
	}

	public void setMachineNikOrFingerIndex(String machineNikOrFingerIndex) {
		this.machineNikOrFingerIndex = machineNikOrFingerIndex;
	}

	public Long getMachineFingerId() {
		return machineFingerId;
	}

	public void setMachineFingerId(Long machineFingerId) {
		this.machineFingerId = machineFingerId;
	}

	public String getEmpDataNik() {
		return empDataNik;
	}

	public void setEmpDataNik(String empDataNik) {
		this.empDataNik = empDataNik;
	}

	public String getEmpFullName() {
		return empFullName;
	}

	public void setEmpFullName(String empFullName) {
		this.empFullName = empFullName;
	}

	public Long getJabatanId() {
		return jabatanId;
	}

	public void setJabatanId(Long jabatanId) {
		this.jabatanId = jabatanId;
	}

	public String getJabatanName() {
		return jabatanName;
	}

	public void setJabatanName(String jabatanName) {
		this.jabatanName = jabatanName;
	}
	
	
    
}
