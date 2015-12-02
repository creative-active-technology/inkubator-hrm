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
public class EmpPersonAchievementModel implements Serializable{
    private Long id;
    private long empDataId;
    private String achievementName;
    private String description;
    private Date dateAchievement;
    private EmpData empData;
    private String nikWithFullName;
    private Long careerAwardTypeId;

    public long getEmpDataId() {
        return empDataId;
    }

    public void setEmpDataId(long empDataId) {
        this.empDataId = empDataId;
    }

    public String getAchievementName() {
        return achievementName;
    }

    public void setAchievementName(String achievementName) {
        this.achievementName = achievementName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateAchievement() {
        return dateAchievement;
    }

    public void setDateAchievement(Date dateAchievement) {
        this.dateAchievement = dateAchievement;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

	public Long getCareerAwardTypeId() {
		return careerAwardTypeId;
	}

	public void setCareerAwardTypeId(Long careerAwardTypeId) {
		this.careerAwardTypeId = careerAwardTypeId;
	}
    
    
}
