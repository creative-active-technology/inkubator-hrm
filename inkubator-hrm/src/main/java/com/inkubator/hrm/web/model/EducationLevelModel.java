package com.inkubator.hrm.web.model;

import java.io.Serializable;

/**
 *
 * @author rizkykojek
 */
public class EducationLevelModel implements Serializable {

	private Long id;
	private String name;
        private String code;
        private String description;
	private Integer level;
	private Boolean isActive;
	private Boolean isActiveEditable;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	public Boolean getIsActiveEditable() {
		return isActiveEditable;
	}
	public void setIsActiveEditable(Boolean isActiveEditable) {
		this.isActiveEditable = isActiveEditable;
	}
	
	
}
