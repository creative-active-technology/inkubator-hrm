package com.inkubator.hrm.web.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

/**
 *
 * @author rizkykojek
 */
public class SelectionPositionPassedViewModel implements Serializable {
	private BigInteger positionId;
	private String positionName;
	private Integer candidateRequest;
	private BigDecimal candidatePassed;
	private Double avgMaxScore;
	private Double avgMinScore;
	private Double totalMaxScore;
	private Double totalMinScore;
	
	public BigInteger getPositionId() {
		return positionId;
	}
	public void setPositionId(BigInteger positionId) {
		this.positionId = positionId;
	}
	public String getPositionName() {
		return positionName;
	}
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	public Integer getCandidateRequest() {
		return candidateRequest;
	}
	public void setCandidateRequest(Integer candidateRequest) {
		this.candidateRequest = candidateRequest;
	}
	public BigDecimal getCandidatePassed() {
		return candidatePassed;
	}
	public void setCandidatePassed(BigDecimal candidatePassed) {
		this.candidatePassed = candidatePassed;
	}
	public Double getAvgMaxScore() {
		avgMaxScore =  0.0;
		if(totalMaxScore != 0.0 && candidatePassed.doubleValue() != 0.0){
			avgMaxScore = new BigDecimal(totalMaxScore).divide(candidatePassed, 2, RoundingMode.HALF_UP).doubleValue();
		}
		return avgMaxScore;
	}
	public void setAvgMaxScore(Double avgMaxScore) {
		this.avgMaxScore = avgMaxScore;
	}
	public Double getAvgMinScore() {
		avgMinScore =  0.0;
		if(totalMinScore != 0.0 && candidatePassed.doubleValue() != 0.0){
			avgMinScore = new BigDecimal(totalMinScore).divide(candidatePassed, 2, RoundingMode.HALF_UP).doubleValue();
		}
		return avgMinScore;
	}
	public void setAvgMinScore(Double avgMinScore) {
		this.avgMinScore = avgMinScore;
	}
	public Double getTotalMaxScore() {
		return totalMaxScore;
	}
	public void setTotalMaxScore(Double totalMaxScore) {
		this.totalMaxScore = totalMaxScore;
	}
	public Double getTotalMinScore() {
		return totalMinScore;
	}
	public void setTotalMinScore(Double totalMinScore) {
		this.totalMinScore = totalMinScore;
	}
}
