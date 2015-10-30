package com.inkubator.hrm.web.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
public class RecruitmentScheduleSettingViewModel implements Serializable {
	
	private Long id;
	private Long jabatanId;
	private Long mppPeriodId;
	private String jabatanName;
	private Long totalApplicant;
	private Integer candidateCountRequest;
	private Date startMppPeriodDate;
	private Date endMppPeriodDate;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getJabatanId() {
		return jabatanId;
	}

	public void setJabatanId(Long jabatanId) {
		this.jabatanId = jabatanId;
	}

	public Long getMppPeriodId() {
		return mppPeriodId;
	}

	public void setMppPeriodId(Long mppPeriodId) {
		this.mppPeriodId = mppPeriodId;
	}

	public String getJabatanName() {
		return jabatanName;
	}

	public void setJabatanName(String jabatanName) {
		this.jabatanName = jabatanName;
	}

	public Long getTotalApplicant() {
		return totalApplicant;
	}

	public void setTotalApplicant(Long totalApplicant) {
		this.totalApplicant = totalApplicant;
	}

	public Integer getCandidateCountRequest() {
		return candidateCountRequest;
	}

	public void setCandidateCountRequest(Integer candidateCountRequest) {
		this.candidateCountRequest = candidateCountRequest;
	}

	public Date getStartMppPeriodDate() {
		return startMppPeriodDate;
	}

	public void setStartMppPeriodDate(Date startMppPeriodDate) {
		this.startMppPeriodDate = startMppPeriodDate;
	}

	public Date getEndMppPeriodDate() {
		return endMppPeriodDate;
	}

	public void setEndMppPeriodDate(Date endMppPeriodDate) {
		this.endMppPeriodDate = endMppPeriodDate;
	}
	
	
	
	
}
