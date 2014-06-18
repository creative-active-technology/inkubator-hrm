package com.inkubator.hrm.web.model;

import java.io.Serializable;

/**
 *
 * @author Taufik Hidayat
 */
public class OccupationTypeModel implements Serializable {

	private Long id;
	private String occupationTypeName;
        private String description;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

    public String getOccupationTypeName() {
        return occupationTypeName;
    }

    public void setOccupationTypeName(String occupationTypeName) {
        this.occupationTypeName = occupationTypeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
	
	
	
}
