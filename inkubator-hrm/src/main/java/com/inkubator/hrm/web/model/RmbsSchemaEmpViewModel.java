package com.inkubator.hrm.web.model;

import java.io.Serializable;

/**
 *
 * @author rizkykojek
 */
public class RmbsSchemaEmpViewModel implements Serializable{

	private Long empDataId;
	private Long rmbsSchemaId;
	private String empNik;
	private String empName;
	private String empGolJabatan;
	private String rmbsSchemaCode;
        private String rmbsSchemaName;
	private String nomorSK;
	
	public Long getEmpDataId() {
		return empDataId;
	}
	public void setEmpDataId(Long empDataId) {
		this.empDataId = empDataId;
	}
	public Long getRmbsSchemaId() {
		return rmbsSchemaId;
	}
	public void setRmbsSchemaId(Long rmbsSchemaId) {
		this.rmbsSchemaId = rmbsSchemaId;
	}
	public String getEmpNik() {
		return empNik;
	}
	public void setEmpNik(String empNik) {
		this.empNik = empNik;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmpGolJabatan() {
		return empGolJabatan;
	}
	public void setEmpGolJabatan(String empGolJabatan) {
		this.empGolJabatan = empGolJabatan;
	}
	public String getRmbsSchemaCode() {
		return rmbsSchemaCode;
	}
	public void setRmbsSchemaCode(String rmbsSchemaCode) {
		this.rmbsSchemaCode = rmbsSchemaCode;
	}
	public String getNomorSK() {
		return nomorSK;
	}
	public void setNomorSK(String nomorSK) {
		this.nomorSK = nomorSK;
	}	

        public String getRmbsSchemaName() {
            return rmbsSchemaName;
        }

        public void setRmbsSchemaName(String rmbsSchemaName) {
            this.rmbsSchemaName = rmbsSchemaName;
        }
	
}
