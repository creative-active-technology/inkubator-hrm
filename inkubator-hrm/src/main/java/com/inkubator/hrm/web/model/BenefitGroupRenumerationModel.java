package com.inkubator.hrm.web.model;

import java.io.Serializable;
/**
 *
 * @author Taufik Hidayat
 */
public class BenefitGroupRenumerationModel implements Serializable {

    private String name;
    private Double nominal;
    private Integer componentCategory; 
    private Boolean isBasicSalary;
    
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getComponentCategory() {
		return componentCategory;
	}

	public void setComponentCategory(Integer componentCategory) {
		this.componentCategory = componentCategory;
	}

	public Double getNominal() {
		return nominal;
	}

	public void setNominal(Double nominal) {
		this.nominal = nominal;
	}

	public Boolean getIsBasicSalary() {
		return isBasicSalary;
	}

	public void setIsBasicSalary(Boolean isBasicSalary) {
		this.isBasicSalary = isBasicSalary;
	}
	
}
