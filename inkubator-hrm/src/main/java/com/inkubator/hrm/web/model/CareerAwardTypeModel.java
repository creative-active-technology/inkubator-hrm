package com.inkubator.hrm.web.model;

import java.io.Serializable;

public class CareerAwardTypeModel implements Serializable{
	
	private Long id;
	private String code;
	private String name;
	private String description;
	private Integer validity;
	private Double point;
	private Long systemLetterReferenceByLetterTemplateId;
	private Long systemLetterReferenceByCertificateLetterTemplateId;
	private Boolean awardSource;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getValidity() {
		return validity;
	}
	public void setValidity(Integer validity) {
		this.validity = validity;
	}
	public Double getPoint() {
		return point;
	}
	public void setPoint(Double point) {
		this.point = point;
	}
	public Long getSystemLetterReferenceByLetterTemplateId() {
		return systemLetterReferenceByLetterTemplateId;
	}
	public void setSystemLetterReferenceByLetterTemplateId(Long systemLetterReferenceByLetterTemplateId) {
		this.systemLetterReferenceByLetterTemplateId = systemLetterReferenceByLetterTemplateId;
	}
	public Long getSystemLetterReferenceByCertificateLetterTemplateId() {
		return systemLetterReferenceByCertificateLetterTemplateId;
	}
	public void setSystemLetterReferenceByCertificateLetterTemplateId(
			Long systemLetterReferenceByCertificateLetterTemplateId) {
		this.systemLetterReferenceByCertificateLetterTemplateId = systemLetterReferenceByCertificateLetterTemplateId;
	}
	public Boolean getAwardSource() {
		return awardSource;
	}
	public void setAwardSource(Boolean awardSource) {
		this.awardSource = awardSource;
	}

	
}
