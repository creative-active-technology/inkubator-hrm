/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

/**
 *
 * @author Deni
 */
public class CareerTransitionInboxViewModel implements Serializable {
    
    private BigInteger approvalActivityId;
    private BigInteger empCareerHistoryId;
    private String empNik;
    private String empName;
    private String rmbsApplicationCode;
    private Integer approvalStatus;
    private String jsonData;
    private String jabatanName;
    private Date requestTime;

    public BigInteger getApprovalActivityId() {
        return approvalActivityId;
    }

    public void setApprovalActivityId(BigInteger approvalActivityId) {
        this.approvalActivityId = approvalActivityId;
    }

    public BigInteger getEmpCareerHistoryId() {
        return empCareerHistoryId;
    }

    public void setEmpCareerHistoryId(BigInteger empCareerHistoryId) {
        this.empCareerHistoryId = empCareerHistoryId;
    }

    public String getEmpNik() {
        return empNik;
    }

    public void setEmpNik(String empNik) {
        this.empNik = empNik;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getRmbsApplicationCode() {
        return rmbsApplicationCode;
    }

    public void setRmbsApplicationCode(String rmbsApplicationCode) {
        this.rmbsApplicationCode = rmbsApplicationCode;
    }

    public Integer getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(Integer approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public String getJsonData() {
        return jsonData;
    }

    public void setJsonData(String jsonData) {
        this.jsonData = jsonData;
    }

	public String getJabatanName() {
		return jabatanName;
	}

	public void setJabatanName(String jabatanName) {
		this.jabatanName = jabatanName;
	}

	public Date getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(Date requestTime) {
		this.requestTime = requestTime;
	}
    
    
}
