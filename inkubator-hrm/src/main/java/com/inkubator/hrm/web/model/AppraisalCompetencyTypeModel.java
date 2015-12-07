package com.inkubator.hrm.web.model;


import java.io.Serializable;


/**
 *
 * @author Ahmad Mudzakkir Amal
 */
public class AppraisalCompetencyTypeModel implements Serializable{
    private Long id;
    private String code;
    private String name;
    private String description;
    private Byte visibility;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

	public Byte getVisibility() {
		return visibility;
	}

	public void setVisibility(Byte visibility) {
		this.visibility = visibility;
	}

  
    
    
}
