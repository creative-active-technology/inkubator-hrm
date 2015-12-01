/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.model;

import com.inkubator.hrm.entity.EmpData;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Deni
 */
public class PersonalDisciplineModel implements Serializable{
    private Long id;
    private Long admonitionType;
    private Long empDataId;
    private Date startDate;
    private Date expireDate;
    private String description;
    private String nikWithFullName;
    private EmpData empData;
    private Long careerDisciplineTypeId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAdmonitionType() {
        return admonitionType;
    }

    public void setAdmonitionType(Long admonitionType) {
        this.admonitionType = admonitionType;
    }

    public Long getEmpDataId() {
        return empDataId;
    }

    public void setEmpDataId(Long empDataId) {
        this.empDataId = empDataId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EmpData getEmpData() {
        return empData;
    }

    public void setEmpData(EmpData empData) {
        this.empData = empData;
    }

    public String getNikWithFullName() {
        return nikWithFullName;
    }

    public void setNikWithFullName(String nikWithFullName) {
        this.nikWithFullName = nikWithFullName;
    }

	public Long getCareerDisciplineTypeId() {
		return careerDisciplineTypeId;
	}

	public void setCareerDisciplineTypeId(Long careerDisciplineTypeId) {
		this.careerDisciplineTypeId = careerDisciplineTypeId;
	}
    
    
    
}
