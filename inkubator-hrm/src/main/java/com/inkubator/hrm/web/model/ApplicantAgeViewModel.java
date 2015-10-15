package com.inkubator.hrm.web.model;

import java.io.Serializable;

/**
 *
 * @author rizkykojek
 */
public class ApplicantAgeViewModel implements Serializable {

	private Long femaleAgeBelow25;
	private Long maleAgeBelow25;
	private Long femaleAgeBetween25And30;
	private Long maleAgeBetween25And30;
	private Long femaleAgeAbove30;
	private Long maleAgeAbove30;
	private Long femaleMarried;
	private Long maleMarried;
	private String candidate;
	
	public Long getFemaleAgeBelow25() {
		return femaleAgeBelow25;
	}
	public void setFemaleAgeBelow25(Long femaleAgeBelow25) {
		this.femaleAgeBelow25 = femaleAgeBelow25;
	}
	public Long getMaleAgeBelow25() {
		return maleAgeBelow25;
	}
	public void setMaleAgeBelow25(Long maleAgeBelow25) {
		this.maleAgeBelow25 = maleAgeBelow25;
	}
	public Long getFemaleAgeBetween25And30() {
		return femaleAgeBetween25And30;
	}
	public void setFemaleAgeBetween25And30(Long femaleAgeBetween25And30) {
		this.femaleAgeBetween25And30 = femaleAgeBetween25And30;
	}
	public Long getMaleAgeBetween25And30() {
		return maleAgeBetween25And30;
	}
	public void setMaleAgeBetween25And30(Long maleAgeBetween25And30) {
		this.maleAgeBetween25And30 = maleAgeBetween25And30;
	}
	public Long getFemaleAgeAbove30() {
		return femaleAgeAbove30;
	}
	public void setFemaleAgeAbove30(Long femaleAgeAbove30) {
		this.femaleAgeAbove30 = femaleAgeAbove30;
	}
	public Long getMaleAgeAbove30() {
		return maleAgeAbove30;
	}
	public void setMaleAgeAbove30(Long maleAgeAbove30) {
		this.maleAgeAbove30 = maleAgeAbove30;
	}
	public Long getFemaleMarried() {
		return femaleMarried;
	}
	public void setFemaleMarried(Long femaleMarried) {
		this.femaleMarried = femaleMarried;
	}
	public Long getMaleMarried() {
		return maleMarried;
	}
	public void setMaleMarried(Long maleMarried) {
		this.maleMarried = maleMarried;
	}
	public String getCandidate() {
		return candidate;
	}
	public void setCandidate(String candidate) {
		this.candidate = candidate;
	}
	
}
