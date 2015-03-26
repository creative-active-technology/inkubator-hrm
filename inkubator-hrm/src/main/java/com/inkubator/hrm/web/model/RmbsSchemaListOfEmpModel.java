package com.inkubator.hrm.web.model;

import java.io.Serializable;

/**
 *
 * @author rizkykojek
 */
public class RmbsSchemaListOfEmpModel implements Serializable {
	
	private Long rmbsSchemaId;
	private Long empDataId;
	private String nomorSk;
	private String description;
	
	public Long getRmbsSchemaId() {
		return rmbsSchemaId;
	}
	public void setRmbsSchemaId(Long rmbsSchemaId) {
		this.rmbsSchemaId = rmbsSchemaId;
	}
	public Long getEmpDataId() {
		return empDataId;
	}
	public void setEmpDataId(Long empDataId) {
		this.empDataId = empDataId;
	}
	public String getNomorSk() {
		return nomorSk;
	}
	public void setNomorSk(String nomorSk) {
		this.nomorSk = nomorSk;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
