package com.inkubator.hrm.web.model;

import java.io.Serializable;
import java.math.BigInteger;

/**
 *
 * @author rizkykojek
 */
public class SelectionApplicantPassedViewModel implements Serializable {
	private BigInteger applicantId;
	private String applicantName;
	private Integer applicantCareerCandidate;
	private Double maxScore;
	private Double minScore;
	
	public BigInteger getApplicantId() {
		return applicantId;
	}
	public void setApplicantId(BigInteger applicantId) {
		this.applicantId = applicantId;
	}
	public String getApplicantName() {
		return applicantName;
	}
	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}
	public Integer getApplicantCareerCandidate() {
		return applicantCareerCandidate;
	}
	public void setApplicantCareerCandidate(Integer applicantCareerCandidate) {
		this.applicantCareerCandidate = applicantCareerCandidate;
	}
	public Double getMaxScore() {
		return maxScore;
	}
	public void setMaxScore(Double maxScore) {
		this.maxScore = maxScore;
	}
	public Double getMinScore() {
		return minScore;
	}
	public void setMinScore(Double minScore) {
		this.minScore = minScore;
	}
	
}
