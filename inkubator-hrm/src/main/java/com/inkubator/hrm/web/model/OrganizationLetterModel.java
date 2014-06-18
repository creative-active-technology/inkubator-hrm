package com.inkubator.hrm.web.model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author rizkykojek
 */
public class OrganizationLetterModel implements Serializable {

	private Long id;
	private String letterNumber;
	private Date letterDate;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLetterNumber() {
		return letterNumber;
	}
	public void setLetterNumber(String letterNumber) {
		this.letterNumber = letterNumber;
	}
	public Date getLetterDate() {
		return letterDate;
	}
	public void setLetterDate(Date letterDate) {
		this.letterDate = letterDate;
	}
	
	
}
