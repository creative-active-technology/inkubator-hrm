/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.model;

import java.io.Serializable;

/**
 *
 * @author deniarianto
 */
public class DepartmentModel implements Serializable {
    private Long id;
    private String code;
    private String name;
    private Long costCenterDeptId;

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

	public Long getCostCenterDeptId() {
		return costCenterDeptId;
	}

	public void setCostCenterDeptId(Long costCenterDeptId) {
		this.costCenterDeptId = costCenterDeptId;
	}
    
}
