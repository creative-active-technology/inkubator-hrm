package com.inkubator.hrm.web.model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Taufik Hidayat
 */
public class ReportPermitHistoryModel implements Serializable {

	private Long id;
    private String nikWithFullName;
    private String numberFilling;
    private String permitClassification;
    private String approvedBy;
    private Date startDate;
    private Date endDate;
    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNikWithFullName() {
        return nikWithFullName;
    }

    public void setNikWithFullName(String nikWithFullName) {
        this.nikWithFullName = nikWithFullName;
    }

    public String getNumberFilling() {
        return numberFilling;
    }

    public void setNumberFilling(String numberFilling) {
        this.numberFilling = numberFilling;
    }

    public String getPermitClassification() {
        return permitClassification;
    }

    public void setPermitClassification(String permitClassification) {
        this.permitClassification = permitClassification;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    
    
    

}
