package com.inkubator.hrm.web.model;

import java.io.Serializable;
import java.math.BigInteger;

/**
 *
 * @author rizkykojek
 */
public class SelectionApplicantPassedViewModel implements Serializable {
	private BigInteger applicantId;
	private BigInteger hireApplyId;
	private BigInteger bioDataId;
	private String applicantName;
	private Integer applicantCareerCandidate;
	private Double maxScore;
	private Double minScore;
	private String selectionTypeOfMaxScore;
	private String selectionTypeOfMinScore;
	private String placementStatus;
	
	
	public BigInteger getApplicantId() {
		return applicantId;
	}
	public void setApplicantId(BigInteger applicantId) {
		this.applicantId = applicantId;
	}
	public BigInteger getHireApplyId() {
		return hireApplyId;
	}
	public void setHireApplyId(BigInteger hireApplyId) {
		this.hireApplyId = hireApplyId;
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
	public String getSelectionTypeOfMaxScore() {
		return selectionTypeOfMaxScore;
	}
	public void setSelectionTypeOfMaxScore(String selectionTypeOfMaxScore) {
		this.selectionTypeOfMaxScore = selectionTypeOfMaxScore;
	}
	public String getSelectionTypeOfMinScore() {
		return selectionTypeOfMinScore;
	}
	public void setSelectionTypeOfMinScore(String selectionTypeOfMinScore) {
		this.selectionTypeOfMinScore = selectionTypeOfMinScore;
	}
	public String getPlacementStatus() {
		return placementStatus;
	}
	public void setPlacementStatus(String placementStatus) {
		this.placementStatus = placementStatus;
	}
	public BigInteger getBioDataId() {
		return bioDataId;
	}
	public void setBioDataId(BigInteger bioDataId) {
		this.bioDataId = bioDataId;
	}
		
	
}
