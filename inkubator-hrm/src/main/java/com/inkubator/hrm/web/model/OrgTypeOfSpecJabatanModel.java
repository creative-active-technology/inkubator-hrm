/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.model;

import java.io.Serializable;

/**
 *
 * @author Deni
 */
public class OrgTypeOfSpecJabatanModel implements Serializable {

    private String jabatanCode;
    private String jabatanName;
    private Long jabatanId;
    private Long orgTypeOfSpecListId;
    private Long orgTypeOfSpecId;

    public Long getJabatanId() {
        return jabatanId;
    }

    public void setJabatanId(Long jabatanId) {
        this.jabatanId = jabatanId;
    }

    public Long getOrgTypeOfSpecListId() {
        return orgTypeOfSpecListId;
    }

    public void setOrgTypeOfSpecListId(Long orgTypeOfSpecListId) {
        this.orgTypeOfSpecListId = orgTypeOfSpecListId;
    }

    public String getJabatanCode() {
        return jabatanCode;
    }

    public void setJabatanCode(String jabatanCode) {
        this.jabatanCode = jabatanCode;
    }

    public String getJabatanName() {
        return jabatanName;
    }

    public void setJabatanName(String jabatanName) {
        this.jabatanName = jabatanName;
    }

	public Long getOrgTypeOfSpecId() {
		return orgTypeOfSpecId;
	}

	public void setOrgTypeOfSpecId(Long orgTypeOfSpecId) {
		this.orgTypeOfSpecId = orgTypeOfSpecId;
	}
    
    
}
